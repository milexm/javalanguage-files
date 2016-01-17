package com.acloudysky.files;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Read and write raw byte data, such as image data. from and to a file. 
 * A FileInputStream reads raws bytes from a file. It works with ObjectInputStream which deserializes primitive data and objects 
 * read from the file.
 * A FileInputStream writes raw bytes to a file. It works with ObjectOutputStream which serializes primitive data and objects 
 * to write to the file.
 * <ol>
 * 		<li> There is a kind of cursor, or index into the implied array, called the file pointer. </li>
 * 		<li> Input operations read bytes starting at the file pointer and advance the file pointer 
 * 			 past the bytes read.</li>
 * 		<li> If the random access file is created in read/write mode, then output operations are also available. </li>
 * 	    <li> Output operations write bytes starting at the file pointer and advance the file pointer past the bytes written.</li> 
 * 		<li> Output operations that write past the current end of the implied array cause the array to be extended. </li>
 * 		<li> The file pointer can be read by the getFilePointer method and set by the seek method.</li>
 * </ol> 
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html" target="_blank">FileInputStream</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html" target="_blank">FileOutputStream</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/ObjectInputStream.html" target="_blank">ObjectInputStream</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutputStream.html" target="_blank">ObjectOutputStream</a>
 * @author Michael
 * 
 */
public class RawFileAccess {
	
	// Use a list to manage all the current cards in the system
	ArrayList<CreditCard> cards = null;
	CreditCard cc = null;
	
	
	/**
	 * Get the number for the new card to be created.
	 * Parse the input to assure that it is in the allowed format.
	 * @param input The Scanner to obtain user's input.
	 * @return The card number in the proper format.
	 */
	private static long getNewCardNumber(Scanner input)
	{
		String matchPattern = "[0-9]{16}";
		boolean valid = false;
		
		while(!valid)
		{
			System.err.println("Please enter a valid 16-digit credit card number:");
			String userNum = input.nextLine();
			valid = userNum.matches(matchPattern);
			// Really we should make sure not used too, but not going to worry about it.
			if (valid)
			{
				return Long.parseLong(userNum);
			}
		}
		return -1;
	}
	
	/**
	 * Get the balance for the new card to be created.
	 * @param input The Scanner to obtain user's input.
	 * @return The card balance.
	 */
	private static double getNewCardBalance(Scanner input)
	{
		System.out.println("Please enter the current card balance:");
		return Double.parseDouble(input.nextLine());
	}
	
	/**
	 * Get card number and balance details from the user to
	 * create a new card.
	 * @param input The Scanner to obtain user's input.
	 */
	public void getNewCardDetails(Scanner input)
	{
		if (cards == null)
			// Create the list to store card information.
			cards = new ArrayList<CreditCard>();
		
		// Create the card object.
		CreditCard cc = new CreditCard();
		// Get the card number and the balance from the user.
		// We should check if the card already exists, but 
		// we'll ignore it for simplicity.
		cc.setCreditCardNumber(getNewCardNumber(input));
		cc.setBalance(getNewCardBalance(input));
		// Store the card information.
		cards.add(cc);
		
		// Display the new card information. 
		System.out.println(cc.toString() + " added to the system.");
		System.out.println("Remember to store the information to file.");
	}
	
	/**
	 * Display card information.
	 * @param cc The card to display.
	 */
	private void displayCardDetails(CreditCard cc)
	{
		System.out.println("Card Details: " + cc.toString());
	}
	
	/**
	 * Find the card selected by the user.
	 * Display related information.
	 * @param input The Scanner input object to obtain user's card number.
	 */
	public void findCard(Scanner input)
	{
		
		if (cards == null || cards.size() == 0)
		{
			System.err.println("There are currently no cards in the system.");
			System.err.println("You must load the cards info from file, first.");
		}
		else
		{
			// Find the card and display detailed information.
			System.out.println("Please enter the 16-digit number of the card to view:");
		
			// Get the card number from the user.
			long cardnum = Long.parseLong(input.nextLine());
			
			// Loop through the array to find the card.
			for (CreditCard currentCard : cards)
			{
				if (currentCard.getCreditCardNumber() == cardnum)
					cc = currentCard;
					break;
			}
			
			if (cc != null)
			{
				displayCardDetails(cc);
			}
			else
			{
				System.out.println("No card with that number found.");
			}
		}
		
	}
	
	/**
	 * Update the card balance.
	 * @param input The Scanner to obtain user's input.
	 */
	public void updateCard(Scanner input)
	{
		
		if (cards == null || cards.size() == 0)
		{
			System.err.println("There are currently no cards in the system.");
			System.err.println("You must load the cards info from file, first.");
		}
		else
		{
			// Find the card and display detailed information.
			System.out.println("Please enter the 16-digit number of the card to update:");
		
			// Get the card number from the user.
			long cardnum = Long.parseLong(input.nextLine());
			
			// Loop through the array to find the card.
			for (CreditCard currentCard : cards)
			{
				if (currentCard.getCreditCardNumber() == cardnum)
					cc = currentCard;
					break;
			}
			
			if (cc != null)
			{
				// Get new balance from the user.
				double balance = getNewCardBalance(input);
				cc.setBalance(balance);
				displayCardDetails(cc);
			}
			else
			{
				System.out.println("No card with that number found.");
			}
		}
		
	}
	
	
	/**
	 * Display all the stored cards.
	 */
	public void displayCardDetails()
	{
	
		// List credit card data.
		if (cards!= null && cards.size() > 0)
		{
			System.out.println("");
			
			for (CreditCard c : cards)
			{
				displayCardDetails(c);
			}
			System.out.println("");
		}
		else
		{
			System.out.println("No cards in the system at this time.");
			System.out.println("Load them from file first.");
		}
		
	}

	/**
	 * Read data from a file
	 * @param dataFilePath The path of the file that contains the data.
	 */
	@SuppressWarnings("unchecked")
	public void loadCardDataFromFile(String dataFilePath)
	{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try 
		{
			// Read the raw data from the file.
			fis = new FileInputStream(dataFilePath);
			// Deserialize the data.
            ois = new ObjectInputStream(fis);
            // Store the objects in the array.
            cards = (ArrayList<CreditCard>) ois.readObject();
            // Inform the user.
            System.out.println("Cards loaded from file.");
            
		}
		catch (ClassNotFoundException ex) 
		{
			ex.printStackTrace();
		}
		catch (FileNotFoundException ex) 
		{
			ex.printStackTrace();
		}
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		finally 
		{
			try 
			{
				// Perform house-keeping.
				fis.close();
				ois.close();
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}	
		}
		
	}
	
	/**
	 * Write data to a file
	 * @param dataFilePath The path of the file that contains the data.
	 */
	public void saveCardDataToFile(String dataFilePath)
	{	
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try 
		{
			if (cards == null) 
				System.out.println("No Cards in the system at this time");	
			else
			{
				// Open the file.
				fos = new FileOutputStream(dataFilePath);
				// Create the object stream.
				oos = new ObjectOutputStream(fos);
				// Serialize the data and store it into the file.
				oos.writeObject(cards);
				// Inform the user.
			    System.out.println("Cards saved to file.");
			}
			
		}
		catch (FileNotFoundException ex) 
		{
			ex.printStackTrace();
		}
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		finally 
		{
			try 
			{
				// Perform house-keeping.
				fos.close();
				oos.close();
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}	
		}
		
	}

}
