package vo;

import java.io.Serializable;

public class CafeStoreMenuVo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int cafeMenuId;
	private String cafeMenuName;
	private String cafeMenuImgUrl;
	private int cafeMenuPrice;

	public CafeStoreMenuVo() {

	}

	public CafeStoreMenuVo(int cafeMenuId, String cafeMenuName, String cafeMenuImgUrl, int cafeMenuPrice) {
		super();
		this.cafeMenuId = cafeMenuId;
		this.cafeMenuName = cafeMenuName;
		this.cafeMenuImgUrl = cafeMenuImgUrl;
		this.cafeMenuPrice = cafeMenuPrice;
	}

	public int getcafeMenuId() {
		return cafeMenuId;
	}

	public void setcafeMenuId(int cafeMenuId) {
		this.cafeMenuId = cafeMenuId;
	}

	public String getcafeMenuName() {
		return cafeMenuName;
	}

	public void setcafeMenuName(String cafeMenuName) {
		this.cafeMenuName = cafeMenuName;
	}

	public String getcafeMenuImgUrl() {
		return cafeMenuImgUrl;
	}

	public void setcafeMenuImgUrl(String cafeMenuImgUrl) {
		this.cafeMenuImgUrl = cafeMenuImgUrl;
	}

	public int getCafeMenuPrice() {
		return cafeMenuPrice;
	}

	public void setCafeMenuPrice(int cafeMenuPrice) {
		this.cafeMenuPrice = cafeMenuPrice;
	}






	}
