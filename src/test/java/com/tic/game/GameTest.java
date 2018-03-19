package com.tic.game;

import org.junit.*;

import com.tic.errors.ConfigurationException;
import com.tic.player.Player;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;

public class GameTest {
	
	@Test public void testInvalidConfiguration1() {
		try {
			String configuration = "a\n";
			StringReader stringReader = new StringReader(configuration);
			BufferedReader reader = new BufferedReader(stringReader);
			new Game(reader);
		} catch (Exception ex) {
			assertTrue("Expected ConfigurationException", ex instanceof ConfigurationException);
		}
	}

	@Test public void testValidConfiguration() {
		try {
			String configuration = "3\nA\nB\nC";
			StringReader stringReader = new StringReader(configuration);
			BufferedReader reader = new BufferedReader(stringReader);
			Game tested = new Game(reader);
			assertNotNull("Expected to create game", tested);
			Player random = tested.randomPlayer();
			assertNotNull("Expected random player", random);
			Player next = tested.nextPlayer(random);
			assertNotNull("Expected random player", random);
			next = tested.nextPlayer(next);
			next = tested.nextPlayer(next);
			assertEquals("Expected to return to the first player", next, random);
		} catch (Exception ex) {
			fail("Didn't expect to fail");
		}
	}
	
	@Test public void testNextPlayer() {
	}

}
