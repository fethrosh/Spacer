//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IODriver {
	//This basically tells the program where these files are, and basically opens them. 
	private static File file = new File ("save.txt"), position = new File("position.txt"),manifest = new File("manifest.txt");
	
	//this basically is here to load things from the files
	public static int Loader(Player p1,position pos) throws InterruptedException {
		//initialises the money array
		int[] money = {0,0,0};
		try {
			//These scanners are reading the save file and the manifest file
			Scanner read = new Scanner(file);
			Scanner man = new Scanner(manifest);
			//sets health and sheild to the value in the save file
			p1.sethealth(read.nextInt());
			p1.setsheild(read.nextInt());
			//iterates through the money array and sets the money to the values in the save file
			for(int i = 0;i<3;i++) {
				p1.setmoney(i, read.nextInt());
			}
			//reads the save file and sets the systems as open(true)or closed(false)
			for (int i=0;i<6;i++) {
				pos.setSys(i, read.nextBoolean());
			}
			//p1.gainmoney(0) will make sure that 100 bronze turns to 1 silver,
			//100 silver to 1 gold
			p1.gainmoney(0);
			//reads the manifest file and sets the values for the materials in the manifest
			for (int i=0;i<p1.materialLength;i++) {
				p1.setMaterialAmount(i, man.nextInt());
			}
			//calls the position reading method
			readPosition(pos);
			//Prints out the greeting with the players name
			//TODO need to make the player's name saved somewhere
			System.out.printf("\nLoaded!\n\nGreetings Captain %s!",p1.getName());
			//test part of the class where I wanted to make the output colored. It dindn't work yet...
			/*prints out 
			Hull:     50            0 gold
			Shields:  100           1 silver
			Position: epsilon       0 bronze
			*/
			if (p1.gethealth()>0) {
				System.out.printf("%n%-10s%-14d%d %s%n%-10s%-14d%d %s%n%-10s%-14s%d %4s%n","Hull:",p1.gethealth(),p1.getmoney(2),"gold",
						"Shields:",p1.getsheild(),p1.getmoney(1),"silver","Position:",pos.getCurrentPosition(),p1.getmoney(0),"bronze");
			}
			//closes the files so it doesn't leak outside of the method
			read.close();
			man.close();
		} 
		//catches an error if the .txt files aren't found
		catch(FileNotFoundException e){
			System.out.print("File was not found, please try again");
		} 
		//catches an error of when something unexpected comes through where it shouldn't
		catch(InputMismatchException i) {
			System.out.print("Save file may be corrupted. Please try again");
		}
		//test stuff, ignore
		catch(NoSuchElementException n) {
			int[] cargo = new int[1];
			saver(0, 0, money, cargo, p1, pos, file);
		}
		return 1;
		
	}
	
	//Writes to the save file 
	public static int saver(int h, int s, int[]m, int[]cargo, Player p1, position pos, File file) {
		try {
			//creates the writing objects. They can only write, not read
			FileWriter write = new FileWriter(file);
			FileWriter man = new FileWriter(manifest);
			//initialization of the save string that will be put into the save.txt file
			String saveline = ""+h+" "+s;
			//initialization of the save string that will be put into the manifest.txt file
			String manLine = "";
			//writes the money amounts to the save line
			for (int i =0;i<m.length;i++) {
				saveline+=" "+m[i];
			}
			//adds the active and inactive systems to the save line
			for (int i=0;i<pos.active_Systems.length;i++) {
				saveline+=" "+pos.getsys(i);
			}
			//adds the amount of materials in the manifest to the manifest.txt file
			for (int i=0;i<p1.materialLength;i++) {
				manLine+=p1.getMaterialAmount(i)+" ";
			}
			//next two lines write to their files
			write.write(saveline);
			man.write(manLine);
			//stopping resourse leaks, can be ignored
			write.close();
			man.close();
			//lets the user know that the save was successful
			System.out.print("Successfully saved your information!\n");
		} 
		//catches a fail in saving the information. Usually triggered by the file not existing in the place it was supposed to be
		catch (IOException e) {
			System.out.print("There was an unexpected error. Please try again later.\n\n");
		}
		//returns a success code
		return 0;
	}
	
	//method to read the position file and add it to the position class
	public static void readPosition(position pos) {
		try {
			//creates a reader for the position.txt file
			Scanner read = new Scanner(position);
			//sets the position to that of the save file
			pos.setCurrentLocation(read.nextLine());
			//resource leak prevention, ignore
			read.close();
		} 
		//trips if the file isn't found
		catch (FileNotFoundException e) {
			System.out.print("Unable to find your location. Resetting you to your base");
			pos.setCurrentLocation("base");
		//trips if Something that isn't a string comes through
		} catch (InputMismatchException i) {
			System.out.print("Unable to find your location. Resetting you to your base");
			pos.setCurrentLocation("base");
		}
	}
	
	//method for saving the position of the player
	public static void savePosition(position pos) {
		try {
			//creates a writer for the position.txt file
			FileWriter write = new FileWriter(position);
			//writes the current position of the player
			write.write(pos.getCurrentPosition());
			//resource leak prevention, ignore
			write.close();
			//lets the user know their position was saved correctly
			System.out.print("Successfully saved your position!\n");
		} 
		//trips if something other than a string comes through
		catch (IOException e) {
			System.out.print("Unable to save your location. Please try again later");
		}
	}
}
