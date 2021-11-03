package bank.main;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bank.UI.LoginScreen;

public class Main {
	
	private static void init() {
	
		// Open DB Connection
		DatabaseManager.setConnection();
		
		JFrame frame = new JFrame("Cal Poly Banking");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {	
        		DatabaseManager.closeConnection();
            }
        });		
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				init();
			}
		});

	}

}
