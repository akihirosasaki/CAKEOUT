package model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import dao.UserDao;
import vo.UserVo;

/**
 * ユーザーModel
 * UserDaoを扱うモデル
 * @author Akihiro Sasaki
 */
public class UserModel {
	/**
	 * @param mailAdd メールアドレス
	 * @param password パスワード
	 * @return loginUser ユーザー情報をもつVo
	 * @throws SQLException
	 * @throws NamingException
	 */
	public UserVo getLoginUser(String mailAdd, String password) throws SQLException, NamingException {
		//		問い合わせ開始
		UserDao userDao = new UserDao();
		UserVo loginUser = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = userDao.connect()) {
			loginUser = userDao.getLoginUser(conn, mailAdd, password);
		}
		return loginUser;
	}

	/**
	 * @param mailAdd メールアドレス
	 * @return mailCheck メールアドレスの重複チェック
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean checkAccount(String mailAdd) throws SQLException, NamingException {
		//		問い合わせ開始
		UserDao userDao = new UserDao();
		boolean mailCheck;
		//		コネクション管理はこのレベルで
		try (Connection conn = userDao.connect()) {
			mailCheck = userDao.accountCheck(conn, mailAdd);
		}
		return mailCheck;
	}

	/**
	 * @param userName ユーザーネーム
	 * @param mailAdd メールアドレス
	 * @param password　パスワード
	 * @throws SQLException
	 * @throws NamingException
	 */
	public void insertAccount(String userName, String mailAdd, String password) throws SQLException, NamingException {
		//		問い合わせ開始
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
