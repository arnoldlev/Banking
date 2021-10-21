package bank.UI;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.*;

public class CardTab extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrollPane2;
	private JTable cards;
	private JButton payment;
	private JButton cardInfo;
	private JButton deleteCard;
	private JCheckBox onlyCredit;
	private JCheckBox onlyDebit;
	private JButton cardTrans;
	
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
		onlyCredit = new JCheckBox();
		onlyDebit = new JCheckBox();
		cardTrans = new JButton();
		
		//======== scrollPane2 ========
		cards.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane2.setViewportView(cards);
		add(scrollPane2);
		scrollPane2.setBounds(30, 10, 360, 75);

		//---- payment ----
		payment.setText("Make Payment");
		add(payment);
		payment.setBounds(30, 235, 140, payment.getPreferredSize().height);

		//---- cardInfo ----
		cardInfo.setText("View Card Info");
		add(cardInfo);
		cardInfo.setBounds(30, 145, 140, cardInfo.getPreferredSize().height);

		//---- deleteCard ----
		deleteCard.setText("Delete Card");
		add(deleteCard);
		deleteCard.setBounds(30, 190, 140, 23);

		//---- onlyCredit ----
		onlyCredit.setText("Credit Cards Only");
		add(onlyCredit);
		onlyCredit.setBounds(215, 150, 200, 23);

		//---- onlyDebit ----
		onlyDebit.setText("Debit Cards Only");
		add(onlyDebit);
		onlyDebit.setBounds(215, 165, 200, 23);

		//---- cardTrans ----
		cardTrans.setText("Add Transaction");
		add(cardTrans);
		cardTrans.setBounds(30, 100, 140, 23);
		
	}
	

}
