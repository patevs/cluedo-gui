package cluedo.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import cluedo.model.Card;
import cluedo.model.CardRadioBtn;
import cluedo.model.CharacterToken;

/**
 * This class creates a custom JDialog which gets
 * 	the player's suggestion.
 *
 * @author Patrick and Maria
 *
 */
@SuppressWarnings("serial")
public class Refutation extends JDialog implements ActionListener {

	// reference to main frame
	private CluedoFrame frame;
	private Suggestion suggestFrame;
	// player's suggestion
	private List<String> suggestion;
	// selected refute
	private Card refuteCrd = null;
	// current refute
	public CharacterToken refuter;
	private JPanel handPnl;
	
	public Refutation(CharacterToken refuter, CluedoFrame parent, Suggestion suggestFrame, List<String> suggestion) {
		super(parent, "Refutation", true);
        frame = parent;
        this.suggestFrame = suggestFrame;
        this.suggestion = suggestion;
        this.refuter = refuter;
        
		initGUI();

		// make the dialog blocking (always on top)
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		// set window title
        this.setTitle("Refutation");
		// set close operation
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        // set position
        this.setLocationRelativeTo(getParent());
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
        // handles the user closing the window dialog
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt){
                confirmExit();
            }
        });
        // make visible
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			switch(e.getActionCommand()){
				case "noRefuteBtn":
					// if player has a refute card show a message
					if(canRefute()){
						String msg = "You Must Refute the Suggestion." ;
						JOptionPane.showMessageDialog(this, msg);
						break;
					}
					// otherwise next player
					else{
						dispose();
					}
					break;
				case "OKbtn":
					// if player can refute
					if(canRefute()){
						// asks player to pick a card
						if(refuteCrd==null){
							String msg = "Please select a card." ;
							JOptionPane.showMessageDialog(this, msg);
						}
						// checks player's selection correctly refutes the suggestion
						else if(suggestion.contains(refuteCrd.toString())){
							suggestFrame.refutedCard = refuteCrd;
							suggestFrame.refuter = refuter;
							dispose();
						}
						// player did not select a card that refutes the suggestion
						else{
							String msg = "Please select a card that refutes the suggestion." ;
							JOptionPane.showMessageDialog(this, msg);
						}
					}
					// player cannot refute
					else{
						String msg = "You cannot refute the suggestion." ;
						JOptionPane.showMessageDialog(this, msg);
						dispose();
					}
					break;
				default:
					confirmExit();
					break;
			}
		}
	}
	
	/**
	 * Checks if the player has a card that can refute the suggestion.
	 * @return
	 */
	private boolean canRefute(){
		for(Card c: refuter.getHand()){
			if(suggestion.contains(c.toString())){
				return true;
			}
		}
		return false;
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
		JLabel playerMsg = new JLabel("<html><b><u>" + refuter.getName() + ": Can You Refute the Suggestion?</u></b></html>");
		JLabel infoMsg = new JLabel(frame.player.getName() + " accused " + suggestion.get(0) +
				" of committing the crime with the " + suggestion.get(1) + " in the " + 
				suggestion.get(2));

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
		handPnl = initHandPnl();

		// adding the panels to the content panel
		content.add(handPnl);

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
		JButton noRefuteBtn = new JButton("Cannot Refute");
		noRefuteBtn.setActionCommand("noRefuteBtn");
		noRefuteBtn.addActionListener(this);

		// Creating an ok button
		JButton OKbtn = new JButton("OK");
		OKbtn.setActionCommand("OKbtn");
		// Adding an action listener to the button
		OKbtn.addActionListener(this);

		// Adding the button to the right side of the panel
		footer.add(Box.createHorizontalGlue());
		footer.add(noRefuteBtn);
		footer.add(OKbtn);

		// Display the footer south in the window
		add(footer, BorderLayout.SOUTH);
	}
	

	/**
	 * Displays other player's cards to refute.
	 * @param otherPlayer
	 * @return
	 */
	private JPanel initHandPnl(){
		JPanel handPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// setting the hand panel border
		handPnl.setBorder(
		   BorderFactory.createCompoundBorder(
				      BorderFactory.createLineBorder(Color.GRAY, 1),
				      BorderFactory.createEmptyBorder(4, 2, 4, 1)
				   )
				);

		// draws hand 
		JPanel hand = hand();
		handPnl.add(hand);
		
		return handPnl;
	}
	
	/**
	 * Returns a JPanel with the refuter's cards as buttons to select.
	 * @return
	 */
	private JPanel hand(){
		JPanel hand = new JPanel();
		for(Card c: refuter.getHand()){
			// makes a button with the card image
			CardRadioBtn cardBtn = new CardRadioBtn(c);
			cardBtn.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					refuteCrd = cardBtn.card();
				}
			});
			cardBtn.setActionCommand(c.toString());
			hand.add(cardBtn);
		}
		return hand;
	}
	
	/**
	 * Redraws the player controls panel.
	 * @param player
	 * @param newPlayer
	 */
	private void redraw(){
		remove(handPnl); // removes the existing hand panel
		initHandPnl(); // makes a new one
		revalidate(); // redraws the hand panel
	}

	/**
	 * Displays dialog asking if user wants to exit the game
	 */
	private void confirmExit() {
		String msg = "Are You Sure You Want to Exit the Game?" ;
		int result = JOptionPane.showConfirmDialog(this, msg,
		        "Alert", JOptionPane.OK_CANCEL_OPTION);
		if(result==0){
			System.exit(0);
			dispose();
		}
	}
}