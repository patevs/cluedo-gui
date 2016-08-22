package cluedo.control;
import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cluedo.model.CharacterToken;
import cluedo.model.CluedoGame;
import cluedo.view.CluedoBoard;

/**
 * Sets up the Cluedo game and begins running it.
 * @author Patrick
 *
 */
public class Main {

	private static boolean gameOver; // checks if the game is over

	/**
	 * Method used for checking the program arguments
	 * @param args
	 */
	private static void checkArgs(String[] args) {
		// check number of arguments
		if (args.length != 1) {
			System.out.println("command: java TextClient gameBoard.txt");
			System.exit(1);
		}

		// get program argument (board file)
		String boardName = args[0];
		File file = new File("./" + boardName);

		// check the file exists
		if (!file.exists()) {
			System.out.println(boardName + " does not exist in current directory.");
			System.exit(2);
		}

		// check file type
		if (!boardName.toLowerCase().endsWith(".txt")) {
			System.out.println("Only .txt file is accepted.");
			System.exit(3);
		}
	}

	/**
	 * Initialises the Cluedo game
	 * @param parent frame
	 */
	protected static CluedoGame initGame(CluedoFrame frame) {
		// Display welcome dialog
		WelcomeDialog welcome = new WelcomeDialog(frame);
		welcome.setAlwaysOnTop(true);
		int numOfPlayers = welcome.showDialog();

		// Getting player information
		List<CharacterToken> players = new ArrayList<CharacterToken>();
		for(int i = 1; i<=numOfPlayers; i++){
			PlayerSetupDialog playerSetup = new PlayerSetupDialog(frame, i, players);
			playerSetup.setAlwaysOnTop(true);
			playerSetup.setVisible(false);
			players.add(playerSetup.showDialog());
		}
		
		// Setting up the game board
		CluedoBoard board = frame.getBoard();
		board.initPlayers(players);
		board.redraw();
		
		CluedoGame game = new CluedoGame(board, players);
		return game;
	}

	/**
	 * Main run method for the Cluedo game program
	 * @param args
	 */
	public static void main(String[] args){
		checkArgs(args);
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				CluedoFrame frame = new CluedoFrame(args[0]);
				frame.setVisible(true);
				CluedoGame game = initGame(frame);
				frame.setGame(game);
//				frame.repaint();
				// TODO something with the players
				
				frame.player = game.getActivePlayers().get(0); // first player
				if(!gameOver){
					// play game
				}
			}
		});
	}
}
