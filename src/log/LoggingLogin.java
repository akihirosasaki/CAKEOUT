package log;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.naming.NamingException;

import Dao.LogDao;

public class LoggingLogin {
	public void businessMethod(int userId, String ipAddress, LocalDateTime ldt, String logType) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		LogDao logDao = new LogDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = logDao.connect()) {
			logDao.loggingLogin(conn, userId, ipAddress, ldt, logType);
		}
	}
}
