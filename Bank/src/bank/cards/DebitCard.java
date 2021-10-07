package bank.cards;

public class DebitCard extends Card {
	
	private double maxTransaction;
	private double atmLimit;

	public DebitCard(String name) {
		super(name);
	}
	
	public DebitCard(String name, double maxTrans, double limit) {
		super(name);
		setMaxTransaction(maxTrans);
		setAtmLimit(limit);
	}

	/**
	 * @return the maxTransaction
	 */
	public double getMaxTransaction() {
		return maxTransaction;
	}

	/**
	 * @param maxTransaction the maxTransaction to set
	 */
	public void setMaxTransaction(double maxTransaction) {
		this.maxTransaction = maxTransaction;
	}

	/**
	 * @return the atmLimit
	 */
	public double getAtmLimit() {
		return atmLimit;
	}

	/**
	 * @param atmLimit the atmLimit to set
	 */
	public void setAtmLimit(double atmLimit) {
		this.atmLimit = atmLimit;
	}

}
