package com.tic.player;

import java.util.Scanner;

import com.tic.errors.ConfigurationException;
import com.tic.errors.PlayException;
import com.tic.game.Position;

/**
 * Represents human player
 */
public class HumanPlayer extends Player {
	
	/** The reader. */
	static Scanner reader = new Scanner(System.in);
	
	/**
	 * Instantiates a new player.
	 *
	 * @param label the label of the player
	 * @throws ConfigurationException the configuration exception
	 */
	public HumanPlayer(String label) throws ConfigurationException {
		super(label);
	}

	/* (non-Javadoc)
	 * @see com.tic.player.Player#choosePosition()
	 */
	@Override
	protected Position choosePosition() throws PlayException {
		Position result = null;	
		
		System.out.println("Player " + label + ", enter next positon (row" + Position.SEPARATOR + "column - where 1" + Position.SEPARATOR + "1 is the top left corner): ");
		result = Position.positionWithUserInput(reader.nextLine());
		
		return result;
	}
}
