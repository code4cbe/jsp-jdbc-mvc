package com.sheeba.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myConn = datasource.getConnection();
			myStmt =  myConn.createStatement();
			myRs = myStmt.executeQuery("select * from student");
			out.println("<h3>Database Successfully Connected !</h3><hr/>");
			while(myRs.next()){
				out.println(myRs.getString("first_name")+" "+myRs.getString("last_name")+"<br/>");
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	
	}

}
