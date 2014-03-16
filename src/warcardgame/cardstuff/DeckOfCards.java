/*
 * Copyright (c) 2014 Jasper Reddin.
 * All rights reserved.
 */

package warcardgame.cardstuff;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jasper
 */
public class DeckOfCards {
	ArrayList<Card> orderOfCards = new ArrayList<>(52);

	public DeckOfCards(){
		for(int i = 1; i <= 13; i++){
			orderOfCards.add(new Card("spades", i));
		}
		for(int i = 1; i <= 13; i++){
			orderOfCards.add(new Card("clubs", i));
		}
		for(int i = 1; i <= 13; i++){
			orderOfCards.add(new Card("hearts", i));
		}
		for(int i = 1; i <= 13; i++){
			orderOfCards.add(new Card("diamonds", i));
		}
	}

	public String[] getOrderOfCardsString(){
		String[] order = new String[52];

		int i = 0;

		for(Card card:orderOfCards){
			if(i<52){
				order[i] = orderOfCards.get(i).toString();
				i++;
			}
		}

		return order;
	}

	public Card[] getOrderOfCards(){
		Card[] order = new Card[52];

		int i = 0;

		for(Card card:orderOfCards){
			order[i] = orderOfCards.get(i);
			i++;
		}

		return order;
	}

	public void shuffleOrder(){
		Collections.shuffle(orderOfCards);
	}

	public void putBackInOrder(){
		ArrayList<Card> newArray = new ArrayList<>(52);
		for(int i = 1; i <= 13; i++){
			newArray.add(new Card("spades", i));
		}
		for(int i = 1; i <= 13; i++){
			newArray.add(new Card("clubs", i));
		}
		for(int i = 1; i <= 13; i++){
			newArray.add(new Card("hearts", i));
		}
		for(int i = 1; i <= 13; i++){
			newArray.add(new Card("diamonds", i));
		}
		orderOfCards =  newArray;
	}

	public void deal(PlayerHand[] hands, int amount){
		int i = 0;
		for(int j = 0;j<=amount-1;j++){
			for(PlayerHand hand:hands){
				try{
					hand.addCard(orderOfCards.get(i));
				}catch(ArrayIndexOutOfBoundsException e){
					return;
				}catch(IndexOutOfBoundsException e){
					return;
				}
				i++;
			}
		}
	}

	public void distributeEvenly(PlayerHand[] hands){
		int amount = orderOfCards.size() / hands.length;
		deal(hands, amount);
	}
}
