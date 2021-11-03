package bank.UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bank.customer.Customer;

public class InfoTab extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel info;
	private JLabel name;
	private JLabel address;
	private JLabel phone;
	private JButton logout;
	
	public InfoTab(Customer customer) {
		setLayout(null);
	
		info = new JLabel();
		name = new JLabel();
		address = new JLabel();
		phone = new JLabel();
		logout = new JButton();

		info.setText("  Your Personal Information:");
		info.setFont(new Font("Courier New", Font.BOLD, 14));
		info.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
		add(info);
		info.setBounds(110, 5, 270, 25);

		name.setText("Name: " + customer.getFullName());
		add(name);
		name.setBounds(65, 35, 205, 20);

		address.setText("Address: " + customer.getFullAddress());
		add(address);
		address.setBounds(65, 60, 290, 15);

		phone.setText("Phone Number: " + customer.getPhoneNumber());
		add(phone);
		phone.setBounds(65, 85, 270, 14);

		logout.setText("Logout");
		add(logout);
		logout.setBounds(145, 145, 105, 45);
		
	}

}
