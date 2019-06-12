package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import dao.OrderDao;
import vo.OrderVo;

/**
 * 注文Dao
 * OrderDaoを扱うモデル
 * @author Akihiro Sasaki
 *
 */
public class OrderModel {
	/**
	 * @param userId ユーザーID
	 * @param cakeStoreId ケーキ屋ID
	 * @param cafeStoreId カフェID
	 * @param orderNum 注文人数
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void insertOrder(int userId, int cakeStoreId, int cafeStoreId, int orderNum)
			throws SQLException, NamingException {
		//		問い合わせ開始
		OrderDao orderDao = new OrderDao();

		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			try {
				orderDao.insertOrder(conn, userId, cakeStoreId, cafeStoreId, orderNum);
			} catch (Exception e) {
				conn.rollback();
			}

		}
	}

	/**
	 * @param userId ユーザーID
	 * @param cakeStoreId ケーキ屋ID
	 * @param cafeStoreId カフェID
	 * @param date 注文日時
	 * @return orderId 注文ID
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int getOrderId(int userId, int cakeStoreId, int cafeStoreId, String date)
			throws SQLException, NamingException {
		//		問い合わせ開始
		OrderDao orderDao = new OrderDao();
		int orderId;
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderId = orderDao.getOrderId(conn, userId, cakeStoreId, cafeStoreId, date);
		}
		return orderId;
	}

	/**
	 * @param userId ユーザーID
	 * @param cakeStoreId ケーキ屋ID
	 * @param cafeStoreId カフェID
	 * @param date 注文日時
	 * @return isOrderCheck　入店ステータス
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean checkOrderDuplicate(int userId, int cakeStoreId, int cafeStoreId, String date)
			throws SQLException, NamingException {
		//		問い合わせ開始
		OrderDao orderDao = new OrderDao();
		boolean isOrderCheck;
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			isOrderCheck = orderDao.checkOrderDuplicate(conn, userId, cakeStoreId, cafeStoreId, date);
		}
		return isOrderCheck;
	}

	/**
	 * @param orderId 注文ID
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void ticketCheck(int orderId) throws SQLException, NamingException {
		//		問い合わせ開始
		OrderDao orderDao = new OrderDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderDao.ticketCheck(conn, orderId);
		}
	}

	/**
	 * @param userId ユーザーID
	 * @return orderList 注文情報を持つVoのリスト
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<OrderVo> getOrderList(int userId) throws SQLException, NamingException {
		//		問い合わせ開始
		OrderDao orderDao = new OrderDao();
		ArrayList<OrderVo> orderList = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderList = orderDao.getOrderList(conn, userId);
		}
		return orderList;
	}

	/**
	 * @param orderId 注文ID
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void cancelOrder(int orderId) throws SQLException, NamingException {
		//		問い合わせ開始
		OrderDao orderDao = new OrderDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			conn.setAutoCommit(false);
			try {
				orderDao.lock(conn, orderId);
				orderDao.cancelOrder(conn, orderId);
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
			}
		}
	}

	/**
	 * @param orderId 注文ID
	 * @return orderItem 注文情報をもつVo
	 * @throws SQLException
	 * @throws NamingException
	 */
	public OrderVo getOrder(int orderId) throws SQLException, NamingException {
		//		問い合わせ開始
		OrderDao orderDao = new OrderDao();
		OrderVo orderItem = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderItem = orderDao.getOrder(conn, orderId);
		}
		return orderItem;
	}

	/**
	 * @param orderId 注文ID
	 * @param orderNum 注文人数
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void changeOrderNum(int orderId, int orderNum) throws SQLException, NamingException {
		//		問い合わせ開始
		OrderDao orderDao = new OrderDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			conn.setAutoCommit(false);
			try {
				orderDao.lock(conn, orderId);
				orderDao.changeOrderNum(conn, orderId, orderNum);
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
			}
		}
	}
}
