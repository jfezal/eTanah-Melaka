/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class ReportUtilPath implements ReportGenerator, ReportUtilMBean{

   private static String SERVER_LOCATION = "";
//    private String sourcePath;
//    private String userid;
    private static String reportKey;
//    private String DMS_BASE;
    private String DMSPATH_WO_DMSBASE;
//    private String DMS_PATH;
    private static final Logger LOG = Logger.getLogger(ReportUtil.class);
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
    
//    @Inject
//    public ReportUtil(etanah.Configuration conf) {
//        SERVER_LOCATION = conf.getProperty("report.server.location");
////        sourcePath = conf.getProperty("report.source.path");
////        userid = conf.getProperty("report.generator.userid");
//        reportKey = conf.getProperty("report.key");
//        DMS_BASE = conf.getProperty("document.path");
//    }
//    
//    public static void main(String[] args) {
//        String[] params = {"p_id_mohon"};
//        String[] values = {"TEST141020090928"};
//        etanah.Configuration conf = new Configuration();
//        ReportUtil util = new ReportUtil(conf);
////        util.generateReport("001","1550","V_DOC_002.rdf", params, values, null);
//    }

    @Override
    public byte[] generateReport(String reportName, String[] params, String[] values, String outputPath, Pengguna pengguna) {

        boolean isAkuan = false;
        boolean isResit = false;

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
            
            Header contextType = method.getResponseHeader("Content-Type");
            if (contentType != null) contentType = contextType.getValue();

            LOG.debug("contentType =" + contentType);


            InputStream is = method.getResponseBodyAsStream();            
//            HTTPConnection httpConn = new HTTPConnection(cmd.toString());
//            Future<byte[]> http = executor.schedule(httpConn, WAITING_TIME, TimeUnit.MILLISECONDS);
//            InputStream is = new ByteArrayInputStream(http.get());
//            if (httpConn.getContentType() != null) contentType = httpConn.getContentType().getValue();

            // check if outputPath is null
            // -- we do not want to write to disk
            if (outputPath == null) {
                if (isDebug) LOG.debug("Writing generated report to array.");
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                FileUtil.copyStream(is, out);
                return out.toByteArray();
            }

            String fName = outputPath.substring(outputPath.lastIndexOf(File.separator)+1);
            outputPath = outputPath.substring(0, outputPath.lastIndexOf(File.separator));

            if(outputPath.matches("(?i).*AkuanPenerimaan*")) {
                isAkuan = true;
            }
            if (outputPath.matches("(?i).*RESIT*")) {
                isResit = true;
            }

            String DMS_PATH = outputPath;
       
            if(isDebug) LOG.debug("DIR to created = " + DMS_PATH);            
            
            FileUtil f = new FileUtil(DMS_PATH);
//            f.saveFile(reportName.substring(0, reportName.indexOf(".")), is);
            f.saveFile(fName, is);
            DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + (DMSPATH_WO_DMSBASE.endsWith(File.separator) ? "" : File.separator) + fName;
        } catch (Exception e) {
            LOG.error("Fatal protocol violation", e);
        } finally {
            // Release the connection.
            method.releaseConnection();
            load.decrementAndGet();
            joblist.remove(currentJobId);
        }
        // output is written to disk
        return null;
    }

    public String getDMSPath() {
        return DMSPATH_WO_DMSBASE;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getReports(String reportName, String params){
        
        byte[] bytes = null;
        // Using new URL
        // ie. http://report_server:9001/?key&report.rdf&param
        StringBuilder cmd = new StringBuilder(SERVER_LOCATION)
                .append("?").append(reportKey)
                .append("&").append(reportName)
                .append(params);

        long start = System.currentTimeMillis();
        if(isDebug) LOG.debug(cmd.toString());

        try {
            Future<byte[]> http = executor.schedule(new HTTPConnection(cmd.toString()), WAITING_TIME, TimeUnit.MILLISECONDS);
            bytes = http.get();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (isDebug) LOG.debug("Report completed in " + (System.currentTimeMillis()-start) + "ms");
        return bytes;
    }

    
   static final class HTTPConnection implements Callable<byte[]> {

        static final HttpClient client;
        String url;
        GetMethod method;
        private long currentJobId;        
        
        static {
            MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
            HttpConnectionManagerParams params = new HttpConnectionManagerParams();
            params.setStaleCheckingEnabled(true);
            params.setDefaultMaxConnectionsPerHost(24);
            params.setMaxTotalConnections(32);
            manager.setParams(params);
            client = new HttpClient(manager);
        }

        public HTTPConnection(String url) {
            this.url = url;
            // force close idle connections
            MultiThreadedHttpConnectionManager manager = (MultiThreadedHttpConnectionManager) client.getHttpConnectionManager();
            manager.closeIdleConnections(TIMEOUT);
            if (isDebug) {
                LOG.debug("Total connection in pool: " + manager.getConnectionsInPool());
            }
        }

        public Header getContentType() {
            return method.getRequestHeader("Content-Type");
        }
                
        @Override
        public byte[] call() throws Exception {
            byte[] buff = null;
            int statusCode = HttpStatus.SC_METHOD_FAILURE;
            method = new GetMethod(url);
            HttpMethodParams param = method.getParams();
            param.setParameter(HttpMethodParams.SO_TIMEOUT, TIMEOUT);
            param.setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler(3, false));

            try {
                currentJobId = sequence.getAndIncrement();
                joblist.put(currentJobId, method);
                load.incrementAndGet();
                // Execute the method.
                statusCode = client.executeMethod(method);

                if (statusCode != HttpStatus.SC_OK) {
                    LOG.error("Error while connecting to Report Server - " + statusCode);
                }
                buff = method.getResponseBody();

            } catch (Exception e) {
                LOG.error("Fatal protocol violation: ", e);
                throw e;
            } finally {
                // Release the connection.
                method.releaseConnection();
                load.decrementAndGet();
                joblist.remove(currentJobId);
            }
            return buff;
        }
    }
   
   // **************** JMX
   
   @Override
   public boolean kill(long id) {
       boolean ok = false;
       try {
           GetMethod method = joblist.get(id);
           if (method != null) {
               LOG.info(String.format("Killing http report request %d - %s", id, method.getURI().toString()));
               if (method.isRequestSent() && !method.isAborted()) {
                   method.abort();
                   joblist.remove(id);
               }
           }
       } catch (Exception e) {
           LOG.error("Error while aborting connection", e);
       }
       return ok;
   }
   
   @Override
   public long getCurrentLoad() {
       return load.get();
   }
   
   @Override
   public Map<Long, String> getJobs() {
       Map map = null;
       if (!joblist.isEmpty()) {
            map = new HashMap();
            Enumeration e = joblist.keys();
            while (e.hasMoreElements()) {
                Long key = (Long) e.nextElement();
                GetMethod method = joblist.get(key);
                if (method != null) {
                    try {
                    String url = method.getURI().toString();
                    map.put(key, url);
                    } catch (Exception err) {
                        // ignore
                    }
                }
            }
       }
       return map;
   }
   
   @Override
   public String getReportKey() {
       return reportKey;
   }
   
   @Override
   public void setReportKey(String key) {
       reportKey = key;
   }
   
   @Override
   public String getServerURL() {
       return SERVER_LOCATION;
   }
   
   @Override
   public void setServerURL(String url) {
       SERVER_LOCATION = url;
   }
   
   @Override
   public long getTotalReportRun() {
       return sequence.get();
   }
    
}
