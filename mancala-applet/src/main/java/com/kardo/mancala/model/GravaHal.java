package com.kardo.mancala.model;

public class GravaHal extends AbstractBowl {
	
	public GravaHal(int seeds, int player, Bowl next) {
		super(seeds, player, next);
	}
	
	/**
	 * extra method to add more than one seed
	 * @param newSeeds
	 */
	public void addSeeds(int newSeeds) {
		seeds += newSeeds;
	}

}
