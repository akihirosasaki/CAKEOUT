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
 * Servlet implementation class CakeStoreListServlet
 */
@WebServlet("/CakeStoreListServlet")
public class CakeStoreListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CakeStoreListServlet() {
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


		String cakeStoreNameInput = req.getParameter("cakeStoreName");

		CakeStoreNameSearchModel csnsm = new CakeStoreNameSearchModel();
		try {
			ArrayList<CakeStoreVo> cakeStoreList = csnsm.businessMethod(cakeStoreNameInput);
			HttpSession session = req.getSession(true);
			System.out.println(cakeStoreList);
			session.setAttribute("cakeStoreNameInput", cakeStoreNameInput);
			session.setAttribute("cakeStoreList", cakeStoreList);
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P019.jsp");
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
