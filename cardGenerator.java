
import java.util.Scanner;

public class cardGenerator {
	
	//Note this card generator is only for blackjack; hence, cards are assigned their relative blackjack values
	
	static Scanner scan = new Scanner(System.in);
	
	public static int card;
	
	
	static int[][] cards = {{1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1}	
				};
	
	
	public static void cardDraw() {

		if (cardsRemain()) {
			
			//suit of card in blackjack doesnt matter. So only consider columns which represent number
			int col = 0;
			int row = 0;
			
			col = (int)(Math.random() * (12-0+1)) + 0;
			
			
			if (cards[row][col] == 0) {
				int count = 0;
				while(cards[row][col]==0 && count<52){
					row++;
					if (row>3) {
						row=0;
						if (col == 12)
							col=0;
						else
							col++;
					}
				}
				if (count>=52) {
					System.out.println("ERROR IN CARD GENERATION!");
				}

			}
			
			if (col>=9)
				card=10;
			else
				card=col+1;
			
			cards[row][col] = 0;
		}else {
			outOfCards();
			cardDraw();
		}
	}
	
	
	public static void resetCards() {
		for (int row = 0; row < cards.length; row++) {
			for (int col = 0; col<cards[0].length; col++) {
				cards[row][col]=1;
			}
		}
	}

	
	public static void outOfCards() {
		System.out.println("\nNo more cards, resetting");
		for (int i = 0; i<3; i++)
			System.out.println(".");
		System.out.println("Reset\n");
		resetCards();
	}
	
	public static boolean cardsRemain() {
		for (int row = 0; row < cards.length; row++) {
			for (int col = 0; col<cards[0].length; col++) {
				if (cards[row][col]==1)
					return true;
			}
		}
		
		return false; 
	}
	

}
