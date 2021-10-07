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
	
	
	/**
	 * @return the loanID
	 */
	public int getLoanID() {
		return loanID;
	}
	
	/**
	 * @param loanID the loanID to set
	 */
	private void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	/**
	 * @return the loanedAmount
	 */
	public double getLoanedAmount() {
		return loanedAmount;
	}

	/**
	 * @param loanedAmount the loanedAmount to set
	 */
	public void setLoanedAmount(double loanedAmount) {
		this.loanedAmount = loanedAmount;
	}

	/**
	 * @return the interest
	 */
	public double getInterest() {
		return interest;
	}

	/**
	 * @param interest the interest to set
	 */
	public void setInterest(double interest) {
		this.interest = interest;
	}

	/**
	 * @return the amountLeft
	 */
	public double getAmountLeft() {
		return amountLeft;
	}

	/**
	 * @param amountLeft the amountLeft to set
	 */
	public void setAmountLeft(double amountLeft) {
		this.amountLeft = amountLeft;
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
	 * @return the termInYears
	 */
	public int getTermInYears() {
		return termInYears;
	}

	/**
	 * @param termInYears the termInYears to set
	 */
	public void setTermInYears(int termInYears) {
		this.termInYears = termInYears;
	}
	
	public abstract boolean makePayment(double amount);

}
