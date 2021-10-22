package bank.UI;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import bank.UI.Dialogs.FormDialogs;
import bank.UI.Dialogs.ViewDialogs;
import bank.accounts.CheckingAccount;
import bank.accounts.Transaction;
import bank.cards.DebitCard;

public class AccountTab extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton view;
	private JButton checking;
	private JButton savings;
	private JButton cd;
	private JButton delete;
	private JButton trans;
	private JScrollPane scrollPane1;
	private JTable accs;
	
	String[] rows = { "ID", "Type", "Balance"};
	String[][] data = { 
			{ "123", "Checking", "$100"},
			{ "234", "Savings", "$10000"}
	};

	public AccountTab() {
		setLayout(null);
		
		view = new JButton();
		checking = new JButton();
		savings = new JButton();
		cd = new JButton();
		delete = new JButton();
		trans = new JButton();
		scrollPane1 = new JScrollPane();
		accs = new JTable(data, rows);
		

		//---- savings ----
		savings.setText("Open Savings Account");
		add(savings);
		savings.setBounds(15, 115, 190, 23);
		
		//---- cd ----
		cd.setText("Open CD Account");
		add(cd);
		cd.setBounds(15, 170, 190, 23);
		
		//---- checking ----
		checking.setText("Open Checking Account");
		checking.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			FormDialogs c = new FormDialogs(frame);
			c.createCheckingAccount();
		});
		add(checking);
		checking.setBounds(15, 220, 190, 23);
		
		//---- view ----
		view.setText("View Account Info");
		add(view);
		view.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getRoot(this);
			CheckingAccount acc = new CheckingAccount((long) 244656234, 100.0, new Date(), null, 100.0, null);
			//JOptionPane.showMessageDialog(this, "<html> Account Number: 123 <br>Balance: $100 </br> <br>Open Date: 10/10/2021 </br> <br>Monthly Charge: $25 </br> <br>"
				//	+ "Debit Card Info: Visa | Last 4: 2312");
			new ViewDialogs(frame).viewAccountInfo(acc);
		});
		view.setBounds(235, 115, 190, 23);


		//---- transactions ----
		trans.setText("View Transactions");
		add(trans);
		trans.setBounds(235, 170, 190, 25);
		

		//---- delete ----
		delete.setText("Delete Account");
		add(delete);
		delete.setBounds(235, 220, 190, 23);
		
		//======== scrollPane1 ========
		accs.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		scrollPane1.setViewportView(accs);
		
		add(scrollPane1);
		scrollPane1.setBounds(10, 5, 400, 80);
	}

}
