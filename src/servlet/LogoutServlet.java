package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

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

/**
 * ログアウトサーブレット
 * セッションを破棄し、ログアウト画面を表示するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		} else {
			String isLogin = (String) session.getAttribute("isLogin");
			if (!("true".equals(isLogin))) {
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				return;
			}
		}
		String ip = req.getHeader("x-forwarded-for");
		if (ip == null) {
			ip = req.getRemoteAddr();
		}
		String ips[] = ip.split(",");
		ip = ips[0];
		LocalDateTime ldt = LocalDateTime.now();
		LoggingModel ll = new LoggingModel();
		String logType = "logout";
		int userId = (Integer) session.getAttribute("userId");
		try {
			ll.loggingLogin(userId, ip, ldt, logType);
		} catch (SQLException | NamingException e) {
			throw new ServletException(e);
		}
		session.invalidate();
		final String url = "/jsp/P017.jsp";
		req.getRequestDispatcher(url).forward(req, res);
		return;
	}

}
