package FinalProject;

public class Player {
	private String name;
	private int score;
	public Hand hand;
	
	public Player() {
		name = null;
		hand = new Hand();
		score = 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public int getNumofCards() {
		return hand.getNumCards();
	}
	
	public Card draw() {
		return hand.draw();
	}
	
	public void addToHand(Card card) {
		hand.add(card);
	}
	
	public void givePoint() {
		score++;
	}
	
	public int getScore() {
		return score;
	}
}
