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

import model.CafeStoreSearchModel;
import vo.CafeStoreMenuVo;
import vo.CafeStoreVo;

/**
 * カフェ詳細ページ表示サーブレット
 * カフェの詳細ページを表示するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/CafeStoreInfoServlet")
public class CafeStoreInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CafeStoreInfoServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String[] cafeStoreIdString = req.getParameterValues("cafeStoreId");
		if (session == null || cafeStoreIdString == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		}

		int[] cafeStoreId = Stream.of(cafeStoreIdString).mapToInt(Integer::parseInt).toArray();
		CafeStoreSearchModel cssm = new CafeStoreSearchModel();

		try {
			CafeStoreVo cafeStoreInfo = cssm.getCafeStoreInfo(cafeStoreId[0]);
			ArrayList<String> cafeStoreImgList = cssm.getCafeStoreImg(cafeStoreId[0]);
			ArrayList<CafeStoreMenuVo> cafeStoreMenuList = cssm.getCafeStoreMenu(cafeStoreId[0]);
			session.setAttribute("cafeStoreInfo", cafeStoreInfo);
			session.setAttribute("cafeStoreImgList", cafeStoreImgList);
			session.setAttribute("cafeStoreMenuList", cafeStoreMenuList);
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P009.jsp");
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
