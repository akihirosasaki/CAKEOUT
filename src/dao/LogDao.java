package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ログDao
 * Logテーブルを扱うDaoです
 * @author Akihiro Sasaki

 */
public class LogDao extends BaseDao {
	/**
	 * @param conn コネクション
	 * @param ldt 現在時間
	 * @param requestUrl リクエストされたURL
	 * @param referer リクエスト元ページ
	 * @param userAgent ユーザーエージェント
	 * @throws SQLException
	 */
	public void loggingAccess(Connection conn, LocalDateTime ldt, StringBuffer requestUrl, String referer, String userAgent)
			throws SQLException {
		String ldtStr = toStr(ldt, "yyyy-MM-dd HH:mm:ss");
		String requestUrlStr = requestUrl.toString();

		final String sql = "INSERT INTO log VALUES (?,?,?,?)";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, ldtStr);
			pstmt.setString(2, requestUrlStr);
			pstmt.setString(3, referer);
			pstmt.setString(4, userAgent);
			pstmt.executeUpdate();
		}
	}

	/**
	 * @param localDateTime LocalDatetime型の時間
	 * @param format 指定したい時間フォーマット
	 * @return localDateTime 変換後のLocalDateTime
	 */
	public String toStr(LocalDateTime localDateTime, String format) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(dateTimeFormatter);

	}

	/**
	 * @param conn コネクション
	 * @param userId ユーザーID
	 * @param ipAddress ユーザーのIPアドレス
	 * @param ldt 現在時間
	 * @param logType ログイン、ログアウト、ログイン失敗のいずれか
	 * @throws SQLException
	 */
	public void loggingLogin(Connection conn, int userId, String ipAddress, LocalDateTime ldt, String logType) throws SQLException {
		String ldtStr = toStr(ldt, "yyyy-MM-dd HH:mm:ss");

		final String sql = "INSERT INTO loginlog (user_id, ip_address, login_date, log_type) VALUES (?,?,?,?)";

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
