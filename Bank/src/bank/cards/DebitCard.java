package bank.cards;

import java.util.Date;

import bank.accounts.CheckingAccount;
import bank.accounts.Transaction;

public class DebitCard extends Card {
	
	private double maxTransaction;
	private double atmLimit;

	public DebitCard() {
		super();
	}
	
	public DebitCard(String num, int csv, Date expire, double maxTrans, double limit) {
		super(num, csv, expire);
		setMaxTransaction(maxTrans);
		setAtmLimit(limit);
	}

	public double getMaxTransaction() {
		return maxTransaction;
	}

	public boolean setMaxTransaction(double maxTransaction) {
		if (maxTransaction < 0) {
			return false;
		}
		this.maxTransaction = maxTransaction;
		return true;
	}

	public double getAtmLimit() {
		return atmLimit;
	}

	public boolean setAtmLimit(double atmLimit) {
		if (atmLimit < 0) {
			return false;
		}
		this.atmLimit = atmLimit;
		return true;
	}

	/**
	 * Creates a transaction, adds it to Database and updates balance
	 * @param transaction Transaction object
	 * @return true if successful, else false
	 */
	public boolean addTransaction(CheckingAccount acc, Transaction transaction) {
		if (transaction.getAmount() > getMaxTransaction() || acc.getBalance() < transaction.getAmount()) {
			return false;
		}
		
		insertTransaction(transaction);
		
		if (!acc.withdraw(transaction.getAmount(), transaction.getDescription())) {
			return false;
		}
		
		return true;

	}

}
