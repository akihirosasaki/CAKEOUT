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
 * @author Akihiro Sasaki
 * ユーザーが登録確認したユーザーネーム、メールアドレス、パスワードの情報をDBに格納し、完了画面を表示するサーブレット
 */
@WebServlet("/AccountRegistCompleteServlet")
public class InsertAccountCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertAccountCompleteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String userName = (String) session.getAttribute("userName");
		String mailAdd = (String) session.getAttribute("mailAdd");
		String password = (String) session.getAttribute("password");
		Digest digest = new Digest(Digest.SHA512);
		String hashPass = digest.hex(password);

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
		session.setAttribute("loginUser", loginUser);
		session.setAttribute("isLogin", isLogin);
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P005.jsp");
		rd.forward(req, res);
		return;

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
