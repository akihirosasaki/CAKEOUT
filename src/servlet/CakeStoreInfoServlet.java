package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Vo.CakeStoreMenuVo;
import Vo.CakeStoreVo;
import model.CakeStoreSearchModel;

/**
 * Servlet implementation class CakeStoreInfoServlet
 */
@WebServlet("/CakeStoreInfoServlet")
public class CakeStoreInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CakeStoreInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		String[] cakeStoreIdString = req.getParameterValues("cakeStoreId");
		int[] cakeStoreId = Stream.of(cakeStoreIdString).mapToInt(Integer::parseInt).toArray();
		System.out.println(cakeStoreId[0]);
		CakeStoreSearchModel cssm = new CakeStoreSearchModel();

		try {
			CakeStoreVo cakeStoreInfo = cssm.getCakeStoreInfo(cakeStoreId[0]);
			ArrayList<String> cakeStoreImgList = cssm.getCakeStoreImg(cakeStoreId[0]);
			ArrayList<CakeStoreMenuVo> cakeStoreMenuList = cssm.getCakeStoreMenu(cakeStoreId[0]);
			HttpSession session = req.getSession(true);
			session.setAttribute("cakeStoreInfo", cakeStoreInfo);
			session.setAttribute("cakeStoreImgList", cakeStoreImgList);
			session.setAttribute("cakeStoreMenuList", cakeStoreMenuList);
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P007.jsp");
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