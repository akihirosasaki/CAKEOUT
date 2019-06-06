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

import Vo.UserVo;
import model.UserModel;
import util.Digest;


@WebServlet("/AccountRegistCompleteServlet")
public class AccountRegistCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccountRegistCompleteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		String userName = (String) session.getAttribute("userName");
		String mailAdd = (String) session.getAttribute("mailAdd");
		String password = (String) session.getAttribute("password");
		Digest digest = new Digest(Digest.SHA512);
		String hashPass = digest.hex(password);

		UserModel lm = new UserModel();
		try {
			lm.accountRegist(userName, mailAdd, hashPass);
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
			loginUser = um.selectLoginUser(mailAdd, hashPass);
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
