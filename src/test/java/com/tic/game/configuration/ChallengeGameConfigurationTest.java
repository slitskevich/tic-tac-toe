package com.tic.game.configuration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tic.errors.ConfigurationException;

public class ChallengeGameConfigurationTest {

	@Test
	public void testEmptyConfiguration() {
		try {
			ChallengeGameConfiguration tested = new ChallengeGameConfiguration();
			tested.initInternalWithLines(new ArrayList<String>());
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected ConfigurationException", ex instanceof ConfigurationException);
		}
	}

	@Test
	public void testInvalidSize() {
		try {
			ChallengeGameConfiguration tested = new ChallengeGameConfiguration();
			List<String> lines = new ArrayList<String>();
			lines.add("a");
			lines.add("b");
			lines.add("c");
			lines.add("d");
			tested.initInternalWithLines(lines);
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected ConfigurationException", ex instanceof ConfigurationException);
		}
	}

	@Test
	public void testValidConfiguration() {
		try {
			ChallengeGameConfiguration tested = new ChallengeGameConfiguration();
			List<String> lines = new ArrayList<String>();
			lines.add("3");
			lines.add("b");
			lines.add("c");
			lines.add("d");
			tested.initInternalWithLines(lines);
			for (int i = 1; i < lines.size(); i += 1) {
				assertTrue(tested.getPlayers().get(i - 1).label == lines.get(i).charAt(0));
			}
		} catch (Exception ex) {
			fail("Didn't expected to fail");
		}
	}

	@Test
	public void testRepeatingPlayerLabels() {
		try {
			ChallengeGameConfiguration tested = new ChallengeGameConfiguration();
			List<String> lines = new ArrayList<String>();
			lines.add("3");
			lines.add("b");
			lines.add("b");
			lines.add("d");
			tested.initInternalWithLines(lines);
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected ConfigurationException", ex instanceof ConfigurationException);
		}
	}

	@Test
	public void testMinimalFieldSize() {
		try {
			ChallengeGameConfiguration tested = new ChallengeGameConfiguration();
			List<String> lines = new ArrayList<String>();
			lines.add("" + (ThreePlayersConfiguration.MINIMAL_SIZE - 1));
			lines.add("b");
			lines.add("b");
			lines.add("d");
			tested.initInternalWithLines(lines);
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected ConfigurationException", ex instanceof ConfigurationException);
		}
	}
	
	@Test
	public void testMaximalFieldSize() {
		try {
			ChallengeGameConfiguration tested = new ChallengeGameConfiguration();
			List<String> lines = new ArrayList<String>();
			lines.add("" + (ThreePlayersConfiguration.MAXIMAL_SIZE + 1));
			lines.add("b");
			lines.add("b");
			lines.add("d");
			tested.initInternalWithLines(lines);
			fail("Expected to fail");
		} catch (Exception ex) {
			assertTrue("Expected ConfigurationException", ex instanceof ConfigurationException);
		}
	}
}
