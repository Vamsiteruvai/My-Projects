package com.loginDAO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.DocterModel;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DocterDAO {
	public static boolean postDocter(DocterModel docter) throws SQLException, IOException {
		
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		Connection con=sd.getConnection();
		
		String query="INSERT INTO docter (first_name, last_name, phone, email, age, gender, department, website,address, salary, image, description, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, docter.getFirst_name());
		pst.setString(2, docter.getLast_name());
		pst.setLong(3, docter.getPhone());
		pst.setString(4, docter.getEmail());
		pst.setInt(5, docter.getAge());
		pst.setString(6, docter.getGender());
		pst.setString(7, docter.getDepartment());
		pst.setString(8, docter.getWebsite());
		pst.setString(9, docter.getAddress());
		pst.setDouble(10, docter.getSalary());
		pst.setString(11, docter.getImage());
		pst.setString(12, docter.getDescription());
		pst.setString(13, docter.getUsername());
		pst.setString(14, docter.getPassword());
		
		
		int i=pst.executeUpdate();
		
		if(i!=0) {
			return true;
		}
		return false;
	}
	
	public static List<DocterModel> getDocter() throws SQLException{
		
		List<DocterModel> data=new ArrayList<DocterModel>();
		
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		Connection con=sd.getConnection();
		
		Statement pst=con.createStatement();
		
		ResultSet i=pst.executeQuery("select first_name,last_name,phone,email,age,gender,department,website,address,salary,image,description,username,password from docter");
		
		while(i.next()) {
			data.add(new DocterModel(i.getString(1), i.getString(2), Long.parseLong(i.getString(3)), i.getString(4), i.getInt(5), i.getString(6), i.getString(7), i.getString(8), i.getString(9), i.getDouble(10), i.getString(11), i.getString(12), i.getString(13), i.getString(14)));
		}
		
		if(data!=null)
			return data;
		return null;
	}
}
