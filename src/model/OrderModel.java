package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import dao.OrderDao;
import vo.OrderVo;

/**
 * @author Akihiro Sasaki
 * OrderDaoを扱うモデル
 */
public class OrderModel {
	/**
	 * @param userId
	 * @param cakeStoreId
	 * @param cafeStoreId
	 * @param orderNum
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void insertOrder(int userId, int cakeStoreId, int cafeStoreId, int orderNum) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
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
	 * @param userId
	 * @param cakeStoreId
	 * @param cafeStoreId
	 * @param date
	 * @return orderId
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int getOrderId(int userId, int cakeStoreId, int cafeStoreId, String date) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		OrderDao orderDao = new OrderDao();
		int orderId;
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderId = orderDao.getOrderId(conn, userId, cakeStoreId, cafeStoreId, date);
		}
		return orderId;
	}

	/**
	 * @param userId
	 * @param cakeStoreId
	 * @param cafeStoreId
	 * @param date
	 * @return isOrderCheck
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean checkOrderDuplicate(int userId, int cakeStoreId, int cafeStoreId, String date) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		OrderDao orderDao = new OrderDao();
		boolean isOrderCheck;
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			isOrderCheck = orderDao.checkOrderDuplicate(conn, userId, cakeStoreId, cafeStoreId, date);
		}
		return isOrderCheck;
	}

	/**
	 * @param orderId
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void ticketCheck(int orderId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		OrderDao orderDao = new OrderDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderDao.ticketCheck(conn, orderId);
		}
	}

	/**
	 * @param userId
	 * @return orderList
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<OrderVo> getOrderList(int userId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		OrderDao orderDao = new OrderDao();
		ArrayList<OrderVo> orderList = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderList = orderDao.getOrderList(conn, userId);
		}
		return orderList;
	}

	/**
	 * @param orderId
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void cancelOrder(int orderId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
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
	 * @param orderId
	 * @return orderItem
	 * @throws SQLException
	 * @throws NamingException
	 */
	public OrderVo getOrder(int orderId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		OrderDao orderDao = new OrderDao();
		OrderVo orderItem = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderItem = orderDao.getOrder(conn, orderId);
		}
		return orderItem;
	}

	/**
	 * @param orderId
	 * @param orderNum
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void changeOrderNum(int orderId, int orderNum) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
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
