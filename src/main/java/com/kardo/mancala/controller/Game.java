package com.kardo.mancala.controller;

import java.util.ArrayList;

import com.kardo.mancala.model.AbstractBowl;

public class Game {
	CircularBoard board;
	
	

	public Game() {
		board = new CircularBoard();
	}

	public ArrayList<AbstractBowl> getBoard() {
		return board.getBoard();
	}

	public void distributeSeeds(int index) {
		board.distributeSeeds(index);
	}

}
