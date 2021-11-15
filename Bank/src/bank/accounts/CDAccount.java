package bank.accounts;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

	public int getTermInYears() {
		return termInYears;
	}

	public boolean setTermInYears(int termInYears) {
		if (termInYears <= 0) {
			return false;
		}
		this.termInYears = termInYears;
		return true;
	}

	public double getInterest() {
		return interest;
	}


	public boolean setInterest(double interest) {
		if (interest <= 0) {
			return false;
		}
		this.interest = interest;
		return true;
	}


	public double getMinDeposit() {
		return minDeposit;
	}

	
	public boolean setMinDeposit(double minDeposit) {
		if (minDeposit <= 0) {
			return false;
		}
		this.minDeposit = minDeposit;
		return true;
	}
	
	
	@Override
	public boolean deposit(double amount, String desc) throws Exception {
		if (amount < minDeposit) {
			throw new Exception("Error: Your deposit must be at least $" + minDeposit);
		}
		setBalance(getBalance() + amount);
		Transaction t = new Transaction(desc, amount);
		insertTransaction(t);
		updateBalance(getBalance());
		return true;
	}
	

	@Override
	public boolean deposit(double amount) throws Exception {
		if (amount < minDeposit) {
			throw new Exception("Error: Your deposit must be at least $" + minDeposit);
		}
		setBalance(getBalance() + amount);
		Transaction t = new Transaction("CD Deposit", amount);
		insertTransaction(t);
		updateBalance(getBalance());
		return true;
	}
	
	@Override
	public boolean withdraw(double amount, String desc) throws Exception {
		if (amount < 0) {
			return false;
		}
		if (!canWithdraw()) {
			throw new Exception("Error: Account has not reached maturity date!");
		}
		if (getBalance() - amount < 0) {
			return false;
		}
		
		setBalance(getBalance() - amount);
		Transaction t = new Transaction(desc, amount);
		insertTransaction(t);
		updateBalance(getBalance());
		return true;
	}

	@Override
	public boolean withdraw(double amount) throws Exception {
		if (amount < 0) {
			return false;
		}
		if (!canWithdraw()) {
			throw new Exception("Error: Account has not reached maturity date!");
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
	public boolean transfer(Account acc, double amount) throws Exception {
		if (amount < 0) {
			return false;
		}
		if (!canWithdraw()) {
			throw new Exception("Error: Account has not reached maturity date!");
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
