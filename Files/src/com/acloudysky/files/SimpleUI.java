package com.acloudysky.files;

import java.io.IOException;
import java.io.RandomAccessFile;



/**
* Instantiate the Scanner class to process user's input.
* Display the user's menu.
* Process the user's input.
* @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html" target="_blank">Scanner</a>
* @author Michael
*
*/
public class SimpleUI extends UserInterface {

	// Local class types variables. 
	private static Utilities util =  new Utilities();
	
	private static FileOperations fileOps =  new FileOperations();
	
	private static RandomFileAccess randomFile =  new RandomFileAccess();
	
	private static RawFileAccess rawFile =  new RawFileAccess();
	
	
	/**
	 * Initializes the user's menu.
	 * Constructs a new Scanner that produces 
	 * values scanned from the specified input stream. 
	 */
	SimpleUI() {
		super();
	}
		

	/**
	 * Perform random access operation on the passed file. 
	 * Use the {@link com.acloudysky.util.Utilities#getResourceAbsolutePath(String, String)} to obtain the 
	 * file absolute path.
	 * @param resourceFolder The name of the resources folder.
	 * @param fileName The name of the file containing credit card information.
	 * @param input The Scanner object to allow user's input.
	 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html" target="_blank">RandomAccessFile</a>
	 */
	private void performRandomAccess(String resourceFolder, String fileName)  {
		
		// Absolute path of the random accessed file.
		String dataFileAbsolutePath;
		
		// Instances of the RandomAccessFile class support both reading and 
		// writing to a randomly accessed file. 
		RandomAccessFile creditCardDataFile = null;
	
		/*
		 * Get the absolute path of the file containing credit card information.
		 */
		dataFileAbsolutePath = Utilities.getFileAbsolutePath(resourceFolder, fileName);
	
		try
		{
			/* Open the credit card file for read/write. If the file exists, load it up; 
			 * otherwise, just create a new one.
			 */
			creditCardDataFile = new RandomAccessFile(dataFileAbsolutePath, "rw");
			
			
			boolean done = false;
			
			// Get user's choice.
			do
			{
				// Display the menu.
				Utilities.displayMenu(util.getRandomFilesMenuEntries());
			
				int choice = readUserInput();
				
				switch(choice)
				{
					case 1:
						// Add a new card to the end of the file.
						randomFile.addCreditCard(_input, creditCardDataFile);
						break;
					case 2:
						// Find and view credit card. 
						randomFile.findCreditCard(_input, creditCardDataFile);
						break;
					case 3:
						// Update card details.
						randomFile.updateCreditCard(_input, creditCardDataFile);
						break;
					case 4:
						// List credit card data.
						randomFile.listCreditCards(creditCardDataFile);
						break;
					case 5:
					default:
						done = true;
						break;
				}
			} while (!done);
			
			System.out.println("Thank you for using the credit card manager");
		}
		catch (IOException ioex)
		{
			ioex.printStackTrace();
		}
		finally
		{
			if (creditCardDataFile != null)
			{
				try {
					creditCardDataFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * Perform read and write raw bytes operations on the passed file. 
	 * Use the {@link com.acloudysky.util.Utilities#getResourceAbsolutePath(String, String)} to obtain the 
	 * file absolute path.
	 * @param resourceFolder The name of the resources folder.
	 * @param fileName The name of the file containing credit card information.
	 * @param input The Scanner object to allow user's input.
	 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html" target="_blank">RandomAccessFile</a>
	 */
	private void performRawBytesAccess(String resourceFolder, String fileName)  {
		
		/*
		 * Get the absolute path of the file containing credit card information.
		 */
		String dataFileAbsolutePath = Utilities.getFileAbsolutePath(resourceFolder, fileName);
		
	
		boolean done = false;
		
		// Get user's choice.
		do
		{
			// Display the menu.
			Utilities.displayMenu(util.getRawFilesMenuEntries());
			
			int choice = readUserInput();
			
			switch(choice)
			{
				case 1:
					// Add a new card to the end of the file.
					rawFile.getNewCardDetails(_input);
					break;
				case 2:
					// Find and view credit card. 
					rawFile.findCard(_input);
					break;
				case 3:
					// Update card details.
					rawFile.updateCard(_input);
					break;
				case 4:
					// Display all the cards.
					rawFile.displayCardDetails();
					break;
				case 5: 
					// Load cards from file into local memory.
					rawFile.loadCardDataFromFile(dataFileAbsolutePath);
					break;
				case 6:
					// Save cards from local memory to file.
					rawFile.saveCardDataToFile(dataFileAbsolutePath);
					break;
				case 7:
				default:
					done = true;
					break;
			}
		} while (!done);
		
		System.out.println("Thank you for using the credit card manager");
		
	}
	
	/**
	 * Process input until the user exits the loop.
	 * Display results.
	 */
	public void processUserInput() {
		
		boolean exit = false;
		boolean inputError = true;
		String dir = null;
		String file = null;
		
		do
		{
			// Display the menu.
		
			Utilities.displayMenu(util.getFilesMenuEntries());
			 
			int menuChoice = 0;
			
			do 
			{
				
				// Loop to get user's input.
				try 
				{
					menuChoice = readUserInput();
					inputError = false;
					
					switch(menuChoice)
					{
					case 0:
							// Enter allowed value
							System.out.println(String.format("Select one of the values shown in the menu"));
							break;
						case 1:
							// Show how to read a write a file using the Scanner class.
							System.out.println(String.format("*** Read a write a file using Scanner and FileWriter *** %s", newline));
							fileOps.readWriteFileScanned("resources", "racers.txt", "racersw.txt");
							break;
						case 2:
							// Show how to read a write a file using the Scanner class.
							System.out.println(String.format("*** Read a write a file using BufferedReader and BufferedWriter *** %s", newline));
							fileOps.readWriteFileBuffered("resources", "racers.txt", "racersw.txt");
							break;
						
						case 3:
							// Show how to read a write a file using RandomAccessFile class. 
							System.out.println(String.format("*** Read a write a file using RandomAccessFile *** %s", newline));
							
							dir = null;
							file = null;
							
							System.out.println("Credit card file name or just enter to accept the default value:");
							file = _input.nextLine();
							if (file.equals("")) 
									// Change this value to your actual file name.
									file = "CreditProcessingData.dat";
							System.out.println("Credit card file dir or just enter to accept the default value:");
							dir = _input.nextLine();
							if (dir.equals("")) 
									// Change this value to your actual directory name.
									dir = "Programming\\GitHub\\javalanguage-files\\Files\\resources";
							
							performRandomAccess(dir, file); 
							break;
							
						case 4:
							// Show how to read a write a file using FileINputStream and FileOutputStream classes. 
							System.out.println(String.format("*** Read a write a file using FileInputStream and FileOutpputStream *** %s", newline));
							dir = null;
							file = null;
					
							System.out.println("Credit card file name or just enter to accept the default value:");
							file = _input.nextLine();
							if (file.equals("")) 
									// Change this value to your actual file name.
									file = "CreditProcessingData.bin";
							System.out.println("Credit card file dir or just enter to accept the default value:");
							dir = _input.nextLine();
							if (dir.equals("")) 
									// Change this value to your actual directory name.
									dir = "Programming\\GitHub\\javalanguage-files\\Files\\resources";
							
							performRawBytesAccess(dir, file); 
							break;
							
						case 9:
							// Exit
							exit = true;
							_input.close();
							break;
						default:
							// Enter allowed value
							System.out.println(String.format("Select one of the values shown in the menu"));
					}
					
				} 
				catch (Exception e) 
				{
					// Error encountered while reading user's input.
					inputError = true;
					System.out.println(String.format("Exception: %s", e.toString()));
					System.out.println(String.format("Select one of the values shown in the menu"));
					// Display the menu.
					Utilities.displayMenu(util.getFilesMenuEntries());
				}
			} while (inputError);
			
		} while (!exit);
	
	}

}
