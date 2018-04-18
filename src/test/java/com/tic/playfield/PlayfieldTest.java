package com.tic.playfield;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	private final static String B = "b";

	@Test public void testValidConfiguration() {
		try {
			tested = new Playfield(10);
			assertFalse("Expected not completed field", tested.isCompleted());
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
	
	@Test public void testDraw() {
		try {
			tested = new Playfield(3);
			assertEquals("Expected incomplete status", Playfield.INCOMPLETE, tested.getResultMessage());
			Player playerA = new HumanPlayer(A);
			playerA.setPlayfield(tested);
			Player playerB = new HumanPlayer(B);
			playerB.setPlayfield(tested);
			Player playerC = new HumanPlayer("C");
			playerC.setPlayfield(tested);
			Player playerD = new HumanPlayer("D");
			playerD.setPlayfield(tested);
			tested.takePosition(playerA, new Position(0, 0));
			tested.takePosition(playerB, new Position(0, 1));
			tested.takePosition(playerC, new Position(0, 2));
			tested.takePosition(playerB, new Position(1, 0));
			tested.takePosition(playerC, new Position(1, 1));
			tested.takePosition(playerD, new Position(1, 2));
			tested.takePosition(playerB, new Position(2, 0));
			tested.takePosition(playerD, new Position(2, 1));
			tested.takePosition(playerA, new Position(2, 2));
			assertEquals("Expected draw", Playfield.DRAW, tested.getResultMessage());
		} catch (Exception e) {
			fail("Didn't expecte to fail");
		}
	}
	
	@Test public void testWinner() {
		try {
			tested = new Playfield(3);
			assertEquals("Expected incomplete status", Playfield.INCOMPLETE, tested.getResultMessage());
			Player playerA = new HumanPlayer(A);
			playerA.setPlayfield(tested);
			for (int i = 0; i < SIZE; i += 1) {
				tested.takePosition(playerA, new Position(0, i));
			}
			assertEquals("Expected winner", String.format(Playfield.WON_FORMAT, playerA.label), tested.getResultMessage());
		} catch (Exception e) {
			fail("Didn't expecte to fail");
		}
	}
}
