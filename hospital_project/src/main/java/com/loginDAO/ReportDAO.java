package com.loginDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.ReportModel;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ReportDAO {
	public static boolean postReport(ReportModel report) throws SQLException {
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		Connection con=sd.getConnection();
		
		String query="insert into report(patient_id,name,phone,report) values(?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, report.getPatient_id());
		pst.setString(2, report.getName());
		pst.setString(3, report.getPhone());
		pst.setString(4, report.getReport());
			
		int i=pst.executeUpdate();
		if(i!=0)return true;
		return false;
	}
	
public static List<ReportModel> getReports() throws SQLException{
		
		List<ReportModel> report=new ArrayList<>();
		
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		Connection con=sd.getConnection();
		
		String query="select * from report";
		PreparedStatement pst=con.prepareStatement(query);
		
		ResultSet rs=pst.executeQuery();
		
		while(rs.next()) {
			report.add(new ReportModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getTimestamp(6)));
		}
		return report;
	}
}
