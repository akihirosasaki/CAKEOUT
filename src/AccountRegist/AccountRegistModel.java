package AccountRegist;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import Dao.UserDao;

public class AccountRegistModel {
	public void businessMethod(String userName, String mailAdd, String password) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		UserDao userDao = new UserDao();
		//		コネクション管理はこのレベルで
		try (Connection conn = userDao.connect()) {
			userDao.accountRegist(conn, userName, mailAdd, password);
		}
	}
}
