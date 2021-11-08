package bank.accounts;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import bank.main.DatabaseManager;

public class CDAccount extends Account {
	
	private int termInYears;
	private double interest;
	private double minDeposit;

	public CDAccount() {
		super();
	}
	
	public CDAccount(long ID, double bal, Date open, int term, double interest, double deposit) {
		super(ID, bal, open);
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
		if (amount < minDeposit) {
			return false;
		}
		setBalance(getBalance() + amount);
		Transaction t = new Transaction("CD Deposit", amount);
		insertTransaction(t);
		updateBalance(getBalance());
		return true;
	}

	@Override
	public boolean withdraw(double amount) {
		if (amount < 0) {
			return false;
		}
		if (!canWithdraw()) {
			return false;
		}
		if (getBalance() - amount < 0) {
			return false;
		}
		
		setBalance(getBalance() - amount);
		Transaction t = new Transaction("CD Withdrawal", amount);
		insertTransaction(t);
		updateBalance(getBalance());
		return true;
	}

	@Override
	public boolean transfer(Account acc, double amount) {
		if (amount < 0) {
			return false;
		}
		if (!canWithdraw()) {
			return false;
		}
		if (getBalance() - amount < 0) {
			return false;
		}
		
		setBalance(getBalance() - amount);
		acc.setBalance(acc.getBalance() + amount);
		Transaction t = new Transaction("CD Transfer -> Account #" + acc.getAccountID(), amount);
		insertTransaction(t);
		updateBalance(getBalance());
		acc.updateBalance(acc.getBalance());
		return true;
	}
	
	private boolean canWithdraw() {
		Calendar mature = Calendar.getInstance(TimeZone.getDefault());
		mature.setTime(getOpenDate());
		mature.add(Calendar.YEAR, termInYears);
		
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		
		if (today.compareTo(mature) <= 0) {
			return false;
		}
		return true;
	}

}
