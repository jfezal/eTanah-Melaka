/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanTindakanDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.AduanTindakan;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodNegeri;
import etanah.model.KodTindakanPenguatkuasaan;
import etanah.model.KodTransaksi;
import etanah.model.KodTuntut;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Transaction;

/**
 *
 * @author Programmer
 */
@UrlBinding("/penguatkuasaan/perintah_Denda")
public class PerintahDendaTambahanActionBean extends AbleActionBean {

    @Inject
    AduanTindakanDAO aduanTindakanDAO;
    @Inject
    private EnforceService enforceService;
    private static final Logger log = Logger.getLogger(PerintahDendaTambahanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EnforcementService enforcementService;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
//private static final Logger log = Logger.getLogger(SenaraiAduanActionBean.class);
    private KodNegeri negeri;
    private KodCawangan cawangan;
    private String idPermohonan;
    private Pengguna pguna;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private Date tarikhSurat;
    private BigDecimal jumlahDenda;
    private int tempohBayaran;
    private Date tarikhAkhirBayar;
    private BigDecimal jumlahDendaTambahan;
    private AduanTindakan aduanTindakan;
    private AduanTindakan aduanTindakan2;
    private KodTindakanPenguatkuasaan kodTindakanPenguatkuasaan;
    private Dokumen dokumen;
    private String noResit;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    /**
     * @return the negeri
     */
    public KodNegeri getNegeri() {
        return negeri;
    }

    /**
     * @param negeri the negeri to set
     */
    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    /**
     * @return the cawangan
     */
    public KodCawangan getCawangan() {
        return cawangan;
    }

    /**
     * @param cawangan the cawangan to set
     */
    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    /**
     * @return the idPermohonan
     */
    public String getIdPermohonan() {
        return idPermohonan;
    }

    /**
     * @param idPermohonan the idPermohonan to set
     */
    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    /**
     * @return the pguna
     */
    public Pengguna getPguna() {
        return pguna;
    }

    /**
     * @param pguna the pguna to set
     */
    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    /**
     * @return the pengguna
     */
    public Pengguna getPengguna() {
        return pengguna;
    }

    /**
     * @param pengguna the pengguna to set
     */
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    /**
     * @return the permohonan
     */
    public Permohonan getPermohonan() {
        return permohonan;
    }

    /**
     * @param permohonan the permohonan to set
     */
    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    /**
     * @return the aduanTindakan
     */
    public AduanTindakan getAduanTindakan() {
        return aduanTindakan;
    }

    /**
     * @param aduanTindakan the aduanTindakan to set
     */
    public void setAduanTindakan(AduanTindakan aduanTindakan) {
        this.aduanTindakan = aduanTindakan;
    }

    /**
     * @return the tarikhSurat
     */
    public Date getTarikhSurat() {
        return tarikhSurat;
    }

    /**
     * @param tarikhSurat the tarikhSurat to set
     */
    public void setTarikhSurat(Date tarikhSurat) {
        this.tarikhSurat = tarikhSurat;
    }

    /**
     * @return the jumlahDenda
     */
    public BigDecimal getJumlahDenda() {
        return jumlahDenda;
    }

    /**
     * @param jumlahDenda the jumlahDenda to set
     */
    public void setJumlahDenda(BigDecimal jumlahDenda) {
        this.jumlahDenda = jumlahDenda;
    }

    /**
     * @return the tempohBayaran
     */
    public int getTempohBayaran() {
        return tempohBayaran;
    }

    /**
     * @param tempohBayaran the tempohBayaran to set
     */
    public void setTempohBayaran(int tempohBayaran) {
        this.tempohBayaran = tempohBayaran;
    }

    /**
     * @return the tarikhAkhirBayar
     */
    public Date getTarikhAkhirBayar() {
        return tarikhAkhirBayar;
    }

    /**
     * @param tarikhAkhirBayar the tarikhAkhirBayar to set
     */
    public void setTarikhAkhirBayar(Date tarikhAkhirBayar) {
        this.tarikhAkhirBayar = tarikhAkhirBayar;
    }

    /**
     * @return the jumlahDendaTambahan
     */
    public BigDecimal getJumlahDendaTambahan() {
        return jumlahDendaTambahan;
    }

    /**
     * @param jumlahDendaTambahan the jumlahDendaTambahan to set
     */
    public void setJumlahDendaTambahan(BigDecimal jumlahDendaTambahan) {
        this.jumlahDendaTambahan = jumlahDendaTambahan;
    }

    /**
     * @return the aduanTindakan2
     */
    public AduanTindakan getAduanTindakan2() {
        return aduanTindakan2;
    }

    /**
     * @param aduanTindakan2 the aduanTindakan2 to set
     */
    public void setAduanTindakan2(AduanTindakan aduanTindakan2) {
        this.aduanTindakan2 = aduanTindakan2;
    }

    @DefaultHandler
    public Resolution showForm() {
        if ("05".equals(conf.getProperty("kodNegeri")) && permohonan.getKodUrusan().getKod().matches("127")) {
            if (permohonan.getKeputusan() == null) {
                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
                addSimpleError("Sila buat keputusan terlebih dahulu");
            } else if (!permohonan.getKeputusan().getKod().matches("TE")) {
                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
                addSimpleError("Perintah Denda Tambahan tidak perlu disediakan");
            } else {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/perintah_denda_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("status", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda_tambahan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("-----idPermohonan----" + idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            aduanTindakan = enforceService.findDendaTambahanByIdMohon(idPermohonan);
            System.out.println("-----aduanTindakan---" + aduanTindakan);
            PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
            String[] tname = {"permohonan", "kodTuntut.kod"};
            Object[] tvalue = {permohonan, "D02"};
            List<PermohonanTuntutanKos> ptkList = permohonanTuntutanKosDAO.findByEqualCriterias(tname, tvalue, null);
            if (ptkList.size() != 0) {
                ptk = ptkList.get(0);
                PermohonanTuntutanBayar ptb = enforceService.findMohonTuntutBayar(ptk.getIdKos());
                if (ptb != null) {
                    noResit = ptb.getDokumenKewangan().getIdDokumenKewangan();
                }
            }
        }

        setAduanTindakan2(enforceService.findDendaByIdMohon(idPermohonan));


    }

    public Resolution perintahSave() {
        System.out.println("SuratSave");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        InfoAudit ia = new InfoAudit();
        if (dokumen == null) {
            dokumen = new Dokumen();
            ia.setTarikhMasuk(new Date());
            ia.setDimasukOleh(peng);
        } else {
            ia.setTarikhKemaskini(new Date());
            ia.setDiKemaskiniOleh(peng);
        }
        KodDokumen kodDoc = new KodDokumen();
        kodDoc.setKod("7E");
        dokumen.setKodDokumen(kodDoc);
        dokumen.setTajuk("Borang 7E(Denda)");
        dokumen.setNoVersi("1.0");
        dokumen.setInfoAudit(ia);
        dokumenDAO.saveOrUpdate(dokumen);

        PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();

        if (aduanTindakan == null) {
            aduanTindakan = new AduanTindakan();
            ptk = new PermohonanTuntutanKos();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            //aduanTindakan.setTarikhMula(new java.util.Date());
        } else {
            String[] tname = {"kodTuntut.kod"};
            Object[] tvalue = {"D02"};
            List<PermohonanTuntutanKos> ptkList = permohonanTuntutanKosDAO.findByEqualCriterias(tname, tvalue, null);
            if (ptkList.size() != 0) {
                ptk = ptkList.get(0);
            }
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            //aduanTindakan.setTarikhMula(new java.util.Date());
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {

            System.out.println("masuk sini3");
            //aduanTindakan = new AduanTindakan();
            kodTindakanPenguatkuasaan = new KodTindakanPenguatkuasaan();
            //aduanTindakan.getIdTindakan();
            aduanTindakan.setPermohonan(permohonan);
            aduanTindakan.setCawangan(cawangan);
            aduanTindakan.setDokumen(dokumen);
            aduanTindakan.setInfoAudit(ia);
            aduanTindakan.setTarikhMula(getAduanTindakan2().getTarikhMula());
            BigDecimal jumlahDendaTambahan = new BigDecimal((getContext().getRequest().getParameter("aduanTindakan.amaun")));
            //aduanTindakan.setAmaun(jumlahDenda);
            aduanTindakan.setAmaun(jumlahDendaTambahan);
            aduanTindakan.setTempohHari(getAduanTindakan2().getTempohHari());
            aduanTindakan.setTarikhTamat(getAduanTindakan2().getTarikhTamat());

            kodTindakanPenguatkuasaan.setKod("DET");
            aduanTindakan.setTindakan(kodTindakanPenguatkuasaan);
//            enforceService.saveAduanTindakan(aduanTindakan);

            aduanTindakanDAO.saveOrUpdate(aduanTindakan);
            ptk.setCawangan(cawangan);
            ptk.setPermohonan(permohonan);
            ptk.setItem("Denda Tambahan [" + permohonan.getIdPermohonan() + "] ");
            ptk.setAmaunTuntutan(jumlahDendaTambahan);
            KodTransaksi kt = new KodTransaksi();
            kt.setKod("20027");
            ptk.setKodTransaksi(kt);
            KodTuntut ktu = new KodTuntut();
            ktu.setKod("D02");
            ptk.setKodTuntut(ktu);
            ptk.setInfoAudit(ia);
            permohonanTuntutanKosDAO.save(ptk);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError("Not Saved.");
            log.error(t);
        }
        //log.debug("aaa");
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(PerintahDendaTambahanActionBean.class, "showForm");
        //return new JSP("penguatkuasaan/perintah_denda_tambahan.jsp").addParameter("tab", "true");

    }
}
