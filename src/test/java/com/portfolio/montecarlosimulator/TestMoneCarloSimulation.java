package com.portfolio.montecarlosimulator;


import org.junit.Test;

import com.portfolio.montecarlosimulator.MonteCarloSimulator;
import com.portfolio.montecarlosimulator.Portfolio;

public class TestMoneCarloSimulation {

	@Test
	public void test() {
		Portfolio aggressive = new Portfolio("Aggressive", 100000, 0.094324, 0.15675);
		Portfolio veryconservative = new Portfolio("Very Conservative", 100000, 0.06189, 0.06438);
		Portfolio[] portfolios = { aggressive, veryconservative};
		MonteCarloSimulator mcs = new MonteCarloSimulator(portfolios);
		mcs.run();
	}

}
