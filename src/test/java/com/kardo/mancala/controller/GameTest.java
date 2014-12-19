package com.kardo.mancala.controller;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.kardo.mancala.model.CircularBoard;

public class GameTest {

	Game game; 
	
	@Before
	public void setUp() {
		game = new Game();
	}
	
	
	@Test
	public void testDistributeSeeds() {
		game = new MyGame();
		game.distributeSeeds(3);
		assertEquals(1, game.getBoard().get(6).getSeeds());
		game.distributeSeeds(0);
		assertEquals(5, game.getBoard().get(6).getSeeds());		
	}

	@Test
	public void testGetTurn() {
		assertEquals(GameConstants.PLAYER_1, game.getTurn());
		assertTrue(game.distributeSeeds(0));
		assertEquals(GameConstants.PLAYER_1, game.getTurn());
		game.distributeSeeds(5);
		assertEquals(GameConstants.PLAYER_2, game.getTurn());
	}
	
	class MyGame extends Game {
		public MyGame() {
			super();
			circularBoard = new CircularBoard(3);
		}
	}

}
