package bank.cards;

import java.util.Date;

public class CreditCard extends Card {
	
	private double interest;
	private double maxBalance;
	private double avaliableBalance;

	public CreditCard(String name) {
		super(name);
	}
	
	public CreditCard(String num, int csv, Date expire, String name, double interest, double max, double bal) {
		super(num, csv, expire, name);
		setInterest(interest);
		setMaxBalance(max);
		setAvaliableBalance(bal);
	}

	/**
	 * @return the interest
	 */
	public double getInterest() {
		return interest;
	}

	/**
	 * @param interest the interest to set
	 */
	public void setInterest(double interest) {
		this.interest = interest;
	}

	/**
	 * @return the maxBalance
	 */
	public double getMaxBalance() {
		return maxBalance;
	}

	/**
	 * @param maxBalance the maxBalance to set
	 */
	public void setMaxBalance(double maxBalance) {
		this.maxBalance = maxBalance;
	}

	/**
	 * @return the avaliableBalance
	 */
	public double getAvaliableBalance() {
		return avaliableBalance;
	}

	/**
	 * @param avaliableBalance the avaliableBalance to set
	 */
	public void setAvaliableBalance(double avaliableBalance) {
		this.avaliableBalance = avaliableBalance;
	}

}
