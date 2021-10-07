package bank.accounts;

import java.util.Date;

public class Statement {
	
	private int statementID;
	private int accountID;
	private Date date;
	private String description;
	
	private Statement(int statID, int accID, Date date, String desc) {
		setStatementID(statID);
		setAccountID(accID);
		setDate(date);
		setDescription(desc);
	}
	
	/**
	 * @return the statementID
	 */
	public int getStatementID() {
		return statementID;
	}
	
	/**
	 * @param statementID the statementID to set
	 */
	public void setStatementID(int statementID) {
		this.statementID = statementID;
	}

	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
