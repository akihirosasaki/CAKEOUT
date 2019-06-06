package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import model.CakeStoreSearchModel;

/**
 * Servlet implementation class GetCenterPositionServlet
 */
@WebServlet("/GetCenterPositionServlet")
public class GetCenterPositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCenterPositionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		String cakeStoreArea = req.getParameter("cakeStoreArea");
		ArrayList<Double> stationPosition = new ArrayList<Double>();
		CakeStoreSearchModel cssm = new CakeStoreSearchModel();
		try {
			stationPosition = cssm.getCakeStationPosition(cakeStoreArea);

			Gson gson = new Gson();
			PrintWriter out = res.getWriter();
			JsonWriter writer = new JsonWriter(out);
			writer.setIndent(" ");
			gson.toJson(stationPosition, ArrayList.class,writer);
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
