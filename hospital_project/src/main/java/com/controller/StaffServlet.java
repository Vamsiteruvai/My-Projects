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
import com.loginDAO.BillDAO;
import com.loginDAO.StaffDAO;
import com.model.BillModel;
import com.model.StaffModel;


@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
		try {
			List<StaffModel> staff=StaffDAO.getStaff();
			String json = new Gson().toJson(staff);
	        response.getWriter().write(json);
	        
		} catch (SQLException e) {
			PrintWriter out=response.getWriter();
			out.print(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("sname");
		int age=Integer.parseInt(request.getParameter("sage"));
		String address=request.getParameter("saddress");
		String phone=request.getParameter("sphone");
		String email=request.getParameter("semail");
		String designation=request.getParameter("sdesignation");
		String education=request.getParameter("seductaion");
		String username=request.getParameter("uname");
		String password=request.getParameter("upass");
		String cpassword=request.getParameter("confirm");
		String start_time=request.getParameter("stime");
		String end_time=request.getParameter("etime");

		StaffModel staff=new StaffModel(1,name, age, address, phone, email, designation, education, null, username, password,start_time,end_time);

		try {
			if(password.equals(cpassword)) {
				if(StaffDAO.postStaff(staff)) {
					RequestDispatcher rd=request.getRequestDispatcher("./success.html");
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("./error.html");
					rd.forward(request, response);
				}
			}
			else {
				PrintWriter out=response.getWriter();
				out.print("<h1 style='color:red';'font-weight:large'>Password Not matched</h1>");
			}
		} catch (SQLException e) {
			PrintWriter out=response.getWriter();
			out.print("SQL EXCEPTION : "+e.getMessage());
		}
	}

}
