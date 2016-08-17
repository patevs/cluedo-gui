package cluedo.view;

import java.awt.Color;

import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class RoomTile extends Tile {
	
	public RoomTile(char c){
		super(c);
		this.setBorder(new EmptyBorder(0,0,0,0));
		// setup the button tile
		setupBtn(c);
	}

	/**
	 * This method creates room specific tiles
	 * @param c
	 */
	private void setupBtn(char c) {
		switch(c){
			case 'C':
				setToolTipText("CONSERVATORY");
				setBackground(Color.LIGHT_GRAY);
				break;
			case 'I': 
				setToolTipText("BILLIARD ROOM");
				setBackground(Color.GREEN);
				break;
			case 'L':
				setToolTipText("LIBRARY");
				setBackground(Color.ORANGE);
				break;
			case 'S':
				setToolTipText("STUDY");
				setBackground(Color.RED);
				break;
			case 'B':
				setToolTipText("BALL ROOM");
				setBackground(Color.MAGENTA);
				break;
			case 'A':
				setToolTipText("HALL");
				setBackground(Color.PINK);
				break;
			case 'K':
				setToolTipText("KITCHEN");
				setBackground(Color.LIGHT_GRAY);
				break;
			case 'D':
				setToolTipText("DINNING ROOM");
				setBackground(new Color(54,17,89).brighter());
				break;
			case 'O':
				setToolTipText("LOUNGE");
				setBackground(Color.BLUE);
				break;
		}
		
	}

}
