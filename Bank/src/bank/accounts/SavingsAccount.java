package bank.accounts;

import java.util.ArrayList;
import java.util.Date;

public class SavingsAccount extends Account {
	
	private double interest;

	public SavingsAccount() {
		super();
	}
	
	public SavingsAccount(long ID,  double bal, Date open, ArrayList<Statement> stats, double interest) {
		super(ID, bal, open, stats);
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
