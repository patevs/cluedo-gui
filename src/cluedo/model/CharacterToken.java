package cluedo.model;

import java.util.ArrayList;
import java.util.List;

public class CharacterToken extends Token {
	
	// field for the players unique ID
	private final int Uid;
	// stores the players chosen character token string
	private final String characterStr;
	// stores the players hand
	private List<Card> hand;

	public CharacterToken(String name, int playerUID, String characterStr) {
		super(name);
		this.Uid = playerUID;
		this.characterStr = characterStr;
		this.hand = new ArrayList<Card>();
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
	public String getCharacterStr() { return characterStr; }
	public List<Card> getHand(){ return hand; }
	public void setHand(List<Card> newHand){ this.hand = newHand; }
}
