package com.kardo.mancala.controller;

import java.util.LinkedList;

import com.kardo.mancala.model.Bowl;
import com.kardo.mancala.model.GravaHal;

public class Game {
	LinkedList<Bowl> board;
	private final int player1 = 1;
	private final int player2 = 2;
	private final int initalNrOfSeeds = 6;

	public Game() {
		board = new LinkedList<>();
		initForPlayer(player1);
		initForPlayer(player2);
	}
	
	public void initForPlayer(int playerId) {
		for(int i = 0; i < 6; i++) {
			board.add(new Bowl(initalNrOfSeeds, playerId));
		}
		board.add(new GravaHal(initalNrOfSeeds, playerId));
	}
	
	
}
