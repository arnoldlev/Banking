
package bank.accounts;

import java.util.ArrayList;
import java.util.Date;

import bank.cards.DebitCard;

public class CheckingAccount extends Account {
	
	private double monthlyCharge;
	private DebitCard card;
	

	public CheckingAccount() {
		super();
		//TODO: generate debit card
	}
	
	public CheckingAccount(long ID, double bal, Date open, ArrayList<Statement> stats, double charge, DebitCard card) {
		super(ID, bal, open, stats);
		setMonthlyCharge(charge);
		setCard(card);
	}

	/**
	 * @return The monthly charge for having an open account
	 */
	public double getMonthlyCharge() {
		return monthlyCharge;
	}

	/**
	 * @param monthlyCharge Set the monthly charge
	 * @return True if parameter is greater than 0
	 */
	
	public boolean setMonthlyCharge(double monthlyCharge) {
		if (monthlyCharge <= 0) {
			return false;
		}
		this.monthlyCharge = monthlyCharge;
		return true;
	}

	/**
	 * @return Reference to the associated DebitCard
	 */
	public DebitCard getCard() {
		return card;
	}

	/**
	 * @param card The debitcard attached
	 * @apiNote Private because outside should not change this
	 */
	private void setCard(DebitCard card) {
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
