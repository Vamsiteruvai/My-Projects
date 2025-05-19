package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DisplayCount {
	//display car count
	public static int carCount() {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String password = "@@@Vamsi143";

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement p = connection.prepareStatement("SELECT count(car_id) FROM car");
			Class.forName("com.mysql.cj.jdbc.Driver");
			ResultSet rSet = p.executeQuery();

			if (rSet.next()) {
				// email exists
				return rSet.getInt(1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
		}
		return 0;
	}

	//display user count
	public static int userCount() {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String password = "@@@Vamsi143";

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement p = connection.prepareStatement("SELECT count(user_id) FROM user");
			Class.forName("com.mysql.cj.jdbc.Driver");
			ResultSet rSet = p.executeQuery();

			if (rSet.next()) {
				// email exists
				return rSet.getInt(1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
		}
		return 0;
	}

	//display user count
	public static int bookCount() {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String password = "@@@Vamsi143";

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement p = connection.prepareStatement("SELECT count(rent_id) FROM rent");
			Class.forName("com.mysql.cj.jdbc.Driver");
			ResultSet rSet = p.executeQuery();

			if (rSet.next()) {
				// email exists
				return rSet.getInt(1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
		}
		return 0;
	}
}
