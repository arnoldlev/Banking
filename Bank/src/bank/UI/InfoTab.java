package bank.UI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoTab extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private JLabel info;
	private JLabel name;
	private JLabel address;
	private JLabel phone;
	private JButton logout;
	
	
	public InfoTab() {
		setLayout(null);
	
		info = new JLabel();
		name = new JLabel();
		address = new JLabel();
		phone = new JLabel();
		logout = new JButton();

		//---- info ----
			info.setText("Your Information:");
			info.setFont(new Font("Arial", Font.BOLD, 12));
			add(info);
			info.setBounds(140, 5, 180, 25);

		//---- name ----
			name.setText("Name: ");
				add(name);
				name.setBounds(65, 35, 205, 20);

		//---- address ----
			address.setText("Address:");
				add(address);
				address.setBounds(65, 60, 200, 15);

		//---- phone ----
				phone.setText("Phone Number:");
				add(phone);
				phone.setBounds(65, 85, 270, 14);

		//---- logout ----
				logout.setText("Logout");
				add(logout);
				logout.setBounds(145, 145, 105, 45);
		
	}

}
