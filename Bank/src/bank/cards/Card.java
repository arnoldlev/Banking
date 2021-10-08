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

	/**
	 * @return Name on card
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Name on card
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	

}
