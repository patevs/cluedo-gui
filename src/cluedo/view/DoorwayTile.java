package cluedo.view;

import java.awt.Color;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class DoorwayTile extends OccupyableTile {

	public DoorwayTile(char symbol) {
		super(symbol);
		setupBtn();
	}

	@Override
	protected void setupBtn(){
		this.setBackground(new Color(85,60,45).brighter());
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setToolTipText("A DOORWAY");
	}

	@Override
	public void reset() {
		this.setIcon(null);
		this.setBackground(new Color(85,60,45).brighter());
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setToolTipText("A DOORWAY");
	}
}
