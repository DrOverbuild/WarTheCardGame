/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package warcardgame.cardstuff;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jasper
 */
public class PlayerHand {

	ArrayList<Card> inHand = new ArrayList<>();

	public Card[] getHand(){
		return inHand.toArray(new Card[]{});
	}
	public ArrayList<Card> getHandArrayList(){
		return inHand;
	}

	public Card getFirstCard(){
		return inHand.get(0);
	}

	public Card getLastCard(){
		return inHand.get(inHand.size()-1);
	}

	public Card getCardAtIndexOf(int index) throws ArrayIndexOutOfBoundsException{
		return inHand.get(index);
	}

	public void addCard(Card card){
		inHand.add(card);
	}

	public void addCards(ArrayList<Card> cards){
		inHand.addAll(cards);
	}

	public void removeCard(Card card)throws Exception{
		if(inHand.contains(card)){
			inHand.remove(card);
		}else{
			throw new Exception("Player does not have card");
		}
	}

	public void removeCardAtIndex(int index){
		inHand.remove(index);
	}

	public void shuffleHand(){
		Collections.shuffle(inHand);
	}

	public void clearHand(){
		inHand.removeAll(inHand);
	}

	public int numberOfCardsInHand(){
		return inHand.size();
	}

}
