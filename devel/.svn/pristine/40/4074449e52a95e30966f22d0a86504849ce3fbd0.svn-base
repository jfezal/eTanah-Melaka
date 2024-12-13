package etanah.util;

import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Properties;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

/**
 * POC for WORM storage. The Hitachi Content Platform actually uses REST.
 * @author minin
 */
public class WORMUtil {

    private static final String URL;
    private static final String CONF_FILE = "/etanah-worm.properties";
    private static final String AUTH_COOKIE;
    private static final Properties props;
    
    public static final int SC_CREATED   = 201;
    public static final int SC_CREATED_W_ERROR   = 302;
    public static final int SC_OK        = 200;
    public static final int SC_FORBIDDEN = 403;
    public static final int SC_NOT_FOUND = 404;
    public static final int SC_CONFLICT  = 409;
    public static final int SC_TOO_LARGE = 413;
    
    @Inject
    private etanah.Configuration conf;
    
    /**
     * 
     * path: /rest/geran/kod_negeri/kod_daerah/kod_bpm[/kod_seksyen]/kod_hakmilik/id_hakmilik
     * TODO: Store signature as metadata (or should we store as normal file?)
     * ie: /04/40/HSD/000
     */
    public int put(File file, String idHakmilik, String daerah, String bpm, String seksyen, String jenisHm, String versi, String status)
    throws IOException {
        int retcode = SC_FORBIDDEN;
        SimpleHttpConnectionManager smp = new SimpleHttpConnectionManager(true);
        HttpClient client = new HttpClient(smp);
        PostMethod post = new PostMethod(URL);

        if (file == null || idHakmilik == null
            || daerah == null || bpm == null
            || jenisHm == null || versi == null || status == null) {
            throw new IllegalArgumentException("Please provide all required parameters!");
        }
        String path = buildPath(daerah, bpm, seksyen, jenisHm, versi).toString();
        post.setRequestHeader("Cookie", AUTH_COOKIE);

        try {
            Part[] parts = {
                new StringPart("directory", path),
                new FilePart("file", idHakmilik, file)
            };
            post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
            retcode = client.executeMethod(post);
            if (retcode != SC_CREATED && retcode != SC_CREATED_W_ERROR) {
                throw new IOException("File not created on WORM!");
            }

//            // generate metadata
//            String meta = String.format("<geran idhakmilik=\"%s\""
//                + " daerah=\"%s\""
//                + " mukim=\"%s\""
//                + " seksyen=\"%s\""
//                + " jenis=\"%s\""
//                + " status=\"%s\""
//                + " versi=\"%s\"/>",
//                idHakmilik, daerah, bpm, seksyen == null ? "" : seksyen,
//                jenisHm, status, versi);
//            
//            PutMethod put = new PutMethod(URL);
//            path.insert(0, "/rest")
//                .append("/")
//                .append(idHakmilik);
////            put.setPath(path.toString());
//            
//            put.setQueryString("type=custom-metadata");
//            put.setRequestHeader("Cookie", getAuth());
//            Part[] parts2 = {
//                new StringPart("directory", path.toString()),
////                new StringPart("file", idHakmilik),
//                new StringPart("metadata", meta)
//            };
//            put.setRequestEntity(new MultipartRequestEntity(parts2, put.getParams()));
//            System.out.println(client.executeMethod(put));
//            System.out.println(put.getResponseBodyAsString());
        } finally {
            if (post != null) {
                post.releaseConnection();
        }
        }
        return retcode;
    }
    
    public InputStream get(String idHakmilik, String daerah, String bpm, String seksyen, String jenisHm, String versi) 
    throws IOException {
        InputStream out = null;
        SimpleHttpConnectionManager smp = new SimpleHttpConnectionManager(true);
        HttpClient client = new HttpClient(smp);
        GetMethod method = new GetMethod(URL);

        if (idHakmilik == null
            || daerah == null || bpm == null
            || jenisHm == null || versi == null) {
            throw new IllegalArgumentException("Please provide all required parameters!");
        }
        StringBuilder path = buildPath(daerah, bpm, seksyen, jenisHm, versi);
        path.insert(0, "/rest")
            .append("/")
            .append(idHakmilik);
        method.setRequestHeader("Cookie", AUTH_COOKIE);
        method.setPath(path.toString());
        try {
            client.executeMethod(method);
            out = method.getResponseBodyAsStream();
        } finally {
            if (method != null) {
//                method.releaseConnection();                
            }
        }
        return out;
    }
    
    public StringBuilder buildPath(String daerah, String bpm, String seksyen, String jenisHm, String versi) {
        StringBuilder path = new StringBuilder()
            .append("/geran")
            .append("/")
            .append(conf.getKodNegeri())
            .append("/").append(daerah)
            .append("/").append(bpm);
//        if (seksyen != null) {
//            path.append("/").append(seksyen);
//        }
        path.append("/").append(jenisHm)
                .append("/").append(versi);
        

        return path;
    }
    
    static {
        try {
            props = new Properties();
            props.load(WORMUtil.class.getResourceAsStream(CONF_FILE));
            URL = props.getProperty("hcp.url");
            
            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            byte[] user = WLUtil.decrypt(props.getProperty("username")).getBytes();
//            byte[] pass = WLUtil.decrypt(props.getProperty("password")).getBytes();
            byte[] user = props.getProperty("username").getBytes();
            byte[] pass = props.getProperty("password").getBytes();
            
            AUTH_COOKIE = new StringBuilder("hcp-ns-auth=")
                .append(props.getProperty("username"))
                .append(":")
                .append(props.getProperty("password"))                
                .toString();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private void ls(String path) {
        try {
            SimpleHttpConnectionManager smp = new SimpleHttpConnectionManager(true);
            HttpClient http = new HttpClient(smp);
            String url = URL + path + "?version=list";
            GetMethod method = new GetMethod(url);
            method.setRequestHeader("Cookie", AUTH_COOKIE);
            System.out.println(http.executeMethod(method));
            System.out.println(method.getResponseBodyAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static final void main(String[] args) throws IOException {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
        WORMUtil worm = new WORMUtil();        
        System.out.println("================PUT");
        worm.put(new File("/home/fikri/Workplace/Project/etanah/dms/05/2014/OGOS/25/AkaunPenerimaan/2915261"), "sample2", "05", "00", null, "HSD", "1", "D");
        System.out.println("================ls(/05)");
        worm.ls("/05/00/HSD/sample");
        System.out.println("================GET");
        worm.get("sample", "05", "00", null, "HSD", "1");
    }
}

/**
<versions xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:noNamespaceSchemaLocation="/static/xsd/ns-versions.xsd"
          path="/rest/05/00/HSD/sample"
          utf8Path="/rest/05/00/HSD/sample"
          parentDir="/rest/05/00/HSD"
          utf8ParentDir="/rest/05/00/HSD">
  <entry urlName="sample"
         utf8Name="sample"
         type="object"
         size="17512"
         hashScheme="SHA-256"
         hash="D5A5FD5AFF979B211D317FC6D9FAB068712D518187F8E2298ECBC3B5ED85891D"
         retention="0"
         retentionString="Deletion Allowed"
         retentionClass=""
         ingestTime="1309442606"
         ingestTimeString="6/30/2011 10:03PM"
         hold="false"
         shred="false"
         dpl="1"
         index="true"
         customMetadata="false"
         state="deleted"
         version="83803987745601"
         />
  <entry urlName="sample"
         utf8Name="sample"
         type="object"
         size="1752"
         hashScheme="SHA-256"
         hash="1D2541405411A0C3139F1D11FC7552596C623764278DB6FF63B769930C4A51B5"
         retention="0"
         retentionString="Deletion Allowed"
         retentionClass=""
         ingestTime="1309442686"
         ingestTimeString="6/30/2011 10:04PM"
         hold="false"
         shred="false"
         dpl="1"
         index="true"
         customMetadata="false"
         state="deleted"
         version="83804331453121"
         />
  <entry urlName="sample"
         utf8Name="sample"
         type="object"
         size="1752"
         hashScheme="SHA-256"
         hash="1D2541405411A0C3139F1D11FC7552596C623764278DB6FF63B769930C4A51B5"
         retention="0"
         retentionString="Deletion Allowed"
         retentionClass=""
         ingestTime="1309442843"
         ingestTimeString="6/30/2011 10:07PM"
         hold="false"
         shred="false"
         dpl="1"
         index="true"
         customMetadata="false"
         state="deleted"
         version="83804332429761"
         />
  <entry urlName="sample"
         utf8Name="sample"
         type="object"
         size="1752"
         hashScheme="SHA-256"
         hash="1D2541405411A0C3139F1D11FC7552596C623764278DB6FF63B769930C4A51B5"
         retention="0"
         retentionString="Deletion Allowed"
         retentionClass=""
         ingestTime="1309442996"
         ingestTimeString="6/30/2011 10:09PM"
         hold="false"
         shred="false"
         dpl="1"
         index="true"
         customMetadata="false"
         state="created"
         version="83804351766017"
         />
</versions>

 */
