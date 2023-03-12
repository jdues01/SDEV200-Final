package application;

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import FinalProject.Player;
import FinalProject.Deck;
import FinalProject.Card;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane gamePane = new Pane();
		gamePane.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, null, null)));
		Scene gameScene = new Scene(gamePane, 600, 600);
		
		//outlines for the cards
		Rectangle left = new Rectangle(150,100,150,200), right = new Rectangle(325,100,150,200);
		left.setStroke(Color.BLACK);
		left.setFill(Color.TRANSPARENT);
		right.setStroke(Color.BLACK);
		right.setFill(Color.TRANSPARENT);
		
		Player one = new Player();
		Player two = new Player();
		Deck deck = new Deck();
		Card[] allCards = new Card[52];

		Label leftLabel = new Label("Player 1", left);
		leftLabel.setContentDisplay(ContentDisplay.TOP);
		leftLabel.setLayoutX(150);
		leftLabel.setLayoutY(100);
		
		Label rightLabel = new Label("Player 2", right);
		rightLabel.setContentDisplay(ContentDisplay.TOP);
		rightLabel.setLayoutX(325);
		rightLabel.setLayoutY(100);
		
		gamePane.getChildren().addAll(left, leftLabel, right, rightLabel);
		
		Card oneDrawn = new Card();
		Card twoDrawn = new Card();
		
		Button rules = new Button("Rules");
		rules.setLayoutX(500);
		rules.setLayoutY(100);
		gamePane.getChildren().add(rules);
		rules.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				System.out.println("War is a two player game, where each player has 26 cards in their hand. \n"
						+ "Each player flips over a card from their pilel and the player with the highest ranking card earns a point \n"
						+ "and both cards are discarded. \n");
				System.out.println("If the cards are the same rank, it is War, and each player draws four cards. \n"
						+ "The fourth card is compared and the player with the highest card gets a point for \n"
						+ "each card in both players' piles. If these cards are the same rank, the process repeats until \n"
						+ "one player has a higher ranking card, and that player earns a point for all the cards in the war. \n");
				System.out.println("The game ends when one player runs out of cards. The player with the most points wins!");
			}
		});
		
		Text onesTurn = new Text();
		onesTurn.setLayoutX(250);
		onesTurn.setLayoutY(50);
		Text twosTurn = new Text();
		twosTurn.setLayoutX(250);
		twosTurn.setLayoutY(50);
		
		Text win = new Text();
		win.setLayoutX(250);
		win.setLayoutY(50);
		
		Button p1Draw = new Button("Draw");
		Button p1Shuffle = new Button("Shuffle");
		
		Button p2Draw = new Button("Draw");
		Button p2Shuffle = new Button("Shuffle");
		
		Button play = new Button("Play");
		
		ImageView card1 = new ImageView();
		card1.setLayoutX(125);
		card1.setLayoutY(100);
		ImageView card2 = new ImageView();
		card2.setLayoutX(300);
		card2.setLayoutY(100);
		
		p1Draw.setLayoutX(150);
		p1Draw.setLayoutY(350);
		p1Draw.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent click) {
				if(one.getHand().isEmpty()) {
					p1Draw.setDisable(true);
					}

					oneDrawn.setCard(one.draw());
					gamePane.getChildren().remove(onesTurn);
					
					
					p2Draw.setDisable(false);
					p2Shuffle.setDisable(false);
					
					p1Draw.setDisable(true);
					p1Shuffle.setDisable(true);
					
					gamePane.getChildren().addAll(card1,twosTurn);
				}
			}
		});

		p1Shuffle.setLayoutX(235);
		p1Shuffle.setLayoutY(350);
		p1Shuffle.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent click) {
				if(one.getHand().isEmpty()) {
					p1Shuffle.setDisable(true);
				} else {
					one.getHand().shuffle();
				}
			{
		}
		
		p2Draw.setLayoutX(325);
		p2Draw.setLayoutY(350);
		p2Draw.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent click) {
				if(two.getHand().isEmpty()) {
					p2Draw.setDisable(true);
				} else {
					twoDrawn.setCard(two.draw());
					card2.setImage(new Image(findImageURL(twoDrawn)));
					gamePane.getChildren().remove(twosTurn);
					
					p1Draw.setDisable(false);
					p1Shuffle.setDisable(false);
					
					p2Draw.setDisable(true);
					p2Shuffle.setDisable(true);
					
					gamePane.getChildren().addAll(card2,onesTurn);
					
					compareCards(oneDrawn, twoDrawn, one, two, 1, leftLabel, rightLabel);
					
					if(one.getHand().isEmpty() || two.getHand().isEmpty()) {
						if(one.getScore()>two.getScore()) {
							win.setText(one.getName()+" is the winner!");
						}else {
							win.setText(two.getName()+" is the winner!");
						}
						
						gamePane.getChildren().removeAll(twosTurn, onesTurn);
						gamePane.getChildren().add(win);
					}
				}
			}
		});
		
		p2Shuffle.setLayoutX(410);
		p2Shuffle.setLayoutY(350);
		p2Shuffle.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent click) {
				if(two.getHand().isEmpty()) {
					p2Shuffle.setDisable(true);
				}else {
					two.getHand().shuffle();
				}	
			}
		});
		
		play.setLayoutX(280);
		play.setLayoutY(400);
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent click) {
				gamePane.getChildren().remove(play);
				gamePane.getChildren().addAll(p1Draw, p1Shuffle, p2Draw, p2Shuffle, onesTurn);
				
				p2Draw.setDisable(true);
				p2Shuffle.setDisable(true);
				
				deck.deal(one.getHand());
				deck.deal(two.getHand());
						
				one.getHand().shuffle();
				two.getHand().shuffle();
			}
		});
		
		// fields for name inputs
		TextField name2 = new TextField();
		Label name2Label = new Label("What is player 2's name?", name2);
		name2Label.setContentDisplay(ContentDisplay.BOTTOM);
		name2Label.setLayoutX(220);
		name2Label.setLayoutY(400);
		name2.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				name2.setText(name2.getText());
				two.setName(name2.getText());
				twosTurn.setText("It's " +two.getName()+ "'s turn!");
				rightLabel.setText(name2.getText()+ ": " + two.getScore());
				gamePane.getChildren().removeAll(name2, name2Label);
				gamePane.getChildren().add(play);
			}
		});
	
		TextField name1 = new TextField();
		Label name1Label = new Label("What is player 1's name?", name1);
		name1Label.setContentDisplay(ContentDisplay.BOTTOM);
		name1Label.setLayoutX(220);
		name1Label.setLayoutY(400);
		name1.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent click) {
				name1.setText(name1.getText());
				one.setName(name1.getText());
				onesTurn.setText("It's "+ one.getName() + "'s turn!");
				leftLabel.setText(name1.getText()+": "+one.getScore());
				gamePane.getChildren().removeAll(name1, name1Label);
				gamePane.getChildren().addAll(name2, name2Label);
			}
		});
		
		gamePane.getChildren().addAll(name1, name1Label);
		
		primaryStage.setTitle("War Card Game");
		primaryStage.setScene(gameScene);
		primaryStage.show();
	}
	
	public static void compareCards(Card a, Card b, Player p1, Player p2, int w, Label l1, Label l2) {
		
		int warScore = score;
		
		if(a.rankOrder()>b.rankOrder()) {
			for(int i=0;i<warScore;i++) {
				p1.givePoint();
			}
			updateScore(l1, p1);
		}else if(b.rankOrder()>a.rankOrder()) {
			for(int i=0;i<warScore;i++) {
				p2.givePoint();
			}
			updateScore(l2, p2);
		}else if(a.rankOrder()==b.rankOrder()) {
			int i=0;
			Card c = new Card();
			while(!p1.getHand().isEmpty() && i<=3) {
				c.setCard(p1.draw());
				i++;
			}
			int j= 0;
			Card d = new Card();
			while(!p2.getHand().isEmpty() && j<=3) {
				d.setCard(p2.draw());
				j++;
			}
			warScore++;
			compareCards(c, d, p1, p2, warScore, l1, l2);
		}
	}
	
	public static void updateScore(Label l, Player Player1) {
		l.setText(p.getName()+": "+p.getScore());
	}
	}