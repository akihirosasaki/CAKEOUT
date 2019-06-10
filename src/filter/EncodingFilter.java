/**
 *
 */
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 *
 * フィルター名：EncodingFilter
 * 全てのURLに対して適用
 * encoding値：utf-8
 */
@WebFilter(filterName = "EncodingFilter",
urlPatterns = { "/*" },
initParams = { @WebInitParam(name = "encoding", value = "utf-8") })
public class EncodingFilter implements Filter {
	/**
	 * Encoding名.
	 */
	private String encoding = null;

	/* (非 Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/* (非 Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response,
			FilterChain chain)
					throws IOException, ServletException {
		//リクエストに対してエンコーディングをutf-8にセット
		request.setCharacterEncoding(encoding);
		//レスポンスに対してエンコーディングををutf-8にセット
		response.setCharacterEncoding(encoding);

		//連続するフィルタがある場合は、次のフィルタを呼び出します。
		//最後のフィルタの場合は元の要求処理を呼び出します。
		chain.doFilter(request, response);

	}

	/* (非 Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//初期化パラメータから値を読み込みセット
		encoding = filterConfig.getInitParameter("encoding");

	}

}
