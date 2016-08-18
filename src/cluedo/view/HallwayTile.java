package cluedo.view;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;

@SuppressWarnings("serial")
public class HallwayTile extends Tile {

	public HallwayTile(char c){
		super(c);
		this.setBackground(Color.YELLOW.darker());
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		this.setMargin(new Insets(10,10,10,10));
	}
}
