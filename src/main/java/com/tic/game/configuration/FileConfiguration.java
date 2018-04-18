package com.tic.game.configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tic.errors.ConfigurationException;
import com.tic.errors.TicSystemException;
import com.tic.player.Player;

/**
 * The Class FileConfiguration.
 */
public abstract class FileConfiguration implements GameConfiguration {
	
	/** The wrapped configuration. */
	protected GameConfiguration internalConfiguration;
	
	/**
	 * Reads configuration from the file defined by the path and initializes internal configuration with the read content.
	 *
	 * @param path the path of the file containing the configuration
	 * @param configurationClass the class of the internal configuration
	 * @throws ConfigurationException the configuration exception if the configuration can't be read from the file or is invalid
	 */
	public FileConfiguration(String path, Class<? extends GameConfiguration> configurationClass) throws ConfigurationException {
		try {
			internalConfiguration = configurationClass.newInstance();
			List<String> lines = configurationLines(path);
			initInternalWithLines(lines);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			throw TicSystemException.initializationException();
		}
	}
	
	/**
	 * Instantiates a new file configuration. Used for Unit testing
	 *
	 * @param configurationClass the configuration class
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	FileConfiguration(Class<? extends GameConfiguration> configurationClass) throws InstantiationException, IllegalAccessException {
		internalConfiguration = configurationClass.newInstance();
	}
	
	/**
	 * Uses the read file content to initialize wrapped configuration instance
	 *
	 * @param lines the content of the configuration file
	 * @throws ConfigurationException the configuration exception
	 */
	protected abstract void initInternalWithLines(List<String> lines) throws ConfigurationException;
	
	/**
	 * Reads configuration file content
	 *
	 * @param configurationPath the path to the configuration file
	 * @return the list configuration file content
	 * @throws ConfigurationException the configuration exception if the file can't be found or read
	 */
	private List<String> configurationLines(String configurationPath) throws ConfigurationException {
		try {
			List<String> result = new ArrayList<String>();
			FileReader fileReader = new FileReader(configurationPath);
			BufferedReader configurationReader = new BufferedReader(fileReader);
			String line = configurationReader.readLine();
			while (line != null) {
				result.add(line);
				line = configurationReader.readLine();
			}
			fileReader.close();
			configurationReader.close();
			return result;
		} catch (FileNotFoundException e) {
			throw ConfigurationException.configurationNotFound();
		} catch (IOException e) {
			throw ConfigurationException.configurationRead();
		}

	}


	/* (non-Javadoc)
	 * @see com.tic.game.configuration.GameConfiguration#getGameSize()
	 */
	@Override
	public int getGameSize() {
		return internalConfiguration.getGameSize();
	}

	/* (non-Javadoc)
	 * @see com.tic.game.configuration.GameConfiguration#getPlayers()
	 */
	@Override
	public List<Player> getPlayers() {
		return internalConfiguration.getPlayers();
	}

}
