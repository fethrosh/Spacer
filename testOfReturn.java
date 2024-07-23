public class testOfReturn{

	public static void main(String[] args){
		System.out.print("Testing scrolling load");
		String[] arr = {"["," "," "," "," "," ","]"};
		for (int i = 1; i<arr.length()-1;i++){
			for (int j=0; j<arr.length(); j++){
				System.out.print(arr[j]);
			}
			arr[i] = "=";
			System.out.print("\r");
			Thread.sleep(5000);
		}
	}

}
