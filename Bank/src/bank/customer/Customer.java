package bank.customer;

import java.util.ArrayList;

import bank.accounts.Account;
import bank.cards.Card;
import bank.cards.CreditCard;
import bank.loans.Loan;

public class Customer {
	
	private long broncoID;
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
	
	public Customer(long ID, String password) throws Exception {
		//do stuff
		throw new Exception();
	}
	
	public Customer(long ID) {
		setID(ID);
		setAccounts(new ArrayList<Account>());
		setCards(new ArrayList<Card>());
		setLoans(new ArrayList<Loan>());
	}

	
	public long getBroncoID() {
		return broncoID;
	}

	private void setID(long id) {
		this.broncoID = id; 
	}

	public String getFullName() {
		return fullName;
	}


	/**
	 * @param fullName First and Last name of student
	 * @return True if parameter has length greater than 1
	 */
	public boolean setFullName(String fullName) {
		if (fullName.length() < 1) {
			return false;
		}
		this.fullName = fullName;
		return true;
	}
	
	
	public String getFullAddress() {
		return fullAddress;
	}
	
	/**
	 * @param address Full Address of student, including zipcode
	 * @return True if address is valid
	 */
	public boolean setFullAddress(String add) {
		this.fullAddress = add;
		return true; //TODO: Validate address
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber Phone number in ###-###-#### format
	 * @return True if phone number is valid
	 */
	public boolean setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return true; //TODO: Validate phone number
	}

	/**
	 * @return Get all accounts
	 */
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts All accounts retrieved from database
	 */
	private void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return Get all debit and credit cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}

	/**
	 * @param cards All cards retrieved from database
	 */
	private void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	/**
	 * @return Get all loans
	 */
	public ArrayList<Loan> getLoans() {
		return loans;
	}

	/**
	 * @param loans All loans retrieved from database
	 */
	private void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}
	
	/**
	 * Open an account for a student
	 * @param acc Account thats to be added
	 * @return True if creation went successful
	 */
	public boolean addAccount(Account acc) {
		//TODO: Creation of account
		return false;
	}
	
	/**
	 * Open a credit card
	 * @param card Card thats to be added
	 * @return True if creation went successful
	 */
	public boolean addCreditCard(CreditCard card) {
		//TODO: Creation of credit card
		return false;
	}
	
	/**
	 * Take out a student or personal loan
	 * @param loan Loan thats to be added
	 * @return True if creation went successful
	 */
	public boolean addLoan(Loan loan) {
		//TODO: Creation of loan
		return false;
	}
	
	
	

}
