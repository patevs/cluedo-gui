package cluedo.control;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cluedo.view.CluedoFrame;

/**
 * This class creates a welcome message as a dialog.
 * 	The dialog welcomes the user(s) and asks for the
 * 	number of players in the game to be entered.
 * @author Patrick
 *
 */
@SuppressWarnings("serial")
public class WelcomeDialog extends JDialog {
	
	// Field for the number of players combobox and its result value 
	private JComboBox<Integer> numPlayersBox;
	private int result;

	public WelcomeDialog(CluedoFrame parent) {
		super(parent);
		
		initGUI();
		
		// make the dialog blocking (always on top)
		this.setModalityType(ModalityType.APPLICATION_MODAL);

		// set window title
        this.setTitle("Welcome");
        
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
		this.pack();
        // ensures the minimum size is enforced.
		this.setMinimumSize(this.getSize());
        // set close operation
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // set display position
        this.setLocationRelativeTo(getParent());
	}
	
	/**
	 * Displays the dialog and returns the result
	 * @return number of players
	 */
	public int showDialog(){
		this.setVisible(true);
		return result;
	}

	/**
	 * Creates the simple welcome message and asks for
	 *  number of players to be entered
	 */
	private void initGUI() {
		// Creating a header panel
		JPanel header = new JPanel(new GridLayout(0,1,5,5));
		header.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		// Creating a heading
		JLabel heading = new JLabel("Welcome to Cluedo");
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setBorder(new EmptyBorder(5,5,5,5));
		heading.setFont(new Font("Serif", Font.BOLD, 24));
		
		// Creating a subheading
		JLabel subheader = new JLabel("Begin by Entering Number of Players");
		subheader.setHorizontalAlignment(SwingConstants.CENTER);
		subheader.setFont(new Font("Serif", Font.BOLD, 18));
		
		// Creating a panel for getting number of players
		JPanel numPlayer = new JPanel();
		numPlayer.setBorder(new EmptyBorder(0, 10, 0, 10));
		numPlayer.setLayout(new BoxLayout(numPlayer, BoxLayout.X_AXIS));
		
		// Creating a combobox with options and an ok button
		// the combobox is a field so the value can be accessed
		Integer[] options = {3,4,5,6};
		numPlayersBox = new JComboBox<Integer>(options);
		numPlayersBox.setBorder(new EmptyBorder(10,10,10,10));
		JButton ok = new JButton("OK");
		// action listener added to the ok button
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// setting result
				result = (int) numPlayersBox.getSelectedItem();
				// closing the dialog
				dispose();
			}
		});
		
		// adding combobox and ok btn to numplayers panel 
		numPlayer.add(numPlayersBox);
		numPlayer.add(ok);
		
		// adding the heading, subheading, and numplayer panel to header
		header.add(heading);
		header.add(subheader);
		header.add(numPlayer);
		
		// display center window
		add(header, BorderLayout.CENTER);	
	}
}
