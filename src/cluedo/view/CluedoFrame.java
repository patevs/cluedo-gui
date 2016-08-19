package cluedo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import cluedo.control.Suggestion;

@SuppressWarnings("serial")
public class CluedoFrame extends JFrame {

	private static final String IMAGE_PATH = "images/";

	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private CluedoBoard board;

	public CluedoFrame(String boardFile){
		super("Cluedo");

		// setup menu
		initMenu();
		// setup game board
		initBoard(boardFile);
		// setup player UI
		initPlayerUI();

		// setting title
		setTitle("Cluedo Game");
		// set size
		setSize(700, 700);
		// set display location
		setLocationRelativeTo(null);
		// set close operation
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// pack to minimum size
		pack();
		// enforce minimum size
		setMinimumSize(getSize());
		// handles the closing of the game
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt){
                confirmExit();
            }
        });
	}

	/**
	 * Creates the menu bar for the game
	 */
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		ImageIcon iconExit = new ImageIcon(IMAGE_PATH + "exit.png");
		ImageIcon iconNew = new ImageIcon(IMAGE_PATH + "new.png");
		ImageIcon iconHelp = new ImageIcon(IMAGE_PATH + "help.png");
		// creating game and help menus
		JMenu menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);

		// creating the view help menu item
		JMenuItem hMenuItem = new JMenuItem("View Help", iconHelp);
		hMenuItem.setMnemonic(KeyEvent.VK_H);
		hMenuItem.setToolTipText("Click for Game Help");
		hMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// displays a help message to user
				displayHelp();
			}
		});
		// creating new game and exit menu items
		JMenuItem nMenuItem = new JMenuItem("New Game", iconNew);
		nMenuItem.setMnemonic(KeyEvent.VK_N);
		nMenuItem.setToolTipText("Click to start a new Game");
		nMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO close current game; start new game
			}
		});
		JMenuItem eMenuItem = new JMenuItem("Exit", iconExit);
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exit App");
		eMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}
		});
		// adding menu and help menus
		menu.add(nMenuItem);
		menu.addSeparator();
		menu.add(eMenuItem);
		help.add(hMenuItem);
		// adding menus to menubar
		menuBar.add(menu);
		menuBar.add(help);
		// set the menu bar
		setJMenuBar(menuBar);
	}

	/**
	 * Initialises the game board
	 * @param boardFile
	 */
	private void initBoard(String boardFile) {
		board = new CluedoBoard(boardFile, this);
		add(gui);
	}

	/**
	 * Initialises the game's player user interface
	 */
	private void initPlayerUI(){
		// Creating a panel to store the UI
		JPanel playerControls = new JPanel();
		playerControls.setBorder(
				   BorderFactory.createCompoundBorder(
						      BorderFactory.createEmptyBorder(0,12,2,12),
						      BorderFactory.createLineBorder(Color.BLACK, 1)
						   )
						);

		// Creating a panel to store the dice images and roll button
		JPanel rollPnl = new JPanel(new GridLayout(0,1,2,2));
		rollPnl.setBorder(new EmptyBorder(0,4,0,2));
		JPanel dicePnl = new JPanel();
		// Creating the dice images
		try {
			JLabel dice1 = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH + "dice3.png"))));
			dice1.setBorder(new LineBorder(Color.BLACK));
			JLabel dice2 = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH + "dice4.png"))));
			dice2.setBorder(new LineBorder(Color.BLACK));
			dicePnl.add(dice1);
			dicePnl.add(dice2);
		} catch (IOException e1) { e1.printStackTrace(); }
		// Adding dice and roll button to the roll panel
		JButton rollBtn = new JButton("Roll.");
		rollBtn.setEnabled(false);
		rollPnl.add(dicePnl);
		rollPnl.add(rollBtn);

		// Creating a panel to display game information
		JPanel gameInfoPnl = new JPanel();
		// Creating a text area to display game information to the user
		JTextArea gameTextArea = new JTextArea(4, 28);
		gameTextArea.setEditable(false);
		gameTextArea.setBorder(
				   BorderFactory.createCompoundBorder(
						      BorderFactory.createTitledBorder(new LineBorder(Color.BLACK,1),
						    		  "<html><b><u>GAME INFO</u></b></html>"), // using html tags to underline text
						      BorderFactory.createEmptyBorder(4,4,2,2)
						   )
						);
		// Adding the text area to the panel
		gameInfoPnl.add(gameTextArea, BorderLayout.CENTER);

		// Creating a panel to display the current players options
		JPanel gameOptionsPnl = new JPanel(new GridLayout(0,1,5,5));
		gameOptionsPnl.setBorder(new EmptyBorder(0,2,0,4));
		// Creating buttons for the options
		JButton suggestBtn = new JButton("Suggest / Accuse.");
		JButton endTurnBtn = new JButton("End Turn.");
		JButton quitBtn = new JButton("Quit.");
		// setting up buttons
//		suggestBtn.setEnabled(false);
		endTurnBtn.setEnabled(false);
		// adding action listeners to buttons
		suggestBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				suggest();
			}});

		quitBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmExit();
			}});
		// Adding the buttons to the panel
		gameOptionsPnl.add(suggestBtn);
		gameOptionsPnl.add(endTurnBtn);
		gameOptionsPnl.add(quitBtn);

		/* Testing adding images to the UI
		 *
		JPanel handPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		List<BufferedImage> cards = new ArrayList<BufferedImage>();
		//TODO: find images for cards
		try {
			cards.add(ImageIO.read(new File(IMAGE_PATH + "scarlett-card.png")));
			cards.add(ImageIO.read(new File(IMAGE_PATH + "scarlett-card.png")));
			cards.add(ImageIO.read(new File(IMAGE_PATH + "scarlett-card.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(BufferedImage img: cards){
			JLabel picLabel = new JLabel(new ImageIcon(img));
			handPnl.add(picLabel);
		}
		//playerControls.add(handPnl, BorderLayout.CENTER);
		 */

		// TODO: add dice
		// TODO: add options

		// adding the roll panel to the player controls panel
		playerControls.add(rollPnl, BorderLayout.WEST);
		// adding the game info panel to the player controls panel
		playerControls.add(gameInfoPnl, BorderLayout.CENTER);
		// adding the game options panel to the player controls UI
		playerControls.add(gameOptionsPnl, BorderLayout.EAST);
		// adding the player controls UI to the bottom of the window
		add(playerControls, BorderLayout.SOUTH); // adds playerUI to frame
	}

	/**
	 * Displays a suggestion dialogue.
	 */
	private void suggest(){
		new Suggestion();
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

	/**
	 * Displays a help dialog message to the player
	 */
	protected void displayHelp() {
		String msg = "-- Cluedo Game Help -- \n" + "Select New Game to restart the game." ;
		JFrame helpPanel = new JFrame();
		JPanel pnl = (JPanel) helpPanel.getContentPane();
		JOptionPane.showMessageDialog(pnl, msg,
                "Cluedo Game Guide", JOptionPane.INFORMATION_MESSAGE);
	}

	/*
	 * Getter methods
	 */
	public JPanel getGui() {
		return gui;
	}
	public CluedoBoard getBoard(){
		return board;
	}

}
