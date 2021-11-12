package bank.cards;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import bank.accounts.Transaction;
import bank.main.DatabaseManager;
import bank.accounts.CheckingAccount;
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
	 * Adds a Transaction to the Card
	 * @implNote If card is a DebitCard, it will update the balance of the associated Checking Account
	 * @param transaction Transaction object
	 * @return 0 if successful, 1 if invalid funds, or 2 for SQLException
	 */
	public int insertTransaction(Transaction transaction){
		//DummyCode Below
		try{
			if(getAtmLimit() >= transaction.getAmount()){
				PreparedStatement stat = DatabaseManager.getConnection().prepareStatement("UPDATE DebitCards SET avaliableBalance = ? WHERE cardNumber = ?");
				stat.setDouble(1, getAtmLimit() - transaction.getAmount());
				stat.setString(2, getCardNumber());
				stat.execute();
				getTransactions().add(transaction);
				return 0;
			}
			else{
				return 1;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return 2; 
		}

	}

}
