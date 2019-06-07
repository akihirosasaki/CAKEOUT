package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Vo.OrderVo;

public class OrderDao extends BaseDao {
	public boolean checkOrderDuplicate(Connection conn, int userId, int cakeStoreId, int cafeStoreId, Date date)
			throws SQLException {
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
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					isOrderCheck = false;
				}
			}
		}
		return isOrderCheck;
	}

	public void insertOrder(Connection conn, int userId, int cakeStoreId, int cafeStoreId, int orderNum)
			throws SQLException {
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

	public int getOrderId(Connection conn, int userId, int cakeStoreId, int cafeStoreId, Date date)
			throws SQLException {
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
			try (ResultSet rs = pstmt.executeQuery();) {
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

	public ArrayList<OrderVo> getOrderList(Connection conn, int userId) throws SQLException {
		ArrayList<OrderVo> orderList = new ArrayList<OrderVo>();

		String sql = "select t1.order_id, t1.user_id, t1.cake_store_id, t2.cake_store_name, t1.cafe_store_id, "
				+ "t3.cafe_store_name, t1.order_num, t1.created_at, t1.updated_at, t4.cake_store_img_url, "
				+ "t5.cafe_store_img_url from order_info as t1 left join cake_store as t2 "
				+ "on t1.cake_store_id = t2.cake_store_id left join cafe_store as t3 on "
				+ "t1.cafe_store_id = t3.cafe_store_id left join cake_store_img as t4 on "
				+ "t1.cake_store_id = t4.cake_store_id left join cafe_store_img as t5 on "
				+ "t1.cafe_store_id = t5.cafe_store_id where user_id = ? and is_checked = 0 and "
				+ "t4.cake_store_img_primary=1 and t5.cafe_store_img_primary=1";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					int orderId = rs.getInt("order_id");
					int cakeStoreId = rs.getInt("cake_store_id");
					String cakeStoreName = rs.getString("cake_store_name");
					int cafeStoreId = rs.getInt("cafe_store_id");
					String cafeStoreName = rs.getString("cafe_store_name");
					int orderNum = rs.getInt("order_num");
					Date createdAt = rs.getDate("created_at");
					Date updatedAt = rs.getDate("updated_at");
					String cakeStorePrimaryImg = rs.getString("cake_store_img_url");
					String cafeStorePrimaryImg = rs.getString("cafe_store_img_url");

					orderList.add(new OrderVo(orderId, userId, cakeStoreId, cakeStoreName, cafeStoreId, cafeStoreName,
							orderNum, createdAt, updatedAt, cakeStorePrimaryImg, cafeStorePrimaryImg));
				}
			}
		}
		return orderList;
	}

	public void cancelOrder(Connection conn, int orderId) throws SQLException {
		int rs = 0;

		String sql = "delete from order_info where order_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderId);
			rs = pstmt.executeUpdate();
			if (rs != 1) {
				System.out.println("挿入の失敗");
			}
		}
	}

	public OrderVo getOrder(Connection conn, int orderId)
			throws SQLException {
		OrderVo orderItem = null;

		String sql = "select t1.user_id, t1.cake_store_id, t2.cake_store_name, t1.cafe_store_id, t3.cafe_store_name, "
				+ "t1.order_num, t1.created_at "
				+ "from order_info as t1 left join cake_store as t2 on t1.cake_store_id = t2.cake_store_id left join "
				+ "cafe_store as t3 on t1.cafe_store_id = t3.cafe_store_id where order_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderId);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					int userId = rs.getInt("user_id");
					int cakeStoreId = rs.getInt("cake_store_id");
					String cakeStoreName = rs.getString("cake_store_name");
					int cafeStoreId = rs.getInt("cafe_store_id");
					String cafeStoreName = rs.getString("cafe_store_name");
					int orderNum = rs.getInt("order_num");
					Date createdAt = rs.getDate("created_at");

					orderItem = new OrderVo(orderId, userId, cakeStoreId, cakeStoreName, cafeStoreId, cafeStoreName, orderNum, createdAt,
							createdAt, "", "");
				}
			}
			return orderItem;
		}
	}

	public void changeOrderNum(Connection conn, int orderId, int orderNum) throws SQLException {
		int rs = 0;

		String sql = "update order_info set order_num = ? where order_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderNum);
			pstmt.setInt(2, orderId);
			rs = pstmt.executeUpdate();
			if (rs != 1) {
				System.out.println("挿入の失敗");
			}
		}
	}
}
