package com.acloudysky.files;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


/**
 * Show how to use <a href="https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html" target="_blank">RandomAccessFile</a>
 * <p>
 * 	A random access file behaves like a large array of bytes stored in the file system. 
 *  The following are some of the main characteristics:
 * <ol>
 * 		<li> There is a kind of cursor, or index into the implied array, called the file pointer. </li>
 * 		<li> Input operations read bytes starting at the file pointer and advance the file pointer 
 * 			 past the bytes read.</li>
 * 		<li> If the random access file is created in read/write mode, then output operations are also available. </li>
 * 	    <li> Output operations write bytes starting at the file pointer and advance the file pointer past the bytes written.</li> 
 * 		<li> Output operations that write past the current end of the implied array cause the array to be extended. </li>
 * 		<li> The file pointer can be read by the getFilePointer method and set by the seek method.</li>
 * </ol> 
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html#getFilePointer--" target="_blank">getFilePointer</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html#seek-long-" target="_blank">seek(long)</a>
 * 
 * @author Michael
 * 
 */
public class RandomFileAccess {

	// Absolute path of the random accessed file.
//	private static String dataFileAbsolutePath;
	
	private static final int RECORD_LENGTH = 16;
	
	
	
	/**
	 * Obtain the card number from the user.
	 * Assure that the number is in the correct format using regualr expression
	 * pattern matching.
	 * @param input The Scanner object to obtain user's input.
	 * @return The card number entered by the user.
	 */
	private long getCardNumber(Scanner input)
	{
		
		String matchPattern = "[0-9]{16}";
		boolean valid = false;
		
		while(!valid)
		{
			System.out.println("Please enter a valid 16-digit credit card number:");
			String userNum = input.nextLine();
			valid = userNum.matches(matchPattern);
			// Really we should make sure that the number has not been used already, 
			// but not going to worry about it.
			if (valid)
			{
				return Long.parseLong(userNum);
			}
		}
		
		return -1;
	}
	
	/**
	 * Obtain the card balance from the user.
	 * @param input The Scanner object to obtain user's input.
	 * @return The card balance entered by the user.
	 */
	private double getBalance(Scanner input)
	{
		System.out.println("Please enter the current card balance:");
		return Double.parseDouble(input.nextLine());
	}

	/*
	 * Get the card position in the file.
	 */
	private int findCardPosition(long cardNumber, RandomAccessFile f) throws IOException
	{
		
		// Record length = long[8 bytes] + double[8 bytes].
		long totalRecords = f.length() / RECORD_LENGTH;  
		
		for (int i = 0; i < totalRecords; i++)
		{
			// Move to the start of the next record.
			f.seek(i * RECORD_LENGTH);  
			// Get the next card number.
			long ccNum = f.readLong();
			// If is a match, return i
			if (ccNum == cardNumber)
			{
				return i;
			}
		}
		// If no match, return -1
		return -1;
	}
	
	private void displayCardDetails(int position, RandomAccessFile f) throws IOException
	{
		f.seek(position * RECORD_LENGTH);
		CreditCard cc = new CreditCard(f.readLong(), f.readDouble());
		System.out.println("Card Details: " + cc.toString());
	}
	
	
	/**
	 * Add a new credit card to the file.
	 * @param input The Scanner object to obtain user's input.
	 * @param creditCardDataFile The file to hold the credit card data.
	 * @throws IOException Exception due to I/O error
	 */
	public void addCreditCard(Scanner input, RandomAccessFile creditCardDataFile) throws IOException {
		
		// Create a credit card object.
		CreditCard cc = new CreditCard();
		// Get the credit card number
		cc.setCreditCardNumber(getCardNumber(input));
		// Get the credit card balance.
		cc.setBalance(getBalance(input));
		
		// Point to the end of the file.
		creditCardDataFile.seek(creditCardDataFile.length());
		
		creditCardDataFile.writeLong(cc.getCreditCardNumber());
		creditCardDataFile.writeDouble(cc.getBalance());
		System.out.println(cc.toString() + " added to the system.");
	}
	
	/*
	 * Find a view specific credit card information.
	 * Ask the user to enter the number of the credit card. 
	 */
	/**
	 * Find a view credit card selected by the user.
	 * @param input The Scanner object to obtain user's input.
	 * @param creditCardDataFile The file holding the credit card data.
	 * @throws IOException Exception due to I/O error
	 */
	public void findCreditCard(Scanner input, RandomAccessFile creditCardDataFile) throws IOException {
		int position;
		long ccNum;
		
		System.out.println("Please enter the 16-digit number of the card to view:");
		ccNum = Long.parseLong(input.nextLine());
		// Get the card position in the file.
		position = findCardPosition(ccNum, creditCardDataFile);
		if (position >= 0)
		{
			System.out.println("");
			displayCardDetails(position, creditCardDataFile);
			System.out.println("");
		}
		else
		{
			System.out.println("No card with that number found.");
		}
	
	}

	/*
	 * Update credit card information.
	 */
	
	/**
	 * Update credit card information.
	 * @param input The Scanner object to obtain user's input.
	 * @param creditCardDataFile The file to hold the credit card data.
	 * @throws IOException Exception due to I/O error
	 */
	public void updateCreditCard(Scanner input, RandomAccessFile creditCardDataFile) throws IOException {
		
		int position;
		long ccNum;
		
		
		System.out.println("Please enter the 16-digit number of the card to view:");
		ccNum = Long.parseLong(input.nextLine());
		// Get the card position in the file. 
		position = findCardPosition(ccNum, creditCardDataFile);
		
		if (position >= 0)
		{
			double newBalance = getBalance(input);
			if (newBalance >= 0.0)
			{
				//move pointer and overwrite at position
				creditCardDataFile.seek(position * RECORD_LENGTH);
				creditCardDataFile.writeLong(ccNum);
				creditCardDataFile.writeDouble(newBalance);
				System.out.println("Updated: " + ccNum + " to balance $" + newBalance);
			}
		}
		else
		{
			System.out.println("No card with that number found.");
		}
		
	}
	
	/*
	 * List all the stored credit cards.
	 */
	/**
	 * Display credit card information.
	 * @param creditCardDataFile The file holding the credit card data.
	 * @throws IOException Exception due to I/O error
	 */
	public void listCreditCards(RandomAccessFile creditCardDataFile) throws IOException {
		
		// list all data
		System.out.println("");
		
		long totalRecords = creditCardDataFile.length() / RECORD_LENGTH;
		
		if (totalRecords == 0)
		{
			System.out.println("No current cards on file.");
		}
		else
		{
			for (int i = 0; i < totalRecords; i++)
			{
				displayCardDetails(i, creditCardDataFile);
			}
		}
		System.out.println("");
	}
	
}
