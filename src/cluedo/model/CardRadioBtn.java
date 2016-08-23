package cluedo.model;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

/**
 * Creates a button representing a card.
 * @author Maria
 *
 */
public class CardRadioBtn extends JRadioButton{
	Card card;

	/**
	 * Creates an instance of a radio button with a card image.
	 * @param card
	 */
	public CardRadioBtn(Card card){
		super(card.toString(), new ImageIcon(card.getImage()));
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(JRadioButton.TOP);
		this.card = card;
	}

	/**
	 * Returns the card associated with this button.
	 * @return
	 */
	public Card card(){
		return card;
	}
}
