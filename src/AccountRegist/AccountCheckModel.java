package AccountRegist;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import Dao.UserDao;

public class AccountCheckModel {
	public boolean businessMethod(String mailAdd) throws SQLException, NamingException {
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
}
