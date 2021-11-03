package bank.UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bank.UI.Dialogs.LoanDialogs;
import bank.customer.Customer;
import bank.loans.Loan;

public class LoansTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTable loanOpen;
	private JButton open;
	private JButton makePayment;
	private JButton viewTrans;
	private JButton viewInfo;
	
	public LoansTab(Customer customer) {
		setLayout(null);
		
		String[] cols = { "ID", "Loaned Amount", "Balance Left"};
		
		DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
		customer.getLoans().forEach(e -> {
			Object[] s = { e.getLoanID(), "$" + e.getLoanedAmount(), "$" + e.getAmountLeft()};
			tableModel.addRow(s);
		});
		

		scrollPane = new JScrollPane();
		loanOpen = new JTable(tableModel);
		open = new JButton();
		makePayment = new JButton();
		viewTrans = new JButton();
		viewInfo = new JButton();
		
		//======== Scroll Bar for JTable ========
		loanOpen.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		loanOpen.setDefaultEditor(Object.class, null);
		JTableHeader header = loanOpen.getTableHeader();
		header.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
		header.setFont(new Font("Courier New", Font.BOLD, 14));
		header.setBackground(Color.yellow);
		
		scrollPane.setViewportView(loanOpen);
		add(scrollPane);
		scrollPane.setBounds(10, 5, 400, 80);
		
		//---- Open a New Loan ----
		open.setText("Open new Loan");
		add(open);
		open.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			Loan loan = LoanDialogs.openLoan(frame);
			if (loan != null) {
				if (customer.addLoan(loan)) {
					JOptionPane.showMessageDialog(frame, "Successfully opened new Loan!", "Success", JOptionPane.PLAIN_MESSAGE);
					Object[] s = { loan.getLoanID(), "$" + loan.getLoanedAmount(), "$" + loan.getAmountLeft()};
					tableModel.addRow(s);
				} else {
					JOptionPane.showMessageDialog(frame, "Problem occured creating now loan", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		open.setBounds(15, 115, 190, 23);


		//---- Make a Payment ----
		makePayment.setText("Make Payment");
		add(makePayment);
		makePayment.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (loanOpen.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Loan first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			long ID = (long) tableModel.getValueAt(loanOpen.getSelectedRow(), 0);
			Loan l = customer.getLoan(ID);
			boolean result = LoanDialogs.makeLoanPayment(frame, l);
			if (result) {
				tableModel.setValueAt("$" + (l.getAmountLeft()), loanOpen.getSelectedRow(), 2);
			}
			
		});
		makePayment.setBounds(15, 170, 190, 23);

		//---- View Loan Transactions ----
		viewTrans.setText("View Transactions");
		add(viewTrans);
		viewTrans.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (loanOpen.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Loan first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			long ID = (long) tableModel.getValueAt(loanOpen.getSelectedRow(), 0);
			Loan l = customer.getLoan(ID);
			LoanDialogs.viewTransactions(frame, l);
		});
		viewTrans.setBounds(235, 115, 190, 23);
		
		//---- Info ---
		viewInfo.setText("View Info");
		add(viewInfo);
		viewInfo.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			if (loanOpen.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(frame, "Please select a Loan first", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			long ID = (long) tableModel.getValueAt(loanOpen.getSelectedRow(), 0);
			Loan l = customer.getLoan(ID);
			LoanDialogs.viewInfo(frame, l);
			
		});
		viewInfo.setBounds(235, 170, 190, 25);
		
	}

}
