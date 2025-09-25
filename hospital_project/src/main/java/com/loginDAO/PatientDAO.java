package com.loginDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.PatientModel;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.protocol.a.authentication.AuthenticationWebAuthnClient;

public class PatientDAO {
	public static boolean postPatient(PatientModel patient) throws SQLException {
		
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		Connection con=sd.getConnection();
		
		String query="insert into patient(fullname,email,phone,preferred_date,reason,doctor_name,age,address) values(?,?,?,?,?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, patient.getFullname());
		pst.setString(2, patient.getEmail());
		pst.setString(3, patient.getPhone());
		pst.setDate(4, patient.getPreferred_date());
		pst.setString(5, patient.getReason());
		pst.setString(6, patient.getDoctor_name());
		pst.setInt(7, patient.getAge());
		pst.setString(8, patient.getAddress()); 
		
		int i=pst.executeUpdate();
		
		if(i!=0)return true;
		return false;
	}
	
	public static List<PatientModel> getPatients() throws SQLException {
		
		List<PatientModel> patients=new ArrayList<>();
		
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		Connection con=sd.getConnection();
		
		String query="select * from patient";
		PreparedStatement pst=con.prepareStatement(query);
		
		ResultSet rs=pst.executeQuery();
		
		while(rs.next()) {
			patients.add(new PatientModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9)));
		}
		
		return patients;
	}
}
