/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author User
 */
@UrlBinding("/pelupusan/Kertas_stasatan")
public class KertasStasatanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    private List<PermohonanPihak> mohanPihakList;
    private String idPermohonan;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Pihak pihak;
    private PermohonanPihak permohonanPihak;
    private Hakmilik hakmilik;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan permohonanKertasKandungan = new PermohonanKertasKandungan();
    private PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
    private Pemohon pemohon;
    private InfoAudit ia;
    private Pengguna peng;
    private BigDecimal luasT;
    private boolean check = false;
    private String kodUnitLuas;
    private String kand0 = "";
    private String kand1 = "";
    private String kand2 = "";
    private String kand3 = "";
    private String kand4 = "";
    private String kand5 = "";
    private String kand6 = "";
    private String kand7 = "";
    private String kand8 = "";
    private String kand9 = "";
    private String kand10 = "";
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(LesenTerdahuluActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if (permohonan.getIdPermohonan() != null) {
                mohanPihakList = new ArrayList<PermohonanPihak>();
                pemohon = pelupusanService.findPemohon(idPermohonan);
                mohanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
                List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
                hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                /*
                 * TEMPORARY SINCE LALULANG NOT CATER FOR MANY HAKMILIK
                 */
                if (hmList.size() > 0) {
                    hakmilikPermohonan = hmList.get(0);
                }
//                hakmilikPermohonan = pelupusanService.findHakmilikPermohonan(idPermohonan);
            }
        }
        return new JSP("pelupusan/kertas_stasatan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        try {
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                if (permohonan.getIdPermohonan() != null) {
//                check = pelupusanService.findPermohonanKertas(permohonan.getIdPermohonan());
                    permohonanKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "KST");
                    mohanPihakList = new ArrayList<PermohonanPihak>();
                    pemohon = pelupusanService.findPemohon(idPermohonan);
                    mohanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
                    List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
                    hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                    permohonanRujukanLuar = pelupusanService.findMohonRujukLuarIdPermohonanNamaSidang(idPermohonan, "Nilaian");
                    /*
                     * TEMPORARY SINCE LALULANG NOT CATER FOR MANY HAKMILIK
                     */
                    if (hmList.size() > 0) {
                        hakmilikPermohonan = hmList.get(0);
                        luasT = hakmilikPermohonan.getLuasTerlibat();
                        kodUnitLuas = hakmilikPermohonan.getKodUnitLuas().getKod();
                    }

                    if (permohonanKertas != null) {
                        List<PermohonanKertasKandungan> listMKK = new ArrayList<PermohonanKertasKandungan>();
                        listMKK = pelupusanService.findByIdKertasOnly(permohonanKertas.getIdKertas());
                        for (PermohonanKertasKandungan mkk : listMKK) {
                            if (mkk.getBil() == 6 && mkk.getSubtajuk().equals("1")) {
                                kand0 = mkk.getKandungan();
                            } else if (mkk.getBil() == 6 && mkk.getSubtajuk().equals("2")) {
                                kand1 = mkk.getKandungan();
                            } else if (mkk.getBil() == 6 && mkk.getSubtajuk().equals("3")) {
                                kand2 = mkk.getKandungan();
                            } else if (mkk.getBil() == 6 && mkk.getSubtajuk().equals("4")) {
                                kand3 = mkk.getKandungan();
                            } else if (mkk.getBil() == 6 && mkk.getSubtajuk().equals("5")) {
                                kand4 = mkk.getKandungan();
                            } /*else if(mkk.getBil()==7&&mkk.getSubtajuk().equals("0"))
                            kand5 = mkk.getKandungan();
                            else if(mkk.getBil()==8&&mkk.getSubtajuk().equals("0"))
                            kand6 = mkk.getKandungan();
                            else if(mkk.getBil()==9&&mkk.getSubtajuk().equals("0"))
                            kand7 = mkk.getKandungan();
                            else if(mkk.getBil()==10&&mkk.getSubtajuk().equals("0"))
                            kand8 = mkk.getKandungan();
                            else if(mkk.getBil()==11&&mkk.getSubtajuk().equals("0"))
                            kand9 = mkk.getKandungan();
                            else if(mkk.getBil()==12&&mkk.getSubtajuk().equals("0"))
                            kand10 = mkk.getKandungan();
                             */ else if (mkk.getBil() == 7 && mkk.getSubtajuk().equals("0")) {
                                kand5 = mkk.getKandungan();
                            } else if (mkk.getBil() == 8 && mkk.getSubtajuk().equals("0")) {
                                kand7 = mkk.getKandungan();
                            } else if (mkk.getBil() == 9 && mkk.getSubtajuk().equals("0")) {
                                kand6 = mkk.getKandungan();
                            } else if (mkk.getBil() == 10 && mkk.getSubtajuk().equals("0")) {
                                kand8 = mkk.getKandungan();
                            } else if (mkk.getBil() == 11 && mkk.getSubtajuk().equals("0")) {
                                kand9 = mkk.getKandungan();
                            } else if (mkk.getBil() == 12 && mkk.getSubtajuk().equals("0")) {
                                kand10 = mkk.getKandungan();
                            }
                        }
                    }
//                hakmilikPermohonan = pelupusanService.findHakmilikPermohonan(idPermohonan);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Resolution kertasStasatanSimpan() {
        try {
            ArrayList kandList = new ArrayList();
            peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            PermohonanKertas permohonanKertasTemp = new PermohonanKertas();
            permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "KST");
            PermohonanKertasKandungan permohonanKertasKandungan_ref = new PermohonanKertasKandungan();

            List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
            hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);

            for (HakmilikPermohonan hp : hmList) {
                hakmilikPermohonan = hmList.get(0);
                if (hp != null) {
                    hp.setLuasTerlibat(luasT);
                    KodUOM kodUOM = new KodUOM();
                    kodUOM = kodUOMDAO.findById(kodUnitLuas);
                    hp.setKodUnitLuas(kodUOM);

                    hp = pelupusanService.saveOrUpdateHakmilikPermohonan(hp); //COMMEND 1ST SINCE ERROR,SO THAT SHAHRIMI CAN DEPLOY, MAKE SURE COMMIT PELUPUSAN SERVICE BEFORE UNCOMMEND THIS METHOD
                }
            }


            if (permohonanKertasTemp == null) {
                ia = new InfoAudit();
                permohonanKertasTemp = new PermohonanKertas();
                permohonanKertasTemp.setCawangan(permohonan.getCawangan());
                permohonanKertasTemp.setPermohonan(permohonan);
                KodDokumen kodDok = new KodDokumen();
                kodDok = kodDokumenDAO.findById("KST");
                permohonanKertasTemp.setKodDokumen(kodDok);
                permohonanKertasTemp.setTajuk("Kertas Siasatan");
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                permohonanKertasTemp.setInfoAudit(ia);
                pelupusanService.simpanPermohonanKertas(permohonanKertasTemp);
                permohonanKertasTemp = new PermohonanKertas();
                permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "KST");
            }

            if (permohonan != null) {
                if (!kand0.equals("") || !kand1.equals("") || !kand2.equals("") || !kand3.equals("") || !kand4.equals("") || !kand5.equals("") || !kand6.equals("") || !kand7.equals("") || !kand8.equals("")) {
                    for (int i = 0; i < 12; i++) {
                        if (!("kand" + i).equals("")) {
                            kandList.add("kand" + i);
                        }
                    }

                    if (kandList.size() > 0) {
                        int bil = 99;
                        String subTajuk = new String();
                        String kand = new String();
                        for (int j = 0; j < kandList.size(); j++) {
                            PermohonanKertasKandungan mohonKKTemp = new PermohonanKertasKandungan();
                            for (int i = 0; i < kandList.size(); i++) {
                                if (kandList.get(j).toString().equals("kand5")) {

                                    if (!StringUtils.isBlank(kand5)) {
                                        bil = 7;
                                        subTajuk = "0";
                                        kand = kand5;
                                        break;
                                    } else {
                                        bil = 7;
                                        subTajuk = "0";
                                        kand = " ";
                                        break;
                                    }
                                } else if (kandList.get(j).toString().equals("kand6")) {

                                    if (!StringUtils.isBlank(kand6)) {
                                        bil = 9;
                                        subTajuk = "0";
                                        kand = kand6;
                                        break;
                                    } else {
                                        bil = 9;
                                        subTajuk = "0";
                                        kand = " ";
                                        break;
                                    }
                                } else if (kandList.get(j).toString().equals("kand7")) {

                                    if (!StringUtils.isBlank(kand7)) {
                                        bil = 8;
                                        subTajuk = "0";
                                        kand = kand7;
                                        break;
                                    } else {
                                        bil = 8;
                                        subTajuk = "0";
                                        kand = " ";
                                        break;
                                    }
                                } else if (kandList.get(j).toString().equals("kand8")) {

                                    if (!StringUtils.isBlank(kand8)) {
                                        bil = 10;
                                        subTajuk = "0";
                                        kand = kand8;
                                        break;
                                    } else {
                                        bil = 10;
                                        subTajuk = "0";
                                        kand = " ";
                                        break;
                                    }
                                } else if (kandList.get(j).toString().equals("kand0")) {

                                    if (!StringUtils.isBlank(kand0)) {
                                        bil = 6;
                                        subTajuk = "1";
                                        kand = kand0;
                                        break;
                                    } else {
                                        bil = 6;
                                        subTajuk = "1";
                                        kand = " ";
                                        break;
                                    }

                                } else if (kandList.get(j).toString().equals("kand1")) {

                                    if (!StringUtils.isBlank(kand1)) {
                                        bil = 6;
                                        subTajuk = "2";
                                        kand = kand1;
                                        break;
                                    } else {
                                        bil = 6;
                                        subTajuk = "2";
                                        kand = " ";
                                        break;
                                    }
                                } else if (kandList.get(j).toString().equals("kand2")) {

                                    if (!StringUtils.isBlank(kand2)) {
                                        bil = 6;
                                        subTajuk = "3";
                                        kand = kand2;
                                        break;
                                    } else {
                                        bil = 6;
                                        subTajuk = "3";
                                        kand = " ";
                                        break;
                                    }
                                } else if (kandList.get(j).toString().equals("kand3")) {
                                    if (!StringUtils.isBlank(kand3)) {
                                        bil = 6;
                                        subTajuk = "4";
                                        kand = kand3;
                                        break;
                                    } else {
                                        bil = 6;
                                        subTajuk = "4";
                                        kand = " ";
                                        break;
                                    }
                                } else if (kandList.get(j).toString().equals("kand4")) {

                                    if (!StringUtils.isBlank(kand4)) {
                                        bil = 6;
                                        subTajuk = "5";
                                        kand = kand4;
                                        break;
                                    } else {
                                        bil = 6;
                                        subTajuk = "5";
                                        kand = " ";
                                        break;
                                    }
                                } else if (kandList.get(j).toString().equals("kand9")) {

                                    if (!StringUtils.isBlank(kand9)) {
                                        bil = 11;
                                        subTajuk = "0";
                                        kand = kand9;
                                        break;
                                    } else {
                                        bil = 11;
                                        subTajuk = "0";
                                        kand = " ";
                                        break;
                                    }
                                } else if (kandList.get(j).toString().equals("kand10")) {

                                    if (!StringUtils.isBlank(kand10)) {
                                        bil = 12;
                                        subTajuk = "0";
                                        kand = kand10;
                                        break;
                                    } else {
                                        bil = 12;
                                        subTajuk = "0";
                                        kand = " ";
                                        break;
                                    }
                                }
                            }
                            mohonKKTemp = pelupusanService.findByBilNIdKertasNSubtajuk(bil, permohonanKertasTemp.getIdKertas(), subTajuk);
                            permohonanKertasKandungan_ref = new PermohonanKertasKandungan();
                            if (mohonKKTemp == null) {
                                ia = new InfoAudit();
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                                KodCawangan kodCawangan = kodCawanganDAO.findById(permohonan.getCawangan().getKod());
                                permohonanKertasKandungan_ref.setCawangan(kodCawangan);
                                permohonanKertasKandungan_ref.setKertas(permohonanKertasTemp);
                                permohonanKertasKandungan_ref.setBil(bil);
                                permohonanKertasKandungan_ref.setSubtajuk(subTajuk);
                                permohonanKertasKandungan_ref.setKandungan(kand);
                                LOG.info(" kodCawangan : " + kodCawangan);
                            } else {
                                permohonanKertasKandungan_ref = mohonKKTemp;
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan_ref.getInfoAudit();
                                permohonanKertasKandungan_ref.setKandungan(kand);
                            }
                            permohonanKertasKandungan_ref.setInfoAudit(ia);
                            LOG.info(" kand : " + kand);
                            LOG.info(" subTajuk : " + subTajuk);
                            LOG.info(" bil : " + bil);
                            LOG.info(" permohonanKertasTemp : " + permohonanKertasTemp);

                            //
                            //                            if( kandList.get(j).toString().equals("kand5")){
                            //                                if(kand5 != null && kand5.length() > 0){
                            //                                permohonanKertasKandungan_ref.setBil(7);
                            //                                permohonanKertasKandungan_ref.setSubtajuk("0");
                            //                                permohonanKertasKandungan_ref.setKandungan(kand5);
                            //                                }
                            //                            }
                            //                            if( kandList.get(j).toString().equals("kand6")){
                            //                                if(kand6 != null && kand6.length() > 0){
                            //                                permohonanKertasKandungan_ref.setBil(8);
                            //                                permohonanKertasKandungan_ref.setSubtajuk("0");
                            //                                permohonanKertasKandungan_ref.setKandungan(kand6);
                            //                                }
                            //                            }
                            //                            if( kandList.get(j).toString().equals("kand7")){
                            //                                if(kand7 != null && kand7.length() > 0){
                            //                                permohonanKertasKandungan_ref.setBil(9);
                            //                                permohonanKertasKandungan_ref.setSubtajuk("0");
                            //                                permohonanKertasKandungan_ref.setKandungan(kand7);
                            //                                }
                            //                            }
                            //                            if( kandList.get(j).toString().equals("kand8")){
                            //                                if(kand8 != null && kand8.length() > 0){
                            //                                permohonanKertasKandungan_ref.setBil(10);
                            //                                permohonanKertasKandungan_ref.setSubtajuk("0");
                            //                                permohonanKertasKandungan_ref.setKandungan(kand8);
                            //                                }
                            //                            }
                            //                            if( kandList.get(j).toString().equals("kand0")){
                            //                                if(kand0 != null && kand0.length() > 0){
                            //                                permohonanKertasKandungan_ref.setBil(6);
                            //                                permohonanKertasKandungan_ref.setSubtajuk("1");
                            //                                permohonanKertasKandungan_ref.setKandungan(kand0);
                            //                                }
                            //                            }
                            //                            if( kandList.get(j).toString().equals("kand1")){
                            //                                if(kand1 != null && kand1.length() > 0){
                            //                                permohonanKertasKandungan_ref.setBil(6);
                            //                                permohonanKertasKandungan_ref.setSubtajuk("2");
                            //                                permohonanKertasKandungan_ref.setKandungan(kand1);
                            //                                }
                            //                            }
                            //                            if( kandList.get(j).toString().equals("kand2")){
                            //                                if(kand2 != null && kand2.length() > 0){
                            //                                permohonanKertasKandungan_ref.setBil(6);
                            //                                permohonanKertasKandungan_ref.setSubtajuk("3");
                            //                                permohonanKertasKandungan_ref.setKandungan(kand2);
                            //                                }
                            //                            }
                            //                            if( kandList.get(j).toString().equals("kand3")){
                            //                                if(kand3 != null && kand3.length() > 0){
                            //                                permohonanKertasKandungan_ref.setBil(6);
                            //                                permohonanKertasKandungan_ref.setSubtajuk("4");
                            //                                permohonanKertasKandungan_ref.setKandungan(kand3);
                            //                                }
                            //                            }
                            //                            if( kandList.get(j).toString().equals("kand4")){
                            //                                if(kand4 != null && kand4.length() > 0){
                            //                                permohonanKertasKandungan_ref.setBil(6);
                            //                                permohonanKertasKandungan_ref.setSubtajuk("5");
                            //                                permohonanKertasKandungan_ref.setKandungan(kand4);
                            //                                }
                            //                            }

                            if (permohonanKertasKandungan_ref.getKandungan() != null) {
                                permohonanKertasKandungan = pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan_ref);
                            }
                        }
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    }

                } else {
                    addSimpleError("Sila Isikan Sekurang-Kurangnya Beberapa Butiran");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSP("pelupusan/kertas_stasatan.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public InfoAudit getIa() {
        return ia;
    }

    public void setIa(InfoAudit ia) {
        this.ia = ia;
    }

    public String getKand0() {
        return kand0;
    }

    public void setKand0(String kand0) {
        this.kand0 = kand0;
    }

    public String getKand1() {
        return kand1;
    }

    public void setKand1(String kand1) {
        this.kand1 = kand1;
    }

    public String getKand2() {
        return kand2;
    }

    public void setKand2(String kand2) {
        this.kand2 = kand2;
    }

    public String getKand3() {
        return kand3;
    }

    public void setKand3(String kand3) {
        this.kand3 = kand3;
    }

    public String getKand4() {
        return kand4;
    }

    public void setKand4(String kand4) {
        this.kand4 = kand4;
    }

    public String getKand5() {
        return kand5;
    }

    public void setKand5(String kand5) {
        this.kand5 = kand5;
    }

    public String getKand6() {
        return kand6;
    }

    public void setKand6(String kand6) {
        this.kand6 = kand6;
    }

    public String getKand7() {
        return kand7;
    }

    public void setKand7(String kand7) {
        this.kand7 = kand7;
    }

    public String getKand8() {
        return kand8;
    }

    public void setKand8(String kand8) {
        this.kand8 = kand8;
    }

    public String getKand10() {
        return kand10;
    }

    public void setKand10(String kand10) {
        this.kand10 = kand10;
    }

    public String getKand9() {
        return kand9;
    }

    public void setKand9(String kand9) {
        this.kand9 = kand9;
    }

    public KodCawanganDAO getKodCawanganDAO() {
        return kodCawanganDAO;
    }

    public void setKodCawanganDAO(KodCawanganDAO kodCawanganDAO) {
        this.kodCawanganDAO = kodCawanganDAO;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
    }

    public List<PermohonanPihak> getMohanPihakList() {
        return mohanPihakList;
    }

    public void setMohanPihakList(List<PermohonanPihak> mohanPihakList) {
        this.mohanPihakList = mohanPihakList;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
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

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public BigDecimal getLuasT() {
        return luasT;
    }

    public void setLuasT(BigDecimal luasT) {
        this.luasT = luasT;
    }

    public String getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(String kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }
}
