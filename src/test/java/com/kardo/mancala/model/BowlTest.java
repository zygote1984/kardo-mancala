package com.kardo.mancala.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BowlTest {

	Bowl bowl;

	@Before
	public void setUp() {
		bowl = new Bowl(6, 1, null);
	}

	@Test
	public void testIncrement() {
		assertEquals(6, bowl.getSeeds());
		bowl.increment();
		assertEquals(7, bowl.getSeeds());
	}

	@Test
	public void testSetNext() {
		assertNull(bowl.getNext());
		bowl.setNext(new Bowl(6, 1, null));
		assertNotNull(bowl.getNext());
	}

	@Test
	public void testEmpty() {
		assertEquals(6, bowl.getSeeds());
		bowl.empty();
		assertEquals(0, bowl.getSeeds());
	}

}
