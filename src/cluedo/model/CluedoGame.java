package cluedo.model;

import java.util.List;

import cluedo.view.CluedoBoard;

/**
 * Contains the game equipment, cards and players.
 * @author Patrick
 *
 */
public class CluedoGame {

	// represents the current game board
	private CluedoBoard board;
	// a list of all current players
	private List<CharacterToken> activePlayers;
	
	public CluedoGame(CluedoBoard board, List<CharacterToken> players) {
		this.setBoard(board);
		this.setActivePlayers(players);
	}

	/*
	 * Getter and Setter methods
	 */
	public CluedoBoard getBoard() {
		return board;
	}
	public void setBoard(CluedoBoard board) {
		this.board = board;
	}
	public List<CharacterToken> getActivePlayers() {
		return activePlayers;
	}

	public void setActivePlayers(List<CharacterToken> activePlayers) {
		this.activePlayers = activePlayers;
	}
	
	/*
	 * Enums to represent the characters, rooms, and weapons
	 * 	In the game.
	 */
	/**
	 * Represents the six characters in the game
	 */
	public enum Character implements Card {
		MISS_SCARLETT,
		COLONEL_MUSTARD,
		MRS_WHITE,
		THE_REVEREND_GREEN,
		MRS_PEACOCK,
		PROFESSOR_PLUM;

		/**
		 * Returns a random card.
		 * @return
		 */
		public static Card getRandom() {
			return values()[(int) (Math.random() * values().length)];
		}

		@Override
		public String toString() {
			return this.name().replaceAll("_", " ");
		}
	}

	/**
	 * Represents the six weapons in the game
	 */
	public enum Weapon implements Card {
		CANDLESTICK,
		DAGGER,
		LEAD_PIPE,
		REVOLVER,
		ROPE,
		SPANNER;

		/**
		 * Returns a random weapon.
		 * @return
		 */
		public static Card getRandom() {
			return values()[(int) (Math.random() * values().length)];
		}

		@Override
		public String toString() {
			return this.name().replaceAll("_", " ");
		}
	}

	/**
	 * Represents the nine rooms in the game
	 */
	public enum Room implements Card {
		KITCHEN,
		BALL_ROOM,
		CONSERVATORY,
		BILLIARD_ROOM,
		LIBRARY,
		STUDY,
		HALL,
		LOUNGE,
		DINING_ROOM;

		/**
		 * Returns a random room.
		 * @return
		 */
		public static Card getRandom() {
			return values()[(int) (Math.random() * values().length)];
		}

		@Override
		public String toString() {
			return this.name().replaceAll("_", " ");
		}
	}
}
