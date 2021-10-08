package bank.accounts;

import java.util.Date;

public class Statement {
	
	private int statementID;
	private int accountID;
	private Date date;
	private String description;
	
	private Statement(int accID, Date date, String desc) {
		setAccountID(accID);
		setDate(date);
		setDescription(desc);
	}
	
	private Statement(int statID, int accID, Date date, String desc) {
		setStatementID(statID);
		setAccountID(accID);
		setDate(date);
		setDescription(desc);
	}


	public int getStatementID() {
		return statementID;
	}

	private void setStatementID(int statementID) {
		this.statementID = statementID; // TODO: Set statement ID after data entered into database
	}

	public int getAccountID() {
		return accountID;
	}

	/**
	 * @apiNote Private because outside classes should not change this
	 */
	private void setAccountID(int accountID) {
		this.accountID = accountID;
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

}
