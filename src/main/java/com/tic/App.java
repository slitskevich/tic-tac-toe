package com.tic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.tic.errors.ConfigurationException;
import com.tic.game.Game;

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
        			Game game = App.configureGame(configurationPath);
        			System.out.println("The game started");
        			game.play();
        			System.out.println("The game completed");
    			} catch (ConfigurationException ex) {
    				System.err.println("Failed to initialize the game with the configuration at: " + configurationPath);
    				System.err.println(ex.getMessage());
    			}
    		}        
    }
    
    /**
     * Configure game. Reads configuration file defined by the path passed as an execution parameter.
     *
     * @param configurationPath the configuration file absolute path
     * @return configured game.
     * @throws ConfigurationException the configuration fails
     */
    private static Game configureGame(String configurationPath) throws ConfigurationException {
    		Game result = null;
		try {
			FileReader fileReader = new FileReader(configurationPath);
			BufferedReader configurationReader = new BufferedReader(fileReader);
			result = new Game(configurationReader);
			fileReader.close();
			configurationReader.close();
		} catch (FileNotFoundException e) {
			throw ConfigurationException.configurationNotFound();
		} catch (IOException e) {
			throw ConfigurationException.configurationRead();
		}
		return result;
    }
}
