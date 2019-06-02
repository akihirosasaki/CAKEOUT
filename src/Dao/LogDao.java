package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogDao extends BaseDao {
	public void logging(Connection conn, LocalDateTime ldt, StringBuffer requestUrl, String referer, String userAgent)
			throws SQLException {
		String ldtStr = toStr(ldt, "yyyy-MM-dd HH:mm:ss");
		String requestUrlStr = requestUrl.toString();

		String sql = "insert into log values (?,?,?,?)";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, ldtStr);
			pstmt.setString(2, requestUrlStr);
			pstmt.setString(3, referer);
			pstmt.setString(4, userAgent);
			pstmt.executeUpdate();

		}
	}

	public static String toStr(LocalDateTime localDateTime, String format) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(dateTimeFormatter);

	}

	public void loggingLogin(Connection conn, int userId, String ipAddress, LocalDateTime ldt, String logType) throws SQLException {
		String ldtStr = toStr(ldt, "yyyy-MM-dd HH:mm:ss");

		String sql = "insert into loginlog (user_id, ip_address, login_date, log_type) values (?,?,?,?)";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setString(2, ipAddress);
			pstmt.setString(3, ldtStr);
			pstmt.setString(4, logType);
			pstmt.executeUpdate();

		}
	}
}
