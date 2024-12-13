/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.laporan;

import able.stripes.AbleActionBean;
import etanah.view.etanahActionBeanContext;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import java.util.*;
import etanah.service.*;
import etanah.service.common.*;
import etanah.service.common.HakmilikService;
import etanah.report.ReportUtil;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import java.io.File;
import able.stripes.JSP;
import org.hibernate.Session;

@UrlBinding("/strata/cetakJanaGeranStrata")
public class cetakJanaGeranStrataActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(cetakJanaGeranStrataActionBean.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    etanah.kodHasilConfig khconf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    DokumenCapaianService dokumenCapaianService;
    private List<DokumenCapaian> senaraiDokumenCapai;
    private List<KandunganFolder> senaraiKandunganFolder = new ArrayList<KandunganFolder>();
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    private String idHakmilikInduk;
    private String idHakmilikStrata;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    StrataPtService strataPtService;
    @Inject
    PenggunaDAO penggunaDAO;
    private List<Hakmilik> senaraiHakmilik = new ArrayList();
    private List<Hakmilik> hakmilikStrata;
    private List<Hakmilik> senaraiHakmilikStrata;
    private List<Hakmilik> senaraiHakmilikProv = new ArrayList();
    private List<HakmilikTukarGantiStrata> senaraiTkrganti = new ArrayList();
    private List<HakmilikTukarGantiStrata> senaraiTkrgantiProv = new ArrayList();
    private List<HakmilikTukarGantiStrata> senaraiTkrganti2k3k = new ArrayList();
    private List<Dokumen> senaraiDokumen = new ArrayList();
    private List<Dokumen> senaraiDokumenkk = new ArrayList();
    private List<Dokumen> senaraiDokumenBSK = new ArrayList();
    private List<Dokumen> senaraiDokumenBSKkk = new ArrayList();
    private List<Dokumen> senaraiDokumenPSK = new ArrayList();
    private List<Dokumen> senaraiDokumenProv = new ArrayList();
    private List<Dokumen> senaraiDokumen2K3K = new ArrayList();
    private String sbb_cetakan_semula;
    private int bilHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        idHakmilikInduk = null;
        idHakmilikStrata = null;
        hakmilikStrata = null;

        String idHakmilik = getContext().getRequest().getParameter("bilHakmilik");
        if (idHakmilik != null) {
            bilHakmilik = Integer.parseInt(idHakmilik);
        } else {
            bilHakmilik = 5;
        }

        getContext().getRequest().setAttribute("versi", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strataCetakJana.jsp");
    }

    public Resolution simpanSebab() {
        LOG.info("simpanSebab");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        LOG.info("idHakmilik------" + idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strataCetakJana.jsp");
    }

    public Resolution cetakSemula() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String sbbCetakanSemula = getContext().getRequest().getParameter("sbb_cetakan_semula");
        String idDokumen = getContext().getRequest().getParameter("id_dokumen");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
//        idHakmilikStrata = getContext().getRequest().getParameter("idHakmilikStrata");
        LOG.info("idHakmilik" + idHakmilik);
        Dokumen d = dokumenDAO.findById(Long.parseLong(idDokumen));

        if (d == null) {
            return new StreamingResolution("text/plain", "2");
        }

        List<DokumenCapaian> dc2 = strataPtService.findDokumenCapaian(d.getIdDokumen());
        int count = 0;
        for (DokumenCapaian dc1 : dc2) {
            LOG.info(dc1.getAlasan());
            String alasan = "CETAKAN SEMULA [ " + sbbCetakanSemula + " ]";
            if (dc1.getAlasan().equals(alasan)) {
                count++;
                return new StreamingResolution("text/plain", "0");
            }
        }
        if (count == 0) {
            DokumenCapaian dc = new DokumenCapaian();

            if (StringUtils.isNotBlank(sbbCetakanSemula)) {
                dc.setAlasan("CETAKAN SEMULA [ " + sbbCetakanSemula + " ]");
            } else {
                dc.setAlasan("Cetakan Dokumen");
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

            Dokumen dk = strataPtService.findDokumen(dc.getDokumen().getIdDokumen());

            if (!idHakmilik.isEmpty()) {
                List<HakmilikTukarGantiStrata> hmTG = strataPtService.findHmStrTGbyInduk(idHakmilik);

                for (HakmilikTukarGantiStrata hmTGStr : hmTG) {
                    hmTGStr.setTarikhCetak2k(new java.util.Date());
                    strataPtService.simpanHmTukarGantiStrata(hmTGStr);
                }
            }

            if (!idHakmilik.isEmpty()) {
                HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(idHakmilik);

                if (hmTG != null) {
                    hmTG.setTarikhCetak4k(new java.util.Date());
                    strataPtService.simpanHmTukarGantiStrata(hmTG);
                }
            }

            tx.commit();
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution cari() {

        idHakmilikInduk = getContext().getRequest().getParameter("idHakmilikInduk");
        idHakmilikStrata = null;

        String idHakmilik = getContext().getRequest().getParameter("bilHakmilik");
        if (idHakmilik != null || !idHakmilik.isEmpty() || idHakmilik.equals("")) {
            bilHakmilik = Integer.parseInt(idHakmilik);
        } else {
            bilHakmilik = 5;
        }

        sbb_cetakan_semula = null;

        LOG.info("idHakmilikInduk" + idHakmilikInduk);
        LOG.info("idHakmilikStrata" + idHakmilikStrata);

        if (!idHakmilikInduk.isEmpty()) {
            Hakmilik induk = strataPtService.findInfoByIdHakmilikInduk(idHakmilikInduk);
            if (induk != null) {
                senaraiHakmilik = strataPtService.findIdHakmilikByIdHakmilikIndukversi1(idHakmilikInduk);
                senaraiHakmilikProv = strataPtService.findHakmilibyParentProv(idHakmilikInduk);
                senaraiTkrganti2k3k = strataPtService.findHakmilikTkrganti(idHakmilikInduk);
                senaraiTkrganti = strataPtService.findHakmilikTkrganti(idHakmilikInduk);
                if (senaraiHakmilik.isEmpty()) {
                    addSimpleError("Hakmilik Strata Untuk Hakmilik Ini Belum Ditukarganti.");

                    if (induk != null) {
                        if (induk.getNoVersiIndeksStrata() != 0) {

                            List<Dokumen> ls2 = new ArrayList<Dokumen>();
                            ls2 = strataPtService.findGeranStrata2k3k(idHakmilikInduk);
                            for (Dokumen dok : ls2) {
                                senaraiDokumen2K3K.add(dok);
                            }
                        } else {
                            addSimpleError("Borang 2K dan 3K Untuk Hakmilik Ini Belum Ditukarganti.");
                        }
                    }

                } else {
                    List<String> senaraiHm = new ArrayList<String>();
                    for (Hakmilik hm : senaraiHakmilik) {
                        senaraiHm.add(hm.getIdHakmilik());
                    }
                    senaraiDokumen = strataPtService.findGeranStrataDokumen(senaraiHm);
                    senaraiDokumenkk = strataPtService.findGeranStrataDokumenKK(senaraiHm);
                    senaraiDokumenBSK = strataPtService.findGeranStrataDokumenBSK(senaraiHm);
                    senaraiDokumenBSKkk = strataPtService.findGeranStrataDokumenBSKK(senaraiHm);
                    senaraiDokumenPSK = strataPtService.findGeranStrataDokumenPSK(senaraiHm);
//                    List<Dokumen> ls = new ArrayList<Dokumen>();
//                    int count = 0;
//                    for (Hakmilik hm : senaraiHakmilik) {
//                        LOG.info(hm.getIdHakmilik());
//                        if (hm.getKodKategoriBangunan().getKod().equals("B") || hm.getKodKategoriBangunan().getKod().equals("L")) {
//                            ls = strataPtService.findGeranStrata(hm.getIdHakmilik());
//                            for (Dokumen dok : ls) {
//                                senaraiDokumen.add(dok);
//                            }
//                        } else {
//                            ls = strataPtService.findGeranStrataProv(hm.getIdHakmilikInduk());
//                            if (count == 0) {
//                                for (Dokumen dok : ls) {
//                                    senaraiDokumenProv.add(dok);
//                                }
//                                count++;
//                            }
//                            LOG.info("senaraiDokumenProv++" + senaraiDokumenProv.size());
//                        }
//
//                    }
                    if (senaraiHakmilik.get(0).getNoVersiIndeksStrata() != null) {
                        if (senaraiHakmilik.get(0).getNoVersiIndeksStrata() != 0) {

                            List<Dokumen> ls2 = new ArrayList<Dokumen>();
                            ls2 = strataPtService.findGeranStrata2k3k(senaraiHakmilik.get(0).getIdHakmilikInduk());
                            for (Dokumen dok : ls2) {
                                senaraiDokumen2K3K.add(dok);
                            }
                        }
                    } else {
                        addSimpleError("Borang 2K dan 3K Untuk Hakmilik Ini Belum Ditukarganti.");
                    }
                }
                if (!senaraiHakmilikProv.isEmpty()) {
                    List<Dokumen> ls = new ArrayList<Dokumen>();
                    int count = 0;
                    for (Hakmilik hm : senaraiHakmilikProv) {
                        ls = strataPtService.findGeranStrataProv(hm.getIdHakmilikInduk());
                        if (count == 0) {
                            HakmilikTukarGantiStrata hmTG = strataPtService.hakmilikTkrgantiStrata(hm.getIdHakmilik());
                            for (Dokumen dok : ls) {
                                senaraiDokumenProv.add(dok);
                            }
                            count++;
                            senaraiTkrgantiProv.add(hmTG);
                        }
                    }
                }
            } else {
                addSimpleError("Id Hakmilik Ini " + idHakmilikInduk + " Tidak Dijumpai");
                idHakmilikInduk = null;
                idHakmilikStrata = null;
            }

        } else {
            String idHakmilikm = getContext().getRequest().getParameter("bilHakmilik");
            if (idHakmilikm != null) {
                bilHakmilik = Integer.parseInt(idHakmilikm);
            } else {
                bilHakmilik = 5;
            }
            senaraiTkrganti.clear();
            List<String> senaraiHm2 = new ArrayList<String>();
            for (int i = 0; i < bilHakmilik; i++) {
                String idHakmilikStrata = getContext().getRequest().getParameter("idHakmilikStrata" + i);
                Hakmilik idStrata = strataPtService.findInfoByIdHakmilik(idHakmilikStrata);
                LOG.info("idStrata--" + idStrata);
                if (!idHakmilikStrata.isEmpty()) {
                    if (idStrata != null) {
                        List<HakmilikTukarGantiStrata> senaraiTkrgantiStrata = strataPtService.findHakmilikTkrgantiStrata(idStrata.getIdHakmilik());
                        for (HakmilikTukarGantiStrata hmStr : senaraiTkrgantiStrata) {
                            senaraiTkrganti.add(hmStr);
                        }
                        LOG.info("senaraiTkrganti=======" + senaraiTkrganti.size());
                        int count = 0;
                        if (idStrata.getNoVersiDhde() != 0) {
                            List<Dokumen> ls3 = new ArrayList<Dokumen>();

                            if (idStrata.getKodKategoriBangunan().getKod().equals("B") || idStrata.getKodKategoriBangunan().getKod().equals("L")|| idStrata.getKodKategoriBangunan().getKod().equals("P")) {
//                                ls3 = strataPtService.findGeranStrata(idStrata.getIdHakmilik());
//                                for (Dokumen dok : ls3) {
//                                    senaraiDokumen.add(dok);
//                                }
                                senaraiHm2.add(idStrata.getIdHakmilik());
                            }

                        } else {
                            addSimpleError("Id Hakmilik Strata Ini " + idHakmilikStrata + " Belum Ditukarganti.");
                            idHakmilikInduk = null;
                            idHakmilikStrata = null;
                        }
                    } else {
                        addSimpleError("Id Hakmilik Strata Ini " + idHakmilikStrata + " Tidak Dijumpai");
                        idHakmilikInduk = null;
                        idHakmilikStrata = null;
                    }
                }
            }
            senaraiDokumen = strataPtService.findGeranStrataDokumen(senaraiHm2);
            senaraiDokumenkk = strataPtService.findGeranStrataDokumenKK(senaraiHm2);
            senaraiDokumenPSK = strataPtService.findGeranStrataDokumenPSK(senaraiHm2);
            senaraiDokumenBSK = strataPtService.findGeranStrataDokumenBSK(senaraiHm2);
            senaraiDokumenBSKkk = strataPtService.findGeranStrataDokumenBSKK(senaraiHm2);
            if (senaraiDokumen.isEmpty()) {
                addSimpleError("Id Hakmilik Strata Ini Belum Ditukarganti.");
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strataCetakJana.jsp");
    }

    public Resolution jana() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        ctx.getRequest().setAttribute("tanpaBayaran", true);

        idHakmilikInduk = null;
        idHakmilikStrata = null;
        String jana2k3k = null;
        String jana4AK = null;

        idHakmilikInduk = getContext().getRequest().getParameter("idHakmilikInduk");
        idHakmilikStrata = getContext().getRequest().getParameter("idHakmilikStrata");
        jana2k3k = getContext().getRequest().getParameter("2k3k");
        jana4AK = getContext().getRequest().getParameter("4AK");
        LOG.info("idHakmilikInduk" + idHakmilikInduk);
        LOG.info("idHakmilikStrata" + idHakmilikStrata);
        LOG.info("jana2k3k" + jana2k3k);

        String documentPath = conf.getProperty("document.path");
        senaraiHakmilik.clear();

        if (!idHakmilikInduk.isEmpty()) {
            List<Hakmilik> hakmilikInduk = strataPtService.findHakmilikIndukNull(idHakmilikInduk);
            List<String> listBngn = strataPtService.findNoBangunanProv(idHakmilikInduk);
            LOG.info("listHmp~~~~" + listBngn.size());
            if (jana4AK != null) {
                for (int i = 0; i < listBngn.size(); i++) {
                    LOG.info("listBngn.get(i).getNoBangunan()~~~~" + listBngn.get(i));
                    String nobgn = listBngn.get(i);
                    List<Hakmilik> hmbngn = strataPtService.findHm(idHakmilikInduk, nobgn);
                    if (hmbngn != null) {
                        senaraiHakmilik.add(hmbngn.get(0));
                    }
                }
            } else {
                senaraiHakmilik = strataPtService.findIdHakmilikByIdHakmilikIndukversi1(idHakmilikInduk);
            }
            LOG.info("senaraiHakmilik--" + senaraiHakmilik.size());
        } else {
            String bilHakmilik = getContext().getRequest().getParameter("bilHakmilik");
            if (!bilHakmilik.isEmpty()) {
                int bilangan = Integer.parseInt(bilHakmilik);
                LOG.info("bilangan--" + bilangan);

                for (int i = 0; i < bilangan; i++) {
                    String idStrata = getContext().getRequest().getParameter("idHakmilikStrata" + i);
                    LOG.info("idStrata--" + idStrata);
                    Hakmilik idHmStrata = strataPtService.findInfoByIdHakmilik(idStrata);
                    if (idHmStrata != null) {
                        senaraiHakmilik.add(idHmStrata);
                    }
                }
            }
            LOG.info("senaraiHakmilik--" + senaraiHakmilik.size());
//            senaraiHakmilik = strataPtService.findIdHakmilikByIdHakmilikversi1(idHakmilikStrata);
        }

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        //  try {
        FolderDokumen fd = new FolderDokumen();
        fd.setTajuk("-");
        fd.setInfoAudit(ia);
        folderDokumenDAO.save(fd);

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            for (int i = 0; i < Report_Name.length; i++) {
                // LOG.debug("id hakmilik =" + ch.getIdHakmilik());
                LOG.debug("SIZE REPORT =" + Report_Name.length);
                LOG.debug("SIZE REPORT =" + Report_Label.length);
                LOG.debug("name REPORT =" + Report_Name[i]);
                LOG.debug("label REPORT =" + Report_Label[i]);

                //   KodUrusan kodUrusan = kodUrusanDAO.findById("CRHMR");
                KodDokumen kodDokumen = null;
                String nameOfReport = "";
                kodDokumen = kodDokumenDAO.findById(Report_Label[i]);
                LOG.debug("kod dokumen =" + kodDokumen.getKod());
                nameOfReport = Report_Name[i];

                senaraiKandunganFolder = generateReport(kodDokumen, ia, documentPath, nameOfReport, fd);

            }
        }

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            for (int i = 0; i < Report_NameNS.length; i++) {
                // LOG.debug("id hakmilik =" + ch.getIdHakmilik());
                LOG.debug("SIZE REPORT =" + Report_NameNS.length);
                LOG.debug("SIZE REPORT =" + Report_Label.length);
                LOG.debug("name REPORT =" + Report_NameNS[i]);
                LOG.debug("label REPORT =" + Report_Label[i]);

                //   KodUrusan kodUrusan = kodUrusanDAO.findById("CRHMR");
                KodDokumen kodDokumen = null;
                String nameOfReport = "";
                kodDokumen = kodDokumenDAO.findById(Report_Label[i]);
                LOG.debug("kod dokumen =" + kodDokumen.getKod());
                nameOfReport = Report_NameNS[i];

                senaraiKandunganFolder = generateReportNS(kodDokumen, ia, documentPath, nameOfReport, fd);

            }
        }

        tx.commit();

        if (!idHakmilikInduk.isEmpty()) {
            Hakmilik induk = strataPtService.findInfoByIdHakmilikInduk(idHakmilikInduk);
            if (induk != null) {
                senaraiHakmilik = strataPtService.findIdHakmilikByIdHakmilikIndukversi1(idHakmilikInduk);
                senaraiTkrganti2k3k = strataPtService.findHakmilikTkrganti(idHakmilikInduk);
                senaraiTkrganti = strataPtService.findHakmilikTkrganti(idHakmilikInduk);
                senaraiHakmilikProv = strataPtService.findHakmilibyParentProv(idHakmilikInduk);
                if (senaraiHakmilik.isEmpty()) {
                    if (induk != null) {
                        if (induk.getNoVersiIndeksStrata() != 0) {

                            List<Dokumen> ls2 = new ArrayList<Dokumen>();
                            ls2 = strataPtService.findGeranStrata2k3k(idHakmilikInduk);
                            for (Dokumen dok : ls2) {
                                senaraiDokumen2K3K.add(dok);
                            }
                        }
                    }

                } else {
                    List<String> senaraiHm2 = new ArrayList<String>();
//                    int count = 0;
                    for (Hakmilik hm : senaraiHakmilik) {
                        LOG.info(hm.getIdHakmilik());
                        if (hm.getKodKategoriBangunan().getKod().equals("B") || hm.getKodKategoriBangunan().getKod().equals("L")) {
//                            ls = strataPtService.findGeranStrata(hm.getIdHakmilik());
//                            for (Dokumen dok : ls) {
//                                senaraiDokumen.add(dok);
//                            }
                            senaraiHm2.add(hm.getIdHakmilik());
                        }
                    }
                    senaraiDokumen = strataPtService.findGeranStrataDokumen(senaraiHm2);
                    senaraiDokumenkk = strataPtService.findGeranStrataDokumenKK(senaraiHm2);
                    senaraiDokumenBSK = strataPtService.findGeranStrataDokumenBSK(senaraiHm2);
                    senaraiDokumenBSKkk = strataPtService.findGeranStrataDokumenBSKK(senaraiHm2);
                    senaraiDokumenPSK = strataPtService.findGeranStrataDokumenPSK(senaraiHm2);
                    if (senaraiHakmilik.get(0).getNoVersiIndeksStrata() != null) {
                        if (senaraiHakmilik.get(0).getNoVersiIndeksStrata() != 0) {

                            List<Dokumen> ls2 = new ArrayList<Dokumen>();
                            ls2 = strataPtService.findGeranStrata2k3k(senaraiHakmilik.get(0).getIdHakmilikInduk());
                            for (Dokumen dok : ls2) {
                                senaraiDokumen2K3K.add(dok);
                            }
                        }
                    }
                }
                if (!senaraiHakmilikProv.isEmpty()) {
                    List<Dokumen> ls = new ArrayList<Dokumen>();
                    int count = 0;
                    for (Hakmilik hm : senaraiHakmilikProv) {
                        ls = strataPtService.findGeranStrataProv(hm.getIdHakmilikInduk());
                        if (count == 0) {
                            HakmilikTukarGantiStrata hmTG = strataPtService.hakmilikTkrgantiStrata(hm.getIdHakmilik());
                            for (Dokumen dok : ls) {
                                senaraiDokumenProv.add(dok);
                            }
                            count++;
                            senaraiTkrgantiProv.add(hmTG);
                        }
                    }
                }
            }

        } else {
            String idHakmilik = getContext().getRequest().getParameter("bilHakmilik");
            if (idHakmilik != null) {
                bilHakmilik = Integer.parseInt(idHakmilik);
            } else {
                bilHakmilik = 5;
            }
            senaraiTkrganti.clear();
            List<String> senaraiHm2 = new ArrayList<String>();
            for (int i = 0; i < bilHakmilik; i++) {
                String hmParam = getContext().getRequest().getParameter("idHakmilikStrata" + i);
                if (!hmParam.equals("")) {
                    Hakmilik idStrata = strataPtService.findInfoByIdHakmilik(hmParam);
                    if (idStrata != null) {
                        List<HakmilikTukarGantiStrata> senaraiTkrgantiStrata = strataPtService.findHakmilikTkrgantiStrata(idStrata.getIdHakmilik());
                        for (HakmilikTukarGantiStrata hmStr : senaraiTkrgantiStrata) {
                            senaraiTkrganti.add(hmStr);
                        }
                        if (idStrata.getNoVersiDhde() != 0) {

                            if (idStrata.getKodKategoriBangunan().getKod().equals("B") || idStrata.getKodKategoriBangunan().getKod().equals("L")) {
//                                ls3 = strataPtService.findGeranStrata(idStrata.getIdHakmilik());
//                                for (Dokumen dok : ls3) {
//                                    senaraiDokumen.add(dok);
//                                }
                                senaraiHm2.add(idStrata.getIdHakmilik());
                            }

                        }
                    }
                }
            }
            senaraiDokumen = strataPtService.findGeranStrataDokumen(senaraiHm2);
            senaraiDokumenkk = strataPtService.findGeranStrataDokumenKK(senaraiHm2);
            senaraiDokumenBSK = strataPtService.findGeranStrataDokumenBSK(senaraiHm2);
            senaraiDokumenBSKkk = strataPtService.findGeranStrataDokumenBSKK(senaraiHm2);
            senaraiDokumenPSK = strataPtService.findGeranStrataDokumenPSK(senaraiHm2);
        }

        ctx.removeWorkdata();
        addSimpleMessage("Dokumen Berjaya Dijana.");

        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strataCetakJana.jsp");

    }
    public static String[] Report_Name = {
        "UTILITIB2K_MLK.rdf",
        "UTILITIB3K_MLK.rdf",
        "UTILITIBSKDHDK_MLK.rdf",
        "UTILITIBSKDHKK_MLK.rdf",
        "UTILITIPSK_MLK.rdf",
        "UTILITIB4KDHDK_MLK.rdf",
        "UTILITIB4KDHKK_MLK.rdf",
        "UTILITIB4AKDHDK_MLK.rdf",
        "UTILITIB4AKDHKK_MLK.rdf"};
    public static String[] Report_NameNS = {
        "UTILITIB2K_NS.rdf",
        "UTILITIB3K_NS.rdf",
        "UTILITIBSKDHDK_NS.rdf",
        "UTILITIBSKDHKK_NS.rdf",
        "UTILITIPSK_NS.rdf",
        "UTILITIB4KDHDK_NS.rdf",
        "UTILITIB4KDHKK_NS.rdf",
        "UTILITIB4AKDHDK_NS.rdf",
        "UTILITIB4AKDHKK_NS.rdf"};
    public static String[] Report_Label = {
        "2K",
        "3K",
        "BSKDK",
        "BSKKK",
        "PSK",
        "4KDHD",
        "4KDHK",
        "4AKDH",
        "4AKKK"};

    private List<KandunganFolder> generateReport(KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, FolderDokumen fd) {

        idHakmilikInduk = getContext().getRequest().getParameter("idHakmilikInduk");
        idHakmilikStrata = getContext().getRequest().getParameter("idHakmilikStrata");
        String jana2k3k = getContext().getRequest().getParameter("2k3k");

        String parameterToPass = "";
        String parameterToPass2 = "";
        String valueToPass = "";
        String valueToPass2 = "";
        //Added by Aizuddin seems to me parameter and value not pass correctly
        String[] params = null;
        String[] values = null;

        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();

        //  List<CarianHakmilik> list = pc.getSenaraiHakmilik();
        if (!idHakmilikInduk.equals("")) {
            if (jana2k3k != null) {
                if (reportName.equals("UTILITIB2K_MLK.rdf") || reportName.equals("UTILITIB3K_MLK.rdf")) {
                    Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(idHakmilikInduk);
                    if (hmInduk.getNoVersiIndeksStrata() != null) {
                        if (hmInduk.getNoVersiIndeksStrata() == 1) {
                            LOG.info("~~~~~gune id hakmilik induk sahaja~~~~~" + hmInduk.getIdHakmilikInduk());
                            parameterToPass = "p_id_hakmilik";
                            valueToPass = hmInduk.getIdHakmilik();

                            Dokumen dokumenCarian = new Dokumen();
                            dokumenCarian.setFormat("application/pdf");
                            dokumenCarian.setInfoAudit(ia);
                            dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                            dokumenCarian.setKodDokumen(kodDokumen);
                            dokumenCarian.setNoVersi("1.0");
                            dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                            dokumenCarian.setHakmilik(valueToPass);
                            dokumenDAO.saveOrUpdate(dokumenCarian);
                            LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                            KandunganFolder kf1 = new KandunganFolder();
                            kf1.setFolder(fd);
                            LOG.info("id_folder : " + fd.getFolderId());
                            kf1.setDokumen(dokumenCarian);
                            LOG.info("id_dokumen : " + dokumenCarian);
                            kf1.setInfoAudit(ia);
                            kandunganFolderDAO.save(kf1);
                            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                                    + String.valueOf(dokumenCarian.getIdDokumen());
                            LOG.info("path : " + path);
                            reportUtil.generateReport(reportName,
                                    new String[]{parameterToPass, parameterToPass2},
                                    new String[]{valueToPass, valueToPass2},
                                    path, ia.getDimasukOleh());
                            LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                            dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                            dokumenDAO.update(dokumenCarian);
                            LOG.info("Finishh~~~~~~" + reportName);
                            senaraiKF.add(kf1);

                        }
                    }
                }
            }

            if (reportName.equals("UTILITIB4AKDHDK_MLK.rdf") || reportName.equals("UTILITIB4AKDHKK_MLK.rdf")
                    || reportName.equals("UTILITIBSKDHDK_MLK.rdf") || reportName.equals("UTILITIBSKDHKK_MLK.rdf")
                    || reportName.equals("UTILITIPSK_MLK.rdf")) {
                Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(idHakmilikInduk);

                for (Hakmilik hm : senaraiHakmilik) {
                    parameterToPass = "p_id_hakmilik";
                    valueToPass = hm.getIdHakmilik();

                    if (hm.getKodKategoriBangunan() != null && hm.getNoVersiDhde() == 1) {
                        if (hm.getKodKategoriBangunan().getKod().equals("P")) {
                            Dokumen dokumenCarian = new Dokumen();
                            dokumenCarian.setFormat("application/pdf");
                            dokumenCarian.setInfoAudit(ia);
                            dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                            dokumenCarian.setKodDokumen(kodDokumen);
                            dokumenCarian.setNoVersi("1.0");
                            String nobgn = StringUtils.leftPad(hm.getNoBangunan(), 3, '0');
                            valueToPass = hm.getIdHakmilik();
                            dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + hm.getIdHakmilikInduk() + nobgn + ")");
                            dokumenCarian.setHakmilik(hm.getIdHakmilikInduk());
                            dokumenDAO.saveOrUpdate(dokumenCarian);
                            LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                            KandunganFolder kf1 = new KandunganFolder();
                            kf1.setFolder(fd);
                            LOG.info("id_folder : " + fd.getFolderId());
                            kf1.setDokumen(dokumenCarian);
                            LOG.info("id_dokumen : " + dokumenCarian);
                            kf1.setInfoAudit(ia);
                            kandunganFolderDAO.save(kf1);
                            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                                    + String.valueOf(dokumenCarian.getIdDokumen());
                            LOG.info("path : " + path);
                            reportUtil.generateReport(reportName,
                                    new String[]{parameterToPass, parameterToPass2},
                                    new String[]{valueToPass, valueToPass2},
                                    path, ia.getDimasukOleh());
                            LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                            dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                            dokumenDAO.update(dokumenCarian);
                            LOG.info("Finishh~~~~~~" + reportName);
                            senaraiKF.add(kf1);
                        }
                    }
                }
            }
            if (reportName.equals("UTILITIBSKDHDK_MLK.rdf") || reportName.equals("UTILITIBSKDHKK_MLK.rdf")
                    || reportName.equals("UTILITIPSK_MLK.rdf") || reportName.equals("UTILITIB4KDHDK_MLK.rdf")
                    || reportName.equals("UTILITIB4KDHKK_MLK.rdf")) {

                for (Hakmilik hm : senaraiHakmilik) {
                    parameterToPass = "p_id_hakmilik";
                    valueToPass = hm.getIdHakmilik();

                    if (hm.getKodKategoriBangunan() != null && hm.getNoVersiDhde() == 1) {
                        if (!hm.getKodKategoriBangunan().getKod().equals("P")) {
                            Dokumen dokumenCarian = new Dokumen();
                            dokumenCarian.setFormat("application/pdf");
                            dokumenCarian.setInfoAudit(ia);
                            dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                            dokumenCarian.setKodDokumen(kodDokumen);
                            dokumenCarian.setNoVersi("1.0");
                            valueToPass = hm.getIdHakmilik();
                            dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                            dokumenCarian.setHakmilik(valueToPass);
                            dokumenDAO.saveOrUpdate(dokumenCarian);
                            LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                            KandunganFolder kf1 = new KandunganFolder();
                            kf1.setFolder(fd);
                            LOG.info("id_folder : " + fd.getFolderId());
                            kf1.setDokumen(dokumenCarian);
                            LOG.info("id_dokumen : " + dokumenCarian);
                            kf1.setInfoAudit(ia);
                            kandunganFolderDAO.save(kf1);
                            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                                    + String.valueOf(dokumenCarian.getIdDokumen());
                            LOG.info("path : " + path);
                            reportUtil.generateReport(reportName,
                                    new String[]{parameterToPass, parameterToPass2},
                                    new String[]{valueToPass, valueToPass2},
                                    path, ia.getDimasukOleh());
                            LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                            dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                            dokumenDAO.update(dokumenCarian);
                            LOG.info("Finishh~~~~~~" + reportName);
                            senaraiKF.add(kf1);
                        }
                    }
                }
            }
        }

        if (idHakmilikInduk.equals("")) {
            String idHakmilik = getContext().getRequest().getParameter("bilHakmilik");
            if (idHakmilik != null) {
                bilHakmilik = Integer.parseInt(idHakmilik);
            } else {
                bilHakmilik = 5;
            }
            for (int i = 0; i < bilHakmilik; i++) {
                String hmParam = getContext().getRequest().getParameter("idHakmilikStrata" + i);
                if (!hmParam.equals("")) {
                    Hakmilik hm = strataPtService.findInfoByIdHakmilik(hmParam);
                    if (hm != null) {
                        if (hm.getNoVersiDhde() == 1) {
                            if (reportName.equals("UTILITIBSKDHDK_MLK.rdf") || reportName.equals("UTILITIBSKDHKK_MLK.rdf")
                                    || reportName.equals("UTILITIPSK_MLK.rdf")
                                    || reportName.equals("UTILITIB4KDHDK_MLK.rdf") || reportName.equals("UTILITIB4KDHKK_MLK.rdf")) {

                                if (hm.getKodKategoriBangunan().getKod().equals("B") || hm.getKodKategoriBangunan().getKod().equals("L")) {
                                    LOG.info("gune id hakmilik panjang");
                                    parameterToPass = "p_id_hakmilik";
                                    valueToPass = hmParam;

                                    Dokumen dokumenCarian = new Dokumen();
                                    dokumenCarian.setFormat("application/pdf");
                                    dokumenCarian.setInfoAudit(ia);
                                    dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                                    dokumenCarian.setKodDokumen(kodDokumen);
                                    dokumenCarian.setNoVersi("1.0");
                                    dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                                    dokumenCarian.setHakmilik(valueToPass);
                                    dokumenDAO.saveOrUpdate(dokumenCarian);
                                    LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                                    KandunganFolder kf1 = new KandunganFolder();
                                    kf1.setFolder(fd);
                                    LOG.info("id_folder : " + fd.getFolderId());
                                    kf1.setDokumen(dokumenCarian);
                                    LOG.info("id_dokumen : " + dokumenCarian);
                                    kf1.setInfoAudit(ia);
                                    kandunganFolderDAO.save(kf1);
                                    String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                                            + String.valueOf(dokumenCarian.getIdDokumen());
                                    LOG.info("path : " + path);
                                    reportUtil.generateReport(reportName,
                                            new String[]{parameterToPass, parameterToPass2},
                                            new String[]{valueToPass, valueToPass2},
                                            path, ia.getDimasukOleh());
                                    LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                                    dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                                    dokumenDAO.update(dokumenCarian);
                                    LOG.info("Finishh~~~~~~" + reportName);
                                    senaraiKF.add(kf1);
                                }
                            }
                        }
                    }
                }
            }
        }

        //To-do by Aizuddin (if generateReport2 does not work pass url report here)
        LOG.info("senarai kandungan" + senaraiKF);
        LOG.info("senarai kandungan" + senaraiKF.size());
        return senaraiKF;
    }

    private List<KandunganFolder> generateReportNS(KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, FolderDokumen fd) {

        idHakmilikInduk = getContext().getRequest().getParameter("idHakmilikInduk");
        idHakmilikStrata = getContext().getRequest().getParameter("idHakmilikStrata");

        String parameterToPass = "";
        String parameterToPass2 = "";
        String valueToPass = "";
        String valueToPass2 = "";
        //Added by Aizuddin seems to me parameter and value not pass correctly
        String[] params = null;
        String[] values = null;

        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();

        //  List<CarianHakmilik> list = pc.getSenaraiHakmilik();
        if (!idHakmilikInduk.equals("")) {
            if (reportName.equals("UTILITIB2K_NS.rdf") || reportName.equals("UTILITIB3K_NS.rdf")) {
                Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(idHakmilikInduk);
                if (hmInduk.getNoVersiIndeksStrata() != null) {
                    if (hmInduk.getNoVersiIndeksStrata() != 0) {
                        LOG.info("~~~~~gune id hakmilik induk sahaja~~~~~" + hmInduk.getIdHakmilikInduk());
                        parameterToPass = "p_id_hakmilik";
                        valueToPass = hmInduk.getIdHakmilik();

                        Dokumen dokumenCarian = new Dokumen();
                        dokumenCarian.setFormat("application/pdf");
                        dokumenCarian.setInfoAudit(ia);
                        dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                        dokumenCarian.setKodDokumen(kodDokumen);
                        dokumenCarian.setNoVersi("1.0");
                        dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                        dokumenCarian.setHakmilik(valueToPass);
                        dokumenDAO.saveOrUpdate(dokumenCarian);
                        LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                        KandunganFolder kf1 = new KandunganFolder();
                        kf1.setFolder(fd);
                        LOG.info("id_folder : " + fd.getFolderId());
                        kf1.setDokumen(dokumenCarian);
                        LOG.info("id_dokumen : " + dokumenCarian);
                        kf1.setInfoAudit(ia);
                        kandunganFolderDAO.save(kf1);
                        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                                + String.valueOf(dokumenCarian.getIdDokumen());
                        LOG.info("path : " + path);
                        reportUtil.generateReport(reportName,
                                new String[]{parameterToPass, parameterToPass2},
                                new String[]{valueToPass, valueToPass2},
                                path, ia.getDimasukOleh());
                        LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                        dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                        dokumenDAO.update(dokumenCarian);
                        LOG.info("Finishh~~~~~~" + reportName);
                        senaraiKF.add(kf1);

                    }
                }
            }

            if (reportName.equals("UTILITIB4AKDHDK_NS.rdf") || reportName.equals("UTILITIB4AKDHKK_NS.rdf")) {
                Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(idHakmilikInduk);

                for (Hakmilik hm : senaraiHakmilik) {
                    parameterToPass = "p_id_hakmilik";
                    valueToPass = hm.getIdHakmilik();

                    Dokumen dokumenCarian = new Dokumen();
                    dokumenCarian.setFormat("application/pdf");
                    dokumenCarian.setInfoAudit(ia);
                    dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                    dokumenCarian.setKodDokumen(kodDokumen);
                    dokumenCarian.setNoVersi("1.0");
                    if (hm.getKodKategoriBangunan().getKod().equals("P")) {
                        String nobgn = StringUtils.leftPad(hm.getNoBangunan(), 3, '0');
                        valueToPass = hm.getIdHakmilik();
                        dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + hm.getIdHakmilikInduk() + nobgn + ")");
                        dokumenCarian.setHakmilik(hm.getIdHakmilikInduk());
                    }
                    dokumenDAO.saveOrUpdate(dokumenCarian);
                    LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                    KandunganFolder kf1 = new KandunganFolder();
                    kf1.setFolder(fd);
                    LOG.info("id_folder : " + fd.getFolderId());
                    kf1.setDokumen(dokumenCarian);
                    LOG.info("id_dokumen : " + dokumenCarian);
                    kf1.setInfoAudit(ia);
                    kandunganFolderDAO.save(kf1);
                    String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                            + String.valueOf(dokumenCarian.getIdDokumen());
                    LOG.info("path : " + path);
                    reportUtil.generateReport(reportName,
                            new String[]{parameterToPass, parameterToPass2},
                            new String[]{valueToPass, valueToPass2},
                            path, ia.getDimasukOleh());
                    LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                    dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                    dokumenDAO.update(dokumenCarian);
                    LOG.info("Finishh~~~~~~" + reportName);
                    senaraiKF.add(kf1);
                }
            }
        }

        if (idHakmilikInduk.equals("")) {
            String idHakmilik = getContext().getRequest().getParameter("bilHakmilik");
            if (idHakmilik != null) {
                bilHakmilik = Integer.parseInt(idHakmilik);
            } else {
                bilHakmilik = 5;
            }
            for (int i = 0; i < bilHakmilik; i++) {
                String hmParam = getContext().getRequest().getParameter("idHakmilikStrata" + i);
                if (!hmParam.equals("")) {
                    Hakmilik hm = strataPtService.findInfoByIdHakmilik(hmParam);
                    if (hm != null) {
                        if (reportName.equals("UTILITIBSKDHDK_NS.rdf") || reportName.equals("UTILITIBSKDHKK_MLNS.rdf")
                                || reportName.equals("UTILITIPSK_NS.rdf")
                                || reportName.equals("UTILITIB4KDHDK_NS.rdf") || reportName.equals("UTILITIB4KDHKK_NS.rdf")) {

                            if (hm.getKodKategoriBangunan().getKod().equals("B") || hm.getKodKategoriBangunan().getKod().equals("L")) {
                                LOG.info("gune id hakmilik panjang");
                                parameterToPass = "p_id_hakmilik";
                                valueToPass = hmParam;

                                Dokumen dokumenCarian = new Dokumen();
                                dokumenCarian.setFormat("application/pdf");
                                dokumenCarian.setInfoAudit(ia);
                                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                                dokumenCarian.setKodDokumen(kodDokumen);
                                dokumenCarian.setNoVersi("1.0");
                                dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                                dokumenCarian.setHakmilik(valueToPass);
                                dokumenDAO.saveOrUpdate(dokumenCarian);
                                LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                                KandunganFolder kf1 = new KandunganFolder();
                                kf1.setFolder(fd);
                                LOG.info("id_folder : " + fd.getFolderId());
                                kf1.setDokumen(dokumenCarian);
                                LOG.info("id_dokumen : " + dokumenCarian);
                                kf1.setInfoAudit(ia);
                                kandunganFolderDAO.save(kf1);
                                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                                        + String.valueOf(dokumenCarian.getIdDokumen());
                                LOG.info("path : " + path);
                                reportUtil.generateReport(reportName,
                                        new String[]{parameterToPass, parameterToPass2},
                                        new String[]{valueToPass, valueToPass2},
                                        path, ia.getDimasukOleh());
                                LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                                dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                                dokumenDAO.update(dokumenCarian);
                                LOG.info("Finishh~~~~~~" + reportName);
                                senaraiKF.add(kf1);
                            }
                        }
                    }
                }
            }
        }

        //To-do by Aizuddin (if generateReport2 does not work pass url report here)
        LOG.info("senarai kandungan" + senaraiKF);
        LOG.info("senarai kandungan" + senaraiKF.size());
        return senaraiKF;
    }

    public Resolution viewSejarahPaparan() {
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        senaraiDokumenCapai = dokumenCapaianService.findByIdDokumenAndPD(idDokumen);

        return new JSP("daftar/utiliti/view_sejarah_paparan.jsp").addParameter("popup", "true");

    }

    public String getIdHakmilikInduk() {
        return idHakmilikInduk;

    }

    public void setIdHakmilikInduk(String idHakmilikInduk) {
        this.idHakmilikInduk = idHakmilikInduk;

    }

    public String getIdHakmilikStrata() {
        return idHakmilikStrata;

    }

    public void setIdHakmilikStrata(String idHakmilikStrata) {
        this.idHakmilikStrata = idHakmilikStrata;

    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;

    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;

    }

    public String getSbb_cetakan_semula() {
        return sbb_cetakan_semula;

    }

    public void setSbb_cetakan_semula(String sbb_cetakan_semula) {
        this.sbb_cetakan_semula = sbb_cetakan_semula;

    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;

    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;

    }

    public List<DokumenCapaian> getSenaraiDokumenCapai() {
        return senaraiDokumenCapai;

    }

    public void setSenaraiDokumenCapai(List<DokumenCapaian> senaraiDokumenCapai) {
        this.senaraiDokumenCapai = senaraiDokumenCapai;

    }

    public List<KandunganFolder> getSenaraiKandunganFolder() {
        return senaraiKandunganFolder;

    }

    public void setSenaraiKandunganFolder(List<KandunganFolder> senaraiKandunganFolder) {
        this.senaraiKandunganFolder = senaraiKandunganFolder;

    }

    public List<Hakmilik> getSenaraiHakmilikStrata() {
        return senaraiHakmilikStrata;

    }

    public void setSenaraiHakmilikStrata(List<Hakmilik> senaraiHakmilikStrata) {
        this.senaraiHakmilikStrata = senaraiHakmilikStrata;

    }

    public List<Dokumen> getSenaraiDokumen2K3K() {
        return senaraiDokumen2K3K;

    }

    public void setSenaraiDokumen2K3K(List<Dokumen> senaraiDokumen2K3K) {
        this.senaraiDokumen2K3K = senaraiDokumen2K3K;

    }

    public List<Hakmilik> getHakmilikStrata() {
        return hakmilikStrata;
    }

    public void setHakmilikStrata(List<Hakmilik> hakmilikStrata) {
        this.hakmilikStrata = hakmilikStrata;
    }

    public List<Dokumen> getSenaraiDokumenProv() {
        return senaraiDokumenProv;
    }

    public void setSenaraiDokumenProv(List<Dokumen> senaraiDokumenProv) {
        this.senaraiDokumenProv = senaraiDokumenProv;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public List<HakmilikTukarGantiStrata> getSenaraiTkrganti() {
        return senaraiTkrganti;
    }

    public void setSenaraiTkrganti(List<HakmilikTukarGantiStrata> senaraiTkrganti) {
        this.senaraiTkrganti = senaraiTkrganti;
    }

    public List<HakmilikTukarGantiStrata> getSenaraiTkrganti2k3k() {
        return senaraiTkrganti2k3k;
    }

    public void setSenaraiTkrganti2k3k(List<HakmilikTukarGantiStrata> senaraiTkrganti2k3k) {
        this.senaraiTkrganti2k3k = senaraiTkrganti2k3k;
    }

    public List<Hakmilik> getSenaraiHakmilikProv() {
        return senaraiHakmilikProv;
    }

    public void setSenaraiHakmilikProv(List<Hakmilik> senaraiHakmilikProv) {
        this.senaraiHakmilikProv = senaraiHakmilikProv;
    }

    public List<HakmilikTukarGantiStrata> getSenaraiTkrgantiProv() {
        return senaraiTkrgantiProv;
    }

    public void setSenaraiTkrgantiProv(List<HakmilikTukarGantiStrata> senaraiTkrgantiProv) {
        this.senaraiTkrgantiProv = senaraiTkrgantiProv;
    }

    public List<Dokumen> getSenaraiDokumenBSK() {
        return senaraiDokumenBSK;
    }

    public void setSenaraiDokumenBSK(List<Dokumen> senaraiDokumenBSK) {
        this.senaraiDokumenBSK = senaraiDokumenBSK;
    }

    public List<Dokumen> getSenaraiDokumenPSK() {
        return senaraiDokumenPSK;
    }

    public void setSenaraiDokumenPSK(List<Dokumen> senaraiDokumenPSK) {
        this.senaraiDokumenPSK = senaraiDokumenPSK;
    }

    public List<Dokumen> getSenaraiDokumenkk() {
        return senaraiDokumenkk;
    }

    public void setSenaraiDokumenkk(List<Dokumen> senaraiDokumenkk) {
        this.senaraiDokumenkk = senaraiDokumenkk;
    }

    public List<Dokumen> getSenaraiDokumenBSKkk() {
        return senaraiDokumenBSKkk;
    }

    public void setSenaraiDokumenBSKkk(List<Dokumen> senaraiDokumenBSKkk) {
        this.senaraiDokumenBSKkk = senaraiDokumenBSKkk;
    }
}
