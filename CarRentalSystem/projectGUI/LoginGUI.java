package projectGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI{
	private JLabel headingLabel,subHeadingLabel,usernameLabel,passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton signInButton,signUpButton,forgotButton;
	private JFrame frame;

	public LoginGUI(){

		//frame properties
		frame = new JFrame("User Signin");
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		//container black in colur
		Container contentPane = frame.getContentPane();
		contentPane.setBackground(Color.BLACK);

		//main heading label
		headingLabel=new JLabel("Welcome To CAR RENTAL SYSTEM");
		headingLabel.setBounds(170, 60, 340, 30);
		headingLabel.setFont(new Font("Arial", Font.BOLD, 19));
		headingLabel.setForeground(Color.orange);
		frame.add(headingLabel);

		//sub heading label
		subHeadingLabel = new JLabel("Please Enter your Username and Password to validate ");
		subHeadingLabel.setBounds(180,90,350,30);
		subHeadingLabel.setForeground(Color.white);
		frame.add(subHeadingLabel);

		//Username label
		usernameLabel = new JLabel("Email :");
		usernameLabel.setBounds(150, 147, 80, 30);
		usernameLabel.setForeground(Color.white);
		frame.add(usernameLabel);

		//Username TextField
		usernameField = new JTextField();
		usernameField.setBounds(230, 147, 200, 30);
		usernameField.setBackground(Color.darkGray);
		usernameField.setForeground(Color.WHITE);
		usernameField.setCaretColor(Color.WHITE);
		frame.add(usernameField);

		//Password Label
		passwordLabel = new JLabel("Password :");
		passwordLabel.setBounds(150, 200, 80, 30);
		passwordLabel.setForeground(Color.white);
		frame.add(passwordLabel);

		//Password TextField
		passwordField = new JPasswordField();
		passwordField.setBounds(230, 200, 200, 30);
		passwordField.setBackground(Color.darkGray);
		passwordField.setForeground(Color.WHITE);
		passwordField.setCaretColor(Color.WHITE);
		passwordField.setFont(new Font("Arial", Font.BOLD, 14));
		passwordField.setEchoChar('*'); // Show '*' instead of dots
		frame.add(passwordField);

		//SignIn Button
		signInButton = new JButton("Login");
		signInButton.setBounds(360, 270, 100, 30);
		signInButton.setBackground(Color.green);
		signInButton.setFocusPainted(false);   // Removes focus highlight (dotted border)
		signInButton.setBorderPainted(false); 
		frame.add(signInButton);

		//SignUp Button
		signUpButton = new JButton("Signup");
		signUpButton.setBounds(200, 270, 100, 30);
		signUpButton.setFocusPainted(false);   // Removes focus highlight (dotted border)
		signUpButton.setBorderPainted(false);  // Removes button border outline
		signUpButton.setBackground(Color.red);
		frame.add(signUpButton);

		//Forgot Password
		forgotButton = new JButton("Forgot Password");
		forgotButton.setBounds(360, 320, 130, 30);
		forgotButton.setFocusPainted(false);   // Removes focus highlight (dotted border)
		forgotButton.setBorderPainted(false);  // Removes button border outline
		forgotButton.setBackground(Color.pink);
		frame.add(forgotButton);

		frame.setLayout(null);
		frame.setVisible(true);

		//action listners
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignupGUI();
				frame.dispose();
			}
		});

		//action listner for login
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=validate(usernameField, passwordField);
				String name=getUserName(usernameField, passwordField);
				char[] password=passwordField.getPassword();
				String pass=new String(password);
				if(usernameField.getText().equals("Admin") && pass.equals("Admin")) {
					new Admindashboard();
					frame.dispose();
				}
				else if(id!=0) {
					new Userdashboard(id,name);
					frame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(frame, "Invalid Inputs!");
					usernameField.setText("");
					passwordField.setText("");
				}
			}
		});
		
		forgotButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inpuString;
				String pin;
				if((inpuString=JOptionPane.showInputDialog(new JFrame(), "Enter Your Email Address"))!=null) {
					if(JDBC.forgotPassword.validatePass(inpuString)) {
						pin=JOptionPane.showInputDialog(new JFrame(), "Enter Your New Password");
						if(JDBC.forgotPassword.changePassword(pin, inpuString)) {
							JOptionPane.showMessageDialog(new JFrame(), "Successfully Changed");
						}
						else {
							JOptionPane.showMessageDialog(new JFrame(), "Password Updation Failed");
						}
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(), "Enter valid Email");
					}
				}
				
			}
		});
		
	}
	
	private static int validate(JTextField username,JTextField password) {
		//set connection
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String dbpassword = "@@@Vamsi143";

		try {
			//load jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			//make connection
			Connection con=DriverManager.getConnection(url,user,dbpassword);

			//sql query
			String sql="select email,password,user_id from user where email=? and password=?";

			PreparedStatement smt=con.prepareStatement(sql);

			smt.setString(1, username.getText().trim());
			smt.setString(2, password.getText().trim());

			ResultSet rs=smt.executeQuery();
			
			if(rs.next()) {
				//System.out.println(rs.getInt("user_id"));
				return rs.getInt("user_id");
			}

		} catch (Exception e) {

		}
		return 0;
	}
	
	private static String getUserName(JTextField username,JTextField password) {
		//set connection
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String dbpassword = "@@@Vamsi143";

		try {
			//load jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			//make connection
			Connection con=DriverManager.getConnection(url,user,dbpassword);

			//sql query
			String sql="select first_name from user where email=? and password=?";

			PreparedStatement smt=con.prepareStatement(sql);

			smt.setString(1, username.getText().trim());
			smt.setString(2, password.getText().trim());

			ResultSet rs=smt.executeQuery();
			
			if(rs.next()) {
				//System.out.println(rs.getInt("user_id"));
				return rs.getString("first_name");
			}

		} catch (Exception e) {

		}
		return null;
	}

}