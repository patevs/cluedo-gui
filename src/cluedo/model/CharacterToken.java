package cluedo.model;

import java.util.ArrayList;
import java.util.List;

import cluedo.model.CluedoGame.Character;

public class CharacterToken extends Token {

	// field for the players unique ID
	private final int Uid;
	// stores the players chosen character token
	private final CluedoGame.Character character;
	// stores the players hand
	private List<Card> hand;
	// stores how many moves the player can make
	private int stepsRemaining;

	public CharacterToken(String name, int playerUID, String characterStr) {
		super(name);
		this.Uid = playerUID;
		this.character = getChar(characterStr);
		this.hand = new ArrayList<Card>();
	}

	/**
	 * Returns a cluedo character from a string or
	 * 	null if the character isn't recognised.
	 * @param str
	 * @return
	 */
	private CluedoGame.Character getChar(String str) {
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
		return res;
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
	public int getUid() { return Uid; }
	public CluedoGame.Character getCharacter() { return character; }
	public List<Card> getHand(){ return hand; }
	public void setHand(List<Card> newHand){ hand = newHand; }
	public void setStepsRemaining(int steps){ stepsRemaining = steps; }
	public int getStepsRemaining(){ return stepsRemaining; }
}
