package Customer;

public class Customer {
	public int customerId;
	private String customerName;
	private String customerAddress;
	private int customerMeterNo;
	
	public Customer() {
		//default for invocation
	}
	public Customer(int customerId, String customerName, String customerAddress, int customerMeterNo) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerMeterNo = customerMeterNo;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public int getCustomerMeterNo() {
		return customerMeterNo;
	}

	public void setCustomerMeterNo(int customerMeterNo) {
		this.customerMeterNo = customerMeterNo;
	}

}
