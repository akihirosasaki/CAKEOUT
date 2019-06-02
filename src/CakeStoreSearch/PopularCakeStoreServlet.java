package CakeStoreSearch;

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

import Vo.CakeStoreVo;

/**
 * Servlet implementation class PopularCakeStoreServlet
 */
@WebServlet("/PopularCakeStoreServlet")
public class PopularCakeStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopularCakeStoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=Shift_JIS");

		String searchArea = req.getParameter("searchArea");
		System.out.println(searchArea);
	 	ArrayList<CakeStoreVo> popularCakeStores = new ArrayList<CakeStoreVo>();
	 	String isPopularCakeStore = "true";



		popularCakeStoreSearchModel pcsm = new popularCakeStoreSearchModel();
		 try {
			 popularCakeStores = pcsm.businessMethod(searchArea);
			 if(popularCakeStores.isEmpty()) {
				 isPopularCakeStore = "false";
			 }

		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}
		HttpSession session = req.getSession(true);
		session.setAttribute("searchArea",searchArea);
		session.setAttribute("popularCakeStores", popularCakeStores);
		session.setAttribute("isPopularCakeStore", isPopularCakeStore);
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P001.jsp");
		rd.forward(req, res);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
