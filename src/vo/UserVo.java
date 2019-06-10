package vo;

import java.io.Serializable;

public class UserVo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String userMailAddress;
	private String userPassword;
	private int userRole;

	public UserVo() {

	}
	public UserVo(int userId, String userName, String userMailAddress, String userPassword, int userRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userMailAddress = userMailAddress;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMailAddress() {
		return userMailAddress;
	}
	public void setUserMailAddress(String userMailAddress) {
		this.userMailAddress = userMailAddress;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}



}
