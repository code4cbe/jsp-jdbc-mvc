package com.sheeba.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource datasource;
	
	public StudentDbUtil(DataSource theDataSource){
		datasource = theDataSource;
	}
	
	public List<Student> getStudents(){
		
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		
		try {
			myConn = datasource.getConnection();
			mySt = myConn.createStatement();
			myRs = mySt.executeQuery("select * from student");
			while(myRs.next()){
				Student tempStudent = new Student(myRs.getInt("id"),myRs.getString("first_name"),myRs.getString("last_name"),myRs.getString("email"));
				students.add(tempStudent);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myConn,mySt,myRs);
		}
		return students;
	}
	
	public void addStudent(Student student){
		Connection myConn = null;
		PreparedStatement mySt = null;
		ResultSet myRs = null;
		
		String firstName = student.getFirstName();
		String lastName = student.getLastName();
		String email = student.getEmail();

		try {
			myConn = datasource.getConnection();
			mySt = myConn.prepareStatement("insert into student(first_name,last_name,email) values (?,?,?)");
			mySt.setString(1, firstName);
			mySt.setString(2, lastName);
			mySt.setString(3, email);
			boolean result = mySt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myConn,mySt,myRs);
		}
	}
	
	public void close(Connection c, Statement s, ResultSet r){
		try{
			if (r!=null) r.close();
			if (s!=null) s.close();
			if (c!=null) c.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void updateStudent(Student student) {
		Connection myConn = null;
		PreparedStatement mySt = null;
		ResultSet myRs = null;
		
		int id = student.getId();
		String firstName = student.getFirstName();
		String lastName = student.getLastName();
		String email = student.getEmail();

		try {
			myConn = datasource.getConnection();
			mySt = myConn.prepareStatement("update student set first_name=?,last_name=?,email=? where id=?");
			mySt.setString(1, firstName);
			mySt.setString(2, lastName);
			mySt.setString(3, email);
			mySt.setInt(4, id);
			int result = mySt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myConn,mySt,myRs);
		}
		
	}

	public void deleteStudent(int id) {
		Connection myConn = null;
		PreparedStatement mySt = null;
		ResultSet myRs = null;

		try {
			myConn = datasource.getConnection();
			mySt = myConn.prepareStatement("delete from student where id = ?");
			mySt.setInt(1, id);
			boolean result = mySt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myConn,mySt,myRs);
		}
		
	}

	public Student getStudent(int id) {
		Student student = null;
		
		Connection myConn = null;
		PreparedStatement mySt = null;
		ResultSet myRs = null;
		
		try {
			myConn = datasource.getConnection();
			mySt = myConn.prepareStatement("select * from student where id=?");
			mySt.setInt(1, id);
			myRs = mySt.executeQuery();
			if (myRs.next()){
				student = new Student(myRs.getInt("id"),myRs.getString("first_name"),myRs.getString("last_name"),myRs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myConn,mySt,myRs);
		}
		return student;
	}

}
