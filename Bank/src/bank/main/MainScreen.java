package bank.main;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;

import bank.UI.AccountTab;
import bank.UI.CardTab;
import bank.UI.InfoTab;

public class MainScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane pane;
	private JPanel mainScreen;
	private JPanel accountScreen;
	private JPanel cardScreen;
	
	public MainScreen() {
		setLayout(new BorderLayout());
		
		pane = new JTabbedPane();
		pane.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
		
		mainScreen = new InfoTab();
		accountScreen = new AccountTab();
		cardScreen = new CardTab();
		
		pane.add("Main Menu", mainScreen);
		pane.add("Accounts", accountScreen);
		pane.add("Cards", cardScreen);
		
		add(pane, BorderLayout.CENTER);
		
	}
	
	
	

}