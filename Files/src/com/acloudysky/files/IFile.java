package com.acloudysky.files;

import java.util.ArrayList;

/**
 * Defien attributes and data to implement utility functions.
 * @author Michael
 *
 */
public interface IFile {
	
	// Files application menu entries.
	ArrayList<String> filesMenuEntries = new ArrayList<String>();

	// Menu entries for random file access.
	ArrayList<String> randomFilesMenuEntries = new ArrayList<String>();
	
	// Menu entries for raw file access.
	ArrayList<String> rawFilesMenuEntries = new ArrayList<String>();
	
	/**
	 * Get the main menu for the application. 
	 * @return The list containing the main menu.
	 */
	ArrayList<String> getFilesMenuEntries();
	
	/**
	 * Get the menu for random file access.
	 * @return The list containing the menu for random file access.
	 */
	ArrayList<String> getRandomFilesMenuEntries();
 
	/**
	 * Get the menu for raw file access.
	 * @return The list containing the menu for raw file access.
	 */
	ArrayList<String> getRawFilesMenuEntries();
	
	
	/**
	  * Create the user's menu for the Files application.
	 */
	void createFilesMenu();
	

	/**
	  * Create the user's menu for the Files application.
	 */
	void createRandomFilesSubMenu();
		
	
	/**
	  * Create the user's menu for the Files application.
	 */
	void createRawFilesSubMenu();
	
}
