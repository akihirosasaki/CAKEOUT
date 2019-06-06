package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Vo.CafeStoreVo;
import Vo.CakeStoreVo;
import model.CafeStoreSearchModel;
import model.CakeStoreSearchModel;

/**
 * Servlet implementation class CakeCafeConfirmServlet
 */
@WebServlet("/CakeCafeConfirmServlet")
public class CakeCafeConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CakeCafeConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(true);
		int selectedCakeStoreId = Integer.parseInt((String) session.getAttribute("selectedCakeStoreId"));
		int selectedCafeStoreId = Integer.parseInt(req.getParameter("selectedCafeStoreId"));


		CakeStoreSearchModel cksm = new CakeStoreSearchModel();
		CafeStoreSearchModel cfsm = new CafeStoreSearchModel();

		try {
			CakeStoreVo cakeStoreInfo = cksm.getCakeStoreInfo(selectedCakeStoreId);
			CafeStoreVo cafeStoreInfo = cfsm.getCafeStoreInfo(selectedCafeStoreId);

			session.setAttribute("selectedCakeStoreId", selectedCakeStoreId);
			session.setAttribute("selectedCafeStoreId", selectedCafeStoreId);
			session.setAttribute("cakeStoreInfo", cakeStoreInfo);
			session.setAttribute("cafeStoreInfo", cafeStoreInfo);

			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P010.jsp");
			rd.forward(req, res);
			return;
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}






	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
