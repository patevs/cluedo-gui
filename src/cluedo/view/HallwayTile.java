package cluedo.view;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;

import cluedo.model.CluedoGame;

@SuppressWarnings("serial")
public class HallwayTile extends OccupyableTile {

	// Stores the character which starts in this square
	// 	or null if none.
	CluedoGame.Character startChar;

	public HallwayTile(char c){
		super(c);
		setupBtn();
	}

	@Override
	protected void setupBtn(){
		this.setBackground(Color.decode("#525564"));
		this.setBorder(BorderFactory.createLineBorder(Color.decode("#BEB9B5").darker(), 1));
		this.setMargin(new Insets(10,10,10,10));
	}
	/**
	 * Sets this tile as a player starting tile
	 * @param charID
	 */
	public void setStartCharacter(int charID){
		switch(charID){
			case 1:
				startChar = CluedoGame.Character.MISS_SCARLETT;
				break;
			case 2:
				startChar = CluedoGame.Character.COLONEL_MUSTARD;
				break;
			case 3:
				startChar = CluedoGame.Character.THE_REVEREND_GREEN;
				break;
			case 4:
				startChar = CluedoGame.Character.MRS_PEACOCK;
				break;
			case 5:
				startChar = CluedoGame.Character.PROFESSOR_PLUM;
				break;
			case 6:
				startChar = CluedoGame.Character.MRS_WHITE;
				break;
		}
	}
	/**
	 * Returns the cluedo character which starts in this tile
	 * 	or null if none
	 * @return start character
	 */
	public CluedoGame.Character getStartCharacter(){
		return startChar;
	}

	@Override
	public void reset() {
		this.setIcon(null);
		this.setBackground(Color.decode("#525564"));
		this.setBorder(BorderFactory.createLineBorder(Color.decode("#BEB9B5").darker(), 1));
		this.setMargin(new Insets(10,10,10,10));
		this.setToolTipText(null);
	}
}
