package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.UserVo;

/**
 * @author Akihiro Sasaki
 * Userテーブルを扱うDaoです
 */
public class UserDao extends BaseDao{
	/**
	 * @param conn
	 * @param mailAdd
	 * @return mailCheck
	 * @throws SQLException
	 */
	public boolean accountCheck(Connection conn, String mailAdd) throws SQLException {
		boolean mailCheck = true;

		String sql = "SELECT user_mail_address FROM user WHERE user_mail_address=?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, mailAdd);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				mailCheck=false;
			}
		}
		return mailCheck;
	}

	/**
	 * @param conn
	 * @param mailAdd
	 * @param password
	 * @return loginUser
	 * @throws SQLException
	 */
	public UserVo getLoginUser(Connection conn, String mailAdd, String password) throws SQLException {
		UserVo loginUser = null;

		String sql = "SELECT user_id, user_name, user_mail_address, user_password, user_role FROM user WHERE user_mail_address=?"
				+ " AND user_password=?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, mailAdd);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int userId = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				String userMailAdd = rs.getString("user_mail_address");
				String userPassword = rs.getString("user_password");
				int userRole = rs.getInt("user_role");

				loginUser = new UserVo(userId, userName, userMailAdd, userPassword, userRole);
			}
		}
		return loginUser;
	}

	/**
	 * @param conn
	 * @param userName
	 * @param mailAdd
	 * @param password
	 * @throws SQLException
	 */
	public void accountRegist(Connection conn, String userName, String mailAdd, String password) throws SQLException {
		int rs = 0;

		String sql = "INSERT INTO user (user_name, user_mail_address, user_password, user_status, user_role) VALUES (?,?,?,0,2)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, userName);
			pstmt.setString(2, mailAdd);
			pstmt.setString(3, password);
			rs = pstmt.executeUpdate();
			if (rs != 1) {
				System.out.println("挿入の失敗");
			}
		}
	}
}
