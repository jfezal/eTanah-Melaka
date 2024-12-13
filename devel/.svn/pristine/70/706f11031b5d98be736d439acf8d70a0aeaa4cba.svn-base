/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Siti Fariza Hanim
 */
@UrlBinding("/penguatkuasaan/maklumat_agensi_baru")
public class AgensiActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(AgensiActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    private etanah.Configuration conf;
    private List<PermohonanRujukanLuar> senaraiMohonRuj;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private PermohonanRujukanLuar mohonRuj;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private KodCawangan cawangan;
    private KodAgensi agensi;
    private String namaAgensi;
    private String lokasiAgensi;
    private String tarikhKuatkuasa;
    private String catatan;
    private String idPermohonan;
    private String idRujukan;
    private String dok;
    private String ruj;
    private FolderDokumen folderDokumen;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_agensi.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_agensi.jsp").addParameter("tab", "true");
    }

    public Resolution popupAgensi() {
        return new JSP("penguatkuasaan/popup_agensi.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
//        dok = mohonRuj.getKodDokumenRujukan().getKod();
//        ruj = mohonRuj.getKodRujukan().getKod();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))){
                senaraiMohonRuj = enforceService.findPermohonanRujukanLuar3(idPermohonan);
            }else{
                senaraiMohonRuj = enforceService.findPermohonanRujukanLuar2(idPermohonan);
            }
            
//            senaraiMohonRuj = enforceService.findPermohonanRujukanLuar3(idPermohonan,dok,ruj);
            if (senaraiMohonRuj.size() == 0) {
                senaraiKandungan = new ArrayList<KandunganFolder>();
                folderDokumen = permohonan.getFolderDokumen();
                if (folderDokumen != null) {

                    if (folderDokumen.getSenaraiKandungan() != null) {
                        for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                            kf.setDokumen(d);
                            senaraiKandungan.add(kf);
                        }
                    }
                }

                logger.info("----size senarai kandungan : ---- " + senaraiKandungan.size());
            }
        }

    }

    public Resolution simpanPopup() throws ParseException {
        logger.debug("--------------simpanPopup-----------------");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (mohonRuj == null) {
            mohonRuj = new PermohonanRujukanLuar();
        }
        KodDokumen doc = new KodDokumen();
        KodRujukan kr = new KodRujukan();
        if("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))){
            doc.setKod("AGEPU");
        }else{
            doc.setKod("BR");
        }
        
        kr.setKod("AP");
        mohonRuj = new PermohonanRujukanLuar();
        mohonRuj.setPermohonan(permohonan);
        mohonRuj.setCawangan(permohonan.getCawangan());
        if (mohonRuj.getTarikhKuatkuasa() == null) {
            mohonRuj.setTarikhKuatkuasa(sdf.parse(tarikhKuatkuasa));
        }
        mohonRuj.setCatatan(catatan);
        mohonRuj.setNamaAgensi(namaAgensi);
        mohonRuj.setLokasiAgensi(lokasiAgensi);
        mohonRuj.setKodDokumenRujukan(doc);
        mohonRuj.setKodRujukan(kr);

        InfoAudit ia = mohonRuj.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            mohonRuj.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforceService.simpanRujukan(mohonRuj);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_agensi.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_agensi.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        logger.info("-----------deleteSingle----------------");
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        mohonRuj = enforceService.findByIdRujukan(Long.parseLong(idRujukan));

        if (mohonRuj != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            mohonRuj.setInfoAudit(ia);
            mohonRuj.setCawangan(cawangan);
            enforceService.deleteAgensi(mohonRuj);
            //Delete image from dokumen
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            tx.begin();
            try {
                if (permohonan.getFolderDokumen() != null) {
                    senaraiKandungan = enforceService.getListDokumen(permohonan.getFolderDokumen().getFolderId());
                    if (senaraiKandungan.size() != 0) {
                        for (KandunganFolder kf : senaraiKandungan) {
                            Dokumen dokumen = kf.getDokumen();
                            if (dokumen.getKodDokumen() != null) {
                                if (dokumen.getKodDokumen().getKod().equalsIgnoreCase("BR")) {
                                    if (dokumen.getPerihal().equalsIgnoreCase(idRujukan)) {
                                        logger.info("------------id dokumen----------------- : " + dokumen.getIdDokumen());
                                        dokumenDAO.delete(dokumen);
                                    }
                                }
                            }
                        }
                    }
                }

                tx.commit();

            } catch (Exception ex) {
                tx.rollback();
                Throwable t = ex;
                // getting the root cause
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                ex.printStackTrace();
                addSimpleError(t.getMessage());
            }
        }
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_agensi.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_agensi.jsp").addParameter("tab", "true");
    }

    public PermohonanRujukanLuar getMohonRuj() {
        return mohonRuj;
    }

    public void setMohonRuj(PermohonanRujukanLuar mohonRuj) {
        this.mohonRuj = mohonRuj;
    }

    public String getLokasiAgensi() {
        return lokasiAgensi;
    }

    public void setLokasiAgensi(String lokasiAgensi) {
        this.lokasiAgensi = lokasiAgensi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNamaAgensi() {
        return namaAgensi;
    }

    public void setNamaAgensi(String namaAgensi) {
        this.namaAgensi = namaAgensi;
    }

    public String getTarikhKuatkuasa() {
        return tarikhKuatkuasa;
    }

    public void setTarikhKuatkuasa(String tarikhKuatkuasa) {
        this.tarikhKuatkuasa = tarikhKuatkuasa;
    }

    public List<PermohonanRujukanLuar> getSenaraiMohonRuj() {
        return senaraiMohonRuj;
    }

    public void setSenaraiMohonRuj(List<PermohonanRujukanLuar> senaraiMohonRuj) {
        this.senaraiMohonRuj = senaraiMohonRuj;
    }

    public KodAgensi getAgensi() {
        return agensi;
    }

    public void setAgensi(KodAgensi agensi) {
        this.agensi = agensi;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        AgensiActionBean.logger = logger;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public String getDok() {
        return dok;
    }

    public void setDok(String dok) {
        this.dok = dok;
    }

    public String getRuj() {
        return ruj;
    }

    public void setRuj(String ruj) {
        this.ruj = ruj;
    }


}
