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

import model.CafeStoreSearchModel;
import vo.CafeStoreVo;

/**
 * @author Akihiro Sasaki
 * CafeStoreMap.jsから取得した駅情報をもとに、DBからその最寄駅内のカフェを取得し、CafeStoreMap.jsに送り返す
 */
@WebServlet("/CafeStoreMapServlet")
public class CafeStoreMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CafeStoreMapServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String cakeStoreArea = req.getParameter("cakeStoreArea");
		String[] statusList = req.getParameterValues("statusList[]");
		System.out.println(cakeStoreArea);

		ArrayList<CafeStoreVo> cafeStoreList = null;

		CafeStoreSearchModel cssm = new CafeStoreSearchModel();

		try {
			cafeStoreList = cssm.getCafeStoreByArea(cakeStoreArea, statusList);
			Gson gson = new Gson();
			PrintWriter out = res.getWriter();
			JsonWriter writer = new JsonWriter(out);
			writer.setIndent(" ");
			gson.toJson(cafeStoreList, ArrayList.class, writer);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
