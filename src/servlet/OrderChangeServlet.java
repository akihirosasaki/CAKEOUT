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
 * @author Akihiro Sasaki
 * 注文の人数変更を実行するサーブレット
 */
@WebServlet("/OrderChangeServlet")
public class OrderChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public OrderChangeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
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

		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P016.jsp");
		rd.forward(req, res);
		return;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
