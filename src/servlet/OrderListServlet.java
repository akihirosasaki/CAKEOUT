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

import Vo.OrderVo;
import model.OrderModel;

@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		String orderStatus = req.getParameter("accountLink");

		HttpSession session = req.getSession(true);
		session.setAttribute("orderStatus", orderStatus);
		int userId = (Integer) session.getAttribute("userId");
		ArrayList<OrderVo> orderList = new ArrayList<OrderVo>();

		OrderModel om = new OrderModel();
		try {
			orderList = om.getOrderList(userId);
			session.setAttribute("orderList", orderList);
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}
		if (orderList.size() > 1) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P012.jsp");
			rd.forward(req, res);
			return;
		} else if (orderList.size() == 1) {
			int orderId = orderList.get(0).getOrderId();
			session.setAttribute("orderId", orderId);
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/OrderSelectServlet");
			rd.forward(req, res);
			return;
		} else {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
