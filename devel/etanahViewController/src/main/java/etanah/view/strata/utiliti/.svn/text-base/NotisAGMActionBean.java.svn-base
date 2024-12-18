/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.Dokumen;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import etanah.model.HakmilikUrusan;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDokumen;
import etanah.model.KodSeksyen;
import etanah.model.Pengguna;
import etanah.report.CallableReport;
import etanah.report.ReportUtil;
import etanah.service.RegService;
import etanah.util.StringUtils;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.daftar.KutipanDataService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Query;

/**
 *
 * @author azri
 */
@UrlBinding("/strata/utiliti/notis_1st_AGM")
public class NotisAGMActionBean extends AbleActionBean {
    
    @Inject
    private StrataPtService strataPtService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KutipanDataService kutipanDataService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    RegService regService;
    @Inject
    ReportUtil reportUtilAGM;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private String trhMula;
    private String trhTamat;
    private Pengguna pguna;
    private Hakmilik hakmilik;
    private List<Hakmilik> listHakmilik;
    private List<HakmilikUrusan> listHakmilikUrusan = new ArrayList();
    private List<Hakmilik> listHakmilikInduk = new ArrayList();
    private List<KodBandarPekanMukim> senaraiBPM;
    private String idHakmilik;
    private String bandarPekanMukim;
    private String daerah;
    private String seksyen;
    private String lot;
    private String noLot;
    private String kodHakmilik;
    private String namaJenisHakmilik;
    private String noHakmilik;
    private List<KodSeksyen> senaraiKodSeksyen;
    private List<Map<String, String>> listNotisAGM;
    private static final Logger LOG = Logger.getLogger(NotisAGMActionBean.class);
    //  private static boolean isDebug = logger.isDebugEnabled();
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private static String NOTIS_AGM_MAIN = "strata/utiliti/notis_agm.jsp";
    
    @DefaultHandler
    public Resolution main() {
        return new JSP(NOTIS_AGM_MAIN);
    }
    
    public Resolution penyukatanBPM() {
        String kodDaerah = getDaerah();
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        senaraiBPM = q.list();
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/notis_agm.jsp");
    }
    
    public Resolution penyukatanSeksyen() {
        String kodDaerah = getContext().getRequest().getParameter("daerah");
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        senaraiBPM = q.list();
        String kodBPM = getBandarPekanMukim();
        //   Logger.debug("BPM-->" + kodBPM);
//        String sql = null;
//        Session s = sessionProvider.get();
//        Query q = null;
        if (kodBPM == null || kodBPM.equals("")) {
            sql = "select sek from KodSeksyen sek ";
            q = s.createQuery(sql);
        } else {
            sql = "select sek from KodSeksyen sek where sek.kodBandarPekanMukim.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodBPM);
        }
        senaraiKodSeksyen = q.list();
//        logger.debug("senaraiKodSeksyen-->" + senaraiKodSeksyen.get(0).getNama());
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/notis_agm.jsp");
    }
    
    public Resolution seterusnya() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();
        listNotisAGM = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        
        LOG.info("tarikh Mula : " + trhMula);
        LOG.info("tarikh Akhir : " + trhTamat);
        LOG.info("kodDaerah : " + daerah);
        LOG.info("kodSeksyen : " + seksyen);
        LOG.info("kodLot : " + lot);
        LOG.info("namaJenisHakmilik : " + namaJenisHakmilik);
        LOG.info("noHakmilik : " + noHakmilik);
        
        if (trhMula != null && trhTamat != null && idHakmilik !=null) {
             String msg = "";
                msg = "Sila buat carian mengikut Tarikh (atau) Hakmilik. Klik butang Isi Semula. ";
                addSimpleError(msg);
        }
       else if (trhMula != null && trhTamat != null && idHakmilik == null) {
            listHakmilikInduk = strataPtService.findListHakmilikInduk(trhMula, trhTamat);
            LOG.info("Size hakmilik Induk : " + listHakmilikInduk.size());
            
            if (!listHakmilikInduk.isEmpty()) {
                KodDokumen codeDoc = new KodDokumen();
                codeDoc.setKod("AGM");
                for (int i = 0; i < listHakmilikInduk.size(); i++) {
                    String idHmInduk = String.valueOf(listHakmilikInduk.get(i));
                    List<Hakmilik> lhm = strataPtService.findIdHakmilikByIdHakmilikInduk(idHmInduk);
                    int y = lhm.size(); // get size of hakmilik strata
                    String total = String.valueOf(y);
                    
                    listHakmilikUrusan = strataPtService.findListHakmilikUrusanPMTbyHakmilikInduk(idHmInduk);
                    int x = listHakmilikUrusan.size(); // size hakmilik strata that have urusan PMT    
                    String pmt = String.valueOf(x);
                    float p = x * 100 / y;
                    String percent = String.valueOf(p);
                    
                    long idDok = 0;
                    String tajukDok = "";
                    List<Dokumen> dok = kutipanDataService.findListDokumen(codeDoc, idHmInduk);
                    for (Dokumen d : dok) {
                        idDok = d.getIdDokumen();
                        tajukDok = d.getTajuk();
                        
                    }
                    
                    map = new HashMap<String, String>();
                    map.put("idHakmilikInduk", idHmInduk);
                    map.put("pmt", pmt);
                    map.put("total", total);
                    map.put("percent", percent);
                    String idDokumen = String.valueOf(idDok);
                    map.put("idDok", idDokumen);
                    map.put("tajukDok", tajukDok);
                    
                    listNotisAGM.add(map);
                }
            } else {
                String msg = "";
                msg = "Maaf. Tiada rekod dijumpai.";
                addSimpleError(msg);
            }
        }
//         List<Hakmilik> hm = strataPtService.findIDHakmilikInduk(daerah, bandarPekanMukim, seksyen, lot, noLot);
//        LOG.info("IDHM :" + idHakmilikInduk);
//        if (idHakmilik != null) {
       else {
//            LOG.info("Masuk >>>>>>>>>>>>>>>>>>>"+ hm.size());
//            idHakmilik = hm.get(0).getIdHakmilikInduk();
//            idHakmilik = "050505GRN00062371";
            listHakmilikInduk = strataPtService.findListHakmilikIndukByHakmilik(idHakmilik);
            LOG.info("Size hakmilik Induk 2 >>>>>>>>>>>>>> : " + listHakmilikInduk.size());
            LOG.info("idhakmilik" + idHakmilik);
            if (!listHakmilikInduk.isEmpty()) {
                KodDokumen codeDoc = new KodDokumen();
                codeDoc.setKod("AGM");
                for (int i = 0; i < listHakmilikInduk.size(); i++) {
                    String idHmInduk = String.valueOf(listHakmilikInduk.get(i));
                    List<Hakmilik> lhm = strataPtService.findIdHakmilikByIdHakmilikInduk(idHmInduk);
                    int y = lhm.size(); // get size of hakmilik strata
                    String total = String.valueOf(y);
                    
                    listHakmilikUrusan = strataPtService.findListHakmilikUrusanPMTbyHakmilikInduk(idHmInduk);
                    int x = listHakmilikUrusan.size(); // size hakmilik strata that have urusan PMT    
                    String pmt = String.valueOf(x);
                    float p = x * 100 / y;
                    String percent = String.valueOf(p);
                    
                    long idDok = 0;
                    String tajukDok = "";
                    List<Dokumen> dok = kutipanDataService.findListDokumen(codeDoc, idHmInduk);
                    for (Dokumen d : dok) {
                        idDok = d.getIdDokumen();
                        tajukDok = d.getTajuk();
                        
                    }
                    
                    map = new HashMap<String, String>();
                    map.put("idHakmilikInduk", idHmInduk);
                    map.put("pmt", pmt);
                    map.put("total", total);
                    map.put("percent", percent);
                    String idDokumen = String.valueOf(idDok);
                    map.put("idDok", idDokumen);
                    map.put("tajukDok", tajukDok);
                    
                    listNotisAGM.add(map);
                }
            } else {
                String msg = "";
                msg = "Maaf. Tiada rekod dijumpai.";
                addSimpleError(msg);
            }
            
        }
        LOG.info("Size listNotisAGM >> : " + listNotisAGM.size());
        return new JSP(NOTIS_AGM_MAIN);
    }
    
    public Resolution janaDokumen() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();
        
        String hm = getContext().getRequest().getParameter("id");
        LOG.info("--> id hakmilik : " + hm);
        
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        String dokumenPath = conf.getProperty("document.path");
        
        KodDokumen kodAGM = new KodDokumen();
        kodAGM.setKod("AGM");
        String notisAGM = "STRFirstAGM_NS"; // NOTIS PDF 
        Dokumen agm = saveOrUpdateDokumen(kodAGM, hm);
        
        String[] params = new String[]{"p_id_pguna", "p_id_hakmilik"};
        String[] values = new String[]{pguna.getIdPengguna(), hm};
        String pathAGM = File.separator + String.valueOf(agm.getIdDokumen());
        
        Future<byte[]> fagm = executor.submit(new CallableReport(reportUtilAGM, notisAGM, params, values, dokumenPath + pathAGM, pguna));
        File signAGM = new File(dokumenPath + pathAGM + ".sig");
        
        if (signAGM.exists()) {
            signAGM.delete();
        }
        
        try {
            fagm.get();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage(), ex);
        }
        
        updatePathDokumen(reportUtilAGM.getDMSPath(), agm.getIdDokumen(), reportUtilAGM.getContentType());
        if(idHakmilik != null){
             return new RedirectResolution(NotisAGMActionBean.class, "seterusnya").addParameter("idHakmilik", idHakmilik);
        } else {
             return new RedirectResolution(NotisAGMActionBean.class, "seterusnya").addParameter("trhMula", trhMula).addParameter("trhTamat", trhTamat);

        }
        
       }
    
    private Dokumen saveOrUpdateDokumen(KodDokumen kd, String id) {
        // USE THIS TO ADD OR UPDATE TABLE DOKUMEN
        InfoAudit ia = new InfoAudit();
        List<Dokumen> listdDokumen = kutipanDataService.findListDokumen(kd, id);
        for (Dokumen listdDoc : listdDokumen) {
            // UPDATE previous Dokumen
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
            listdDoc.setBaru('T');
            listdDoc.setInfoAudit(ia);
            dokumenService.saveOrUpdate(listdDoc);
        }

        // Add new Dokumen
        Dokumen doc = new Dokumen();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        doc.setBaru('Y');
        doc.setKlasifikasi(kodKlasifikasiDAO.findById(3));
        doc.setFormat("application/pdf");
        doc.setNoVersi("0");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc.setTajuk(kd.getKod());
        doc.setInfoAudit(ia);
        doc = dokumenService.saveOrUpdate(doc);
        
        return doc;
    }
    
    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        // USE THIS TO UPDATE NAMA_FIZIKAL
        Dokumen doc = dokumenService.findById(idDokumen);
        doc.setNamaFizikal(namaFizikal);
        doc.setFormat(format);
        dokumenService.saveOrUpdate(doc);
    }
    
    public Pengguna getPguna() {
        return pguna;
    }
    
    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }
    
    public String getTrhMula() {
        return trhMula;
    }
    
    public void setTrhMula(String trhMula) {
        this.trhMula = trhMula;
    }
    
    public String getTrhTamat() {
        return trhTamat;
    }
    
    public void setTrhTamat(String trhTamat) {
        this.trhTamat = trhTamat;
    }
    
    public List<HakmilikUrusan> getListHakmilikUrusan() {
        return listHakmilikUrusan;
    }
    
    public void setListHakmilikUrusan(List<HakmilikUrusan> listHakmilikUrusan) {
        this.listHakmilikUrusan = listHakmilikUrusan;
    }
    
    public List<Map<String, String>> getListNotisAGM() {
        return listNotisAGM;
    }
    
    public void setListNotisAGM(List<Map<String, String>> listNotisAGM) {
        this.listNotisAGM = listNotisAGM;
    }
    
    public Hakmilik getHakmilik() {
        return hakmilik;
    }
    
    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
    
    public List<Hakmilik> getListHakmilikInduk() {
        return listHakmilikInduk;
    }
    
    public void setListHakmilikInduk(List<Hakmilik> listHakmilikInduk) {
        this.listHakmilikInduk = listHakmilikInduk;
    }
    
    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }
    
    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }
    
    public String getDaerah() {
        return daerah;
    }
    
    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }
    
    public String getIdHakmilik() {
        return idHakmilik;
    }
    
    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }
    
    public String getSeksyen() {
        return seksyen;
    }
    
    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }
    
    public String getLot() {
        return lot;
    }
    
    public void setLot(String lot) {
        this.lot = lot;
    }
    
    public String getNoLot() {
        return noLot;
    }
    
    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }
    
    public String getKodHakmilik() {
        return kodHakmilik;
    }
    
    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }
    
    public List<KodSeksyen> getSenaraiKodSeksyen() {
        return senaraiKodSeksyen;
    }
    
    public void setSenaraiKodSeksyen(List<KodSeksyen> senaraiKodSeksyen) {
        this.senaraiKodSeksyen = senaraiKodSeksyen;
    }
    
    public String getNamaJenisHakmilik() {
        return namaJenisHakmilik;
    }
    
    public String getBandarPekanMukim() {
        return bandarPekanMukim;
    }
    
    public void setBandarPekanMukim(String bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }
    
    public void setNamaJenisHakmilik(String namaJenisHakmilik) {
        this.namaJenisHakmilik = namaJenisHakmilik;
    }
    
    public String getNoHakmilik() {
        return noHakmilik;
    }
    
    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }
    
    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }
    
    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }
    
    public List<Hakmilik> getListHakmilik() {
        return listHakmilik;
    }
    
    public void setListHakmilik(List<Hakmilik> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }
}
