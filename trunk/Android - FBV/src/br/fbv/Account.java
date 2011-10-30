package br.fbv;

public class Account 
{
	private Integer id;
	private String userName;
	private String userPasswd;
	/**
	 * @return the userNam
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the userPasswd
	 */
	public String getUserPasswd() {
		return userPasswd;
	}
	/**
	 * @param userNam the userNam to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @param userPasswd the userPasswd to set
	 */
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	
} // end class account
