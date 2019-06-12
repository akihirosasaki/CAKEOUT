package servlet;

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

import model.CakeStoreSearchModel;

/**
 * 駅の位置情報取得サーブレット
 * cakeStoreMap.js、cafeStoreMap.jsと連携し、ユーザーが選択した駅の緯度経度をjsに返すサーブレット
 * @author Akihiro Sasaki
 *
 */
@WebServlet("/GetCenterPositionServlet")
public class GetCenterPositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetCenterPositionServlet() {
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
		ArrayList<Double> stationPosition = new ArrayList<Double>();
		CakeStoreSearchModel cssm = new CakeStoreSearchModel();
		try {
			stationPosition = cssm.getCakeStationPosition(cakeStoreArea);

			Gson gson = new Gson();
			PrintWriter out = res.getWriter();
			JsonWriter writer = new JsonWriter(out);
			writer.setIndent(" ");
			gson.toJson(stationPosition, ArrayList.class, writer);
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
