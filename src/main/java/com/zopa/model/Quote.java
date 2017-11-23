package com.zopa.model;

public class Quote {
	private int requestedAmount;
	private double rate;
	private double monthlyPayment;
	private double totalPayment;

	/**
	 * @return the requestedAmount
	 */
	public int getRequestedAmount() {
		return requestedAmount;
	}

	/**
	 * @param requestedAmount
	 *            the requestedAmount to set
	 */
	public void setRequestedAmount(int requestedAmount) {
		this.requestedAmount = requestedAmount;
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
	 * @return the monthlyPayment
	 */
	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	/**
	 * @param monthlyPayment
	 *            the monthlyPayment to set
	 */
	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	/**
	 * @return the totalPayment
	 */
	public double getTotalPayment() {
		return totalPayment;
	}

	/**
	 * @param totalPayment
	 *            the totalPayment to set
	 */
	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

}
