package Bill;

public class Bill {
	private int billId;
	private int customerId;
	private int unitsConsumed;
	private double amount;
	
	public Bill(int billId, int customerId, int unitsConsumed, double amount) {
		super();
		this.billId = billId;
		this.customerId = customerId;
		this.unitsConsumed = unitsConsumed;
		this.amount = amount;
	}
	
	public void display() {
        	System.out.println("Bill ID: " + billId);
        	System.out.println("Customer ID: " + customerId);
        	System.out.println("Units Consumed: " + unitsConsumed);
        	System.out.println("Amount: Rs. " + amount);
    	}
}
