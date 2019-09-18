
public class LotteryAddictPlayer extends LotteryPlayer {

	public LotteryAddictPlayer(String newName, int newMoney) {
		money = newMoney;
		name = newName;
	}
	
	public int [] random2() {
		int [] random2 = {(int)(Math.random() * (2)) + 1};
		if (random2[0] == 1) System.out.println("Coin landed heads");
		else System.out.println("Coin landed tails");
		return random2;
	}
	
	public int [] random3() {
		int [] random3 = new int [3];
		for (int i = 0; i<3; i++) {
			random3[i] = (int)(Math.random() * (3)) + 1;
		}
		return random3;
	}
	
	public void advancedLottery() {
		System.out.println("What kind of betting game would you like to play? Type \"coinflip\", \"threenumbers\", \"or 649\".");
		String response = scan2.nextLine();
		int type = 0;
		if (response.substring(0, 1).equalsIgnoreCase("c")) type = 1;
		if (response.substring(0, 1).equalsIgnoreCase("t")) type = 3;
		if (response.substring(0, 1).equalsIgnoreCase("6")) type = 2;
		
		int winnings = 0;
		
		if (type == 1 || type == 3) makeBet();
		
		if (type == 1) {
			System.out.println("Heads or tails? \"1\" for heads, \"2\" for tails.");
			int [] user = {scan.nextInt()};
			int [] random = random2();
			if (random[0] == user[0]) {
				win(bet);
			}
			else lose(bet);
			if (debt>0 && money >0) {
				payDebt();
			}
		}else if (type == 2) {
			playLottery();
		}else {
			System.out.println("Pick 3 numbers 1-3: ");
			int [] user = new int[3];
			for (int i = 0; i<3; i++) {
				user[i] = scan.nextInt();
			}
			System.out.print("You picked: ");
			for (int i = 0; i<3; i++) {
				System.out.print(user[i] + ", ");
			}
			System.out.println();
			int [] random = random3();
			System.out.print("The numbers are: ");
			for (int i = 0; i<3; i++) {
				System.out.print(random[i] + ", ");
			}
			System.out.println();
			for (int i = 0; i<3; i++) {
				if (user[i] == random[i]) {
					winnings += bet;
				}
			}
			if (winnings > 0) win(winnings);
			else lose(bet);
			if (debt>0 && money >0) {
				payDebt();
			}
		}
	}

	
	
	
}
