import java.util.Scanner;
public class CasinoTest extends cardGenerator{

	public static void main(String[] args) {
		
		int card = 0;
		
		Scanner scan2 = new Scanner(System.in);
		
		System.out.print("Are you ready? ");
		String name = scan2.nextLine();
		
		while(name.equals("") || name.substring(0, 1).equalsIgnoreCase("y")) {
			cardDraw();
			card = cardGenerator.card;
			
			System.out.println("You drew the value: " + card);
			
			System.out.println("Go again?");
			name = scan2.nextLine();
		}
		
		
		
	}

}
