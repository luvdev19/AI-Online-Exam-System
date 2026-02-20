package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class ViolationServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req,HttpServletResponse res)
 throws ServletException,IOException{

  try{

   HttpSession session=req.getSession(false);
   String email=(String)session.getAttribute("email");
   String reason=req.getParameter("reason");

   Connection con=DBConnection.getConnection();

   PreparedStatement ps=con.prepareStatement(
    "insert into violations(student_email,reason) values(?,?)"
   );

   ps.setString(1,email);
   ps.setString(2,reason);

   ps.executeUpdate();

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}
