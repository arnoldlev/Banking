package bank.UI;

import javax.swing.*;

public class CardTab extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrollPane2;
	private JTable cards;
	private JButton payment;
	private JButton cardInfo;
	private JButton deleteCard;
	private JButton cardTrans;
	private JButton openCard;
	private JButton viewTrans;
	
	public CardTab() {
		initComponents();
	}
	
	private void initComponents() {
		setLayout(null);
		
		String[] rows = { "ID", "Type", "Last 4"};
		String[][] data = { 
				{ "123", "Credit", "4231"},
				{ "234", "Debit", "1923"}
		};
		
		scrollPane2 = new JScrollPane();
		cards = new JTable(data, rows);
		payment = new JButton();
		cardInfo = new JButton();
		deleteCard = new JButton();
		cardTrans = new JButton();
		openCard = new JButton();
		viewTrans = new JButton();
		
		//======== scrollPane2 ========
		cards.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane2.setViewportView(cards);
		add(scrollPane2);
		scrollPane2.setBounds(10, 5, 400, 80);

		//---- payment ----
		payment.setText("Make Payment");
		add(payment);
		payment.setBounds(15, 115, 190, 23);

		//---- cardInfo ----
		cardInfo.setText("View Card Info");
		add(cardInfo);
		cardInfo.setBounds(15, 170, 190, 23);

		//---- deleteCard ----
		deleteCard.setText("Delete Card");
		add(deleteCard);
		deleteCard.setBounds(15, 220, 190, 23);
		
		//---- cardTrans ----
		cardTrans.setText("Add Transaction");
		add(cardTrans);
		cardTrans.setBounds(235, 115, 190, 23);

		//---- openCard ----
		openCard.setText("Open Credit Card");
		add(openCard);
		openCard.setBounds(235, 170, 190, 25);

		//---- viewTrans ----
		viewTrans.setText("View Transactions");
		add(viewTrans);
		viewTrans.setBounds(235, 220, 190, 23);
		
	}
	

}
