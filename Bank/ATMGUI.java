package Bank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ATMGUI {
    private JFrame frame;
   
    private JLabel title,title2,accountLabel,pinLabel;
    
    private JTextField accountField,pinField;
    
    private JButton loginButton,depositButton,withdrawButton,balanceButton,pinButton,logoutButton;

    private User user;
    public ATMGUI() {
        frame = new JFrame("ATM Machine");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        title = new JLabel("Welcome to CVCORP ATM");
        title.setBounds(220, 10, 200, 30);
        frame.add(title);
        
        title2 = new JLabel("Please Enter your Account Number and Pin to validate ");
        title2.setBounds(150,30,350,30);
        frame.add(title2);
        
        accountLabel = new JLabel("Account No:");
        accountLabel.setBounds(150, 80, 80, 30);
        frame.add(accountLabel);
        
        accountField = new JTextField();
        accountField.setBounds(230, 80, 200, 30);
        frame.add(accountField);
          
        pinLabel = new JLabel("PIN :");
        pinLabel.setBounds(150, 120, 80, 30);
        frame.add(pinLabel);
        
        pinField = new JTextField();
        pinField.setBounds(230, 120, 200, 30);
        frame.add(pinField);
        
        loginButton = new JButton("Login");
        loginButton.setBounds(330, 160, 100, 30);
        frame.add(loginButton);
        
        depositButton = new JButton("Deposit");
        depositButton.setBounds(30, 250, 100, 30); 
        depositButton.setEnabled(false);
        frame.add(depositButton);
        
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(450, 250, 100, 30);
        withdrawButton.setEnabled(false);
        frame.add(withdrawButton);
        
        balanceButton = new JButton("Balance");
        balanceButton.setBounds(30, 330, 100, 30);
        balanceButton.setEnabled(false);
        frame.add(balanceButton);
        
        pinButton = new JButton("Change PIN");
        pinButton.setBounds(450, 330, 100, 30);
        pinButton.setEnabled(false);
        frame.add(pinButton);
        
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(450, 400, 100, 30);
        logoutButton.setEnabled(false);
        frame.add(logoutButton);
        
        frame.setLayout(null);
        frame.setVisible(true);
                
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountField.getText();
                String pin = pinField.getText();
                
                if((user = ATM.atm.validateUser(accountNumber, pin))!=null) {
                    JOptionPane.showMessageDialog(frame,"Login successful");
                	System.out.println("Login Successfull..");
                	withdrawButton.setEnabled(true);
                	depositButton.setEnabled(true);
                	balanceButton.setEnabled(true);
                	pinButton.setEnabled(true);
                	logoutButton.setEnabled(true);
                	
                } else {
                    JOptionPane.showMessageDialog(frame,"Invalid account number or PIN");
                	System.out.println("Invalid account number or PIN");
                	accountField.setText("");
                	pinField.setText("");
                }
            }
        });
        
        withdrawButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String inputDialog;
        		double withamount=0;;
            	try{
            		if((inputDialog=JOptionPane.showInputDialog(frame,"Enter The Amount to withdraw.."))!=null)
            		 withamount = Double.parseDouble(inputDialog);
            	}
            	catch(NumberFormatException x) {
            		int attempts=0;
        		while(attempts<=3) {
        			JOptionPane.showMessageDialog(frame,"Please Enter the Amount only in Numbers format.");
        			String input = JOptionPane.showInputDialog(frame,"Enter The Amount to withdraw..");
        	        if(input ==null) {}
        			Pattern pattern = Pattern.compile("^[1-9][0-9]{0,6}$|^10000000$");
        	        Matcher matcher = pattern.matcher(input);
        	        if (matcher.matches()) {
        	        	withamount = Double.parseDouble(input);
        	        		break;
        	        } else
        	        	attempts++;
        		}
        		if(attempts>=3) {
        			JOptionPane.showMessageDialog(frame,"Maximum number of attempts reached, please try again later.");
        			return;
        		}
        		}
            	catch(NullPointerException y) {}
            	 if(ATM.atm.withdrawFunds(user, withamount ))
            		 JOptionPane.showMessageDialog(frame,"Withdrawal Successful");                 	 
            	 else
            		 JOptionPane.showMessageDialog(frame,"Withdrawal Failed, Insufficient Funds..");        	 
        	}
        });
        
        balanceButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(frame,"Available balance is: "+ATM.atm.checkBalance(user));	
        	}
        });
        
        depositButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		double depamount=0;
        		String inputDialog;
            	try {
            		if((inputDialog=JOptionPane.showInputDialog(frame,"Enter The Amount to deposit.."))!=null)
            		depamount = Double.parseDouble(inputDialog);
            	}catch(NumberFormatException x) {
            		int attempts=0;
            		while(attempts<=3) {
            			JOptionPane.showMessageDialog(frame,"Please Enter the Amount only in Numbers format.");
            			String input = JOptionPane.showInputDialog(frame,"Enter The Amount to deposit..");
            	        if(input ==null) {}
            			Pattern pattern = Pattern.compile("^[1-9][0-9]{0,6}$|^10000000$");
            	        Matcher matcher = pattern.matcher(input);
            	        if (matcher.matches()) {
            	        	depamount = Double.parseDouble(input);
            	        		break;
            	        } else
            	        	attempts++;
            		}
            		if(attempts>=3) {
            			JOptionPane.showMessageDialog(frame,"Maximum number of attempts reached, please try again later.");
            			return;
            		}
            	}
            	System.out.println(depamount);
            	if(ATM.atm.depositFunds(user, depamount))
           		 	JOptionPane.showMessageDialog(frame,"Deposition Successful");                 	 
            	else
            		JOptionPane.showMessageDialog(frame,"Deposition Failed");
        	}
        });
        
        pinButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String inputDialog;
        		String pin;
        		try {
        			if((inputDialog=JOptionPane.showInputDialog(frame,"Enter your mobile Number for verification"))!=null)
        			if(ATM.atm.validatePinchange(inputDialog, user)) {
        				pin= JOptionPane.showInputDialog(frame,"Enter your New Pin");
        				if(ATM.atm.setPin(pin,user))
        					JOptionPane.showMessageDialog(frame,"Pin Updated Successfully");
        				else
        					JOptionPane.showMessageDialog(frame,  "Pin updation Failed");
        			}
        		}catch(Exception y) {}
        		
        	}
        });
        
        logoutButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		withdrawButton.setEnabled(false);
            	depositButton.setEnabled(false);
            	balanceButton.setEnabled(false);
            	pinButton.setEnabled(false);
            	accountField.setText("");
            	pinField.setText("");
        	}
        });
    }
}