package cluedo.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import cluedo.view.CluedoBoard;

/**
 * Contains the game equipment, cards and players.
 * @author Patrick
 *
 */
public class CluedoGame {

	// path to images - source: game-board.blogspot.co.nz/2008/05/clue-characters-board-game-vs-clue.html
	private static final String IMAGE_PATH = "images/";
	// represents the current game board
	private CluedoBoard board;
	// a list of equipment
	private List<CharacterToken> activePlayers;
	private List<WeaponToken> weapons;
	// stores the random solution to the game
	private Card[] solution;
	// stores the deck of cards for the game
	private List<Card> deck;
	// stores the unused leftover cards once all the cards are dealt
	private List<Card> unusedCards;

	public CluedoGame(CluedoBoard board, List<CharacterToken> players) {

		this.setBoard(board);
		this.setActivePlayers(players);

		// deals the cards
		this.solution = initSolution();
		this.deck = initDeck();
		dealCards();
		getDeck();

	}

	/**
	 * This method gets a random solution to the Cluedo game.
	 *  The solution contains one Character card, one Room card,
	 *  and one weapon card.
	 * @return solution
	 */
	private Card[] initSolution() {
		solution = new Card[3];
		solution[0] = Character.getRandom();
		solution[1] = Room.getRandom();
		solution[2] = Weapon.getRandom();
		return solution;
	}

	/**
	 * Returns a list of all the cards in the game deck
	 * 	excluding the solution cards
	 * @return list of all cards
	 */
	private List<Card> initDeck() {
		List<Card> deck = new ArrayList<Card>();
		deck.addAll(Arrays.asList(Character.values()));
		deck.addAll(Arrays.asList(Weapon.values()));
		deck.addAll(Arrays.asList(Room.values()));
		deck.removeAll(Arrays.asList(getSolution()));
		return deck;
	}

	/**
	 * Deals the cards evenly to all player leaving out left over cards
	 */
	private void dealCards() {
		// remove unused cards from deck so remaining cards can be dealt evenly
		int numUnused = deck.size() % numPlayers();
		unusedCards = new ArrayList<Card>();
		for(int i=0; i<numUnused; i++){
			unusedCards.add(getCardFromDeck());
		}
		// deal cards evenly to players
		int numCardsToDeal = deck.size() / numPlayers();
		for(CharacterToken player: getActivePlayers()){
			for(int i=0; i<numCardsToDeal; i++){
				player.addCard(getCardFromDeck());
			}
		}
	}

	/**
	 * Gets a random card from the remaining deck of cards
	 * @return random card from deck
	 */
	private Card getCardFromDeck() {
		Card result = deck.get(new Random().nextInt(deck.size()));
		deck.remove(result);
		return result;
	}

	/*
	 * Getter and Setter methods
	 */

	/**
	 * Returns the board
	 * @return
	 */
	public CluedoBoard getBoard() { return board; }

	/**
	 * Sets the board
	 * @param board
	 */
	public void setBoard(CluedoBoard board) { 
		this.board = board; 
	}

	/**
	 * Returns all players
	 * @return
	 */
	public List<CharacterToken> getActivePlayers() { 
		return activePlayers; 
	}

	/**
	 * Sets all players
	 * @param activePlayers
	 */
	public void setActivePlayers(List<CharacterToken> activePlayers) { 
		this.activePlayers = activePlayers; 
	}

	/**
	 * Returns the solution to the game
	 * @return game solution
	 */
	private Card[] getSolution() { return solution;	}

	/**
	 * This method returns the current game deck
	 * @return
	 */
	private List<Card> getDeck(){ return deck; }

	/**
	 * Returns the number of current players
	 * @return number of current players
	 */
	private int numPlayers() { return activePlayers.size();	}

	/*
	 * Enums to represent the characters, rooms, and weapons
	 * 	in the game.
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

		@Override
		public BufferedImage getImage() {
			try{
				switch(this){
					case MISS_SCARLETT:
						return ImageIO.read(new File(IMAGE_PATH + "scarlett-card.png"));
					case COLONEL_MUSTARD:
						return ImageIO.read(new File(IMAGE_PATH + "mustard-card.png"));
					case MRS_WHITE:
						return ImageIO.read(new File(IMAGE_PATH + "white-card.png"));
					case THE_REVEREND_GREEN:
						return ImageIO.read(new File(IMAGE_PATH + "green-card.png"));
					case MRS_PEACOCK:
						return ImageIO.read(new File(IMAGE_PATH + "peacock-card.png"));
					case PROFESSOR_PLUM:
						return ImageIO.read(new File(IMAGE_PATH + "plum-card.png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * Represents the six weapons in the game
	 */
	public enum Weapon implements Card {
		CANDLESTICK,
		KNIFE, // changed from dagger to match cards
		LEAD_PIPE,
		REVOLVER,
		ROPE,
		WRENCH; // changed from spanner to match cards

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

		@Override
		public BufferedImage getImage() {
			try{
				switch(this){
					case CANDLESTICK:
						return ImageIO.read(new File(IMAGE_PATH + "candlestick-card.png"));
					case KNIFE:
						return ImageIO.read(new File(IMAGE_PATH + "knife-card.png"));
					case LEAD_PIPE:
						return ImageIO.read(new File(IMAGE_PATH + "pipe-card.png"));
					case REVOLVER:
						return ImageIO.read(new File(IMAGE_PATH + "revolver-card.png"));
					case ROPE:
						return ImageIO.read(new File(IMAGE_PATH + "rope-card.png"));
					case WRENCH:
						return ImageIO.read(new File(IMAGE_PATH + "wrench-card.png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
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

		@Override
		public BufferedImage getImage() {
			try{
				switch(this){
					case KITCHEN:
						return ImageIO.read(new File(IMAGE_PATH + "kitchen-card.png"));
					case BALL_ROOM:
						return ImageIO.read(new File(IMAGE_PATH + "ballroom-card.png"));
					case CONSERVATORY:
						return ImageIO.read(new File(IMAGE_PATH + "conservatory-card.png"));
					case BILLIARD_ROOM:
						return ImageIO.read(new File(IMAGE_PATH + "billiard-card.png"));
					case LIBRARY:
						return ImageIO.read(new File(IMAGE_PATH + "library-card.png"));
					case STUDY:
						return ImageIO.read(new File(IMAGE_PATH + "study-card.png"));
					case HALL:
						return ImageIO.read(new File(IMAGE_PATH + "hall-card.png"));
					case LOUNGE:
						return ImageIO.read(new File(IMAGE_PATH + "lounge-card.png"));
					case DINING_ROOM:
						return ImageIO.read(new File(IMAGE_PATH + "diningroom-card.png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
