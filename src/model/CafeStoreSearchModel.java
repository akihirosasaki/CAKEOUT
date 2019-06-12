package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import dao.CafeStoreDao;
import vo.CafeStoreMenuVo;
import vo.CafeStoreVo;

/**
 * カフェModel
 * CafeStoreDaoを扱うモデル
 * @author Akihiro Sasaki
 */
public class CafeStoreSearchModel {
	/**
	 * @param cafeStoreArea カフェを検索するエリア
	 * @param statusList ユーザーが指定した条件リスト
	 * @return cafeStoreList カフェの情報をもつVo
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CafeStoreVo> getCafeStoreByArea(String cafeStoreArea, String[] statusList)
			throws SQLException, NamingException {
		//		問い合わせ開始
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		ArrayList<CafeStoreVo> cafeStoreList = new ArrayList<CafeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			cafeStoreList = cafeStoreDao.getCafeStoreByArea(conn, cafeStoreArea, statusList);
		}
		return cafeStoreList;
	}

	/**
	 * @param cafeStoreId カフェID
	 * @return cafeStoreMenuList カフェのメニュー情報をもつVo
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CafeStoreMenuVo> getCafeStoreMenu(int cafeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		ArrayList<CafeStoreMenuVo> cafeStoreMenuList = new ArrayList<CafeStoreMenuVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			cafeStoreMenuList = cafeStoreDao.getCafeStoreMenu(conn, cafeStoreId);
		}
		return cafeStoreMenuList;
	}

	/**
	 * @param cafeStoreId カフェID
	 * @return cafeStoreInfo カフェ情報を持つVo
	 * @throws SQLException
	 * @throws NamingException
	 */
	public CafeStoreVo getCafeStoreInfo(int cafeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		CafeStoreVo cafeStoreInfo = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			cafeStoreInfo = cafeStoreDao.getCafeStoreInfo(conn, cafeStoreId);
		}
		return cafeStoreInfo;
	}

	/**
	 * @param cafeStoreId カフェID
	 * @return cafeStoreImg カフェ画像
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<String> getCafeStoreImg(int cafeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		ArrayList<String> cafeStoreImg;
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			cafeStoreImg = cafeStoreDao.getCafeStoreImg(conn, cafeStoreId);
		}
		return cafeStoreImg;
	}

	/**
	 * @param cafeStoreArea カフェの検索エリア
	 * @return stationPosition 最寄駅の緯度経度情報
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<Double> getCafeStationPosition(String cafeStoreArea) throws SQLException, NamingException {
		//		問い合わせ開始
		CafeStoreDao cafeStoreDao = new CafeStoreDao();
		ArrayList<Double> stationPosition = new ArrayList<Double>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cafeStoreDao.connect()) {
			stationPosition = cafeStoreDao.getCafeStationPosition(conn, cafeStoreArea);
		}
		return stationPosition;
	}

}
