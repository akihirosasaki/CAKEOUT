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

import model.OrderModel;
import vo.OrderVo;

/**
 * @author Akihiro Sasaki
 * ユーザーの注文情報を取得し、未入店の注文数をもとに、遷移先を分岐させるサーブレット
 */
@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String orderStatus = req.getParameter("accountLink");

		HttpSession session = req.getSession(false);
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
		final String url;
		if (orderList.size() > 1) {
			url = "/jsp/P012.jsp";
		} else if (orderList.size() == 1) {
			int orderId = orderList.get(0).getOrderId();
			session.setAttribute("orderId", orderId);
			url = "/OrderSelectServlet";
		} else {
			url = "/IndexServlet";
		}
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(req, res);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
