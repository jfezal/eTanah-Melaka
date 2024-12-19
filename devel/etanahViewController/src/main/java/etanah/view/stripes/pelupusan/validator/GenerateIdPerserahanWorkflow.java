/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCaraPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanUkur;
import etanah.model.TanahRizabPermohonan;
import etanah.model.gis.PelanGIS;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.PelupusanService;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.initiateService.MohonHakmilikPelupusanService;
import etanah.workflow.WorkFlowService;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author w.fairul
 */
public class GenerateIdPerserahanWorkflow {

    private static final Logger LOG = Logger.getLogger(GenerateIdPerserahanWorkflow.class);
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
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    MohonHakmilikPelupusanService mhservice;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    PelupusanService plpservice;
    @Inject
    KodCaraPermohonanDAO kodCaraPermohonanDAO;
    private Permohonan permohonan;
    /*
     *  for permohonan that not start with spoc
     *  generate id permohonan and send to bpel process
     *  @param KodUrusan
     *  @param Pengguna
     *  @param hakmilikList
     */

    public boolean generateIdPerserahanWorkflow(KodUrusan ku, Pengguna pengguna, List<HakmilikPermohonan> senaraiHakmilik, Permohonan permohonan, String stageName) {
        KodCawangan caw = pengguna.getKodCawangan();
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

//            for (Hakmilik hm : senaraiHakmilik) {
            Permohonan p = new Permohonan();
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(pengguna.getKodCawangan());
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            p.setIdWorkflow(ku.getIdWorkflowIntegration());
            p.setIdStagePermohonanSebelum(stageName);
            p.setCaraPermohonan(kodCaraPermohonanDAO.findById("UD"));
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
//                p.setPenyerahNama(permohonan.getPenyerahNama());
                p.setPenyerahNama("Unit Pelupusan");
                p.setKodPenyerah(permohonan.getKodPenyerah());
                p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
                p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
                if (permohonan.getPenyerahAlamat3() != null) {
                    p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
                }

                if (permohonan.getPenyerahAlamat4() != null) {
                    p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
                }
                p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
                p.setPenyerahNegeri(permohonan.getPenyerahNegeri());

//                }
                p.setInfoAudit(ia);
                setPermohonan(p);
                permohonanDAO.save(p);
                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                
                if (senaraiHakmilik != null) {
                    for (HakmilikPermohonan hp : senaraiHakmilik) {
                        DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                        HakmilikPihakBerkepentingan hpb = null;
                        String idMohonHakmilik = new String();
                        String idP = new String();
                        idMohonHakmilik = String.valueOf(hp.getId());
                        idP = hp.getPermohonan() != null ? hp.getPermohonan().getIdPermohonan() : new String();
                        LOG.info(" idMH : " + idMohonHakmilik);
                        LOG.info(" idMohon =" + idP);
                        if (hp.getHakmilik() != null) {
                            LOG.info(" ada hakmilik ");
                            hp = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, hp, hp.getHakmilik(), new String[]{}, disLaporanTanahService);
                        } else {

                            String kbpm = new String();
                            String ksek = new String();
                            String khakmilik = new String();
                            String klot = new String();
                            String kktanah = new String();
                            String ksyarat = new String();
                            String kguna = new String();
                            String ksekatan = new String();
                            String tP = new String();
                            String luas = new String();
                            String kuom = new String();
                            String cukai = new String();

                            /*
                             * String[] data = to cater Permohonan with null hakmilik
                             * data[0]->KodBandarPekanMukim
                             * data[1]->KodSeksyen
                             * data[2]->KodHakmilik
                             * data[3]->KodLot
                             * data[4]->NoLot
                             * data[5]->Lokasi
                             * data[6]->KodKategoriTanah
                             * data[7]->KodSyaratNyata
                             * data[8]->KodKegunaanTanah
                             * data[9]->KodSekatanKepentingan
                             * data[10]->pegangan
                             * data[11]->tempohPajakan/tempohpegangan
                             * data[12]->luas terlibat
                             * data[13]->luas UOM
                             * data[14]->Cukai Sebenar
                             */
                            kbpm = hp.getBandarPekanMukimBaru() != null ? String.valueOf(hp.getBandarPekanMukimBaru().getKod()) : null;
                            ksek = hp.getKodSeksyen() != null ? String.valueOf(hp.getKodSeksyen().getKod()) : null;
                            if (permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PBGSA") || permohonan.getKodUrusan().getKod().equals("PJTK")) {
                                khakmilik = hp.getKodHakmilikSementara() != null ? hp.getKodHakmilikSementara().getKod() : hp.getKodHakmilik().getKod();
                            } else {
                                khakmilik = hp.getKodHakmilik() != null ? hp.getKodHakmilik().getKod() : null;
                            }
                            klot = hp.getLot() != null ? hp.getLot().getKod() : null;
                            kktanah = hp.getKategoriTanahBaru() != null ? hp.getKategoriTanahBaru().getKod() : null;
                            ksyarat = hp.getSyaratNyataBaru() != null ? hp.getSyaratNyataBaru().getKod() : null;
                            kguna = hp.getKodKegunaanTanah() != null ? hp.getKodKegunaanTanah().getKod() : null;
                            ksekatan = hp.getSekatanKepentinganBaru() != null ? hp.getSekatanKepentinganBaru().getKod() : null;
                            tP = hp.getTempohPajakan() != null ? String.valueOf(hp.getTempohPajakan()) : null;
                            luas = hp.getLuasTerlibat() != null ? hp.getLuasTerlibat().toString() : null;

                            kuom = hp.getKodUnitLuas() != null ? hp.getKodUnitLuas().getKod() : null;
                            cukai = hp.getCukaiBaru() != null ? hp.getCukaiBaru().toString() : null;
                            hp = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, hp, hp.getHakmilik(), new String[]{kbpm, ksek, khakmilik, klot, hp.getNoLot(), hp.getLokasi(), kktanah, ksyarat, kguna, ksekatan, hp.getPegangan(), tP, luas, kuom, cukai}, disLaporanTanahService);
                        }

                        LOG.info(" hp.getBandarPekanMukimBaru().getKod() : " + hp.getBandarPekanMukimBaru().getKod());

                        TanahRizabPermohonan trizab = new TanahRizabPermohonan();
                        Hakmilik hm = new Hakmilik();
                        hm = disHakmilikPermohonan.copyPropertiesMohonHMToHakmilik(permohonan, hp, hm, new String[]{}, disLaporanTanahService);
                        if (permohonan.getCawangan() != null && permohonan.getCawangan().getKod().equals("08")) { //For gemas
                            hm.setDaerah(kodDaerahDAO.findById("06"));
                        } else {
                            hm.setDaerah(permohonan.getCawangan().getDaerah());
                        }
                        hm.setNoFail(permohonan.getIdPermohonan());
                        if (hp.getHakmilik() != null) {
                            hm.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                            if (ku.getKod().equals("HKGHS")) {
                                hm.setTarikhDaftarAsal(hp.getHakmilik().getTarikhDaftar());
                            }
                        }
                        FasaPermohonan fasaPermohonan = new FasaPermohonan();
                        if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                            fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "kemasukan");
                            if (fasaPermohonan.getKeputusan().getKod().equals("YQ")) {
                                hm.setKodHakmilik(kodHakmilikDAO.findById("HMM"));
                            } else if (fasaPermohonan.getKeputusan().getKod().equals("YT")) {
                                hm.setKodHakmilik(kodHakmilikDAO.findById("GMM"));
                            }
                            if (hp.getHakmilik() != null) {
                                hm.setNoLitho(hp.getHakmilik().getNoLitho() != null ? hp.getHakmilik().getNoLitho() : null);
                                //If FT No Pelan
                                hm.setNoPelan(hp.getHakmilik().getNoPelan() != null ? hp.getHakmilik().getNoPelan() : null);
                                //If QT No PU
                                hm.setNoPu(hp.getHakmilik().getNoPu() != null ? hp.getHakmilik().getNoPu() : null);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("BMBT")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik()}, 1);
                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                            if (hp.getHakmilik() != null) {
                                hm.setNoPelan(hp.getHakmilik().getNoPelan() != null ? hp.getHakmilik().getNoPelan() : null);
                                hm.setKodHakmilik(hp.getKodHakmilik());
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                            List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
                            listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            PelanGIS pelanGIS = new PelanGIS();
                            Permohonan mohonInduk = null;
                            NoPt noPt = null;
                            if (listMohonKelompok.size() > 0) {
                                mohonInduk = listMohonKelompok.get(0).getPermohonanInduk();
                            }
                            if (ku.getKod().equals("HSBM")) {
                                String noUntukPT = new String();
                                if (listMohonKelompok.size() > 0) {
                                    noPt = disLaporanTanahService.getPelupusanService().findNoPTByIdMH(Long.valueOf(idMohonHakmilik));
                                    if (noPt != null && noPt.getNoPtSementara() != null) {
                                        int a = noPt.getNoPtSementara().intValue();
                                        noUntukPT = String.format("%07d", a);
                                        LOG.info("No Pt formatted in 7 digit :" + noUntukPT);
                                    }
                                    pelanGIS = disLaporanTanahService.getPelupusanService().findPelanB2GISByIdPermohonanAndNoLot(mohonInduk.getIdPermohonan(), noUntukPT);
                                } else {
                                    pelanGIS = disLaporanTanahService.getPelupusanService().findPelanB2GISByIdPermohonan(permohonan.getIdPermohonan());
                                }

                            }
                            if (ku.getKod().equals("HKGHS") || ku.getKod().equals("HKBM")) {
                                pelanGIS = disLaporanTanahService.getPelupusanService().findPelanB1GISByIdPermohonan(permohonan.getIdPermohonan());
                            }
                            if (listMohonKelompok.size() > 0) {
                                mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{mohonInduk.getIdPermohonan()}, 0);
                                mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{mohonInduk.getIdPermohonan()}, 0);
                            } else {
                                mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                                mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            }

                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                            if (ku.getKod().equals("HKGHS") || ku.getKod().equals("HSBM")) {
                                if (pelanGIS != null) {
                                    hm.setNoPelan(pelanGIS.getNoPelanAkui());
                                }
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBGSA")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PJTK")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                        } //edit by Syazwan 18/3/2014
                        else if (permohonan.getKodUrusan().getKod().equals("MMRE")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();

                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            trizab = (TanahRizabPermohonan) disLaporanTanahService.findObject(trizab, new String[]{permohonan.getIdPermohonan()}, 1);

                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                            if (trizab != null) {
                                trizab.setNoWarta(trizab.getNoWarta());
                                trizab.setTarikhWarta(trizab.getTarikhWarta());
                                trizab.setBandarPekanMukim(trizab.getBandarPekanMukim());
                            }
                        } //edit by Syazwan 18/3/2014
                        else if (permohonan.getKodUrusan().getKod().equals("WMRE")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();

                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            trizab = (TanahRizabPermohonan) disLaporanTanahService.findObject(trizab, new String[]{permohonan.getIdPermohonan()}, 1);

                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                            if (trizab != null) {
                                trizab.setNoWarta(trizab.getNoWarta());
                                trizab.setTarikhWarta(trizab.getTarikhWarta());
                                trizab.setBandarPekanMukim(trizab.getBandarPekanMukim());
                            }
                        } //edit by Syazwan 18/3/2014
                        else if (permohonan.getKodUrusan().getKod().equals("BMRE")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();

                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            trizab = (TanahRizabPermohonan) disLaporanTanahService.findObject(trizab, new String[]{permohonan.getIdPermohonan()}, 1);

                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                            if (trizab != null) {
                                trizab.setNoWarta(trizab.getNoWarta());
                                trizab.setTarikhWarta(trizab.getTarikhWarta());
                                trizab.setBandarPekanMukim(trizab.getBandarPekanMukim());
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PTMTA")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                        }
                        /**
                         * Need to change for put data for mohon ruj luar
                         * no_mesyuarat --> no_ruj (MCL x de, urusan lain based
                         * on mohon_kertas.kod_dokumen tarikh_mesyuarat -->
                         * tarikh_ruj yang diluluslam --> no_sidang (urusan MCL
                         * , filter based on keputusan sama ada di table mohon
                         * atau mohon_fasa, letak siapa nama pengguna itu)
                         * tarikh kelulusan --> trh_sidang (urusan MCL, filter
                         * mohon_fasa.tarikh_kelulusan , else mohon.trkh_kpsn)
                         *
                         */
//                KodBandarPekanMukim kod = kodBandarPekanMukimDAO.findById(hp.getHakmilik().getBandarPekanMukim().getKod()) ;
//                LOG.info("kodBandarPekanMukimDAO :" + kodBandarPekanMukimDAO.findById(hp.getBandarPekanMukimBaru().getKod())) ;
//                LOG.info("BPM :" + kod.getNama()) ;
//                hm.setBandarPekanMukim(kod);
//                hm.setKodHakmilik(hp.getKodHakmilik());
                        //String id = hm.getIdHakmilik();
//                hm = hakmilikDAO.findById(id);
//                if (hm == null) {
//                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
//                }
//                HakmilikPermohonan hpa = new HakmilikPermohonan();
//                hpa.setHakmilik(hm);
//                hpa.setInfoAudit(ia);
//                hpa.setPermohonan(p);
//                hakmilikPermohonanDAO.save(hpa);
                        String idPermohonan = new String();
                        if (StringUtils.isNotBlank(permohonan.getCatatan()) && permohonan.getCatatan().equals("K")) {
                            idPermohonan = idP;
                        } else {
                            idPermohonan = permohonan.getIdPermohonan();
//                            permohonan = p;
                        }

                        if (permohonan.getKodUrusan().getKod().equals("BMRE") || permohonan.getKodUrusan().getKod().equals("WMRE") || permohonan.getKodUrusan().getKod().equals("MMRE")) {
                            mhservice.janaHakmilikBaru1(hm, ia, p, pengguna, idPermohonan, idMohonHakmilik, 1, trizab);
                        } else {
                            mhservice.janaHakmilikBaru(hm, ia, p, pengguna, idPermohonan, idMohonHakmilik, 1);
                        }
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
            return false;
        }
        return true;
    }

    public boolean generateIdPerserahanWorkflowPendaftar(KodUrusan ku, Pengguna pengguna, List<HakmilikPermohonan> senaraiHakmilik, Permohonan permohonan, String stageName) {
        KodCawangan caw = kodCawanganDAO.findById("00");
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

//            for (Hakmilik hm : senaraiHakmilik) {
            Permohonan p = new Permohonan();
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(caw);
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            p.setIdWorkflow(ku.getIdWorkflowIntegration());
            p.setIdStagePermohonanSebelum(stageName);
            p.setCaraPermohonan(kodCaraPermohonanDAO.findById("UD"));
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
//                p.setPenyerahNama(permohonan.getPenyerahNama());
                p.setPenyerahNama("Unit Pelupusan");
                p.setKodPenyerah(permohonan.getKodPenyerah());
                p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
                p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
                if (permohonan.getPenyerahAlamat3() != null) {
                    p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
                }

                if (permohonan.getPenyerahAlamat4() != null) {
                    p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
                }
                p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
                p.setPenyerahNegeri(permohonan.getPenyerahNegeri());

//                }
                p.setInfoAudit(ia);
                setPermohonan(p);
                permohonanDAO.save(p);
                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                if (senaraiHakmilik != null) {
                    for (HakmilikPermohonan hp : senaraiHakmilik) {
                        DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                        String idMohonHakmilik = new String();
                        String idP = new String();
                        idMohonHakmilik = String.valueOf(hp.getId());
                        idP = hp.getPermohonan() != null ? hp.getPermohonan().getIdPermohonan() : new String();
                        LOG.info(" idMH : " + idMohonHakmilik);
                        LOG.info(" idMohon =" + idP);

                        HakmilikPihakBerkepentingan hpb = null;
                        if (hp.getHakmilik() != null) {
                            hp = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, hp, hp.getHakmilik(), new String[]{}, disLaporanTanahService);
                        } else {

                            String kbpm = new String();
                            String ksek = new String();
                            String khakmilik = new String();
                            String klot = new String();
                            String kktanah = new String();
                            String ksyarat = new String();
                            String kguna = new String();
                            String ksekatan = new String();
                            String tP = new String();
                            String luas = new String();
                            String kuom = new String();
                            String cukai = new String();

                            /*
                             * String[] data = to cater Permohonan with null hakmilik
                             * data[0]->KodBandarPekanMukim
                             * data[1]->KodSeksyen
                             * data[2]->KodHakmilik
                             * data[3]->KodLot
                             * data[4]->NoLot
                             * data[5]->Lokasi
                             * data[6]->KodKategoriTanah
                             * data[7]->KodSyaratNyata
                             * data[8]->KodKegunaanTanah
                             * data[9]->KodSekatanKepentingan
                             * data[10]->pegangan
                             * data[11]->tempohPajakan/tempohpegangan
                             * data[12]->luas terlibat
                             * data[13]->luas UOM
                             * data[14]->Cukai Sebenar
                             */
                            kbpm = hp.getBandarPekanMukimBaru() != null ? String.valueOf(hp.getBandarPekanMukimBaru().getKod()) : null;
                            ksek = hp.getKodSeksyen() != null ? String.valueOf(hp.getKodSeksyen().getKod()) : null;
                            if (permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PBGSA")) {
                                khakmilik = hp.getKodHakmilikSementara() != null ? hp.getKodHakmilikSementara().getKod() : hp.getKodHakmilik().getKod();
                            } else {
                                khakmilik = hp.getKodHakmilik() != null ? hp.getKodHakmilik().getKod() : null;
                            }
                            klot = hp.getLot() != null ? hp.getLot().getKod() : null;
                            kktanah = hp.getKategoriTanahBaru() != null ? hp.getKategoriTanahBaru().getKod() : null;
                            ksyarat = hp.getSyaratNyataBaru() != null ? hp.getSyaratNyataBaru().getKod() : null;
                            kguna = hp.getKodKegunaanTanah() != null ? hp.getKodKegunaanTanah().getKod() : null;
                            ksekatan = hp.getSekatanKepentinganBaru() != null ? hp.getSekatanKepentinganBaru().getKod() : null;
                            tP = hp.getTempohPajakan() != null ? String.valueOf(hp.getTempohPajakan()) : null;
                            luas = hp.getLuasTerlibat() != null ? hp.getLuasTerlibat().toString() : null;
                            kuom = hp.getKodUnitLuas() != null ? hp.getKodUnitLuas().getKod() : null;
                            cukai = hp.getCukaiBaru() != null ? hp.getCukaiBaru().toString() : null;
                            hp = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, hp, hp.getHakmilik(), new String[]{kbpm, ksek, khakmilik, klot, hp.getNoLot(), hp.getLokasi(), kktanah, ksyarat, kguna, ksekatan, hp.getPegangan(), tP, luas, kuom, cukai}, disLaporanTanahService);
                        }

                        LOG.info(" hp.getBandarPekanMukimBaru().getKod() : " + hp.getBandarPekanMukimBaru().getKod());

                        Hakmilik hm = new Hakmilik();
                        hm = disHakmilikPermohonan.copyPropertiesMohonHMToHakmilik(permohonan, hp, hm, new String[]{}, disLaporanTanahService);
                        if (permohonan.getCawangan() != null && permohonan.getCawangan().getKod().equals("08")) { //For gemas
                            hm.setDaerah(kodDaerahDAO.findById("06"));
                        } else {
                            hm.setDaerah(permohonan.getCawangan().getDaerah());
                        }
                        hm.setNoFail(permohonan.getIdPermohonan());
                        if (hp.getHakmilik() != null) {
                            hm.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                            if (ku.getKod().equals("HKGHS")) {
                                hm.setTarikhDaftarAsal(hp.getHakmilik().getTarikhDaftar());
                            }
                        }
                        FasaPermohonan fasaPermohonan = new FasaPermohonan();
                        if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                            fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "kemasukan");
                            if (fasaPermohonan.getKeputusan().getKod().equals("YQ")) {
                                hm.setKodHakmilik(kodHakmilikDAO.findById("HMM"));
                            } else if (fasaPermohonan.getKeputusan().getKod().equals("YT")) {
                                hm.setKodHakmilik(kodHakmilikDAO.findById("GMM"));
                            }
                            if (hp.getHakmilik() != null) {
                                hm.setNoLitho(hp.getHakmilik().getNoLitho() != null ? hp.getHakmilik().getNoLitho() : null);
                                //If FT No Pelan
                                hm.setNoPelan(hp.getHakmilik().getNoPelan() != null ? hp.getHakmilik().getNoPelan() : null);
                                //If QT No PU
                                hm.setNoPu(hp.getHakmilik().getNoPu() != null ? hp.getHakmilik().getNoPu() : null);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("BMBT")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik()}, 1);
                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                            if (hp.getHakmilik() != null) {
                                hm.setNoPelan(hp.getHakmilik().getNoPelan() != null ? hp.getHakmilik().getNoPelan() : null);
                                hm.setKodHakmilik(hp.getKodHakmilik());
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                            List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
                            listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon(permohonan.getIdPermohonan());
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            PelanGIS pelanGIS = new PelanGIS();
                            Permohonan mohonInduk = new Permohonan();
                            NoPt noPt = new NoPt();
                            if (listMohonKelompok.size() > 0) {
                                mohonInduk = listMohonKelompok.get(0).getPermohonanInduk();
                            }
                            if (ku.getKod().equals("HSBM")) {
                                String noUntukPT = new String();
                                if (listMohonKelompok.size() > 0) {
                                    noPt = disLaporanTanahService.getPelupusanService().findNoPTByIdMH(Long.valueOf(idMohonHakmilik));
                                    if (noPt != null && noPt.getNoPt() != null) {
                                        String a = String.valueOf(noPt.getNoPt().intValue());
                                        noUntukPT = String.format("%07d", a);
                                        LOG.info("No Pt formatted in 7 digit :" + noUntukPT);
                                    }
                                    pelanGIS = disLaporanTanahService.getPelupusanService().findPelanB2GISByIdPermohonanAndNoLot(mohonInduk.getIdPermohonan(), noUntukPT);
                                } else {
                                    pelanGIS = disLaporanTanahService.getPelupusanService().findPelanB2GISByIdPermohonan(permohonan.getIdPermohonan());
                                }

                            }
                            if (ku.getKod().equals("HKGHS") || ku.getKod().equals("HKBM")) {
                                pelanGIS = disLaporanTanahService.getPelupusanService().findPelanB1GISByIdPermohonan(permohonan.getIdPermohonan());
                            }
                            if (listMohonKelompok.size() > 0) {
                                mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{mohonInduk.getIdPermohonan()}, 0);
                                mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{mohonInduk.getIdPermohonan()}, 0);
                            } else {
                                mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                                mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            }

                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                            if (ku.getKod().equals("HKGHS") || ku.getKod().equals("HSBM")) {
                                if (pelanGIS != null) {
                                    hm.setNoPelan(pelanGIS.getNoPelanAkui());
                                }
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBGSA")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PJTK")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                        }
                        /**
                         * Need to change for put data for mohon ruj luar
                         * no_mesyuarat --> no_ruj (MCL x de, urusan lain based
                         * on mohon_kertas.kod_dokumen tarikh_mesyuarat -->
                         * tarikh_ruj yang diluluslam --> no_sidang (urusan MCL
                         * , filter based on keputusan sama ada di table mohon
                         * atau mohon_fasa, letak siapa nama pengguna itu)
                         * tarikh kelulusan --> trh_sidang (urusan MCL, filter
                         * mohon_fasa.tarikh_kelulusan , else mohon.trkh_kpsn)
                         *
                         */
//                KodBandarPekanMukim kod = kodBandarPekanMukimDAO.findById(hp.getHakmilik().getBandarPekanMukim().getKod()) ;
//                LOG.info("kodBandarPekanMukimDAO :" + kodBandarPekanMukimDAO.findById(hp.getBandarPekanMukimBaru().getKod())) ;
//                LOG.info("BPM :" + kod.getNama()) ;
//                hm.setBandarPekanMukim(kod);
//                hm.setKodHakmilik(hp.getKodHakmilik());
                        //String id = hm.getIdHakmilik();
//                hm = hakmilikDAO.findById(id);
//                if (hm == null) {
//                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
//                }
//                HakmilikPermohonan hpa = new HakmilikPermohonan();
//                hpa.setHakmilik(hm);
//                hpa.setInfoAudit(ia);
//                hpa.setPermohonan(p);
//                hakmilikPermohonanDAO.save(hpa);
                        String idPermohonan = new String();
                        if (StringUtils.isNotBlank(permohonan.getCatatan()) && permohonan.getCatatan().equals("K")) { //For kelompok
                            idPermohonan = idP;
                        } else {
                            idPermohonan = permohonan.getIdPermohonan();
//                            permohonan = p;
                        }

                        mhservice.janaHakmilikBaruPendaftar(hm, ia, p, pengguna, idPermohonan, idMohonHakmilik, 1);
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
            return false;
        }
        return true;
    }

    public boolean generateIdPerserahanWorkflowNew(KodUrusan ku, Pengguna pengguna, List<HakmilikPermohonan> senaraiHakmilik, Permohonan permohonan, int cawangan) {
        KodCawangan caw = new KodCawangan();
        if (cawangan == 1) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = pengguna.getKodCawangan();
        }

        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

//            for (Hakmilik hm : senaraiHakmilik) {
            Permohonan p = new Permohonan();
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(pengguna.getKodCawangan());
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            p.setIdWorkflow(ku.getIdWorkflowIntegration());
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
                p.setPenyerahNama(permohonan.getPenyerahNama());
                p.setKodPenyerah(permohonan.getKodPenyerah());
                p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
                p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
                if (permohonan.getPenyerahAlamat3() != null) {
                    p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
                }

                if (permohonan.getPenyerahAlamat4() != null) {
                    p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
                }
                p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
                p.setPenyerahNegeri(permohonan.getPenyerahNegeri());

//                }
                p.setInfoAudit(ia);
                setPermohonan(p);
                permohonanDAO.save(p);
                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                if (senaraiHakmilik != null) {
                    for (HakmilikPermohonan hp : senaraiHakmilik) {
                        DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                        HakmilikPihakBerkepentingan hpb = null;
                        String idMohonHakmilik = new String();
                        String idP = new String();
                        idMohonHakmilik = String.valueOf(hp.getId());
                        idP = hp.getPermohonan() != null ? hp.getPermohonan().getIdPermohonan() : new String();
                        LOG.info(" idMH : " + idMohonHakmilik);
                        LOG.info(" idMohon =" + idP);
                        if (hp.getHakmilik() != null) {
                            LOG.info(" ada hakmilik ");
                            hp = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, hp, hp.getHakmilik(), new String[]{}, disLaporanTanahService);
                        } else {

                            String kbpm = new String();
                            String ksek = new String();
                            String khakmilik = new String();
                            String klot = new String();
                            String kktanah = new String();
                            String ksyarat = new String();
                            String kguna = new String();
                            String ksekatan = new String();
                            String tP = new String();
                            String luas = new String();
                            String kuom = new String();
                            String cukai = new String();

                            /*
                             * String[] data = to cater Permohonan with null hakmilik
                             * data[0]->KodBandarPekanMukim
                             * data[1]->KodSeksyen
                             * data[2]->KodHakmilik
                             * data[3]->KodLot
                             * data[4]->NoLot
                             * data[5]->Lokasi
                             * data[6]->KodKategoriTanah
                             * data[7]->KodSyaratNyata
                             * data[8]->KodKegunaanTanah
                             * data[9]->KodSekatanKepentingan
                             * data[10]->pegangan
                             * data[11]->tempohPajakan/tempohpegangan
                             * data[12]->luas terlibat
                             * data[13]->luas UOM
                             * data[14]->Cukai Sebenar
                             */
                            kbpm = hp.getBandarPekanMukimBaru() != null ? String.valueOf(hp.getBandarPekanMukimBaru().getKod()) : null;
                            ksek = hp.getKodSeksyen() != null ? String.valueOf(hp.getKodSeksyen().getKod()) : null;
                            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                                khakmilik = hp.getKodHakmilikSementara() != null ? hp.getKodHakmilikSementara().getKod() : hp.getKodHakmilik().getKod();
                            } else {
                                khakmilik = hp.getKodHakmilik() != null ? hp.getKodHakmilik().getKod() : null;
                            }
                            klot = hp.getLot() != null ? hp.getLot().getKod() : null;
                            kktanah = hp.getKategoriTanahBaru() != null ? hp.getKategoriTanahBaru().getKod() : null;
                            ksyarat = hp.getSyaratNyataBaru() != null ? hp.getSyaratNyataBaru().getKod() : null;
                            kguna = hp.getKodKegunaanTanah() != null ? hp.getKodKegunaanTanah().getKod() : null;
                            ksekatan = hp.getSekatanKepentinganBaru() != null ? hp.getSekatanKepentinganBaru().getKod() : null;
                            tP = hp.getTempohPajakan() != null ? String.valueOf(hp.getTempohPajakan()) : null;
                            luas = hp.getLuasTerlibat() != null ? hp.getLuasTerlibat().toString() : null;
                            kuom = hp.getKodUnitLuas() != null ? hp.getKodUnitLuas().getKod() : null;
                            cukai = hp.getCukaiBaru() != null ? hp.getCukaiBaru().toString() : null;
                            hp = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, hp, hp.getHakmilik(), new String[]{kbpm, ksek, khakmilik, klot, hp.getNoLot(), hp.getLokasi(), kktanah, ksyarat, kguna, ksekatan, hp.getPegangan(), tP, luas, kuom, cukai}, disLaporanTanahService);
                        }

                        LOG.info(" hp.getBandarPekanMukimBaru().getKod() : " + hp.getBandarPekanMukimBaru().getKod());

                        Hakmilik hm = new Hakmilik();
                        hm = disHakmilikPermohonan.copyPropertiesMohonHMToHakmilik(permohonan, hp, hm, new String[]{}, disLaporanTanahService);
                        if (permohonan.getCawangan() != null && permohonan.getCawangan().getKod().equals("08")) { //For gemas
                            hm.setDaerah(kodDaerahDAO.findById("06"));
                        } else {
                            hm.setDaerah(permohonan.getCawangan().getDaerah());
                        }
                        hm.setNoFail(permohonan.getIdPermohonan());
                        if (hp.getHakmilik() != null) {
                            hm.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                            if (ku.getKod().equals("HKGHS")) {
                                hm.setTarikhDaftarAsal(hp.getHakmilik().getTarikhDaftar());
                            }
                        }
                        FasaPermohonan fasaPermohonan = new FasaPermohonan();
                        if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                            fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "kemasukan");
                            if (fasaPermohonan.getKeputusan().getKod().equals("YQ")) {
                                hm.setKodHakmilik(kodHakmilikDAO.findById("HMM"));
                            } else if (fasaPermohonan.getKeputusan().getKod().equals("YT")) {
                                hm.setKodHakmilik(kodHakmilikDAO.findById("GMM"));
                            }
                            if (hp.getHakmilik() != null) {
                                hm.setNoLitho(hp.getHakmilik().getNoLitho() != null ? hp.getHakmilik().getNoLitho() : null);
                                //If FT No Pelan
                                hm.setNoPelan(hp.getHakmilik().getNoPelan() != null ? hp.getHakmilik().getNoPelan() : null);
                                //If QT No PU
                                hm.setNoPu(hp.getHakmilik().getNoPu() != null ? hp.getHakmilik().getNoPu() : null);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("BMBT")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik()}, 1);
                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                            if (hp.getHakmilik() != null) {
                                hm.setNoPelan(hp.getHakmilik().getNoPelan() != null ? hp.getHakmilik().getNoPelan() : null);
                                hm.setKodHakmilik(hp.getKodHakmilik());
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                            PermohonanUkur mohonUkur = new PermohonanUkur();
                            PelanGIS pelanGIS = new PelanGIS();
                            pelanGIS = disLaporanTanahService.getPelupusanService().findPelanB1GISByIdPermohonan(permohonan.getIdPermohonan());
                            mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);
                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                            if (mohonLaporPelan != null) {
                                hm.setNoLitho(mohonLaporPelan.getNoLitho() != null ? mohonLaporPelan.getNoLitho() : null);
                            }
                            if (mohonUkur != null) {
                                hm.setNoPu(mohonUkur.getNoPermohonanUkur());
                            }
                            if (ku.getKod().equals("HKGHS") || ku.getKod().equals("HSBM")) {
                                if (pelanGIS != null) {
                                    hm.setNoPelan(pelanGIS.getNoPelanAkui());
                                }
                            }
                        }
                        String idPermohonan = new String();
                        if (StringUtils.isNotBlank(permohonan.getCatatan()) && permohonan.getCatatan().equals("K")) {
                            idPermohonan = idP;
                        } else {
                            idPermohonan = permohonan.getIdPermohonan();
//                            permohonan = p;
                        }

                        mhservice.janaHakmilikBaruNew(hm, ia, p, pengguna, idPermohonan, idMohonHakmilik, 1, cawangan);
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
            return false;
        }
        return true;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
}
