package bank.UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bank.UI.Dialogs.CardDialogs;
import bank.accounts.CheckingAccount;
import bank.accounts.Transaction;
import bank.cards.Card;
import bank.cards.CreditCard;
import bank.cards.DebitCard;
import bank.customer.Customer;

public class CardTab extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrollPane;
	private JTable cards;
	private JButton payment;
	private JButton cardInfo;
	private JButton deleteCard;
	private JButton cardTrans;
	private JButton openCard;
	private JButton viewTrans;
	
	public CardTab(Customer customer) {
		setLayout(null);
		
		String[] cols = { "Card Number", "Type", "CSV"};

		DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
		customer.getCards().forEach(e -> {
			if (e instanceof CreditCard) {
				Object[] s = { e.getCardNumber(), "Credit", e.getCsv()};
				tableModel.addRow(s);
			} else {
				Object[] s = { e.getCardNumber(), "Debit", e.getCsv() };
				tableModel.addRow(s);
			}
			
		});
		
		scrollPane = new JScrollPane();
		cards = new JTable(tableModel);
		payment = new JButton();
		cardInfo = new JButton();
		deleteCard = new JButton();
		cardTrans = new JButton();
		openCard = new JButton();
		viewTrans = new JButton();
		
		//======== Scroll Pane for JTable ========
		cards.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cards.setDefaultEditor(Object.class, null);
		JTableHeader header = cards.getTableHeader();
		header.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
		header.setFont(new Font("Courier New", Font.BOLD, 14));
		header.setBackground(Color.yellow);
		
		scrollPane.setViewportView(cards);
		add(scrollPane);
		scrollPane.setBounds(10, 5, 400, 80);
		

		//---- Make a CREDITCARD Payment [Cannot make payments on debit card] ----
		payment.setText("Make Payment");
		add(payment);
		payment.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (cards.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Card first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String num = (String) tableModel.getValueAt(cards.getSelectedRow(), 0);
			Card c = customer.getCard(num);
			
			CardDialogs.makePayment(frame, c);
		});
		payment.setBounds(15, 115, 190, 23);
		
		

		//---- View Card Information  ----
		cardInfo.setText("View Card Info");
		add(cardInfo);
		cardInfo.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (cards.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Card first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String num = (String) tableModel.getValueAt(cards.getSelectedRow(), 0);
			Card c = customer.getCard(num);
			CardDialogs.viewInfo(frame, c);
		});
		cardInfo.setBounds(15, 170, 190, 23);
		
		

		//---- Delete a CREDITCARD [Cannot delete DebitCard] ----
		deleteCard.setText("Delete Card");
		add(deleteCard);
		deleteCard.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (cards.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Card first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String num = (String) tableModel.getValueAt(cards.getSelectedRow(), 0);
			Card c = customer.getCard(num);
			int r = CardDialogs.deleteCreditCard(frame, c);
			if (r == 0) {
				if (customer.deleteCreditCard((CreditCard) c)) {
					JOptionPane.showMessageDialog(frame, "Card Deleted!", "Deleted", JOptionPane.INFORMATION_MESSAGE);
					tableModel.removeRow(cards.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(frame, "Problem occured deleting Credit Card", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deleteCard.setBounds(15, 220, 190, 23);
		
		
		
		//---- Add a Transaction ----
		cardTrans.setText("Add Transaction");
		add(cardTrans);
		cardTrans.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (cards.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Card first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String num = (String) tableModel.getValueAt(cards.getSelectedRow(), 0);
			Card c = customer.getCard(num);
			Transaction t = CardDialogs.addTransaction(frame, c);
			if (t != null) {
				boolean result;
				if (c instanceof CreditCard) {
					result = ((CreditCard) c).addTransaction(t);
				} else {
					CheckingAccount acc = customer.getCheckAssociated(c.getCardNumber());
					result = ((DebitCard) c).addTransaction(acc, t);
				}
				if (result) {
					JOptionPane.showMessageDialog(frame, "Successfully added a transaction!", "Success", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "You have invalid funds for this transaction!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		cardTrans.setBounds(235, 115, 190, 23);
		
		
		//---- Open a new CREDITCARD [DebitCards are automatically created with CheckingAccounts only] ----
		openCard.setText("Open Credit Card");
		add(openCard);
		openCard.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			CreditCard card = CardDialogs.openCreditCard(frame);
			if (card != null) {
				if (customer.addCreditCard(card)) {
					JOptionPane.showMessageDialog(frame, "Successfully opened new Credit Card with limit $" + card.getMaxBalance() + "!", "Success", JOptionPane.PLAIN_MESSAGE);
					Object[] s = { card.getCardNumber(), "Credit", card.getCsv()};
					tableModel.addRow(s);
				} else {
					JOptionPane.showMessageDialog(frame, "Problem occured creating new Credit Card", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		openCard.setBounds(235, 170, 190, 25);
		
		

		//---- View Transactions ----
		viewTrans.setText("View Transactions");
		add(viewTrans);
		viewTrans.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (cards.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Card first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String num = (String) tableModel.getValueAt(cards.getSelectedRow(), 0);
			Card c = customer.getCard(num);
			CardDialogs.viewCardTransactions(frame, c);
			
		});
		viewTrans.setBounds(235, 220, 190, 23);
	}
	

}
