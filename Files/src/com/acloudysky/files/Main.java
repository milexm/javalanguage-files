package com.acloudysky.files;


/**
 * Files application main.
 * Display greetings, instantiate the {@link SimpleUI} class, finally start user's input processing.
 * @author Michael
 *
 */
public class Main {

	private static SimpleUI sui;

	
	public static void main(String[] args) {
		
		Utilities.displayWelcomeMessage("Files Training");
	
		// Instantiate SmpleUI class.
		sui = new SimpleUI();
		
		// Process user's input.
		sui.processUserInput();
		
		Utilities.displayGoodbyeMessage("Files Training");	
	}

}
