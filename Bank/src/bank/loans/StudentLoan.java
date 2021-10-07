package bank.loans;

import java.util.Date;

public class StudentLoan extends Loan {

	public StudentLoan(double amount) {
		super(amount);
	}
	
	public StudentLoan(int ID, double amount, double left, double interest, Date open, int term) {
		super(ID, amount, left, interest, open, term);
	}

	@Override
	public boolean makePayment(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
