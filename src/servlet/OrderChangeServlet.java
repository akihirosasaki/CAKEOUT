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

/**
 * 人数変更サーブレット
 * 注文の人数変更を実行するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/OrderChangeServlet")
public class OrderChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderChangeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String orderNumString = req.getParameter("orderNum");
		if (session == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		} else {
			if (orderNumString == null) {
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				return;
			}
		}
		int orderId = (Integer) session.getAttribute("orderId");
		int orderNum = Integer.parseInt(req.getParameter("orderNum"));
		OrderModel om = new OrderModel();
		try {
			om.changeOrderNum(orderId, orderNum);
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}
		String isNullCheck = "true";
		session.setAttribute("isNullCheck", isNullCheck);
		final String url = "OrderChangeViewServlet";
		res.setStatus(HttpServletResponse.SC_SEE_OTHER);
		res.sendRedirect(url);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
