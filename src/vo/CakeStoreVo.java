package vo;

import java.io.Serializable;

public class CakeStoreVo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int cakeStoreId;
	private String cakeStoreName;
	private String cakeStoreOpenTime;
	private String cakeStoreCloseTime;
	private String cakeStorePhoneNum;
	private String cakeStoreAddress;
	private String cakeStoreStation;
	private String cakeStoreCloseDays;
	private double cakeStoreLat;
	private double cakeStoreLon;
	private String cakeStorePrimaryImg;

	public CakeStoreVo() {

	}

	public CakeStoreVo(int cakeStoreId, String cakeStoreName, String cakeStoreOpenTime, String cakeStoreCloseTime,
			String cakeStorePhoneNum, String cakeStoreAddress, String cakeStoreStation, String cakeStoreCloseDays,
			double cakeStoreLat, double cakeStoreLon, String cakeStorePrimaryImg) {
		super();
		this.cakeStoreId = cakeStoreId;
		this.cakeStoreName = cakeStoreName;
		this.cakeStoreOpenTime = cakeStoreOpenTime;
		this.cakeStoreCloseTime = cakeStoreCloseTime;
		this.cakeStorePhoneNum = cakeStorePhoneNum;
		this.cakeStoreAddress = cakeStoreAddress;
		this.cakeStoreStation = cakeStoreStation;
		this.cakeStoreCloseDays = cakeStoreCloseDays;
		this.cakeStoreLat = cakeStoreLat;
		this.cakeStoreLon = cakeStoreLon;
		this.cakeStorePrimaryImg = cakeStorePrimaryImg;
	}

	public int getCakeStoreId() {
		return cakeStoreId;
	}

	public void setCakeStoreId(int cakeStoreId) {
		this.cakeStoreId = cakeStoreId;
	}

	public String getCakeStoreName() {
		return cakeStoreName;
	}

	public void setCakeStoreName(String cakeStoreName) {
		this.cakeStoreName = cakeStoreName;
	}

	public String getCakeStoreOpenTime() {
		return cakeStoreOpenTime;
	}

	public void setCakeStoreOpenTime(String cakeStoreOpenTime) {
		this.cakeStoreOpenTime = cakeStoreOpenTime;
	}

	public String getCakeStoreCloseTime() {
		return cakeStoreCloseTime;
	}

	public void setCakeStoreCloseTime(String cakeStoreCloseTime) {
		this.cakeStoreCloseTime = cakeStoreCloseTime;
	}

	public String getCakeStorePhoneNum() {
		return cakeStorePhoneNum;
	}

	public void setCakeStorePhoneNum(String cakeStorePhoneNum) {
		this.cakeStorePhoneNum = cakeStorePhoneNum;
	}

	public String getCakeStoreAddress() {
		return cakeStoreAddress;
	}

	public void setCakeStoreAddress(String cakeStoreAddress) {
		this.cakeStoreAddress = cakeStoreAddress;
	}

	public String getCakeStoreStation() {
		return cakeStoreStation;
	}

	public void setCakeStoreStation(String cakeStoreStation) {
		this.cakeStoreStation = cakeStoreStation;
	}

	public String getCakeStoreCloseDays() {
		return cakeStoreCloseDays;
	}

	public void setCakeStoreCloseDays(String cakeStoreCloseDays) {
		this.cakeStoreCloseDays = cakeStoreCloseDays;
	}

	public double getCakeStoreLat() {
		return cakeStoreLat;
	}

	public void setCakeStoreLat(double cakeStoreLat) {
		this.cakeStoreLat = cakeStoreLat;
	}

	public double getCakeStoreLon() {
		return cakeStoreLon;
	}

	public void setCakeStoreLon(double cakeStoreLon) {
		this.cakeStoreLon = cakeStoreLon;
	}

	public String getCakeStorePrimaryImg() {
		return cakeStorePrimaryImg;
	}

	public void setCakeStorePrimaryImg(String cakeStorePrimaryImg) {
		this.cakeStorePrimaryImg = cakeStorePrimaryImg;
	}






}
