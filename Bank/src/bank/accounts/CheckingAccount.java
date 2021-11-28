
package bank.accounts;

import java.util.Date;

import bank.cards.DebitCard;

public class CheckingAccount extends Account {
	
	private double monthlyCharge;
	private DebitCard card;
	

	public CheckingAccount() {
		super();
	}
	
	public CheckingAccount(long ID, double bal, Date open,  double charge, DebitCard card) {
		super(ID, bal, open);
		setMonthlyCharge(charge);
		setCard(card);
	}

	public double getMonthlyCharge() {
		return monthlyCharge;
	}
	
	public boolean setMonthlyCharge(double monthlyCharge) {
		if (monthlyCharge <= 0) {
			return false;
		}
		this.monthlyCharge = monthlyCharge;
		return true;
	}

	public DebitCard getCard() {
		return card;
	}

	public void setCard(DebitCard card) {
		this.card = card;
	}
	
	
	@Override
	public boolean deposit(double amount, String desc) {
		if (amount <= 0) {
			return false;
		}
		setBalance(getBalance() + amount);
		Transaction t = new Transaction(desc, amount);
		insertTransaction(t);
		updateBalance(getBalance());
		return true;
	}

	@Override
	public boolean deposit(double amount) {
		if (amount <= 0) {
			return false;
		}
		setBalance(getBalance() + amount);
		Transaction t = new Transaction("Checking Deposit", amount);
		insertTransaction(t);
		updateBalance(getBalance());
		return true;
	}
	
	@Override
	public boolean withdraw(double amount, String desc) {
		if (amount <= 0 || amount > card.getAtmLimit()) {
			return false;
		}
		if (getBalance() - amount < 0) {
			return false;
		}
		
		setBalance(getBalance() - amount);
		Transaction t = new Transaction("DebitCard - " + desc, amount);
		insertTransaction(t);
		updateBalance(getBalance());
		return true;
	}

	@Override
	public boolean withdraw(double amount) {
		if (amount <= 0 || amount > card.getAtmLimit()) {
			return false;
		}
		if (getBalance() - amount < 0) {
			return false;
		}
		
		setBalance(getBalance() - amount);
		Transaction t = new Transaction("Checking Withdrawal", amount);
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
		Transaction t = new Transaction("Checking Transfer -> Account #" + acc.getAccountID(), amount);
		insertTransaction(t);
		updateBalance(getBalance());
		acc.updateBalance(acc.getBalance()); 
		return true;
	}

}
