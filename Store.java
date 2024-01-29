package Spacer;

public class Store {
	public int rep;
	
	//prints out the greeting for the store
	public void greet(String position) {
		System.out.print("Welcome to the market of the "+position.toUpperCase()+" System");
		chooseMarket(position);
	}
	
	//Chooses the market to put the player into 
	public void chooseMarket(String position) {
		switch (position) {
		case "alpha":
			break;
		case "beta":
			break;
		case "gamma":
			break;
		case "delta":
			break;
		case "epsilon":
			break;
		case "zeta":
			break;
		}
	}
}