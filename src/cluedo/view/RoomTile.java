package cluedo.view;

import java.awt.Color;

import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class RoomTile extends OccupyableTile {

	private Color roomColor = Color.decode("#660000").brighter();

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
//				setBackground(Color.LIGHT_GRAY);
				this.setBackground(roomColor);
				break;
			case 'I':
				setToolTipText("BILLIARD ROOM");
//				setBackground(Color.GREEN);
				this.setBackground(roomColor);
				break;
			case 'L':
				setToolTipText("LIBRARY");
//				setBackground(Color.ORANGE);
				this.setBackground(roomColor);
				break;
			case 'S':
				setToolTipText("STUDY");
//				setBackground(Color.RED);
				this.setBackground(roomColor);
				break;
			case 'B':
				setToolTipText("BALL ROOM");
//				setBackground(Color.MAGENTA);
				this.setBackground(roomColor);
				break;
			case 'A':
				setToolTipText("HALL");
//				setBackground(Color.PINK);
				this.setBackground(roomColor);
				break;
			case 'K':
				setToolTipText("KITCHEN");
//				setBackground(Color.LIGHT_GRAY);
				this.setBackground(roomColor);
				break;
			case 'D':
				setToolTipText("DINING ROOM");
//				setBackground(new Color(54,17,89).brighter());
				this.setBackground(roomColor);
				break;
			case 'O':
				setToolTipText("LOUNGE");
//				setBackground(Color.BLUE);
				this.setBackground(roomColor);
				break;
		}

	}

}
