/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.servlet;

import com.google.inject.Injector;
import etanah.dao.DokumenCapaianDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.service.common.DokumenService;
import etanah.util.FileUtil;
import etanah.util.WORMUtil;
import etanah.view.etanahContextListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author md.nurfikri
 */
public class PrintServlet extends HttpServlet {


    etanah.Configuration conf = new etanah.Configuration();
    private static final Logger LOG = Logger.getLogger(PrintServlet.class);
    private static final boolean isDebug = LOG.isDebugEnabled();
    DokumenService dokumenService = new DokumenService();

    PenggunaDAO penggunaDAO = new PenggunaDAO();

    DokumenCapaianDAO dokumenCapaianDAO = new DokumenCapaianDAO();

    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO =  new KodStatusDokumenCapaiDAO();

    private static final int CODE_TO_ADD_WATERMARK = 3;


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
//        PrintWriter out = response.getWriter();
        try {
            //TODO access validation?
            String fn = request.getParameter("filename");
            String folder = request.getParameter("folderId");            
            boolean createWatermark = false;
            boolean disableWatermark = false;
            boolean withoutSignature = false;
            String idPengguna = "";
            if (request.getParameter("disableWatermark") != null) {
                disableWatermark = Boolean.parseBoolean(request.getParameter("disableWatermark"));
            }
            
            if (request.getParameter("withoutSignature") != null) {
                withoutSignature = Boolean.parseBoolean(request.getParameter("withoutSignature"));
            }

            if (request.getParameter("idPengguna") != null) {
                idPengguna = request.getParameter("idPengguna");
            }

            
            if (isDebug) {
                LOG.debug("file name = " + fn);
                LOG.debug("folder = " + folder);
                LOG.debug("id pengguna = " + idPengguna);
            }

            if (StringUtils.isNotBlank(fn)) {
                Injector injector = etanahContextListener.getInjector();
                injector.injectMembers(dokumenService);
                injector.injectMembers(penggunaDAO);
                injector.injectMembers(dokumenCapaianDAO);
                injector.injectMembers(kodStatusDokumenCapaiDAO);

                Session sess = injector.getProvider(Session.class).get();               
                
                Dokumen d = dokumenService.findById(Long.parseLong(fn));
                
                if( d == null
                        || d.getKodDokumen() == null
                        || StringUtils.isBlank(d.getNamaFizikal()) )  {
                    LOG.warn("dokumen tiada!!!");
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Dokumen tiada!!");
                    return ;
                }

                Pengguna pengguna = null;
                if (StringUtils.isNotBlank(idPengguna)) {
                    pengguna = penggunaDAO.findById(idPengguna);
                } 

                if (pengguna != null) {
                    //log view
                    DokumenCapaian dc = new DokumenCapaian();
                    dc.setDokumen(d);
                    dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("CD"));
                    dc.setAlasan("Cetakan Dokumen");
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                    dc.setInfoAudit(ia);

                    Transaction tx = sess.beginTransaction();
                    dokumenCapaianDAO.save(dc);
                    tx.commit();
                }   

                String svrPath = conf.getProperty("document.path");
                String docPath = svrPath + (svrPath.endsWith(File.separator) ? "" : File.separator)
                        + d.getNamaFizikal();

                if (d.getKodDokumen().getKawalCapaian() == 'Y') {
                    createWatermark = true;
                }
                
                
                File f = new File(docPath);
                InputStream in = null;
                File signFile = null;
                
                if (!f.exists()) {
                WORMUtil worm = etanahContextListener.newInstance(WORMUtil.class);
                String[] path2 = d.getNamaFizikal().split("/");
                   String path = d.getNamaFizikal();
                    if (path2.length > 0) {
                        String daerah = path2[3];
                        String bpm = path2[4];
                        String jenisHm = path2[5];
                        String versi = path2[6];
                        String idHakmilik = path2[7];
                        in = worm.get(idHakmilik, daerah, bpm, versi, jenisHm, versi);
                        InputStream sign = worm.get(idHakmilik+".sig", daerah, bpm, versi, jenisHm, versi);
                        if (sign != null) disableWatermark = true;
                         else disableWatermark = false;                        
                    }
               } else {
                   in = new FileInputStream(f);
                   if (!withoutSignature) {
                       signFile = new File(docPath + ".sig");
                       disableWatermark = signFile.exists();
                   } else {
                       createWatermark = false;
                   }                  
               }
                
               if (d.getKodDokumen().getKod().equalsIgnoreCase("DHDK")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("DHKK")) {
                    createWatermark = false;
                    disableWatermark = true;  
                }
                

//                File signFile = new File(docPath + ".sig");

               

//                File f = new File(docPath);
//                FileInputStream in = new FileInputStream(f);
                ServletOutputStream out = response.getOutputStream();               
                
                if (!disableWatermark && createWatermark) {
                    ByteArrayOutputStream bous = FileUtil.createWaterMark(in);
                    bous.writeTo(out);                    
                } else {
                    // Copy the contents of the file to the output stream
                    byte[] buf = new byte[1024];
                    int count = 0;
                    while ((count = in.read(buf)) >= 0) {
                         out.write(buf, 0, count);
                    }                    
                }
                out.close();
                in.close();
            }
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
