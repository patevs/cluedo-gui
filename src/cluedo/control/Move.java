package cluedo.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cluedo.view.CluedoBoard;

public class Move implements MouseListener{
	private CluedoBoard board;

	public Move(CluedoBoard board){
		this.board = board;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
