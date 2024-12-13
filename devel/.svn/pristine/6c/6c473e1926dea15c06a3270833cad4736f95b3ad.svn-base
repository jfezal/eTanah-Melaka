/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.KompaunDAO;
import etanah.model.InfoAudit;
import etanah.model.Kompaun;
import etanah.service.EnforceService;

import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PihakDAO;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodTransaksi;
import etanah.model.KodTuntut;
import etanah.model.Pemohon;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pihak;
import java.math.BigDecimal;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_bayaran_jaminan")
public class MaklumatBayaranJaminanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatBayaranJaminanActionBean.class);
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    PermohonanPihakDAO mohonPihakDAO;
    private Pengguna pengguna;
    private String idPermohonan;
    private Permohonan permohonan;
    private Kompaun bayaranJaminan;
    private String tempohHari;
    private PermohonanTuntutanKos mohonTuntutKos;
    private String jumlahBayaran;
    private String pembayar;
    private Pemohon pmohon;
    private Long idPihak;
    private Pihak pk;
     private PermohonanPihak mohonPihak;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/penguatkuasaan/bayaran_jaminan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            bayaranJaminan = enforceService.findJaminanByKodStatusTerima(idPermohonan, "JM");
            if (bayaranJaminan != null) {
                pembayar = bayaranJaminan.getIsuKepada();
                jumlahBayaran = bayaranJaminan.getAmaun().toString();
            }
            mohonTuntutKos = enforceService.findByIdMohon(idPermohonan, "J01");
            logger.info("------------Bayaran Jaminan-------------- : " + bayaranJaminan);
            logger.info("------------Permohonan Tuntut Kos-------------- : " + mohonTuntutKos);
        }

    }

    public Resolution simpanBayaranJaminan() {
        logger.info("------------simpanBayaranJaminan--------------");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        
        InfoAudit ia = new InfoAudit();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();

        if (conf.getProperty("kodNegeri").equalsIgnoreCase("05")) {
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
        }

        try {
            if (mohonTuntutKos == null) {
                System.out.println("new ptk");
                mohonTuntutKos = new PermohonanTuntutanKos();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
            } else {
                System.out.println("old ptk");
                ia = mohonTuntutKos.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
            }

            BigDecimal total = new BigDecimal(jumlahBayaran);
            System.out.println("total : " + total);
            mohonTuntutKos.setCawangan(pengguna.getKodCawangan());
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setItem("BAYARAN JAMINAN");
            mohonTuntutKos.setAmaunTuntutan(total);
            KodTransaksi kt = new KodTransaksi();
            kt.setKod("79501");
            mohonTuntutKos.setKodTransaksi(kt);
            KodTuntut ktu = new KodTuntut();
            ktu.setKod("J01");
            mohonTuntutKos.setKodTuntut(ktu);
            mohonTuntutKos.setInfoAudit(ia);
            permohonanTuntutanKosDAO.saveOrUpdate(mohonTuntutKos);

            if (bayaranJaminan == null) {
                System.out.println("new bayaranJaminan");
                bayaranJaminan = new Kompaun();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
            } else {
                System.out.println("old bayaranJaminan");
                ia = bayaranJaminan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
            }

            /* insert into table Kompaun*/
            bayaranJaminan.setPermohonan(permohonan);
            bayaranJaminan.setCawangan(pengguna.getKodCawangan());
            bayaranJaminan.setAmaun(total);
            bayaranJaminan.setNoRujukan("JM"); //JM = Jaminan
            bayaranJaminan.setIsuKepada(pembayar);
//            bayaranJaminan.setTempohHari(Integer.parseInt(tempohHari));
            bayaranJaminan.setStatusTerima(kodStatusTerimaDAO.findById("JM")); //JM = Jaminan
            bayaranJaminan.setPermohonanTuntutanKos(mohonTuntutKos);
            bayaranJaminan.setInfoAudit(ia);
            kompaunDAO.saveOrUpdate(bayaranJaminan);

            //update column no rujukan by set no rujukan = id kompaun

            bayaranJaminan.setNoRujukan(String.valueOf(bayaranJaminan.getIdKompaun()));
            kompaunDAO.saveOrUpdate(bayaranJaminan);

            //update column item (mohon_tuntut_kos)
            mohonTuntutKos.setItem("BAYARAN JAMINAN [" + bayaranJaminan.getIdKompaun() + "]");
            permohonanTuntutanKosDAO.saveOrUpdate(mohonTuntutKos);

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
        return new JSP("penguatkuasaan/bayaran_jaminan.jsp").addParameter("tab", "true");
    }

    public Kompaun getBayaranJaminan() {
        return bayaranJaminan;
    }

    public void setBayaranJaminan(Kompaun bayaranJaminan) {
        this.bayaranJaminan = bayaranJaminan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getJumlahBayaran() {
        return jumlahBayaran;
    }

    public void setJumlahBayaran(String jumlahBayaran) {
        this.jumlahBayaran = jumlahBayaran;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public String getPembayar() {
        return pembayar;
    }

    public void setPembayar(String pembayar) {
        this.pembayar = pembayar;
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

    public String getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(String tempohHari) {
        this.tempohHari = tempohHari;
    }
}
