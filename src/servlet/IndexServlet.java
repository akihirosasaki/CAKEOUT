package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IndexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession sess = req.getSession(true);
		if (sess.getAttribute("isLogin") == null) {
			String isLogin = "false";
			sess.setAttribute("isLogin", isLogin);
		}
		final String url = "/jsp/P001.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
