package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.util.Base64;

public class SnapshotServlet extends HttpServlet {

 protected void doPost(HttpServletRequest req,HttpServletResponse res)
 throws ServletException,IOException{

  try{

   HttpSession session=req.getSession(false);
   String email=(String)session.getAttribute("email");
   String reason=req.getParameter("reason");

   String image=req.getParameter("image");
   image=image.replace("data:image/png;base64,","");

   byte[] decodedBytes=Base64.getDecoder().decode(image);

   String filename=System.currentTimeMillis()+".png";

   String path=getServletContext().getRealPath("/")+"cheatImages/";

   FileOutputStream fos=new FileOutputStream(path+filename);
   fos.write(decodedBytes);
   fos.close();

   Connection con=DBConnection.getConnection();

   PreparedStatement ps=con.prepareStatement(
    "insert into violations(student_email,reason,image) values(?,?,?)"
   );

   ps.setString(1,email);
   ps.setString(2,reason);
   ps.setString(3,filename);

   ps.executeUpdate();

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}
