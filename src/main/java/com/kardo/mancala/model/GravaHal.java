package com.kardo.mancala.model;

public class GravaHal extends AbstractBowl {
	
	public GravaHal(int seeds, int player, Bowl next) {
		super(seeds, player, next);
	}
	
	
	public void addSeeds(int newSeeds) {
		seeds += newSeeds;
	}

}
