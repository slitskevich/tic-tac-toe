package com.tic.player;

import com.tic.errors.ConfigurationException;
import com.tic.errors.PlayException;
import com.tic.game.Position;
import com.tic.playfield.Playfield;

/**
 * Represents computer player
 */
public class ComputerPlayer extends Player {

	/**
	 * Instantiates a new player.
	 *
	 * @param label the label of the player
	 * @param playfield the game play field
	 * @throws ConfigurationException the configuration exception
	 */
	public ComputerPlayer(String line, Playfield playfield) throws ConfigurationException {
		super(line, playfield);
	}

	/* (non-Javadoc)
	 * @see com.tic.player.Player#choosePosition()
	 */
	@Override
	public Position choosePosition() throws PlayException {
		Position result = playfield.pickRandomPosition();
		
		System.out.println("User " + label + " move: " + result);
		
		return result;
	}
}
