/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.PermohonanDAO;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Permohonan;
import etanah.model.PermohonanUkur;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.service.common.HakmilikService;
//import etanah.service.HakmilikService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.ForwardResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import java.util.List;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Rohan
 */
@UrlBinding("/pengambilan/sijilPengecualianUkur")
public class SijilPengecualianUkurActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(SijilPengecualianUkurActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PembangunanService devService;
    @Inject
    HakmilikService hakmilikservice;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private PermohonanUkur permohonanUkur;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private String tarikhPerakuan;
    private String failPTG;
    private String tujuan;
    private Pengguna pengguna;
    private List<HakmilikPermohonan> sebahagianList;
    private List<HakmilikPermohonan> hakMilikPermohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen831a/sijilPengecualianUkur.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pengambilan/negerisembilan/seksyen831a/sijilPengecualianUkur.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("rehydrate start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
        sebahagianList = hakmilikservice.findMHSebahagian(permohonan.getIdPermohonan());
        List<HakmilikPermohonan> hakMilikPermohonanList = permohonan.getSenaraiHakmilik();
        String noHakmilik = "";
        int jum = hakMilikPermohonanList.size();
        int jum2 = jum - 1;
        int bil = 0;
        for (HakmilikPermohonan hpl : hakMilikPermohonanList) {
            bil++;
            if (bil < jum2) {
                noHakmilik += hpl.getHakmilik().getLot().getNama() + " " + hpl.getHakmilik().getNoLot() + " " + hpl.getHakmilik().getKodHakmilik().getKod() + " " + hpl.getHakmilik().getNoHakmilik() + ", ";
            } else if (bil == jum2) {
                noHakmilik += hpl.getHakmilik().getLot().getNama() + " " + hpl.getHakmilik().getNoLot() + " " + hpl.getHakmilik().getKodHakmilik().getKod() + " " + hpl.getHakmilik().getNoHakmilik() + " ";
            } else if (bil == jum) {
                noHakmilik += " dan " + hpl.getHakmilik().getLot().getNama() + " " + hpl.getHakmilik().getNoLot() + " " + hpl.getHakmilik().getKodHakmilik().getKod() + " " + hpl.getHakmilik().getNoHakmilik() + " ";
            }
        }
        if (permohonanUkur != null) {
            if (sebahagianList.size() != 0) {
                logger.info("masuk tujuan");
                tujuan = "Mengikut kuasa yang diberikan di bawah perenggan 4(2) N.L.C. (Survey Fees) Order 1965 yang telah diwartakan mengikut L.N. 486 bertarikh 30.12.1965, pembebasan upah ukur adalah diberi bagi tujuan pengambilan "
                        + "sebahagian tanah " + noHakmilik + " " + hakmilik.getBandarPekanMukim().getNama() + ", " + hakmilik.getDaerah().getNama() + " bagi tujuan " + WordUtils.capitalizeFully(permohonan.getSebab().toLowerCase()) + ". ";
                //" + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik() + "
            }
        }




//        try {
//            if (permohonanUkur != null) {
//                tarikhPerakuan = sdf.format(permohonanUkur.getTarikhPerakuan());
//                failPTG = permohonanUkur.getNoFailISO();
//
//
//            }
//
//        } catch (Exception e) {
//            logger.info(e);
//        }

        logger.info("rehydrate finish");
    }

    public Resolution simpan() {
        logger.info("simpan start");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            String idMohonUkur = (String) getContext().getRequest().getParameter("permohonanUkur.idMohonUkur");
//            PermohonanUkur mohonUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
            PermohonanUkur mohonUkur = new PermohonanUkur();

            if (idMohonUkur != null && !idMohonUkur.equals("")) {
                mohonUkur = permohonanUkurDAO.findById(Integer.parseInt(idMohonUkur));
                infoAudit = mohonUkur.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

                NumberFormat df = new DecimalFormat("0000");
                Date dt = new Date();
                PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
                System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);

                if (maxPuPermohonanUkur == null) {
                    int val = 1;
                    String noPU = "";
                    noPU = df.format(val) + "/" + (1900 + dt.getYear());

                    System.out.println("-----------Seq-------------" + noPU);
                    mohonUkur.setNoPermohonanUkur(noPU);
                } else {
                    String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                    System.out.println("-----------maxNoPU-------------" + maxNoPU);
                    if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
                        String subNoPU1 = maxNoPU.substring(0, 4);
                        //                     String subNoPU2 = subNoPU1.substring(subNoPU1.lastIndexOf("0"));
                        //                     int val = Integer.parseInt(subNoPU2);
                        int val = Integer.parseInt(subNoPU1);
                        val = val + 1;
                        String noPU = "";
                        noPU = df.format(val) + "/" + (1900 + dt.getYear());
                        System.out.println("-----------Seq-------------" + noPU);
                        mohonUkur.setNoPermohonanUkur(noPU);
                    } else {
                        int value = 1;
                        String nopu = "";
                        nopu = df.format(value) + "/" + (1900 + dt.getYear());
                        mohonUkur.setNoPermohonanUkur(nopu);
                    }
                }

            }
            mohonUkur.setNoRujukanPejabatTanah(permohonan.getIdPermohonan());
            mohonUkur.setInfoAudit(infoAudit);
            mohonUkur.setPermohonan(permohonan);
            try {
                if (tarikhPerakuan != null) {
                    mohonUkur.setTarikhPerakuan(sdf.parse(tarikhPerakuan));
                    mohonUkur.setNoFailISO(failPTG);
                }
            } catch (Exception e) {
                logger.info(e);
            }

            devService.simpanPermohonanUkur(mohonUkur);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            tx.commit();
            if (idPermohonan != null) {
                permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
            }

//            if (mohonUkur != null) {
//                logger.info("update tarikh perakuan in mohon ukur start");
//                infoAudit = mohonUkur.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//
//                try {
//                    mohonUkur.setTarikhPerakuan(sdf.parse(tarikhPerakuan));
//
//                } catch (Exception e) {
//                    logger.info(e);
//                }
//
////                mohonUkur.setTarikhPerakuan(permohonanUkur.getTarikhPerakuan());
//                mohonUkur.setNoRujukanPejabatTanah(permohonanUkur.getNoRujukanPejabatTanah());
//                mohonUkur.setNoPermohonanUkur(permohonanUkur.getNoPermohonanUkur());
//                mohonUkur.setInfoAudit(infoAudit);
//                mohonUkur.setPermohonan(permohonan);
//
//                devService.simpanPermohonanUkur(mohonUkur);
//                addSimpleMessage("Maklumat telah berjaya disimpan.");
//                logger.info("update tarikh perakuan finish");
//            }
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("pengambilan/negerisembilan/seksyen831a/sijilPengecualianUkur.jsp").addParameter("tab", "true");
        }
        logger.info("simpan finish");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/negerisembilan/seksyen831a/sijilPengecualianUkur.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getTarikhPerakuan() {
        return tarikhPerakuan;
    }

    public void setTarikhPerakuan(String tarikhPerakuan) {
        this.tarikhPerakuan = tarikhPerakuan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getFailPTG() {
        return failPTG;
    }

    public void setFailPTG(String failPTG) {
        this.failPTG = failPTG;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public List<HakmilikPermohonan> getSebahagianList() {
        return sebahagianList;
    }

    public void setSebahagianList(List<HakmilikPermohonan> sebahagianList) {
        this.sebahagianList = sebahagianList;
    }
}
