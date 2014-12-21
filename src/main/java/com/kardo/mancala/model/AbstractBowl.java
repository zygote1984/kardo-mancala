package com.kardo.mancala.model;

/**
 * Root of the bowl hierarchy. Represents a bowl which holds seeds and belongs to a specific player.
 * 
 * @author kardo
 *
 */
public abstract class AbstractBowl {
	int seeds;
	int player;
	AbstractBowl next;

	/**
	 * Constructor
	 * 
	 * @param seeds
	 *            number of initial seeds
	 * @param player
	 *            player id, owner of the bowl
	 */
	public AbstractBowl(int seeds, int player, Bowl next) {
		this.seeds = seeds;
		this.player = player;
		this.next = next;
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

	public AbstractBowl getNext() {
		return next;
	}

	public void setNext(AbstractBowl next) {
		this.next = next;
	}

	/**
	 * sets the number of seeds in the bowl to 0
	 * 
	 * @return number of seeds in the bowl
	 */
	public int empty() {
		int tempSeeds = seeds;
		seeds = 0;
		return tempSeeds;
	}

	public boolean isEmpty() {
		return seeds == 0;
	}

	@Override
	public String toString() {
		return "AbstractBowl [seeds=" + seeds + ", player=" + player + "]";
	}
	
	
}
