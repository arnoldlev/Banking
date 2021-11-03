package bank.cards;

import java.util.ArrayList;
import java.util.Date;

import bank.accounts.Transaction;

public abstract class Card {
	
	private String cardNumber;
	private int csv;
	private Date expireDate;
	private ArrayList<Transaction> transactions;
	
	public Card(String num, int csv, Date expire) {
		setCardNumber(num);
		setCsv(csv);
		setExpireDate(expire);
		setTransactions(new ArrayList<Transaction>());
	}
	
	public Card() {
		//TODO: Generate card
	}
	
	/**
	 * @return The 16 digit credit card number
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * @param cardNumber The 16 digit creditcard number
	 */
	private void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber; 
	}
	
	/**
	 * Add a transaction to the account
	 * @param transaction Transaction object
	 * @return 0 if successful, 1 if invalid funds, 2 for SQLException
	 */
	public int addTransaction(Transaction transaction) {
		//TODO:
		getTransactions().add(transaction);
		return 0;
	}
	
	/**
	 * @return Retrieve all transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions Set the transactions retrieved from database
	 * @apiNote Private because this should not be changed outside
	 */
	private void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	/**
	 * @return The 3 digit security code
	 */
	public int getCsv() {
		return csv;
	}
	
	/**
	 * @param csv The 3 digit security code
	 */
	public void setCsv(int csv) {
		this.csv = csv;
	}

	/**
	 * @return The date when the card is expired
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * @param expireDate The date when the card is expired
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	

}
