package cluedo.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import cluedo.control.CluedoFrame;
import cluedo.model.CharacterToken;
import cluedo.model.Position;

/**
 * This class represents the cluedo board for the the game
 * @author Patrick
 *
 */
public class CluedoBoard {

	private static final String IMAGE_PATH = "images/";

	// The board is made up of a 2D array of Jbuttons
	private JButton[][] boardSquares = new JButton[22][22];
	// field to store the board panel
	private JPanel board;
	// field to stores the player starting tiles
	private List<HallwayTile> startTiles = new ArrayList<HallwayTile>();

	private CluedoFrame parent;

	public CluedoBoard(String boardFile, CluedoFrame frame) {
		// initialise the board squares from file
		initSquares(boardFile);
		// initialise the board
		initBoard(frame);

		this.parent = frame;
	}

	/**
	 * Scans a text file and constructs a 2D array
	 * 	of board squares
	 * @param boardFile
	 */
	private void initSquares(String boardFile){
		Scanner scanner = null;
		try{
			// Creating the scanner on the board file
			scanner = new Scanner(new File(boardFile));
			for(int height=0; scanner.hasNextLine(); height++){
				// reading a line of text
				char[] line = scanner.nextLine().toCharArray();
				for(int width = 0; width < line.length; width++){
					// read the text character
					char c = line[width];
					JButton b;
					// If character is a digit, then make tile a player starting location
					if(Character.isDigit(c)){
						int digit = Character.digit(c, 10);
						// Create this tile as a player starting tile
						b = new HallwayTile('H');
						((HallwayTile) b).setStartCharacter(digit);
						startTiles.add((HallwayTile) b);
					} else {
						// get the tile represented by the character
						b = getTile(c);
					}
					// set the tile on the board
					boardSquares[width][height] = b;
				}
			}
		} catch(IOException e){
			// board reading failed
			System.out.println("Board file reading fail: " + e.getMessage());
		}
	}

	/**
	 * Initlises the cluedo board and adds it to
	 * 	the parent board frame
	 * @param frame
	 */
	private void initBoard(CluedoFrame frame) {
		// Setting the parent frame border
		frame.getGui().setBorder(new EmptyBorder(6, 12, 6, 12));
		// Setting up the board
		board = new JPanel(new GridLayout(22, 22));
        board.setBorder(new LineBorder(Color.BLACK));
        // Adding the board to the frame
        frame.getGui().add(board);
        // Adding all the board squares to the board
        for (int ii = 0; ii < boardSquares.length; ii++) {
            for(int jj = 0; jj < boardSquares[ii].length; jj++) {
            	board.add(boardSquares[jj][ii]);
            }
        }
        board.addMouseListener(frame);
		board.addKeyListener(frame);
	}

	/**
	 * Gets a board tile from a character
	 * @param c
	 * @return board tile
	 */
	private JButton getTile(char c) {
		JButton b = new JButton();
		switch(c){
		case 'C':
		case 'I':
		case 'L':
		case 'S':
		case 'B':
		case 'A':
		case 'K':
		case 'D':
		case 'O':
			b = new RoomTile(c);
			break;
		case 'd':
			b = new DoorwayTile(c);
			break;
		case 'X':
			b = new WallTile(c);
			break;
		case 'H':
			b = new HallwayTile(c);
			break;
		}
		// tiles are 24x24 px in size
		ImageIcon icon = new ImageIcon(
		                new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB));
		b.setIcon(icon);
		b.setPreferredSize(new Dimension(24, 24));
		return b;
	}

	/**
	 * Initlises the players onto the board.
	 * @param list of players
	 */
	public void initPlayers(List<CharacterToken> players) {
		for(HallwayTile t: startTiles){
			for(CharacterToken p: players){
				if(t.getStartCharacter() != null){
					if(p.getCharacter().toString().equalsIgnoreCase(
							t.getStartCharacter().toString())){
						initCharacterTile(t, p.getCharacter().toString(), p.getName());
						t.setCharacter(p);
					}
				}
			}
		}
		redraw();
	}
	
	public void redraw(){
		parent.getGui().removeAll();
		parent.getGui().revalidate();
		
		parent.getGui().add(board);
		parent.getGui().revalidate();
		parent.getGui().repaint();
	}

	/**
	 * This method creates the character token images and
	 *  sets the tool tip text.
	 * @param c
	 * @return board square jbutton
	 */
	private void initCharacterTile(JButton tile, String charName, String playerName) {

		switch(charName.toUpperCase()){
			case "MISS SCARLETT":
				tile.setIcon(new ImageIcon(IMAGE_PATH + "scarlett.png"));
				tile.setToolTipText(playerName + ": MISS SCARLETT");
				break;
			case "COLONEL MUSTARD":
				tile.setIcon(new ImageIcon(IMAGE_PATH + "mustard.png"));
				tile.setToolTipText(playerName + ": COLONEL MUSTARD");
				break;
			case "THE REVEREND GREEN":
				tile.setIcon(new ImageIcon(IMAGE_PATH + "green.png"));
				tile.setToolTipText(playerName + ": THE REVERENED GREEN");
				break;
			case "MRS PEACOCK":
				tile.setIcon(new ImageIcon(IMAGE_PATH + "peacock.png"));
				tile.setToolTipText(playerName + ": MRS PEACOCK");
				break;
			case "PROFESSOR PLUM":
				tile.setIcon(new ImageIcon(IMAGE_PATH + "plum.png"));
				tile.setToolTipText(playerName + ": PROFESSOR PLUM");
				break;
			case "MRS WHITE":
				tile.setIcon(new ImageIcon(IMAGE_PATH + "white.png"));
				tile.setToolTipText(playerName + ": MRS WHITE");
				break;
		}
	}

	/**
	 * Returns the tile at a given position.
	 * @param p
	 * @return
	 */
	public Tile tileAt(Position p){
		if(p.getX() < 0 || p.getX() >= 24 || p.getY() < 0 || p.getY() >= 24)
			return null;
		return (Tile) boardSquares[p.getY()][p.getX()];
	}
}
