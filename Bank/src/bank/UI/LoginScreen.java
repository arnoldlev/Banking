package bank.UI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.*;

import bank.customer.Customer;
import bank.main.MainScreen;

public class LoginScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel welcome;
	private JLabel loginText;
	private JLabel idText;
	private JLabel passText;
	private JLabel incorrectLogin;
	private JTextField ID;
	private JPasswordField password;
	private JButton login;
	
	public LoginScreen() {
		initComponents();
	}

	private void loginActionPerformed(ActionEvent e) {
		JPanel parent = (JPanel) getParent();
		CardLayout card = (CardLayout) parent.getLayout();
		
		try {
			Customer cust = new Customer(Long.valueOf(ID.getText()), String.valueOf(password.getPassword()));
			//Customer cust = new Customer(123456789, "corncake21");
			MainScreen main = new MainScreen(cust);
			parent.add(main, "Main");
			card.show(parent, "Main");
		} catch (Exception exc) {
			incorrectLogin.setVisible(true);
		}
		
	}

	private void initComponents() {
		welcome = new JLabel();
		loginText = new JLabel();
		idText = new JLabel();
		passText = new JLabel();
		incorrectLogin = new JLabel();
		ID = new JTextField();
		password = new JPasswordField();
		login = new JButton();
		
		setLayout(null);

		//---- Welcome Message ----
		welcome.setText("Welcome to Cal Poly Banking System.");
		welcome.setFont(new Font("Arial", Font.BOLD, 12));
		welcome.setVerticalAlignment(SwingConstants.TOP);
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		add(welcome);
		welcome.setBounds(35, 10, 425, 25);

		//---- Login Text Display ----
		loginText.setText("Please enter your login information below provided by the school administrator");
		loginText.setFont(new Font("Arial", Font.PLAIN, 12));
		add(loginText);
		loginText.setBounds(40, 30, 430, 25);

		//---- Bronco ID Text Display ----
		idText.setText("Bronco-ID:");
		idText.setFont(new Font("Arial", Font.PLAIN, 14));
		add(idText);
		idText.setBounds(45, 100, 75, idText.getPreferredSize().height);

		//---- Password Text Display ----
		passText.setText("Password:");
		passText.setFont(new Font("Arial", Font.PLAIN, 14));
		add(passText);
		passText.setBounds(45, 125, 70, 17);
		
		//---- ID and Password Fields ----
		add(ID);
		ID.setBounds(125, 100, 150, ID.getPreferredSize().height);
		add(password);
		password.setBounds(125, 125, 150, password.getPreferredSize().height);

		//---- Login Button ----
		login.setText("Login");
		login.setFont(new Font("Arial", Font.BOLD, 14));
		login.addActionListener(e -> loginActionPerformed(e));
		add(login);
		login.setBounds(125, 165, 75, 40);
		
		//--- Incorrect Password or ID Login  ---
		incorrectLogin.setText("(!) Error: The credentials you entered are incorrect.");
		incorrectLogin.setFont(new Font("Arial", Font.PLAIN, 12));
		incorrectLogin.setForeground(Color.RED);
		incorrectLogin.setBounds(125, 175, 300, 100);
		incorrectLogin.setVisible(false);
		add(incorrectLogin);

		
		Dimension preferredSize = new Dimension();
		for (int i = 0; i < getComponentCount(); i++) {
			Rectangle bounds = getComponent(i).getBounds();
			preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
			preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
		}
		Insets insets = getInsets();
		preferredSize.width += insets.right;
		preferredSize.height += insets.bottom;
		setMinimumSize(preferredSize);
		setPreferredSize(preferredSize);
		
		setSize(485, 445);
		
	}


}
