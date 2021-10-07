package bank.cards;

import java.util.Date;

public abstract class Card {
	
	private String cardNumber;
	private int csv;
	private Date expireDate;
	private String name;
	
	public Card(String num, int csv, Date expire, String name) {
		setCardNumber(num);
		setCsv(csv);
		setExpireDate(expire);
		setName(name);
	}
	
	public Card(String name) {
		setName(name);
		//TODO: Generate card
	}
	
	
	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * @param cardNumber the cardNumber to set
	 */
	private void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber; // TODO: Generate valid card number using algorithm
	}
	
	/**
	 * @return the csv
	 */
	public int getCsv() {
		return csv;
	}
	/**
	 * @param csv the csv to set
	 */
	public void setCsv(int csv) {
		this.csv = csv;
	}

	/**
	 * @return the expireDate
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	

}
