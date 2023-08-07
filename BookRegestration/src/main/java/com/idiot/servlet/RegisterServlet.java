package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")

public class RegisterServlet extends HttpServlet {
	private static final String query = "INSERT INTO BOOK_DATA(BookName,BookEdition,BookPrice) VALUES(?,?,?)";
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
    	PrintWriter pw=res.getWriter();
    	res.setContentType("text/html");
    	String BookName=req.getParameter("BookName");
    	String BookEdition=req.getParameter("BookEdition");
    	Float BookPrice=Float.parseFloat(req.getParameter("BookPrice"));
    	try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	}catch(ClassNotFoundException cnf) {
    		cnf.printStackTrace();
    	}
    	try(Connection con=DriverManager.getConnection("jdbc:mysql:///book","root","dipti@123");
    		PreparedStatement ps=con.prepareStatement(query);){
    		ps.setString(1,BookName);
    		ps.setString(2,BookEdition);
    		ps.setFloat(3,BookPrice);
    		int count=ps.executeUpdate();
    		if(count==1) {
    			pw.println("<h2>Record is registred sucessfully</h2>");
    		}else {
    			pw.println("<h2>Record is not registred sucessfully</h2>");
    		}
    		
    	}catch(SQLException se) {
    		se.printStackTrace();
    		pw.println("<h1>"+se.getMessage()+"</h2>");
    	}catch(Exception e) {
    		e.printStackTrace();
    		pw.println("<h1>"+e.getMessage()+"</h2>");
    	}
    	pw.println("<a href='booklist'>Book List</a>");
    	
    }
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doGet(req,res);
    	
    }
}
