package servlet;

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

import model.UserModel;

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

		String userName = req.getParameter("userName");
		String mailAdd = req.getParameter("mailAdd");
		String password = req.getParameter("password");

		boolean mailDuplicateCheck;
		String mailFormatCheck = "true";
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		if(!mailAdd.matches(mailFormat)) {
			mailFormatCheck = "false";
		}
		String isPassCheck = "true";
		Pattern passPettern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!-/:-@[-`{-~])[!-~]{8,48}$");
        Matcher passMatch = passPettern.matcher("aA1!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~");
        Boolean result = passMatch.matches();
        if(result) {
        	isPassCheck = "false";
        	req.setAttribute("isPassCheck", isPassCheck);
        	final String url = "/jsp/P001.jsp";
			req.getRequestDispatcher(url).forward(req, res);
			return;
        }

		String userLengthCheck = "true";
		String passLengthCheck = "true";
		if(userName.length()>16) {
			userLengthCheck ="false";
		}
		if(password.length()>16) {
			passLengthCheck="false";
		}
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

		UserModel um = new UserModel();
		try {
			mailDuplicateCheck = um.accountCheck(mailAdd);
			HttpSession session = req.getSession(true);
			if(mailDuplicateCheck==false || mailFormatCheck.equals("false") || userLengthCheck.equals("false") || passLengthCheck.equals("false")) {
				req.setAttribute("mailDuplicateCheck", mailDuplicateCheck);
				req.setAttribute("mailFormatCheck", mailFormatCheck);
				req.setAttribute("userLengthCheck", userLengthCheck);
				req.setAttribute("passLengthCheck", passLengthCheck);
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsp/P003.jsp");
				rd.forward(req, res);
				return;
			}else {
				HttpSession sess = req.getSession(true);
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
