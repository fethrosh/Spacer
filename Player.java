package Spacer;

//import java.util.Random;

public class Player {
	//declarations and inicializations
	private int health,sheild;
	private int[] money= {0,0,0}, materialAmount= {0,0};
	private String name;
	position p = new position();
	private String[] materials= {"Iron","Gold"};
	//private Random ran = new Random();
	public final int materialLength = materialAmount.length;
	
	//constructor. Basically what it does is create an instatnce of the class that can be called.
	public Player() {
		health= 100;
		sheild = 100;
		money[0]=10;
		name = "Default";
		p.setCurrentLocation("base");
	}
	
	//returns the name
	public String getName() {
		return name;
	}
	
	//sets the name of the player
	public void setName( String n) {
		name = n;
	}
	
	//returns the health value remaining
	public int gethealth() {
		return health;
	}
	
	//sets the health value
	public void sethealth(int h) {
		health=h;
	}
	
	//returen the amount of sheild remaining
	public int getsheild() {
		return sheild;
	}
	
	//sets the sheild points
	public void setsheild(int s) {
		sheild=s;
	}
	
	//returns the amount of money that the player has
	public int getmoney(int denomination) {
		return money[denomination];
	}
	
	//returns the full array of money
	//tbh, I don't remember why I did this...
	public int[] getmoneyArray() {
		return money;
	}
	
	//adds money to the bronze section of the array and then checks through the denominations
	//to make sure there are no more than 100 coins in anything that isn't gold
	// ***subject to change***
	public void gainmoney(int m) {
		money[0]+=m;
		if (money[0]>=100) {
			money[1]+=1;
			money[0]= money[0]-100;
		}
		if (money[1]>=100) {
			money[2]+=1;
			money[1]=money[1]-100;
		}
	}
	
	//sets the value of the denominations given to the amount given
	public void setmoney(int denomination, int amount) {
		money[denomination] = amount;
	}
	
	//gets the name of the material in the from the array
	public String getMaterialName(int id) {
		return materials[id];
	}
	
	//sets the selected material to the specified amount
	public void setMaterialName(int id, String name) {
		materials[id]=name;
	}
	
	//returns the entire array of materials
	public int[] getCargo() {
		return materialAmount;
	}
	
	//returns the amount of material of the selected type
	public int getMaterialAmount(int id) {
		return materialAmount[id];
	}
	
	//sets the selected material to the given amount
	public void setMaterialAmount(int id, int amount) {
		materialAmount[id] = amount;
	}
}