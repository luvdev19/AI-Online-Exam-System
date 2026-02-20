package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
              "insert into students(name,email,password) values(?,?,?)"
            );

            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,password);

            ps.executeUpdate();

            res.getWriter().println("Signup Successful!");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
