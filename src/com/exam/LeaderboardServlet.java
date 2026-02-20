package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class LeaderboardServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req,HttpServletResponse res)
 throws ServletException,IOException{

  res.setContentType("application/json");
  PrintWriter out=res.getWriter();

  try{

   Connection con=DBConnection.getConnection();

   Statement st=con.createStatement();

   ResultSet rs=st.executeQuery(
    "select student_email,score from results order by score desc"
   );

   String json="[";

   while(rs.next()){

    json+="{\"email\":\""+rs.getString("student_email")+"\",\"score\":\""+rs.getInt("score")+"\"},";

   }

   if(json.length()>1)
    json=json.substring(0,json.length()-1);

   json+="]";

   out.print(json);

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}

