package com.tic.player;

import org.junit.*;

import com.tic.errors.ConfigurationException;
import com.tic.playfield.Playfield;

import static org.junit.Assert.*;

public class PlayerTest {
	
	@Test public void testInvalidLabel1() {
		try {
			new HumanPlayer(null);
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected ConfigurationException", ex instanceof ConfigurationException);
		}
	}

	@Test public void testInvalidLabel2() {
		try {
			new ComputerPlayer("");
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected ConfigurationException", ex instanceof ConfigurationException);
		}
	}

	@Test public void testInvalidLabel3() {
		try {
			new HumanPlayer("long");
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected ConfigurationException", ex instanceof ConfigurationException);
		}
	}
	
	@Test public void takeRandomPosition() {
		try {
			Playfield field = new Playfield(3);
			Player computer = new ComputerPlayer("C");
			computer.setPlayfield(field);
			computer.move();
			assertTrue("Hoped to find player cell", field.toString().indexOf('C') > -1);
		} catch (Exception ex) {
			fail("Didn't expect to fail");
		}
	}

}
