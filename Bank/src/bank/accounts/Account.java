package bank.accounts;

import java.util.ArrayList;
import java.util.Date;

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
		setOpenDate(new Date());
		setTransactions(new ArrayList<Transaction>());
	}

	public long getAccountID() {
		return accountID;
	}

	public void setAccountID(long accountID) {
		this.accountID = accountID; // Set this after inserting data into database tables
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
	 * Add a transaction to the account
	 * @param transaction Transaction object
	 */
	public void addTransaction(Transaction transaction) {
		//TODO:
		getTransactions().add(transaction);
	}

	public abstract boolean deposit(double amount);
	public abstract boolean withdraw(double amount);
	public abstract boolean transfer(Account acc, double amount);


}
