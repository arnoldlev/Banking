package bank.main;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;

import bank.UI.AccountTab;
import bank.UI.CardTab;
import bank.UI.InfoTab;
import bank.UI.LoansTab;
import bank.customer.Customer;

public class MainScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane pane;
	private JPanel mainScreen;
	private JPanel accountScreen;
	private JPanel cardScreen;
	private JPanel loansScreen;
	
	public MainScreen(Customer customer) {
		setLayout(new BorderLayout());
		
		pane = new JTabbedPane();
		pane.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
		
		mainScreen = new InfoTab(customer);
		accountScreen = new AccountTab(customer);
		cardScreen = new CardTab(customer);
		loansScreen = new LoansTab(customer);
		
		pane.add("Main Menu", mainScreen);
		pane.add("Accounts", accountScreen);
		pane.add("Cards", cardScreen);
		pane.add("Loans", loansScreen);
		
		add(pane, BorderLayout.CENTER);
		
	}
	
	
	

}
