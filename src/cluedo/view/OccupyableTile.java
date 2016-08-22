package cluedo.view;

import cluedo.model.CharacterToken;
import cluedo.model.CluedoGame;

@SuppressWarnings("serial")
public abstract class OccupyableTile extends Tile {

	// field to store the character occupying this tile or null if none
	private CharacterToken character = null;
	// field to store the weapon occupying this tile or null if none
	private CluedoGame.Weapon weapon = null;

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
	public boolean setCharacter(CharacterToken newChar){
		if(isOccupied()) return false; // tile already occupuied
		if(newChar == null){
			character = null;
			return true;
		}
		if(character == null){
			character = newChar;
			return true;
		}
		return false;
	}
	public CharacterToken getCharacter(){ return character; }

	public boolean setWeapon(CluedoGame.Weapon newWeap){
		if(isOccupied()) return false; // tile already occupuied
		if(weapon == null){
			weapon = newWeap;
			return true;
		}
		return false;
	}
	public CluedoGame.Weapon getWeapon(){ return weapon; }
}
