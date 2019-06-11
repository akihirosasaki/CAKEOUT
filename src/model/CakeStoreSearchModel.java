package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import dao.CakeStoreDao;
import vo.CakeStoreMenuVo;
import vo.CakeStoreVo;

/**
 * ケーキ屋Model
 * CakeStoreDaoを扱うモデル
 * @author Akihiro Sasaki
 */
public class CakeStoreSearchModel {
	/**
	 * @param searchArea ケーキ屋を検索するエリア
	 * @return popularCakeStores 人気ケーキ屋情報
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CakeStoreVo> getPopularCakeStore(String searchArea) throws SQLException, NamingException {
		//		問い合わせ開始
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreVo> popularCakeStores = new ArrayList<CakeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			popularCakeStores = cakeStoreDao.getPopularCakeStore(conn, searchArea);
		}
		return popularCakeStores;
	}

	/**
	 * @param cakeStoreNameInput ケーキ屋の名前検索でユーザーが入力した文字列
	 * @return cakeStoreList ケーキ屋情報をもつVoのリスト
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CakeStoreVo> searchCakeStoreName(String cakeStoreNameInput) throws SQLException, NamingException {
		//		問い合わせ開始
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreVo> cakeStoreList = new ArrayList<CakeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreList = cakeStoreDao.cakeStoreNameSearch(conn, cakeStoreNameInput);
		}
		return cakeStoreList;
	}

	/**
	 * @param cakeStoreArea ケーキ屋の検索エリア
	 * @param statusList ユーザーが指定した検索条件リスト
	 * @return cakeStoreList ケーキ屋の情報をもつVoのリスト
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CakeStoreVo> getCakeStoreByArea(String cakeStoreArea, String[] statusList) throws SQLException, NamingException {
		//		問い合わせ開始
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreVo> cakeStoreList = new ArrayList<CakeStoreVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreList = cakeStoreDao.getCakeStoreByArea(conn, cakeStoreArea, statusList);
		}
		return cakeStoreList;
	}

	/**
	 * @param cakeStoreId ケーキ屋ID
	 * @return cakeStoreMenuList ケーキ屋のメニュー情報をもつVoのリスト
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<CakeStoreMenuVo> getCakeStoreMenu(int cakeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<CakeStoreMenuVo> cakeStoreMenuList = new ArrayList<CakeStoreMenuVo>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreMenuList = cakeStoreDao.getCakeStoreMenu(conn, cakeStoreId);
		}
		return cakeStoreMenuList;
	}

	/**
	 * @param cakeStoreId ケーキ屋ID
	 * @return cakeStoreInfo ケーキ屋の情報をもつVo
	 * @throws SQLException
	 * @throws NamingException
	 */
	public CakeStoreVo getCakeStoreInfo(int cakeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		CakeStoreVo cakeStoreInfo = null;
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreInfo = cakeStoreDao.getCakeStoreInfo(conn, cakeStoreId);
		}
		return cakeStoreInfo;
	}

	/**
	 * @param cakeStoreId ケーキ屋ID
	 * @return cakeStoreImg ケーキ屋の画像
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<String> getCakeStoreImg(int cakeStoreId) throws SQLException, NamingException {
		//		問い合わせ開始
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<String> cakeStoreImg;
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			cakeStoreImg = cakeStoreDao.getCakeStoreImg(conn, cakeStoreId);
		}
		return cakeStoreImg;
	}

	/**
	 * @param cakeStoreArea ケーキ屋の検索エリア
	 * @return stationPosition 駅の緯度経度情報
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<Double> getCakeStationPosition(String cakeStoreArea) throws SQLException, NamingException {
		//		問い合わせ開始
		CakeStoreDao cakeStoreDao = new CakeStoreDao();
		ArrayList<Double> stationPosition = new ArrayList<Double>();
		//		コネクション管理はこのレベルで
		try (Connection conn = cakeStoreDao.connect()) {
			stationPosition = cakeStoreDao.getCakeStationPosition(conn, cakeStoreArea);
		}
		return stationPosition;
	}

}
