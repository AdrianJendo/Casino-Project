import java.util.Scanner;
public class CasinoPlayer extends cardGenerator{

	boolean playAgain = true;
	boolean makeBet = true;
	static int money;
	int bet = 0;
	static public String name;
	static int totalBet = 0;
	static int totalWin = 0;
	static int totalLoss = 0;
	static int totalBorrow = 0;
	static int debt = 0;
	public static boolean play = true;
	
	boolean playAtAll = true;
	
	Scanner scan = new Scanner(System.in);
	Scanner scan2 = new Scanner(System.in);
	
	public CasinoPlayer() {
	}
	
	public CasinoPlayer(String newName, int newMoney) {
		money = newMoney;
		name = newName;
	}
	
	public void readyPlay() {
		System.out.print("R u ready to play? ");
		String ready2Play = scan.nextLine();
		if ((ready2Play.substring(0,1)).equalsIgnoreCase("n")) {
			System.out.print("Too bad");
			playAgain = false;
			playAtAll = false;
		}
		
		if (playAtAll) System.out.print("You have $" + money + " to bet. ");
	}
	
	public void makeBet() { 
		while (makeBet) {
			System.out.print("How much would you like to bet? ");
			bet = scan.nextInt();
			if (bet>money) {
				System.out.println("Sorry, you don't have that much to bet. You currently have $" + money + ".");
				borrow();
			}
			else if (bet <= 0)
				System.out.println("Please enter valid number next time");
			else {
				makeBet = false;
				System.out.print("You bet $" + bet + ".");
				totalBet+=bet;
			}
	}
		makeBet = true;
	}
	
	
	public void enter() {
		System.out.println("Press 'enter' to continue. ");
		scan2.nextLine();
	}
	
	public void lose(int loss) {
		System.out.println("You lost $" + loss + "! ");
		money-=loss;
		totalLoss += loss;
		System.out.println("You currently have $" + money + ".");
	}
	
	public void win(int win) {
		System.out.println("You won $" + win + "! ");
		money+=win;
		totalWin += win;
		System.out.println("You currently have $" + money + ".");
	}
	
	
	public String toString() {
		return name;
	}
	
	public static void stats() {
		System.out.println("You ended with $" + money + "." );
		System.out.println("You bet a total of $" + totalBet + "." );
		System.out.println("You won a total of $" + totalWin + ".");
		System.out.println("You lost a total of $" + totalLoss + ".");
		System.out.println("You borrwed a total of $" + totalBorrow + ".");
		if (debt > 0) {
			System.out.println("You have $" + debt + " of unpaid debt...");
		}
	}
	
	public boolean keepPlaying(){
		System.out.print("Would you like to keep playing? ");
		String yesNo = scan2.nextLine();
		if (yesNo.substring(0, 1).equalsIgnoreCase("y")) return true;
		return false;
	}
	
	public void leaveCasino() {
		System.out.print("Would you like to leave the casino? ");
		String yesNo = scan2.nextLine();
		if (yesNo.substring(0, 1).equalsIgnoreCase("y")) {
			play = false;
		}
	}
	
	public void borrow() {
		System.out.print("Would you like to borrow from the bank? Type '1' for yes and '2' for no: ");
		int yesNo = scan.nextInt();
		if (yesNo == 1) {
			System.out.print("How much would you like to take? Enter amount: ");
			int withdrawal = scan.nextInt();
			debt += withdrawal;
			money+=withdrawal;
			totalBorrow+=withdrawal;
			System.out.println("You currently have $" + money + " and your debt is $" + debt + ".");
		}
		else System.out.println("No money will be borrowed from the bank");
	}
	
	public void payDebt() {
		System.out.println("Your debt is currently $" + debt + ". You also currently have $" + money + ".");
		System.out.print("Would you like to pay some or all of your debt? Type '1' for yes and '2' for no: ");
		int yesNo = scan.nextInt();
		boolean pay = true;
		if (yesNo == 1) {
			while (pay) {
			System.out.print("How much would you like to pay back? Enter amount: ");
			int returns = scan.nextInt();
			if (returns<money && debt<=money) {
				debt -= returns;
				money-=returns;
				pay = false;
			}else System.out.println("Not valid input. Try again.");	
			}
		}
		else System.out.println("No money will be returned to the bank");
	}


}
	
