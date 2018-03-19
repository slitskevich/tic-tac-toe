package com.tic.playfield;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tic.game.Position;

public class BoardTest {
	private final static int SIZE = 3;
	private final static char playerA = 'a';
	
	private Board tested;
	
	@Before public void setUp() {
		tested = new Board(SIZE);
	}
	
	@Test public void testEmpty() {
		for (int i = 0; i < SIZE; i += 1) {
			for (int j = 0; j < SIZE; j += 1) {
				assertEquals("Expected empty cell", Playfield.EMPTY, tested.labelAtPosition(new Position(i, j)));
			}
		}
	}
	
	@Test public void testPlayer() {
		Position position = new Position(0, 0);
		tested.occupyFieldPosition(position, playerA);
		assertEquals("Expected to see player A", playerA, tested.labelAtPosition(position));
	}
	
	@Test public void testOutputLineSeparator() {
		String line = tested.lineSeparator().toString();
		assertTrue("Expected new line at the beginning", line.startsWith(Board.NEW_LINE));
		assertTrue("Expected new line at the end", line.endsWith(Board.NEW_LINE));
		assertTrue("Expected to find separator char", line.indexOf(Board.ROW_SEPARATOR) > -1);
	}
	
	@Test public void testOutput() {
		tested.occupyFieldPosition(new Position(0, 0), playerA);
		assertTrue("Expected to find player cell", tested.toString().indexOf(playerA) > -1);
	}

}
