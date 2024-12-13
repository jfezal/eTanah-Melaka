/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanKertasButiranDAO;
import etanah.dao.PermohonanKertasKeputusanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasButiran;
import etanah.model.PermohonanKertasKeputusan;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.KodDokumen;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodNegeri;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.model.PihakPengarah;
import etanah.model.Transaksi;
import etanah.service.BPelService;
import etanah.service.common.HakmilikPermohonanService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/rayuanjkbb")
public class RayuanJKBBActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RayuanJKBBActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PembangunanService devServ;
    @Inject
    etanah.Configuration conf;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanKertasButiranDAO permohonanKertasButiranDAO;
    @Inject
    PermohonanKertasKeputusanDAO permohonanKertasKeputusanDAO;
    @Inject
    PembangunanUtility pembangunanUtility;
    
    private Pengguna pengguna;
    private Permohonan permohonan;
    private List<Pemohon> listPemohon;
    private List<Pemohon> senaraiPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private String persidangan;
    private String masasidang;
    private String tempatsidang;
    private String lokasi;
    private int saizList;
    private String tarikhpermohonan;
    private String nolot;
    private String nohakmilik;
    private Hakmilik hakmilikSingle;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat tdf = new SimpleDateFormat("hh:mm a");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private String nama;
    private String bilrayuan;
    private String jenisrayuan;
    private String lokasitanah;
    private String tarikhjkbbawl;
    private String latarbelakang;
    private String alasanrayuan;
    private String perakuan;
    private String keputusan;
    private List<PermohonanKertasButiran> senaraiKertasButiran;
    private List<PermohonanKertasKeputusan> senaraiKertasKpsn;
    private String bayaran;
    private String noresit;
    private Transaksi kewTrans;
    private String jenispermohonan;
    private boolean btn = true;
    private String pt;
    private String pejTanah;
    private BPelService service;
    private String taskId;
    Task task = null;
    private String stageId;
    private KodDokumen kd;
    private String tarikhMesyuarat;
    private String jam;
    private String minit;
    private String pagiPetang;
    private String tarikhKeputusan;
    private PermohonanKertasButiran kertasButir;
    private PermohonanKertasKeputusan kertasKpsn;
    private List<Pihak> uniquePemohonList2;
    private List<PihakPengarah> listPengarah;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("show editable Form");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/kertas_rayuan_jkbb.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("display form");
        btn = false;
        return new JSP("pembangunan/melaka/kertas_rayuan_jkbb.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        service = new BPelService();
        senaraiKertasButiran = new ArrayList<PermohonanKertasButiran>();
        senaraiKertasKpsn = new ArrayList<PermohonanKertasKeputusan>();

        LOG.info("rehydrate start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        uniquePemohonList2 = devServ.findUniquePemohonByIdPermohonan2(idPermohonan);

        if (permohonan.getPermohonanSebelum() != null) {
            tarikhKeputusan = sdf.format(permohonan.getPermohonanSebelum().getTarikhKeputusan());
        }

        //String[] tname = {"permohonan"};
        //Object[] model = {permohonan};

//        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        listPemohon = permohonan.getSenaraiPemohon();
        senaraiPemohon = permohonan.getSenaraiPemohon();

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            saizList = hakmilikPermohonanList.size();
        }

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }
        // testing purpose
//          stageId="";
//        stageId = "sediarencanajkbb";


        HakmilikPermohonan hp = new HakmilikPermohonan();

        if (!(hakmilikPermohonanList.isEmpty())) {
            hp = hakmilikPermohonanList.get(0);
            hakmilikSingle = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            nolot = hakmilikSingle.getLot().getNama() + " " + hakmilikSingle.getNoLot();
            nohakmilik = hakmilikSingle.getKodHakmilik().getKod() + " " + hakmilikSingle.getNoHakmilik();
        }

        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            if (w == 0) {
                lokasi = hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()
                        + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
            }

            if (w > 0) {
                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                    lokasi = lokasi + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()
                            + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                } else if (w == (hakmilikPermohonanList.size() - 1)) {
                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()
                            + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                }
            }
        }

        for (int j = 0; j < listPemohon.size(); j++) {
            Pemohon pm = new Pemohon();
            pm = listPemohon.get(j);

            if (j == 0) {
                nama = pm.getPihak().getNama();
            }
            if (j > 0) {
                if (j <= ((listPemohon.size() + 2) - 4)) {
                    nama = nama + ", " + pm.getPihak().getNama() + ", ";
                } else if (j == (listPemohon.size() - 1)) {
                    nama = nama + " dan " + pm.getPihak().getNama();
                }
            }
        }
        nama=pembangunanUtility.convertStringtoInitCap(nama);
        LOG.info("------Pemohon Nama2------:"+nama);
       
        for (Pemohon pemohon1 : listPemohon) {
            listPengarah = pemohon1.getPihak().getSenaraiPengarah();
        }
        
        tarikhpermohonan = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
        PermohonanKertas kertasP = null;
        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            kertasP = new PermohonanKertas();
            if (stageId.equals("rekodkedjkbbtangguh")) {
                kertasP = devServ.findLatestKertas(idPermohonan, "RJKBB");
            } else {
                kertasP = devServ.findKertasByKod(idPermohonan, "RJKBB");
            }
            if (kertasP != null) {
//                System.out.println("kertasP x null");
                if (kertasP.getTarikhSidang() != null) {
                    tarikhMesyuarat = sdf.format(kertasP.getTarikhSidang());
                    masasidang = tdf.format(kertasP.getTarikhSidang());
                    jam = masasidang.substring(0, 2);
                    minit = masasidang.substring(3, 5);
                    pagiPetang = masasidang.substring(6, 8);
                }
                tempatsidang = kertasP.getTempatSidang();
                persidangan = kertasP.getTajuk();

                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                    LOG.info("senarai kandungan:" + kertasP.getSenaraiKandungan().size());
                    kertasK = new PermohonanKertasKandungan();
                    kertasK = kertasP.getSenaraiKandungan().get(j);

                    if (kertasK.getBil() == 1) {
                        jenisrayuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 2) {
                        jenispermohonan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 3) {
                        lokasitanah = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 4) {
                        tarikhjkbbawl = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 9 && kertasK.getSubtajuk().equals("9.1")) {
                        latarbelakang = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 10) {
                        alasanrayuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 11) {
                        perakuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 12) {
                        keputusan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 13) {
                        bilrayuan = kertasK.getKandungan();
                    }
                }
                PermohonanKertasKandungan kand1 = devServ.findKertasKandByIdBilAndSubtajuk(kertasP.getIdKertas(), 9, "9.2");
                PermohonanKertasKandungan kand2 = devServ.findKertasKandByIdBilAndSubtajuk(kertasP.getIdKertas(), 9, "9.3");
                if (kand1 != null) {
                    senaraiKertasButiran = devServ.findKertasButiranByIdKand(kand1.getIdKandungan());
                    System.out.println("------senaraiKertasButiran------- :" + senaraiKertasButiran.size());
                }
                if (kand2 != null) {
                    senaraiKertasKpsn = devServ.findKertasKeputusanByIdKand(kand2.getIdKandungan());
                    System.out.println("------senaraiKertasKpsn------- :" + senaraiKertasKpsn.size());
                }
            }
        }
        if (permohonan.getSenaraiKertas().isEmpty() || kertasP == null) {
            // first time login display default data
            List<PermohonanKertas> senaraiKertas1 = new ArrayList<PermohonanKertas>();
            if (permohonan.getPermohonanSebelum() != null) {
                senaraiKertas1 = devServ.findSenaraiKertasByKod(permohonan.getPermohonanSebelum().getIdPermohonan(), "JKBB");
            }
            PermohonanKertas kertasP1 = null;
            LOG.info("------------JKBB1-senaraiKertas1.size()-------:" + senaraiKertas1.size());
            if (senaraiKertas1.size() > 0) {
                kertasP1 = senaraiKertas1.get(0);
            }
            if (kertasP1 != null) {

                for (int j = 0; j < kertasP1.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();
                    kertasK1 = kertasP1.getSenaraiKandungan().get(j);

                    if (kertasK1.getBil() == 3) {
                        lokasitanah = kertasK1.getKandungan();
                        LOG.info("------------JKBB----lokasitanah-----:" + lokasitanah);
                    }
                }
            }

//                tempatsidang = "BILIK MESYUARAT MMKN BANGUNAN SERI NEGERI AYER KEROH, MELAKA";
            perakuan = "Pengarah Tanah dan Galian, Melaka setelah mengkaji rayuan ini memperakukan supaya rayuan untuk";
            keputusan = "Mesyuarat Jawatankuasa Belah Bahagi diminta mempertimbangkan sama ada bersetuju atau sebaliknya dengan perakuan PTG di para";
//              jenispermohonan = permohonan.getKodUrusan().getNama() + " Kanun Tanah Negara.";
        }


        kewTrans = devServ.findAkaunByIdPermohonan(idPermohonan);


        LOG.info("rehydrate finish");
    }

    public Resolution simpan() {
        LOG.info("simpan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("RJKBB");

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }

        //testing pupose
//        stageId = "sediarencanajkbb";

        PermohonanKertas permohonanKertas = new PermohonanKertas();
        if (kertasK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        ArrayList listUlasan = new ArrayList();
        ArrayList listBill = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();

        if (jenisrayuan == null || jenisrayuan.equals("")) {
            jenisrayuan = "TIADA DATA.";
        }
        if (jenispermohonan == null || jenispermohonan.equals("")) {
            jenispermohonan = "TIADA DATA.";
        }
        if (lokasitanah == null || lokasitanah.equals("")) {
            lokasitanah = "TIADA DATA.";
        }
        if (tarikhjkbbawl == null || tarikhjkbbawl.equals("")) {
            tarikhjkbbawl = "TIADA DATA.";
        }
        if (latarbelakang == null || latarbelakang.equals("")) {
            latarbelakang = "TIADA DATA.";
        }
        if (alasanrayuan == null || alasanrayuan.equals("")) {
            alasanrayuan = "TIADA DATA.";
        }
        if (perakuan == null || perakuan.equals("")) {
            perakuan = "TIADA DATA.";
        }
        if (keputusan == null || keputusan.equals("")) {
            keputusan = "TIADA DATA.";
        }
        if (bilrayuan == null || bilrayuan.equals("")) {
            bilrayuan = " - ";
        }
        if (persidangan == null || persidangan.equals("")) {
            persidangan = " ";
        }

        listUlasan.add(jenisrayuan);
        listUlasan.add(jenispermohonan);
        listUlasan.add(lokasitanah);
        listUlasan.add(tarikhjkbbawl);
        listUlasan.add(latarbelakang);
        listUlasan.add(alasanrayuan);
        listUlasan.add(perakuan);
        listUlasan.add(keputusan);
        listUlasan.add(bilrayuan);

        listBill.add(new Integer(1));
        listBill.add(new Integer(2));
        listBill.add(new Integer(3));
        listBill.add(new Integer(4));
        listBill.add(new Integer(9));
        listBill.add(new Integer(10));
        listBill.add(new Integer(11));
        listBill.add(new Integer(12));
        listBill.add(new Integer(13));

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("9.1");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        if (kertasK != null) {
            if (!kertasK.getKandungan().isEmpty()) {
                LOG.info("kemaskini start");
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
                    LOG.info("senarai kandungan:" + permohonanKertas.getSenaraiKandungan().size());

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(jenisrayuan);
                    } else if (kertasKandungan.getBil() == 2) {
                        kertasKandungan.setKandungan(jenispermohonan);
                    } else if (kertasKandungan.getBil() == 3) {
                        kertasKandungan.setKandungan(lokasitanah);
                    } else if (kertasKandungan.getBil() == 4) {
                        kertasKandungan.setKandungan(tarikhjkbbawl);
                    } else if (kertasKandungan.getBil() == 9 && kertasKandungan.getSubtajuk().equals("9.1")) {
                        kertasKandungan.setKandungan(latarbelakang);
                    } else if (kertasKandungan.getBil() == 10) {
                        kertasKandungan.setKandungan(alasanrayuan);
                    } else if (kertasKandungan.getBil() == 11) {
                        kertasKandungan.setKandungan(perakuan);
                    } else if (kertasKandungan.getBil() == 12) {
                        kertasKandungan.setKandungan(keputusan);
                    } else if (kertasKandungan.getBil() == 13) {
                        kertasKandungan.setKandungan(bilrayuan);
                    }

                    try {
                        if (tarikhMesyuarat != null && !jam.equals("0") && !minit.equals("0") && !pagiPetang.equals("0")) {
                            masasidang = jam + ":" + minit + " " + pagiPetang;
                            Date tarikhsidang = (Date) formatter.parse(tarikhMesyuarat + " " + masasidang);
                            permohonanKertas.setTarikhSidang(tarikhsidang);
                        }
                    } catch (java.text.ParseException e) {
                        LOG.info("Exception: " + e);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kd);
                    permohonanKertas.setTajuk(persidangan);
                    permohonanKertas.setTempatSidang(tempatsidang);
                    kertasKandungan.setInfoAudit(infoAudit);
                    devServ.simpanPermohonanKertas(permohonanKertas);
                    devServ.simpanPermohonanKertasKandungan(kertasKandungan);
                }
                addSimpleMessage("Maklumat telah berjaya disimpan.");
            }
            LOG.info("kemaskini finish");
        } else {
            LOG.info("simpan new entry");
            for (int i = 0; i < listUlasan.size(); i++) {

                String dataulasan = (String) listUlasan.get(i);
                String subTajuk = (String) listSubtajuk.get(i);
                Integer bil = (Integer) listBill.get(i);
                kertasK = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setKodDokumen(kd);
                permohonanKertas.setTajuk(persidangan);
                permohonanKertas.setTempatSidang(tempatsidang);
                LOG.info("------dataulasan--------:" + dataulasan);
                kertasK.setKertas(permohonanKertas);
                kertasK.setBil(bil);
                kertasK.setSubtajuk(subTajuk);
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(dataulasan);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devServ.simpanPermohonanKertas(permohonanKertas);
                devServ.simpanPermohonanKertasKandungan(kertasK);
            }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        for (int i = 0; i < senaraiPemohon.size(); i++) {
            String suratNegeri = getContext().getRequest().getParameter("suratNegeri" + i);
            Pemohon pemohon = new Pemohon();
            pemohon = senaraiPemohon.get(i);
            if (pemohon != null) {
                LOG.debug("------suratNegeri--------:" + suratNegeri);

                if (suratNegeri != null && !suratNegeri.equals("")) {
                    KodNegeri kodNegeri = kodNegeriDAO.findById(suratNegeri);
                    LOG.debug("------kodNegeri--------:" + kodNegeri);
                    pemohon.getPihak().setSuratNegeri(kodNegeri);
                } else {
                    pemohon.getPihak().setSuratNegeri(null);
                }
                pemohon.setPermohonan(permohonan);
                pemohon.setInfoAudit(infoAudit);
                devServ.simpanPemohon(pemohon);
            }
        }

        PermohonanKertasKandungan kand1;
        kand1 = devServ.findKertasKandByIdBilAndSubtajuk(permohonanKertas.getIdKertas(), 9, "9.2");
        if (kand1 == null) {
            LOG.info("----------kand1--------:" + kand1);
            kand1 = new PermohonanKertasKandungan();
            kand1.setKertas(permohonanKertas);
            kand1.setBil(9);
            kand1.setSubtajuk("9.2");
            kand1.setInfoAudit(infoAudit);
            kand1.setKandungan("kertas Butir");
            kand1.setCawangan(pengguna.getKodCawangan());
            devServ.simpanPermohonanKertas(permohonanKertas);
            devServ.simpanPermohonanKertasKandungan(kand1);
        }

        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pengguna);
        String StrCount = getContext().getRequest().getParameter("count");
        LOG.info("----------count Butiran--------:" + StrCount);
        LOG.info("--------kand1----------:" + kand1);
        if (StrCount != null) {
            String pembangunan = "";
            String unit = "";
            String luas = "";
            String hargaUnit = "";
            int count = Integer.parseInt(StrCount);
            for (int i = 0; i < count; i++) {
                String idKand = getContext().getRequest().getParameter("idKertasBtr" + i);
                pembangunan = getContext().getRequest().getParameter("pembangunan" + i);
                unit = getContext().getRequest().getParameter("unit" + i);
                luas = getContext().getRequest().getParameter("luas" + i);
                hargaUnit = getContext().getRequest().getParameter("hargaUnit" + i);
                LOG.info("----------idKand--------:" + idKand);
                LOG.info("----------pembangunan--------:" + pembangunan);
                LOG.info("----------unit--------:" + unit);
                LOG.info("----------luas--------:" + luas);
                LOG.info("----------hargaUnit--------:" + hargaUnit);
                kertasButir = new PermohonanKertasButiran();
                if (idKand != null && !idKand.equals("")) {
                    kertasButir = permohonanKertasButiranDAO.findById(Long.parseLong(idKand));
                    ia = kertasButir.getInfoAudit();
                    ia.setTarikhKemaskini(new Date());
                    ia.setDiKemaskiniOleh(pengguna);
                }
                kertasButir.setInfoAudit(ia);
                kertasButir.setPembangunan(pembangunan);
                kertasButir.setLuas(luas);
                kertasButir.setUnit(unit);
                kertasButir.setHargaSeunit(hargaUnit);
                kertasButir.setKertasKandungan(kand1);
                devServ.simpanPermohonanKertasButiran(kertasButir);
            }//for
        }// if

        PermohonanKertasKandungan kand2;
        kand2 = devServ.findKertasKandByIdBilAndSubtajuk(permohonanKertas.getIdKertas(), 9, "9.3");
        if (kand2 == null) {
            LOG.info("----------kand2--if------:" + kand2);
            kand2 = new PermohonanKertasKandungan();
            kand2.setKertas(permohonanKertas);
            kand2.setBil(9);
            kand2.setSubtajuk("9.3");
            kand2.setInfoAudit(infoAudit);
            kand2.setKandungan("kertas Keputusan");
            kand2.setCawangan(pengguna.getKodCawangan());
            devServ.simpanPermohonanKertas(permohonanKertas);
            devServ.simpanPermohonanKertasKandungan(kand2);
        }
        StrCount = getContext().getRequest().getParameter("count2");
        LOG.info("--------Keputusan--count--------:" + StrCount);
        LOG.info("--------kand2----------:" + kand2);
        if (StrCount != null) {
            String tarikhRayuan = "";
            String jenisRayuan = "";
            String kpsn = "";
            int count = Integer.parseInt(StrCount);
            for (int i = 0; i < count; i++) {
                String idKand = getContext().getRequest().getParameter("idKertasKpsn" + i);
                tarikhRayuan = getContext().getRequest().getParameter("tarikhRayuan" + i);
                jenisRayuan = getContext().getRequest().getParameter("jenisRayuan" + i);
                kpsn = getContext().getRequest().getParameter("kpsn" + i);
                LOG.info("----------idKand--------:" + idKand);
                LOG.info("----------tarikhRayuan--------:" + tarikhRayuan);
                LOG.info("----------jenisRayuan--------:" + jenisRayuan);
                LOG.info("----------kpsn--------:" + kpsn);
                kertasKpsn = new PermohonanKertasKeputusan();
                if (idKand != null && !idKand.equals("")) {
                    kertasKpsn = permohonanKertasKeputusanDAO.findById(Long.parseLong(idKand));
                    ia = kertasKpsn.getInfoAudit();
                    ia.setTarikhKemaskini(new Date());
                    ia.setDiKemaskiniOleh(pengguna);
                }
                kertasKpsn.setInfoAudit(ia);
                try {
                    if (tarikhRayuan != null) {
                        Date tarikhRayuan1 = (Date) sdf.parse(tarikhRayuan);
                        kertasKpsn.setTrhRayuan(tarikhRayuan1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                kertasKpsn.setJenisRayuan(jenisRayuan);
                kertasKpsn.setKeputusan(kpsn);
                kertasKpsn.setKertasKandungan(kand2);
                devServ.simpanPermohonanKertasKeputusan(kertasKpsn);
            }//for

        }//if

        kand1 = devServ.findKertasKandByIdBilAndSubtajuk(permohonanKertas.getIdKertas(), 9, "9.2");
        kand2 = devServ.findKertasKandByIdBilAndSubtajuk(permohonanKertas.getIdKertas(), 9, "9.3");
        if (kand1 != null) {
            senaraiKertasButiran = devServ.findKertasButiranByIdKand(kand1.getIdKandungan());
            System.out.println("------senaraiKertasButiran------- :" + senaraiKertasButiran.size());
        }
        if (kand2 != null) {
            senaraiKertasKpsn = devServ.findKertasKeputusanByIdKand(kand2.getIdKandungan());
            System.out.println("------senaraiKertasKpsn------- :" + senaraiKertasKpsn.size());
        }

        LOG.info("simpan finish");
        if (stageId.equals("cetakrekodkpsnrayuanjkbb")) {
            btn = false;
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/kertas_rayuan_jkbb.jsp").addParameter("tab", "true");
    }

    public Resolution kertasBaru() {
        LOG.info("kertasBaru start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("RJKBB");

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }

        //Testing Purpose
//        stageId = "rekodrayuanterikinitangguh";

        PermohonanKertas permohonanKertas = new PermohonanKertas();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        ArrayList listUlasan = new ArrayList();
        ArrayList listBill = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();

        if (jenisrayuan == null || jenisrayuan.equals("")) {
            jenisrayuan = "TIADA DATA.";
        }
        if (jenispermohonan == null || jenispermohonan.equals("")) {
            jenispermohonan = "TIADA DATA.";
        }
        if (lokasitanah == null || lokasitanah.equals("")) {
            lokasitanah = "TIADA DATA.";
        }
        if (tarikhjkbbawl == null || tarikhjkbbawl.equals("")) {
            tarikhjkbbawl = "TIADA DATA.";
        }
        if (latarbelakang == null || latarbelakang.equals("")) {
            latarbelakang = "TIADA DATA.";
        }
        if (alasanrayuan == null || alasanrayuan.equals("")) {
            alasanrayuan = "TIADA DATA.";
        }
        if (perakuan == null || perakuan.equals("")) {
            perakuan = "TIADA DATA.";
        }
        if (keputusan == null || keputusan.equals("")) {
            keputusan = "TIADA DATA.";
        }
        if (bilrayuan == null || bilrayuan.equals("")) {
            bilrayuan = "TIADA DATA.";
        }


        listUlasan.add(jenisrayuan);
        listUlasan.add(jenispermohonan);
        listUlasan.add(lokasitanah);
        listUlasan.add(tarikhjkbbawl);
        listUlasan.add(latarbelakang);
        listUlasan.add(alasanrayuan);
        listUlasan.add(perakuan);
        listUlasan.add(keputusan);
        listUlasan.add(bilrayuan);

        listBill.add(new Integer(1));
        listBill.add(new Integer(2));
        listBill.add(new Integer(3));
        listBill.add(new Integer(4));
        listBill.add(new Integer(9));
        listBill.add(new Integer(10));
        listBill.add(new Integer(11));
        listBill.add(new Integer(12));
        listBill.add(new Integer(13));

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("9.1");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        LOG.info("simpan kertasBaru entry");
        for (int i = 0; i < listUlasan.size(); i++) {
            String dataulasan = (String) listUlasan.get(i);
            String subTajuk = (String) listSubtajuk.get(i);
            Integer bil = (Integer) listBill.get(i);

            kertasK = new PermohonanKertasKandungan();
            try {
                if (tarikhMesyuarat != null && !jam.equals("0") && !minit.equals("0") && !pagiPetang.equals("0")) {
                    masasidang = jam + ":" + minit + " " + pagiPetang;
                    Date tarikhsidang = (Date) formatter.parse(tarikhMesyuarat + " " + masasidang);
                    permohonanKertas.setTarikhSidang(tarikhsidang);
                }
            } catch (java.text.ParseException e) {
                LOG.info("Exception: " + e);
            }
            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setCawangan(pengguna.getKodCawangan());
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setKodDokumen(kd);
            permohonanKertas.setTajuk(persidangan);
            permohonanKertas.setTempatSidang(tempatsidang);
            kertasK.setKertas(permohonanKertas);
            kertasK.setBil(bil);
            kertasK.setSubtajuk(subTajuk);
            kertasK.setInfoAudit(infoAudit);
            kertasK.setKandungan(dataulasan);
            kertasK.setCawangan(pengguna.getKodCawangan());
            devServ.simpanPermohonanKertas(permohonanKertas);
            devServ.simpanPermohonanKertasKandungan(kertasK);
        }
        for (int i = 0; i < senaraiPemohon.size(); i++) {
            String suratNegeri = getContext().getRequest().getParameter("suratNegeri" + i);
            Pemohon pemohon = new Pemohon();
            pemohon = senaraiPemohon.get(i);
            if (pemohon != null) {
                LOG.debug("------suratNegeri--------:" + suratNegeri);

                if (suratNegeri != null && !suratNegeri.equals("")) {
                    KodNegeri kodNegeri = kodNegeriDAO.findById(suratNegeri);
                    LOG.debug("------kodNegeri--------:" + kodNegeri);
                    pemohon.getPihak().setSuratNegeri(kodNegeri);
                } else {
                    pemohon.getPihak().setSuratNegeri(null);
                }
                pemohon.setPermohonan(permohonan);
                pemohon.setInfoAudit(infoAudit);
                devServ.simpanPemohon(pemohon);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        LOG.info("kertasBaru finish");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/kertas_rayuan_jkbb.jsp").addParameter("tab", "true");
    }

    public Resolution deletePermohonanKertasButiran() {
        String idKand = getContext().getRequest().getParameter("idKand");
        LOG.info("-----deletePermohonanKertasButiran------idKand-----:" + idKand);
        try {
            if (idKand != null) {
                kertasButir = permohonanKertasButiranDAO.findById(Long.parseLong(idKand));
                devServ.deletePermohonanKertasButiran(kertasButir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(RayuanJKBBActionBean.class, "locate");
    }

    public Resolution deletePermohonanKertasKeputusan() {
        String idKand = getContext().getRequest().getParameter("idKand");
        LOG.info("-----deletePermohonanKertasKeputusan------idKand-----:" + idKand);
        try {
            if (idKand != null) {
                kertasKpsn = permohonanKertasKeputusanDAO.findById(Long.parseLong(idKand));
                devServ.deletePermohonanKertasKeputusan(kertasKpsn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(RayuanJKBBActionBean.class, "locate");
    }

    public Resolution deleteSingle() {
        try {
            List<PermohonanKertasKandungan> senaraiKand = new ArrayList<PermohonanKertasKandungan>();
            senaraiKand = devServ.findKertasKandByIdKertasSubtajuk(kertasK.getKertas().getIdKertas(), 9);
            if (!senaraiKand.isEmpty()) {
                for (int i = 0; i < 4; i++) {
                    LOG.info("---------senaraiKand.get(i)-------" + senaraiKand.get(i));
                    devServ.deleteKertasKandungan(senaraiKand.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(RayuanJKBBActionBean.class, "locate");
    }

    public Resolution deleteSingle2() {
        try {
            List<PermohonanKertasKandungan> senaraiKand = new ArrayList<PermohonanKertasKandungan>();
            senaraiKand = devServ.findPermohonanRayuanByIdKertasSubtajuk(kertasK.getKertas().getIdKertas(), 9);
            if (!senaraiKand.isEmpty()) {
                for (int i = 0; i < 3; i++) {
                    LOG.info("---------senaraiKand.get(i)-------" + senaraiKand.get(i));
                    devServ.deleteKertasKandungan(senaraiKand.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(RayuanJKBBActionBean.class, "locate");
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

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getMasasidang() {
        return masasidang;
    }

    public void setMasasidang(String masasidang) {
        this.masasidang = masasidang;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public String getPersidangan() {
        return persidangan;
    }

    public void setPersidangan(String persidangan) {
        this.persidangan = persidangan;
    }

    public String getTempatsidang() {
        return tempatsidang;
    }

    public void setTempatsidang(String tempatsidang) {
        this.tempatsidang = tempatsidang;
    }

    public int getSaizList() {
        return saizList;
    }

    public void setSaizList(int saizList) {
        this.saizList = saizList;
    }

    public Hakmilik getHakmilikSingle() {
        return hakmilikSingle;
    }

    public void setHakmilikSingle(Hakmilik hakmilikSingle) {
        this.hakmilikSingle = hakmilikSingle;
    }

    public String getNohakmilik() {
        return nohakmilik;
    }

    public void setNohakmilik(String nohakmilik) {
        this.nohakmilik = nohakmilik;
    }

    public String getNolot() {
        return nolot;
    }

    public void setNolot(String nolot) {
        this.nolot = nolot;
    }

    public String getTarikhpermohonan() {
        return tarikhpermohonan;
    }

    public void setTarikhpermohonan(String tarikhpermohonan) {
        this.tarikhpermohonan = tarikhpermohonan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getBilrayuan() {
        return bilrayuan;
    }

    public void setBilrayuan(String bilrayuan) {
        this.bilrayuan = bilrayuan;
    }

    public String getJenisrayuan() {
        return jenisrayuan;
    }

    public void setJenisrayuan(String jenisrayuan) {
        this.jenisrayuan = jenisrayuan;
    }

    public String getLokasitanah() {
        return lokasitanah;
    }

    public void setLokasitanah(String lokasitanah) {
        this.lokasitanah = lokasitanah;
    }

    public String getLatarbelakang() {
        return latarbelakang;
    }

    public void setLatarbelakang(String latarbelakang) {
        this.latarbelakang = latarbelakang;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setListPengarah(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public String getAlasanrayuan() {
        return alasanrayuan;
    }

    public void setAlasanrayuan(String alasanrayuan) {
        this.alasanrayuan = alasanrayuan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public String getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(String perakuan) {
        this.perakuan = perakuan;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public DateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(DateFormat formatter) {
        this.formatter = formatter;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public SimpleDateFormat getTdf() {
        return tdf;
    }

    public void setTdf(SimpleDateFormat tdf) {
        this.tdf = tdf;
    }

    public String getTarikhjkbbawl() {
        return tarikhjkbbawl;
    }

    public void setTarikhjkbbawl(String tarikhjkbbawl) {
        this.tarikhjkbbawl = tarikhjkbbawl;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public List<Pemohon> getSenaraiPemohon() {
        return senaraiPemohon;
    }

    public void setSenaraiPemohon(List<Pemohon> senaraiPemohon) {
        this.senaraiPemohon = senaraiPemohon;
    }

    public String getBayaran() {
        return bayaran;
    }

    public void setBayaran(String bayaran) {
        this.bayaran = bayaran;
    }

    public String getNoresit() {
        return noresit;
    }

    public void setNoresit(String noresit) {
        this.noresit = noresit;
    }

    public Transaksi getKewTrans() {
        return kewTrans;
    }

    public void setKewTrans(Transaksi kewTrans) {
        this.kewTrans = kewTrans;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getPagiPetang() {
        return pagiPetang;
    }

    public void setPagiPetang(String pagiPetang) {
        this.pagiPetang = pagiPetang;
    }

    public String getJenispermohonan() {
        return jenispermohonan;
    }

    public void setJenispermohonan(String jenispermohonan) {
        this.jenispermohonan = jenispermohonan;
    }

    public String getTarikhKeputusan() {
        return tarikhKeputusan;
    }

    public void setTarikhKeputusan(String tarikhKeputusan) {
        this.tarikhKeputusan = tarikhKeputusan;
    }

    public PermohonanKertasButiran getKertasButir() {
        return kertasButir;
    }

    public void setKertasButir(PermohonanKertasButiran kertasButir) {
        this.kertasButir = kertasButir;
    }

    public PermohonanKertasKeputusan getKertasKpsn() {
        return kertasKpsn;
    }

    public void setKertasKpsn(PermohonanKertasKeputusan kertasKpsn) {
        this.kertasKpsn = kertasKpsn;
    }

    public List<PermohonanKertasButiran> getSenaraiKertasButiran() {
        return senaraiKertasButiran;
    }

    public void setSenaraiKertasButiran(List<PermohonanKertasButiran> senaraiKertasButiran) {
        this.senaraiKertasButiran = senaraiKertasButiran;
    }

    public List<PermohonanKertasKeputusan> getSenaraiKertasKpsn() {
        return senaraiKertasKpsn;
    }

    public void setSenaraiKertasKpsn(List<PermohonanKertasKeputusan> senaraiKertasKpsn) {
        this.senaraiKertasKpsn = senaraiKertasKpsn;
    }

    public List<Pihak> getUniquePemohonList2() {
        return uniquePemohonList2;
    }

    public void setUniquePemohonList2(List<Pihak> uniquePemohonList2) {
        this.uniquePemohonList2 = uniquePemohonList2;
    }
   
}
