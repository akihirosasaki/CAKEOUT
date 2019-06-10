package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

import model.LoggingModel;
import model.UserModel;
import util.Digest;
import vo.UserVo;

/**
 * @author Akihiro Sasaki
 * ログイン画面でユーザーが入力した情報をチェックし、問題なければログイン済みのセッションを発行するサーブレット
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		UserVo loginUser = null;
		String mailAdd = req.getParameter("mailAdd");
		String password = req.getParameter("password");

		String isLogin = "false";
		String isAdmin = "false";
		String isUserNull = "false";

		Digest digest = new Digest(Digest.SHA512);
		String hashPass = digest.hex(password);
		System.out.println(hashPass);
		String exceptPattern = "<|>|\"|\'|&";
		Pattern p = Pattern.compile(exceptPattern);
		Matcher m = p.matcher(mailAdd);
		String isExceptionString = "false";
		if (m.find()) {
			isExceptionString = "true";
			req.setAttribute("isExceptionString", isExceptionString);
			final String url = "IndexServlet";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		}

		try {
			UserModel um = new UserModel();
			loginUser = um.getLoginUser(mailAdd, hashPass);
			if (loginUser != null) {
				isLogin = "true";
				if (loginUser.getUserRole() == 1) {
					isAdmin = "true";
				}
			} else {
				isUserNull = "true";
			}

		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}

		String ip = req.getHeader("x-forwarded-for");
		if (ip == null) {
			ip = req.getRemoteAddr();
		}
		String ips[] = ip.split(",");
		ip = ips[0];
		LocalDateTime ldt = LocalDateTime.now();
		LoggingModel ll = new LoggingModel();
		HttpSession session = req.getSession(false);
		session.setAttribute("isLogin", isLogin);
		req.setAttribute("isUserNull", isUserNull);
		if ("true".equals(isLogin)) {
			String logType = "login";
			try {
				ll.loggingLogin(loginUser.getUserId(), ip, ldt, logType);
			} catch (SQLException | NamingException e) {
				System.out.println("SQLの実行に失敗しました");
				System.out.println("SQLException:" + e.getMessage());
				System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
				e.printStackTrace();
				throw new ServletException(e);
			}
			session.setAttribute("userId", loginUser.getUserId());
			session.setAttribute("userRole", loginUser.getUserRole());
			session.setAttribute("userName", loginUser.getUserName());
			ServletContext sc = this.getServletContext();
			if ("false".equals(isAdmin)) {
				String isOrdered = (String) session.getAttribute("isOrdered");
				if (isOrdered == null) {
					RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
					rd.forward(req, res);
					System.out.println("注文前顧客ログイン成功");
					return;
				} else {
					RequestDispatcher rd = sc.getRequestDispatcher("/TicketServlet");
					rd.forward(req, res);
					System.out.println("注文後顧客ログイン成功");
					return;
				}
			} else {
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				System.out.println("管理者ログイン成功");
				return;
			}
		} else {
			String logType = "failed";
			int failedId = 0;
			try {
				ll.loggingLogin(failedId, ip, ldt, logType);
			} catch (SQLException | NamingException e) {
				System.out.println("SQLの実行に失敗しました");
				System.out.println("SQLException:" + e.getMessage());
				System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
				e.printStackTrace();
				throw new ServletException(e);
			}
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P002.jsp");
			rd.forward(req, res);
			return;
		}
	}
}
