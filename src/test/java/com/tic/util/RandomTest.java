package com.tic.util;

import org.junit.*;
import static org.junit.Assert.assertEquals;

import java.util.*;

public class RandomTest {
	
	@Test public void testRandomIndex() {
		int size = 5;
		List<Integer> tested = new ArrayList<Integer>(5);
		for (int i = 0; i < size; i += 1) {
			tested.add(i);
		}
		
		assertEquals("expected to find first item", 0, Random.randomIndex(0.0, tested));
		Random.randomIndex(1.0, tested);
		assertEquals("expected to find last item", size - 1, Random.randomIndex(0.99, tested));
	}

}
