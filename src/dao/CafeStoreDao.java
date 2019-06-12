package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import vo.CafeStoreMenuVo;
import vo.CafeStoreVo;

/**
 * カフェDao
 * CafeStoreテーブルを扱うDaoです
 * @author Akihiro Sasaki
 */
public class CafeStoreDao extends BaseDao {
	/**
	 * @param conn コネクション
	 * @param cafeStoreId カフェID
	 * @return cafeStoreInfo カフェ情報をもつVo
	 * @throws SQLException
	 */
	public CafeStoreVo getCafeStoreInfo(Connection conn, int cafeStoreId) throws SQLException {
		CafeStoreVo cafeStoreInfo = null;

		final String sql = "SELECT t1.cafe_store_id,t1.cafe_store_name,t1.cafe_store_open_time,t1.cafe_store_close_time,"
				+ "t1.cafe_store_phone_num,t1.cafe_store_address,t1.cafe_store_close_days,t1.cafe_store_station,t1.cafe_store_lat,"
				+ "t1.cafe_store_lon, t2.cafe_store_img_url, t2.cafe_store_img_primary FROM cafe_store AS t1 "
				+ "LEFT JOIN cafe_store_img AS t2 ON t1.cafe_store_id = t2.cafe_store_id WHERE is_deleted = 0 AND "
				+ "cafe_store_img_primary = 1 AND t1.cafe_store_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cafeStoreId);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					String cafeStoreName = rs.getString("cafe_store_name");
					String cafeStoreOpenTime = rs.getString("cafe_store_open_time");
					String cafeStoreCloseTime = rs.getString("cafe_store_close_time");
					String cafeStorePhoneNum = rs.getString("cafe_store_phone_num");
					String cafeStoreAddress = rs.getString("cafe_store_address");
					String cafeStoreStation = rs.getString("cafe_store_station");
					String cafeStoreCloseDays = rs.getString("cafe_store_close_days");
					double cafeStoreLat = rs.getDouble("cafe_store_lat");
					double cafeStoreLon = rs.getDouble("cafe_store_lon");
					String cafeStorePrimaryImg = rs.getString("cafe_store_img_url");

					cafeStoreInfo = new CafeStoreVo(cafeStoreId, cafeStoreName, cafeStoreOpenTime, cafeStoreCloseTime,
							cafeStorePhoneNum, cafeStoreAddress, cafeStoreStation, cafeStoreCloseDays, cafeStoreLat,
							cafeStoreLon, cafeStorePrimaryImg);
				}
			}
		}
		return cafeStoreInfo;
	}

	/**
	 * @param conn コネクション
	 * @param cafeStoreId カフェID
	 * @return cafeStoreImgs カフェ画像
	 * @throws SQLException
	 */
	public ArrayList<String> getCafeStoreImg(Connection conn, int cafeStoreId) throws SQLException {
		ArrayList<String> cafeStoreImgs = new ArrayList<String>();

		final String sql = "SELECT cafe_store_img_url FROM cafe_store_img WHERE cafe_store_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cafeStoreId);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					String cafeStoreImgUrl = rs.getString("cafe_store_img_url");

					cafeStoreImgs.add(cafeStoreImgUrl);
				}
			}
		}
		return cafeStoreImgs;
	}

	/**
	 * @param conn コネクション
	 * @param cafeStoreId カフェID
	 * @return cafeStoreMenuList カフェのメニュー情報をもつVo
	 * @throws SQLException
	 */
	public ArrayList<CafeStoreMenuVo> getCafeStoreMenu(Connection conn, int cafeStoreId) throws SQLException {
		ArrayList<CafeStoreMenuVo> cafeStoreMenuList = new ArrayList<CafeStoreMenuVo>();

		final String sql = "SELECT cafe_menu_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price FROM cafe_menu "
				+ "WHERE cafe_store_id = ? AND is_deleted=0";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cafeStoreId);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					int cafeMenuId = rs.getInt("cafe_menu_id");
					String cafeMenuName = rs.getString("cafe_menu_name");
					String cafeMenuImgUrl = rs.getString("cafe_menu_img_url");
					int cafeMenuPrice = rs.getInt("cafe_menu_price");

					cafeStoreMenuList.add(new CafeStoreMenuVo(cafeMenuId, cafeMenuName, cafeMenuImgUrl, cafeMenuPrice));

				}
			}
		}
		return cafeStoreMenuList;
	}

	/**
	 * @param conn
	 * @param cafeStoreArea
	 * @param statusList
	 * @return cafeStoreList
	 * @throws SQLException
	 */
	public ArrayList<CafeStoreVo> getCafeStoreByArea(Connection conn, String cafeStoreArea, String[] statusList)
			throws SQLException {
		ArrayList<CafeStoreVo> cafeStoreList = new ArrayList<CafeStoreVo>();

		String sql = "SELECT t1.cafe_store_id,t1.cafe_store_name,t1.cafe_store_open_time,t1.cafe_store_close_time,"
				+ "t1.cafe_store_phone_num,t1.cafe_store_address,t1.cafe_store_close_days,t1.cafe_store_station,t1.cafe_store_lat,"
				+ "t1.cafe_store_lon, t2.cafe_store_img_url, t2.cafe_store_img_primary FROM cafe_store AS t1 "
				+ "LEFT JOIN cafe_store_img AS t2 ON t1.cafe_store_id = t2.cafe_store_id  WHERE is_deleted = 0 AND "
				+ "cafe_store_img_primary = 1 AND cafe_store_station = ?";

		if (statusList != null) {
			if (Arrays.asList(statusList).contains("open")) {
				String[] week_name = { "日曜", "月曜", "火曜", "水曜", "木曜", "金曜", "土曜" };
				Calendar calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
				String open = " AND t1.cafe_store_open_time < " + hour + " AND t1.cafe_store_close_time > " + hour
						+ " AND t1.cafe_store_close_days not like '%" + week_name[week] + "%'";
				sql = sql + open;
			}
			if (Arrays.asList(statusList).contains("more")) {
				String more = " AND t1.cafe_store_seat_num > 50";
				sql = sql + more;
			}
			if (Arrays.asList(statusList).contains("smoking")) {
				String smoking = " AND t1.cafe_store_smoking_seat = 1";
				sql = sql + smoking;
			}
		}

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, cafeStoreArea);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					int cafeStoreId = rs.getInt("cafe_store_id");
					String cafeStoreName = rs.getString("cafe_store_name");
					String cafeStoreOpenTime = rs.getString("cafe_store_open_time");
					String cafeStoreCloseTime = rs.getString("cafe_store_close_time");
					String cafeStorePhoneNum = rs.getString("cafe_store_phone_num");
					String cafeStoreAddress = rs.getString("cafe_store_address");
					String cafeStoreStation = rs.getString("cafe_store_station");
					String cafeStoreCloseDays = rs.getString("cafe_store_close_days");
					double cafeStoreLat = rs.getDouble("cafe_store_lat");
					double cafeStoreLon = rs.getDouble("cafe_store_lon");
					String cafeStorePrimaryImg = rs.getString("cafe_store_img_url");

					cafeStoreList.add(new CafeStoreVo(cafeStoreId, cafeStoreName, cafeStoreOpenTime, cafeStoreCloseTime,
							cafeStorePhoneNum, cafeStoreAddress, cafeStoreStation, cafeStoreCloseDays, cafeStoreLat,
							cafeStoreLon, cafeStorePrimaryImg));
				}
			}
		}
		return cafeStoreList;
	}

	/**
	 * @param conn コネクション
	 * @param cafeStoreArea カフェの最寄駅
	 * @return stationPosition カフェの最寄駅の緯度経度情報
	 * @throws SQLException
	 */
	public ArrayList<Double> getCafeStationPosition(Connection conn, String cafeStoreArea) throws SQLException {
		ArrayList<Double> stationPosition = new ArrayList<Double>();

		final String sql = "SELECT station_lat, station_lon FROM station_location WHERE station_name = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, cafeStoreArea);
			try (ResultSet rs = pstmt.executeQuery();) {
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
