package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import Dao.CafeStoreDao;
import Vo.CafeStoreMenuVo;
import Vo.CafeStoreVo;

public class CafeStoreSearchModel {
	public ArrayList<CafeStoreVo> selectCafeStoreByArea(String cafeStoreArea, String[] statusList) throws SQLException, NamingException {
		//		問い合わせ開始
		System.out.println("問い合わせ開始");
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		ArrayList<CafeStoreVo> cafeStoreList = new ArrayList<CafeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			cafeStoreList = cafeStoreDao.selectCafeStoreByArea(conn, cafeStoreArea, statusList);
		}
		return cafeStoreList;
	}

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
