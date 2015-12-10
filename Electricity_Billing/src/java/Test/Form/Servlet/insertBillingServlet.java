/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Form.Servlet;

import Connection.Manager.Tables.BillingManager;
import Connection.Manager.Tables.UserBillingManager;
import Connection.Testing.Beans.BillingControl;
import Connection.Testing.Beans.UserBilling;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sadika
 */
@WebServlet(name = "insertBillingServlet", urlPatterns = {"/insertBillingServlet"})
public class insertBillingServlet extends HttpServlet {

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
            out.println("<title>Servlet insertBillingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet insertBillingServlet at " + request.getContextPath() + "</h1>");
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

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        String cid = request.getParameter("cId");
        String unit = request.getParameter("unit");
        String paytime = request.getParameter("timestamp");
        int cost = 0;
        if (type.length()==0 && cid.length()==0 && unit.length()==0 && paytime.length()==0) {
            out.print("Not Inserted value into userBilling Table");
        } else {
            try {
                BillingControl bcon = BillingManager.getRow(type);
                if (bcon == null) {
                    out.print("BillingControll is Null");
                } else {
                    cost = bcon.getUnit();
                }
            } catch (SQLException ex) {
                Logger.getLogger(insertBillingServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(insertBillingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            int uUnit = Integer.valueOf(unit);
            cost = (cost * uUnit);
            UserBilling ubilling = new UserBilling();
            ubilling.setType(type);
            ubilling.setUserId(cid);
            ubilling.setTotalUnit(uUnit);
            ubilling.setCost(cost);
            ubilling.setPdate(paytime);

            boolean insert;
            try {
                insert = UserBillingManager.insert(ubilling);
                if (insert) {
                    response.sendRedirect("index.jsp");
                } else {
                    out.print("Not Inserted value into userBilling Table");
                }
            } catch (Exception ex) {
                Logger.getLogger(insertBillingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
