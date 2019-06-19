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
import vo.CakeStoreVo;

/**
 * ケーキ屋情報取得サーブレット
 * cakeStoreMap.jsから投げられたケーキ屋の駅情報をもとに、DBから最寄り駅県内のケーキ情報を取得し、cakeStoreMap.jsに返すサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/CakeStoreMapServlet")
public class CakeStoreMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CakeStoreMapServlet() {
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
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
