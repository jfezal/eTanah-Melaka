/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.servlet;

import com.google.inject.Injector;
import com.sun.media.jai.codec.JPEGEncodeParam;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import etanah.view.etanahContextListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
/**
 * Returns JPEG for B1/B2 TIFF
 * Param:
 *  type - types of plan, B1 / B2
 *  file - the image filename
 * etanah2.properties
 *  pelan.path
 * Servlet mapping example:
 *  http://localhost:8084/etanah/image?type=B1&file=05070400010008580.TIF
 * @author minin
 */
public class ImageServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ImageServlet.class);
    private static etanah.Configuration conf;
    private static boolean coherenceCache = true;
    // NamedCache for coherence
    private NamedCache cache = null;
    
    static {
        Injector injector = etanahContextListener.getInjector();
        conf = injector.getInstance(etanah.Configuration.class);
        if ("false".equals(System.getProperty("etanah/coherence")))
            coherenceCache = false;
    }

    public ImageServlet() {
        // disable local storage
        // require dedicated Coherence cache server
        System.setProperty("tangosol.coherence.distributed.localstorage", "false");
        // dist_pelan_tiff* cache must be configured to expire after predefined time
        // and must limit the collection to something like 100 items
        if (coherenceCache)
            cache = CacheFactory.getCache("dist_pelan_tiff_" + System.getProperty("etanah/kodNegeri"));
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        OutputStream out = response.getOutputStream();
        try {
            String type = request.getParameter("type");
            String file = request.getParameter("file");
            if (type == null || file == null) {
                logger.warn("Parameter 'type' or 'file' not specified in url");
                return;
            }
            String fname = conf.getProperty("pelan.path") + File.separator
                    + type + File.separator
                    + file;
            File imgFile = new File(fname);
            if (!imgFile.canRead()) {
                logger.warn("Unable to read file - " + imgFile.getAbsoluteFile());
                return;
            }

            response.setContentType("image/jpeg");
            response.setHeader("Content-Disposition", "attachment; filename=" + file + ".jpeg");
            byte[] buff = getImage(fname);
            out.write(buff);
        } finally {
            out.close();
        }
    }

    /**
     * Generate a JPEG image from TIFF.
     * This image will be cached if coherence is available,
     * if not the image will be generated for each TIFF.
     * @param fname the filename
     * @return byte array of the JPEG
     */
    private byte[] getImage(String fname) {
        // check if pelan already cached
        byte[] buff = getFromCache(fname);
        if (buff == null) {
            // if not, create image and put into cache
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            RenderedOp img = JAI.create("fileload", fname);
            JPEGEncodeParam enc = new JPEGEncodeParam();
            enc.setQuality(1.0f);
            JAI.create("encode", img, out, "JPEG");
            buff = out.toByteArray();
            if (coherenceCache) cache.put(fname, buff);
        }
        return buff;
    }


    /**
     * Get image from cache, if coherence is enabled.
     * @param fname
     * @return
     */
    private byte[] getFromCache(String fname) {
        try {
            if (coherenceCache)
                return (byte[]) cache.get(fname);
        } catch (com.tangosol.net.RequestPolicyException e) {
            logger.warn("Coherence not available. Fallback to caching in filesystem!\n"
                    + "Please restart the coherence server and weblogic to re-enables caching.");
            coherenceCache = false;
        }
        return null;
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
