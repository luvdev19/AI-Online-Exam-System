package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class ResultServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req, HttpServletResponse res)
 throws ServletException, IOException {

  try{

   HttpSession session=req.getSession(false);
   String email=(String)session.getAttribute("email");

   Connection con=DBConnection.getConnection();

   PreparedStatement ps=con.prepareStatement(
    "select score,status from results where student_email=?"
   );

   ps.setString(1,email);
   ResultSet rs=ps.executeQuery();

   if(rs.next()){

    if(rs.getString("status").equals("released")){
     int score=rs.getInt("score");
     res.sendRedirect("pages/viewResult.html?score="+score);
    }
    else{
     res.sendRedirect("pages/viewResult.html?score=pending");
    }

   }

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}
