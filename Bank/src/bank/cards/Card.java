package bank.cards;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import bank.accounts.Transaction;
import bank.main.DatabaseManager;

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
		Random rand = new Random();
		final int numOfDigits = 16;
		String cNumber = "";
		boolean isValid = false;
		int nSum = 0;
		
		while (!isValid) {
			for(int i = 0; i < numOfDigits; i++) {
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
		for(int i = 0; i < 3; i++) {
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
	 * Adds a Transaction to the Card Database and current instance
	 * @param trans Transaction object
	 * @apiNote Do not use this to insert data retrieved from database
	 * @return  true if successful, else false
	 */
	public void insertTransaction(Transaction trans) {
		try {
			PreparedStatement stat = DatabaseManager.getConnection().prepareStatement("INSERT INTO Transactions(date, description, amount) VALUES (?,?,?)", 
					Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, new java.sql.Date(trans.getDate().getTime()));
			stat.setString(2, trans.getDescription());
			stat.setDouble(3, trans.getAmount());
			stat.execute();
			
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
			    long id = rs.getLong(1);
			    trans.setTransactionID(id);
			}
			
			stat.close();
			
			stat = DatabaseManager.getConnection().prepareStatement("INSERT INTO CardTransactions(transactionID, cardNumber) VALUES (?,?)");
			stat.setLong(1, trans.getTransactionID());
			stat.setString(2, getCardNumber());
			stat.execute();
			stat.close();
			
			getTransactions().add(trans);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	private void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber; 
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	private void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public int getCsv() {
		return csv;
	}

	public void setCsv(int csv) {
		this.csv = csv;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}


}
