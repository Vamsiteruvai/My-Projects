package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.loginDAO.DocterDAO;
import com.model.DocterModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@WebServlet("/DocterServlet")
@MultipartConfig(maxFileSize = 16177215)
public class DocterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uploadPath = "C:/Users/teruv/eclipse-workspace/hospital_project/src/main/webapp/assets/doctorsProfiles";

		Part part = request.getPart("image");
		String fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName();
		
		
		InputStream input = part.getInputStream();
		String p=uploadPath + File.separator + fileName;
	    FileOutputStream fos = new FileOutputStream(p);

		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1) {
			fos.write(buffer, 0, bytesRead);
		}

		// Collect form data
		String firs_tname = request.getParameter("firstname");
		String last_name = request.getParameter("lastname");
		String ph_no = request.getParameter("phone");
		String email = request.getParameter("gmail");
		String p_age = request.getParameter("age");
		String p_gender = request.getParameter("gender");
		String speciality = request.getParameter("department");
		String web = request.getParameter("website");
		String p_address = request.getParameter("address");
		String p_salary = request.getParameter("salary");
		String desct = request.getParameter("description");
		String username = request.getParameter("uname");
		String password = request.getParameter("upass");
		String cpassword = request.getParameter("uconfirmpass");

		if (password.equals(cpassword)) {
			DocterModel docter = new DocterModel(
					firs_tname, last_name, Long.parseLong(ph_no), email,
					Integer.parseInt(p_age), p_gender, speciality, web,
					p_address, Double.parseDouble(p_salary),
					fileName, desct, username, password
					);

			try {
				if (DocterDAO.postDocter(docter)) {
					request.getRequestDispatcher("./success.html").forward(request, response);
				} else {
					request.getRequestDispatcher("./error.html").forward(request, response);
				}
			} catch (SQLException e) {
				response.getWriter().println("Database Exception => " + e.getMessage());
			}
		}
	}


	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

		try {
			List<DocterModel> docter=DocterDAO.getDocter();
			String json = new Gson().toJson(docter);
	        res.getWriter().write(json);
		} catch (SQLException e) {
			PrintWriter out=res.getWriter();
			out.print(e.getMessage());
		}
	}

}
