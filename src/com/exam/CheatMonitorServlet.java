package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class CheatMonitorServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req,HttpServletResponse res)
 throws ServletException,IOException{

  res.setContentType("text/html");
  PrintWriter out=res.getWriter();

  try{

   Connection con=DBConnection.getConnection();

   Statement st=con.createStatement();

   ResultSet rs=st.executeQuery("select * from violations");

   out.println("<h2>Violation Log</h2>");
   out.println("<table border='1'>");
   out.println("<tr><th>Email</th><th>Reason</th><th>Time</th><th>Snapshot</th></tr>");

   while(rs.next()){

    out.println("<tr>");
    out.println("<td>"+rs.getString("student_email")+"</td>");
    out.println("<td>"+rs.getString("reason")+"</td>");
    out.println("<td>"+rs.getString("time")+"</td>");

    String img=rs.getString("image");

    if(img!=null){
     out.println("<td><img src='/ExamPortal/cheatImages/"+img+"' width='100'></td>");
    }else{
     out.println("<td>No Image</td>");
    }

    out.println("</tr>");

   }

   out.println("</table>");

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}
