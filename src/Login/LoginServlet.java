package Login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import util.Digest;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=Shift_JIS");

		UserVo loginUser = null;
		String mailAdd = req.getParameter("mailAdd");
		String password = req.getParameter("password");

		String isLogin = "false";
		String isAdmin = "false";


		Digest digest = new Digest(Digest.SHA512);
		String hashPass = digest.hex(password);
		System.out.println(hashPass);
		String exceptPattern = "<|>|\"|\'|&";
		Pattern p = Pattern.compile(exceptPattern);
		Matcher m = p.matcher(mailAdd);
		String isExceptionString = "false";
		if(m.find()) {
			isExceptionString = "true";
			req.setAttribute("isExceptionString", isExceptionString);
			final String url = "IndexServlet";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		}

		try {
			LoginModel lm = new LoginModel();
			loginUser = lm.businessMethod(mailAdd, hashPass);
			if (loginUser != null) {
				isLogin = "true";
				if (loginUser.getUserRole()==1) {
					isAdmin = "true";
				}
			}

		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}

//		String ip = req.getHeader("x-forwarded-for");
//		if (ip == null) {
//			ip = req.getRemoteAddr();
//		}
//		String ips[] = ip.split(",");
//		ip = ips[0];
//		LocalDateTime ldt = LocalDateTime.now();
//		LoggingLogin ll = new LoggingLogin();
//
//		if (isLogin.equals("true")) {
//			String logType = "login";
//			try {
//				System.out.println(loginUser.getUserId());
//				System.out.println(logType);
//				ll.businessMethod(loginUser.getUserId(), ip, ldt, logType);
//			} catch (SQLException | NamingException e) {
//				System.out.println("SQLの実行に失敗しました");
//				System.out.println("SQLException:" + e.getMessage());
//				System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
//				e.printStackTrace();
//				throw new ServletException(e);
//			}
			HttpSession sess = req.getSession(true);
			sess.invalidate();
			HttpSession session = req.getSession(true);
			session.setAttribute("userId", loginUser.getUserId());
			session.setAttribute("userRole", loginUser.getUserRole());
			session.setAttribute("isLogin", isLogin);
			session.setAttribute("userName", loginUser.getUserName());
			ServletContext sc = this.getServletContext();
			if (isAdmin.equals("false")) {
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				System.out.println("顧客ログイン成功");
				return;
			} else {
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				System.out.println("管理者ログイン成功");
				return;
			}
//		} else {
//			String logType = "failed";
//			int failedId;
//			int userId = loginUser.getUserId();
//			failedId = userId;
//			try {
//				ll.businessMethod(failedId, ip, ldt, logType);
//			} catch (SQLException | NamingException e) {
//				System.out.println("SQLの実行に失敗しました");
//				System.out.println("SQLException:" + e.getMessage());
//				System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
//				e.printStackTrace();
//				throw new ServletException(e);
//			}
//			ServletContext sc = this.getServletContext();
//			RequestDispatcher rd = sc.getRequestDispatcher("/login/loginError.jsp");
//			rd.forward(req, res);
//			return;
//		}

	}
}
