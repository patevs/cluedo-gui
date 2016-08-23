package cluedo.view;

import cluedo.model.CharacterToken;
import cluedo.model.CluedoGame;

@SuppressWarnings("serial")
public abstract class OccupyableTile extends Tile {

	// field to store the character occupying this tile or null if none
	private CharacterToken character = null;
	// field to store the weapon occupying this tile or null if none
	private CluedoGame.Weapon weapon = null;

	/**
	 * Creates an instance of a tile that can store one weapon or character at a time.
	 * @param symbol
	 */
	public OccupyableTile(char symbol) {
		super(symbol);
	}

	/**
	 * Returns true if this tile is current occupied
	 * @return
	 */
	public boolean isOccupied(){
		return (character!=null || weapon!=null);
	}

	/*
	 * Getter and Setter Methods
	 */
	/**
	 * Stores a character in this tile.
	 * @param newChar
	 * @return
	 */
	public void setCharacter(CharacterToken newChar){
		if(newChar == null){
			character = null;
			return;
		}
		if(character == null){
			character = newChar;
			return;
		}
	}
	
	/**
	 * Returns the character stored in this tile.
	 * @return
	 */
	public CharacterToken getCharacter(){ return character; }

	/**
	 * Stores a weapon in this tile.
	 * @param newWeap
	 * @return
	 */
	public boolean setWeapon(CluedoGame.Weapon newWeap){
		if(isOccupied()) return false; // tile already occupuied
		if(weapon == null){
			weapon = newWeap;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the weapon stored in this tile.
	 * @return
	 */
	public CluedoGame.Weapon getWeapon(){ return weapon; }
	
	public abstract void reset();
}
