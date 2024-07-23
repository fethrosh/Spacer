import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main{
	//Setup of necessary files and modules
	private static File file = new File ("save.txt"),manifest = new File ("manifest.txt");
	private static Scanner kb = new Scanner(System.in);
	private static Player p1 = new Player();
	private static position pos = new position();
	private static story s = new story();
	private static Store store = new Store();

	//Choices and main method
	public static void main(String[] args) throws IOException, InterruptedException {
		boolean end = false;
		int in=0;
		if (file.exists()) {
			System.out.println("Welcome back, traveler. How can I help you?");
			while (in!= 1) {
				in = options();
			}
		} else {
			file.createNewFile();
			System.out.println("Welcome to the system, traveler. What brings you here?");
			options();
		}
		while (end!=true) {
			System.out.printf("%n%s%n(%d)%-20s%n(%d)%-20s%n(%d)%-20s%n>","Control Panel",1,"Jump to system",2,"Check cargo",3,"Quit Game");
			int choice = kb.nextInt();
			switch (choice){
				case 1:
					systems();
					break;
				case 2:
					System.out.print("\nPulling up the list of cargo in our hold. Pease wait...\n\n");
					showCargo();
					break;
				case 3:
					System.out.println("\nSaving your position...\n");
					IODriver.savePosition(pos);
					System.out.println("\nSaving information...\n");
					IODriver.saver(p1.gethealth(), p1.getsheild(), p1.getmoneyArray(), p1.getCargo(), p1, pos, file);
					System.out.print("\nSee you later, Commander! o7");
					end = true;
			}
		}
	}

	//Checks and prints out the contents of the manifest file
	public static void cargo() throws FileNotFoundException, InterruptedException {
		if (manifest.exists()) {
			Scanner read = new Scanner(manifest);
			int id = 0;
			while(read.hasNextLine()) {
				p1.setMaterialAmount(id, Integer.parseInt(read.nextLine()));
				id+=1;
			}
			read.close();
		} else {
			try {
				manifest.createNewFile();
			} catch (IOException e) {
				System.out.print("Apologies Captain! We are unable to locate or create a new manifest document. Please try again later!");
			}
		}
	}

	private static void showCargo() throws IOException {
		System.out.print("This is what we have in our manifest:\n");
		for (int i=0;i<p1.materialLength;i++) {
			System.out.printf("%-10s%d units%n", p1.getMaterialName(i),p1.getMaterialAmount(i));
		}
		System.out.print("\n\nPress enter to exit...");
		System.in.read();;
	}

	//shows the choices that one has in the current location
	private static int options() throws IOException{
		int[] cargo = new int[1];
		System.out.printf("%-10s(1)%n%-10s(2)%n%-10s(3)%n%-10s(4)%n", "Continue","New Game","Options","Credits");
		int ans = kb.nextInt();
		switch(ans) {
		case 1:
			System.out.print("Loading...\n");
			s.choose_story("Intro");
			try {
				return IODriver.Loader(p1,pos);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		case 2:
			IODriver.saver(p1.gethealth(),p1.getsheild(),p1.getmoneyArray(),cargo, p1, pos, file);
			return 1;
		case 3:
			settings();
			return 0;
		case 4:
			System.out.println("Copyright: 2022\n Yakov Koshavenko\n\n");
			return 0;
		default:
			System.out.print("Input not recognized");
			ans = kb.nextInt();
			return 0;
		}
	}

	//The settings that are available to the player
	private static void settings() {
		System.out.println("Showing settings");
	}


	//Displays and changes the players current location
	private static String systems(){
		String system="";
		int count = 0;
		List<String> systems = new ArrayList<String>();
		for (int i = 0;i<pos.active_Systems.length;i++) {
			if(pos.getsys(i)) {
				systems.add(pos.getSysName(i));
			}
		}
		for (int i = 0;i<pos.active_Systems.length;i++) {
			if(pos.getsys(i)&&!pos.getSysName(i).equals(pos.getCurrentPosition())) {
				count+=1;
			}
		}
		if (count!=0) {
			System.out.print("\nYou are currently located at "+pos.getCurrentPosition()+
					"\n\nThese are the systems that are availible for you to jump to:\n");
			for (int i = 0;i<pos.active_Systems.length;i++) {
				if(pos.getsys(i)==true&&!pos.getSysName(i).equals(pos.getCurrentPosition())) {
					System.out.print(pos.getSysName(i)+"\n");
				}
			}
			System.out.print("\nWhere do you wish to travel?\n>");
			kb.nextLine();
			system = kb.nextLine();
			while (!systems.contains(system)) {
				System.out.print("Unknown system. Please choose again\n>");
				system = kb.nextLine();
			}
			String reloc = "Relocating";
			pos.setCurrentLocation(system);
			for (int i = 0; i<4; i++){
				System.out.print("\r" + reloc);
				reloc+= ".";
				try{
					Thread.sleep(1000);
				} catch (Exception E) {
					System.out.print("Error");
				}
			}
			System.out.print("\nYou are now located at "+pos.getCurrentPosition()+"\n");
		} else {
			System.out.print("\nThere are no open systems for you to jump to\n");
		}
		return system;
	}
}
