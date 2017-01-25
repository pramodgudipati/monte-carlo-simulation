package com.portfolio.montecarlosimulator;

import java.util.HashMap;
import java.util.Map;

public class MonteCarloSimulator {

	private Portfolio[] portfolios;
	private double inflation;
	private long numberofSimulations;
	private int years;
	private Map<Portfolio, SimulationProgressandResults> progress;

	public MonteCarloSimulator(Portfolio[] portfolios) {
		this.portfolios = portfolios;
		this.inflation = 0.035;
		this.years = 20;
		this.numberofSimulations = 10000;
		progress = new HashMap<Portfolio, SimulationProgressandResults>();

		for (Portfolio p : portfolios) {
			progress.put(
					p,
					new SimulationProgressandResults(p.getMean(), p
							.getStandardDeviation()));
		}

	}

	/*
	 * run simulation using portfolio properties and random sampling using
	 * NormalDistribution. store results in DescriptiveStatistiscs object.
	 */
	public void run() {
		for (int i = 0; i < numberofSimulations; i++) {
			for (Portfolio p : portfolios) {
				double investmentReturn = p.getInitialInvestment();
				for (int j = 0; j < years; j++) {
					investmentReturn += progress.get(p).nextSample()
							* investmentReturn;
					investmentReturn -= inflation * investmentReturn;
				}
				progress.get(p).save(investmentReturn);
			}
		}

		System.out
				.println(" Portfolio Type |   Median  |  Best Case | Worst Case");
		for (Portfolio p : portfolios) {
			SimulationProgressandResults res = progress.get(p);
			p.setMedian(res.getPercentile(50));
			p.setBestCase(res.getPercentile(90));
			p.setWorstCase(res.getPercentile(10));
			System.out.println(p.getName() + " | " + p.getMedian() + " | "
					+ p.getBestCase() + " | " + p.getWorstCase());
		}
	}

	public Portfolio[] getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(Portfolio[] portfolios) {
		this.portfolios = portfolios;
	}

	public double getInflation() {
		return inflation;
	}

	public void setInflation(double inflation) {
		this.inflation = inflation;
	}

	public long getNumberofSimulations() {
		return numberofSimulations;
	}

	public void setNumberofSimulations(long numberofSimulations) {
		this.numberofSimulations = numberofSimulations;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

}
