package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import vo.CakeStoreMenuVo;
import vo.CakeStoreVo;

/**
 * @author Akihiro Sasaki
 * CakeStoreテーブルを扱うDaoです
 */
public class CakeStoreDao extends BaseDao{
	/**
	 * @param conn
	 * @param searchArea
	 * @return popularCakeStores
	 * @throws SQLException
	 */
	public ArrayList<CakeStoreVo> getPopularCakeStore(Connection conn, String searchArea) throws SQLException {
		ArrayList<CakeStoreVo> popularCakeStores = new ArrayList<CakeStoreVo>();

		String sql = "SELECT t4.cake_store_id, t4.cake_store_name, t4.cake_store_open_time, t4.cake_store_close_time,"
				+ " t4.cake_store_phone_num, t4.cake_store_address, t4.cake_store_close_days, t4.cake_store_station,"
				+ " t4.cake_store_lat, t4.cake_store_lon, t4.cake_store_img_url FROM (SELECT t1.cake_store_id,"
				+ "t1.cake_store_name,t1.cake_store_open_time," +
				"t1.cake_store_close_time,t1.cake_store_phone_num,t1.cake_store_address,t1.cake_store_close_days," +
				"t1.cake_store_station,t1.cake_store_lat,t1.cake_store_lon,t2.order_times, t1.is_deleted, " +
				"t3.cake_store_img_url, t3.cake_store_img_primary FROM cake_store AS t1 LEFT JOIN " +
				"(SELECT cake_store_id, count(cake_store_id) AS order_times FROM order_info group by cake_store_id) " +
				"AS t2 on t1.cake_store_id = t2.cake_store_id LEFT JOIN cake_store_img AS t3 on " +
				"t1.cake_store_id = t3.cake_store_id) AS t4 WHERE cake_store_station= ? AND is_deleted = 0 " +
				"AND cake_store_img_primary = 1 order by order_times desc limit 3;";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, searchArea);
			try(ResultSet rs = pstmt.executeQuery();){
				while (rs.next()) {
					int cakeStoreId = rs.getInt("cake_store_id");
					String cakeStoreName = rs.getString("cake_store_name");
					String cakeStoreOpenTime = rs.getString("cake_store_open_time");
					String cakeStoreCloseTime = rs.getString("cake_store_close_time");
					String cakeStorePhoneNum = rs.getString("cake_store_phone_num");
					String cakeStoreAddress = rs.getString("cake_store_address");
					String cakeStoreStation = rs.getString("cake_store_station");
					String cakeStoreCloseDays = rs.getString("cake_store_close_days");
					double cakeStoreLat = rs.getDouble("cake_store_lat");
					double cakeStoreLon = rs.getDouble("cake_store_lon");
					String cakeStorePrimaryImg = rs.getString("cake_store_img_url");

					popularCakeStores.add(new CakeStoreVo(cakeStoreId, cakeStoreName, cakeStoreOpenTime, cakeStoreCloseTime, cakeStorePhoneNum, cakeStoreAddress, cakeStoreStation, cakeStoreCloseDays, cakeStoreLat, cakeStoreLon, cakeStorePrimaryImg));
				}
			}
		}
		return popularCakeStores;
	}

	/**
	 * @param conn
	 * @param cakeStoreId
	 * @return cakeStoreInfo
	 * @throws SQLException
	 */
	public CakeStoreVo getCakeStoreInfo(Connection conn, int cakeStoreId) throws SQLException {
		CakeStoreVo cakeStoreInfo = null;

		String sql = "SELECT t1.cake_store_id,t1.cake_store_name,t1.cake_store_open_time,t1.cake_store_close_time,"
				+ "t1.cake_store_phone_num,t1.cake_store_address,t1.cake_store_close_days,t1.cake_store_station,"
				+ "t1.cake_store_lat,t1.cake_store_lon, t2.cake_store_img_url, t2.cake_store_img_primary FROM cake_store AS t1 "
				+ "LEFT JOIN cake_store_img AS t2 on t1.cake_store_id = t2.cake_store_id WHERE is_deleted = 0 AND "
				+ "cake_store_img_primary = 1 AND t1.cake_store_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cakeStoreId);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					String cakeStoreName = rs.getString("cake_store_name");
					String cakeStoreOpenTime = rs.getString("cake_store_open_time");
					String cakeStoreCloseTime = rs.getString("cake_store_close_time");
					String cakeStorePhoneNum = rs.getString("cake_store_phone_num");
					String cakeStoreAddress = rs.getString("cake_store_address");
					String cakeStoreStation = rs.getString("cake_store_station");
					String cakeStoreCloseDays = rs.getString("cake_store_close_days");
					double cakeStoreLat = rs.getDouble("cake_store_lat");
					double cakeStoreLon = rs.getDouble("cake_store_lon");
					String cakeStorePrimaryImg = rs.getString("cake_store_img_url");

					cakeStoreInfo = new CakeStoreVo(cakeStoreId, cakeStoreName, cakeStoreOpenTime, cakeStoreCloseTime, cakeStorePhoneNum, cakeStoreAddress, cakeStoreStation, cakeStoreCloseDays, cakeStoreLat, cakeStoreLon, cakeStorePrimaryImg);
				}
			}
		}
		return cakeStoreInfo;
	}

	/**
	 * @param conn
	 * @param cakeStoreId
	 * @return cakeStoreImgs
	 * @throws SQLException
	 */
	public ArrayList<String> getCakeStoreImg(Connection conn, int cakeStoreId) throws SQLException {
		ArrayList<String> cakeStoreImgs = new ArrayList<String>();

		String sql = "SELECT cake_store_img_url FROM cake_store_img WHERE cake_store_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cakeStoreId);
			try(ResultSet rs = pstmt.executeQuery();){
				while (rs.next()) {
					String cakeStoreImgUrl = rs.getString("cake_store_img_url");

					cakeStoreImgs.add(cakeStoreImgUrl);
				}
			}
		}
		return cakeStoreImgs;
	}

	/**
	 * @param conn
	 * @param cakeStoreId
	 * @return cakeStoreMenuList
	 * @throws SQLException
	 */
	public ArrayList<CakeStoreMenuVo> getCakeStoreMenu(Connection conn, int cakeStoreId) throws SQLException {
		ArrayList<CakeStoreMenuVo> cakeStoreMenuList = new ArrayList<CakeStoreMenuVo>();

		String sql = "SELECT t1.cake_menu_id, t1.cake_menu_name, t1.cake_menu_img_url, t2.stock_num FROM cake_menu AS t1 "
				+ "LEFT JOIN cake_stock AS t2 on t1.cake_menu_id = t2.cake_menu_id WHERE cake_store_id = ? AND is_deleted = 0";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cakeStoreId);
			try(ResultSet rs = pstmt.executeQuery();){
				while (rs.next()) {
					int cakeMenuId = rs.getInt("cake_menu_id");
					String cakeMenuName = rs.getString("cake_menu_name");
					String cakeMenuImgUrl = rs.getString("cake_menu_img_url");
					int cakeStock = rs.getInt("stock_num");

					cakeStoreMenuList.add(new CakeStoreMenuVo(cakeMenuId, cakeMenuName, cakeMenuImgUrl, cakeStock));
				}
			}
		}
		return cakeStoreMenuList;
	}

	/**
	 * @param conn
	 * @param cakeStoreNameInput
	 * @return cakeStoreList
	 * @throws SQLException
	 */
	public ArrayList<CakeStoreVo> cakeStoreNameSearch(Connection conn, String cakeStoreNameInput) throws SQLException {
		ArrayList<CakeStoreVo> cakeStoreList = new ArrayList<CakeStoreVo>();

		String sql = "SELECT t1.cake_store_id,t1.cake_store_name,t1.cake_store_open_time,t1.cake_store_close_time,"
				+ "t1.cake_store_phone_num,t1.cake_store_address,t1.cake_store_close_days,t1.cake_store_station,"
				+ "t1.cake_store_lat,t1.cake_store_lon, t2.cake_store_img_url, t2.cake_store_img_primary FROM cake_store as t1 "
				+ "LEFT JOIN cake_store_img as t2 on t1.cake_store_id = t2.cake_store_id WHERE is_deleted = 0 AND "
				+ "cake_store_img_primary = 1 AND cake_store_name like ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, "%" + cakeStoreNameInput + "%");
			try(ResultSet rs = pstmt.executeQuery();){
				while (rs.next()) {
					int cakeStoreId = rs.getInt("cake_store_id");
					String cakeStoreName = rs.getString("cake_store_name");
					String cakeStoreOpenTime = rs.getString("cake_store_open_time");
					String cakeStoreCloseTime = rs.getString("cake_store_close_time");
					String cakeStorePhoneNum = rs.getString("cake_store_phone_num");
					String cakeStoreAddress = rs.getString("cake_store_address");
					String cakeStoreStation = rs.getString("cake_store_station");
					String cakeStoreCloseDays = rs.getString("cake_store_close_days");
					double cakeStoreLat = rs.getDouble("cake_store_lat");
					double cakeStoreLon = rs.getDouble("cake_store_lon");
					String cakeStorePrimaryImg = rs.getString("cake_store_img_url");

					cakeStoreList.add(new CakeStoreVo(cakeStoreId, cakeStoreName, cakeStoreOpenTime, cakeStoreCloseTime, cakeStorePhoneNum, cakeStoreAddress, cakeStoreStation, cakeStoreCloseDays, cakeStoreLat, cakeStoreLon, cakeStorePrimaryImg));
				}
			}
		}
		return cakeStoreList;
	}

	/**
	 * @param conn
	 * @param cakeStoreArea
	 * @param statusList
	 * @return cakeStoreList
	 * @throws SQLException
	 */
	public ArrayList<CakeStoreVo> getCakeStoreByArea(Connection conn, String cakeStoreArea, String[] statusList) throws SQLException {
		ArrayList<CakeStoreVo> cakeStoreList = new ArrayList<CakeStoreVo>();

		String sql = "SELECT t1.cake_store_id,t1.cake_store_name,t1.cake_store_open_time,t1.cake_store_close_time,"
				+ "t1.cake_store_phone_num,t1.cake_store_address,t1.cake_store_close_days,t1.cake_store_station,t1.cake_store_lat,"
				+ "t1.cake_store_lon, t2.cake_store_img_url, t2.cake_store_img_primary FROM cake_store AS t1 "
				+ "LEFT JOIN cake_store_img AS t2 on t1.cake_store_id = t2.cake_store_id  WHERE is_deleted = 0 AND "
				+ "cake_store_img_primary = 1 AND cake_store_station = ?";

		if(statusList!=null) {
			if(Arrays.asList(statusList).contains("open")) {
				String[] week_name = {"日曜", "月曜", "火曜", "水曜", "木曜", "金曜", "土曜"};
				Calendar calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
				String open = " AND t1.cake_store_open_time < " + hour + " AND t1.cake_store_close_time > " + hour + " AND t1.cake_store_close_days not like '%" + week_name[week] + "%'";
				sql = sql + open;
			}
		}


		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, cakeStoreArea);
			try(ResultSet rs = pstmt.executeQuery();){
				while (rs.next()) {
					int cakeStoreId = rs.getInt("cake_store_id");
					String cakeStoreName = rs.getString("cake_store_name");
					String cakeStoreOpenTime = rs.getString("cake_store_open_time");
					String cakeStoreCloseTime = rs.getString("cake_store_close_time");
					String cakeStorePhoneNum = rs.getString("cake_store_phone_num");
					String cakeStoreAddress = rs.getString("cake_store_address");
					String cakeStoreStation = rs.getString("cake_store_station");
					String cakeStoreCloseDays = rs.getString("cake_store_close_days");
					double cakeStoreLat = rs.getDouble("cake_store_lat");
					double cakeStoreLon = rs.getDouble("cake_store_lon");
					String cakeStorePrimaryImg = rs.getString("cake_store_img_url");

					cakeStoreList.add(new CakeStoreVo(cakeStoreId, cakeStoreName, cakeStoreOpenTime, cakeStoreCloseTime, cakeStorePhoneNum, cakeStoreAddress, cakeStoreStation, cakeStoreCloseDays, cakeStoreLat, cakeStoreLon, cakeStorePrimaryImg));
				}
			}
		}
		return cakeStoreList;
	}


	/**
	 * @param conn
	 * @param cakeStoreArea
	 * @return stationPosition
	 * @throws SQLException
	 */
	public ArrayList<Double> getCakeStationPosition(Connection conn, String cakeStoreArea) throws SQLException {
		ArrayList<Double> stationPosition = new ArrayList<Double>();

		String sql = "SELECT station_lat, station_lon FROM station_location WHERE station_name = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, cakeStoreArea);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					double stationLat = rs.getDouble("station_lat");
					double stationLon = rs.getDouble("station_lon");

					stationPosition.add(stationLat);
					stationPosition.add(stationLon);
				}
			}
		}
		return stationPosition;
	}
}
