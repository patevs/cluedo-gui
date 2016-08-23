package cluedo.model;

import java.util.ArrayList;
import java.util.List;

import cluedo.model.CluedoGame.Weapon;

public class WeaponToken extends Token {
	private String name;
	private char symbol;
	
	public WeaponToken(String name) {
		super(name);
		this.name = name;
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

	@Override
	public String toString(){
		return name;
	}
}
