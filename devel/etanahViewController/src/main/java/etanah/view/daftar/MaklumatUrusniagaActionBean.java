/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodPerintahDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanUrusanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.service.common.UrusniagaService;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.PermohonanUrusan;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/daftar/maklumat_urusniaga")
public class MaklumatUrusniagaActionBean extends AbleActionBean {

    @Inject
    PermohonanUrusanDAO permohonanUrusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarService;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KodPerintahDAO kodPerintahDAO;
    @Inject
    UrusniagaService uManager;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;    
    private PermohonanUrusan urusan;
    private Permohonan permohonan;
    public PermohonanRujukanLuar permohonanRujukanLuar;
    public PermohonanRujukanLuar uniquePermohonanRujukanLuar;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    public String trhSaksi;
    public String noFail;
    public String namaSidang;
    private static final Logger LOGGER = Logger.getLogger(MaklumatUrusniagaActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final String[] URUSAN_KOD_PERINTAH_MAHKAMAH = {"PJTM"};

    public PermohonanUrusan getUrusan() {
        return urusan;
    }

    public void setUrusan(PermohonanUrusan urusan) {
        this.urusan = urusan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
//        if(urusan != null )
//        {
//            BigDecimal amaun = urusan.getPerjanjianAmaun();
//            BigDecimal tax = new BigDecimal("0.02");
//            BigDecimal tax2 = new BigDecimal("0.01");
//            if (amaun != null) {
//                if (amaun.doubleValue() > 100000) {
//                    amaun = amaun.multiply(tax);
//                } else {
//                    amaun = amaun.multiply(tax2);
//                }
//                
//                if (urusan.getPerjanjianDutiSetem() == null) {
//                    urusan.setPerjanjianDutiSetem(amaun);
//                }
//            }
//        }
        return new JSP("daftar/kemasukan_maklumat_urusniaga.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPendaftar() {
        if (urusan != null) {
            BigDecimal amaun = urusan.getPerjanjianAmaun();
            BigDecimal tax = new BigDecimal("0.02");
            BigDecimal tax2 = new BigDecimal("0.01");
            if (amaun != null) {
                if (amaun.doubleValue() > 100000) {
                    amaun = amaun.multiply(tax);
                } else {
                    amaun = amaun.multiply(tax2);
                }
                
                if (urusan.getPerjanjianDutiSetem() == null) {
                    urusan.setPerjanjianDutiSetem(amaun);
                }
            }
        }
        return new JSP("daftar/paparan_maklumat_urusniaga.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOGGER.info("rehydrate");
//        permohonan = ((etanahActionBeanContext)getContext()).getPermohonan();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
//            List<PermohonanUrusan> urs = uManager.findAll();
//            for (PermohonanUrusan permohonanUrusan : urs) {
//                if (permohonanUrusan.getPermohonan().equals(permohonan)) {
//                    urusan = permohonanUrusa
//
//                    if (urusan != null && urusan.getTarikhSaksi() != null) {
//                        trhSaksi = sdf.format(urusan.getTarikhSaksi());
//                    }
//                    break;
//                }
//            }

            List<PermohonanUrusan> urs = permohonan.getSenaraiUrusan();
            if(urs != null && urs.size() > 0) {
                urusan = urs.get(0);
                if (urusan != null && urusan.getTarikhSaksi() != null) {
                    trhSaksi = sdf.format(urusan.getTarikhSaksi());
                    if (urusan.getPerjanjianDutiSetem() == null) {
                        BigDecimal amaun = urusan.getPerjanjianAmaun();
                        BigDecimal tax = new BigDecimal("0.02");
                        BigDecimal tax2 = new BigDecimal("0.01");
                        if (amaun != null) {
                            if (amaun.doubleValue() > 100000) {
                                amaun = amaun.multiply(tax);
                            } else {
                                amaun = amaun.multiply(tax2);
                            }
                            urusan.setPerjanjianDutiSetem(amaun);
                        }                        
                        uManager.saveOrUpdate(urusan);
                    }
                }
            }

            List<PermohonanRujukanLuar> mohonList = permohonan.getSenaraiRujukanLuar();
            if (mohonList.size() > 0) {
                permohonanRujukanLuar = mohonList.get(0);
                if (permohonanRujukanLuar != null ) {
                    if(permohonanRujukanLuar.getNoFail() != null)
                    {noFail = permohonanRujukanLuar.getNoFail();}
                    if(permohonanRujukanLuar.getNamaSidang() != null) 
                    {namaSidang = permohonanRujukanLuar.getNamaSidang();}
                }
                
            }
        }
    }

    @HandlesEvent("save")
    public Resolution saveOrUpdate() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();        
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            if(urusan == null || urusan.getInfoAudit() == null) {
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
            } else {
                info = urusan.getInfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new Date());
            }
            urusan.setInfoAudit(info);
            urusan.setCawangan(peng.getKodCawangan());
            urusan.setPermohonan(permohonan);
            if (trhSaksi != null) {
                urusan.setTarikhSaksi(sdf.parse(trhSaksi));
            }

            uManager.saveOrUpdateByConn(urusan);

            List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
            senaraiPermohonanRujukanLuar = permohonan.getSenaraiRujukanLuar();
            for (int i = 0; i < senaraiHakmilik.size(); i++) {
                HakmilikPermohonan hakmilikPermohonan = senaraiHakmilik.get(i);
/*Enter New Data*/if (senaraiPermohonanRujukanLuar.isEmpty()) {                    
                    uniquePermohonanRujukanLuar = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(hakmilikPermohonan.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
                     /*Avoid redundant*/  
                    if(uniquePermohonanRujukanLuar == null){
                    permohonanRujukanLuar = new PermohonanRujukanLuar();    
                    info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    if (trhSaksi != null) {
                            permohonanRujukanLuar.setTarikhSidang(sdf.parse(trhSaksi));
                        }
                        if (urusan.getPerjanjianNoRujukan() != null) {
                            permohonanRujukanLuar.setNoSidang(urusan.getPerjanjianNoRujukan());
                        }
                        if (ArrayUtils.contains(URUSAN_KOD_PERINTAH_MAHKAMAH, permohonan.getKodUrusan().getKod()))
                        {
                            permohonanRujukanLuar.setKodPerintah(kodPerintahDAO.findById("M"));
                        }
                        permohonanRujukanLuar.setNamaSidang(namaSidang);
                        permohonanRujukanLuar.setHakmilik(hakmilikPermohonan.getHakmilik());
                        permohonanRujukanLuar.setPermohonan(permohonan);
                        permohonanRujukanLuar.setInfoAudit(info);
                        permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                        permohonanRujukanLuar.setNoFail(noFail);
                        permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("FL"));
                         
                        permohonanRujukanLuarService.saveOrUpdateByConn(permohonanRujukanLuar);
                        }
/*Update Data*/   } else { 
                    info.setTarikhKemaskini(new java.util.Date());
                    info.setDiKemaskiniOleh(peng);
                        permohonanRujukanLuar = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(hakmilikPermohonan.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
                        if (trhSaksi != null) {
                            permohonanRujukanLuar.setTarikhSidang(sdf.parse(trhSaksi));
                        }
                        if (urusan.getPerjanjianNoRujukan() != null) {
                            permohonanRujukanLuar.setNoSidang(urusan.getPerjanjianNoRujukan());
                        }
                        if (ArrayUtils.contains(URUSAN_KOD_PERINTAH_MAHKAMAH, permohonan.getKodUrusan().getKod()))
                        {
                            permohonanRujukanLuar.setKodPerintah(kodPerintahDAO.findById("M"));
                        }
                        permohonanRujukanLuar.setNamaSidang(namaSidang);
                        permohonanRujukanLuar.setInfoAudit(info);
                        permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                        permohonanRujukanLuar.setNoFail(noFail);
                        permohonanRujukanLuarService.saveOrUpdateByConn(permohonanRujukanLuar);
                }
               
            }
            tx.commit();
            addSimpleMessage("Kemasukan Data Berjaya");
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
            addSimpleError("Kemasukan Data Gagal.");
        }        
        return new JSP("daftar/kemasukan_maklumat_urusniaga.jsp").addParameter("tab", "true");
    }
}
