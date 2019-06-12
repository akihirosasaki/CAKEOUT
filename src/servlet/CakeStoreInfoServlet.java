package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CakeStoreSearchModel;
import vo.CakeStoreMenuVo;
import vo.CakeStoreVo;

/**
 * ケーキ屋詳細ページ表示サーブレット
 * ケーキ屋の詳細ページを表示するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/CakeStoreInfoServlet")
public class CakeStoreInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CakeStoreInfoServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String[] cakeStoreIdString = req.getParameterValues("cakeStoreId");
		if (session == null || cakeStoreIdString == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		}
		int[] cakeStoreId = Stream.of(cakeStoreIdString).mapToInt(Integer::parseInt).toArray();
		CakeStoreSearchModel cssm = new CakeStoreSearchModel();

		try {
			CakeStoreVo cakeStoreInfo = cssm.getCakeStoreInfo(cakeStoreId[0]);
			ArrayList<String> cakeStoreImgList = cssm.getCakeStoreImg(cakeStoreId[0]);
			ArrayList<CakeStoreMenuVo> cakeStoreMenuList = cssm.getCakeStoreMenu(cakeStoreId[0]);
			session.setAttribute("cakeStoreInfo", cakeStoreInfo);
			session.setAttribute("cakeStoreImgList", cakeStoreImgList);
			session.setAttribute("cakeStoreMenuList", cakeStoreMenuList);
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P007.jsp");
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
