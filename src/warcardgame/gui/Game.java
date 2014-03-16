/*
 * Copyright (c) 2014 Jasper Reddin.
 * All rights reserved.
 */

package warcardgame.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import warcardgame.WarCardGame;
import warcardgame.cardstuff.Card;
import warcardgame.cardstuff.DeckOfCards;
import warcardgame.cardstuff.PlayerHand;

/**
 *
 * @author jasper
 */
public class Game extends JFrame implements ActionListener{

	public int gamemode;
	public int players;
	int gameState;
	int numberOfTimesPressed=0;

	ImageIcon backOfCardIcon = WarCardGame.getIconInJar("cards/back.png");
	ImageIcon blankCardIcon = WarCardGame.getIconInJar("cards/blank.png");
	ImageIcon currentTiedCardsIcon;

	JLabel opponentsCardsLabel = new JLabel();
	JLabel opponentsCardsNumber = new JLabel();
	JLabel opponentsWinsLabel = new JLabel();
	JLabel opponentsWinsNumber = new JLabel("0");
	JLabel playersCardsLabel = new JLabel();
	JLabel playersCardsNumber = new JLabel();
	JLabel playersWinsLabel = new JLabel();
	JLabel playersWinsNumber = new JLabel("0");


	/**
	 * Holds all cards and images therein.
	 */
	JPanel randomPanel = new JPanel(new GridLayout(4,4));

	JButton opponentsCardPileLabel = new JButton();
	JButton playersCardPileLabel = new JButton();
	JLabel opponentsPlayedCardLabel = new JLabel();
	JLabel playersPlayedCardLabel = new JLabel();
	JLabel opponentsTiedCardsPile = new JLabel();
	JLabel playersTiedCardsPile = new JLabel();
	Card opponentsPlayedCard;
	Card playersPlayedCard;
	final ArrayList<Card> tieCards = new ArrayList<>();

	JLabel opponentsCardPlayedLabel = new JLabel();
	JLabel playersCardPlayedLabel = new JLabel();

	DeckOfCards deck = new DeckOfCards();

	PlayerHand opponentsHand = new PlayerHand();
	PlayerHand playersHand = new PlayerHand();
	PlayerHand opponentsWonCards = new PlayerHand();
	PlayerHand playersWonCards = new PlayerHand();

	public Game(int gamemode, int players){
		super("War!");

		// Crashes game when not on Mac.
		//FullScreenUtilities.setWindowCanFullScreen(this,true);

		this.gamemode = gamemode;
		this.players = players;
		this.gameState = 0;

		if(players == 1){
			opponentsCardsLabel.setText("Opponent's Cards: ");
			opponentsWinsLabel.setText("Opponent's Won Cards: ");
			playersWinsLabel.setText("Your Won Cards: ");
			playersCardsLabel.setText("Your Cards: ");
		}else if(players == 2){
			opponentsCardsLabel.setText("Player 2's Cards: ");
			opponentsWinsLabel.setText("Player 2's Won Cards: ");
			playersWinsLabel.setText("Player 1's Won Cards: ");
			playersCardsLabel.setText("Player 1's Cards: ");
		}

		// Shuffle deck 100 times
		for(int i=0; i <100; i++){
			deck.shuffleOrder();
		}
		deck.distributeEvenly(new PlayerHand[]{opponentsHand,playersHand});

		setLayout(new BorderLayout());

		JPanel howManyContainersDoINeed = new JPanel(new BorderLayout());
		opponentsCardsNumber.setText(opponentsHand.numberOfCardsInHand() + " cards");
		opponentsCardsNumber.setFont(new Font("Times", Font.BOLD, 20));

		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(opponentsCardsLabel, BorderLayout.WEST);
		panel1.add(opponentsCardsNumber, BorderLayout.EAST);

		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(opponentsWinsLabel,BorderLayout.WEST);
		panel2.add(opponentsWinsNumber,BorderLayout.EAST);

		howManyContainersDoINeed.add(panel1,BorderLayout.PAGE_START);
		howManyContainersDoINeed.add(panel2,BorderLayout.PAGE_END);
		howManyContainersDoINeed.setBorder(new EmptyBorder(5, 5, 5, 5));

		add(howManyContainersDoINeed,BorderLayout.PAGE_START);

		JPanel howManyContainersDoINeed2 = new JPanel(new BorderLayout());
		playersCardsNumber.setText(opponentsHand.numberOfCardsInHand() + " cards");
		playersCardsNumber.setFont(new Font("Times", Font.BOLD, 20));

		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(playersWinsLabel,BorderLayout.WEST);
		panel4.add(playersWinsNumber,BorderLayout.EAST);

		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add(playersCardsLabel, BorderLayout.WEST);
		panel3.add(playersCardsNumber, BorderLayout.EAST);

		howManyContainersDoINeed2.add(panel4,BorderLayout.PAGE_START);
		howManyContainersDoINeed2.add(panel3,BorderLayout.PAGE_END);
		howManyContainersDoINeed2.setBorder(new EmptyBorder(5, 5, 5, 5));

		add(howManyContainersDoINeed2,BorderLayout.PAGE_END);

		opponentsCardPileLabel.setBorderPainted(false);
		opponentsCardPileLabel.addActionListener(this);
		playersCardPileLabel.setBorderPainted(false);
		playersCardPileLabel.addActionListener(this);
		opponentsPlayedCardLabel.setIcon(GamemodeChooser.resizeIcon(blankCardIcon, 60, 80));
		playersPlayedCardLabel.setIcon(GamemodeChooser.resizeIcon(blankCardIcon, 60, 80));
		opponentsCardPileLabel.setIcon(GamemodeChooser.resizeIcon(backOfCardIcon, 60, 80));
		playersCardPileLabel.setIcon(GamemodeChooser.resizeIcon(backOfCardIcon, 60, 80));

		opponentsTiedCardsPile.setIcon(GamemodeChooser.resizeIcon(blankCardIcon, 60, 80));
		playersTiedCardsPile.setIcon(GamemodeChooser.resizeIcon(blankCardIcon, 60, 80));
		currentTiedCardsIcon = blankCardIcon;

		randomPanel.add(opponentsCardPileLabel);
		for(int i = 0;i<14;i++){
			if(i+2==6){
				randomPanel.add(opponentsPlayedCardLabel);
			}else if(i+2==11){
				randomPanel.add(playersPlayedCardLabel);
			}else if(i+2==14){
				randomPanel.add(playersTiedCardsPile);
			}else if(i+2==3){
				randomPanel.add(opponentsTiedCardsPile);
			}
			else{
				randomPanel.add(new JLabel());
			}

		}
		randomPanel.add(playersCardPileLabel);

		add(randomPanel,BorderLayout.CENTER);

		randomPanel.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				opponentsCardPileLabel.setIcon(GamemodeChooser.resizeIcon(backOfCardIcon,
					  opponentsCardPileLabel.getWidth(),
					  opponentsCardPileLabel.getHeight()));
				playersCardPileLabel.setIcon(GamemodeChooser.resizeIcon(backOfCardIcon,
					  playersCardPileLabel.getWidth(),
					  playersCardPileLabel.getHeight()));
				opponentsTiedCardsPile.setIcon(GamemodeChooser.resizeIcon(currentTiedCardsIcon,
					  opponentsCardPileLabel.getWidth(),
					  opponentsCardPileLabel.getHeight()));
				playersTiedCardsPile.setIcon(GamemodeChooser.resizeIcon(currentTiedCardsIcon,
					  opponentsCardPileLabel.getWidth(),
					  opponentsCardPileLabel.getHeight()));
				if(opponentsPlayedCard==null){
					opponentsPlayedCardLabel.setIcon(GamemodeChooser.resizeIcon(blankCardIcon,
					  playersCardPileLabel.getWidth(),
					  playersCardPileLabel.getHeight()));
				} else {
					opponentsPlayedCardLabel.setIcon(GamemodeChooser.resizeIcon(opponentsPlayedCard.getCardIcon(),
					  playersCardPileLabel.getWidth(),
					  playersCardPileLabel.getHeight()));
				}
				if(playersPlayedCard==null){
					playersPlayedCardLabel.setIcon(GamemodeChooser.resizeIcon(blankCardIcon,
					  playersCardPileLabel.getWidth(),
					  playersCardPileLabel.getHeight()));
				} else {
					playersPlayedCardLabel.setIcon(GamemodeChooser.resizeIcon(playersPlayedCard.getCardIcon(),
					  playersCardPileLabel.getWidth(),
					  playersCardPileLabel.getHeight()));
				}
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});

		pack();
		setMinimumSize(getSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setDownCards(){
			setIcons(true);

			opponentsPlayedCard = opponentsHand.getFirstCard();
			playersPlayedCard = playersHand.getFirstCard();

			try {
				opponentsHand.removeCard(opponentsPlayedCard);
				playersHand.removeCard(playersPlayedCard);
			} catch (Exception ex) {
			}

			setIcons(false);

			int opponentsCardNumericalValue = opponentsPlayedCard.getNumericalValue();
			int playersCardNumericalValue = playersPlayedCard.getNumericalValue();

			if(opponentsCardNumericalValue==1){
				opponentsCardNumericalValue = 14;
			}
			if(playersCardNumericalValue == 1){
				playersCardNumericalValue = 14;
			}

			System.out.println("Opponent: " + opponentsPlayedCard);
			System.out.println("Player: " + playersPlayedCard);

			if(opponentsCardNumericalValue>playersCardNumericalValue){
				opponentsWonCards.addCard(playersPlayedCard);
				opponentsWonCards.addCard(opponentsPlayedCard);
				opponentsWonCards.addCards(tieCards);
				tieCards.clear();
			}else if (opponentsCardNumericalValue<playersCardNumericalValue){
				playersWonCards.addCard(playersPlayedCard);
				playersWonCards.addCard(opponentsPlayedCard);
				playersWonCards.addCards(tieCards);
				tieCards.clear();
			}else if (opponentsCardNumericalValue==playersCardNumericalValue){
				gameState = 1;
			}
			opponentsWinsNumber.setText(opponentsWonCards.numberOfCardsInHand() + " cards");
			playersWinsNumber.setText(playersWonCards.numberOfCardsInHand() + " cards");
	}

	public void finishGame(){
		int opponentsWinsNumberInt = opponentsWonCards.numberOfCardsInHand();
		int playersWinsNumberInt = playersWonCards.numberOfCardsInHand();
		if(opponentsWinsNumberInt > playersWinsNumberInt){
			JOptionPane.showMessageDialog(null, getOpponentName() + " wins!");
			new GamemodeChooser();
			this.dispose();
		}else if(opponentsWinsNumberInt < playersWinsNumberInt){
			String name = getPlayerName();
			String message;
			if (name.equals("You")){
				message = "You win!";
			}else{
				message = "Player 1 wins!";
			}
			JOptionPane.showMessageDialog(null, message);
			new GamemodeChooser();
			this.dispose();
		}else{
			JOptionPane.showConfirmDialog(null, "Tie.");
			new GamemodeChooser();
			this.dispose();
		}
	}

	public void tie() throws Exception{
		numberOfTimesPressed++;
		if(numberOfTimesPressed <= 3){
			currentTiedCardsIcon = WarCardGame.getIconInJar("cards/pile-"+numberOfTimesPressed+".png");
			opponentsTiedCardsPile.setIcon(GamemodeChooser.resizeIcon(currentTiedCardsIcon,
				  opponentsTiedCardsPile.getWidth(),
				  opponentsTiedCardsPile.getHeight()));
			playersTiedCardsPile.setIcon(GamemodeChooser.resizeIcon(currentTiedCardsIcon,
				  playersTiedCardsPile.getWidth(),
				  playersTiedCardsPile.getHeight()));
			Card playerCardTemp = playersHand.getFirstCard();
			Card opponentCardTemp = opponentsHand.getFirstCard();
			tieCards.add(playerCardTemp);
			playersHand.removeCard(playerCardTemp);
			tieCards.add(opponentCardTemp);
			opponentsHand.removeCard(opponentCardTemp);
		}
		if(numberOfTimesPressed == 3){
			gameState = 0;
			numberOfTimesPressed = 0;
		}
	}

	void setIcons(boolean makeBlank){
		if(makeBlank){
			opponentsPlayedCardLabel.setIcon(GamemodeChooser.resizeIcon(blankCardIcon,
				  opponentsPlayedCardLabel.getWidth(),
				  opponentsPlayedCardLabel.getHeight()));
			playersPlayedCardLabel.setIcon(GamemodeChooser.resizeIcon(blankCardIcon,
				  playersPlayedCardLabel.getWidth(),
				  playersPlayedCardLabel.getHeight()));
			opponentsTiedCardsPile.setIcon(GamemodeChooser.resizeIcon(blankCardIcon,
				  opponentsTiedCardsPile.getWidth(),
				  opponentsTiedCardsPile.getHeight()));
			playersTiedCardsPile.setIcon(GamemodeChooser.resizeIcon(blankCardIcon,
				  playersTiedCardsPile.getWidth(),
				  playersTiedCardsPile.getHeight()));

		} else{
			opponentsPlayedCardLabel.setIcon(GamemodeChooser.resizeIcon(opponentsPlayedCard.getCardIcon(),
				  opponentsPlayedCardLabel.getWidth(),
				  opponentsPlayedCardLabel.getHeight()));
			playersPlayedCardLabel.setIcon(GamemodeChooser.resizeIcon(playersPlayedCard.getCardIcon(),
				  playersPlayedCardLabel.getWidth(),
				  playersPlayedCardLabel.getHeight()));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(opponentsHand.numberOfCardsInHand()>0&&playersHand.numberOfCardsInHand()>0){
			if(gameState == 1){
				try {
					tie();
				} catch (Exception ex) {
					Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
				}
			}else{
				setDownCards();
			}
		}else{
			if(gamemode==1){
				finishGame();
			}else{
				if((opponentsWonCards.numberOfCardsInHand()==0&&opponentsHand.numberOfCardsInHand()==0)
					  || (playersWonCards.numberOfCardsInHand()==0&&playersHand.numberOfCardsInHand()==0)){
					opponentsWonCards.addCards(opponentsHand.getHandArrayList());
					opponentsHand.clearHand();
					playersWonCards.addCards(playersHand.getHandArrayList());
					playersHand.clearHand();
					finishGame();
				}else{
					opponentsHand.addCards(opponentsWonCards.getHandArrayList());
					opponentsWonCards.clearHand();
					playersHand.addCards(playersWonCards.getHandArrayList());
					playersWonCards.clearHand();
					opponentsHand.shuffleHand();
					playersHand.shuffleHand();
				}
			}
		}
		opponentsCardsNumber.setText(opponentsHand.numberOfCardsInHand()+" cards");
		playersCardsNumber.setText(playersHand.numberOfCardsInHand()+" cards");
		opponentsWinsNumber.setText(opponentsWonCards.numberOfCardsInHand() + " cards");
		playersWinsNumber.setText(playersWonCards.numberOfCardsInHand() + " cards");
	}

	String getOpponentName(){
		if (players == 1){
			return "CPU";
		}if(players == 2){
			return "Player 2";
		} else {
			return "";
		}
	}

	String getPlayerName(){
		if (players == 1){
			return "You";
		}if(players == 2){
			return "Player 1";
		} else {
			return "";
		}
	}

}
