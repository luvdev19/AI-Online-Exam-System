package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class ReleaseResultServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req, HttpServletResponse res)
 throws ServletException, IOException {

  try {

   Connection con = DBConnection.getConnection();

   PreparedStatement ps = con.prepareStatement(
    "update results set status='released'"
   );

   ps.executeUpdate();

res.sendRedirect("/ExamPortal/pages/adminDashboard.html?msg=released");

  } catch(Exception e){
   e.printStackTrace();
  }
 }
}
