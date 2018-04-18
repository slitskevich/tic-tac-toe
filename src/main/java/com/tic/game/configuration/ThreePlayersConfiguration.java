package com.tic.game.configuration;

import java.util.ArrayList;
import java.util.List;

import com.tic.errors.ConfigurationException;
import com.tic.player.ComputerPlayer;
import com.tic.player.HumanPlayer;
import com.tic.player.Player;

/**
 * The Class represents game configuration for the play field boards size from 3 to 10 and
 * for three players - two human and one computer.
 */
public class ThreePlayersConfiguration implements GameConfiguration {
	
	/** Minimal play field board size. */
	final static int MINIMAL_SIZE = 3;
	
	/** Maximal play field board size. */
	final static int MAXIMAL_SIZE = 10;
	
	/**  Fixed number of human players. */
	final static int HUMAN_PLAYERS_COUNT = 2;

	/** The game size. */
	private int gameSize;
	
	/** The players. */
	private List<Player> players;

	/* (non-Javadoc)
	 * @see com.tic.game.configuration.GameConfiguration#getGameSize()
	 */
	@Override
	public int getGameSize() {
		return gameSize;
	}

	/* (non-Javadoc)
	 * @see com.tic.game.configuration.GameConfiguration#getPlayers()
	 */
	@Override
	public List<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Sets the game size.
	 *
	 * @param size the new game size
	 * @throws ConfigurationException the configuration exception
	 */
	public void setGameSize(int size) throws ConfigurationException {
		if (size < MINIMAL_SIZE || size > MAXIMAL_SIZE) {
			throw ConfigurationException.wrongSizeBounds(MINIMAL_SIZE, MAXIMAL_SIZE, size);
		}
		gameSize = size;
	}
	
	/**
	 * Sets the players.
	 *
	 * @param playerLabels the labels of players
	 * @throws ConfigurationException the configuration exception if wrong number of labels passed,
	 * labels are invalid or there are repeating labels
	 */
	public void setPlayers(List<String> playerLabels) throws ConfigurationException {
		if (playerLabels.size() != HUMAN_PLAYERS_COUNT + 1) {
			throw ConfigurationException.invalidPlayersCount();
		}
		players = new ArrayList<Player>(playerLabels.size());
		for (int i = 0; i < HUMAN_PLAYERS_COUNT; i += 1) {
			String next = playerLabels.get(i);
			if (playerLabels.lastIndexOf(next) != i) {
				throw ConfigurationException.duplicatedPlayers();
			}
			players.add(new HumanPlayer(playerLabels.get(i)));
		}
		players.add(new ComputerPlayer(playerLabels.get(playerLabels.size() - 1)));
	}

}
