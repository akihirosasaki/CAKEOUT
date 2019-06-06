package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import Vo.CafeStoreMenuVo;
import Vo.CafeStoreVo;

public class CafeStoreDao extends BaseDao {
	public CafeStoreVo getCafeStoreInfo(Connection conn, int cafeStoreId) throws SQLException {
		CafeStoreVo cafeStoreInfo = null;

		String sql = "select t1.cafe_store_id,t1.cafe_store_name,t1.cafe_store_open_time,t1.cafe_store_close_time,"
				+ "t1.cafe_store_phone_num,t1.cafe_store_address,t1.cafe_store_close_days,t1.cafe_store_station,t1.cafe_store_lat,"
				+ "t1.cafe_store_lon, t2.cafe_store_img_url, t2.cafe_store_img_primary from cafe_store as t1 "
				+ "left join cafe_store_img as t2 on t1.cafe_store_id = t2.cafe_store_id  where is_deleted = 0 and "
				+ "cafe_store_img_primary = 1 and t1.cafe_store_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cafeStoreId);
			try(ResultSet rs = pstmt.executeQuery();) {
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

					cafeStoreInfo = new CafeStoreVo(cafeStoreId, cafeStoreName, cafeStoreOpenTime, cafeStoreCloseTime, cafeStorePhoneNum, cafeStoreAddress, cafeStoreStation, cafeStoreCloseDays, cafeStoreLat, cafeStoreLon, cafeStorePrimaryImg);
				}
			}
		}
		return cafeStoreInfo;
	}

	public ArrayList<String> getCafeStoreImg(Connection conn, int cafeStoreId) throws SQLException {
		ArrayList<String> cafeStoreImgs = new ArrayList<String>();

		String sql = "select * from cafe_store_img where cafe_store_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cafeStoreId);
			try(ResultSet rs = pstmt.executeQuery();){
				while (rs.next()) {
					String cafeStoreImgUrl = rs.getString("cafe_store_img_url");

					cafeStoreImgs.add(cafeStoreImgUrl);
				}
			}
		}
		return cafeStoreImgs;
	}

	public ArrayList<CafeStoreMenuVo> getCafeStoreMenu(Connection conn, int cafeStoreId) throws SQLException {
		ArrayList<CafeStoreMenuVo> cafeStoreMenuList = new ArrayList<CafeStoreMenuVo>();

		String sql = "select cafe_menu_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price from cafe_menu "
				+ "where cafe_store_id = ? and is_deleted=0";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cafeStoreId);
			try(ResultSet rs = pstmt.executeQuery();){
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

	public ArrayList<CafeStoreVo> selectCafeStoreByArea(Connection conn, String cafeStoreArea, String[] statusList)
			throws SQLException {
		ArrayList<CafeStoreVo> cafeStoreList = new ArrayList<CafeStoreVo>();

		String sql = "select t1.cafe_store_id,t1.cafe_store_name,t1.cafe_store_open_time,t1.cafe_store_close_time,"
				+ "t1.cafe_store_phone_num,t1.cafe_store_address,t1.cafe_store_close_days,t1.cafe_store_station,t1.cafe_store_lat,"
				+ "t1.cafe_store_lon, t2.cafe_store_img_url, t2.cafe_store_img_primary from cafe_store as t1 "
				+ "left join cafe_store_img as t2 on t1.cafe_store_id = t2.cafe_store_id  where is_deleted = 0 and "
				+ "cafe_store_img_primary = 1 and cafe_store_station = ?";

		if (statusList != null) {
			if (Arrays.asList(statusList).contains("open")) {
				String[] week_name = { "日曜", "月曜", "火曜", "水曜", "木曜", "金曜", "土曜" };
				Calendar calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
				String open = " and t1.cafe_store_open_time < " + hour + " and t1.cafe_store_close_time > " + hour
						+ " and t1.cafe_store_close_days not like '%" + week_name[week] + "%'";
				sql = sql + open;
			}
		}

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, cafeStoreArea);
			try(ResultSet rs = pstmt.executeQuery();){
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

	public ArrayList<Double> getCafeStationPosition(Connection conn, String cafeStoreArea) throws SQLException {
		ArrayList<Double> stationPosition = new ArrayList<Double>();

		String sql = "select * from station_location where station_name = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, cafeStoreArea);
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
