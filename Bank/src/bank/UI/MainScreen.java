package bank.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;

public class MainScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane pane;
	private JPanel mainScreen;
	private JPanel accountScreen;
	private JLabel welcome;
	
	public MainScreen() {
		setLayout(new BorderLayout());
		
		pane = new JTabbedPane();
		pane.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
		
		mainScreen = new JPanel();
		welcome = new JLabel("Welcome <user>. Here you can create accounts, loans and cards.");
		welcome.setFont(new Font("Arial", Font.PLAIN, 12));
		mainScreen.add(welcome);
		
		accountScreen = new AccountScreen();
		
		pane.add("Main Menu", mainScreen);
		pane.add("Accounts", accountScreen);
		
		add(pane, BorderLayout.CENTER);
		
	}
	
	
	private class AccountScreen extends JPanel {
		
		private static final long serialVersionUID = 1L;

		public AccountScreen() {
			setLayout(new FlowLayout());
		}
		
	}
	
	
	

}
