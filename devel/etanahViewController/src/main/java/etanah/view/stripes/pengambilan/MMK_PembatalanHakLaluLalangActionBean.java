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
import etanah.model.PermohonanPihak;
import etanah.model.PihakPengarah;

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
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Massita
 */
@UrlBinding("/pengambilan/mmk_BatalLaluLalang")
public class MMK_PembatalanHakLaluLalangActionBean extends AbleActionBean {


    private static Logger logger = Logger.getLogger(MMK_PembatalanHakLaluLalangActionBean.class);
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
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;

    private PermohonanPihak penerima;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private LaporanTanah laporanTanah;
    private String heading;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan kertasTahun;
    private PermohonanKertasKandungan kertasBil;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan tujuanObj;
    private PermohonanKertasKandungan tarikhMesyuaratObj;
    private PermohonanKertasKandungan permohonanKertasKandungan1;
    private PermohonanKertasKandungan permohonanKertasKandungan2;
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan permohonanKertasKandungan5;
    private PermohonanKertasKandungan jawatanKuasaKhasObj;
   
    private int bil = 0;
    private String ulasan1;
    private String ulasan2;
    private String kandungan;
    private String idKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan1;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan2;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pemohon> listPemohon;
    private List<PihakPengarah> listPengarah;
    private Pemohon pemohon;
    private Hakmilik hakmilik;
    private String tujuan;
    private String lokasi;
    String tarikhMesyuarat;
    String jawatanKuasaKhas;
    String tarikhDaftar;
    private final String tajuk = "KERTAS PERMOHONAN PEMBATALAN HAK LALU LALANG";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");


    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pembatalanhaklalulalang/mmk_PembatalanHakLaluLalang.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("form2", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pembatalanhaklalulalang/mmk_PembatalanHakLaluLalang.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug(idPermohonan + "ni id mohon dier");
        permohonan = permohonanDAO.findById(idPermohonan);

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        HakmilikPermohonan hp = new HakmilikPermohonan();

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        hakmilikPermohonanList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null) ;
        for (Pemohon pemohon : listPemohon) {
            listPengarah = pemohon.getPihak().getSenaraiPengarah();
        }

        String pemohonPihakNama = " ";
        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
            pemohonPihakNama = pemohon.getPihak().getNama();
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        if(!hakmilikPermohonanList.isEmpty()){

        for (int w = 0; w < hakmilikPermohonanList.size(); w++){
            hp = permohonan.getSenaraiHakmilik().get(w);
            if(hp.getHakmilik() != null){
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            if (w == 0){
                lokasi = hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
            }

            if(w > 0 ){
                if(w <= ((hakmilikPermohonanList.size() + 2) - 4)){
                    lokasi = lokasi + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
                } else if(w == (hakmilikPermohonanList.size() - 1)){
                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                        }
                    }
                }
            }
        }

        tujuan = "Kertas ini bertujuan untuk mendapat kelulusan Majlis Mesyuarat Negeri Sembilan Darul Khusus, terhadap permohonan " + permohonan.getKodUrusan().getNama() + " di " + lokasi + permohonan.getSebab() + ".";

        String gunaTanah = "";
        for(HakmilikPermohonan hakmilikPermohonan :hakmilikPermohonanList) {
            if(hakmilikPermohonan.getHakmilik().getKegunaanTanah() != null)
            gunaTanah = gunaTanah + " " +hakmilikPermohonan.getHakmilik().getKegunaanTanah().getNama();
        }


        String temp = "PERMOHONAN DARIPADA "+ pemohonPihakNama+" UNTUK "+ permohonan.getKodUrusan().getNama()+" BAGI "
                +lokasi+" UNTUK TUJUAN "+permohonan.getSebab()+ ".";
        heading = temp.toUpperCase();

       if (idPermohonan != null) {
           System.out.println("----rehydrate-----");

           permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan,tajuk);
           System.out.println("--------permohonanKertas======"+permohonanKertas);

            if (permohonanKertas != null) {//
                kertasBil = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
                kertasTahun = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 2);
                tarikhMesyuaratObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 3);
                headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 4);
                tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 5);
                jawatanKuasaKhasObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 6);
                senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),7);
                senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),8);
                senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),9);
                senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),10);
                senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),11);

                if(tujuanObj != null)
                    tujuan = tujuanObj.getKandungan().toLowerCase();
                if(tarikhMesyuaratObj != null)
                    tarikhMesyuarat = tarikhMesyuaratObj.getKandungan().toLowerCase();
                if(jawatanKuasaKhasObj != null)
                    jawatanKuasaKhas = jawatanKuasaKhasObj.getKandungan().toLowerCase();
            }

            if(kertasTahun == null) {
                kertasTahun = new PermohonanKertasKandungan();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                kertasTahun.setKandungan((sdf.format(new java.util.Date())).toString());
            }
        }
    }

    public Resolution simpan() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        InfoAudit infoAudit = new InfoAudit();

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
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                kertasBil.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
            }
            else
            {
            if (kertasBil == null) {
                    kertasBil = new PermohonanKertasKandungan();
                }
                kertasBil.setKertas(permohonanKertas);
                kertasBil.setBil(1);
                kandungan = "tiada data";
                kertasBil.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                kertasBil.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                kertasBil.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
            }
        
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

            if (getContext().getRequest().getParameter("tarikhMesyuarat") != null) {
                if (tarikhMesyuaratObj == null) {
                    tarikhMesyuaratObj = new PermohonanKertasKandungan();
                }
                tarikhMesyuaratObj.setKertas(permohonanKertas);
                tarikhMesyuaratObj.setBil(3);
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
                tarikhMesyuaratObj.setBil(3);
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

            if (getContext().getRequest().getParameter("heading") != null) {
            infoAudit = new InfoAudit();
            if (headingObj == null) {
                headingObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }else {
                infoAudit = headingObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            headingObj.setKertas(permohonanKertas);
            headingObj.setBil(4);
            kandungan = getContext().getRequest().getParameter("heading");
            headingObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            headingObj.setCawangan(cawangan);
            headingObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(headingObj);
        }

        if (getContext().getRequest().getParameter("tujuan") != null) {
            infoAudit = new InfoAudit();
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }else {
                infoAudit = tujuanObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(5);
            kandungan = getContext().getRequest().getParameter("tujuan");
            tujuanObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tujuanObj.setCawangan(cawangan);
            tujuanObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(tujuanObj);
        }


        senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),7);

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
            permohonanKertasKandungan1.setBil(7);
            kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan1.setCawangan(cawangan);
            permohonanKertasKandungan1.setSubtajuk("2.1." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan1);
//                }
        }

        senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),8);

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

            senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),9);
            int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
            for (int k = 1; k <= kira3; k++) {
                if (senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
                Long id = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
                } else {
                    permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                }
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

            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),10);

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
                    permohonanKertasKandungan4.setBil(10);
                    kandungan = getContext().getRequest().getParameter("kandungan4" + k);
                    permohonanKertasKandungan4.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan4.setCawangan(cawangan);
                    permohonanKertasKandungan4.setSubtajuk("5." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan4.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
            }

            if (getContext().getRequest().getParameter("jawatanKuasaKhas") != null) {
                if (jawatanKuasaKhasObj == null) {
                    jawatanKuasaKhasObj = new PermohonanKertasKandungan();
                }
                jawatanKuasaKhasObj.setKertas(permohonanKertas);
                jawatanKuasaKhasObj.setBil(6);
                kandungan = getContext().getRequest().getParameter("jawatanKuasaKhas");
                jawatanKuasaKhasObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                jawatanKuasaKhasObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                jawatanKuasaKhasObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(jawatanKuasaKhasObj);
            }
            else
            {
            if (jawatanKuasaKhasObj == null) {
                    jawatanKuasaKhasObj = new PermohonanKertasKandungan();
                }
                jawatanKuasaKhasObj.setKertas(permohonanKertas);
                jawatanKuasaKhasObj.setBil(6);
                kandungan = "TIADA DATA";
                jawatanKuasaKhasObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                jawatanKuasaKhasObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                jawatanKuasaKhasObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(jawatanKuasaKhasObj);
            }

            senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),11);

            int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
            for (int k = 1; k <= kira5; k++) {
                if (senaraiKertasKandungan5.size() != 0 && k <= senaraiKertasKandungan5.size()) {
                Long id = senaraiKertasKandungan5.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
                } else {
                    permohonanKertasKandungan5 = new PermohonanKertasKandungan();
                }
                    permohonanKertasKandungan5.setKertas(permohonanKertas);
                    permohonanKertasKandungan5.setBil(11);
                    kandungan = getContext().getRequest().getParameter("kandungan5" + k);
                    permohonanKertasKandungan5.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan5.setCawangan(cawangan);
                    permohonanKertasKandungan5.setSubtajuk("7." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan5.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
            }

        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new RedirectResolution(MMK_PembatalanHakLaluLalangActionBean.class, "locate");
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
        return new RedirectResolution(MMK_PembatalanHakLaluLalangActionBean.class, "locate");
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

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan3() {
        return senaraiKertasKandungan3;
    }

    public void setSenaraiKertasKandungan3(List<PermohonanKertasKandungan> senaraiKertasKandungan3) {
        this.senaraiKertasKandungan3 = senaraiKertasKandungan3;
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

    public PermohonanKertasKandungan getHeadingObj() {
        return headingObj;
    }

    public void setHeadingObj(PermohonanKertasKandungan headingObj) {
        this.headingObj = headingObj;
    }

    public PermohonanKertasKandungan getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(PermohonanKertasKandungan kertasBil) {
        this.kertasBil = kertasBil;
    }

    public PermohonanKertasKandungan getKertasTahun() {
        return kertasTahun;
    }

    public void setKertasTahun(PermohonanKertasKandungan kertasTahun) {
        this.kertasTahun = kertasTahun;
    }

    public PermohonanKertasKandungan getTujuanObj() {
        return tujuanObj;
    }

    public void setTujuanObj(PermohonanKertasKandungan tujuanObj) {
        this.tujuanObj = tujuanObj;
    }

    public PermohonanKertasKandungan getTarikhMesyuaratObj() {
        return tarikhMesyuaratObj;
    }

    public void setTarikhMesyuaratObj(PermohonanKertasKandungan tarikhMesyuaratObj) {
        this.tarikhMesyuaratObj = tarikhMesyuaratObj;
    }

    public PendudukanSementaraMMKNService getPendudukanSementaraMMKNService() {
        return pendudukanSementaraMMKNService;
    }

    public void setPendudukanSementaraMMKNService(PendudukanSementaraMMKNService pendudukanSementaraMMKNService) {
        this.pendudukanSementaraMMKNService = pendudukanSementaraMMKNService;
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

    public String getJawatanKuasaKhas() {
        return jawatanKuasaKhas;
    }

    public void setJawatanKuasaKhas(String jawatanKuasaKhas) {
        this.jawatanKuasaKhas = jawatanKuasaKhas;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PermohonanKertasKandungan getJawatanKuasaKhasObj() {
        return jawatanKuasaKhasObj;
    }

    public void setJawatanKuasaKhasObj(PermohonanKertasKandungan jawatanKuasaKhasObj) {
        this.jawatanKuasaKhasObj = jawatanKuasaKhasObj;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setListPengarah(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

}

