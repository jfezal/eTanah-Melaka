/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pembangunan.PuActionBean;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Murali
 */
@UrlBinding("/pelupusan/surat_tolak")
public class SuratTolakActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PembangunanService pembangunanService;
    private Permohonan permohonan;
    private String idPermohonan;
    private List<Pengguna> penggunaList;
    private String ditundatangan;
    private PermohonanTandatanganDokumen tandatanganDokumen;
    Logger logger = Logger.getLogger(SuratTolakActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/surat_tolak.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.info("--------idPermohonan-------" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        logger.info("--------permohonan in rehydrate()::-------" + permohonan);
        if (idPermohonan != null) {
            penggunaList = getSenaraiPengguna(permohonan.getCawangan());
//            permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
//            if(permohonanUkur != null &&  permohonanUkur.getKodHakmilik()!= null){
//                kodHakmilik=permohonanUkur.getKodHakmilik().getKod();
//            }
//            if(permohonanUkur == null){
//                permohonanUkur = new PermohonanUkur();
//            }

            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                if (tandatanganDokumen != null) {
                    ditundatangan = tandatanganDokumen.getDiTandatangan();
                    logger.info("ditundatangan not null");
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("PBMMK")) {
                tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                if (tandatanganDokumen != null) {
                    ditundatangan = tandatanganDokumen.getDiTandatangan();
                    logger.info("ditundatangan not null");
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                if (tandatanganDokumen != null) {
                    ditundatangan = tandatanganDokumen.getDiTandatangan();
                    logger.info("ditundatangan not null");
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                if (tandatanganDokumen != null) {
                    ditundatangan = tandatanganDokumen.getDiTandatangan();
                    logger.info("ditundatangan not null");
                }
            }
        }
    }

    public Resolution simpanTandatangan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        PermohonanTandatanganDokumen tandatanganDokumen1 = pembangunanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
        ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        logger.info("testing.............. " + ditundatangan);
        if (ditundatangan != null) {
            if (tandatanganDokumen1 != null) {
                ia = tandatanganDokumen1.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                tandatanganDokumen1.setPermohonan(permohonan);
                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            }
            tandatanganDokumen1.setInfoAudit(ia);
            tandatanganDokumen1.setDiTandatangan(ditundatangan);
            pembangunanService.simpanPermohonanTandatanganDokumen(tandatanganDokumen1);
        }
        addSimpleMessage("Rekod tandatangan telah dimasukkan");
        return new JSP("pelupusan/surat_tolak.jsp").addParameter("tab", "true");
    }

    public List<Pengguna> getSenaraiPengguna(KodCawangan kod) {
        try {
            // Melaka
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('224','225') and u.kodCawangan.kod = :kod order by u.nama";
//            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('PTD','PPTD','PPTK','PTT') and u.kodCawangan.kod = :kod order by u.nama";
            // NS
//            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('PTD','PPTD','TPTD','PPTK') and u.kodCawangan.kod = :kod order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    public Resolution simpan() {

        logger.info("--------Simpan-------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        logger.info("--------permohonan in Simpan-------" + permohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        logger.info("--------Pengguna::-------" + peng);

        if (permohonan != null) {
            logger.info("--------permohonan is Not Null-------");
            InfoAudit ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
            pelupusanService.simpanPermohonan(permohonan);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("pelupusan/surat_tolak.jsp").addParameter("tab", "true");

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

    public List<Pengguna> getPenggunaList() {
        return penggunaList;
    }

    public void setPenggunaList(List<Pengguna> penggunaList) {
        this.penggunaList = penggunaList;
    }

    public String getDitundatangan() {
        return ditundatangan;
    }

    public void setDitundatangan(String ditundatangan) {
        this.ditundatangan = ditundatangan;
    }
    
    
}
