package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.CsrfTokenManager;

/**
 * ログイン画面表示サーブレット
 * ログイン画面を入力するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/LoginViewServlet")
public class LoginViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginViewServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		}
		CsrfTokenManager ctm = new CsrfTokenManager();
		String token = ctm.getCsrfToken();
		session.setAttribute("token", token);
		final String url = "/jsp/P002.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
