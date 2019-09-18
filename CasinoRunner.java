import java.util.Scanner;

public class CasinoRunner {

	public static void main(String[] args) {

		System.out.println("This is Casino. Welcome");
		
		Scanner scan2 = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		
		System.out.print("What is your name? ");
		String name = scan2.nextLine();
		
		boolean c = true;
		int money = 0;
		while(money<=0) {
			System.out.print("How much would you like to bring to the casino? ");
			money = scan.nextInt();
		}
		
		CasinoPlayer.money = money;
		
		//CasinoPlayer player = new CasinoPlayer(name, money);
		
		
		boolean blackJack = false;
		boolean roulette = false;
		boolean lottery = false;
		boolean expertRoulette = false;
		boolean lotteryAddict = false;
		
		
		while (CasinoPlayer.play) {
			System.out.println();
			System.out.print("What would you like to play. \nYou can play either Roulette, Lottery, or Blackjack. ");
			String game = scan2.nextLine();
			
			if (game.substring(0, 1).equalsIgnoreCase("r")) roulette = true;
			if (game.substring(0, 1).equalsIgnoreCase("l")) lottery = true;
			if (game.substring(0, 1).equalsIgnoreCase("b")) blackJack = true;
			
			if (blackJack) {
				BlackJack player = new BlackJack(name, CasinoPlayer.money);
				System.out.println("Welcome to blackjack " + player + ".");
				player.blackJack();
				blackJack = false;
				player.leaveCasino();
			}
			else if (roulette) {
				System.out.print("Wecome to roulette. Are you an expert roulette player? ");
				String yesNo = scan2.nextLine();
				
				if (yesNo.substring(0, 1).equalsIgnoreCase("y")) expertRoulette = true;
				else expertRoulette = false;
				
				if(expertRoulette) {
					ExpertRoulettePlayer player = new ExpertRoulettePlayer(name, CasinoPlayer.money);
					boolean keepPlaying = true;
					while (keepPlaying) {
					player.expertRoulette();
					keepPlaying = player.keepPlaying();
					}
					player.leaveCasino();
				}else {
					RoulettePlayer player = new RoulettePlayer(name, CasinoPlayer.money);
					boolean keepPlaying = true;
					while (keepPlaying) {
					player.simpleRoulette();
					keepPlaying = player.keepPlaying();
					}
					player.leaveCasino();
				}
				roulette = false;
			}
			else if (lottery) {
				System.out.print("Wecome to lottery. Are you an addicted lottery player? ");
				String yesNo = scan2.nextLine();
				
				if (yesNo.substring(0, 1).equalsIgnoreCase("y")) lotteryAddict = true;
				else lotteryAddict = false;
				
				if (lotteryAddict) {
					boolean keepPlaying = true;
					LotteryAddictPlayer player = new LotteryAddictPlayer(name, CasinoPlayer.money);
					while (keepPlaying) {
					player.advancedLottery();
					keepPlaying = player.keepPlaying();
					}
					player.leaveCasino();
				}else {
					boolean keepPlaying = true;
					LotteryPlayer player = new LotteryPlayer();
					while (keepPlaying) {
					player.playLottery();
					keepPlaying = player.keepPlaying();
					}
					
					player.leaveCasino();
				}
				lottery = false;
			}
			
		
		}
		
		
		System.out.println("Thanks for coming " + name + ".");
		CasinoPlayer.stats();	
	}

}
