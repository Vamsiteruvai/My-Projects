package Bank;

import java.util.LinkedList;

public class ATM {
	
	static ATM atm;
    private LinkedList<User> users;

    public ATM() {
        users = new LinkedList<>();
        User user1 = new User("John Doe", "1234567890", "1001", "1234", 10000);
        users.add(user1);
        User user2 = new User("John", "1234567890", "1002", "4321",12345);
        users.add(user2);
        User user3 = new User(" Doe", "1234567890", "1003", "5678",1005);
        users.add(user3);
        User user4 = new User("Jina Doe", "1234567890", "1004", "8765",8700);
        users.add(user4);
        User user5 = new User("John Amcky", "1234567890", "1005", "1000",50000);
        users.add(user5);

    }
    public User validateUser(String accountNumber, String pin) {
        for (User user : users) {
            if (user.getAccountNumber().equals(accountNumber) && user.getPin().equals(pin)) {
                return user;
            }
        }
        return null;
    }
    
    public boolean setPin(String pin,User user) {
    	user.setPin(pin);
    	return true;
    }
    
    public boolean validatePinchange(String mobileNumber,User user) {
    	if(user.getMobileNumber().equals(mobileNumber)) {
    		return true;
    	}
    	return false;
    }
    
    public boolean depositFunds(User user, double amount) {
        double currentBalance = user.getAccountBalance();
        user.setAccountBalance(currentBalance + amount);
        return true;
    }
    
    public boolean withdrawFunds(User user, double amount) {
        double currentBalance = user.getAccountBalance();
        if (currentBalance >= amount) {
            user.setAccountBalance(currentBalance - amount);
            return true;
        } else {
            System.out.println("Insufficient funds");
            return false;
        }
    }

    public double checkBalance(User user) {
        return user.getAccountBalance();
    }
    
    public static void main(String[] args) {
    	 atm = new ATM();
    	 new ATMGUI();
    	 
	}
}

