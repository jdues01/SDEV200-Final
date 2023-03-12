package FinalProject;

public class Card {
	private int cardNum;
	final static String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};
	final static String[] cardValues = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
	
	public Card() {
	}
	Card (int theCard) {
        setCardNum (theCard);
    }

	public int getCardNum() {
        return cardNum;
    }
	public void setCardNum (int theCard) {
        cardNum = (theCard >= 0 && theCard <= 51)? theCard: 0;
    }
	
	public String toString() {
        return cardValues[cardNum%13] + " of " + suits[cardNum/13];
    }

    public String getSuit() {
        return suits[cardNum/13];
    }

    public String getRank() {
        return cardValues[cardNum%13];
    }

    public int getValue() {
        return cardNum%13;
    } 
}