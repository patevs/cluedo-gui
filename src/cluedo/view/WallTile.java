package cluedo.view;

import java.awt.Color;

import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WallTile extends Tile {

	public WallTile(char c){
		super(c);
		this.setToolTipText("A WALL");
		this.setBackground(Color.GRAY);
		this.setBorder(new EmptyBorder(0,0,0,0));
	}
}
