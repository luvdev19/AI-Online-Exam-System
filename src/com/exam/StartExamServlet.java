package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class StartExamServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req,HttpServletResponse res)
 throws ServletException,IOException{

  res.setContentType("application/json");
  PrintWriter out=res.getWriter();

  try{

   Connection con=DBConnection.getConnection();

   Statement st=con.createStatement();

   ResultSet rs=st.executeQuery("select * from questions");

   String json="[";

   while(rs.next()){

    json+="{\"qid\":\""+rs.getInt("qid")+
    "\",\"question\":\""+rs.getString("question")+
    "\",\"op1\":\""+rs.getString("option1")+
    "\",\"op2\":\""+rs.getString("option2")+
    "\",\"op3\":\""+rs.getString("option3")+
    "\",\"op4\":\""+rs.getString("option4")+"\"},";

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
