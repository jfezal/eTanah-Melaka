
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
public class InitiateIntegrationPendaftaranValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow1 generateIdPerserahanWorkflow1;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    private static final Logger LOG = Logger.getLogger(InitiateIntegrationPendaftaranValidator.class);
    private String idHakmilik;
    private String stageId;
    private String keputusan;
    private PermohonanNota permohonanNota;
    private List<Pemohon> listPemohon = new ArrayList<Pemohon>();
    private List<Pemohon> listPemohonPihak = new ArrayList<Pemohon>();
    private List<PermohonanPihak> listPermohonanPihak = new ArrayList<PermohonanPihak>();
    private Boolean initialUrusan = Boolean.FALSE;
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private List<PermohonanPihak> permohonanPihakList = new ArrayList<PermohonanPihak>();
    private List<HakmilikPihakBerkepentingan> listPihakPemohon = new ArrayList<HakmilikPihakBerkepentingan>();
    private Boolean urusanToInitiate = Boolean.FALSE;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();

        Pengguna peng = (Pengguna) context.getPengguna();

        stageId = context.getStageName();

        LOG.info("--------------stage id------------- : " + stageId);

        //checking list of pemohon
        List<Pemohon> senaraiPemohon = enforceService.getSenaraiPemohonByIdPermohonan(permohonan.getIdPermohonan());


        permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);

        try {
            if (permohonanNota != null) {
                LOG.info("::: kandungan nota :" + permohonanNota.getNota());
                context.addMessage("Sila Masukkan Nota/Kertas Minit Untuk Permohonan : " + permohonan.getIdPermohonan());
                return null;
            }


            List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
            FasaPermohonan fp2 = new FasaPermohonan();

            for (FasaPermohonan fp : senaraiFasa) {
                if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                    if (fp.getKeputusan() != null) {
                        fp2 = fp;
                        keputusan = fp.getKeputusan().getKod();
                        LOG.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                    }
                }
            }

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                    if (stageId.equalsIgnoreCase("arah_endorsan")) {//maklum_aduan
                        if (senaraiPemohon.isEmpty()) {
                            LOG.info("::::::::::::: senarai pemohon empty");
                            context.addMessage("Sila masukkan maklumat di tab Pihak Berkepentingan : " + permohonan.getIdPermohonan());
                            return null;
                        } else {
                            LOG.info("::::::::::::: senarai pemohon not empty :: " + senaraiPemohon.size());
                            initialUrusan = true;
                            createPermohonanPendaftaran(permohonan, peng, initialUrusan, "NO");
                        }
                    } else if (stageId.equalsIgnoreCase("bantahan_1tahun")) {
                        if (keputusan.equalsIgnoreCase("XT")) { //XT = Tiada Bantahan
                            createPermohonanPendaftaran(permohonan, peng, initialUrusan, keputusan);
                        }
                    } else if (stageId.equalsIgnoreCase("keputusan_enkuiri_2ab2")) {
                        if (keputusan.equalsIgnoreCase("WJ") || keputusan.equalsIgnoreCase("TW")) { //WJ = Wujud & TW = Tidak Wujud
                            createPermohonanPendaftaran(permohonan, peng, initialUrusan, keputusan);
                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return null;
        }

        return proposedOutcome;
//        return null;
    }

    public void createPermohonanPendaftaran(Permohonan permohonan, Pengguna pengguna, Boolean initialUrusan, String keputusan) {
        System.out.println("initial urusan ::::::::::::" + initialUrusan);
        permohonanPihakList = enforcementService.findByIdPermohonan(permohonan.getIdPermohonan());
        listPemohon = enforcementService.findListPemohon(permohonan.getIdPermohonan());
        hakmilikPermohonanList = enforceService.findSenaraiMohonHakmilik(permohonan.getIdPermohonan());

        ArrayList kodUrusanTerlibat = new ArrayList<String>();

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
            if (keputusan.equalsIgnoreCase("WJ")) {
                kodUrusanTerlibat.add("TTWB");
            } else if (keputusan.equalsIgnoreCase("TW")) {
                kodUrusanTerlibat.add("TTWKP");
                kodUrusanTerlibat.add("TTWLM");
                kodUrusanTerlibat.add("TTWLB");
            } else {
                kodUrusanTerlibat.add("TTWB");
                kodUrusanTerlibat.add("TTWKP");
                kodUrusanTerlibat.add("TTWLM");
                kodUrusanTerlibat.add("TTWLB");
            }

        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
            if (keputusan.equalsIgnoreCase("WJ")) {
                kodUrusanTerlibat.add("TTB");
            } else if (keputusan.equalsIgnoreCase("TW")) {
                kodUrusanTerlibat.add("TTTK");
            } else {
                kodUrusanTerlibat.add("TTTK");
                kodUrusanTerlibat.add("TTB");
            }
        }

        ArrayList senaraiHakmilikPermohonan = new ArrayList<String>();

        if (!hakmilikPermohonanList.isEmpty()) {
            for (int j = 0; j < hakmilikPermohonanList.size(); j++) {
                HakmilikPermohonan hp = hakmilikPermohonanList.get(j);
                if (hp.getHakmilik() != null) {
                    senaraiHakmilikPermohonan.add(hp.getHakmilik().getIdHakmilik());
                }
            }

            int j = 1;

            for (Object kod : kodUrusanTerlibat) {
                String kodUrusanPendaftaran = kod.toString();
                LOG.info(" :::::::::::::::::::::::::::::::::::::::::::::::::::::START:::::::::::::::::::::::::::::::::::::::::::::::");
                LOG.info(j + ") :::::::::::::::::::::::::::: " + kodUrusanPendaftaran);
                HashSet hsKod = new HashSet();
                if (!senaraiHakmilikPermohonan.isEmpty()) {
                    //looping list of pemohon
                    for (Pemohon pm : listPemohon) {
                        if (StringUtils.isNotBlank(pm.getDalamanNilai1())) {
                            if (pm.getDalamanNilai1().equalsIgnoreCase(kodUrusanPendaftaran)) {
                                listPihakPemohon = enforcementService.findPihakTerlibat(senaraiHakmilikPermohonan, permohonan, "IN", pm.getDalamanNilai1());
                                for (HakmilikPihakBerkepentingan hpb : listPihakPemohon) {
                                    if (hpb.getHakmilik() != null) {
                                        urusanToInitiate = true;
                                        hsKod.add(hpb.getHakmilik().getIdHakmilik());
                                    }
                                }
                            }

                        }
                    }

                    //looping list of mohon pihak
                    for (PermohonanPihak pp : permohonanPihakList) {
                        if (StringUtils.isNotBlank(pp.getDalamanNilai1())) {
                            if (pp.getDalamanNilai1().equalsIgnoreCase(kodUrusanPendaftaran)) {
                                hsKod.add(pp.getHakmilik().getIdHakmilik());
                                urusanToInitiate = true;
                            }
                        }
                    }

                    KodUrusan kodUrusan = null;
                    Date now = new Date();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();

                    if (urusanToInitiate == true) {
                        listPemohonPihak = enforcementService.findListPemohon(permohonan.getIdPermohonan(), kodUrusanPendaftaran);
                        listPermohonanPihak = enforcementService.findByIdPermohonan(permohonan.getIdPermohonan(), kodUrusanPendaftaran);

                        if (initialUrusan == true) {
                            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                                LOG.info("::value TTW for " + kodUrusanPendaftaran + " ::");
                                kodUrusan = kodUrusanDAO.findById("TTW");
                            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                                kodUrusan = kodUrusanDAO.findById("TT");
                            }
                        } else {
                            kodUrusan = kodUrusanDAO.findById(kodUrusanPendaftaran);
                        }

                        Iterator it = hsKod.iterator();
                        while (it.hasNext()) {
                            String value = (String) it.next();
                            Hakmilik h = hakmilikDAO.findById(value);
                            senaraiHakmilik.add(h);
                            LOG.info("::value " + kodUrusanPendaftaran + " : " + value);
                        }
                        System.out.println("senarai hakmilik " + kodUrusanPendaftaran + " ::::::::::::::: " + senaraiHakmilik.size());
                        LOG.info(kodUrusan.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow1.generateIdPerserahan(kodUrusan, pengguna, senaraiHakmilik, permohonan, listPemohonPihak, listPermohonanPihak);
                    } else {
                        LOG.info("*********** TIADA URUSAN UNTUK " + kodUrusanPendaftaran + "***********");
                    }
                }
                j++;
                urusanToInitiate = false;
                LOG.info(" :::::::::::::::::::::::::::::::::::::::::::::::::::::FINISH:::::::::::::::::::::::::::::::::::::::::::::::");
            }

        }

    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        Permohonan permohonan = context.getPermohonan();

        stageId = context.getStageName();
        LOG.info("--------------stage id------------- : " + stageId);

        permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        if (permohonanNota == null) {
            LOG.info("::: kandungan nota tidak null ::: ");
            PermohonanNota nota = enforcementService.findNotEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
            if (nota != null) {
                LOG.info("::: update status nota to T = tidak aktif ::: ");
                nota.setStatusNota('T');
                enforceService.simpanNota(nota);
            }
        }
    }
}
