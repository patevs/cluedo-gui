package cluedo.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import cluedo.model.CluedoGame.Weapon;

/**
 * A game token displayed on the board to represent a weapon. 
 * @author Maria
 *
 */
public class WeaponToken extends Token {
	private static final String IMAGE_PATH = "images/";
	private String name;
	private char symbol;
	private BufferedImage image;
	
	/**
	 * Creates a weapon token.
	 * @param name
	 */
	public WeaponToken(String name) {
		super(name);
		this.name = name;
		getImage();
	}

	/**
	 * Returns a cluedo weapon from a string
	 * @param str
	 * @return
	 */
	private Weapon getWeapon(String str) {
		Weapon res = null;
		switch(str.toUpperCase()){
			case "CANDLESTICK":
				res = Weapon.CANDLESTICK;
				break;
			case "KNIFE":
				res = Weapon.KNIFE;
				break;
			case "LEAD_PIPE":
				res = Weapon.LEAD_PIPE;
				break;
			case "REVOLVER":
				res = Weapon.REVOLVER;
				break;
			case "ROPE":
				res = Weapon.ROPE;
				break;
			case "WRENCH":
				res = Weapon.WRENCH;
				break;
		}
		return res;
	}
	
	/**
	 * Returns an basic image associated with this weapon.
	 * @return
	 */
	private void getImage() {
		try{
			switch(name){
				case "CANDLESTICK":
					image = ImageIO.read(new File(IMAGE_PATH + "candlestick.png"));
				case "KNIFE":
					image = ImageIO.read(new File(IMAGE_PATH + "knife.png"));
				case "LEAD_PIPE":
					image = ImageIO.read(new File(IMAGE_PATH + "pipe.png"));
				case "REVOLVER":
					image = ImageIO.read(new File(IMAGE_PATH + "revolver.png"));
				case "ROPE":
					image = ImageIO.read(new File(IMAGE_PATH + "rope.png"));
				case "WRENCH":
					image = ImageIO.read(new File(IMAGE_PATH + "wrench.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the image associated with this weapon.
	 * @return
	 */
	public BufferedImage image(){
		return image;
	}

	@Override
	public String toString(){
		return name;
	}
}
