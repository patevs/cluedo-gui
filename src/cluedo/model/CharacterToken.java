package cluedo.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import cluedo.model.CluedoGame.Character;
import cluedo.view.RoomTile;
import cluedo.view.Tile;

/**
 * Character token class representing characters on the board
 * @author Patrick
 *
 */
public class CharacterToken extends Token {
	private static final String IMAGE_PATH = "images/";

	// field for the players unique ID
	private final int Uid;
	// stores the players chosen character token
	private CluedoGame.Character character;
	// stores the players hand
	private List<Card> hand;
	// stores how many moves the player can make
	private int stepsRemaining;
	// stores basic image associated with this character token
	private BufferedImage image;
	
	public boolean suggested;
	public boolean active;

	/**
	 * Creates a character token representing a player on the board.
	 * @param name
	 * @param playerUID
	 * @param characterStr
	 */
	public CharacterToken(String name, int playerUID, String characterStr) {
		super(name);
		Uid = playerUID;
		character = null;
		setChar(characterStr);
		hand = new ArrayList<Card>();
		suggested = false;
		active = true;
	}

	/**
	 * Sets the character associated with this token.
	 * @param str
	 * @return
	 */
	private void setChar(String str) {
		Character res = null;
		try{
			switch(str.toUpperCase()){
				case "MISS SCARLETT":
					res = Character.MISS_SCARLETT;	
					image = ImageIO.read(new File(IMAGE_PATH + "scarlett.png"));
					break;
				case "COLONEL MUSTARD":
					res = Character.COLONEL_MUSTARD;
					image = ImageIO.read(new File(IMAGE_PATH + "mustard.png"));
					break;
				case "MRS WHITE":
					res = Character.MRS_WHITE;
					image = ImageIO.read(new File(IMAGE_PATH + "white.png"));
					break;
				case "MRS PEACOCK":
					res = Character.MRS_PEACOCK;
					image = ImageIO.read(new File(IMAGE_PATH + "peacock.png"));
					break;
				case "THE REVEREND GREEN":
					res = Character.THE_REVEREND_GREEN;
					image = ImageIO.read(new File(IMAGE_PATH + "green.png"));
					break;
				case "PROFESSOR PLUM":
					res = Character.PROFESSOR_PLUM;
					image = ImageIO.read(new File(IMAGE_PATH + "plum.png"));
					break;
			}
			character = res;
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Adds a card to the players hand
	 * @param card
	 */
	public void addCard(Card card) {
		hand.add(card);
	}

	/*
	 * Getter and Setter methods
	 */
	/**
	 * Returns the uid of this player.
	 * @return
	 */
	public int getUid() { return Uid; }
	/**
	 * Returns the character associated with this character token.
	 * @return
	 */
	public CluedoGame.Character getCharacter() { return character; }
	/**
	 * Returns all the cards in player's hand.
	 * @return
	 */
	public List<Card> getHand(){ return hand; }
	/**
	 * Sets the cards in this hand.
	 * @param newHand
	 */
	public void setHand(List<Card> newHand){ hand = newHand; }
	/**
	 * Sets the amount of steps the player can move.
	 * @param steps
	 */
	public void setStepsRemaining(int steps){ stepsRemaining = steps; }
	/**
	 * Returns the amount of steps the player can move.
	 * @return
	 */
	public int getStepsRemaining(){ return stepsRemaining; }
	/**
	 * Returns the image associated with this character token.
	 * @return
	 */
	public BufferedImage image(){ return image; }
	
	public boolean inRoom(){
		Tile t = this.getTile();
		if(t == null) return false;
		if(t instanceof RoomTile){
			return true;
		}
		return false;
	}
}
