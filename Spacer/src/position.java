public class position {
	private String currentLocation;
	private static String[] system_Name= {"alpha","beta","gamma","delta","epsilon","zeta"};
	public boolean [] active_Systems= {false,false,false,false,false,false};
	public static final int systemLength = system_Name.length;

	//constructor
	public position() {
		active_Systems[0]=true;
		currentLocation="base";
	}

	//returns the current position of the player
	public String getCurrentPosition() {
		return currentLocation;
	}

	//sets the location to that provided
	public void setCurrentLocation(String location) {
		currentLocation=location;
	}

	//sets the system open or closed
	public void setSys(int number, boolean active){
		active_Systems[number] = active;
	}

	//gets whether the system is active or not
	public boolean getsys(int number){
		return active_Systems[number];
	}

	//reads the system's name
	public String getSysName(int i) {
		return system_Name[i];
	}
}
