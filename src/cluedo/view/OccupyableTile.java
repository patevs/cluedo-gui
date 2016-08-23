package cluedo.view;

import javax.swing.ImageIcon;

import cluedo.model.CharacterToken;
import cluedo.model.WeaponToken;

@SuppressWarnings("serial")
public abstract class OccupyableTile extends Tile {

	// field to store the character occupying this tile or null if none
	private CharacterToken character = null;
	// field to store the weapon occupying this tile or null if none
	private WeaponToken weapon = null;

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
	 * @param weapon2
	 * @return
	 */
	public boolean setWeapon(WeaponToken weapon2){
		if(isOccupied()) return false; // tile already occupuied
		if(weapon == null){
			weapon = weapon2;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the weapon stored in this tile.
	 * @return
	 */
//	public CluedoGame.Weapon getWeapon(){ return weapon; }
	public WeaponToken getWeapon(){ return weapon; }
	
	/** FIXME
	 * Sets the image of this character and text to display.
	 */
	public void setFancy(){
		if(character!=null){
			setIcon(new ImageIcon(character.image()));
			setToolTipText(character.getName() + ": MISS SCARLETT");
		}
		else if(weapon!=null){
			setIcon(new ImageIcon(weapon.image()));
			setToolTipText(weapon.getName());
		}
		else{
			setupBtn();
		}
	}
	/**
	 * Sets the image and hover text.
	 */
	protected void setupBtn(){}
	
	// resets the tile to its initial state
	public abstract void reset();
}
