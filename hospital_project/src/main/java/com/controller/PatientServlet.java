package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.model.PatientModel;
import com.google.gson.Gson;
import com.loginDAO.PatientDAO;


@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		
		List<PatientModel> list=null;
		
		try {
			if(!(list=PatientDAO.getPatients()).isEmpty()) {
				String json=new Gson().toJson(list);
				response.getWriter().write(json);
			}
		} catch (SQLException e) {
			PrintWriter out=response.getWriter();
			out.print(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fullname=request.getParameter("pname");
		String email=request.getParameter("pemail");
		String phone=request.getParameter("phone");
		int age=Integer.parseInt(request.getParameter("age"));
		String address=request.getParameter("address");
		Date date=Date.valueOf(request.getParameter("date"));
		String reason=request.getParameter("message");
		String docter=request.getParameter("docter");
		
		PatientModel patient=new PatientModel(1, fullname, email, phone, date, reason,docter, age, address);
		
		try {
			if(PatientDAO.postPatient(patient)) {
				RequestDispatcher rd=request.getRequestDispatcher("./success.html");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("./error.html");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			PrintWriter out=response.getWriter();
			out.print(e.getMessage());
		}
		
		
	}

}
