package projectGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import JDBC.registerJDBC;

public class SignupGUI{
	private JLabel title,subTitle,firstnameLabel,passwordLabel,lastnameLabel,phoneLabel,
	emailLabel,addresslabel,adhaarLabel,designationLabel;
	
	public static JTextField firstname,lastname,password,email,phone,address,adhaar,designation;
	private JButton signInButton,signUpButton;
	private static JFrame frame;
	
	public SignupGUI(){
		
		//frame properties
		frame = new JFrame("User Signup");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		//main heading label
		title=new JLabel("Welcome To User Signup Page");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setBounds(200, 30, 300, 30);
		title.setForeground(Color.orange);
		frame.add(title);
		
		//sub heading label
		subTitle = new JLabel("------All the fields below are mandatory-------");
        subTitle.setBounds(200,60,350,30);
        subTitle.setForeground(Color.white);
        frame.add(subTitle);
        
		//firstname
        firstnameLabel = new JLabel("Firstname :");
        firstnameLabel.setBounds(100, 100, 120, 30);
        firstnameLabel.setForeground(Color.white);
        frame.add(firstnameLabel);
        
        //firstname text field
        firstname = new JTextField();
        firstname.setBounds(230, 100, 200, 30);
        firstname.setBackground(Color.darkGray);
        firstname.setForeground(Color.WHITE);
        firstname.setCaretColor(Color.WHITE);
        frame.add(firstname);
        
        //lastname
        lastnameLabel = new JLabel("Lastname :");
        lastnameLabel.setBounds(100, 140, 120, 30);
        lastnameLabel.setForeground(Color.white);
        frame.add(lastnameLabel);
        
        //lastname Textfield
        lastname = new JTextField();
        lastname.setBounds(230, 140, 200, 30);
        lastname.setBackground(Color.darkGray);
        lastname.setForeground(Color.WHITE);
        lastname.setCaretColor(Color.WHITE);
        frame.add(lastname);
        
        //phone
        phoneLabel = new JLabel("Phone +91:");
        phoneLabel.setBounds(100, 220, 120, 30);
        phoneLabel.setForeground(Color.white);
        frame.add(phoneLabel);
        
        //phone text field
        phone = new JTextField();
        phone.setBounds(230, 220, 200, 30);
        phone.setBackground(Color.darkGray);
        phone.setForeground(Color.WHITE);
        phone.setCaretColor(Color.WHITE);
        frame.add(phone);
        
        //address
        addresslabel = new JLabel("Address :");
        addresslabel.setBounds(100, 260, 120, 30);
        addresslabel.setForeground(Color.white);
        frame.add(addresslabel);
        
        //address text field
        address = new JTextField();
        address.setBounds(230, 260, 200, 30);
        address.setBackground(Color.darkGray);
        address.setForeground(Color.WHITE);
        address.setCaretColor(Color.WHITE);
        frame.add(address);
        
        //email
        emailLabel = new JLabel("Email :");
        emailLabel.setBounds(100, 300, 120, 30);
        emailLabel.setForeground(Color.white);
        frame.add(emailLabel);
        
        //email text field
        email = new JTextField();
        email.setBounds(230, 300, 200, 30);
        email.setBackground(Color.darkGray);
        email.setForeground(Color.WHITE);
        email.setCaretColor(Color.WHITE);
        frame.add(email);
        
        //password
        passwordLabel = new JLabel("Password :");
        passwordLabel.setBounds(100, 340, 120, 30);
        passwordLabel.setForeground(Color.white);
        frame.add(passwordLabel);
        
        //password text field
        password = new JTextField();
        password.setBounds(230, 340, 200, 30);
        password.setBackground(Color.darkGray);
        password.setForeground(Color.WHITE);
        password.setCaretColor(Color.WHITE);
        frame.add(password);
        
        //adhaar
        adhaarLabel = new JLabel("Adhaar No:");
        adhaarLabel.setBounds(100, 380, 120, 30);
        adhaarLabel.setForeground(Color.white);
        frame.add(adhaarLabel);
        
        //adhaar text field
        adhaar = new JTextField();
        adhaar.setBounds(230, 380, 200, 30);
        adhaar.setBackground(Color.darkGray);
        adhaar.setForeground(Color.WHITE);
        adhaar.setCaretColor(Color.WHITE);
        frame.add(adhaar);
        
        //designation
        designationLabel = new JLabel("Designation :");
        designationLabel.setBounds(100, 420, 120, 30);
        designationLabel.setForeground(Color.white);
        frame.add(designationLabel);
        
        //designation textField
        designation = new JTextField();
        designation.setBounds(230, 420, 200, 30);
        designation.setBackground(Color.darkGray);
        designation.setForeground(Color.WHITE);
        designation.setCaretColor(Color.WHITE);
        frame.add(designation);
        
		//SignIn Button
        signInButton = new JButton("Register");
        signInButton.setBounds(360, 460, 100, 30);
        signInButton.setBackground(Color.green);
        signInButton.setFocusPainted(false);   // Removes focus highlight (dotted border)
        signInButton.setBorderPainted(false);  // Removes button border outline
        frame.add(signInButton);
        
		//SignUp Button
		signUpButton = new JButton("Back To Signin");
        signUpButton.setBounds(200, 460, 120, 30);
        signUpButton.setFocusPainted(false);   // Removes focus highlight (dotted border)
        signUpButton.setBorderPainted(false);  // Removes button border outline
        signUpButton.setBackground(Color.red);
        frame.add(signUpButton);
        
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(Color.BLACK);
        
        frame.setLayout(null);
        frame.setVisible(true);
        
        //action listners
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginGUI();
                frame.dispose();
            }
        });
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(errorHandle()) {
                	return;
                }
                else {
                	new registerJDBC(firstname,lastname,phone,address,email,password,adhaar,designation);
                    firstname.setText("");
                    lastname.setText("");
                    phone.setText("");
                    address.setText("");
                    email.setText("");
                    password.setText("");
                    adhaar.setText("");
                    designation.setText("");
                    new LoginGUI();
                    frame.dispose();
                }
            }
        });
	}
	private static boolean errorHandle() {
		boolean errorCheck=false;

		if (!email.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			JOptionPane.showMessageDialog(frame, "Invalid email format!");
	        return true;
	    }
		else if (password.getText().length() < 8 || !password.getText().matches(".*[A-Z].*") || !password.getText().matches(".*[a-z].*") || !password.getText().matches(".*[0-9].*")) {
	        JOptionPane.showMessageDialog(frame,
	            "Password must be at least 8 characters and include uppercase, lowercase, and number.");
	        return true;
	    }
		else if (!phone.getText().matches("^[6-9][0-9]{9}$")) {
	        JOptionPane.showMessageDialog(frame, "Phone number must be 10 digits and start with 6/7/8/9.");
	        return true;
	    }
		else if (!adhaar.getText().matches("^[2-9][0-9]{11}$")) {
	        JOptionPane.showMessageDialog(frame, "Aadhaar number must be 12 digits and not start with 0 or 1.");
	        return true;
	    }
		else if (firstname.getText().equals("")) {
	        JOptionPane.showMessageDialog(frame, "Firstname should not be Empty.");
	        return true;
	    }
		else if (address.getText().equals("")) {
	        JOptionPane.showMessageDialog(frame, "Address should not be Empty");
	        return true;
	    }
		else if (designation.getText().equals("")) {
	        JOptionPane.showMessageDialog(frame, "Designation should not be Empty");
	        return true;
	    }
		return false;
	}
}