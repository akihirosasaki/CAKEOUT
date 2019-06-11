package model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import dao.UserDao;
import vo.UserVo;

/**
 * @author Akihiro Sasaki
 * UserDaoを扱うモデル
 */
public class UserModel {
	/**
	 * @param mailAdd
	 * @param password
	 * @return loginUser
	 * @throws SQLException
	 * @throws NamingException
	 */
	public UserVo getLoginUser(String mailAdd, String password) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		UserDao userDao = new UserDao();
		UserVo loginUser = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = userDao.connect()) {
			loginUser = userDao.getLoginUser(conn, mailAdd, password);
		}
		return loginUser;
	}

	/**
	 * @param mailAdd
	 * @return mailCheck
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean checkAccount(String mailAdd) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		UserDao userDao = new UserDao();
		boolean mailCheck;
		//		コネクション管理はこのレベルで
		try (Connection conn = userDao.connect()) {
			mailCheck = userDao.accountCheck(conn, mailAdd);
		}
		return mailCheck;
	}

	/**
	 * @param userName
	 * @param mailAdd
	 * @param password
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void insertAccount(String userName, String mailAdd, String password) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		UserDao userDao = new UserDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = userDao.connect()) {
			try {
				userDao.accountRegist(conn, userName, mailAdd, password);
			} catch (Exception e) {
				conn.rollback();
			}
		}
	}
}
