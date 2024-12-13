/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.AduanLokasi;
import etanah.model.BarangRampasan;


import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.util.Date;
import etanah.dao.DokumenDAO;
import etanah.Configuration;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodNegeri;
import etanah.model.Dokumen;
import etanah.model.Notis;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.EnforceService;

import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import java.text.SimpleDateFormat;
import etanah.service.common.EnforcementService;
import java.util.Properties;
import org.hibernate.Session;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/lawatanTapakHLL")
public class LawatanTapakHLLActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(LawatanTapakHLLActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private AduanLokasi aduanLokasi;
    private KodNegeri kodNegeri;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan permohonanKertasKandungan;
//    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanRujukanLuar permohonanRujukanLuar1;
    private Notis notis;
    private List<BarangRampasan> senaraiBarangRampasan;
    private List<PermohonanRujukanLuar> senaraiMahkamah;
    private String idKertas;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
     public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/laporan_lawatan_tapak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/laporan_lawatan_tapak.jsp").addParameter("tab", "true");
    }

//    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new JSP("penguatkuasaan/maklumat_kertas_siasatan.jsp").addParameter("tab", "true");
//    }
//
//    public Resolution showForm2() {
//        getContext().getRequest().setAttribute("view", Boolean.TRUE);
//        return new JSP("penguatkuasaan/maklumat_kertas_siasatan.jsp").addParameter("tab", "true");
//    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        ctx = (etanahActionBeanContext) getContext();
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String kodN = ctx.getKodNegeri();
        kodNegeri = new KodNegeri();
        kodNegeri.setKod(kodN);
        kodNegeri = kodNegeriDAO.findById(kodN);

        if (idPermohonan != null) {
//            Properties props = new Properties();
//        try {
//
//            props.load(LawatanTapakHLLPActionBean.class.getResourceAsStream(Configuration.CONF_FILE));
//            String kod = props.getProperty("kodNegeri");
////        kodNegeri = kodNegeriDAO.findById(kod);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }


            operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
            permohonanKertas = enforcementService.findByKodIdPermohonan(idPermohonan);
            aduanLokasi = enforceService.findAduanLokasiByIdPermohonan(idPermohonan);
//            permohonanRujukanLuar = enforcementService.findMahkamahByIdMohon(idPermohonan);
            senaraiMahkamah = enforcementService.findMahkamahByIDPermohonan(idPermohonan);
            permohonanRujukanLuar1 = enforceService.findLaporanPolisByIdpermohonan(idPermohonan);
            notis = enforcementService.findSamanByIdmohon(idPermohonan);
            OperasiPenguatkuasaan op = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);

            if (op != null) {
                senaraiBarangRampasan = enforceService.findByIDOperasi(op.getIdOperasi());
            }
            if (permohonanKertas != null) {
                permohonanKertasKandungan = enforcementService.findByKodIdKertas(permohonanKertas.getIdKertas());

            }
        }
    }

    public Resolution simpan() {

        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        System.out.println("permohonan :" + permohonan.getIdPermohonan());
        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();

        }
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setTajuk("Laporan Lawatan Tapak");

        permohonanKertasKandungan.setKertas(permohonanKertas);
        permohonanKertasKandungan.setCawangan(cawangan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonanKertas.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanKertas.setInfoAudit(ia);

        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        InfoAudit ia2 = new InfoAudit();
        ia2.setDimasukOleh(peng);
        ia2.setTarikhMasuk(new java.util.Date());

        permohonanKertasKandungan.setInfoAudit(ia2);

        enforcementService.simpanKertas(permohonanKertas, permohonanKertasKandungan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_kertas_siasatan.jsp").addParameter("tab", "true");

    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        idKertas = getContext().getRequest().getParameter("idKertas");
        permohonanKertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
        permohonanKertasKandungan = enforcementService.findByKodIdKertas(permohonanKertas.getIdKertas());

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            permohonanKertas.setDokumen(null);
            InfoAudit ia = permohonanKertas.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertas.setInfoAudit(ia);
            enforcementService.simpanKertas(permohonanKertas, permohonanKertasKandungan);
//            enforceService.simpanRujukan(permohonanKertas);

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
        return new RedirectResolution(LawatanTapakHLLActionBean.class, "locate");
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

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

//    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
//        return permohonanRujukanLuar;
//    }
//
//    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
//        this.permohonanRujukanLuar = permohonanRujukanLuar;
//    }
    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar1() {
        return permohonanRujukanLuar1;
    }

    public void setPermohonanRujukanLuar1(PermohonanRujukanLuar permohonanRujukanLuar1) {
        this.permohonanRujukanLuar1 = permohonanRujukanLuar1;
    }

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<PermohonanRujukanLuar> getSenaraiMahkamah() {
        return senaraiMahkamah;
    }

    public void setSenaraiMahkamah(List<PermohonanRujukanLuar> senaraiMahkamah) {
        this.senaraiMahkamah = senaraiMahkamah;
    }

    public Resolution refreshpage() {
        return new RedirectResolution(LawatanTapakHLLActionBean.class, "locate");
    }


}

