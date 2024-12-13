/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import com.google.inject.Inject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import etanah.dao.HakmilikDAO;
import etanah.model.Hakmilik;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author fikri
 */
public class ETappUtil {
    
    @Inject
    private HakmilikDAO hakmilikDAO;
    
    @Inject
    private  etanah.Configuration conf;       

    //TODO : put in etanah2.properties
    private static final String ROOT_URL = "http://localhost:8081/etapp/";

    private static final Logger LOG = Logger.getLogger(ETappUtil.class);
    
    public static final int SC_CREATED   = 201;
    public static final int SC_OK        = 200;
    public static final int SC_FORBIDDEN = 403;
    public static final int SC_NOT_FOUND = 404;
    public static final int SC_CONFLICT  = 409;
    public static final int SC_TOO_LARGE = 413;
    
    
    /*
    *   since we have connection problem to eTapp, thus we have to test it first
    *   to make sure connection is good enough to send data
    */
    private void doTestConnection() {
         
        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL("http://175.28.12.80").openConnection();
            HttpURLConnection connection = (HttpURLConnection) new URL("http://202.75.7.226").openConnection();
//            LOG.info("connection.getResponseCode()" + connection.getResponseCode());
        } catch (IOException ex) {
            LOG.error("IOException ", ex);
        } 
    }
    

    
    private String doPost(String input, String actionToPerform) {
        String val = "400";
        int retcode = SC_FORBIDDEN;        
//        Client client = Client.create();
//        try {
//
//            client.setReadTimeout(10000);
//            client.setConnectTimeout(10000);
//            WebResource webResource = client
//                    .resource(ROOT_URL + actionToPerform);
//
//            ClientResponse response = webResource
//                    .accept("application/json")
//                    .type("application/json")
//                    .post(ClientResponse.class, input);            
//
//            val = String.valueOf(response.getStatus());
//
//        } catch (Exception ex) {
//            LOG.error("ex {}", ex);
//        }        
        
        doTestConnection();
        
        HttpClient client = new HttpClient();        
        PostMethod post = new PostMethod(ROOT_URL + actionToPerform);
        LOG.debug("setting connection timeout");
        client.getHttpConnectionManager().getParams().setConnectionTimeout(100000);
        LOG.debug("setting socket timeout");
        client.getHttpConnectionManager().getParams().setSoTimeout(100000);
            post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                                    new DefaultHttpMethodRetryHandler(0, false));
       
        try{
            StringRequestEntity requestEntity = new StringRequestEntity(
                input,
                "application/json",
                "UTF-8");
            post.setRequestEntity(requestEntity);    
            LOG.debug("execute");
            retcode = client.executeMethod(post);     
            LOG.debug("finish " + retcode);
            
        }catch (UnsupportedEncodingException ex) {
            LOG.error("UnsupportedEncodingException", ex);
        }catch(IOException ex) {
            LOG.error("IOException", ex);
        }finally{
            if (post !=null) post.releaseConnection();
        }
        
//        client.destroy();
        return String.valueOf(retcode);

    }
    
    private String doPostHakmilikFed(String input, String actionToPerform) {
        
        doTestConnection();
        String ROOT = "http://localhost:8083/etapp_fed/";
        String val = "400";
        int retcode = SC_FORBIDDEN;    
        HttpClient client = new HttpClient();        
        PostMethod post = new PostMethod(ROOT + actionToPerform);
        LOG.debug("setting connection timeout");
        client.getHttpConnectionManager().getParams().setConnectionTimeout(100000);
        LOG.debug("setting socket timeout");
        client.getHttpConnectionManager().getParams().setSoTimeout(100000);
            post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                                    new DefaultHttpMethodRetryHandler(0, false));
        
//        Client client = Client.create();
//        try {
//
//            client.setReadTimeout(10000);
//            client.setConnectTimeout(10000);
//            WebResource webResource = client
//                    .resource(ROOT + actionToPerform);
//
//            ClientResponse response = webResource
//                    .accept("application/json")
//                    .type("application/json")
//                    .post(ClientResponse.class, input);            
//
//            val = String.valueOf(response.getStatus());
//
//        } catch (Exception ex) {
//            LOG.error("ex {}", ex);
//        }        
        
        try{
            StringRequestEntity requestEntity = new StringRequestEntity(
                input,
                "application/json",
                "UTF-8");
            post.setRequestEntity(requestEntity);    
            LOG.debug("execute");
            retcode = client.executeMethod(post);     
            LOG.debug("finish " + retcode);
            
        }catch (UnsupportedEncodingException ex) {
            LOG.error("UnsupportedEncodingException", ex);
        }catch(IOException ex) {
            LOG.error("IOException", ex);
        }finally{
            if (post !=null) post.releaseConnection();
        }

//        return val;
         return String.valueOf(retcode);

    }
    
    public String dokumenToMyEtapp(List<Map<String,String>> listDocuments) { 
        LOG.info("dokumenToMyEtapp");
        return doPost(formatJsonAttachment(listDocuments), "dokumenToMyEtapp");
    }

    public String updateKeputusanMMK(String borang, String flag, String stringflag, String nofail, Date date) {
        LOG.info("updateKeputusanMMK");
        return doPost(formatJsonMMK(borang, flag, stringflag, nofail, date), "updateKeputusanMMK");
    }

    public String endorsan(List<Map<String,String>> endorsanInfos) {
        LOG.info("endorsan");
        return doPost(formatJsonEndorsan(endorsanInfos),"endorsan");
    }
    
    public String insertHakmilikFederal(Hakmilik hm) {
        LOG.info("insertHakmilikFederal");
        return doPostHakmilikFed(formatJsonHakmilik(hm), "insert_hakmilik");
    }

    private String formatJsonMMK(String borang, String flag, String stringflag, String nofail, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        JSONObject obj = new JSONObject();
        obj.put("borang", borang);
        obj.put("flag", flag);
        obj.put("stringflag", stringflag);
        obj.put("nofail", nofail);
        obj.put("date", sdf.format(date));

        return obj.toJSONString();
    }

    private String formatJsonAttachment(List<Map<String, String>> listDocuments) {

//        JSONArray list = new JSONArray();
//        
//        for (Map<String,String> map : listDocuments) {
//            JSONObject obj = new JSONObject();
//            obj.put("filePath", map.get("path"));
//            obj.put("flag", map.get("borang"));
//            obj.put("documentName", map.get("fileName"));
//            obj.put("documentFormatFile", map.get("contentType"));
//            obj.put("documentFile", map.get("contentType"));
//            obj.put("documentTitle", map.get("title"));
//            obj.put("documentSize", map.get("length"));
//            obj.put("fileNoJkptg", map.get("failNo"));
//            list.add(obj);
//        }   
        JSONObject obj = new JSONObject();
        if (!listDocuments.isEmpty()) {
            Map<String, String> map = listDocuments.get(0);
            obj.put("filePath", map.get("path"));
            obj.put("flag", map.get("borang"));
            obj.put("documentName", map.get("fileName"));
            obj.put("documentFormatFile", map.get("contentType"));
            obj.put("documentFile", map.get("contentType"));
            obj.put("documentTitle", map.get("title"));
            obj.put("documentSize", map.get("length"));
            obj.put("fileNoJkptg", map.get("failNo"));
        }

        return obj.toJSONString();

    }

    private String formatJsonEndorsan(List<Map<String, String>> endorsanInfos) {

        JSONArray list = new JSONArray();
        
        for (Map<String, String> map : endorsanInfos) {
            JSONObject obj = new JSONObject();
            obj.put("flagProsess", map.get("flag"));
            obj.put("noJilid", map.get("noJilid"));
            obj.put("tkhEndorsan", map.get("tkhEndorsan"));
            obj.put("noFailJkptg", map.get("noFailJkptg"));
            obj.put("idHakmilik", map.get("idHakmilik"));
            obj.put("kodDaerah", map.get("kodDaerah"));
            obj.put("kodMukim", map.get("kodMukim"));
            obj.put("kodUnitHakmilik", map.get("kodUnitHakmilik"));
            obj.put("noHakmilik", map.get("noHakmilik"));
            list.add(obj);
        }

        return list.toJSONString();
    }
    
    private String formatJsonHakmilik (Hakmilik hakmilik) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        JSONObject obj = new JSONObject();
        obj.put("cukai", hakmilik.getCukaiSebenar());
        obj.put("idDaerah", hakmilik.getDaerah().getKod());
        obj.put("idHakmilik", hakmilik.getIdHakmilik());
        obj.put("idJenisHakmilik", hakmilik.getKodHakmilik()!= null?hakmilik.getKodHakmilik().getKod():null);
        obj.put("idJenisRizab", hakmilik.getRizab()!= null? hakmilik.getRizab().getKod():null);
        obj.put("idKategori", hakmilik.getKategoriTanah()!=null?hakmilik.getKategoriTanah().getKod():null);
        obj.put("idLot", hakmilik.getLot()!= null?hakmilik.getLot().getKod():null);
        obj.put("idLuas", hakmilik.getKodUnitLuas()!=null?hakmilik.getKodUnitLuas().getKod():null);
        obj.put("idMukim", hakmilik.getBandarPekanMukim()!=null?hakmilik.getBandarPekanMukim().getbandarPekanMukim():null);
        obj.put("idNegeri", conf.getProperty("kodNegeri"));
        obj.put("idSubkategori", null);
        obj.put("kawasanRizab", hakmilik.getRizab()!=null?hakmilik.getRizab().getNama():null);
        obj.put("kegunaanTanah", hakmilik.getKegunaanTanah()!=null?hakmilik.getKegunaanTanah().getKodMyEtapp():null);
        obj.put("lokasi", hakmilik.getLokasi());
        obj.put("luas", hakmilik.getLuas());
        obj.put("noBangunan", hakmilik.getNoBangunan());
        obj.put("noFailPTD", null);
        obj.put("noFailPTG", null);
        obj.put("noHakmilik", hakmilik.getNoHakmilik());
        obj.put("noLot", hakmilik.getNoLot());
        obj.put("noPU", hakmilik.getNoPu());
        obj.put("noPelan", hakmilik.getNoPelan());
        obj.put("noPetak", hakmilik.getNoPetak());
        obj.put("noRizab", hakmilik.getRizabNoWarta());
        obj.put("noSyit", hakmilik.getNoLitho());
        obj.put("noTingkat", hakmilik.getNoTingkat());
        obj.put("noWarta", hakmilik.getRizabNoWarta());
        obj.put("sekatan", hakmilik.getSekatanKepentingan()!=null?hakmilik.getSekatanKepentingan().getSekatan():null);
        obj.put("statusHakmilik", "telah dimiliki oleh Pesuruhjaya tanah persekutuan");
        obj.put("statusPajakan", String.valueOf(hakmilik.getPegangan()));
        obj.put("statusRizab", null);
        obj.put("syaratNyata", hakmilik.getSyaratNyata()!=null?hakmilik.getSyaratNyata().getSyarat():null);
        obj.put("tarafHakmilik", null);
        obj.put("tarikhDaftar", hakmilik.getTarikhDaftar()!=null?sdf.format(hakmilik.getTarikhDaftar()):null);
        obj.put("tarikhLuput", hakmilik.getTarikhLuput()!=null?sdf.format(hakmilik.getTarikhLuput()):null);
        obj.put("tarikhRizab", null);
        obj.put("tarikhTamatPajakan", null);
        obj.put("tarikhWarta", null);
        obj.put("tempoh", null);
       
        LOG.info(obj.toJSONString());
        
        return obj.toJSONString();
    }

    public static void main(String[] args) {
        String val = new ETappUtil().updateKeputusanMMK("BorangC", "Y", "TEST_20062014_0003", "JKPTG(S).MLK/01/881/18/2012/10", new Date());
        System.out.println("vall ===" + val);
    }

}
