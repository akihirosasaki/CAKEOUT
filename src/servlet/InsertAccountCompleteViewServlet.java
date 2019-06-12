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

/**
 * アカウント登録完了画面表示サーブレット
 * アカウント登録完了画面を表示するサーブレットです
 * @author Akihiro Sasaki
 */
@WebServlet("/InsertAccountCompleteViewServlet")
public class InsertAccountCompleteViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertAccountCompleteViewServlet() {
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
		} else {
			if (session.getAttribute("isNullCheck")==null) {
				session.setAttribute("isNullCheck", null);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				return;
			}
		}

		final String url = "/jsp/P005.jsp";
		req.getRequestDispatcher(url).forward(req, res);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
