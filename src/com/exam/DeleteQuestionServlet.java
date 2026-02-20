package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class DeleteQuestionServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req, HttpServletResponse res)
 throws ServletException, IOException {

  int id = Integer.parseInt(req.getParameter("qid"));

  try{

   Connection con = DBConnection.getConnection();

   PreparedStatement ps = con.prepareStatement(
    "delete from questions where qid=?"
   );

   ps.setInt(1,id);
   ps.executeUpdate();

   res.sendRedirect("ViewQuestionsServlet");

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}
