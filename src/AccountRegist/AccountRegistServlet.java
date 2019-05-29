package AccountRegist;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AccountRegistServlet
 */
@WebServlet("/AccountRegistServlet")
public class AccountRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=Shift_JIS");

		String userName = req.getParameter("userName");
		String mailAdd = req.getParameter("mailAdd");
		String password = req.getParameter("password");
		boolean mailCheck;


		String exceptPattern = "<|>|\"|\'|&";
		Pattern p = Pattern.compile(exceptPattern);
		Matcher m = p.matcher(mailAdd);
		String isExceptionString = "false";
		if(m.find()) {
			isExceptionString = "true";
			req.setAttribute("isExceptionString", isExceptionString);
			final String url = "IndexServlet";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		}

		AccountCheckModel acm = new AccountCheckModel();
		try {
			mailCheck = acm.businessMethod(mailAdd);
			System.out.println(mailCheck);
			HttpSession session = req.getSession(true);
			if(mailCheck==false) {
				session.setAttribute("mailCheck", mailCheck);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P003.jsp");
				rd.forward(req, res);
				return;
			}else {
				HttpSession sess = req.getSession(true);
				sess.invalidate();

				session.setAttribute("userName", userName);
				session.setAttribute("mailAdd", mailAdd);
				session.setAttribute("password", password);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P004.jsp");
				rd.forward(req, res);
				return;
			}
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
