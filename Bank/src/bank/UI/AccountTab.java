package bank.UI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bank.UI.Dialogs.AccountDialogs;
import bank.accounts.Account;
import bank.accounts.CDAccount;
import bank.accounts.CheckingAccount;
import bank.accounts.SavingsAccount;
import bank.customer.Customer;

public class AccountTab extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton view;
	private JButton checking;
	private JButton savings;
	private JButton cd;
	private JButton trans;
	private JButton deposit;
	private JButton withdraw;
	private JButton transfer;
	private JScrollPane scrollPane;
	private JTable accs;

	public AccountTab(Customer customer) {
		setLayout(null);
		
		String[] cols = { "Account Number", "Type", "Balance"};
		DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
		
		customer.getAccounts().forEach(e -> {
			if (e instanceof CheckingAccount) {
				Object[] s = { e.getAccountID(), "Checking", "$" + e.getBalance() };
				tableModel.addRow(s);
			} else if (e instanceof SavingsAccount) {
				Object[] s = { e.getAccountID(), "Savings", "$" + e.getBalance() };
				tableModel.addRow(s);
			} else {
				Object[] s = { e.getAccountID(), "CD", "$" + e.getBalance() };
				tableModel.addRow(s);
			}
		});
		
		view = new JButton();
		checking = new JButton();
		savings = new JButton();
		cd = new JButton();
		trans = new JButton();
		deposit = new JButton();
		withdraw = new JButton();
		transfer = new JButton();
		scrollPane = new JScrollPane();
		accs = new JTable(tableModel);
		
		//======== ScrollPane for JTable ========
		accs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		accs.setDefaultEditor(Object.class, null);
		JTableHeader header = accs.getTableHeader();
		header.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
		header.setFont(new Font("Courier New", Font.BOLD, 14));
		header.setBackground(Color.yellow);
		
		scrollPane.setViewportView(accs);
		add(scrollPane);
		scrollPane.setBounds(10, 5, 400, 80);

		//---- Open a Savings Account ----
		savings.setText("Open Savings Account");
		add(savings);
		savings.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			SavingsAccount save = AccountDialogs.createSavingsAccount(frame);
			if (save != null) {
				if (customer.addAccount(save)) {
					JOptionPane.showMessageDialog(frame, "Successfully opened new Savings Account!", "Success", JOptionPane.PLAIN_MESSAGE);
					Object[] s = { save.getAccountID(), "Savings", "$" + save.getBalance()};
					tableModel.addRow(s);
				} else {
					JOptionPane.showMessageDialog(frame, "Problem occured creating new Savings Account", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		savings.setBounds(15, 115, 190, 23);
		
		
		//---- Open a CD Account ----
		cd.setText("Open CD Account");
		add(cd);
		cd.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			CDAccount c = AccountDialogs.createCDAccount(frame);
			if (c != null) {
				if (customer.addAccount(c)) {
					JOptionPane.showMessageDialog(frame, "Successfully opened new CD Account!", "Success", JOptionPane.PLAIN_MESSAGE);
					Object[] s = { c.getAccountID(), "CD", "$" + c.getBalance()};
					tableModel.addRow(s);
				} else {
					JOptionPane.showMessageDialog(frame, "Problem occured creating new CD Account", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		cd.setBounds(15, 170, 190, 23);
		
		
		//---- Open a Checking Account ----
		checking.setText("Open Checking Account");
		checking.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			CheckingAccount check = AccountDialogs.createCheckingAccount(frame);
			if (check != null) {
				if (customer.addAccount(check)) {
					JOptionPane.showMessageDialog(frame, "Successfully opened new Checking Account!", "Success", JOptionPane.PLAIN_MESSAGE);
					Object[] s = { check.getAccountID(), "Checking", "$" + check.getBalance()};
					tableModel.addRow(s);
				} else {
					JOptionPane.showMessageDialog(frame, "Problem occured creating new Checking Account", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(checking);
		checking.setBounds(15, 220, 190, 23);
		
		//---- View Account Information ----
		view.setText("View Account Info");
		add(view);
		view.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (accs.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Account first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			long ID = (long) tableModel.getValueAt(accs.getSelectedRow(), 0);
			Account account = customer.getAccount(ID);
			AccountDialogs.viewAccountInfo(frame, account);
		});
		view.setBounds(235, 115, 190, 23);


		//---- View Transactions ----
		trans.setText("View Transactions");
		add(trans);
		trans.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (accs.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Account first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			long ID = (long) tableModel.getValueAt(accs.getSelectedRow(), 0);
			Account account = customer.getAccount(ID);
			AccountDialogs.viewAccountTransactions(frame, account);
		});
		trans.setBounds(235, 170, 190, 25);
		

		//---- Make a Deposit ----
		deposit.setText("Deposit");
		add(deposit);
		deposit.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (accs.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Account first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			long ID = (long) tableModel.getValueAt(accs.getSelectedRow(), 0);
			Account account = customer.getAccount(ID);
			Double amount = AccountDialogs.deposit(frame, account);
			if (amount != null) {
				try {
					boolean result = account.deposit(amount);
					if (result) {
						JOptionPane.showMessageDialog(frame, "Successfully deposited $" + amount + "!", "Success", JOptionPane.PLAIN_MESSAGE);
						tableModel.setValueAt("$" + account.getBalance(), accs.getSelectedRow(), 2);
					} else
						JOptionPane.showMessageDialog(frame, "Error: Unable to deposit", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deposit.setBounds(235, 220, 190, 23);
		
		//--- Make a withdrawal ----
		withdraw.setText("Withdraw");
		add(withdraw);
		withdraw.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (accs.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Account first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			long ID = (long) tableModel.getValueAt(accs.getSelectedRow(), 0);
			Account account = customer.getAccount(ID);
			Double amount = AccountDialogs.withdraw(frame, account);
			if (amount != null) {
				try {
					boolean result = account.withdraw(amount);
					if (result) {
						JOptionPane.showMessageDialog(frame, "Successfully withdrew $" + amount + "!", "Success", JOptionPane.PLAIN_MESSAGE);
						tableModel.setValueAt("$" + account.getBalance(), accs.getSelectedRow(), 2);
					} else
						JOptionPane.showMessageDialog(frame, "Error: Unable to withdraw", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		withdraw.setBounds(235, 270, 190, 23);
		
		//--- Transfer money to another account ---
		transfer.setText("Transfer");
		add(transfer);
		transfer.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (accs.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Account first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			long ID = (long) tableModel.getValueAt(accs.getSelectedRow(), 0);
			Account account = customer.getAccount(ID);
			Object[] info = AccountDialogs.transfer(frame, account, customer);
			if (info[0] != null) {
				Account to = (Account) info[0];
				double amount = (double) info[1];
				try {
					boolean result = account.transfer(to, amount);
					if (result) {
						JOptionPane.showMessageDialog(frame, "Successfully transferred $" + amount + "!", "Transfer", JOptionPane.PLAIN_MESSAGE);
						tableModel.setValueAt("$" + account.getBalance(), accs.getSelectedRow(), 2);
						updateTransferRow(to, tableModel);
					} else
						JOptionPane.showMessageDialog(frame, "Error: Unable to transfer", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		transfer.setBounds(15, 270, 190, 23);
		
		
	}
	
	private void updateTransferRow(Account to, DefaultTableModel model) {
		for (int i = 0; i < model.getRowCount(); i++) {
			if ((long) model.getValueAt(i, 0) == to.getAccountID()) {
				model.setValueAt("$" + to.getBalance(), i, 2);
				return;
			}
		}
	}

}
