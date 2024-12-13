/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import java.text.ParseException;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.KompaunDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.Kompaun;
import etanah.model.DokumenKewangan;
import etanah.model.KodDokumen;
import etanah.service.EnforceService;

import etanah.model.Pengguna;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.google.inject.Inject;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.KodTransaksi;
import etanah.model.KodTuntut;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.sequence.GeneratorNoResit;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/tawaran_kompaun")
public class TawaranKompaunActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(TawaranKompaunActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    GeneratorNoResit noResitGenerator;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private Kompaun kompaun;
    private KodCawangan cawangan;
    private KodJenisPengenalan jenisPengenalan;
    private AduanOrangKenaSyak orangKenaSyak;
    private DokumenKewangan resit;
    private KodDokumen kodDokumen;
    private String idKompaun;
    private String noRujukan;
    private String amaun;
    private String isuKepada;
    private String tempohHari;
    private String noPengenalan;
    private String nama;
    private int saiz;
    private String idOrangKenaSyak;
    private List<Kompaun> senaraiKompaun;
    private List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak;
    private List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    private Date tarikhBayar;
    private PermohonanTuntutanBayar ptb;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {

            for (int i = 0; i < senaraiKompaun.size(); i++) {
                Kompaun kompaun = senaraiKompaun.get(i);
                if (kompaun.getResit() == null) {
                    PermohonanTuntutanBayar ptb = enforceService.findMohonTuntutBayar(kompaun.getPermohonanTuntutanKos().getIdKos());
                    if (ptb != null) {
                        kompaun.setResit(ptb.getDokumenKewangan());
                        kompaunDAO.save(kompaun);
                    }
                }
            }

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
        getContext().getRequest().setAttribute("status", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution popup() {
        return new JSP("penguatkuasaan/maklumat_tawaran_kompaun_popup.jsp").addParameter("popup", "true");
    }

    public Resolution popupedit() {
        idKompaun = getContext().getRequest().getParameter("idKompaun");
        kompaun = kompaunDAO.findById(Long.parseLong(idKompaun));
        return new JSP("penguatkuasaan/maklumat_tawaran_kompaun_edit.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        logger.debug("refreshpage2........");
        return new RedirectResolution(TawaranKompaunActionBean.class, "locate");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idKompaun = getContext().getRequest().getParameter("idKompaun");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            senaraiKompaun = enforcementService.findKompaunByID(idPermohonan);
            for (int i = 0; i < senaraiKompaun.size(); i++) {
                Kompaun k = senaraiKompaun.get(i);
                if (k.getResit() != null) {
                    ptb = enforceService.findMohonTuntutBayar(k.getPermohonanTuntutanKos().getIdKos());
                    if (ptb != null) {
                        tarikhBayar = ptb.getTarikhBayar();
                    }
                }
            }
            senaraiAduanOrangKenaSyak = enforceService.findByID(idPermohonan);
            if (senaraiKompaun.size() == senaraiAduanOrangKenaSyak.size()) {
                getContext().getRequest().setAttribute("maxSize", Boolean.TRUE);
            }

            senaraiMohonTuntutBayar = enforceService.getSenaraiPtb(idPermohonan);
            logger.info("size list senarai mohon tuntut bayar : "+senaraiMohonTuntutBayar.size());

        }
    }

    public Resolution simpanPopup() throws ParseException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idOrangKenaSyak = getContext().getRequest().getParameter("idOrangKenaSyak");
        permohonan = permohonanDAO.findById(idPermohonan);
        orangKenaSyak = enforceService.findAduanOrangKenaSyakByIdaduanOrangKenaSyak(Long.parseLong(idOrangKenaSyak));
        kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(idOrangKenaSyak));
        amaun = getContext().getRequest().getParameter("amaun");
        noRujukan = getContext().getRequest().getParameter("noRujukan");
        tempohHari = getContext().getRequest().getParameter("tempohHari");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
            if (kompaun == null) {
                cawangan = permohonan.getCawangan();
//                resit = null;

                PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
                ptk.setCawangan(cawangan);
                ptk.setPermohonan(permohonan);
                ptk.setItem("Kompaun [" + noRujukan + "] " + orangKenaSyak.getNama());
                ptk.setAmaunTuntutan(new java.math.BigDecimal(amaun));
                KodTransaksi kt = new KodTransaksi();
                kt.setKod("20027");
                ptk.setKodTransaksi(kt);
                KodTuntut ktu = new KodTuntut();
                ktu.setKod("K01");
                ptk.setKodTuntut(ktu);
                InfoAudit ip = new InfoAudit();
                ip.setDimasukOleh(peng);
                ip.setTarikhMasuk(new Date());
                ptk.setInfoAudit(ip);
                permohonanTuntutanKosDAO.save(ptk);

                kompaun = new Kompaun();
                kompaun.setPermohonan(permohonan);
                kompaun.setCawangan(cawangan);
                kompaun.setAmaun(new java.math.BigDecimal(amaun));
                kompaun.setNoRujukan(noRujukan);
                kompaun.setOrangKenaSyak(orangKenaSyak);
                kompaun.setIsuKepada(orangKenaSyak.getNama());
                kompaun.setNoPengenalan(orangKenaSyak.getNoPengenalan());
                kompaun.setTempohHari(Integer.parseInt(tempohHari));
                kompaun.setPermohonanTuntutanKos(ptk);

                InfoAudit ia = kompaun.getInfoAudit();
                if (ia == null) {
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new Date());
                    kompaun.setInfoAudit(ia);
                } else {
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new Date());
                }
                kompaunDAO.save(kompaun);
//            enforcementService.simpanKompaun(kompaun);
                addSimpleMessage("Maklumat telah berjaya disimpan.");

            } else {
                addSimpleError("Maklumat telah sedia ada.");
            }

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
        return new RedirectResolution(TawaranKompaunActionBean.class, "locate");
    }

    public Resolution deleteSingle() {
        idKompaun = getContext().getRequest().getParameter("idKompaun");
        kompaun = enforcementService.findKompaunByIdKompaun(Long.parseLong(idKompaun));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            PermohonanTuntutanKos ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());
            permohonanTuntutanKosDAO.delete(ptk);
            kompaunDAO.delete(kompaun);
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
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(TawaranKompaunActionBean.class, "locate");
    }

    public Resolution simpanSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idKompaun = getContext().getRequest().getParameter("idKompaun");
        kompaun = enforcementService.findKompaunByIdKompaun(Long.parseLong(idKompaun));
        cawangan = permohonan.getCawangan();

        if (kompaun == null) {
            kompaun = new Kompaun();
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {

            kompaun.setPermohonan(permohonan);
            kompaun.setCawangan(cawangan);
            amaun = getContext().getRequest().getParameter("kompaun.amaun");
            kompaun.setAmaun(new java.math.BigDecimal(amaun));
            noRujukan = getContext().getRequest().getParameter("kompaun.noRujukan");
            kompaun.setNoRujukan(noRujukan);
//        kompaun.setOrangKenaSyak(kompaun.getOrangKenaSyak());
            kompaun.setIsuKepada(kompaun.getIsuKepada());
            kompaun.setNoPengenalan(kompaun.getNoPengenalan());
            tempohHari = getContext().getRequest().getParameter("kompaun.tempohHari");
            kompaun.setTempohHari(Integer.parseInt(tempohHari));

            if (ia == null) {
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
                kompaun.setInfoAudit(ia);
            } else {
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            kompaunDAO.saveOrUpdate(kompaun);

            PermohonanTuntutanKos ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());
            ptk.setAmaunTuntutan(new java.math.BigDecimal(amaun));
            InfoAudit ip = ptk.getInfoAudit();
            ip.setDiKemaskiniOleh(peng);
            ip.setTarikhKemaskini(new java.util.Date());
            ptk.setInfoAudit(ip);
            permohonanTuntutanKosDAO.saveOrUpdate(ptk);

//            enforcementService.simpanKompaun(kompaun);
            tx.commit();
            addSimpleMessage("Maklumat telah berjaya dikemaskini.");

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
        return new JSP("penguatkuasaan/maklumat_tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution cariOrang() {

        noPengenalan = getContext().getRequest().getParameter("orangKenaSyak.idOrangKenaSyak");
        orangKenaSyak = enforceService.findAduanOrangKenaSyakByIdaduanOrangKenaSyak(Long.parseLong(noPengenalan));

        nama = orangKenaSyak.getNama();

        return new JSP("penguatkuasaan/maklumat_tawaran_kompaun_popup.jsp").addParameter("popup", "true");
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdKompaun() {
        return idKompaun;
    }

    public void setIdKompaun(String idKompaun) {
        this.idKompaun = idKompaun;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public Kompaun getKompaun() {
        return kompaun;
    }

    public void setKompaun(Kompaun kompaun) {
        this.kompaun = kompaun;
    }

    public AduanOrangKenaSyak getOrangKenaSyak() {
        return orangKenaSyak;
    }

    public void setOrangKenaSyak(AduanOrangKenaSyak orangKenaSyak) {
        this.orangKenaSyak = orangKenaSyak;
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

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public List<Kompaun> getSenaraiKompaun() {
        return senaraiKompaun;
    }

    public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
        this.senaraiKompaun = senaraiKompaun;
    }

    public List<AduanOrangKenaSyak> getSenaraiAduanOrangKenaSyak() {
        return senaraiAduanOrangKenaSyak;
    }

    public void setSenaraiAduanOrangKenaSyak(List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak) {
        this.senaraiAduanOrangKenaSyak = senaraiAduanOrangKenaSyak;
    }

    public String getAmaun() {
        return amaun;
    }

    public void setAmaun(String amaun) {
        this.amaun = amaun;
    }

    public String getIsuKepada() {
        return isuKepada;
    }

    public void setIsuKepada(String isuKepada) {
        this.isuKepada = isuKepada;
    }

    public String getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(String tempohHari) {
        this.tempohHari = tempohHari;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdOrangKenaSyak() {
        return idOrangKenaSyak;
    }

    public void setIdOrangKenaSyak(String idOrangKenaSyak) {
        this.idOrangKenaSyak = idOrangKenaSyak;
    }

    public DokumenKewangan getResit() {
        return resit;
    }

    public void setResit(DokumenKewangan resit) {
        this.resit = resit;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public int getSaiz() {
        return saiz;
    }

    public void setSaiz(int saiz) {
        this.saiz = saiz;
    }

    public Date getTarikhBayar() {
        return tarikhBayar;
    }

    public void setTarikhBayar(Date tarikhBayar) {
        this.tarikhBayar = tarikhBayar;
    }

    public PermohonanTuntutanBayar getPtb() {
        return ptb;
    }

    public void setPtb(PermohonanTuntutanBayar ptb) {
        this.ptb = ptb;
    }

    public List<PermohonanTuntutanBayar> getSenaraiMohonTuntutBayar() {
        return senaraiMohonTuntutBayar;
    }

    public void setSenaraiMohonTuntutBayar(List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar) {
        this.senaraiMohonTuntutBayar = senaraiMohonTuntutBayar;
    }


}
