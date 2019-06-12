package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * アカウント登録入力画面表示サーブレット
 * アカウント登録画面を表示するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/AccountRegistViewServlet")
public class InsertAccountViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertAccountViewServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String url = "/jsp/P003.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
