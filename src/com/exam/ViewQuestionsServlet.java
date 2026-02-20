package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class ViewQuestionsServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req, HttpServletResponse res)
 throws ServletException, IOException {

  res.setContentType("text/html");
  PrintWriter out = res.getWriter();

  try {

   Connection con = DBConnection.getConnection();
   Statement st = con.createStatement();

   ResultSet rs = st.executeQuery("select * from questions");

   out.println("<html>");
   out.println("<head>");
   out.println("<title>View Questions</title>");
   out.println("</head>");
   out.println("<body>");

   out.println("<h2 style='text-align:center;'>All Questions</h2>");

   out.println("<table border='1' width='80%' align='center' cellpadding='10'>");
   out.println("<tr style='background-color:lightgray;'>");
   out.println("<th>ID</th>");
   out.println("<th>Question</th>");
   out.println("<th>Action</th>");
   out.println("</tr>");

   while(rs.next()){

    out.println("<tr>");
    out.println("<td>"+rs.getInt("qid")+"</td>");
    out.println("<td>"+rs.getString("question")+"</td>");
    out.println("<td><a href='/ExamPortal/DeleteQuestionServlet?qid="+rs.getInt("qid")+
    "' onclick=\"return confirm('Are you sure you want to delete this question?');\">Delete</a></td>");
    out.println("</tr>");

   }

   out.println("</table>");

   out.println("<br><center>");
   out.println("<a href='/ExamPortal/pages/adminDashboard.html'>Back to Dashboard</a>");
   out.println("</center>");

   out.println("</body>");
   out.println("</html>");

  } catch(Exception e){
   e.printStackTrace();
  }
 }
}
