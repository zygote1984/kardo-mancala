package com.kardo.mancala.model;

/**
 * Represents a bowl 
 * @author kardo
 */
public class Bowl {
	
	int seeds;
	int player;
	
	/**
	 * Constructor
	 * @param seeds number of initial seeds
	 * @param player player id, owner of the bowl
	 */
	public Bowl(int seeds, int player) {
		this.seeds = seeds;
		this.player = player;
	}
	
	/**
	 * increments the number of seeds
	 */
	public void increment() {
		seeds++;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public int getSeeds() {
		return seeds;
	}
	
	/**
	 * sets the number of seeds in the bowl to 0
	 * @return number of seeds in the bowl
	 */
	public int empty() {
		int tempSeeds = seeds;
		seeds = 0;
		return tempSeeds;
	}
	
}
