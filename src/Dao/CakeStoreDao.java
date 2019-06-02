package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Vo.CakeStoreMenuVo;
import Vo.CakeStoreVo;

public class CakeStoreDao extends BaseDao{
	public static ArrayList<CakeStoreVo> selectPopularCakeStore(Connection conn, String searchArea) throws SQLException {
		ArrayList<CakeStoreVo> popularCakeStores = new ArrayList<CakeStoreVo>();

		String sql = "select * from (select t1.cake_store_id,t1.cake_store_name,t1.cake_store_open_time,t1.cake_store_close_time,t1.cake_store_phone_num,t1.cake_store_address,t1.cake_store_close_days,t1.cake_store_station,t1.cake_store_lat,t1.cake_store_lon,t2.order_times, t1.is_deleted, t3.cake_store_img_url, t3.cake_store_img_primary from cake_store as t1 left join (select cake_store_id, count(cake_store_id) as order_times from order_info group by cake_store_id) as t2 on t1.cake_store_id = t2.cake_store_id left join cake_store_img as t3 on t1.cake_store_id = t3.cake_store_id) as t4 where cake_store_station = ? and is_deleted = 0 and cake_store_img_primary = 1 order by order_times desc limit 3";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, searchArea);
			ResultSet rs = pstmt.executeQuery();
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
		return popularCakeStores;
	}

	public static CakeStoreVo getCakeStoreInfo(Connection conn, int cakeStoreId) throws SQLException {
		CakeStoreVo cakeStoreInfo = null;

		String sql = "select * from cake_store where cake_store_id = ? and is_deleted = 0";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cakeStoreId);
			ResultSet rs = pstmt.executeQuery();
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

				cakeStoreInfo = new CakeStoreVo(cakeStoreId, cakeStoreName, cakeStoreOpenTime, cakeStoreCloseTime, cakeStorePhoneNum, cakeStoreAddress, cakeStoreStation, cakeStoreCloseDays, cakeStoreLat, cakeStoreLon, "");
			}
		}
		return cakeStoreInfo;
	}

	public static ArrayList<String> getCakeStoreImg(Connection conn, int cakeStoreId) throws SQLException {
		ArrayList<String> cakeStoreImgs = new ArrayList<String>();

		String sql = "select * from cake_store_img where cake_store_id = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cakeStoreId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String cakeStoreImgUrl = rs.getString("cake_store_img_url");

				cakeStoreImgs.add(cakeStoreImgUrl);
			}
		}
		return cakeStoreImgs;
	}

	public static ArrayList<CakeStoreMenuVo> getCakeStoreMenu(Connection conn, int cakeStoreId) throws SQLException {
		ArrayList<CakeStoreMenuVo> cakeStoreMenuList = new ArrayList<CakeStoreMenuVo>();

		String sql = "select t1.cake_menu_id, t1.cake_menu_name, t1.cake_menu_img_url, t2.stock_num from cake_menu as t1 left join cake_stock as t2 on t1.cake_menu_id = t2.cake_menu_id where cake_store_id = ? and is_deleted = 0";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, cakeStoreId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int cakeMenuId = rs.getInt("cake_menu_id");
				String cakeMenuName = rs.getString("cake_menu_name");
				String cakeMenuImgUrl = rs.getString("cake_menu_img_url");
				int cakeStock = rs.getInt("stock_num");

				cakeStoreMenuList.add(new CakeStoreMenuVo(cakeMenuId, cakeMenuName, cakeMenuImgUrl, cakeStock));
			}
		}
		return cakeStoreMenuList;
	}

	public static ArrayList<CakeStoreVo> cakeStoreNameSearch(Connection conn, String cakeStoreNameInput) throws SQLException {
		ArrayList<CakeStoreVo> cakeStoreList = new ArrayList<CakeStoreVo>();

		String sql = "select t1.cake_store_id,t1.cake_store_name,t1.cake_store_open_time,t1.cake_store_close_time,t1.cake_store_phone_num,t1.cake_store_address,t1.cake_store_close_days,t1.cake_store_station,t1.cake_store_lat,t1.cake_store_lon, t2.cake_store_img_url, t2.cake_store_img_primary from cake_store as t1 left join cake_store_img as t2 on t1.cake_store_id = t2.cake_store_id where is_deleted = 0 and cake_store_img_primary = 1 and cake_store_name like ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, "%" + cakeStoreNameInput + "%");
			ResultSet rs = pstmt.executeQuery();
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
				System.out.println(cakeStoreList);
			}
		}
		return cakeStoreList;
	}

	public static ArrayList<CakeStoreVo> selectCakeStoreByArea(Connection conn, String cakeStoreArea) throws SQLException {
		ArrayList<CakeStoreVo> cakeStoreList = new ArrayList<CakeStoreVo>();

		String sql = "select t1.cake_store_id,t1.cake_store_name,t1.cake_store_open_time,t1.cake_store_close_time,t1.cake_store_phone_num,t1.cake_store_address,t1.cake_store_close_days,t1.cake_store_station,t1.cake_store_lat,t1.cake_store_lon, t2.cake_store_img_url, t2.cake_store_img_primary from cake_store as t1 left join cake_store_img as t2 on t1.cake_store_id = t2.cake_store_id  where is_deleted = 0 and cake_store_img_primary = 1 and cake_store_station = ?";

		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, cakeStoreArea);
			ResultSet rs = pstmt.executeQuery();
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
		return cakeStoreList;
	}
}
