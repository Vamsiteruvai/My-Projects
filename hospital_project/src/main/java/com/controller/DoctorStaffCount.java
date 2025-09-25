package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.connect.Connect;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/DoctorStaffCount")
public class DoctorStaffCount extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String type = request.getParameter("type");
		Map<String, Integer> data = new HashMap<>();

		try (Connection con = Connect.getConnection();
				Statement st = con.createStatement()) {

			String query = "";
			if ("doctor".equalsIgnoreCase(type)) {
				query = "SELECT department, COUNT(*) AS cnt FROM docter GROUP BY department";
			} else if ("staff".equalsIgnoreCase(type)) {
				query = "SELECT department, COUNT(*) AS cnt FROM staff GROUP BY department";
			}

			if (!query.isEmpty()) {
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					data.put(rs.getString("department"), rs.getInt("cnt"));
				}
			}

			String json = new Gson().toJson(data);
			response.getWriter().write(json);

		} catch (SQLException e) {
			response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}


}
