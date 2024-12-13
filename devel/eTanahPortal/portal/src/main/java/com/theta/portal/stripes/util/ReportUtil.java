/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.theta.portal.stripes.util;

import com.google.inject.Injector;
import com.theta.portal.config.ableContextListener;
import com.theta.portal.stripes.Configuration;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


/**
 *
 * @author john_doe
 */
public class ReportUtil {

    private static String SERVER_LOCATION = "";

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
        Injector inject = ableContextListener.getInjector();
        Configuration conf = inject.getInstance(Configuration.class);
        SERVER_LOCATION = conf.getProperty("report.server.location");

    }

    public byte[] generateReport(String reportName, String[] values) {
     

        if (values == null) {
            throw new IllegalArgumentException("Check the parameters passed");
        }

        StringBuilder cmd = new StringBuilder(SERVER_LOCATION)
                .append("?").append(reportName);
        for (String value : values) {
            cmd.append("&").append(value);
        }
        System.out.println("cmd = " + cmd.toString());

        long start = System.currentTimeMillis();

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

            Header contextType = method.getResponseHeader("Content-Type");
            if (contentType != null) {
                contentType = contextType.getValue();
            }

            InputStream is = method.getResponseBodyAsStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            FileUtil.copyStream(is, out);
            return out.toByteArray();

        } catch (Exception e) {
            
        } finally {
            // Release the connection.
            method.releaseConnection();
            load.decrementAndGet();
            joblist.remove(currentJobId);
        }
        return null;
    }

}
