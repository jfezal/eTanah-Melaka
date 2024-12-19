/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.model.gis.PelanGIS;
import etanah.service.NotifikasiService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.initiateService.MohonHakmilikPelupusanService;
import etanah.service.StrataPtService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import etanah.service.common.HakmilikService;
import etanah.service.common.PermohonanRujukanLuarService;
import java.math.BigDecimal;
import org.apache.log4j.Logger;

/**
 *
 * @author 
 */
public class EndorsanValidator implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    etanah.Configuration conf;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    MohonHakmilikPelupusanService mhservice;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanRujukanLuarService mrlservice;
    @Inject
    HakmilikService hakmilikservice;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String keputusan;
    public ArrayList<Hakmilik> listHakmilikBaru = new ArrayList<Hakmilik>();
    private static final Logger LOG = Logger.getLogger(EndorsanValidator.class);

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

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
        String[] name = {"idHakmilik"};
        Object[] value = {idHakmilik};
        String[] name1 = {"permohonan"};
        Object[] value1 = {permohonan};
        List<HakmilikPermohonan> senaraiHakmilik1 = hakmilikPermohonanDAO.findByEqualCriterias(name1, value1, null);
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
        }

        FasaPermohonan mohonFasa = new FasaPermohonan();
        KodUrusan kod = new KodUrusan();
        Pengguna peng = (Pengguna) context.getPengguna();
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        String stageid = context.getStageName();


        if (permohonan.getKodUrusan().getKod().equals("BMBT")) {
            HakmilikPermohonan mohonHakmilik = strService.findIdbyIDMohon(permohonan.getIdPermohonan());
            if (mohonHakmilik.getCatatan().equals("SL")) {
                LOG.info("---Initiate KPESL---");
                kod = kodUrusanDAO.findById("KPESL");
            } else if (mohonHakmilik.getCatatan().equals("BL")) {
                LOG.info("---Initiate KPEBL---");
                kod = kodUrusanDAO.findById("KPEBL");
            }

            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "38Keputusan");
            if (mohonFasa.getKeputusan().getKod().equals("L")) {
                LOG.info("--kod.getNama()--:" + kod.getNama());
                LOG.info("--mohon.getFolderDokumen()--:" + permohonan.getFolderDokumen());
                Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow4(kod, peng, senaraiHakmilik1, permohonan, stageid);
                HakmilikPermohonan mohonHakmilik1 = strService.findIdbyIDMohon(mohonReg.getPermohonanSebelum().getIdPermohonan());

                LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                LOG.info("--Sending Idmohon to Reg/Saving in Mohon Rujuluar--:");
                PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                permohonanRujLuar.setCawangan(strService.findByKodCaw("00"));
                permohonanRujLuar.setCatatan(permohonan.getSebab());
                permohonanRujLuar.setPermohonan(mohonReg);
                permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                permohonanRujLuar.setNoRujukan("1");
                permohonanRujLuar.setNilai(mohonHakmilik1.getLuasTerlibat());
                permohonanRujLuar.setNilai2(mohonHakmilik1.getKedalamanTanah());
                permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String formattedDate = dateFormat.format(date);
                    LOG.info("--formattedDate--" + formattedDate);
                    permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                }
                KodRujukan kodRujukan;
                kodRujukan = kodRujukanDAO.findById("FL");
                permohonanRujLuar.setKodRujukan(kodRujukan);
                strService.SimpanMohonRujukLuar(permohonanRujLuar);
                LOG.info("--Saved in Mohon Rujuluar--:");

                Hakmilik hakmilikbr = strService.findInfoByIdHakmilik(mohonHakmilik1.getHakmilik().getIdHakmilik());
                if (hakmilikbr != null) {
                    hakmilikbr.setKedalamanTanah(mohonHakmilik.getKedalamanTanahDiluluskan());
                    hakmilikbr.setKedalamanTanahUOM(mohonHakmilik.getKedalamanTanahUOMDiLuluskan());
                    hakmilikDAO.saveOrUpdate(hakmilikbr);
                }

            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS")) {
            if (context.getStageName().equals("g_terima_pa_b1")) {
                LOG.info("---Initiate BMSTM---");
                kod = kodUrusanDAO.findById("BMSTM");

                LOG.info("--kod.getNama()--:" + kod.getNama());
                LOG.info("--mohon.getFolderDokumen()--:" + permohonan.getFolderDokumen());
                HakmilikPermohonan mohonHakmilik = strService.findIdbyIDMohon(permohonan.getIdPermohonan());

                PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                PermohonanUkur mohonUkur = new PermohonanUkur();
                PelanGIS pelanGIS = new PelanGIS();

                if (mohonHakmilik != null) {
                    mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{permohonan.getIdPermohonan()}, 0);

                    if (mohonUkur != null) {
                        mohonHakmilik.setKodHakmilik(mohonUkur.getKodHakmilik());
                        LOG.info("--kod hakmilik --:" + mohonUkur.getKodHakmilik());

                        if (mohonUkur.getKodHakmilik().getKod().equals("GRN")) {
                            mohonHakmilik.setPegangan("S");
                            mohonHakmilik.setKodHakmilik(kodHakmilikDAO.findById("GRN"));
                        } else {
                            mohonHakmilik.setPegangan("P");
                            mohonHakmilik.setKodHakmilik(kodHakmilikDAO.findById("PN"));
                        }
                        hakmilikPermohonanDAO.saveOrUpdate(mohonHakmilik);
                    }
                }

                Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow4(kod, peng, senaraiHakmilik1, permohonan, stageid);

                LOG.info("mohonReg.getPermohonanSebelum().getIdPermohonan()" + mohonReg.getPermohonanSebelum().getIdPermohonan());
                LOG.info("mohonHakmilikwww" + mohonHakmilik.getLuasTerlibat());
                LOG.info("mohonHakmilik.getLuasTerlibat()" + mohonHakmilik.getLuasTerlibat());
                LOG.info("mohonHakmilik.getKedalamanTanah()" + mohonHakmilik.getKedalamanTanah());
                LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                LOG.info("--Sending Idmohon to Reg/Saving in Mohon Rujuluar--:");
                PermohonanRujukanLuar permohonanRujLuar = strService.findNPermohonan(mohonReg.getIdPermohonan());
//                PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();

//                permohonanRujLuar.setCawangan(strService.findByKodCaw("00"));
                permohonanRujLuar.setCatatan(permohonan.getSebab());
//                permohonanRujLuar.setPermohonan(mohonReg);
//                permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                permohonanRujLuar.setNilai(mohonHakmilik.getLuasTerlibat());
                permohonanRujLuar.setNilai2(mohonHakmilik.getKedalamanTanah());
//                permohonanRujLuar.setNoRujukan("1");
//                permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String formattedDate = dateFormat.format(date);
                    LOG.info("--formattedDate--" + formattedDate);
                    permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                }
                KodRujukan kodRujukan;
                kodRujukan = kodRujukanDAO.findById("FL");
                permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                permohonanRujLuar.setKodRujukan(kodRujukan);
                strService.SimpanMohonRujukLuar(permohonanRujLuar);
                LOG.info("--Saved in Mohon Rujuluar--:");

                HakmilikPermohonan mohonIdHakmilik = strService.findIdbyIDMohon(permohonan.getIdPermohonan());
                if (mohonIdHakmilik != null) {

                    mohonIdHakmilik.setHakmilik(permohonanRujLuar.getHakmilik());
                    hakmilikPermohonanDAO.saveOrUpdate(mohonIdHakmilik);
                }

                HakmilikPermohonan mohonIdHakmilikMH = strService.findIdbyIDMohon(mohonReg.getIdPermohonan());
                if (mohonIdHakmilikMH != null) {

                    mohonIdHakmilikMH.setSekatanKepentingan(mohonIdHakmilik.getSekatanKepentingan());
                    mohonIdHakmilikMH.setSyaratNyata(mohonIdHakmilik.getSyaratNyata());
                    hakmilikPermohonanDAO.saveOrUpdate(mohonIdHakmilikMH);
                }

                Hakmilik hakmilikbr = strService.findInfoByIdHakmilik(permohonanRujLuar.getHakmilik().getIdHakmilik());
                PermohonanLaporanKawasan lprnKws = strService.findLprnKws(permohonan.getIdPermohonan());
                if (hakmilikbr != null) {
                    if (lprnKws != null) {
                        String nopelan = lprnKws.getNoPelanWarta();
                        hakmilikbr.setNoPelan(nopelan);
                        LOG.info("--nopelan" + nopelan);
                    }
                    hakmilikbr.setKedalamanTanah(mohonHakmilik.getKedalamanTanahDiluluskan());
                    hakmilikbr.setKedalamanTanahUOM(mohonHakmilik.getKedalamanTanahUOMDiLuluskan());
                    hakmilikDAO.saveOrUpdate(hakmilikbr);
                }
            }
        }
        return proposedOutcome;
//        return null;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
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
