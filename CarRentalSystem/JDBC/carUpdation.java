package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import projectClasses.Car;

public class carUpdation extends JFrame{
    
		public static Car getUpdationData(JTextField id) {
			Car car = null;
			
			String url = "jdbc:mysql://localhost:3306/carrentalsystem";
	        String user = "root";
	        String dbpassword = "@@@Vamsi143";
	        try{
	        	Connection con=DriverManager.getConnection(url,user,dbpassword);
	            String sql = "SELECT * FROM car WHERE car_id = ?";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setInt(1, Integer.parseInt(id.getText().trim()));
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                car = new Car();
	                car.id = rs.getInt("car_id");
	                car.brand = rs.getString("brand");
	                car.price = rs.getDouble("price");
	                car.colour = rs.getString("colour");
	                car.model = rs.getString("model");
	                car.mileage = rs.getString("mileage");
	                car.seating_capacity = rs.getInt("seating_capacity");
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
	        }
	        return car;
		}
}
