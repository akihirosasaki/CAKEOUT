package Login;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import Dao.BaseDao;
import Dao.UserDao;
import Vo.UserVo;

public class LoginModel {
	public UserVo businessMethod(String mailAdd, String password) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		UserDao loginDao = new UserDao();
		UserVo loginUser = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = BaseDao.connect()) {
			loginUser = loginDao.selectLoginUser(conn, mailAdd, password);
		}
		return loginUser;
	}
}
