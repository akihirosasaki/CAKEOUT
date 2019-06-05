package Dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDao {
	public Connection connect() throws SQLException, NamingException {
		DataSource ds = null;
		Connection con = null;
		Context context = null;
		String localName = "java:comp/env/jdbc/myCon";

		//		コンテキストの生成
		context = new InitialContext();
		//		コンテキストを検索
		ds = (DataSource) context.lookup(localName);
		//		データベースへ接続
		con = ds.getConnection();
		return con;
	}

	protected void disconnect(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
}
