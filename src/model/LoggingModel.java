package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.naming.NamingException;

import dao.LogDao;

/**
 * ログModel
 * LogDaoを扱うモデル
 * @author Akihiro Sasaki
 */
public class LoggingModel {
	/**
	 * @param userId ユーザーID
	 * @param ip Ipアドレス
	 * @param ldt 現在時間
	 * @param logType ログイン、ログアウト、ログイン失敗のいずれか
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void loggingLogin(int userId, String ip, LocalDateTime ldt, String logType) throws SQLException, NamingException {
		//		問い合わせ開始
		dao.LogDao logDao = new dao.LogDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = logDao.connect()) {
			logDao.loggingLogin(conn, userId, ip, ldt, logType);
		}
	}

	/**
	 * @param ldt 現在時間
	 * @param requestUrl リクエスト先のURL
	 * @param referer リクエスト元のURL
	 * @param userAgent ユーザーエージェント
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void loggingAccess(LocalDateTime ldt, StringBuffer requestUrl, String referer, String userAgent) throws SQLException, NamingException {
		//		問い合わせ開始
		LogDao logDao = new LogDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = logDao.connect()) {
			logDao.loggingAccess(conn, ldt, requestUrl, referer, userAgent);
		}
	}
}
