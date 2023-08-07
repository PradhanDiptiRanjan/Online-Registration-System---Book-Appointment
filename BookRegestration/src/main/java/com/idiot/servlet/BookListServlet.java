package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/booklist")
public class BookListServlet extends HttpServlet {
	private static final String query = "SELECT ID,BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOK_DATA";
    @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   	
   	PrintWriter pw=res.getWriter();
   	res.setContentType("text/html");
   	
   	try {
   	Class.forName("com.mysql.cj.jdbc.Driver");
   	}catch(ClassNotFoundException cnf) {
   		cnf.printStackTrace();
   	}
   	try(Connection con=DriverManager.getConnection("jdbc:mysql:///book","root","dipti@123");
   		PreparedStatement ps=con.prepareStatement(query);){
   		ResultSet rs=ps.executeQuery();
   		pw.println("<table>");
   		pw.println("<tr>");
   		pw.println("<th>Book Id</td>");
   		pw.println("<th>Book Name</td>");
   		pw.println("<th>Book Edition</td>");
   		pw.println("<th>Book Price</td>");
   		pw.println("</tr>");
   		while(rs.next()) {
   			pw.println("<tr>");
   			pw.println("<td>"+rs.getInt(1)+"</td>");
   			pw.println("<td>"+rs.getString(2)+"</td>");
   			pw.println("<td>"+rs.getString(3)+"</td>");
   			pw.println("<td>"+rs.getFloat(4)+"</td>");
   	   		pw.println("</tr>");
   		}
   		pw.println("</table>");
   		
   		
   	}catch(SQLException se) {
   		se.printStackTrace();
   		pw.println("<h1>"+se.getMessage()+"</h2>");
   	}catch(Exception e) {
   		e.printStackTrace();
   		pw.println("<h1>"+e.getMessage()+"</h2>");
   	}
   	
   }
    @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   	doGet(req,res);
   	
   }

}
