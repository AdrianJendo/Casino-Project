
public class RoulettePlayer extends CasinoPlayer{

	public RoulettePlayer() {
	
	}
	
	public RoulettePlayer(String newName, int newMoney) {
		money = newMoney;
		name = newName;
	}
	
	public int numRoulette() {
		System.out.println("Pick a number 1-36: ");
		return scan.nextInt();
	}
	
	public int rouletteNum() {
		return (int)(Math.random() * 36) + 1;
	}
	
	public void simpleRoulette() {
		makeBet();
		int num = numRoulette();
		int rouletteNum = rouletteNum();
		if (num<=12 && rouletteNum<=12) {
			System.out.println("You picked " + num + " and the computer picked " + rouletteNum + ".");
			win(bet);
		}
		else if (num<=24 && rouletteNum<=24 && rouletteNum>12 && num>12) {
			System.out.println("You picked " + num + " and the computer picked " + rouletteNum + ".");
			win(bet);
		}
		else if (num<=36 && rouletteNum<=36 && rouletteNum>24 && num>24) {
			System.out.println("You picked " + num + " and the computer picked " + rouletteNum + ".");
			win(bet);
		}
		else {
			System.out.println("You picked " + num + " and the computer picked " + rouletteNum + ".");
			lose(bet);
		}
		if (debt>0 && money >0) {
			payDebt();
		}
	}
	
	//1-12, 13-24, 25-36
	//red black
	
	
	
}
