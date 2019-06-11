package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Akihiro Sasaki
 * アカウント登録画面を表示するサーブレット
 */
@WebServlet("/AccountRegistViewServlet")
public class InsertAccountViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertAccountViewServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String url = "/jsp/P003.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
