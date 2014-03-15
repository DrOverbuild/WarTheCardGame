/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package warcardgame.cardstuff;

import javax.swing.ImageIcon;
import warcardgame.WarCardGame;

/**
 *
 * @author jasper
 */
public class Card {
	int numericalValue;
	int suit;
	ImageIcon cardIcon;

	public static final int SPADES_SUIT = 0;
	public static final int CLUBS_SUIT = 1;
	public static final int HEARTS_SUIT = 2;
	public static final int DIAMONDS_SUIT = 3;

	public static final Card ACE_OF_SPADES   = new Card(SPADES_SUIT, 1);
	public static final Card TWO_OF_SPADES   = new Card(SPADES_SUIT, 2);
	public static final Card THREE_OF_SPADES = new Card(SPADES_SUIT, 3);
	public static final Card FOUR_OF_SPADES  = new Card(SPADES_SUIT, 4);
	public static final Card FIVE_OF_SPADES  = new Card(SPADES_SUIT, 5);
	public static final Card SIX_OF_SPADES   = new Card(SPADES_SUIT, 6);
	public static final Card SEVEN_OF_SPADES = new Card(SPADES_SUIT, 7);
	public static final Card EIGHT_OF_SPADES = new Card(SPADES_SUIT, 8);
	public static final Card NINE_OF_SPADES  = new Card(SPADES_SUIT, 9);
	public static final Card TEN_OF_SPADES   = new Card(SPADES_SUIT, 10);
	public static final Card JACK_OF_SPADES  = new Card(SPADES_SUIT, 11);
	public static final Card QUEEN_OF_SPADES = new Card(SPADES_SUIT, 12);
	public static final Card KING_OF_SPADES  = new Card(SPADES_SUIT, 13);

	public static final Card ACE_OF_CLUBS   = new Card(CLUBS_SUIT, 1);
	public static final Card TWO_OF_CLUBS   = new Card(CLUBS_SUIT, 2);
	public static final Card THREE_OF_CLUBS = new Card(CLUBS_SUIT, 3);
	public static final Card FOUR_OF_CLUBS  = new Card(CLUBS_SUIT, 4);
	public static final Card FIVE_OF_CLUBS  = new Card(CLUBS_SUIT, 5);
	public static final Card SIX_OF_CLUBS   = new Card(CLUBS_SUIT, 6);
	public static final Card SEVEN_OF_CLUBS = new Card(CLUBS_SUIT, 7);
	public static final Card EIGHT_OF_CLUBS = new Card(CLUBS_SUIT, 8);
	public static final Card NINE_OF_CLUBS  = new Card(CLUBS_SUIT, 9);
	public static final Card TEN_OF_CLUBS   = new Card(CLUBS_SUIT, 10);
	public static final Card JACK_OF_CLUBS  = new Card(CLUBS_SUIT, 11);
	public static final Card QUEEN_OF_CLUBS = new Card(CLUBS_SUIT, 12);
	public static final Card KING_OF_CLUBS  = new Card(CLUBS_SUIT, 13);

	public static final Card ACE_OF_HEARTS   = new Card(HEARTS_SUIT, 1);
	public static final Card TWO_OF_HEARTS   = new Card(HEARTS_SUIT, 2);
	public static final Card THREE_OF_HEARTS = new Card(HEARTS_SUIT, 3);
	public static final Card FOUR_OF_HEARTS  = new Card(HEARTS_SUIT, 4);
	public static final Card FIVE_OF_HEARTS  = new Card(HEARTS_SUIT, 5);
	public static final Card SIX_OF_HEARTS   = new Card(HEARTS_SUIT, 6);
	public static final Card SEVEN_OF_HEARTS = new Card(HEARTS_SUIT, 7);
	public static final Card EIGHT_OF_HEARTS = new Card(HEARTS_SUIT, 8);
	public static final Card NINE_OF_HEARTS  = new Card(HEARTS_SUIT, 9);
	public static final Card TEN_OF_HEARTS   = new Card(HEARTS_SUIT, 10);
	public static final Card JACK_OF_HEARTS  = new Card(HEARTS_SUIT, 11);
	public static final Card QUEEN_OF_HEARTS = new Card(HEARTS_SUIT, 12);
	public static final Card KING_OF_HEARTS  = new Card(HEARTS_SUIT, 13);

	public static final Card ACE_OF_DIAMONDS   = new Card(DIAMONDS_SUIT, 1);
	public static final Card TWO_OF_DIAMONDS   = new Card(DIAMONDS_SUIT, 2);
	public static final Card THREE_OF_DIAMONDS = new Card(DIAMONDS_SUIT, 3);
	public static final Card FOUR_OF_DIAMONDS  = new Card(DIAMONDS_SUIT, 4);
	public static final Card FIVE_OF_DIAMONDS  = new Card(DIAMONDS_SUIT, 5);
	public static final Card SIX_OF_DIAMONDS   = new Card(DIAMONDS_SUIT, 6);
	public static final Card SEVEN_OF_DIAMONDS = new Card(DIAMONDS_SUIT, 7);
	public static final Card EIGHT_OF_DIAMONDS = new Card(DIAMONDS_SUIT, 8);
	public static final Card NINE_OF_DIAMONDS  = new Card(DIAMONDS_SUIT, 9);
	public static final Card TEN_OF_DIAMONDS   = new Card(DIAMONDS_SUIT, 10);
	public static final Card JACK_OF_DIAMONDS  = new Card(DIAMONDS_SUIT, 11);
	public static final Card QUEEN_OF_DIAMONDS = new Card(DIAMONDS_SUIT, 12);
	public static final Card KING_OF_DIAMONDS  = new Card(DIAMONDS_SUIT, 13);

	public Card(int suit, int numericalValue)throws IllegalArgumentException{
		if(suit <= 3 && suit >= 0){
			this.suit = suit;
		}else{
			throw new IllegalArgumentException("Invalid suit");
		}
		this.numericalValue = numericalValue;
		cardIcon = WarCardGame.getIconInJar("cards/"+numericalValue+"of"+this.suit+".png");

	}

	public Card(String suit, int numericalValue) throws IllegalArgumentException{
		if(suit.toLowerCase().startsWith("s")){
			this.suit = SPADES_SUIT;
		}else if(suit.toLowerCase().startsWith("c")){
			this.suit = CLUBS_SUIT;
		}else if(suit.toLowerCase().startsWith("h")){
			this.suit = HEARTS_SUIT;
		}else if(suit.toLowerCase().startsWith("d")){
			this.suit = DIAMONDS_SUIT;
		}else{
			throw new IllegalArgumentException("Invalid suit");
		}
		cardIcon = WarCardGame.getIconInJar("cards/"+numericalValue+"of"+this.suit+".png");
		this.numericalValue = numericalValue;
	}

	public String getSuitString(){
		if (suit == 0){
			return "Spades";
		}else if(suit == 1){
			return "Clubs";
		}else if(suit == 2){
			return "Hearts";
		}else if(suit == 3){
			return "Diamonds";
		}else{
			return "";
		}
	}

	public int getSuit(){
		return suit;
	}

	public String getFaceCard(){
		if(numericalValue == 1){
			return "Ace";
		}else if(numericalValue == 13){
			return "King";
		}else if(numericalValue == 12){
			return "Queen";
		}else if(numericalValue == 11){
			return "Jack";
		}else{
			return "" + numericalValue;
		}
	}

	public int getNumericalValue(){
		return numericalValue;
	}

	public ImageIcon getCardIcon() {
		return cardIcon;
	}

	@Override
	public String toString(){
		return getFaceCard()+" of " + getSuitString();
	}


}
