package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class SubmitExamServlet extends HttpServlet {

 protected void doPost(HttpServletRequest req, HttpServletResponse res)
 throws ServletException, IOException {

  int score = 0;

  try {

   Connection con = DBConnection.getConnection();
   Statement st = con.createStatement();

   ResultSet rs = st.executeQuery("select * from questions");

   HttpSession session = req.getSession(false);

   if(session == null || session.getAttribute("email")==null){
    res.sendRedirect("pages/login.html");
    return;
   }

   String email = (String)session.getAttribute("email");

   while(rs.next()){

    int qid = rs.getInt("qid");
    String correct = rs.getString("answer");
    String given = req.getParameter(String.valueOf(qid));

    if(correct!=null && correct.equals(given)){
     score++;
    }

    PreparedStatement ans = con.prepareStatement(
     "insert into answers(student_email,qid,selected_option,correct_option) values(?,?,?,?)"
    );

    ans.setString(1,email);
    ans.setInt(2,qid);
    ans.setString(3,given);
    ans.setString(4,correct);
    ans.executeUpdate();
   }

   PreparedStatement ps = con.prepareStatement(
    "insert into results(student_email,score,status) values(?,?,?)"
   );

   ps.setString(1,email);
   ps.setInt(2,score);
   ps.setString(3,"pending");

   try{
    ps.executeUpdate();
   }catch(Exception e){
    System.out.println("Already attempted");
   }

   res.sendRedirect("pages/dashboard.html");

  } catch(Exception e){
   e.printStackTrace();
  }
 }
}

