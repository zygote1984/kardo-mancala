package com.kardo.mancala.controller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.kardo.mancala.model.AbstractBowl;
import com.kardo.mancala.model.Bowl;
import com.kardo.mancala.model.GravaHal;

public class CircularBoard {

	private Logger logger = Logger.getLogger("Board");
	private ArrayList<AbstractBowl> board;
	private int initalNrOfSeeds = GameConstants.INIT_NR_OF_SEEDS;
	private Bowl headBowl;

	public CircularBoard() {
		board = new ArrayList<>();
		initBoard();
	}

	protected CircularBoard(int initNrSeeds) {
		initalNrOfSeeds = initNrSeeds;
		board = new ArrayList<>();
		initBoard();
	}

	private void initBoard() {
		headBowl = new Bowl(initalNrOfSeeds, GameConstants.PLAYER_1, null);
		AbstractBowl curBowl = headBowl;
		board.add(curBowl);
		for (int i = 2; i <= 14; i++) {
			int player = ((double)i / 7 )<= 1 ? GameConstants.PLAYER_1 : GameConstants.PLAYER_2;
			if (i % 7 != 0) {
				curBowl.setNext(new Bowl(initalNrOfSeeds, player, null));
			} else {
				curBowl.setNext(new GravaHal(0, player, null));
			}
			curBowl = curBowl.getNext();
			board.add(curBowl);
		}
		if (curBowl.getNext() == null) {
			curBowl.setNext(headBowl);
		}
	}

	public Bowl getHeadBowl() {
		return headBowl;
	}

	public ArrayList<AbstractBowl> getBoard() {
		return board;
	}

	public AbstractBowl getBowl(int index) {
		return board.get(index);
	}

	public int getPlayer(int index) {
		return board.get(index).getPlayer();
	}

	/**
	 * distributes the seeds in bowl to the subsequent bowls
	 * @return true if the last seed ends up in user's grava hal
	 */
	public boolean distributeSeeds(int index) {
		logger.log(Level.INFO, "Distribute seeds from bowl " + index);
		AbstractBowl bowl = board.get(index);
		int player = bowl.getPlayer();
		int seeds = bowl.empty();
		logger.log(Level.INFO, "Bowl is emptied " + bowl.getSeeds());
		boolean isUsersGravaHal = false;
		for (int i = 1; i <= seeds; i++) {
			isUsersGravaHal = bowl.getNext() instanceof GravaHal
					&& bowl.getNext().getPlayer() == player;
			boolean isOpponentsGravaHal = bowl.getNext() instanceof GravaHal
					&& bowl.getNext().getPlayer() != player;
			
			if(isOpponentsGravaHal) {
				bowl = bowl.getNext();
			}
			if (bowl.getNext() instanceof Bowl || isUsersGravaHal) {
				bowl.getNext().increment();
				bowl = bowl.getNext();
			}
		}
		if(isUsersGravaHal) {
			logger.log(Level.INFO, "The last seed ended up in user's grava hal");
			return true;
		}
		return false;
	}

}
