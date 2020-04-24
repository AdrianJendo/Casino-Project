
public class LotteryAddictPlayer extends LotteryPlayer {

	public LotteryAddictPlayer(String newName, int newMoney) {
		money = newMoney;
		name = newName;
	}
	
	public String random2() {
		int random2 = (int)(Math.random() * (2)) + 1;
		if (random2 == 1) {
			System.out.println("Coin landed heads");
			return "heads";
		}
		
		System.out.println("Coin landed tails");
		return "tails";
	}
	
	public int [] random3() {
		int [] random3 = new int [3];
		for (int i = 0; i<3; i++) {
			random3[i] = (int)(Math.random() * (3)) + 1;
		}
		return random3;
	}
	
	public void advancedLottery() {
		System.out.print("What kind of betting game would you like to play? Type \"coinflip\", \"3 numbers\", \"or lottery\". ");
		String response = selectString("coinflip","3 nums","lottery");
		int type = 0;
		if (response.substring(0, 1).equalsIgnoreCase("c")) type = 1;
		if (response.substring(0, 1).equalsIgnoreCase("3")) type = 3;
		if (response.substring(0, 1).equalsIgnoreCase("l")) type = 2;
		
		int winnings = 0;
		
		
		if (type == 1) {
			
			if (!knowHowPlay()) {
				System.out.println("Choose heads or tails. If you pick the correct side of the coin, you win!");
				enter();
			}
			
			makeBet();
				
			
			System.out.print("Heads or tails? ");
			String user = selectString("Heads", "Tails");
			String random = random2();
			if (random.substring(0, 1).equalsIgnoreCase(user.substring(0,1))) {
				win(bet);
			} else 
				lose(bet);
			
			if (debt>0 && money >0) {
				payDebt();
			}
		} else if (type == 2) {
			playLottery();
		}else {
			if (!knowHowPlay()) {
				System.out.println("Pick three number. For every number that matches with the computer's number, you win!");
				enter();
			}
			
			makeBet();
			
			System.out.println("Pick 3 numbers 1-3: ");
			int [] user = new int[3];
			
			for (int i = 0; i<3; i++) {
				System.out.print("Select number " + (i+1) + ": ");
				user[i] = enterInt(1,3);
			}
			
			System.out.print("You picked: ");
			for (int i = 0; i<3; i++) 
				System.out.print(user[i] + ", ");
			
			
			System.out.println();
			int [] random = random3();
			System.out.print("The computer numbers are: ");
			for (int i = 0; i<3; i++) 
				System.out.print(random[i] + ", ");
			
			System.out.println();
			
			for (int i = 0; i<3; i++) {
				if (user[i] == random[i]) {
					winnings += bet;
				}
			}
			
			if (winnings > 0) 
				win(winnings);
			else 
				lose(bet);
			
			if (debt>0 && money >0) {
				payDebt();
			}
			
		}
	}	
}
