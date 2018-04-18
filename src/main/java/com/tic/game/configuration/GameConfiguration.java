package com.tic.game.configuration;

import java.util.List;

import com.tic.player.Player;

/**
 * Represents Game configuration.
 */
public interface GameConfiguration {
	
	/**
	 * Gets the game size.
	 *
	 * @return the size of the playfield board
	 */
	public int getGameSize();
	
	/**
	 * Gets the players.
	 *
	 * @return the list of game players
	 */
	public List<Player> getPlayers();
}
