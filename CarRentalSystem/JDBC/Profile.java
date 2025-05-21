package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;

import projectClasses.User;

public class Profile {
	public static User getProfile(int id) {
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
			String sql="select first_name,last_name,phone,email,adhaar,address,designation from user where user_id=?";

			PreparedStatement smt=con.prepareStatement(sql);

			smt.setInt(1, id);

			ResultSet rs=smt.executeQuery();

			if(rs.next()) {
				projectClasses.User profile=new projectClasses.User();
				profile.firstName=rs.getString("first_name");
				profile.lastName=rs.getString("last_name");
				profile.phone=rs.getString("phone");
				profile.email=rs.getString("email");
				profile.adhaar=rs.getString("adhaar");
				profile.address=rs.getString("address");
				profile.designation=rs.getString("designation");
				return profile;
			}

		} catch (Exception e) {

		}
		return null;
	}
}
