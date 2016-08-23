package cluedo.view;

import java.awt.Color;

import javax.swing.border.EmptyBorder;

import cluedo.model.CluedoGame;
import cluedo.model.CluedoGame.Room;

@SuppressWarnings("serial")
public class RoomTile extends OccupyableTile {

	private Color roomColor = Color.decode("#660000").brighter();
	private CluedoGame.Room name = null;

	public RoomTile(char c){
		super(c);
		this.setBorder(new EmptyBorder(0,0,0,0));
		// setup the button tile
		setupBtn();
	}

	@Override
	protected void setupBtn() {
		switch(super.getSymbol()){
			case 'C':
				setToolTipText("CONSERVATORY");
//				setBackground(Color.LIGHT_GRAY);
				this.setBackground(roomColor);
				this.name = Room.CONSERVATORY;
				break;
			case 'I':
				setToolTipText("BILLIARD ROOM");
//				setBackground(Color.GREEN);
				this.setBackground(roomColor);
				this.name = Room.CONSERVATORY;
				break;
			case 'L':
				setToolTipText("LIBRARY");
//				setBackground(Color.ORANGE);
				this.setBackground(roomColor);
				this.name = Room.CONSERVATORY;
				break;
			case 'S':
				setToolTipText("STUDY");
//				setBackground(Color.RED);
				this.setBackground(roomColor);
				this.name = Room.CONSERVATORY;
				break;
			case 'B':
				setToolTipText("BALL ROOM");
//				setBackground(Color.MAGENTA);
				this.setBackground(roomColor);
				this.name = Room.CONSERVATORY;
				break;
			case 'A':
				setToolTipText("HALL");
//				setBackground(Color.PINK);
				this.setBackground(roomColor);
				this.name = Room.CONSERVATORY;
				break;
			case 'K':
				setToolTipText("KITCHEN");
//				setBackground(Color.LIGHT_GRAY);
				this.setBackground(roomColor);
				this.name = Room.CONSERVATORY;
				break;
			case 'D':
				setToolTipText("DINING ROOM");
//				setBackground(new Color(54,17,89).brighter());
				this.setBackground(roomColor);
				this.name = Room.CONSERVATORY;
				break;
			case 'O':
				setToolTipText("LOUNGE");
//				setBackground(Color.BLUE);
				this.setBackground(roomColor);
				this.name = Room.CONSERVATORY;
				break;
		}
	}

	@Override
	public void reset() {
		this.setIcon(null);
		this.setBorder(new EmptyBorder(0,0,0,0));
		// setup the button tile
		setupBtn();
	}
	
	/**
	 * Returns the room associated with this room.
	 * @return
	 */
	public Room name(){
		return name;
	}
}
