package myKeyRep;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class FileManager {
    public static void main(String[] args) {
    	// Display the Application and Developer Details 
		System.out.println("Welcome to the File Manager Application");
		System.out.println("Developed by: Shrinivas ");
		
        Scanner scanner = new Scanner(System.in);
        
        // Main loop of the application
        while (true) {
        	// Main Display Menu Options
        	String [] mainDisplayOptions = new String [4];
        	mainDisplayOptions[0] = "Please select one of the following options:" ;
        	mainDisplayOptions[1] = "1. Display current file names in ascending order";
        	mainDisplayOptions[2] = "2. File Management Options";
        	mainDisplayOptions[3] = "3. Close the Application";
        	        		
			// Read the user's choice or throw an error in case of invalid input
            int choice = promptForInteger(scanner, mainDisplayOptions); //scanner.nextInt();
				                   
            switch (choice) {
                case 1: // Option to Display files in ascending order 
                    File directory = new File(".");
                    
					String[] files = directory.list();
					
					if (files != null) {
						// Sorting the files in ascending order
						Arrays.sort(files);
						// Printing the file name available in the directory 
						for (String file : files) {
							System.out.println(file);
						}
					}
                    break;
                    
                case 2: // Option for File Management 
                	// Setting the boolean to Loop until the user chooses to go back to the main menu
                	Boolean temp = true;
                	String[] fileManageOptions = new String[5];
                	fileManageOptions[0] = "Please select the File Management Options:" ;
                	fileManageOptions[1] = "1. To Add a file" ;
                	fileManageOptions[2] = "2. To Delete a file" ;
                	fileManageOptions[3] = "3. To Search a file" ;
                	fileManageOptions[4] = "4. To Back to Main Menu";
                	
                    while (temp) {
                    	                    	
                    	choice = promptForInteger(scanner, fileManageOptions);
						switch (choice) {
							case 1: // Option to add file
								System.out.print("Enter the file name to add: ");
								// Reading the file name to create from user 
								String fileName = scanner.next();
								// Creating a new file with the user input name and display upon completion 
								File file = new File(fileName);
								
								try {
									if (file.createNewFile()) {
										System.out.println("File " + fileName + " added successfully.");
									} else {
										System.out.println("File already exists."); // Provide feedback if file name already exists 
									}
								} 
								// Error handling in case of any exceptions 
								catch (Exception e) {
									System.out.println("Error creating file.");
								}
								break;
								
							case 2: // Option to delete file
								System.out.print("Enter the file name to delete: ");
								// Reading the file name to delete from user 
								String delName = scanner.next();
								
								File delFile  = new File(delName);
								// Checking if the file exists in the directory
								if (delFile.exists() && delFile.isFile()) {
									// Deleting the file from the directory 
									if (delFile.delete()) { 
										System.out.println("File " + delName + " deleted successfully.");
									} else {
										System.out.println("Error deleting file.");
									}
								} else {
									// If file does not exists displaying FNF 
									System.out.println("File not found.");
								}
								break;
							case 3: // Option to search file
								System.out.print("Enter the file name to search: ");
								// Reading the file name to search from user 
								String scanName = scanner.next();
								
								File scandirectory = new File(".");
								// Reading all the files available in the directory
								String[] scanfiles = scandirectory.list();
								
								if (scanfiles != null) {
									boolean found = false;
									// Searching for all the file available in the directory against the user input
									for (String scanfile : scanfiles) {
										if (scanfile.equals(scanName)) {
											System.out.println("File " + scanName + " found in the directory.");
											found = true;
											break;
										}
									}
									if (!found) {
										System.out.println("File not found.");
									}
								}
								break;
							case 4: // Option to go back to main menu 
								temp = false;
								break;
							default: // For invalid user input 
								System.out.println("Invalid choice.");
						}
                    }
                    break;
                case 3: // Close the program 
                    System.out.println("Thank you for using the application.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    // Method that prompts the user to enter an integer and returns the input
    private static int promptForInteger(Scanner scanner, String[] displayOption) {
        int choice = 0;
        boolean validInput = false;
        // Loop until the user enters a valid integer input
        do {
            try {
            	// Display the options available for the users and get the user input as an integer
            	for (String line : displayOption) {
                    System.out.println(line);
            	}
                choice = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
            	// Catch the InputMismatchException if the user enters a non-integer input
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume the newline character
            }
        } while (!validInput);
        
        // Return the valid integer input
        return choice;
    }
}

    