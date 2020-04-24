
public class BlackJack extends CasinoPlayer{
	
	public BlackJack(String newName, int newMoney) {
		money = newMoney;
		name = newName;
	}
	
	public boolean compareSums(int playerSum, int houseSum, boolean arr[], int index) {
		if (playerSum > 21) {
			System.out.println("The player's sum was greater than 21. ");
			return false;
		} else if (playerSum == 21) {
			System.out.println("The player's sum is exactly 21. ");
			return true;
		} else if (playerSum > houseSum) {
			System.out.println("The player had a higher sum than the house. ");
			return true;
		} else if (playerSum < houseSum) {
			System.out.println("The house had a higher sum than the player. ");
			return false;
		}
		
		arr[index] = true;
		System.out.println("The player and the house have the same sum. ");
		return false;
	}
	
	public void determineWinner(boolean playerWin, boolean tie, int count) {
		if (playerWin && count==5) {
			System.out.println("You drew 5 cards and still have less than 21. You automatically win. ");
			win(bet);
		} else if (playerWin) {
			System.out.println("The player has won!");
			win(bet);
		} else if (tie) {
			System.out.println("You have tied. You don't win or lose any money");
		} else if (!playerWin) {
			System.out.println("The player has lost!");
			lose(bet);
		}
		else System.out.print("Broken game");
	}


	public void playBlackJack() {
		boolean keepPlaying = false;
		boolean stop = false;
		boolean stop2 = false;
		
		int[] roundCards = {0,0,0,0};
		
		int sum = 0;
		int houseSum = 0;
		int yesNo = 0;
		
		boolean playerWin = true;
		boolean playerWin2 = true;
		boolean tie[] = {false,false};
		
		readyPlay();
		
		//Bonus
		int count = 0;
		boolean doubleDown = false;
		boolean split = false;
		int sum2 = 0;
		int count2 = 0;
		boolean secondHand = false;
		int splitCount = 0;
		
		while (playAgain) {
			count = 0;
			sum2 = 0;
			tie[0]=tie[1]=false;
			splitCount = 0;
			doubleDown = false;
			playerWin=false;
			
			//make a bet
			makeBet();
			
			System.out.println();	
			
			//deal initial cards to player and house
			for (int i = 0; i<4; i++) {	
				cardDraw();
				roundCards[i] = card;
			}
			
			//Decide dealers ace values
			if (roundCards[2] == 1 || roundCards[3] == 1) {
				if (roundCards[2] == 1 && roundCards[3] == 1){
					roundCards[2] = 11;
				}
				else if (roundCards[2] == 1) 
					roundCards[2] = 11;
				else  
					roundCards[3] = 11;
			}
			
			
			//reveal players cards:
			for (int i = 0; i<1; i++) {
				
				if(i==0)
					System.out.print("The player's cards are ");
				else
					System.out.print("The dealer's cards are ");
				
				if (roundCards[2*i]==roundCards[2*i+1]) {
					System.out.print("two ");
					if (roundCards[2*i]==1)
						System.out.println("aces. ");
					else if (roundCards[2*i]==10)
						System.out.println("high cards. ");
					else
						System.out.println(roundCards[0] + "s. ");
				}else {
					if (roundCards[2*i]==1)
						System.out.print("an ace ");
					else if (roundCards[2*i]==10)
						System.out.print("a high card ");
					else
						System.out.print(roundCards[2*i] + " ");
					
					System.out.print("and ");
					
					if (roundCards[2*i+1]==1)
						System.out.println("an ace. ");
					else if (roundCards[2*i+1]==10)
						System.out.println("a high card. ");
					else
						System.out.println(roundCards[2*i+1] + ". ");
				}
			}
			
			
				
			//split
			split = false;
			if (roundCards[0] == roundCards[1] && bet*2 <= money) {
				System.out.print("You have two of the same card. Would you like to split? Type '1' for yes and '2' for no ");
				yesNo = enterInt(1,2);
				if (yesNo == 1) {
					split = true;
					keepPlaying = true;
					count = 1;
					count2 = 1;
				}
				else if (yesNo == 2) {
					split = false;
				}
			} else if (roundCards[0] == roundCards[1])
				System.out.println("You have two of the same card, but not enough money to split.");
			
			
			//decide player ace (if they have ace)
			if ((roundCards[0]+roundCards[1]==11) && (roundCards[0] == 1 || roundCards[1]==1)) {
				System.out.println("BlackJack!!");
				playerWin = true;
				stop = false;
				roundCards[0] = 11;
				roundCards[1] = 10;
			} else if (roundCards[0] == 1 || roundCards[1] == 1) {
				if (roundCards[0] ==1 && roundCards[1]==1) {
					System.out.println("What Value would you like to make your aces? ");
					System.out.print("Enter the first value you want: ");
					roundCards[0] = selectValue(1,11);
					
					if (roundCards[0]==11 && !split) {
						roundCards[1]=1;
						System.out.println("You did not split so your second ace gets the value 1. ");
					} else {
						System.out.print("Enter the second value you want: ");
						roundCards[1] = selectValue(1,11);
					}					
				} else {
					System.out.print("Enter the desired value of your ace: ");
					
					if (roundCards[0] == 1) 
						roundCards[0] = selectValue(1,11);
					else {
						roundCards[1] = selectValue(1,11);
					}
				}
			}
			
			
			
			if (!split) {
				//sum player's cards
				sum = (roundCards[0] + roundCards[1]);
				System.out.println("Your sum is " + sum + ". ");
			} else {
				sum = roundCards[0];
				sum2 = roundCards[1];
			}
				
				
			if (sum != 21){
				//announce one of house's cards:
				if (!(roundCards[3] == 1 || roundCards[3] == 11 || roundCards[3] == 10))
					System.out.println("One of the dealer's cards is " + roundCards[3] + ". The other is unknown.");
				else if (roundCards[3] == 10) 
					System.out.println("One of the dealer's cards is a high card. The other is unknown.");
				else
					System.out.println("One of the dealer's cards is an ace. The other is unknown.");
				
			
				if (bet*2 <= money && !split) {
					System.out.print("Would you like to 'Double Down'? Type '1' for yes and '2' for no ");
					yesNo = selectValue(1,2);
						
					if (yesNo == 1)
						doubleDown = true;
					else 
						doubleDown = false;
				} else if (split) {
					System.out.println("You split so you cannot double down at this table. ");
				}
				
				
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
				} else if (!split){
					System.out.print("Would you like to hit? Type '1' for yes and '2' for no ");
					yesNo = selectValue(1,2);
					if (yesNo == 1) {
						keepPlaying = true;
						count = 2;	//two cards to start
					}
					else if (yesNo == 2) {
						keepPlaying = false;
						stop = true;
					}
				}
	
				secondHand = false;
				while ((keepPlaying && sum<21 && count!=5) || secondHand) {
					
					//draw the next card(s)
					cardDraw();
					
					if (secondHand) {
						System.out.println("\nWe are now playing your second hand.");
						int temp = 0;
						temp = sum;
						sum = sum2;
						sum2 = temp;
						
						temp = count;
						count = count2;
						count2 = temp;
						
						boolean temp2;
						temp2 = stop;
						stop = stop2;
						stop2 = temp2;
						
						temp2 = playerWin;
						playerWin = playerWin2;
						playerWin2 = temp2;
			
						keepPlaying = true;
						secondHand = false;
					}
					
					if (card != 1) {
						System.out.println("You drew a " + card);
						sum += card;
					}
					if (card == 1) {
						if (11 + sum == 21) {
							System.out.print("You drew an ace. You got blackjack! ");
							sum+=11;
						} else if (11 + sum < 21) {
							System.out.print("You drew an ace. Would you like to make your ace a 1 or an 11? ");
							card = selectValue(1,11);
							sum += card;
						} else {
							System.out.println("You drew an ace. Your current sum is " + sum + ", so I am making your ace a 1");
							sum += 1;
						}	
					}
					
					if (doubleDown) {
						System.out.println("Your final sum is " + sum + ".");
						if (sum<21) {
							stop = true;
						} else if (sum > 21) {
							stop = false;
							playerWin = false;
						} else if (sum == 21) {
							stop = false;
							playerWin = true;
						}
						
						keepPlaying = false;
					} else { 
						
						//compare sum to 21 after drawing the next card
							
						if (sum<21) {
							count++;
							System.out.println("Your current sum is " + sum + ". ");
							
							if (count<5) {
								System.out.print("Would you like to hit again? Type '1' for yes and '2' for no "); 
								yesNo = selectValue(1,2);
								if (yesNo == 1) {
									keepPlaying = true;
								} else if (yesNo == 2) {
									keepPlaying = false;
									stop = true;
								}
							} else if (count == 5) {
								playerWin = true;
								stop = false;
								keepPlaying = false;
								System.out.println("You have 5 cards!");
							} else 
								System.out.print("count is more than 5- something is wrong");
						} else if (sum == 21) {
							System.out.println("Your sum is exactly 21.");
							keepPlaying = false;
							stop = false;
							playerWin = true;
						} else if (sum > 21) {
							System.out.println("Your sum is " + sum);
							System.out.println("You went over 21. That means you lose. ");
							
							keepPlaying = false;
							stop = false;
							playerWin = false;
						}
						
						if (!keepPlaying && split && splitCount != 2) {
							System.out.println("Good first hand. Let's play your second hand now.");
							splitCount = 2;
							secondHand = true;
							enter();
						}
					}//double down else brace
				} //while loop closing brace
				
				//reveal dealers cards
				if (!(roundCards[2] == 11 || roundCards[2] == 1 || roundCards[3] == 11 || roundCards[3] == 1)) 
					System.out.println("The dealer's cards are " + roundCards[2] + " and " + roundCards[3] + ". ");
				else if ((roundCards[2] ==1 && roundCards[3] ==11) || (roundCards[2] ==11 && roundCards[3] ==1)) 
					System.out.println("The dealer's cards are two aces. ");
				else if (roundCards[2] == 11 || roundCards[2] == 1) 
					System.out.println("The dealer's cards are an ace and a " + roundCards[3] + ". ");
				else if (roundCards[3] == 11 || roundCards[3] == 1) 
					System.out.println("The dealer's cards are a " + roundCards[2] + " and an ace. ");
				
				
				if (stop || stop2) {
					//reveal dealers sum

					houseSum = roundCards[2] + roundCards[3];
					
					System.out.println("Their current sum is " + houseSum + ". ");
					enter();
					
					
					//deal for house and reveal their sum until it is >17
					while (houseSum<17) {
						cardDraw();
					
						if (card != 1) {
							System.out.println("The house drew a " + card + ".");
							houseSum += card;
						} else if (card == 1) {
							if (houseSum+11 <= 21) {
								houseSum += 11;
								System.out.println("The house drew an ace (11)");
							} else if (houseSum+11 > 21) {
								houseSum +=1;
								System.out.println("The house drew an ace (1)");
							}
						}
						System.out.println("The house's current sum is " + houseSum + ".");
						
						enter();
					}
					
					
					//house sum is > 17; decide who wins ---------------------------------------------------------
					
					
					if (houseSum >= 17 && houseSum < 21) {
						System.out.print("The house stops. ");
						
						if (split) {
							System.out.println("For Game 1: ");
							playerWin2 = compareSums(sum2, houseSum, tie, 0); //game 1
							enter();
							System.out.println("For Game 2: ");
						}
						
						playerWin = compareSums(sum, houseSum, tie, 1); //game 2
						System.out.println();
						
						
						
					} else if (houseSum > 21) {
						System.out.println("The house's sum is greater than 21. ");
						
						if (stop && stop2)
							playerWin = playerWin2 = true;
						else if (stop)
							playerWin = true;
						else if (stop2)
							playerWin2 = true;
						
						
						
					} else if (houseSum == 21) {
						System.out.println("The house's sum is 21. ");
						
						if (stop && stop2)
							playerWin = playerWin2 = false;
						else if (stop)
							playerWin = false;
						else if (stop2)
							playerWin2 = false;
						
						
					} else 
						System.out.println("Game broke");
					
					stop = false;
					stop2 = false;
				} //if stop brace
				
			} else if (sum == 21) // if sum != 21 brace
				playerWin = true;
			else 
				System.out.println("Game broke");
				
	//-----------------------------------------------------------------------------------
				
			if (split) {
				System.out.println("For game 1: ");
				determineWinner(playerWin2, tie[0], count2);
				enter();
				
				System.out.println("For game 2: ");
			}
			
			determineWinner(playerWin, tie[1], count);
			System.out.println();
	
			
			//bet again?
			if (money > 0) {
				System.out.print("Would you like to bet again? Type '1' for yes and '2' for no. ");
				yesNo = selectValue(1,2);
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
		
		if (money<=0) {
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
	
	
