/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.report;

import com.google.inject.Injector;
import etanah.Configuration;
import etanah.model.Pengguna;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.etanahContextListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

/**
 *
 * @author afham
 */
public class ReportUtilMMKN extends ReportUtil {
    
    private static String SERVER_LOCATION = "";
//    private String sourcePath;
//    private String userid;
    private static String reportKey = "";
//    private String DMS_BASE;
    private String DMSPATH_WO_DMSBASE;
//    private String DMS_PATH;
    private static final Logger LOG = Logger.getLogger(ReportUtilMMKN.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    private static final DMSUtil dmsUtil = new DMSUtil();

    private String contentType = "application/pdf";

    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(32);
    private static final int WAITING_TIME = 350;
    // HTTP Socket timeout to 5 minutes #change from 5 minutes to 30 minutes based on a lots data to load assisted by en.hisyam 21062011
    private static final int TIMEOUT = 30 * 60 * 1000;
    private static final AtomicLong load = new AtomicLong();
    private static final AtomicLong sequence = new AtomicLong();
    private static final ConcurrentHashMap<Long, GetMethod> joblist = new ConcurrentHashMap<Long, GetMethod>();
    
    private long currentJobId;
    
    static {
        Injector inject = etanahContextListener.getInjector();
        Configuration conf = inject.getInstance(Configuration.class);
        SERVER_LOCATION = conf.getProperty("report.server.location");
        reportKey = conf.getProperty("report.key");
    }

    @Override
    public byte[] generateReport(String reportName, String[] params, String[] values, String outputPath, Pengguna pengguna) {
        boolean isAkuan = false;
        boolean isResit = false;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        if (params == null || values == null || params.length != values.length) {
            throw new IllegalArgumentException("Check the parameters passed");
        }
        
        if (outputPath != null && pengguna == null) {
            throw new IllegalArgumentException("Check the parameters passed - outputPath must be null for pengguna to be null");
        }

        // Using new URL
        // ie. http://report_server:9001/?key&report.rdf&param
        StringBuilder cmd = new StringBuilder(SERVER_LOCATION)
                .append("?").append(reportKey)
                .append("&").append(reportName)
                .append("&desname=").append(System.currentTimeMillis());
        
        for (int i = 0; i < params.length; i++) {            
//            cmd.append("&").append(params[i]).append("=").append(values[i]).append(" ");
            cmd.append("&").append(params[i]).append("=").append(values[i]);
        }

        long start = System.currentTimeMillis();
        if(isDebug) LOG.debug(cmd.toString());

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(cmd.toString());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, TIMEOUT);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
                
        try {
            currentJobId = sequence.getAndIncrement();
            joblist.put(currentJobId, method);
            load.incrementAndGet();
            
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            if (isDebug) LOG.debug("Report " + cmd.toString() +  " completed in " + (System.currentTimeMillis() - start + "ms"));
            
//            Header contextType = method.getResponseHeader("Content-Type");
//            if (contentType != null) contentType = contextType.getValue();

            LOG.debug("contentType =" + contentType);


            InputStream is = method.getResponseBodyAsStream();    
            if (outputPath == null) {
                if (isDebug) LOG.debug("Writing generated report to array.");
                
                FileUtil.copyStream(is, out);
                return out.toByteArray();
            }
            
        }catch (Exception e) {
            LOG.error("Fatal protocol violation", e);
        } finally {
            // Release the connection.
            method.releaseConnection();
            load.decrementAndGet();
            joblist.remove(currentJobId);
        }
 
            return null ;
        
    }
    
}
