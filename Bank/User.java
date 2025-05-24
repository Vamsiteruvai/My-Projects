package Bank;

public class User {
	private String name;
    private String mobileNumber;
    private String accountNumber;
    private String pin;
    private double accountBalance;

    public User(String name, String mobileNumber, String accountNumber, String pin, double accountBalance) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.accountBalance=accountBalance;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setPin(String pin) {
    	this.pin=pin;
    }
    public void setMobileNumber(String number) {
    	this.mobileNumber=number;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }
    
    public double getAccountBalance() {
    	return accountBalance;
    }

	public void setAccountBalance(double accountBalance) {
		this.accountBalance=accountBalance;
	}
}
