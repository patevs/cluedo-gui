package cluedo.view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
		
		// setting title
		setTitle("Cluedo Game");
		// set size
		setSize(700, 700);
		// set display location
		setLocationRelativeTo(null);
		// set close operation
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// pack to minimum size
		pack();
		// enforce minimum size
		setMinimumSize(getSize());
	}

	/**
	 * Creating the menu bar for the game
	 */
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();	
		ImageIcon iconExit = new ImageIcon(IMAGE_PATH + "exit.png");
		ImageIcon iconNew = new ImageIcon(IMAGE_PATH + "new.png");
		// creating game and help menus
		JMenu menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		
		// creating the view help menu item
		JMenuItem hMenuItem = new JMenuItem("View Help");
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
	 * Displays dialog asking if user wants to exit the game
	 */
	protected void confirmExit() {
		String msg = "Are You Sure You Want to Exit the Game?" ;
		JFrame exitPanel = new JFrame();
		JPanel pnl = (JPanel) exitPanel.getContentPane();
		int result = JOptionPane.showConfirmDialog(pnl, msg,
		        "alert", JOptionPane.OK_CANCEL_OPTION);
		if(result==0){
			System.exit(0);
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
