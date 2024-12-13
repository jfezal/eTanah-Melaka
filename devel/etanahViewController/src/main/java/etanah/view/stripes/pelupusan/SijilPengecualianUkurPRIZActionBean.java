/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.KodPeranan;
import etanah.model.PermohonanLaporanUlasan;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.KodCawangan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanUkur;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import java.util.List;

/**
 *
 * @author ctzainal
 */
@UrlBinding("/pelupusan/sijilPengecualianUkurPRIZ")
public class SijilPengecualianUkurPRIZActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(SijilPengecualianUkurPRIZActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    PelupusanService disService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private PermohonanUkur permohonanUkur;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private String tarikhPerakuan;
    private String ulasan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> hakmilikList;
    private HakmilikPermohonan hp;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/sijil_pengecualian_ukur_priz.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pelupusan/sijil_pengecualian_ukur_view_priz.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("rehydrate start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        try {
            hakmilikList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
            if (hakmilikList.size() != 0) {
                System.out.println(" hakmilikList.size() : " + hakmilikList.size());
                hp = hakmilikList.get(0);
                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                permohonanUkur = disService.findPermohonanUkurByIdPermohonan(idPermohonan);
                permohonanLaporanUlasan = disService.findPermohonanUkurByIdPermohonanTujuan(idPermohonan);

                if (permohonanUkur != null) {
                    tarikhPerakuan = sdf.format(permohonanUkur.getTarikhPerakuan());
                }

                if (permohonanLaporanUlasan != null) {
                    ulasan = permohonanLaporanUlasan.getUlasan();

                }
            }

        } catch (Exception ex) {
            logger.error(ex);
            ex.printStackTrace();
        }

        logger.info("rehydrate finish");
    }

    public Resolution simpan() {
//        logger.info("simpan start");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            InfoAudit infoAudit = new InfoAudit();

            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String kodPeranan = pguna.getPerananUtama().getKod();


            permohonanLaporanUlasan = disService.findPermohonanUkurByIdPermohonanTujuan(idPermohonan);

            if (permohonanLaporanUlasan != null) {
                logger.info("update tarikh perakuan in mohon ukur start");
                infoAudit = permohonanLaporanUlasan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pguna);
            }

            permohonanLaporanUlasan.setUlasan(ulasan);
            KodDokumen kodDokumen = kodDokumenDAO.findById("SPU");
            permohonanLaporanUlasan.setKodDokumen(kodDokumen);
            permohonanLaporanUlasan.setJenisUlasan("SijilPU");
            permohonanLaporanUlasan.setCawangan(pguna.getKodCawangan());
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            KodPeranan kp = kodPerananDAO.findById(kodPeranan);
            permohonanLaporanUlasan.setKodPeranan(kp);

            permohonanLaporanUlasanDAO.saveOrUpdate(permohonanLaporanUlasan);
            addSimpleMessage("Maklumat telah berjaya disimpan.");

        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("pelupusan/sijil_pengecualian_ukur_priz.jsp").addParameter("tab", "true");
        }
        logger.info("simpan finish");
        tx.commit();
        return new JSP("pelupusan/sijil_pengecualian_ukur_priz.jsp").addParameter("tab", "true");
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

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }
}
