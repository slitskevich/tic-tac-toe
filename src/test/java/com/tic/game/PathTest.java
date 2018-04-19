package com.tic.game;

import org.junit.*;

import com.tic.game.Path;
import com.tic.playfield.Playfield;

import static org.junit.Assert.*;

public class PathTest {
	
	private static final int SIZE = 5;
	
	@Test public void testInitialState() {		
		Path tested = new Path(SIZE);
		
		assertTrue("Expected to be winnable", tested.isWinnable());
		assertFalse("Didn't expect winner", tested.isWinner());
		assertEquals("Expected empty latest player", Playfield.EMPTY, tested.latestPlayer());
	}

	@Test public void testWinner() {		
		Path tested = new Path(SIZE);
		char player = 'a';
		for (int i = 0; i < SIZE; i += 1) {
			tested.putLabel(player);
		}
		
		assertTrue("Expected to be winnable", tested.isWinnable());
		assertTrue("Expected to be winner", tested.isWinner());
		assertEquals("Expected proper latest player", player, tested.latestPlayer());
	}

	@Test public void testNotWinnable() {		
		Path tested = new Path(SIZE);
		char playerA = 'a';
		char playerB = 'b';
		tested.putLabel(playerA);
		tested.putLabel(playerB);
		
		assertFalse("Didn't expected to be winnable", tested.isWinnable());
		assertFalse("Didn't expected to be winner", tested.isWinner());
		assertEquals("Expected proper latest player", playerB, tested.latestPlayer());
	}
}
