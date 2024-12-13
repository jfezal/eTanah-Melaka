/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodKeputusan;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.LanjutanTempoh;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author murali
 */
public class SemakKertasValidator implements StageListener {

    @Inject
    PermohonanDAO mohonDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodKeputusanDAO kodKpsnDAO;
    private PermohonanKertas mohonKertas = new PermohonanKertas();
    private List<PermohonanKertasKandungan> listMohonKertasKand = new ArrayList<PermohonanKertasKandungan>();
    private static final Logger LOG = Logger.getLogger(SemakKertasValidator.class);
    @Inject
    etanah.Configuration conf;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        String kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("--kodNegeri--" + kodNegeri);
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();

        if (kodNegeri.equals("04")) {
            if (mohon.getKodUrusan().getKod().equals("RTHS") || mohon.getKodUrusan().getKod().equals("RTPS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "semaklaporan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("DI")) {
                        mohonHakmilik = strService.findMohonHakmilik(mohon.getIdPermohonan());
                        LanjutanTempoh lanjutTempoh = new LanjutanTempoh();
                        if (mohonHakmilik != null) {
                            lanjutTempoh = strService.findMohonLanjutTempoh(mohonHakmilik.getId());
                            LOG.info("--------mohonHakmilik.getId()-------:" + mohonHakmilik.getId());
                            LOG.info("--------lanjutTempoh-------:" + lanjutTempoh);
                            if (lanjutTempoh.getTarikhAkhirDilulus() == null) {
                                LOG.info("--------lanjutTempoh-------:" + lanjutTempoh);
                                context.addMessage("Sila masukkan tarikh kelulusan dan klik Simpan. Kemudian, sila klik butang Jana Dokumen untuk menjana semula surat makluman");
                                return null;
                            }
                        }

                    }
                }
            }
            if (mohon.getKodUrusan().getKod().equals("RBHS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "semakkertasptg");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("DI")) {
                        mohonHakmilik = strService.findMohonHakmilik(mohon.getIdPermohonan());
                        LanjutanTempoh lanjutTempoh = new LanjutanTempoh();
                        if (mohonHakmilik != null) {
                            lanjutTempoh = strService.findMohonLanjutTempoh(mohonHakmilik.getId());
                            LOG.info("--------mohonHakmilik.getId()-------:" + mohonHakmilik.getId());
                            LOG.info("--------lanjutTempoh-------:" + lanjutTempoh);
                            if (lanjutTempoh.getTarikhAkhirDilulus() == null) {
                                LOG.info("--------lanjutTempoh-------:" + lanjutTempoh);
                                context.addMessage("Sila masukkan tarikh kelulusan dan klik Simpan. Kemudian, sila klik butang Jana Dokumen untuk menjana semula surat makluman");
                                return null;
                            }
                        }

                    }
                }
            }
        }


        if (mohon.getKodUrusan().getKod().equals("RTHS") || mohon.getKodUrusan().getKod().equals("RTPS") || mohon.getKodUrusan().getKod().equals("PNB")) {
            mohonFasa = strService.findFasaPermohonanByIdAliran(mohon.getIdPermohonan(), "semaklaporan");
            LOG.debug("--mohonFasa--" + mohonFasa.getIdAliran());
        }

        if (mohon.getKodUrusan().getKod().equals("RBHS")
                || mohon.getKodUrusan().getKod().equals("RMHS1")
                || mohon.getKodUrusan().getKod().equals("RMH1A")
                || mohon.getKodUrusan().getKod().equals("RMHS5")
                || mohon.getKodUrusan().getKod().equals("RMHS6")
                || mohon.getKodUrusan().getKod().equals("RMHS7")) {
            mohonFasa = strService.findFasaPermohonanByIdAliran(mohon.getIdPermohonan(), "semakkertasptg");
            LOG.debug("--mohonFasa--" + mohonFasa.getIdAliran());
        }

        if (mohonFasa != null) {
            KodKeputusan kodKpsn = new KodKeputusan();
            if (mohonFasa.getKeputusan() != null) {
                kodKpsn = kodKpsnDAO.findById(mohonFasa.getKeputusan().getKod());
                mohonFasa.setKeputusan(kodKpsn);
                System.out.println("-----kodKpsn-----" + kodKpsn);
            }
            String ulas = "";
            mohonKertas = strService.findKertas(mohon.getIdPermohonan());
            if (mohonKertas != null) {
                LOG.info("----------- mohonKertas != null -------------");
                listMohonKertasKand = strService.findByIdLapor(mohonKertas.getIdKertas(), 3);
                LOG.info("----------- listMohonKertasKand -------------" + listMohonKertasKand);

            }
            int subtajuk = 1;
            for (PermohonanKertasKandungan ulasan : listMohonKertasKand) {
                System.out.println("-----ulas------" + ulas);
                System.out.println("-----ulasan.getKandungan()------" + ulasan.getKandungan());
                System.out.println("-----ulasan.subtajuk------" + ulasan.getSubtajuk());
                ulas += subtajuk + "." + ulasan.getKandungan() + "&#13;&#10;" + "&#13;&#10;";
                subtajuk++;
            }
            System.out.println("-----ulas-----" + ulas);
            mohonFasa.setUlasan(ulas);
            System.out.println("-----tarikh semasa-----" + new java.util.Date());
            mohonFasa.setTarikhKeputusan(new java.util.Date());
            strService.saveFasaMohon(mohonFasa);
        }


        if (mohon.getKodUrusan().getKod().equals("PNB") || mohon.getKodUrusan().getKod().equals("RTHS")
                || mohon.getKodUrusan().getKod().equals("RTPS")) {

            try {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "semaklaporan");
                if (mohonFasa.getKeputusan() == null) {
                    context.addMessage(mohon.getIdPermohonan() + " - Sila masukkan syor Penolong Pengarah Strata/Penolong Pegawai Tadbir. ");
                    return null;
                } else {
                    mohonFasa.setTarikhKeputusan(new Date());
                }

            } catch (Exception e) {
                LOG.error(e.getMessage());
                return null;
            }
        }

        if (mohon.getKodUrusan().getKod().equals("RMH1A") || mohon.getKodUrusan().getKod().equals("RMHS1")
                || mohon.getKodUrusan().getKod().equals("RMHS5") || mohon.getKodUrusan().getKod().equals("RMHS6")
                || mohon.getKodUrusan().getKod().equals("RMHS7") || mohon.getKodUrusan().getKod().equals("RBHS")) {

            try {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "semakkertasptg");
                if (mohonFasa.getKeputusan() == null) {
                    context.addMessage(mohon.getIdPermohonan() + " - Sila masukkan syor Penolong Pengarah Strata/Penolong Pegawai Tadbir. ");
                    return null;
                }
            } catch (Exception e) {
                LOG.error(e.getMessage());
                return null;
            }
        }



        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
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
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        //return proposedOutcome;
        return "back";
    }


}
