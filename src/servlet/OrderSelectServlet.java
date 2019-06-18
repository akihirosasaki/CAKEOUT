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
 * マイページアクション振り分けサーブレット
 * ユーザーのアクションごとにそれぞれの処理に分岐させるサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/OrderSelectServlet")
public class OrderSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderSelectServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		} else {
			String isLogin = (String) session.getAttribute("isLogin");
			if (!("true".equals(isLogin))) {
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				return;
			}
		}
		int orderId;
		String orderIdJsp = req.getParameter("orderId");
		if (orderIdJsp == null) {
			orderId = (Integer) session.getAttribute("orderId");
		} else {
			orderId = Integer.parseInt(orderIdJsp);
		}

		session.setAttribute("orderId", orderId);
		OrderModel om = new OrderModel();
		try {
			OrderVo orderItem = om.getOrder(orderId);
			session.setAttribute("orderItem", orderItem);
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}

		String orderStatus = (String) session.getAttribute("orderStatus");
		String destination = null;
		if ("ticket".equals(orderStatus)) {
			destination = "/TicketConfirmServlet";
		} else if ("cancel".equals(orderStatus)) {
			destination = "/jsp/P013.jsp";
		} else {
			destination = "/jsp/P015.jsp";
		}
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(destination);
		rd.forward(req, res);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
