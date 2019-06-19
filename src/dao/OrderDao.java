package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.OrderVo;

/**
 * 注文Dao
 * Orderテーブルを扱うDaoです
 * @author Akihiro Sasaki
 */
public class OrderDao extends BaseDao {

	/**
	 * @param conn コネクション
	 * @param orderId 注文ID
	 * @throws SQLException
	 */
	public void lock(Connection conn, int orderId) throws SQLException {

		final String sql = "SELECT order_id, user_id, cake_store_id, cafe_store_id, order_num, created_at, UPDATEd_at,"
				+ " is_checked FROM order_info WHERE order_id=? FOR UPDATE";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderId);
			pstmt.executeQuery();
		}
	}

	/**
	 * @param conn コネクション
	 * @param userId ユーザーID
	 * @param cakeStoreId ケーキ屋ID
	 * @param cafeStoreId カフェID
	 * @param date 注文日時
	 * @return isOrderCheck 入店ステータス
	 * @throws SQLException
	 */
	public boolean checkOrderDuplicate(Connection conn, int userId, int cakeStoreId, int cafeStoreId, String date)
			throws SQLException {
		boolean isOrderCheck = true;

		final String sql = "SELECT order_id FROM order_info WHERE user_id = ? AND cake_store_id = ? AND cafe_store_id = ? AND SUBSTRING(created_at, 1, 10) = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, cakeStoreId);
			pstmt.setInt(3, cafeStoreId);
			pstmt.setString(4, date);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					isOrderCheck = false;
				}
			}
		}
		return isOrderCheck;
	}

	/**
	 * @param conn コネクション
	 * @param userId ユーザーID
	 * @param cakeStoreId ケーキ屋ID
	 * @param cafeStoreId カフェID
	 * @param orderNum 注文人数
	 * @throws SQLException
	 */
	public void insertOrder(Connection conn, int userId, int cakeStoreId, int cafeStoreId, int orderNum)
			throws SQLException {
		final String sql = "INSERT INTO order_info (user_id, cake_store_id, cafe_store_id, order_num) VALUES (?,?,?,?)";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, cakeStoreId);
			pstmt.setInt(3, cafeStoreId);
			pstmt.setInt(4, orderNum);
			pstmt.executeUpdate();
		}
	}

	/**
	 * @param conn コネクション
	 * @param userId ユーザーID
	 * @param cakeStoreId ケーキ屋ID
	 * @param cafeStoreId カフェID
	 * @param date 注文日時
	 * @return orderId 注文ID
	 * @throws SQLException
	 */
	public int getOrderId(Connection conn, int userId, int cakeStoreId, int cafeStoreId, String date)
			throws SQLException {
		int orderId = 0;

		final String sql = "SELECT order_id FROM order_info WHERE user_id = ? AND cake_store_id = ? AND cafe_store_id = ? AND substring(created_at, 1, 10) = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, cakeStoreId);
			pstmt.setInt(3, cafeStoreId);
			pstmt.setString(4, date);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					orderId = rs.getInt("order_id");
				}
			}
		}
		return orderId;
	}

	/**
	 * @param conn コネクション
	 * @param orderId 注文ID
	 * @throws SQLException
	 */
	public void ticketCheck(Connection conn, int orderId) throws SQLException {
		System.out.println("check");

		final String sql = "UPDATE order_info SET is_checked = 1 WHERE order_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderId);
			pstmt.executeUpdate();
		}
	}

	/**
	 * @param conn コネクション
	 * @param userId ユーザーID
	 * @return orderList 注文情報をもつVo
	 * @throws SQLException
	 */
	public ArrayList<OrderVo> getOrderList(Connection conn, int userId) throws SQLException {
		ArrayList<OrderVo> orderList = new ArrayList<OrderVo>();

		final String sql = "SELECT t1.order_id, t1.user_id, t1.cake_store_id, t2.cake_store_name, t1.cafe_store_id, "
				+ "t3.cafe_store_name, t1.order_num, t1.created_at, t1.UPDATEd_at, t4.cake_store_img_url, "
				+ "t5.cafe_store_img_url FROM order_info AS t1 LEFT JOIN cake_store AS t2 "
				+ "on t1.cake_store_id = t2.cake_store_id LEFT JOIN cafe_store AS t3 ON "
				+ "t1.cafe_store_id = t3.cafe_store_id LEFT JOIN cake_store_img AS t4 ON "
				+ "t1.cake_store_id = t4.cake_store_id LEFT JOIN cafe_store_img AS t5 ON "
				+ "t1.cafe_store_id = t5.cafe_store_id WHERE user_id = ? AND is_checked = 0 AND "
				+ "t4.cake_store_img_primary=1 AND t5.cafe_store_img_primary=1";

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
					Date UpdatedAt = rs.getDate("UPDATEd_at");
					String cakeStorePrimaryImg = rs.getString("cake_store_img_url");
					String cafeStorePrimaryImg = rs.getString("cafe_store_img_url");

					orderList.add(new OrderVo(orderId, userId, cakeStoreId, cakeStoreName, cafeStoreId, cafeStoreName,
							orderNum, createdAt, UpdatedAt, cakeStorePrimaryImg, cafeStorePrimaryImg));
				}
			}
		}
		return orderList;
	}

	/**
	 * @param conn コネクション
	 * @param orderId 注文ID
	 * @throws SQLException
	 */
	public void cancelOrder(Connection conn, int orderId) throws SQLException {

		final String sql = "DELETE FROM order_info WHERE order_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderId);
			pstmt.executeUpdate();
		}
	}

	/**
	 * @param conn コネクション
	 * @param orderId 注文ID
	 * @return orderItem 注文情報をもつVo
	 * @throws SQLException
	 */
	public OrderVo getOrder(Connection conn, int orderId)
			throws SQLException {
		OrderVo orderItem = null;

		final String sql = "SELECT t1.user_id, t1.cake_store_id, t2.cake_store_name, t1.cafe_store_id, t3.cafe_store_name, t1.order_num, t1.created_at, t4.cake_store_img_url, t5.cafe_store_img_url " +
				"FROM order_info AS t1 LEFT JOIN cake_store AS t2 ON t1.cake_store_id = t2.cake_store_id LEFT JOIN " +
				"cafe_store AS t3 ON t1.cafe_store_id = t3.cafe_store_id " +
				"LEFT JOIN cake_store_img AS t4 ON t1.cake_store_id = t4.cake_store_id " +
				"LEFT JOIN cafe_store_img AS t5 ON t1.cafe_store_id = t5.cafe_store_id " +
				"WHERE t4.cake_store_img_primary = 1 AND t5.cafe_store_img_primary = 1 AND order_id = ?";

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
					String cakeStorePrimaryImg = rs.getString("cake_store_img_url");
					String cafeStorePrimaryImg = rs.getString("cafe_store_img_url");

					orderItem = new OrderVo(orderId, userId, cakeStoreId, cakeStoreName, cafeStoreId, cafeStoreName,
							orderNum, createdAt,
							createdAt, cakeStorePrimaryImg, cafeStorePrimaryImg);
				}
			}
			return orderItem;
		}
	}

	/**
	 * @param conn コネクション
	 *  @param orderId 注文ID
	 * @param orderNum 注文人数
	 * @throws SQLException
	 */
	public void changeOrderNum(Connection conn, int orderId, int orderNum) throws SQLException {

		final String sql = "UPDATE order_info SET order_num = ? WHERE order_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderNum);
			pstmt.setInt(2, orderId);
			pstmt.executeUpdate();
		}
	}
}
