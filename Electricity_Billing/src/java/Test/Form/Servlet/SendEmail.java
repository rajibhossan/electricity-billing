/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Form.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sadika
 */
public class SendEmail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendEmail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name =request.getParameter("name");
        String mail=request.getParameter("mail");
        String comment=request.getParameter("comment");
        
        
        // Recipient's email ID needs to be mentioned.
        //String to = "rajibhossan@yahoo.com";

        // Sender's email ID needs to be mentioned
        String from = "rajibhossan@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        properties.setProperty("mail.user", "rajibhossan@gmail.com");
        properties.setProperty("mail.password", "aparajitaRAJIB2");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            try {
                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));
            } catch (MessagingException ex) {
                Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(mail));
            // Set Subject: header field
            message.setSubject(name+" sending a mail for you");
            // Now set the actual message
            message.setText(comment);
            // Send message
            Transport.send(message);
            String title = "Send Email";
            String res = "Sent message successfully....";
            String docType
                    = "<!doctype html public \"-//w3c//dtd html 4.0 "
                    + "transitional//en\">\n";
            out.println(docType
                    + "<html>\n"
                    + "<head><title>" + title + "</title></head>\n"
                    + "<body bgcolor=\"#f0f0f0\">\n"
                    + "<h1 align=\"center\">" + title + "</h1>\n"
                    + "<p align=\"center\">" + res + "</p>\n"
                    + "</body></html>");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
