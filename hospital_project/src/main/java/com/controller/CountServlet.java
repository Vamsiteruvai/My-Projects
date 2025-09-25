package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.connect.Connect;
import com.google.gson.Gson;

@WebServlet("/CountServlet")
public class CountServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Integer> data = new HashMap<>();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try (Connection con = Connect.getConnection();
				Statement st1 = con.createStatement();
				Statement st2 = con.createStatement();
				Statement st3 = con.createStatement()) {

			ResultSet rs1 = st1.executeQuery("SELECT COUNT(*) FROM docter");
			if (rs1.next()) {
				data.put("doctor", rs1.getInt(1));
			}

			ResultSet rs2 = st2.executeQuery("SELECT COUNT(*) FROM patient");
			if (rs2.next()) {
				data.put("patient", rs2.getInt(1));
			}

			ResultSet rs3 = st3.executeQuery("SELECT COUNT(*) FROM staff");
			if (rs3.next()) {
				data.put("staff", rs3.getInt(1));
			}

			String json = new Gson().toJson(data);
			response.getWriter().write(json);

		} catch (SQLException e) {
			PrintWriter out = response.getWriter();
			out.print(e.getMessage());
		}
	}


}
