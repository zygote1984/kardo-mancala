package com.kardo.mancala.controller;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.kardo.mancala.game.Game;
import com.kardo.mancala.game.GameConstants;
import com.kardo.mancala.model.CircularBoard;
import com.kardo.mancala.ui.GameListener;

public class GameTest {

	Game game; 
	GameListener listener;
	
	@Before
	public void setUp() {
		game = new Game();
		listener = new GameListener() {
			
			@Override
			public void updateBoard() {
				
			}
			
			@Override
			public void announceWinner(int winner, int seeds) {
				
			}
			
			@Override
			public void annouceTie() {
				
			}
		};
		game.setListener(listener);
	}
	
	
	@Test
	public void testDistributeSeeds() {
		game = new MyGame();
		game.setListener(listener);
		game.distributeSeeds(3);
		assertEquals(1, game.getBoard().get(6).getSeeds());
		game.distributeSeeds(0);
		assertEquals(5, game.getBoard().get(6).getSeeds());		
	}

	@Test
	public void testGetTurn() {
		assertEquals(GameConstants.PLAYER_1, game.getTurn());
		game.distributeSeeds(0);
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
