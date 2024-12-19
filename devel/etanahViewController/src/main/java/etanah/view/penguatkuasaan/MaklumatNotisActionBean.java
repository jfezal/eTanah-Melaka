/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import java.text.ParseException;

import able.stripes.AbleActionBean;
import able.stripes.JSP;

import etanah.dao.NotisDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KandunganFolderDAO;

import etanah.model.Dokumen;
import etanah.model.Notis;
import etanah.model.KodNotis;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodDokumen;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.FasaPermohonan;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.dao.KodKlasifikasiDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import net.sourceforge.stripes.action.StreamingResolution;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.EnforcementService;
import etanah.service.BPelService;
import etanah.view.etanahActionBeanContext;
import etanah.report.ReportUtil;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.inject.Inject;
import etanah.dao.KodJabatanDAO;
import etanah.model.KodJabatan;
import etanah.service.common.LelongService;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_notis")
public class MaklumatNotisActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatNotisActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    DokumenService dokumenService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    LelongService lelongService;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private Notis notisPenguatkuasaan;
    private Notis suratPeringatan;
    private String idPermohonan;
    private String tarikhDihantarSP;
    private String idNotis;
    private String tarikhNotis;
    private String tarikhHantar;
    private int tempohHari;
    private String tempohHari1;
    private String flag;
    private Dokumen dokumen;
    private KodNotis kodNotis;
    private List<Notis> listKodNotis;
    private List<KandunganFolder> senaraiKandunganFolder;
    private String stageId;
    private String mesej;
    private Boolean errorMsg = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String jenisKesalahan;
    private String lokasiKesalahan;
    private String taskId;
    private Boolean tempohLanjutRemedi = false;
    private Notis bilanganNotis;
    private int bil = 0;
    private String keputusan;
    private Boolean addNotisFlag = Boolean.FALSE;
    private List<Notis> senaraiNotis;
    private String kodNegeri;

    @DefaultHandler
    public Resolution showForm() {
//        if (errorMsg) {
//            addSimpleError(mesej);
//        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_notis.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        setErrorMsg(false);
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_notis.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("penguatkuasaan/bukti_penyampaian_notis.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_surat_peringatan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_surat_peringatan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        return new JSP("penguatkuasaan/bukti_penyampaian.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/tempoh_borang_2A2B.jsp").addParameter("tab", "true");
    }

    public Resolution showForm8() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/tempoh_borang_2A2B.jsp").addParameter("tab", "true");
    }

    public Resolution showForm9() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/tempoh_buka_halangan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm10() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/tempoh_buka_halangan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idNotis = getContext().getRequest().getParameter("idNotis");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        kodNegeri = conf.getProperty("kodNegeri");

        BPelService service = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            logger.info("-------------stageId : BPEL ON----- :" + stageId);
        } else {
            stageId = "semak_notis";
            logger.info("-------------stageId : BPEL OFF----- :" + stageId);
        }

        String kod_notis = "NK";
        String kod_notis1 = "SP";
        String kodNotisBukaHalangan = "NHL";

        //kod dokumen for 351 , 352 & 428
        String kodDokumen351 = "2A";
        String kodDokumen352 = "2B";
        String kodDokumen428 = "SPBH";
        String kodDokumen127 = "7A";
        String kodDokumen127Remedi = "LTR";
        String kodDokumen49 = "2B";

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getKodUrusan().getKod().matches("425") || permohonan.getKodUrusan().getKod().matches("425A")) {
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    bilanganNotis = enforcementService.findMaxBil(permohonan.getIdPermohonan(), kod_notis);
                    if (bilanganNotis != null) {
                        if (StringUtils.isNotEmpty(bilanganNotis.getBilangan())) {
                            bil = Integer.parseInt(bilanganNotis.getBilangan());
                        }

                        if (bil < 3 && StringUtils.isNotEmpty(bilanganNotis.getNoRujukan())) {
                            if (bilanganNotis.getNoRujukan().equalsIgnoreCase("0")) {
                                addNotisFlag = true;
                            }
                        }
                    }
                    if (stageId.equalsIgnoreCase("semak_notis")) {
                        addNotisFlag = false;
                    }

                    notisPenguatkuasaan = enforcementService.findMaxBil(permohonan.getIdPermohonan(), kod_notis);
                } else {
                    //for ns
                    notisPenguatkuasaan = enforcementService.findByIdNotis(idPermohonan, kod_notis);
                }

                System.out.println("bil :: " + bil);
                System.out.println("addNotisFlag :: " + addNotisFlag);


                suratPeringatan = enforcementService.findByIdNotis(idPermohonan, kod_notis1);
                if (suratPeringatan != null) {
                    tarikhNotis = sdf.format(suratPeringatan.getTarikhNotis()).substring(0, 10);
                    tempohHari = suratPeringatan.getTempohHari();
                    if (suratPeringatan.getTarikhHantar() != null) {
                        tarikhHantar = sdf.format(suratPeringatan.getTarikhHantar()).substring(0, 10);
                    }
                }
                senaraiNotis = enforcementService.findListNotis(idPermohonan, kod_notis);
                listKodNotis = enforcementService.SenaraiKodNotis(idPermohonan);

            }


            if (permohonan.getKodUrusan().getKod().matches("351")) {
                notisPenguatkuasaan = enforcementService.findNotis(idPermohonan, kodDokumen351);
            }
            if (permohonan.getKodUrusan().getKod().matches("352")) {
                notisPenguatkuasaan = enforcementService.findNotis(idPermohonan, kodDokumen352);
            }
            if (permohonan.getKodUrusan().getKod().matches("428")) {
                notisPenguatkuasaan = enforcementService.findNotis(idPermohonan, kodDokumen428);
            }
            if (permohonan.getKodUrusan().getKod().matches("127")) {
                if (stageId.equalsIgnoreCase("perintah_tempoh_remedi") || stageId.equalsIgnoreCase("terima_tempoh_remedi")
                        || stageId.equalsIgnoreCase("arah_laporan6") || stageId.equalsIgnoreCase("sedia_laporan6")
                        || stageId.equalsIgnoreCase("semak_laporan6") || stageId.equalsIgnoreCase("terima_laporan6")
                        || stageId.equalsIgnoreCase("kpsn_remedi3") || stageId.equalsIgnoreCase("pilih_tindakan")) {
                    notisPenguatkuasaan = enforcementService.findNotis(idPermohonan, kodDokumen127Remedi);
                    tempohLanjutRemedi = true;
                } else {
                    notisPenguatkuasaan = enforcementService.findNotis(idPermohonan, kodDokumen127);
                }
            }
            if (permohonan.getKodUrusan().getKod().matches("49")) {
                notisPenguatkuasaan = enforcementService.findNotis(idPermohonan, kodDokumen49);
            }
            if (notisPenguatkuasaan != null) {
                tarikhNotis = sdf.format(notisPenguatkuasaan.getTarikhNotis()).substring(0, 10);
                tempohHari = notisPenguatkuasaan.getTempohHari();
                jenisKesalahan = notisPenguatkuasaan.getCatatanPenerimaan();
                lokasiKesalahan = notisPenguatkuasaan.getUlasan();
                if (notisPenguatkuasaan.getTarikhHantar() != null) {
                    tarikhHantar = sdf.format(notisPenguatkuasaan.getTarikhHantar()).substring(0, 10);
                }
            }

            List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
            for (FasaPermohonan fp : senaraiFasa) {
                if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                    if (fp.getKeputusan() != null) {
                        keputusan = fp.getKeputusan().getKod();
                        logger.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                    }

                }
            }

        }
    }

    public Resolution simpan() throws ParseException {

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {

            if (dokumen == null) {
                dokumen = new Dokumen();
            }

            KodDokumen dokNotis = new KodDokumen();
            if (permohonan.getKodUrusan().getKod().matches("425") || permohonan.getKodUrusan().getKod().matches("425A")) {
                dokNotis.setKod("PKT");
                dokumen.setTajuk("Notis Kosongkan Tanah");
            }
            if (permohonan.getKodUrusan().getKod().matches("351")) {
                dokNotis.setKod("2A");
                dokumen.setTajuk("Tempoh Notis 2A");
            }
            if (permohonan.getKodUrusan().getKod().matches("352")) {
                dokNotis.setKod("2B");
                dokumen.setTajuk("Tempoh Notis 2B");
            }

            if (permohonan.getKodUrusan().getKod().matches("49")) {
                dokNotis.setKod("2B");
                dokumen.setTajuk("Tempoh Notis 2B");
            }
            if (permohonan.getKodUrusan().getKod().matches("428")) {
                dokNotis.setKod("SPBH");
                dokumen.setTajuk("Surat Perintah Buka Halangan");
            }
            if (permohonan.getKodUrusan().getKod().matches("127")) {
                if (stageId.equalsIgnoreCase("perintah_tempoh_remedi") || stageId.equalsIgnoreCase("pilih_tindakan")) {
                    dokNotis.setKod("LTR");
                    dokumen.setTajuk("Tempoh Lanjut Remedi");
                } else {
                    dokNotis.setKod("7A");
                    dokumen.setTajuk("Tempoh Notis 7A");
                }

            }
            dokumen.setKodDokumen(dokNotis);
            dokumen.setNoVersi("1.0");
            InfoAudit iaPermohonan = new InfoAudit();
            iaPermohonan.setTarikhMasuk(new Date());
            iaPermohonan.setDimasukOleh(pengguna);
            dokumen.setInfoAudit(iaPermohonan);
            dokumenDAO.saveOrUpdate(dokumen);
            InfoAudit iaP = new InfoAudit();

            if (addNotisFlag) {
                notisPenguatkuasaan = new Notis();
                notisPenguatkuasaan.setTarikhNotis(new Date());
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            } else {
                if (notisPenguatkuasaan == null) {
                    notisPenguatkuasaan = new Notis();
                    notisPenguatkuasaan.setTarikhNotis(new Date());
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(pengguna);
                } else {
                    iaP.setTarikhMasuk(notisPenguatkuasaan.getInfoAudit().getTarikhMasuk());
                    iaP.setDimasukOleh(notisPenguatkuasaan.getInfoAudit().getDimasukOleh());
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                    notisPenguatkuasaan.setTarikhNotis(notisPenguatkuasaan.getTarikhNotis());
                }
            }




            KodCaraPenghantaran hantar = new KodCaraPenghantaran();

            if (permohonan.getKodUrusan().getKod().matches("428") || permohonan.getKodUrusan().getKod().matches("351") || permohonan.getKodUrusan().getKod().matches("352")
                    || permohonan.getKodUrusan().getKod().matches("425") || permohonan.getKodUrusan().getKod().matches("425A")) {

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    hantar.setKod("PN");//PN = Penghantar Notis
                } else {
                    hantar.setKod("01");//01 = serahan tangan (penghantar notis daerah seremban)
                }

            } else if (permohonan.getKodUrusan().getKod().matches("127") || permohonan.getKodUrusan().getKod().matches("49")) {
                hantar.setKod("");//null = tidak perlu
            }

            KodNotis not = new KodNotis();
            notisPenguatkuasaan.setPermohonan(permohonan);
            notisPenguatkuasaan.setKodCaraPenghantaran(hantar);
            notisPenguatkuasaan.setDokumenNotis(dokumen);

            if (permohonan.getKodUrusan().getKod().matches("428")) {
                not.setKod("NHL");
                notisPenguatkuasaan.setKodNotis(not);
            }
            if (!permohonan.getKodUrusan().getKod().matches("428")) {
                not.setKod("NK");
                notisPenguatkuasaan.setKodNotis(not);
            }
            if (permohonan.getKodUrusan().getKod().matches("127")) {
                if (stageId.equalsIgnoreCase("perintah_tempoh_remedi") || stageId.equalsIgnoreCase("pilih_tindakan")) {
                    not.setKod("LTR");
                } else {
                    not.setKod("7A");
                }
                notisPenguatkuasaan.setKodNotis(not);
            }

            notisPenguatkuasaan.setTarikhNotis(new Date());
            notisPenguatkuasaan.setTempohHari(tempohHari);
            notisPenguatkuasaan.setJabatan(permohonan.getKodUrusan().getJabatan());
            notisPenguatkuasaan.setInfoAudit(iaP);

            /* Need to setTempatHantar8 in order to differentiate between
             notis from notis page AND notis from Bukti Penyampaian page - both page using same table */
            if (permohonan.getKodUrusan().getKod().matches("425") || permohonan.getKodUrusan().getKod().matches("425A")) {
                notisPenguatkuasaan.setCatatanPenerimaan(jenisKesalahan);
                if(kodNegeri.equalsIgnoreCase("05")){
                    notisPenguatkuasaan.setTempatHantar8(lokasiKesalahan);
                }else{
                    notisPenguatkuasaan.setTempatHantar8("NotisKosongkanTanah");
                }
                
                notisPenguatkuasaan.setUlasan(lokasiKesalahan);
                if (addNotisFlag == true || bil == 0) {
                    bil = bil + 1;
                }
                notisPenguatkuasaan.setBilangan(Integer.toString(bil));
                notisPenguatkuasaan.setNoRujukan("1");
            }

            if (permohonan.getKodUrusan().getKod().matches("351")) {
                notisPenguatkuasaan.setTempatHantar8("TempohBorang2A");
            }

            if (permohonan.getKodUrusan().getKod().matches("352")) {
                notisPenguatkuasaan.setTempatHantar8("TempohBorang2B");
            }
            if (permohonan.getKodUrusan().getKod().matches("428")) {
                notisPenguatkuasaan.setTempatHantar8("NotisBukaHalangan");
            }

            if (permohonan.getKodUrusan().getKod().matches("127")) {
                if (stageId.equalsIgnoreCase("perintah_tempoh_remedi") || stageId.equalsIgnoreCase("pilih_tindakan")) {
                    notisPenguatkuasaan.setTempatHantar8("TempohLanjutRemedi");
                } else {
                    notisPenguatkuasaan.setTempatHantar8("TempohBorang7A");
                }
            }

            if (permohonan.getKodUrusan().getKod().matches("49")) {
                notisPenguatkuasaan.setTempatHantar8("TempohBorang2B");
            }


            notisDAO.saveOrUpdate(notisPenguatkuasaan);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        if (permohonan.getKodUrusan().getKod().matches("351") || permohonan.getKodUrusan().getKod().matches("352") || permohonan.getKodUrusan().getKod().matches("127") || permohonan.getKodUrusan().getKod().matches("49")) {
            return new JSP("penguatkuasaan/tempoh_borang_2A2B.jsp").addParameter("tab", "true");
        } else if (permohonan.getKodUrusan().getKod().matches("428")) {
            return new JSP("penguatkuasaan/tempoh_buka_halangan.jsp").addParameter("tab", "true");
        }
        return new JSP("penguatkuasaan/maklumat_notis.jsp").addParameter("tab", "true");


    }

    public void janaDokumen() throws Exception {

        if (permohonan != null) {
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";

//            if (permohonan.getKeputusan() != null) {

//                if (permohonan.getKeputusan().getKod().equals("L")) {
//                    for (int i = 0; i < 2; i++) {
//                        if (i == 0) {
//                            kd = kodDokumenDAO.findById("PKT");
//                            reportName = "ENF_Notis_Pemberitahuan.rdf";
//                        }
////                        else {
////                            kd = kodDokumenDAO.findById("PKT");
////                            reportName = "ENF_Notis_Pemberitahuan.rdf";
////                        }
//                        d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
//                        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
//                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
//                    }
//                } else {
            kd = kodDokumenDAO.findById("PKT");
            reportName = "ENF_Notis_Pemberitahuan.rdf";
            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
//                }
//            }
        }
    }

    public Resolution simpanBukti() throws ParseException {

        KodCaraPenghantaran hantar = new KodCaraPenghantaran();
        notisPenguatkuasaan.setPermohonan(permohonan);

        if (getContext().getRequest().getParameter("notisPenguatkuasaan.tarikhHantar").isEmpty()) {
            notisPenguatkuasaan.setTarikhHantar(null);
        } else {
            tarikhHantar = getContext().getRequest().getParameter("notisPenguatkuasaan.tarikhHantar");
            notisPenguatkuasaan.setTarikhHantar(sdf.parse(tarikhHantar));
        }

        hantar.setKod("01");
        notisPenguatkuasaan.setKodCaraPenghantaran(hantar);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = notisPenguatkuasaan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            notisPenguatkuasaan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanNotisPenguatkuasaan(notisPenguatkuasaan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/bukti_penyampaian.jsp").addParameter("tab", "true");

    }

    public Resolution simpanBuktiSP() throws ParseException {
        logger.debug("start simpan");
        KodCaraPenghantaran hantar = new KodCaraPenghantaran();
        suratPeringatan.setPermohonan(permohonan);
        hantar.setKod("01");
        suratPeringatan.setKodCaraPenghantaran(hantar);
        if (getContext().getRequest().getParameter("suratPeringatan.tarikhHantar").isEmpty()) {
            suratPeringatan.setTarikhHantar(null);
        } else {
            tarikhDihantarSP = getContext().getRequest().getParameter("suratPeringatan.tarikhHantar");
            suratPeringatan.setTarikhHantar(sdf.parse(tarikhDihantarSP));
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = suratPeringatan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            suratPeringatan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanNotisPenguatkuasaan(suratPeringatan);
        logger.debug("tess1 :" + suratPeringatan.getIdNotis());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        logger.debug("MaklumatLokasiActionBean::simpan::" + suratPeringatan.getIdNotis());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/bukti_penyampaian.jsp").addParameter("tab", "true");

    }

    public Resolution simpanSurat() throws ParseException {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        tempohHari1 = getContext().getRequest().getParameter("suratPeringatan.tempohHari");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if (dokumen == null) {
            dokumen = new Dokumen();
        }

        KodDokumen dokNotis = new KodDokumen();
        dokNotis.setKod("PKT");
        dokumen.setKodDokumen(dokNotis);
        dokumen.setTajuk("Notis Kosongkan Tanah");
        dokumen.setNoVersi("1.0");
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new Date());
        iaPermohonan.setDimasukOleh(peng);
        dokumen.setInfoAudit(iaPermohonan);
        dokumenDAO.saveOrUpdate(dokumen);

        if (suratPeringatan == null) {
            suratPeringatan = new Notis();
        }

        KodCaraPenghantaran hantar = new KodCaraPenghantaran();
        KodNotis not = new KodNotis();
        suratPeringatan.setPermohonan(permohonan);
        suratPeringatan.setDokumenNotis(dokumen);
        hantar.setKod("01");
        suratPeringatan.setKodCaraPenghantaran(hantar);
        suratPeringatan.setDokumenNotis(dokumen);
        not.setKod("SP");
        suratPeringatan.setKodNotis(not);
        suratPeringatan.setTarikhNotis(new Date());
        suratPeringatan.setTempohHari(Integer.parseInt(tempohHari1));
        InfoAudit iaP = new InfoAudit();
        iaP.setTarikhMasuk(new Date());
        iaP.setDimasukOleh(peng);
        suratPeringatan.setInfoAudit(iaP);
        KodJabatan kodJabatan = kodJabatanDAO.findById("24");
        suratPeringatan.setJabatan(kodJabatan);
        notisDAO.saveOrUpdate(suratPeringatan);

        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_surat_peringatan.jsp").addParameter("tab", "true");

    }

    public Resolution genReport() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String code = "";

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
            List senaraiOK = enforcementService.getListAduanOrangkenaSyak2(permohonan.getIdPermohonan());
            if (senaraiOK.size() > 0) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                    gen = "ENFNP_425_NS.rdf";
                    code = "PKT";
                } else {
                    gen = "ENFNP_425A_NS.rdf";
                    code = "PKT";
                }
            } else {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                    gen = "ENFNP_425_Kosong_NS.rdf";
                    code = "PKT";
                } else {
                    gen = "ENFNP_425A_Kosong_NS.rdf";
                    code = "PKT";
                }
            }
        }

        try {
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }


        return new StreamingResolution("text/plain", msg);
    }

    public Resolution janaNotis() throws FileNotFoundException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idPermohonan);
        reportUtil.generateReport("ENF_Notis_Pemberitahuan.rdf",
                //                new String[]{"p_id_kew_dok"},
                new String[]{"p_id_mohon"},
                new String[]{idPermohonan},
                documentPath + path, null);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());

        String[] tname = {"dokumen.idDokumen"};
        Object[] tvalue = {notisPenguatkuasaan.getDokumenNotis().getIdDokumen()};
        List<KandunganFolder> senaraiKandunganFolder = kandunganFolderDAO.findByEqualCriterias(tname, tvalue, null);

        if (senaraiKandunganFolder.size() == 0) {
            KandunganFolder kf = new KandunganFolder();

            kf.setFolder(permohonan.getFolderDokumen());
            kf.setDokumen(notisPenguatkuasaan.getDokumenNotis());
            kf.setInfoAudit(ia);

            Transaction tx = sessionProvider.get().getTransaction();
            tx.begin();
            kandunganFolderDAO.save(kf);

            tx.commit();

        }
        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            logger.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            logger.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        logger.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getKod() + "-" + id);
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public String getTarikhDihantarSP() {
        return tarikhDihantarSP;
    }

    public void setTarikhDihantarSP(String tarikhDihantarSP) {
        this.tarikhDihantarSP = tarikhDihantarSP;
    }

    public Notis getSuratPeringatan() {
        return suratPeringatan;
    }

    public void setSuratPeringatan(Notis suratPeringatan) {
        this.suratPeringatan = suratPeringatan;
    }

    public KodNotis getNotis() {
        return kodNotis;
    }

    public void setNotis(KodNotis kodNotis) {
        this.kodNotis = kodNotis;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public Notis getNotisPenguatkuasaan() {
        return notisPenguatkuasaan;
    }

    public void setNotisPenguatkuasaan(Notis notisPenguatkuasaan) {
        this.notisPenguatkuasaan = notisPenguatkuasaan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getTarikhDihantar() {
        return tarikhHantar;
    }

    public void setTarikhDihantar(String tarikhDihantar) {
        this.tarikhHantar = tarikhDihantar;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Notis> getListKodNotis() {
        return listKodNotis;
    }

    public void setListKodNotis(List<Notis> listKodNotis) {
        this.listKodNotis = listKodNotis;
    }

    public int getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(int tempohHari) {
        this.tempohHari = tempohHari;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTempohHari1() {
        return tempohHari1;
    }

    public void setTempohHari1(String tempohHari1) {
        this.tempohHari1 = tempohHari1;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Boolean getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Boolean errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMesej() {
        return mesej;
    }

    public void setMesej(String mesej) {
        this.mesej = mesej;
    }

    public List<KandunganFolder> getSenaraiKandunganFolder() {
        return senaraiKandunganFolder;
    }

    public void setSenaraiKandunganFolder(List<KandunganFolder> senaraiKandunganFolder) {
        this.senaraiKandunganFolder = senaraiKandunganFolder;
    }

    public String getJenisKesalahan() {
        return jenisKesalahan;
    }

    public void setJenisKesalahan(String jenisKesalahan) {
        this.jenisKesalahan = jenisKesalahan;
    }

    public String getLokasiKesalahan() {
        return lokasiKesalahan;
    }

    public void setLokasiKesalahan(String lokasiKesalahan) {
        this.lokasiKesalahan = lokasiKesalahan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getTempohLanjutRemedi() {
        return tempohLanjutRemedi;
    }

    public void setTempohLanjutRemedi(Boolean tempohLanjutRemedi) {
        this.tempohLanjutRemedi = tempohLanjutRemedi;
    }

    public Notis getBilanganNotis() {
        return bilanganNotis;
    }

    public void setBilanganNotis(Notis bilanganNotis) {
        this.bilanganNotis = bilanganNotis;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public List<Notis> getSenaraiNotis() {
        return senaraiNotis;
    }

    public void setSenaraiNotis(List<Notis> senaraiNotis) {
        this.senaraiNotis = senaraiNotis;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public Boolean getAddNotisFlag() {
        return addNotisFlag;
    }

    public void setAddNotisFlag(Boolean addNotisFlag) {
        this.addNotisFlag = addNotisFlag;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
