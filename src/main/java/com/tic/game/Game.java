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
	
	/** The format of the message for the won game */
	public final static String WON_FORMAT = "%s won!";
	
	/** The message for the game draw */
	public final static String DRAW = "Draw!";
	
	/** The incomplete game status message */
	public final static String INCOMPLETE = "The game is not completed";
	
	/** The list of all game players. */
	private List<Player> players;
	
	/** The data structure listing all play field paths that still can be used to win the game */
	private Winnable winnable;
	
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
			next.setOutput(output);
		}
		winnable = new Winnable(configuration.getGameSize());			
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
				Position newPosition = player.choosePosition();
				player = nextPlayer(player);
				makeMove(player, newPosition);
				output.println(playfield);
			} catch (PlayException ex) {
				output.println(ex.getMessage());
			}
		} while (!isCompleted());
		output.println(getResultMessage());
	}
	
	void makeMove(Player player, Position move) throws PlayException {
		playfield.takePosition(player, move);
		winnable.updateWinnablePaths(move, player.label);
	}
	
	/**
	 * @return true, if the game is completed - won or draw; false otherwise
	 */
	public boolean isCompleted() {
		return winnable.getWinner() != null || !winnable.haveWinnablePaths();
	}
	
	/**
	 * @return the result status message
	 */
	public String getResultMessage() {
		if (winnable.getWinner() != null) {
			return String.format(WON_FORMAT, winnable.getWinner().latestPlayer());
		} else if (!winnable.haveWinnablePaths()) {
			return DRAW;
		}
		return INCOMPLETE;
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
	
	/**
	 * @return the list of the game players
	 */
	List<Player> getPlayers() {
		return players;
	}
}
