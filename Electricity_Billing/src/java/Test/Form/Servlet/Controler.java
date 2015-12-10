/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Form.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import User.Login.UserValidation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Sadika
 */
@WebServlet(name = "Controler", urlPatterns = {"/Controler"})
public class Controler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        response.sendRedirect("Login.jsp");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        String name = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out=response.getWriter();

        UserValidation checkPass = new UserValidation(name, password);
        try {
            if (checkPass.isTrue()) {
                request.getSession().setAttribute("username", name);
                RequestDispatcher dispat=request.getRequestDispatcher("index.jsp");
                dispat.forward(request, response);

                
            } else {
                out.println(" <center> <font color=red > <b> Yout enter wrong user Name and Password</b></font></center>");
                
                RequestDispatcher re =request.getRequestDispatcher("Login.jsp");
                re.include(request, response);
               
               
            }

//        PrintWriter pw = response.getWriter();
//        ConnectionManager conn = ConnectionManager.getInstance();
//        String constatus = "";
//        try {
//            constatus = conn.getConnection();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        pw.println(constatus);
//        pw.println("<br>");
//        pw.println("User Id" + password);
//        try {
//            User user = UserManager.getRow(1);
//
//            response.setContentType("text/html");
//            pw.println("User Id :" + user.getId());
//            pw.println("<br>");
//            pw.println("User Id :" + user.getUserName());
//            pw.println("<br>");
//            pw.println("User Id :" + user.getPassword());
//        } catch (SQLException ex) {
//            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
//
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        pw.println(conn.close());
            // JDBC driver name and database URL
//        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//        String DB_URL = "jdbc:mysql://localhost:3306/user";
//        //  Database credentials
//        String USER = "root";
//        String PASS = "";
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        // Set response content type
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        String title = "Database Result";
//        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
//
//        out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
//                + "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n");
//        try {
//            // Register JDBC driver
//            Class.forName(JDBC_DRIVER);
//
//            // Open a connection
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            // Execute SQL query
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT * FROM  usertable";
//            rs = stmt.executeQuery(sql);
//
//            // Extract data from result set
//            while (rs.next()) {
//                //Retrieve by column name
//                int id = rs.getInt("id");
//                String userName = rs.getString("userName");
//                String Password = rs.getString("Password");
//
//
//                //Display values
//                out.println("ID: " + id + "<br>");
//                out.println(", user Name : " + userName + "<br>");
//                out.println(", Password : " + Password + "<br>");
//               
//            }
//            out.println("</body></html>");
//
//            // Clean-up environment
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (SQLException se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        } catch (Exception e) {
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        } finally {
//            //finally block used to close resources
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//            } catch (SQLException se2) {
//            }// nothing we can do
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }//end finally try
//        } //end try
        } catch (Exception ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
