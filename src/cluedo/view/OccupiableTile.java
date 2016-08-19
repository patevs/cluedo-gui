package cluedo.view;

import cluedo.model.CluedoGame;

@SuppressWarnings("serial")
public class OccupiableTile extends Tile {

	// field to store the character occuping this tile or null if none
	private CluedoGame.Character character;
	// field to store the weapon occuping this tile or null if none
	private CluedoGame.Weapon weapon;
	
	public OccupiableTile(char symbol) {
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
	public boolean setCharacter(CluedoGame.Character newChar){
		if(isOccupied()) return false; // tile already occupuied
		if(character == null){
			character = newChar;
			return true;
		}
		return false;
	}
	public CluedoGame.Character getCharacter(){ return character; }
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
