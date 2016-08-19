package cluedo.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cluedo.model.CharacterToken;

public class Suggestion extends JFrame implements ActionListener{
	String character;
	String weapon;
	String room;

	public Suggestion(){
		// Creating the content panel to contain the suggestion/accusation panel
		JPanel content = new JPanel();
		// setting the content layout and border
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.setBorder(new EmptyBorder(5,5,5,5));

		// Creating a panel for character, weapon, room selection
		JPanel charPnl = createCharacterBtnsPnl();
		JPanel weapPnl = createWeaponBtnsPnl();

		// adding the character, weapon, and room panel to the content panel
		content.add(charPnl);
		content.add(weapPnl);
		// display the content center window
		add(content, BorderLayout.CENTER);

		this.setVisible(true);
	}

	/**
	 * Creates and returns a panel containing the
	 * 	character radio buttons for the user to select.
	 * @return JPanel containing radiobuttons
	 */
	private JPanel createCharacterBtnsPnl() {
		// Creating a panel for character selection
		JPanel charPnl = new JPanel(new GridLayout(0,3));
		// setting the character panel border
		charPnl.setBorder(new EmptyBorder(0,5,0,5));

		// Creating a button group for the radio buttons
		ButtonGroup bg = new ButtonGroup();
		// Creating a button for each character in the game
		JRadioButton scarlett = new JRadioButton("Miss Scarlett");
		JRadioButton mustard = new JRadioButton("Colonel Mustard");
		JRadioButton white = new JRadioButton("Mrs White");
		JRadioButton green = new JRadioButton("The Reverend Green");
		JRadioButton peacock = new JRadioButton("Mrs Peacock");
		JRadioButton plum = new JRadioButton("Professor Plum");

		// Adding action commands to the buttons
		scarlett.setActionCommand("Miss Scarlett");
		mustard.setActionCommand("Colonel Mustard");
		white.setActionCommand("Mrs White");
		green.setActionCommand("The Reverend Green");
		peacock.setActionCommand("Mrs Peacock");
		plum.setActionCommand("Professor Plum");

		// Adding Action listeners
		scarlett.addActionListener(this);
		mustard.addActionListener(this);
		white.addActionListener(this);
		green.addActionListener(this);
		peacock.addActionListener(this);
		plum.addActionListener(this);

		// adding the character radio buttons the the button group
		bg.add(scarlett);
		bg.add(mustard);
		bg.add(white);
		bg.add(green);
		bg.add(peacock);
		bg.add(plum);

		// adding all the buttons to an array
		JRadioButton[] characterBtns = new JRadioButton[6];
		characterBtns[0] = scarlett;
		characterBtns[1] = mustard;
		characterBtns[2] = white;
		characterBtns[3] = green;
		characterBtns[4] = peacock;
		characterBtns[5] = plum;

		// adding each button to the character panel
		charPnl.add(plum);
		charPnl.add(peacock);
		charPnl.add(green);
		charPnl.add(white);
		charPnl.add(mustard);
		charPnl.add(scarlett);

		// return the panel
		return charPnl;
	}

	/**
	 * Creates and returns a panel containing the
	 * 	weapon radio buttons for the user to select.
	 * @return JPanel containing radiobuttons
	 */
	private JPanel createWeaponBtnsPnl() {
		// Creating a panel for weapon selection
		JPanel weapPnl = new JPanel(new GridLayout(0,3));
		// setting the weapon panel border
		weapPnl.setBorder(new EmptyBorder(0,5,0,5));

		// Creating a button group for the radio buttons
		ButtonGroup bg = new ButtonGroup();
		// Creating a button for each character in the game
		JRadioButton candlestick = new JRadioButton("Candlestick");
		JRadioButton dagger = new JRadioButton("Dagger");
		JRadioButton pipe = new JRadioButton("Lead Pipe");
		JRadioButton revolver = new JRadioButton("Revolver");
		JRadioButton rope = new JRadioButton("Rope");
		JRadioButton spanner = new JRadioButton("Spanner");

		// Adding action commands to the buttons
		candlestick.setActionCommand("Candlestick");
		dagger.setActionCommand("Dagger");
		pipe.setActionCommand("Lead Pipe");
		revolver.setActionCommand("Revolver");
		rope.setActionCommand("Rope");
		spanner.setActionCommand("Spanner");

		// Adding Action listeners
		candlestick.addActionListener(this);
		dagger.addActionListener(this);
		pipe.addActionListener(this);
		revolver.addActionListener(this);
		rope.addActionListener(this);
		spanner.addActionListener(this);

		// adding the weapon radio buttons the the button group
		bg.add(candlestick);
		bg.add(dagger);
		bg.add(pipe);
		bg.add(revolver);
		bg.add(rope);
		bg.add(spanner);

		// adding all the buttons to an array
		JRadioButton[] weaponBtns = new JRadioButton[6];
		weaponBtns[0] = candlestick;
		weaponBtns[1] = dagger;
		weaponBtns[2] = pipe;
		weaponBtns[3] = revolver;
		weaponBtns[4] = rope;
		weaponBtns[5] = spanner;

		// adding each button to the character panel
		weapPnl.add(candlestick);
		weapPnl.add(dagger);
		weapPnl.add(pipe);
		weapPnl.add(revolver);
		weapPnl.add(rope);
		weapPnl.add(spanner);

		// return the panel
		return weapPnl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String currentSelection = null;
		if(e.getSource() instanceof JRadioButton){
			currentSelection = e.getActionCommand();
		}
		if(e.getSource() instanceof JButton){
			if(currentSelection == null){
				JOptionPane.showMessageDialog(this, "Select a character, weapon and room token.",
		                "Alert", JOptionPane.ERROR_MESSAGE);
			}
			if(currentSelection != null){
				// return current selection
				dispose();
			}
		}
	}
}
