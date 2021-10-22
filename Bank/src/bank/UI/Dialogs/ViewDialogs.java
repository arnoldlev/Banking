package bank.UI.Dialogs;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bank.accounts.Account;
import bank.accounts.CheckingAccount;

public class ViewDialogs {
	
	private JDialog dialog;
	
	public ViewDialogs(JFrame parent) {
		dialog = new JDialog(parent, "", true);
		dialog.setSize(400, 330);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(parent);
		dialog.setLayout(new FlowLayout());
	}
	
	public void viewAccountInfo(Account acc) {
		dialog.setTitle("Account Information");
		
		JLabel info = new JLabel();
		info.setText("<html> "
				+ "Account Number: " + acc.getAccountID() + ""
				+ "<br>Open Date: " + acc.getOpenDate() + "</br>"
				+ "<br>Balance: $" + acc.getBalance() + "</br>");
		info.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		dialog.add(info);
		
		if (acc instanceof CheckingAccount) {
			CheckingAccount a = (CheckingAccount) acc;
			info.setText(info.getText() + "<br>Monthly Charge: " + a.getMonthlyCharge() + "</br>");
		}
		
		dialog.setVisible(true);
		
	}

}
