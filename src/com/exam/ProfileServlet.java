package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class ProfileServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req, HttpServletResponse res)
 throws ServletException, IOException {

  try{

   HttpSession session=req.getSession(false);
   String email=(String)session.getAttribute("email");

   Connection con=DBConnection.getConnection();

   // GET NAME
   PreparedStatement ps1=con.prepareStatement(
    "select name from students where email=?"
   );

   ps1.setString(1,email);
   ResultSet rs1=ps1.executeQuery();

   String name="";
   if(rs1.next()){
    name=rs1.getString("name");
   }

   // GET RESULT
   PreparedStatement ps2=con.prepareStatement(
    "select score,status from results where student_email=?"
   );

   ps2.setString(1,email);
   ResultSet rs2=ps2.executeQuery();

   String status="Not Attempted";
   String score="-";

   if(rs2.next()){
    status=rs2.getString("status");
    score=String.valueOf(rs2.getInt("score"));
   }

   res.sendRedirect(
    "pages/profile.html?name="+name+
    "&email="+email+
    "&status="+status+
    "&score="+score
   );

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}
