package JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class book_car {
	public static void bookNow(JComboBox<String> names,JTextField start,JTextField returnDate,JTextField rentPday,JTextField pick,JTextField drop,int id) {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
        String user = "root";
        String dbpassword = "@@@Vamsi143";

        String name = (String)names.getSelectedItem();

        Date start1 = Date.valueOf(start.getText().trim());

        Date return1 = Date.valueOf(returnDate.getText().trim());

        Double rentPerDay = Double.parseDouble(rentPday.getText().trim());

        String pickL = pick.getText().trim();

        String dropL = drop.getText().trim();

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            Connection con = DriverManager.getConnection(url, user, dbpassword);

            // Prepare SQL insert
            String sql = "INSERT INTO rent (rent_start_date, rent_end_date, rent_price_per_day, pickupLocation, dropLocation, car_name,user_id) VALUES (?, ?, ?, ?, ?, ?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setDate(1, start1);
            pst.setDate(2, return1);
            pst.setDouble(3, rentPerDay);
            pst.setString(4, pickL);
            pst.setString(5, dropL);
            pst.setString(6, name);
            pst.setInt(7, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
            	JOptionPane.showMessageDialog(new JFrame(), "Registered Successfully");
            }

            // Close connections
            pst.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());;
        }
	}
}
