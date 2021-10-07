package bank.accounts;

import java.util.ArrayList;
import java.util.Date;

import bank.customer.Customer;

public class CDAccount extends Account {
	
	private int termInYears;
	private double interest;
	private double minDeposit;

	public CDAccount(Customer customer) {
		super(customer);
	}
	
	public CDAccount(long ID, Customer customer, double bal, Date open, ArrayList<Statement> stats, int term, double interest, double deposit) {
		super(ID, customer, bal, open, stats);
		setTermInYears(term);
		setInterest(interest);
		setMinDeposit(deposit);
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
	 * @return the minDeposit
	 */
	public double getMinDeposit() {
		return minDeposit;
	}



	/**
	 * @param minDeposit the minDeposit to set
	 */
	public void setMinDeposit(double minDeposit) {
		this.minDeposit = minDeposit;
	}



	@Override
	public boolean deposit(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdraw(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean transfer(Account acc, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
