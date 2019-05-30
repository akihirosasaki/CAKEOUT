package Vo;

public class CakeStoreMenuVo {
	private int cakeMenuId;
	private String cakeMenuName;
	private String cakeMenuImgUrl;
	private int cakeStock;

	public CakeStoreMenuVo() {

	}

	public CakeStoreMenuVo(int cakeMenuId, String cakeMenuName, String cakeMenuImgUrl, int cakeStock) {
		super();
		this.cakeMenuId = cakeMenuId;
		this.cakeMenuName = cakeMenuName;
		this.cakeMenuImgUrl = cakeMenuImgUrl;
		this.cakeStock = cakeStock;
	}

	public int getCakeMenuId() {
		return cakeMenuId;
	}

	public void setCakeMenuId(int cakeMenuId) {
		this.cakeMenuId = cakeMenuId;
	}

	public String getCakeMenuName() {
		return cakeMenuName;
	}

	public void setCakeMenuName(String cakeMenuName) {
		this.cakeMenuName = cakeMenuName;
	}

	public String getCakeMenuImgUrl() {
		return cakeMenuImgUrl;
	}

	public void setCakeMenuImgUrl(String cakeMenuImgUrl) {
		this.cakeMenuImgUrl = cakeMenuImgUrl;
	}

	public int getCakeStock() {
		return cakeStock;
	}

	public void setCakeStock(int cakeStock) {
		this.cakeStock = cakeStock;
	}




	}
