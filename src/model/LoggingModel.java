package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.naming.NamingException;

import dao.LogDao;

/**
 * @author Akihiro Sasaki
 * LogDaoを扱うモデル
 */
public class LoggingModel {
	/**
	 * @param userId
	 * @param ip
	 * @param ldt
	 * @param logType
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void loggingLogin(int userId, String ip, LocalDateTime ldt, String logType) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		dao.LogDao logDao = new dao.LogDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = logDao.connect()) {
			logDao.loggingLogin(conn, userId, ip, ldt, logType);
		}
	}

	/**
	 * @param ldt
	 * @param requestUrl
	 * @param referer
	 * @param userAgent
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void loggingAccess(LocalDateTime ldt, StringBuffer requestUrl, String referer, String userAgent) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		LogDao logDao = new LogDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = logDao.connect()) {
			logDao.loggingAccess(conn, ldt, requestUrl, referer, userAgent);
		}
	}
}
