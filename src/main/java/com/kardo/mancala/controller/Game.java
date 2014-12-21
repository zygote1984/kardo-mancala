package com.kardo.mancala.controller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.kardo.mancala.model.AbstractBowl;
import com.kardo.mancala.model.Bowl;
import com.kardo.mancala.model.CircularBoard;
import com.kardo.mancala.model.GravaHal;

/**
 * This class implements the game mechanics and hold the turn information.
 * 
 * @author kardo
 *
 */
public class Game {
	private Logger logger = Logger.getLogger("Game");
	protected CircularBoard circularBoard;
	private int turn = GameConstants.PLAYER_1;

	public Game() {
		circularBoard = new CircularBoard();
	}

	public ArrayList<AbstractBowl> getBoard() {
		return circularBoard.getBoard();
	}

	public boolean distributeSeeds(int index) {
		if (turn == circularBoard.getPlayer(index) && !circularBoard.getBowl(index).isEmpty()) {
			if (!playTurn(index)) {
				switchTurns();
			}
			return true;
		}
		return false;
	}

	private boolean checkGameEnd() {
		if(circularBoard.getPlayerSeeds(GameConstants.PLAYER_1, false) == 0 ||
		   circularBoard.getPlayerSeeds(GameConstants.PLAYER_2, false) == 0) {
			return true;
		}
		return false;
	}

	public int getTurn() {
		return turn;
	}

	private void switchTurns() {
		turn = turn == GameConstants.PLAYER_1 ? GameConstants.PLAYER_2
				: GameConstants.PLAYER_1;
	}
	
	/**
	 * distributes the seeds in bowl to the subsequent bowls
	 * 
	 * @return true if the last seed ends up in user's grava hal
	 */
	private boolean playTurn(int index) {
		logger.log(Level.INFO, "Distribute seeds from bowl " + index);
		AbstractBowl bowl = circularBoard.getBowl(index);
		int player = circularBoard.getPlayer(index);
		int seeds = circularBoard.empty(index);
		logger.log(Level.INFO, "Bowl is emptied " + bowl.getSeeds());
		boolean isUsersGravaHal = false;
		for (int i = 1; i <= seeds; i++) {
			isUsersGravaHal = bowl.getNext() instanceof GravaHal
					&& bowl.getNext().getPlayer() == player;
			boolean isOpponentsGravaHal = bowl.getNext() instanceof GravaHal
					&& bowl.getNext().getPlayer() != player;

			if (isOpponentsGravaHal) {
				bowl = bowl.getNext();
			}
			if (bowl.getNext().isEmpty() && bowl.getNext().getPlayer() == player
					&& bowl.getNext() instanceof Bowl && i == seeds) {
				int idxOfBowl = circularBoard.getIndexOf(bowl.getNext());
				int oppositeBowlIdx = (GameConstants.NR_OF_BOWLS_PER_PLAYER - 1 - idxOfBowl) * 2 + idxOfBowl;
				if (!circularBoard.getBowl(oppositeBowlIdx).isEmpty()) {
					int opponentsSeeds = circularBoard.getBowl(oppositeBowlIdx).empty();
					((GravaHal) circularBoard.getGravaHal(player))
							.addSeeds(1 + opponentsSeeds);
					break;
				} 
			} 
			if (bowl.getNext() instanceof Bowl || isUsersGravaHal) {
				bowl.getNext().increment();
				bowl = bowl.getNext();
			}
		}
		//check for game end
		if(checkGameEnd()) {
			finalTurn();
			return false;
		}
		if (isUsersGravaHal) {
			logger.log(Level.INFO, "The last seed ended up in user's grava hal");
			return true;
		}
		return false;
	}
	
	protected void finalTurn() {
		circularBoard.getGravaHal(GameConstants.PLAYER_1).addSeeds(circularBoard.getPlayerSeeds(GameConstants.PLAYER_1, true));
		circularBoard.getGravaHal(GameConstants.PLAYER_2).addSeeds(circularBoard.getPlayerSeeds(GameConstants.PLAYER_2, true));
	}

}
