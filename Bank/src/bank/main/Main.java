package bank.main;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bank.UI.LoginScreen;

public class Main {
	
	private static void init() {
		// Create the main frame
		JFrame frame = new JFrame("Cal Poly Banking");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);

		// Create the main panel and create a Card Layout
		JPanel main = new JPanel();
		CardLayout card = new CardLayout();
		main.setLayout(card);
		
		// Initialize the first screen
		JPanel login = new LoginScreen();
		main.add(login, "Login");

		// Add main panel to main frame and show
		frame.add(main);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				init();
			}
		});

	}

}
