package cluedo.model;

public abstract class Token {

	protected static String name;
	protected Position pos;

	public Token(String name){
		Token.name = name;
	}

	/*
	 * Getter/setter methods
	 */
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
