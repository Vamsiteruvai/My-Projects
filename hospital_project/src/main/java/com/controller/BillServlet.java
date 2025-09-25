package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.loginDAO.BillDAO;
import com.model.BillModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		try {
			List<BillModel> bill=BillDAO.getBill();
			String json = new Gson().toJson(bill);
	        response.getWriter().write(json);
		} catch (SQLException e) {
			PrintWriter out=response.getWriter();
			out.print(e.getMessage());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("pname");
		String phone=request.getParameter("pphone");
		String email=request.getParameter("pemail");
		String address=request.getParameter("address");
		String disease=request.getParameter("disease");
		int patient_id=Integer.parseInt(request.getParameter("pID"));
		double total_amount=Double.parseDouble(request.getParameter("amount"));
		
		BillModel bill=new BillModel(1, name, phone, email, address, disease, total_amount, null,patient_id);
		
		try {
			if(BillDAO.postBill(bill)) {
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
