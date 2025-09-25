package com.loginDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.StaffModel;
import com.mysql.cj.jdbc.MysqlDataSource;

public class StaffDAO {
	public static boolean postStaff(StaffModel staff) throws SQLException {
		
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		Connection con=sd.getConnection();
		
		String query="insert into staff(name,age,address,phone,email,department,education,username,password,start_time,end_time) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, staff.getName());
		pst.setInt(2, staff.getAge());
		pst.setString(3, staff.getAddress());
		pst.setString(4, staff.getPhone());
		pst.setString(5, staff.getEmail());
		pst.setString(6, staff.getDesignation());
		pst.setString(7, staff.getEducation());
		pst.setString(8, staff.getUsername());
		pst.setString(9, staff.getPassword());
		pst.setString(10, staff.getStart_time());
		pst.setString(11, staff.getEnd_time());
		
		int i=pst.executeUpdate();
		if(i!=0)return true;
		return false;
	}
	
	public static List<StaffModel> getStaff() throws SQLException{
		
		List<StaffModel> staff=new ArrayList<>();
		
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		Connection con=sd.getConnection();
		
		String query="select * from staff";
		PreparedStatement pst=con.prepareStatement(query);
		
		ResultSet rs=pst.executeQuery();
		
		while(rs.next()) {
			staff.add(new StaffModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getString(11),rs.getString(12),rs.getString(13)));
		}
		return staff;
	}
}
