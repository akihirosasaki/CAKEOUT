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

import model.OrderModel;
import vo.OrderVo;

/**
 * チケット確認ページ表示サーブレット
 * アカウントページからユーザーがチケット確認アクションを起こした時に、取得したOrderIdをもとに、チケットページを表示するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/TicketConfirmServlet")
public class TicketConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TicketConfirmServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		} else {
			if (session.getAttribute("orderId") == null) {
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				return;
			}
		}

		int orderId = (Integer) session.getAttribute("orderId");

		OrderModel om = new OrderModel();
		OrderVo orderItem = null;

		try {
			orderItem = om.getOrder(orderId);
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}
		String userName = (String) session.getAttribute("userName");
		session.setAttribute("userName", userName);
		session.setAttribute("orderNum", orderItem.getOrderNum());
		session.setAttribute("date", orderItem.getCreatedAt());
		session.setAttribute("cakeStoreName", orderItem.getCakeStoreName());
		session.setAttribute("cafeStoreName", orderItem.getCafeStoreName());
		session.setAttribute("selectedCakeStoreId", orderItem.getCakeStoreId());
		session.setAttribute("selectedCafeStoreId", orderItem.getCafeStoreId());

		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P011.jsp");
		rd.forward(req, res);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

}
