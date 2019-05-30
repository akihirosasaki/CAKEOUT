package CakeStoreSearch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import Dao.CakeStoreDao;
import Vo.CakeStoreMenuVo;

public class CakeStoreGetMenuModel {
	public ArrayList<CakeStoreMenuVo> businessMethod(int cakeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreMenuVo> cakeStoreMenuList = new ArrayList<CakeStoreMenuVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreMenuList = CakeStoreDao.getCakeStoreMenu(conn, cakeStoreId);
		}
		return cakeStoreMenuList;
	}
}
