package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CafeStoreSearchModel;
import model.CakeStoreSearchModel;
import vo.CafeStoreVo;
import vo.CakeStoreVo;

/**
 * @author Akihiro Sasaki
 * ユーザーが選択したケーキ屋とカフェの情報をもとに、注文確認画面を生成するサーブレット
 */
@WebServlet("/CakeCafeConfirmServlet")
public class CakeCafeConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CakeCafeConfirmServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		int selectedCakeStoreId = Integer.parseInt(session.getAttribute("selectedCakeStoreId").toString());
		int selectedCafeStoreId = Integer.parseInt(req.getParameter("selectedCafeStoreId"));
		CakeStoreSearchModel cksm = new CakeStoreSearchModel();
		CafeStoreSearchModel cfsm = new CafeStoreSearchModel();

		try {
			CakeStoreVo cakeStoreInfo = cksm.getCakeStoreInfo(selectedCakeStoreId);
			CafeStoreVo cafeStoreInfo = cfsm.getCafeStoreInfo(selectedCafeStoreId);

			session.setAttribute("selectedCakeStoreId", selectedCakeStoreId);
			session.setAttribute("selectedCafeStoreId", selectedCafeStoreId);
			session.setAttribute("cakeStoreInfo", cakeStoreInfo);
			session.setAttribute("cafeStoreInfo", cafeStoreInfo);

			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P010.jsp");
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

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
