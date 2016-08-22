package cluedo.model;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class CardRadioBtn extends JRadioButton{
	Card card;

	public CardRadioBtn(Card card){
		super(new ImageIcon(card.getImage()));
		this.card = card;
	}

	public Card card(){
		return card;
	}
}
