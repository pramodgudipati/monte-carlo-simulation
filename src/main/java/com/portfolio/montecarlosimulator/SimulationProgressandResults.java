package com.portfolio.montecarlosimulator;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class SimulationProgressandResults {

	private NormalDistribution normalDistribution;

	/*
	 * Maintains a data set of values of a single variable and computes
	 * descriptive statistics based on stored data.
	 */
	private DescriptiveStatistics results;

	public SimulationProgressandResults(double mean, double standardDeviation) {
		/*
		 * Create a normal distribution using the given mean and standard
		 * deviation.
		 */
		this.normalDistribution = new NormalDistribution(mean,
				standardDeviation);
		this.results = new DescriptiveStatistics();
	}

	public void save(double investmentReturn) {
		// Adds the value to the data set.
		this.results.addValue(investmentReturn);
	}

	public double nextSample() {
		// Generate a random value sampled from this distribution.
		return this.normalDistribution.sample();
	}

	public double getPercentile(double n) {
		// Returns an estimate for the nth percentile of the stored values.
		return this.results.getPercentile(n);
	}
}
