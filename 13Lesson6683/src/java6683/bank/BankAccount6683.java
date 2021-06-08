package java6683.bank;

public class BankAccount6683 {
	private String bankID;
	private Double balance;

	public BankAccount6683(String bankID, Double balance) {
		this.bankID = bankID;
		this.balance = balance;
	}

	public String getBankID() {
		return bankID;
	}

	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
