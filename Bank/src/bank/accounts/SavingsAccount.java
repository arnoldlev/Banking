package bank.accounts;

import java.util.Date;

public class SavingsAccount extends Account {
	
	private double interest;

	public SavingsAccount() {
		super();
	}
	
	public SavingsAccount(long ID, double bal, Date open, double interest) {
		super(ID, bal, open);
		setInterest(interest);
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
	

	@Override
	public boolean deposit(double amount) {
		if (amount <= 0) {
			return false;
		}
		setBalance(getBalance() + amount);
		Transaction t = new Transaction("Savings Deposit", amount);
		insertTransaction(t);
		updateBalance(getBalance());
		return true;
	}

	@Override
	public boolean withdraw(double amount) {
		if (amount <= 0) {
			return false;
		}
		if (getBalance() - amount < 0) {
			return false;
		}
		
		setBalance(getBalance() - amount);
		Transaction t = new Transaction("Savings Withdrawal", amount);
		insertTransaction(t);
		updateBalance(getBalance());
		return true;
	}

	@Override
	public boolean transfer(Account acc, double amount) {
		if (amount <= 0) {
			return false;
		}
		if (getBalance() - amount < 0) {
			return false;
		}
		setBalance(getBalance() - amount);
		acc.setBalance(acc.getBalance() + amount);
		Transaction t = new Transaction("Savings Transfer -> Account #" + acc.getAccountID(), amount);
		insertTransaction(t);
		updateBalance(getBalance());
		acc.updateBalance(acc.getBalance());
		return true;
	}

}
