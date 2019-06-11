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
import vo.CakeStoreVo;

/**
 * @author Akihiro Sasaki
 * cakeStoreMap.jsから投げられたケーキ屋の駅情報をもとに、DBから最寄り駅県内のケーキ情報を取得し、cakeStoreMap.jsに返すサーブレット
 */
@WebServlet("/CakeStoreMapServlet")
public class CakeStoreMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CakeStoreMapServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String cakeStoreArea = req.getParameter("cakeStoreArea");
		String[] statusList = req.getParameterValues("statusList[]");

		ArrayList<CakeStoreVo> cakeStoreList = null;

		CakeStoreSearchModel cssm = new CakeStoreSearchModel();

		try {
			cakeStoreList = cssm.getCakeStoreByArea(cakeStoreArea, statusList);
			Gson gson = new Gson();
			PrintWriter out = res.getWriter();
			JsonWriter writer = new JsonWriter(out);
			writer.setIndent(" ");
			gson.toJson(cakeStoreList, ArrayList.class, writer);
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

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
