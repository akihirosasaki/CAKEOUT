package CakeStoreSearch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import Dao.CakeStoreDao;

public class CakeStoreGetImgModel {
	public ArrayList<String> businessMethod(int cakeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<String> cakeStoreImg;
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreImg = CakeStoreDao.getCakeStoreImg(conn, cakeStoreId);
		}
		return cakeStoreImg;
	}
}
