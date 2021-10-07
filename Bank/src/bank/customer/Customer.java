package bank.customer;

import java.util.ArrayList;

import bank.accounts.Account;
import bank.cards.Card;
import bank.loans.Loan;

public class Customer {
	
	private long ID;
	private String fullName;
	private String fullAddress;
	private String phoneNumber;
	private ArrayList<Account> accounts;
	private ArrayList<Card> cards;
	private ArrayList<Loan> loans;
	
	public Customer(long ID, String name, String address, String number, ArrayList<Account> accs, ArrayList<Card> cards, ArrayList<Loan> loans) {
		setID(ID);
		setFullName(name);
		setFullAddress(address);
		setPhoneNumber(number);
		setAccounts(accs);
		setCards(cards);
		setLoans(loans);
	}
	
	public Customer(long ID) {
		setID(ID);
		setAccounts(new ArrayList<Account>());
		setCards(new ArrayList<Card>());
		setLoans(new ArrayList<Loan>());
	}

	// Retrieve bronco id 
	public long getID() {
		return ID;
	}

	// Outside classes should not tamper with bronco ID
	private void setID(long iD) {
		ID = iD; 
	}

	// Get customers full name
	public String getFullName() {
		return fullName;
	}

	/**
	 * @author Arnold Lev
	 * @param fullName {string}
	 * @return True if length is > 1 otherwise False
	 */
	public boolean setFullName(String fullName) {
		if (fullName.length() < 1) {
			return false;
		}
		this.fullName = fullName;
		return true;
	}
	
	// Get customers full address
	public String getFullAddress() {
		return fullAddress;
	}
	
	/**
	 * @author Arnold Lev
	 * @param add {String}
	 * @return True if valid address otherwise False
	 */
	public boolean setFullAddress(String add) {
		this.fullAddress = add;
		return true; //TODO: Validate address
	}

	// Get customers phone number
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @author Arnold Lev
	 * @param phoneNUmber {String}
	 * @return True if valid phone number otherwise False
	 */
	public boolean setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return true; //TODO: Validate phone number
	}

	/**
	 * @return the accounts
	 */
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}

	/**
	 * @param cards the cards to set
	 */
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	/**
	 * @return the loans
	 */
	public ArrayList<Loan> getLoans() {
		return loans;
	}

	/**
	 * @param loans the loans to set
	 */
	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}
	
	
	

}
