package cluedo.control;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import cluedo.model.CharacterToken;
import cluedo.view.CluedoFrame;

/**
 * This Class creates a custom JDialog which gets
 * 	the player infomation for the Cluedo game from the users.
 *  
 * @author Patrick
 *
 */
@SuppressWarnings("serial")
public class PlayerSetupDialog extends JDialog implements ActionListener {
	
	// respresents the unique player id
	private final int playerUID;
	// represents a list of current players
	private List<CharacterToken> players;
	// a field for the user to enter their name
	private JTextField nameField;
	// a field for the currently selected character
	private String currentSelection;
	// represents the resulting plater token
	private CharacterToken player;
	// an array of character buttons
	private JRadioButton[] characterBtns;
	
	public PlayerSetupDialog(CluedoFrame parent, int playerUid, List<CharacterToken> curPlayers) {
		super(parent, "Player number " + playerUid + " setup", true);
		
		this.playerUID = playerUid;
		this.players = curPlayers;

		initGUI();
		
		// make the dialog blocking (always on top)
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		// set window title
        this.setTitle("Enter Player Information");
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
		this.pack();
        // ensures the minimum size is enforced.
		this.setMinimumSize(this.getSize());
		// set close operation
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // set position
        this.setLocationRelativeTo(getParent());
	}

	/**
	 * Displays the dialog and returns the result
	 * @return number of players
	 */
	public CharacterToken showDialog(){
		this.setVisible(true);
		return player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		if(e.getSource() instanceof JRadioButton){
			currentSelection = e.getActionCommand();
		}
		if(e.getSource() instanceof JButton){
			if(currentSelection == null || name.length() == 0){
				JOptionPane.showMessageDialog(this, "Enter a valid name & select a character token.",
		                "Alert", JOptionPane.ERROR_MESSAGE);
			}
			if(name.length() > 0 && currentSelection != null){
				player = new CharacterToken(name, playerUID, currentSelection);
				dispose();
			}
		}
	}

	/**
	 * Initilizes the player setup dialog GUI
	 */
	private void initGUI() {
		createHeader();
		createPlayerGUI();
		createFooter();
	}
	
	/**
	 * This method create the header for this dialog UI
	 */
	private void createHeader() {
		// Creating a header panel
		JPanel header = new JPanel();
		// Setting header layout and border
		header.setLayout(new BoxLayout(header, BoxLayout.PAGE_AXIS));
		header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		// Creating player and info message labels
		// using html tags to underline text
		JLabel playerMsg = new JLabel("<html><b><u>Player " + playerUID  + ".</u></b></html>");
		JLabel infoMsg = new JLabel("Enter your name & select a character token.");
		
		// Setting labels font, border, and alignments
		playerMsg.setFont(new Font("Serif", Font.BOLD, 22));
		playerMsg.setBorder(new EmptyBorder(5,20,5,5));
		infoMsg.setHorizontalAlignment(SwingConstants.CENTER);
		infoMsg.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
		infoMsg.setBorder(new EmptyBorder(0,10,0,15));
		
		// adding labels to header panel
		header.add(playerMsg);
		header.add(infoMsg);
		// display header at the north of window
		add(header, BorderLayout.NORTH);
	}

	/**
	 * This method creates the player infomation UI
	 */
	private void createPlayerGUI() {
		
		// Creating the content panel to contain the player setup UI
		JPanel content = new JPanel();
		// setting the content layout and border
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.setBorder(new EmptyBorder(5,5,5,5));
		
		// Creating a panel for name info
		JPanel namePnl = new JPanel();
		// Creating a name label
		JLabel nameLbl = new JLabel("Name: ");
		// initlizising the nameField for the player name
		nameField = new JTextField(20);
		// setting the name field border
		nameField.setBorder(
				   BorderFactory.createCompoundBorder(
						      BorderFactory.createLineBorder(Color.GRAY, 1),
						      BorderFactory.createEmptyBorder(4, 2, 4, 1)
						   )
						);
		// adding name label and text field to name panel
		namePnl.add(nameLbl, BorderLayout.WEST);
		namePnl.add(nameField, BorderLayout.CENTER);
		
		// Creating a panel for character selection
		JPanel charPnl = createCharacterBtnsPnl();
		
		// adding the name panel and character panel to the content panel
		content.add(namePnl);
		content.add(charPnl);
		// display the content center window
		add(content, BorderLayout.CENTER);
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
		characterBtns = new JRadioButton[6];
		characterBtns[0] = scarlett;
		characterBtns[1] = mustard;
		characterBtns[2] = white;
		characterBtns[3] = green;
		characterBtns[4] = peacock;
		characterBtns[5] = plum;
		
		// disabling chosen characters
		for(CharacterToken player : players){
			for(int i = 0; i<characterBtns.length; i++){
				if(player.getCharacterStr().equals(characterBtns[i].getText())){
					characterBtns[i].setEnabled(false);
				}
			}
		}
		
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
	 * This method creates the footer for the player setup gui.
	 * 	The footer contains an ok button which validates the
	 * 	users input.
	 */
	private void createFooter() {
		// Creating a panel for the footer
		JPanel footer = new JPanel();
		// Setting the layout and border of the panel
		footer.setLayout(new BoxLayout(footer, BoxLayout.LINE_AXIS));
		footer.setBorder(new EmptyBorder(5,5,5,5));
		
		// Creating an ok button
		JButton OKbtn = new JButton("OK");
		// Adding an action listener to the button
		OKbtn.addActionListener(this);
		
		// Adding the button to the right side of the panel
		footer.add(Box.createHorizontalGlue());
		footer.add(OKbtn);
		
		// Display the footer south in the window
		add(footer, BorderLayout.SOUTH);
	}
}
