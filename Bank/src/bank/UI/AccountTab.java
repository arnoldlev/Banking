package bank.UI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

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
		
		//---- view ----
		view.setText("View Account Info");
		add(view);
		view.setBounds(235, 115, 165, view.getPreferredSize().height);

		//---- checking ----
		checking.setText("Open Checking Account");
		add(checking);
		checking.setBounds(15, 220, 190, 23);

		//---- savings ----
		savings.setText("Open Savings Account");
		add(savings);
		savings.setBounds(15, 115, 190, 23);

		//---- cd ----
		cd.setText("Open CD Account");
		add(cd);
		cd.setBounds(15, 170, 190, 25);
		
		//---- delete ----
		delete.setText("Delete Account");
		add(delete);
		delete.setBounds(235, 220, 170, 23);

		//---- statements ----
		trans.setText("View Transactions");
		add(trans);
		trans.setBounds(235, 170, 170, 25);

		//======== scrollPane1 ========
		accs.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		scrollPane1.setViewportView(accs);
		
		add(scrollPane1);
		scrollPane1.setBounds(10, 5, 400, 80);
	}

}
