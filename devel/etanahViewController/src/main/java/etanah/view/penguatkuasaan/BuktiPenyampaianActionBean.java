/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

/**
 *
 * @author farah.shafilla
 */
import java.text.ParseException;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.NotisDAO;
import etanah.model.Dokumen;
import etanah.model.Notis;
import etanah.model.InfoAudit;
import etanah.model.KodNotis;
import etanah.model.KodCaraPenghantaran;

import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.model.KodDokumen;
import etanah.model.KodStatusTerima;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Transaction;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/bukti_penyampaian")
public class BuktiPenyampaianActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(BuktiPenyampaianActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    EnforcementService enforcementService;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private Notis notis;
    private KodStatusTerima kodStatusTerima;
    private String idPermohonan;
    private String idNotis;
    private String tarikhNotis;
    private String tarikhHantar;
    private int tempohHari;
    private String jenis;
    private Dokumen dokumen;
    private KodDokumen kodDokumen;
    private KodNotis kodNotis;
    private List<Notis> senaraiNotis;
    private String noRujukan;
   //private List<Notis> listKodNotis;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/bukti_penyampaian.jsp").addParameter("tab", "true");
    }

    public Resolution popupedit() {
        idNotis = getContext().getRequest().getParameter("idNotis");
//        notis = notisDAO.findById(Long.parseLong(idNotis));
        return new JSP("penguatkuasaan/bukti_penyampaian_edit.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(BuktiPenyampaianActionBean.class, "locate");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idNotis = getContext().getRequest().getParameter("idNotis");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            senaraiNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
        }
    }

    public Resolution simpan() throws ParseException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idNotis = getContext().getRequest().getParameter("idNotis");
        jenis = getContext().getRequest().getParameter("jenis");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();


        if (idNotis == null) {
            notis = enforcementService.findByIdNotis(idPermohonan, jenis);
            if (notis == null) {
                notis = new Notis();
                dokumen = new Dokumen();
                KodDokumen dokNotis = new KodDokumen();

                if (getContext().getRequest().getParameter("jenis").equals("NC")) {
                    dokNotis.setKod("9");
                    dokumen.setKodDokumen(dokNotis);
                    dokumen.setTajuk("Kompaun");
                }
                if (getContext().getRequest().getParameter("jenis").equals("SML")) {
                    dokNotis.setKod("SML");
                    dokumen.setKodDokumen(dokNotis);
                    dokumen.setTajuk("Saman");
                }
                if (getContext().getRequest().getParameter("jenis").equals("LLH")) {
                    dokNotis.setKod("LLH");
                    dokumen.setKodDokumen(dokNotis);
                    dokumen.setTajuk("Notis Lucuthak");
                }
                if (getContext().getRequest().getParameter("jenis").equals("SJA")) {
                    dokNotis.setKod("SJA");
                    dokumen.setKodDokumen(dokNotis);
                    dokumen.setTajuk("Notis Lucuthak");
                }

                dokumen.setNoVersi("1.0");
                InfoAudit iaPermohonan = new InfoAudit();
                iaPermohonan.setTarikhMasuk(new Date());
                iaPermohonan.setDimasukOleh(peng);
                dokumen.setInfoAudit(iaPermohonan);
                dokumenDAO.saveOrUpdate(dokumen);

            } else {
                addSimpleError("Maklumat telah sedia ada.");
                return new JSP("penguatkuasaan/bukti_penyampaian.jsp").addParameter("tab", "true");
            }
        }

        if (notis == null) {
            notis = new Notis();
        }

        KodCaraPenghantaran hantar = new KodCaraPenghantaran();
        kodNotis = new KodNotis();
        kodNotis.setKod(jenis);
        notis.setKodNotis(kodNotis);



        notis.setPermohonan(permohonan);
        notis.setDokumenNotis(dokumen);

        hantar.setKod("01");
        notis.setKodCaraPenghantaran(hantar);
        notis.setDokumenNotis(dokumen);

        notis.setTarikhNotis(new Date());
        InfoAudit iaP = new InfoAudit();
        iaP.setTarikhMasuk(new Date());
        iaP.setDimasukOleh(peng);
        notis.setInfoAudit(iaP);
        notisDAO.saveOrUpdate(notis);

        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(BuktiPenyampaianActionBean.class, "locate");

    }

    public Resolution simpanSingle() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idNotis = getContext().getRequest().getParameter("idNotis");
        noRujukan = getContext().getRequest().getParameter("notis.noRujukan");
        String stat = getContext().getRequest().getParameter("kodStatusTerima.kod");
        notis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis));

        notis.setPermohonan(permohonan);

        if (getContext().getRequest().getParameter("notis.tarikhHantar").isEmpty()) {
            notis.setTarikhHantar(null);
        } else {
            tarikhHantar = getContext().getRequest().getParameter("notis.tarikhHantar");
            notis.setTarikhHantar(sdf.parse(tarikhHantar));
        }
        KodCaraPenghantaran hantar = new KodCaraPenghantaran();
        hantar.setKod("01");
        notis.setKodCaraPenghantaran(hantar);

        dokumen = new Dokumen();
        dokumen.setIdDokumen(notis.getDokumenNotis().getIdDokumen());
        notis.setDokumenNotis(dokumen);
        notis.setNoRujukan(noRujukan);
        notis.setKodNotis(notis.getKodNotis());
        kodStatusTerima = new KodStatusTerima();
        kodStatusTerima.setKod(stat);
        notis.setKodStatusTerima(kodStatusTerima);

        InfoAudit ia = notis.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            notis.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanNotisPenguatkuasaan(notis);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(BuktiPenyampaianActionBean.class, "locate");
    }
     public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idNotis = getContext().getRequest().getParameter("idNotis");
        notis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis));
        dokumen = enforcementService.findDokumenByIdDokumen(notis.getDokumenNotis().getIdDokumen());

        if (notis != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            notis.setInfoAudit(ia);
            
            //  aduanPemantauan.setDipantauOleh(peng);
            enforcementService.deleteNotis(notis);
        }
         if (dokumen != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            dokumen.setInfoAudit(ia);

            //  aduanPemantauan.setDipantauOleh(peng);
            enforcementService.deleteDokumen(dokumen);
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(BuktiPenyampaianActionBean.class, "locate");
    }


    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
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

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public int getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(int tempohHari) {
        this.tempohHari = tempohHari;
    }

    public KodNotis getKodNotis() {
        return kodNotis;
    }

    public void setKodNotis(KodNotis kodNotis) {
        this.kodNotis = kodNotis;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public List<Notis> getSenaraiNotis() {
        return senaraiNotis;
    }

    public void setSenaraiNotis(List<Notis> senaraiNotis) {
        this.senaraiNotis = senaraiNotis;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public KodStatusTerima getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(KodStatusTerima kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

}
