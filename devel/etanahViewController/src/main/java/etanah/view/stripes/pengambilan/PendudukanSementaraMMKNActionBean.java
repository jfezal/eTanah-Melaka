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
import java.text.ParseException;
import java.util.Date;

import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
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
 * @author Rajesh
 */
@UrlBinding("/pengambilan/borang_mmkn_sementara")
public class PendudukanSementaraMMKNActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(PendudukanSementaraMMKNActionBean.class);
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
    private Permohonan permohonan;
    private String idPermohonan;
    private KodCawangan cawangan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan headingObj;
    private String kertasBil;
    private String kertasTahun;
//    private PermohonanKertasKandungan masaObj;
    private String tempat;
    private String tarikhmesyuarat;
    private PermohonanKertasKandungan syorPtg;
    private PermohonanKertasKandungan subtajukObj1;
    private PermohonanKertasKandungan subtajukObj2;
    private PermohonanKertasKandungan subtajukObj3;
    private PermohonanKertasKandungan ulasanObj1;
    private PermohonanKertasKandungan ulasanObj2;
    private PermohonanKertasKandungan ulasanObj3;
    private PermohonanKertasKandungan ulasanObj4;
    private PermohonanKertasKandungan permohonanKertasKandungan1;
    private PermohonanKertasKandungan permohonanKertasKandungan2;
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;

    private PermohonanKertasKandungan tujuanObj;

    private String tajuk;
    private int bil = 0;
    private String syorptg;
    private String kertasbil;
    private String kandungan;
    private String idKandungan;
    private String ulasan1;
    private String ulasan2;
    private String ulasan3;
    private String ulasan4;
    private String subtajuk1;
    private String subtajuk2;
    private String subtajuk3;
    private List<PihakPengarah> listPengarah;
    private List<Pemohon> listPemohon;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan1;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan2;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private Hakmilik hakmilik;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private String tujuan;
    private String heading;
    private String lokasi;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private String jam;
    private String minit;
    private String pagiPetang;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("pengambilan/pendudukan_sementara_mmkn.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("showPtg", Boolean.TRUE);
        return new JSP("pengambilan/pendudukan_sementara_mmkn.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug(idPermohonan+"ni id mohon dier");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();

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
            StringBuilder sb = new StringBuilder();

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

                    sb.append("Tujuan kertas ini adalah untuk mendapatkan pertimbangan Majlis Mesyuarat");
                    sb.append("Kerajaan Negeri Melaka mengenai permohonan daripada").append(pemohonPihakNama);
                    sb.append("No. K/P:").append(permohonan.getPenyerahNoPengenalan());
                    sb.append("untuk memiliki tanah Kerajaan di bawah Seksyen 65 (1) Kanun Tanah Negara secara Lesen Pendudukan Sementara ke atas");
                    sb.append(lokasi);
                    sb.append(permohonan.getSebab().toLowerCase()).append(".");
                    tujuan = sb.toString().toLowerCase();
                }
            }
        }

        System.out.println("lokasi------------------"+lokasi);

        String gunaTanah = "";
        for(HakmilikPermohonan hakmilikPermohonan :hakmilikPermohonanList) {
            if(hakmilikPermohonan.getHakmilik().getKegunaanTanah() != null)
            gunaTanah = gunaTanah + " " +hakmilikPermohonan.getHakmilik().getKegunaanTanah().getNama();
        }

        tujuan = "Tujuan Kertas ini adalah untuk mendapatkan pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Melaka mengenai "
                +"permohonan daripada " + permohonan.getPenyerahNama() + " no. K/P: " + permohonan.getPenyerahNoPengenalan()
                +" untuk memiliki tanah kerajaan di bawah seksyen 65 (1) Kanun Tanah Negara secara Lesen Pendudukan Sementara ke atas "
                + lokasi + " untuk tujuan " + permohonan.getSebab().toLowerCase() + ".";


        String temp = "PERMOHONAN DARIPADA "+ pemohonPihakNama+", NO. K/P : "+ permohonan.getPenyerahNoPengenalan()+" UNTUK MEMILIKI TANAH KERAJAAN DI BAWAH " +
                      "SESYEN 65 (1) KANUN TANAH NEGARA SECARA LESEN PENDUDUKAN SEMENTARA KE ATAS "+lokasi+ " UNTUK "+permohonan.getSebab().toLowerCase()+ ".";
        heading = temp.toUpperCase();

            if (idPermohonan != null) {
                permohonanKertas = pendudukanSementaraMMKNService.findMMKNByIdPermohonan(idPermohonan);

                if (permohonanKertas != null) {
                    headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 18);
                    tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(),19);
//                    masaObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 2);
                    syorPtg = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 10);
                    subtajukObj1 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 9);
//                    kertasBil = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
//                    kertasTahun = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 17);
//                    tarikhmesyuarat = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 3);
//                    tempat = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 4);

                    tajuk = permohonanKertas.getTajuk();
                    if(tajuk.length()>0){
                        kertasBil = tajuk.substring(0, tajuk.length()-5);
                        kertasTahun = tajuk.substring(tajuk.length()-4, tajuk.length());
                    }
                    if(permohonanKertas.getTarikhSidang() != null) {
                        String tarikhSidang = dateFormat.format(permohonanKertas.getTarikhSidang());
                        tarikhmesyuarat = tarikhSidang.substring(0, 10);
                        jam = tarikhSidang.substring(11, 13);
                        minit = tarikhSidang.substring(14, 16);
                        String ampm = tarikhSidang.substring(20, 22);
                        if(ampm.equalsIgnoreCase("AM")){
                            pagiPetang = "PAGI";
                        }else if(ampm.equalsIgnoreCase("PM")){
                            pagiPetang = "PETANG";
                        }
                    }
                    tempat = permohonanKertas.getTempatSidang();
//                    if(masaObj != null){
//                        String masa = masaObj.getKandungan();
//                        jam = masa.substring(0, 2);
//                        minit = masa.substring(3, 5);
//                        pagiPetang = masa.substring(6, masa.length());
//                        masaObj.getKandungan();
//                        System.out.println("jam"+jam+"minit"+minit+"pagiPetang"+pagiPetang);
//                    }

                    if(syorPtg != null)
                    syorptg = syorPtg.getKandungan().toLowerCase();
                    if(subtajukObj1 != null)
                        subtajuk1 = subtajukObj1.getKandungan();
                    subtajukObj2 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 11);
                    if(subtajukObj2 != null)
                        subtajuk2 = subtajukObj2.getKandungan();
                    subtajukObj3 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 12);
                    if(subtajukObj3 != null)
                    subtajuk3 = subtajukObj3.getKandungan();
                    ulasanObj1 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 13);
                    if(ulasanObj1 != null)
                        ulasan1 = ulasanObj1.getKandungan();
                    ulasanObj2 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 14);
                    if(ulasanObj2 != null)
                        ulasan2 = ulasanObj2.getKandungan();
                    ulasanObj3 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 15);
                    if(ulasanObj3 != null)
                        ulasan3 = ulasanObj3.getKandungan();
                    ulasanObj4 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 16);
                    if(ulasanObj4 != null)
                        ulasan4 = ulasanObj4.getKandungan();

                    senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),5);
                    senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),6);
                    senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),7);
                    senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),8);


                }
                if(kertasTahun == null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    kertasTahun = sdf.format(new java.util.Date()).toString();
                }

         if(syorPtg == null){
             syorPtg = new PermohonanKertasKandungan();
             syorPtg.setKandungan("Pengarah Tanah dan Galian bersetuju dengan perakuan Pentadbir Tanah Alor Gajah dan diangkat untuk pertimbangan majlis.");
         }
        if(syorPtg != null && syorPtg.getKandungan().equals("Tiada Data")){
             syorPtg.setKandungan("Pengarah Tanah dan Galian bersetuju dengan perakuan Pentadbir Tanah Alor Gajah dan diangkat untuk pertimbangan majlis.");
         }
    }
    }

    public Resolution simpan() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            permohonan = permohonanDAO.findById(idPermohonan);
//            Session s = sessionProvider.get();
//            Transaction tx = s.beginTransaction();

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
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setCawangan(cawangan);
            permohonanKertas.setKodDokumen(doc);
            kertasBil = getContext().getRequest().getParameter("kertasBil");
            kertasTahun = getContext().getRequest().getParameter("kertasTahun");
            if(kertasBil == null)
                kertasBil = "Tiada Data";
            permohonanKertas.setTajuk(kertasBil+"/"+kertasTahun);
            if(tempat == null)
                tempat = "Tiada Data";
            permohonanKertas.setTempatSidang(tempat);
//            permohonanKertasDAO.saveOrUpdate(permohonanKertas);
            pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);

            senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),5);

            int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));

            for (int i = 1; i <= kira1; i++) {
                if (senaraiKertasKandungan1.size() != 0 && i <= senaraiKertasKandungan1.size()) {
                Long id = senaraiKertasKandungan1.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
                } else {
                        permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                }
                    permohonanKertasKandungan1.setKertas(permohonanKertas);
                    permohonanKertasKandungan1.setBil(5);
                    kandungan = getContext().getRequest().getParameter("kandungan1" + i);
                    if(kandungan==null)
                        kandungan = "TIADA DATA";
                    permohonanKertasKandungan1.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan1.setCawangan(cawangan);
                    permohonanKertasKandungan1.setSubtajuk("2.1." + i);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan1.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan1);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//                }
            }

            senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),6);

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
                    permohonanKertasKandungan2.setBil(6);
                    kandungan = getContext().getRequest().getParameter("kandungan2" + j);
                    permohonanKertasKandungan2.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan2.setCawangan(cawangan);
                    permohonanKertasKandungan2.setSubtajuk("2.2." + j);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan2.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
            }

            senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),7);

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
                    permohonanKertasKandungan3.setBil(7);
                    kandungan = getContext().getRequest().getParameter("kandungan3" + k);
                    if(kandungan==null){
                        kandungan = "TIADA DATA";
                    }
                    permohonanKertasKandungan3.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan3.setCawangan(cawangan);
                    permohonanKertasKandungan3.setSubtajuk("2.3." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan3.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
            }

            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),8);

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
                    permohonanKertasKandungan4.setBil(8);
                    kandungan = getContext().getRequest().getParameter("kandungan4" + k);
                    permohonanKertasKandungan4.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan4.setCawangan(cawangan);
                    permohonanKertasKandungan4.setSubtajuk("5." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan4.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
            }



//             if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
//                if (kertasBil == null) {
//                    kertasBil = new PermohonanKertasKandungan();
//
//                }
//                kertasBil.setKertas(permohonanKertas);
//                kertasBil.setBil(1);
//                kandungan = getContext().getRequest().getParameter("kertasBil.kandungan");
//                kertasBil.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                kertasBil.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                kertasBil.setInfoAudit(iaP);
////                permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasBil);
//             }else
//            {
//            if (kertasBil == null) {
//                    kertasBil = new PermohonanKertasKandungan();
//                }
//                kertasBil.setKertas(permohonanKertas);
//                kertasBil.setBil(1);
//                kandungan = "TIADA DATA";
//                kertasBil.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                kertasBil.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                kertasBil.setInfoAudit(iaP);
////                permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasBil);
//            }
//
//            if (getContext().getRequest().getParameter("kertasTahun.kandungan") != null) {
//                if (kertasTahun == null) {
//                    kertasTahun = new PermohonanKertasKandungan();
//
//                }
//                kertasTahun.setKertas(permohonanKertas);
//                kertasTahun.setBil(17);
//                kandungan = getContext().getRequest().getParameter("kertasTahun.kandungan");
//                if(kandungan==null){
//                    kertasTahun.setKandungan("Tiada");
//                }else{
//                    kertasTahun.setKandungan(kandungan);
//                }
//                cawangan = permohonan.getCawangan();
//                kertasTahun.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                kertasTahun.setInfoAudit(iaP);
////                permohonanKertasKandunganDAO.saveOrUpdate(kertasTahun);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasTahun);
//             }

//            if (getContext().getRequest().getParameter("jam") != null && getContext().getRequest().getParameter("minit") !=null && getContext().getRequest().getParameter("pagiPetang") !=null ) {
//                if (masaObj == null) {
//                    masaObj = new PermohonanKertasKandungan();
//                }
//                masaObj.setKertas(permohonanKertas);
//                masaObj.setBil(2);
//                String StrMasa = jam + ":" + minit + " " + pagiPetang;
//                logger.debug(jam+"jam" + minit + "minit" + pagiPetang +"pagiPetang");
//                masaObj.setKandungan(StrMasa);
//                cawangan = permohonan.getCawangan();
//                masaObj.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                masaObj.setInfoAudit(iaP);
////                permohonanKertasKandunganDAO.saveOrUpdate(masaObj);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(masaObj);
//            }
//            else
//            {
//            if (masaObj == null) {
//                    masaObj = new PermohonanKertasKandungan();
//                }
//                masaObj.setKertas(permohonanKertas);
//                masaObj.setBil(2);
//                String StrMasa = jam + ":" + minit + " " + pagiPetang;
//                logger.debug(jam+"jam" + minit + "minit" + pagiPetang +"pagiPetang");
//                masaObj.setKandungan(StrMasa);
//                cawangan = permohonan.getCawangan();
//                masaObj.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                masaObj.setInfoAudit(iaP);
////                permohonanKertasKandunganDAO.saveOrUpdate(masaObj);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(masaObj);
//            }

//                if (getContext().getRequest().getParameter("tarikhmesyuarat.kandungan") != null) {
//                if (tarikhmesyuarat == null) {
//                    tarikhmesyuarat = new PermohonanKertasKandungan();
//
//                }
//                tarikhmesyuarat.setKertas(permohonanKertas);
//                tarikhmesyuarat.setBil(3);
//                kandungan = getContext().getRequest().getParameter("tarikhmesyuarat.kandungan");
//                tarikhmesyuarat.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                tarikhmesyuarat.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                tarikhmesyuarat.setInfoAudit(iaP);
////                permohonanKertasKandunganDAO.saveOrUpdate(tarikhmesyuarat);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tarikhmesyuarat);
//            }else{
//            if (tarikhmesyuarat == null) {
//                    tarikhmesyuarat = new PermohonanKertasKandungan();
//                }
//                tarikhmesyuarat.setKertas(permohonanKertas);
//                tarikhmesyuarat.setBil(3);
//                kandungan = "TIADA DATA";
//                tarikhmesyuarat.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                tarikhmesyuarat.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                tarikhmesyuarat.setInfoAudit(iaP);
////                permohonanKertasKandunganDAO.saveOrUpdate(tarikhmesyuarat);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tarikhmesyuarat);
//            }

//            if (getContext().getRequest().getParameter("tempat.kandungan") != null) {
//                if (tempat == null) {
//                    tempat = new PermohonanKertasKandungan();
//                }
//                tempat.setKertas(permohonanKertas);
//                tempat.setBil(4);
//                kandungan = getContext().getRequest().getParameter("tempat.kandungan");
//                tempat.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                tempat.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                tempat.setInfoAudit(iaP);
////                permohonanKertasKandunganDAO.saveOrUpdate(tempat);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tempat);
//            }else{
//            if (tempat == null) {
//                    tempat = new PermohonanKertasKandungan();
//                }
//                tempat.setKertas(permohonanKertas);
//                tempat.setBil(4);
//                kandungan = "TIADA DATA";
//                tempat.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                tempat.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                tempat.setInfoAudit(iaP);
////                permohonanKertasKandunganDAO.saveOrUpdate(tempat);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tempat);
//            }

            if (getContext().getRequest().getParameter("heading") != null) {
            InfoAudit infoAudit = new InfoAudit();
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
            headingObj.setBil(18);
            kandungan = getContext().getRequest().getParameter("heading");
            headingObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            headingObj.setCawangan(cawangan);
            headingObj.setInfoAudit(infoAudit);
//            permohonanKertasKandunganDAO.saveOrUpdate(headingObj);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(headingObj);
        }

            if (getContext().getRequest().getParameter("tujuan") != null) {
                if (tujuanObj == null) {
                    tujuanObj = new PermohonanKertasKandungan();
                }

                tujuanObj.setKertas(permohonanKertas);
                tujuanObj.setBil(19);
                kandungan = getContext().getRequest().getParameter("tujuan");
                tujuanObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tujuanObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tujuanObj.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(tujuanObj);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
            }
            else {
                if (tujuanObj == null) {
                    tujuanObj = new PermohonanKertasKandungan();
                }
                tujuanObj.setKertas(permohonanKertas);
                tujuanObj.setBil(19);
                kandungan = tujuan;
                tujuanObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tujuanObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tujuanObj.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(tujuanObj);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
            }

            if (getContext().getRequest().getParameter("syorPtg.kandungan") != null) {
                if (syorPtg == null) {
                    syorPtg = new PermohonanKertasKandungan();
                }
                syorPtg.setKertas(permohonanKertas);
                syorPtg.setBil(10);
                kandungan = getContext().getRequest().getParameter("syorPtg.kandungan");
                syorPtg.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                syorPtg.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                syorPtg.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(syorPtg);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(syorPtg);
            }else{
            if (syorPtg == null) {
                    syorPtg = new PermohonanKertasKandungan();
                }
                syorPtg.setKertas(permohonanKertas);
                syorPtg.setBil(10);
                kandungan = "TIADA DATA";
                syorPtg.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                syorPtg.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                syorPtg.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(syorPtg);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(syorPtg);
            }

            if (getContext().getRequest().getParameter("subtajuk1") != null) {
                if (subtajukObj1 == null) {
                    subtajukObj1 = new PermohonanKertasKandungan();
                }
                subtajukObj1.setKertas(permohonanKertas);
                subtajukObj1.setBil(9);
                kandungan = getContext().getRequest().getParameter("subtajuk1");
                subtajukObj1.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                subtajukObj1.setCawangan(cawangan);
                subtajukObj1.setSubtajuk("3.1");
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                subtajukObj1.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(subtajukObj1);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(subtajukObj1);
            }

            if (getContext().getRequest().getParameter("subtajuk2") != null) {
                if (subtajukObj2 == null) {
                    subtajukObj2 = new PermohonanKertasKandungan();
                }
                subtajukObj2.setKertas(permohonanKertas);
                subtajukObj2.setBil(11);
                kandungan = getContext().getRequest().getParameter("subtajuk2");
                subtajukObj2.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                subtajukObj2.setCawangan(cawangan);
                subtajukObj2.setSubtajuk("3.2");
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                subtajukObj2.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(subtajukObj2);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(subtajukObj2);
            }

            if (getContext().getRequest().getParameter("subtajuk3") != null) {
                if (subtajukObj3 == null) {
                    subtajukObj3 = new PermohonanKertasKandungan();
                }
                subtajukObj3.setKertas(permohonanKertas);
                subtajukObj3.setBil(12);
                kandungan = getContext().getRequest().getParameter("subtajuk3");
                subtajukObj3.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                subtajukObj3.setCawangan(cawangan);
                subtajukObj3.setSubtajuk("3.3");
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                subtajukObj3.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(subtajukObj3);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(subtajukObj3);
            }


            if (getContext().getRequest().getParameter("ulasan1") != null) {
                if (ulasanObj1 == null) {
                    ulasanObj1 = new PermohonanKertasKandungan();
                }
                ulasanObj1.setKertas(permohonanKertas);
                ulasanObj1.setBil(13);
                kandungan = getContext().getRequest().getParameter("ulasan1");
                ulasanObj1.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                ulasanObj1.setCawangan(cawangan);
                ulasanObj1.setSubtajuk("3.1");
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                ulasanObj1.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(ulasanObj1);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(ulasanObj1);
            }

            if (getContext().getRequest().getParameter("ulasan2") != null) {
                if (ulasanObj2 == null) {
                    ulasanObj2 = new PermohonanKertasKandungan();
                }
                ulasanObj2.setKertas(permohonanKertas);
                ulasanObj2.setBil(14);
                kandungan = getContext().getRequest().getParameter("ulasan2");
                ulasanObj2.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                ulasanObj2.setCawangan(cawangan);
                ulasanObj2.setSubtajuk("3.2");
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                ulasanObj2.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(ulasanObj2);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(ulasanObj2);
            }

            if (getContext().getRequest().getParameter("ulasan3") != null) {
                if (ulasanObj3 == null) {
                    ulasanObj3 = new PermohonanKertasKandungan();
                }
                ulasanObj3.setKertas(permohonanKertas);
                ulasanObj3.setBil(15);
                kandungan = getContext().getRequest().getParameter("ulasan3");
                ulasanObj3.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                ulasanObj3.setCawangan(cawangan);
                ulasanObj3.setSubtajuk("3.3");
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                ulasanObj3.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(ulasanObj3);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(ulasanObj3);
            }

            if (getContext().getRequest().getParameter("ulasan4") != null) {
                if (ulasanObj4 == null) {
                    ulasanObj4 = new PermohonanKertasKandungan();
                }
                ulasanObj4.setKertas(permohonanKertas);
                ulasanObj4.setBil(16);
                kandungan = getContext().getRequest().getParameter("ulasan4");
                ulasanObj4.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                ulasanObj4.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                ulasanObj4.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(ulasanObj4);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(ulasanObj4);
            }
//            tx.commit();
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            getContext().getRequest().setAttribute("form", Boolean.TRUE);
            return new RedirectResolution(PendudukanSementaraMMKNActionBean.class, "locate");
   }

    public Resolution simpanPtg() throws ParseException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            permohonan = permohonanDAO.findById(idPermohonan);
//            Session s = sessionProvider.get();
//            Transaction tx = s.beginTransaction();

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
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setCawangan(cawangan);
            permohonanKertas.setKodDokumen(doc);
            kertasBil = getContext().getRequest().getParameter("kertasBil");
            kertasTahun = getContext().getRequest().getParameter("kertasTahun");
            permohonanKertas.setTajuk(kertasBil+"/"+kertasTahun);
            tarikhmesyuarat = getContext().getRequest().getParameter("tarikhmesyuarat");
            jam = getContext().getRequest().getParameter("jam");
            minit = getContext().getRequest().getParameter("minit");
            pagiPetang = getContext().getRequest().getParameter("pagiPetang");
            if(tarikhmesyuarat.length() > 0 && jam.length() > 0 && minit.length() > 0 && pagiPetang.length()>0){
                String ampm = "";
                if(pagiPetang.equalsIgnoreCase("PAGI")){
                    ampm = "AM";
                }else if(pagiPetang.equalsIgnoreCase("PETANG")){
                    ampm = "PM";
                }
                String strMasa = tarikhmesyuarat + " " + jam + ":" + minit + ":00 " + ampm;
                Date tarikhSidang = dateFormat.parse(strMasa);
                permohonanKertas.setTarikhSidang(tarikhSidang);
            }
            permohonanKertas.setTempatSidang(tempat);
//            permohonanKertasDAO.saveOrUpdate(permohonanKertas);
            pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);

            senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),5);

            int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));

            for (int i = 1; i <= kira1; i++) {
                if (senaraiKertasKandungan1.size() != 0 && i <= senaraiKertasKandungan1.size()) {
                Long id = senaraiKertasKandungan1.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
                } else {
                        permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                }
                    permohonanKertasKandungan1.setKertas(permohonanKertas);
                    permohonanKertasKandungan1.setBil(5);
                    kandungan = getContext().getRequest().getParameter("kandungan1" + i);
                    if(kandungan==null)
                        kandungan = "TIADA DATA";
                    permohonanKertasKandungan1.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan1.setCawangan(cawangan);
                    permohonanKertasKandungan1.setSubtajuk("2.1." + i);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan1.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan1);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//                }
            }

            senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),6);

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
                    permohonanKertasKandungan2.setBil(6);
                    kandungan = getContext().getRequest().getParameter("kandungan2" + j);
                    permohonanKertasKandungan2.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan2.setCawangan(cawangan);
                    permohonanKertasKandungan2.setSubtajuk("2.2." + j);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan2.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
            }

            senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),7);

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
                    permohonanKertasKandungan3.setBil(7);
                    kandungan = getContext().getRequest().getParameter("kandungan3" + k);
                    if(kandungan==null){
                        kandungan = "TIADA DATA";
                    }
                    permohonanKertasKandungan3.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan3.setCawangan(cawangan);
                    permohonanKertasKandungan3.setSubtajuk("2.3." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan3.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
            }

            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),8);

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
                    permohonanKertasKandungan4.setBil(8);
                    kandungan = getContext().getRequest().getParameter("kandungan4" + k);
                    permohonanKertasKandungan4.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan4.setCawangan(cawangan);
                    permohonanKertasKandungan4.setSubtajuk("5." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan4.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
            }

//            if (getContext().getRequest().getParameter("kertasTahun.kandungan") != null) {
//                if (kertasTahun == null) {
//                    kertasTahun = new PermohonanKertasKandungan();
//
//                }
//                kertasTahun.setKertas(permohonanKertas);
//                kertasTahun.setBil(17);
//                kandungan = getContext().getRequest().getParameter("kertasTahun.kandungan");
//                kertasTahun.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                kertasTahun.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                kertasTahun.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(kertasTahun);
//             }
//
//             if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
//                if (kertasBil == null) {
//                    kertasBil = new PermohonanKertasKandungan();
//
//                }
//                kertasBil.setKertas(permohonanKertas);
//                kertasBil.setBil(1);
//                kandungan = getContext().getRequest().getParameter("kertasBil.kandungan");
//                kertasBil.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                kertasBil.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                kertasBil.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
//             }
//
//            if (masaObj != null) {
//                //masaObj = new PermohonanKertasKandungan();
//                  System.out.println("-------------masaobj");
//                masaObj.setKertas(permohonanKertas);
//                masaObj.setBil(2);
//                jam = getContext().getRequest().getParameter("jam");
//                minit = getContext().getRequest().getParameter("minit");
//                pagiPetang = getContext().getRequest().getParameter("pagiPetang");
//                String StrMasa = jam + ":" + minit + " " + pagiPetang;
//                logger.debug(jam+"jam" + minit + "minit" + pagiPetang +"pagiPetang");
//                masaObj.setKandungan(StrMasa);
//                cawangan = permohonan.getCawangan();
//                masaObj.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                masaObj.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(masaObj);
//             }
//
//                if (getContext().getRequest().getParameter("tarikhmesyuarat.kandungan") != null) {
//                if (tarikhmesyuarat == null) {
//                    tarikhmesyuarat = new PermohonanKertasKandungan();
//
//                }
//                tarikhmesyuarat.setKertas(permohonanKertas);
//                tarikhmesyuarat.setBil(3);
//                kandungan = getContext().getRequest().getParameter("tarikhmesyuarat.kandungan");
//                tarikhmesyuarat.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                tarikhmesyuarat.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                tarikhmesyuarat.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(tarikhmesyuarat);
//            }
//
//            if (getContext().getRequest().getParameter("tempat.kandungan") != null) {
//                if (tempat == null) {
//                    tempat = new PermohonanKertasKandungan();
//                }
//                tempat.setKertas(permohonanKertas);
//                tempat.setBil(4);
//                kandungan = getContext().getRequest().getParameter("tempat.kandungan");
//                tempat.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                tempat.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                tempat.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(tempat);
//            }

            if (getContext().getRequest().getParameter("syorPtg.kandungan") != null) {
                if (syorPtg == null) {
                    syorPtg = new PermohonanKertasKandungan();
                }
                syorPtg.setKertas(permohonanKertas);
                syorPtg.setBil(10);
                kandungan = getContext().getRequest().getParameter("syorPtg.kandungan");
//                if(kandungan==null)
//                    kandungan="Pengarah Tanah dan Galian bersetuju dengan perakuan Pentadbir Tanah Alor Gajah dan diangkat untuk pertimbangan majlis";
                syorPtg.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                syorPtg.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                syorPtg.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(syorPtg);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(syorPtg);
            }


//            tx.commit();
            rehydrate();
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            getContext().getRequest().setAttribute("showPtg", Boolean.TRUE);
            return new JSP("pengambilan/pendudukan_sementara_mmkn.jsp").addParameter("tab", "true");
//            return new RedirectResolution(PendudukanSementaraMMKNActionBean.class, "locate");
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
      return new RedirectResolution(PendudukanSementaraMMKNActionBean.class, "locate");
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

    public PermohonanKertasKandungan getPermohonanKertasKandungan1() {
        return permohonanKertasKandungan1;
    }

    public void setPermohonanKertasKandungan1(PermohonanKertasKandungan permohonanKertasKandungan1) {
        this.permohonanKertasKandungan1 = permohonanKertasKandungan1;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan2() {
        return permohonanKertasKandungan2;
    }

    public void setPermohonanKertasKandungan2(PermohonanKertasKandungan permohonanKertasKandungan2) {
        this.permohonanKertasKandungan2 = permohonanKertasKandungan2;
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

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
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

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
        return senaraiKertasKandungan4;
    }

    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
    }

    public String getSubtajuk1() {
        return subtajuk1;
    }

    public void setSubtajuk1(String subtajuk1) {
        this.subtajuk1 = subtajuk1;
    }

    public String getSubtajuk2() {
        return subtajuk2;
    }

    public void setSubtajuk2(String subtajuk2) {
        this.subtajuk2 = subtajuk2;
    }

    public String getSubtajuk3() {
        return subtajuk3;
    }

    public void setSubtajuk3(String subtajuk3) {
        this.subtajuk3 = subtajuk3;
    }

    public String getUlasan1() {
        return ulasan1;
    }

    public void setUlasan1(String ulasan1) {
        this.ulasan1 = ulasan1;
    }

    public String getUlasan2() {
        return ulasan2;
    }

    public void setUlasan2(String ulasan2) {
        this.ulasan2 = ulasan2;
    }

    public String getUlasan3() {
        return ulasan3;
    }

    public void setUlasan3(String ulasan3) {
        this.ulasan3 = ulasan3;
    }

    public PermohonanKertasKandungan getSubtajukObj1() {
        return subtajukObj1;
    }

    public void setSubtajukObj1(PermohonanKertasKandungan subtajukObj1) {
        this.subtajukObj1 = subtajukObj1;
    }

    public PermohonanKertasKandungan getSubtajukObj2() {
        return subtajukObj2;
    }

    public void setSubtajukObj2(PermohonanKertasKandungan subtajukObj2) {
        this.subtajukObj2 = subtajukObj2;
    }

    public PermohonanKertasKandungan getSubtajukObj3() {
        return subtajukObj3;
    }

    public void setSubtajukObj3(PermohonanKertasKandungan subtajukObj3) {
        this.subtajukObj3 = subtajukObj3;
    }

    public String getUlasan4() {
        return ulasan4;
    }

    public void setUlasan4(String ulasan4) {
        this.ulasan4 = ulasan4;
    }

    public PermohonanKertasKandungan getUlasanObj1() {
        return ulasanObj1;
    }

    public void setUlasanObj1(PermohonanKertasKandungan ulasanObj1) {
        this.ulasanObj1 = ulasanObj1;
    }

    public PermohonanKertasKandungan getUlasanObj2() {
        return ulasanObj2;
    }

    public void setUlasanObj2(PermohonanKertasKandungan ulasanObj2) {
        this.ulasanObj2 = ulasanObj2;
    }

    public PermohonanKertasKandungan getUlasanObj3() {
        return ulasanObj3;
    }

    public void setUlasanObj3(PermohonanKertasKandungan ulasanObj3) {
        this.ulasanObj3 = ulasanObj3;
    }

    public PermohonanKertasKandungan getUlasanObj4() {
        return ulasanObj4;
    }

    public void setUlasanObj4(PermohonanKertasKandungan ulasanObj4) {
        this.ulasanObj4 = ulasanObj4;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getSyorptg() {
        return syorptg;
    }

    public void setSyorptg(String syorptg) {
        this.syorptg = syorptg;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public PermohonanKertasKandungan getHeadingObj() {
        return headingObj;
    }

    public void setHeadingObj(PermohonanKertasKandungan headingObj) {
        this.headingObj = headingObj;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
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

    public PermohonanKertasKandungan getTujuanObj() {
        return tujuanObj;
    }

    public void setTujuanObj(PermohonanKertasKandungan tujuanObj) {
        this.tujuanObj = tujuanObj;
    }

    public String getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(String kertasBil) {
        this.kertasBil = kertasBil;
    }

    public String getKertasTahun() {
        return kertasTahun;
    }

    public void setKertasTahun(String kertasTahun) {
        this.kertasTahun = kertasTahun;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTarikhmesyuarat() {
        return tarikhmesyuarat;
    }

    public void setTarikhmesyuarat(String tarikhmesyuarat) {
        this.tarikhmesyuarat = tarikhmesyuarat;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }


}
