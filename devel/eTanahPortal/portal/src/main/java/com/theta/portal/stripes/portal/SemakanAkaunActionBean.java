/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.portal;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.service.portal.StatusPermohonanService;
import com.theta.portal.stripes.BaseActionBean;
import com.theta.portal.stripes.Configuration;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.theta.client.form.DokumenInfoClientForm;
import net.theta.client.form.StatusPermohonanForm;
import net.theta.client.form.StatusSemakanAkaunForm;
import net.theta.client.service.ClientSemakanAkaunService;
import org.apache.log4j.Logger;

@UrlBinding("/semakan_akaun")
public class SemakanAkaunActionBean extends BaseActionBean {

    @Inject
    StatusPermohonanService statusPermohonanService;
    @Inject
    ClientSemakanAkaunService clientSemakanAkaunService;
        @Inject
    Configuration conf;
    String url1;
    String id;
    String katgCarian;

    String idPermohonan;
    String status;
    String kodUrusan;
    String namaUrusan;
    String noAkaun;

    List<StatusPermohonanForm> listStatusPermohonan;
    List<StatusSemakanAkaunForm> listStatusSemakanAkaun;
    StatusSemakanAkaunForm akaunForm;

    private static final Logger LOGGER = Logger.getLogger(SemakanAkaunActionBean.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();
    private final static String TMP_DIR = System.getProperty("java.io.tmpdir");

    @DefaultHandler
    public Resolution mainPage() {

        return new JSP("/portal/semakan_akaun.jsp");
    }

    public Resolution cari() throws MalformedURLException {
url1  = conf.getProperty("url.webservice");
URL url = new URL(url1);
        if (katgCarian.equals("a")) {
            akaunForm = clientSemakanAkaunService.findStatusSemakanAkaun(id, url);

            if (akaunForm != null) {
                
                listStatusSemakanAkaun = new ArrayList<StatusSemakanAkaunForm>();
                listStatusSemakanAkaun.add(akaunForm);
            }

        } else if(katgCarian.equals("b")) {
          
            List<StatusSemakanAkaunForm> f = clientSemakanAkaunService.findStatusByNoKp(id,url);

            listStatusSemakanAkaun = new ArrayList<StatusSemakanAkaunForm>();
            if (f != null) {
                for (int i = 0; i < f.size(); i++) {
                    StatusSemakanAkaunForm a = f.get(i);
                    StatusSemakanAkaunForm b = new StatusSemakanAkaunForm();

                    b.setNoAkaun(a.getNoAkaun());
                    b.setStatus(a.getStatus());
                    b.setBaki(a.getBaki());
                    b.setIdHakmilik(a.getIdHakmilik());

                    listStatusSemakanAkaun.add(b);
                }
            }

        }else{
            akaunForm = clientSemakanAkaunService.findAkaunByIDHakmilik(id,url);

            if (akaunForm != null) {
                
                listStatusSemakanAkaun = new ArrayList<StatusSemakanAkaunForm>();
                listStatusSemakanAkaun.add(akaunForm);
            }
        }

        return new JSP("/portal/semakan_akaun.jsp");
    }

    public Resolution penafian() {
        return new JSP("/portal/semakan_akaun_popup.jsp").addParameter("popup", true);
    }

    public Resolution viewBil() throws MalformedURLException {
       url1  = conf.getProperty("url.webservice"); URL url = new URL(url1);
        LOGGER.info("MASUK VIEW");
        id = getContext().getRequest().getParameter("idHakmilik");
//getfrom parameter
        DokumenInfoClientForm d = clientSemakanAkaunService.muatTurunBilCukai(id,url);
        InputStream fis = null;
        boolean flag = false;

        BufferedInputStream bis = new BufferedInputStream(
                new ByteArrayInputStream(d.getBytes()));
//
        final BufferedInputStream b = bis;
//
        String filename = d.getFileName();
//                int sep = d.getNamaFizikal().lastIndexOf(File.separator);
//                if (sep >= 0) d.getNamaFizikal().substring(sep);
//                else filename = d.getNamaFizikal();
//                final String fn = filename;
//                
        getContext().getResponse().setContentType("application/octet-stream");
//                
        return new StreamingResolution(d.getDocType(), bis).setFilename(filename);
//                return null;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getNamaUrusan() {
        return namaUrusan;
    }

    public void setNamaUrusan(String namaUrusan) {
        this.namaUrusan = namaUrusan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKatgCarian() {
        return katgCarian;
    }

    public void setKatgCarian(String katgCarian) {
        this.katgCarian = katgCarian;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<StatusPermohonanForm> getListStatusPermohonan() {
        return listStatusPermohonan;
    }

    public void setListStatusPermohonan(List<StatusPermohonanForm> listStatusPermohonan) {
        this.listStatusPermohonan = listStatusPermohonan;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public List<StatusSemakanAkaunForm> getListStatusSemakanAkaun() {
        return listStatusSemakanAkaun;
    }

    public void setListStatusSemakanAkaun(List<StatusSemakanAkaunForm> listStatusSemakanAkaun) {
        this.listStatusSemakanAkaun = listStatusSemakanAkaun;
    }

}
