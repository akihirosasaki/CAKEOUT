package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import dao.CakeStoreDao;
import vo.CakeStoreMenuVo;
import vo.CakeStoreVo;

/**
 * @author Akihiro Sasaki
 * CakeStoreDaoを扱うモデル
 */
public class CakeStoreSearchModel {
	/**
	 * @param searchArea
	 * @return popularCakeStores
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CakeStoreVo> getPopularCakeStore(String searchArea) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreVo> popularCakeStores = new ArrayList<CakeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			popularCakeStores = cakeStoreDao.getPopularCakeStore(conn, searchArea);
		}
		return popularCakeStores;
	}

	/**
	 * @param cakeStoreNameInput
	 * @return cakeStoreList
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CakeStoreVo> searchCakeStoreName(String cakeStoreNameInput) throws SQLException, NamingException {
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

	/**
	 * @param cakeStoreArea
	 * @param statusList
	 * @return cakeStoreList
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CakeStoreVo> getCakeStoreByArea(String cakeStoreArea, String[] statusList) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreVo> cakeStoreList = new ArrayList<CakeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreList = cakeStoreDao.getCakeStoreByArea(conn, cakeStoreArea, statusList);
		}
		return cakeStoreList;
	}

	/**
	 * @param cakeStoreId
	 * @return cakeStoreMenuList
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CakeStoreMenuVo> getCakeStoreMenu(int cakeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreMenuVo> cakeStoreMenuList = new ArrayList<CakeStoreMenuVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreMenuList = cakeStoreDao.getCakeStoreMenu(conn, cakeStoreId);
		}
		return cakeStoreMenuList;
	}

	/**
	 * @param cakeStoreId
	 * @return cakeStoreInfo
	 * @throws SQLException
	 * @throws NamingException
	 */
	public CakeStoreVo getCakeStoreInfo(int cakeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		CakeStoreVo cakeStoreInfo = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreInfo = cakeStoreDao.getCakeStoreInfo(conn, cakeStoreId);
		}
		return cakeStoreInfo;
	}

	/**
	 * @param cakeStoreId
	 * @return cakeStoreImg
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<String> getCakeStoreImg(int cakeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<String> cakeStoreImg;
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreImg = cakeStoreDao.getCakeStoreImg(conn, cakeStoreId);
		}
		return cakeStoreImg;
	}

	/**
	 * @param cakeStoreArea
	 * @return stationPosition
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<Double> getCakeStationPosition(String cakeStoreArea) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<Double> stationPosition = new ArrayList<Double>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			stationPosition = cakeStoreDao.getCakeStationPosition(conn, cakeStoreArea);
		}
		return stationPosition;
	}

}
