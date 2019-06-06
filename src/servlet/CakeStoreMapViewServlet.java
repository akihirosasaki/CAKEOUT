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

@WebServlet("/CakeStoreMapViewServlet")
public class CakeStoreMapViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CakeStoreMapViewServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		String cakeStoreArea = req.getParameter("cakeStoreArea");
		System.out.println(cakeStoreArea);
		HttpSession session = req.getSession();
		session.setAttribute("cakeStoreArea", cakeStoreArea);
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P006.jsp");
		rd.forward(req, res);
		return;

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
