/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.permit;

/**
 *
 * @author Hazwan
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.PihakDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.Permit;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pengguna;
import etanah.model.KodCawangan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.KodPemilikan;
import etanah.model.KodTuntut;
import etanah.model.KodUOM;
import etanah.model.KodUnit;
import etanah.model.Pemohon;
import etanah.model.PermitItem;
import etanah.model.PermitItemKuantiti;
import etanah.model.PermitLPSRekod;
import etanah.model.PermitSahLaku;
import etanah.model.PermitStrukturDiluluskan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import etanah.model.gis.PelanGIS;
import etanah.model.gis.PelanGISPK;
import etanah.view.etanahActionBeanContext;
import etanah.service.PembangunanService;
import etanah.service.common.PermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.util.FileUtil;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.LinkedList;

import java.util.List;
import java.util.Locale;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jpedal.color.DeviceCMYKColorSpace;

@UrlBinding("/cancelPermitEntry")
public class CancelPermitActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(CancelPermitActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    @Inject
    PermitDAO permitDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String radio1;
    private String kodPermit;
    private String noPermit;
    private String noFail;
    private String jenisBorang;
    private InfoAudit infoAudit;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/permit/batalPermit.jsp");
    }

    public Resolution hapusPermit() throws Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        noFail = getContext().getRequest().getParameter("noFailRuj");
        jenisBorang = getContext().getRequest().getParameter("radio");
        kodPermit = getContext().getRequest().getParameter("kodPermit");
        noPermit = getContext().getRequest().getParameter("noPermit");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + noFail);

        infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        // *TABLE INVOLVED
        // MOHON, MOHON_HAKMILIK, MOHON_TUNTUT_KOS, MOHON_TUNTUT_BAYAR, PERMIT, PERMIT_SAH_LAKU, PERMIT_STRUKTUR_LUAS,
        // PERMIT_LPS_REKOD, PERMIT_ITEM, PERMIT_KNTT_ITEM, GIS PELAN

        if (noFail != null && noPermit != null) {
            System.out.println("--DELETING START--");

            PelanGIS pgNoPermit = pembangunanServ.findPelanByIdPermohonanAndNoPermit(noFail, noPermit);
            if (pgNoPermit != null) {
                pembangunanServ.deletePelan(pgNoPermit);
            } else {
                PelanGIS pg = pembangunanServ.findPelanByIdPermohonan(noFail);
                if (pg != null) {
                    pembangunanServ.deletePelan(pg);
                }
            }

            Permit p = pembangunanServ.findPerrmitByIdMohonAndNoPermit(noFail, noPermit);
            if (p != null) {

                PermitItem pi = pembangunanServ.findPermitItemByIdPermit(p.getIdPermit());
                if (pi != null) {
                    System.out.println("--DELETING PERMIT ITEM START--");
                    PermitItemKuantiti pik = pembangunanServ.findPermitItemKNTTByIdPermitItem(pi.getIdPermitItem());
                    if (pik != null) {
                        pembangunanServ.deletePermitItemKuantiti(pik);
                    }
                    pembangunanServ.deletePermitItem(pi);
                    System.out.println("--FINISHED PERMIT ITEM START--");
                }

                List<PermitLPSRekod> plr = pembangunanServ.findLPSRekodForOverall(noFail, noPermit);
                for (PermitLPSRekod plrExist : plr) {
                    pembangunanServ.deletePermitLPSRekodItem(plrExist);
                }

                PermitStrukturDiluluskan psdl = pembangunanServ.findPermitStrukturLulusByIdPermit(p.getIdPermit());
                if (psdl != null) {
                    pembangunanServ.deletePermitStrukturLulus(psdl);
                }

                PermitSahLaku psl = pembangunanServ.findPermitSahLakuByIdKosPermit(p.getIdPermit());
                if (psl != null) {
                    pembangunanServ.deletePermitSahLaku(psl);
                }

                pembangunanServ.deletePermit(p);
                System.out.println("--PROGRESS(FINISH DELETE ALL PERMIT)--");

                PermohonanTuntutanKos ptk = pembangunanServ.findMohonTuntutKosAndNoLesen(noFail, noPermit);
                if (ptk != null) {
                    PermohonanTuntutanBayar ptb = pembangunanServ.findTuntuBayarByPremium(ptk.getIdKos());
                    if (ptb != null) {
                        pembangunanServ.deletePermohonanTuntutanBayar(ptb);
                        pembangunanServ.deletePermohonanTuntutanKos(ptk);
                        System.out.println("--PROGRESS(FINISH DELETE ALL MOHON TUNTUT)--");

                        HakmilikPermohonan hp = pembangunanServ.findByIdPermohonanAndPermitForCarianPermit(noFail, noPermit);
                        if (hp != null) {
                            pembangunanServ.deleteHp(hp);

                            List<HakmilikPermohonan> pe = pembangunanServ.findHakmilikPermohonanByIdPermohonan(noFail);
                            if (pe.size() > 0) {
                                if (pe.size() > 1) {
                                    System.out.println("--PROGRESS(DO NOTHING)--");
                                } else {
                                    Permohonan mohon = pembangunanServ.findPermohonanByIdPermohonan(noFail);
                                    pembangunanServ.deletePermohonan(mohon);
                                    System.out.println("--PROGRESS(DELETE MOHON)--");
                                }
                            }
                            System.out.println("--PROGRESS(FINISH DELETE HM MOHON)--");
                        }
                    }
                }

            }
            System.out.println("--DELETING END !(=_=)! --");
        }

        addSimpleMessage("Maklumat Permohonan " + noFail + " Telah Berjaya DiHapuskan");
        return new ForwardResolution("/WEB-INF/jsp/permit/batalPermit.jsp");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getRadio1() {
        return radio1;
    }

    public void setRadio1(String radio1) {
        this.radio1 = radio1;
    }

    public String getKodPermit() {
        return kodPermit;
    }

    public void setKodPermit(String kodPermit) {
        this.kodPermit = kodPermit;
    }

    public String getNoPermit() {
        return noPermit;
    }

    public void setNoPermit(String noPermit) {
        this.noPermit = noPermit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getJenisBorang() {
        return jenisBorang;
    }

    public void setJenisBorang(String jenisBorang) {
        this.jenisBorang = jenisBorang;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }
}
