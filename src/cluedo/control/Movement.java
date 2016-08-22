package cluedo.control;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cluedo.model.CharacterToken;
import cluedo.model.Position;
import cluedo.model.Token;
import cluedo.view.*;

public class Movement{
	private CluedoFrame frame;
	private CluedoBoard board;

	public Movement(CluedoFrame frame, CluedoBoard board){
		this.frame = frame;
		this.board = board;
	}

	public boolean move(CharacterToken player, Position newPos){
		if(!canMove(player, newPos))
			return false;
		Tile t = board.tileAt(newPos);// get button
		Position oldPos = player.pos();// get pos of player
//		t.setToken(null); // remove player from current tile
//		t.setToken(player); // add player to new tile
		//player.setPos(newPos); // set player pos
		return true;
	}

	public boolean canMove(CharacterToken player, Position newPos){
		Tile t = board.tileAt(newPos);
		// check valid tile
		if(!(t instanceof OccupyableTile))
			return false;
		// check enough steps to move
		int distance = distanceOf(player.pos(), newPos);
		if(distance < 0 || player.getStepsRemaining()< distance)
			return false;
		//
		return true;
	}

	private int distanceOf(Tile first, Tile second){
		return distanceOf(first.pos(), second.pos());
	}

	private int distanceOf(Position first, Position second){
		if(first==null||second==null)
			return -1;
		return Math.abs(first.getX() - second.getX()) + Math.abs(first.getY() - second.getY());
	}


	/**
	 * Returns true if a given player can move north on the board
	 * @param token
	 * @return
	 */
	public boolean canMoveNorth(CharacterToken token){
		if(token==null)
			return false;
		// already in north most square
		if(token.y()- 1 < 0){
			return false;
		}

		// cannot move if north is a room, invalid tile, or entrance which is not north
		Tile t = board.tileAt(new Position(token.x(), token.y()-1));
		if(!(t instanceof OccupyableTile))
			return false;
		OccupyableTile oTile = (OccupyableTile)t;
		// cannot move if next tile already contains a token
		if(oTile.isOccupied()){
			return false;
		}

		char symbol = t.getSymbol();
		if(inDoorway(token) || inRoom(token)){
			return Character.isUpperCase(symbol) || symbol == 'n'
					|| symbol == 'e' || symbol == 's' || symbol == 'w';
		}
		if(Character.isUpperCase(symbol) || symbol == 'x'
				|| symbol == 'e' || symbol == 's' || symbol == 'w'){
			return false;
		}
		return true;
	}

	/**
	 * Returns true if a given player can move east on the board
	 * @param token
	 * @return
	 */
	public boolean canMoveEast(CharacterToken token){
		if(token==null)
			return false;
		// already in east most square
		if(token.x() + 1 >= 22){
			return false;
		}

		// cannot move if north is a room, invalid tile, or entrance which is not north
		Tile t = board.tileAt(new Position(token.x()+1, token.y()));
		if(!(t instanceof OccupyableTile))
			return false;
		OccupyableTile oTile = (OccupyableTile)t;
		// cannot move if next tile already contains a token
		if(oTile.isOccupied()){
			return false;
		}

		char symbol = t.getSymbol();
		if(inDoorway(token) || inRoom(token)){
			return Character.isUpperCase(symbol) || symbol == 'n'
					|| symbol == 'e' || symbol == 's' || symbol == 'w';
		}
		if(Character.isUpperCase(symbol) || symbol == 'x'
				|| symbol == 'n' || symbol == 's' || symbol == 'w'){
			return false;
		}
		return true;
	}

	/**
	 * Returns true if a given player can move south on the board
	 * @param token
	 * @return
	 */
	public boolean canMoveSouth(CharacterToken token){
		if(token==null)
			return false;
		// already in south most square
		if(token.y()+ 1 >= 22){
			return false;
		}

		// cannot move if north is a room, invalid tile, or entrance which is not north
		Tile t = board.tileAt(new Position(token.x(), token.y()+1));
		if(!(t instanceof OccupyableTile))
			return false;
		OccupyableTile oTile = (OccupyableTile)t;
		// cannot move if next tile already contains a token
		if(oTile.isOccupied()){
			return false;
		}

		char symbol = t.getSymbol();
		if(inDoorway(token) || inRoom(token)){
			return Character.isUpperCase(symbol) || symbol == 'n'
					|| symbol == 'e' || symbol == 's' || symbol == 'w';
		}
		if(Character.isUpperCase(symbol) || symbol == 'x'
				|| symbol == 'n' || symbol == 'e' || symbol == 'w'){
			return false;
		}
		return true;
	}

	/**
	 * Returns true if a given player can move west on the board
	 * @param token
	 * @return
	 */
	public boolean canMoveWest(CharacterToken token){
		if(token==null)
			return false;
		// already in west most square
		if(token.x() - 1 < 0){
			return false;
		}

		// cannot move if north is a room, invalid tile, or entrance which is not north
		Tile t = board.tileAt(new Position(token.x()-1, token.y()));
		if(!(t instanceof OccupyableTile))
			return false;
		OccupyableTile oTile = (OccupyableTile)t;
		// cannot move if next tile already contains a token
		if(oTile.isOccupied()){
			return false;
		}

		char symbol = t.getSymbol();
		if(inDoorway(token) || inRoom(token)){
			return Character.isUpperCase(symbol) || symbol == 'n'
					|| symbol == 'e' || symbol == 's' || symbol == 'w';
		}
		if(Character.isUpperCase(symbol) || symbol == 'x'
				|| symbol == 'n' || symbol == 'e' || symbol == 's'){
			return false;
		}
		return true;
	}

	/**
	 * Moves player one position up
	 * @param player
	 */
	public void moveNorth(CharacterToken player) {
		if(player==null)
			return;
		Position newPos = new Position(player.x(), player.y()-1);
		move(player, newPos);
	}

	/**
	 * Moves player one position to the right
	 * @param player
	 */
	public void moveEast(CharacterToken player){
		if(player==null)
			return;
		Position newPos = new Position(player.x()+1, player.y());
		move(player, newPos);
	}

	/**
	 * Moves player one position down
	 * @param player
	 */
	public void moveSouth(CharacterToken player) {
		if(player==null)
			return;
		Position newPos = new Position(player.x(), player.y()+1);
		move(player, newPos);
	}

	/**
	 * Moves player one position left
	 * @param player
	 */
	public void moveWest(CharacterToken player) {
		if(player==null)
			return;
		Position newPos = new Position(player.x()-1, player.y());
		move(player, newPos);
	}

	/**
	 * Moves player to opposite room.
	 * @param player
	 */
	public void useStairs(CharacterToken player){
		//TODO: use stairs
		/*if(player==null)
			return;
		// checks player is in a corner room
		Tile tile = board.tileAt(player.pos());
		if(!(tile instanceof RoomTile))
			throw new CluedoError("No stairs in this area: " + tile.toString());
		if(!(isCornerRoom(tile))
			throw new CluedoError("No stairs in this area: " + tile.toString());
		RoomTile rTile = (RoomTile)tile;
		// finds the opposite room and moves player to that room
		CluedoGame.Room opposite = rTile.oppositeRoomPos();
		moveIntoRoom(player, opposite);*/
	}


	/**
	 * Returns true if a given token is in any room
	 * @param token
	 * @return
	 */
	public boolean inRoom(Token token){
		if(token==null)
			return false;
		Tile t = board.tileAt(new Position(token.x(), token.y()));
		return(t instanceof RoomTile);
	}

	/**
	 * Returns true if a given token is in a corner room
	 * @param token
	 * @return
	 */
	public boolean inCornerRoom(Token token){
		if(token==null)
			return false;
		Tile t = board.tileAt(new Position(token.x(), token.y()));
		if(t instanceof RoomTile){
			RoomTile r = (RoomTile)t;
			if(isCornerRoom(r));
				return true;
		}
		return false;
	}

	/**
	 * Returns true if a given token is in any doorway
	 * @param token
	 * @return
	 */
	public boolean inDoorway(Token token){
		if(token==null)
			return false;
		int Xpos = token.x();
		int Ypos = token.y();
		Tile t = board.tileAt(new Position(token.x(), token.y()));
		return(t instanceof DoorwayTile);
	}

	private boolean isCornerRoom(Tile room){
		if(!(room instanceof RoomTile))
			return false;
		RoomTile rTile = (RoomTile)room;
		if(rTile.getName().equalsIgnoreCase("Kitchen")||rTile.getName().equalsIgnoreCase("Conservatory")||
				rTile.getName().equalsIgnoreCase("Lounge")||rTile.getName().equalsIgnoreCase("Study"))
			return true;
		return false;
	}
}
