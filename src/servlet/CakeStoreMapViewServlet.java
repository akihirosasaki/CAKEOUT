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
 * ケーキ屋検索地図表示サーブレット
 * ケーキ屋の検索地図を表示するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/CakeStoreMapViewServlet")
public class CakeStoreMapViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CakeStoreMapViewServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String cakeStoreArea = req.getParameter("cakeStoreArea");
		if (session == null || cakeStoreArea == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		}
		session.setAttribute("cakeStoreArea", cakeStoreArea);
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P006.jsp");
		rd.forward(req, res);
		return;

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
