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

import model.CafeStoreSearchModel;
import vo.CafeStoreVo;

/**
 * カフェ情報取得サーブレット
 * CafeStoreMap.jsから取得した駅情報をもとに、DBからその最寄駅内のカフェを取得し、CafeStoreMap.jsに送り返すサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/CafeStoreMapServlet")
public class CafeStoreMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CafeStoreMapServlet() {
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
		String[] statusList = req.getParameterValues("statusList[]");

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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
