package FinalProject;

public class Deck {
	private final int LIMIT = 52;
		
	public Deck() {
		
	}
	
	public void deal(Hand h) {
		// keep track of cards flipped over
		int count = 0;
		
		while(count < h.getLimit()) {
			h.add(draw());
			count++;
		}
	}
	
	public Card flip() {
		int rng = (int)(Math.random()*getNumCards());
		Card flipped = getCard(rng);
		
		remove(flipped);
		
		return flipped;
	}
	
	private void remove(Card flipped) {
		
	}

	private double getNumCards() {
		// TODO Auto-generated method stub
		return 0;
	}

	private Card getCard(int card) {
		Card[] cards = toArray();
		return cards[card];
	}
	
	private Card[] toArray() {
	}

	public void shuffle() {
		Card[] cards = toArray();
		
		// shuffle the cards 3 times
		int TimesShuffled = 3;	
		
		while(TimesShuffled > 0) {
			for(int i=0; i<cards.length; i++) {
				int rng = (int)(Math.random()*cards.length);
				
				Card temp = cards[i];
				cards[i] = cards[rng];
				cards[rng] = temp;
			}
			TimesShuffled--;
		}
		
		clear();
		for(int i= 0;i< cards.length;i++) {
			add(cards[i]);
		}
	}
	
	private void add(Card card) {
		// TODO Auto-generated method stub
		
	}

	private void clear() {
		// TODO Auto-generated method stub
		
	}

	public int getLimit() {
		return LIMIT;
	}
}
