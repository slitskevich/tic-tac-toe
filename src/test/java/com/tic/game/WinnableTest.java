package com.tic.game;

import org.junit.*;

import com.tic.game.Position;
import com.tic.game.Winnable;

import static org.junit.Assert.*;

public class WinnableTest {
	
	private Winnable tested;
	
	private final static int SIZE = 3;
	private final static char playerA = 'a';
	private final static char playerB = 'b';
	
	@Before public void setUp() {
		tested = new Winnable(SIZE);
	}
	
	@Test public void testEmpty() {
		assertTrue("Expected to have a lot of winnable paths", tested.haveWinnablePaths());
		assertNull("Didn't expect a winner", tested.getWinner());
	}
	
	@Test public void testUnwinnable() {
		for (int i = 0; i < SIZE; i += 1) {
			tested.updateWinnablePaths(new Position(0, i), playerA);
			tested.updateWinnablePaths(new Position(1, i), playerB);			
		}		
		assertTrue("Still should be winnable", tested.haveWinnablePaths());
		
		for (int i = 0; i < SIZE; i += 1) {
			tested.updateWinnablePaths(new Position(i, 0), playerA);
			tested.updateWinnablePaths(new Position(i, 1), playerB);			
		}		
		assertFalse("Now shouldn't be winnable", tested.haveWinnablePaths());
	}
	
	@Test public void testBackwardDiagonal() {
		tested.updateWinnablePaths(new Position(0, 0), playerA);
		tested.updateWinnablePaths(new Position(SIZE - 1, SIZE - 1), playerB);	
		
		for (int i = 0; i < SIZE; i += 1) {
			tested.updateWinnablePaths(new Position(SIZE - i - 1, i), playerA);
		}
		assertEquals("Expected backward diagonal winner", tested.backwardDiagonal, tested.getWinner());
	}
	

}
