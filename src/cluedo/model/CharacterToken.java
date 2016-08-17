package cluedo.model;

public class CharacterToken extends Token {
	
	private final int Uid;
	private final String characterStr;

	public CharacterToken(String name, int playerUID, String characterStr) {
		super(name);
		this.Uid = playerUID;
		this.characterStr = characterStr;
	}

	/*
	 * Getter methods
	 */
	public int getUid() { return Uid; }
	public String getCharacterStr() { return characterStr; }
}
