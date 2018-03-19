package com.tic.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tic.errors.ConfigurationException;
import com.tic.errors.PlayException;
import com.tic.player.ComputerPlayer;
import com.tic.player.HumanPlayer;
import com.tic.player.Player;
import com.tic.playfield.Playfield;
import com.tic.util.Random;

/**
 * The class describes game and hosts the game.
 */
public class Game {
	
	/** Fixed number of human players */
	final static int HUMAN_PLAYERS_COUNT = 2;
	
	/** The list of all game players. */
	private List<Player> players = new ArrayList<Player>(HUMAN_PLAYERS_COUNT + 1);
	
	/** The game play field. */
	private Playfield playfield;
	
	/**
	 * Instantiates and configures a new game.
	 *
	 * @param configurationReader the reader to read the configuration line by line.
	 * @throws ConfigurationException when configuration can't be read
	 */
	public Game(BufferedReader configurationReader) throws ConfigurationException {
		try {			
			playfield = new Playfield(configurationReader.readLine());
			for (int i = 0; i < HUMAN_PLAYERS_COUNT; i += 1) {
				players.add(new HumanPlayer(configurationReader.readLine(), playfield));
			}
			players.add(new ComputerPlayer(configurationReader.readLine(), playfield));
		} catch (IOException e) {
			throw ConfigurationException.configurationRead();
		}
	}
	
	/**
	 * The game execution.
	 */
	public void play() {
		System.out.println(playfield);
		Player player = randomPlayer();
		do {
			try {
				player.move();
				player = nextPlayer(player);
				System.out.println(playfield);
			} catch (PlayException ex) {
				System.out.println(ex.getMessage());
			}
		} while (!playfield.isCompleted());
		System.out.println(playfield.getResultMessage());
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
