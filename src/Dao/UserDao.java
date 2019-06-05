package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Vo.UserVo;

public class UserDao extends BaseDao{
	public static boolean accountCheck(Connection conn, String mailAdd) throws SQLException {
		boolean mailCheck = true;

		String sql = "select user_mail_address from user where user_mail_address=?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, mailAdd);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				mailCheck=false;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return mailCheck;
	}

	public UserVo selectLoginUser(Connection conn, String mailAdd, String password) throws SQLException {
		UserVo loginUser = null;

		String sql = "select user_id, user_name, user_mail_address, user_password, user_role from user where user_mail_address=?"
				+ " and user_password=?";

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
		}catch (Exception e) {
			// TODO: handle exception
		}
		return loginUser;
	}

	public void accountRegist(Connection conn, String userName, String mailAdd, String password) throws SQLException {
		int rs = 0;

		String sql = "insert into user (user_name, user_mail_address, user_password, user_status, user_role) values (?,?,?,0,2)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, userName);
			pstmt.setString(2, mailAdd);
			pstmt.setString(3, password);
			rs = pstmt.executeUpdate();
			if (rs != 1) {
				System.out.println("挿入の失敗");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
