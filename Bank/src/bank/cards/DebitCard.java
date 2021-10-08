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
	 * @return Maximum transaction amount allowed to make
	 */
	public double getMaxTransaction() {
		return maxTransaction;
	}

	/**
	 * @param maxTransaction Maximum transaction amount allowed to make
	 * @return True if parameter is positive
	 * @apiNote Customer can choose to have a 0 max!
	 */
	public boolean setMaxTransaction(double maxTransaction) {
		if (maxTransaction < 0) {
			return false;
		}
		this.maxTransaction = maxTransaction;
		return true;
	}

	/**
	 * @return The maximum amount allowed to withdraw from ATMs
	 */
	public double getAtmLimit() {
		return atmLimit;
	}

	/**
	 * @param atmLimit The maximum amount allowed to withdraw from ATMs
	 * @return True if parameter is positive
	 * @apiNote Customer can choose to have a 0 max!
	 */
	public boolean setAtmLimit(double atmLimit) {
		if (atmLimit < 0) {
			return false;
		}
		this.atmLimit = atmLimit;
		return true;
	}

}
