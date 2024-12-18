/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.servlet;

//import com.google.inject.Injector;
//import etanah.dao.PenggunaDAO;
//import etanah.model.Pengguna;
import etanah.report.ReportUtil;
//import etanah.view.etanahContextListener;
//import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fikri
 */
public class PrintReportServlet extends HttpServlet {

    etanah.Configuration conf = new etanah.Configuration();
//    private String tmpFolder = System.getProperty("java.io.tmpdir");

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        ReportUtil report = new ReportUtil();
        try {
            String reportName = request.getParameter("reportname");
            String[] params = request.getParameterValues("params");
            String[] values = request.getParameterValues("values");
//            String userId = request.getParameter("userId");
            
//            String tmpPath = tmpFolder + (tmpFolder.endsWith(File.separator) ? "" : File.separator) + System.nanoTime();
//            File f = new File(tmpPath);
//            f.deleteOnExit();
//            Injector i = etanahContextListener.getInjector();
//            PenggunaDAO penggunaDAO = i.getInstance(PenggunaDAO.class);
//            Pengguna pengguna = penggunaDAO.findById(userId);
            byte[] pdf = report.generateReport(reportName, params, values, null, null);
//            FileInputStream in = new FileInputStream(f);
            ServletOutputStream out = response.getOutputStream();
//            byte[] buf = new byte[1024];
//            int count = 0;
//            while ((count = in.read(buf)) >= 0) {
//                out.write(buf, 0, count);
//            }
//            in.close();
            response.setContentType(report.getContentType());
            out.write(pdf);
            out.close();

        } finally {
            
        }
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
