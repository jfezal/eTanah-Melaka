/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodTransaksi;
import etanah.model.KodTuntut;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pihak;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/bayaran_jaminan")
public class BayaranJaminanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanPihakDAO mohonPihakDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private long idKos;
    private KodCawangan cawangan;
    private Pengguna pengguna;
    private String kodCaw;
    private Permohonan permohonan;
    private KodTransaksi kodTransaksi;
    private String kod;
    private PermohonanPihak mohonPihak;
    private KodTuntut kodTuntut;
    private String item;
    private BigDecimal amaunTuntutan;
    private String idPermohonan;
    private String idOrangKenaSyak;
    private String nama;
    private List<AduanOrangKenaSyak> aduanOrangKenaSyak;
    private AduanOrangKenaSyak oks;
    private Pemohon pmohon;
    private Pihak pk;
    private Long idPihak;
    private String orangKenaSyak;
    IWorkflowContext ctxW = null;
    private static final Logger LOG = Logger.getLogger(BayaranJaminanActionBean.class);
    private String tuntutKod = "J01";
    private String pembayar;

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("/penguatkuasaan/bayaran_jaminan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        try {
            ctxW = WorkFlowService.authenticate(pengguna.getIdPengguna());
        } catch (Exception e) {
            LOG.error("error ::" + e.getMessage());
        }
        System.out.println("kod tuntut : " + tuntutKod);
        System.out.println("id mohon : " + idPermohonan);
        PermohonanTuntutanKos mohonTuntutKos = enforceService.findByIdMohon(idPermohonan, tuntutKod);
        Pemohon p = enforceService.findById(idPermohonan);

        if (mohonTuntutKos != null) {
            amaunTuntutan = mohonTuntutKos.getAmaunTuntutan();
            if (p != null) {
                pembayar = p.getPihak().getNama();
            }

        }

        aduanOrangKenaSyak = enforceService.findByID(idPermohonan);
        LOG.info("list oks : " + aduanOrangKenaSyak.size());
        permohonan = permohonanDAO.findById(idPermohonan);

    }

    public Resolution simpan() {

        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        int year = now.getYear() + 1900;
        InfoAudit ia = new InfoAudit();

        PermohonanTuntutanKos ptk = enforceService.findByIdMohon(idPermohonan, tuntutKod);

        System.out.println("pembayar : " + pembayar);

        if (ptk == null) {
            ptk = new PermohonanTuntutanKos();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
        } else {
            ia = ptk.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(now);
        }

        ptk.setPermohonan(permohonan);
        ptk.setCawangan(caw);
        ptk.setAmaunTuntutan(amaunTuntutan);
        ptk.setItem("Bayaran Jaminan");
        ptk.setInfoAudit(ia);
        KodTransaksi kod2 = new KodTransaksi();
        kod2.setKod("79501");
        ptk.setKodTransaksi(kod2);
        KodTuntut kod3 = new KodTuntut();
        kod3.setKod("J01");
        ptk.setKodTuntut(kod3);

        pmohon = enforceService.findById(idPermohonan);
        if (pmohon != null) {
            idPihak = pmohon.getPihak().getIdPihak();
            if (idPihak != null) {
                pk = pihakDAO.findById(idPihak);
                ia = pk.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(now);
                pk.setInfoAudit(ia);
                pk.setNama(pembayar);
                pihakDAO.saveOrUpdate(pk);

                mohonPihak = enforceService.findByIdPihak(idPermohonan, idPihak);
                KodJenisPihakBerkepentingan kodPihak = kodJenisPihakBerkepentinganDAO.findById("PMB");//sementara
                ia = mohonPihak.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(now);
                mohonPihak.setInfoAudit(ia);
                mohonPihak.setPermohonan(permohonan);
                mohonPihak.setPihak(pk);
                mohonPihak.setJenis(kodPihak);
                mohonPihak.setCawangan(caw);
                mohonPihakDAO.saveOrUpdate(mohonPihak);

            }
        } else {
            pk = new Pihak();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
            pk.setInfoAudit(ia);
            pk.setNama(pembayar);
            pihakDAO.save(pk);

            KodJenisPihakBerkepentingan kodPihak = kodJenisPihakBerkepentinganDAO.findById("PMB");
            mohonPihak = new PermohonanPihak();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
            mohonPihak.setInfoAudit(ia);
            mohonPihak.setPermohonan(permohonan);
            mohonPihak.setPihak(pk);
            mohonPihak.setJenis(kodPihak);
            mohonPihak.setCawangan(caw);
            mohonPihakDAO.save(mohonPihak);

        }

        if (pmohon == null) {
            pmohon = new Pemohon();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
        } else {
            ia = pmohon.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(now);
        }

        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pk);
        pmohon.setCawangan(caw);
        pmohon.setInfoAudit(ia);
        pemohonDAO.saveOrUpdate(pmohon);

        enforceService.simpanBayaran(ptk);

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new JSP("/penguatkuasaan/bayaran_jaminan.jsp").addParameter("tab", "true");
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public long getIdKos() {
        return idKos;
    }

    public void setIdKos(long idKos) {
        this.idKos = idKos;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
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

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public PermohonanPihak getMohonPihak() {
        return mohonPihak;
    }

    public void setMohonPihak(PermohonanPihak mohonPihak) {
        this.mohonPihak = mohonPihak;
    }

    public BigDecimal getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(BigDecimal amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod2(String kod) {
        this.kod = kod;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public KodTuntut getKodTuntut() {
        return kodTuntut;
    }

    public void setKodTuntut(KodTuntut kodTuntut) {
        this.kodTuntut = kodTuntut;
    }

    public String getIdOrangKenaSyak() {
        return idOrangKenaSyak;
    }

    public void setIdOrangKenaSyak(String idOrangKenaSyak) {
        this.idOrangKenaSyak = idOrangKenaSyak;
    }

    public List<AduanOrangKenaSyak> getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(List<AduanOrangKenaSyak> aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public AduanOrangKenaSyak getOks() {
        return oks;
    }

    public void setOks(AduanOrangKenaSyak oks) {
        this.oks = oks;
    }

    public Pemohon getPmohon() {
        return pmohon;
    }

    public void setPmohon(Pemohon pmohon) {
        this.pmohon = pmohon;
    }

    public Pihak getPk() {
        return pk;
    }

    public void setPk(Pihak pk) {
        this.pk = pk;
    }

    public Long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(Long idPihak) {
        this.idPihak = idPihak;
    }

    public String getOrangKenaSyak() {
        return orangKenaSyak;
    }

    public void setOrangKenaSyak(String orangKenaSyak) {
        this.orangKenaSyak = orangKenaSyak;
    }

    public String getPembayar() {
        return pembayar;
    }

    public void setPembayar(String pembayar) {
        this.pembayar = pembayar;
    }
}
