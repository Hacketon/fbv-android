package br.fbv.account;

public class Account 
{
	private Integer id;
	private String userName;
	private String userPasswd;
	
//	/**
//	 * @param id
//	 * @param userName
//	 * @param userPasswd
//	 */
//	public Account(Integer id, String userName, String userPasswd) 
//	{
//		super();
//		this.id = id;
//		this.userName = userName;
//		this.userPasswd = userPasswd;
//	}

	/**
	 * @return the id
	 */
	public Integer getId() 
	{
		return id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() 
	{
		return userName;
	}

	/**
	 * @return the userPasswd
	 */
	public String getUserPasswd() 
	{
		return userPasswd;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) 
	{
		this.id = id;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	/**
	 * @param userPasswd the userPasswd to set
	 */
	public void setUserPasswd(String userPasswd) 
	{
		this.userPasswd = userPasswd;
	}
	
} // end class account
