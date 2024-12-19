/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import java.util.Date;
//import antlr.StringUtils;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.FasaPermohonan;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
//import oracle.bpel.services.common.concurrent.Task;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/kertasPertimbanganPTG2")
public class KertasPertimbanganPTG2ActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KertasPertimbanganPTGActionBean.class);
    @Inject
    BPelService service;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    // newly added
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    private KodDokumen kd;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private FasaPermohonan fasaMohon;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String lokasi;
    private String pejTanah;
    private String nama;
    private String tajuk;
    private String tujuan;
    private String latarBelakang1;
    private String butirButirPembangunan1;
    private String huraianPentadbir1;
    private String syorPentadbir1;
    private String keputusanPTG;
    private String ulasanPTG;
    private String stageId;
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan3;
    private List<PermohonanKertasKandungan> senaraiKandungan5;
    private List<PermohonanKertasKandungan> senaraiKandungan6;
    private List<PermohonanKertasKandungan> senaraiKandungan7;
    private List<PermohonanKertasKandungan> senaraiKandungan8;
    private int rowCount2;
    private int rowCount3;
    private int rowCount5;
    private int rowCount6;
    private int rowCount7;
    private int rowCount8;
    private String huraianTimbalan1;
    private String syorTimbalan1;
    private Pengguna peng;
    private boolean btn;
    private String noRuj;

    @DefaultHandler
    public Resolution showForm() {
        btn = Boolean.TRUE;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahBahagian/kertas_pertimbangan_PTG2.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm2() {
        btn = Boolean.TRUE;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahBahagian/kertas_pertimbangan_PTG2.jsp").addParameter("tab", "true");
    }

    // hide Tambah & Hapus Button
    public Resolution showForm3() {
        btn = Boolean.TRUE;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahBahagian/kertas_pertimbangan_PTG2.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        rowCount2 = 1;
        rowCount3 = 1;
        rowCount5 = 1;
        rowCount6 = 1;
        rowCount7 = 1;
        rowCount8 = 1;
        
        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        List<Pemohon> listPemohon = new ArrayList<Pemohon>();
        listPemohon = permohonan.getSenaraiPemohon();
        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
        }

        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            if (w == 0) {
                lokasi = hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " +hakmilik.getKodHakmilik().getKod()+ " " +hakmilik.getNoHakmilik() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", Di Bawah Seksyen " + permohonan.getKodUrusan().getRujukanKanun();
            }

            if (w > 0) {
                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
//                  lokasi = lokasi + ", " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
                    lokasi = lokasi + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " +hakmilik.getKodHakmilik().getKod()+ " " +hakmilik.getNoHakmilik() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", Di Bawah Seksyen " + permohonan.getKodUrusan().getRujukanKanun() + ", ";
                } else if (w == (hakmilikPermohonanList.size() - 1)) {
//                  lokasi = lokasi + " dan " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " +hakmilik.getKodHakmilik().getKod()+ " " + hakmilik.getNoHakmilik() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", Di Bawah Seksyen " + permohonan.getKodUrusan().getRujukanKanun();
                }
            }
        }

        ArrayList list = new ArrayList(5);
        for (int j = 0; j < listPemohon.size(); j++) {
            Pemohon pm = new Pemohon();
            pm = listPemohon.get(j);

            String pihakName = pm.getPihak().getNama();
            if (!list.contains(pihakName)) {
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
                list.add(pihakName);
            }
        }
        System.out.println("---******--Pihak Name  sss------" + nama);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        System.out.println("---******--taskId------" + taskId);
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("---******--StageId------" + stageId);
        }

        if (peng.getKodCawangan().getDaerah() != null) {
            if (peng.getKodCawangan().getDaerah().getKod().equals("01")) {
                pejTanah = "Jelebu";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("02")) {
                pejTanah = "Kuala Pilah";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("03")) {
                pejTanah = "Port Dickson";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("04")) {
                pejTanah = "Rembau";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("05")) {
                pejTanah = "Seremban";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("06")) {
                pejTanah = "Tampin";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("07")) {
                pejTanah = "Jempol";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("08")) {
                pejTanah = "Kecil Gemas";
            }
        }

        PermohonanKertas kertasP = new PermohonanKertas();
        if (!(permohonan.getSenaraiKertas().isEmpty())) {

            kertasP = devService.findKertasByKod(idPermohonan, "KPTG");

            if (kertasP != null) {
                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                    kertasK = new PermohonanKertasKandungan();
                    kertasK = kertasP.getSenaraiKandungan().get(j);

                    if (kertasK.getBil() == 1) {
                        tujuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 2 && kertasK.getSubtajuk().equals("2.1")) {
                        latarBelakang1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 3 && kertasK.getSubtajuk().equals("3.1")) {
                        butirButirPembangunan1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 5 && kertasK.getSubtajuk().equals("5.1")) {
                        huraianPentadbir1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 6 && kertasK.getSubtajuk().equals("6.1")) {
                        syorPentadbir1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 7 && kertasK.getSubtajuk().equals("7.1")) {
                        huraianTimbalan1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 8 && kertasK.getSubtajuk().equals("8.1")) {
                        syorTimbalan1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 9) {
                        ulasanPTG = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 10) {
                        tajuk = kertasK.getKandungan();
                    }
                }

                senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan5 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan6 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan7 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan8 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan2 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 2);
                senaraiKandungan3 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 3);
                senaraiKandungan5 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 5);
                senaraiKandungan6 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 6);
                senaraiKandungan7 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 7);
                senaraiKandungan8 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 8);
                rowCount2 = senaraiKandungan2.size();
                rowCount3 = senaraiKandungan3.size();
                rowCount5 = senaraiKandungan5.size();
                rowCount6 = senaraiKandungan6.size();
                rowCount7 = senaraiKandungan7.size();
                rowCount8 = senaraiKandungan8.size();
                noRuj = kertasP.getNomborRujukan();
            }

        }
        
        if(rowCount2 == 0){
            rowCount2 = 1;
        }
        
        if(rowCount3 == 0){
            rowCount3 = 1;
        }
        
        if(rowCount5 == 0){
            rowCount5 = 1;
        }
        
        if(rowCount6 == 0){
            rowCount6 = 1;
        }
        
        if(rowCount7 == 0){
            rowCount7 = 1;
        }
        
        if(rowCount8 == 0){
            rowCount8 = 1;
        }

        if ((permohonan.getSenaraiKertas().isEmpty()) || (kertasP == null)) {
            tajuk = "Kertas untuk pertimbangan Pengarah Tanah dan Galian bagi " + permohonan.getKodUrusan().getNama() + " di " + lokasi + ", Daripada "+hakmilik.getSyaratNyata().getSyarat()+" Kepada "+hp.getSyaratNyataBaru().getSyarat();
            tujuan = "Tujuan kertas ini adalah untuk mendapat pertimbangan Pengarah Tanah Dan Galian bagi permohonan daripada " + nama + " untuk " + permohonan.getKodUrusan().getNama() + " di " + lokasi + ".";
            latarBelakang1 = "Pentadbir Tanah " + pejTanah + " telah menerima satu permohonan daripada " + nama + " untuk " + permohonan.getKodUrusan().getNama() + " di " + lokasi + ".";
        }

    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("KPTG");

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

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-----StageId------" + stageId);
        }

        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();
        ArrayList listBill = new ArrayList();

        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }

        if (ulasanPTG == null || ulasanPTG.equals("")) {
            ulasanPTG = "TIADA DATA.";
        }
        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }


        listUlasan.add(tujuan);
        listUlasan.add(ulasanPTG);
        listUlasan.add(tajuk);

        listBill.add(new Integer(1));
        listBill.add(new Integer(9));
        listBill.add(new Integer(10));

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        if (kertasK != null) {
            if (!kertasK.getKandungan().isEmpty()) {
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(tujuan);
                    } else if (kertasKandungan.getBil() == 9) {
                        kertasKandungan.setKandungan(ulasanPTG);
                    } else if (kertasKandungan.getBil() == 10) {
                        kertasKandungan.setKandungan(tajuk);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTG");
                    permohonanKertas.setKodDokumen(kd);
                    permohonanKertas.setNomborRujukan(noRuj);
                    kertasKandungan.setInfoAudit(infoAudit);

                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }

            }
        } else {

            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                Integer bill = (Integer) listBill.get(i);

                kertasK = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTG");
                permohonanKertas.setKodDokumen(kd);
                permohonanKertas.setNomborRujukan(noRuj);

                kertasK.setKertas(permohonanKertas);
                kertasK.setBil(bill);
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(ulasan);
                kertasK.setSubtajuk(subtajuk);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }

        }

        senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 2);
        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("-----------count------------:" + kira2);
        for (int i = 1; i <= kira2; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan2.size() != 0 && i <= senaraiKandungan2.size()) {
                Long id = senaraiKandungan2.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(2);
            String kandungan = getContext().getRequest().getParameter("latarBelakang" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }
        
        senaraiKandungan3 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 3);
        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        LOG.info("-----------count------------:" + kira3);
        for (int i = 1; i <= kira3; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan3.size() != 0 && i <= senaraiKandungan3.size()) {
                Long id = senaraiKandungan3.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(3);
            String kandungan = getContext().getRequest().getParameter("butirButirPembangunan" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("3." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

//        senaraiKandungan5 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 5);
//        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
//        LOG.info("-----------count------------:" + kira5);
//        for (int i = 1; i <= kira5; i++) {
//            InfoAudit iaP = new InfoAudit();
//            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//            if (senaraiKandungan5.size() != 0 && i <= senaraiKandungan5.size()) {
//                Long id = senaraiKandungan5.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
//                    iaP = permohonanKertasKandungan1.getInfoAudit();
//                    iaP.setTarikhKemaskini(new Date());
//                    iaP.setDiKemaskiniOleh(pengguna);
//                }
//            } else {
//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(pengguna);
//            }
//            permohonanKertasKandungan1.setKertas(permohonanKertas);
//            permohonanKertasKandungan1.setBil(5);
//            String kandungan = getContext().getRequest().getParameter("huraianPentadbir" + i);
//            if (kandungan == null || kandungan.equals("")) {
//                kandungan = "TIADA DATA.";
//            }
//            permohonanKertasKandungan1.setKandungan(kandungan);
//            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan1.setSubtajuk("5." + i);
//            permohonanKertasKandungan1.setInfoAudit(iaP);
//            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//        }
//        
//        senaraiKandungan6 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
//        int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
//        LOG.info("-----------count------------:" + kira6);
//        for (int i = 1; i <= kira6; i++) {
//            InfoAudit iaP = new InfoAudit();
//            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//            if (senaraiKandungan6.size() != 0 && i <= senaraiKandungan6.size()) {
//                Long id = senaraiKandungan6.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
//                    iaP = permohonanKertasKandungan1.getInfoAudit();
//                    iaP.setTarikhKemaskini(new Date());
//                    iaP.setDiKemaskiniOleh(pengguna);
//                }
//            } else {
//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(pengguna);
//            }
//            permohonanKertasKandungan1.setKertas(permohonanKertas);
//            permohonanKertasKandungan1.setBil(6);
//            String kandungan = getContext().getRequest().getParameter("syorPentadbir" + i);
//            if (kandungan == null || kandungan.equals("")) {
//                kandungan = "TIADA DATA.";
//            }
//            permohonanKertasKandungan1.setKandungan(kandungan);
//            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan1.setSubtajuk("6." + i);
//            permohonanKertasKandungan1.setInfoAudit(iaP);
//            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//        }
//        
//        
        senaraiKandungan7 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 7);
        int kira7=0;
        if(getContext().getRequest().getParameter("rowCount7") != null){
            kira7 = Integer.parseInt(getContext().getRequest().getParameter("rowCount7"));
        }
        LOG.info("-----------countxxx------------:" + kira7);
        for (int i = 1; i <= kira7; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan7.size() != 0 && i <= senaraiKandungan7.size()) {
                Long id = senaraiKandungan7.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(7);
            String kandungan = getContext().getRequest().getParameter("huraianTimbalan" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("7." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }
        
        senaraiKandungan8 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 8);
        int kira8=0;
        if(getContext().getRequest().getParameter("rowCount8") != null){
            kira8 = Integer.parseInt(getContext().getRequest().getParameter("rowCount8"));
        }
        LOG.info("-----------count8------------:" + kira8);
        for (int i = 1; i <= kira8; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan8.size() != 0 && i <= senaraiKandungan8.size()) {
                Long id = senaraiKandungan8.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(8);
            String kandungan = getContext().getRequest().getParameter("syorTimbalan" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("8." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 2);
        senaraiKandungan3 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 3);
        senaraiKandungan5 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 5);
        senaraiKandungan6 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
        senaraiKandungan7 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 7);
        senaraiKandungan8 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 8);
        rowCount2 = senaraiKandungan2.size();
        rowCount3 = senaraiKandungan3.size();
        rowCount5 = senaraiKandungan5.size();
        rowCount6 = senaraiKandungan6.size();
        rowCount7 = senaraiKandungan7.size();
        rowCount8 = senaraiKandungan8.size();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahBahagian/kertas_pertimbangan_PTG2.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        } catch (Exception e) {
            LOG.debug("Hapus empty record");
        }
        if (permohonanKertasKandungan1 != null) {
            devService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("Record deleted Successfully...");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(KertasPertimbanganPTGActionBean.class, "locate");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
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

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getHuraianPentadbir1() {
        return huraianPentadbir1;
    }

    public void setHuraianPentadbir1(String huraianPentadbir1) {
        this.huraianPentadbir1 = huraianPentadbir1;
    }

    public String getLatarBelakang1() {
        return latarBelakang1;
    }

    public void setLatarBelakang1(String latarBelakang1) {
        this.latarBelakang1 = latarBelakang1;
    }

    public String getSyorPentadbir1() {
        return syorPentadbir1;
    }

    public void setSyorPentadbir1(String syorPentadbir1) {
        this.syorPentadbir1 = syorPentadbir1;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getKeputusanPTG() {
        return keputusanPTG;
    }

    public void setKeputusanPTG(String keputusanPTG) {
        this.keputusanPTG = keputusanPTG;
    }

    public String getUlasanPTG() {
        return ulasanPTG;
    }

    public void setUlasanPTG(String ulasanPTG) {
        this.ulasanPTG = ulasanPTG;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public int getRowCount5() {
        return rowCount5;
    }

    public void setRowCount5(int rowCount5) {
        this.rowCount5 = rowCount5;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<PermohonanKertasKandungan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan5() {
        return senaraiKandungan5;
    }

    public void setSenaraiKandungan5(List<PermohonanKertasKandungan> senaraiKandungan5) {
        this.senaraiKandungan5 = senaraiKandungan5;
    }

    public String getHuraianTimbalan1() {
        return huraianTimbalan1;
    }

    public void setHuraianTimbalan1(String huraianTimbalan1) {
        this.huraianTimbalan1 = huraianTimbalan1;
    }

    public String getSyorTimbalan1() {
        return syorTimbalan1;
    }

    public void setSyorTimbalan1(String syorTimbalan1) {
        this.syorTimbalan1 = syorTimbalan1;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public String getNoRuj() {
        return noRuj;
    }

    public void setNoRuj(String noRuj) {
        this.noRuj = noRuj;
    }

    public int getRowCount7() {
        return rowCount7;
    }

    public void setRowCount7(int rowCount7) {
        this.rowCount7 = rowCount7;
    }    

    public List<PermohonanKertasKandungan> getSenaraiKandungan7() {
        return senaraiKandungan7;
    }

    public void setSenaraiKandungan7(List<PermohonanKertasKandungan> senaraiKandungan7) {
        this.senaraiKandungan7 = senaraiKandungan7;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan6() {
        return senaraiKandungan6;
    }

    public void setSenaraiKandungan6(List<PermohonanKertasKandungan> senaraiKandungan6) {
        this.senaraiKandungan6 = senaraiKandungan6;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan8() {
        return senaraiKandungan8;
    }

    public void setSenaraiKandungan8(List<PermohonanKertasKandungan> senaraiKandungan8) {
        this.senaraiKandungan8 = senaraiKandungan8;
    }

    public int getRowCount6() {
        return rowCount6;
    }

    public void setRowCount6(int rowCount6) {
        this.rowCount6 = rowCount6;
    }

    public int getRowCount8() {
        return rowCount8;
    }

    public void setRowCount8(int rowCount8) {
        this.rowCount8 = rowCount8;
    }

    public String getButirButirPembangunan1() {
        return butirButirPembangunan1;
    }

    public void setButirButirPembangunan1(String butirButirPembangunan1) {
        this.butirButirPembangunan1 = butirButirPembangunan1;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan3() {
        return senaraiKandungan3;
    }

    public void setSenaraiKandungan3(List<PermohonanKertasKandungan> senaraiKandungan3) {
        this.senaraiKandungan3 = senaraiKandungan3;
    }

    public int getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(int rowCount3) {
        this.rowCount3 = rowCount3;
    }
    
}
