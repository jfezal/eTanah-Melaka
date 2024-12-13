/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import antlr.StringUtils;
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
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 *
 * @author Programmer
 */
@UrlBinding("/penguatkuasaan/surat_perintah")
public class SuratPerintahActionBean extends AbleActionBean {

    @Inject
    etanah.Configuration conf;
    @Inject
    AduanTindakanDAO aduanTindakanDAO;
    @Inject
    private EnforceService enforceService;
    private static final Logger log = Logger.getLogger(SuratPerintahActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EnforcementService enforcementService;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
//private static final Logger log = Logger.getLogger(SenaraiAduanActionBean.class);
//@ValidateNestedProperties({@Validate(field="aduanTindakan.amaun" ,required=true, expression="this != '0'" )})
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
    private AduanTindakan aduanTindakan;
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

    @DefaultHandler
    public Resolution showForm() {
        if ("05".equals(conf.getProperty("kodNegeri")) && permohonan.getKodUrusan().getKod().matches("127")) {
            if (permohonan.getKeputusan() == null) {
                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
                addSimpleError("Sila buat keputusan terlebih dahulu");
            } else if (!permohonan.getKeputusan().getKod().matches("DD")) {
                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
                addSimpleError("Surat Perintah Denda tidak perlu disediakan");
            } else {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/surat_perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("status", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_perintah_denda.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        System.out.println("----idPermohonan----" + idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            aduanTindakan = enforceService.findDendaByIdMohon(idPermohonan);
            System.out.println("----AduanTindakan------" + aduanTindakan);
            //System.out.println("----AduanTindakan------"+aduanTindakan.getPermohonan().getIdPermohonan());
            tarikhSurat = new Date();

            PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
            String[] tname = {"permohonan","kodTuntut.kod"};
            Object[] tvalue = {permohonan,"D01"};
            List<PermohonanTuntutanKos> ptkList = permohonanTuntutanKosDAO.findByEqualCriterias(tname, tvalue, null);
            if (ptkList.size()!=0){
                ptk = ptkList.get(0);
                PermohonanTuntutanBayar ptb = enforceService.findMohonTuntutBayar(ptk.getIdKos());
                    if (ptb != null) {
                        noResit = ptb.getDokumenKewangan().getIdDokumenKewangan();
                    }
            }
        }


    }

    public Resolution suratSave() {
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
            Object[] tvalue = {"D01"};
            List<PermohonanTuntutanKos> ptkList = permohonanTuntutanKosDAO.findByEqualCriterias(tname, tvalue, null);
            if (ptkList.size()!=0){
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
            kodTindakanPenguatkuasaan = new KodTindakanPenguatkuasaan();
            //aduanTindakan.getIdTindakan();
            aduanTindakan.setPermohonan(permohonan);
            aduanTindakan.setCawangan(cawangan);
            aduanTindakan.setDokumen(dokumen);
            aduanTindakan.setInfoAudit(ia);
            //DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            //Date tarikhSurat = df.parse("tarikhSurat");
            //tarikhSurat = sdf.parse(getContext().getRequest().getParameter("tarikhSurat"));
            aduanTindakan.setTarikhMula(tarikhSurat);
            BigDecimal jumlahDenda = new BigDecimal((getContext().getRequest().getParameter("aduanTindakan.amaun")));
            aduanTindakan.setAmaun(jumlahDenda);
            tempohBayaran = Integer.parseInt(getContext().getRequest().getParameter("aduanTindakan.tempohHari"));
            aduanTindakan.setTempohHari(tempohBayaran);
            tarikhAkhirBayar = sdf.parse(getContext().getRequest().getParameter("aduanTindakan.tarikhTamat"));
            aduanTindakan.setTarikhTamat(tarikhAkhirBayar);
            kodTindakanPenguatkuasaan.setKod("DEN");
            aduanTindakan.setTindakan(kodTindakanPenguatkuasaan);
            System.out.println("masuk sini3");
//            enforceService.saveAduanTindakan(aduanTindakan);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            //return new JSP("penguatkuasaan/surat_perintah_denda.jsp").addParameter("tab", "true");
             aduanTindakanDAO.saveOrUpdate(aduanTindakan);
            ptk.setCawangan(cawangan);
            ptk.setPermohonan(permohonan);
            ptk.setItem("Denda [" + permohonan.getIdPermohonan()+ "] ");
            ptk.setAmaunTuntutan(jumlahDenda);
            KodTransaksi kt = new KodTransaksi();
            kt.setKod("20027");
            ptk.setKodTransaksi(kt);
            KodTuntut ktu = new KodTuntut();
            ktu.setKod("D01");
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
        //addSimpleMessage("Maklumat telah berjaya disimpan.");
        //return new JSP("penguatkuasaan/surat_perintah_denda.jsp").addParameter("tab", "true");
        return new RedirectResolution(SuratPerintahActionBean.class, "showForm");

    }
}
