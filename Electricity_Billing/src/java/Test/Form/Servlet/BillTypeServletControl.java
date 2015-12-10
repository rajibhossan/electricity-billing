/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Form.Servlet;

import Connection.Manager.Tables.BillTypeManager;
import Connection.Manager.Tables.BillingManager;
import Connection.Testing.Beans.BillType;
import Connection.Testing.Beans.BillingControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sadika
 */
public class BillTypeServletControl extends HttpServlet {

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
            out.println("<title>Servlet BillTypeServletControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillTypeServletControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String type = request.getParameter("type");
//        
//        PrintWriter out=response.getWriter();
//        out.println("id" + id);
//        out.println("type" + type);

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm:ss");
        String formattedDate = sdf.format(date);

        BillingControl btype = new BillingControl();
        btype.setId(Integer.valueOf(id));
        btype.setType(type);
        btype.setEdate(formattedDate);

        try {
            BillingManager.update(btype);
            response.sendRedirect("billSetup.jsp");

        } catch (Exception ex) {
            Logger.getLogger(BillTypeServletControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.encodeRedirectURL("billSetup.jsp");

        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        String sdate = request.getParameter("timestamp");
        String unitCost = request.getParameter("unit");

        if (type.length() == 0 && sdate.length() == 0 && unitCost.length() == 0) {

            response.setContentType("text/html");
            out.println("Invalide Input!!!");
            out.println("<a href=\"billSetup.jsp\">Billing Setup</a> ");

        } else {
            BillType btype = new BillType();
            btype.setBillType(type);

            BillingControl bcontrol = new BillingControl();
            bcontrol.setType(type);
            bcontrol.setUnit(Integer.valueOf(unitCost));
            bcontrol.setSdate(sdate);
            bcontrol.setEdate("");

            try {
                BillingManager.insert(bcontrol);
            } catch (Exception e) {
                out.println("problem with BillingManager!!!");
            }
            try {
                BillTypeManager.insert(btype);
            } catch (Exception e) {
                out.println("problem with BillTypeManager!!!");
            }
            response.sendRedirect("billSetup.jsp");
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
