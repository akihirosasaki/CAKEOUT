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
import util.CsrfTokenManager;
import vo.CafeStoreVo;
import vo.CakeStoreVo;

/**
 * 注文確認サーブレット
 * ユーザーが選択したケーキ屋とカフェの情報をもとに、注文確認画面を生成するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/CakeCafeConfirmServlet")
public class CakeCafeConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CakeCafeConfirmServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String isnullCheck = req.getParameter("selectedCafeStoreId");
		if (session == null || isnullCheck == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		}
		int selectedCakeStoreId = Integer.parseInt(session.getAttribute("selectedCakeStoreId").toString());
		int selectedCafeStoreId = Integer.parseInt(req.getParameter("selectedCafeStoreId"));

		CakeStoreSearchModel cksm = new CakeStoreSearchModel();
		CafeStoreSearchModel cfsm = new CafeStoreSearchModel();
		CsrfTokenManager ctm = new CsrfTokenManager();

		try {
			CakeStoreVo cakeStoreInfo = cksm.getCakeStoreInfo(selectedCakeStoreId);
			CafeStoreVo cafeStoreInfo = cfsm.getCafeStoreInfo(selectedCafeStoreId);
			String token = ctm.getCsrfToken();
			session.setAttribute("token", token);
			session.setAttribute("selectedCakeStoreId", selectedCakeStoreId);
			session.setAttribute("selectedCafeStoreId", selectedCafeStoreId);
			session.setAttribute("cakeStoreInfo", cakeStoreInfo);
			session.setAttribute("cafeStoreInfo", cafeStoreInfo);

			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P010.jsp");
			rd.forward(req, res);
			return;
		} catch (SQLException | NamingException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
