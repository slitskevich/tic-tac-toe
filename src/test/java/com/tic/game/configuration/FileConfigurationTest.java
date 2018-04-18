package com.tic.game.configuration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.tic.errors.ConfigurationException;

public class FileConfigurationTest {
	
	@Test
	public void testMissingConfiguration() {
		try {
			new ChallengeGameConfiguration("wrong path");
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected ConfigurationException", ex instanceof ConfigurationException);
		}
	}

}
