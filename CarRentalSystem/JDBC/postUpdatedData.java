package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import projectClasses.Car;

public class postUpdatedData {
	public static void postData(int id,String brand,Double price,String color,String model,String mileage,int seat) {
		Connection con = null;
	    PreparedStatement stmt = null;

	    try {
	    	Car car=new Car();
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalsystem", "root", "@@@Vamsi143");

	        String sql;
	        if (!(id==0)) {
	            sql = "UPDATE car SET brand = ?,price=?,colour=?,model=?,mileage=?,seating_capacity=? WHERE car_id = ?";
	            stmt = con.prepareStatement(sql);
	            stmt.setString(1, brand);
	            stmt.setDouble(2, price);
	            stmt.setString(3, color);
	            stmt.setString(4, model);
	            stmt.setString(5, mileage);
	            stmt.setInt(6, seat);
	            stmt.setInt(7, id);
	        }
	        else {
	            JOptionPane.showMessageDialog(new JFrame(), "Please fill at least one field to update.");
	        }

	        int rowsUpdated = stmt.executeUpdate();
	        
	        if (rowsUpdated > 0) {
	            JOptionPane.showMessageDialog(new JFrame(), "Record updated successfully!");
	        } else {
	            JOptionPane.showMessageDialog(new JFrame(), "No record found with the given ID.");
	        }

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(new JFrame(), "Error updating record");
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}
