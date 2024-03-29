package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
import util.CheckInput;
import util.Digest;
import vo.UserVo;

/**
 * ログインサーブレット
 * ログイン画面でユーザーが入力した情報をチェックし、問題なければログイン済みのセッションを発行するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserVo loginUser = null;
		String mailAdd = req.getParameter("mailAdd");
		String password = req.getParameter("password");
		if (session == null || password == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		}

		String isLogin = "false";
		String isAdmin = "false";
		String isUserNull = "false";

		Digest digest = new Digest(Digest.SHA512);
		String hashPass = digest.hex(password);
		CheckInput ci = new CheckInput();
		ci.CheckException(mailAdd);

		String isExceptionMail = ci.CheckException(mailAdd);
		String isExceptionPass = ci.CheckException(password);
		if ("false".equals(isExceptionMail) || "false".equals(isExceptionPass)) {
			String isExceptionString = "true";
			req.setAttribute("isExceptionString", isExceptionString);
			final String url = "/jsp/P002.jsp";
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
		Enumeration<?> sessionEnum = session.getAttributeNames();
		HashMap<String, Object> map = new HashMap<String, Object>();
		while (sessionEnum.hasMoreElements()) {
			String key = (String) sessionEnum.nextElement();
			Object value = session.getAttribute(key);
			map.put(key, value);
		}
		session.invalidate();
		HttpSession sess = req.getSession(true);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sess.setAttribute(entry.getKey(), entry.getValue());
		}
		sess.setAttribute("isLogin", isLogin);
		req.setAttribute("isUserNull", isUserNull);
		if ("true".equals(isLogin)) {
			String logType = "login";
			try {
				ll.loggingLogin(loginUser.getUserId(), ip, ldt, logType);
			} catch (SQLException | NamingException e) {
				throw new ServletException(e);
			}
			sess.setAttribute("userId", loginUser.getUserId());
			sess.setAttribute("userRole", loginUser.getUserRole());
			sess.setAttribute("userName", loginUser.getUserName());
			ServletContext sc = this.getServletContext();
			if ("false".equals(isAdmin)) {
				String isOrdered = (String) sess.getAttribute("isOrdered");
				if (isOrdered == null) {
					RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
					rd.forward(req, res);
					return;
				} else {
					RequestDispatcher rd = sc.getRequestDispatcher("/TicketServlet");
					rd.forward(req, res);
					return;
				}
			} else {
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				return;
			}
		} else {
			String logType = "failed";
			int failedId = 0;
			try {
				ll.loggingLogin(failedId, ip, ldt, logType);
			} catch (SQLException | NamingException e) {
				throw new ServletException(e);
			}
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P002.jsp");
			rd.forward(req, res);
			return;
		}
	}
}
