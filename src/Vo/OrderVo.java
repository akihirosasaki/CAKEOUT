package Vo;

import java.io.Serializable;
import java.sql.Date;

public class OrderVo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int orderId;
	private int userId;
	private int cakeStoreId;
	private String cakeStoreName;
	private int cafeStoreId;
	private String cafeStoreName;
	private int orderNum;
	private Date createdAt;
	private Date updatedAt;
	private String cakeStorePrimaryImg;
	private String cafeStorePrimaryImg;

	public OrderVo() {

	}

	public OrderVo(int orderId, int userId, int cakeStoreId, String cakeStoreName, int cafeStoreId,
			String cafeStoreName, int orderNum, Date createdAt,
			Date updatedAt, String cakeStorePrimaryImg, String cafeStorePrimaryImg) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.cakeStoreId = cakeStoreId;
		this.cakeStoreName = cakeStoreName;
		this.cafeStoreId = cafeStoreId;
		this.cafeStoreName = cafeStoreName;
		this.orderNum = orderNum;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.cakeStorePrimaryImg = cakeStorePrimaryImg;
		this.cafeStorePrimaryImg = cafeStorePrimaryImg;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCakeStoreId() {
		return cakeStoreId;
	}

	public void setCakeStoreId(int cakeStoreId) {
		this.cakeStoreId = cakeStoreId;
	}

	public int getCafeStoreId() {
		return cafeStoreId;
	}

	public void setCafeStoreId(int cafeStoreId) {
		this.cafeStoreId = cafeStoreId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCakeStoreName() {
		return cakeStoreName;
	}

	public void setCakeStoreName(String cakeStoreName) {
		this.cakeStoreName = cakeStoreName;
	}

	public String getCafeStoreName() {
		return cafeStoreName;
	}

	public void setCafeStoreName(String cafeStoreName) {
		this.cafeStoreName = cafeStoreName;
	}

	public String getCakeStorePrimaryImg() {
		return cakeStorePrimaryImg;
	}

	public void setCakeStorePrimaryImg(String cakeStorePrimaryImg) {
		this.cakeStorePrimaryImg = cakeStorePrimaryImg;
	}

	public String getCafeStorePrimaryImg() {
		return cafeStorePrimaryImg;
	}

	public void setCafeStorePrimaryImg(String cafeStorePrimaryImg) {
		this.cafeStorePrimaryImg = cafeStorePrimaryImg;
	}


}
