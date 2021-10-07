package bank.loans;

import java.util.Date;

public class PersonalLoan extends Loan {

	public PersonalLoan(double amount) {
		super(amount);
	}
	
	public PersonalLoan(int ID, double amount, double left, double interest, Date open, int term) {
		super(ID, amount, left, interest, open, term);
	}
	
	@Override
	public boolean makePayment(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
