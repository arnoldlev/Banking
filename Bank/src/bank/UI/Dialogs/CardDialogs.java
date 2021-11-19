package bank.UI.Dialogs;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bank.accounts.Transaction;
import bank.cards.Card;
import bank.cards.CreditCard;
import bank.cards.DebitCard;

public class CardDialogs {
	
	private CreditCard credit;
	private Transaction transac;
	private boolean result;
	
	public boolean getResult() {
		return result;
	}
	
	public void setResult(boolean s) {
		result = s;
	}
	
	public CreditCard getCreditCard() {
		return credit;
	}
	
	public void setCreditCard(CreditCard c) {
		credit = c;
	}
	
	public Transaction getTransac() {
		return transac;
	}
	
	public void setTransaction(Transaction t) {
		transac = t;
	}
	
	public static void viewInfo(JFrame frame, Card card) {
		String t = "<html> "
				+ "Card Number: " + card.getCardNumber() + ""
				+ "<br>Expiration: " + card.getExpireDate() + "</br>"
				+ "<br>CSV: " + card.getCsv() + "</br>";
		
		if (card instanceof CreditCard) {
			CreditCard credit = (CreditCard) card;
			t += "<br>Avaliable Balance: " + credit.getAvaliableBalance() + "</br>"
					+ "<br>Maximum Limit: " + credit.getMaxBalance() + "</br>"
					+ "<br>Interest Rate: " + credit.getInterest() + "%";
		} else {
			DebitCard debit = (DebitCard) card;
			t += "<br>ATM Limit: " + debit.getAtmLimit() + "</br>"
					+ "<br>Maximum Transaction: " + debit.getMaxTransaction();
		}
	
		JOptionPane.showMessageDialog(frame, t, "Loan Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void viewCardTransactions(JFrame parent, Card card) {
		JDialog dialog = new JDialog(parent, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		
		dialog.setTitle("Card Transactions");
		
		String[] cols = { "ID", "Date", "Description", "Amount" };
		
		DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
		card.getTransactions().forEach(e -> tableModel.addRow(e.toData()));
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
	
	public static int deleteCreditCard(JFrame parent, Card card) {
		if (card instanceof DebitCard) {
			JOptionPane.showMessageDialog(null, "You cannot delete DebitCards!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
		}
		
		int result = JOptionPane.showConfirmDialog(parent, "Are you sure you want to delete Card Number: " + card.getCardNumber() + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		return result;
	}

	public static CreditCard openCreditCard(JFrame parent) {
		JDialog dialog = new JDialog(parent, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		dialog.setLayout(null);
		
		dialog.setTitle("Open new Credit Card");
		
		CardDialogs credit = new CardDialogs();
		
		Double[] interest = { 0.5, 1.0, 2.0 };
		
		JLabel creditText = new JLabel();
		JLabel rateText = new JLabel();
		JSpinner creditScore = new JSpinner();
		JButton create = new JButton();
		JButton cancel = new JButton();
		JComboBox<Double> interests = new JComboBox<Double>(interest);
		
		//---- creditText ----
		creditText.setText("Enter your credit score:");
		creditText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(creditText);
		creditText.setBounds(10, 15, 150, 25);

		//---- rateText ----
		rateText.setText("Select interest rate:");
		rateText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(rateText);
		rateText.setBounds(10, 55, 130, 15);
		
		creditScore.setModel(new SpinnerNumberModel(500, 500, 800, 1));
		dialog.add(creditScore);
		creditScore.setBounds(160, 20, 65, 20);

		//---- create ----
		create.setText("Open Credit Card");
		dialog.add(create);
		create.addActionListener(e -> {
			CreditCard card = new CreditCard((double) interests.getSelectedItem());
			credit.setCreditCard(card);
			
			if ((int) creditScore.getValue() <= 500) {
				card.setMaxBalance(300.0);
				card.setAvaliableBalance(300.0);
			} else if ((int) creditScore.getValue() > 500 && (int) creditScore.getValue() <= 600) {
				card.setMaxBalance(500.0);
				card.setAvaliableBalance(500.0);
			} else {
				card.setMaxBalance(1000.0);
				card.setAvaliableBalance(1000.0);
			}
			
			dialog.setVisible(false);
		});
		create.setBounds(20, 125, 160, 25);

		//---- cancel ----
		cancel.setText("Cancel");
		dialog.add(cancel);
		cancel.addActionListener(e -> {
			dialog.setVisible(false);
		});
		cancel.setBounds(220, 125, 135, 25);
		
		
		dialog.add(interests);
		interests.setBounds(145, 55, 85, 20);
		
		dialog.setVisible(true);
		
		return credit.getCreditCard();
	}
	
	public static Transaction addTransaction(JFrame parent, Card card) {
		JDialog dialog = new JDialog(parent, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		dialog.setLayout(null);
		
		CardDialogs tran = new CardDialogs();
		
		dialog.setTitle("Add Transaction");
		
		JLabel amText = new JLabel();
		JLabel detailsText = new JLabel();
		JSpinner transAmount = new JSpinner();
		JButton create = new JButton();
		JButton cancel = new JButton();
		JTextField info = new JTextField(80);
		
		//---- amText ----
		amText.setText("Enter the transaction amount:");
		amText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(amText);
		amText.setBounds(10, 15, 200, 25);

		//---- detailsText ----
		detailsText.setText("Enter the details of transaction:");
		detailsText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(detailsText);
		detailsText.setBounds(10, 55, 200, 15);
		
		transAmount.setModel(new SpinnerNumberModel(1.0, 1.0, null, 1.0));
		dialog.add(transAmount);
		transAmount.setBounds(205, 20, 65, 20);

		//---- create ----
		create.setText("Add Transaction");
		dialog.add(create);
		create.addActionListener(e -> {
			Transaction t = new Transaction(info.getText(), (double) transAmount.getValue());
			tran.setTransaction(t);
			dialog.setVisible(false);
		});
		create.setBounds(30, 125, 150, 25);

		//---- cancel ----
		cancel.setText("Cancel");
		dialog.add(cancel);
		cancel.addActionListener(e -> {
			dialog.setVisible(false);
		});
		cancel.setBounds(220, 125, 135, 25);
		
		dialog.add(info);
		info.setBounds(10, 80, 350, info.getPreferredSize().height);
		
		dialog.setVisible(true);
		return tran.getTransac();
	}
	
	public static void makePayment(JFrame parent, Card card) {
		if (card instanceof DebitCard) {
			JOptionPane.showMessageDialog(null, "Cannot make payment on DebitCards!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		CreditCard credit = (CreditCard) card;
		if (credit.getMaxBalance() - credit.getAvaliableBalance() < 1) {
			JOptionPane.showMessageDialog(null, "This CreditCard is currently fully paid!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JDialog dialog = new JDialog(parent, "", true);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		dialog.setLayout(null);
		dialog.setSize(375, 185);
		

		JLabel payText = new JLabel();
		JSpinner payment = new JSpinner();
		JLabel balance = new JLabel();
		JLabel limit = new JLabel();
		JButton ok = new JButton();
		JButton cancel = new JButton();
		
		//---- payText ----
		payText.setFont(new Font("Tahoma", Font.BOLD, 12));
		payText.setText("Enter your payment:");
		dialog.add(payText);
		payText.setBounds(10, 20, 150, 15);
		
		payment.setModel(new SpinnerNumberModel(1.0, 1.0, credit.getMaxBalance() - credit.getAvaliableBalance(), 1.0));
		dialog.add(payment);
		payment.setBounds(140, 20, 100, 20);

		//---- balance ----
		balance.setText("Card Balance: $" + credit.getAvaliableBalance());
		balance.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(balance);
		balance.setBounds(30, 55, 130, 15);


		//---- limit ----
		limit.setText("Card Limit: $" + credit.getMaxBalance());
		limit.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(limit);
		limit.setBounds(185, 55, 115, 15);

		//---- ok ----
		ok.setText("Make Payment");
		dialog.add(ok);
		ok.addActionListener(e -> {
			boolean result = credit.makePayment((double) payment.getValue());
			if (result) {
				JOptionPane.showMessageDialog(parent, "Successfully made payment!", "Success", JOptionPane.PLAIN_MESSAGE);
				dialog.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(parent, "A problem occured when making payment", "Error", JOptionPane.ERROR_MESSAGE);
				dialog.setVisible(false);
			}
		});
		ok.setBounds(50, 95, 120, ok.getPreferredSize().height);

		//---- cancel ----
		cancel.setText("Cancel");
		dialog.add(cancel);
		cancel.addActionListener(e -> {
			dialog.setVisible(false);
		});
		cancel.setBounds(190, 95, 120, 23);
		
		dialog.setVisible(true);
		
	}

}
