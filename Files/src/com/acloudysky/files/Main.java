package com.acloudysky.files;

import com.acloudysky.util.Utilities;

/**
 * Files application main.
 * Display greetings, instantiate the {@link SimpleUI} class, finally starts user's input processing.
 * @author Michael
 *
 */
public class Main {

	private static SimpleUI sui;
	

	public static void main(String[] args) {
		
		Utilities.printWelcome("Files Training.");
		sui = new SimpleUI();
		sui.processUserInput();
		Utilities.printGoodbye("Files Training.");	
	}

}
