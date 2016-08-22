package cluedo.view;

import javax.swing.JButton;

import cluedo.model.Position;

@SuppressWarnings("serial")
public abstract class Tile extends JButton {

	// Stores the character symbol of the tile
	private final char symbol;
	private Position pos;

	public Tile(char symbol){
		this.symbol = symbol;
	}

	/*
	 * Getter/setter methods
	 */
	/**
	 * Returns the symbol associated with this tile.
	 * @return
	 */
	public char getSymbol(){ return symbol;	}
	/**
	 * Returns the position of this tile.
	 * @return
	 */
	public Position pos(){ return pos; }
	/**
	 * Sets the position of this tile.
	 * @param pos
	 */
	public void setPos(Position pos){ this.pos = pos; }
}
