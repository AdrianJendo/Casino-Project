
public class ExpertRoulettePlayer extends RoulettePlayer{

	public ExpertRoulettePlayer(String newName, int newMoney) {
		money = newMoney;
		name = newName;
	}
	
	public int colourNum() {
		System.out.println("Would you like to play colours or numbers? ");
		String response = scan2.nextLine();
		int numResponse = 0;
		if (response.substring(0, 1).equalsIgnoreCase("c")) numResponse = 1;
		else if (response.substring(0, 1).equalsIgnoreCase("n")) numResponse = 2;
		return numResponse;
	}
	
	public String redBlack() {
		System.out.println("Red or Black?");
		String input = scan2.nextLine();
		String response = "";
		if (input.substring(0, 1).equalsIgnoreCase("r")) response = "red";
		else if (input.substring(0, 1).equalsIgnoreCase("b")) response = "black";
		return response;
	}
	
	public int rouletteNum() {
		return (int)(Math.random() * 2) + 1;
	}
	
	public void expertRoulette() {
		int numsOrColours = colourNum();
		if (numsOrColours == 1) {
			makeBet();
			String rouletteColour = "";
			String colour = redBlack();
			int rouletteNum = rouletteNum();
			if (rouletteNum == 1) {
				rouletteColour = "black";
			}
			else rouletteColour = "red";
			System.out.println("You picked " + colour + ".");
			enter();
			System.out.println("The thing landed on " + rouletteColour + ".");
			if (colour.equals(rouletteColour)) {
				win(bet);
			}else {
				lose(bet);
			}	
			if (debt>0 && money >0) {
				payDebt();
			}
		}else {
			simpleRoulette();
		}
	}
}
