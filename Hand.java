package FinalProject;

public class Hand {
private final int LIMIT = 26;
	
	public Hand() {
		
	}
	
	public Card draw() {
		int rng = (int)(Math.random()* getNumCards());
		Card drawn = getCard(rng);
		
		remove(drawn);
		
		return drawn;
	}
	
	private void remove(Card drawn) {
		// TODO Auto-generated method stub
		
	}

	private Card getCard(int x) {
		Card[] cards = toArray();
		return cards[x];
	}
	
	private Card[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLimit() {
		return LIMIT;
	}

	public int getNumCards() {
		// TODO Auto-generated method stub
		return 0;
	}
}