package com.zopa.model;

public class Offer implements Comparable {

	private String name;
	private double rate;
	private int available;

	public Offer(String name, double rate, int available) {
		this.name = name;
		this.rate = rate;
		this.available = available;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the available
	 */
	public int getAvailable() {
		return available;
	}

	/**
	 * @param available
	 *            the available to set
	 */
	public void setAvailable(int available) {
		this.available = available;
	}

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Offer)) {
			throw new IllegalArgumentException();
		}
		int result = 0;
		Offer that = (Offer) o;
		if (getRate() > that.getRate()) {
			result = 1;
		} else if (getRate() < that.getRate()) {
			result = -1;
		} else if (getAvailable() > that.getAvailable()) {
			result = 1;
		} else if (getAvailable() < that.getAvailable()) {
			result = -1;
		}

		return result;
	}

}
