package Vo;

import java.io.Serializable;

public class CafeStoreVo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int cafeStoreId;
	private String cafeStoreName;
	private String cafeStoreOpenTime;
	private String cafeStoreCloseTime;
	private String cafeStorePhoneNum;
	private String cafeStoreAddress;
	private String cafeStoreStation;
	private String cafeStoreCloseDays;
	private double cafeStoreLat;
	private double cafeStoreLon;
	private String cafeStorePrimaryImg;

	public CafeStoreVo() {

	}

	public CafeStoreVo(int cafeStoreId, String cafeStoreName, String cafeStoreOpenTime, String cafeStoreCloseTime,
			String cafeStorePhoneNum, String cafeStoreAddress, String cafeStoreStation, String cafeStoreCloseDays,
			double cafeStoreLat, double cafeStoreLon, String cafeStorePrimaryImg) {
		super();
		this.cafeStoreId = cafeStoreId;
		this.cafeStoreName = cafeStoreName;
		this.cafeStoreOpenTime = cafeStoreOpenTime;
		this.cafeStoreCloseTime = cafeStoreCloseTime;
		this.cafeStorePhoneNum = cafeStorePhoneNum;
		this.cafeStoreAddress = cafeStoreAddress;
		this.cafeStoreStation = cafeStoreStation;
		this.cafeStoreCloseDays = cafeStoreCloseDays;
		this.cafeStoreLat = cafeStoreLat;
		this.cafeStoreLon = cafeStoreLon;
		this.cafeStorePrimaryImg = cafeStorePrimaryImg;
	}

	public int getcafeStoreId() {
		return cafeStoreId;
	}

	public void setcafeStoreId(int cafeStoreId) {
		this.cafeStoreId = cafeStoreId;
	}

	public String getcafeStoreName() {
		return cafeStoreName;
	}

	public void setcafeStoreName(String cafeStoreName) {
		this.cafeStoreName = cafeStoreName;
	}

	public String getcafeStoreOpenTime() {
		return cafeStoreOpenTime;
	}

	public void setcafeStoreOpenTime(String cafeStoreOpenTime) {
		this.cafeStoreOpenTime = cafeStoreOpenTime;
	}

	public String getcafeStoreCloseTime() {
		return cafeStoreCloseTime;
	}

	public void setcafeStoreCloseTime(String cafeStoreCloseTime) {
		this.cafeStoreCloseTime = cafeStoreCloseTime;
	}

	public String getcafeStorePhoneNum() {
		return cafeStorePhoneNum;
	}

	public void setcafeStorePhoneNum(String cafeStorePhoneNum) {
		this.cafeStorePhoneNum = cafeStorePhoneNum;
	}

	public String getcafeStoreAddress() {
		return cafeStoreAddress;
	}

	public void setcafeStoreAddress(String cafeStoreAddress) {
		this.cafeStoreAddress = cafeStoreAddress;
	}

	public String getcafeStoreStation() {
		return cafeStoreStation;
	}

	public void setcafeStoreStation(String cafeStoreStation) {
		this.cafeStoreStation = cafeStoreStation;
	}

	public String getcafeStoreCloseDays() {
		return cafeStoreCloseDays;
	}

	public void setcafeStoreCloseDays(String cafeStoreCloseDays) {
		this.cafeStoreCloseDays = cafeStoreCloseDays;
	}

	public double getcafeStoreLat() {
		return cafeStoreLat;
	}

	public void setcafeStoreLat(double cafeStoreLat) {
		this.cafeStoreLat = cafeStoreLat;
	}

	public double getcafeStoreLon() {
		return cafeStoreLon;
	}

	public void setcafeStoreLon(double cafeStoreLon) {
		this.cafeStoreLon = cafeStoreLon;
	}

	public String getcafeStorePrimaryImg() {
		return cafeStorePrimaryImg;
	}

	public void setcafeStorePrimaryImg(String cafeStorePrimaryImg) {
		this.cafeStorePrimaryImg = cafeStorePrimaryImg;
	}






}
