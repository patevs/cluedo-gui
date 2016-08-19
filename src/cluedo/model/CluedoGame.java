package cluedo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
	// stores the random solution to the game
	private Card[] solution;
	// stores the deck of cards for the game
	private List<Card> deck;
	// stores the unused leftover cards once all the cards are dealt
	private List<Card> unusedCards;
	
	public CluedoGame(CluedoBoard board, List<CharacterToken> players) {
		board.initPlayers(players);
		this.setBoard(board);
		this.setActivePlayers(players);
		
		this.solution = initSolution();
		this.deck = initDeck();
		
		dealCards();
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
		for(CharacterToken player: getPlayers()){
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
	public CluedoBoard getBoard() { return board; }
	public void setBoard(CluedoBoard board) { this.board = board; }
	public List<CharacterToken> getActivePlayers() { return activePlayers; }
	public void setActivePlayers(List<CharacterToken> activePlayers) { this.activePlayers = activePlayers; }
	/**
	 * Returns the solution to the game
	 * @return game solution
	 */
	private Card[] getSolution() {
		return solution;
	}
	/**
	 * This method returns the current game deck
	 * @return
	 */
	private List<Card> getDeck(){
		return deck;
	}
	/**
	 * Returns a list of active players in the game
	 * @return list of active players
	 */
	private List<CharacterToken> getPlayers() {
		return activePlayers;
	}
	/**
	 * Returns the number of current players
	 * @return number of current players
	 */
	private int numPlayers() {
		return activePlayers.size();
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
