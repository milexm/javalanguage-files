package com.acloudysky.files;

import java.util.Scanner;

/**
 * Define the attributes and methods required to implement a simple user interface.
 * @author Michael
 *
 */
public abstract class UserInterface {

	// Platform specific separator.
	protected static final String newline = System.getProperty("line.separator");
	
	// Scanner object to get user's input.
	protected static Scanner _input;
	
	/**
	 * Initializes the user's menu.
	 * Constructs a new Scanner that produces 
	 * values scanned from the specified input stream. 
	 */
	UserInterface() {
		
		// Instantiate Scanner object to read user's input.
		_input = new Scanner(System.in);

	}
	
	/**
	 * Prompt user for input.
	 * Read user's input.
	 */
	protected int readUserInput() {
		System.out.print(">>");
		int result = Integer.parseInt(_input.nextLine());
		return result;
	}
	
	/**
	 * Process user's input.
	 */
	public abstract void processUserInput ();
	
}
