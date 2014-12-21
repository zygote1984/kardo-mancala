package com.kardo.mancala.model;

import java.util.ArrayList;

import static com.kardo.mancala.controller.GameConstants.*;

/**
 * Data structure that represents a the circular mancala board. Each bowl has a
 * reference to the next bowl in the counter clockwise direction.
 * 
 * @author kardo
 *
 */
public class CircularBoard {

	private ArrayList<AbstractBowl> board;
	private int initalNrOfSeeds = INIT_NR_OF_SEEDS;
	// 1st bowl of player 1
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
		headBowl = new Bowl(initalNrOfSeeds, PLAYER_1, null);
		AbstractBowl curBowl = headBowl;
		board.add(curBowl);
		for (int i = 2; i <= NR_OF_BOWLS_PER_PLAYER * 2; i++) {
			int player = ((double) i / NR_OF_BOWLS_PER_PLAYER) <= 1 ? PLAYER_1
					: PLAYER_2;
			if (i % NR_OF_BOWLS_PER_PLAYER != 0) {
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

	public GravaHal getGravaHal(int player) {
		return (GravaHal) board.get(player * NR_OF_BOWLS_PER_PLAYER - 1);
	}

	public int getPlayerSeeds(int player, boolean empty) {
		int totalSeeds = 0;
		for (int i = (player - 1) * NR_OF_BOWLS_PER_PLAYER; i < player
				* NR_OF_BOWLS_PER_PLAYER - 1; i++) {
			totalSeeds += empty ? empty(i) : board.get(i).getSeeds();
		}
		return totalSeeds;
	}

}
