package com.exam;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
              "select * from students where email=? and password=?"
            );

            ps.setString(1,email);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                HttpSession session = req.getSession();
                session.setAttribute("email", email);

                res.sendRedirect("pages/dashboard.html");

            } else {

                res.getWriter().println("Invalid Login!");

            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
