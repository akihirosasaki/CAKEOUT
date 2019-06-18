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
 * チケット生成サーブレット
 * ユーザーが選択したケーキ屋、カフェ、人数をもとに、チケットを生成するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TicketServlet() {
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
		}
		String cakeStoreName = req.getParameter("cakeStoreName");
		String cafeStoreName = req.getParameter("cafeStoreName");
		String[] orderNumString = req.getParameterValues("orderNum");
		String isOrdered = "true";
		String isNumCheck = "true";
		String sendPageToken = req.getParameter("pageToken");
		String sessionPageToken = (String) session.getAttribute("token");
		String errorReason;
		if (sessionPageToken == null || sendPageToken == null) {
			errorReason = "正規の順序でアクセスしていません";
			req.setAttribute("errorReason", errorReason);
			final String url = "/jsp/P020.jsp";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		} else {
			if (!(sendPageToken.equals(sessionPageToken))) {
				errorReason = "正規の順序でアクセスしていません";
				req.setAttribute("errorReason", errorReason);
				final String url = "/jsp/p020.jsp";
				req.getRequestDispatcher(url).forward(req, res);
				return;
			}
		}
		if (cakeStoreName != null && cafeStoreName != null && orderNumString != null) {
			session.setAttribute("cakeStoreName", cakeStoreName);
			session.setAttribute("cafeStoreName", cafeStoreName);
			session.setAttribute("orderNum", orderNumString);
			session.setAttribute("isOrdered", isOrdered);
			if (orderNumString[0].matches("[-_@+*;:#$%&A-Za-z]+") || orderNumString[0].length() > 2) {
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
			orderNumString = (String[]) session.getAttribute("orderNum");
			String isInputCheck = "true";
			int orderNum;
			if (orderNumString[0] == null || "".equals(orderNumString[0])) {
				isInputCheck = "false";
				req.setAttribute("isInputCheck", isInputCheck);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P010.jsp");
				rd.forward(req, res);
				return;
			} else {
				orderNum = Integer.parseInt(orderNumString[0]);
			}

			String dateSQL = toStr(LocalDateTime.now(), "yyyy-MM-dd");
			System.out.println(dateSQL);
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
			String isNullCheck = "true";
			session.setAttribute("isNullCheck", isNullCheck);
			session.setAttribute("cakeStoreName", cakeStoreName);
			session.setAttribute("cafeStoreName", cafeStoreName);
			session.setAttribute("orderNum", orderNum);
			session.setAttribute("date", dateSQL);
			session.setAttribute("orderId", orderId);
			session.setAttribute("selectedCafeStoreId", selectedCafeStoreId);
			final String url = "TicketViewServlet";
			res.setStatus(HttpServletResponse.SC_SEE_OTHER);
			res.sendRedirect(url);
			return;
		}
	}

	public static String toStr(LocalDateTime localDateTime, String format) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(dateTimeFormatter);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
