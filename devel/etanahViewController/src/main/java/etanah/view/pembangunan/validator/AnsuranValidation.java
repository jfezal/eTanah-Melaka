/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;
import com.google.inject.Provider;

import etanah.Configuration;
import etanah.kodHasilConfig;
import etanah.dao.AkaunDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodAkaunDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodStatusAkaunDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermitSahLakuDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.model.Akaun;
import etanah.model.DokumenKewangan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodStatusAkaun;
import etanah.model.KodTuntut;
import etanah.model.Lelongan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Transaksi;
import etanah.sequence.GeneratorNoAkaun;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author afham
 */
public class AnsuranValidation implements StageListener {
     @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO ;
    @Inject
    KodTransaksiDAO kodTransaksiDAO ;
    @Inject
    PermitSahLakuDAO permitSahLakuDAO ;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    PermohonanTuntutanBayarDAO mohonTuntutBayarDAO ;
    @Inject
    KodAkaunDAO kodAkaunDAO;
    @Inject
    KodStatusAkaunDAO kodStatusAkaunDAO;
    @Inject
    kodHasilConfig hasilCfg;
    @Inject
    GeneratorNoAkaun generatorNoAkaun;
    @Inject
    PelupusanService pelupusanService ;
    @Inject
    Configuration configuration;
    @Inject
    KodJenisPihakBerkepentinganDAO kodPihakDAO;
    @Inject
    Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(AnsuranValidation.class);
    private static final boolean DEBUG = LOG.isDebugEnabled();

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("beforeComplete::Start");
        Permohonan p = context.getPermohonan() ;
        String kodAkaun = "APT" ; //Ansuran Premium Tanah
        if(p.getPermohonanSebelum() != null){
        Akaun akAmanah = new Akaun();
       // List<HakmilikPermohonan> hakmilikPermohonanList = p.getSenaraiHakmilik() ;
        InfoAudit ia = new InfoAudit() ;
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(p.getInfoAudit().getDimasukOleh());
            KodCawangan caw = p.getCawangan();
            PermohonanTuntutanKos permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(p.getPermohonanSebelum().getIdPermohonan(), "DEV04");
            akAmanah.setCawangan(caw);
            akAmanah.setNoAkaun(generatorNoAkaun.generate(configuration.getProperty("kodNegeri"), caw, akAmanah));
            akAmanah.setKodAkaun(kodAkaunDAO.findById(kodAkaun));
            akAmanah.setPermohonan(p);
            akAmanah.setAmaunMatang(permohonanTuntutanKos.getAmaunTuntutan());
            akAmanah.setBilCetakPenyata(0);
            akAmanah.setBaki(permohonanTuntutanKos.getAmaunTuntutan()); // kredit
            akAmanah.setInfoAudit(ia);
            akaunDAO.save(akAmanah);
        }
        else {
            context.addMessage("Sila masukkan Permohonan Sebelum Terdahulu Supaya Mengelakkan Sistem Menjadi Ralat");
            return null ;
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

}
