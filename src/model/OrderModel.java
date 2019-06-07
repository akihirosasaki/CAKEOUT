package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import Dao.OrderDao;
import Vo.OrderVo;

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

	public void cancelOrder(int orderId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		OrderDao orderDao = new OrderDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderDao.cancelOrder(conn, orderId);
		}
	}


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

	public void changeOrderNum(int orderId, int orderNum) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		OrderDao orderDao = new OrderDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = orderDao.connect()) {
			orderDao.changeOrderNum(conn, orderId, orderNum);
		}
	}
}
