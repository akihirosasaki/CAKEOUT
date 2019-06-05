package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.naming.NamingException;

public class LoggingModel {
	public void businessMethod(LocalDateTime ldt, StringBuffer requestUrl, String referer, String userAgent) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		Dao.LogDao logDao = new Dao.LogDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = logDao.connect()) {
			logDao.logging(conn, ldt, requestUrl, referer, userAgent);
		}
	}
}
