package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import dao.CafeStoreDao;
import vo.CafeStoreMenuVo;
import vo.CafeStoreVo;

/**
 * @author Akihiro Sasaki
 * CafeStoreDaoを扱うモデル
 */
public class CafeStoreSearchModel {
	/**
	 * @param cafeStoreArea
	 * @param statusList
	 * @return cafeStoreList
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CafeStoreVo> getCafeStoreByArea(String cafeStoreArea, String[] statusList) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		ArrayList<CafeStoreVo> cafeStoreList = new ArrayList<CafeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			cafeStoreList = cafeStoreDao.getCafeStoreByArea(conn, cafeStoreArea, statusList);
		}
		return cafeStoreList;
	}

	/**
	 * @param cafeStoreId
	 * @return cafeStoreMenuList
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CafeStoreMenuVo> getCafeStoreMenu(int cafeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		ArrayList<CafeStoreMenuVo> cafeStoreMenuList = new ArrayList<CafeStoreMenuVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			cafeStoreMenuList = cafeStoreDao.getCafeStoreMenu(conn, cafeStoreId);
		}
		return cafeStoreMenuList;
	}

	/**
	 * @param cafeStoreId
	 * @return cafeStoreInfo
	 * @throws SQLException
	 * @throws NamingException
	 */
	public CafeStoreVo getCafeStoreInfo(int cafeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		CafeStoreVo cafeStoreInfo = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			cafeStoreInfo = cafeStoreDao.getCafeStoreInfo(conn, cafeStoreId);
		}
		return cafeStoreInfo;
	}

	/**
	 * @param cafeStoreId
	 * @return cafeStoreImg
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<String> getCafeStoreImg(int cafeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		ArrayList<String> cafeStoreImg;
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			cafeStoreImg = cafeStoreDao.getCafeStoreImg(conn, cafeStoreId);
		}
		return cafeStoreImg;
	}

	/**
	 * @param cafeStoreArea
	 * @return stationPosition
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<Double> getCafeStationPosition(String cafeStoreArea) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		ArrayList<Double> stationPosition = new ArrayList<Double>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			stationPosition = cafeStoreDao.getCafeStationPosition(conn, cafeStoreArea);
		}
		return stationPosition;
	}

}
