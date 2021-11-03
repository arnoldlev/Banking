package bank.loans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import bank.accounts.Transaction;
import bank.main.DatabaseManager;

public class Loan {
	
	private long loanID;
	private double loanedAmount;
	private double amountLeft;
	private double interest;
	private Date openDate;
	private int termInYears;
	private ArrayList<Transaction> transactions;
	
	public Loan(long ID, double amount, double left, double interest, Date open, int term) {
		setLoanID(ID);
		setLoanedAmount(amount);
		setAmountLeft(left);
		setInterest(interest);
		setOpenDate(open);
		setTermInYears(term);
		setTransactions(new ArrayList<Transaction>());
	}
	
	public Loan(double amount, double interest, int term) {
		setLoanedAmount(amount);
		setAmountLeft(amount);
		setInterest(interest);
		setTermInYears(term);
		setOpenDate(new Date());
		setTransactions(new ArrayList<Transaction>());
	}
	

	public long getLoanID() {
		return loanID;
	}

	public void setLoanID(long loanID) {
		this.loanID = loanID;
	}
	
	public boolean makePayment(double payment) {
		if (payment > loanedAmount || payment > amountLeft) {
			return false;
		}
		
		amountLeft -= payment;

		Transaction trans = new Transaction("Loan Payment", payment);
		try {
			PreparedStatement stat = DatabaseManager.getConnection().prepareStatement("UPDATE Loans SET remainingBal = ? WHERE loanID = ?");
			stat.setDouble(1, amountLeft);
			stat.setLong(2, loanID);
			stat.execute();
			stat.close();
			
			stat = DatabaseManager.getConnection().prepareStatement("INSERT INTO Transactions(date, description, amount) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, new java.sql.Date(trans.getDate().getTime()));
			stat.setString(2, trans.getDescription());
			stat.setDouble(3, trans.getAmount());
			stat.execute();
			
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
			    long id = rs.getLong(1);
			    trans.setTransactionID(id);
			    transactions.add(trans);
			}
			stat.close();
			
			stat = DatabaseManager.getConnection().prepareStatement("INSERT INTO LoanTransactions(transactionID, loanID) VALUES (?,?)");
			stat.setLong(1, trans.getTransactionID());
			stat.setLong(2, getLoanID());
			stat.execute();
			stat.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param transactions Set the transactions retrieved from database
	 * @apiNote Private because this should not be changed outside
	 */
	private void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * @return Retrieve all transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	/**
	 * Add a transaction to the account
	 * @param transaction Transaction object
	 */
	public void addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
	}
	
	/**
	 * @return The amount loaned
	 */
	public double getLoanedAmount() {
		return loanedAmount;
	}

	/**
	 * @param loanedAmount The amount loaned
	 * @return True if parameter is greater than 0
	 */
	public boolean setLoanedAmount(double loanedAmount) { 
		if (loanedAmount <= 0) {
			return false;
		}
		this.loanedAmount = loanedAmount;
		return true;
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
	 * @return The amount left to be paid
	 */
	public double getAmountLeft() {
		return amountLeft;
	}

	/**
	 * @param amountLeft The amount left to be paid
	 * @return True if parameter is a positive number, including 0
	 */
	public boolean setAmountLeft(double amountLeft) {
		if (amountLeft < 0) {
			return false;
		}
		this.amountLeft = amountLeft;
		return true;
	}

	/**
	 * @return Date when loan was issued
	 */
	public Date getOpenDate() {
		return openDate;
	}

	/**
	 * @param openDate Date when loan was issued
	 */
	private void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	/**
	 * @return The term in years
	 */
	public int getTermInYears() {
		return termInYears;
	}


	/**
	 * @param termInYears Set the term in years
	 * @return True if parameter is greater than 0
	 */
	public boolean setTermInYears(int termInYears) {
		if (termInYears <= 0) {
			return false;
		}
		this.termInYears = termInYears;
		return true;
	}


}
