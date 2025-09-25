package com.connect;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Connect {
	public static Connection getConnection() throws SQLException {
		MysqlDataSource sd=new MysqlDataSource();
		sd.setUrl("jdbc:mysql://localhost:3306/hospital");
		sd.setUser("root");
		sd.setPassword("@@@Vamsi143");
		
		return sd.getConnection();
	}
}
