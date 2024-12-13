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
import etanah.dao.HakmilikDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenasihatUndangDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
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
import etanah.dao.KodJenisPihakBerkepentinganDAO;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/notis_borangB")
public class NotisBorangBActionBean extends AbleActionBean {

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
    HakmilikDAO hakmilikDAO;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    private Hakmilik hakmilik;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private Notis notis;
    private String idPermohonan;
    private List<Notis> listNotis;
    private List<KandunganFolder> listKandunganFolder;
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Integer> namaPengahantar = new ArrayList<Integer>();

    private List<String> kodStatusTerima = new ArrayList<String>();
    private List<String> kodPenghantaran = new ArrayList<String>();
    private List<String> catatanPenerimaan = new ArrayList<String>();
    private List<String> tarikhHantar = new ArrayList<String>();
    private List<String> tarikhTerima = new ArrayList<String>();
    private List<String> idDokumenList = new ArrayList<String>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(NotisBorangBActionBean.class);
    private String idNotis;
    private boolean btn = false;
    private boolean show = false;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PenghantarNotis penghantarNotis;
    private Pengguna pengguna;
    private long idDokumen;
    private PermohonanPihak pp;
    private Pemohon pemohon;
    private String idHakmilik;

    private HakmilikPermohonan hp;
    // for upload document
    FileBean fileToBeUploaded;

    @DefaultHandler
    public Resolution showForm() {
//        if (listNotis.size() == 0) {
//            show = true;
//            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
//        }
        return new JSP("pengambilan/NotisBorangBSek4.jsp").addParameter("tab", "true");
    }

    public Resolution popupPenghantarNotis() {
        return new JSP("lelong/penghantarNotis_popup_16H.jsp").addParameter("popup", "true");
    }

    public Resolution showForm4() {
        btn = true;
        return new JSP("pengambilan/NotisBorangBSek4.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        LOG.info("------showForm3-----");
        if (listNotis.size() == 0) {
            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
            show = true;
            return new JSP("pengambilan/NotisBorangBSek4.jsp").addParameter("tab", "true");
        }
        List<PermohonanTuntutanKos> pt = lelongService.listPermohonanTuntutanKos2(idPermohonan);
        if (pt.size() == 0) {
            for (PermohonanPihak pp : senaraiPermohonanPihak) {
                if (pp.getStatusMohonPihak() != null) {
                    if (pp.getStatusMohonPihak().getKod().equals("PG")) {
                        permohonanPihak = pp;
                        break;
                    }
                }
            }
            LOG.info("------permohonanTuntutanKos-----");
            permohonanTuntutanKos = new PermohonanTuntutanKos();
            KodCawangan caw = pengguna.getKodCawangan();
            InfoAudit info = pengguna.getInfoAudit();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            KodTuntut kodTuntut = null;
            kodTuntut = kodTuntutDAO.findById("L01");
            permohonanTuntutanKos.setKodTuntut(kodTuntut);
            permohonanTuntutanKos.setInfoAudit(info);
            permohonanTuntutanKos.setItem("Bayaran Penyediaan 16H");
            permohonanTuntutanKos.setCawangan(caw);
            permohonanTuntutanKos.setPermohonan(permohonan);
            permohonanTuntutanKos.setAmaunTuntutan(BigDecimal.valueOf(80));
            permohonanTuntutanKos.setPihak(permohonanPihak);
            KodTransaksi kodTransaksi = kodTransaksiDAO.findById("72499");
            LOG.info("kodTransaksi : " + kodTransaksi.getKod());
            permohonanTuntutanKos.setKodTransaksi(kodTransaksi);
            lelongService.saveOrUpdate(permohonanTuntutanKos);

            permohonanTuntutanKos = new PermohonanTuntutanKos();
            kodTuntut = kodTuntutDAO.findById("L02");
            permohonanTuntutanKos.setKodTuntut(kodTuntut);
            permohonanTuntutanKos.setInfoAudit(info);
            permohonanTuntutanKos.setItem("Bayaran Proses Perintah Jual");
            permohonanTuntutanKos.setCawangan(caw);
            permohonanTuntutanKos.setPermohonan(permohonan);
            permohonanTuntutanKos.setAmaunTuntutan(BigDecimal.valueOf(60));
            permohonanTuntutanKos.setPihak(permohonanPihak);
            LOG.info("kodTransaksi : " + kodTransaksi.getKod());
            permohonanTuntutanKos.setKodTransaksi(kodTransaksi);
            lelongService.saveOrUpdate(permohonanTuntutanKos);
            LOG.info("-----abis-----");
        }
        rehydrate();

        return new JSP("pengambilan/NotisBorangBSek4.jsp").addParameter("tab", "true");
    }

    public void simpanNotis() {
         Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
        LOG.info("Kod Jabatan : " + p.getFolderDokumen().getFolderId());
        listKandunganFolder = notisPenerimaanService.getListDokumenB(p.getFolderDokumen().getFolderId());
         pemohon = aduanService.findPemohonByIdMohon(idPermohonan);
        senaraiPermohonanPihak=aduanService.findPihak(idPermohonan);
//        hp =  aduanService.findHPByIdMohon(p.getIdPermohonan());
        System.out.println("Pemohon"+pemohon);

//        listKandunganFolder = lelongService.getListDokumen16H(permohonan.getFolderDokumen().getFolderId());
        listKandunganFolder = notisPenerimaanService.getListDokumenB(permohonan.getFolderDokumen().getFolderId());

        if (listKandunganFolder.size() != 0) {

            Dokumen dokumen = new Dokumen();

            for (KandunganFolder kk : listKandunganFolder) {
                dokumen = kk.getDokumen();
            }

            LOG.info("Kod Jabatan : " + permohonan.getKodUrusan().getJabatan());
            KodNotis kodNotis = new KodNotis();
            kodNotis = kodNotisDAO.findById("NBB");
            LOG.info("kodNotis : " + kodNotis.getKod());
            if (dokumen.getKodDokumen() != null) {

                if (dokumen.getKodDokumen().getKod().equals("B")) {

                    InfoAudit info = pengguna.getInfoAudit();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());

//                    Notis notis2 = new Notis();
//                    notis2.setPermohonan(permohonan);
//                    notis2.setInfoAudit(info);
//                    notis2.setTarikhNotis(new java.util.Date());
//                    notis2.setKodNotis(kodNotis);
//                    notis2.setPihak(pp);
//                    notis2.setDokumenNotis(dokumen);
//                    notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
//                    notisPenerimaanService.saveOrUpdateNotis(notis2);
                    if(pemohon!=null)
                    {
                    permohonanPihak=new PermohonanPihak();
                    permohonanPihak.setPermohonan(pemohon.getPermohonan());
                    permohonanPihak.setPihak(pemohon.getPihak());
                    permohonanPihak.setCawangan(pemohon.getCawangan());
                    permohonanPihak.setJenis(kodJenisPihakBerkepentinganDAO.findById("TER"));
                    permohonanPihak.setInfoAudit(info);
                    aduanService.simpanPP(permohonanPihak);
                    }
                  
                          for (int j = 0; j < senaraiPermohonanPihak.size(); j++) {

                        LOG.info("senaraiPermohonanPihak name : " + senaraiPermohonanPihak.get(j).getPihak().getNama());

                        Notis notis22 = new Notis();
                        notis22.setPermohonan(permohonan);
                        notis22.setInfoAudit(info);
                        notis22.setTarikhNotis(new java.util.Date());
                        notis22.setKodNotis(kodNotis);
                        notis22.setPihak(senaraiPermohonanPihak.get(j));
                        notis22.setDokumenNotis(dokumen);
                        notis22.setJabatan(permohonan.getKodUrusan().getJabatan());
                        notisPenerimaanService.saveOrUpdateNotis(notis22);

                    }


                } else {
                    LOG.info("lain-lain..");
                }
            }
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            pemohon = aduanService.findPemohonByIdMohon(idPermohonan);
            System.out.println("pemohon"+pemohon.getPihak().getNama());
//            pp = aduanService.findPemohon(permohonan.getIdPermohonan(),pemohon.getPihak().getIdPihak());
             senaraiPermohonanPihak=aduanService.findPihak(idPermohonan);
             System.out.println("Pemohon"+pp);
//            hp =  aduanService.findHPByIdMohon(permohonan.getIdPermohonan());
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            listNotis = notisPenerimaanService.getListNotis(idPermohonan, "NBB");
            LOG.info("listNotis : " + listNotis.size());
            if (listNotis.size() == 0) {
                LOG.info("Belum ade lg...");
                simpanNotis();
                listNotis = notisPenerimaanService.getListNotis(idPermohonan, "NBB");
            }
            for (Notis n : listNotis) {
                notis = n;
            }
        }
    }

    public Resolution simpan() {

        InfoAudit info = pengguna.getInfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        for (int i = 0; i < listNotis.size(); i++) {
            Notis nn = listNotis.get(i);
            KodStatusTerima kodterime = new KodStatusTerima();
            KodCaraPenghantaran kodHanta = new KodCaraPenghantaran();
            kodterime.setKod(getContext().getRequest().getParameter("kodPenyampaian" + i));
            kodHanta.setKod(getContext().getRequest().getParameter("kodPenghantaran" + i));
            PenghantarNotis peng = penghantarNotisDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("namaPengahantar" + i)));
            nn.setPenghantarNotis(peng);
            nn.setKodCaraPenghantaran(kodHanta);
            nn.setKodStatusTerima(kodterime);
            nn.setInfoAudit(info);
            lelongService.saveOrUpdate(nn);
        }
        addSimpleMessage("Makluamt Telah Berjaya Disimpan..");
        return new JSP("pengambilan/NotisBorangBSek4.jsp").addParameter("tab", "true");
    }

     public Resolution pihakDetails() {

//         showHP = "true";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        int count = hakmilik.getSenaraiPihakBerkepentingan().size();
       hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
       listNotis = notisPenerimaanService.getListNotis(idPermohonan, "NBB");
       senaraiPermohonanPihak=aduanService.findPihak(idPermohonan);

//        if(fasaPermohonan != null){
//            if(fasaPermohonan.getIdAliran().equalsIgnoreCase("21JanaBorangQ")){
//                for(HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()){
//                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                    Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");
//                    if(notisNBQ != null){
//                        listNotis.add(notisNBQ);
//                    }
//                }
//            }
//        }
//permohonanPihak.getIdPermohonanPihak()
        if (listNotis.size() == count) {
            for(int i=0;i < senaraiPermohonanPihak.size(); i++){
//                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan,senaraiPermohonanPihak.get(i).getIdPermohonanPihak(), "NBB");
                if(notisNBQ != null){
                    if(notisNBQ.getPenghantarNotis() != null)
                        namaPengahantar.add(notisNBQ.getPenghantarNotis().getIdPenghantarNotis());
                    if(notisNBQ.getKodStatusTerima() != null)
                        kodStatusTerima.add(notisNBQ.getKodStatusTerima().getKod());
                    if(notisNBQ.getKodCaraPenghantaran() != null)
                        kodPenghantaran.add(notisNBQ.getKodCaraPenghantaran().getKod());
                    catatanPenerimaan.add(notisNBQ.getCatatanPenerimaan());
                    if(notisNBQ.getTarikhHantar()!= null)
                        tarikhHantar.add(sdf.format(notisNBQ.getTarikhHantar()));
                    if(notisNBQ.getTarikhTerima()!= null)
                        tarikhTerima.add(sdf.format(notisNBQ.getTarikhTerima()));
                    if(notisNBQ.getBuktiPenerimaan() == null){
                        idDokumenList.add("");
                    }else{
                        idDokumenList.add(String.valueOf(notisNBQ.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
            }
        }
//        }else{
//            show = false;
//            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
//        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/NotisBorangBSek4.jsp").addParameter("tab", "true");
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

    public Resolution popupUpload() {
        LOG.info("idNotis :" + idNotis);
        return new JSP("pengambilan/BorangB_upload_docNotis.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDoc() {
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("B"); // FIXME : BPN = Borang B
        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = idNotis;
        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
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
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kd);
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                dtn.setInfoAudit(ia);
                lelongService.updateNotis(dtn);
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
        return new JSP("pengambilan/BorangB_upload_docNotis.jsp").addParameter("popup", "true");
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
        return new JSP("pengambilan/BorangB_scan_docNotis.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        rehydrate();
        return new JSP("pengambilan/BorangB_scan_docNotis.jsp").addParameter("tab", "true");
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

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
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

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }


    public List<String> getCatatanPenerimaan() {
        return catatanPenerimaan;
    }

    public void setCatatanPenerimaan(List<String> catatanPenerimaan) {
        this.catatanPenerimaan = catatanPenerimaan;
    }

    public List<String> getIdDokumenList() {
        return idDokumenList;
    }

    public void setIdDokumenList(List<String> idDokumenList) {
        this.idDokumenList = idDokumenList;
    }

    public List<String> getKodPenghantaran() {
        return kodPenghantaran;
    }

    public void setKodPenghantaran(List<String> kodPenghantaran) {
        this.kodPenghantaran = kodPenghantaran;
    }

    public List<String> getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(List<String> kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public List<Integer> getNamaPengahantar() {
        return namaPengahantar;
    }

    public void setNamaPengahantar(List<Integer> namaPengahantar) {
        this.namaPengahantar = namaPengahantar;
    }
}
