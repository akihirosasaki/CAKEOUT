package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;

import Dao.OrderDao;

public class OrderModel {
	public void insertOrder(int userId, int cakeStoreId, int cafeStoreId, int orderNum) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		OrderDao orderDao = new OrderDao();

		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderDao.insertOrder(conn, userId, cakeStoreId, cafeStoreId, orderNum);
		}
	}

	public int getOrderId(int userId, int cakeStoreId, int cafeStoreId, Date date) throws SQLException, NamingException {
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

	public boolean checkOrderDuplicate(int userId, int cakeStoreId, int cafeStoreId, Date date) throws SQLException, NamingException {
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

	public void ticketCheck(int orderId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		OrderDao orderDao = new OrderDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderDao.ticketCheck(conn, orderId);
		}
	}
}
