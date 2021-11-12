package bank.cards;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
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

		//Generates Valid CC or Debit Card Number
		//Uses Luhn's Algorithm to check for validity
		//Restarts the process if number is invalid
		Random rand = new Random();
		final int numOfDigits = 16;
		String cNumber = "";
		boolean isValid = false;
		int nSum = 0;
		
		while(!isValid)
		{
			for(int i = 0; i < numOfDigits; i++)
			{
				int digit = rand.nextInt(9) + 1;
				cNumber = cNumber + (digit);
				
				if(i % 2 == 0) {
					digit *= 2;
				}
				
				nSum += digit / 10;
				nSum += digit % 10;
				
			}
			
			if (nSum % 10 == 0)
				isValid = true;
			else {
				nSum = 0;
				cNumber = "";
			}
		}
		
		cardNumber = cNumber;
		
		//Generating the CSV number
		int csvNum = 0;
		for(int i = 0; i < 3; i++)
		{
			csvNum *= 10;
			csvNum += rand.nextInt(9) + 1;
		}
		
		csv = csvNum;
		
		//Generating exp. date
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        Date date = new Date(); //Current Date
        localCalendar.setTime(date); //Set localCalendar to current date
        localCalendar.add(Calendar.YEAR, 5); //Add five years for expiration
        expireDate = localCalendar.getTime(); //Add the new time to expire date

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
	 * Adds a Transaction to the Card
	 * @implNote If card is a CreditCard, it will update the balance. If card is a DebitCard, it will update the balance of the associated Checking Account
	 * @param transaction Transaction object
	 * @return 0 if successful, 1 if invalid funds, or 2 for SQLException
	 */
	public abstract int insertTransaction(Transaction transaction);
	
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
