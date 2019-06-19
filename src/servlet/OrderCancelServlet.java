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
 * 注文キャンセルサーブレット
 * キャンセルされた注文をDBから削除するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/OrderCancelServlet")
public class OrderCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderCancelServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String isNullCheck = req.getParameter("isNullCheck");
		if (session == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		} else {
			if(!("true".equals(isNullCheck))) {
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				return;
			}
		}
		int orderId = (Integer) session.getAttribute("orderId");

		OrderModel om = new OrderModel();

		try {
			om.cancelOrder(orderId);
		} catch (SQLException | NamingException e) {
			throw new ServletException(e);
		}
		session.setAttribute("isNullCheck", isNullCheck);
		final String url = "OrderCancelViewServlet";
		res.setStatus(HttpServletResponse.SC_SEE_OTHER);
		res.sendRedirect(url);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
