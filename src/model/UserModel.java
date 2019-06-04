package model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import Dao.UserDao;
import Vo.UserVo;

public class UserModel {
	public UserVo selectLoginUser(String mailAdd, String password) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		UserDao userDao = new UserDao();
		UserVo loginUser = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = userDao.connect()) {
			loginUser = userDao.selectLoginUser(conn, mailAdd, password);
		}
		return loginUser;
	}

	public boolean accountCheck(String mailAdd) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		UserDao userDao = new UserDao();
		boolean mailCheck;
		//		コネクション管理はこのレベルで
		try (Connection conn = userDao.connect()) {
			mailCheck = UserDao.accountCheck(conn, mailAdd);
		}
		return mailCheck;
	}

	public void accountRegist(String userName, String mailAdd, String password) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		UserDao userDao = new UserDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = userDao.connect()) {
			userDao.accountRegist(conn, userName, mailAdd, password);
		}
	}
}
