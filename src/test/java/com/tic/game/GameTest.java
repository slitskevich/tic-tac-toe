package com.tic.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tic.game.configuration.ThreePlayersConfiguration;
import com.tic.player.HumanPlayer;
import com.tic.player.Player;
import com.tic.playfield.Playfield;

public class GameTest {
	
	private final static int SIZE = 3;
	private final static String A = "a";

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
	
	@Test public void testDraw() {
		try {
			ThreePlayersConfiguration configuration = new ThreePlayersConfiguration();
			configuration.setGameSize(3);
			List<String> labels = new ArrayList<String>(3);
			labels.add("A");
			labels.add("B");
			labels.add("C");
			configuration.setPlayers(labels);
			Game tested = new Game(configuration, System.out);
			assertEquals("Expected incomplete status", Game.INCOMPLETE, tested.getResultMessage());
			Player playerA = tested.getPlayers().get(0);
			Player playerB = tested.getPlayers().get(1);
			Player playerC = tested.getPlayers().get(2);
			tested.makeMove(playerA, new Position(0, 0));
			tested.makeMove(playerB, new Position(0, 1));
			tested.makeMove(playerC, new Position(0, 2));
			tested.makeMove(playerB, new Position(1, 0));
			tested.makeMove(playerC, new Position(1, 1));
			tested.makeMove(playerB, new Position(2, 0));
			tested.makeMove(playerA, new Position(2, 2));
			assertEquals("Expected draw", Game.DRAW, tested.getResultMessage());
		} catch (Exception e) {
			fail("Didn't expecte to fail");
		}
	}
	
	@Test public void testWinner() {
		try {
			ThreePlayersConfiguration configuration = new ThreePlayersConfiguration();
			configuration.setGameSize(3);
			List<String> labels = new ArrayList<String>(3);
			labels.add("A");
			labels.add("B");
			labels.add("C");
			configuration.setPlayers(labels);
			Game game = new Game(configuration, System.out);
			assertEquals("Expected incomplete status", Game.INCOMPLETE, game.getResultMessage());
			Player playerA = game.getPlayers().get(0);
			for (int i = 0; i < SIZE; i += 1) {
				game.makeMove(playerA, new Position(0, i));
			}
			assertEquals("Expected winner", String.format(Game.WON_FORMAT, playerA.label), game.getResultMessage());
		} catch (Exception e) {
			fail("Didn't expecte to fail");
		}
	}

	@Test public void testNextPlayer() {
	}

}
