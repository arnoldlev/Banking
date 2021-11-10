package bank.accounts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import bank.main.DatabaseManager;

public abstract class Account {
	
	private long accountID;
	private double balance;
	private Date openDate;
	private ArrayList<Transaction> transactions;
	
	public Account(long ID, double bal, Date open) {
		setAccountID(ID);
		setBalance(bal);
		setOpenDate(open);
		setTransactions(new ArrayList<Transaction>());
	}
	
	public Account() {
		Random rand = new Random();
		int ID = rand.nextInt(9000000) + 100000;
		setAccountID(ID);
		setOpenDate(new Date());
		setTransactions(new ArrayList<Transaction>());
	}

	public long getAccountID() {
		return accountID;
	}

	public void setAccountID(long accountID) {
		this.accountID = accountID; 
	}

	/**
	 * @return Available balance to use
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance Balance to set available
	 * @implNote Balance can be negative!
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return Get date account was created
	 */
	public Date getOpenDate() {
		return openDate;
	}

	/**
	 * @param openDate set when the account was opened
	 * @apiNote Private because this should not be changed outside
	 */
	private void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	/**
	 * @return Retrieve all transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions Set the transactions retrieved from database
	 * @apiNote Private because this should not be changed outside
	 */
	private void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	/**
	 * Add a transaction to the account and SQL Database
	 * @apiNote Do not use this to insert data retrieved from database
	 * @param transaction Transaction object
	 */
	public void insertTransaction(Transaction trans) {
		try {
			PreparedStatement stat = DatabaseManager.getConnection().prepareStatement("INSERT INTO Transactions(date, description, amount) VALUES (?,?,?)", 
					Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, new java.sql.Date(trans.getDate().getTime()));
			stat.setString(2, trans.getDescription());
			stat.setDouble(3, trans.getAmount());
			stat.execute();
			
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
			    long id = rs.getLong(1);
			    trans.setTransactionID(id);
			}
			
			stat.close();
			
			stat = DatabaseManager.getConnection().prepareStatement("INSERT INTO AccountTransactions(transactionID, accountNumber) VALUES (?,?)");
			stat.setLong(1, trans.getTransactionID());
			stat.setLong(2, getAccountID());
			stat.execute();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getTransactions().add(trans);
	}
	
	
	/**
	 * Updates the balance in the SQL Database
	 * @param amount Amount to set
	 * @apiNote Does not update the current instance attributes!
	 */
	public void updateBalance(double amount) {
		try {
			PreparedStatement stat = DatabaseManager.getConnection().prepareCall("UPDATE Accounts SET balance = ? WHERE accountNumber = ?");
			stat.setDouble(1, amount);
			stat.setLong(2, getAccountID());
			stat.execute();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public abstract boolean deposit(double amount) throws Exception;
	public abstract boolean withdraw(double amount) throws Exception;
	public abstract boolean transfer(Account acc, double amount) throws Exception;


}
