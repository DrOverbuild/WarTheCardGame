/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package warcardgame.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import warcardgame.WarCardGame;

/**
 *
 * @author jasper
 */
public class GamemodeChooser extends JFrame implements ActionListener{

		public int gamemode;
		public int players;

		ImageIcon logo = WarCardGame.getIconInJar("logo2000x1111.png");
		JLabel thisIsALabelThatWillContainTheLogo = new JLabel(logo);

		JButton gamemodeEndlessButton = new JButton("");
		JButton gamemodeQuickButton = new JButton("");
		JButton playAgainstPlayer = new JButton("");
		JButton playAgainstCPU = new JButton("");
		JLabel label = new JLabel("Select Gamemode:");

		/**
		 * Container that holds buttonsPanel and freakingJPanel.
		 */
		JPanel iCurrentlyHaveNoCreativityForVariableNames = new JPanel(new BorderLayout());
		/**
		 * Container that holds buttons.
		 */
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		/**
		 * Container that holds the label to tell user what to do.
		 */
		JPanel freakingJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

	public GamemodeChooser(){
		super("War!");
		setLayout(new BorderLayout());
		setBackground(new Color(111, 111, 111));

		setupButton(gamemodeEndlessButton, "endlessGamemodeButton", "e");
		setupButton(gamemodeQuickButton, "quickGamemodeButton", "q");
		setupButton(playAgainstCPU, "playAgainstCPUButton", "cpu");
		setupButton(playAgainstPlayer, "playAgainstPlayerButton", "player");


		buttonsPanel.add(gamemodeEndlessButton);
		buttonsPanel.add(gamemodeQuickButton);

		iCurrentlyHaveNoCreativityForVariableNames.add(buttonsPanel,BorderLayout.CENTER);
		freakingJPanel.add(label);
		iCurrentlyHaveNoCreativityForVariableNames.add(freakingJPanel,BorderLayout.NORTH);

		thisIsALabelThatWillContainTheLogo.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				ImageIcon temp = resizeIcon(logo,
					  thisIsALabelThatWillContainTheLogo.getWidth(),
					  thisIsALabelThatWillContainTheLogo.getHeight());
				thisIsALabelThatWillContainTheLogo.setIcon(temp);

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
		add(thisIsALabelThatWillContainTheLogo,BorderLayout.CENTER);
		add(iCurrentlyHaveNoCreativityForVariableNames,BorderLayout.SOUTH);

		ImageIcon icon = resizeIcon(logo, 400, 150);
		thisIsALabelThatWillContainTheLogo.setIcon(icon);

		pack();
		setMinimumSize(getSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	void setupButton(JButton button, String icon, String actionCommand){
		button.setBorderPainted(false);
		button.setIcon(WarCardGame.getIconInJar("buttons/"+icon+".png"));
		button.setPressedIcon(WarCardGame.getIconInJar("buttons/"+icon+"Pressed.png"));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addActionListener(this);
		button.setActionCommand(actionCommand);
	}

	void removeButtonThenAddOne(JButton buttonToRemove, JButton buttonToAdd){
		buttonsPanel.remove(buttonToRemove);
		buttonsPanel.add(buttonToAdd);
		pack();
		setMinimumSize(getMinimumSize());
	}

	public static ImageIcon resizeIcon(ImageIcon icon, int width, int height){
		ImageIcon temp = icon;
		Image scaledInstance = temp.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		temp = new ImageIcon(scaledInstance);
		return temp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
			case "e":
				gamemode = 0;
				removeButtonThenAddOne(gamemodeEndlessButton, playAgainstCPU);
				removeButtonThenAddOne(gamemodeQuickButton, playAgainstPlayer);
				label.setText("Select number of players:");
				break;
			case "q":
				gamemode = 1;
				removeButtonThenAddOne(gamemodeEndlessButton, playAgainstCPU);
				removeButtonThenAddOne(gamemodeQuickButton, playAgainstPlayer);
				label.setText("Select number of players:");
				break;
			case "cpu":
				players = 1;
				new Game(gamemode, players);
				this.dispose();
				break;
			case "player":
				players = 2;
				new Game(gamemode, players);
				this.dispose();
				break;
		}

	}

}
