package com.acloudysky.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.acloudysky.util.Utilities;

/**
 * Show how to perform file I/O operations.
 * @author Michael
 *
 */
public  class  FileOperations {

	// Variable to store file content.
	private static ArrayList<String> readStrings = new ArrayList<String>();
	
	
   
	
	// Simple text scanner which can parse primitive types and 
	// strings using regular expressions. 
	private static Scanner input = null;
	
	/**
	 * Read a text file using the Scanner class.
	 * @param inputFilePath  The file path.
	 * @see Scanner
	 */
	private static void readFileScanned(String inputFilePath) {
		try
		{
			// Open the file using the Scanner object.
			// Get the input file.
			File inputFile = new File(inputFilePath);
			input = new Scanner(inputFile);
			
			readStrings.clear();
			
			while (input.hasNextLine())
			{
				String nextString = input.nextLine();
				// Store the string.
				readStrings.add(nextString);
				// Display the added string.
				System.out.println(nextString + " added...");	
			}	
			if (input != null)
			{
				input.close();
				input = null;
			}
			
		}
		catch (IOException ioex)
		{
			ioex.printStackTrace();
		}
		
	}
	
	/**
	 * Write to the output file using the FileWriter class.
	 * @param outputFilePath The path of the output file.
	 * @see FileWriter
	 */
	private static void writeFileScanned(String outputFilePath) {
		
		// Convenience class for writing character files. 
		FileWriter fw = null;
		try
		{
			fw = new FileWriter(outputFilePath);
			
			// Write to a file using the Scanner object:
			Random r = new Random();
			int max = 85;
			int min = 18;
			for (String s : readStrings)
			{
				//let's assign a random age to each racer
				//and then we'll save the data to a new file "RacersModified"
				int age = r.nextInt((max - min)) + min + 1;  //0 based
				
				String output = s + "|" + age + "\n";
				fw.write(output);
			}
		}
		catch (IOException ioex)
		{
			ioex.printStackTrace();
		}
		finally
		{
			if (fw != null)
			{
				try {
					fw.close();
					fw = null;
				} catch (IOException ioex2) {
					// TODO Auto-generated catch block
					ioex2.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Read and write the specified file.
	 * @param resourceFolder  The name of the resource folder.
	 * @param inputFileName   The name of the text file to read.
	 * @param outputFileName  The name of the text file to write.
	 */
	public static void readWriteFileScanner(String resourceFolder, String inputFileName, String outputFileName) 
	{
		
		// Get input file absolute path.
		String inputFileAbsolutePath =  Utilities.getResourceAbsolutePath(resourceFolder, inputFileName);
		
		// Get output file absolute path.
		String outputFileAbsolutePath =  Utilities.getResourceAbsolutePath(resourceFolder, outputFileName);
				
		// Read the input file.
		readFileScanned(inputFileAbsolutePath);
		
		// Write to the output file.
		writeFileScanned(outputFileAbsolutePath);
		
	
	}
	 
	/**
	 * Read input text file.
	 * @param inputFilePath - The input file path.
	 */
	public static void readFileBuffered(String inputFilePath) 
	{
		FileReader fr = null;
		BufferedReader br = null;
		readStrings.clear();
		
		try 
		{
			
			// Read input file.
			fr = new FileReader(inputFilePath);
			br = new BufferedReader(fr);
			String nextLine = null;
			
			while ((nextLine = br.readLine()) != null)
			{
				// Add to the String array.
				readStrings.add(nextLine);
				System.out.println("Added " + nextLine);
			}
			
		} 
		catch (IOException ioex1) 
		{
			ioex1.printStackTrace();
		}
		finally 
		{
			try
			{
				if (fr != null)
				{
					if (br != null)
					{
						br.close();
						br = null;
					}
					fr.close();
					fr = null;
				}
			}
			catch (IOException ioex2)
			{
				ioex2.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Write to the output file. 
	 * @param outputFilePath  The output file path.
	 */
	public static void writeFileBuffered(String outputFilePath) 
	{
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try 
		{
			// Write output file.
			fw = new FileWriter(outputFilePath);
			bw = new BufferedWriter(fw);
			Random r = new Random();
			int max = 85;
			int min = 18;
			
			for (String s : readStrings)
			{
				int age = r.nextInt((max - min)) + min + 1;  //0 based
				
				String output = s + "|" + age;
				bw.write(output);
				bw.newLine();
			}
		} 
		catch (IOException ioex1) 
		{
			ioex1.printStackTrace();
		}
		finally 
		{
			try
			{
				if (fw != null)
				{
					if (bw != null)
					{
						bw.close();
						bw = null;
					}
					fw.close();
					fw = null;
				}
			}
			catch (IOException ioex2)
			{
				ioex2.printStackTrace();
			}
			
		}
		
	}
	
	
	/**
	 * Read and write the specified file.
	 * @param resourceFolder  The name of the resource folder.
	 * @param inputFileName   The name of the text file to read.
	 * @param outputFileName  The name of the text file to write.
	 */
	public static void readWriteFileBuffered(String resourceFolder, String inputFileName, String outputFileName) 
	{
		
		// Get input file absolute path.
		String inputFileAbsolutePath =  Utilities.getResourceAbsolutePath(resourceFolder, inputFileName);
		
		// Get output file absolute path.
		String outputFileAbsolutePath =  Utilities.getResourceAbsolutePath(resourceFolder, outputFileName);
				
		// Read input file.
		readFileBuffered(inputFileAbsolutePath);
		
		// Write output file.
		writeFileBuffered(outputFileAbsolutePath);
		
		
	}

}
