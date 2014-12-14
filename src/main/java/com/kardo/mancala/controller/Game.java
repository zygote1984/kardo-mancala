package com.kardo.mancala.controller;

import java.util.ArrayList;

import com.kardo.mancala.model.AbstractBowl;

public class Game {
	CircularBoard board;
	private int turn = GameConstants.PLAYER_1;

	public Game() {
		board = new CircularBoard();
	}

	public ArrayList<AbstractBowl> getBoard() {
		return board.getBoard();
	}

	public boolean distributeSeeds(int index) {
		if (turn == board.getBowl(index).getPlayer()) {
			if (!board.distributeSeeds(index)) {
				switchTurns();
			}
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

}
