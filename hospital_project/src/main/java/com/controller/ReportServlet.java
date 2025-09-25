package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.loginDAO.ReportDAO;
import com.loginDAO.StaffDAO;
import com.model.ReportModel;
import com.model.StaffModel;


@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
		try {
			List<ReportModel> reports=ReportDAO.getReports();
			String json = new Gson().toJson(reports);
	        response.getWriter().write(json);
	        
		} catch (SQLException e) {
			PrintWriter out=response.getWriter();
			out.print(e.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int patient_id=Integer.parseInt(request.getParameter("pID"));
		String name=request.getParameter("pname");
		String phone=request.getParameter("pphone");
		String report=request.getParameter("preport");
		
		ReportModel reports=new ReportModel(0, patient_id, name, phone, report, null);
		
		try {
			if(ReportDAO.postReport(reports)) {
				RequestDispatcher rd=request.getRequestDispatcher("./success.html");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("./error.html");
				rd.forward(request, response);
			}
			
		} catch (SQLException e) {
			PrintWriter out=response.getWriter();
			out.print("SQL EXCEPTION : "+e.getMessage());
		}
	}

}
