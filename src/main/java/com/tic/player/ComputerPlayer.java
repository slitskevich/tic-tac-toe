package com.tic.player;

import com.tic.errors.ConfigurationException;
import com.tic.errors.PlayException;
import com.tic.game.Position;

/**
 * Represents computer player
 */
public class ComputerPlayer extends Player {

	/**
	 * Instantiates a new player.
	 *
	 * @param label the label of the player
	 * @throws ConfigurationException the configuration exception
	 */
	public ComputerPlayer(String line) throws ConfigurationException {
		super(line);
	}

	/* (non-Javadoc)
	 * @see com.tic.player.Player#choosePosition()
	 */
	@Override
	public Position choosePosition() throws PlayException {
		Position result = playfield.pickRandomPosition();
		
		output.println("User " + label + " move: " + result);
		
		return result;
	}
}
