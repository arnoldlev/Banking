package bank.accounts;

import java.util.ArrayList;
import java.util.Date;

public abstract class Account {
	
	private int accountID;
	private double balance;
	private Date openDate;
	private ArrayList<Statement> statements;
	
	public Account(long ID, double bal, Date open, ArrayList<Statement> stats) {
		setAccountID(accountID);
		setBalance(bal);
		setOpenDate(open);
		setStatements(stats);
	}
	
	public Account() {
		setOpenDate(new Date());
		setStatements(new ArrayList<Statement>());
	}

	public int getAccountID() {
		return accountID;
	}

	private void setAccountID(int accountID) {
		this.accountID = accountID; //TODO: Set this after inserting data into database tables
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
	 * @return Retrieve all statements
	 */
	public ArrayList<Statement> getStatements() {
		return statements;
	}

	/**
	 * @param statements Set the statements retrieved from database
	 * @apiNote Private because this should not be changed outside
	 */
	private void setStatements(ArrayList<Statement> statements) {
		this.statements = statements;
	}
	
	/**
	 * Add a statement to the account
	 * @param statement Statement object
	 */
	public void addStatement(Statement statement) {
		// TODO: Develop creating a statement
	}
	
	

	public abstract boolean deposit(double amount);
	public abstract boolean withdraw(double amount);
	public abstract boolean transfer(Account acc, double amount);


}
