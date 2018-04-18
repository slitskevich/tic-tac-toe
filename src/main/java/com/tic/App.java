package com.tic;

import com.tic.errors.ConfigurationException;
import com.tic.game.Game;
import com.tic.game.configuration.ChallengeGameConfiguration;
import com.tic.game.configuration.GameConfiguration;

/**
 * The main command line tool class.
 */
public class App {

	/**
	 * The main method. Expects relative path to the game configuration file.
	 * Configures and starts the game
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please specify relative path to the game configuration file");
		} else {
			String configurationPath = System.getProperty("user.dir") + "/" + args[0];
			try {
				System.out.println("Initializing the game...");
				GameConfiguration configuration = new ChallengeGameConfiguration(configurationPath);
				Game game = new Game(configuration, System.out);
				System.out.println("The game started");
				game.play();
				System.out.println("The game completed");
			} catch (ConfigurationException ex) {
				System.err.println("Failed to initialize the game with the configuration at: " + configurationPath);
				System.err.println(ex.getMessage());
			} catch (Exception ex) {
				System.err.println("Game failed: " + ex.getMessage());
			}
		}
	}
}
