package bank.UI;

import javax.swing.*;

public class LoansTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane3;
	private JTable loanOpen;
	private JButton open;
	private JButton makePayment;
	private JButton viewTrans;
	private JButton viewInfo;
	
	public LoansTab() {
		setLayout(null);
		
		String[] rows = { "ID", "Loaned Amount", "Balance Left"};
		String[][] data = { 
				{ "123", "$1000", "$100"},
				{ "234", "$12", "$0"}
		};

		scrollPane3 = new JScrollPane();
		loanOpen = new JTable(data, rows);
		open = new JButton();
		makePayment = new JButton();
		viewTrans = new JButton();
		viewInfo = new JButton();
		
		//======== scrollPane3 ========
		loanOpen.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane3.setViewportView(loanOpen);
		add(scrollPane3);
		scrollPane3.setBounds(10, 5, 400, 80);
		
		//---- button1 ----
		open.setText("Open new Loan");
		add(open);
		open.setBounds(15, 115, 190, 23);


		//---- makePayment ----
		makePayment.setText("Make Payment");
		add(makePayment);
		makePayment.setBounds(15, 170, 190, 23);

		//---- button3 ----
		viewTrans.setText("View Transactions");
		add(viewTrans);
		viewTrans.setBounds(235, 115, 190, 23);
		
		//---- Info ---
		viewInfo.setText("View Info");
		add(viewInfo);
		viewInfo.setBounds(235, 170, 190, 25);
		
	}

}
