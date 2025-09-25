package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.loginDAO.DocterDAO;
import com.model.DocterModel;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("userpassword");
		
		PrintWriter out=response.getWriter();
		
		try {
			List<DocterModel> data=DocterDAO.getDocter();
			
			for(DocterModel i:data) {
				if(i.getUsername().equals(username)&&i.getPassword().equals(password)) {
					HttpSession session = request.getSession();
		            session.setAttribute("user", i.getEmail());
		            session.setMaxInactiveInterval(30*60);
		            
					RequestDispatcher rd=request.getRequestDispatcher("./admin.html");
					rd.forward(request, response);
					break;
				}
				else {
					out.print("<h1 style='color:white';'font-width:large'>Login failed!...........</h1>");
					RequestDispatcher rd=request.getRequestDispatcher("./signIn_Up.html");
					rd.include(request, response);
					break;
				}
			}
		} catch (SQLException e) {
			out.print("SQL EXCEPTION : "+e.getMessage());
		}
	
	}

}
