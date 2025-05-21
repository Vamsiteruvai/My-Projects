package projectGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ProfileGUI extends JFrame{
	private JLabel titile,fistnameJLabel,lastnameLabel,phoneLabel,emaiJLabel,adhaarJLabel,addressLabel,designationLabel,
	firstname,lastname,phone,email,adhaar,address,designation;
	private static JDBC.Profile profile=new JDBC.Profile();
	
	public ProfileGUI(int id) {
		
		setTitle("Profile");
		setSize(650,500);
		setLocationRelativeTo(null);
		setLayout(null);

		//title
		titile=new JLabel("Profile");
		titile.setBounds(280, 30, 300, 30);
		titile.setForeground(new Color(138, 43, 226));
		titile.setFont(new Font("Segoe UI", Font.BOLD, 20));
		add(titile);

		//first name
		fistnameJLabel=new JLabel("Firstname");
		fistnameJLabel.setBounds(175, 90, 100, 30);
		fistnameJLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		add(fistnameJLabel);

		//first name
		firstname=new JLabel();
		firstname.setBounds(320, 90, 200, 30);
		firstname.setForeground(new Color(220, 20, 60));
		firstname.setFont(new Font("Verdana", Font.BOLD, 14));
		firstname.setText(JDBC.Profile.getProfile(id).firstName);
		add(firstname);

		//last name
		lastnameLabel=new JLabel("Lastname");
		lastnameLabel.setBounds(175, 130, 100, 30);
		lastnameLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		add(lastnameLabel);

		//last name
		lastname=new JLabel();
		lastname.setBounds(320, 130, 200, 30);
		lastname.setForeground(new Color(220, 20, 60));
		lastname.setFont(new Font("Verdana", Font.BOLD, 14));
		lastname.setText(JDBC.Profile.getProfile(id).lastName);
		add(lastname);

		//phone
		phoneLabel=new JLabel("Phone");
		phoneLabel.setBounds(175, 170, 100, 30);
		phoneLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		add(phoneLabel);

		//phone
		phone=new JLabel();
		phone.setBounds(320, 170, 200, 30);
		phone.setForeground(new Color(220, 20, 60));
		phone.setFont(new Font("Verdana", Font.BOLD, 14));
		phone.setText(JDBC.Profile.getProfile(id).phone);
		add(phone);

		//email
		emaiJLabel=new JLabel("Email");
		emaiJLabel.setBounds(175, 210, 100, 30);
		emaiJLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		add(emaiJLabel);

		//email
		email=new JLabel();
		email.setBounds(320, 210, 300, 30);
		email.setForeground(new Color(220, 20, 60));
		email.setFont(new Font("Verdana", Font.BOLD, 14));
		email.setText(JDBC.Profile.getProfile(id).email);
		add(email);

		//adhaar
		adhaarJLabel=new JLabel("Adhaar");
		adhaarJLabel.setBounds(175, 250, 100, 30);
		adhaarJLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		add(adhaarJLabel);

		//adhaar
		adhaar=new JLabel();
		adhaar.setBounds(320, 250, 200, 30);
		adhaar.setForeground(new Color(220, 20, 60));
		adhaar.setFont(new Font("Verdana", Font.BOLD, 14));
		adhaar.setText(JDBC.Profile.getProfile(id).adhaar);
		add(adhaar);

		//address
		addressLabel=new JLabel("Address");
		addressLabel.setBounds(175, 290, 100, 30);
		addressLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		add(addressLabel);

		//address
		address=new JLabel();
		address.setBounds(320, 290, 300, 30);
		address.setForeground(new Color(220, 20, 60));
		address.setFont(new Font("Verdana", Font.BOLD, 14));
		address.setText(JDBC.Profile.getProfile(id).address);
		add(address);

		//designation
		designationLabel=new JLabel("Designation");
		designationLabel.setBounds(175, 330, 150, 30);
		designationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		add(designationLabel);

		//designation
		designation=new JLabel();
		designation.setBounds(320, 330, 300, 30);
		designation.setForeground(new Color(220, 20, 60));
		designation.setFont(new Font("Verdana", Font.BOLD, 14));
		designation.setText(JDBC.Profile.getProfile(id).designation);
		add(designation);

		setVisible(true);
	}
}
