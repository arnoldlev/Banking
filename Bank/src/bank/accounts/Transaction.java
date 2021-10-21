package bank.accounts;

import java.util.Date;

public class Transaction {
	
	private int transactionID;
	private Date date;
	private String description;
	private double amount;
	
	public Transaction(Date date, String desc) {
		setDate(date);
		setDescription(desc);
	}
	
	public Transaction(int statID, Date date, String desc) {
		setTransactionID(statID);
		setDate(date);
		setDescription(desc);
	}


	public int getTransactionID() {
		return transactionID;
	}

	private void setTransactionID(int statementID) {
		this.transactionID = statementID; // TODO: Set statement ID after data entered into database
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

}
