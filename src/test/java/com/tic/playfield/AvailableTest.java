package com.tic.playfield;

import org.junit.*;

import com.tic.errors.PlayException;
import com.tic.game.Position;

import static org.junit.Assert.*;

public class AvailableTest {
	
	private Available tested;
	
	private final static int SIZE = 3;
	
	@Before public void setUp() {
		tested = new Available(SIZE);
	}
	
	@Test public void testPickRandom() {
		try {
			Position pick = tested.pickRandomAvailablePosition();
			assertTrue("Invalid position pick", pick.getRow() < SIZE);
			assertTrue("Invalid position pick", pick.getColumn() < SIZE);
		} catch (Exception ex) {
			fail("Expected to succeed");
		}
	}
	
	@Test public void testAllUnvailable() {
		for (int i = 0; i < SIZE; i += 1) {
			for (int j = 0; j < SIZE; j += 1) {
				Position move = new Position(i, j);
				assertTrue("Expected available position", tested.isPositionAvailable(move));
				tested.updateAvailablePosition(move);
				assertFalse("Expected unavailable position", tested.isPositionAvailable(move));
			}
		}
		try {
			tested.pickRandomAvailablePosition();
			fail("Expected an exception");
		} catch (Exception ex) {
			assertTrue("Expected PlayException", ex instanceof PlayException);
		}
	}

}
