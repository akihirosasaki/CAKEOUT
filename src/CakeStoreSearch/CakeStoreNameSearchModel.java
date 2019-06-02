package CakeStoreSearch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import Dao.CakeStoreDao;
import Vo.CakeStoreVo;

public class CakeStoreNameSearchModel {
	public ArrayList<CakeStoreVo> businessMethod(String cakeStoreNameInput) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreVo> cakeStoreList = new ArrayList<CakeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreList = cakeStoreDao.cakeStoreNameSearch(conn, cakeStoreNameInput);
		}
		return cakeStoreList;
	}
}
