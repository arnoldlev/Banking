package bank.UI.Dialogs;

import java.awt.Color;

import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bank.accounts.Account;
import bank.accounts.CDAccount;
import bank.accounts.CheckingAccount;
import bank.accounts.SavingsAccount;
import bank.cards.DebitCard;
import bank.customer.Customer;

public class AccountDialogs {
	
	private Account account;
	
	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account acc) {
		account = acc;
	}
	
	public static Double deposit(JFrame parent, Account acc) {
		String val = JOptionPane.showInputDialog(parent, "Enter the amount to deposit: ", "Deposit", JOptionPane.OK_CANCEL_OPTION);
		try {
			Double amount = Double.parseDouble(val);
			return amount;
		} catch (NumberFormatException | NullPointerException e) {
			JOptionPane.showMessageDialog(parent, "Error: You must enter a numeric input", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public static Double withdraw(JFrame parent, Account acc) {
		String val = JOptionPane.showInputDialog(parent, "Enter the amount to withdraw: ", "Withdrawal", JOptionPane.OK_CANCEL_OPTION);
		try {
			Double amount = Double.parseDouble(val);
			return amount;
		} catch (NumberFormatException | NullPointerException e) {
			JOptionPane.showMessageDialog(parent, "Error: You must enter a numeric input", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public static Object[] transfer(JFrame parent, Account acc, Customer c) {
		JDialog dialog = new JDialog(parent, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		dialog.setLayout(null);
		dialog.setTitle("Transfer Money");
		
		JLabel amText = new JLabel();
		JLabel toText = new JLabel();
		JSpinner amount = new JSpinner();
		JButton transfer = new JButton();
		JButton cancel = new JButton();
		JComboBox<String> toList = new JComboBox<String>();
		c.getAccounts().forEach(e -> {
			if (e.getAccountID() != acc.getAccountID())
				toList.addItem("Account #" + e.getAccountID());
		});
		
		Object[] s = {null, null};
		
		//---- Amount to Transfer (Text) ----
		amText.setText("Enter the amount:");
		amText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(amText);
		amText.setBounds(10, 15, 120, 25);

		//---- Transfer To (Text)  ----
		toText.setText("Transfer to:");
		toText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(toText);
		toText.setBounds(10, 55, 120, 15);
		
		//---- Amount to Transfer (Spinner) ----
		SpinnerNumberModel model;
		if (acc instanceof CheckingAccount) {
			CheckingAccount check = (CheckingAccount) acc;
			model = new SpinnerNumberModel(1.0, 1.0, check.getCard().getMaxTransaction(), 1.0); // Checking Accounts transactions are linked to their DebitCards
		} else {
			model = new SpinnerNumberModel(1.0, 1.0, acc.getBalance(), 1.0);
		}
		amount.setModel(model);
		dialog.add(amount);
		amount.setBounds(130, 20, 90, 20);
		
		//---- Accounts (ComboBox) ----
		dialog.add(toList);
		toList.setBounds(130, 55, 96, 20);

		//---- Button to Transfer ----
		transfer.setText("Transfer Money");
		dialog.add(transfer);
		transfer.addActionListener(e -> {
			String ID = (String) toList.getSelectedItem();
			long idValue = Long.parseLong(ID.substring(ID.indexOf("#") + 1));
			Account to = c.getAccount(idValue);
			s[0] = to;
			s[1] = (double) amount.getValue();
			dialog.setVisible(false);
		});
		transfer.setBounds(25, 100, 160, 25);

		//---- Cancel ----
		cancel.setText("Cancel");
		dialog.add(cancel);
		cancel.addActionListener(e -> {
			dialog.setVisible(false);
		});
		cancel.setBounds(215, 100, 135, 25);
		dialog.setVisible(true);
		return s;
	}
	
	public static SavingsAccount createSavingsAccount(JFrame parent) {
		JDialog dialog = new JDialog(parent, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		dialog.setLayout(null);
		dialog.setTitle("Create a new Savings Account");
		
		AccountDialogs acc = new AccountDialogs();
		Double[] interest = { 0.5, 1.0, 2.0 };
		
		JLabel depositText = new JLabel();
		JSpinner deposit = new JSpinner();
		JLabel minValue = new JLabel();
		JLabel interestText = new JLabel();
		JComboBox<Double> interests = new JComboBox<Double>(interest);
		JButton create = new JButton();
		JButton cancel = new JButton();
		
		//---- depositText ----
		depositText.setText("Enter the initial deposit:");
		depositText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(depositText);
		depositText.setBounds(10, 20, 150, 15);
		
		deposit.setModel(new SpinnerNumberModel(100.0, 100.0, null, 1.0));
		dialog.add(deposit);
		deposit.setBounds(165, 20, 85, 20);

		//---- minValue ----
		minValue.setText("(Min Value: $100)");
		minValue.setForeground(Color.red);
		minValue.setFont(minValue.getFont().deriveFont(minValue.getFont().getStyle() | Font.ITALIC));
		dialog.add(minValue);
		minValue.setBounds(260, 15, 150, 25);

		//---- interestText ----
		interestText.setText("Choose a interest rate:");
		interestText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(interestText);
		interestText.setBounds(10, 60, 150, 15);
		
		dialog.add(interests);
		interests.setBounds(165, 60, 85, interests.getPreferredSize().height);

		//---- create ----
		create.setText("Create Account");
		dialog.add(create);
		create.addActionListener(e -> {
			SavingsAccount savings = new SavingsAccount();
			savings.setBalance((double) deposit.getValue());
			savings.setInterest((double) interests.getSelectedItem());
			acc.setAccount(savings);
			dialog.setVisible(false);
		});
		create.setBounds(25, 100, 150, 23);

		//---- cancel ----
		cancel.setText("Cancel");
		dialog.add(cancel);
		cancel.addActionListener(e -> {
			dialog.setVisible(false);
		});
		cancel.setBounds(185, 100, 120, 23);
		
		dialog.setVisible(true);
		
		return (SavingsAccount) acc.getAccount();
	}
	
	public static CDAccount createCDAccount(JFrame parent) {
		JDialog dialog = new JDialog(parent, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		dialog.setLayout(null);
		dialog.setTitle("Create a new CD Account");
		
		AccountDialogs acc = new AccountDialogs();
		Double[] interest = { 0.5, 1.0, 2.0 };
		
		JLabel depositText = new JLabel();
		JSpinner deposit = new JSpinner();
		JLabel termText = new JLabel();
		JLabel minValue = new JLabel();
		JSpinner term = new JSpinner();
		JLabel interestText = new JLabel();
		JLabel minText = new JLabel();
		JSpinner minDeposit = new JSpinner();
		JComboBox<Double> interests = new JComboBox<Double>(interest);
		JButton create = new JButton();
		JButton cancel = new JButton();
		
		//---- Initial Deposit (Text) ----
		depositText.setText("Enter the initial deposit:");
		depositText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(depositText);
		depositText.setBounds(10, 20, 150, 15);
		
		//---- Initial Deposit (Spinner) ----
		deposit.setModel(new SpinnerNumberModel(1000.0, 1000.0, null, 1.0));
		dialog.add(deposit);
		deposit.setBounds(165, 20, 100, 20);
		
		//---- Minimum Deposit (Text) ----
		minText.setText("Set your minimum deposit:");
		minText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(minText);
		minText.setBounds(10, 80, 175, 15);
		
		//---- Minimum Deposit (Spinner) ----
		minDeposit.setModel(new SpinnerNumberModel(1.0, 1.0, null, 1.0));
		dialog.add(minDeposit);
		minDeposit.setBounds(180, 80, 85, 20);

		//---- Term Years (Text) ----
		termText.setText("Enter the term in years:");
		termText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(termText);
		termText.setBounds(10, 55, 150, 15);
		
		//---- Term Years (Spinner) ----
		term.setModel(new SpinnerNumberModel(1, 1, null, 1));
		dialog.add(term);
		term.setBounds(165, 55, 50, 20);


		//---- Minimum Value Warning (Text) ----
		minValue.setText("(Min Value: $1000)");
		minValue.setForeground(Color.red);
		minValue.setFont(minValue.getFont().deriveFont(minValue.getFont().getStyle() | Font.ITALIC));
		dialog.add(minValue);
		minValue.setBounds(270, 15, minValue.getPreferredSize().width, 25);

		//---- Interest Rate (Text) ----
		interestText.setText("Choose a interest rate:");
		interestText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(interestText);
		interestText.setBounds(10, 120, 150, 15);
		
		//---- Interest Rate (Combo) ----
		dialog.add(interests);
		interests.setBounds(160, 110, 85, interests.getPreferredSize().height);

		//---- create ----
		create.setText("Create Account");
		dialog.add(create);
		create.addActionListener(e -> {
			CDAccount cd = new CDAccount();
			cd.setBalance((double) deposit.getValue());
			cd.setInterest((double) interests.getSelectedItem());
			cd.setMinDeposit((double) minDeposit.getValue());
			cd.setTermInYears((int) term.getValue());
			
			acc.setAccount(cd);
			dialog.setVisible(false);
		});
		create.setBounds(40, 180, 150, create.getPreferredSize().height);

		//---- cancel ----
		cancel.setText("Cancel");
		dialog.add(cancel);
		cancel.addActionListener(e -> {
			dialog.setVisible(false);
		});
		cancel.setBounds(200, 180, 120, 23);

		dialog.setVisible(true);
		
		return (CDAccount) acc.getAccount();
	}
	
	public static CheckingAccount createCheckingAccount(JFrame parent) {
		JDialog dialog = new JDialog(parent, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		dialog.setLayout(null);
		dialog.setTitle("Create a new Checking Account");
		
		AccountDialogs acc = new AccountDialogs();
		
		JLabel startText = new JLabel();
		JLabel maxText = new JLabel();
		JLabel atmText = new JLabel();
		JLabel min = new JLabel();
		JSpinner starting = new JSpinner();
		JSpinner maxTrans = new JSpinner();
		JSpinner maxTrans2 = new JSpinner();
		JButton create = new JButton();
		JButton cancel = new JButton();
		
		//---- Minimum Value Text ----
		min.setText("Minimum Value: $100");
		min.setForeground(Color.RED);
		min.setFont(min.getFont().deriveFont(min.getFont().getStyle() | Font.ITALIC));
		dialog.add(min);
		min.setBounds(180, 15, 215, 25);
		
		//---- Starting Balance (Text) ----
		startText.setText("Enter the starting amount:");
		startText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(startText);
		startText.setBounds(15, 30, 210, 25);

		//---- Max Transaction (Text) ----
		maxText.setText("Enter the maximum debit card transaction amount:");
		maxText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(maxText);
		maxText.setBounds(15, 70, 310, 25);

		//---- Max ATM Withdrawal (Text) ----
		atmText.setText("Enter the maximum ATM Withdrawal Limit:");
		atmText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(atmText);
		atmText.setBounds(15, 135, 280, 25);

		//---- Starting Balance (Spinner) ----
		starting.setModel(new SpinnerNumberModel(100.0, 100.0, null, 1.0));
		dialog.add(starting);
		starting.setBounds(185, 35, 90, starting.getPreferredSize().height);

		//---- Max Transaction (Spinner) ----
		maxTrans.setModel(new SpinnerNumberModel(100.0, 0.0, null, 1.0));
		dialog.add(maxTrans);
		maxTrans.setBounds(15, 95, 90, 20);

		//---- Max ATM Withdrawal (Spinner) ----
		maxTrans2.setModel(new SpinnerNumberModel(100.0, 0.0, null, 1.0));
		dialog.add(maxTrans2);
		maxTrans2.setBounds(15, 165, 90, 20);

		//---- create ----
		create.setText("Create Account");
		dialog.add(create);
		create.addActionListener(e -> {
			CheckingAccount checking = new CheckingAccount();
			checking.setBalance((double) starting.getValue());
			checking.setMonthlyCharge(25.0);
			
			DebitCard debit = new DebitCard();
			debit.setMaxTransaction((double) maxTrans.getValue());
			debit.setAtmLimit((double) maxTrans2.getValue());
			checking.setCard(debit);
			
			acc.setAccount(checking);
			dialog.setVisible(false);
		});
		create.setBounds(80, 205, 165, create.getPreferredSize().height);

		//---- cancel ----
		cancel.setText("Cancel");
		cancel.addActionListener(e -> {
			dialog.setVisible(false);
		});
		dialog.add(cancel);
		cancel.setBounds(80, 240, 165, 23);
		
		dialog.setVisible(true);
		return (CheckingAccount) acc.getAccount();
	}

	public static void viewAccountTransactions(JFrame parent, Account acc) {
		JDialog dialog = new JDialog(parent, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		dialog.setTitle("Account Transactions");
		
		String[] cols = { "ID", "Date", "Description", "Amount" };

		DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
		acc.getTransactions().forEach(e -> tableModel.addRow(e.toData()));
		JTable transacs = new JTable(tableModel);
		
		JTableHeader header = transacs.getTableHeader();
		header.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
		header.setFont(new Font("Courier New", Font.BOLD, 14));
		header.setBackground(Color.yellow);
		
		JScrollPane pane = new JScrollPane(transacs);
		dialog.add(pane);
		dialog.pack();
		dialog.setVisible(true);
	}
	
	public static void viewAccountInfo(JFrame parent, Account acc) {
		String t = "<html> "
				+ "Account Number: " + acc.getAccountID() + ""
				+ "<br>Open Date: " + acc.getOpenDate() + "</br>"
				+ "<br>Balance: $" + acc.getBalance() + "</br>";
		
		if (acc instanceof CheckingAccount) {
			CheckingAccount a = (CheckingAccount) acc;
			t += "<br>Monthly Charge: " + a.getMonthlyCharge() + "</br>";
		} else if (acc instanceof SavingsAccount) {
			SavingsAccount s = (SavingsAccount) acc;
			t += "<br>Interest Rate: " + s.getInterest() + "%";
		} else {
			CDAccount cd = (CDAccount) acc;
			t += "<br>Term In Years: " + cd.getTermInYears() + "</br>"
				+ "<br>Interest Rate: " + cd.getInterest() + "%</br>"
				+ "<br>Minimum Deposit: $" + cd.getMinDeposit() + "</br>";
		}
		
		JOptionPane.showMessageDialog(parent, t, "Account Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
