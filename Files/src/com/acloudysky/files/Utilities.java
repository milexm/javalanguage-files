package com.acloudysky.files;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
* Define utility methods to use in the examples.
* @author Michael
*
*/
public class Utilities implements IFile
{
	
	// Test flag.
	private static boolean DEBUG = false;

	// Divider length.
	private static final int DIVIDER_LENGTH = 66;
	
	/**
	 * Get menu for the Files application.
	 */
	public ArrayList<String> getFilesMenuEntries() {
		return filesMenuEntries;
	}

	/**
	 * Get the menu for the random file access operations.
	 */
	public ArrayList<String> getRandomFilesMenuEntries() {
		return randomFilesMenuEntries;
	}

	/**
	 * Get the menu for the raw file operations.
	 */
	public ArrayList<String> getRawFilesMenuEntries() {
		return rawFilesMenuEntries;
	}

	
	/** 
	 * Initialize the Utilities class.
	 */
	public Utilities(){
	
		// Initialize the Files application main menu.
		createFilesMenu();	
		
		// Initialize the random file access sub-menu.
		createRandomFilesSubMenu();
		
		// Initialize the raw file access sub-menu.
		createRawFilesSubMenu();
	}


	 /**
	   * Create the user's menu for the Files application.
	  */
	 public void createFilesMenu()
	 {
	 	// Clear current content.
		 filesMenuEntries.clear();
	 	
	 	// Prepare the menu.
	 	filesMenuEntries.add(dividerLine("*", DIVIDER_LENGTH));
	 	filesMenuEntries.add("0 - Display menu");
	 	filesMenuEntries.add("1 - Read and write file using Scanner");
	 	filesMenuEntries.add("2 - Read and write file using BufferRead and BufferWrite");
	 	filesMenuEntries.add("3 - Read and write file using RandomAccessFile");
	 	filesMenuEntries.add("4 - Read and write file using InputStreamFile and OutputStreamFile");
	 	filesMenuEntries.add("9 - Quit");
	 	filesMenuEntries.add(dividerLine("*", DIVIDER_LENGTH));
	 	
	 
	 }
		    
	 /**
	  * Create the user's sub-menu for the Files application using random access.
	  */
	 public void createRandomFilesSubMenu()
	 {
		 // Clear current content.
		 randomFilesMenuEntries.clear();
	 	
	 	 // Prepare the menu.
		 randomFilesMenuEntries.add(dividerLine("*", DIVIDER_LENGTH));
		 randomFilesMenuEntries.add("1 - Add new credit card");
		 randomFilesMenuEntries.add("2 - View credit card details");
		 randomFilesMenuEntries.add("3 - Update credit card details");
		 randomFilesMenuEntries.add("4 - List all cards and balances");
		 randomFilesMenuEntries.add("5 - Quit random access operations");
		 randomFilesMenuEntries.add(dividerLine("*", DIVIDER_LENGTH));
	 	
	 }
 
	 /**
	  * Create the user's sub-menu for the Files application using raw byte access.
	  */
	 public void createRawFilesSubMenu()
	 {
		 // Clear current content.
		 rawFilesMenuEntries.clear();
	 	
	 	 // Prepare the menu.
		 rawFilesMenuEntries.add(dividerLine("*", DIVIDER_LENGTH));
		 rawFilesMenuEntries.add("1 - Add new credit card");
		 rawFilesMenuEntries.add("2 - View credit card details");
		 rawFilesMenuEntries.add("3 - Update credit card details");
		 rawFilesMenuEntries.add("4 - List all cards and balances");
		 rawFilesMenuEntries.add("5 - Load card data");
		 rawFilesMenuEntries.add("6 - Save card data");
		 rawFilesMenuEntries.add("7 - Quit random access operations");
		 rawFilesMenuEntries.add(dividerLine("*", DIVIDER_LENGTH));
	 	
	 }
	 
	 
	 /**
	  * Display the menu.
	  * @param entry The array containing the menu entries. 
	  */
	 public static void displayMenu(ArrayList<String> entry) {
	 	Iterator<String> i = entry.iterator();
	 	while (i.hasNext()) {
	 		System.out.println(i.next());
	 	}	
	 }
	
	 public static boolean nullSafeEquals(Object one, Object two)
	 {
	     boolean result = one != null ? one.equals(two) : two == null;
	     return result;
	 }

	 /**
	  * Display welcome message.
	  * @param message The message to display.
	  */
	 public static void displayWelcomeMessage(String message)
	 {
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	     String welcome = "Welcome to " + message; 
	     System.out.println(headerLine(welcome, DIVIDER_LENGTH));
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	 }
	
	 /**
	  * Display good bye message.
	  * @param message The message to display.
	  */
	 public static void displayGoodbyeMessage(String message)
	 {
		 headerLine(message, DIVIDER_LENGTH);
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	     String bye = "Thank you for using " + message; 
	     System.out.println(headerLine(bye, DIVIDER_LENGTH));
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	 }
	
	 /**
	  * Create the divider line.
	  * @param c The character to use to create the divider line.
	  * @param length The length of the divider line.
	  * @return
	  */
	 public static String dividerLine(String c, int length)
	 {
	     String divider = "";
	     for(int i = 0; i < length; i++)
	         divider = divider.concat(c);
	
	     return divider;
	 }
	 
	 /**
	  * Create the header to display.
	  * @param headerText The text to display in the header.
	  * @param length The length of the header.  
	  * @return
	  */
	 public static String headerLine(String headerText, int length)
	 {
	     String header = "";
	     header = header.concat("***");
	     int blankSpaces = (length - (header.length() + headerText.length()))/2;
	     
	     for(int i = 2; i < blankSpaces; i++)
	     	header = header.concat(" ");
	     header = header.concat(headerText);
	     for(int i = header.length(); i < length - 3; i++)
	     	header = header.concat(" ");
	     header = header.concat("***");
	     return header;
	 }
	
	 
	 public static String getResourceAbsolutePath(String dir, String file)
	 {
	    String prjAbsolutePath = (new File("")).getAbsolutePath();
	    String fileAbsolutePath = (new StringBuilder(String.valueOf(prjAbsolutePath))).append(File.separator).append(dir).append(File.separator).append(file).toString();
	    return fileAbsolutePath;
	 }
	
	 /**
	  * Get the absolute path of the passed file. 
	  * @param dir The directory where the file exists. 
	  * @param file The name of the file for which to obtain the path.
	  * @return The formatted header.
	  */
	 public static String getFileAbsolutePath(String dir, String file)
	 {
	     String OS = System.getProperty("os.name");
	     String home_dir = System.getProperty("user.home");
	     String filePath = null;
	     if(OS.startsWith("Windows"))
	         filePath = home_dir.concat((new StringBuilder("\\")).append(dir).append("\\").append(file).toString());
	     else
	     if(OS.startsWith("Mac"))
	         filePath = home_dir.concat((new StringBuilder("/")).append(dir).append("/").append(file).toString());
	     if(DEBUG)
	     {
	         System.out.println(String.format("Home dir: %s", new Object[] {
	             home_dir
	         }));
	         System.out.println(String.format("OS: %s", new Object[] {
	             OS
	         }));
	         System.out.println(String.format("File path: %s", new Object[] {
	             filePath
	         }));
	     }
	     return filePath;
	 }
	

}
