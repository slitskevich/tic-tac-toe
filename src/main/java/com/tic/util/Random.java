package com.tic.util;

import java.util.*;

/**
 * Utility class helping to pick random list item
 */
public class Random {
	
	
	/**
	 * Picks random list item.
	 *
	 * @param items the list 
	 * @return the random list item
	 */
	public static Object pickItem(List<? extends Object> items) {
		return items.get(Random.pickIndex(items));
	}
	
	/**
	 * Picks random list index.
	 *
	 * @param items the list
	 * @return the random list index
	 */
	public static int pickIndex(List<? extends Object> items) {
		return Random.randomIndex(Random.random(), items);
	}
	
	/**
	 * Adjusts random value in the range [0.0 ..< 1.0] to a valid list index.
	 *
	 * @param random the random value in the range [0.0 ..< 1.0]
	 * @param items the list
	 * @return the list index
	 */
	static int randomIndex(double random, List<? extends Object> items) {
		return (int)Math.floor((random * (items.size())));
	}
	
	/**
	 * @return random double in the range [0.0 ..< 1.0]
	 */
	private static double random() {
		return Math.random();
	}
}
