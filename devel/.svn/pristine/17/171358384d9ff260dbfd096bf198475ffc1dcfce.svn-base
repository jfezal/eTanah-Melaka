/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanLogDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPihak;
import etanah.service.common.LelongService;
import etanah.util.FileUtil;
import etanah.util.DateUtil;
import etanah.view.etanahActionBeanContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/kemasukkan_rekod")
public class KemasukkanRekodActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    LelongService lelongService;
    @Inject
    FasaPermohonanLogDAO fasaPermohonanLogDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private Notis notis;
    private String idPermohonan;
    private List<KandunganFolder> listKandunganFolder;
    private List<Notis> listNotis;
    private List<Notis> listNotisssm;
    private List<Notis> listNotisOld;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String idNotis;
    FileBean fileToBeUploaded;
    private static final Logger LOG = Logger.getLogger(KemasukkanRekodActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    private boolean button = false;
    private boolean view = false;
    private FasaPermohonan fasaMohon;
    private String stageId;
    private Pengguna pengguna;
    private String kodUrusan;
    private String disabled;
    private boolean showBtn = false;
    private boolean negori;
    private long idDokumen;
    private PenghantarNotis penghantarNotis;
    private List<PenghantarNotis> senaraiPenghantarNotis;
    private List<FasaPermohonan> fasaPermohonan;
    private boolean gantian = true;
    private String padam = "";

    @DefaultHandler
    public Resolution showForm() {
        if (listNotis.isEmpty() && listKandunganFolder.isEmpty()) {
            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu Di Tab Maklumat Siasatan..");
            view = true;
            showBtn = true;
            return new JSP("lelong/kemasukan_rekod.jsp").addParameter("tab", "true");
        }
        fasaPermohonan = lelongService.getPermonanFasa(idPermohonan);
        if (fasaPermohonan.isEmpty() && listNotis.get(0).getKodNotis().getKod().equals("NG")) {
            LOG.info("---ok---");
            listNotis = lelongService.getListNotis(idPermohonan, "NG");
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                negori = true;
                if (!listNotis.isEmpty()) {
                    for (Notis nn : listNotis) {
                        if (nn.getKodStatusTerima() != null) {
                            if (nn.getKodStatusTerima().getKod().equals("XT")) {
                                LOG.info("XT");
                                showBtn = true;
                                break;
                            } else {
                                showBtn = false;
                            }
                        }
                    }
                }
            }
            gantian = false;
        }

        if (!fasaPermohonan.isEmpty() && listKandunganFolder.isEmpty() && listNotis.get(0).getKodNotis().getKod().equals("SSL")) {
            LOG.info("---ko---");
            InfoAudit info = pengguna.getInfoAudit();
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            for (Notis nn : listNotis) {
                nn.setBuktiPenerimaan(null);
                nn.setPenghantarNotis(null);
                nn.setKodCaraPenghantaran(null);
                nn.setKodStatusTerima(null);
                nn.setCatatanPenerimaan(null);
                nn.setInfoAudit(info);
                nn.setTarikhHantar(null);
                nn.setTarikhTerima(null);
                nn.setBuktiPenerimaan(null);
                lelongService.saveOrUpdate(nn);
            }
            if (!fasaPermohonan.isEmpty()) {
                FasaPermohonan fasa = fasaPermohonan.get(0);
                FasaPermohonanLog mfl = lelongService.findFasaPermohonanLog(fasa);
                
                if(mfl != null){
                    lelongService.deleteMohonFasa(mfl);
                }               
                lelongService.delete(fasa);
            }
        }
        return new JSP("lelong/kemasukan_rekod.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("lelong/kemasukan_rekod.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        if (listNotis.size() > 0) {
            if (listNotis.get(0).getKodNotis().getKod().equals("NG")) {
                for (Notis nn : listNotis) {
                    if (nn.getPenghantarNotis() == null || nn.getTarikhHantar() == null) {
                        listNotis = lelongService.getListNotis(idPermohonan, "SSL");
                        gantian = false;
                        break;
                    }
                }
            }
        }
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
        } else {
            negori = false;
        }
        button = true;
        return new JSP("lelong/semak_notis.jsp").addParameter("tab", "true");
    }

    public Resolution popupUpload() {
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        LOG.info("idNotis :" + idNotis);
        return new JSP("lelong/notis_siasatan_upload_doc.jsp").addParameter("popup", "true");
    }

    public Resolution popupPenghantarNotis() {
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        return new JSP("lelong/penghantarNotis_popup.jsp").addParameter("popup", "true");
    }

    public void notisGantian() {
        LOG.info("----notisGantian----");
        listKandunganFolder = lelongService.getListDokumenNG(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder.isEmpty()) {
            LOG.info("----1-----");
            Dokumen dokumen = new Dokumen();
            for (KandunganFolder kk : listKandunganFolder) {
                dokumen = kk.getDokumen();
            }
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (dokumen.getKodDokumen() != null) {
                    LOG.info("----2-----");
                    KodNotis kodNotis = new KodNotis();
                    kodNotis = kodNotisDAO.findById("NG");
                    if (dokumen.getKodDokumen().getKod().equals("SWT")) {
                        LOG.info("----3-----");

                        List<PermohonanAtasPerserahan> listPAP = lelongService.getPermohonanAtasPerserahan(idPermohonan);
                        PermohonanAtasPerserahan pAP = listPAP.get(0);
                        if (pAP.getUrusan().getKodUrusan().getKod().equals("GD") || pAP.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
                            List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihakPG(idPermohonan);
                            for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {

                                if (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PM")) {
                                    PermohonanPihak pPihak = null;
                                    long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                                    for (PermohonanPihak pp : listPP) {
                                        if (pp.getPihak().getIdPihak() == idPihak) {
                                            pPihak = pp;
                                            break;
                                        }
                                    }
                                    LOG.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                                    InfoAudit info = pengguna.getInfoAudit();
                                    info.setDimasukOleh(pengguna);
                                    info.setTarikhMasuk(new java.util.Date());
                                    Notis notis2 = new Notis();
                                    notis2.setPermohonan(permohonan);
                                    notis2.setInfoAudit(info);
                                    if (pPihak != null) {
                                        notis2.setPihak(pPihak);
                                    }
                                    if ((senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKS")) || (senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKD")) || (senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKA"))) {
                                        if (senaraiHakmilikPihak.get(j).getKaveatAktif() == 'Y') {
                                            notis2.setTarikhNotis(new java.util.Date());
                                            notis2.setKodNotis(kodNotis);
                                            notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                            notis2.setDokumenNotis(dokumen);
                                            notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                            lelongService.saveOrUpdate(notis2);
                                        }
                                    } else if ((!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKS")) || (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKD")) || (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKA"))) {
                                        notis2.setTarikhNotis(new java.util.Date());
                                        notis2.setKodNotis(kodNotis);
                                        notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                        notis2.setDokumenNotis(dokumen);
                                        notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                        lelongService.saveOrUpdate(notis2);
                                    }
                                }
                            }
                            Map<Long, PermohonanPihak> pihakMap = new HashMap<Long, PermohonanPihak>();
                            listPP = lelongService.getSenaraiPmohonPihakPM(idPermohonan);
                            if (!listPP.isEmpty()) {
                                for (PermohonanPihak hp : listPP) {
                                    Long id = hp.getPihak().getIdPihak();
                                    if (pihakMap.containsKey(id)) {
                                        continue;
                                    } else {
                                        pihakMap.put(id, hp);
                                    }
                                }
                                listPP = new ArrayList(pihakMap.values());
                                for (PermohonanPihak pp : listPP) {
                                    HakmilikPihakBerkepentingan hb = null;
                                    long idPihak = pp.getPihak().getIdPihak();
                                    for (int k = 0; k < senaraiHakmilikPihak.size(); k++) {
                                        if (senaraiHakmilikPihak.get(k).getPihak().getIdPihak() == idPihak) {
                                            hb = senaraiHakmilikPihak.get(k);
                                            break;
                                        }
                                    }
                                    InfoAudit info = pengguna.getInfoAudit();
                                    info.setDimasukOleh(pengguna);
                                    info.setTarikhMasuk(new java.util.Date());
                                    Notis notis2 = new Notis();
                                    notis2.setPermohonan(permohonan);
                                    notis2.setInfoAudit(info);
                                    notis2.setPihak(pp);
                                    notis2.setTarikhNotis(new java.util.Date());
                                    notis2.setKodNotis(kodNotis);
                                    notis2.setHakmilikPihakBerkepentingan(hb);
                                    notis2.setDokumenNotis(dokumen);
                                    notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                    lelongService.saveOrUpdate(notis2);
                                }
                            }
                        }

                        if (pAP.getUrusan().getKodUrusan().getKod().equals("GDPJ") || pAP.getUrusan().getKodUrusan().getKod().equals("GDPJK")) {
                            List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(idPermohonan);
                            for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {

                                PermohonanPihak pPihak = null;
                                long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                                for (PermohonanPihak pp : listPP) {
                                    if (pp.getPihak().getIdPihak() == idPihak) {
                                        pPihak = pp;
                                        break;
                                    }
                                }
                                LOG.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                                InfoAudit info = pengguna.getInfoAudit();
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                Notis notis2 = new Notis();
                                notis2.setPermohonan(permohonan);
                                notis2.setInfoAudit(info);
                                if (pPihak != null) {
                                    notis2.setPihak(pPihak);
                                }
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotis);
                                notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                notis2.setDokumenNotis(dokumen);
                                notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                lelongService.saveOrUpdate(notis2);
                            }
                        }
                    } else {
                        LOG.info("lain-lain..");
                    }
                }

            }

            if ("05".equals(conf.getProperty("kodNegeri"))) {
                if (dokumen.getKodDokumen() != null) {
                    LOG.info("----2-----");
                    KodNotis kodNotis = new KodNotis();
                    kodNotis = kodNotisDAO.findById("NG");
                    if (dokumen.getKodDokumen().getKod().equals("NG")) {
                        LOG.info("----3-----");

                        List<PermohonanAtasPerserahan> listPAP = lelongService.getPermohonanAtasPerserahan(idPermohonan);
                        PermohonanAtasPerserahan pAP = listPAP.get(0);
                        if (pAP.getUrusan().getKodUrusan().getKod().equals("GD") || pAP.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
                            List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihakPG(idPermohonan);
                            for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {

                                if (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PM")) {
                                    PermohonanPihak pPihak = null;
                                    long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                                    for (PermohonanPihak pp : listPP) {
                                        if (pp.getPihak().getIdPihak() == idPihak) {
                                            pPihak = pp;
                                            break;
                                        }
                                    }
                                    LOG.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                                    InfoAudit info = pengguna.getInfoAudit();
                                    info.setDimasukOleh(pengguna);
                                    info.setTarikhMasuk(new java.util.Date());
                                    Notis notis2 = new Notis();
                                    notis2.setPermohonan(permohonan);
                                    notis2.setInfoAudit(info);
                                    if (pPihak != null) {
                                        notis2.setPihak(pPihak);
                                    }
                                    notis2.setTarikhNotis(new java.util.Date());
                                    notis2.setKodNotis(kodNotis);
                                    notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                    notis2.setDokumenNotis(dokumen);
                                    notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                    lelongService.saveOrUpdate(notis2);
                                }
                            }
                            Map<Long, PermohonanPihak> pihakMap = new HashMap<Long, PermohonanPihak>();
                            listPP = lelongService.getSenaraiPmohonPihakPM(idPermohonan);
                            if (!listPP.isEmpty()) {
                                for (PermohonanPihak hp : listPP) {
                                    Long id = hp.getPihak().getIdPihak();
                                    if (pihakMap.containsKey(id)) {
                                        continue;
                                    } else {
                                        pihakMap.put(id, hp);
                                    }
                                }
                                listPP = new ArrayList(pihakMap.values());
                                for (PermohonanPihak pp : listPP) {
                                    HakmilikPihakBerkepentingan hb = null;
                                    long idPihak = pp.getPihak().getIdPihak();
                                    for (int k = 0; k < senaraiHakmilikPihak.size(); k++) {
                                        if (senaraiHakmilikPihak.get(k).getPihak().getIdPihak() == idPihak) {
                                            hb = senaraiHakmilikPihak.get(k);
                                            break;
                                        }
                                    }
                                    InfoAudit info = pengguna.getInfoAudit();
                                    info.setDimasukOleh(pengguna);
                                    info.setTarikhMasuk(new java.util.Date());
                                    Notis notis2 = new Notis();
                                    notis2.setPermohonan(permohonan);
                                    notis2.setInfoAudit(info);
                                    notis2.setPihak(pp);
                                    notis2.setTarikhNotis(new java.util.Date());
                                    notis2.setKodNotis(kodNotis);
                                    notis2.setHakmilikPihakBerkepentingan(hb);
                                    notis2.setDokumenNotis(dokumen);
                                    notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                    lelongService.saveOrUpdate(notis2);
                                }
                            }
                        }

                        if (pAP.getUrusan().getKodUrusan().getKod().equals("GDPJ") || pAP.getUrusan().getKodUrusan().getKod().equals("GDPJK")) {
                            List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(idPermohonan);
                            for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {

                                PermohonanPihak pPihak = null;
                                long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                                for (PermohonanPihak pp : listPP) {
                                    if (pp.getPihak().getIdPihak() == idPihak) {
                                        pPihak = pp;
                                        break;
                                    }
                                }
                                LOG.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                                InfoAudit info = pengguna.getInfoAudit();
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                Notis notis2 = new Notis();
                                notis2.setPermohonan(permohonan);
                                notis2.setInfoAudit(info);
                                if (pPihak != null) {
                                    notis2.setPihak(pPihak);
                                }
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotis);
                                notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                notis2.setDokumenNotis(dokumen);
                                notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                lelongService.saveOrUpdate(notis2);
                            }
                        }
                    } else {
                        LOG.info("lain-lain..");
                    }
                }
            }
        }
    }

    public void simpanNotis() {

        listKandunganFolder = lelongService.getListDokumen(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder.isEmpty()) {
            Dokumen dokumen = new Dokumen();
            for (KandunganFolder kk : listKandunganFolder) {
                dokumen = kk.getDokumen();
            }

            if (dokumen.getKodDokumen() != null) {
                KodNotis kodNotis = new KodNotis();
                kodNotis = kodNotisDAO.findById("SSL");
                if (dokumen.getKodDokumen().getKod().equals("SSL")) {
                    List<PermohonanAtasPerserahan> listPAP = lelongService.getPermohonanAtasPerserahan(idPermohonan);
                    PermohonanAtasPerserahan pAP = listPAP.get(0);
                    if (pAP.getUrusan().getKodUrusan().getKod().equals("GD") || pAP.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
                        List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihakPG(idPermohonan);
                        for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {
                            //Selain Pemilik.PG dan lain2
                            if (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PM")) {
                                PermohonanPihak pPihak = null;
                                long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                                for (PermohonanPihak pp : listPP) {
                                    if (pp.getPihak().getIdPihak() == idPihak) {
                                        pPihak = pp;
                                        break;
                                    }
                                }
                                LOG.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                                InfoAudit info = pengguna.getInfoAudit();
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                Notis notis2 = new Notis();
                                notis2.setPermohonan(permohonan);
                                notis2.setInfoAudit(info);
                                if (pPihak != null) {
                                    notis2.setPihak(pPihak);
                                }
                                if ((senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKS")) || (senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKD")) || (senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKA"))) {
                                    if (senaraiHakmilikPihak.get(j).getKaveatAktif() == 'Y') {
                                        notis2.setTarikhNotis(new java.util.Date());
                                        notis2.setKodNotis(kodNotis);
                                        notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                        notis2.setDokumenNotis(dokumen);
                                        notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                        lelongService.saveOrUpdate(notis2);
                                    }
                                } else if ((!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKS")) || (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKD")) || (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PKA"))) {
                                    notis2.setTarikhNotis(new java.util.Date());
                                    notis2.setKodNotis(kodNotis);
                                    notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                    notis2.setDokumenNotis(dokumen);
                                    notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                    lelongService.saveOrUpdate(notis2);
                                }
                            }
                        }
                        //Penggadai Sahaja
                        Map<Long, PermohonanPihak> pihakMap = new HashMap<Long, PermohonanPihak>();
                        listPP = lelongService.getSenaraiPmohonPihakPM(idPermohonan);
                        if (!listPP.isEmpty()) {
                            for (PermohonanPihak hp : listPP) {
                                Long id = hp.getPihak().getIdPihak();
                                if (pihakMap.containsKey(id)) {
                                    continue;
                                } else {
                                    pihakMap.put(id, hp);
                                }
                            }
                            listPP = new ArrayList(pihakMap.values());
                            for (PermohonanPihak pp : listPP) {
                                HakmilikPihakBerkepentingan hb = null;
                                long idPihak = pp.getPihak().getIdPihak();
                                for (int k = 0; k < senaraiHakmilikPihak.size(); k++) {
                                    if (senaraiHakmilikPihak.get(k).getPihak().getIdPihak() == idPihak) {
                                        hb = senaraiHakmilikPihak.get(k);
                                        break;
                                    }
                                }
                                InfoAudit info = pengguna.getInfoAudit();
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                Notis notis2 = new Notis();
                                notis2.setPermohonan(permohonan);
                                notis2.setInfoAudit(info);
                                notis2.setPihak(pp);
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotis);
                                notis2.setHakmilikPihakBerkepentingan(hb);
                                notis2.setDokumenNotis(dokumen);
                                notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                lelongService.saveOrUpdate(notis2);
                            }
                        }
                    }
                    //Untuk urusan GDPJ dan GDPJK hantar ke lain2 pihak dalam hakmilik pihak..GD hantar ke peggadai dan pemegang gadai sahaja
                    if (pAP.getUrusan().getKodUrusan().getKod().equals("GDPJ") || pAP.getUrusan().getKodUrusan().getKod().equals("GDPJK")) {
                        List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(idPermohonan);
                        for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {
                            PermohonanPihak pPihak = null;
                            long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                            for (PermohonanPihak pp : listPP) {
                                if (pp.getPihak().getIdPihak() == idPihak) {
                                    pPihak = pp;
                                    break;
                                }
                            }
                            LOG.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                            InfoAudit info = pengguna.getInfoAudit();
                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(new java.util.Date());
                            Notis notis2 = new Notis();
                            notis2.setPermohonan(permohonan);
                            notis2.setInfoAudit(info);
                            if (pPihak != null) {
                                notis2.setPihak(pPihak);
                            }
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotis);
                            notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                            notis2.setDokumenNotis(dokumen);
                            notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                            lelongService.saveOrUpdate(notis2);
                        }
                    }
                } else {
                    LOG.info("lain-lain..");
                }
            }
        }

    }

    public void simpanNotisSSLM() {
        LOG.info("uiyyyy");
        listKandunganFolder = lelongService.getListDokumenSSLM(permohonan.getFolderDokumen().getFolderId());
        fasaPermohonan = lelongService.findFasa(idPermohonan);
        LOG.info("+++ fasaPermohonan = " + fasaPermohonan.get(0).getIdAliran());
        if (!listKandunganFolder.isEmpty()) {
            if (!fasaPermohonan.get(0).getIdAliran().equals("kpsnSiasatan")) {
//                tab Rekod Penghantaran xbole bukak bile kptsn 'TG' && selepas klik tab Maklumat Keputusan
                //by ejal 1/7/2015
                LOG.info("tetstt");
                Dokumen dokumen = new Dokumen();
                for (KandunganFolder kk : listKandunganFolder) {
                    dokumen = kk.getDokumen();
                }

                if (dokumen.getKodDokumen() != null) {
                    KodNotis kodNotis = new KodNotis();
                    kodNotis = kodNotisDAO.findById("SSL");
                    if (dokumen.getKodDokumen().getKod().equals("SSLM")) {
                        List<PermohonanAtasPerserahan> listPAP = lelongService.getPermohonanAtasPerserahan(idPermohonan);
                        PermohonanAtasPerserahan pAP = listPAP.get(0);
                        if (pAP.getUrusan().getKodUrusan().getKod().equals("GD") || pAP.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
                            List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihakPG(idPermohonan);

                            for (Notis notis3 : listNotis) {
                                if (notis3.getKodNotis().getKod().equals("SSL")) {
                                    notis3.setKodNotis(kodNotisDAO.findById("SSM"));
                                    lelongService.saveOrUpdate(notis3);
                                    //                                        
                                }

                            }

                            for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {
                                //Selain Pemilik.PG dan lain2
                                if (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PM")) {
                                    PermohonanPihak pPihak = null;
                                    long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                                    for (PermohonanPihak pp : listPP) {
                                        if (pp.getPihak().getIdPihak() == idPihak) {
                                            pPihak = pp;
                                            break;
                                        }
                                    }
                                    LOG.info("senaraiPermohonanPihak name 1 : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                                    InfoAudit info = pengguna.getInfoAudit();
                                    info.setDimasukOleh(pengguna);
                                    info.setTarikhMasuk(new java.util.Date());
                                    // create new row
                                    //                                listNotis = lelongService.getListNotis(idPermohonan, "SSL");

                                    Notis notis2 = new Notis();
                                    notis2.setPermohonan(permohonan);
                                    notis2.setInfoAudit(info);
                                    if (pPihak != null) {
                                        notis2.setPihak(pPihak);
                                    }
                                    notis2.setTarikhNotis(new java.util.Date());
                                    notis2.setKodNotis(kodNotis);
                                    notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                    notis2.setDokumenNotis(dokumen);
                                    notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                    lelongService.saveOrUpdate(notis2);
                                }
                            }
                            //Penggadai Sahaja
                            Map<Long, PermohonanPihak> pihakMap = new HashMap<Long, PermohonanPihak>();
                            listPP = lelongService.getSenaraiPmohonPihakPM(idPermohonan);

                            for (Notis notis3 : listNotis) {
                                if (notis3.getKodNotis().getKod().equals("SSL")) {
                                    notis3.setKodNotis(kodNotisDAO.findById("SSM"));
                                    lelongService.saveOrUpdate(notis3);
                                }
                            }

                            if (!listPP.isEmpty()) {
                                for (PermohonanPihak hp : listPP) {
                                    Long id = hp.getPihak().getIdPihak();
                                    if (pihakMap.containsKey(id)) {
                                        continue;
                                    } else {
                                        pihakMap.put(id, hp);
                                    }
                                }
                                listPP = new ArrayList(pihakMap.values());
                                for (PermohonanPihak pp : listPP) {
                                    HakmilikPihakBerkepentingan hb = null;
                                    long idPihak = pp.getPihak().getIdPihak();
                                    for (int k = 0; k < senaraiHakmilikPihak.size(); k++) {
                                        if (senaraiHakmilikPihak.get(k).getPihak().getIdPihak() == idPihak) {
                                            hb = senaraiHakmilikPihak.get(k);
                                            break;
                                        }
                                    }
                                    InfoAudit info = pengguna.getInfoAudit();
                                    info.setDimasukOleh(pengguna);
                                    info.setTarikhMasuk(new java.util.Date());

                                    listNotis = lelongService.getListNotis(idPermohonan, "SSL");
                                    //                                Notis notis2 = new Notis();

                                    Notis notis2 = new Notis();
                                    notis2.setPermohonan(permohonan);
                                    notis2.setInfoAudit(info);
                                    notis2.setPihak(pp);
                                    notis2.setTarikhNotis(new java.util.Date());
                                    notis2.setKodNotis(kodNotis);
                                    notis2.setHakmilikPihakBerkepentingan(hb);
                                    notis2.setDokumenNotis(dokumen);
                                    notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                    lelongService.saveOrUpdate(notis2);
                                }

                            }
                        }
                        //Untuk urusan GDPJ dan GDPJK hantar ke lain2 pihak dalam hakmilik pihak..GD hantar ke peggadai dan pemegang gadai sahaja
                        if (pAP.getUrusan().getKodUrusan().getKod().equals("GDPJ") || pAP.getUrusan().getKodUrusan().getKod().equals("GDPJK")) {
                            List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(idPermohonan);

                            for (Notis notis3 : listNotis) {
                                if (notis3.getKodNotis().getKod().equals("SSL")) {
                                    notis3.setKodNotis(kodNotisDAO.findById("SSM"));
                                    lelongService.saveOrUpdate(notis3);
                                }
                            }

                            for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {
                                PermohonanPihak pPihak = null;
                                long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                                for (PermohonanPihak pp : listPP) {
                                    if (pp.getPihak().getIdPihak() == idPihak) {
                                        pPihak = pp;
                                        break;
                                    }
                                }
                                LOG.info("senaraiPermohonanPihak name 2: " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                                InfoAudit info = pengguna.getInfoAudit();
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());

                                Notis notis2 = new Notis();
                                notis2.setPermohonan(permohonan);
                                notis2.setInfoAudit(info);
                                if (pPihak != null) {
                                    notis2.setPihak(pPihak);
                                }
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotis);
                                notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                notis2.setDokumenNotis(dokumen);
                                notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                lelongService.saveOrUpdate(notis2);

                            }
                        }
                    } else {
                        LOG.info("lain-lain..");
                    }
                }
            }
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws IllegalStateException {
        LOG.info("----rehydrate start----");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            List<String> listIDHakmilik = new ArrayList<String>();
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                listIDHakmilik.add(hp.getHakmilik().getIdHakmilik());
            }
            LOG.info("listIDHakmilik : " + listIDHakmilik.size());
            for (String idHakmilik : listIDHakmilik) {
                LOG.info("idHakmilik = " + idHakmilik);
            }
            List<HakmilikPihakBerkepentingan> listHP = lelongService.getHakmilikPihakALL(idPermohonan, listIDHakmilik);
            Map<Long, HakmilikPihakBerkepentingan> pihakMap = new HashMap<Long, HakmilikPihakBerkepentingan>();
            for (HakmilikPihakBerkepentingan hp : listHP) {
                if (hp.getPihak() != null) {
                    LOG.info("ID HP = " + hp.getIdHakmilikPihakBerkepentingan());
                    Long id = hp.getPihak().getIdPihak();
                    if (pihakMap.containsKey(id)) {
                        continue;
                    } else {
                        pihakMap.put(id, hp);
                    }
                }
            }
            senaraiHakmilikPihak = new ArrayList(pihakMap.values());
            LOG.info("senaraiPermohonanPihak : " + senaraiHakmilikPihak.size());
            listNotisOld = lelongService.getListNotisAll(idPermohonan);
            //penah ade tangguh,utk create idnotis baru.utk urusan tangguh
            fasaMohon = lelongService.findFasaPermohonanSiasatanKodTG(idPermohonan);
            listKandunganFolder = lelongService.getListDokumenNG(permohonan.getFolderDokumen().getFolderId());

            senaraiPenghantarNotis = lelongService.findPenghantarNotisAktif();

            LOG.info("listKandunganFolder : " + listKandunganFolder.size());
            if (!listKandunganFolder.isEmpty()) {
                gantian = true;
                LOG.info("-----NULL----");
                listNotis = lelongService.getListNotis(idPermohonan, "NG");
                if (listNotis.isEmpty()) {
                    notisGantian();
                    listNotis = lelongService.getListNotis(idPermohonan, "NG");
                }
            } else {
                gantian = false;
                listNotis = lelongService.getListNotis(idPermohonan, "SSL");
                LOG.info("listNotis : " + listNotis.size());
                if (listNotis.isEmpty()) {
                    LOG.info("Belum ade lg...");
                    simpanNotis();
                    listNotis = lelongService.getListNotis(idPermohonan, "SSL");
                }

                if (fasaMohon != null) {
                    listNotis = lelongService.getListNotis(idPermohonan, "SSL");
                    listNotisssm = lelongService.getListNotis(idPermohonan, "SSM");
                    if (listNotisssm.isEmpty()) {
                        simpanNotisSSLM();
                        listNotis = lelongService.getListNotis(idPermohonan, "SSL");
                        LOG.info("list notis ada brapa: " + listNotis.size());
                        LOG.info("list notis ada brapa SSM: " + listNotisssm.size());

                    }
                    listNotis = lelongService.getListNotis(idPermohonan, "SSL");
                }
                for (Notis n : listNotis) {
                    if (n.getHakmilikPihakBerkepentingan().getJenis().getKod() == "PKS") {
                        if (n.getHakmilikPihakBerkepentingan().getKaveatAktif() == 'Y') {
                            notis = n;
                        }
                    } else {
                        notis = n;
                    }
                }
            }
//          cetak surat warta klu x sampai
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                if (!listNotis.isEmpty()) {
                    for (Notis nn : listNotis) {
                        if (nn.getKodStatusTerima() != null) {
                            if (nn.getKodStatusTerima().getKod().equals("XT")) {
                                LOG.info("XT");
                                showBtn = true;
                                break;
                            } else {
                                showBtn = false;
                            }
                        }
                    }
                }
            }
        }
    }

    public Resolution simpan() {

        LOG.info("----simpala----");
        LOG.info("listNotisla : " + listNotis.size());
        InfoAudit info = pengguna.getInfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        for (int i = 0; i < listNotis.size(); i++) {
            Notis nn = listNotis.get(i);
            KodStatusTerima kodterime = kodStatusTerimaDAO.findById(getContext().getRequest().getParameter("kodStatusTerima" + i));
            KodCaraPenghantaran kodHanta = kodCaraPenghantaranDAO.findById(getContext().getRequest().getParameter("kodCaraPenghantaran" + i));
            String namaPenghantar = getContext().getRequest().getParameter("namaPengahantar" + i);

            LOG.info("kodHanta = " + kodHanta.getKod());
            LOG.info("namaPenghantar = " + namaPenghantar);

            if (!"".equals(namaPenghantar)) {
                PenghantarNotis peng = penghantarNotisDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("namaPengahantar" + i)));
                nn.setPenghantarNotis(peng);
            } else {
                nn.setPenghantarNotis(null);
            }

            nn.setKodCaraPenghantaran(kodHanta);
            nn.setKodStatusTerima(kodterime);
            nn.setInfoAudit(info);
            LOG.info("nn : " + nn.getIdNotis());
            lelongService.saveOrUpdate(nn);
        }
        rehydrate();
        showForm();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new JSP("lelong/kemasukan_rekod.jsp").addParameter("tab", "true");
    }

    public Resolution simpan2() {

        LOG.info("----simpala----");
        LOG.info("listNotisla : " + listNotis.size());
        InfoAudit info = pengguna.getInfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        for (int i = 0; i < listNotis.size(); i++) {
            Notis nn = listNotis.get(i);
            KodStatusTerima kodterime = kodStatusTerimaDAO.findById(getContext().getRequest().getParameter("kodStatusTerima" + i));
            KodCaraPenghantaran kodHanta = kodCaraPenghantaranDAO.findById("WA");
            PenghantarNotis peng = penghantarNotisDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("namaPengahantar" + i)));
            nn.setPenghantarNotis(peng);
            nn.setKodCaraPenghantaran(kodHanta);
            nn.setKodStatusTerima(kodterime);
            nn.setInfoAudit(info);
            LOG.info("nn : " + nn.getIdNotis());
            lelongService.saveOrUpdate(nn);
        }
        rehydrate();
        showForm();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new JSP("lelong/kemasukan_rekod.jsp").addParameter("tab", "true");
    }

    public Resolution findNoPengenalan() {
        String result = "";
        int idPenghantarNotis = (Integer.parseInt(getContext().getRequest().getParameter("idPenghantarNotis")));
        PenghantarNotis pN = penghantarNotisDAO.findById(idPenghantarNotis);
        pN = lelongService.findPenghantarNotis(idPenghantarNotis);
        result = pN.getNoPengenalan();
        return new StreamingResolution("text/plain", result);
    }

    public Resolution simpanPenghantarNotis() {

        InfoAudit info = pengguna.getInfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = pengguna.getKodCawangan();
        penghantarNotis.setInfoAudit(info);
        penghantarNotis.setCawangan(caw);
        penghantarNotis.setAktif('Y');
        lelongService.saveOrUpdate(penghantarNotis);
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP("lelong/kemasukan_rekod.jsp").addParameter("tab", "true");
    }

    public Resolution processUploadDoc() {
        LOG.info("---muat naik---");
        LOG.info("fileToBeUploaded : " + fileToBeUploaded);
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = idNotis;
        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
                Dokumen doc = new Dokumen();
                Notis dtn = notisDAO.findById(Long.parseLong(fname));
                if (dtn.getBuktiPenerimaan() != null) {
                    doc = dtn.getBuktiPenerimaan();
                    ia = dtn.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kd);
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                dtn.setInfoAudit(ia);
                lelongService.updateNotis(dtn);
                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        rehydrate();
        listNotis = lelongService.getListNotis(idPermohonan, "SSL");
        return new JSP("lelong/notis_siasatan_upload_doc.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        rehydrate();
        return new JSP("lelong/kemasukan_rekod.jsp").addParameter("tab", "true");
    }

    public Resolution popupScan() {
        LOG.info("idNotis :" + idNotis);
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String fname = idNotis;
        LOG.info("idNotis : " + fname);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try {
            if (p != null && fname != null) {
                Dokumen doc = new Dokumen();
                Notis dtn = notisDAO.findById(Long.parseLong(fname));
                if (dtn.getBuktiPenerimaan() != null) {
                    doc = dtn.getBuktiPenerimaan();
                    ia = dtn.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                doc.setKodDokumen(kd);
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat("application/pdf/image/gif");
                doc.setInfoAudit(ia);
                idDokumen = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDokumen);
                // update at DasarTuntutanNotis
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setBuktiPenerimaan(dokumenDAO.findById(idDokumen));
                dtn.setInfoAudit(ia);
                lelongService.updateNotis(dtn);
            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
        }
        return new JSP("lelong/notis_siasatan_scan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution genReport() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        try {
            LOG.info("genReportFromXML");
            String gen = "LLGSuratWarta_MLK.rdf";
            String code = "NG";
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution view() {

        LOG.info("idNotis : " + idNotis);

        List<Notis> listNS = lelongService.getNotis(Long.parseLong(idNotis));
        LOG.info("listNS : " + listNS.size());
        if (listNS.get(0).getBuktiPenerimaan() != null) {
            idDokumen = listNS.get(0).getBuktiPenerimaan().getIdDokumen();
        }
        LOG.info("idDokumen : " + idDokumen);
        if (idDokumen == 0) {
            return new ErrorResolution(500, "Dokumen tidak dinyatakan");
        }

        Dokumen d = dokumenDAO.findById(idDokumen);
        if (d == null || d.getKodDokumen() == null) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }

        // classification checking
        Pengguna p = ((etanahActionBeanContext) getContext()).getUser();
        if (d.getKlasifikasi() != null
                && p.getTahapSekuriti().getKod() < d.getKlasifikasi().getKod()) {
            return new ErrorResolution(401, "Pengguna tidak boleh mencapai dokumen ini.");
        }

        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500,
                    "Konfigurasi \"document.path\" tidak dijumpai");
        }
        // log the view
        DokumenCapaian dc = new DokumenCapaian();
        dc.setDokumen(d);
        dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("PD"));
        dc.setAlasan("Paparan Dokumen");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new java.util.Date());
        dc.setInfoAudit(ia);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        dokumenCapaianDAO.save(dc);
        if (d.getBaru() == 'Y') {
            ia = d.getInfoAudit();
            ia.setTarikhKemaskini(new Date());
            ia.setDiKemaskiniOleh(pengguna);
            d.setInfoAudit(ia);
            d.setBaru('T');
            dokumenDAO.update(d);
        }
        tx.commit();

        String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
        File f = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            if (f != null && d.getKodDokumen().getKawalCapaian() == 'Y') {
                if (isDebug) {
                    LOG.debug("creating watermark..");
                }
                boolean createWatermark = true;

                if (d.getKodDokumen().getKod().equalsIgnoreCase("DHKE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("DHDE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1KE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1DE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2KE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2DE")) {
                    File signFile = new File(path + ".sig");
                    if (signFile.exists()) {
                        createWatermark = false;
                    }
                }

                if (createWatermark) {
                    final InputStream is = fis;
                    final String format = d.getFormat();

                    ByteArrayOutputStream bous = FileUtil.createWaterMark(is);

                    InputStream inputStream = new ByteArrayInputStream(bous.toByteArray());

                    return new StreamingResolution(d.getFormat(), inputStream);
                }
            }
        } catch (Exception e) {
            LOG.error(e);
            return new ErrorResolution(500,
                    "Fail " + path + " tidak dijumpai");
        }
        getContext().getResponse().setContentType("application/octet-stream");
        return new StreamingResolution(d.getFormat(), fis);
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public boolean isShowBtn() {
        return showBtn;
    }

    public void setShowBtn(boolean showBtn) {
        this.showBtn = showBtn;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PenghantarNotis getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public List<FasaPermohonan> getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(List<FasaPermohonan> fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public boolean isGantian() {
        return gantian;
    }

    public void setGantian(boolean gantian) {
        this.gantian = gantian;
    }

    public List<Notis> getListNotisOld() {
        return listNotisOld;
    }

    public void setListNotisOld(List<Notis> listNotisOld) {
        this.listNotisOld = listNotisOld;
    }

    public List<Notis> getListNotisssm() {
        return listNotisssm;
    }

    public void setListNotisssm(List<Notis> listNotisssm) {
        this.listNotisssm = listNotisssm;
    }

    public List<PenghantarNotis> getSenaraiPenghantarNotis() {
        return senaraiPenghantarNotis;
    }

    public void setSenaraiPenghantarNotis(List<PenghantarNotis> senaraiPenghantarNotis) {
        this.senaraiPenghantarNotis = senaraiPenghantarNotis;
    }

    public boolean isNegori() {
        return negori;
    }

    public void setNegori(boolean negori) {
        this.negori = negori;
    }
}
