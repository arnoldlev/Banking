package bank.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import bank.accounts.Account;
import bank.accounts.CDAccount;
import bank.accounts.CheckingAccount;
import bank.accounts.SavingsAccount;
import bank.accounts.Transaction;
import bank.cards.Card;
import bank.cards.CreditCard;
import bank.cards.DebitCard;
import bank.loans.Loan;
import bank.main.DatabaseManager;

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
    	try {
    		Connection con = DatabaseManager.getConnection();
    		PreparedStatement stat = con.prepareStatement("SELECT * FROM Customers WHERE broncoID = ? AND password = ?");
    		stat.setLong(1, ID);
    		stat.setString(2, password);
    		ResultSet rs = stat.executeQuery();
    		if (rs.next()) {
    			setID(ID);
    			setFullName(rs.getString("name"));
    			setFullAddress(rs.getString("address"));
    			setPhoneNumber(rs.getString("phoneNumber"));
    		} else {
    			throw new Exception();
    		}
    		
    		stat.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
		setAccounts(new ArrayList<Account>());
		setCards(new ArrayList<Card>());
		setLoans(new ArrayList<Loan>());
		
		loadCards();
    	loadAccounts();
    	loadLoans();
    	
    	accounts.forEach(e -> loadTransactions(e));
    	cards.forEach(e -> loadTransactions(e));
    	loans.forEach(e -> loadTransactions(e));
	}
	
	private void loadTransactions(Account acc) {
		try {
    		Connection con = DatabaseManager.getConnection();
    		PreparedStatement stat = con.prepareStatement("SELECT * FROM AccountTransactions JOIN Transactions ON AccountTransactions.transactionID=Transactions.transactionID where accountNumber = ?");
    		stat.setLong(1, acc.getAccountID());
    		ResultSet rs = stat.executeQuery();
    		while (rs.next()) {
    			//int statID, Date date, String desc, double amount
    			Transaction trans = new Transaction(rs.getLong("transactionID"), rs.getDate("date"), rs.getString("description"), rs.getDouble("amount"));
    			acc.addTransaction(trans);
    		}
    		rs.close();
    		stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadTransactions(Card card) {
		if (card instanceof DebitCard) {
			return;
		}
		
		try {
    		Connection con = DatabaseManager.getConnection();
    		PreparedStatement stat = con.prepareStatement("SELECT * FROM CardTransactions JOIN Transactions ON CardTransactions.trasactionID=Transactions.transactionID where cardNumber = ?");
    		stat.setString(1, card.getCardNumber());
    		ResultSet rs = stat.executeQuery();
    		while (rs.next()) {
    			//int statID, Date date, String desc, double amount
    			Transaction trans = new Transaction(rs.getLong("transactionID"), rs.getDate("date"), rs.getString("description"), rs.getDouble("amount"));
    			((CreditCard) card).addTransaction(trans);
    		}
    		rs.close();
    		stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadTransactions(Loan loan) {
		try {
    		Connection con = DatabaseManager.getConnection();
    		PreparedStatement stat = con.prepareStatement("SELECT * FROM LoanTransactions JOIN Transactions ON LoanTransactions.transactionID=Transactions.transactionID where loanID = ?");
    		stat.setLong(1, loan.getLoanID());
    		ResultSet rs = stat.executeQuery();
    		while (rs.next()) {
    			//int statID, Date date, String desc, double amount
    			Transaction trans = new Transaction(rs.getLong("transactionID"), rs.getDate("date"), rs.getString("description"), rs.getDouble("amount"));
    			loan.addTransaction(trans);
    		}
    		rs.close();
    		stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadLoans() {
		try {
    		Connection con = DatabaseManager.getConnection();
    		PreparedStatement stat = con.prepareStatement("SELECT * FROM Loans WHERE broncoID = ?");
    		stat.setLong(1, getBroncoID());
    		ResultSet rs = stat.executeQuery();
    		while (rs.next()) {
    			//int ID, double amount, double left, double interest, Date open, int term)
    			Loan loan = new Loan(rs.getLong("loanID"), rs.getDouble("amount"), rs.getDouble("remainingBal"), rs.getDouble("interest"), rs.getDate("openDate"), rs.getInt("term"));
    			loans.add(loan);
    		}
    		rs.close();
    		stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadCards() {
    	try {
    		Connection con = DatabaseManager.getConnection();
    		PreparedStatement stat = con.prepareStatement("SELECT * FROM DebitCards INNER JOIN Cards ON DebitCards.cardNumber=Cards.cardNumber WHERE broncoID = ?");
    		stat.setLong(1, getBroncoID());
    		ResultSet rs = stat.executeQuery();
    		while (rs.next()) {
    			//String num, int csv, Date expire, double maxTrans, double limit
    			DebitCard debit = new DebitCard(rs.getString("cardNumber"), rs.getInt("csv"), rs.getDate("expireDate"), rs.getDouble("maxTransaction"), rs.getDouble("atmLimit"));
    			cards.add(debit);
    		}
    		rs.close();
    		stat.close();
    	
    		stat = con.prepareStatement("SELECT * FROM CreditCards INNER JOIN Cards ON CreditCards.cardNumber=Cards.cardNumber WHERE broncoID = ?");
    		stat.setLong(1, getBroncoID());
    		rs = stat.executeQuery();
    		while (rs.next()) {
    			//String num, int csv, Date expire, double interest, double max, double bal, ArrayList<Transaction> trans
    			CreditCard credit = new CreditCard(rs.getString("cardNumber"), rs.getInt("csv"), rs.getDate("expireDate"), rs.getDouble("interest"), 
    					rs.getDouble("maxBalance"), rs.getDouble("avaliableBalance"));
    			cards.add(credit);
    		}
    		
    		rs.close();
    		stat.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} 
	}
	
	private void loadAccounts() {
    	try {
    		Connection con = DatabaseManager.getConnection();
    		PreparedStatement stat = con.prepareStatement("SELECT * FROM CheckingAccount INNER JOIN Accounts ON CheckingAccount.accountNumber=Accounts.accountNumber WHERE broncoID = ?");
    		stat.setLong(1, getBroncoID());
    		ResultSet rs = stat.executeQuery();
    		while (rs.next()) {
    			//long ID, double bal, Date open, ArrayList<Transaction> stats, double charge, DebitCard card
    			CheckingAccount acc = new CheckingAccount(rs.getLong("accountNumber"), rs.getDouble("balance"), rs.getDate("openDate"), rs.getDouble("monthlyCharge"), null);
    			accounts.add(acc);
    			
    			String cardnum = rs.getString("cardNumber");
    			cards.forEach(e -> {
    				if (e.getCardNumber().equalsIgnoreCase(cardnum)) {
    					acc.setCard((DebitCard) e);
    				}
    			});
    			
    		}
    		rs.close();
    		stat.close();
    	
    		stat = con.prepareStatement("SELECT * FROM SavingsAccount INNER JOIN Accounts on SavingsAccount.accountNumber=Accounts.accountNumber WHERE broncoID = ?");
    		stat.setLong(1, getBroncoID());
    		rs = stat.executeQuery();
    		while (rs.next()) {
    			//long ID,  double bal, Date open, ArrayList<Transaction> stats, double interest
    			SavingsAccount acc = new SavingsAccount(rs.getLong("accountNumber"), rs.getDouble("balance"), rs.getDate("openDate"), rs.getDouble("interest"));
    			accounts.add(acc);
    		}
    		rs.close();
    		stat.close();
    		
    		stat = con.prepareStatement("SELECT * FROM CDAccount INNER JOIN Accounts on CDAccount.accountNumber=Accounts.accountNumber WHERE broncoID = ?");
    		stat.setLong(1, getBroncoID());
    		rs = stat.executeQuery();
    		while (rs.next()) {
    			//long ID, double bal, Date open, ArrayList<Transaction> stats, int term, double interest, double deposit
    			CDAccount acc = new CDAccount(rs.getLong("accountNumber"), rs.getDouble("balance"), rs.getDate("openDate"), rs.getInt("termInYears"), rs.getDouble("interest"), rs.getDouble("minDeposit"));
    			accounts.add(acc);
    		}
    		
    		rs.close();
    		stat.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} 
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
	 * 
	 * @param ID Account ID
	 * @return Account with specified account ID. If no such exists, returns null
	 */
	public Account getAccount(long ID) {
		for (Account acc : getAccounts()) {
			if (acc.getAccountID() == ID) {
				return acc;
			}
		}
		return null;
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
	 * 
	 * @param num Card Number
	 * @return Card with specified card number. If no such exists, returns null
	 */
	public Card getCard(String num) {
		for (Card c : getCards()) {
			if (c.getCardNumber().equalsIgnoreCase(num)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * @return Get all loans
	 */
	public ArrayList<Loan> getLoans() {
		return loans;
	}
	
	/**
	 * @param loanID The ID of the Loan
	 * @return Loan with specified ID. If no such exists, returns null
	 */
	public Loan getLoan(long loanID) {
		for (Loan l : getLoans()) {
			if (l.getLoanID() == loanID) {
				return l;
			}
		}
		return null;
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
		
		//TODO: Creation of credit card into db
		return false;
	}
	
	/**
	 * Take out a student or personal loan
	 * @param loan Loan thats to be added
	 * @return True if creation went successful!
	 */
	public boolean addLoan(Loan loan) {
		if (loan == null) {
			return false;
		}
		
		try {
			PreparedStatement stat = DatabaseManager.getConnection().prepareStatement("INSERT INTO Loans (amount, remainingBal, interest, openDate, broncoID, term) "
					+ "VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stat.setDouble(1, loan.getLoanedAmount());
			stat.setDouble(2, loan.getAmountLeft());
			stat.setDouble(3, loan.getInterest());
			stat.setDate(4, new java.sql.Date(loan.getOpenDate().getTime()));
			stat.setLong(5, getBroncoID());
			stat.setInt(6, loan.getTermInYears());
			stat.execute();
			
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
			    long id = rs.getLong(1);
			    loan.setLoanID(id);
			    getLoans().add(loan);
			    System.out.println(id);
			}
			
			stat.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	

}
