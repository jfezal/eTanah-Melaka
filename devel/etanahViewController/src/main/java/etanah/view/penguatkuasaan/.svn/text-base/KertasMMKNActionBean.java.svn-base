/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;


import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.util.Date;

import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
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
import etanah.service.common.EnforcementService;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/kertas_mmkn")
public class KertasMMKNActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KertasMMKNActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertasKandungan permohonanKertasKandungan1;
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan permohonanKertasKandungan5;
    private PermohonanKertasKandungan permohonanKertasKandungan6;
    private String tajuk;
    private String bil;
    private String kandungan;
    private String idKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/drafKertasMMKN.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/drafKertasMMKN.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (idPermohonan != null) {
            permohonanKertas = enforcementService.findMMKNByKodIdPermohonan(idPermohonan);

            if (permohonanKertas != null) {
                senaraiKertasKandungan = enforcementService.findByKertas(permohonanKertas.getIdKertas());

                permohonanKertasKandungan1 = enforcementService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
                permohonanKertasKandungan3 = enforcementService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 3);
                permohonanKertasKandungan4 = enforcementService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 4);
                permohonanKertasKandungan5 = enforcementService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 5);
                permohonanKertasKandungan6 = enforcementService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 6);
            }

        }
        logger.debug(getContext().getRequest().getParameter("rowCount") + " asasasaasd");

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
        tajuk = getContext().getRequest().getParameter("permohonanKertas.tajuk");
        permohonanKertas.setTajuk(tajuk);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);


        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));

        for (int i = 1; i <= kira; i++) {
            if (senaraiKertasKandungan != null) {
                if (i <= senaraiKertasKandungan.size()) {
                    Long id = senaraiKertasKandungan.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan = enforcementService.findkandunganByIdKandungan(id);
                    }
                }
            } else {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
            }

            permohonanKertasKandungan.setKertas(permohonanKertas);
            permohonanKertasKandungan.setBil(2);
            kandungan = getContext().getRequest().getParameter("kandungan" + i);
            permohonanKertasKandungan.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan.setCawangan(cawangan);
            permohonanKertasKandungan.setSubtajuk("2." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
        }
        if (getContext().getRequest().getParameter("permohonanKertasKandungan3.kandungan")!=null) {
            if (permohonanKertasKandungan3 == null) {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(3);
            kandungan = getContext().getRequest().getParameter("permohonanKertasKandungan3.kandungan");
            permohonanKertasKandungan3.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan3.setCawangan(cawangan);
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan3.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
        }
        if (getContext().getRequest().getParameter("permohonanKertasKandungan1.kandungan") != null) {
            if (permohonanKertasKandungan1 == null) {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(1);
            kandungan = getContext().getRequest().getParameter("permohonanKertasKandungan1.kandungan");
            permohonanKertasKandungan1.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan1.setCawangan(cawangan);
            // permohonanKertasKandungan.setSubtajuk("Tujuan");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan1);
        }
        if (getContext().getRequest().getParameter("permohonanKertasKandungan4.kandungan") != null) {
            if (permohonanKertasKandungan4 == null) {
                permohonanKertasKandungan4 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan4.setKertas(permohonanKertas);
            permohonanKertasKandungan4.setBil(4);
            kandungan = getContext().getRequest().getParameter("permohonanKertasKandungan4.kandungan");
            permohonanKertasKandungan4.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan4.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan4.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
        }
         if (getContext().getRequest().getParameter("permohonanKertasKandungan5.kandungan") != null) {
            if (permohonanKertasKandungan5 == null) {
                permohonanKertasKandungan5 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan5.setKertas(permohonanKertas);
            permohonanKertasKandungan5.setBil(5);
            kandungan = getContext().getRequest().getParameter("permohonanKertasKandungan5.kandungan");
            permohonanKertasKandungan5.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan5.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan5.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
        }
         if (getContext().getRequest().getParameter("permohonanKertasKandungan6.kandungan") != null) {
            if (permohonanKertasKandungan6 == null) {
                permohonanKertasKandungan6 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan6.setKertas(permohonanKertas);
            permohonanKertasKandungan6.setBil(6);
            kandungan = getContext().getRequest().getParameter("permohonanKertasKandungan6.kandungan");
            permohonanKertasKandungan6.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan6.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan6.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
        }
        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        //return new JSP("penguatkuasaan/drafKertasMMKN.jsp").addParameter("tab", "true");
        return new RedirectResolution(KertasMMKNActionBean.class, "locate");

    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idKandungan = getContext().getRequest().getParameter("idKandungan");

        if(idKandungan !=null)
        {
        permohonanKertasKandungan = enforcementService.findkandunganByIdKandungan(Long.parseLong(idKandungan));

        if (permohonanKertasKandungan != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertasKandungan.setInfoAudit(ia);
            permohonanKertasKandungan.setCawangan(cawangan);
            //  aduanPemantauan.setDipantauOleh(peng);
            enforcementService.deleteKertasKandungan(permohonanKertasKandungan);
        }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(KertasMMKNActionBean.class, "locate");
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

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
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

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getBil() {
        return bil;
    }

    public void setBil(String bil) {
        this.bil = bil;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan() {
        return senaraiKertasKandungan;
    }

    public void setSenaraiKertasKandungan(List<PermohonanKertasKandungan> senaraiKertasKandungan) {
        this.senaraiKertasKandungan = senaraiKertasKandungan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan1() {
        return permohonanKertasKandungan1;
    }

    public void setPermohonanKertasKandungan1(PermohonanKertasKandungan permohonanKertasKandungan1) {
        this.permohonanKertasKandungan1 = permohonanKertasKandungan1;
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

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan5() {
        return permohonanKertasKandungan5;
    }

    public void setPermohonanKertasKandungan5(PermohonanKertasKandungan permohonanKertasKandungan5) {
        this.permohonanKertasKandungan5 = permohonanKertasKandungan5;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan6() {
        return permohonanKertasKandungan6;
    }

    public void setPermohonanKertasKandungan6(PermohonanKertasKandungan permohonanKertasKandungan6) {
        this.permohonanKertasKandungan6 = permohonanKertasKandungan6;
    }

}
