package com.loginDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.BillModel;
import com.mysql.cj.jdbc.MysqlDataSource;

public class BillDAO {
	public static boolean postBill(BillModel bill) throws SQLException {
		
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		Connection con=sd.getConnection();
		
		String query="insert into bill(name,phone,email,address,disease,total_amount) values(?,?,?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, bill.getName());
		pst.setString(2, bill.getPhone());
		pst.setString(3, bill.getEmail());
		pst.setString(4, bill.getAddress());
		pst.setString(5, bill.getDisease());
		pst.setDouble(6, bill.getTotal_amount());
		
		int i=pst.executeUpdate();
		if(i!=0)return true;
		return false;
	}
	
	public static List<BillModel> getBill() throws SQLException{
		
		List<BillModel> bill=new ArrayList<>();
		
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		Connection con=sd.getConnection();
		
		String query="select * from bill";
		PreparedStatement pst=con.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		
		while(rs.next()) {
			bill.add(new BillModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDate(8),rs.getInt(9)));
		}
		
		return bill;
	}
}
