package com.tic.game;

import java.io.PrintStream;
import java.util.List;

import com.tic.errors.ConfigurationException;
import com.tic.errors.PlayException;
import com.tic.game.configuration.GameConfiguration;
import com.tic.player.Player;
import com.tic.playfield.Playfield;
import com.tic.util.Random;

/**
 * The class describes game and hosts the game.
 */
public class Game {
	
	/** The list of all game players. */
	private List<Player> players;
	
	/** The game play field. */
	private Playfield playfield;
	
	private PrintStream output;
	
	/**
	 * Instantiates and configures a new game.
	 *
	 * @param configuration the game configuration.
	 * @param output stream to print the game output
	 * @throws ConfigurationException when configuration can't be read
	 */
	public Game(GameConfiguration configuration, PrintStream output) throws ConfigurationException {
		playfield = new Playfield(configuration.getGameSize());
		players = configuration.getPlayers();
		for (Player next : players) {
			next.setPlayfield(playfield);
		}
		this.output = output;
	}
	
	/**
	 * The game execution.
	 */
	public void play() {
		output.println(playfield);
		Player player = randomPlayer();
		do {
			try {
				player.move();
				player = nextPlayer(player);
				output.println(playfield);
			} catch (PlayException ex) {
				output.println(ex.getMessage());
			}
		} while (!playfield.isCompleted());
		output.println(playfield.getResultMessage());
	}
	
	/**
	 * @return the random player from the list of available players
	 */
	Player randomPlayer() {
		return (Player)Random.pickItem(players);
	}
	
	/**
	 * Returns the player that should make a move next after the current player.
	 *
	 * @param player the player - the current player
	 * @return the next player to take the move
	 */
	Player nextPlayer(Player player) {
		int currentIndex = players.indexOf(player);
		int nextIndex = (currentIndex + 1) % players.size();
		return players.get(nextIndex);
	}
}
