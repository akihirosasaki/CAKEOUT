package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		System.out.println("invalidateした");
		//		String ip = req.getHeader("x-forwarded-for");
		//		if (ip == null) {
		//			ip = req.getRemoteAddr();
		//		}
		//		String ips[] = ip.split(",");
		//		ip = ips[0];
		//		LocalDateTime ldt = LocalDateTime.now();
		//		LoggingLogin ll = new LoggingLogin();
		//		String logType = "logout";
		//		String userId = (String) session.getAttribute("userId");
		//		try {
		//			ll.businessMethod(userId, ip, ldt, logType);
		//		} catch (SQLException | NamingException e) {
		//			System.out.println("SQLの実行に失敗しました");
		//			System.out.println("SQLException:" + e.getMessage());
		//			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
		//			e.printStackTrace();
		//			throw new ServletException(e);
		//		}
		session.invalidate();
		final String url = "/jsp/P017.jsp";
		req.getRequestDispatcher(url).forward(req, res);
		return;
	}

}
