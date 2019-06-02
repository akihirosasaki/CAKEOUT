package AccountRegist;

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

import Login.LoginModel;
import Vo.UserVo;
import util.Digest;

/**
 * Servlet implementation class AccountRegistCompleteServlet
 */
@WebServlet("/AccountRegistCompleteServlet")
public class AccountRegistCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountRegistCompleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=Shift_JIS");

		HttpSession session = req.getSession();
		String userName = (String) session.getAttribute("userName");
		String mailAdd = (String) session.getAttribute("mailAdd");
		String password = (String) session.getAttribute("password");
		Digest digest = new Digest(Digest.SHA512);
		String hashPass = digest.hex(password);

		AccountRegistModel arm = new AccountRegistModel();
		try {
			arm.businessMethod(userName, mailAdd, hashPass);
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}
		UserVo loginUser = null;
		LoginModel lm = new LoginModel();
		try {
			loginUser = lm.businessMethod(mailAdd, hashPass);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
