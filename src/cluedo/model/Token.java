package cluedo.model;

public abstract class Token {

	protected static String name;
	
	public Token(String name){
		Token.name = name;
	}
	
	/*
	 * Getter Methods
	 */
	public String getName(){
		return name;
	}
}
