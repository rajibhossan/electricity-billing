/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Form.Servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import com.darwinsys.spdf.PDF;
//import com.darwinsys.spdf.Page;
//import com.darwinsys.spdf.Text;
//import com.darwinsys.spdf.MoveTo;

public class ServletPDF extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("application/pdf");

        response.setHeader("Content-disposition", "inline; filename='javatpoint.pdf'");

//        PDF p = new PDF(out);
//        Page p1 = new Page(p);
//        p1.add(new MoveTo(p, 200, 700));
//        p1.add(new Text(p, "www.javatpoint.com"));
//        p1.add(new Text(p, "by Sonoo Jaiswal"));
//
//        p.add(p1);
//        p.setAuthor("Ian F. Darwin");
//
//        p.writePDF();

    }
}
