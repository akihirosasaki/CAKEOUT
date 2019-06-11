package filter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import model.LoggingModel;

/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter(urlPatterns = {"/*"})
public class LogFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LogFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		// 全リクエストヘッダ名を取得
		// 日時取得
		LocalDateTime ldt = LocalDateTime.now();
		// URL取得
		StringBuffer requestUrl = req.getRequestURL();
		String requestUrlStr = requestUrl.toString();
		String referer = req.getHeader("Referer");
		String userAgent = req.getHeader("user-agent");
		System.out.println(referer);
		System.out.println(requestUrlStr);

		LoggingModel lm = new LoggingModel();
		try {
			lm.loggingAccess(ldt, requestUrl, referer, userAgent);
		} catch (SQLException | NamingException e) {
			System.out.println("SQLの実行に失敗しました");
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("VendorError:" + ((SQLException) e).getErrorCode());
			e.printStackTrace();
			throw new ServletException(e);
		}


		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
