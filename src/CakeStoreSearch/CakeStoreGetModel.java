package CakeStoreSearch;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import Dao.CakeStoreDao;
import Vo.CakeStoreVo;

public class CakeStoreGetModel {
	public CakeStoreVo businessMethod(int cakeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		CakeStoreVo cakeStoreInfo = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreInfo = CakeStoreDao.getCakeStoreInfo(conn, cakeStoreId);
		}
		return cakeStoreInfo;
	}
}
