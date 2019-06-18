package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserModel;
import util.Digest;
import vo.UserVo;

/**
 * アカウント登録完了画面表示サーブレット
 * ユーザーが登録確認したユーザーネーム、メールアドレス、パスワードの情報をDBに格納するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/InsertAccountCompleteServlet")
public class InsertAccountCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertAccountCompleteServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		} else {
			if (session.getAttribute("userName") == null) {
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				return;
			}
		}
		String userName = (String) session.getAttribute("userName");
		String mailAdd = (String) session.getAttribute("mailAdd");
		String password = (String) session.getAttribute("password");
		Digest digest = new Digest(Digest.SHA512);
		String hashPass = digest.hex(password);
		String sendPageToken = req.getParameter("pageToken");
		String sessionPageToken = (String) session.getAttribute("token");
		String errorReason;
		if (sessionPageToken == null || sendPageToken == null) {
			errorReason = "正規の順序でアクセスしていません";
			req.setAttribute("errorReason", errorReason);
			final String url = "/jsp/P020.jsp";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		} else {
			if (!(sendPageToken.equals(sessionPageToken))) {
				errorReason = "正規の順序でアクセスしていません";
				req.setAttribute("errorReason", errorReason);
				final String url = "/jsp/p020.jsp";
				req.getRequestDispatcher(url).forward(req, res);
				return;
			}
		}

		UserModel lm = new UserModel();
		try {
			lm.insertAccount(userName, mailAdd, hashPass);
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}
		UserVo loginUser = null;
		UserModel um = new UserModel();
		try {
			loginUser = um.getLoginUser(mailAdd, hashPass);
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}
		String isLogin = "true";
		String isNullCheck = "true";
		session.setAttribute("isNullCheck", isNullCheck);
		session.setAttribute("loginUser", loginUser);
		session.setAttribute("isLogin", isLogin);
		session.setAttribute("userId", loginUser.getUserId());
		session.setAttribute("userRole", loginUser.getUserRole());
		session.setAttribute("userName", loginUser.getUserName());
		String isOrdered = (String) session.getAttribute("isOrdered");
		final String url;
		if(isOrdered==null) {
			url = "InsertAccountCompleteViewServlet";
			res.setStatus(HttpServletResponse.SC_SEE_OTHER);
			res.sendRedirect(url);
			return;
		}else {
			url = "/TicketServlet";
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(req, res);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
