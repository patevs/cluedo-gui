package cluedo.view;

import javax.swing.JButton;

@SuppressWarnings("serial")
public abstract class Tile extends JButton {
	
	// Stores the character symbol of the tile 
	private char symbol;
	
	public Tile(char symbol){
		this.symbol = symbol;
	}
	
	/*
	 * Getter methods
	 */
	public char getSymbol(){
		return symbol;
	}

}
