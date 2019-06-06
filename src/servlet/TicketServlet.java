package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TicketServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(true);
		String cakeStoreName = req.getParameter("cakeStoreName");
		String cafeStoreName = req.getParameter("cafeStoreName");
		String orderNumString = req.getParameter("orderNum");
		String isOrdered = "true";
		if (cakeStoreName != null && cafeStoreName != null && orderNumString != null) {
			session.setAttribute("cakeStoreName", cakeStoreName);
			session.setAttribute("cafeStoreName", cafeStoreName);
			session.setAttribute("orderNum", orderNumString);
			session.setAttribute("isOrdered", isOrdered);
		}

		String isLogin = (String) session.getAttribute("isLogin");
		if (isLogin.equals("false")) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/LoginViewServlet");
			rd.forward(req, res);
			return;
		} else {
			int userId = (Integer) session.getAttribute("userId");
			int selectedCakeStoreId = (Integer) session.getAttribute("selectedCakeStoreId");
			int selectedCafeStoreId = (Integer) session.getAttribute("selectedCafeStoreId");
			int orderId = 0;
			cakeStoreName = (String) session.getAttribute("cakeStoreName");
			cafeStoreName = (String) session.getAttribute("cafeStoreName");
			orderNumString = (String) session.getAttribute("orderNum");
			String isInputCheck = "true";
			int orderNum;
			if (orderNumString == null || orderNumString.equals("")) {
				isInputCheck = "false";
				req.setAttribute("isInputCheck", isInputCheck);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P010.jsp");
				rd.forward(req, res);
				return;
			} else {
				orderNum = Integer.parseInt(orderNumString);
			}
			Date date = new Date();
			java.sql.Date dateSQL = new java.sql.Date(date.getTime());
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			String dateString = df.format(date).toString();
			OrderModel om = new OrderModel();

			try {
				boolean isOrderCheck = om.checkOrderDuplicate(userId, selectedCakeStoreId, selectedCafeStoreId,
						dateSQL);
				if (isOrderCheck == false) {
					req.setAttribute("isOrderCheck", isOrderCheck);
					ServletContext sc = this.getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P010.jsp");
					rd.forward(req, res);
					return;
				} else {
					om.insertOrder(userId, selectedCakeStoreId, selectedCafeStoreId, orderNum);
					orderId = om.getOrderId(userId, selectedCakeStoreId, selectedCafeStoreId, dateSQL);
				}

			} catch (SQLException | NamingException e) {
				System.out.println("SQLの実行に失敗しました");
				System.out.println("SQLException:" + e.getMessage());
				System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
				e.printStackTrace();
				throw new ServletException(e);
			}
			session.setAttribute("cakeStoreName", cakeStoreName);
			session.setAttribute("cafeStoreName", cafeStoreName);
			session.setAttribute("orderNum", orderNum);
			session.setAttribute("date", dateString);
			session.setAttribute("orderId", orderId);
			session.setAttribute("selectedCafeStoreId", selectedCafeStoreId);
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P011.jsp");
			rd.forward(req, res);
			return;

		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
