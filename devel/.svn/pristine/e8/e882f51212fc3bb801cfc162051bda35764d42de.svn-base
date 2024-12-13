/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.InfoAudit;

import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.google.inject.Inject;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.KodRujukan;
import etanah.model.PermohonanRujukanLuar;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/terima_warta")
public class TerimaanWartaActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(TerimaanWartaActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodRujukanDAO kodRujukanAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private etanah.Configuration conf;
    private Pengguna pengguna;
    private String idPermohonan;
    private Permohonan permohonan;
    private List<KodRujukan> senaraiKodRuj;
    private List<PermohonanRujukanLuar> senaraiWarta;
    private PermohonanRujukanLuar mohonRujukLuar;
    private String jenisWarta;
    private String noWarta;
    private String tarikhWarta;
    private String tarikhTerima;
    private String tarikhSampai;
    private String catatan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String kodNegeri;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_terima_warta.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_terima_warta.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            
            if(kodNegeri.equalsIgnoreCase("05")){
                senaraiKodRuj = enforcementService.senaraiKodRujukanWarta();
            }else{
                senaraiKodRuj = enforcementService.senaraiKodRujukan();
            }
            
            senaraiWarta = enforcementService.getSenaraiWarta(idPermohonan);
        }

    }

    public Resolution tambahWarta() {
        return new JSP("penguatkuasaan/popup_tambah_warta.jsp").addParameter("popup", "true");
    }

    public Resolution viewTerimaanWarta() {
        String id = getContext().getRequest().getParameter("id");
        mohonRujukLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(id));
        return new JSP("penguatkuasaan/popup_view_warta.jsp").addParameter("popup", "true");
    }

    public Resolution deleteWarta() {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        String id = getContext().getRequest().getParameter("id");
        mohonRujukLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(id));

        if (mohonRujukLuar != null) {
            permohonanRujukanLuarDAO.delete(mohonRujukLuar);
            tx.commit();
        }
        return new RedirectResolution(TerimaanWartaActionBean.class, "locate");
    }

    public Resolution editWarta() {
        String id = getContext().getRequest().getParameter("id");
        mohonRujukLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(id));
        if (mohonRujukLuar != null) {
            try {
                jenisWarta = mohonRujukLuar.getKodRujukan().getKod();
                noWarta = mohonRujukLuar.getNoRujukan();
                catatan = mohonRujukLuar.getCatatan();
                if (mohonRujukLuar.getTarikhRujukan() != null) {
                    tarikhWarta = sdf.format(mohonRujukLuar.getTarikhRujukan());
                }
                if (mohonRujukLuar.getTarikhTerima() != null) {
                    tarikhTerima = sdf.format(mohonRujukLuar.getTarikhTerima());
                }
                if (mohonRujukLuar.getTarikhDisampai() != null) {
                    tarikhSampai = sdf.format(mohonRujukLuar.getTarikhDisampai());
                }

            } catch (Exception e) {
                logger.error(e);
                e.printStackTrace();

            }

        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_edit_warta.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() {
        logger.info("------------simpan--------------");
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            InfoAudit info = pengguna.getInfoAudit();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());

            KodRujukan kodRujuk = kodRujukanAO.findById(jenisWarta);
            mohonRujukLuar = new PermohonanRujukanLuar();
            mohonRujukLuar.setPermohonan(permohonan);
            mohonRujukLuar.setCawangan(pengguna.getKodCawangan());
            mohonRujukLuar.setInfoAudit(info);
            mohonRujukLuar.setKodRujukan(kodRujuk);
            mohonRujukLuar.setNoRujukan(noWarta);
            mohonRujukLuar.setCatatan(catatan);
            try {
                if (tarikhWarta != null) {
                    mohonRujukLuar.setTarikhRujukan(sdf.parse(tarikhWarta));
                } else {
                    mohonRujukLuar.setTarikhRujukan(null);
                }
                if (tarikhTerima != null) {
                    mohonRujukLuar.setTarikhTerima(sdf.parse(tarikhTerima));
                } else {
                    mohonRujukLuar.setTarikhTerima(null);
                }
                if (tarikhSampai != null) {
                    if (kodNegeri.equalsIgnoreCase("05")) {
                        mohonRujukLuar.setTarikhKuatkuasa(sdf.parse(tarikhSampai));
                    }
                    mohonRujukLuar.setTarikhDisampai(sdf.parse(tarikhSampai));
                } else {
                    if (kodNegeri.equalsIgnoreCase("05")) {
                        mohonRujukLuar.setTarikhKuatkuasa(null);
                    }
                    mohonRujukLuar.setTarikhDisampai(null);
                }

            } catch (Exception e) {
                logger.error(e);
            }
            permohonanRujukanLuarDAO.saveOrUpdate(mohonRujukLuar);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/maklumat_terima_warta.jsp").addParameter("tab", "true");

    }

    public Resolution edit() {
        logger.info("------------edit--------------");

        String id = getContext().getRequest().getParameter("id");
        mohonRujukLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(id));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            InfoAudit info = mohonRujukLuar.getInfoAudit();
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());

            KodRujukan kodRujuk = kodRujukanAO.findById(jenisWarta);
            mohonRujukLuar.setInfoAudit(info);
            mohonRujukLuar.setKodRujukan(kodRujuk);
            mohonRujukLuar.setNoRujukan(noWarta);
            mohonRujukLuar.setCatatan(catatan);
            try {
                if (tarikhWarta != null) {
                    mohonRujukLuar.setTarikhRujukan(sdf.parse(tarikhWarta));
                } else {
                    mohonRujukLuar.setTarikhRujukan(null);
                }
                if (tarikhTerima != null) {
                    mohonRujukLuar.setTarikhTerima(sdf.parse(tarikhTerima));
                } else {
                    mohonRujukLuar.setTarikhTerima(null);
                }
                if (tarikhSampai != null) {
                    mohonRujukLuar.setTarikhDisampai(sdf.parse(tarikhSampai));
                } else {
                    mohonRujukLuar.setTarikhDisampai(null);
                }

            } catch (Exception e) {
                logger.error(e);
            }
            permohonanRujukanLuarDAO.saveOrUpdate(mohonRujukLuar);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/maklumat_terima_warta.jsp").addParameter("tab", "true");

    }

    public Resolution refreshpage() {
        return new RedirectResolution(TerimaanWartaActionBean.class, "locate");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getJenisWarta() {
        return jenisWarta;
    }

    public void setJenisWarta(String jenisWarta) {
        this.jenisWarta = jenisWarta;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public List<KodRujukan> getSenaraiKodRuj() {
        return senaraiKodRuj;
    }

    public void setSenaraiKodRuj(List<KodRujukan> senaraiKodRuj) {
        this.senaraiKodRuj = senaraiKodRuj;
    }

    public String getTarikhSampai() {
        return tarikhSampai;
    }

    public void setTarikhSampai(String tarikhSampai) {
        this.tarikhSampai = tarikhSampai;
    }

    public String getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(String tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public PermohonanRujukanLuar getMohonRujukLuar() {
        return mohonRujukLuar;
    }

    public void setMohonRujukLuar(PermohonanRujukanLuar mohonRujukLuar) {
        this.mohonRujukLuar = mohonRujukLuar;
    }

    public List<PermohonanRujukanLuar> getSenaraiWarta() {
        return senaraiWarta;
    }

    public void setSenaraiWarta(List<PermohonanRujukanLuar> senaraiWarta) {
        this.senaraiWarta = senaraiWarta;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
