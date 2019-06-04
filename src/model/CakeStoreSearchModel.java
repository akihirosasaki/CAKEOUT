package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import Dao.CakeStoreDao;
import Vo.CakeStoreMenuVo;
import Vo.CakeStoreVo;

public class CakeStoreSearchModel {
	public ArrayList<CakeStoreVo> selectPopularCakeStore(String searchArea) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreVo> popularCakeStores = new ArrayList<CakeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			popularCakeStores = cakeStoreDao.selectPopularCakeStore(conn, searchArea);
		}
		return popularCakeStores;
	}

	public ArrayList<CakeStoreVo> cakeStoreNameSearch(String cakeStoreNameInput) throws SQLException, NamingException {
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

	public ArrayList<CakeStoreVo> selectCakeStoreByArea(String cakeStoreArea, String[] statusList) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreVo> cakeStoreList = new ArrayList<CakeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreList = cakeStoreDao.selectCakeStoreByArea(conn, cakeStoreArea, statusList);
		}
		return cakeStoreList;
	}

	public ArrayList<CakeStoreMenuVo> getCakeStoreMenu(int cakeStoreId) throws SQLException, NamingException {
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

	public CakeStoreVo getCakeStoreInfo(int cakeStoreId) throws SQLException, NamingException {
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

	public ArrayList<String> getCakeStoreImg(int cakeStoreId) throws SQLException, NamingException {
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
