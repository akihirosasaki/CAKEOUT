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

/**
 * マイページアクション振り分けサーブレット
 * ユーザーのアクションごとにそれぞれの処理に分岐させるサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/OrderSelectServlet")
public class OrderSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderSelectServlet() {
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
		int orderId;
		String orderIdJsp = req.getParameter("orderId");
		if (orderIdJsp == null) {
			orderId = (Integer) session.getAttribute("orderId");
		} else {
			orderId = Integer.parseInt(orderIdJsp);
		}

		session.setAttribute("orderId", orderId);

		String orderStatus = (String) session.getAttribute("orderStatus");
		String destination = null;
		if ("ticket".equals(orderStatus)) {
			destination = "/TicketConfirmServlet";
		} else if ("cancel".equals(orderStatus)) {
			destination = "/jsp/P013.jsp";
		} else {
			destination = "/jsp/P015.jsp";
		}
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(destination);
		rd.forward(req, res);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
