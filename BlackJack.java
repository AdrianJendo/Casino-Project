
public class BlackJack extends CasinoPlayer{
	
	public BlackJack(String newName, int newMoney) {
		money = newMoney;
		name = newName;
	}
	
	//note: this was initially coded before I had any knowledge of methods or arrays
	
	public void blackJack() {
		boolean ace1 = false;
		boolean ace2 = false;
		boolean keepPlaying = false;
		boolean stop = true;
		boolean ace = false;
		int card1Player = 0;
		int card2Player = 0;
		int card1House = 0;
		int card2House = 0;
		int sum = 0;
		int houseSum = 0;
		int yesNo = 0;
		
		boolean playerWin = true;
		boolean tie = false;
		
		readyPlay();
		
		//Bonus
		int count = 0;
		boolean doubleDown = false;
		boolean split = false;
		boolean keepPlaying2 = false;
		int sum2 = 0;
		int count2 = 0;
		int biggerSum = 0;
		
		while (playAgain) {
			count = 0;
			
			//make a bet
			makeBet();
			
			System.out.println();	
			
			//deal initial cards to player and house
			for (int i = 0; i<4; i++) {	
				cardDraw();
				if (card>10) {
					card = 10;
				}
				
				//for player
				if (i == 0 && card != 1) {
					card1Player = card;
					System.out.print("The player's cards are " + card1Player);
				}
				if (i == 0 && card == 1) {
					ace1 = true;
					card1Player = card;
					System.out.print("The player's cards are an ace");
				}
				if (i == 1 && card != 1) {
					card2Player = card;
					System.out.println(" and " + card2Player + ". ");
				}
				if (i == 1 && card == 1) {
					ace2 = true;
					card2Player = card;
					System.out.println(" and an ace. ");
				}
				
				//for house
				if (i == 2) {
					card1House = card;
				}
				if (i == 3) {
					card2House = card;
				}
				
				if (card1House == 1 || card2House == 1) {
					if (card1House == 1 && card2House == 1){
						card1House = 11;
					}
					else if (card1House == 1) card1House = 11;
					else if (card2House == 1) card2House = 11;
				}
			}
				//--------------- Cards have been dealt --------------------------------------
				
				//split
				split = false;
				if (card1Player == card2Player) {
					System.out.print("You have two of the same card. Would you like to split? Type '1' for yes and '2' for no ");
					yesNo = scan.nextInt();
					if (yesNo == 1) {
						split = true;
						keepPlaying = true;
						sum = card1Player;
						sum2 = card2Player;
						count = 1;
						count2 = 1;
					}
					else if (yesNo == 2) {
						split = false;
					}
				}
			
			
				//decide player ace (if they have ace)
				while (ace1) {
					System.out.print("Would you like to make your first card a 1 or an 11? ");
					card1Player = scan.nextInt();
					if (card1Player == 1 || card1Player == 11) {
						ace1 = false;
						if (split) sum = card1Player;
					}
					else System.out.println("Please enter a valid number. ");
				}
				
				while (ace2) {
					System.out.print("Would you like to make your second card a 1 or an 11? ");
					card2Player = scan.nextInt();
					if (card2Player == 1 || card2Player == 11) {
						ace2 = false;
						if (split) sum2 = card2Player;
					}
					else System.out.println("Please enter a valid number. ");
				}
				
				if (!split) {
				//sum player's cards
				sum = (card1Player + card2Player);
				System.out.println("Your sum is " + sum + ". ");
				}
				
				
				if (sum != 21)
				{
				//announce one of house's cards:
				System.out.println("One of the dealer's cards is " + card1House + ". The other is unknown.");
				
				if (bet*2 <= money) {
				System.out.println("Would you like to 'Double Down'? Type '1' for yes and '2' for no ");
				yesNo = scan.nextInt();
				if (yesNo == 1) {
					doubleDown = true;
				}
				else if (yesNo == 2) {
					doubleDown = false;
				}
				}
				else doubleDown = false;
				
				
				
				System.out.println();
				
				//doubleDown
				if (doubleDown) {
					keepPlaying = true;
					System.out.print("Your bet was $" + bet + ". ");
					bet *= 2;
					System.out.println("Now it is $" + bet + ". ");
					
					System.out.print("Press enter to continue");
					scan2.nextLine();
					System.out.println();
				}
				
				
				//keep playing 
				else if (!split && !doubleDown){
				System.out.print("Would you like to keep playing? Type '1' for yes and '2' for no ");
				yesNo = scan.nextInt();
				if (yesNo == 1) {
					keepPlaying = true;
					count = 2;	//two cards to start
				}
				else if (yesNo == 2) {
					keepPlaying = false;
					stop = true;
				}
				}
	
				while (keepPlaying && sum<21 && count!=5) {
					
					//draw the next card(s)
					cardDraw();
					if (card>10) {
						card = 10;
					}
					
					if (card != 1) 
						{
						System.out.println("You drew a " + card);
						sum += card;
						}
					if (card == 1) {
						ace = false;
						if (11 + sum <= 21) {
							ace = true;
							while (ace) {
							System.out.print("You drew an ace. Would you like to make your ace a 1 or an 11? ");
							card = scan.nextInt();
							if (card == 1 || card == 11) ace = false;
							else System.out.println("Please enter a valid number. ");
							}
							sum += card;
						}
						else{
							System.out.println("You drew an ace. Your current sum is " + sum + ", so I am making your ace a 1");
							sum += 1;
						}	
					}
					
					if (doubleDown) {
						System.out.println("Your final sum is " + sum + ".");
						if (sum<21) stop = true;
						else if (sum > 21) {
							stop = false;
							playerWin = false;
						}
						else if (sum == 21) {
							stop = false;
							playerWin = true;
							split = false;
						}
						keepPlaying = false;
					}
					
					else { //not doubleDown		
					//compare sum to 21 after drawing the next card
					if (sum<21) {
						count++;
						System.out.println("Your current sum is " + sum + ". ");
						if (count<5) {
						System.out.print("Would you like to draw again? Type '1' for yes and '2' for no"); //make all these while loops if time
						yesNo = scan.nextInt();
						if (yesNo == 1) keepPlaying = true;
						else if (yesNo == 2) {
							keepPlaying = false;
							stop = true;
						}
						}
						else if (count == 5) {
							playerWin = true;
							stop = false;
							keepPlaying = false;
							split = false;
						}
						else System.out.print("count is more than 5- something broke");
					}
					else if (sum == 21) {
						System.out.println("Your sum is exactly 21.");
						keepPlaying = false;
						stop = false;
						playerWin = true;
						split = false;
					}
					else if (sum > 21) {
						if (!split) {
							System.out.println("You went over 21. That means you lose :,(");
						keepPlaying = false;
						stop = false;
						playerWin = false;
						}
						if (split) {
							System.out.println("You went over 21. Let's play your second hand now.");
						}
					}
					}//double down else brace
				} //while loop closing brace
				
				if (split) {
						keepPlaying2 = true;
						count2 = 1;
						System.out.println("\nWe are now playing your second hand.");
					while (keepPlaying2 && sum2<21 && count2!=5) {
						
						//draw the next card(s)
						int card = (int)(Math.random() * 13) + 1;
						if (card == 1) card = 1;
						else if (card > 9) card = 10;
						else {
							card = (int)(Math.random() * (9-2+1)) + 2;
						}
						
						if (card != 1) 
							{
							System.out.println("You drew a " + card);
							sum2 += card;
							}
						if (card == 1) {
							ace = false;
							if (11 + sum2 <= 21) {
								ace = true;
								while (ace) {
								System.out.print("You drew an ace. Would you like to make your ace a 1 or an 11? ");
								card = scan.nextInt();
								if (card == 1 || card == 11) ace = false;
								else System.out.println("Please enter a valid number. ");
								}
								sum2 += card;
							}
							else{
								System.out.println("You drew an ace. Your current sum is " + sum2 + ", so I am making your ace a 1");
								sum2 += 1;
							}	
						}
						
						if (doubleDown) {
							System.out.println("Your sum is " + sum2 + ".");
							if (sum2<21) stop = true;
							else if (sum2 > 21 && sum > 21) {
									stop = false;
									playerWin = false;
							}
							else if (sum2 == 21) {
								stop = false;
								playerWin = true;
								split = false;
							}
							keepPlaying2 = false;
						}
						
						else { //not doubleDown		
						//compare sum2 to 21 after drawing the next card
						if (sum2<21) {
							count2++;
							System.out.println("Your current sum is " + sum2 + ". ");
							if (count2<5) {
							System.out.print("Would you like to draw again? Type '1' for yes and '2' for no"); //make all these while loops if time
							yesNo = scan.nextInt();
							if (yesNo == 1) keepPlaying2 = true;
							else if (yesNo == 2) {
								keepPlaying2 = false;
								stop = true;
							}
							}
							else if (count2 == 5) {
								playerWin = true;
								stop = false;
								keepPlaying2 = false;
								split = false;
							}
							else System.out.print("Wtf count is more than 5");
						}
						else if (sum2 == 21) {
							System.out.println("Your sum is exactly 21.");
							keepPlaying2 = false;
							stop = false;
							playerWin = true;
							split = false;
						}
						else if (sum2 > 21 && sum > 21) {
							System.out.println("You went over 21 with both hands. That means you lose. ");
							keepPlaying2 = false;
							stop = false;
							playerWin = false;
							stop = false;
						}
						else if (sum2 > 21 && sum < 21) {
							System.out.println("You went over 21. But your first sum is less than 21 so we will check the dealers cards.");
						}
						}//double down else brace
					}
				}
		
				//check dealer cards if player stops (ie: the don't go more than or equal 21)
				//This happens after both splits
				if (stop) {
					//reveal dealers cards and sum
					if (card1House != 11 && card1House != 1 && card2House != 11 && card2House != 1) System.out.print("The dealers cards are " + card1House + " and " + card2House + ". ");
					else if ((card1House ==1 && card2House ==11) || (card1House ==11 && card2House ==1)) System.out.print("The dealer's cards are two aces");
					else if (card1House == 11 || card1House == 1) System.out.print("The dealers cards are an ace an a " + card2House + ". ");
					else if (card2House == 11 || card2House == 1) System.out.print("The dealers cards are a " + card1House + " and an ace. ");
					houseSum = card1House + card2House;
					System.out.print("Their current sum is " + houseSum + ". \nPress enter to continue");
					scan2.nextLine();
					
					System.out.println();
					
					//deal for house and reveal their sum until it is >17
					while (houseSum<17) {
						int card = (int)(Math.random() * 13) + 1;
						if (card == 1) card = 1;
						else if (card > 9) card = 10;
						else {
							card = (int)(Math.random() * (9-2+1)) + 2;
						}
					if (card != 1) {
						System.out.println("The house drew a " + card + ".");
						houseSum += card;
					}
					if (card == 1) {
						if (houseSum+11 <= 21) {
							houseSum += 11;
							System.out.println("The house drew an ace (11)");
						}
						else if (houseSum+11 > 21) {
							houseSum +=1;
							System.out.println("The house drew an ace (1)");
						}
					}
					System.out.println("The house's current sum is " + houseSum + ".");
					
					System.out.print("Press enter to continue");
					scan2.nextLine();
					System.out.println();
					
					}
					
					
					//house sum is > 17; decide who wins
					if (houseSum >= 17 && houseSum < 21) {
						System.out.print("The house stops. ");
						
						if (!split) {
							biggerSum = sum;
						}
						else if (split) {
							if (sum > sum2 && sum <21) biggerSum = sum;
							else if (sum2 > sum && sum2<21) biggerSum = sum2;
							else if (sum == sum2) biggerSum = sum;
							else if (sum > 21) biggerSum = sum2;
							else if (sum2 > 21) biggerSum = sum;
							else System.out.print("Game broke");
						}
						
						if (biggerSum > houseSum) {
							playerWin = true;
							System.out.println("The player had a higher sum than the house. ");
						}
						else if (biggerSum < houseSum) {
							playerWin = false;
							System.out.println("The house had a higher sum than the player. ");
						}
						else if (biggerSum == houseSum) {
							playerWin = false;
							tie = true;
							if (!split) System.out.println("The player and the house have the same sum. ");
							if (split) System.out.println("All of the sums are the same ");
						}
					}
					
					else if (houseSum > 21) {
						playerWin = true;
						System.out.println("The house's sum is greater than 21. ");
					}
					else if (houseSum == 21) {
						playerWin = false;
						System.out.println("The house's sum is 21. ");
					}
					else System.out.println("Game broke");
					stop = true;
				} //if stop brace
				
				}// if sum != 21 brace
				else if (sum == 21) playerWin = true;
				else System.out.println("Game broke");
				
	//-----------------------------------------------------------------------------------
				
				
			//Decide who wins and loses the game
			if (playerWin && count!=5) {
				System.out.println("The player has won!");
				win(bet);
			}
			else if (playerWin && count==5) {
				System.out.println("You drew 5 cards and still have less than 21. You automatically win. ");
				win(bet);
			}
			else if (tie) {
				System.out.println("You have tied. You don't win or lose any money");
				tie = false;
			}
			else if (!playerWin) {
				System.out.println("The player has lost!");
				lose(bet);
			}
			else System.out.print("Broken game");
		
			
			 //bet again?
			if (money > 0) {
				System.out.print("Would you like to bet again? Type '1' for yes and '2' for no. ");
				yesNo = scan.nextInt();
				if (yesNo == 1) playAgain = true;
				else if (yesNo == 2) playAgain = false;
			}
			else if (money == 0) {
				playAgain = false;
				System.out.println("You lost all your money.");
			}
			else if (debt > 0) System.out.println("You're still in debt. ");
			
		}//big while loop brace
		
		
		//--------------------- Basically over -----------------------------------------
		
		if (money<0) {
			borrow();
		}
		
		if (debt>0 && money >0) {
			payDebt();
		}
		
		if (money>=0 && playAtAll) {
			System.out.println();
			System.out.println("You have left Blackjack. You ended with $" + money + ".");
		}else if (debt>0 && playAtAll) System.out.println("You have left Blackjack. You ended with a debt of $" + debt + ".");
	}
}
