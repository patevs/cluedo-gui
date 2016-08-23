package cluedo.control;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import cluedo.model.Card;
import cluedo.model.CharRadioBtn;
import cluedo.model.CharacterToken;
import cluedo.model.Position;
import cluedo.model.RoomRadioBtn;
import cluedo.model.WeapRadioBtn;

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
	protected CluedoFrame frame;
	// a field for the selected crime evidence
	public String suspect;
	public String weapon;
	public String room;
	// an arrays of buttons
	private CharRadioBtn[] characterBtns;
	private WeapRadioBtn[] weaponBtns;
	private RoomRadioBtn[] roomBtns;
	// selected refute
	public Card refutedCard = null;
	private boolean refuted;
	public CharacterToken refuter;

	public Suggestion(CluedoFrame parent) {
		super(parent, "Suggestion", true);
        frame = parent;
        
        getRoom();
        
		initGUI();

		// make the dialog blocking (always on top)
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		// set window title
        this.setTitle("Enter Suggestion");
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
	 * Initilizes the player setup dialog GUI
	 */
	private void initGUI() {
		// adds elements
		createHeader();
		createPlayerGUI();
		createFooter();
		// sets position and size
		pack();
		this.setMinimumSize(this.getSize());
        this.setLocationRelativeTo(getParent());
        // make visible
		this.setVisible(true);
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
		else if(e.getSource() instanceof RoomRadioBtn){
			room = e.getActionCommand();
		}

		// get player's suggestion
		if(e.getSource() instanceof JButton){
			// check all elements selected
			if(suspect == null||suspect.length()<1){
				JOptionPane.showMessageDialog(this, "Select a suspect.",
		                "Alert", JOptionPane.ERROR_MESSAGE);
			}
			else if(weapon == null||weapon.length()<1){
				JOptionPane.showMessageDialog(this, "Select a weapon.",
		                "Alert", JOptionPane.ERROR_MESSAGE);
			}
			else if(room == null||suspect.length()<1){
				JOptionPane.showMessageDialog(this, "Select a room.",
		                "Alert", JOptionPane.ERROR_MESSAGE);
			}
			else{
				dispose();
				// gets player to move suspect and weapons into crime room
//				frame.moveSuggestionItems(suspect, weapon, room);
				// calls another class to handle refutations
				CharacterToken currentRefuter = refuter;
				while((refuter = nextRefuter())!= frame.player){
					Refutation refutation = new Refutation(refuter, frame, this, getSuggestion());
					currentRefuter = refuter;
					if(refuted()){
						break;
					}
				}
				result(currentRefuter);
			}
		}
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
		JLabel playerMsg = new JLabel("<html><b><u>" + frame.player.getCharacter().toString() + 
				": " + frame.player.getName() + "</u></b></html>");
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
		JPanel roomPnl = createRoomBtnsPnl();

		// adding the panels to the content panel
		content.add(charPnl);
		content.add(weapPnl);
		content.add(roomPnl);

		// display the content center window
		add(content, BorderLayout.CENTER);
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
	 * Returns a list of the player's suggestion.
	 * @return
	 */
	public List<String> getSuggestion(){
		List<String> suggestion = new ArrayList<String>();
		suggestion.add(suspect);
		suggestion.add(weapon);
		suggestion.add(room);
		return suggestion;
	}
	
	/**
	 * Returns a string representation of player's suggestion
	 * @return
	 */
	public String getPlayerSuggestion(){
		return "You accused " + suspect + " of committing\nthe crime with the " +
				weapon + "\nin the " + room;
	}
	
	private void getRoom(){
		Position p = frame.player.pos();
		room = frame.getBoard().tileAt(p).getName();
	}
	
	/**
	 * Returns string representation of refutation result
	 * @return
	 */
	public String getResult(){
		if(refuted){
			return "Your suggestion was refuted by " + refuter.getName() + "\nwho had " + refutedCard;
		}
		return "Your suggestion could not be refuted";
	}
	
	/**
	 * Returns true if another player refuted the suggestion.
	 * @param refuter
	 * @return
	 */
	private boolean refuted(){
		if(refutedCard==null){
			return false;
		}
		return true;
	}
	
	/**
	 * Communicates results to suggester.
	 * @param refuter
	 */
	private void result(CharacterToken refuter){
		if(refutedCard==null){
			String msg = "No one could refute your suggestion!" ;
			JOptionPane.showMessageDialog(this, msg);
			refuted = false;
			dispose();
		}
		else{
			String msg = refuter.getName() + " refuted your suggestion with the " + refutedCard.toString();
			JOptionPane.showMessageDialog(this, msg);
			refuted = true;
			dispose();
		}
	}
	
	/**
	 * Gets the next player to refute
	 */
	private CharacterToken nextRefuter(){
		if(refuter==null){
			// gets the player after current player
			if(frame.player.getUid()<frame.getPlayers().size()){
				refuter = frame.getPlayers().get(frame.player.getUid());
			}
			else{
				refuter = frame.getPlayers().get(0);
			}
		}
		else{
			// otherwise get next refuter
			if(refuter.getUid()<frame.getPlayers().size()){
				refuter = frame.getPlayers().get(refuter.getUid());
			}
			else{
				refuter = frame.getPlayers().get(0);
			}
		}
		return refuter;
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

	/*---------------------------------------------------
	 * Buttons for suspect, weapon, crime scene selection
	 --------------------------------------------------*/

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

		// sets a title for this panel
		TitledBorder border = new TitledBorder("Suspects");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
	    charPnl.setBorder(border);

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
		scarlett.setActionCommand("MISS SCARLETT");
		mustard.setActionCommand("COLONEL MUSTARD");
		white.setActionCommand("MRS WHITE");
		green.setActionCommand("THE REVEREND GREEN");
		peacock.setActionCommand("MRS PEACOCK");
		plum.setActionCommand("PROFESSOR PLUM");

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
		charPnl.add(scarlett);
		charPnl.add(mustard);
		charPnl.add(white);
		charPnl.add(green);
		charPnl.add(peacock);
		charPnl.add(plum);

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

		// sets a title for this panel
		TitledBorder border = new TitledBorder("Weapons");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
	    weapPnl.setBorder(border);

		// Creating a button group for the radio buttons
		ButtonGroup bg = new ButtonGroup();
		// Creating a button for each weapon in the game
		WeapRadioBtn candlestick = new WeapRadioBtn("Candlestick");
		WeapRadioBtn knife = new WeapRadioBtn("Knife");
		WeapRadioBtn pipe = new WeapRadioBtn("Lead Pipe");
		WeapRadioBtn revolver = new WeapRadioBtn("Revolver");
		WeapRadioBtn rope = new WeapRadioBtn("Rope");
		WeapRadioBtn wrench = new WeapRadioBtn("Wrench");

		// Adding action commands to the buttons
		candlestick.setActionCommand("CANDLESTICK");
		knife.setActionCommand("KNIFE");
		pipe.setActionCommand("LEAD PIPE");
		revolver.setActionCommand("REVOLER");
		rope.setActionCommand("ROPE");
		wrench.setActionCommand("WRENCH");

		// Adding Action listeners
		candlestick.addActionListener(this);
		knife.addActionListener(this);
		pipe.addActionListener(this);
		revolver.addActionListener(this);
		rope.addActionListener(this);
		wrench.addActionListener(this);

		// adding the weapon radio buttons the the button group
		bg.add(candlestick);
		bg.add(knife);
		bg.add(pipe);
		bg.add(revolver);
		bg.add(rope);
		bg.add(wrench);

		// adding all the buttons to an array
		weaponBtns = new WeapRadioBtn[6];
		weaponBtns[0] = candlestick;
		weaponBtns[1] = knife;
		weaponBtns[2] = pipe;
		weaponBtns[3] = revolver;
		weaponBtns[4] = rope;
		weaponBtns[5] = wrench;

		// adding each button to the weapon panel
		weapPnl.add(candlestick);
		weapPnl.add(knife);
		weapPnl.add(pipe);
		weapPnl.add(revolver);
		weapPnl.add(rope);
		weapPnl.add(wrench);

		// return the panel
		return weapPnl;
	}

	/**
	 * Creates and returns a panel containing the
	 * 	room radio buttons for the user to select.
	 * @return JPanel containing radiobuttons
	 */
	private JPanel createRoomBtnsPnl() {
		// Creating a panel for room selection
		JPanel roomPnl = new JPanel(new GridLayout(0,3));
		// setting the room panel border
		roomPnl.setBorder(
		   BorderFactory.createCompoundBorder(
				      BorderFactory.createLineBorder(Color.GRAY, 1),
				      BorderFactory.createEmptyBorder(4, 2, 4, 1)
				   )
				);

		// sets a title for this panel
		TitledBorder border = new TitledBorder("Rooms");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
	    roomPnl.setBorder(border);

		// Creating a button group for the radio buttons
		ButtonGroup bg = new ButtonGroup();
		// Creating a button for each room in the game
		RoomRadioBtn kitchen = new RoomRadioBtn("Kitchen");;
		RoomRadioBtn ballroom = new RoomRadioBtn("Ball Room");
		RoomRadioBtn conservatory = new RoomRadioBtn("Conservatory");
		RoomRadioBtn billiardRoom = new RoomRadioBtn("Billiard Room");
		RoomRadioBtn library = new RoomRadioBtn("Library");
		RoomRadioBtn study = new RoomRadioBtn("Study");
		RoomRadioBtn hall = new RoomRadioBtn("Hall");
		RoomRadioBtn lounge = new RoomRadioBtn("Lounge");
		RoomRadioBtn diningRoom = new RoomRadioBtn("Dining Room");

//		// Adding action commands to the buttons
//		kitchen.setActionCommand("KITCHEN");
//		ballroom.setActionCommand("BALL ROOM");
//		conservatory.setActionCommand("CONSERVATORY");
//		billiardRoom.setActionCommand("BILLIARD ROOM");
//		library.setActionCommand("LIBRARY");
//		study.setActionCommand("STUDY");
//		hall.setActionCommand("HALL");
//		lounge.setActionCommand("LOUNGE");
//		diningRoom.setActionCommand("DINING ROOM");
//
//		// Adding Action listeners
//		kitchen.addActionListener(this);
//		ballroom.addActionListener(this);
//		conservatory.addActionListener(this);
//		billiardRoom.addActionListener(this);
//		library.addActionListener(this);
//		study.addActionListener(this);
//		hall.addActionListener(this);
//		lounge.addActionListener(this);
//		diningRoom.addActionListener(this);

		kitchen.setEnabled(false);
		ballroom.setEnabled(false);
		conservatory.setEnabled(false);
		billiardRoom.setEnabled(false);
		library.setEnabled(false);
		study.setEnabled(false);
		hall.setEnabled(false);
		lounge.setEnabled(false);
		diningRoom.setEnabled(false);
		
		// adding the room radio buttons the the button group
		bg.add(kitchen);
		bg.add(ballroom);
		bg.add(conservatory);
		bg.add(billiardRoom);
		bg.add(library);
		bg.add(study);
		bg.add(hall);
		bg.add(lounge);
		bg.add(diningRoom);

		// adding all the buttons to an array
		roomBtns = new RoomRadioBtn[9];
		roomBtns[0] = kitchen;
		roomBtns[1] = ballroom;
		roomBtns[2] = conservatory;
		roomBtns[3] = billiardRoom;
		roomBtns[4] = library;
		roomBtns[5] = study;
		roomBtns[6] = hall;
		roomBtns[7] = lounge;
		roomBtns[8] = diningRoom;


		// adding each button to the room panel
		roomPnl.add(kitchen);
		roomPnl.add(ballroom);
		roomPnl.add(conservatory);
		roomPnl.add(billiardRoom);
		roomPnl.add(library);
		roomPnl.add(study);
		roomPnl.add(hall);
		roomPnl.add(lounge);
		roomPnl.add(diningRoom);
		
		// return the panel
		return roomPnl;
	}
}
