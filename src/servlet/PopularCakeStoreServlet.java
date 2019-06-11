package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CakeStoreSearchModel;
import vo.CakeStoreVo;

/**
 * @author Akihiro Sasaki
 * TOPページでユーザーが選択したエリア情報をもとに、人気ケーキ屋をDBから取得し、ページに表示させるサーブレット
 */
@WebServlet("/PopularCakeStoreServlet")
public class PopularCakeStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PopularCakeStoreServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String searchArea = req.getParameter("searchArea");

		ArrayList<CakeStoreVo> popularCakeStores = new ArrayList<CakeStoreVo>();
		String isPopularCakeStore = "true";

		CakeStoreSearchModel cssm = new CakeStoreSearchModel();
		try {
			popularCakeStores = cssm.getPopularCakeStore(searchArea);
			if (popularCakeStores.isEmpty()) {
				isPopularCakeStore = "false";
			}

		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}
		HttpSession session = req.getSession(false);
		session.setAttribute("searchArea", searchArea);
		session.setAttribute("popularCakeStores", popularCakeStores);
		session.setAttribute("isPopularCakeStore", isPopularCakeStore);
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P001.jsp");
		rd.forward(req, res);
		return;

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
