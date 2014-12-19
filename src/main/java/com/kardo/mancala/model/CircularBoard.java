package com.kardo.mancala.model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.kardo.mancala.controller.GameConstants;

public class CircularBoard {

	private ArrayList<AbstractBowl> board;
	private int initalNrOfSeeds = GameConstants.INIT_NR_OF_SEEDS;
	private Bowl headBowl;

	public CircularBoard() {
		board = new ArrayList<>();
		initBoard();
	}

	public CircularBoard(int initNrSeeds) {
		initalNrOfSeeds = initNrSeeds;
		board = new ArrayList<>();
		initBoard();
	}

	private void initBoard() {
		headBowl = new Bowl(initalNrOfSeeds, GameConstants.PLAYER_1, null);
		AbstractBowl curBowl = headBowl;
		board.add(curBowl);
		for (int i = 2; i <= 14; i++) {
			int player = ((double) i / 7) <= 1 ? GameConstants.PLAYER_1
					: GameConstants.PLAYER_2;
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

	public int getIndexOf(AbstractBowl bowl) {
		return board.indexOf(bowl);
	}

	public int empty(int index) {
		return getBowl(index).empty();
	}

}
