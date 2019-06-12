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
 * カフェ検索地図表示サーブレット
 * カフェの検索地図を表示するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/CafeStoreMapViewServlet")
public class CafeStoreMapViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CafeStoreMapViewServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String selectedCakeStoreId = req.getParameter("selectedCakeStoreId");
		String selectedCakeStoreName = req.getParameter("selectedCakeStoreName");
		String selectedCakeStoreArea = req.getParameter("selectedCakeStoreArea");
		HttpSession session = req.getSession(false);
		if (session == null || selectedCakeStoreArea == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		}
		if (selectedCakeStoreId != null && selectedCakeStoreName != null && selectedCakeStoreArea != null) {
			session.setAttribute("selectedCakeStoreId", selectedCakeStoreId);
			session.setAttribute("selectedCakeStoreName", selectedCakeStoreName);
			session.setAttribute("selectedCakeStoreArea", selectedCakeStoreArea);
		}
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P008.jsp");
		rd.forward(req, res);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
