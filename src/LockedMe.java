import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;



public class LockedMe {
	
   private static File folder; 
   private static String path;
   public static void main(String[] args)
 { 
	 System.out.println("Application  : Locked Me");
     System.out.println("Developer : Harshitha G M");	 
	 folder = new File("LockedMe");
	 folder.mkdir();      //Creating a Folder - LockedMe
	 path = folder.getAbsolutePath(); //Fetching the current path the user is in
	 System.out.println("Your current path is: " + path+"\\"); 
		 	 Mainmenu(); //Calling Main menu method
 }
    
 static void Mainmenu(){ // Function to display Main Menu
 
		System.out.println("Main Menu\n1.Display file list\n2.Add..Delete..Search\n3.Close or exit");
		System.out.println("Please choose from above option");
		try (Scanner mopt = new Scanner (System.in)) {
			int option = mopt.nextInt(); // option variable to scan user's input
			
					switch(option) {
			case(1):{
			
				DisplayinAscending();
			   break;
			}
			case(2):{
				Submenu();
				break;
			}
			case(3):
				System.out.println("Thank you");
				System.exit(0);
			default:
				System.out.println("Invalid option. Please close and run the code again");
			}
		}
	}
 static void DisplayinAscending() { //Function to display file/folder names list in ascending order
		
		
		File file = new File(path);
		File dir[]=file.listFiles();
		Arrays.sort(dir);
		int flag=1;
		for(File e : dir) {
			System.out.println("List of files/folders present in : " + path);
			if(e.isFile()) {
				
				System.out.println(e.getName());
				flag=0;
			}
			else if (e.isDirectory()){
				System.out.println(e.getName());
				flag=0;
			}
		}
			if(flag==1)
				System.out.println("Folder is empty");
		
		
		Mainmenu();
	}
 static void Submenu() { //Function to display Sub Menu ( to add,delete, search files from existing directory)
		System.out.println("Please choose an option from Submenu");
		System.out.println("\n1->Add new files\n2->Delete existing files\n3->Search for a file\n4->Back to Main menu");
		try (Scanner choice = new Scanner (System.in)) {
			int i = choice.nextInt(); // Integer i variable to scan user's input
			
				switch(i) {
				case(1):
				{
					System.out.println("Please enter a new file name to add");
					Scanner Scannerobj = new Scanner(System.in); 
					String filename = Scannerobj.next();
					ToCreateFile(filename);
					break;
				}
				case(2):
				{
					System.out.println("Please Enter the file name to delete");
					Scanner Scannerobj = new Scanner(System.in); 
					String filename = Scannerobj.next();
					ToDeleteFiles(filename);
					break;
				}
				case(3):
				{
					System.out.println("Please Enter the file name to search");
					Scanner Scannerobj = new Scanner(System.in); 
					String filename = Scannerobj.nextLine();
					ToSearchFile(filename);
					break;
					
				}
				case(4):
				{
					Mainmenu();
					break;
				}
				default:
				{
					System.out.println("Please select valid option");
					Submenu();
					break;
				}
			}
		}
}
static void ToCreateFile(String filename)  { // Function to add new files to existing directory
	  File filelist = new File(path);
	  String[] list = filelist.list();
	  File newfile = new File(path+"/"+filename);
	  try {
		if(newfile.createNewFile()) {
				 System.out.println("New File: "+ filename + " created in Folder "+ folder);//File creation successful message
			}
	} catch (IOException e1) {
				e1.printStackTrace();
	} 
	 for(String e : list)
	{
	  		
	 if(filename.equalsIgnoreCase(e)) {// Condition to check if file with same name exists
	 		 System.out.println("File name exists. Please try creating with new name");
	 		 	 		
	 }
	}Submenu();
}
static void ToDeleteFiles(String filename) { //Function to delete files from existing directory
	File filelist = new File(path);
	File[] dir=filelist.listFiles();
	int flag=1;
	for(File e: dir) {
		if(filename.equalsIgnoreCase(e.getName())) {
			e.delete();
		System.out.println(filename+" deleted from "+ folder);//Deleted successfully message
		flag=0;
		Submenu();
		return;
		}}
		if(flag==1) 
		{
			System.out.println("File not found");//Error message
		}
		Submenu();
	}

static void ToSearchFile(String filename) { //Function to search files from existing directory
	File file = new File(path);
	File[] dir=file.listFiles();
	int flag = 1;
	for(File e : dir) {
		
			if(filename.equalsIgnoreCase(e.getName())) {
					System.out.println(filename+" found in folder "+ folder); //Success message
					flag = 0;
					break;
			}
			}
	if(flag==1)
		System.out.println("File not found"); // Error message
		Submenu();	
			
	}
}
