package bank.UI.Dialogs;

import java.awt.Font;

import javax.swing.*;

import bank.accounts.CheckingAccount;

public class FormDialogs {
	
	private JDialog dialog;
	
	public FormDialogs(JFrame parent) {
		dialog = new JDialog(parent, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		dialog.setLayout(null);
	}
	
	public CheckingAccount createCheckingAccount() {
		dialog.setTitle("Create a new Checking Account");
		
		JLabel startText = new JLabel();
		JLabel maxText = new JLabel();
		JLabel atmText = new JLabel();
		JLabel min = new JLabel();
		JSpinner starting = new JSpinner();
		JSpinner maxTrans = new JSpinner();
		JSpinner maxTrans2 = new JSpinner();
		JButton create = new JButton();
		JButton cancel = new JButton();
		
		//---- label1 ----
		min.setText("Minimum Value: $100");
		min.setFont(min.getFont().deriveFont(min.getFont().getStyle() | Font.ITALIC));
		dialog.add(min);
		min.setBounds(180, 15, 215, 25);
		
		//---- startText ----
		startText.setText("Enter the starting amount:");
		startText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(startText);
		startText.setBounds(15, 30, 210, 25);

		//---- maxText ----
		maxText.setText("Enter the maximum debit card transaction amount:");
		maxText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(maxText);
		maxText.setBounds(15, 70, 310, 25);

		//---- atmText ----
		atmText.setText("Enter the maximum ATM Withdrawal Limit:");
		atmText.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialog.add(atmText);
		atmText.setBounds(15, 135, 280, 25);

		//---- starting ----
		starting.setModel(new SpinnerNumberModel(100.0, 100.0, null, 1.0));
		dialog.add(starting);
		starting.setBounds(185, 35, 90, starting.getPreferredSize().height);

		//---- maxTrans ----
		maxTrans.setModel(new SpinnerNumberModel(100.0, 0.0, null, 1.0));
		dialog.add(maxTrans);
		maxTrans.setBounds(15, 95, 90, 20);

		//---- maxTrans2 ----
		maxTrans2.setModel(new SpinnerNumberModel(100.0, 0.0, null, 1.0));
		dialog.add(maxTrans2);
		maxTrans2.setBounds(15, 165, 90, 20);

		//---- create ----
		create.setText("Create Account");
		dialog.add(create);
		create.setBounds(80, 205, 165, create.getPreferredSize().height);

		//---- cancel ----
		cancel.setText("Cancel");
		cancel.addActionListener(e -> {
			dialog.setVisible(false);
		});
		dialog.add(cancel);
		cancel.setBounds(80, 240, 165, 23);
		
		dialog.setVisible(true);
		
		
		return null;
	}

}
