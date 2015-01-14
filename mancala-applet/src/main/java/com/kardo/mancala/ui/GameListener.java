package com.kardo.mancala.ui;

public interface GameListener {
	public void updateBoard();
	void announceWinner(int winner, int numberOfSeeds);
	public void annouceTie();
}
