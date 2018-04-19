package com.tic.playfield;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.tic.errors.PlayException;
import com.tic.game.Position;
import com.tic.player.HumanPlayer;
import com.tic.player.Player;

public class PlayfieldTest {
	
	private Playfield tested;
	
	private final static int SIZE = 3;
	private final static String A = "a";

	@Test public void testValidConfiguration() {
		try {
			int TEST_SIZE = 10;
			tested = new Playfield(TEST_SIZE);
			assertEquals("Expected the proper field size", TEST_SIZE, tested.size);
			assertEquals("Expected the proper board size", TEST_SIZE, tested.getBoard().getSize());
			assertEquals("Expected the proper available cells structure size", TEST_SIZE, tested.getAvailable().getSize());
		} catch (Exception ex) {
			fail("Didn't expected to fail");
		}
	}
	
	@Test public void testInvalidPosition() {
		try {
			tested = new Playfield(SIZE);
			Player playerA = new HumanPlayer(A);
			playerA.setPlayfield(tested);
			tested.takePosition(playerA, new Position(-1, -1));
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected PlayException", ex instanceof PlayException);
		}
	}
	
	@Test public void testInvalidPosition2() {
		try {
			tested = new Playfield(SIZE);
			Player playerA = new HumanPlayer(A);
			playerA.setPlayfield(tested);
			tested.takePosition(playerA, new Position(SIZE, SIZE));
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected PlayException", ex instanceof PlayException);
		}
	}
	
	@Test public void testUnavailablePosition() {
		try {
			tested = new Playfield(SIZE);
			Player playerA = new HumanPlayer(A);
			playerA.setPlayfield(tested);
			tested.takePosition(playerA, new Position(0, 0));
			tested.takePosition(playerA, new Position(0, 0));
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected PlayException", ex instanceof PlayException);
		}
	}
	
}
