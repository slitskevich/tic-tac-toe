package com.tic.game.configuration;

import java.util.List;

import com.tic.errors.ConfigurationException;

/**
 * Represents game configuration as specified by the challenge requirements - read from the file, 3 players.
 */
public class ChallengeGameConfiguration extends FileConfiguration {

	/**
	 * Instantiates a new challenge game configuration with the path to the configuration file
	 *
	 * @param path the configuration file path
	 * @throws ConfigurationException the configuration exception
	 */
	public ChallengeGameConfiguration(String path) throws ConfigurationException {
		super(path, ThreePlayersConfiguration.class);
	}
	
	/**
	 * Instantiates a new challenge game configuration for unit testing
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	ChallengeGameConfiguration() throws InstantiationException, IllegalAccessException {
		super(ThreePlayersConfiguration.class);
	}
	
	/* (non-Javadoc)
	 * @see com.tic.game.configuration.FileConfiguration#initInternalWithLines(java.util.List)
	 */
	@Override
	protected void initInternalWithLines(List<String> lines) throws ConfigurationException {
		if (lines.size() < 2) {
			throw ConfigurationException.invalidConfiguration();
		}
		ThreePlayersConfiguration configuration = (ThreePlayersConfiguration)internalConfiguration;
		try {
			configuration.setGameSize(Integer.parseInt(lines.get(0)));
			configuration.setPlayers(lines.subList(1, lines.size()));
		} catch (ConfigurationException cex) {
			throw cex;
		} catch (Exception ex) {
			throw ConfigurationException.invalidConfiguration();
		}
	}
}
