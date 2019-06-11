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


/**
 * @author Akihiro Sasaki
 * ユーザーがアカウント登録画面で入力した情報をチェックし、問題がなければ確認画面を表示するサーブレット
 */
@WebServlet("/AccountRegistServlet")
public class InsertAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertAccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String userName = req.getParameter("userName");
		String mailAdd = req.getParameter("mailAdd");
		String password = req.getParameter("password");
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
        	final String url = "/jsp/P001.jsp";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		}
        if("false".equals(isExceptionUser) || "false".equals(isExceptionMail) || "false".equals(isExceptionPass)) {
        	String isExceptionString="true";
			req.setAttribute("isExceptionString", isExceptionString);
			final String url = "IndexServlet";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		}

		UserModel um = new UserModel();
		final String url;
		try {
			mailDuplicateCheck = um.checkAccount(mailAdd);
			HttpSession session = req.getSession(false);
			if(mailDuplicateCheck==false || "false".equals(mailFormatCheck) || "false".equals(userLengthCheck) || "false".equals(passLengthCheck)) {
				req.setAttribute("mailDuplicateCheck", mailDuplicateCheck);
				req.setAttribute("mailFormatCheck", mailFormatCheck);
				req.setAttribute("userLengthCheck", userLengthCheck);
				req.setAttribute("passLengthCheck", passLengthCheck);
				url = "/jsp/P003.jsp";
			}else {
				session.setAttribute("userName", userName);
				session.setAttribute("mailAdd", mailAdd);
				session.setAttribute("password", password);
				url = "/jsp/P004.jsp";
			}
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}

		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(req, res);
		return;

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
