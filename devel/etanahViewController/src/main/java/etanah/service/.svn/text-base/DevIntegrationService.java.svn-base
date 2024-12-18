/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Akaun;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodHakmilik;
import etanah.model.KodJabatan;
import etanah.model.KodLot;
import etanah.model.KodRujukan;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUrusan;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanUkur;
import etanah.model.gis.PelanGIS;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.daftar.MohonHakmilikService;
import etanah.workflow.StageContext;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class DevIntegrationService {

    private static final Logger LOG = Logger.getLogger(DevIntegrationService.class);
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    MohonHakmilikService mhservice;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    PembangunanService devServ;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    JanaHakmilikService js;
    @Inject
    CalcTax calcTax;
    @Inject
    ConsentPtdService consentPtdService;
    @Inject
    PembangunanService pembangunanServ;
    private FasaPermohonan fasaPermohonan;
    private Permohonan permohonan;
    private Permohonan mohon;
    private List<Hakmilik> senaraiHakmilikRujukan = new ArrayList<Hakmilik>();
    PermohonanUkur mohonUkur;
    private PermohonanRujukanLuar prl;
    private PermohonanPlotPelan pps;

    public String intRegPermohonan(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, String kodJabatan, String mohonRujLuarFlag, String stageId) {
        List<Hakmilik> senaraiHakmilikPTD = new ArrayList<Hakmilik>();
        List<Hakmilik> senaraiHakmilikPNDFTR = new ArrayList<Hakmilik>();

        boolean comparePTD = false;
        boolean comparePTG = false;
        for (int i = 0; i < senaraiHakmilik.size(); i++) {
            Hakmilik h = senaraiHakmilik.get(i);

            if (h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("PN") || h.getKodHakmilik().getKod().equals("HSD")) {
                comparePTG = true;
                senaraiHakmilikPNDFTR.add(h);
            } else {
                comparePTD = true;
                senaraiHakmilikPTD.add(h);
            }
        }

        //if (ku.getKod().equals("CM") || ku.getKod().equals("CL") || ku.getKod().equals("CB")) {
        //    if (comparePTD && comparePTG) {
        //        return generateIdPerserahanWorkflowDiffHakmilik("T", ku, pengguna, senaraiHakmilik, permohonan, kodJabatan, mohonRujLuarFlag, ku.getIdWorkflowIntegration(), context);
        //    } else {
        //        return generateIdPerserahanWorkflow("T", ku, pengguna, senaraiHakmilik, permohonan, kodJabatan, mohonRujLuarFlag, ku.getIdWorkflowIntegration(), context);
        //    }
        //} else {
        String returnStatement = new String();
        if (!senaraiHakmilikPTD.isEmpty()) {
            returnStatement = generateIdPerserahanWorkflow("T", ku, pengguna, senaraiHakmilikPTD, permohonan, kodJabatan, mohonRujLuarFlag, ku.getIdWorkflowIntegration(), stageId);
        }
        if (!senaraiHakmilikPNDFTR.isEmpty()) {
            returnStatement = generateIdPerserahanWorkflow("T", ku, pengguna, senaraiHakmilikPNDFTR, permohonan, kodJabatan, mohonRujLuarFlag, ku.getIdWorkflowIntegration(), stageId);
        }

        return returnStatement;
        //return generateIdPerserahanWorkflow("T", ku, pengguna, senaraiHakmilik, permohonan, kodJabatan, mohonRujLuarFlag, ku.getIdWorkflowIntegration(), context);
        //}
    }

    public String intRegKelulusan(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, String kodJabatan, String mohonRujLuarFlag, String stageId) {
        List<Hakmilik> senaraiHakmilikPTD = new ArrayList<Hakmilik>();
        List<Hakmilik> senaraiHakmilikPNDFTR = new ArrayList<Hakmilik>();


        for (int i = 0; i < senaraiHakmilik.size(); i++) {
            Hakmilik h = senaraiHakmilik.get(i);

            if (h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("PN") || h.getKodHakmilik().getKod().equals("HSD")) {

                senaraiHakmilikPNDFTR.add(h);
            } else {

                senaraiHakmilikPTD.add(h);
            }
        }
        String returnStatement = new String();
        if (!senaraiHakmilikPTD.isEmpty()) {
            returnStatement = generateIdPerserahanWorkflow("F", ku, pengguna, senaraiHakmilikPTD, permohonan, kodJabatan, mohonRujLuarFlag, ku.getIdWorkflow(), stageId);
        }
        if (!senaraiHakmilikPNDFTR.isEmpty()) {
            returnStatement = generateIdPerserahanWorkflow("F", ku, pengguna, senaraiHakmilikPNDFTR, permohonan, kodJabatan, mohonRujLuarFlag, ku.getIdWorkflow(), stageId);
        }

        return returnStatement;

        //return generateIdPerserahanWorkflow("F", ku, pengguna, senaraiHakmilik, permohonan, kodJabatan, mohonRujLuarFlag, ku.getIdWorkflow(), context);
    }

    public String generateIdPerserahanWorkflowDiffHakmilik(String agih, KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, String kodJabatan, String mohonRujLuarFlag, String idW, String stageId) {
        String error = "";
        KodCawangan caw = null;

        for (int a = 0; a < senaraiHakmilik.size(); a++) {
            if (!senaraiHakmilik.isEmpty() && (senaraiHakmilik.get(a).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(a).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(a).getKodHakmilik().getKod().equals("HSD"))) {
                caw = kodCawanganDAO.findById("00");
            } else {
                caw = permohonan.getCawangan();
            }
            LOG.info(ku.getNama());
            LOG.info(permohonan.getFolderDokumen());
            InfoAudit ia = new InfoAudit();
            Date now = new Date();
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            if (ku == null) {
                return "Kod Urusan null";
            }
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            long idFolder = Long.parseLong(tarikh); // TODO create seq

            try {

                FolderDokumen fd = new FolderDokumen();

                fd.setTajuk("TEST_" + tarikh); // TODO
                fd.setInfoAudit(ia);
                fd.setFolderId(idFolder);
                folderDokumenDAO.save(fd);

                KodCaraPermohonan kcp = new KodCaraPermohonan();
                kcp = pembangunanServ.findKodCaraPermohonan("UD");

                KodCawanganJabatan kodCawanganJabatan = (KodCawanganJabatan) pembangunanServ.findAlamat(caw.getKod());

                Permohonan p = new Permohonan();
                p.setCaraPermohonan(kcp);
                p.setStatus("TA");
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setCawangan(caw);
                p.setKodUrusan(ku);
                p.setFolderDokumen(fd);
                p.setIdWorkflow(idW);
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
                    //p.setPenyerahNama(permohonan.getPenyerahNama());
                    p.setPenyerahNama("Unit Pembangunan");
                    //p.setKodPenyerah(permohonan.getKodPenyerah());
                    p.setPenyerahAlamat1(kodCawanganJabatan.getAlamat1());
                    p.setPenyerahAlamat2(kodCawanganJabatan.getAlamat2());
                    if (kodCawanganJabatan.getAlamat3() != null) {
                        p.setPenyerahAlamat3(kodCawanganJabatan.getAlamat3());
                    }

                    if (kodCawanganJabatan.getAlamat4() != null) {
                        p.setPenyerahAlamat4(kodCawanganJabatan.getAlamat4());
                    }
                    p.setPenyerahPoskod(kodCawanganJabatan.getPoskod());
                    p.setPenyerahNegeri(kodCawanganJabatan.getNegeri());

                }
                System.out.println("-->Stage Name Diff Hakmilik : " + stageId);
                p.setIdStagePermohonanSebelum(stageId);
                p.setInfoAudit(ia);
                permohonanDAO.save(p);

                for (int c = 0; c < 1; c++) {
                    Hakmilik hm = (Hakmilik) senaraiHakmilik.get(a);
                    String id = hm.getIdHakmilik();
                    hm = hakmilikDAO.findById(id);
                    if (hm == null) {
                        throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                    }
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hpa.setInfoAudit(ia);
                    hpa.setPermohonan(p);
                    hakmilikPermohonanDAO.save(hpa);
                }

                if (agih.equalsIgnoreCase("T")) {
                    WorkFlowService.initiateTask(idW,
                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                            p.getKodUrusan().getNama());
                } else {
                    WorkFlowService.initiateTask(idW,
                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                            p.getKodUrusan().getNama());
                }
                mohon = p;

                if (mohon.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("MH")) {
                    for (Hakmilik hmBaru : senaraiHakmilikRujukan) {
                        if (mohonRujLuarFlag.equalsIgnoreCase("T")) {
                            //if(ku.getKodPerserahan().getKod().equalsIgnoreCase("N")){
                            updateMohonRujukLuar(mohon, ia, kodJabatan, permohonan, hmBaru);
                        } else {
                        }
                        //for PSM
                    }
                } else {
                    for (int d = 0; d < 1; d++) {
                        Hakmilik hm = (Hakmilik) senaraiHakmilik.get(a);
                        if (mohonRujLuarFlag.equalsIgnoreCase("T")) {
                            //if(ku.getKodPerserahan().getKod().equalsIgnoreCase("N")){
                            //updateMohonRujukLuar(mohon, ia, kodJabatan, permohonan, hm);
                            PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
                            permohonanRujukanLuar.setCawangan(mohon.getCawangan());
                            permohonanRujukanLuar.setPermohonan(mohon);
                            permohonanRujukanLuar.setInfoAudit(ia);
                            LOG.info("--------Update Mohon Ruj Luar PYTN-------------" + hm.getIdHakmilik());
                            permohonanRujukanLuar.setHakmilik(hm);
                            KodRujukan r = kodRujukanDAO.findById("FL");
                            KodJabatan kj = kodJabatanDAO.findById(kodJabatan);
                            permohonanRujukanLuar.setJabatan(kj);
                            permohonanRujukanLuar.setNoFail(permohonan.getIdPermohonan());
                            permohonanRujukanLuar.setKodRujukan(r);
                            permohonanRujukanLuar.setTarikhRujukan(permohonan.getTarikhKeputusan());

                            Date date = new Date();
                            Date lulusan = new Date();
                            String NoSidang = null;
                            String NoRuj = null;
                            List<PermohonanKertas> senaraiKertas = permohonan.getSenaraiKertas();
                            for (PermohonanKertas mk : senaraiKertas) {
                                if (mk.getKodDokumen().getKod().equals("MMK")) {
                                    date = mk.getTarikhSidang();
                                    lulusan = mk.getTarikhSahKeputusan();
                                    NoRuj = mk.getTajuk();
                                }
                            }
                            permohonanRujukanLuar.setNoRujukan(NoRuj); // no.msyuarat kelulusan
                            permohonanRujukanLuar.setTarikhRujukan(date);// tarikh mesyuarat tarikh sidang

                            permohonanRujukanLuar.setNoSidang("Majlis Mesyuarat Kerajaan Negeri Negeri Sembilan"); // no ruj sidang
                            permohonanRujukanLuar.setTarikhSidang(lulusan); //tarikh kelulusan

                            permohonanRujukanLuar.setNamaSidang("Majlis Mesyuarat Kerajaan Negeri Negeri Sembilan"); //nama sidang
                            strService.SimpanMohonRujukLuar(permohonanRujukanLuar);
                        } else {
                        }
                    }
                }

                tx.commit();

            } catch (Exception e) {
                tx.rollback();
                Throwable t = e;
                // getting the root cause
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                t.printStackTrace();
                LOG.error(t);
                throw new RuntimeException(t);
            }

            if (error.equals("")) {
                error = "Berikut adalah info berkaitan integrasi pembangunan " + '\n'
                        + "         ID Perserahan     : " + mohon.getIdPermohonan() + '\n'
                        + "         ID Permohonan DEV : " + mohon.getPermohonanSebelum().getIdPermohonan() + '\n'
                        + "         ID Hakmilik Asal  : " + stringListHakmilik(mohon.getPermohonanSebelum().getIdPermohonan()) + '\n'
                        + "         ID Hakmilik Sambungan : " + stringListHakmilik(mohon.getIdPermohonan()) + '\n' + '\n' + '\n'
                        + "Jika terdapat sebarang kesilapan pengiraan cukai, sila maklumkan ke unit Pendaftaran untuk pembetulan.";


            }
        }

        return error;
    }

    public String generateIdPerserahanWorkflow(String agih, KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, String kodJabatan, String mohonRujLuarFlag, String idW, String stageId) {
        String error = "";
        KodCawangan caw = null;
        if (!senaraiHakmilik.isEmpty() && (senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD"))) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = permohonan.getCawangan();
        }
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return "Kod Urusan null";
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {

            FolderDokumen fd = new FolderDokumen();

            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            KodCaraPermohonan kcp = new KodCaraPermohonan();
            kcp = pembangunanServ.findKodCaraPermohonan("UD");

            KodCawanganJabatan kodCawanganJabatan = (KodCawanganJabatan) pembangunanServ.findAlamat(caw.getKod());

            Permohonan p = new Permohonan();
            p.setCaraPermohonan(kcp);
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(caw);
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            p.setIdWorkflow(idW);
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
                //p.setPenyerahNama(permohonan.getPenyerahNama());
                p.setPenyerahNama("Unit Pembangunan");
                //p.setKodPenyerah(permohonan.getKodPenyerah());
                p.setPenyerahAlamat1(kodCawanganJabatan.getAlamat1());
                p.setPenyerahAlamat2(kodCawanganJabatan.getAlamat2());
                if (kodCawanganJabatan.getAlamat3() != null) {
                    p.setPenyerahAlamat3(kodCawanganJabatan.getAlamat3());
                }

                if (kodCawanganJabatan.getAlamat4() != null) {
                    p.setPenyerahAlamat4(kodCawanganJabatan.getAlamat4());
                }
                p.setPenyerahPoskod(kodCawanganJabatan.getPoskod());
                p.setPenyerahNegeri(kodCawanganJabatan.getNegeri());

            }
            System.out.println("-->Stage Name : " + stageId);
            p.setIdStagePermohonanSebelum(stageId);
            p.setInfoAudit(ia);
            permohonanDAO.save(p);
            if (ku.getKod().equals("HKSB") || ku.getKod().equals("HKPS")
                    || ku.getKod().equals("HKSTB") || ku.getKod().equals("HKPB") || ku.getKod().equals("HKBM")
                    || ku.getKod().equals("HKPTK") || ku.getKod().equals("HKBTK")) {
                error = janaHakmilikBaru(p, pengguna, permohonan, "");
            } else if (ku.getKod().equals("HSPS") || ku.getKod().equals("HSPB")
                    || ku.getKod().equals("HSSTB") || ku.getKod().equals("HSSB") || ku.getKod().equals("HSBM")
                    || ku.getKod().equals("HSPTK") || ku.getKod().equals("HSBTK")) {
                //QT hakmilik
                String sts = "QT";
                error = janaHakmilikBaru(p, pengguna, permohonan, sts);
            } else if (ku.getKod().equals("HKC") || ku.getKod().equals("HKCTK")) {
                error = janaHakmilikBaruPenyatuan(p, pengguna, permohonan, "", senaraiHakmilik);
            } else if (ku.getKod().equals("HSC") || ku.getKod().equals("HSCTK")) {
                String sts = "QT";
                error = janaHakmilikBaruPenyatuan(p, pengguna, permohonan, sts, senaraiHakmilik);
            } else if (ku.getKod().equals("HKGHS")) {
                error = janaHakmilikBaruKekalGantiSementara(p, pengguna, permohonan, "", senaraiHakmilik);
            } else {
                for (Hakmilik hm : senaraiHakmilik) {
                    String id = hm.getIdHakmilik();
                    hm = hakmilikDAO.findById(id);
                    if (hm == null) {
                        throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                    }
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hpa.setInfoAudit(ia);
                    if (p.getKodUrusan().getKod().equals("TSSKL") || p.getKodUrusan().getKod().equals("SSKPL")) {
                        hpa.setJenisPenggunaanTanah(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getJenisPenggunaanTanah());
                        hpa.setSyaratNyataBaru(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getSyaratNyataBaru());
                        hpa.setSekatanKepentinganBaru(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getSekatanKepentinganBaru());
                        hpa.setKategoriTanahBaru(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getKategoriTanahBaru());
                        hpa.setKodKegunaanTanah(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getKodKegunaanTanah());
//                        String kodTanah, String kodBpm, String kodUOM, BigDecimal luas, Hakmilik hakmilik, String kodRizab

                        BigDecimal cukaiBaru;
                        if ((findCukai(p.getPermohonanSebelum().getIdPermohonan()) == null) || (findCukai(p.getPermohonanSebelum().getIdPermohonan()).equals("0"))) {
                            cukaiBaru = hm.getCukai();
                        } else {
                            cukaiBaru = findCukai(p.getPermohonanSebelum().getIdPermohonan()).getAmaunTuntutan();
                        }
                        hpa.setCukaiBaru(cukaiBaru);
                        //hmb2.setCukai(calcTax.calculate(kodGunaTanah,kodBpm, kodUOM, hmb2.getLuas(), hmb2, kodRizab));


                    }
                    if (p.getKodUrusan().getKod().equals("SBSTL") || p.getKodUrusan().getKod().equals("SBTL")) {
                        if (conf.getProperty("kodNegeri").equals("04")) {
                            LOG.info("KOD----------->");
                            LOG.info("id mohon----------->" + p.getPermohonanSebelum().getIdPermohonan());
                            if (p.getKodUrusan().getKod().equals("SBSTL")) {
                                List<PermohonanPlotPelan> listpps = pembangunanServ.findPermohonanPlotPelanKerajaanByIdPermohonan(p.getPermohonanSebelum().getIdPermohonan());

                                BigDecimal jumlahLuas = BigDecimal.ZERO;
                                for (int i = 0; i < listpps.size(); i++) {
                                    PermohonanPlotPelan ppp = listpps.get(i);
                                    jumlahLuas = jumlahLuas.add(ppp.getLuas());
                                    hpa.setKodUnitLuas(ppp.getKodUnitLuas());
                                }
                                hpa.setLuasTerlibat(jumlahLuas);
                            }

                            if (p.getKodUrusan().getKod().equals("SBTL")) {


                                BigDecimal jumlahLuas = BigDecimal.ZERO;
                                for (int i = 0; i < p.getPermohonanSebelum().getSenaraiHakmilik().size(); i++) {
                                    HakmilikPermohonan ppp = p.getPermohonanSebelum().getSenaraiHakmilik().get(i);
                                    jumlahLuas = jumlahLuas.add(ppp.getHakmilik().getLuas());
                                    hpa.setKodUnitLuas(ppp.getHakmilik().getKodUnitLuas());
                                }
                                hpa.setLuasTerlibat(jumlahLuas);
                            }


                            //LOG.info("LUAS----------->" + jumlahLuas);
                            //LOG.info("UNIT----------->" + hpa.getKodUnitLuas().getNama());

                        } else if (conf.getProperty("kodNegeri").equals("05")) {
                            LOG.info("KOD----------->");
                            LOG.info("id mohon----------->" + p.getPermohonanSebelum().getIdPermohonan());
                            pps = pembangunanServ.findluasPlotPelanByIdMohon(p.getPermohonanSebelum().getIdPermohonan());
                            hpa.setLuasTerlibat(pps.getLuas());
                            hpa.setKodUnitLuas(pps.getKodUnitLuas());
                            LOG.info("LUAS----------->" + pps.getLuas());
                            LOG.info("UNIT----------->" + pps.getKodUnitLuas());
                        }
                    }

                    hpa.setPermohonan(p);
                    hakmilikPermohonanDAO.save(hpa);
                }
            }
            LOG.info("EROROROROROR" + error);

            if (error.equals("")) {

                if (agih.equalsIgnoreCase("T")) {
                    WorkFlowService.initiateTask(idW,
                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                            p.getKodUrusan().getNama());
                } else {
                    WorkFlowService.initiateTask(idW,
                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                            p.getKodUrusan().getNama());
                }
                mohon = p;

                if (mohon.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("MH")) {

                    for (Hakmilik hmBaru : senaraiHakmilikRujukan) {

                        if (mohonRujLuarFlag.equalsIgnoreCase("T")) {
                            //if(ku.getKodPerserahan().getKod().equalsIgnoreCase("N")){

                            updateMohonRujukLuar(mohon, ia, kodJabatan, permohonan, hmBaru);

                        } else {
                        }
                        //for PSM

                    }

                } else {

                    for (Hakmilik hm : senaraiHakmilik) {

                        if (mohonRujLuarFlag.equalsIgnoreCase("T")) {
                            //if(ku.getKodPerserahan().getKod().equalsIgnoreCase("N")){

                            updateMohonRujukLuar(mohon, ia, kodJabatan, permohonan, hm);

                        } else {
                        }
                        //for PSM

                    }

                }

            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
            throw new RuntimeException(t);
        }

        if (error.equals("") || error.isEmpty()) {
            if (mohon != null) {
                if (mohon.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("MH")) {
                    error = "Berikut adalah info berkaitan integrasi pembangunan " + '\n'
                            + "         ID Perserahan     : " + mohon.getIdPermohonan() + '\n'
                            + "         ID Permohonan DEV : " + mohon.getPermohonanSebelum().getIdPermohonan() + '\n'
                            + "         ID Hakmilik Asal  : " + stringListHakmilik(mohon.getPermohonanSebelum().getIdPermohonan()) + '\n'
                            + "         ID Hakmilik Sambungan : " + stringListHakmilik(mohon.getIdPermohonan()) + '\n' + '\n' + '\n'
                            + "Jika terdapat sebarang kesilapan pengiraan cukai, sila maklumkan ke unit Pendaftaran untuk pembetulan.";


                }
            }

        }



        return error;
    }

    public List<Hakmilik> setListHakmilikByMohonHakmilik(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hm : senaraiHakmilikPermohonan) {
            listHakmilik.add(hm.getHakmilik());
        }

        return listHakmilik;

    }

    public List<Hakmilik> setListHakmilikByMohonHakmilikPYTN(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
        System.out.println("Id Mohon : " + senaraiHakmilikPermohonan.get(0).getPermohonan().getIdPermohonan());

        boolean comparePTD = false;
        boolean comparePTG = false;
        for (HakmilikPermohonan hm : senaraiHakmilikPermohonan) {
            if (hm.getHakmilik().getKodHakmilik().getKod().equals("GRN") || hm.getHakmilik().getKodHakmilik().getKod().equals("PN") || hm.getHakmilik().getKodHakmilik().getKod().equals("HSD")) {
                comparePTG = true;
            } else {
                comparePTD = true;
            }
        }

        System.out.println("comparePTG : " + comparePTG);
        System.out.println("comparePTD : " + comparePTD);
        for (HakmilikPermohonan hm : senaraiHakmilikPermohonan) {
            if (comparePTD && comparePTG) {
                System.out.println("Hakmilik dari table hakmilik : " + hm.getHakmilik().getKodHakmilik().getKod());
                PermohonanKertas pk = pembangunanServ.findKertasByKod(senaraiHakmilikPermohonan.get(0).getPermohonan().getIdPermohonan(), "MMK");
                System.out.println("MMK : " + pk.getKodHakmilikBaru().getKod());
                if (pk.getKodHakmilikBaru().getKod().equals(hm.getHakmilik().getKodHakmilik().getKod())) {
                    listHakmilik.add(hm.getHakmilik());
                    System.out.println("Inside berjaya : Id hakmilik : " + hm.getHakmilik().getIdHakmilik());
                }
            } else {
                listHakmilik.add(hm.getHakmilik());
            }
        }
        System.out.println("Size List Hakmilik : " + listHakmilik.size());

        return listHakmilik;
    }

    public KodUrusan findKodUrusan(String kod) {
        return kodUrusanDAO.findById(kod);
    }

    public KodHakmilik findHakmilik(String kod) {
        return kodHakmilikDAO.findById(kod);
    }

    public String janaHakmilikBaru(Permohonan mohonREG, Pengguna pengguna, Permohonan permohonan, String sts) {
        String error = "";
        String noPA = "";
        try {
            InfoAudit ia = new InfoAudit();
            Date now = new Date();
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            Hakmilik hmb = new Hakmilik();
            KodHakmilik kh = new KodHakmilik();
            mohonUkur = new PermohonanUkur();
            mohonUkur = devServ.findMohonUkurByMohon(permohonan.getIdPermohonan());
            if(mohonUkur!=null){
            hmb.setNoPu(mohonUkur.getNoPermohonanUkur());
            }
            hmb.setNoFail(permohonan.getIdPermohonan());
            hmb.setNoVersiDhde(0);
            hmb.setNoVersiDhke(0);


            if (sts.equalsIgnoreCase("QT")) {

                if (conf.getProperty("kodNegeri").equals("04")) {

                    if (checkFlagAscii(permohonan.getIdPermohonan())) {
                        if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("GRN") || permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("PN") || permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HSD")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HSD"));
                        } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("GM") || permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("PM") || permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HSM")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HSM"));
                        } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("GMM") || permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HMM")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HMM"));
                        }
                    } else {
                        if (mohonUkur.getKodHakmilik().getKod().equals("GRN") || mohonUkur.getKodHakmilik().getKod().equals("PN") || mohonUkur.getKodHakmilik().getKod().equals("HSD")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HSD"));
                        } else if (mohonUkur.getKodHakmilik().getKod().equals("GM") || mohonUkur.getKodHakmilik().getKod().equals("PM") || mohonUkur.getKodHakmilik().getKod().equals("HSM")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HSM"));
                        } else if (mohonUkur.getKodHakmilik().getKod().equals("GMM") || mohonUkur.getKodHakmilik().getKod().equals("HMM")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HMM"));
                        }
                    }

                } else if (conf.getProperty("kodNegeri").equals("05")) {

                    if (mohonUkur.getKodHakmilik().getKod().equals("GRN") || mohonUkur.getKodHakmilik().getKod().equals("PN") || mohonUkur.getKodHakmilik().getKod().equals("HSD")) {
                        hmb.setKodHakmilik(kodHakmilikDAO.findById("HSD"));
                    } else if (mohonUkur.getKodHakmilik().getKod().equals("GM") || mohonUkur.getKodHakmilik().getKod().equals("PM") || mohonUkur.getKodHakmilik().getKod().equals("HSM")) {
                        hmb.setKodHakmilik(kodHakmilikDAO.findById("HSM"));
                    }

                }



            } else {

                if (conf.getProperty("kodNegeri").equals("04")) {
                    if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HSD")) {
                        //GRN
                        kh = kodHakmilikDAO.findById(mohonUkur.getKodHakmilik().getKod());
                        hmb.setKodHakmilik(kh);
                    } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HMM")) {
                        kh = kodHakmilikDAO.findById("GMM");
                        hmb.setKodHakmilik(kh);
                    } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HSM")) {
                        kh = kodHakmilikDAO.findById(mohonUkur.getKodHakmilik().getKod());
                        hmb.setKodHakmilik(kh);
                    } else {
                        hmb.setKodHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik());
                    }

                } else if (conf.getProperty("kodNegeri").equals("05")) {

                    if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HSD")) {
                        //GRN
                        kh = kodHakmilikDAO.findById(mohonUkur.getKodHakmilik().getKod());
                        hmb.setKodHakmilik(kh);
                    } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HMM")) {
                        kh = kodHakmilikDAO.findById("GMM");
                        hmb.setKodHakmilik(kh);
                    } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HSM")) {
                        kh = kodHakmilikDAO.findById(mohonUkur.getKodHakmilik().getKod());
                        hmb.setKodHakmilik(kh);
                    } else {
                        hmb.setKodHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik());
                    }

                }




                List<PelanGIS> list = findPelanB1ByMohon(permohonan.getIdPermohonan());
                if (list.size() > 0) {
                    noPA = list.get(0).getNoPelanAkui();
                    hmb.setNoPelan(noPA);
                    hmb.setNoLot(formatNoLotIkutPendaftaran(list.get(0).getPelanGISPK().getNoLot()));
                }
            }
            senaraiHakmilikRujukan = new ArrayList<Hakmilik>();
            String[] listluas = new String[0];
            int sum = 0;
            List<PermohonanPlotPelan> mpp = devServ.findPermohonanPlotPelanByIdPermohonan(permohonan.getIdPermohonan());
            listluas = new String[100];
            LOG.info("SIZE mohon_plot_plan :::" + mpp.size());
            for (int i = 0; i < mpp.size(); i++) {
                PermohonanPlotPelan permohonanPlotPelan = new PermohonanPlotPelan();
                permohonanPlotPelan = mpp.get(i);
                if (permohonanPlotPelan.getPemilikan().getKod() == 'H') {

                    //utk sbms melaka
                    List<NoPt> senaraiNoPT = new ArrayList<NoPt>();
                    senaraiNoPT = devServ.findListNoPtByIdMohon(permohonanPlotPelan.getIdPlot());

                    for (int jx = 0; jx < permohonanPlotPelan.getBilanganPlot(); jx++) {
                        listluas[sum] = permohonanPlotPelan.getLuas().toString();
                        sum = sum + 1;
                        //start set hakmilik
                        //todo
                        Hakmilik hmb2 = new Hakmilik();
                        hmb2.setKodSumber("ET");
                        hmb2.setKodHakmilik(hmb.getKodHakmilik());
                        //
                        hmb2.setNoPu(hmb.getNoPu());
                        hmb2.setNoFail(permohonan.getIdPermohonan());
                        //
                        hmb2.setKategoriTanah(permohonanPlotPelan.getKategoriTanah());
                        //hmb2.setKategoriTanah(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKategoriTanah());
                        hmb2.setKegunaanTanah(permohonanPlotPelan.getKegunaanTanah());
                        //hmb2.setKegunaanTanah(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKegunaanTanah());
                        //  hmb2.setKodHakmilik(devServ.findMohonUkurByMohon(permohonan.getIdPermohonan()).getKodHakmilik());
                        if (conf.getProperty("kodNegeri").equals("04")) {
                            if (permohonan.getKodUrusan().getKod().equals("TSPSS") || permohonan.getKodUrusan().getKod().equals("SBMS")) {
                                hmb2.setSekatanKepentingan(permohonanPlotPelan.getKodSekatanKepentingan());
                                hmb2.setSyaratNyata(permohonanPlotPelan.getKodSyaratNyata());

                            } else {
                                //hmb2.setSekatanKepentingan(permohonanPlotPelan.getKodSekatanKepentingan());
                                hmb2.setSekatanKepentingan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSekatanKepentingan());
                                //hmb2.setSyaratNyata(permohonanPlotPelan.getKodSyaratNyata());
                                hmb2.setSyaratNyata(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSyaratNyata());
                            }
                        }
                        if (conf.getProperty("kodNegeri").equals("05")) {
                            if (permohonan.getKodUrusan().getKod().equals("TSPSS")) {
                                hmb2.setSekatanKepentingan(permohonanPlotPelan.getKodSekatanKepentingan());
                                hmb2.setSyaratNyata(permohonanPlotPelan.getKodSyaratNyata());

                            } else {
                                //hmb2.setSekatanKepentingan(permohonanPlotPelan.getKodSekatanKepentingan());
                                hmb2.setSekatanKepentingan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSekatanKepentingan());
                                //hmb2.setSyaratNyata(permohonanPlotPelan.getKodSyaratNyata());
                                hmb2.setSyaratNyata(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSyaratNyata());
                            }
                        }


                        hmb2.setKodUnitLuas(permohonanPlotPelan.getKodUnitLuas());
                        hmb2.setLuas(permohonanPlotPelan.getLuas());

//                        String kodTanah, String kodBpm, String kodUOM, BigDecimal luas, Hakmilik hakmilik, String kodRizab
//                        hmb2.setCukai(calcTax.calculate(hmb2.getKategoriTanah().getKod(), String.valueOf(hmb2.getBandarPekanMukim().getKod()), hmb2.getKodUnitLuas().getKod(), hmb2.getLuas(), hmb2, String.valueOf(hmb2.getRizab().getKod())));


                        if (sts.equalsIgnoreCase("QT")) {
                            KodLot kl = new KodLot();
                            kl.setKod("3");
                            hmb2.setLot(kl);
                            if (conf.getProperty("kodNegeri").equals("04")) {

                                if (permohonan.getKodUrusan().getKod().equals("SBMS")) {

                                    if (senaraiNoPT.size() >= permohonanPlotPelan.getBilanganPlot()) {
                                        hmb2.setNoLot(formatNoLotIkutPendaftaran(String.valueOf(senaraiNoPT.get(jx).getNoPt())));
                                    } else {
                                        hmb2.setNoLot("");
                                    }

                                } else {

                                    hmb2.setNoLot(formatNoLotIkutPendaftaran(devServ.findNolotByIdPlot(permohonanPlotPelan.getIdPlot()).getNoPt() == null ? "" : String.valueOf(devServ.findNolotByIdPlot(permohonanPlotPelan.getIdPlot()).getNoPt())));
                                }

                            }
                            if (conf.getProperty("kodNegeri").equals("05")) {
                                hmb2.setNoLot(formatNoLotIkutPendaftaran(devServ.findNolotByIdPlot(permohonanPlotPelan.getIdPlot()).getNoPt() == null ? "" : String.valueOf(devServ.findNolotByIdPlot(permohonanPlotPelan.getIdPlot()).getNoPt())));
                            }


                            hmb2.setPegangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPegangan()); // Pegangan HS is Pajakan
                            hmb2.setTempohPegangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getTempohPegangan());//99 Tahun
                        } else {
                            KodLot kl = new KodLot();
                            kl.setKod("1");
                            hmb2.setLot(kl);
                            if (conf.getProperty("kodNegeri").equals("04")) {
                                String numberPT = String.valueOf(senaraiNoPT.get(jx).getNoPt());
                                String noLotJUPEM = "";
                                if (numberPT != null) {
                                    noLotJUPEM = devServ.findNoLotGisPelanByPT(permohonan.getIdPermohonan(), numberPT);
                                } else {
                                    noLotJUPEM = devServ.findNoLotGisPelanByIDMohon(permohonan.getIdPermohonan());
                                    //kena revise balik
                                }
                                hmb2.setNoLot(formatNoLotIkutPendaftaran(noLotJUPEM));
                            }

                            if (conf.getProperty("kodNegeri").equals("05")) {
                                String numberPT = devServ.findNolotByIdPlot(permohonanPlotPelan.getIdPlot()).getNoPt() == null ? "" : String.valueOf(devServ.findNolotByIdPlot(permohonanPlotPelan.getIdPlot()).getNoPt());
                                if (numberPT != null) {
                                    hmb2.setNoLot(formatNoLotIkutPendaftaran(devServ.findNoLotGisPelanByPT(permohonan.getIdPermohonan(), numberPT)));
                                } else {
                                    hmb2.setNoLot(formatNoLotIkutPendaftaran(devServ.findNoLotGisPelanByIDMohon(permohonan.getIdPermohonan())));
                                }
                            }
                            hmb2.setNoPelan(noPA);
                            hmb2.setPegangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPegangan());
                            hmb2.setTempohPegangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getTempohPegangan());
                        }

                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hmb.setKodStatusHakmilik(ksh);
                        hmb2.setKodStatusHakmilik(ksh);
                        hmb2.setCawangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getCawangan());
                        hmb2.setSeksyen(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSeksyen());
                        hmb2.setLokasi(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLokasi());
                        hmb2.setDaerah(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah());
                        hmb2.setMilikPersekutuan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getMilikPersekutuan());
                        hmb2.setLotBumiputera(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLotBumiputera());
//                    hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());
                        hmb2.setNoLitho(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getNoLitho());

                        List<HakmilikUrusan> listNaikTarafKwsn = new ArrayList<HakmilikUrusan>();
                        listNaikTarafKwsn = devServ.findHakmilikUrusanByHakmilikAndUrusan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ITP", "ITB");
                        if (listNaikTarafKwsn.size() > 0) {
                            KodBandarPekanMukim kodBandarPekanMukim = new KodBandarPekanMukim();
                            for (HakmilikUrusan hu : listNaikTarafKwsn) {
                                if (hu.getKodUrusan().getKod().equalsIgnoreCase("ITP")) {
                                    kodBandarPekanMukim = devServ.findKodBPMlikeItembyUrusanITP(hu);
                                    hmb2.setBandarPekanMukim(kodBandarPekanMukim);
                                }
                            }

                        } else {
                            hmb2.setBandarPekanMukim(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim());
                        }

                        hmb2.setRizab(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getRizab());
                        hmb2.setRizabKawasan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getRizabKawasan());
                        hmb2.setRizabNoWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getRizabNoWarta());
                        hmb2.setRizabTarikhWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getRizabTarikhWarta());

                        hmb2.setGsaNoWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getGsaNoWarta());
                        hmb2.setGsaKawasan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getGsaKawasan());
                        hmb2.setGsaTarikhWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getGsaTarikhWarta());

                        hmb2.setPbtKawasan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPbtKawasan());
                        hmb2.setPbtNoWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPbtNoWarta());
                        hmb2.setPbtTarikhWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPbtTarikhWarta());

                        hmb2.setMilikPersekutuan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getMilikPersekutuan());

                        if (!permohonan.getKodUrusan().getKod().equals("HSSTB") && !permohonan.getKodUrusan().getKod().equals("HKSTB")) {
                            hmb2.setTarikhDaftarAsal(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftarAsal());
                        }




                        String kodGunaTanah = hmb2.getKegunaanTanah().getKod();
                        String kodTanah = hmb2.getKategoriTanah().getKod();
                        Integer intBpm = 0;
                        if (hmb2.getBandarPekanMukim() != null) {
                            intBpm = hmb2.getBandarPekanMukim().getKod();
                        }
                        String kodBpm = intBpm.toString();
                        String kodUOM = hmb2.getKodUnitLuas().getKod();
                        Integer intRizab = 0;
                        if (hmb2.getRizab() != null) {
                            intRizab = hmb2.getRizab().getKod();
                        }
                        String kodRizab = intRizab.toString();
                        if (permohonan.getKodUrusan().getKod().equals("TSPSS")) {

                            PermohonanTuntutanKos kos = findCukai(permohonan.getIdPermohonan());
                            if (kos != null) {
                                hmb2.setCukai(kos.getAmaunTuntutan());
                            }

                        } else {
                            //hardcode utk melaka
                            // P11 ialah kebun
                            // P04 ialah buah-buahan
                            //ikut value dari meeting kebun mesti use buah2an

                            //if(kodGunaTanah.equalsIgnoreCase("P11")){
                            //    kodGunaTanah = "P04";
                            //}

                            hmb2.setCukai(calcTax.calculate(kodGunaTanah, kodBpm, kodUOM, hmb2.getLuas(), hmb2, kodRizab));
                        }
                        if (mohonREG.getKodUrusan().getKod().equals("SBTL")) {
                            hmb2.setLuas(BigDecimal.ZERO);
                        }

                        hmb2.setCukaiSebenar(hmb2.getCukai());
                        hmb2.setNoVersiDhde(0); //default new hakmilik
                        hmb2.setNoVersiDhke(0);

                        Hakmilik hmBaru = js.returnJanaHakmilikBaruFromHakmilikTerlibat(permohonan.getSenaraiHakmilik().get(0).getHakmilik(), hmb2, ia, mohonREG, pengguna, permohonanPlotPelan);
                        senaraiHakmilikRujukan.add(hmBaru);
                    }
                }
            }

            List<HakmilikPermohonan> hmp = permohonan.getSenaraiHakmilik();
            LOG.info(sum);
//            mhservice.janaHakmilikBaruFromHakmilikTerlibat(hmp, hmb, ia, mohonREG, pengguna, sum, null, listluas);


        } catch (Exception ex) {
            error = ex.toString();
        }


        return error;
    }

    public String janaHakmilikBaruPenyatuan(Permohonan mohonREG, Pengguna pengguna, Permohonan permohonan, String sts, List<Hakmilik> senaraiHakmilik) {
        String error = "";
        String noPA = "";
        try {
            InfoAudit ia = new InfoAudit();
            Date now = new Date();
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            Hakmilik hmb = new Hakmilik();
            KodHakmilik kh = new KodHakmilik();
            mohonUkur = new PermohonanUkur();
            mohonUkur = devServ.findMohonUkurByMohon(permohonan.getIdPermohonan());
            hmb.setNoPu(mohonUkur.getNoPermohonanUkur());
            hmb.setNoFail(permohonan.getIdPermohonan());
            hmb.setNoVersiDhde(0);
            hmb.setNoVersiDhke(0);

            List listNaikTarafKwsn = devServ.findHakmilikUrusanByHakmilikAndUrusan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ITP", "ITB");

            if (sts.equalsIgnoreCase("QT")) {
                if (conf.getProperty("kodNegeri").equals("04")) {

                    if (checkFlagAscii(permohonan.getIdPermohonan())) {
                        if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("GRN") || permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("PN") || permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HSD")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HSD"));
                        } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("GM") || permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("PM") || permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HSM")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HSM"));
                        } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("GMM") || permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HMM")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HMM"));
                        }
                    } else {
                        if (mohonUkur.getKodHakmilik().getKod().equals("GRN") || mohonUkur.getKodHakmilik().getKod().equals("PN") || mohonUkur.getKodHakmilik().getKod().equals("HSD")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HSD"));
                        } else if (mohonUkur.getKodHakmilik().getKod().equals("GM") || mohonUkur.getKodHakmilik().getKod().equals("PM") || mohonUkur.getKodHakmilik().getKod().equals("HSM")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HSM"));
                        } else if (mohonUkur.getKodHakmilik().getKod().equals("GMM") || mohonUkur.getKodHakmilik().getKod().equals("HMM")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HMM"));
                        }
                    }

                } else if (conf.getProperty("kodNegeri").equals("05")) {
                    if (listNaikTarafKwsn.size() == 0) {
                        if (mohonUkur.getKodHakmilik().getKod().equals("GRN") || mohonUkur.getKodHakmilik().getKod().equals("PN") || mohonUkur.getKodHakmilik().getKod().equals("HSD")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HSD"));
                        } else if (mohonUkur.getKodHakmilik().getKod().equals("GM") || mohonUkur.getKodHakmilik().getKod().equals("PM") || mohonUkur.getKodHakmilik().getKod().equals("HSM")) {
                            hmb.setKodHakmilik(kodHakmilikDAO.findById("HSM"));
                        }
                    } else {
                        hmb.setKodHakmilik(kodHakmilikDAO.findById("HSD"));
                    }
                }

            } else {
                if (listNaikTarafKwsn.size() == 0) {
                    if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HSD")) {
                        //GRN
                        kh = kodHakmilikDAO.findById(mohonUkur.getKodHakmilik().getKod());
                        hmb.setKodHakmilik(kh);
                    } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HMM")) {
                        kh = kodHakmilikDAO.findById("GMM");
                        hmb.setKodHakmilik(kh);
                    } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getKod().equals("HSM")) {
                        kh = kodHakmilikDAO.findById(mohonUkur.getKodHakmilik().getKod());
                        hmb.setKodHakmilik(kh);
                    } else {
                        hmb.setKodHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik());
                    }
                } else {
                    hmb.setKodHakmilik(kodHakmilikDAO.findById("GRN"));
                }

                List<PelanGIS> list = findPelanB1ByMohon(permohonan.getIdPermohonan());
                if (list.size() > 0) {
                    noPA = list.get(0).getNoPelanAkui();
                    hmb.setNoPelan(noPA);
                    hmb.setNoLot(formatNoLotIkutPendaftaran(list.get(0).getPelanGISPK().getNoLot()));
                }
            }
            senaraiHakmilikRujukan = new ArrayList<Hakmilik>();
            String[] listluas = new String[0];
            int sum = 0;
            List<PermohonanPlotPelan> mpp = devServ.findPermohonanPlotPelanByIdPermohonan(permohonan.getIdPermohonan());
            listluas = new String[100];
            LOG.info("SIZE mohon_plot_plan :::" + mpp.size());
            for (int i = 0; i < mpp.size(); i++) {
                PermohonanPlotPelan permohonanPlotPelan = new PermohonanPlotPelan();
                permohonanPlotPelan = mpp.get(i);
                if (permohonanPlotPelan.getPemilikan().getKod() == 'H') {
                    for (int j = 0; j < permohonanPlotPelan.getBilanganPlot(); j++) {
                        listluas[sum] = permohonanPlotPelan.getLuas().toString();
                        sum = sum + 1;
                        //start set hakmilik
                        //todo
                        Hakmilik hmb2 = new Hakmilik();
                        hmb2.setKodHakmilik(hmb.getKodHakmilik());
                        //
                        hmb2.setNoPu(hmb.getNoPu());
                        hmb2.setNoFail(permohonan.getIdPermohonan());
                        //
                        hmb2.setKategoriTanah(permohonanPlotPelan.getKategoriTanah());
                        //hmb2.setKategoriTanah(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKategoriTanah());
                        hmb2.setKegunaanTanah(permohonanPlotPelan.getKegunaanTanah());
                        //hmb2.setKegunaanTanah(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKegunaanTanah());
                        //  hmb2.setKodHakmilik(devServ.findMohonUkurByMohon(permohonan.getIdPermohonan()).getKodHakmilik());
                        if (permohonan.getKodUrusan().getKod().equals("TSPSS")) {
                            hmb2.setSekatanKepentingan(permohonanPlotPelan.getKodSekatanKepentingan());
                            hmb2.setSyaratNyata(permohonanPlotPelan.getKodSyaratNyata());

                        } else {
                            //hmb2.setSekatanKepentingan(permohonanPlotPelan.getKodSekatanKepentingan());
                            hmb2.setSekatanKepentingan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSekatanKepentingan());
                            //hmb2.setSyaratNyata(permohonanPlotPelan.getKodSyaratNyata());
                            hmb2.setSyaratNyata(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSyaratNyata());
                        }

                        hmb2.setKodUnitLuas(permohonanPlotPelan.getKodUnitLuas());
                        hmb2.setLuas(permohonanPlotPelan.getLuas());
                        //hmb2.setCukaiSebenar(BigDecimal.ZERO);
//                        String kodTanah, String kodBpm, String kodUOM, BigDecimal luas, Hakmilik hakmilik, String kodRizab
//                        hmb2.setCukai(calcTax.calculate(hmb2.getKategoriTanah().getKod(), String.valueOf(hmb2.getBandarPekanMukim().getKod()), hmb2.getKodUnitLuas().getKod(), hmb2.getLuas(), hmb2, String.valueOf(hmb2.getRizab().getKod())));


                        if (sts.equalsIgnoreCase("QT")) {
                            KodLot kl = new KodLot();
                            kl.setKod("3");
                            hmb2.setLot(kl);
                            hmb2.setNoLot(formatNoLotIkutPendaftaran(devServ.findNolotByIdPlot(permohonanPlotPelan.getIdPlot()).getNoPt() == null ? "" : String.valueOf(devServ.findNolotByIdPlot(permohonanPlotPelan.getIdPlot()).getNoPt())));
                            hmb2.setPegangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPegangan()); // Pegangan HS is Pajakan
                            hmb2.setTempohPegangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getTempohPegangan());//99 Tahun
                        } else {
                            KodLot kl = new KodLot();
                            kl.setKod("1");
                            hmb2.setLot(kl);
                            String numberPT = devServ.findNolotByIdPlot(permohonanPlotPelan.getIdPlot()).getNoPt() == null ? "" : String.valueOf(devServ.findNolotByIdPlot(permohonanPlotPelan.getIdPlot()).getNoPt());
                            if (numberPT != null) {
                                hmb2.setNoLot(formatNoLotIkutPendaftaran(devServ.findNoLotGisPelanByPT(permohonan.getIdPermohonan(), numberPT)));
                            } else {
                                hmb2.setNoLot(formatNoLotIkutPendaftaran(devServ.findNoLotGisPelanByIDMohon(permohonan.getIdPermohonan())));
                            }
                            hmb2.setNoPelan(noPA);
                            hmb2.setPegangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPegangan());
                            hmb2.setTempohPegangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getTempohPegangan());
                        }
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hmb.setKodStatusHakmilik(ksh);
                        hmb2.setKodStatusHakmilik(ksh);
                        hmb2.setCawangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getCawangan());
                        hmb2.setSeksyen(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSeksyen());
                        hmb2.setLokasi(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLokasi());
                        hmb2.setDaerah(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah());
                        hmb2.setMilikPersekutuan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getMilikPersekutuan());
                        hmb2.setLotBumiputera(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLotBumiputera());
//                    hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());
                        hmb2.setNoLitho(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getNoLitho());
                        hmb2.setTarikhDaftarAsal(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftarAsal());
                        hmb2.setBandarPekanMukim(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim());
                        hmb2.setRizab(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getRizab());
                        hmb2.setRizabKawasan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getRizabKawasan());
                        hmb2.setRizabNoWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getRizabNoWarta());
                        hmb2.setRizabTarikhWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getRizabTarikhWarta());
                        //hmb2.setPegangan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPegangan());
                        hmb2.setGsaNoWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getGsaNoWarta());
                        hmb2.setGsaKawasan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getGsaKawasan());
                        hmb2.setGsaTarikhWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getGsaTarikhWarta());

                        hmb2.setPbtKawasan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPbtKawasan());
                        hmb2.setPbtNoWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPbtNoWarta());
                        hmb2.setPbtTarikhWarta(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getPbtTarikhWarta());

                        hmb2.setMilikPersekutuan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getMilikPersekutuan());


                        String kodGunaTanah = hmb2.getKegunaanTanah().getKod();
                        String kodTanah = hmb2.getKategoriTanah().getKod();
                        Integer intBpm = 0;
                        if (hmb2.getBandarPekanMukim() != null) {
                            intBpm = hmb2.getBandarPekanMukim().getKod();
                        }
                        String kodBpm = intBpm.toString();
                        String kodUOM = hmb2.getKodUnitLuas().getKod();
                        Integer intRizab = 0;
                        if (hmb2.getRizab() != null) {
                            intRizab = hmb2.getRizab().getKod();
                        }
                        String kodRizab = intRizab.toString();
                        //hardcode utk melaka
                        // P11 ialah kebun
                        // P04 ialah buah-buahan
                        //ikut value dari meeting kebun mesti use buah2an

                        //if(kodGunaTanah.equalsIgnoreCase("P11")){
                        //    kodGunaTanah = "P04";
                        //}
                        hmb2.setCukai(calcTax.calculate(kodGunaTanah, kodBpm, kodUOM, hmb2.getLuas(), hmb2, kodRizab));

                        hmb2.setCukaiSebenar(hmb2.getCukai());
                        hmb2.setNoVersiDhde(0); //default new hakmilik
                        hmb2.setNoVersiDhke(0);

                        Hakmilik hmBaru = js.returnJanaHakmilikBaruPenyatuanFromHakmilikTerlibat(senaraiHakmilik, hmb2, ia, mohonREG, pengguna);
                        senaraiHakmilikRujukan.add(hmBaru);
                    }
                }
            }

            List<HakmilikPermohonan> hmp = permohonan.getSenaraiHakmilik();
            LOG.info(sum);
//            mhservice.janaHakmilikBaruFromHakmilikTerlibat(hmp, hmb, ia, mohonREG, pengguna, sum, null, listluas);


        } catch (Exception ex) {
            error = ex.toString();
        }


        return error;
    }

    public String janaHakmilikBaruKekalGantiSementara(Permohonan mohonREG, Pengguna pengguna, Permohonan permohonan, String sts, List<Hakmilik> senaraiHakmilik) {
        String error = "";
        String noPA = "";
        try {
            InfoAudit ia = new InfoAudit();
            Date now = new Date();
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            Hakmilik hmb = new Hakmilik();
            KodHakmilik kh = new KodHakmilik();
            mohonUkur = new PermohonanUkur();
            mohonUkur = devServ.findMohonUkurByMohon(permohonan.getIdPermohonan());
            hmb.setNoPu(mohonUkur.getNoPermohonanUkur());
            hmb.setNoFail(permohonan.getIdPermohonan());
            hmb.setNoVersiDhde(0);
            hmb.setNoVersiDhke(0);

            if (sts.equalsIgnoreCase("QT")) {
            } else {
                //please amik kira jenis hakmilik yg ditetapkan oleh pbn/exco/emmkn
                if (senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD")) {
                    //GRN
                    kh = kodHakmilikDAO.findById("GRN");
                    hmb.setKodHakmilik(kh);
                } else if (senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HMM")) {
                    kh = kodHakmilikDAO.findById("GMM");
                    hmb.setKodHakmilik(kh);
                } else if (senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSM")) {
                    kh = kodHakmilikDAO.findById("GM");
                    hmb.setKodHakmilik(kh);
                }

                List<PelanGIS> list = findPelanB1ByMohon(permohonan.getIdPermohonan());
                if (list.size() > 0) {
                    noPA = list.get(0).getNoPelanAkui();
                    hmb.setNoPelan(noPA);
                    hmb.setNoLot(formatNoLotIkutPendaftaran(list.get(0).getPelanGISPK().getNoLot()));
                    //hmb.setSeksyen(list.get(0).getPelanGISPK());
                }
            }

            senaraiHakmilikRujukan = new ArrayList<Hakmilik>();

            for (Hakmilik hakmiliksementara : senaraiHakmilik) {

                //start set hakmilik
                //todo
                Hakmilik hmb2 = new Hakmilik();
                hmb2.setKodHakmilik(hmb.getKodHakmilik());
                //
                hmb2.setNoPu(hmb.getNoPu());
                hmb2.setNoFail(permohonan.getIdPermohonan());
                //
                hmb2.setKategoriTanah(hakmiliksementara.getKategoriTanah());
                hmb2.setKegunaanTanah(hakmiliksementara.getKegunaanTanah());
                hmb2.setSekatanKepentingan(hakmiliksementara.getSekatanKepentingan());
                hmb2.setSyaratNyata(hakmiliksementara.getSyaratNyata());

                hmb2.setKodUnitLuas(hakmiliksementara.getKodUnitLuas());
                hmb2.setLuas(hakmiliksementara.getLuas());
                //hmb2.setCukaiSebenar(BigDecimal.ZERO);

                if (sts.equalsIgnoreCase("QT")) {
                } else {
                    KodLot kl = new KodLot();
                    kl.setKod("1");
                    hmb2.setLot(kl);
                    String numberPT = hakmiliksementara.getNoLot();
                    hmb2.setNoLot(formatNoLotIkutPendaftaran(devServ.findNoLotGisPelanByPT(permohonan.getIdPermohonan(), numberPT)));
                    hmb2.setNoPelan(noPA);
                    hmb2.setPegangan(new Character('S'));
                    hmb2.setTempohPegangan(null);
                }
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("T");
                hmb.setKodStatusHakmilik(ksh);
                hmb2.setKodStatusHakmilik(ksh);
                hmb2.setCawangan(hakmiliksementara.getCawangan());
                hmb2.setSeksyen(hakmiliksementara.getSeksyen());
                hmb2.setLokasi(hakmiliksementara.getLokasi());
                hmb2.setDaerah(hakmiliksementara.getDaerah());
                hmb2.setMilikPersekutuan(hakmiliksementara.getMilikPersekutuan());
                hmb2.setLotBumiputera(hakmiliksementara.getLotBumiputera());
//                    hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());
                hmb2.setNoLitho(hakmiliksementara.getNoLitho());
                hmb2.setTarikhDaftarAsal(hakmiliksementara.getTarikhDaftarAsal());
                hmb2.setBandarPekanMukim(hakmiliksementara.getBandarPekanMukim());
                hmb2.setRizab(hakmiliksementara.getRizab());
                hmb2.setRizabKawasan(hakmiliksementara.getRizabKawasan());
                hmb2.setRizabNoWarta(hakmiliksementara.getRizabNoWarta());
                hmb2.setRizabTarikhWarta(hakmiliksementara.getRizabTarikhWarta());

                hmb2.setGsaNoWarta(hakmiliksementara.getGsaNoWarta());
                hmb2.setGsaKawasan(hakmiliksementara.getGsaKawasan());
                hmb2.setGsaTarikhWarta(hakmiliksementara.getGsaTarikhWarta());

                hmb2.setPbtKawasan(hakmiliksementara.getPbtKawasan());
                hmb2.setPbtNoWarta(hakmiliksementara.getPbtNoWarta());
                hmb2.setPbtTarikhWarta(hakmiliksementara.getPbtTarikhWarta());

                hmb2.setMilikPersekutuan(hakmiliksementara.getMilikPersekutuan());


                String kodGunaTanah = hmb2.getKegunaanTanah().getKod();
                String kodTanah = hmb2.getKategoriTanah().getKod();
                Integer intBpm = 0;
                if (hmb2.getBandarPekanMukim() != null) {
                    intBpm = hmb2.getBandarPekanMukim().getKod();
                }
                String kodBpm = intBpm.toString();
                String kodUOM = hmb2.getKodUnitLuas().getKod();
                Integer intRizab = 0;
                if (hmb2.getRizab() != null) {
                    intRizab = hmb2.getRizab().getKod();
                }
                String kodRizab = intRizab.toString();
                //hardcode utk melaka
                // P11 ialah kebun
                // P04 ialah buah-buahan
                //ikut value dari meeting kebun mesti use buah2an

                //if(kodGunaTanah.equalsIgnoreCase("P11")){
                //    kodGunaTanah = "P04";
                //}
                hmb2.setCukai(calcTax.calculate(kodGunaTanah, kodBpm, kodUOM, hmb2.getLuas(), hmb2, kodRizab));

                hmb2.setCukaiSebenar(hmb2.getCukai());
                hmb2.setNoVersiDhde(0); //default new hakmilik
                hmb2.setNoVersiDhke(0);

                Hakmilik hmBaru = js.returnJanaHakmilikBaruFromHakmilikTerlibat(hakmiliksementara, hmb2, ia, mohonREG, pengguna, new PermohonanPlotPelan());
                senaraiHakmilikRujukan.add(hmBaru);
            }










        } catch (Exception ex) {
            error = ex.toString();
        }


        return error;
    }

    public boolean checkCukai(String idHakmilik) {
        boolean cukai = false;
        String query = "SELECT b FROM etanah.model.Akaun b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        Akaun a = (Akaun) q.uniqueResult();
        if (a != null) {
            if (a.getBaki().equals(BigDecimal.ZERO)) {
                cukai = true;
            } else {
                cukai = false;
            }
        } else {
            LOG.info("Account not found!");
            cukai = false;
        }

        return cukai;
    }

    public PermohonanTuntutanKos findCukai(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod = 'DEV10'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<PelanGIS> findPelanByMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PelanGIS> findPelanB1ByMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.jenisPelan = 'B1'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    private void updateMohonRujukLuar(Permohonan mohon, InfoAudit ia, String kodJabatan, Permohonan permohonan, Hakmilik hakmilik) {
        LOG.info(mohon);
        PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
        permohonanRujukanLuar.setCawangan(mohon.getCawangan());
        permohonanRujukanLuar.setPermohonan(mohon);
        permohonanRujukanLuar.setInfoAudit(ia);
        LOG.info("idhakmilik yg diinsert  = " + hakmilik.getIdHakmilik());
        permohonanRujukanLuar.setHakmilik(hakmilik);
        KodRujukan r = kodRujukanDAO.findById("FL");
        KodJabatan kj = kodJabatanDAO.findById(kodJabatan);
        permohonanRujukanLuar.setJabatan(kj);
        //permohonanRujukanLuar.setNoRujukan(permohonan.getIdPermohonan());
        permohonanRujukanLuar.setNoFail(permohonan.getIdPermohonan());
        permohonanRujukanLuar.setKodRujukan(r);
        permohonanRujukanLuar.setTarikhRujukan(permohonan.getTarikhKeputusan());
        List<PermohonanKertas> senaraiKertas = permohonan.getSenaraiKertas();

//
//        String strDate = "01/01/1900";
//        DateFormat formatter;
        Date date = null;
        Date lulusan = new Date();
        String NoSidang = "";
        String NoRuj = "";
        String NamaSidang = "";
//        formatter = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            date = formatter.parse(strDate);
//        } catch (Exception e) {
//        }

        //prl = pembangunanServ.findUlasanJabatanTek2(permohonan.getIdPermohonan());

        if (conf.getProperty("kodNegeri").equals("05")) {
            for (PermohonanKertas mk : senaraiKertas) {
                //please update based on urusan
                if (mohon.getKodUrusan().getKod().equals("SBTL")
                        || mohon.getKodUrusan().getKod().equals("SBSTL")
                        || mohon.getKodUrusan().getKod().equals("SSKPL")
                        || mohon.getKodUrusan().getKod().equals("TSSKL")
                        || mohon.getKodUrusan().getKod().equals("TSSKB")
                        || mohon.getKodUrusan().getKod().equals("CL")
                        || mohon.getKodUrusan().getKod().equals("PSL")
                        || mohon.getKodUrusan().getKod().equals("PBL")
                        || mohon.getKodUrusan().getKod().equals("SBKSL")) {

                    NamaSidang = "Majlis Mesyuarat Kerajaan Negeri Negeri Sembilan";

                    //TSMMK
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("TSMMK")) {
                        fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanmmkptg");
                        Pengguna ppp = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                        if (mk.getKodDokumen().getKod().equals("MMKTG")) {
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        } else if (mk.getKodDokumen().getKod().equals("MMK")) {
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        }
                    }

                    //TSPSS
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSS")) {
                        fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanmmkptg");
                        Pengguna ppp = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                        if (mk.getKodDokumen().getKod().equals("MMKTG")) {
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        } else if (mk.getKodDokumen().getKod().equals("MMK")) {
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        }
                    }

                    //PYTN
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("PYTN")) {

                        if (mk.getKodDokumen().getKod().equals("KPTG")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "kpsnpertimbptg");
                            Pengguna ppp1 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                            lulusan = fasaPermohonan.getTarikhKeputusan();
                            NoSidang = ppp1.getNama();
                        } else if (mk.getKodDokumen().getKod().equals("KPTD")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "kpsnkertaskerjaptd");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                            lulusan = fasaPermohonan.getTarikhKeputusan();
                            NoSidang = ppp2.getNama();
                        } else if (mk.getKodDokumen().getKod().equals("MMKTG")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanptgmmk");
                            Pengguna ppp = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        } else if (mk.getKodDokumen().getKod().equals("MMK")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanptgmmk");
                            Pengguna ppp = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        }
                    }

                    //TSPTG
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPTG")) {
                        fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "kpsnkertaspertimbptg");
                        Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());
                        NamaSidang = "Pendaftar Hakmilik Negeri Sembilan";

                        lulusan = fasaPermohonan.getTarikhKeputusan();
                        NoSidang = ppp2.getNama();
                    }

                    //TSPTD
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPTD")) {
                        fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "kpsnpertimbptd");
                        Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());
                        NamaSidang = "Pentadbir Tanah Daerah" + mohon.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama();

                        lulusan = fasaPermohonan.getTarikhKeputusan();
                        NoSidang = ppp2.getNama();
                    }

                    //PSBT
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("PSBT")) {
                        if (mk.getKodDokumen().getKod().equals("KPTG")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "kpsnpertimbptg");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                            lulusan = fasaPermohonan.getTarikhKeputusan();
                            NoSidang = ppp2.getNama();
                        } else if (mk.getKodDokumen().getKod().equals("KPTD")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "kpsnkertaskerjaptd");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                            lulusan = fasaPermohonan.getTarikhKeputusan();
                            NoSidang = ppp2.getNama();
                        }
                    }

                    //PSMT
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("PSMT")) {
                        if (mk.getKodDokumen().getKod().equals("KPTG")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "kpsnpertimbptg");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                            lulusan = fasaPermohonan.getTarikhKeputusan();
                            NoSidang = ppp2.getNama();
                        } else if (mk.getKodDokumen().getKod().equals("KPTD")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "kpsnkertaskerjaptd");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                            lulusan = fasaPermohonan.getTarikhKeputusan();
                            NoSidang = ppp2.getNama();
                        }
                    }

                    //PPCS
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("PPCS")) {
                        if (mk.getKodDokumen().getKod().equals("MMK")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanmmkptg");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp2.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        } else if (mk.getKodDokumen().getKod().equals("MMKTG")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanmmkptg");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp2.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        }
                    }

                    //PPCB & PPCBA
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("PPCB") || mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("PPCBA")) {

                        if (mk.getKodDokumen().getKod().equals("KPTG")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "kpsnpertimbptg");
                            Pengguna ppp1 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                            lulusan = fasaPermohonan.getTarikhKeputusan();
                            NoSidang = ppp1.getNama();
                        } else if (mk.getKodDokumen().getKod().equals("KPTD")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "kpsnkertaskerjaptd");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                            lulusan = fasaPermohonan.getTarikhKeputusan();
                            NoSidang = ppp2.getNama();
                        } else if (mk.getKodDokumen().getKod().equals("MMKTG")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanptgmmk");
                            Pengguna ppp = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        } else if (mk.getKodDokumen().getKod().equals("MMK")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanptgmmk");
                            Pengguna ppp = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());

                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        }
                    }

                    //SBPS
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("SBPS")) {
                        if (mk.getKodDokumen().getKod().equals("MMK")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanmmkptg");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp2.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        } else if (mk.getKodDokumen().getKod().equals("MMKTG")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanmmkptg");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp2.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        }
                    }

                    //SBMS
                    if (mohon.getPermohonanSebelum().getKodUrusan().getKod().equals("SBMS")) {
                        if (mk.getKodDokumen().getKod().equals("MMK")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanmmkptg");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp2.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        } else if (mk.getKodDokumen().getKod().equals("MMKTG")) {
                            fasaPermohonan = consentPtdService.findMohonFasaByStage(mohon.getPermohonanSebelum().getIdPermohonan(), "perakuanmmkptg");
                            Pengguna ppp2 = pembangunanServ.findNama(fasaPermohonan.getIdPengguna());
                            lulusan = mk.getTarikhSahKeputusan();
                            NoSidang = ppp2.getNama();

                            date = mk.getTarikhSidang();
                            NoRuj = mk.getTajuk();
                        }
                    }
                }

                permohonanRujukanLuar.setNoRujukan(NoRuj); // no msyuarat
                permohonanRujukanLuar.setTarikhRujukan(date);// tarikh mesyuarat

                permohonanRujukanLuar.setNoSidang(NoSidang); // yang meluluskan
                permohonanRujukanLuar.setTarikhSidang(lulusan); //tarikh kelulusan

                permohonanRujukanLuar.setNamaSidang(NamaSidang); //nama sidang



            }


        } else if (conf.getProperty("kodNegeri").equals("04")) {


            String strDate = "01/01/1900";
            DateFormat formatter;
            date = null;
            //Date lulusan = new Date();
            //String NoSidang = "";
            //String NoRuj = "";
            //String NamaSidang = "";
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                date = formatter.parse(strDate);
            } catch (Exception e) {
            }



            for (PermohonanKertas mk : senaraiKertas) {
                if (mk.getKodDokumen().getKod().equals("JKBB")) {
                    if (mk.getTarikhSidang() != null) {
                        if (mk.getTarikhSidang().compareTo(date) > 0) {
                            date = mk.getTarikhSidang();
                            permohonanRujukanLuar.setTarikhSidang(date);
                            permohonanRujukanLuar.setNoSidang(mk.getTajuk());
                        }
                    } else {
                        System.out.println("--NO TARIKH SIDANG--");
                        permohonanRujukanLuar.setTarikhSidang(new java.util.Date());
                        permohonanRujukanLuar.setNoSidang(mk.getTajuk());
                    }
                }
            }

        }

        if (mohon.getKodUrusan().getKod().equals("TSSKL") || mohon.getKodUrusan().getKod().equals("TSSKB")) {
            if (conf.getProperty("kodNegeri").equals("04")) {
                permohonanRujukanLuar.setNamaSidang("Jawatankuasa Belah Bahagi Tanah Melaka");
            }

        } else {
            if (mohon.getPermohonanSebelum().getKeputusan() != null) {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    //permohonanRujukanLuar.setNamaSidang("Majlis Mesyuarat Kerajaan Negeri Negeri Sembilan");
                } else if (conf.getProperty("kodNegeri").equals("04")) {
                    permohonanRujukanLuar.setNamaSidang("Jawatankuasa Belah Bahagi Tanah Melaka");
                }

            } else {
                if (mohon.getPermohonanSebelum().getSenaraiFasa().size() > 0) {
                    List<FasaPermohonan> mohonFasa = mohon.getPermohonanSebelum().getSenaraiFasa();
                    for (FasaPermohonan fasa : mohonFasa) {
                        if (conf.getProperty("kodNegeri").equals("05")) {
//                            if (fasa.getIdAliran().equals("kpsnkertaskerjaptd") && fasa.getKeputusan() != null) {
//                                permohonanRujukanLuar.setNamaSidang("Pentadbir Tanah Daerah" + mohon.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama());
//                            }
//                            if (fasa.getIdAliran().equals("kpsnpertimbptg") && fasa.getKeputusan() != null) {
//                                permohonanRujukanLuar.setNamaSidang("Pendaftar Hakmilik Negeri Sembilan");
//                            }
                        } else if (conf.getProperty("kodNegeri").equals("04")) {
                            if (fasa.getIdAliran().equals("cetakjkbbrekodkpsn") && fasa.getKeputusan() != null) {
                                permohonanRujukanLuar.setNamaSidang("Jawatankuasa Belah Bahagi Tanah Melaka");
                            }
                        }


                    }
                }
            }
        }
        strService.SimpanMohonRujukLuar(permohonanRujukanLuar);
    }

    public List<Pengguna> findPenggunaByBPEL(List<String> bpelName, String kodCaw) {
        String query = "Select u from etanah.model.PenggunaPeranan u WHERE ";
        int count = 0;
        for (String s : bpelName) {
            if (count == 0) {
                query = query + "u.peranan.kumpBPEL ='" + s + "'";
            } else {
                query = query + " or u.peranan.kumpBPEL ='" + s + "'";
            }
            count++;
        }
        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPp = q.list();
        for (PenggunaPeranan pp : listPp) {
            listPengguna.add(pp.getPengguna());
        }
        return listPengguna;
    }

//    private String stringListHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
//        String displayHM = new String();
//        for(HakmilikPermohonan hp : senaraiHakmilik){
//            displayHM = hp.getHakmilik().getIdHakmilik() + " cukai (RM)=" + hp.getHakmilik().getCukai() + '\n';
//        }
//        return displayHM;
//    }
    private String stringListHakmilik(String idPermohonan) {
        String displayHM = new String();
        if (!idPermohonan.isEmpty()) {
            String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("idPermohonan", idPermohonan);
            List<HakmilikPermohonan> senaraiHakmilik = q.list();

            if (senaraiHakmilik.size() > 0) {
                for (HakmilikPermohonan hp : senaraiHakmilik) {
                    displayHM = displayHM + hp.getHakmilik().getIdHakmilik() + " cukai (RM)=" + hp.getHakmilik().getCukai() + '\n';
                }
            }

        }
        return displayHM;
    }

    private boolean checkFlagAscii(String idPermohonan) {
        boolean flagAscii = false;

        ArrayList<PermohonanPlotPelan> listMohonPlotPelan = new ArrayList<PermohonanPlotPelan>(pembangunanServ.findPermohonanPlotPelanByIdPermohonan(idPermohonan));
        for (PermohonanPlotPelan mpp : listMohonPlotPelan) {
            LOG.info("list size of mohon_plot_pelan = " + listMohonPlotPelan.size());
            List<NoPt> senaraiNoPT = new ArrayList<NoPt>();
            senaraiNoPT = devServ.findListNoPtByIdMohon(mpp.getIdPlot());
            if (senaraiNoPT.size() > 0) {
                NoPt beanNoPt = senaraiNoPT.get(0);
                if (beanNoPt != null) {
                    if (beanNoPt.getNoPtSementara() != null) {
                        if (beanNoPt.getNoPtSementara() == 1000L) {
                            LOG.info("This is message for flag ascii - no_pt_sementara = 1000");
                            flagAscii = true;
                            break;
                        }
                    }
                }
            }

        }

        return flagAscii;
    }

    public String formatNoLotIkutPendaftaran(String noLot) {
        NumberFormat formatter = new DecimalFormat("0000000");
        String returnString = new String();
        if (StringUtils.isNotBlank(noLot)) {
            returnString = formatter.format(Integer.parseInt(noLot));
        }
        return returnString;
    }
}
