package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

/**
 * @author Akihiro Sasaki
 * ユーザーが選択したケーキ屋、カフェ、人数をもとに、チケットを生成するサーブレット
 */
@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TicketServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String cakeStoreName = req.getParameter("cakeStoreName");
		String cafeStoreName = req.getParameter("cafeStoreName");
		String orderNumString = req.getParameter("orderNum");
		String isOrdered = "true";
		String isNumCheck = "true";
		if (cakeStoreName != null && cafeStoreName != null && orderNumString != null) {
			session.setAttribute("cakeStoreName", cakeStoreName);
			session.setAttribute("cafeStoreName", cafeStoreName);
			session.setAttribute("orderNum", orderNumString);
			session.setAttribute("isOrdered", isOrdered);
			if (orderNumString.matches("[-_@+*;:#$%&A-Za-z]+") || orderNumString.length() > 2) {
				isNumCheck = "false";
				req.setAttribute("isNumCheck", isNumCheck);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P010.jsp");
				rd.forward(req, res);
				return;
			}
		}

		String isLogin = (String) session.getAttribute("isLogin");
		if ("false".equals(isLogin)) {

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
			if (orderNumString == null || "".equals(orderNumString)) {
				isInputCheck = "false";
				req.setAttribute("isInputCheck", isInputCheck);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P010.jsp");
				rd.forward(req, res);
				return;
			} else {
				orderNum = Integer.parseInt(orderNumString);
			}

			String dateSQL = toStr(LocalDateTime.now(), "yyyy/MM/dd");
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
			session.setAttribute("date", dateSQL);
			session.setAttribute("orderId", orderId);
			session.setAttribute("selectedCafeStoreId", selectedCafeStoreId);
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P011.jsp");
			rd.forward(req, res);
			return;

		}

	}

	public static String toStr(LocalDateTime localDateTime, String format) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(dateTimeFormatter);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
