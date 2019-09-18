
import java.util.Scanner;

public class cardGenerator {
	
	static int ace = 4;
	static int two = 4;
	static int three = 4;
	static int four = 4;
	static int five = 4;
	static int six = 4;
	static int seven = 4;
	static int eight = 4;
	static int nine = 4;
	static int ten = 4;
	static int jack = 4;
	static int queen = 4;
	static int king = 4;
	
	static Scanner scan = new Scanner(System.in);
	
	
	public static int card;
	public static void cardDraw() {
		int sum = ace+two+three+four+five+six+seven+eight+nine+ten+jack+queen+king;
		if (sum > 1) {
		int drawnCard = (int)(Math.random()*(sum) + 1);
		if (drawnCard <= ace) {
			card = 1;
			ace--;
		}else if (drawnCard<= ace+two) {
			card = 2;
			two--;
		}else if (drawnCard<= ace+two+three) {
			card = 3;
			three--;
		}else if (drawnCard<= ace+two+three+four) {
			card = 4;
			four--;
		}else if (drawnCard<= ace+two+three+four+five) {
			card = 5;
			five--;
		}else if (drawnCard<= ace+two+three+four+five+six) {
			card = 6;
			six--;
		}else if (drawnCard<= ace+two+three+four+five+six+seven) {
			card = 7;
			seven--;
		}else if (drawnCard<= ace+two+three+four+five+six+seven+eight) {
			card = 8;
			eight--;
		}else if (drawnCard<= ace+two+three+four+five+six+seven+eight+nine) {
			card = 9;
			nine--;
		}else if (drawnCard<= ace+two+three+four+five+six+seven+eight+nine+ten) {
			card = 10;
			ten--;
		}else if (drawnCard<= ace+two+three+four+five+six+seven+eight+nine+ten+jack) {
			card = 11;
			jack--;
		}else if (drawnCard<= ace+two+three+four+five+six+seven+eight+nine+ten+jack+queen) {
			card = 12;
			queen--;
		}else if (drawnCard<= ace+two+three+four+five+six+seven+eight+nine+ten+jack+queen+king) {
			card = 13;
			king--;
		}
		}else if (sum == 1) {
			if (ace == 1) {
				card = 1;
				ace--;
			}
			else if (two == 1) {
				card = 2;
				two--;
			}
			else if (three == 1) {
				card = 3;
				three--;
			}
			else if (four == 1) {
				card = 4;
				four--;
			}
			else if (five == 1) {
				card = 5;
				five--;
			}
			else if (six == 1) {
				six--;
				card = 6;
			}
			else if (seven == 1) {
				seven--;
				card = 7;
			}
			else if (eight == 1) {
				eight--;
				card = 8;
			}
			else if (nine == 1) {
				nine--;
				card = 9;
			}
			else if (ten == 1) {
				ten--;
				card = 10;
			}
			else if (jack == 1) {
				jack--;
				card = 11;
			}
			else if (queen == 1) {
				queen--;
				card = 12;
			}
			else if (king == 1) {
				king--;
				card = 13;
			}
		}else {
			outOfCards();
			resetCards();
			cardDraw();
		}
	}
	
	
	public static void resetCards() {
		ace = 4;
		two = 4;
		three = 4;
		four = 4;
		five = 4;
		six = 4;
		seven = 4;
		eight = 4;
		nine = 4;
		ten = 4;
		jack = 4;
		queen = 4;
		king = 4;
	}
	
	public static int sum() {
		return ace+two+three+four+five+six+seven+eight+nine+ten+jack+queen+king;
	}
	
	public static void outOfCards() {
		System.out.println("No more cards");
	}

}
