/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;

import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.util.Date;

import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodNegeri;
import etanah.model.KodPeranan;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.PenggunaPeranan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;

import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.lang.StringUtils;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.WordUtils;
import java.text.SimpleDateFormat;
import etanah.service.DrafKertasMMKNService;
import etanah.service.RegService;
import etanah.workflow.WorkFlowService;
import java.util.LinkedList;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author massita 
 */
@UrlBinding("/pengambilan/borang_mmkn")
public class PermohonanPenarikanBalikMMKNActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(PermohonanPenarikanBalikMMKNActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PenggunaPerananDAO penggunaperananDao;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    DrafKertasMMKNService drafKertasMMKNService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    RegService regService;
    IWorkflowContext ctx = null;
    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private LaporanTanah laporanTanah;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertasKandungan kertasBil;
    private PermohonanKertasKandungan masa;
    private PermohonanKertasKandungan tempat;
    private PermohonanKertasKandungan tarikhmesyuarat;
    private PermohonanKertasKandungan syorPtg;
    private KodNegeri kodNegeri;
    private PermohonanKertasKandungan permohonanKertasKandungan2;
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    //    private PermohonanKertasKandungan perakuanPentadbirTanah;
    private String tajuk;
    private int bil = 0;
    private String kertasbil;
    private String kandungan;
    private String kandungan2;
    private String kandungan3;
    private String idKandungan;
    private String idKandungan2;
    private String idKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan2;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<Pengguna> listPp;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private Hakmilik hakmilik;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private String tujuan;
//    private String masa;
//    private String tempat;
    String tarikhMesyuarat;
    String tarikhDaftar;
    String namaProjek;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
    public Resolution showForm() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        KodCawangan kod = pguna.getKodCawangan();
        // kene cater ikut peranan..
//        List<PenggunaPeranan> list = penggunaperananDao.findAll();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

//        at opis testing
//        if (pguna.getKodCawangan().getKod().equals(permohonan.getCawangan().getKod())) {
//                if (pguna.getPerananUtama() != null) {
//                    getContext().getRequest().setAttribute("ptptgpengambilan1", Boolean.FALSE);
//                    getContext().getRequest().setAttribute("ptg1", Boolean.FALSE);
//                    //TODO : change peranan utama
////                        4 ptptdpengambilan1
//                       if ((pguna.getPerananUtama().getKod().equals("14")||
//                       pguna.getPerananUtama().getKod().equals("17") ||
//                       pguna.getPerananUtama().getKod().equals("53"))) {
//                        listPp.add(pguna);
//                        getContext().getRequest().setAttribute("form", Boolean.TRUE);
//                        getContext().getRequest().setAttribute("ptptdpengambilan1", Boolean.TRUE);
//                       }
////                         4 ptptgpengambilan1- pengambilan ptg
//                       if (pguna.getPerananUtama().getKod().equals("90")) {
//                        listPp.add(pguna);
//                            getContext().getRequest().setAttribute("form", Boolean.TRUE);
//                            getContext().getRequest().setAttribute("ptptgpengambilan1", Boolean.TRUE);
//                        }
//                       if (pguna.getPerananUtama().getKod().equals("12")) {
//                        listPp.add(pguna);
//                            getContext().getRequest().setAttribute("form", Boolean.TRUE);
//                            getContext().getRequest().setAttribute("ptg1", Boolean.TRUE);
//                        }
//
//                }
//            }

         if (pguna.getKodCawangan().getKod().equals(permohonan.getCawangan().getKod())) {
                if (pguna.getPerananUtama() != null) {
                    getContext().getRequest().setAttribute("ptgptambil1", Boolean.FALSE);
                    getContext().getRequest().setAttribute("ptg1", Boolean.FALSE);
                    //TODO : change peranan utama
//                        4 ptptdpengambilan1
                       if ((pguna.getPerananUtama().getKod().equals("29")||
                       pguna.getPerananUtama().getKod().equals("76") ||
                       pguna.getPerananUtama().getKod().equals("77") ||
                       pguna.getPerananUtama().getKod().equals("9"))) {
                        listPp.add(pguna);
                        getContext().getRequest().setAttribute("form", Boolean.TRUE);
                        getContext().getRequest().setAttribute("mtptambil1", Boolean.TRUE);
                       }
//                         4 ptptgpengambilan1- pengambilan ptg
                       if (pguna.getPerananUtama().getKod().equals("33")) {
                           logger.debug("--------ptgptambil1----");
                        listPp.add(pguna);
                            getContext().getRequest().setAttribute("form", Boolean.TRUE);
                            getContext().getRequest().setAttribute("ptgptambil1", Boolean.TRUE);
                        }
                       if (pguna.getPerananUtama().getKod().equals("12")) {
                           logger.debug("--------ptg1----");
                        listPp.add(pguna);
                            getContext().getRequest().setAttribute("form", Boolean.TRUE);
                            getContext().getRequest().setAttribute("ptg1", Boolean.TRUE);
                        }
                    System.out.println("peranan current"+pguna.getPerananUtama().getKod());

                }
            }

      String  Borang_K  = (String)getContext().getRequest().getSession().getAttribute("Borang_K");
      if(Borang_K !=null){
          addSimpleError("Haraf maaf draf MMKN tidak boleh diisi");
           getContext().getRequest().setAttribute("form", Boolean.FALSE);
      }

        return new JSP("pengambilan/drafKertasPenarikanBalikMMKN.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/drafKertasPenarikanBalikMMKN.jsp").addParameter("tab", "true");
    }

//    public Resolution showPentadbirTanah() {
//        getContext().getRequest().setAttribute("form", Boolean.TRUE);
//        getContext().getRequest().setAttribute("!ptg", Boolean.FALSE);
//        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
//        return new JSP("pengambilan/drafKertasPenarikanBalikMMKN.jsp").addParameter("tab", "true");
//    }
//
//       public Resolution showPtg() {
//         getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
//        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
//        return new JSP("pengambilan/drafKertasPenarikanBalikMMKN.jsp").addParameter("tab", "true");
//    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idSblm = (String)getContext().getRequest().getSession().getAttribute("idSblm");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug(idPermohonan + "ni id mohon dier");
        permohonan = permohonanDAO.findById(idPermohonan);
         Permohonan pm = permohonanDAO.findById(idPermohonan);
            permohonanSebelum = pm.getPermohonanSebelum();
            namaProjek = permohonanSebelum.getSebab();

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonanSebelum.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        StringBuilder sb = new StringBuilder();
        sb.append("TUJUAN KERTAS INI ADALAH UNTUK MENDAPATKAN PERTIMBANGAN DAN KEPUTUSAN MAJLIS MESYUARAT ")
                .append("KERAJAAN NEGERI UNTUK PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ");
        sb.append(hakmilik.getNoLot()).append(" ,").append(hakmilik.getKodHakmilik().getKod()).append(" ");
        sb.append(hakmilik.getLuas()).append(" ,");
        sb.append(hakmilik.getBandarPekanMukim().getNama()).append(" ,");
        sb.append(" DAERAH ").append(hakmilik.getDaerah().getNama()).append(", MELAKA BAGI ");
        sb.append(permohonanSebelum.getSebab()).append(".");
        tujuan = sb.toString().toUpperCase();
//        + kodNegeri.getNama()

            if (idPermohonan != null) {
            permohonanKertas = drafKertasMMKNService.findMMKNByKodIdPermohonan(idPermohonan);

            if (permohonanKertas != null) {
                kertasBil = drafKertasMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
                masa = drafKertasMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 2);
                tarikhmesyuarat = drafKertasMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 3);
                tempat = drafKertasMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 4);
                syorPtg = drafKertasMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 10);
                senaraiKertasKandungan = drafKertasMMKNService.findByKertas(permohonanKertas.getIdKertas());
                logger.debug("senaraiKertasKandungan : " + senaraiKertasKandungan.size());
                senaraiKertasKandungan2 = drafKertasMMKNService.findByKertas2(permohonanKertas.getIdKertas());
                senaraiKertasKandungan3 = drafKertasMMKNService.findByKertas3(permohonanKertas.getIdKertas());
            }
        }

         if(syorPtg == null){
             syorPtg = new PermohonanKertasKandungan();
             syorPtg.setKandungan("Pengarah Tanah dan Galian bersetuju dengan perakuan Pentadbir Tanah Alor Gajah dan diangkat untuk pertimbangan majlis.");
         }
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }
        

        KodCawangan kod = pguna.getKodCawangan();
        // kene cater ikut peranan..
//        List<PenggunaPeranan> list = penggunaperananDao.findAll();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();


//testing @opis
//        if (pguna.getKodCawangan().getKod().equals(permohonan.getCawangan().getKod())) {
//                if (pguna.getPerananUtama() != null) {
//                    getContext().getRequest().setAttribute("ptptgpengambilan1", Boolean.FALSE);
//                    getContext().getRequest().setAttribute("ptg1", Boolean.FALSE);
//                    //TODO : change peranan utama
////                        4 ptptdpengambilan1
//                       if ((pguna.getPerananUtama().getKod().equals("14")||
//                       pguna.getPerananUtama().getKod().equals("17") ||
//                       pguna.getPerananUtama().getKod().equals("53"))) {
//                        listPp.add(pguna);
//                        getContext().getRequest().setAttribute("form", Boolean.TRUE);
//                        getContext().getRequest().setAttribute("ptptdpengambilan1", Boolean.TRUE);
//                       }
//
////                         4 ptptgpengambilan1- pengambilan ptg
//                       if (pguna.getPerananUtama().getKod().equals("90")) {
//                        listPp.add(pguna);
//                            getContext().getRequest().setAttribute("form", Boolean.TRUE);
//                            getContext().getRequest().setAttribute("ptptgpengambilan1", Boolean.TRUE);
//                        }
//                       if (pguna.getPerananUtama().getKod().equals("12")) {
//                        listPp.add(pguna);
//                            getContext().getRequest().setAttribute("form", Boolean.TRUE);
//                            getContext().getRequest().setAttribute("ptg1", Boolean.TRUE);
//                        }
//
//                }
//            }

        if (pguna.getKodCawangan().getKod().equals(permohonan.getCawangan().getKod())) {
                if (pguna.getPerananUtama() != null) {
                    getContext().getRequest().setAttribute("ptgptambil1", Boolean.FALSE);
                    getContext().getRequest().setAttribute("ptg1", Boolean.FALSE);
                    //TODO : change peranan utama
//                        4 ptptdpengambilan1
                       if ((pguna.getPerananUtama().getKod().equals("29")||
                       pguna.getPerananUtama().getKod().equals("76") ||
                       pguna.getPerananUtama().getKod().equals("77"))) {
                        listPp.add(pguna);
                        getContext().getRequest().setAttribute("form", Boolean.TRUE);
                        getContext().getRequest().setAttribute("mtptambil1", Boolean.TRUE);
                       }

//                         4 ptptgpengambilan1- pengambilan ptg
                       if (pguna.getPerananUtama().getKod().equals("33")) {
                           logger.debug("--------ptgptambil1----");
                        listPp.add(pguna);
                            getContext().getRequest().setAttribute("form", Boolean.TRUE);
                            getContext().getRequest().setAttribute("ptgptambil1", Boolean.TRUE);
                        }
                       if (pguna.getPerananUtama().getKod().equals("12")) {
                           logger.debug("--------ptgptambil1----");
                        listPp.add(pguna);
                            getContext().getRequest().setAttribute("form", Boolean.TRUE);
                            getContext().getRequest().setAttribute("ptg1", Boolean.TRUE);
                        }

                }
            }


        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        //getContext().getRequest().setAttribute("ptptgpengambilan1", Boolean.TRUE);
        logger.debug(getContext().getRequest().getParameter("rowCount") + " asasasaasd");
        logger.debug(getContext().getRequest().getParameter("rowCount2") + " heeee");
        logger.debug(getContext().getRequest().getParameter("rowCount3") + " keeee");
    }

    public Resolution simpan() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKN");
        cawangan = permohonan.getCawangan();
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new Date());
        iaPermohonan.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(iaPermohonan);
        permohonanKertas.setTajuk("KERTAS PERMOHONAN PENGAMBILAN PENARIKAN BALIK");
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);

        senaraiKertasKandungan = drafKertasMMKNService.findByKertas(permohonanKertas.getIdKertas());

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));

        for (int i = 1; i <= kira; i++) {
            if (senaraiKertasKandungan.size() != 0 && i <= senaraiKertasKandungan.size()) {
                {
                    Long id = senaraiKertasKandungan.get(i - 1).getIdKandungan();

                    if (id != null) {
                        System.out.println("-------ID Kandunganan-----------"+id);
                        permohonanKertasKandungan = drafKertasMMKNService.findkandunganByIdKandungan(id);
                        logger.debug(id + " lagi idKandungan ler");
                    }
                }
            } else {
//                    if (permohonanKertasKandungan == null)
//                    {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
//                    }
            }
            permohonanKertasKandungan.setKertas(permohonanKertas);
            // logger.debug(permohonanKertas.getIdKertas()+" after set idKertas");
            permohonanKertasKandungan.setBil(7);
            kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            permohonanKertasKandungan.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan.setCawangan(cawangan);
            permohonanKertasKandungan.setSubtajuk("2." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
//                }
        }

        senaraiKertasKandungan2 = drafKertasMMKNService.findByKertas2(permohonanKertas.getIdKertas());

            int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));

            for (int j = 1; j
                    <= kira2; j++) {
                if (senaraiKertasKandungan2.size() != 0 && j <= senaraiKertasKandungan2.size()) {
                   {
                        Long id2 = senaraiKertasKandungan2.get(j - 1).getIdKandungan();

                        if (id2 != null) {
                            permohonanKertasKandungan2 = drafKertasMMKNService.findkandunganByIdKandungan2(id2);
                      }
                    }
                } else {
                    permohonanKertasKandungan2 = new PermohonanKertasKandungan();
                }
                    logger.debug(permohonanKertas.getIdKertas()+"ID1");
                    permohonanKertasKandungan2.setKertas(permohonanKertas);
                    permohonanKertasKandungan2.setBil(8);
                    kandungan = getContext().getRequest().getParameter("kandungan2" + j);
                    permohonanKertasKandungan2.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan2.setCawangan(cawangan);
                    permohonanKertasKandungan2.setSubtajuk("3." + j);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan2.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
            }

            senaraiKertasKandungan3 = drafKertasMMKNService.findByKertas3(permohonanKertas.getIdKertas());

            int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));

            for (int k = 1; k
                    <= kira3; k++) {
                if (senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
                    {
                        Long id3 = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
             if (id3 != null) {
                            permohonanKertasKandungan3 = drafKertasMMKNService.findkandunganByIdKandungan3(id3);
                        }
                    }
                } else {
                    permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                }
                    logger.debug(permohonanKertas.getIdKertas()+"ID2");
                    permohonanKertasKandungan3.setKertas(permohonanKertas);
                    permohonanKertasKandungan3.setBil(9);
                    kandungan = getContext().getRequest().getParameter("kandungan3" + k);
                    permohonanKertasKandungan3.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan3.setCawangan(cawangan);
                    permohonanKertasKandungan3.setSubtajuk("4." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan3.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
            }

        if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
            if (kertasBil == null) {
                kertasBil = new PermohonanKertasKandungan();

            }
            kertasBil.setKertas(permohonanKertas);
            kertasBil.setBil(1);
            kandungan = getContext().getRequest().getParameter("kertasBil.kandungan");
            kertasBil.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            kertasBil.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            kertasBil.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
        }

        if (getContext().getRequest().getParameter("masa.kandungan") != null) {
            if (masa == null) {
                masa = new PermohonanKertasKandungan();

            }
            masa.setKertas(permohonanKertas);
            masa.setBil(2);
            kandungan = getContext().getRequest().getParameter("masa.kandungan");
            masa.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            masa.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            masa.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(masa);
        }

        if (getContext().getRequest().getParameter("tarikhmesyuarat.kandungan") != null) {
            if (tarikhmesyuarat == null) {
                tarikhmesyuarat = new PermohonanKertasKandungan();

            }
            logger.debug(permohonanKertas.getIdKertas() + "ID5");
            tarikhmesyuarat.setKertas(permohonanKertas);
            tarikhmesyuarat.setBil(3);
            kandungan = getContext().getRequest().getParameter("tarikhmesyuarat.kandungan");
            tarikhmesyuarat.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tarikhmesyuarat.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            tarikhmesyuarat.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(tarikhmesyuarat);
        }

        if (getContext().getRequest().getParameter("tempat.kandungan") != null) {
            if (tempat == null) {
                tempat = new PermohonanKertasKandungan();
            }
            logger.debug(permohonanKertas.getIdKertas() + "ID6");
            tempat.setKertas(permohonanKertas);
            tempat.setBil(4);
            kandungan = getContext().getRequest().getParameter("tempat.kandungan");
            tempat.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tempat.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            tempat.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(tempat);
        }
            
       if (getContext().getRequest().getParameter("syorPtg.kandungan") != null) {
            if (syorPtg == null) {
                syorPtg = new PermohonanKertasKandungan();
            }
            logger.debug(permohonanKertas.getIdKertas() + "ID7");
            syorPtg.setKertas(permohonanKertas);
            syorPtg.setBil(10);
            kandungan = getContext().getRequest().getParameter("syorPtg.kandungan");
            syorPtg.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            syorPtg.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            syorPtg.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(syorPtg);
        }
        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new RedirectResolution(PermohonanPenarikanBalikMMKNActionBean.class, "locate");
    }

    public Resolution simpanPtg() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKN");
        cawangan = permohonan.getCawangan();
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new Date());
        iaPermohonan.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(iaPermohonan);
        permohonanKertas.setTajuk("KERTAS PERMOHONAN PENGAMBILAN PENARIKAN BALIK");
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);

        senaraiKertasKandungan = drafKertasMMKNService.findByKertas(permohonanKertas.getIdKertas());

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));
//        String count = Integer.toString(kira);
//         if (StringUtils.isNotBlank(count)) {
//            addSimpleError("tiada data");
//            getContext().getRequest().setAttribute("view", Boolean.TRUE);
//        }

        for (int i = 1; i <= kira; i++) {
            if (senaraiKertasKandungan.size() != 0) {

                logger.debug(senaraiKertasKandungan.size() + "size senarai");
                if (i <= senaraiKertasKandungan.size()) {
                    Long id = senaraiKertasKandungan.get(i - 1).getIdKandungan();

                    if (id != null) {
                        permohonanKertasKandungan = drafKertasMMKNService.findkandunganByIdKandungan(id);
                        logger.debug(id + " lagi idKandungan ler");
                    }
                }
            } else {
//                    if (permohonanKertasKandungan == null)
//                    {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
//                    }
            }
            permohonanKertasKandungan.setKertas(permohonanKertas);
            // logger.debug(permohonanKertas.getIdKertas()+" after set idKertas");
            permohonanKertasKandungan.setBil(7);
            kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            permohonanKertasKandungan.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan.setCawangan(cawangan);
            permohonanKertasKandungan.setSubtajuk("2." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
//                }
        }

            int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
//            String count2 = Integer.toString(kira2);
//             if (StringUtils.isNotBlank(count2)) {
//                addSimpleError("tiada data");
//                getContext().getRequest().setAttribute("view", Boolean.TRUE);
//            }

            for (int j = 1; j
                    <= kira2; j++) {
                if (senaraiKertasKandungan2.size() != 0) {
                    if (j <= senaraiKertasKandungan2.size()) {
                        Long id2 = senaraiKertasKandungan2.get(j - 1).getIdKandungan();

                        if (id2 != null) {
                            permohonanKertasKandungan2 = drafKertasMMKNService.findkandunganByIdKandungan2(id2);
                      }
                    }
                } else {
                    permohonanKertasKandungan2 = new PermohonanKertasKandungan();
                }
                    logger.debug(permohonanKertas.getIdKertas()+"ID1");
                    permohonanKertasKandungan2.setKertas(permohonanKertas);
                    permohonanKertasKandungan2.setBil(8);
                    kandungan = getContext().getRequest().getParameter("kandungan2" + j);
                    permohonanKertasKandungan2.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan2.setCawangan(cawangan);
                    permohonanKertasKandungan2.setSubtajuk("3." + j);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan2.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
            }

            int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
//            String count3 = Integer.toString(kira3);
//             if (StringUtils.isNotBlank(count3)) {
//                addSimpleError("tiada data");
//                getContext().getRequest().setAttribute("view", Boolean.TRUE);
//            }

            for (int k = 1; k
                    <= kira3; k++) {
                if (senaraiKertasKandungan3.size() != 0) {
                    if (k <= senaraiKertasKandungan3.size()) {
                        Long id3 = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
             if (id3 != null) {
                            permohonanKertasKandungan3 = drafKertasMMKNService.findkandunganByIdKandungan3(id3);
                        }
                    }
                } else {
                    permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                }
                    logger.debug(permohonanKertas.getIdKertas()+"ID2");
                    permohonanKertasKandungan3.setKertas(permohonanKertas);
                    permohonanKertasKandungan3.setBil(9);
                    kandungan = getContext().getRequest().getParameter("kandungan3" + k);
                    permohonanKertasKandungan3.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan3.setCawangan(cawangan);
                    permohonanKertasKandungan3.setSubtajuk("4." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan3.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
            }

        if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
            if (kertasBil == null) {
                kertasBil = new PermohonanKertasKandungan();

            }
            logger.debug(permohonanKertas.getIdKertas() + "ID3");
            kertasBil.setKertas(permohonanKertas);
            kertasBil.setBil(1);
            kandungan = getContext().getRequest().getParameter("kertasBil.kandungan");
            kertasBil.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            kertasBil.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            kertasBil.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
        }

        if (getContext().getRequest().getParameter("masa.kandungan") != null) {
            if (masa == null) {
                masa = new PermohonanKertasKandungan();

            }
            logger.debug(permohonanKertas.getIdKertas() + "ID4");
            masa.setKertas(permohonanKertas);
            masa.setBil(2);
            kandungan = getContext().getRequest().getParameter("masa.kandungan");
            masa.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            masa.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            masa.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(masa);
        }

        if (getContext().getRequest().getParameter("tarikhmesyuarat.kandungan") != null) {
            if (tarikhmesyuarat == null) {
                tarikhmesyuarat = new PermohonanKertasKandungan();

            }
            logger.debug(permohonanKertas.getIdKertas() + "ID5");
            tarikhmesyuarat.setKertas(permohonanKertas);
            tarikhmesyuarat.setBil(3);
            kandungan = getContext().getRequest().getParameter("tarikhmesyuarat.kandungan");
            tarikhmesyuarat.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tarikhmesyuarat.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            tarikhmesyuarat.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(tarikhmesyuarat);
        }

        if (getContext().getRequest().getParameter("tempat.kandungan") != null) {
            if (tempat == null) {
                tempat = new PermohonanKertasKandungan();
            }
            logger.debug(permohonanKertas.getIdKertas() + "ID6");
            tempat.setKertas(permohonanKertas);
            tempat.setBil(4);
            kandungan = getContext().getRequest().getParameter("tempat.kandungan");
            tempat.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tempat.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            tempat.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(tempat);
        }

        if (getContext().getRequest().getParameter("syorPtg.kandungan") != null) {
            if (syorPtg == null) {
                syorPtg = new PermohonanKertasKandungan();
            }
            logger.debug(permohonanKertas.getIdKertas() + "ID7");
            syorPtg.setKertas(permohonanKertas);
            syorPtg.setBil(10);
            kandungan = getContext().getRequest().getParameter("syorPtg.kandungan");
            syorPtg.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            syorPtg.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            syorPtg.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(syorPtg);
            }
        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new RedirectResolution(PermohonanPenarikanBalikMMKNActionBean.class, "locate");
    }

    public Resolution simpanPtd() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKN");
        cawangan = permohonan.getCawangan();
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new Date());
        iaPermohonan.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(iaPermohonan);
        permohonanKertas.setTajuk("KERTAS PERMOHONAN PENGAMBILAN PENARIKAN BALIK");
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);

        senaraiKertasKandungan = drafKertasMMKNService.findByKertas(permohonanKertas.getIdKertas());

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));
//        String count = Integer.toString(kira);
//         if (StringUtils.isNotBlank(count)) {
//            addSimpleError("tiada data");
//            getContext().getRequest().setAttribute("view", Boolean.TRUE);
//        }

        for (int i = 1; i <= kira; i++) {
            if (senaraiKertasKandungan.size() != 0) {

                logger.debug(senaraiKertasKandungan.size() + "size senarai");
                if (i <= senaraiKertasKandungan.size()) {
                    Long id = senaraiKertasKandungan.get(i - 1).getIdKandungan();

                    if (id != null) {
                        permohonanKertasKandungan = drafKertasMMKNService.findkandunganByIdKandungan(id);
                        logger.debug(id + " lagi idKandungan ler");
                    }
                }
            } else {
//                    if (permohonanKertasKandungan == null)
//                    {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
//                    }
            }
            permohonanKertasKandungan.setKertas(permohonanKertas);
            // logger.debug(permohonanKertas.getIdKertas()+" after set idKertas");
            permohonanKertasKandungan.setBil(7);
            kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            permohonanKertasKandungan.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan.setCawangan(cawangan);
            permohonanKertasKandungan.setSubtajuk("2." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
//                }
        }

            int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
//            String count2 = Integer.toString(kira2);
//             if (StringUtils.isNotBlank(count2)) {
//                addSimpleError("tiada data");
//                getContext().getRequest().setAttribute("view", Boolean.TRUE);
//            }

            for (int j = 1; j
                    <= kira2; j++) {
                if (senaraiKertasKandungan2.size() != 0) {
                    if (j <= senaraiKertasKandungan2.size()) {
                        Long id2 = senaraiKertasKandungan2.get(j - 1).getIdKandungan();

                        if (id2 != null) {
                            permohonanKertasKandungan2 = drafKertasMMKNService.findkandunganByIdKandungan2(id2);
                      }
                    }
                } else {
                    permohonanKertasKandungan2 = new PermohonanKertasKandungan();
                }
                    logger.debug(permohonanKertas.getIdKertas()+"ID1");
                    permohonanKertasKandungan2.setKertas(permohonanKertas);
                    permohonanKertasKandungan2.setBil(8);
                    kandungan = getContext().getRequest().getParameter("kandungan2" + j);
                    permohonanKertasKandungan2.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan2.setCawangan(cawangan);
                    permohonanKertasKandungan2.setSubtajuk("3." + j);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan2.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
            }

            int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));

            for (int k = 1; k
                    <= kira3; k++) {
                if (senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
                    {
                        Long id3 = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
             if (id3 != null) {
                            permohonanKertasKandungan3 = drafKertasMMKNService.findkandunganByIdKandungan3(id3);
                        }
                    }
                } else {
                    permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                }
                    logger.debug(permohonanKertas.getIdKertas()+"ID2");
                    permohonanKertasKandungan3.setKertas(permohonanKertas);
                    permohonanKertasKandungan3.setBil(9);
                    kandungan = getContext().getRequest().getParameter("kandungan3" + k);
                    permohonanKertasKandungan3.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan3.setCawangan(cawangan);
                    permohonanKertasKandungan3.setSubtajuk("4." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan3.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
            }

        if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
            if (kertasBil == null) {
                kertasBil = new PermohonanKertasKandungan();

            }
            logger.debug(permohonanKertas.getIdKertas() + "ID3");
            kertasBil.setKertas(permohonanKertas);
            kertasBil.setBil(1);
            kandungan = getContext().getRequest().getParameter("kertasBil.kandungan");
            kertasBil.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            kertasBil.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            kertasBil.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
        }

        if (getContext().getRequest().getParameter("masa.kandungan") != null) {
            if (masa == null) {
                masa = new PermohonanKertasKandungan();

            }
            logger.debug(permohonanKertas.getIdKertas() + "ID4");
            masa.setKertas(permohonanKertas);
            masa.setBil(2);
            kandungan = getContext().getRequest().getParameter("masa.kandungan");
            masa.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            masa.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            masa.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(masa);
        }

        if (getContext().getRequest().getParameter("tarikhmesyuarat.kandungan") != null) {
            if (tarikhmesyuarat == null) {
                tarikhmesyuarat = new PermohonanKertasKandungan();

            }
            logger.debug(permohonanKertas.getIdKertas() + "ID5");
            tarikhmesyuarat.setKertas(permohonanKertas);
            tarikhmesyuarat.setBil(3);
            kandungan = getContext().getRequest().getParameter("tarikhmesyuarat.kandungan");
            tarikhmesyuarat.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tarikhmesyuarat.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            tarikhmesyuarat.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(tarikhmesyuarat);
        }

        if (getContext().getRequest().getParameter("tempat.kandungan") != null) {
            if (tempat == null) {
                tempat = new PermohonanKertasKandungan();
            }
            logger.debug(permohonanKertas.getIdKertas() + "ID6");
            tempat.setKertas(permohonanKertas);
            tempat.setBil(4);
            kandungan = getContext().getRequest().getParameter("tempat.kandungan");
            tempat.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tempat.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            tempat.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(tempat);
        }

//        if (getContext().getRequest().getParameter("syorPtg.kandungan") != null) {
//            if (syorPtg == null) {
//                syorPtg = new PermohonanKertasKandungan();
//            }
//            logger.debug(permohonanKertas.getIdKertas() + "ID7");
//            syorPtg.setKertas(permohonanKertas);
//            syorPtg.setBil(10);
//            kandungan = getContext().getRequest().getParameter("syorPtg.kandungan");
//            syorPtg.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            syorPtg.setCawangan(cawangan);
//            //permohonanKertas.setTajuk("Pentadbir Tanah");
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            syorPtg.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(syorPtg);
//            }
        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        return new RedirectResolution(PermohonanPenarikanBalikMMKNActionBean.class, "locate");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        try{
        permohonanKertasKandungan = drafKertasMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        }catch(Exception e){
            logger.debug("Hapus empty record");

        }
       if (permohonanKertasKandungan != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertasKandungan.setInfoAudit(ia);
            permohonanKertasKandungan.setCawangan(cawangan);
            drafKertasMMKNService.deleteKertasKandungan(permohonanKertasKandungan);
        }


//        HakmilikPermohonan hp = new HakmilikPermohonan();
//
//        hp = permohonan.getSenaraiHakmilik().get(0);
//        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
//
//        String[] tname = {"permohonan"};
//        Object[] model = {permohonan};
//
//        List<Pemohon> listPemohon;
//        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
//
//        if (!(listPemohon.isEmpty())) {
//            pemohon = listPemohon.get(0);
//        }
//
//        List<PermohonanPihak> listPenerima;
//        listPenerima = permohonan.getSenaraiPihak();
//
//        if (!(listPenerima.isEmpty())) {
//            penerima = listPenerima.get(0);
//        }
//
//        List<HakmilikPihakBerkepentingan> listPB;
//        listPB = hakmilik.getSenaraiPihakBerkepentingan();
//
//        if (!(listPB.isEmpty())) {
//            hakmilikPihakBerkepentingan = listPB.get(0);
//        }
//
//        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
      return new RedirectResolution(PermohonanPenarikanBalikMMKNActionBean.class, "locate");
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan2() {
        return permohonanKertasKandungan2;
    }

    public void setPermohonanKertasKandungan2(PermohonanKertasKandungan permohonanKertasKandungan2) {
        this.permohonanKertasKandungan2 = permohonanKertasKandungan2;
    }

    public PermohonanKertasKandungan getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(PermohonanKertasKandungan kertasBil) {
        this.kertasBil = kertasBil;
    }

    public PermohonanKertasKandungan getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(PermohonanKertasKandungan syorPtg) {
        this.syorPtg = syorPtg;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan3() {
        return permohonanKertasKandungan3;
    }

    public void setPermohonanKertasKandungan3(PermohonanKertasKandungan permohonanKertasKandungan3) {
        this.permohonanKertasKandungan3 = permohonanKertasKandungan3;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan4() {
        return permohonanKertasKandungan4;
    }

    public void setPermohonanKertasKandungan4(PermohonanKertasKandungan permohonanKertasKandungan4) {
        this.permohonanKertasKandungan4 = permohonanKertasKandungan4;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public PermohonanKertasKandunganDAO getPermohonanKertasKandunganDAO() {
        return permohonanKertasKandunganDAO;

    }

    public void setPermohonanKertasKandunganDAO(PermohonanKertasKandunganDAO permohonanKertasKandunganDAO) {
        this.permohonanKertasKandunganDAO = permohonanKertasKandunganDAO;

    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public String getKandungan() {
        return kandungan;

    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public String getKandungan2() {
        return kandungan2;
    }

    public void setKandungan2(String kandungan2) {
        this.kandungan2 = kandungan2;
    }

    public String getKandungan3() {
        return kandungan3;
    }

    public void setKandungan3(String kandungan3) {
        this.kandungan3 = kandungan3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan() {
        return senaraiKertasKandungan;
    }

    public void setSenaraiKertasKandungan(List<PermohonanKertasKandungan> senaraiKertasKandungan) {
        this.senaraiKertasKandungan = senaraiKertasKandungan;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getIdKandungan2() {
        return idKandungan2;
    }

    public void setIdKandungan2(String idKandungan2) {
        this.idKandungan2 = idKandungan2;
    }

    public String getIdKandungan3() {
        return idKandungan3;
    }

    public void setIdKandungan3(String idKandungan3) {
        this.idKandungan3 = idKandungan3;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan2() {
        return senaraiKertasKandungan2;
    }

    public void setSenaraiKertasKandungan2(List<PermohonanKertasKandungan> senaraiKertasKandungan2) {
        this.senaraiKertasKandungan2 = senaraiKertasKandungan2;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan3() {
        return senaraiKertasKandungan3;
    }

    public void setSenaraiKertasKandungan3(List<PermohonanKertasKandungan> senaraiKertasKandungan3) {
        this.senaraiKertasKandungan3 = senaraiKertasKandungan3;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getKertasbil() {
        return kertasbil;
    }

    public void setKertasbil(String kertasbil) {
        this.kertasbil = kertasbil;
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

    public PermohonanKertasKandungan getTarikhmesyuarat() {
        return tarikhmesyuarat;
    }

    public void setTarikhmesyuarat(PermohonanKertasKandungan tarikhmesyuarat) {
        this.tarikhmesyuarat = tarikhmesyuarat;
    }

    public PermohonanKertasKandungan getTempat() {
        return tempat;
    }

    public void setTempat(PermohonanKertasKandungan tempat) {
        this.tempat = tempat;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public PermohonanKertasKandungan getMasa() {
        return masa;
    }

    public void setMasa(PermohonanKertasKandungan masa) {
        this.masa = masa;
    }

    public String getNamaProjek() {
        return namaProjek;
    }

    public void setNamaProjek(String namaProjek) {
        this.namaProjek = namaProjek;
    }
}
