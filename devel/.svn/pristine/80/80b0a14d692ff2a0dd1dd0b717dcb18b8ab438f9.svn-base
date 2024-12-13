/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiDAO;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.DasarTuntutanCukai;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import etanah.util.DateUtil;
import etanah.util.FileUtil;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/rekod_penghantaran2")
public class RekodPenghantaran2ActionBean extends AbleActionBean  {
    private static final Logger LOG = Logger.getLogger(RekodPenghantaran2ActionBean.class);
    
    @Inject
    DasarTuntutanCukaiDAO dasarTuntutanCukaiDAO;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarHakmilikDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraHantarDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DasarTuntutanNotisDAO dasarNotisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    NotisPeringatan6AManager manager;
    @Inject
    GenerateIdPermohonanWorkflow gipw;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    private DasarTuntutanCukai dasarTuntutanCukai;
    private DasarTuntutanCukaiHakmilik dasarHakmilik;
    private Hakmilik hakmilik;
    private List<DasarTuntutanCukaiHakmilik> senaraiHakmilik;
    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<DasarTuntutanNotis> senaraiNotis;
    private static List<DasarTuntutanNotis> senaraiNotisStatic;
    private InfoAudit ia;
    private List<String> statusSampai = new ArrayList<String>();
    private List<String> caraHantar = new ArrayList<String>();
    private List<String> tarikhHantar = new ArrayList<String>();
    private List<String> tarikhTerima = new ArrayList<String>();
    private List<String> catatan = new ArrayList<String>();
    private String noDasar;
    private String kodStatus;
    private String idHakmilik;
    private String idNotis;
    private boolean visible = false;
    private String kodNegeri;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    // for upload and scan document
    FileBean fileToBeUploaded;
    private long idDokumen;
    private List<KodNotis> senaraiKodNotis = new ArrayList<KodNotis>();
    
    @DefaultHandler
    public Resolution showForm() {
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
//        senaraiKodNotis = new ArrayList<KodNotis>();
        senaraiKodNotis = manager.doSenaraiKodNotisHantar();
        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_penghantaran2.jsp");
    }
    
    public Resolution search() {
        String sama = null;
        setVisible(true);
        senaraiHakmilik = new ArrayList<DasarTuntutanCukaiHakmilik>();
        dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(noDasar);
        LOG.debug("noDasar :" + noDasar);
        LOG.debug("kodStatus :" + kodStatus);
        if(dasarTuntutanCukai != null){
            if (!kodStatus.equals("SPN")) { //filter selain surat peringatan(SPN)
                for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarHakmilik(dasarTuntutanCukai)) {
                    for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                        if(kodStatus.equals(dtn.getNotis().getKod()))
                            sama = "sama";
                    }
                    if(sama != null)
                        senaraiHakmilik.add(dtch);
                }
            }
        }
        else {
            setVisible(false);
            addSimpleError("No. Rujukan Dasar tidak tepat.");
        }
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        senaraiKodNotis = manager.doSenaraiKodNotisHantar();
        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_penghantaran2.jsp");
    }

    public Resolution rekodHantar(){
        hakmilik = hakmilikDAO.findById(idHakmilik);
        String[] nama = {"hakmilik"};
        Object[] value= {hakmilik};
        List<DasarTuntutanCukaiHakmilik> senaraiHakmilik1 = dasarHakmilikDAO.findByEqualCriterias(nama, value, null);
        LOG.info("noDasar :"+noDasar);
        LOG.info("kodStatus :"+kodStatus);
        senaraiNotis = new ArrayList<DasarTuntutanNotis>();
        for (DasarTuntutanCukaiHakmilik dtch : senaraiHakmilik1) {
            if(noDasar.equals(dtch.getDasarTuntutanCukai().getIdDasar())){
                int count = 0;
                for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                    LOG.info("count :"+count);
                    if(kodStatus.equals(dtn.getNotis().getKod())){
                        senaraiNotis.add(dtn);
                        if(dtn.getStatusTerima() != null)
                            statusSampai.add(count, dtn.getStatusTerima().getKod());
                        else
                            statusSampai.add(count, "-");
                        if(dtn.getCaraPenghantaran() != null)
                            caraHantar.add(count, dtn.getCaraPenghantaran().getKod());
                        else
                            caraHantar.add(count, "-");
                        if(dtn.getTarikhDihantar() != null)
                            tarikhHantar.add(count, sdf.format(dtn.getTarikhDihantar()));
                        else
                            tarikhHantar.add(count, "-");
                        if(dtn.getTarikhTerima() != null)
                            tarikhTerima.add(count, sdf.format(dtn.getTarikhTerima()));
                        else
                            tarikhTerima.add(count, "-");
                        if(dtn.getCatatanTerima() != null)
                            catatan.add(count, dtn.getCatatanTerima());
                        else
                            catatan.add(count, "-");
                        count++;
                    }
                }
            }
        }
        // for keep list senaraiNotis on session
        senaraiNotisStatic = new ArrayList<DasarTuntutanNotis>();
        senaraiNotisStatic = senaraiNotis;
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_penghantaran_pihak.jsp").addParameter("popup", "true");
    }

    public Resolution simpan(){
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        LOG.info("(simpan)statusSampai.size :"+statusSampai.size());
        LOG.info("(simpan)caraHantar.size :"+caraHantar.size());
        LOG.info("(simpan)tarikhHantar.size :"+tarikhHantar.size());
        LOG.info("(simpan)tarikhTerima.size :"+tarikhTerima.size());
        LOG.info("(simpan)catatan.size :"+catatan.size());
        int i = 0;
        try{
            for (DasarTuntutanNotis dtn : senaraiNotisStatic) {
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setStatusTerima(kodStatusTerimaDAO.findById(statusSampai.get(i)));
                dtn.setCaraPenghantaran(kodCaraHantarDAO.findById(caraHantar.get(i)));
                if(tarikhHantar.get(i) != null)
                    dtn.setTarikhDihantar(sdf.parse(tarikhHantar.get(i)));
                if("-".equals(tarikhTerima.get(i)))
                    dtn.setTarikhTerima(null);
                else
                    dtn.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                if("-".equals(catatan.get(i)))
                    dtn.setCatatanTerima(null);
                else
                    dtn.setCatatanTerima(catatan.get(i));
                dtn.setInfoAudit(ia);
                manager.updateNotis(dtn);
                i++;
            }
            addSimpleMessage("Maklumat berjaya disimpan.");
            LOG.info("idHakmilik :"+idHakmilik);
            if (kodStatus.equals("N6A") && idHakmilik != null) {
                hakmilik = hakmilikDAO.findById(idHakmilik);
                boolean idPermohonanXWujud = true;
                String[] name = {"hakmilik"};
                Object[] value= {hakmilik};
                List<DasarTuntutanCukaiHakmilik> senaraiDasarHakmilik = dasarHakmilikDAO.findByEqualCriterias(name, value, null);
                List<HakmilikPermohonan> senaraiHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarHakmilik) {
                    for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                        LOG.info("idPermohonan ED6A :"+hp.getPermohonan().getIdPermohonan());
                        if( "ED6A".equals(hp.getPermohonan().getKodUrusan().getKod()) && dtch.getPerserahanBatal6A() == null )
                            idPermohonanXWujud = false;
                        else
                            idPermohonanXWujud = true;
                    }
                }
                if(idPermohonanXWujud)
                    SediaEndorsan(p, hakmilik,  kodStatus);
            }
            if (kodStatus.equals("N8A") && idHakmilik != null) {
                hakmilik = hakmilikDAO.findById(idHakmilik);
                boolean idPermohonanXWujud = true;
                String[] name = {"hakmilik"};
                Object[] value= {hakmilik};
                List<DasarTuntutanCukaiHakmilik> senaraiDasarHakmilik = dasarHakmilikDAO.findByEqualCriterias(name, value, null);
                List<HakmilikPermohonan> senaraiHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarHakmilik) {
                    for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                        LOG.info("idPermohonan ED8A :"+hp.getPermohonan().getIdPermohonan());
                        if( "ED8A".equals(hp.getPermohonan().getKodUrusan().getKod()) && dtch.getPerserahanBatal8A() == null )
                            idPermohonanXWujud = false;
                        else
                            idPermohonanXWujud = true;
                    }
                }
                if(idPermohonanXWujud)
                    SediaEndorsan(p, hakmilik,  kodStatus);
            }
        }catch(Exception ex){
            addSimpleError("Maklumat tidak berjaya disimpan.");
            ex.printStackTrace(); // comment this for production
        }
//        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_penghantaran_pihak.jsp").addParameter("popup", "true");
        return rekodHantar();
    }

    public Resolution simpanRefresh(){
//        String result = "1";
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        LOG.info("(simpanRefresh)statusSampai.size :"+statusSampai.size());
        LOG.info("(simpanRefresh)caraHantar.size :"+caraHantar.size());
        LOG.info("(simpanRefresh)tarikhHantar.size :"+tarikhHantar.size());
        LOG.info("(simpanRefresh)tarikhTerima.size :"+tarikhTerima.size());
        LOG.info("(simpanRefresh)catatan.size :"+catatan.size());
        int i = 0;
        try{
            for (DasarTuntutanNotis dtn : senaraiNotisStatic) {
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setStatusTerima(kodStatusTerimaDAO.findById(statusSampai.get(i)));
                dtn.setCaraPenghantaran(kodCaraHantarDAO.findById(caraHantar.get(i)));
                if("-".equals(tarikhHantar.get(i)) || tarikhHantar.get(i) == null)
                    dtn.setTarikhDihantar(null);
                else
                    dtn.setTarikhDihantar(sdf.parse(tarikhHantar.get(i)));
                if("-".equals(tarikhTerima.get(i)) || tarikhTerima.get(i) == null)
                    dtn.setTarikhTerima(null);
                else
                    dtn.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                if("-".equals(catatan.get(i)) || catatan.get(i) == null)
                    dtn.setCatatanTerima(null);
                else
                    dtn.setCatatanTerima(catatan.get(i));
                dtn.setInfoAudit(ia);
                manager.updateNotis(dtn);
                i++;
            }
            LOG.debug("Maklumat berjaya disimpan.");
            LOG.info("idHakmilik :"+idHakmilik);
        }catch(Exception ex){
//            result = "0";
            LOG.error("masalah :"+ex);
            ex.printStackTrace();
        }
        return rekodHantar();
    }

    public void SediaEndorsan(Pengguna p,Hakmilik h, String ks) {
        String semuaTerima = null;
        String tidakTerima = null;
        KodUrusan ku = new KodUrusan();
        try {
            List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
            String[] name = {"hakmilik"};
            Object[] value = {h};
            senaraiDasarTuntutanCukaiHakmilik = dasarHakmilikDAO.findByEqualCriterias(name, value, null);
             if ("N6A".equals(ks)) {
                LOG.info("Endorsan untuk notis 6A.");
                ku = kodUrusanDAO.findById("ED6A");
                LOG.debug("senaraiDasarTuntutanCukaiHakmilik.size :"+senaraiDasarTuntutanCukaiHakmilik.size());
                for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik) {
                    if (!dtch.getHakmilik().getAkaunCukai().getBaki().equals(BigDecimal.ZERO)) { // check if hakmilik have 'baki' or not
                        for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                            LOG.debug("dtch.getSenaraiNotis().size :"+dtch.getSenaraiNotis().size());
                            if ("N6A".equals(dtn.getNotis().getKod()) && "TM".equals(dtn.getStatusTerima().getKod())) //check utk Notis 6A yg dapat disampaikan sahaja
                            {
                                semuaTerima = "ya";
                                LOG.debug("idNotis terima:"+dtn.getIdNotis());
                            }else if("N6A".equals(dtn.getNotis().getKod()) && "XT".equals(dtn.getStatusTerima().getKod())){
                                tidakTerima = "ya";
                                LOG.debug("idNotis tidak terima:"+dtn.getIdNotis());
                            }
                        }
                    }
                }
                if(tidakTerima == null){
                    LOG.debug("Semua idNotis dapat disampaikan. Generate permohonan diteruskan");
                    senaraiHakmilik.add(h);                
                    LOG.debug("senaraiHakmilik.size :"+senaraiHakmilik.size());
                    if (gipw.generateIdPermohonanWorkflowInternal(ku, p, senaraiHakmilik, "")) {
                        addSimpleMessage("Permohonan telah berjaya diwujudkan.");
                        manager.simpan6A(senaraiHakmilik, p); // add transaksi N6A = 10 for N9 while hakmilik dpt hantar notis6A
                    } else {
                        addSimpleError("Permohonan telah gagal diwujudkan");
                    }
                }else{
                    LOG.error("Semua idNotis setengah dapat disampaikan. Generate permohonan tidak diteruskan");
                }
            }
            else if("N8A".equals(ks)){
                LOG.info("Endorsan untuk notis 8A.");
                ku = kodUrusanDAO.findById("ED8A");
                for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik) {
                    if(!dtch.getHakmilik().getAkaunCukai().getBaki().equals(BigDecimal.ZERO)){ // check if hakmilik have 'baki' or not
                        for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                            if("N8A".equals(dtn.getNotis().getKod()) && "TM".equals(dtn.getStatusTerima().getKod())){ //check utk Notis 8A yg dapat disampaikan sahaja                                
                                semuaTerima = "ya";
                                LOG.debug("idNotis terima:"+dtn.getIdNotis());
                            }else if("N8A".equals(dtn.getNotis().getKod()) && "XT".equals(dtn.getStatusTerima().getKod())){
                                tidakTerima = "ya";
                                LOG.debug("idNotis tidak terima:"+dtn.getIdNotis());
                            }
                        }
                    }
                }
                if(tidakTerima == null){
                    LOG.debug("Semua idNotis dapat disampaikan. Generate permohonan diteruskan");
                    senaraiHakmilik.add(h);
                    if(gipw.generateIdPermohonanWorkflowInternal(ku, p, senaraiHakmilik, ""))
                        addSimpleMessage("Permohonan telah berjaya diwujudkan.");
                    else{
                        addSimpleError("Permohonan telah gagal diwujudkan");
                    }
                }else{
                    LOG.error("Semua idNotis setengah dapat disampaikan. Generate permohonan tidak diteruskan");
                }
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public Resolution reloadPage(){
        search();
        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_penghantaran2.jsp").addParameter("popup", "true");
    }

    public Resolution refreshBase(){
        LOG.debug("noDasar :"+noDasar);
        return showForm();
    }

    public Resolution popupUpload(){
//        idNotis = (String) getContext().getRequest().getSession().getAttribute("idNotis");
        LOG.info("idNotis :"+idNotis);
        LOG.info("idDasar :"+noDasar);
        LOG.info("idHakmilik :"+idHakmilik);
        LOG.info("kodStatus :"+kodStatus);
        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_upload_doc.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDoc(){
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = idNotis;
        DateUtil du = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try{
            if(p != null && fname != null && fileToBeUploaded != null){
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                                    + du.getDateName(String.valueOf(du.getMonth()+1))
                                    + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :"+namaFail);
                Dokumen doc = new Dokumen();
                DasarTuntutanNotis dtn = dasarNotisDAO.findById(Long.parseLong(fname));
                if(dtn.getDokumenBukti() != null){
                    doc = dtn.getDokumenBukti();
                    ia = dtn.getDokumenBukti().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                }else{
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE+File.separator+fname;
                doc.setKodDokumen(kd);
                doc.setTajuk(dtn.getNotis().getKod()+"-Salinan Penghantaran-"+fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = manager.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :"+idDoc);
                // update at DasarTuntutanNotis
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setDokumenBukti(dokumenDAO.findById(idDoc));
                dtn.setInfoAudit(ia);
                manager.updateNotis(dtn);
                addSimpleMessage("Muat naik fail berjaya.");
            }else{
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        }catch(Exception ex){
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_upload_doc.jsp").addParameter("popup", "true");
    }

    public Resolution popupScan(){
//        idNotis = (String) getContext().getRequest().getSession().getAttribute("idNotis");
        LOG.info("idNotis :"+idNotis);
        LOG.info("idDasar :"+noDasar);
        LOG.info("idHakmilik :"+idHakmilik);
        LOG.info("kodStatus :"+kodStatus);
        Date now = new java.util.Date();

        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String fname = idNotis;
        LOG.info("idNotis fname : " + fname);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        LOG.info("Pengguna :"+p.getIdPengguna());
        try{
            if(p != null && fname != null){
                Dokumen doc = new Dokumen();
                DasarTuntutanNotis dtn = dasarNotisDAO.findById(Long.parseLong(fname));
                if(dtn.getDokumenBukti() != null){
                    doc = dtn.getDokumenBukti();
                    ia = dtn.getDokumenBukti().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(now);
                }else{
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(now);
                }
                doc.setKodDokumen(kd);
                doc.setTajuk(dtn.getNotis().getKod()+"-Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
//                doc.setFormat("application/pdf");
                doc.setInfoAudit(ia);
                idDokumen = manager.simpanOrUpdateDokumen(doc);
                LOG.debug("scan: idDoc :"+idDokumen);
                // update at DasarTuntutanNotis
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setDokumenBukti(dokumenDAO.findById(idDokumen));
                dtn.setInfoAudit(ia);
                manager.updateNotis(dtn);
                LOG.debug("popup scan berjaya. Dokumen Berjaya Diwujudkan");
            }else{
                LOG.error("parameter tidak mencukupi.");
            }            
        }catch(Exception ex){
            LOG.error("popup scan tidak berjaya :"+ex);
            ex.printStackTrace();
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_scan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution updateDTN(){
        String dokumen = (String) getContext().getRequest().getSession().getAttribute("idDokumen");
        String results = "";
        LOG.info("updateDTN :: idNotis :"+idNotis);
        LOG.info("updateDTN :: idDokumen :"+dokumen);
        InfoAudit ia = new InfoAudit();
        Date now = new java.util.Date();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try{
            if(idNotis != null && dokumen != null){
                DasarTuntutanNotis dtn = dasarNotisDAO.findById(Long.parseLong(idNotis));
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(now);
                dtn.setDokumenBukti(dokumenDAO.findById(Long.parseLong(dokumen)));
                dtn.setInfoAudit(ia);
                manager.updateNotis(dtn);
                LOG.debug("Maklumat berkaitan Scan berjaya dikemaskini dalam DTN.");
                results = "berjaya";
            }else{
                LOG.error("Terdapat masalah pada parameter");
                results = "masalah";
            }
        }catch(Exception ex){
            LOG.error("Tidak berjaya dikemaskini :"+ex);
            results = "gagal";
            ex.printStackTrace(); // for development only
        }
        return new StreamingResolution("text/plain", results);
    }

    private List<DasarTuntutanCukaiHakmilik> senaraiDasarHakmilik(DasarTuntutanCukai dtc){
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.DasarTuntutanCukaiHakmilik dtch where dtch.dasarTuntutanCukai = :idDasar order by dtch.hakmilik asc");
        q.setString("idDasar", dtc.getIdDasar());
        return q.list();
    }

    public DasarTuntutanCukai getDasarTuntutanCukai() {
        return dasarTuntutanCukai;
    }

    public void setDasarTuntutanCukai(DasarTuntutanCukai dasarTuntutanCukai) {
        this.dasarTuntutanCukai = dasarTuntutanCukai;
    }

    public String getNoDasar() {
        return noDasar;
    }

    public void setNoDasar(String noDasar) {
        this.noDasar = noDasar;
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<String> getCaraHantar() {
        return caraHantar;
    }

    public void setCaraHantar(List<String> caraHantar) {
        this.caraHantar = caraHantar;
    }

    public List<String> getCatatan() {
        return catatan;
    }

    public void setCatatan(List<String> catatan) {
        this.catatan = catatan;
    }

    public List<String> getStatusSampai() {
        return statusSampai;
    }

    public void setStatusSampai(List<String> statusSampai) {
        this.statusSampai = statusSampai;
    }

    public List<String> getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(List<String> tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public List<String> getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(List<String> tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public List<DasarTuntutanNotis> getSenaraiNotis() {
        return senaraiNotis;
    }

    public void setSenaraiNotis(List<DasarTuntutanNotis> senaraiNotis) {
        this.senaraiNotis = senaraiNotis;
    }

    public DasarTuntutanCukaiHakmilik getDasarHakmilik() {
        return dasarHakmilik;
    }

    public void setDasarHakmilik(DasarTuntutanCukaiHakmilik dasarHakmilik) {
        this.dasarHakmilik = dasarHakmilik;
    }

    public static List<DasarTuntutanNotis> getSenaraiNotisStatic() {
        return senaraiNotisStatic;
    }

    public static void setSenaraiNotisStatic(List<DasarTuntutanNotis> senaraiNotisStatic) {
        RekodPenghantaran2ActionBean.senaraiNotisStatic = senaraiNotisStatic;
    }

    public InfoAudit getIa() {
        return ia;
    }

    public void setIa(InfoAudit ia) {
        this.ia = ia;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public List<KodNotis> getSenaraiKodNotis() {
        return senaraiKodNotis;
    }

    public void setSenaraiKodNotis(List<KodNotis> senaraiKodNotis) {
        this.senaraiKodNotis = senaraiKodNotis;
    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDasarTuntutanCukaiHakmilik() {
        return senaraiDasarTuntutanCukaiHakmilik;
    }

    public void setSenaraiDasarTuntutanCukaiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik) {
        this.senaraiDasarTuntutanCukaiHakmilik = senaraiDasarTuntutanCukaiHakmilik;
    }
}
