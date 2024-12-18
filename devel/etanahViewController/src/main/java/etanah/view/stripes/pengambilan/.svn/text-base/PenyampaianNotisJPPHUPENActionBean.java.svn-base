

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenasihatUndangDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodStatusTerima;
import etanah.model.KodTransaksi;
import etanah.model.KodTuntut;
import etanah.model.Notis;
import etanah.model.PenasihatUndang;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanService;
import etanah.service.PengambilanAduanService;
import etanah.util.FileUtil;
import etanah.util.DateUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.common.NotisPenerimaanService;
import etanah.model.Pemohon;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import java.text.ParseException;
/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/penerimaan_notis_jpphupen")
public class PenyampaianNotisJPPHUPENActionBean extends AbleActionBean {

   @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanService permohonanservice;
    @Inject
    NotisDAO notisDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    LelongService lelongService;
    @Inject
    PengambilanAduanService aduanService;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    DasarTuntutanNotisDAO dasarNotisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    PenasihatUndangDAO penasihatUndangDAO;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    KodStatusTerimaDAO KodStatusTerimaDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private Notis notis;
    private String idPermohonan;
    private Notis notisPermohonan;
    private Notis notisPemohon;
    private List<KandunganFolder> listKandunganFolder;
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(PenyampaianNotisJPPHUPENActionBean.class);
    private String idNotis;
    private boolean btn = false;
    private boolean show = false;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PenghantarNotis penghantarNotis;
    private Pengguna pengguna;
    private long idDokumen;
    private PermohonanPihak pp;
    private Pemohon pemohon;
    private FasaPermohonan fasaPermohonan;
    private List<Notis> listNotisJPPH  = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonPB = new ArrayList<Notis>();
    private List<Integer> namaPengahantarP = new ArrayList<Integer>();

    private List<String> kodStatusTerimaP = new ArrayList<String>();
    private List<String> kodPenghantaranP = new ArrayList<String>();
    private List<String> catatanPenerimaanP = new ArrayList<String>();
    private List<String> tarikhHantarP = new ArrayList<String>();
    private List<String> tarikhTerimaP = new ArrayList<String>();
    private List<String> idDokumenListP = new ArrayList<String>();

    private Integer idPenghantarNotis = new Integer(0);
    private String kodStatusTerima;
    private String kodCaraPenghantaran;
    private String catatanPenerimaan;
    private String tarikhHantar;
    private String tarikhTerima;
    private List<String> idDokumenList = new ArrayList<String>();

    private HakmilikPermohonan hp;
    // for upload document
    FileBean fileToBeUploaded;
    private boolean showPP = false;

    public boolean isShowPP() {
        return showPP;
    }

    public void setShowPP(boolean showPP) {
        this.showPP = showPP;
    }

    @DefaultHandler
    public Resolution showForm() {
//        if (listNotis.size() == 0) {
//            show = true;
//            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
//        }
        return new JSP("pengambilan/melaka/penyampaianNotisJPPHUPEN.jsp").addParameter("tab", "true");
    }

    public Resolution popupPenghantarNotis() {
        return new JSP("lelong/penghantarNotis_popup_16H.jsp").addParameter("popup", "true");
    }

    public Resolution showForm4() {
        btn = true;
        return new JSP("pengambilan/notis_aduan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        LOG.info("------showForm3-----");
//        if (listNotis.size() == 0) {
//            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
//            show = true;
//            return new JSP("pengambilan/melaka/penyampaianNotisJPPHUPEN.jsp").addParameter("tab", "true");
//        }
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "1_17rekodbuktisampai");
            if(fasaPermohonan == null){
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "1_08rekodbuktisampai");
            }
            if(fasaPermohonan == null){
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "1_04rekodbuktisampai");
            }


            if(fasaPermohonan != null){
                System.out.println("fasapermohonan"+fasaPermohonan.getIdAliran());
                if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_04rekodbuktisampai")){
                    show = true;
                    notisPermohonan = notisPenerimaanService.getNotiskodNotisJPPH(idPermohonan, "JPP");
                    if (notisPermohonan == null) {
                        LOG.info("Belum ade lg...");
                        notisPermohonan = simpanNotisJPPH();
                    }
                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_08rekodbuktisampai")){
                    showPP=true;
                    pemohonNotis();

                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_17rekodbuktisampai")){
                    show = true;
                    notisPermohonan = notisPenerimaanService.getNotiskodNotisJPPH(idPermohonan, "UPE");
                    if (notisPermohonan == null) {
                        LOG.info("Belum ade lg...");
                        notisPermohonan = simpanNotisUPEN();
                    }
                }
                if(notisPermohonan != null){
                    if(notisPermohonan.getPenghantarNotis() != null)
                        idPenghantarNotis = notisPermohonan.getPenghantarNotis().getIdPenghantarNotis();
                    if(notisPermohonan.getKodStatusTerima() != null)
                        kodStatusTerima = notisPermohonan.getKodStatusTerima().getKod();
                    if(notisPermohonan.getKodCaraPenghantaran() != null)
                        kodCaraPenghantaran = notisPermohonan.getKodCaraPenghantaran().getKod();
                    if(notisPermohonan.getTarikhHantar() != null)
                        tarikhHantar = sdf.format(notisPermohonan.getTarikhHantar());
                    if(notisPermohonan.getTarikhTerima() != null)
                        tarikhTerima = sdf.format(notisPermohonan.getTarikhTerima());
                    if(notisPermohonan.getCatatanPenerimaan() != null)
                        catatanPenerimaan = notisPermohonan.getCatatanPenerimaan();
                    if(notisPermohonan.getBuktiPenerimaan() == null){
                        idDokumenList.add("");
                    }else{
                        idDokumenList.add(String.valueOf(notisPermohonan.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
            }
        }
        return new JSP("pengambilan/melaka/penyampaianNotisJPPHUPEN.jsp").addParameter("tab", "true");
    }

    public void simpanNotis() {
         Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
        LOG.info("Kod Jabatan : " + p.getFolderDokumen().getFolderId());
        listKandunganFolder = notisPenerimaanService.getListDokumenB(p.getFolderDokumen().getFolderId());
        pp = aduanService.findPihakByIdMohon(p.getIdPermohonan());
        hp =  aduanService.findHPByIdMohon(p.getIdPermohonan());

//        listKandunganFolder = lelongService.getListDokumen16H(permohonan.getFolderDokumen().getFolderId());
        listKandunganFolder = notisPenerimaanService.getListDokumenB(permohonan.getFolderDokumen().getFolderId());

        if (listKandunganFolder.size() != 0) {

            Dokumen dokumen = new Dokumen();

            for (KandunganFolder kk : listKandunganFolder) {
                dokumen = kk.getDokumen();
            }

            LOG.info("Kod Jabatan : " + permohonan.getKodUrusan().getJabatan());
            KodNotis kodNotis = new KodNotis();
            kodNotis = kodNotisDAO.findById("PB");
            LOG.info("kodNotis : " + kodNotis.getKod());
            if (dokumen.getKodDokumen() != null) {

                if (dokumen.getKodDokumen().getKod().equals("PB")) {

                    InfoAudit info = pengguna.getInfoAudit();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());

                    Notis notis2 = new Notis();
                    notis2.setPermohonan(permohonan);
                    notis2.setInfoAudit(info);
                    notis2.setTarikhNotis(new java.util.Date());
                    notis2.setKodNotis(kodNotis);
                    notis2.setPihak(pp);
                    notis2.setDokumenNotis(dokumen);
                    notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                    notisPenerimaanService.saveOrUpdateNotis(notis2);
                } else {
                    LOG.info("lain-lain..");
                }
            }
        }
    }

        public Notis simpanNotisJPPH() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);

        Dokumen dokumenPB = null;
        Notis notisPB = null;

        if(fasaPermohonan != null){
            dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "8NIL");
            if(dokumenPB != null){

                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                notisPB = notisPenerimaanService.getNotiskodNotisJPPH(idPermohonan,"JPP");
                if(notisPB == null){
                    Notis notis1 = new Notis();
                    notis1.setInfoAudit(info);
                    notis1.setPermohonan(p);
                    notis1.setTarikhNotis(new java.util.Date());
                    notis1.setKodNotis(kodNotisDAO.findById("JPP"));
                    notis1.setDokumenNotis(dokumenPB);
                    notis1.setJabatan(p.getKodUrusan().getJabatan());
                    notisPenerimaanService.saveOrUpdateNotis(notis1);

                }
            }

        }
        notisPB = notisPenerimaanService.getNotiskodNotisJPPH(idPermohonan,"JPP");
        return notisPB;
    }
    public Notis simpanNotisUPEN() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);

        Dokumen dokumenPB = null;
        Notis notisPB = null;

        if(fasaPermohonan != null){
            dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "8UPE");
            if(dokumenPB != null){

                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                notisPB = notisPenerimaanService.getNotiskodNotisJPPH(idPermohonan,"UPE");
                if(notisPB == null){
                    Notis notis1 = new Notis();
                    notis1.setInfoAudit(info);
                    notis1.setPermohonan(p);
                    notis1.setTarikhNotis(new java.util.Date());
                    notis1.setKodNotis(kodNotisDAO.findById("UPE"));
                    notis1.setDokumenNotis(dokumenPB);
                    notis1.setJabatan(p.getKodUrusan().getJabatan());
                    notisPenerimaanService.saveOrUpdateNotis(notis1);
                }
            }

        }
        notisPB = notisPenerimaanService.getNotiskodNotisJPPH(idPermohonan,"UPE");
        return notisPB;
    }
    public void pemohonNotis(){
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if(permohonan.getSenaraiPemohon() != null){
            pemohon = permohonan.getSenaraiPemohon().get(0);
        }

        if(pemohon != null){
            permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
            if(permohonanPihak == null){
                permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng,pemohon.getPihak());
            }
            if(permohonanPihak != null){
               if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_08rekodbuktisampai")){
                    notisPemohon = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NDP");
                    if(notisPemohon == null){
                        Dokumen dokumenPB = null;
                        dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "D125");
                        if(dokumenPB != null ){
                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(peng);
                            info.setTarikhMasuk(new java.util.Date());
                            Notis notis1 = new Notis();
                            notis1.setInfoAudit(info);
                            notis1.setPermohonan(permohonan);
                            notis1.setTarikhNotis(new java.util.Date());
                            notis1.setKodNotis(kodNotisDAO.findById("NDP"));
                            notis1.setPihak(permohonanPihak);
                            notis1.setDokumenNotis(dokumenPB);
                            notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis1);
                        }
                    }
                    notisPemohon = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NDP");

                }

            }
        }

        if (notisPemohon != null) {
            if(notisPemohon.getPenghantarNotis() != null)
                idPenghantarNotis = notisPemohon.getPenghantarNotis().getIdPenghantarNotis();
            if(notisPemohon.getKodStatusTerima() != null)
                kodStatusTerima = notisPemohon.getKodStatusTerima().getKod();
            if(notisPemohon.getKodCaraPenghantaran() != null)
                kodCaraPenghantaran = notisPemohon.getKodCaraPenghantaran().getKod();
            if(notisPemohon.getTarikhHantar() != null)
                tarikhHantar = sdf.format(notisPemohon.getTarikhHantar());
            if(notisPemohon.getTarikhTerima() != null)
                tarikhTerima = sdf.format(notisPemohon.getTarikhTerima());
            if(notisPemohon.getCatatanPenerimaan() != null)
                catatanPenerimaan = notisPemohon.getCatatanPenerimaan();
        }else{
            show = false;
            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
        }

    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
//            int count = hakmilik.getSenaraiPihakBerkepentingan().size();

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "1_17rekodbuktisampai");
        if(fasaPermohonan == null){
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "1_08rekodbuktisampai");
        }
        if(fasaPermohonan == null){
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "1_04rekodbuktisampai");
        }

        }
    }

    public Resolution simpanPenghantarNotis() {

        InfoAudit info = pengguna.getInfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = pengguna.getKodCawangan();
        penghantarNotis.setInfoAudit(info);
        penghantarNotis.setCawangan(caw);
        penghantarNotis.setAktif('Y');
        lelongService.saveOrUpdate(penghantarNotis);
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP("pengambilan/rekod_penghantaran_16H.jsp").addParameter("tab", "true");
    }

//    public Resolution popupUpload() {
//        LOG.info("idNotis :" + idNotis);
//        return new JSP("pengambilan/upload_831cUPEN.jsp").addParameter("popup", "true");
//    }
    public Resolution popupUpload() {
        LOG.info("idNotis :" + idNotis);
        String id = idNotis;
        idNotis = id;
        return new JSP("pengambilan/melaka/upload_file831cUPEN.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDoc() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
        fname = idNotis;
        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
                Dokumen doc = new Dokumen();
                Notis notis = notisDAO.findById(Long.parseLong(fname));
                if (notis.getBuktiPenerimaan() != null) {
                    doc = notis.getBuktiPenerimaan();
                    ia = notis.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kodDokumenDAO.findById("BPN"));
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                    ia = notis.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notis.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notis.setInfoAudit(ia);
                    lelongService.updateNotis(notis);
                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        return new JSP("pengambilan/melaka/upload_file831cUPEN.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDocPemohon() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPihak = (String) getContext().getRequest().getParameter("idPihak");
        if(idHakmilik != null && !idHakmilik.isEmpty()){
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, Long.parseLong(idPihak));

         fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "1_08rekodbuktisampai");
        Notis notisPemohonNBQ = null;


                if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_08rekodbuktisampai")){
                   notisPemohonNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NDF");
                }


        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
        if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_08rekodbuktisampai")){
                   fname = String.valueOf(notisPemohonNBQ.getIdNotis());
                }
        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
                Dokumen doc = new Dokumen();
                Notis notis = notisDAO.findById(Long.parseLong(fname));
                if (notis.getBuktiPenerimaan() != null) {
                    doc = notis.getBuktiPenerimaan();
                    ia = notis.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kodDokumenDAO.findById("BPN"));
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_08rekodbuktisampai")){
                    ia = notisPemohonNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPemohonNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisPemohonNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisPemohonNBQ);
                }

                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        return new JSP("pengambilan/melaka/upload_file831cUPEN.jsp").addParameter("popup", "true");
    }

    public Resolution popupScan() {

        LOG.info("idNotis :" + idNotis);
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String fname = idNotis;
        LOG.info("idNotis : " + fname);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try {
            if (p != null && fname != null) {
                Dokumen doc = new Dokumen();
                Notis dtn = notisDAO.findById(Long.parseLong(fname));
                if (dtn.getBuktiPenerimaan() != null) {
                    doc = dtn.getBuktiPenerimaan();
                    ia = dtn.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                doc.setKodDokumen(kd);
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat("application/pdf/image/gif");
                doc.setInfoAudit(ia);
                idDokumen = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDokumen);
                // update at DasarTuntutanNotis
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setBuktiPenerimaan(dokumenDAO.findById(idDokumen));
                dtn.setInfoAudit(ia);
                lelongService.updateNotis(dtn);
            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/melaka/scan_doc_831cUPEN.jsp").addParameter("popup", "true");
    }

    public Resolution simpanNew() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        InfoAudit info;

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "1_17rekodbuktisampai");
        if(fasaPermohonan == null){
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "1_08rekodbuktisampai");
        }
        if(fasaPermohonan == null){
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "1_04rekodbuktisampai");
        }

//        if(idHakmilik != null && !idHakmilik.isEmpty()){
//            showHP = "true";
//            hakmilik = hakmilikDAO.findById(idHakmilik);
         if(fasaPermohonan != null){
            if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_04rekodbuktisampai")){
                notisPermohonan = notisPenerimaanService.getNotiskodNotisJPPH(idPermohonan,"JPP");
                if(notisPermohonan != null){
                        info = notisPermohonan.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisPermohonan.setInfoAudit(info);
                        notisPermohonan.setPenghantarNotis(penghantarNotisDAO.findById(idPenghantarNotis));
                        notisPermohonan.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima));
                        notisPermohonan.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodCaraPenghantaran));
                        notisPermohonan.setTarikhHantar(sdf.parse(tarikhHantar));
                        notisPermohonan.setTarikhTerima(sdf.parse(tarikhTerima));
                        notisPermohonan.setCatatanPenerimaan(catatanPenerimaan);
                        notisPenerimaanService.saveOrUpdateNotis(notisPermohonan);
                    }
                show = true;
                notisPermohonan = notisPenerimaanService.getNotiskodNotisJPPH(idPermohonan,"JPP");
            } else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_08rekodbuktisampai")){
                if(permohonan.getSenaraiPemohon() != null){
                    pemohon = permohonan.getSenaraiPemohon().get(0);
                }
                if(pemohon != null){
                    permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                    notisPemohon = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NDP");
                    if(notisPemohon != null){
                        info = notisPemohon.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisPemohon.setInfoAudit(info);
                        notisPemohon.setPenghantarNotis(penghantarNotisDAO.findById(idPenghantarNotis));
                        notisPemohon.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima));
                        notisPemohon.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodCaraPenghantaran));
                        notisPemohon.setTarikhHantar(sdf.parse(tarikhHantar));
                        notisPemohon.setTarikhTerima(sdf.parse(tarikhTerima));
                        notisPemohon.setCatatanPenerimaan(catatanPenerimaan);
                        notisPenerimaanService.saveOrUpdateNotis(notisPemohon);
                    }
                    showPP = true;
                    notisPemohon = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NDP");

                }
            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_17rekodbuktisampai")){
                    notisPermohonan = notisPenerimaanService.getNotiskodNotisJPPH(idPermohonan,"UPE");
                    if(notisPermohonan != null){
                        info = notisPermohonan.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisPermohonan.setInfoAudit(info);
                        notisPermohonan.setPenghantarNotis(penghantarNotisDAO.findById(idPenghantarNotis));
                        notisPermohonan.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima));
                        notisPermohonan.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodCaraPenghantaran));
                        notisPermohonan.setTarikhHantar(sdf.parse(tarikhHantar));
                        notisPermohonan.setTarikhTerima(sdf.parse(tarikhTerima));
                        notisPermohonan.setCatatanPenerimaan(catatanPenerimaan);
                        notisPenerimaanService.saveOrUpdateNotis(notisPermohonan);
                    }
                    show = true;
                    notisPermohonan = notisPenerimaanService.getNotiskodNotisJPPH(idPermohonan,"UPE");

            }
            if(notisPermohonan != null){
                    if(notisPermohonan.getPenghantarNotis() != null)
                        idPenghantarNotis = notisPermohonan.getPenghantarNotis().getIdPenghantarNotis();
                    if(notisPermohonan.getKodStatusTerima() != null)
                        kodStatusTerima = notisPermohonan.getKodStatusTerima().getKod();
                    if(notisPermohonan.getKodCaraPenghantaran() != null)
                        kodCaraPenghantaran = notisPermohonan.getKodCaraPenghantaran().getKod();
                    if(notisPermohonan.getTarikhHantar() != null)
                        tarikhHantar = sdf.format(notisPermohonan.getTarikhHantar());
                    if(notisPermohonan.getTarikhTerima() != null)
                        tarikhTerima = sdf.format(notisPermohonan.getTarikhTerima());
                    if(notisPermohonan.getCatatanPenerimaan() != null)
                        catatanPenerimaan = notisPermohonan.getCatatanPenerimaan();
             }else if (notisPemohon != null) {
                if(notisPemohon.getPenghantarNotis() != null)
                    idPenghantarNotis = notisPemohon.getPenghantarNotis().getIdPenghantarNotis();
                if(notisPemohon.getKodStatusTerima() != null)
                    kodStatusTerima = notisPemohon.getKodStatusTerima().getKod();
                if(notisPemohon.getKodCaraPenghantaran() != null)
                    kodCaraPenghantaran = notisPemohon.getKodCaraPenghantaran().getKod();
                if(notisPemohon.getTarikhHantar() != null)
                    tarikhHantar = sdf.format(notisPemohon.getTarikhHantar());
                if(notisPemohon.getTarikhTerima() != null)
                    tarikhTerima = sdf.format(notisPemohon.getTarikhTerima());
                if(notisPemohon.getCatatanPenerimaan() != null)
                    catatanPenerimaan = notisPemohon.getCatatanPenerimaan();
            }
        }

//        }
//          listNotisPemohonPB = new ArrayList<Notis>();
//        String PP = (String)getContext().getRequest().getParameter("showPP");
//        if(PP.equalsIgnoreCase("true")){
//            showPP = true;
//            for(int i=0;i < permohonan.getSenaraiPemohon().size(); i++){
//                Pemohon pemohon = permohonan.getSenaraiPemohon().get(i);
//                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
//                if(permohonanPihak != null){
//                    Notis notisPemohonPB = null;
//
//                    if(fasaPermohonan.getIdAliran().equalsIgnoreCase("1_08rekodbuktisampai")){
//                        notisPemohonPB = notisPenerimaanService.getNotiskodNotisJPPH(idNotis,"NDP");
//
//                    }
//                    if(notisPemohonPB != null) {
//                        info = notisPemohonPB.getInfoAudit();
//                        info.setDiKemaskiniOleh(peng);
//                        info.setTarikhKemaskini(new java.util.Date());
//                        notisPemohonPB.setInfoAudit(info);
//                        notisPemohonPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarP.get(i)));
//                        notisPemohonPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaP.get(i)));
//                        notisPemohonPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranP.get(i)));
//                        notisPemohonPB.setTarikhHantar(sdf.parse(tarikhHantarP.get(i)));
//                        notisPemohonPB.setTarikhTerima(sdf.parse(tarikhTerimaP.get(i)));
//                        notisPemohonPB.setCatatanPenerimaan(catatanPenerimaanP.get(i));
//                        notisPenerimaanService.saveOrUpdateNotis(notisPemohonPB);
//                        listNotisPemohonPB.add(notisPemohonPB);
//                    }
//                }
//            }
//        }

        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP("pengambilan/melaka/penyampaianNotisJPPHUPEN.jsp").addParameter("tab", "true");
    }


    public Resolution reload() {
        rehydrate();
        return new JSP("pengambilan/aduan_scan_doc.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public PenghantarNotis getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public PermohonanPihak getPp() {
        return pp;
    }

    public void setPp(PermohonanPihak pp) {
        this.pp = pp;
    }


    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }
    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }
    public List<Notis> getListNotisJPPH() {
        return listNotisJPPH;
    }

    public void setListNotisJPPH(List<Notis> listNotisJPPH) {
        this.listNotisJPPH = listNotisJPPH;
    }

    public List<String> getCatatanPenerimaanP() {
        return catatanPenerimaanP;
    }

    public void setCatatanPenerimaanP(List<String> catatanPenerimaanP) {
        this.catatanPenerimaanP = catatanPenerimaanP;
    }

    public List<String> getIdDokumenListP() {
        return idDokumenListP;
    }

    public void setIdDokumenListP(List<String> idDokumenListP) {
        this.idDokumenListP = idDokumenListP;
    }

    public List<String> getKodPenghantaranP() {
        return kodPenghantaranP;
    }

    public void setKodPenghantaranP(List<String> kodPenghantaranP) {
        this.kodPenghantaranP = kodPenghantaranP;
    }

    public List<String> getKodStatusTerimaP() {
        return kodStatusTerimaP;
    }

    public void setKodStatusTerimaP(List<String> kodStatusTerimaP) {
        this.kodStatusTerimaP = kodStatusTerimaP;
    }

    public List<Integer> getNamaPengahantarP() {
        return namaPengahantarP;
    }

    public void setNamaPengahantarP(List<Integer> namaPengahantarP) {
        this.namaPengahantarP = namaPengahantarP;
    }

    public List<String> getTarikhHantarP() {
        return tarikhHantarP;
    }

    public void setTarikhHantarP(List<String> tarikhHantarP) {
        this.tarikhHantarP = tarikhHantarP;
    }

    public List<String> getTarikhTerimaP() {
        return tarikhTerimaP;
    }

    public void setTarikhTerimaP(List<String> tarikhTerimaP) {
        this.tarikhTerimaP = tarikhTerimaP;
    }


    public List<String> getIdDokumenList() {
        return idDokumenList;
    }

    public void setIdDokumenList(List<String> idDokumenList) {
        this.idDokumenList = idDokumenList;
    }

    public String getCatatanPenerimaan() {
        return catatanPenerimaan;
    }

    public void setCatatanPenerimaan(String catatanPenerimaan) {
        this.catatanPenerimaan = catatanPenerimaan;
    }

    public Integer getIdPenghantarNotis() {
        return idPenghantarNotis;
    }

    public void setIdPenghantarNotis(Integer idPenghantarNotis) {
        this.idPenghantarNotis = idPenghantarNotis;
    }

    public String getKodCaraPenghantaran() {
        return kodCaraPenghantaran;
    }

    public void setKodCaraPenghantaran(String kodCaraPenghantaran) {
        this.kodCaraPenghantaran = kodCaraPenghantaran;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public List<Notis> getListNotisPemohonPB() {
        return listNotisPemohonPB;
    }

    public void setListNotisPemohonPB(List<Notis> listNotisPemohonPB) {
        this.listNotisPemohonPB = listNotisPemohonPB;
    }

    public Notis getNotisPermohonan() {
        return notisPermohonan;
    }

    public void setNotisPermohonan(Notis notisPermohonan) {
        this.notisPermohonan = notisPermohonan;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(String tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public Notis getNotisPemohon() {
        return notisPemohon;
    }

    public void setNotisPemohon(Notis notisPemohon) {
        this.notisPemohon = notisPemohon;
    }




}