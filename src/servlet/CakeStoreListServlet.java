package servlet;

import java.io.IOException;
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

import model.CakeStoreSearchModel;
import vo.CakeStoreVo;

/**
 * @author Akihiro Sasaki
 * TOPページで名前検索した後に、該当のケーキ屋リストを表示するサーブレット
 */
@WebServlet("/CakeStoreListServlet")
public class CakeStoreListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CakeStoreListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String isInputCheck = "true";
		String cakeStoreNameInput = req.getParameter("cakeStoreName");

		CakeStoreSearchModel cssm = new CakeStoreSearchModel();
		try {
			if (cakeStoreNameInput == null || "".equals(cakeStoreNameInput)) {
				isInputCheck = "false";
				req.setAttribute("isInputCheck", isInputCheck);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P001.jsp");
				rd.forward(req, res);
				return;
			} else {
				ArrayList<CakeStoreVo> cakeStoreList = cssm.searchCakeStoreName(cakeStoreNameInput);
				HttpSession session = req.getSession(false);
				session.setAttribute("cakeStoreNameInput", cakeStoreNameInput);
				session.setAttribute("cakeStoreList", cakeStoreList);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P019.jsp");
				rd.forward(req, res);
				return;
			}
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
