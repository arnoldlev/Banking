
package bank.accounts;

import java.util.ArrayList;
import java.util.Date;

import bank.cards.DebitCard;
import bank.customer.Customer;

public class CheckingAccount extends Account {
	
	private double monthlyCharge;
	private DebitCard card;
	

	public CheckingAccount(Customer customer) {
		super(customer);
		//TODO: generate debit card
	}
	
	public CheckingAccount(long ID, Customer customer, double bal, Date open, ArrayList<Statement> stats, double charge, DebitCard card) {
		super(ID, customer, bal, open, stats);
		setMonthlyCharge(charge);
		setCard(card);
	}

	/**
	 * @return the monthlyCharge
	 */
	public double getMonthlyCharge() {
		return monthlyCharge;
	}

	/**
	 * @param monthlyCharge the monthlyCharge to set
	 */
	
	public void setMonthlyCharge(double monthlyCharge) {
		this.monthlyCharge = monthlyCharge;
	}

	/**
	 * @return the card
	 */
	public DebitCard getCard() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(DebitCard card) {
		this.card = card;
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
