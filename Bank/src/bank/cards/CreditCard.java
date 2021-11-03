package bank.cards;

import java.util.Date;

import bank.accounts.Transaction;

public class CreditCard extends Card {
	
	private double interest;
	private double maxBalance;
	private double avaliableBalance;

	public CreditCard(double interest) {
		setInterest(interest);
	}
	
	public CreditCard(String num, int csv, Date expire, double interest, double max, double bal) {
		super(num, csv, expire);
		setInterest(interest);
		setMaxBalance(max);
		setAvaliableBalance(bal);
	}

	/**
	 * @return Get the interest rate
	 */
	public double getInterest() {
		return interest;
	}


	/**
	 * @param interest The interest rate to set
	 * @return True if parameter is greater than 0
	 */
	public boolean setInterest(double interest) {
		if (interest <= 0) {
			return false;
		}
		this.interest = interest;
		return true;
	}

	/**
	 * @return The maximum balance allocated
	 */
	public double getMaxBalance() {
		return maxBalance;
	}

	/**
	 * @param maxBalance Set the maximum balance allowed
	 * @return True if parameter is greater than 0
	 */
	public boolean setMaxBalance(double maxBalance) {
		if (maxBalance <= 0) {
			return false;
		}
		this.maxBalance = maxBalance;
		return true;
	}

	/**
	 * @return Available balance left
	 */
	public double getAvaliableBalance() {
		return avaliableBalance;
	}

	/**
	 * @param avaliableBalance Available balance left
	 * @return True if parameter is positive
	 * @apiNote This cannot be negative!
	 */
	public boolean setAvaliableBalance(double avaliableBalance) {
		if (avaliableBalance < 0) {
			return false;
		}
		this.avaliableBalance = avaliableBalance;
		return true;
	}
	
	public boolean makePayment(double payment) {
		//TODO:
		return false;
	}
	

}
