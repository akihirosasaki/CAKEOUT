package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		final String url = "/jsp/P002.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
