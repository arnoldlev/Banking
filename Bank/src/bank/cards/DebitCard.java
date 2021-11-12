package bank.cards;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import bank.accounts.CheckingAccount;
import bank.accounts.Transaction;
import bank.main.DatabaseManager;

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

	/**
	 * @return Maximum transaction amount allowed to make
	 */
	public double getMaxTransaction() {
		return maxTransaction;
	}

	/**
	 * @param maxTransaction Maximum transaction amount allowed to make
	 * @return True if parameter is positive
	 * @apiNote Customer can choose to have a 0 max!
	 */
	public boolean setMaxTransaction(double maxTransaction) {
		if (maxTransaction < 0) {
			return false;
		}
		this.maxTransaction = maxTransaction;
		return true;
	}

	/**
	 * @return The maximum amount allowed to withdraw from ATMs
	 */
	public double getAtmLimit() {
		return atmLimit;
	}

	/**
	 * @param atmLimit The maximum amount allowed to withdraw from ATMs
	 * @return True if parameter is positive
	 * @apiNote Customer can choose to have a 0 max!
	 */
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
	public boolean addTransaction(CheckingAccount acc, Transaction transaction){
		//DummyCode Below
		try{
			if(getAtmLimit() >= transaction.getAmount()){
				PreparedStatement stat = DatabaseManager.getConnection().prepareStatement("UPDATE DebitCards SET avaliableBalance = ? WHERE cardNumber = ?"); // No such column
				stat.setDouble(1, getAtmLimit() - transaction.getAmount());
				stat.setString(2, getCardNumber());
				stat.execute();
				getTransactions().add(transaction);
				return true;
			}
			else{
				return false;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return false; 
		}

	}

}
