/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.servlet;

import com.google.inject.Injector;
import etanah.report.ReportUtil;
import etanah.view.etanahContextListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fikri
 */
public class ReportGeneratorServlet extends HttpServlet {    

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        byte[] bytes = null;       
        
        try {

            String reportName = request.getParameter("reportName");
            if (StringUtils.isBlank(reportName)) {
                throw new Exception("Please provide the report name for the report!!!");
            }            

            StringBuilder params = getSingleParameterMap(request);
            System.out.println("params = " + params.toString());
            if(params.length() <= 0) {
                throw new Exception("Please provide the parameter(s) for the report!!!");
            }
            Injector injector = etanahContextListener.getInjector();
            ReportUtil report = injector.getInstance(ReportUtil.class);
            bytes = report.getReports(reportName, params.toString());

        } catch (Exception ex) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Report | e-Tanah</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            out.println("<span class=\"bnew\">OracleReports encountered this error :</span>");
            out.println("<pre>");
            ex.printStackTrace(out);
            out.println("</pre>");
            out.println("</body>");
            out.println("</html>");
            out.close();

            return;
        } 

        if (bytes != null && !(bytes.length > 800))
        {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>e-Tanah</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            out.println("<span class=\"bold\">Tiada Laporan untuk dipaparkan.</span>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        else if (bytes != null && bytes.length > 0)
        {
            // Set to expire far in the past.
//            response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
            // Set standard HTTP/1.1 no-cache headers.
//            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
//            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            // Set standard HTTP/1.0 no-cache header.
//            response.setHeader("Pragma", "no-cache");            
            response.setContentType("application/pdf");
//            response.setContentType("application/octet-stream" );
//            response.setHeader("Content-Disposition", "inline, filename=report.pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        }
        else
        {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>e-Tanah</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            out.println("<span class=\"bold\">Empty Response.</span>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    private StringBuilder getSingleParameterMap(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        for (Enumeration en = request.getParameterNames();en.hasMoreElements();) {
            String name = (String) en.nextElement();
            if(!name.startsWith("report_")) continue;
            String param = name.replaceAll("report_", "");
            String value = request.getParameter(name);
            value = value.replaceAll(" ", "%20");
            params.append("&").append(param).append("=").append(value);
        }
        return params;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
