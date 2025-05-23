package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class CarPrice {
    // Get price based on seating capacity for a given car model
    public static double getSeatingNumber(String brand) {
        String url = "jdbc:mysql://localhost:3306/carrentalsystem";
        String user = "root";
        String password = "@@@Vamsi143";
        double price = 0.0;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(url, user, password);

            // Prepare and execute query
            PreparedStatement p = connection.prepareStatement("SELECT seating_capacity FROM car WHERE brand=?");
            p.setString(1, brand);
            ResultSet rSet = p.executeQuery();

            // Determine price based on seating capacity
            if (rSet.next()) {
                int seating = rSet.getInt("seating_capacity");
                if (seating == 5) {
                    price = 1500;
                } else if (seating == 7) {
                    price = 2000;
                }
            }

            // Close resources
            rSet.close();
            p.close();
            connection.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage());
        }

        return price;
    }
	
	// Get mode based on brand for a given car
    public static String[] getModels(String brand) {
    String url = "jdbc:mysql://localhost:3306/carrentalsystem";
    String user = "root";
    String password = "@@@Vamsi143";
    String[] models = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);

        // Step 1: Count how many models exist for the brand
        PreparedStatement countStmt = connection.prepareStatement("SELECT COUNT(*) FROM car WHERE brand=?");
        countStmt.setString(1, brand);
        ResultSet countResult = countStmt.executeQuery();

        int count = 0;
        if (countResult.next()) {
            count = countResult.getInt(1);
        }

        countResult.close();
        countStmt.close();

        if (count == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "No models found for this brand.");
            connection.close();
            return null;
        }

        // Step 2: Retrieve the models
        models = new String[count];
        PreparedStatement modelStmt = connection.prepareStatement("SELECT model FROM car WHERE brand=?");
        modelStmt.setString(1, brand);
        ResultSet modelResult = modelStmt.executeQuery();

        int index = 0;
        while (modelResult.next() && index < count) {
            models[index++] = modelResult.getString("model");
        }

        modelResult.close();
        modelStmt.close();
        connection.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage());
    }

    return models;
}

}
