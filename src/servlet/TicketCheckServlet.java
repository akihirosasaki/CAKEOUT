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
 * 入店確認サーブレット
 * 入店が確認された注文のDBを更新するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/TicketCheckServlet")
public class TicketCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TicketCheckServlet() {
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

		int orderId = (Integer) session.getAttribute("orderId");

		OrderModel om = new OrderModel();

		try {
			om.ticketCheck(orderId);
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}
		String isNullCheck = "true";
		session.setAttribute("isNullCheck", isNullCheck);
		final String url = "TicketCheckViewServlet";
		res.setStatus(HttpServletResponse.SC_SEE_OTHER);
		res.sendRedirect(url);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
