package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class AddQuestionServlet extends HttpServlet {

 protected void doPost(HttpServletRequest req, HttpServletResponse res)
 throws ServletException, IOException {

  String q=req.getParameter("question");
  String o1=req.getParameter("option1");
  String o2=req.getParameter("option2");
  String o3=req.getParameter("option3");
  String o4=req.getParameter("option4");
  String ans=req.getParameter("answer");

  try{

   Connection con=DBConnection.getConnection();

   PreparedStatement ps=con.prepareStatement(
    "insert into questions(question,option1,option2,option3,option4,answer) values(?,?,?,?,?,?)"
   );

   ps.setString(1,q);
   ps.setString(2,o1);
   ps.setString(3,o2);
   ps.setString(4,o3);
   ps.setString(5,o4);
   ps.setString(6,ans);

   ps.executeUpdate();

   res.getWriter().println("Question Added!");

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}
