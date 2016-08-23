package cluedo.model;

import java.util.ArrayList;
import java.util.List;

import cluedo.model.CluedoGame.Character;

/**
 * Character token class representing characters on the board
 * @author Patrick
 *
 */
public class CharacterToken extends Token {

	// field for the players unique ID
	private final int Uid;
	// stores the players chosen character token
	private CluedoGame.Character character;
	// stores the players hand
	private List<Card> hand;
	// stores how many moves the player can make
	private int stepsRemaining;

	/**
	 * Creates a character token representing a player on the board.
	 * @param name
	 * @param playerUID
	 * @param characterStr
	 */
	public CharacterToken(String name, int playerUID, String characterStr) {
		super(name);
		this.Uid = playerUID;
		character = null;
		setChar(characterStr);
		this.hand = new ArrayList<Card>();
	}

	/**
	 * Sets the character associated with this token.
	 * @param str
	 * @return
	 */
	private void setChar(String str) {
		Character res = null;
		switch(str.toUpperCase()){
			case "MISS SCARLETT":
				res = Character.MISS_SCARLETT;
				break;
			case "COLONEL MUSTARD":
				res = Character.COLONEL_MUSTARD;
				break;
			case "MRS WHITE":
				res = Character.MRS_WHITE;
				break;
			case "MRS PEACOCK":
				res = Character.MRS_PEACOCK;
				break;
			case "THE REVEREND GREEN":
				res = Character.THE_REVEREND_GREEN;
				break;
			case "PROFESSOR PLUM":
				res = Character.PROFESSOR_PLUM;
				break;
		}
		character = res;
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
}
