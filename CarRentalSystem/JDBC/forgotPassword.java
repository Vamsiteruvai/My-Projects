package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class forgotPassword {

	//password validation
	public static boolean validatePass(String email) {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String password = "@@@Vamsi143";

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement p = connection.prepareStatement("SELECT email FROM user WHERE email = ?");
			Class.forName("com.mysql.cj.jdbc.Driver");
			p.setString(1, email);
			ResultSet rSet = p.executeQuery();

			if (rSet.next()) {
				// email exists
				return true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
		}
		return false;
	}
	//change password in db
	public static boolean changePassword(String pass, String email) {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String password = "@@@Vamsi143";

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement p = connection.prepareStatement("UPDATE user SET password = ? WHERE email = ?");
			Class.forName("com.mysql.cj.jdbc.Driver");
			p.setString(1, pass);
			p.setString(2, email);
			int rows = p.executeUpdate();

			if (rows > 0) {
				return true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
		}
		return false;
	}
}
