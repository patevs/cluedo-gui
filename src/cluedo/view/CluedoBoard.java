package cluedo.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import cluedo.control.CluedoFrame;
import cluedo.model.CharacterToken;
import cluedo.model.CluedoGame.Room;
import cluedo.model.Position;
import cluedo.model.WeaponToken;

/**
 * This class represents the cluedo board for the game
 * @author Patrick
 *
 */
public class CluedoBoard {

	private static final String IMAGE_PATH = "images/";
	
	// Stores the height and width of the board
	private int HEIGHT = 0;
	private int WIDTH = 0;

	// The board is made up of a 2D array of Jbuttons
	private Tile[][] boardSquares = new Tile[22][22];
	// field to store the board panel
	private JPanel board;
	// field to stores the player starting tiles
	private List<HallwayTile> startTiles = new ArrayList<HallwayTile>();
	private List<WeaponToken> weaponTokens = new ArrayList<WeaponToken>();
	// reference to the gui
	private CluedoFrame parent;

	/**
	 * Creates the board from a file and a reference to the gui.
	 * @param boardFile
	 * @param frame
	 */
	public CluedoBoard(String boardFile, CluedoFrame frame) {
		this.parent = frame;
		// initialise the board squares from file
		initSquares(boardFile);
		// initialise the board
		initBoard();
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
			for(HEIGHT=0; scanner.hasNextLine(); HEIGHT++){
				// reading a line of text
				char[] line = scanner.nextLine().toCharArray();
				for(WIDTH=0; WIDTH < line.length; WIDTH++){
					// read the text character
					char c = line[WIDTH];
					Tile b;
					// If character is a digit, then make tile a player starting location
					if(Character.isDigit(c)){
						int digit = Character.digit(c, 10);
						// Create this tile as a player starting tile
						b = new HallwayTile('H');
						((HallwayTile) b).setStartCharacter(digit);
						startTiles.add((HallwayTile) b);
					} else {
						// get the tile represented by the character
						b = (Tile) getTile(c);
					}
					// set the tile on the board
					boardSquares[WIDTH][HEIGHT] = b;
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
	private void initBoard() {
		// Setting the parent frame border
		parent.getGui().setBorder(new EmptyBorder(6, 12, 6, 12));
		// Setting up the board
		board = new JPanel(new GridLayout(22, 22));
        board.setBorder(new LineBorder(Color.BLACK));
        // Adding the board to the frame
        parent.getGui().add(board);
        // Adding all the board squares to the board
        for (int ii = 0; ii < HEIGHT; ii++) {
            for(int jj = 0; jj < WIDTH; jj++) {
            	boardSquares[jj][ii].addMouseListener(parent);
            	boardSquares[jj][ii].setPos(new Position(jj,ii));
            	board.add(boardSquares[jj][ii]);
            }
        }
        
        //board.addMouseListener(frame);
		//board.addKeyListener(frame);
	}

	/**
	 * Initialises the players onto the board.
	 * @param list of players
	 */
	public void initPlayers(List<CharacterToken> players) {
		for(HallwayTile t: startTiles){
			for(CharacterToken p: players){
				if(t.getStartCharacter() != null){
					if(p.getCharacter().toString().equalsIgnoreCase(
							t.getStartCharacter().toString())){
						initCharacterTile(t, p.getCharacter().toString(), p.getName());
						//t.setCharacter(p);
						p.setTile(t);
						p.setPos(t.pos());
					}
				}
			}
		}
	}
	
	/**
	 * Add weapons to the board.
	 */
	public void initWeapons(){
		weaponTokens.add(new WeaponToken("CANDLESTICK"));
		weaponTokens.add(new WeaponToken("KNIFE"));
		weaponTokens.add(new WeaponToken("LEAD_PIPE"));
		weaponTokens.add(new WeaponToken("REVOLVER"));
		weaponTokens.add(new WeaponToken("ROPE"));
		weaponTokens.add(new WeaponToken("WRENCH"));
		placeWeapons();
	}
	
	/**
	 * Puts all the weapons in random rooms
	 */
	private void placeWeapons(){
		boolean finishedAll = false;
		List<Room> rooms = new ArrayList<Room>(Arrays.asList(
				Room.BALL_ROOM, Room.BILLIARD_ROOM, Room.CONSERVATORY,
				Room.DINING_ROOM, Room.HALL, Room.KITCHEN,
				Room.LIBRARY, Room.LOUNGE, Room.STUDY));
		WeaponToken weapon = weaponTokens.get(0);
		for(int i = 0; i < HEIGHT; i++){
			for(int j = 0; j < WIDTH; j++){
				if(boardSquares[i][j] instanceof RoomTile){
					RoomTile rTile = (RoomTile)boardSquares[i][j];
					if(rooms.contains(rTile.name())){
						rTile.setWeapon(weapon);
						weapon.setTile(rTile);
						rTile.setFancy();
						rooms.remove(rTile.name());
						int index = weaponTokens.indexOf(weapon);
						if(index < weaponTokens.size()-1)
							weapon = weaponTokens.get(index+1);
						else{
							finishedAll = true;
							break;
						}
					}
				}
			}
			if(finishedAll)
				break;
		}
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
	 * This method redraws the cluedoBoard on the parent
	 * 	CluedoFrame.
	 */
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
	
	/*
	 * Methods handeling the movement of players around the board.
	 */
	/**
	 * Checks if a player is able to make a move north
	 * @param player
	 * @return
	 */
	public boolean canMoveNorth(CharacterToken player){
		// check parameter
		if(player==null) return false;	
		// already in north most square
		if(player.y() - 1 < 0){
			return false;
		}
		
		// getting player position
		int xpos = player.x();
		int ypos = player.y();
		
		// Getting the north tile
		Tile tile = boardSquares[xpos][ypos-1];
		// Checking if the player can move to the tile
		if(tile instanceof OccupyableTile){
			if(!((OccupyableTile) tile).isOccupied()){
				return true;
			}
		}
		return false;
	}
	/**
	 * Moves a player north on the board
	 * @param player
	 */
	public void moveNorth(CharacterToken player){
		// Resetting the old tile
		player.getTile().reset();
		player.getTile().setCharacter(null);
		
		// Get the new tile position
		Position newPos = new Position(player.x(),player.y()-1);
		
		// Setting up the new tile
		OccupyableTile newTile = (OccupyableTile)boardSquares[newPos.getX()][newPos.getY()];
		initCharacterTile(newTile, player.getCharacter().toString(), player.getName());
		newTile.setCharacter(player);
		
		// Moving the player to the new position
		player.setStepsRemaining(player.getStepsRemaining() - 1);
		player.setPos(newPos);
		player.setTile((OccupyableTile) newTile);
	}
	/**
	 * Checks if a player can move east on the board
	 * @param player
	 * @return
	 */
	public boolean canMoveEast(CharacterToken player){
		// check parameter
		if(player==null) return false;	
		// already in east most square
		if(player.x() + 1 >= WIDTH){
			return false;
		}
		
		// Getting player position
		int xpos = player.x();
		int ypos = player.y();
		
		// Getting the new tile
		Tile tile = boardSquares[xpos+1][ypos];
		// Checking if the player can move to the tile
		if(tile instanceof OccupyableTile){
			if(!((OccupyableTile) tile).isOccupied()){
				return true;
			}
		}
		return false;
	}
	/**
	 * Moves a player east on the board
	 * @param player
	 */
	public void moveEast(CharacterToken player){
		// Resetting the old tile
		player.getTile().reset();
		player.getTile().setCharacter(null);
		
		// Getting the new tile position
		Position newPos = new Position(player.x()+1,player.y());
		
		// Getting the new tile
		OccupyableTile newTile = (OccupyableTile)boardSquares[newPos.getX()][newPos.getY()];
		initCharacterTile(newTile, player.getCharacter().toString(), player.getName());
		newTile.setCharacter(player);
		
		// Moving the player to the new tile
		player.setStepsRemaining(player.getStepsRemaining() - 1);
		player.setPos(newPos);
		player.setTile((OccupyableTile) newTile);
	}
	/**
	 * Checks if a player can move south on the board
	 * @param player
	 * @return
	 */
	public boolean canMoveSouth(CharacterToken player){
		// check parameter
		if(player==null) return false;	
		// already in south most square
		if(player.y() + 1 >= HEIGHT){
			return false;
		}
		
		// Getting player position
		int xpos = player.x();
		int ypos = player.y();
		
		// Getting the new tile
		Tile tile = boardSquares[xpos][ypos+1];
		// Checking if the player can move to the tile
		if(tile instanceof OccupyableTile){
			if(!((OccupyableTile) tile).isOccupied()){
				return true;
			}
		}
		return false;
	}
	/**
	 * Moves a player south on the board
	 * @param player
	 */
	public void moveSouth(CharacterToken player){
		// Resetting the old tile
		player.getTile().reset();
		player.getTile().setCharacter(null);
		
		// Getting the new tile position
		Position newPos = new Position(player.x(),player.y()+1);
		
		// Getting the new tile
		OccupyableTile newTile = (OccupyableTile)boardSquares[newPos.getX()][newPos.getY()];
		initCharacterTile(newTile, player.getCharacter().toString(), player.getName());
		newTile.setCharacter(player);
		
		// Moving the player to the new tile
		player.setStepsRemaining(player.getStepsRemaining() - 1);
		player.setPos(newPos);
		player.setTile((OccupyableTile) newTile);
	}
	/**
	 * Checks if a player can move west on the board
	 * @param player
	 * @return
	 */
	public boolean canMoveWest(CharacterToken player){
		// check parameter
		if(player==null) return false;	
		// already in south most square
		if(player.x() - 1 < 0){
			return false;
		}
		
		// Getting player position
		int xpos = player.x();
		int ypos = player.y();
		
		// Getting the new tile
		Tile tile = boardSquares[xpos-1][ypos];
		// Checking if the player can move to the tile
		if(tile instanceof OccupyableTile){
			if(!((OccupyableTile) tile).isOccupied()){
				return true;
			}
		}
		return false;
	}
	/**
	 * Moves a player west on the board
	 * @param player
	 */
	public void moveWest(CharacterToken player){
		// Resetting the old tile
		player.getTile().reset();
		player.getTile().setCharacter(null);
		
		// Getting the new tile position
		Position newPos = new Position(player.x()-1,player.y());
		
		// Getting the new tile
		OccupyableTile newTile = (OccupyableTile)boardSquares[newPos.getX()][newPos.getY()];
		initCharacterTile(newTile, player.getCharacter().toString(), player.getName());
		newTile.setCharacter(player);
		
		// Moving the player to the new tile
		player.setStepsRemaining(player.getStepsRemaining() - 1);
		player.setPos(newPos);
		player.setTile((OccupyableTile) newTile);
	}

	/** THIS DOESNT WORK
	 * TODO Fix or remove
	 * Returns the tile at a given position.
	 * @param p
	 * @return
	 */
	public Tile tileAt(Position p){
		if(p.getX() <= 0 || p.getX() >= 22 || p.getY() <= 0 || p.getY() >= 22)
			return null;
		return (Tile) boardSquares[p.getX()][p.getY()];
	}
}
