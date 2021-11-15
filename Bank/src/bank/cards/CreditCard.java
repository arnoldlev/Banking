package bank.cards;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import bank.accounts.Transaction;
import bank.main.DatabaseManager;

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

	
	public double getInterest() {
		return interest;
	}

	public boolean setInterest(double interest) {
		if (interest <= 0) {
			return false;
		}
		this.interest = interest;
		return true;
	}

	public double getMaxBalance() {
		return maxBalance;
	}

	public boolean setMaxBalance(double maxBalance) {
		if (maxBalance <= 0) {
			return false;
		}
		this.maxBalance = maxBalance;
		return true;
	}

	public double getAvaliableBalance() {
		return avaliableBalance;
	}

	public boolean setAvaliableBalance(double avaliableBalance) {
		if (avaliableBalance < 0 || avaliableBalance > getMaxBalance()) {
			return false;
		}
		this.avaliableBalance = avaliableBalance;
		return true;
	}
	
	/**
	 * Submits a payment 
	 * @param payment The amount being paid off
	 * @return
	 */
	public boolean makePayment(double payment) {
		if (payment <= 0 || (getAvaliableBalance() + payment) > getMaxBalance()) {
			return false;
		}
		
		setAvaliableBalance(getAvaliableBalance() + payment); 
		Transaction t = new Transaction("Card Payment", payment);
		try {
			PreparedStatement stat = DatabaseManager.getConnection().prepareStatement("UPDATE CreditCards SET availableBalance = ? WHERE cardNumber = ?");
			stat.setDouble(1, getAvaliableBalance());
			stat.setString(2, getCardNumber());
			stat.execute();
			
			insertTransaction(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Creates a transaction, adds it to Database and updates balance
	 * @param transaction Transaction object
	 * @return true if successful, else false
	 */
	public boolean addTransaction(Transaction transaction) {
		try {
			if (getAvaliableBalance() >= transaction.getAmount()) {
				PreparedStatement stat = DatabaseManager.getConnection().prepareStatement("UPDATE CreditCards SET availableBalance = ? WHERE cardNumber = ?");
				stat.setDouble(1, getAvaliableBalance() - transaction.getAmount());
				stat.setString(2, getCardNumber());
				stat.execute();
				
				setAvaliableBalance(getAvaliableBalance() - transaction.getAmount());
				insertTransaction(transaction);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false; 
		}

	}
}
