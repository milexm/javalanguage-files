package com.acloudysky.files;

import java.util.Scanner;

import com.acloudysky.util.Utilities;

/**
* Instantiate the Scanner class to process user's input.
* Display the user's menu.
* Process the user's input.
* @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html" target="_blank">Scanner</a>
* @author Michael
*
*/
public class SimpleUI {

	private static final String newline = System.getProperty("line.separator");
	private static Scanner _input;
	private static StringBuilder _menu;
	
	/**
	 * Initializes the user's menu.
	 * Constructs a new Scanner that produces 
	 * values scanned from the specified input stream. 
	 */
	SimpleUI() {
		
		// Initialize scanner object to read user's input.
		_input = new Scanner(System.in);
		
		// Initialize the user's menu.
		_menu = Utilities.createFilesMenu();
		
	}
	
	/*
	 * Prompt user for input.
	 * Read user's input.
	 */
	private int readInput() {
		System.out.print(">>");
		int result = Integer.parseInt(_input.nextLine());
		return result;
	}
	

	/**
	 * Process input until the user exits the loop.
	 * Display results.
	 */
	public void processUserInput() {
		
		boolean exit = false;
		boolean inputError = true;
		
		// Display the menu.
		System.out.println(_menu.toString());
		
		do
		{
			
			int menuChoice = 0;
			
			do 
			{
				// Loop to get user's input.
				try 
				{
					menuChoice = readInput();
					inputError = false;
					
					switch(menuChoice)
					{
					case 0:
							// Enter allowed value
							System.out.println(String.format("Select one of the values shown in the menu"));
							// Display the menu.
							System.out.println(_menu.toString());
							break;
						case 1:
							// Show how to read a write a file using the Scanner class.
							System.out.println(String.format("*** Read a write a file using Scanner and FileWriter *** %s", newline));
							FileOperations.readWriteFileScanner("resources", "racers.txt", "racersw.txt");
							break;
						case 2:
							// Show how to read a write a file using the Scanner class.
							System.out.println(String.format("*** Read a write a file using BufferedReader and BufferedWriter *** %s", newline));
							FileOperations.readWriteFileBuffered("resources", "racers.txt", "racersw.txt");
							break;
						
						case 3:
							// Show how to read a write a file using RandomAccessFile class. 
							System.out.println(String.format("*** Read a write a file using RandomAccessFile *** %s", newline));
							String dir = null;
							String file = null;
					
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
								
							RandomFileAccess.performRandomAccess(dir, file, _input); 
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
					System.out.println(_menu.toString());
				}
			} while (inputError);
			
		} while (!exit);
	
	}


}
