/*
 * Copyright (c) 2014 Jasper Reddin.
 * All rights reserved.
 */

package warcardgame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import warcardgame.gui.GamemodeChooser;

/**
 *
 * @author jasper
 */
public class WarCardGame extends JFrame{

	public static WarCardGame game = new WarCardGame();

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws Exception {

		new GamemodeChooser();

//		PlayerHand hand = new PlayerHand();
//		hand.addCard(Card.ACE_OF_SPADES);
//		hand.addCard(Card.TWO_OF_HEARTS);
//		hand.addCard(Card.FIVE_OF_DIAMONDS);
//		Card card = hand.getFirstCard();
//		hand.removeCard(card);
//		System.out.println(card.toString());
//		card = hand.getFirstCard();
//		hand.removeCard(card);
//		System.out.println(card.toString());
//		card = hand.getFirstCard();
//		hand.removeCard(card);
//		System.out.println(card.toString());

	}

	public static ImageIcon getIconInJar(String name){
		System.out.println("img/"+name);
		ImageIcon img = new ImageIcon(WarCardGame.class.getResource("img/"+name));
		return img;
	}

}
