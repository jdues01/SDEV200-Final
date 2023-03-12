package FinalProject;

import java.util.Scanner;

public class Game {
public static void main(String[] args) {
	// Create scanner
	Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to the game of War!");
		
		Player player1 = new Player(),player2 = new Player();
		
		
		System.out.println("What is Player 1's name? ");
		String p1Name = input.next();
		player1.setName(p1Name);
			
		System.out.println("What is Player 2's name? ");
		String p2Name = input.next();
		player2.setName(p2Name);
		System.out.println();

		Deck deck = new Deck();
		Card[] allCards = new Card[52];
				
					if (player1.getScore()>player2.getScore()) {
						System.out.println(player1.getName() + " is the winner!");
					} else {
						System.out.println(player2.getName() + " is the winner!");
					}
					boolean play = true;
					
					if (!playAgain()) {
						play = false;
						System.out.println("Thank you for playing!");
						System.exit(0);
					}
				}
		
	
	public static void rules() {
		System.out.println("War is a two player card game in which the deck is split evenly between the players. \n"
				+ "Each player flips over the top card from their hand and the player with the highest card earns a point \n"
				+ "and both cards are discarded. \n");
		System.out.println("If the cards are the same rank (both Aces, both 2s, etc), it is called a War, and each player draws four cards. \n"
				+ "The value of the fourth card is compared and the player with the highest card gains a point for \n"
				+ "every card in both piles. If these cards are the same rank, the process repeats until \n"
				+ "one player's fourth card is higher than the other player's. \n");
		System.out.println("The game of War ends when one player has no cards left. The player with the most points wins. \n");
	}
	
	public static boolean playAgain() {
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to play again? (Y/N)");
		String playAgain = input.nextLine();
		
		while(!(playAgain.equals("Y")) && !(playAgain.equals("N"))) {
			playAgain = input.next();
		}
		
		if(playAgain.equals("Y")) {
			return true;
		}else {
			return false;
		}
	}
}
