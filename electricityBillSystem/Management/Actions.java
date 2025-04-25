package Management;

import java.util.*;

import Customer.Customer;

import Bill.Bill;

public class Actions implements Manager{

	static Scanner sc=new Scanner(System.in);
	private Customer customer;
	private Bill bill;
	Random random=new Random();
	public void addCustomer() {

		System.out.println("Enter customer id");
		int customerId=sc.nextInt();
		System.out.println("Enter customer name");
		String customerName=sc.next();
		System.out.println("Enter customer address");
		String customerAddress=sc.next();
		System.out.println("Enter customer meterno");
		int meterNo=sc.nextInt();
		customer=new Customer(customerId, customerName, customerAddress, meterNo);
	}

	public void generateBill() {
		int Bill_id=1;
		System.out.print("Enter customerId : ");
		int id=sc.nextInt();
		if(customer.customerId!=id){
			System.out.println("Invalid customerId");
		}
		else{
			System.out.print("Enter units consumed : ");
			int units_used=sc.nextInt();
			double amount=calculateBill(units_used);
			bill=new Bill(Bill_id,id,units_used,amount);
			Bill_id++;
		}

	}

	public void viewCustomerDetails() {
		System.out.println(" Customer Id : "+customer.getCustomerId());
		System.out.println(" Customer Name : "+customer.getCustomerName());
		System.out.println(" Customer Address : "+customer.getCustomerAddress());
		System.out.println(" Customer MeterNo : "+customer.getCustomerMeterNo());
	}

	public void viewBillDetails() {
		bill.display();
	}

	public void updateCustomer() {
		System.out.println("Update customer name ");
		System.out.println("Update customer address ");
		System.out.println();
		int num=random.nextInt(8999);
		System.out.println("Your OTP is : '"+num+"'");
		System.out.print("Enter Your OTP here : ");
		int otp=sc.nextInt();
		if(otp==num){
			System.out.print("Enter only (1 or 2) : ");
			System.out.println();
			int option=sc.nextInt();
			switch(option){
			case 1:
				System.out.println("Enter NewName");
				String name=sc.next();
				customer.setCustomerName(name);
				System.out.println("-----------!Updated successfully with 			'"+name+"' !--------------");
				break;
			case 2:
				System.out.println("Enter NewAddress");
				String address=sc.next();
				customer.setCustomerAddress(address);
				System.out.println("-----------!Updated successfully with 	'"+address+"' !--------------");
			}
		}
		else{
			System.out.println("=========Invalid OTP=========");
		}
		
	}

	public void deleteCustomer() {
		customer=new Customer(0,null,null,0);
		System.out.println("---------------!Customer Deleted Successfully!----------");

	}
	double calculateBill(int units){
		double rate = 0;
        	if (units <= 100) {
            		rate = units * 1.5;
        	} else if (units <= 300) {
            		rate = 100 * 1.5 + (units - 100) * 2.0;
        	} else {
            		rate = 100 * 1.5 + 200 * 2.0 + (units - 300) * 3.0;
        	}
        	return rate;
	}
	

}
