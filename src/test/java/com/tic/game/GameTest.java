package com.tic.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tic.game.configuration.ThreePlayersConfiguration;
import com.tic.player.Player;

public class GameTest {
	
	@Test public void testValidConfiguration() {
		try {
			ThreePlayersConfiguration config = new ThreePlayersConfiguration();
			config.setGameSize(3);
			List<String> labels = new ArrayList<String>(3);
			labels.add("A");
			labels.add("B");
			labels.add("C");
			config.setPlayers(labels);
			Game tested = new Game(config, System.out);
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
