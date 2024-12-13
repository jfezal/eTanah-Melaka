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

import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.util.Date;

import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;

import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.RegService;
import etanah.workflow.WorkFlowService;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Massita
 */
@UrlBinding("/pengambilan/borang_mmkn9_penarikanBalik")
public class PengambilanMMKN9PenarikanBalikActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(PengambilanMMKN9PenarikanBalikActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    RegService regService;
    IWorkflowContext ctx = null;
    private Permohonan permohonanSebelum;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private LaporanTanah laporanTanah;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan permohonanKertasKandungan1;
    private PermohonanKertasKandungan permohonanKertasKandungan2;
//    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan permohonanKertasKandungan5;
    private PermohonanKertasKandungan kertasTahun;
    private PermohonanKertasKandungan kertasBilObj;
    private PermohonanKertasKandungan masaObj;
    private PermohonanKertasKandungan tarikhMesyuaratObj;
    private PermohonanKertasKandungan tempatObj;
    private PermohonanKertasKandungan syorPtgObj;

    private int bil = 0;
    private String masa;
    private String kertasBil;
    private String kandungan;
    private String idKandungan;
    private String tempat;
    private String syorPtg;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan1;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan2;
//    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private Pemohon pemohon;
    private Hakmilik hakmilik;
    private String tujuan;
    private String tarikhMesyuarat;
    String tarikhDaftar;
    String namaProjek;
//    private Boolean opFlag = false;
    private final String tajuk = "KERTAS PERMOHONAN PENGAMBILAN PENARIKAN BALIK";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
    public Resolution showForm() {
//        if (opFlag == false) {
//            addSimpleError("Sila isikan maklumat permohonan terdahulu");
//        }
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        String  Borang_K  = (String)getContext().getRequest().getSession().getAttribute("Borang_K");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
          if(Borang_K !=null){
          addSimpleError("Haraf maaf draf MMKN tidak boleh diisi kerana borang K telah dikeluarkan");
           getContext().getRequest().setAttribute("form1", Boolean.FALSE);
      }
        return new JSP("pengambilan/negerisembilan/penarikanbalik/pengambilan_MMKN9_PenarikanBalik.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {

        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/pengambilan_MMKN9_PenarikanBalik.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug(idPermohonan + "ni id mohon dier");
        permohonan = permohonanDAO.findById(idPermohonan);
        Permohonan pm = permohonanDAO.findById(idPermohonan);
        permohonanSebelum = pm.getPermohonanSebelum();

        if (permohonanSebelum !=null)
        {
        namaProjek = permohonanSebelum.getSebab();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        HakmilikPermohonan hp = new HakmilikPermohonan();

        StringBuffer noLotList = new StringBuffer();
        for(HakmilikPermohonan hp1:hakmilikPermohonanList){
            noLotList = noLotList.append(hp1.getHakmilik().getNoLot()).append(", ");
        }

        hp = permohonanSebelum.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        StringBuilder sb = new StringBuilder();
        sb.append("TUJUAN KERTAS INI ADALAH UNTUK MENDAPATKAN PERTIMBANGAN DAN KEPUTUSAN MAJLIS MESYUARAT ")
                .append("KERAJAAN NEGERI UNTUK PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ");
        sb.append(hakmilik.getNoLot()).append(", ").append(hakmilik.getKodHakmilik().getKod()).append(" ");
        sb.append(hakmilik.getLuas()).append(", ");
        sb.append(hakmilik.getBandarPekanMukim().getNama()).append(",");
        sb.append(" DAERAH ").append(hakmilik.getDaerah().getNama()).append(", NEGERI SEMBILAN BAGI ");
        sb.append(permohonanSebelum.getSebab()).append(".");
        tujuan = sb.toString().toUpperCase();

        if (idPermohonan != null) {
            permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan,tajuk);

            if (permohonanKertas != null) {//
//                opFlag = true;
                kertasBilObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 5);
                masaObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 3);
                tarikhMesyuaratObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 7);
                tempatObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 8);
                syorPtgObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 9);

                if(kertasBilObj != null)
                    kertasBil = kertasBilObj.getKandungan().toLowerCase();
                 if(masaObj != null)
                    masa = masaObj.getKandungan().toLowerCase();
                 if(tarikhMesyuaratObj != null)
                    tarikhMesyuarat = tarikhMesyuaratObj.getKandungan().toLowerCase();
                 if(tempatObj != null)
                    tempat = tempatObj.getKandungan().toLowerCase();
                 if(syorPtgObj != null)
                    syorPtg = syorPtgObj.getKandungan().toLowerCase();

                senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),1);
                senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),2);
                senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),4);
                senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),6);

                if(kertasTahun == null) {
                    kertasTahun = new PermohonanKertasKandungan();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    kertasTahun.setKandungan((sdf.format(new java.util.Date())).toString());
                }
            }
        }

        if(syorPtgObj == null){
             syorPtgObj = new PermohonanKertasKandungan();
             syorPtgObj.setKandungan("Pengarah Tanah dan Galian bersetuju dengan perakuan Pentadbir Tanah dan diangkat untuk pertimbangan majlis.");
         }
        if(syorPtgObj != null && syorPtgObj.getKandungan().equals("Tiada Data")){
             syorPtgObj.setKandungan("Pengarah Tanah dan Galian bersetuju dengan perakuan Pentadbir Tanah dan diangkat untuk pertimbangan majlis.");
         }
        }
        else{
            addSimpleError("Sila masukkan id Permohonan sebelum");
            getContext().getRequest().setAttribute("form1", Boolean.FALSE);
        }
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
        permohonanKertas.setTajuk(tajuk);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);

        senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),1);

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));

        for (int i = 1; i <= kira; i++) {
            if (senaraiKertasKandungan1.size() != 0 && i <= senaraiKertasKandungan1.size()) {
                Long id = senaraiKertasKandungan1.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(1);
            kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan1.setCawangan(cawangan);
            permohonanKertasKandungan1.setSubtajuk("2." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan1);
//                }
        }

        senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),2);

            int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));

            for (int j = 1; j <= kira2; j++) {
                if (senaraiKertasKandungan2.size() != 0 && j <= senaraiKertasKandungan2.size()) {
                Long id = senaraiKertasKandungan2.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
                } else {
                    permohonanKertasKandungan2 = new PermohonanKertasKandungan();
                }
                    permohonanKertasKandungan2.setKertas(permohonanKertas);
                    permohonanKertasKandungan2.setBil(2);
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

            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),4);

            int kira4 = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
            for (int k = 1; k <= kira4; k++) {
                if (senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
                Long id = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
                } else {
                    permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                }
                    permohonanKertasKandungan4.setKertas(permohonanKertas);
                    permohonanKertasKandungan4.setBil(4);
                    kandungan = getContext().getRequest().getParameter("kandungan4" + k);
                    permohonanKertasKandungan4.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan4.setCawangan(cawangan);
                    permohonanKertasKandungan4.setSubtajuk("4." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan4.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
            }

            if (getContext().getRequest().getParameter("kertasBil") != null) {
                if (kertasBilObj == null) {
                    kertasBilObj = new PermohonanKertasKandungan();
                }
                kertasBilObj.setKertas(permohonanKertas);
                kertasBilObj.setBil(5);
                kandungan = getContext().getRequest().getParameter("kertasBil");
                kertasBilObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                kertasBilObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                kertasBilObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(kertasBilObj);
            }
            else
            {
            if (kertasBilObj == null) {
                    kertasBilObj = new PermohonanKertasKandungan();
                }
                kertasBilObj.setKertas(permohonanKertas);
                kertasBilObj.setBil(5);
                kandungan = "TIADA DATA";
                kertasBilObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                kertasBilObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                kertasBilObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(kertasBilObj);

            }

            if (getContext().getRequest().getParameter("masa") != null) {
                if (masaObj == null) {
                    masaObj = new PermohonanKertasKandungan();
                }
                masaObj.setKertas(permohonanKertas);
                masaObj.setBil(3);
                kandungan = getContext().getRequest().getParameter("masa");
                masaObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                masaObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                masaObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(masaObj);
            }
            else
            {
            if (masaObj == null) {
                    masaObj = new PermohonanKertasKandungan();
                }
                masaObj.setKertas(permohonanKertas);
                masaObj.setBil(3);
                kandungan = "TIADA DATA";
                masaObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                masaObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                kertasBilObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(masaObj);

            }

            if (getContext().getRequest().getParameter("tarikhMesyuarat") != null) {
                if (tarikhMesyuaratObj == null) {
                    tarikhMesyuaratObj = new PermohonanKertasKandungan();
                }
                tarikhMesyuaratObj.setKertas(permohonanKertas);
                tarikhMesyuaratObj.setBil(7);
                kandungan = getContext().getRequest().getParameter("tarikhMesyuarat");
                tarikhMesyuaratObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tarikhMesyuaratObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tarikhMesyuaratObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(tarikhMesyuaratObj);
            }
            else
            {
            if (tarikhMesyuaratObj == null) {
                    tarikhMesyuaratObj = new PermohonanKertasKandungan();
                }
                tarikhMesyuaratObj.setKertas(permohonanKertas);
                tarikhMesyuaratObj.setBil(7);
                kandungan = "TIADA DATA";
                tarikhMesyuaratObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tarikhMesyuaratObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tarikhMesyuaratObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(tarikhMesyuaratObj);

            }

            if (getContext().getRequest().getParameter("tempat") != null) {
                if (tempatObj == null) {
                    tempatObj = new PermohonanKertasKandungan();
                }
                tempatObj.setKertas(permohonanKertas);
                tempatObj.setBil(8);
                kandungan = getContext().getRequest().getParameter("tempat");
                tempatObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tempatObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tempatObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(tempatObj);
            }
            else
            {
            if (tempatObj == null) {
                    tempatObj = new PermohonanKertasKandungan();
                }
                tempatObj.setKertas(permohonanKertas);
                tempatObj.setBil(8);
                kandungan = "TIADA DATA";
                tempatObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tempatObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tempatObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(tempatObj);

            }

            if (getContext().getRequest().getParameter("syorPtg") != null) {
                if (syorPtgObj == null) {
                    syorPtgObj = new PermohonanKertasKandungan();
                }
                syorPtgObj.setKertas(permohonanKertas);
                syorPtgObj.setBil(9);
                kandungan = getContext().getRequest().getParameter("syorPtg");
                syorPtgObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                syorPtgObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                syorPtgObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(syorPtgObj);
            }
            else
            {
            if (syorPtgObj == null) {
                    syorPtgObj = new PermohonanKertasKandungan();
                }
                syorPtgObj.setKertas(permohonanKertas);
                syorPtgObj.setBil(9);
                kandungan = "TIADA DATA";
                syorPtgObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                syorPtgObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                syorPtgObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(syorPtgObj);
            }

//            senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),6);
//
//            int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
//            for (int k = 1; k <= kira5; k++) {
//                if (senaraiKertasKandungan5.size() != 0 && k <= senaraiKertasKandungan5.size()) {
//                Long id = senaraiKertasKandungan5.get(k - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//                } else {
//                    permohonanKertasKandungan5 = new PermohonanKertasKandungan();
//                }
//                    permohonanKertasKandungan5.setKertas(permohonanKertas);
//                    permohonanKertasKandungan5.setBil(6);
//                    kandungan = getContext().getRequest().getParameter("kandungan5" + k);
//                    permohonanKertasKandungan5.setKandungan(kandungan);
//                    cawangan = permohonan.getCawangan();
//                    permohonanKertasKandungan5.setCawangan(cawangan);
//                    permohonanKertasKandungan5.setSubtajuk("6." + k);
//                    InfoAudit iaP = new InfoAudit();
//                    iaP.setTarikhMasuk(new Date());
//                    iaP.setDimasukOleh(peng);
//                    permohonanKertasKandungan5.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
//            }


        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new RedirectResolution(PengambilanMMKN9PenarikanBalikActionBean.class, "locate");
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
        permohonanKertas.setTajuk(tajuk);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);

            if (getContext().getRequest().getParameter("kertasTahun.kandungan") != null) {
                if (kertasTahun == null) {
                    kertasTahun = new PermohonanKertasKandungan();

                }
                kertasTahun.setKertas(permohonanKertas);
                kertasTahun.setBil(2);
                kandungan = getContext().getRequest().getParameter("kertasTahun.kandungan");
                kertasTahun.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                kertasTahun.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                kertasTahun.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(kertasTahun);
             }

            if (getContext().getRequest().getParameter("kertasBil") != null) {
                if (kertasBilObj == null) {
                    kertasBilObj = new PermohonanKertasKandungan();
                }
                kertasBilObj.setKertas(permohonanKertas);
                kertasBilObj.setBil(1);
                kandungan = getContext().getRequest().getParameter("kertasBil");
                kertasBilObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                kertasBilObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                kertasBilObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(kertasBilObj);
            }
            else
            {
            if (kertasBilObj == null) {
                    kertasBilObj = new PermohonanKertasKandungan();
                }
                kertasBilObj.setKertas(permohonanKertas);
                kertasBilObj.setBil(1);
                kandungan = "TIADA DATA";
                kertasBilObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                kertasBilObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                kertasBilObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(kertasBilObj);

            }

            if (getContext().getRequest().getParameter("masa") != null) {
                if (masaObj == null) {
                    masaObj = new PermohonanKertasKandungan();
                }
                masaObj.setKertas(permohonanKertas);
                masaObj.setBil(3);
                kandungan = getContext().getRequest().getParameter("masa");
                masaObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                masaObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                masaObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(masaObj);
            }
            else
            {
            if (masaObj == null) {
                    masaObj = new PermohonanKertasKandungan();
                }
                masaObj.setKertas(permohonanKertas);
                masaObj.setBil(3);
                kandungan = "TIADA DATA";
                masaObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                masaObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                kertasBilObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(masaObj);

            }

            if (getContext().getRequest().getParameter("tarikhMesyuarat") != null) {
                if (tarikhMesyuaratObj == null) {
                    tarikhMesyuaratObj = new PermohonanKertasKandungan();
                }
                tarikhMesyuaratObj.setKertas(permohonanKertas);
                tarikhMesyuaratObj.setBil(4);
                kandungan = getContext().getRequest().getParameter("tarikhMesyuarat");
                tarikhMesyuaratObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tarikhMesyuaratObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tarikhMesyuaratObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(tarikhMesyuaratObj);
            }
            else
            {
            if (tarikhMesyuaratObj == null) {
                    tarikhMesyuaratObj = new PermohonanKertasKandungan();
                }
                tarikhMesyuaratObj.setKertas(permohonanKertas);
                tarikhMesyuaratObj.setBil(4);
                kandungan = "TIADA DATA";
                tarikhMesyuaratObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tarikhMesyuaratObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tarikhMesyuaratObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(tarikhMesyuaratObj);

            }

            if (getContext().getRequest().getParameter("tempat") != null) {
                if (tempatObj == null) {
                    tempatObj = new PermohonanKertasKandungan();
                }
                tempatObj.setKertas(permohonanKertas);
                tempatObj.setBil(5);
                kandungan = getContext().getRequest().getParameter("tempat");
                tempatObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tempatObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tempatObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(tempatObj);
            }
            else
            {
            if (tempatObj == null) {
                    tempatObj = new PermohonanKertasKandungan();
                }
                tempatObj.setKertas(permohonanKertas);
                tempatObj.setBil(5);
                kandungan = "TIADA DATA";
                tempatObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tempatObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tempatObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(tempatObj);

            }

            if (getContext().getRequest().getParameter("syorPtgObj.kandungan") != null) {
                if (syorPtgObj == null) {
                    syorPtgObj = new PermohonanKertasKandungan();
                }
                syorPtgObj.setKertas(permohonanKertas);
                syorPtgObj.setBil(17);
                kandungan = getContext().getRequest().getParameter("syorPtgObj.kandungan");
                syorPtgObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                syorPtgObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                syorPtgObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(syorPtgObj);
            }
//            else
//            {
//            if (syorPtgObj == null) {
//                    syorPtgObj = new PermohonanKertasKandungan();
//                }
//                syorPtgObj.setKertas(permohonanKertas);
//                syorPtgObj.setBil(9);
//                kandungan = "TIADA DATA";
//                syorPtgObj.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                syorPtgObj.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                syorPtgObj.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(syorPtgObj);
//            }

        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
//        return new RedirectResolution(PengambilanMMKNPenarikanBalikActionBean.class, "locate");
        return new JSP("pengambilan/negerisembilan/penarikanbalik/pengambilan_MMKN9_PenarikanBalik.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        try{
        permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        }catch(Exception e){
            logger.debug("Hapus empty record");

        }
        if (permohonanKertasKandungan1 != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertasKandungan1.setInfoAudit(ia);
            permohonanKertasKandungan1.setCawangan(cawangan);
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan1);
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(PengambilanMMKN9PenarikanBalikActionBean.class, "locate");
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

    public PermohonanKertasKandungan getPermohonanKertasKandungan2() {
        return permohonanKertasKandungan2;
    }

    public void setPermohonanKertasKandungan2(PermohonanKertasKandungan permohonanKertasKandungan2) {
        this.permohonanKertasKandungan2 = permohonanKertasKandungan2;
    }

//    public PermohonanKertasKandungan getPermohonanKertasKandungan3() {
//        return permohonanKertasKandungan3;
//    }
//
//    public void setPermohonanKertasKandungan3(PermohonanKertasKandungan permohonanKertasKandungan3) {
//        this.permohonanKertasKandungan3 = permohonanKertasKandungan3;
//    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan4() {
        return permohonanKertasKandungan4;
    }

    public void setPermohonanKertasKandungan4(PermohonanKertasKandungan permohonanKertasKandungan4) {
        this.permohonanKertasKandungan4 = permohonanKertasKandungan4;
    }

    public PermohonanKertasKandungan getKertasTahun() {
        return kertasTahun;
    }

    public void setKertasTahun(PermohonanKertasKandungan kertasTahun) {
        this.kertasTahun = kertasTahun;
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

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan1() {
        return senaraiKertasKandungan1;
    }

    public void setSenaraiKertasKandungan1(List<PermohonanKertasKandungan> senaraiKertasKandungan1) {
        this.senaraiKertasKandungan1 = senaraiKertasKandungan1;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
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

//    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan3() {
//        return senaraiKertasKandungan3;
//    }
//
//    public void setSenaraiKertasKandungan3(List<PermohonanKertasKandungan> senaraiKertasKandungan3) {
//        this.senaraiKertasKandungan3 = senaraiKertasKandungan3;
//    }

    public String getKertasbil() {
        return kertasBil;
    }

    public void setKertasbil(String kertasbil) {
        this.kertasBil = kertasbil;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
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

    public PermohonanKertasKandungan getPermohonanKertasKandungan1() {
        return permohonanKertasKandungan1;
    }

    public void setPermohonanKertasKandungan1(PermohonanKertasKandungan permohonanKertasKandungan1) {
        this.permohonanKertasKandungan1 = permohonanKertasKandungan1;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan5() {
        return permohonanKertasKandungan5;
    }

    public void setPermohonanKertasKandungan5(PermohonanKertasKandungan permohonanKertasKandungan5) {
        this.permohonanKertasKandungan5 = permohonanKertasKandungan5;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
        return senaraiKertasKandungan4;
    }

    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan5() {
        return senaraiKertasKandungan5;
    }

    public void setSenaraiKertasKandungan5(List<PermohonanKertasKandungan> senaraiKertasKandungan5) {
        this.senaraiKertasKandungan5 = senaraiKertasKandungan5;
    }

    public PermohonanKertasKandungan getKertasBilObj() {
        return kertasBilObj;
    }

    public void setKertasBilObj(PermohonanKertasKandungan kertasBilObj) {
        this.kertasBilObj = kertasBilObj;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getNamaProjek() {
        return namaProjek;
    }

    public void setNamaProjek(String namaProjek) {
        this.namaProjek = namaProjek;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public String getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(String kertasBil) {
        this.kertasBil = kertasBil;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public PermohonanKertasKandungan getMasaObj() {
        return masaObj;
    }

    public void setMasaObj(PermohonanKertasKandungan masaObj) {
        this.masaObj = masaObj;
    }

    public PermohonanKertasKandungan getTarikhmesyuaratObj() {
        return tarikhMesyuaratObj;
    }

    public void setTarikhmesyuaratObj(PermohonanKertasKandungan tarikhMesyuaratObj) {
        this.tarikhMesyuaratObj = tarikhMesyuaratObj;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public PermohonanKertasKandungan getTempatObj() {
        return tempatObj;
    }

    public void setTempatObj(PermohonanKertasKandungan tempatObj) {
        this.tempatObj = tempatObj;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public PermohonanKertasKandungan getSyorPtgObj() {
        return syorPtgObj;
    }

    public void setSyorPtgObj(PermohonanKertasKandungan syorPtgObj) {
        this.syorPtgObj = syorPtgObj;
    }

//    public Boolean getOpFlag() {
//        return opFlag;
//    }
//
//    public void setOpFlag(Boolean opFlag) {
//        this.opFlag = opFlag;
//    }



}

