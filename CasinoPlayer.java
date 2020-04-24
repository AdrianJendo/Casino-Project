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
		System.out.print("Are you ready to play? ");
		String ready2Play = selectString("Yes", "No");
		if ((ready2Play.substring(0,1)).equalsIgnoreCase("n")) {
			System.out.println("Too bad");
			playAgain = false;
			playAtAll = false;
		}
		
		//if (playAtAll) 
		//	System.out.print("You have $" + money + " to bet. ");
	}
	
	public void makeBet() {
		System.out.print("You currently have $" + money + ". How much would you like to bet? ");
		
		bet = enterInt(1, money);
		
		System.out.print("You bet $" + bet + ". ");
		totalBet+=bet;
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
		System.out.println("You borrowed a total of $" + totalBorrow + ".");
		if (debt > 0) {
			System.out.println("You have $" + debt + " of unpaid debt...");
		}
	}
	
	public boolean keepPlaying(){
		
		if (money<=0) {
			System.out.println("You are currently out of money. If you would like to keep playing, you need to borrow some money.");
		}
		
		System.out.print("Would you like to keep playing? ");
		String yesNo = selectString("Yes", "No");
		
		
		if (yesNo.substring(0, 1).equalsIgnoreCase("y")) {
			if (money<=0)
				return borrow();
			
			return true;
		}
			
		return false;
	}
	
	public void leaveCasino() {
		
		if (money<=0) 
			System.out.print("You are currently out of money. ");
		
		System.out.print("Would you like to leave the casino? ");
		String yesNo = selectString("Yes", "No");
		if (yesNo.substring(0, 1).equalsIgnoreCase("y")) {
			play = false;
		} else if (money<=0) {
			play = borrow();
		}
	}
	
	public boolean borrow() {
		System.out.print("Would you like to borrow from the bank? ");
		String yesNo = selectString("Yes", "No");
		if (yesNo.substring(0,1).equalsIgnoreCase("y")) {
			System.out.print("How much would you like to take? Enter amount (max amount is 100 000): ");
			int withdrawal = enterInt(0,100000);
			debt += withdrawal;
			money+=withdrawal;
			totalBorrow+=withdrawal;
			System.out.println("You currently have $" + money + " and your debt is $" + debt + ".");
			return true;
		}
		else {
			System.out.print("No money will be borrowed from the bank. ");
			System.out.println("Unfortunately, you will have to leave. ");
			return false;
			
		}
	}
	
	public void payDebt() {
		System.out.println("Your debt is currently $" + debt + ". You also currently have $" + money + ".");
		System.out.print("Would you like to pay some or all of your debt? ");
		String yesNo = selectString("Yes", "No");
		
		if (yesNo.substring(0,1).equalsIgnoreCase("y")) {

			System.out.print("How much would you like to pay back? Enter amount: ");
			int maxPayable = Math.min(debt, money);
			int payOff = enterInt(1, maxPayable);
			
			debt -= payOff;
			money-= payOff;
			System.out.println("Your remaining debt is $" + debt + " and you currently have $" + money + " available");
		}
		else 
			System.out.println("No money will be returned to the bank");
	}
	
	public boolean knowHowPlay() {
		
		String answer;
		System.out.print("Do you know how to play? ");
		answer = selectString("Yes", "No");
		System.out.println();
		if (answer.substring(0, 1).equalsIgnoreCase("n"))
			return false;
	
		return true;
		
	}
	
	public void enter() {
		System.out.print("Press 'enter' to continue. ");
		scan2.nextLine();
		System.out.println();
	}
	
	public int enterInt(int minBoundry, int maxBoundry) {
		int num = 0;
		int count = 0;
		while(num<minBoundry || num>maxBoundry||count==0) {
			if (count>0) {
				System.out.print("Please enter a valid value: ");
			}
			count++;
			num = scan.nextInt();
		}
		
		return num;
	}
	
	public int selectValue(int option1, int option2) {
		int num = 0;
		int count = 0;
		while((num!=option1 && num!=option2) ||count==0) {
			if (count>0) {
				System.out.print("Please enter a valid value: ");
			}
			count++;
			num = scan.nextInt();
		}
		
		return num;
	}
	
	public String enterString() {
		String response = "";
		int count = 0;
		while(response.equals("")) {
			if (count>0)
				System.out.print("Please enter a valid response: ");
			response = scan2.nextLine();
			count++;
		}
		
		return response;
		
	}
	
	public String selectString(String option1, String option2) {
		String response = "";
		int count = 0;
		while(response.equals("") || !(response.substring(0,1).equalsIgnoreCase(option1.substring(0,1)) || response.substring(0,1).equalsIgnoreCase(option2.substring(0,1)))) {
			if (count>0)
				System.out.print("Please enter a valid response: ");
			response = scan2.nextLine();
			count++;
		}
		
		return response;
	}
	
	public String selectString(String option1, String option2, String option3) {
		String response = "";
		int count = 0;
		while(response.equals("") || !(response.substring(0,1).equalsIgnoreCase(option1.substring(0,1)) || response.substring(0,1).equalsIgnoreCase(option2.substring(0,1)) || response.substring(0,1).equalsIgnoreCase(option3.substring(0,1)))) {
			if (count>0)
				System.out.print("Please enter a valid response: ");
			response = scan2.nextLine();
			count++;
		}
		
		return response;
	}


}
	
