/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.DokumenService;
import etanah.service.daftar.PembetulanService;
//import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

@UrlBinding("/pengambilan/penerimaan_borang1")
public class PenerimaanBorangActionBean_1 extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(PenerimaanBorangActionBean_1.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    PembetulanService pembetulanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
//    @Inject
//    NotisPenerimaanService notisPenerimaanService;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private DokumenService dokumenService;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPihakService permohonanPihakService;
    @Inject
    private PerbicaraanService perbicaraanService;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private String catatan;
    private String idPerbicaraan;
    private String hakmilikPerbicaraan1;
    private String caraPemilikan;
    private String tarikhBicara1;
    private String time;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> hakmilikPermohonanList1;
    private List<HakmilikPerbicaraan> hakmilikbicaraList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private List<String> tarikhBicara = new ArrayList<String>();
    private List<String> waktuPerbicaraan = new ArrayList<String>();
    private List<String> lokasiBicara = new ArrayList<String>();
    private List<String> jam = new ArrayList<String>();
    private List<String> minit = new ArrayList<String>();
    private List<String> ampm = new ArrayList<String>();
    private List<Long> idMH = new ArrayList<Long>();

    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/BorangE_1.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new JSP("pengambilan/BorangE_1.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        catatan = (String) getContext().getRequest().getSession().getAttribute("catatan");
        InfoAudit info = new InfoAudit();

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            permohonanPihakList = p.getSenaraiPihak();
            hakmilikPermohonanList1 = perbicaraanService.findByHakmilikPermohonanList(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();


            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                for (HakmilikPermohonan hh : hakmilikPermohonanList1) {
                    hakmilikbicaraList = perbicaraanService.getHakmilikPerbicaraanByIdMHList(hh.getId());
                    logger.info("hakmilikbicaraList" + hakmilikbicaraList.get(0).getIdPerbicaraan());
                    if (hakmilikbicaraList.size() > 1) {
                        hakmilikPerbicaraan = hakmilikbicaraList.get(0);
                    } else {
                        hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hh.getId());
                        logger.info("hakmilikPerbicaraan id " + hakmilikPerbicaraan.getIdPerbicaraan());
                    }
                    if (hp.getId() == hh.getId()) {
//                        hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hh.getId());
                        hakmilikbicaraList = perbicaraanService.getHakmilikPerbicaraanByIdMHList(hh.getId());
                        if (hakmilikPerbicaraan != null) {
                            catatan = hakmilikPerbicaraan.getCatatan();
                            logger.debug("=== pilih catatan === " + catatan);

                            if (hakmilikPerbicaraan.getTarikhBicara() != null) {
                                String tarikh1 = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
                                tarikhBicara1 = tarikh1.substring(0, 10);
                                time = tarikh1.substring(11, 19);
//                                minit1 = tarikh1.substring(14, 16);
//                                ampm1 = tarikh1.substring(20, 22);
                            }

                            if (StringUtils.isNotBlank(catatan)) {
                                if (catatan.equalsIgnoreCase("Bantahan") || catatan.equalsIgnoreCase("Tangguh")) {
                                    logger.debug("catatan != Lulus");
                                } else if (catatan.equalsIgnoreCase("Lulus") || catatan == null) {
                                    logger.debug("catatan == Lulus " + catatan);
                                    try {
                                        if (hh.getId() == hakmilikPerbicaraan.getHakmilikPermohonan().getId()) {
                                            String lb = hakmilikPerbicaraan.getLokasiBicara();
                                            idMH.add(hakmilikPerbicaraan.getHakmilikPermohonan().getId());
                                            lokasiBicara.add(lb);
                                            String tb = sdf.format(hakmilikPerbicaraan.getTarikhBicara());

                                            String tb1 = tb.substring(0, 10);
                                            String tb2 = tb.substring(11, 13);
                                            String tb3 = tb.substring(14, 16);
                                            String tb4 = tb.substring(17, 19);
                                            tarikhBicara.add(tb1);
                                            jam.add(tb2);
                                            minit.add(tb3);
                                            ampm.add(tb4);
                                        }

                                    } catch (Exception e) {
                                    }

                                }
                            }
                        }
                    }
                }

            }

//            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
//
//                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
//                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
////                hakmilikPermohonanList1 = perbicaraanService.findByHakmilikPermohonanList(hmp.getId());
//
//                if (hakmilikPerbicaraan != null) {
//                    catatan = hakmilikPerbicaraan.getCatatan();
//                    logger.debug("=== pilih catatan === " + catatan);
//
//                    if (StringUtils.isNotBlank(catatan)) {
//                        if (catatan.equalsIgnoreCase("Bantahan") || catatan.equalsIgnoreCase("Tangguh")) {
//                            logger.debug("catatan != Lulus");
//                        } //                        if (catatan.equalsIgnoreCase("Lulus"))
//                        else if (catatan.equalsIgnoreCase("Lulus") || catatan == null) {
//                            logger.debug("catatan == Lulus " + catatan);
//                            try {
//                                String lb = hakmilikPerbicaraan.getLokasiBicara();
//                                lokasiBicara.add(lb);
//                                String tb = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
//
//                                String tb1 = tb.substring(0, 10);
//                                String tb2 = tb.substring(11, 13);
//                                String tb3 = tb.substring(14, 16);
//                                String tb4 = tb.substring(17, 19);
//                                tarikhBicara.add(tb1);
//                                jam.add(tb2);
//                                minit.add(tb3);
//                                ampm.add(tb4);
//                            } catch (Exception e) {
//                            }
//
//                        }
//                    }
//                }
//
//
//
////                HakmilikPerbicaraan hp = new HakmilikPerbicaraan();
////                if (catatan == null) {
////                    hp.setInfoAudit(info);
////                    info.setDimasukOleh(peng);
////                    info.setTarikhMasuk(new java.util.Date());
////                }
////                if (catatan.equalsIgnoreCase("pertikai")) {
////                    hp.setInfoAudit(info);
////                    info.setDimasukOleh(peng);
////                    info.setTarikhMasuk(new java.util.Date());
////                } else {
////                    info = hp.getInfoAudit();
////                    info.setDiKemaskiniOleh(peng);
////                    info.setTarikhKemaskini(new java.util.Date());
////                }
//
//
//            }
        }


    }

    public Resolution refreshpage() {
        rehydrate();





        return new RedirectResolution(PenerimaanBorangActionBean_1.class, "locate");
    }

    public Resolution simpan() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        //hakmilik = hakmilikDAO.findById(idHakmilik);




        if (idPermohonan != null) {
            Notis notis = new Notis();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            notis.setInfoAudit(info);
            notis.setPermohonan(permohonan);
            notis.setPihak(null);


            Dokumen dokumen = new Dokumen();
            dokumen.setTajuk(tajuk);
            dokumen.setInfoAudit(info);
            Dokumen dokumen1 = dokumenService.saveOrUpdate(dokumen);

            notis.setDokumenNotis(dokumen1);
            notis.setTarikhNotis(sdf.parse(tarikhNotis));

            KodStatusTerima kodStatusTerima1 = kodStatusTerimaDAO.findById(kodStatusTerima);

            notis.setKodStatusTerima(kodStatusTerima1);
            notis.setTarikhHantar(sdf.parse(tarikhHantar));
            notis.setTarikhTampal(sdf.parse(tarikhTampal));
//            notisPenerimaanService.saveOrUpdateNotis(notis);
            addSimpleMessage(
                    "Maklumat telah berjaya disimpan");


        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/BorangE_1.jsp").addParameter("tab", "true");


    }

    public Resolution editDetails() {

        String rowCount = (String) getContext().getRequest().getParameter("rowCount");



        int rowCountval = Integer.parseInt(rowCount);


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        KodCawangan cawangan = permohonan.getCawangan();

        HakmilikPermohonan hmp = hakmilikPermohonanList.get(rowCountval);
        HakmilikPerbicaraan hakmilikPerbicaraan = new HakmilikPerbicaraan();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        hakmilikPerbicaraan.setInfoAudit(info);
        hakmilikPerbicaraan.setCawangan(cawangan);
        hakmilikPerbicaraan.setHakmilikPermohonan(hmp);



        try {
            if (lokasiBicara.get(rowCountval) != null) {
                hakmilikPerbicaraan.setLokasiBicara(lokasiBicara.get(rowCountval));


            }

            if (tarikhBicara.get(rowCountval) != null) {
                String StrTarikhBicara = tarikhBicara.get(rowCountval);
                StrTarikhBicara = StrTarikhBicara + " " + jam.get(rowCountval) + ":" + minit.get(rowCountval) + " " + ampm.get(rowCountval);

                hakmilikPerbicaraan.setTarikhBicara(sdf.parse(StrTarikhBicara));


            }
        } catch (Exception e) {
            e.printStackTrace();


        }


        return new ForwardResolution("/WEB-INF/jsp/pengambilan/BorangE_1.jsp").addParameter("tab", "true");




    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();


        hakmilikPermohonanList1 = perbicaraanService.findByHakmilikPermohonanList(idPermohonan);
        for (int i = 0; i
                < hakmilikPermohonanList1.size(); i++) {

            HakmilikPermohonan hmp = hakmilikPermohonanList1.get(i);



            if (perbicaraanService.getHakmilikPerbicaraanByIdMHList(hmp.getId()) != null) {
                if (catatan.equalsIgnoreCase("Bantahan") || catatan.equalsIgnoreCase("Tangguh")) {
                    logger.debug("catatan == " + catatan);
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(info);
                    hakmilikPerbicaraan.setCawangan(cawangan);
                    hakmilikPerbicaraan.setHakmilikPermohonan(hmp);
                    hakmilikPerbicaraan.setBatalRizab('T');
                    hakmilikPerbicaraan.setKawasanPBT('T');
                    hakmilikPerbicaraan.setPelanPembangunan('T');
                }

            } else {
                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
                InfoAudit ia = hakmilikPerbicaraan.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(ia);

            }
            caraPemilikan = getContext().getRequest().getParameter("hakmilikPerbicaraan.caraPemilikan");
            hakmilikPerbicaraan.setCaraPemilikan(caraPemilikan);

            try {

                if (i < lokasiBicara.size()) {
                    if (lokasiBicara.get(i) != null) {
                        hakmilikPerbicaraan.setLokasiBicara(lokasiBicara.get(i));


                    }
                }

                if (i < tarikhBicara.size()) {
                    if (tarikhBicara.get(i) != null) {
                        String StrTarikhBicara = tarikhBicara.get(i);
                        StrTarikhBicara = StrTarikhBicara + " " + jam.get(i) + ":" + minit.get(i) + " " + ampm.get(i);
                        hakmilikPerbicaraan.setTarikhBicara(sdf.parse(StrTarikhBicara));


                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                addSimpleError(
                        "Invalid Data");


            }
            perbicaraanService.saveSingleHakmilikPerbicaraan(hakmilikPerbicaraan);


        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");


        return new ForwardResolution("/WEB-INF/jsp/pengambilan/BorangE_1.jsp").addParameter("tab", "true");


    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;


    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;


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

    public String getIdHakmilik() {
        return idHakmilik;


    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;


    }

    public String getIdDokumen() {
        return idDokumen;


    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;


    }

    public String getIdPihak() {
        return idPihak;


    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;


    }

    public String getKodStatusTerima() {
        return kodStatusTerima;


    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;


    }

    public String getTajuk() {
        return tajuk;


    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;


    }

    public String getTarikhHantar() {
        return tarikhHantar;


    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;


    }

    public String getTarikhNotis() {
        return tarikhNotis;


    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;


    }

    public String getTarikhTampal() {
        return tarikhTampal;


    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;


    }

    public List<String> getLokasiBicara() {
        return lokasiBicara;


    }

    public void setLokasiBicara(List<String> lokasiBicara) {
        this.lokasiBicara = lokasiBicara;


    }

    public String getIdPermohonan() {
        return idPermohonan;


    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;


    }

    public List<String> getTarikhBicara() {
        return tarikhBicara;


    }

    public void setTarikhBicara(List<String> tarikhBicara) {
        this.tarikhBicara = tarikhBicara;


    }

    public List<String> getWaktuPerbicaraan() {
        return waktuPerbicaraan;


    }

    public void setWaktuPerbicaraan(List<String> waktuPerbicaraan) {
        this.waktuPerbicaraan = waktuPerbicaraan;


    }

    public List<String> getAmpm() {
        return ampm;


    }

    public void setAmpm(List<String> ampm) {
        this.ampm = ampm;


    }

    public List<String> getJam() {
        return jam;


    }

    public void setJam(List<String> jam) {
        this.jam = jam;


    }

    public List<String> getMinit() {
        return minit;


    }

    public void setMinit(List<String> minit) {
        this.minit = minit;


    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;


    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;


    }

    public String getCatatan() {
        return catatan;


    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;


    }

    public String getIdPerbicaraan() {
        return idPerbicaraan;


    }

    public void setIdPerbicaraan(String idPerbicaraan) {
        this.idPerbicaraan = idPerbicaraan;


    }

    public String getHakmilikPerbicaraan1() {
        return hakmilikPerbicaraan1;


    }

    public void setHakmilikPerbicaraan1(String hakmilikPerbicaraan1) {
        this.hakmilikPerbicaraan1 = hakmilikPerbicaraan1;


    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;


    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;

    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList1() {
        return hakmilikPermohonanList1;
    }

    public void setHakmilikPermohonanList1(List<HakmilikPermohonan> hakmilikPermohonanList1) {
        this.hakmilikPermohonanList1 = hakmilikPermohonanList1;
    }

    public List<Long> getIdMH() {
        return idMH;
    }

    public void setIdMH(List<Long> idMH) {
        this.idMH = idMH;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPerbicaraan> getHakmilikbicaraList() {
        return hakmilikbicaraList;
    }

    public void setHakmilikbicaraList(List<HakmilikPerbicaraan> hakmilikbicaraList) {
        this.hakmilikbicaraList = hakmilikbicaraList;
    }

    public String getCaraPemilikan() {
        return caraPemilikan;
    }

    public void setCaraPemilikan(String caraPemilikan) {
        this.caraPemilikan = caraPemilikan;
    }

    public String getTarikhBicara1() {
        return tarikhBicara1;
    }

    public void setTarikhBicara1(String tarikhBicara1) {
        this.tarikhBicara1 = tarikhBicara1;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
