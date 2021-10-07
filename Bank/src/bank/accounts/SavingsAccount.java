package bank.accounts;

import java.util.ArrayList;
import java.util.Date;

import bank.customer.Customer;

public class SavingsAccount extends Account {
	
	private double interest;

	public SavingsAccount(Customer customer) {
		super(customer);
	}
	
	public SavingsAccount(long ID, Customer customer, double bal, Date open, ArrayList<Statement> stats, double interest) {
		super(ID, customer, bal, open, stats);
		setInterest(interest);
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
