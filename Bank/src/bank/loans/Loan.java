package bank.loans;

import java.util.Date;

public abstract class Loan {
	
	private int loanID;
	private double loanedAmount;
	private double amountLeft;
	private double interest;
	private Date openDate;
	private int termInYears;
	
	public Loan(int ID, double amount, double left, double interest, Date open, int term) {
		setLoanID(ID);
		setLoanedAmount(amount);
		setAmountLeft(left);
		setInterest(interest);
		setOpenDate(open);
		setTermInYears(term);
	}
	
	public Loan(double amount) {
		setLoanedAmount(amount);
		setOpenDate(new Date());
	}
	

	public int getLoanID() {
		return loanID;
	}

	private void setLoanID(int loanID) {
		this.loanID = loanID;
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
	
	public abstract boolean makePayment(double amount);

}
