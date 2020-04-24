import java.util.Scanner;

public class CasinoRunner {

	public static void main(String[] args) {

		System.out.println("This is Casino. Welcome");
		
		CasinoPlayer player2 = new CasinoPlayer();
		
		System.out.print("What is your name? ");
		String name = player2.enterString();
		
		boolean c = true;
		int money = 0;
		
		
		System.out.print("How much would you like to bring to the casino? (minimum $100) ");
		money = player2.enterInt(100, Integer.MAX_VALUE);
	
	
		CasinoPlayer.money = money;
	
		
		boolean blackJack = false;
		boolean roulette = false;
		boolean lottery = false;
		boolean expertRoulette = false;
		boolean lotteryAddict = false;
		
		
		while (CasinoPlayer.play) {
			System.out.println();
			
			System.out.print("What would you like to play. \nYou can play either Roulette, Lottery, or Blackjack. ");
			String game = player2.selectString("Roulette", "Lottery", "BlackJack");
			
			if (game.substring(0, 1).equalsIgnoreCase("r")) 
				roulette = true;
			else if (game.substring(0, 1).equalsIgnoreCase("l")) 
				lottery = true;
			else
				blackJack = true;
			
			if (blackJack) {
				BlackJack player = new BlackJack(name, CasinoPlayer.money);
				System.out.println("Welcome to blackjack " + player + ".");
				player.playBlackJack();
				blackJack = false;
			}
			else if (roulette) {
				System.out.print("Wecome to roulette. Are you an expert roulette player? ");
				String yesNo = player2.selectString("Yes", "No");
				
				if (yesNo.substring(0, 1).equalsIgnoreCase("y")) 
					expertRoulette = true;
				else 
					expertRoulette = false;
				
				if(expertRoulette) {
					ExpertRoulettePlayer player = new ExpertRoulettePlayer(name, CasinoPlayer.money);
					boolean keepPlaying = true;
					while (keepPlaying) {
						player.expertRoulette();
						keepPlaying = player.keepPlaying();
					}
				} else {
					RoulettePlayer player = new RoulettePlayer(name, CasinoPlayer.money);
					boolean keepPlaying = true;
					while (keepPlaying) {
						player.simpleRoulette();
						keepPlaying = player.keepPlaying();
					}
				}
				roulette = false;
				
				
			} else if (lottery) {
				System.out.print("Wecome to lottery. Are you an addicted lottery player? ");
				String yesNo = player2.selectString("Yes", "No");
				
				if (yesNo.substring(0, 1).equalsIgnoreCase("y")) 
					lotteryAddict = true;
				else 
					lotteryAddict = false;
				
				if (lotteryAddict) {
					boolean keepPlaying = true;
					LotteryAddictPlayer player = new LotteryAddictPlayer(name, CasinoPlayer.money);
					while (keepPlaying) {
						player.advancedLottery();
						keepPlaying = player.keepPlaying();
					}
				}else {
					boolean keepPlaying = true;
					LotteryPlayer player = new LotteryPlayer();
					while (keepPlaying) {
						player.playLottery();
						keepPlaying = player.keepPlaying();
					}
					
				}
				lottery = false;
			}
			
			player2.leaveCasino();
		}
		
		
		System.out.println("Thanks for coming " + name + ".");
		CasinoPlayer.stats();	
	}

}
