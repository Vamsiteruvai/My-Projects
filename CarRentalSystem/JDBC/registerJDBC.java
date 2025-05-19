package JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.print.attribute.standard.PrinterStateReasons;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import projectGUI.SignupGUI;

public class registerJDBC {
	//frame object creation
	private static JFrame frame=new JFrame("Authenticate");
	
    public registerJDBC(JTextField firstname,JTextField lastname,JTextField phone,JTextField address,JTextField email,JTextField password,JTextField adhaar,JTextField designation) {
    	
        String url = "jdbc:mysql://localhost:3306/carrentalsystem";
        String user = "root";
        String dbpassword = "@@@Vamsi143";

        String firstName = firstname.getText().trim();

        String lastName = lastname.getText().trim();

        long phone1 = Long.parseLong(phone.getText().trim());

        String email1 = email.getText().trim();

        long adhaar1 = Long.parseLong(adhaar.getText().trim());

        String address1 = address.getText().trim();

        String designation1 = designation.getText().trim();

        String pwd = password.getText().trim();

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            Connection con = DriverManager.getConnection(url, user, dbpassword);

            // Prepare SQL insert
            String sql = "INSERT INTO user (first_name, last_name, phone, email, adhaar, address, designation, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setLong(3, phone1);
            pst.setString(4, email1);
            pst.setLong(5, adhaar1);
            pst.setString(6, address1);
            pst.setString(7, designation1);
            pst.setString(8, pwd);

            int rows = pst.executeUpdate();

            if (rows > 0) {
            	JOptionPane.showMessageDialog(frame, "Registered Successfully");
            	return;
            }

            // Close connections
            pst.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());;
        }
    }
}
