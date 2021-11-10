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
	 * @return True if parameter is positive and not greater than maximum balance
	 * @apiNote This cannot be negative!
	 */
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
			PreparedStatement stat = DatabaseManager.getConnection().prepareStatement("UPDATE CreditCards SET avaliableBalance = ? WHERE cardNumber = ?");
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
	

}
