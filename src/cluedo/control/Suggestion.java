package cluedo.control;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import cluedo.model.CharRadioBtn;
import cluedo.model.CharacterToken;
import cluedo.view.CluedoFrame;

/**
 * This class creates a custom JDialog which gets
 * 	the player's suggestion.
 *
 * @author Patrick and Maria
 *
 */
@SuppressWarnings("serial")
public class Suggestion extends JDialog implements ActionListener {

	// reference to main frame
	private CluedoFrame frame;
	// a field for the selected crime evidence
	private String suspect;
	private String weapon;
	private String room;
	// represents the resulting player token
	private CharacterToken suspectToken;
//	private WeaponToken weaponToken;
//	private Room roomToken;
	// an arrays of buttons
	private CharRadioBtn[] characterBtns;
	private WeapRadioBtn[] weaponBtns;
	private RoomRadioBtn[] roomBtns;

	public Suggestion(CluedoFrame parent) {
		super(parent, "Suggestion", true);

		initGUI();

		// make the dialog blocking (always on top)
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		//TODO: have option to suggest or accuse
		// set window title
        this.setTitle("Enter Suggestion");
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
		this.pack();
        // ensures the minimum size is enforced.
		this.setMinimumSize(this.getSize());
		// set close operation
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        // set position
        this.setLocationRelativeTo(getParent());
        // handles the user closing the window dialog
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt){
                confirmExit();
            }
        });
	}

	/**
	 * Displays the dialog and returns the result
	 * @return number of players
	 */
	public CharacterToken showDialog(){
		this.setVisible(true);
		return suspectToken;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if selected a suspect
		if(e.getSource() instanceof CharRadioBtn){
			suspect = e.getActionCommand();
		}
		// select a weapon
		else if(e.getSource() instanceof WeapRadioBtn){
			weapon = e.getActionCommand();
		}
		// select a room
		else{
			room = e.getActionCommand();
		}

		// check all elements selected
		if(e.getSource() instanceof JButton){
			if(suspect == null){
				JOptionPane.showMessageDialog(this, "Select a suspect.",
		                "Alert", JOptionPane.ERROR_MESSAGE);
			}
			else{
				for(CharacterToken t: frame.getPlayers()){
					if(suspect.equalsIgnoreCase(t.getName())){
							suspectToken = t;
							//TODO: after selecting suspect, select weapon
							dispose();
							return;
					}
				}
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
		setSize(700, 700);
		pack();
		showDialog();
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
		// TODO: find out which player is making a suggestion/accusation
		JLabel playerMsg = new JLabel("<html><b><u>Player.</u></b></html>");
		JLabel infoMsg = new JLabel("Make Your Suggestion.");

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
	 * This method creates the suggestion buttons.
	 */
	private void createPlayerGUI() {

		// Creating the content panel to contain the player setup UI
		JPanel content = new JPanel();
		// setting the content layout and border
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.setBorder(new EmptyBorder(5,5,5,5));

		// Creating a panel for suggestion selection
		JPanel charPnl = createCharacterBtnsPnl();
		JPanel weapPnl = createWeaponBtnsPnl();

		// adding the panels to the content panel
		content.add(charPnl);
		content.add(weapPnl);
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
		charPnl.setBorder(
		   BorderFactory.createCompoundBorder(
				      BorderFactory.createLineBorder(Color.GRAY, 1),
				      BorderFactory.createEmptyBorder(4, 2, 4, 1)
				   )
				);

		// Creating a button group for the radio buttons
		ButtonGroup bg = new ButtonGroup();
		// Creating a button for each character in the game
		CharRadioBtn scarlett = new CharRadioBtn("Miss Scarlett");
		CharRadioBtn mustard = new CharRadioBtn("Colonel Mustard");
		CharRadioBtn white = new CharRadioBtn("Mrs White");
		CharRadioBtn green = new CharRadioBtn("The Reverend Green");
		CharRadioBtn peacock = new CharRadioBtn("Mrs Peacock");
		CharRadioBtn plum = new CharRadioBtn("Professor Plum");

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
		characterBtns = new CharRadioBtn[6];
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

	private JPanel createWeaponBtnsPnl() {
		// Creating a panel for weapon selection
		JPanel weapPnl = new JPanel(new GridLayout(0,3));
		// setting the weapon panel border
		weapPnl.setBorder(
		   BorderFactory.createCompoundBorder(
				      BorderFactory.createLineBorder(Color.GRAY, 1),
				      BorderFactory.createEmptyBorder(4, 2, 4, 1)
				   )
				);

		// Creating a button group for the radio buttons
		ButtonGroup bg = new ButtonGroup();
		// Creating a button for each weapon in the game
		WeapRadioBtn candlestick = new WeapRadioBtn("Candlestick");
		WeapRadioBtn dagger = new WeapRadioBtn("Dagger");
		WeapRadioBtn pipe = new WeapRadioBtn("Lead Pipe");
		WeapRadioBtn revolver = new WeapRadioBtn("Revolver");
		WeapRadioBtn rope = new WeapRadioBtn("Rope");
		WeapRadioBtn spanner = new WeapRadioBtn("Spanner");

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
		weaponBtns = new WeapRadioBtn[6];
		weaponBtns[0] = candlestick;
		weaponBtns[1] = dagger;
		weaponBtns[2] = pipe;
		weaponBtns[3] = revolver;
		weaponBtns[4] = rope;
		weaponBtns[5] = spanner;

		// adding each button to the weapon panel
		weapPnl.add(candlestick);
		weapPnl.add(spanner);
		weapPnl.add(rope);
		weapPnl.add(revolver);
		weapPnl.add(pipe);
		weapPnl.add(dagger);

		// return the panel
		return weapPnl;
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

		// Creating cancel option
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}
		});

		// Creating an ok button
		JButton OKbtn = new JButton("OK");
		// Adding an action listener to the button
		OKbtn.addActionListener(this);

		// Adding the button to the right side of the panel
		footer.add(Box.createHorizontalGlue());
		footer.add(cancelBtn);
		footer.add(OKbtn);

		// Display the footer south in the window
		add(footer, BorderLayout.SOUTH);
	}

	/**
	 * Displays dialog asking if user wants to exit the game
	 */
	protected void confirmExit() {
		String msg = "Are You Sure You Want to Cancel Your Suggestion?" ;
		int result = JOptionPane.showConfirmDialog(this, msg,
		        "Alert", JOptionPane.OK_CANCEL_OPTION);
		if(result==0){
			dispose();
		}
	}
}