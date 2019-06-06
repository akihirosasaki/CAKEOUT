package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OrderSelectServlet")
public class OrderSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderSelectServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(true);
		int orderId;
		String orderIdJsp = req.getParameter("orderId");
		if (orderIdJsp == null) {
			orderId = (Integer) session.getAttribute("orderId");
		} else {
			orderId = Integer.parseInt(orderIdJsp);
		}

		session.setAttribute("orderId", orderId);

		String orderStatus = (String) session.getAttribute("orderStatus");

		if (orderStatus.equals("ticket")) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/TicketConfirmServlet");
			System.out.println("ticket");
			rd.forward(req, res);
			return;
		} else if (orderStatus.equals("cancel")) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P013.jsp");
			System.out.println("cancel");
			rd.forward(req, res);
			return;
		} else {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P015.jsp");
			System.out.println("change");
			rd.forward(req, res);
			return;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
