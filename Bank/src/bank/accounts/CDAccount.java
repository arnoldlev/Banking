package bank.accounts;

import java.util.ArrayList;
import java.util.Date;

public class CDAccount extends Account {
	
	private int termInYears;
	private double interest;
	private double minDeposit;

	public CDAccount() {
		super();
	}
	
	public CDAccount(long ID, double bal, Date open, ArrayList<Statement> stats, int term, double interest, double deposit) {
		super(ID, bal, open, stats);
		setTermInYears(term);
		setInterest(interest);
		setMinDeposit(deposit);
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
	 * @return The minimum amount required to deposit
	 */
	public double getMinDeposit() {
		return minDeposit;
	}

	
	/**
	 * @param minDeposit The minimum amount required to deposit
	 * @return True if parameter is greater than 0
	 */
	public boolean setMinDeposit(double minDeposit) {
		if (minDeposit <= 0) {
			return false;
		}
		this.minDeposit = minDeposit;
		return true;
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
