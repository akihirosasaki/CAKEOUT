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

@WebServlet("/TicketCheckServlet")
public class TicketCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TicketCheckServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(true);

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
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P018.jsp");
		rd.forward(req, res);
		return;

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
