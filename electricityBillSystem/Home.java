
import java.util.Scanner;
import Management.Actions;
import Management.Manager;

class home{
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Manager manager=new Actions();
        	int choice;

        do {
            System.out.println("\n=== Electricity Bill Management System ===");
            System.out.println("1. Add Customer");
            System.out.println("2. Generate Bill");
            System.out.println("3. View Customer Details");
            System.out.println("4. View Bill Details");
            System.out.println("5. Update Customer");
            System.out.println("6. Delete Customer");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 :
                	manager.addCustomer();
                	break;
                case 2 :
			System.out.println();
                        manager.generateBill();
                	break;
                case 3 :
			System.out.println();
                	manager.viewCustomerDetails();
                	break;
                case 4 :
			System.out.println();
                	manager.viewBillDetails();
                	break;
                case 5 :
			System.out.println();
                	manager.updateCustomer();
                	break;
                case 6 :
			System.out.println();
                	manager.deleteCustomer();
                	break;
                case 7 :
			System.out.println();
                	System.out.println("Exiting the system. Goodbye!");
                	break;
                default :
			System.out.println();
                	System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);
	}
}
