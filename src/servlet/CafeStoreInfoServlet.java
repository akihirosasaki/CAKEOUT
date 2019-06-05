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

import Vo.CafeStoreMenuVo;
import Vo.CafeStoreVo;
import model.CafeStoreSearchModel;

/**
 * Servlet implementation class cafeStoreInfoServlet
 */
@WebServlet("/CafeStoreInfoServlet")
public class CafeStoreInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CafeStoreInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		String[] cafeStoreIdString = req.getParameterValues("cafeStoreId");
		int[] cafeStoreId = Stream.of(cafeStoreIdString).mapToInt(Integer::parseInt).toArray();
		System.out.println(cafeStoreId[0]);
		CafeStoreSearchModel cssm = new CafeStoreSearchModel();

		try {
			CafeStoreVo cafeStoreInfo = cssm.getCafeStoreInfo(cafeStoreId[0]);
			ArrayList<String> cafeStoreImgList = cssm.getCafeStoreImg(cafeStoreId[0]);
			ArrayList<CafeStoreMenuVo> cafeStoreMenuList = cssm.getCafeStoreMenu(cafeStoreId[0]);
			System.out.println("cafeStoreMenuList:"+cafeStoreMenuList);
			HttpSession session = req.getSession(true);
			session.setAttribute("cafeStoreInfo", cafeStoreInfo);
			session.setAttribute("cafeStoreImgList", cafeStoreImgList);
			session.setAttribute("cafeStoreMenuList", cafeStoreMenuList);
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P009.jsp");
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
