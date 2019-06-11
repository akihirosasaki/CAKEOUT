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
 * 注文変更完了画面表示サーブレット
 * 注文変更完了画面を表示するサーブレット
 * @author Akihiro Sasaki
 */
@WebServlet("/OrderChangeViewServlet")
public class OrderChangeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderChangeViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
			rd.forward(req, res);
			return;
		} else {
			if (session.getAttribute("isNullCheck")==null) {
				session.setAttribute("isNullCheck", null);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/IndexServlet");
				rd.forward(req, res);
				return;
			}
		}
		final String url = "/jsp/P016.jsp";
		req.getRequestDispatcher(url).forward(req, res);
		return;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
