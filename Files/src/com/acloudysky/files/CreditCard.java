package com.acloudysky.files;

import java.io.Serializable;

/**
 * Define a simple credit card structure which contains credit 
 * card number and balance.
 * @author Michael
 *
 */
public class CreditCard implements Serializable 
{
	//long for # [8 bytes]
	private long _ccNum;
	
	//double for balance [8 bytes]
	private double _balance;
	
	public CreditCard()
	{
		//do nothing
	}
	
	
	/**
	 * Create a CreditCard object.
	 * @param ccNum The credit card number.
	 * @param balance The credit card balance.
	 */
	public CreditCard(long ccNum, double balance)
	{
		_ccNum = ccNum;
		_balance = balance;
	}
	
	/**
	 * Get the credit card number.
	 * @return The credit card number.
	 */
	public long getCreditCardNumber()
	{
		return _ccNum;
	}
	
	/**
	 * Set the credit card number.
	 * @param num The credit card number.
	 */
	public void setCreditCardNumber(long num)
	{
		_ccNum = num;
	}
	
	/**
	 * Get the credit card balance.
	 * @return The credit card balance.
	 */
	public double getBalance()
	{
		return _balance;
	}
	
	/**
	 * Set the credit card balance.
	 * @param bal The credit card balance.
	 */
	public void setBalance(double bal)
	{
		_balance = bal;
	}
	
	/**
	 * Overwrite toString method to return the card number and balance.
	 */
	public String toString()
	{
		return String.format("Credit Card Number: %d\tBalance $%.2f"
				, getCreditCardNumber()
				, getBalance());
	}
}
