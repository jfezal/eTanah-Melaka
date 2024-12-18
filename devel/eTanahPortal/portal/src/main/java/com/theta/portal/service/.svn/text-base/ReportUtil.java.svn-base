package com.theta.portal.service;

import com.google.inject.Injector;

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
 * <p>An implementation of {@link ReportGenerator} using HTTP request to report server.
 * Each request is sent via a persistence HTTP connection pool, with max connection set to 24.</p>
 * <p>Request to report server is queued using {@link java.util.concurrent.ExecutorService} with
 * a delay of 350ms between each request</p>
 */
public class ReportUtil  {

    private static String SERVER_LOCATION = "";
//    private String sourcePath;
//    private String userid;
    private static String reportKey;
//    private String DMS_BASE;
    private String DMSPATH_WO_DMSBASE;
//    private String DMS_PATH;
    private static final Logger LOG = Logger.getLogger(ReportUtil.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    private String contentType = "application/pdf";

    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(32);
    private static final int WAITING_TIME = 350;
    // HTTP Socket timeout to 5 minutes #change from 5 minutes to 30 minutes based on a lots data to load assisted by en.hisyam 21062011
    private static final int TIMEOUT = 30 * 60 * 1000;
    private static final AtomicLong load = new AtomicLong();
    private static final AtomicLong sequence = new AtomicLong();
    private static final ConcurrentHashMap<Long, GetMethod> joblist = new ConcurrentHashMap<Long, GetMethod>();
    private long currentJobId;



    public String getDMSPath() {
        return DMSPATH_WO_DMSBASE;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getReports(String reportName, String params){
         SERVER_LOCATION = "http://10.66.130.14:9003/reports/rwservlet";
        reportKey = "asset";
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
   
  
}

