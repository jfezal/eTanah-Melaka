/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.model.*;
import etanah.service.common.PermohonanCarianService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.security.action.Secure;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.HakmilikService;
import etanah.service.common.DokumenCapaianService;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/cetak_semula_dokumenNew")
public class CetakSemulaDokumenNewActionBean extends AbleActionBean {

    private String idPermohonan;
    private String idHakmilik;
    private DokumenKewangan dokumenKewangan = new DokumenKewangan();
    private String idDokumenKewangan = null;
    private List<Dokumen> senaraiDokumen;
    private List<Dokumen> senaraiDokumenDHDE;
    private List<Dokumen> senaraiDokumenDHKE;
    private List<Dokumen> senaraiDokumenPELAN;
    private List<Dokumen> senaraiDokumenPELANDHKE;
    private List<Dokumen> senaraiDokumenDHKK;
    private List<Permohonan> senaraiPermohonan;
    private List<PermohonanCarian> senaraiPermohonanCarian;
    private Transaksi transaksi = new Transaksi();
    private List<Transaksi> listT = new ArrayList<Transaksi>();
    private List<DokumenCapaian> senaraiDokumenCapai;
    @Inject
    PermohonanService permohonanDAO;
    @Inject
    PermohonanCarianService carianDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    DokumenCapaianService dokumenCapaianService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static Logger LOG = Logger.getLogger(CetakSemulaDokumenNewActionBean.class);
    private static boolean IS_DEBUG = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/utiliti/cetak_semula_dokumenNew.jsp");
    }

    public Resolution showFormCetakGeran() {
        return new JSP("daftar/utiliti/cetak_semula_geranNew.jsp");
    }

    @Secure(roles = "pendaftarptg")
    public Resolution searchGeran() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (IS_DEBUG) {
            LOG.debug("idHakmilik = " + idHakmilik);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
            if (hm != null) {
                if (hm.getNoVersiDhke() != 0) {

                    senaraiDokumen = new ArrayList<Dokumen>();
                    Dokumen dhke = hm.getDhke();
                    Dokumen dhde = hm.getDhde();
                    Dokumen pelan = hm.getPelan();
                    String penggunaCaw = pengguna.getKodCawangan().getKod();
                    String dokumenCaw = dhke.getInfoAudit().getDimasukOleh().getKodCawangan().getKod();
                    LOG.debug("penggunaCaw = " + penggunaCaw);
                    LOG.debug("dokumenCaw " + dokumenCaw);
                    if ((pelan == null) || (!StringUtils.equals(dokumenCaw, penggunaCaw))) {
                        addSimpleError("Cetakan Pelan Hakmilik tidak wujud.");
                    } else if ((dhke == null && dhde == null) || (!StringUtils.equals(dokumenCaw, penggunaCaw))) {
                        addSimpleError("Cetakan Geran Hakmilik tidak wujud.");
                    } else {
                        if (dhke.getKlasifikasi() != null
                                && pengguna.getTahapSekuriti().getKod() < dhke.getKlasifikasi().getKod()) {
                            addSimpleError("Pengguna tidak boleh mencapai dokumen ini.");
                            return new JSP("daftar/utiliti/cetak_semula_geranNew.jsp");
                        }
                        senaraiDokumenDHKE.add(dhke);
                        senaraiDokumenDHDE.add(dhde);
                        senaraiDokumen.add(pelan);
                    }
                } else {
                    addSimpleError("Hakmilik belum ditukar ganti.");
                }
            } else {
                addSimpleError("Hakmilik tidak dijumpai.");
            }
        }
        return new JSP("daftar/utiliti/cetak_semula_geranNew.jsp");
    }

    public void searchPartialAgain(String idPermohonan) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if ((StringUtils.isNotBlank(idPermohonan) || StringUtils.isNotBlank(idHakmilik)) || (StringUtils.isNotBlank(idPermohonan))) {

            senaraiPermohonan = permohonanDAO.getSenaraiPermohonanPartial(idPermohonan, idHakmilik,
                    pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records());
        }
    }

    public Resolution searchPartial() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if ((StringUtils.isNotBlank(idPermohonan) || StringUtils.isNotBlank(idHakmilik)) || (StringUtils.isNotBlank(idPermohonan))) {

            senaraiPermohonan = permohonanDAO.getSenaraiPermohonanPartial(idPermohonan, idHakmilik,
                    pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records());
        }
        return new JSP("daftar/utiliti/cetak_semula_dokumenNew.jsp");
    }

    public Resolution search() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            if (p != null) {
                FolderDokumen fd = p.getFolderDokumen();
                List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                senaraiDokumen = new ArrayList<Dokumen>();
                senaraiDokumenDHDE = new ArrayList<Dokumen>();
                senaraiDokumenDHKE = new ArrayList<Dokumen>();
                senaraiDokumenPELAN = new ArrayList<Dokumen>();
                for (KandunganFolder kandunganFolder : senaraiKand) {
                    Dokumen d = kandunganFolder.getDokumen();
                    if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                        if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                            //new request : DHDE/DHKE only can be print if urusan already selesai
                            if (d.getKodDokumen().getKod().equals("DHDE")
                                    || d.getKodDokumen().getKod().equals("DHKE")
                                    || d.getKodDokumen().getKod().equals("PB2KE")
                                    || d.getKodDokumen().getKod().equals("DHDK") || d.getKodDokumen().getKod().equals("DHKK")) {
                                if (p.getStatus().equals("SL")) {
                                    if (d.getKodDokumen().getKod().equals("DHDE") || d.getKodDokumen().getKod().equals("DHDK")) {
                                        senaraiDokumenDHDE.add(d);
                                    }
                                    if (d.getKodDokumen().getKod().equals("DHKE") || d.getKodDokumen().getKod().equals("DHKK")) {
                                        senaraiDokumenDHKE.add(d);
                                    }
                                    if (d.getKodDokumen().getKod().equals("PB2KE") || d.getKodDokumen().getKod().equals("PB2DE")) {
                                        senaraiDokumenPELAN.add(d);
                                    }
                                }
                            } else {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }

                if (p.getKeputusan() != null) {
                    //Hakmilik Batal
                    List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
                    List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
                    List<HakmilikSebelumPermohonan> senaraiHakmilikBatal = new ArrayList<HakmilikSebelumPermohonan>();

                    for (HakmilikPermohonan hp : senaraiHakmilik) {

                        if (hp == null || hp.getHakmilik() == null) {
                            continue;
                        }

                        l_temp = hp.getSenaraiHakmilikSebelum();

                        for (HakmilikSebelumPermohonan sebelumPermohonan : l_temp) {
                            if (sebelumPermohonan == null) {
                                continue;
                            }
                            senaraiHakmilikBatal.add(sebelumPermohonan);
                        }
                    }

                    String tempIdHakmilikSejarah = "";

                    for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : senaraiHakmilikBatal) {
                        if (hakmilikSebelumPermohonan == null) {
                            continue;
                        }

                        if (tempIdHakmilikSejarah.equals(hakmilikSebelumPermohonan.getHakmilikSejarah())) {
                            continue;
                        }

                        Dokumen doc = hakmilikSebelumPermohonan.getHakmilik().getDhde();
                        if (doc != null) {
                            senaraiDokumenDHDE.add(doc);
                        }

                        tempIdHakmilikSejarah = hakmilikSebelumPermohonan.getHakmilikSejarah();
                    }
                }

            } else {
                PermohonanCarian pc = carianDAO.findById(idPermohonan);
                if (pc != null) {
                    FolderDokumen fd = pc.getFolderDokumen();
                    List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                    senaraiDokumen = new ArrayList<Dokumen>();
                    for (KandunganFolder kandunganFolder : senaraiKand) {
                        Dokumen d = kandunganFolder.getDokumen();
                        if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                            if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }
            }
        }
        return new JSP("daftar/utiliti/cetak_semula_dokumenNew.jsp");
    }

    public Resolution searchDHDE() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            if (p != null) {
                FolderDokumen fd = p.getFolderDokumen();
                List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                senaraiDokumen = new ArrayList<Dokumen>();
                senaraiDokumenDHDE = new ArrayList<Dokumen>();
                senaraiDokumenDHKE = new ArrayList<Dokumen>();
                senaraiDokumenPELAN = new ArrayList<Dokumen>();
                for (KandunganFolder kandunganFolder : senaraiKand) {
                    Dokumen d = kandunganFolder.getDokumen();
                    if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                        if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                            //new request : DHDE/DHKE only can be print if urusan already selesai
                            if (d.getKodDokumen().getKod().equals("DHDE")
                                    || d.getKodDokumen().getKod().equals("DHKE")
                                    || d.getKodDokumen().getKod().equals("PB2KE")
                                    || d.getKodDokumen().getKod().equals("DHDK") || d.getKodDokumen().getKod().equals("DHKK")) {
                                if (p.getStatus().equals("SL")) {
                                    if (d.getKodDokumen().getKod().equals("DHDE") || d.getKodDokumen().getKod().equals("DHDK")) {
                                        senaraiDokumenDHDE.add(d);
                                    }
                                }
                            } else {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }

                if (p.getKeputusan() != null) {
                    //Hakmilik Batal
                    List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
                    List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
                    List<HakmilikSebelumPermohonan> senaraiHakmilikBatal = new ArrayList<HakmilikSebelumPermohonan>();

                    for (HakmilikPermohonan hp : senaraiHakmilik) {

                        if (hp == null || hp.getHakmilik() == null) {
                            continue;
                        }

                        l_temp = hp.getSenaraiHakmilikSebelum();

                        for (HakmilikSebelumPermohonan sebelumPermohonan : l_temp) {
                            if (sebelumPermohonan == null) {
                                continue;
                            }
                            senaraiHakmilikBatal.add(sebelumPermohonan);
                        }
                    }

                    String tempIdHakmilikSejarah = "";

                    for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : senaraiHakmilikBatal) {
                        if (hakmilikSebelumPermohonan == null) {
                            continue;
                        }

                        if (tempIdHakmilikSejarah.equals(hakmilikSebelumPermohonan.getHakmilikSejarah())) {
                            continue;
                        }

                        Dokumen doc = hakmilikSebelumPermohonan.getHakmilik().getDhde();
                        if (doc != null) {
                            senaraiDokumenDHDE.add(doc);
                        }

                        tempIdHakmilikSejarah = hakmilikSebelumPermohonan.getHakmilikSejarah();
                    }
                }

            } else {
                PermohonanCarian pc = carianDAO.findById(idPermohonan);
                if (pc != null) {
                    FolderDokumen fd = pc.getFolderDokumen();
                    List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                    senaraiDokumen = new ArrayList<Dokumen>();
                    for (KandunganFolder kandunganFolder : senaraiKand) {
                        Dokumen d = kandunganFolder.getDokumen();
                        if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                            if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }
            }
            searchPartialAgain(idPermohonan);
        }
        return new JSP("daftar/utiliti/cetak_semula_dokumenNew.jsp");
    }

    public Resolution searchDHKE() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            if (p != null) {
                FolderDokumen fd = p.getFolderDokumen();
                List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                senaraiDokumen = new ArrayList<Dokumen>();
                senaraiDokumenDHDE = new ArrayList<Dokumen>();
                senaraiDokumenDHKE = new ArrayList<Dokumen>();
                senaraiDokumenPELAN = new ArrayList<Dokumen>();
                for (KandunganFolder kandunganFolder : senaraiKand) {
                    Dokumen d = kandunganFolder.getDokumen();
                    if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                        if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                            //new request : DHDE/DHKE only can be print if urusan already selesai
                            if (d.getKodDokumen().getKod().equals("DHDE")
                                    || d.getKodDokumen().getKod().equals("DHKE")
                                    || d.getKodDokumen().getKod().equals("PB2KE")
                                    || d.getKodDokumen().getKod().equals("DHDK") || d.getKodDokumen().getKod().equals("DHKK")) {
                                if (p.getStatus().equals("SL")) {

                                    if (d.getKodDokumen().getKod().equals("DHKE") || d.getKodDokumen().getKod().equals("DHKK")) {
                                        senaraiDokumenDHKE.add(d);
                                    }

                                }
                            } else {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }

                if (p.getKeputusan() != null) {
                    //Hakmilik Batal
                    List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
                    List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
                    List<HakmilikSebelumPermohonan> senaraiHakmilikBatal = new ArrayList<HakmilikSebelumPermohonan>();

                    for (HakmilikPermohonan hp : senaraiHakmilik) {

                        if (hp == null || hp.getHakmilik() == null) {
                            continue;
                        }

                        l_temp = hp.getSenaraiHakmilikSebelum();

                        for (HakmilikSebelumPermohonan sebelumPermohonan : l_temp) {
                            if (sebelumPermohonan == null) {
                                continue;
                            }
                            senaraiHakmilikBatal.add(sebelumPermohonan);
                        }
                    }

                    String tempIdHakmilikSejarah = "";

                    for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : senaraiHakmilikBatal) {
                        if (hakmilikSebelumPermohonan == null) {
                            continue;
                        }

                        if (tempIdHakmilikSejarah.equals(hakmilikSebelumPermohonan.getHakmilikSejarah())) {
                            continue;
                        }

                        Dokumen doc = hakmilikSebelumPermohonan.getHakmilik().getDhde();
                        if (doc != null) {
                            senaraiDokumenDHDE.add(doc);
                        }

                        tempIdHakmilikSejarah = hakmilikSebelumPermohonan.getHakmilikSejarah();
                    }
                }

            } else {
                PermohonanCarian pc = carianDAO.findById(idPermohonan);
                if (pc != null) {
                    FolderDokumen fd = pc.getFolderDokumen();
                    List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                    senaraiDokumen = new ArrayList<Dokumen>();
                    for (KandunganFolder kandunganFolder : senaraiKand) {
                        Dokumen d = kandunganFolder.getDokumen();
                        if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                            if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }
            }
            searchPartialAgain(idPermohonan);
        }
        return new JSP("daftar/utiliti/cetak_semula_dokumenNew.jsp");
    }

    public Resolution searchPELANKe() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            if (p != null) {
                FolderDokumen fd = p.getFolderDokumen();
                List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                senaraiDokumen = new ArrayList<Dokumen>();
                senaraiDokumenDHDE = new ArrayList<Dokumen>();
                senaraiDokumenDHKE = new ArrayList<Dokumen>();
                senaraiDokumenPELANDHKE = new ArrayList<Dokumen>();
                for (KandunganFolder kandunganFolder : senaraiKand) {
                    Dokumen d = kandunganFolder.getDokumen();
                    if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                        if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                            //new request : DHDE/DHKE only can be print if urusan already selesai
                            if (d.getKodDokumen().getKod().equals("DHDE")
                                    || d.getKodDokumen().getKod().equals("DHKE")
                                    || d.getKodDokumen().getKod().equals("PB2KE")
                                    || d.getKodDokumen().getKod().equals("PB2DE")
                                    || d.getKodDokumen().getKod().equals("DHDK") || d.getKodDokumen().getKod().equals("DHKK")) {
                                if (p.getStatus().equals("SL")) {
                                    if (d.getKodDokumen().getKod().equals("PB2KE")) {
                                        senaraiDokumenPELANDHKE.add(d);
                                    }
                                }
                            } else {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }

                if (p.getKeputusan() != null) {
                    //Hakmilik Batal
                    List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
                    List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
                    List<HakmilikSebelumPermohonan> senaraiHakmilikBatal = new ArrayList<HakmilikSebelumPermohonan>();

                    for (HakmilikPermohonan hp : senaraiHakmilik) {

                        if (hp == null || hp.getHakmilik() == null) {
                            continue;
                        }

                        l_temp = hp.getSenaraiHakmilikSebelum();

                        for (HakmilikSebelumPermohonan sebelumPermohonan : l_temp) {
                            if (sebelumPermohonan == null) {
                                continue;
                            }
                            senaraiHakmilikBatal.add(sebelumPermohonan);
                        }
                    }

                    String tempIdHakmilikSejarah = "";

                    for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : senaraiHakmilikBatal) {
                        if (hakmilikSebelumPermohonan == null) {
                            continue;
                        }

                        if (tempIdHakmilikSejarah.equals(hakmilikSebelumPermohonan.getHakmilikSejarah())) {
                            continue;
                        }

                        Dokumen doc = hakmilikSebelumPermohonan.getHakmilik().getDhde();
                        if (doc != null) {
                            senaraiDokumenDHDE.add(doc);
                        }

                        tempIdHakmilikSejarah = hakmilikSebelumPermohonan.getHakmilikSejarah();
                    }
                }

            } else {
                PermohonanCarian pc = carianDAO.findById(idPermohonan);
                if (pc != null) {
                    FolderDokumen fd = pc.getFolderDokumen();
                    List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                    senaraiDokumen = new ArrayList<Dokumen>();
                    for (KandunganFolder kandunganFolder : senaraiKand) {
                        Dokumen d = kandunganFolder.getDokumen();
                        if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                            if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }
            }
            searchPartialAgain(idPermohonan);
        }
        return new JSP("daftar/utiliti/cetak_semula_dokumenNew.jsp");
    }

    public Resolution searchPELAN() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            if (p != null) {
                FolderDokumen fd = p.getFolderDokumen();
                List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                senaraiDokumen = new ArrayList<Dokumen>();
                senaraiDokumenDHDE = new ArrayList<Dokumen>();
                senaraiDokumenDHKE = new ArrayList<Dokumen>();
                senaraiDokumenPELAN = new ArrayList<Dokumen>();
                for (KandunganFolder kandunganFolder : senaraiKand) {
                    Dokumen d = kandunganFolder.getDokumen();
                    if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                        if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                            //new request : DHDE/DHKE only can be print if urusan already selesai
                            if (d.getKodDokumen().getKod().equals("DHDE")
                                    || d.getKodDokumen().getKod().equals("DHKE")
                                    || d.getKodDokumen().getKod().equals("PB2KE")
                                    || d.getKodDokumen().getKod().equals("PB2DE")
                                    || d.getKodDokumen().getKod().equals("DHDK") || d.getKodDokumen().getKod().equals("DHKK")) {
                                if (p.getStatus().equals("SL")) {
                                    if (d.getKodDokumen().getKod().equals("PB2DE")) {
                                        senaraiDokumenPELAN.add(d);
                                    }
                                }
                            } else {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }

                if (p.getKeputusan() != null) {
                    //Hakmilik Batal
                    List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
                    List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
                    List<HakmilikSebelumPermohonan> senaraiHakmilikBatal = new ArrayList<HakmilikSebelumPermohonan>();

                    for (HakmilikPermohonan hp : senaraiHakmilik) {

                        if (hp == null || hp.getHakmilik() == null) {
                            continue;
                        }

                        l_temp = hp.getSenaraiHakmilikSebelum();

                        for (HakmilikSebelumPermohonan sebelumPermohonan : l_temp) {
                            if (sebelumPermohonan == null) {
                                continue;
                            }
                            senaraiHakmilikBatal.add(sebelumPermohonan);
                        }
                    }

                    String tempIdHakmilikSejarah = "";

                    for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : senaraiHakmilikBatal) {
                        if (hakmilikSebelumPermohonan == null) {
                            continue;
                        }

                        if (tempIdHakmilikSejarah.equals(hakmilikSebelumPermohonan.getHakmilikSejarah())) {
                            continue;
                        }

                        Dokumen doc = hakmilikSebelumPermohonan.getHakmilik().getDhde();
                        if (doc != null) {
                            senaraiDokumenDHDE.add(doc);
                        }

                        tempIdHakmilikSejarah = hakmilikSebelumPermohonan.getHakmilikSejarah();
                    }
                }

            } else {
                PermohonanCarian pc = carianDAO.findById(idPermohonan);
                if (pc != null) {
                    FolderDokumen fd = pc.getFolderDokumen();
                    List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                    senaraiDokumen = new ArrayList<Dokumen>();
                    for (KandunganFolder kandunganFolder : senaraiKand) {
                        Dokumen d = kandunganFolder.getDokumen();
                        if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                            if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }
            }
            searchPartialAgain(idPermohonan);
        }
        return new JSP("daftar/utiliti/cetak_semula_dokumenNew.jsp");
    }

    public Resolution searchDRAF() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            if (p != null) {
                FolderDokumen fd = p.getFolderDokumen();
                List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                senaraiDokumen = new ArrayList<Dokumen>();
                senaraiDokumenDHKK = new ArrayList<Dokumen>();
                for (KandunganFolder kandunganFolder : senaraiKand) {
                    Dokumen d = kandunganFolder.getDokumen();
                    if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                        if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                            //new request : DHDE/DHKE only can be print if urusan already selesai
                            if (d.getKodDokumen().getKod().equals("5F")
                                    || d.getKodDokumen().getKod().equals("DDHDK")) {
                                if (p.getStatus().equals("SL")) {
                                    if (d.getKodDokumen().getKod().equals("5F") || (d.getKodDokumen().getKod().equals("DDHDK"))) {
                                        senaraiDokumenDHKK.add(d);
                                    }
                                }
                            } else {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }

                if (p.getKeputusan() != null) {
                    //Hakmilik Batal
                    List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
                    List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
                    List<HakmilikSebelumPermohonan> senaraiHakmilikBatal = new ArrayList<HakmilikSebelumPermohonan>();

                    for (HakmilikPermohonan hp : senaraiHakmilik) {

                        if (hp == null || hp.getHakmilik() == null) {
                            continue;
                        }

                        l_temp = hp.getSenaraiHakmilikSebelum();

                        for (HakmilikSebelumPermohonan sebelumPermohonan : l_temp) {
                            if (sebelumPermohonan == null) {
                                continue;
                            }
                            senaraiHakmilikBatal.add(sebelumPermohonan);
                        }
                    }

                    String tempIdHakmilikSejarah = "";

                    for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : senaraiHakmilikBatal) {
                        if (hakmilikSebelumPermohonan == null) {
                            continue;
                        }

                        if (tempIdHakmilikSejarah.equals(hakmilikSebelumPermohonan.getHakmilikSejarah())) {
                            continue;
                        }

                        Dokumen doc = hakmilikSebelumPermohonan.getHakmilik().getDhde();
                        if (doc != null) {
                            senaraiDokumenDHDE.add(doc);
                        }

                        tempIdHakmilikSejarah = hakmilikSebelumPermohonan.getHakmilikSejarah();
                    }
                }

            } else {
                PermohonanCarian pc = carianDAO.findById(idPermohonan);
                if (pc != null) {
                    FolderDokumen fd = pc.getFolderDokumen();
                    List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
                    senaraiDokumen = new ArrayList<Dokumen>();
                    for (KandunganFolder kandunganFolder : senaraiKand) {
                        Dokumen d = kandunganFolder.getDokumen();
                        if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
                            if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                                senaraiDokumen.add(d);
                            }
                        }
                    }
                }
            }
            searchPartialAgain(idPermohonan);
        }
        return new JSP("daftar/utiliti/cetak_semula_dokumenNew.jsp");
    }

    public Resolution cetakSemula() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String sbbCetakanSemula = getContext().getRequest().getParameter("sbb_cetakan_semula");
        String idDokumen = getContext().getRequest().getParameter("id_dokumen");
        Dokumen d = dokumenDAO.findById(Long.parseLong(idDokumen));

        if (d == null) {
            return new StreamingResolution("text/plain", "2");
        }


        DokumenCapaian dc = new DokumenCapaian();

        if (StringUtils.isNotBlank(sbbCetakanSemula)) {
            dc.setAlasan("CETAKAN SEMULA [ " + sbbCetakanSemula + " ]");
        }

        dc.setDokumen(d);
        dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("CD"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        dc.setInfoAudit(ia);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        dokumenCapaianDAO.save(dc);
        tx.commit();

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution cetakSemula_2() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String sbbCetakanSemula = getContext().getRequest().getParameter("sbb_cetakan_semula");
        String idDokumens = getContext().getRequest().getParameter("id_dokumen_terlibat");

        String[] splitChar = idDokumens.split("[,\\;]");

        for (String idDok : splitChar) {

            Dokumen d = dokumenDAO.findById(Long.parseLong(idDok));

            if (d == null) {
                return new StreamingResolution("text/plain", "2");
            }

            DokumenCapaian dc = new DokumenCapaian();

            if (StringUtils.isNotBlank(sbbCetakanSemula)) {
                dc.setAlasan("CETAKAN SEMULA [ " + sbbCetakanSemula + " ]");
            }

            dc.setDokumen(d);
            dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("CD"));
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            dc.setInfoAudit(ia);

            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            dokumenCapaianDAO.save(dc);
            tx.commit();

        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution viewSejarahCetakan() {
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        senaraiDokumenCapai = dokumenCapaianService.findByIdDokumenAndCD(idDokumen);
        return new JSP("daftar/utiliti/view_sejarah_cetakan.jsp").addParameter("popup", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdDokumenKewangan() {
        return idDokumenKewangan;
    }

    public void setIdDokumenKewangan(String idDokumenKewangan) {
        this.idDokumenKewangan = idDokumenKewangan;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public List<PermohonanCarian> getSenaraiPermohonanCarian() {
        return senaraiPermohonanCarian;
    }

    public void setSenaraiPermohonanCarian(List<PermohonanCarian> senaraiPermohonanCarian) {
        this.senaraiPermohonanCarian = senaraiPermohonanCarian;
    }

    public List<Transaksi> getListT() {
        return listT;
    }

    public void setListT(List<Transaksi> listT) {
        this.listT = listT;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public List<DokumenCapaian> getSenaraiDokumenCapai() {
        return senaraiDokumenCapai;
    }

    public void setSenaraiDokumenCapai(List<DokumenCapaian> senaraiDokumenCapai) {
        this.senaraiDokumenCapai = senaraiDokumenCapai;
    }

    public List<Dokumen> getSenaraiDokumenDHDE() {
        return senaraiDokumenDHDE;
    }

    public void setSenaraiDokumenDHDE(List<Dokumen> senaraiDokumenDHDE) {
        this.senaraiDokumenDHDE = senaraiDokumenDHDE;
    }

    public List<Dokumen> getSenaraiDokumenDHKE() {
        return senaraiDokumenDHKE;
    }

    public void setSenaraiDokumenDHKE(List<Dokumen> senaraiDokumenDHKE) {
        this.senaraiDokumenDHKE = senaraiDokumenDHKE;
    }

    public List<Dokumen> getSenaraiDokumenPELAN() {
        return senaraiDokumenPELAN;
    }

    public void setSenaraiDokumenPELAN(List<Dokumen> senaraiDokumenPELAN) {
        this.senaraiDokumenPELAN = senaraiDokumenPELAN;
    }

    public List<Dokumen> getSenaraiDokumenDHKK() {
        return senaraiDokumenDHKK;
    }

    public void setSenaraiDokumenDHKK(List<Dokumen> senaraiDokumenDHKK) {
        this.senaraiDokumenDHKK = senaraiDokumenDHKK;
    }

    public List<Dokumen> getSenaraiDokumenPELANDHKE() {
        return senaraiDokumenPELANDHKE;
    }

    public void setSenaraiDokumenPELANDHKE(List<Dokumen> senaraiDokumenPELANDHKE) {
        this.senaraiDokumenPELANDHKE = senaraiDokumenPELANDHKE;
    }
}
