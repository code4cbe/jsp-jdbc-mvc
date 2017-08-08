package com.sheeba.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		try{
			studentDbUtil = new StudentDbUtil(datasource);
		}catch(Exception exc){
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getParameter("command");
		
		if (command==null) command = "list";
		
		switch(command){
			case "list":
				listStudents(request, response);
				break;
			case "add":
				addStudent(request, response);
				break;
			case "update":
				updateStudent(request, response);
				break;
			case "delete":
				deleteStudent(request, response);
				break;
			case "load":
				getStudent(request, response);
				break;
			default:
				listStudents(request, response);
				break;
		}
	}

	private void getStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = studentDbUtil.getStudent(id);
		request.setAttribute("student", student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> students = studentDbUtil.getStudents();
		request.setAttribute("student_list", students);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view-student-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(firstName,lastName,email);
		studentDbUtil.addStudent(student);
		listStudents(request, response);
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		studentDbUtil.deleteStudent(id);
		listStudents(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(id,firstName,lastName,email);
		studentDbUtil.updateStudent(student);		
		listStudents(request, response);
	}

}
