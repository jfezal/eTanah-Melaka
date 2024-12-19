/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.AduanLokasi;
import etanah.model.BarangRampasan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.util.Date;
import etanah.dao.DokumenDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodNegeri;
import etanah.model.Dokumen;
import etanah.model.KandunganFolder;
import etanah.model.Notis;
import etanah.model.OperasiAgensi;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaanPasukan;
import etanah.model.PegawaiPenyiasat;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanSaksi;
import etanah.service.EnforceService;

import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import java.text.SimpleDateFormat;
import etanah.service.common.EnforcementService;
import java.util.ArrayList;
import org.hibernate.Session;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/draf_siasatan")
public class DraftSiasatanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(DraftSiasatanActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    OperasiPenguatkuasaanPasukanDAO operasiPenguatkuasaanPasukanDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private AduanLokasi aduanLokasi;
    private KodNegeri kodNegeri;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanRujukanLuar permohonanRujukanLuar1;
    private Notis notis;
    private List<BarangRampasan> senaraiBarangRampasan;
    private List<PermohonanRujukanLuar> senaraiMahkamah;
    private String idKertas;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private List<OperasiPenguatkuasaanPasukan> senaraiPasukan;
    private List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak;
    private String namaKetua;
    private List<PermohonanSaksi> senaraiPermohonanSaksi;
    private String pasukan;
    private OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan;
    private PermohonanSaksi permohonanSaksi;
    private String noPengenalanKetua;
    private List<OperasiPenguatkuasaanPasukan> senaraiPasukanSaksi;
    private PegawaiPenyiasat pegawaiSiasat;
    private String kronologiKes;
    private List<PermohonanKertasKandungan> senaraiKronologiKes;
    private String rowCount;
    private PermohonanKertas kertasKronologiKes;
    private PermohonanKertasKandungan defaultKronologiKes;
    private List<PermohonanKertasKandungan> listAllKronologiKes;
    private List<OperasiPenguatkuasaan> listOp;
    private List<PegawaiPenyiasat> listPegawaiSiasat;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private List<KandunganFolder> listDokumen;
    private KandunganFolder kf;
    private KandunganFolder folderDokumen;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private Pengguna pengguna;
    private String noRujukan;
    private String jam;
    private String minit;
    private String ampm;
    private String idOperasi;
    private OperasiAgensi operasiAgensi;
    private String lokasi;
    private String idDokumen;
    private String idOperasiAgensi;
    private OperasiPenguatkuasaan operasiPenguatkuasaanIP;
    private List<KandunganFolder> senaraiDokumen;
    private String idPermohonanAsal;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    public Resolution popupViewLaporanOperasi() {
        idOperasiAgensi = getContext().getRequest().getParameter("idOperasiAgensi");
        operasiAgensi = enforcementService.findOperasiByIdOPAgensi(Long.parseLong(idOperasiAgensi));
        return new JSP("penguatkuasaan/view_laporan_polis_detail.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        ctx = (etanahActionBeanContext) getContext();
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String kodN = ctx.getKodNegeri();
        kodNegeri = new KodNegeri();
        kodNegeri.setKod(kodN);
        kodNegeri = kodNegeriDAO.findById(kodN);

        if (idPermohonan != null) {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    if (permohonan.getPermohonanSebelum() != null) {
                        Permohonan p = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                        idPermohonanAsal = p.getIdPermohonan();
                        logger.info("p :::" + p.getIdPermohonan());
                        if (p != null) {
                            if (p.getPermohonanSebelum() != null) {
                                Permohonan p1 = permohonanDAO.findById(p.getPermohonanSebelum().getIdPermohonan());
                                if (p1 != null) {
                                    logger.info("p1 :::" + p1.getIdPermohonan());
                                    idPermohonanAsal = p1.getIdPermohonan();
                                }
                            }
                        }
                    }
                    logger.info("::: idPermohonanAsal : " + idPermohonanAsal);
                    listOp = enforceService.findListLaporanOperasi(idPermohonan);
                    if (listOp.size() == 0) {
                        if (permohonan.getPermohonanSebelum() != null) {
                            listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());

                            //get list dokumen for senarai laporan polis
                            listDokumen = enforcementService.getListDokumen(permohonan.getPermohonanSebelum().getFolderDokumen().getFolderId(), "LP");
                            System.out.println("size list dokumen (id sebelum): " + listDokumen.size());
                        }
                    } else {
                        listDokumen = enforcementService.getListDokumen(permohonan.getFolderDokumen().getFolderId(), "LP");
                        System.out.println("size list dokumen : " + listDokumen.size());
                        if (listDokumen.size() != 0) {
                            folderDokumen = listDokumen.get(0);
                        }
                    }
                    logger.info("listOp size : " + listOp.size());

                    if (listDokumen.size() == 0) {
                        Permohonan p = permohonanDAO.findById(idPermohonanAsal);
                        if (p != null) {
                            listDokumen = enforcementService.getListDokumen(p.getFolderDokumen().getFolderId(), "LP");
                        }
                    }
                } else {
                    //for Melaka, only pass idPermohonan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
                    permohonanRujukanLuar1 = enforceService.findLaporanPolisByIdpermohonan(idPermohonan);
                }
            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
                permohonanRujukanLuar1 = enforceService.findLaporanPolisByIdpermohonan(idPermohonan);
            }

            permohonanKertas = enforcementService.findByKodIdPermohonan(permohonan.getIdPermohonan());
            aduanLokasi = enforceService.findAduanLokasiByIdPermohonan(idPermohonan);
            senaraiMahkamah = enforcementService.findMahkamahByIDPermohonan(idPermohonan);

            notis = enforcementService.findSamanByIdmohon(idPermohonan);

            if (operasiPenguatkuasaan != null) {
                senaraiBarangRampasan = enforceService.findByIDOperasi(operasiPenguatkuasaan.getIdOperasi());
            }


            senaraiPasukan = new ArrayList<OperasiPenguatkuasaanPasukan>();
            listPegawaiSiasat = new ArrayList<PegawaiPenyiasat>();
            senaraiOksIp = new ArrayList<AduanOrangKenaSyak>();

            if (operasiPenguatkuasaan != null) {
                senaraiPasukan = enforceService.getSenaraiPasukan(operasiPenguatkuasaan.getIdOperasi());
                senaraiPasukanSaksi = enforcementService.findListByIdPasukan(operasiPenguatkuasaan.getIdOperasi());
                logger.info("size senaraiPasukanSaksi : " + senaraiPasukanSaksi.size());
                try {
                    if (senaraiPasukan.size() != 0) {
                        for (int i = 0; i < senaraiPasukan.size(); i++) {
                            if (senaraiPasukan.get(i).getKodPerananOperasi().getKod().equalsIgnoreCase("K")) {
                                namaKetua = senaraiPasukan.get(i).getNama();
                                noPengenalanKetua = senaraiPasukan.get(i).getNoKp();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.getCause();
                }


            } else {
                senaraiPasukan = enforceService.getSenaraiPasukanForMultipleOp(idPermohonan);
                senaraiPasukanSaksi = enforceService.findListByIdPasukanForMultipleOp(idPermohonan);
                logger.info("size senaraiPasukan for multiple op: " + senaraiPasukan.size());
                logger.info("size senaraiPasukanSaksi for multiple op: " + senaraiPasukanSaksi.size());

            }

            senaraiAduanOrangKenaSyak = enforceService.findByID(idPermohonan);
            senaraiPermohonanSaksi = enforceService.findByIDSaksi(idPermohonan);
            pegawaiSiasat = enforcementService.findPengguna(idPermohonan);


            senaraiOksIp = enforceService.getListAduanOrangkenaSyak(idPermohonan);
            logger.info("size senaraiOksIp : " + senaraiOksIp.size());

            if (senaraiOksIp.size() != 0) {
                Long idOperasi = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                System.out.println("id operasi : " + idOperasi);
                listPegawaiSiasat = enforcementService.findSenaraiPegawaiPenyiasatIp(idPermohonan);
                logger.info("size listPegawaiSiasat : " + listPegawaiSiasat.size());

                senaraiPasukanSaksi = enforcementService.findSenaraiSaksiPasukanIp(idPermohonan, idOperasi);
                senaraiPasukan = enforcementService.findSenaraiPasukanIp(idPermohonan, idOperasi);
                logger.info("size senaraiPasukanSaksi : " + senaraiPasukanSaksi.size());

                operasiPenguatkuasaanIP = enforceService.findByIdOp(idOperasi);

                if (listPegawaiSiasat.isEmpty()) {
                    if (permohonan.getPermohonanSebelum() != null) {
                        listPegawaiSiasat = enforcementService.findSenaraiPegawaiPenyiasatIp(permohonan.getPermohonanSebelum().getIdPermohonan());
                        logger.info("size listPegawaiSiasat (using id permohonan sebelum): " + listPegawaiSiasat.size());
                    }
                }

                if (permohonanKertas == null) {
                    permohonanKertas = enforcementService.findByKodIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                }

            }

            if (permohonanKertas != null) {
                permohonanKertasKandungan = enforcementService.findByKodIdKertas(permohonanKertas.getIdKertas());

            }

            rowCount = "0";

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //Kronologi kes
                kertasKronologiKes = enforceService.findByIdKrts(idPermohonan, "KRONO");

                if (kertasKronologiKes == null) {
                    if (permohonan.getPermohonanSebelum() != null) {
                        kertasKronologiKes = enforceService.findByIdKrts(permohonan.getPermohonanSebelum().getIdPermohonan(), "KRONO");
                        senaraiDokumen = enforceService.getListDokumen(permohonan.getPermohonanSebelum().getFolderDokumen().getFolderId());
                        System.out.println("senarai dokumen : " + senaraiDokumen.size());
                    }
                }

                if (kertasKronologiKes != null) {
                    //for default kronologi
                    defaultKronologiKes = enforceService.findByIdBil(kertasKronologiKes.getIdKertas());
                    if (defaultKronologiKes != null) {
                        kronologiKes = defaultKronologiKes.getKandungan();
                    }
                    //for senarai kronologi kes - all except bil 1
                    senaraiKronologiKes = enforceService.findAllKandungan(kertasKronologiKes.getIdKertas());
                    logger.info("size senaraiKronologiKes : " + senaraiKronologiKes.size());

                    //for senarai kronologi kes - all 
                    listAllKronologiKes = enforceService.listKronologiKes(kertasKronologiKes.getIdKertas());
                    logger.info("size listAllKronologiKes : " + listAllKronologiKes.size());

                    rowCount = String.valueOf(senaraiKronologiKes.size());
                    logger.info("row count : " + rowCount);
                }

            }


        }
    }

    public Resolution simpan() {

        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        System.out.println("permohonan :" + permohonan.getIdPermohonan());
        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();

        }
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setTajuk("Draf Kertas Siasatan");

        permohonanKertasKandungan.setKertas(permohonanKertas);
        permohonanKertasKandungan.setCawangan(cawangan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonanKertas.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanKertas.setInfoAudit(ia);

        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        InfoAudit ia2 = new InfoAudit();
        ia2.setDimasukOleh(peng);
        ia2.setTarikhMasuk(new java.util.Date());

        permohonanKertasKandungan.setInfoAudit(ia2);

        if ("04".equals(conf.getProperty("kodNegeri"))) {

            InfoAudit infoAudit = new InfoAudit();

            if (kertasKronologiKes == null) {
                kertasKronologiKes = new PermohonanKertas();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = kertasKronologiKes.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }

            kertasKronologiKes.setPermohonan(permohonan);
            kertasKronologiKes.setCawangan(cawangan);
            kertasKronologiKes.setTajuk("KRONOLOGI KES");
            kertasKronologiKes.setInfoAudit(infoAudit);
            kertasKronologiKes.setKodDokumen(kodDokumenDAO.findById("KRONO"));
            enforceService.simpanPermohonanKertas(kertasKronologiKes);

            if (defaultKronologiKes == null) {
                defaultKronologiKes = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = defaultKronologiKes.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            defaultKronologiKes.setKertas(kertasKronologiKes);
            defaultKronologiKes.setCawangan(cawangan);
            defaultKronologiKes.setInfoAudit(infoAudit);
            defaultKronologiKes.setBil(1);
            defaultKronologiKes.setKandungan(kronologiKes);
            enforceService.simpanPermohonanKertasKandungan(defaultKronologiKes);


            //to save senaraiKronologiKes list
            senaraiKronologiKes = enforceService.findAllKandungan(kertasKronologiKes.getIdKertas());
            logger.info("size senaraiKronologiKes (at simpan) : " + senaraiKronologiKes.size());

            int b = 2;
            System.out.println("rowcount : " + rowCount);
            if (rowCount != null && !rowCount.isEmpty()) {
                for (int i = 1; i <= Integer.parseInt(rowCount); i++) {
                    PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    if (senaraiKronologiKes.size() != 0 && i <= senaraiKronologiKes.size()) {
                        Long id = senaraiKronologiKes.get(i - 1).getIdKandungan();
                        if (id != null) {
                            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                            infoAudit = permohonanKertasKandungan1.getInfoAudit();
                            infoAudit.setDiKemaskiniOleh(peng);
                            infoAudit.setTarikhKemaskini(new java.util.Date());
                        }
                    } else {
                        permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                        infoAudit.setDimasukOleh(peng);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                    }
                    permohonanKertasKandungan1.setKertas(kertasKronologiKes);
                    permohonanKertasKandungan1.setBil(b);
                    String kandungan = getContext().getRequest().getParameter("kandungan1" + i);
                    permohonanKertasKandungan1.setKandungan(kandungan);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk(b + ".1");
                    permohonanKertasKandungan1.setInfoAudit(infoAudit);
                    enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                    b++;
                }
            }

        }

        enforcementService.simpanKertas(permohonanKertas, permohonanKertasKandungan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new JSP("penguatkuasaan/maklumat_kertas_siasatan.jsp").addParameter("tab", "true");

    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        idKertas = getContext().getRequest().getParameter("idKertas");
        permohonanKertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
        permohonanKertasKandungan = enforcementService.findByKodIdKertas(permohonanKertas.getIdKertas());

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            permohonanKertas.setDokumen(null);
            InfoAudit ia = permohonanKertas.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertas.setInfoAudit(ia);
            enforcementService.simpanKertas(permohonanKertas, permohonanKertasKandungan);

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");
    }

    /* Maklumat saksi (combine with kertas siasatan): add,edit,delete*/
    public Resolution simpanSaksiPasukan() {
        logger.info("-------- simpan saksi dari senarai pasukan --------");
        String idOperasiPenguatkuasaanPasukan = getContext().getRequest().getParameter("idPasukan");

        try {
            if (idOperasiPenguatkuasaanPasukan != null) {
                operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukanDAO.findById(Long.parseLong(idOperasiPenguatkuasaanPasukan));
            }

            if (operasiPenguatkuasaanPasukan != null) {
                InfoAudit ia = operasiPenguatkuasaanPasukan.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());

                operasiPenguatkuasaanPasukan.setSaksi("Y");
                enforceService.simpanOperasiPenguatkuasaanPasukan(operasiPenguatkuasaanPasukan);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");

    }

    public Resolution checkExistingRecordSaksi() {
        logger.info("--------checkExistingRecordSaksi--------");
        String result = "";
        String idPasukan = getContext().getRequest().getParameter("idPasukan");

        if (idPasukan != null) {
            operasiPenguatkuasaanPasukan = enforcementService.findByIdPasukan(Long.parseLong(idPasukan));
            if (operasiPenguatkuasaanPasukan != null) {
                logger.info("----------record saksi exist-------");
                result = "Exist";
            }
        }

        return new StreamingResolution("text/plain", result);
    }

    public Resolution deleteSaksi() {
        logger.info("--------deleteSaksi--------");

        String idPasukan = getContext().getRequest().getParameter("idPasukan");
        operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukanDAO.findById(Long.parseLong(idPasukan));

        if (operasiPenguatkuasaanPasukan != null) {
            InfoAudit ia = operasiPenguatkuasaanPasukan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());

            operasiPenguatkuasaanPasukan.setSaksi("T");
            enforceService.simpanOperasiPenguatkuasaanPasukan(operasiPenguatkuasaanPasukan);
        }
        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");

    }

    public Resolution deleteSingle() {
        logger.info("--------------deleteSingle--------------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        logger.info("--------------idKandungan : --------------" + idKandungan);
        permohonanKertasKandungan = new PermohonanKertasKandungan();

        try {
            if (idKandungan != null) {
                permohonanKertasKandungan = enforceService.findByIdKKand(idKandungan);
            }
            enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }

        addSimpleMessage("Rekod telah berjaya dihapuskan");
        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");
    }

    public Resolution deleteRakamanPercakapan() {
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        logger.info("--------------deleteRakamanPercakapan : --------------" + id);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public PermohonanKertasKandunganDAO getPermohonanKertasKandunganDAO() {
        return permohonanKertasKandunganDAO;
    }

    public void setPermohonanKertasKandunganDAO(PermohonanKertasKandunganDAO permohonanKertasKandunganDAO) {
        this.permohonanKertasKandunganDAO = permohonanKertasKandunganDAO;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

//    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
//        return permohonanRujukanLuar;
//    }
//
//    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
//        this.permohonanRujukanLuar = permohonanRujukanLuar;
//    }
    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar1() {
        return permohonanRujukanLuar1;
    }

    public void setPermohonanRujukanLuar1(PermohonanRujukanLuar permohonanRujukanLuar1) {
        this.permohonanRujukanLuar1 = permohonanRujukanLuar1;
    }

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<PermohonanRujukanLuar> getSenaraiMahkamah() {
        return senaraiMahkamah;
    }

    public void setSenaraiMahkamah(List<PermohonanRujukanLuar> senaraiMahkamah) {
        this.senaraiMahkamah = senaraiMahkamah;
    }

    public Resolution refreshpage() {
        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");
    }

    public Resolution refreshDiv() {
        rehydrate();
        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukan() {
        return senaraiPasukan;
    }

    public void setSenaraiPasukan(List<OperasiPenguatkuasaanPasukan> senaraiPasukan) {
        this.senaraiPasukan = senaraiPasukan;
    }

    public List<AduanOrangKenaSyak> getSenaraiAduanOrangKenaSyak() {
        return senaraiAduanOrangKenaSyak;
    }

    public void setSenaraiAduanOrangKenaSyak(List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak) {
        this.senaraiAduanOrangKenaSyak = senaraiAduanOrangKenaSyak;
    }

    public String getNamaKetua() {
        return namaKetua;
    }

    public void setNamaKetua(String namaKetua) {
        this.namaKetua = namaKetua;
    }

    public List<PermohonanSaksi> getSenaraiPermohonanSaksi() {
        return senaraiPermohonanSaksi;
    }

    public void setSenaraiPermohonanSaksi(List<PermohonanSaksi> senaraiPermohonanSaksi) {
        this.senaraiPermohonanSaksi = senaraiPermohonanSaksi;
    }

    public String getPasukan() {
        return pasukan;
    }

    public void setPasukan(String pasukan) {
        this.pasukan = pasukan;
    }

    public OperasiPenguatkuasaanPasukan getOperasiPenguatkuasaanPasukan() {
        return operasiPenguatkuasaanPasukan;
    }

    public void setOperasiPenguatkuasaanPasukan(OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan) {
        this.operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukan;
    }

    public PermohonanSaksi getPermohonanSaksi() {
        return permohonanSaksi;
    }

    public void setPermohonanSaksi(PermohonanSaksi permohonanSaksi) {
        this.permohonanSaksi = permohonanSaksi;
    }

    public String getNoPengenalanKetua() {
        return noPengenalanKetua;
    }

    public void setNoPengenalanKetua(String noPengenalanKetua) {
        this.noPengenalanKetua = noPengenalanKetua;
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukanSaksi() {
        return senaraiPasukanSaksi;
    }

    public void setSenaraiPasukanSaksi(List<OperasiPenguatkuasaanPasukan> senaraiPasukanSaksi) {
        this.senaraiPasukanSaksi = senaraiPasukanSaksi;
    }

    public PegawaiPenyiasat getPegawaiSiasat() {
        return pegawaiSiasat;
    }

    public void setPegawaiSiasat(PegawaiPenyiasat pegawaiSiasat) {
        this.pegawaiSiasat = pegawaiSiasat;
    }

    public String getKronologiKes() {
        return kronologiKes;
    }

    public void setKronologiKes(String kronologiKes) {
        this.kronologiKes = kronologiKes;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public List<PermohonanKertasKandungan> getSenaraiKronologiKes() {
        return senaraiKronologiKes;
    }

    public void setSenaraiKronologiKes(List<PermohonanKertasKandungan> senaraiKronologiKes) {
        this.senaraiKronologiKes = senaraiKronologiKes;
    }

    public PermohonanKertas getKertasKronologiKes() {
        return kertasKronologiKes;
    }

    public void setKertasKronologiKes(PermohonanKertas kertasKronologiKes) {
        this.kertasKronologiKes = kertasKronologiKes;
    }

    public PermohonanKertasKandungan getDefaultKronologiKes() {
        return defaultKronologiKes;
    }

    public void setDefaultKronologiKes(PermohonanKertasKandungan defaultKronologiKes) {
        this.defaultKronologiKes = defaultKronologiKes;
    }

    public String getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(String idKertas) {
        this.idKertas = idKertas;
    }

    public List<PermohonanKertasKandungan> getListAllKronologiKes() {
        return listAllKronologiKes;
    }

    public void setListAllKronologiKes(List<PermohonanKertasKandungan> listAllKronologiKes) {
        this.listAllKronologiKes = listAllKronologiKes;
    }

    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }

    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }

    public List<PegawaiPenyiasat> getListPegawaiSiasat() {
        return listPegawaiSiasat;
    }

    public void setListPegawaiSiasat(List<PegawaiPenyiasat> listPegawaiSiasat) {
        this.listPegawaiSiasat = listPegawaiSiasat;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public KandunganFolder getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(KandunganFolder folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOperasi(String idOperasi) {
        this.idOperasi = idOperasi;
    }

    public String getIdOperasiAgensi() {
        return idOperasiAgensi;
    }

    public void setIdOperasiAgensi(String idOperasiAgensi) {
        this.idOperasiAgensi = idOperasiAgensi;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public KandunganFolder getKf() {
        return kf;
    }

    public void setKf(KandunganFolder kf) {
        this.kf = kf;
    }

    public List<KandunganFolder> getListDokumen() {
        return listDokumen;
    }

    public void setListDokumen(List<KandunganFolder> listDokumen) {
        this.listDokumen = listDokumen;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public OperasiAgensi getOperasiAgensi() {
        return operasiAgensi;
    }

    public void setOperasiAgensi(OperasiAgensi operasiAgensi) {
        this.operasiAgensi = operasiAgensi;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaanIP() {
        return operasiPenguatkuasaanIP;
    }

    public void setOperasiPenguatkuasaanIP(OperasiPenguatkuasaan operasiPenguatkuasaanIP) {
        this.operasiPenguatkuasaanIP = operasiPenguatkuasaanIP;
    }

    public List<KandunganFolder> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<KandunganFolder> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public String getIdPermohonanAsal() {
        return idPermohonanAsal;
    }

    public void setIdPermohonanAsal(String idPermohonanAsal) {
        this.idPermohonanAsal = idPermohonanAsal;
    }
}
