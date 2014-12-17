package com.kardo.mancala.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.kardo.mancala.model.AbstractBowl;
import com.kardo.mancala.model.Bowl;
import com.kardo.mancala.model.CircularBoard;
import com.kardo.mancala.model.GravaHal;

public class CircularBoardTest {

	CircularBoard board;

	@Before
	public void setUp() {
		board = new CircularBoard(1);
	}

	@Test
	public void testGetHeadBowl() {
		assertEquals(1, board.getHeadBowl().getSeeds());
		assertEquals(1, board.getHeadBowl().getPlayer());
		assertNotNull(board.getHeadBowl().getNext());
	}

	@Test
	public void testGetBoard() {
		ArrayList<AbstractBowl> bowls = board.getBoard();
		assertEquals(14, bowls.size());

		for (AbstractBowl bowl : bowls) {
			if (bowl instanceof Bowl) {
				assertEquals(1, bowl.getSeeds());
			}
			if (bowl instanceof GravaHal) {
				assertEquals(0, bowl.getSeeds());
			}
		}
	}

	@Test
	public void testGetBowl() {
		assertTrue(board.getBowl(6) instanceof GravaHal);
	}

	@Test
	public void testGetPlayer() {
		for (int i = 0; i < 7; i++) {
			assertEquals(1, board.getBowl(i).getPlayer());
		}
		for (int i = 7; i < 14; i++) {
			assertEquals(2, board.getBowl(i).getPlayer());
		}

	}



}
