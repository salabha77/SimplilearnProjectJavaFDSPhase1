package com.LockMe;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LockMe {
	static String DIRECTORY;
	File folder_name;

	public LockMe() {
		DIRECTORY = System.getProperty("user.dir");
		folder_name = new File(DIRECTORY+"/files");
		if (!folder_name.exists())
			folder_name.mkdirs();
		System.out.println("DIRECTORY : "+ folder_name.getAbsolutePath());
	}

	private static final String WELCOME_PROMPT =
			"\n====================  LockedMe.com ===================="+
					"\n~~~~~~~~~~~~~~~~~~~~ Developed By ~~~~~~~~~~~~~~~~~"+
					"\n==================== Salabha ====================\n";
	private static final String MAIN_MENU_PROMPT =
			"\nMAIN MENU - Select any of the following To Use LockMe: \n"+
					"1 -> List files in directory\n"+
					"2 -> Add,Search or Delete \n"+
					"3 -> Exit Program";

	private static final String SUB_MENU_PROMPT =
			"   \nSelect any of the following Options: \n"+
					"   a -> To add a file\n"+
					"   b -> To search a file\n"+
					"   c -> To Delete a file\n"+
					"   d -> Go Back to Mainmenu";

	void showMainMenu() {
		System.out.println(MAIN_MENU_PROMPT);
		try{
			Scanner scanner = new Scanner(System.in);
			int menu_key = scanner.nextInt();
			switch (menu_key){
			case 1 : {
				showFiles();
				showMainMenu();
			}
			case 2 : {
				showSecondaryMenu();
			}
			case 3 : {
				System.out.println("Thank You !!!");
				System.exit(0);
			}
			default: showMainMenu();
			}
		}
		catch (Exception e){
			System.out.println("Please Enter Option 1, 2 or 3");
			showMainMenu();
		}
	}

	void showSecondaryMenu() {
		System.out.println(SUB_MENU_PROMPT);
		try{
			Scanner sc = new Scanner(System.in);
			char[] input = sc.nextLine().toLowerCase().trim().toCharArray();
			char option = input[0];

			switch (option){
			case 'a' : {
				System.out.print("↳ For Adding a file...Please Enter the File Name : ");
				String filename = sc.next().trim().toLowerCase();
				addFile(filename);
				break;
			}
			case 'b' : {
				System.out.print("For Searching a file...Please Enter the File Name : ");
				String filename = sc.next().trim();
				searchFile(filename);
				break;
			}

			case 'c' : {
				System.out.print("For Deleting a file...Please Enter the File Name : ");
				String filename = sc.next().trim();
				deleteFile(filename);
				break;
			}
			case 'd' : {
				System.out.println("Going Back to MAIN menu");
				showMainMenu();
				break;
			}
			default : System.out.println("Please enter Otion a, b, c or d");
			}
			showSecondaryMenu();
		}
		catch (Exception e){
			System.out.println("Please enter Option a, b, c or d");
			showSecondaryMenu();
		}
	}

	void showFiles() {
		if (folder_name.list().length==0)
			System.out.println("The folder is empty");
		else {
			String[] list = folder_name.list();
			System.out.println("The files in "+ folder_name +" are :");
			Arrays.sort(list);
			for (String str:list) {
				System.out.println(str);
			}
		}
	}

	void addFile(String filename) throws IOException {
		File filepath = new File(folder_name +"/"+filename);
		String[] list = folder_name.list();
		for (String file: list) {
			if (filename.equalsIgnoreCase(file)) {
				System.out.println("File " + filename + " is already exists at " + folder_name);
				return;
			}
		}
		filepath.createNewFile();
		System.out.println("File "+filename+"is added to "+ folder_name+" Successfully......");
	}

	void deleteFile(String filename) {
		File filepath = new File(folder_name +"/"+filename);
		String[] list = folder_name.list();
		for (String file: list) {
			if (filename.equals(file) && filepath.delete()) {
				System.out.println("File " + filename + " deleted from " + folder_name);
				return;
			}
		}
		System.out.println("Delete Operation failed. FILE NOT FOUND");
	}

	void searchFile(String filename) {
		String[] list = folder_name.list();
		for (String file: list) {
			if (filename.equals(file)) {
				System.out.println("FOUND : File " + filename + " Exists at " + folder_name);
				return;
			}
		}
		System.out.println("File NOT found (FNF)");
	}

	public static void main(String[] args) {
		System.out.println(WELCOME_PROMPT);
		LockMe menu = new LockMe();
		menu.showMainMenu();
	}
}