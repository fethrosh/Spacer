import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class story{

	private static final String shutDown = "Occupant #30218735 confirmed alive. Errors acknowledged. Pod shutting down...";
	private static Scanner fileReader;
	private static final String ANSI_RESET = "\u001B[0m",
			ANSI_GREEN = "\u001B[32m",ANSI_RED = "\u001B[31m",
			ANSI_YELLOW = "\u001B[33m",ANSI_BLUE = "\u001B[34m",
			ANSI_PURPLE = "\u001B[35m",ANSI_CYAN = "\u001B[36m",
			ANSI_WHITE = "\u001B[37m";
	private static final String BOLD="\u001b[1m",UNDER="\u001b[4m";
/*
	public static void main(String[] args) {
		int end=0;
		try {
				fileReader = new Scanner(errorText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		while (end!=1) {
			//System.out.print(fileReader.next()+" ");
			end = fprint(fileReader.next());
		}
	}
*/
	public void choose_story(String story){
		switch (story){
		case "Intro":
			fileChoose("error.txt");
		}
	}

	private static int fileChoose(String fileLocation){
		int end=0;
		try{
			fileReader = new Scanner(new File(fileLocation));
		} catch (Exception e){
			System.out.print("unable to start story");
			return 0;
		}
		while (end!=1) {
			try {
				String s = fileReader.nextLine();
				String[] line = s.split(" ");
				switch (line[0]) {
					case "Scene1":
						end = fprint(line[1]);
						break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	private static int fprint(String item) throws InterruptedException, IOException {
		int i = 0;
		while (i<item.length()-1) {
			switch (item.charAt(i)) {
				case '^':
					i++;
					switch(item.charAt(i)) {
						case '0':
							System.out.print("\n\nPress enter to exit...");
							System.in.read();
							return 1;
						case 'n':
							System.out.print("\n");
							i++;
							break;
						case 't':
							System.out.print("\t");
							i++;
							break;
						case 'R':
							System.out.print(ANSI_RED);
							i++;
							break;
						case 'G':
							System.out.print(ANSI_GREEN);
							i++;
							break;
						case 'Y':
							System.out.print(ANSI_YELLOW);
							i++;
							break;
						case 'C':
							System.out.print(ANSI_CYAN);
							i++;
							break;
						case 'B':
							System.out.print(ANSI_BLUE);
							i++;
							break;
						case 'P':
							System.out.print(ANSI_PURPLE);
							i++;
							break;
						case 'W':
							System.out.print(ANSI_WHITE);
							i++;
							break;
						case 'r':
							System.out.print(ANSI_RESET);
							i++;
							break;
					}
					break;
				case '|':
					System.out.print(" ");
					i++;
			default:
				System.out.print(item.charAt(i));
				Thread.sleep(10);
				i++;
				break;
				}
		}
		return 0;
	}
}
