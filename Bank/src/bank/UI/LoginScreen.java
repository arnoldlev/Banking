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
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel incorrectLogin;
	private JTextField textField1;
	private JPasswordField passwordField1;
	private JButton button1;
	
	public LoginScreen() {
		initComponents();
	}

	private void button1ActionPerformed(ActionEvent e) {
		JPanel parent = (JPanel) getParent();
		CardLayout card = (CardLayout) parent.getLayout();
		
		try {
			Customer cust = new Customer(1232);
			parent.add(new MainScreen(), "Main");
			card.show(parent, "Main");
		} catch (Exception edc) {
			incorrectLogin.setVisible(true);
		}
		
	}

	private void initComponents() {
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		incorrectLogin = new JLabel();
		textField1 = new JTextField();
		passwordField1 = new JPasswordField();
		button1 = new JButton();
		
		setLayout(null);

		//---- label1 ----
		label1.setText("Welcome to Cal Poly Banking System.");
		label1.setFont(new Font("Arial", Font.BOLD, 12));
		label1.setVerticalAlignment(SwingConstants.TOP);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label1);
		label1.setBounds(35, 10, 425, 25);

		//---- label2 ----
		label2.setText("Please enter your login information below provided by the school administrator");
		label2.setFont(new Font("Arial", Font.PLAIN, 12));
		add(label2);
		label2.setBounds(40, 30, 430, 25);

		//---- label3 ----
		label3.setText("Bronco-ID:");
		label3.setFont(new Font("Arial", Font.PLAIN, 14));
		add(label3);
		label3.setBounds(45, 100, 75, label3.getPreferredSize().height);

		//---- label4 ----
		label4.setText("Password:");
		label4.setFont(new Font("Arial", Font.PLAIN, 14));
		add(label4);
		label4.setBounds(45, 125, 70, 17);
		add(textField1);
		textField1.setBounds(125, 100, 150, textField1.getPreferredSize().height);
		add(passwordField1);
		passwordField1.setBounds(125, 125, 150, passwordField1.getPreferredSize().height);

		//---- button1 ----
		button1.setText("Login");
		button1.setFont(new Font("Arial", Font.BOLD, 14));
		button1.addActionListener(e -> button1ActionPerformed(e));
		add(button1);
		button1.setBounds(125, 165, 75, 40);
		
		//--- incorrect password ---
		incorrectLogin.setText("Error: You entered incorrect password");
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
