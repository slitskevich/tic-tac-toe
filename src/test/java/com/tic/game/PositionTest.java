package com.tic.game;

import org.junit.*;

import com.tic.errors.PlayException;

import static org.junit.Assert.*;

public class PositionTest {

	@Test public void testWrongPositionFormat1() {
		try {
			Position.positionWithUserInput(null);
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected PlayException", ex instanceof PlayException);
		}
	}
	
	@Test public void testWrongPositionFormat2() {
		try {
			Position.positionWithUserInput("");
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected PlayException", ex instanceof PlayException);
		}
	}
	
	@Test public void testWrongPositionFormat3() {
		try {
			Position.positionWithUserInput("test");
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected PlayException", ex instanceof PlayException);
		}
	}

	@Test public void testWrongPositionFormat4() {
		try {
			Position.positionWithUserInput("a" + Position.SEPARATOR + "b");
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected PlayException", ex instanceof PlayException);
		}
	}

	@Test public void testValidFormat() {
		try {
			int row = 1;
			int column = 1;
			String input = row + Position.SEPARATOR + column;
			Position tested = Position.positionWithUserInput(input);
			assertEquals("Expected proper row", row - 1, tested.getRow());
			assertEquals("Expected proper column", column - 1, tested.getColumn());
			assertEquals("Expected user friendly output", input, tested.toString());
		} catch (Exception ex) {			
			fail("Didn't expected to fail");
		}
	}
}
