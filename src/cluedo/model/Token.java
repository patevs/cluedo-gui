package cluedo.model;

import cluedo.view.OccupyableTile;

public abstract class Token {

	// Stores the name of the token
	protected String name;
	// Stores the position of the token
	// or null if not on board.
	protected Position pos;
	protected OccupyableTile tile;

	public Token(String name){
		this.name = name;
	}
	
	/*
	 * Getter/setter methods
	 */
	public OccupyableTile getTile() {
		return tile;
	}

	public void setTile(OccupyableTile tile) {
		this.tile = tile;
	}
	/**
	 * Returns the name of this token.
	 * @return
	 */
	public String getName(){ return name; }
	/**
	 * Returns the position of this token.
	 * @return
	 */
	public Position pos(){ return pos; }
	/**
	 * Returns the x position of this token.
	 * @return
	 */
	public int x(){ return pos.getX(); }
	/**
	 * Returns the y position of this token.
	 * @return
	 */
	public int y(){ return pos.getY(); }
	/**
	 * Sets the position of this token.
	 * @param pos
	 */
	public void setPos(Position pos){ this.pos = pos; }
}
