package bank.accounts;

import java.util.ArrayList;
import java.util.Date;

import bank.customer.Customer;

public abstract class Account {
	
	private int accountID;
	private Customer accountHolder;
	private double balance;
	private Date openDate;
	private ArrayList<Statement> statements;
	
	public Account(long ID, Customer customer, double bal, Date open, ArrayList<Statement> stats) {
		setAccountID(accountID);
		setAccountHolder(customer);
		setBalance(bal);
		setOpenDate(open);
		setStatements(stats);
	}
	
	public Account(Customer customer) {
		setAccountHolder(customer);
		setOpenDate(new Date());
		setStatements(new ArrayList<Statement>());
	}

	public int getAccountID() {
		return accountID;
	}

	private void setAccountID(int accountID) {
		this.accountID = accountID; //TODO: Generate account ID
	}
	
	
	/**
	 * @return the accountHolder
	 */
	public Customer getAccountHolder() {
		return accountHolder;
	}

	/**
	 * @param accountHolder the accountHolder to set
	 */
	public void setAccountHolder(Customer accountHolder) {
		this.accountHolder = accountHolder;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the openDate
	 */
	public Date getOpenDate() {
		return openDate;
	}

	/**
	 * @param openDate the openDate to set
	 */
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	/**
	 * @return the statements
	 */
	public ArrayList<Statement> getStatements() {
		return statements;
	}

	/**
	 * @param statements the statements to set
	 */
	public void setStatements(ArrayList<Statement> statements) {
		this.statements = statements;
	}
	
	

	public abstract boolean deposit(double amount);
	public abstract boolean withdraw(double amount);
	public abstract boolean transfer(Account acc, double amount);


}
