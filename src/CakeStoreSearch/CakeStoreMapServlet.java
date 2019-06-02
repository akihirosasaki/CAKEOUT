package CakeStoreSearch;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import Vo.CakeStoreVo;

/**
 * Servlet implementation class CakeStoreMapServlet
 */
@WebServlet("/CakeStoreMapServlet")
public class CakeStoreMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CakeStoreMapServlet() {
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

		String cakeStoreArea = req.getParameter("cakeStoreArea");
		System.out.println(cakeStoreArea);
		ArrayList<CakeStoreVo> cakeStoreList = null;
		String isCakeStore = "true";

		CakeStoreAreaSearchModel csasm = new CakeStoreAreaSearchModel();

		try {
			cakeStoreList = csasm.businessMethod(cakeStoreArea);
			if(cakeStoreList.isEmpty()) {
				 isCakeStore = "false";
			}
			System.out.println(cakeStoreList);
			Gson gson = new Gson();
			System.out.println(gson.toJson(cakeStoreList, ArrayList.class));
			PrintWriter out = res.getWriter();
			JsonWriter writer = new JsonWriter(out);
			writer.setIndent(" ");
			gson.toJson(cakeStoreList, ArrayList.class,writer);
			writer.flush();
			out.flush();
			writer.close();



		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}

		HttpSession session = req.getSession(true);
		session.setAttribute("cakeStoreArea",cakeStoreArea);
		session.setAttribute("cakeStoreList", cakeStoreList);
		session.setAttribute("isCakeStore", isCakeStore);
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P006.jsp");
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
