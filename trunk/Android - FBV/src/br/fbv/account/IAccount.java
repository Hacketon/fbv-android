/**
 * 
 */
package br.fbv.account;

public interface IAccount 
{
	public long inserir(Account account);
	
	public Account buscarUserName(String userName);
	
} // end interface IAccount
