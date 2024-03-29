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
import util.CheckInput;
import util.CsrfTokenManager;

/**
 * アカウント登録確認画面表示サーブレット
 * ユーザーがアカウント登録画面で入力した情報をチェックし、問題がなければ確認画面を表示するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/AccountRegistServlet")
public class InsertAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertAccountServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String userName = req.getParameter("userName");
		String mailAdd = req.getParameter("mailAdd");
		String password = req.getParameter("password");
		if (session == null || userName == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		}
		CheckInput ci = new CheckInput();

		boolean mailDuplicateCheck;
		String mailFormatCheck = ci.CheckMail(mailAdd);
		String isPassCheck = ci.CheckPass(password);
		String userLengthCheck = ci.CheckUserLength(userName);
		String passLengthCheck = ci.CheckPassLength(password);
		String isExceptionUser = ci.CheckException(userName);
		String isExceptionMail = ci.CheckException(mailAdd);
		String isExceptionPass = ci.CheckException(password);
		if ("false".equals(isPassCheck)) {
			req.setAttribute("isPassCheck", isPassCheck);
			final String url = "/jsp/P003.jsp";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		}
		if ("false".equals(isExceptionUser) || "false".equals(isExceptionMail) || "false".equals(isExceptionPass)) {
			String isExceptionString = "true";
			req.setAttribute("isExceptionString", isExceptionString);
			final String url = "IndexServlet";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		}

		UserModel um = new UserModel();
		CsrfTokenManager ctm = new CsrfTokenManager();
		String token = ctm.getCsrfToken();
		final String url;
		try {
			mailDuplicateCheck = um.checkAccount(mailAdd);
			if (mailDuplicateCheck == false || "false".equals(mailFormatCheck) || "false".equals(userLengthCheck)
					|| "false".equals(passLengthCheck)) {
				req.setAttribute("mailDuplicateCheck", mailDuplicateCheck);
				req.setAttribute("mailFormatCheck", mailFormatCheck);
				req.setAttribute("userLengthCheck", userLengthCheck);
				req.setAttribute("passLengthCheck", passLengthCheck);
				url = "/jsp/P003.jsp";
			} else {
				session.setAttribute("token", token);
				session.setAttribute("userName", userName);
				session.setAttribute("mailAdd", mailAdd);
				session.setAttribute("password", password);
				url = "/jsp/P004.jsp";
			}
		} catch (SQLException | NamingException e) {
			throw new ServletException(e);
		}

		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(req, res);
		return;

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
