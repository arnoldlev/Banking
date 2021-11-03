package bank.UI.Dialogs;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bank.loans.Loan;

public class LoanDialogs {
	
	private Loan loan;
	private boolean result;
	
	public boolean getResult() {
		return result;
	}
	
	public void setResult(boolean s) {
		result = s;
	}
	
	public Loan getLoan() {
		return loan;
	}
	
	public void setLoan(Loan l) {
		loan = l;
	}
	
	public static Loan openLoan(JFrame frame) {
		JDialog dialog = new JDialog(frame, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(frame);
		dialog.setLayout(null);
		dialog.setTitle("Open new Loan");
		
		LoanDialogs loanD = new LoanDialogs();
		
		
		Double[] interest = { 0.5, 1.0, 2.0 };
		
		JLabel loanText = new JLabel();
		JLabel rateText = new JLabel();
		JSpinner loan = new JSpinner();
		JLabel termText = new JLabel();
		JSpinner term = new JSpinner();
		JButton create = new JButton();
		JButton cancel = new JButton();
		JComboBox<Double> interests = new JComboBox<Double>(interest);
		
		//---- creditText ----
		loanText.setText("Enter the loan amount:");
		loanText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(loanText);
		loanText.setBounds(10, 15, 150, 25);

		//---- rateText ----
		rateText.setText("Select interest rate:");
		rateText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(rateText);
		rateText.setBounds(10, 55, 130, 15);
		
		//--- interest rates ----
		dialog.add(interests);
		interests.setBounds(145, 55, 85, 20);
		
		//--- spinner for loan amount ----
		loan.setModel(new SpinnerNumberModel(100.0, 100.0, 100000.0, 1));
		dialog.add(loan);
		loan.setBounds(160, 20, 80, 20);
		
		//---- termText ----
		termText.setText("Enter term in years:");
		termText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(termText);
		termText.setBounds(10, 90, 130, 15);
		
		
		//---- spinner for loan term ----
		term.setModel(new SpinnerNumberModel(5, 5, 20, 1));
		dialog.add(term);
		term.setBounds(140, 90, 65, 20);

		//---- create ----
		create.setText("Open Loan");
		dialog.add(create);
		create.addActionListener(e -> {
			Loan l2 = new Loan((double) loan.getValue(), (double) interests.getSelectedItem(), (int) term.getValue());
			loanD.setLoan(l2);
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
		
		
		dialog.setVisible(true);
		
		return loanD.getLoan();
	}
	
	public static boolean makeLoanPayment(JFrame frame, Loan loan) {
		if (loan.getAmountLeft() == 0) {
			JOptionPane.showMessageDialog(frame, "This loan is already paid out fully!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		LoanDialogs result = new LoanDialogs();
		
		JDialog dialog = new JDialog(frame, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(frame);
		dialog.setTitle("Make a Payment");
		dialog.setLayout(null);
		
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
		
		//---- spinner for loan payment ----
		payment.setModel(new SpinnerNumberModel(1.0, 1.0, loan.getAmountLeft(), 1.0));
		dialog.add(payment);
		payment.setBounds(140, 20, 80, 20);

		//---- balance ----
		balance.setText("Remaining Balance: $" + loan.getAmountLeft());
		balance.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(balance);
		balance.setBounds(115, 55, 195, 15);

		//---- limit ----
		limit.setText("Loaned Amount: $" + loan.getLoanedAmount());
		limit.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(limit);
		limit.setBounds(115, 80, 195, 15);

		//---- ok ----
		ok.setText("Make Payment");
		dialog.add(ok);
		ok.addActionListener(e -> {
			boolean res = loan.makePayment((double) payment.getValue());
			if (res) {
				JOptionPane.showMessageDialog(frame, "Successfully made payment!", "Success", JOptionPane.PLAIN_MESSAGE);
				result.setResult(true);
			} else {
				JOptionPane.showMessageDialog(frame, "A problem occured when making payment", "Error", JOptionPane.ERROR_MESSAGE);
				result.setResult(false);
			}
			
			dialog.setVisible(false);
		});
		ok.setBounds(50, 105, 120, 23);

		//---- cancel ----
		cancel.setText("Cancel");
		dialog.add(cancel);
		cancel.addActionListener(e -> {
			dialog.setVisible(false);
		});
		cancel.setBounds(190, 105, 120, 23);
		
		dialog.setVisible(true);
		
		return result.getResult();
	}
	
	public static void viewInfo(JFrame frame, Loan loan) {
		String t = "<html>"
				+ "Loaned Amount: $" + loan.getLoanedAmount() + ""
				+ "<br>Remaining: $" + loan.getAmountLeft() + "</br>"
				+ "<br>Interest Rate: " + loan.getInterest() + "%</br>"
				+ "<br>Open Date: " + loan.getOpenDate() + "</br>"
				+ "<br>Term (Years): " + loan.getTermInYears();
	
		JOptionPane.showMessageDialog(frame, t, "Loan Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void viewTransactions(JFrame frame, Loan loan) {
		JDialog dialog = new JDialog(frame, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(frame);
		dialog.setTitle("Loan Transactions");

		String[] cols = { "ID", "Date", "Description", "Amount" };
		
		DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
		loan.getTransactions().forEach(e -> tableModel.addRow(e.toData()));
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

}
