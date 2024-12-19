package etanah.view.strata;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.KandunganFolder;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Mohd Faidzal
 */
public class StrataReportStageName {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    CommonService comm;
    @Inject
    StrataPtService strService;
    private Permohonan permohonan = new Permohonan();
    private static final Logger LOGGER = Logger.getLogger(StrataReportStageName.class);

    public Dokumen urusanPBBS(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        if (stageId.equals("keputusan1")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        //added stage id to genarate report at semaklaporan stage 02-08-2012 @NS
        if (stageId.equals("keputusan")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("g_sediasuratmakluman")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SMPBJ");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        //added stage id to genarate report at semaklaporan stage 08-06-2012 @Melaka
        if (stageId.equals("semaklaporan")) {
            LOGGER.info("--stageId SemalLaporan--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }

        //added stage id to genarate report at g_sediakertasptg stage 25-06-2012 @Melaka
        if (stageId.equals("g_sediakertasptg")) {
            LOGGER.info("--stageId g_sediakertasptg--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }
        return doc;
    }

    public Dokumen urusanPBBD(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        if (stageId.equals("keputusan1")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("g_sediasuratmakluman")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SMPBJ");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        //added stage id to genarate report at semaklaporan stage 02-08-2012 @NS
        if (stageId.equals("keputusan")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        //added stage id to genarate report at semaklaporan stage 08-06-2012 @Melaka
        if (stageId.equals("semaklaporan")) {
            LOGGER.info("--stageId SemalLaporan--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }

        //added stage id to genarate report at g_sediakertasptg stage 25-06-2012 @Melaka
        if (stageId.equals("g_sediakertasptg")) {
            LOGGER.info("--stageId g_sediakertasptg--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }
        return doc;
    }

    public Dokumen urusanPBS(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        if (stageId.equals("keputusan1")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        //added stage id to genarate report at semaklaporan stage 02-08-2012 @NS
        if (stageId.equals("keputusan")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("g_sediasuratmakluman")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SMPBJ");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        //added stage id to genarate report at semaklaporan stage 08-06-2012 @Melaka
        if (stageId.equals("semaklaporan")) {
            LOGGER.info("--stageId SemalLaporan--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }

        //added stage id to genarate report at g_sediakertasptg stage 25-06-2012 @Melaka
        if (stageId.equals("g_sediakertasptg")) {
            LOGGER.info("--stageId g_sediakertasptg--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }
        return doc;
    }

    public Dokumen urusanPSBS(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        if (stageId.equals("keputusan1")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        //added stage id to genarate report at semaklaporan stage 02-08-2012 @NS
        if (stageId.equals("keputusan")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("g_sediasuratmakluman")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SMPBJ");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        //added stage id to genarate report at semaklaporan stage 08-06-2012 @Melaka
        if (stageId.equals("semaklaporan")) {
            LOGGER.info("--stageId SemalLaporan--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }

        //added stage id to genarate report at g_sediakertasptg stage 25-06-2012 @Melaka
        if (stageId.equals("g_sediakertasptg")) {
            LOGGER.info("--stageId g_sediakertasptg--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }
        return doc;
    }

    public Dokumen urusanPHPC(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        if (stageId.equals("keputusan1")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        //added stage id to genarate report at semaklaporan stage 02-08-2012 @NS
        if (stageId.equals("keputusan")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("g_sediasuratmakluman")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SMPBJ");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        //added stage id to genarate report at semaklaporan stage 08-06-2012 @Melaka
        if (stageId.equals("semaklaporan")) {
            LOGGER.info("--stageId SemalLaporan--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }

        //added stage id to genarate report at g_sediakertasptg stage 25-06-2012 @Melaka
        if (stageId.equals("g_sediakertasptg")) {
            LOGGER.info("--stageId g_sediakertasptg--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }
        return doc;
    }
    public Dokumen urusanPHPP(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        if (stageId.equals("keputusan1")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        //added stage id to genarate report at semaklaporan stage 02-08-2012 @NS
        if (stageId.equals("keputusan")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("g_sediasuratmakluman")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SMPBJ");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        //added stage id to genarate report at semaklaporan stage 08-06-2012 @Melaka
        if (stageId.equals("semaklaporan")) {
            LOGGER.info("--stageId SemalLaporan--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }

        //added stage id to genarate report at g_sediakertasptg stage 25-06-2012 @Melaka
        if (stageId.equals("g_sediakertasptg")) {
            LOGGER.info("--stageId g_sediakertasptg--Generating Report--");
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            LOGGER.info("--kandungan folder--"+listKF);
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
                LOGGER.info("--Find dok--"+doc);
            }
        }
        return doc;
    }

    public Dokumen urusanPPPP(Permohonan permohonan, String stageId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Dokumen urusanPKKR(Permohonan permohonan, String stageId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Dokumen urusanPPRUS(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
//        stageId = "sediasuratmakluman";
        if (stageId.equals("keputusan")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "KPTG");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("sediapermit")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "PRU");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("semaklaporan")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        if (stageId.equals("semakpermit")) {
            List<KandunganFolder> listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "PRU");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        if (stageId.equals("sediasuratmakluman")) {
            List<KandunganFolder> listKF = new ArrayList<KandunganFolder>();
            if (strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SKRU");
            } else {
                listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "STRU");
            }
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        return doc;
    }

    public Dokumen urusanPNB(Permohonan mohon, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + mohon.getIdPermohonan());
        Permohonan per = permohonanDAO.findById(mohon.getIdPermohonan());
        List<KandunganFolder> listKF = new ArrayList<KandunganFolder>();
        if (stageId.equals("keputusan") || stageId.equals("perakuan")) {
            LOGGER.info("Folder id : " + per.getFolderDokumen().getFolderId());
            listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "LTBHS");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("semaklaporan") || stageId.equals("perakuan")) {
            LOGGER.info("Folder id : " + per.getFolderDokumen().getFolderId());
            listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "LT");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        if (stageId.equals("sediasuratlulus")) {
            if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "SKBS");
            } else {
                listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "SPBS");
            }
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("sediasurattolak")) {
            if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "SKBS");
            } else {
                listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "SPBS");
            }
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        return doc;
    }

    Dokumen urusanPWPN(Permohonan mohon, String stageId) {
        Permohonan per = permohonanDAO.findById(mohon.getIdPermohonan());

        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        List<KandunganFolder> listKF = new ArrayList<KandunganFolder>();

        if (stageId.equals("sediawaran")) {
            listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "SLWRN");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            } else {
                listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "STWRN");
                if (listKF.size() > 0) {
                    doc = new Dokumen();
                    doc = listKF.get(0).getDokumen();
                }
            }
        }
        if (stageId.equals("perakuan")) {
            listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "KPWRN");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("keputusan")) {
            listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "KPWRN");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        return doc;
    }

    Dokumen urusanPNSS(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        List<KandunganFolder> listKF = new ArrayList<KandunganFolder>();
        if (stageId.equals("sediasurat") || stageId.equals("semaksurat")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SKPS");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("sediasuratmakluman") || stageId.equals("semaksuratmakluman")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SJWRN");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("semaklaporan")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LWRN");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        return doc;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    Dokumen urusanRMHS1(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        List<KandunganFolder> listKF = new ArrayList<KandunganFolder>();

        if (stageId.equals("keputusan")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SLMP");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            } else {
                listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "STMP");
                if (listKF.size() > 0) {
                    doc = new Dokumen();
                    doc = listKF.get(0).getDokumen();
                }

            }
        }
        return doc;
    }

    //added stage id to genarate report at keputusan stage 02-08-2012 @NS
    Dokumen urusanRMH1A(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        List<KandunganFolder> listKF = new ArrayList<KandunganFolder>();

        if (stageId.equals("keputusan")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SLMP");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            } else {
                listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "STMP");
                if (listKF.size() > 0) {
                    doc = new Dokumen();
                    doc = listKF.get(0).getDokumen();
                }

            }
        }
        return doc;
    }

    Dokumen urusanRTPS(Permohonan permohonan, String stageId) {

        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        List<KandunganFolder> listKF = new ArrayList<KandunganFolder>();
        if (stageId.equals("perakuan") || stageId.equals("keputusan")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "DRTPS");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("sediasurat")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SLKR");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            } else {
                listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "STKR");
                if (listKF.size() > 0) {
                    doc = new Dokumen();
                    doc = listKF.get(0).getDokumen();
                }

            }
        }
        if (stageId.equals("semaklaporan")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        return doc;
    }


    Dokumen urusanRTHS(Permohonan permohonan, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        List<KandunganFolder> listKF = new ArrayList<KandunganFolder>();
        if (stageId.equals("perakuan") || stageId.equals("keputusan")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "DRTPS");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("sediasurat")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "SLKR");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            } else {
                listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "STKR");
                if (listKF.size() > 0) {
                    doc = new Dokumen();
                    doc = listKF.get(0).getDokumen();
                }

            }
        }
        if (stageId.equals("semaklaporan")) {
            listKF = comm.kandunganFolderByKodDoc(permohonan.getFolderDokumen().getFolderId(), "LT");
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        return doc;
    }

    public Dokumen urusanP8(Permohonan mohon, String stageId) {
        Dokumen doc = null;
        LOGGER.info("PERMOHONAN : " + mohon.getIdPermohonan());
        Permohonan per = permohonanDAO.findById(mohon.getIdPermohonan());
        List<KandunganFolder> listKF = new ArrayList<KandunganFolder>();
       
        if (stageId.equals("semaklaporan")) {
            if (strService.findMohonFasa(mohon.getIdPermohonan(), "semaklaporan").getKeputusan().getKod().equals("AK")) {
                listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "SLPPS ");
            } else {
                listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "STPS ");
            }
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }
        if (stageId.equals("signsuratlantikan")) {
            if (strService.findMohonFasa(mohon.getIdPermohonan(), "signsuratlantikan").getKeputusan().getKod().equals("AK")) {
                listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "SLPPS ");
            } else {
                listKF = comm.kandunganFolderByKodDoc(per.getFolderDokumen().getFolderId(), "STPS ");
            }
            if (listKF.size() > 0) {
                doc = new Dokumen();
                doc = listKF.get(0).getDokumen();
            }
        }

        return doc;
    }

}
