package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDao extends BaseDao{
	public boolean checkOrderDuplicate(Connection conn, int userId, int cakeStoreId, int cafeStoreId, Date date) throws SQLException {
		boolean isOrderCheck = true;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = df.format(date).toString();

		String sql = "select * from order_info where user_id = ? and cake_store_id = ? and cafe_store_id = ? and substring(created_at, 1, 10) = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, cakeStoreId);
			pstmt.setInt(3, cafeStoreId);
			pstmt.setString(4, dateString);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					isOrderCheck = false;
				}
			}
		}
		return isOrderCheck;
	}

	public void insertOrder(Connection conn, int userId, int cakeStoreId, int cafeStoreId, int orderNum) throws SQLException {
		int rs = 0;

		String sql = "insert into order_info (user_id, cake_store_id, cafe_store_id, order_num) values (?,?,?,?)";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, cakeStoreId);
			pstmt.setInt(3, cafeStoreId);
			pstmt.setInt(4, orderNum);
			rs = pstmt.executeUpdate();
			if (rs != 1) {
				System.out.println("挿入の失敗");
			}
		}
	}

	public int getOrderId(Connection conn, int userId, int cakeStoreId, int cafeStoreId, Date date) throws SQLException {
		int orderId = 0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = df.format(date).toString();

		String sql = "select order_id from order_info where user_id = ? and cake_store_id = ? and cafe_store_id = ? and substring(created_at, 1, 10) = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, cakeStoreId);
			pstmt.setInt(3, cafeStoreId);
			pstmt.setString(4, dateString);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					orderId = rs.getInt("order_id");
				}
			}
		}
		return orderId;
	}

	public void ticketCheck(Connection conn, int orderId) throws SQLException {
		int rs = 0;

		String sql = "update order_info set is_checked = 1 where order_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderId);
			rs = pstmt.executeUpdate();
			if (rs != 1) {
				System.out.println("挿入の失敗");
			}
		}
	}
}
