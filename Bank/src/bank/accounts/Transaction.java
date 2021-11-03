package bank.accounts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	
	private long transactionID;
	private Date date;
	private String description;
	private double amount;
	
	public Transaction(String desc, double amount) {
		setDate(new Date());
		setDescription(desc);
		setAmount(amount);
	}
	
	public Transaction(long statID, Date date, String desc, double amount) {
		setTransactionID(statID);
		setDate(date);
		setDescription(desc);
		setAmount(amount);
	}


	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long statementID) {
		this.transactionID = statementID; 
	}

	/**
	 * @return Get the date transaction was made
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date Set the date transaction made
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return The description of the statement
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The description of the statement
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String[] toData() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
		String[] data = { String.valueOf(getTransactionID()), format.format(getDate()), getDescription(), String.valueOf(getAmount())  };
		return data;
	}

}
