/**
 * 
 */
package br.fbv;

/**
 * @author yurialbuquerque
 *
 */
public interface IAccount 
{
	public long inserir(Account account);
	
	public Account buscarUserName(String userName);
	
} // end interface IAccount
