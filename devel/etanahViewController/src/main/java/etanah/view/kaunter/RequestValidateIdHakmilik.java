package etanah.view.kaunter;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import etanah.dao.HakmilikDAO;
import etanah.model.Akaun;
import etanah.model.Notis; //yus add 17102018
import etanah.model.Hakmilik;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.Pengguna;
import etanah.model.PermohonanPembetulanHakmilik;
import etanah.service.common.HakmilikService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;

import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.*;
import etanah.service.RegService;
import etanah.service.common.*;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Responses:
 *
 * 0: Hakmilik tidak wujud 1: OK (Hakmilik Daftar dan Cukai telah dijelaskan) 2:
 * Cukai belum dijelaskan 3: Hakmilik Provisional 4: Hakmilik Batal. Response
 * format '4 <IdHakmilik Sambungan>' 5: Hakmilik Pajakan yg telah luput
 *
 * @author hishammk
 *
 */
@HttpCache(allow = false)
@UrlBinding("/daftar/check_idhakmilik")
public class RequestValidateIdHakmilik extends AbleActionBean {

    public String idHakmilik;
    private List<Map<String, String>> senaraiUrusanProses;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    private HakmilikService hakmilikService;
    @Inject
    private HakmilikUrusanService hakmilikUrusanService;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    private NotisService notisService;
    @Inject
    private RegService regService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PihakService pihakService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    LelongService lelong;
    @Inject
    LelongService lelongService;
    @Inject
    private HakmilikAsalPermohonanDAO hakmilikAsalPermohonanDAO;
    private Permohonan permohonan2;
    private Pihak pihak;
    private static Logger LOG = Logger.getLogger(RequestValidateIdHakmilik.class);
    private static boolean debug = LOG.isDebugEnabled();
    private static String[] LEPAS_HAKMILIK_BATAL = {
        "RPBNB",
        "PRPMB",
        "PMHHB",
        "BETHM",
        "N8AB"
    };
    private List<String> listHakmilik;
    private Permohonan permohonan;
    private Permohonan idPermohonan;

    public Resolution doCheckHakmilik() {
        if (debug) {
            LOG.debug("*****RequestValidateIdHakmilik.doCheckHakmilik123:hakmilik :" + idHakmilik);
        }
        String kodUrusan = (String) getContext().getRequest().getParameter("kodUrusan");
        LOG.info("kodUrusan : " + kodUrusan);
        KodUrusan kod = kodUrusanDAO.findById(kodUrusan);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        KodDaerah daerah = caw.getDaerah();
        String kodDaerah = null;
        if (daerah != null) {
            kodDaerah = daerah.getKod();
        }
//        if (pengguna.getKodCawangan().getKod().equals("00")) {
//            kodDaerah = null;
//        }
//        LOG.debug("kod caw pengguna :" +pengguna.getKodCawangan().getKod());
//        LOG.debug("kodDaerah : "+kodDaerah);
        String results = "0";
        Hakmilik h = new Hakmilik();

        if (kod.getJabatan().getKod().equals("20")) {
            h = hakmilikService.findHakmilikInDaerahStrata(idHakmilik);
        } else if ((kod.getKod().equals("PMMK1")) || (kod.getKod().equals("PMMK2")) || (kod.getKod().equals("PMJTL"))) {
            h = hakmilikDAO.findById(idHakmilik);
        } // utk user GEMAS ; simulasi 11-Nov-2014 START
        else if ((kodDaerah != null) && (kodDaerah.equals("08"))) {
            h = hakmilikService.findHakmilikInDaerahGemas(idHakmilik, kodDaerah);
        } // END
        else {
            h = hakmilikService.findHakmilikInDaerah(idHakmilik, kodDaerah);
        }
        if (kod.getKod().equals("HSC")) {
            h = hakmilikService.findById(idHakmilik);
        }
        if (kod.getKod().equals("HKC")) {
            h = hakmilikService.findById(idHakmilik);
        }
        String sk = "";

        if (h != null) {
            if (h.getSekatanKepentingan() != null) {
                sk = h.getSekatanKepentingan().getKod();
            }
            System.out.println("sk : ====" + sk);
            // check status Daftar
            String st = h.getKodStatusHakmilik().getKod();
            if ("D".equals(st) && (!(ArrayUtils.contains(LEPAS_HAKMILIK_BATAL, kod.getKod())) || kod.getKod().equals("BETHM"))) { // daftar //yus add 30052019 || kod.getKod().equals("BETHM")
                results = "1";
                // check if tax paid
                Akaun ac = h.getAkaunCukai();
                KodSekatanKepentingan sekatan = h.getSekatanKepentingan();

//                LOG.info("sekatan.getSekatan() : "+sekatan.getSekatan());
//                if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
//                    results = "2";
//                }i
                if (kod.getKodPerserahan().getKod().equals("MH")) {

                    if (!kodUrusan.equals("HKABS") && !kodUrusan.equals("HSABS") && !kodUrusan.equals("HKTHK") && !kodUrusan.equals("HSTHK") && !kodUrusan.equals("HKGHS") && !kodUrusan.equals("HTIR") && !kodUrusan.equals("HMSC")) {
                        if ((ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) && (h.getIdHakmilikInduk() == null)) {
                            results = "6";
                        } else {
                            results = "2";
                        }
                    }
                }

                if (sekatan != null && sekatan.getSekatan().length() > 15 && !kodUrusan.equals("HTIR")) {
                    if ((ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) && (h.getIdHakmilikInduk() == null)) { //block strata
                        if ((kod.getKodPerserahan().getKod().equals("B") || kod.getKodPerserahan().getKod().equals("N")) && (kod.getPerluJelasCukai() == 'T')) {
                            results = "2";
                        } else {
                            results = "6";
                        }
                    } else {
                        results = "5";
                    }
                } else {
                    if ((ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) && (h.getIdHakmilikInduk() == null)) {
                        results = "2";
                    } else {
                        results = "1";
                    }
                }

                if (kodUrusan.equals("HKTHK") || kodUrusan.equals("HSTHK") || kodUrusan.equals("HMSC")
                        || kodUrusan.equals("HTIR")) {
                    if (h.getNoVersiDhde() != 0) {
                        //NOTES: Urusan HKTHK,HSTHK,dan HMSC; hakmilik harus versi 0
                        LOG.info("---- versi adalah :" + h.getNoVersiDhde());
                        results = "11";
                    } else {
                        if (h.getIdHakmilikInduk() == null) {
                            boolean isTukarGantiExist = false;
                            // NOTES: Need Check if id hakmilik already has urusan HKTHK or HSTHK or HMSC
                            List<HakmilikPermohonan> listHp = hakmilikPermohonanService.findByIdHakmilik(idHakmilik);
                            List<HakmilikAsalPermohonan> listHap = hakmilikPermohonanService.findByIdSejHakmilik(idHakmilik);

                            if (!listHp.isEmpty() || !listHap.isEmpty()) {
                                //check dalam table mohon_hakmilik_asal
                                for (HakmilikAsalPermohonan lHap : listHap) {
                                    LOG.debug("masuk sini 1");
                                    List<HakmilikPermohonan> lHp = hakmilikPermohonanService.findByIdHakmilik(lHap.getHakmilik().getIdHakmilik());
                                    for (HakmilikPermohonan hp : lHp) {
                                        Permohonan mohon = permohonanService.findById(hp.getPermohonan().getIdPermohonan());
                                        if (mohon != null) {
                                            if (mohon.getKodUrusan().getKod().equalsIgnoreCase("HKTHK")
                                                    || mohon.getKodUrusan().getKod().equalsIgnoreCase("HSTHK")
                                                    || mohon.getKodUrusan().getKod().equalsIgnoreCase("HMSC")
                                                    || mohon.getKodUrusan().getKod().equalsIgnoreCase("HTIR")) {
                                                if ("TA".equals(mohon.getStatus()) || "AK".equals(mohon.getStatus())) {
                                                    LOG.info("> Terdapat urusan " + mohon.getKodUrusan().getKod() + " yang masih aktif");
                                                    results = "13 Hakmilik ini telah ada urusan " + mohon.getKodUrusan().getKod() + "-"
                                                            + mohon.getIdPermohonan() + ", sila selesaikan tukarganti sebelum buat kemasukan.";
                                                    isTukarGantiExist = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                //check dalam table mohon_hakmilik
                                for (HakmilikPermohonan lHp : listHp) {
                                    LOG.debug("masuk sini 2");
                                    Permohonan mohon = permohonanService.findById(lHp.getPermohonan().getIdPermohonan());
                                    if (mohon != null) {
                                        if (mohon.getKodUrusan().getKod().equalsIgnoreCase("HKTHK")
                                                || mohon.getKodUrusan().getKod().equalsIgnoreCase("HSTHK")
                                                || mohon.getKodUrusan().getKod().equalsIgnoreCase("HMSC")
                                                || mohon.getKodUrusan().getKod().equalsIgnoreCase("HTIR")) {
                                            if ( //                            !mohon.getStatus().equalsIgnoreCase("TK") // TIDAK AKTIF
                                                    //                            && !mohon.getStatus().equalsIgnoreCase("T") // TOLAK PERMOHONAN
                                                    //                            && !mohon.getStatus().equalsIgnoreCase("BP") // BATAL PERMOHONAN
                                                    //                            && !mohon.getStatus().equalsIgnoreCase("B") // BATAL PERMOHONAN
                                                    //                            && !mohon.getStatus().equalsIgnoreCase("W") // TARIK PERMOHONAN
                                                    //                            && !mohon.getStatus().equalsIgnoreCase("SL")
                                                    "TA".equals(mohon.getStatus()) || "AK".equals(mohon.getStatus())) {
                                                LOG.info("> Terdapat urusan " + mohon.getKodUrusan().getKod() + " yang masih aktif");
                                                results = "13 Hakmilik ini telah ada urusan " + mohon.getKodUrusan().getKod() + "-"
                                                        + mohon.getIdPermohonan() + ", sila selesaikan tukarganti sebelum buat kemasukan.";
                                                isTukarGantiExist = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            if (!isTukarGantiExist) {
                                KodHakmilik hm = h.getKodHakmilik();
                                if (kodUrusan.equals("HSTHK")) {

                                    if (kodDaerah == null) {
                                        if (!"HSD".equals(hm.getKod())) {
                                            results = "14 Sila pilih urusan yang betul. HSHTK untuk geran HSD.";
                                        }
                                    } else { //milik daerah
                                        if (!"HSM".equals(hm.getKod()) && !"HMM".equals(hm.getKod())) {
                                            results = "14 Sila pilih urusan yang betul. HSHTK untuk geran HSM.";
                                        }
                                    }
                                } else if (kodUrusan.equals("HKTHK")) {
                                    if (kodDaerah == null) {
                                        if (!"GRN".equals(hm.getKod()) && !"PN".equals(hm.getKod())) {
                                            results = "14 Sila pilih urusan yang betul. HKTHK untuk geran GRN dan PN.";
                                        }
                                    } else {//milik daerah
                                        if (!"PM".equals(hm.getKod()) && !"GM".equals(hm.getKod()) && !"GMM".equals(hm.getKod())) {
                                            results = "14 Sila pilih urusan yang betul. HKTHK untuk geran GM dan PM.";
                                        }
                                    }
                                } else {
//                                    if ("HSM".equals(hm.getKod()) || "PM".equals(hm.getKod()) || "GM".equals(hm.getKod())
//                                            || "HMM".equals(hm.getKod()) || "PN".equals(hm.getKod())
//                                            || "GMM".equals(hm.getKod())) {
//                                        results = "14 Sila pilih urusan yang betul. HMSC untuk geran selain HSM, GM dan PM.";
//                                    }
//                       else if (!("P").equals(h.getKodStatusHakmilik().getKod()) && !kodUrusan.equals("HTIR")) {
//                          results = "14 Hakmilik hendaklah berstatus P";
//                      }
                                }
                            }
                        } else {
                            results = "14 Tukarganti Hakmilik Strata Hanya Terdapat Diruangan Utiliti Pendaftaran.";
                        }
                    }
                } else {
                    //NOTES: selain urusan HKTHK,HSTHK,dan HMSC; hakmilik harus mempunyai versi 1 atau lebih
                    LOG.info("---- versi adalah :" + h.getNoVersiDhde());

                    if (h.getIdHakmilik().length() > 18 && kod.getJabatan().getKod().equals("16")) {
                        if (kodUrusan.equals("PBBM") || kodUrusan.equals("PBBL") || kodUrusan.equals("PBBT")) {
                            results = "14 Sila pilih urusan yang betul.";
                        }
                        if (kodUrusan.equals("PBCTM") || kodUrusan.equals("PBCTL") || kodUrusan.equals("PBCTB")
                                || kodUrusan.equals("PBCTB") || kodUrusan.equals("HTB") || kodUrusan.equals("HT")) {
                            List<HakmilikPermohonan> listHp = hakmilikPermohonanService.findByIdHakmilik(idHakmilik);
                            if (!listHp.isEmpty()) {
                                for (HakmilikPermohonan lHp : listHp) {
                                    Permohonan mohon = permohonanService.findById(lHp.getPermohonan().getIdPermohonan());
                                    if (mohon != null) {
                                        if (mohon.getKodUrusan().getKod().equals(kodUrusan)) {
                                            if ("TA".equals(mohon.getStatus()) || "AK".equals(mohon.getStatus())) {
                                                LOG.info("noting pendaftaran strata");
                                                results = "14 Hakmilik ini telah ada urusan " + mohon.getKodUrusan().getKod() + "-"
                                                        + mohon.getIdPermohonan() + " Yang Masih Aktif";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (kodUrusan.equals("PBBM") || kodUrusan.equals("PBBL") || kodUrusan.equals("PBBT")
                                || kodUrusan.equals("PBBB") || kodUrusan.equals("HTB") || kodUrusan.equals("HT")) {
                            List<HakmilikPermohonan> listHp = hakmilikPermohonanService.findByIdHakmilik(idHakmilik);
                            if (!listHp.isEmpty()) {
                                for (HakmilikPermohonan lHp : listHp) {
                                    Permohonan mohon = permohonanService.findById(lHp.getPermohonan().getIdPermohonan());
                                    if (mohon != null) {
                                        if (mohon.getKodUrusan().getKod().equals(kodUrusan)) {
                                            if ("TA".equals(mohon.getStatus()) || "AK".equals(mohon.getStatus())) {
                                                LOG.info("noting pendaftaran strata");
                                                results = "14 Hakmilik ini telah ada urusan " + mohon.getKodUrusan().getKod() + "-"
                                                        + mohon.getIdPermohonan() + " Yang Masih Aktif";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                if (kodUrusan.equals("PCT")) {
                            List<HakmilikPermohonan> listHp = hakmilikPermohonanService.findByIdHakmilik(idHakmilik);
                            if (!listHp.isEmpty()) {
                                for (HakmilikPermohonan lHp : listHp) {
                                    Permohonan mohon = permohonanService.findById(lHp.getPermohonan().getIdPermohonan());
                                    if (mohon != null) {
                                        if (mohon.getKodUrusan().getKod().equals(kodUrusan)) {
                                            if ("TA".equals(mohon.getStatus()) || "AK".equals(mohon.getStatus()) || "SS".equals(mohon.getStatus())) {
                                                LOG.info("URUSAN PCT");
                                                results = "14 Hakmilik ini telah ada urusan " + mohon.getKodUrusan().getKod() + " - "
                                                        + mohon.getIdPermohonan() + " Yang Masih Aktif";
                                            }
                                        }
                                    }
                                }
                            }
                        }

                if (kod.getKodPerserahan().getKod().equals("MH")) {
                    List<HakmilikAsalPermohonan> Hap = regService.findIdHakmilikAsalMohonByIdHakmilikSej(h.getIdHakmilik());
                    List<HakmilikSebelumPermohonan> lhsp = regService.searchMohonHakmilikSblmByIDHakmilikSej(h.getIdHakmilik());

                    if (!(Hap.isEmpty())) {
                        for (HakmilikAsalPermohonan HAslMhn : Hap) {
                            List<HakmilikPermohonan> hmhn = regService.senaraiHakmilikPermohonanByIDHakmilik(HAslMhn.getHakmilik().getIdHakmilik());

                            if (!(hmhn.isEmpty())) {
                                for (HakmilikPermohonan hmhn1 : hmhn) {
                                    Permohonan mohon = permohonanService.findById(hmhn1.getPermohonan().getIdPermohonan());
                                    if (mohon.getKodUrusan().getKod().equals("HMSC")) {
                                    } else {
                                        if (mohon != null) {
                                            if (mohon.getKodUrusan().getKod().equalsIgnoreCase(kod.getKod())) {
                                                if ("TA".equals(mohon.getStatus()) || "AK".equals(mohon.getStatus()) || "SS".equals(mohon.getStatus())) {
                                                    results = "26 Hakmilik ini telah ada urusan " + kod.getKod() + "-"
                                                            + mohon.getIdPermohonan() + " Yang Masih Aktif";
                                                    break;
                                                } else if ("SL".equals(mohon.getStatus())) {
                                                    if (mohon.getKeputusan().getKod().equalsIgnoreCase("T")) {
                                                    } else {
                                                        results = "26 Hakmilik ini telah ada urusan " + kod.getKod() + "-"
                                                                + mohon.getIdPermohonan() + " Yang Telah Didaftarkan" + mohon.getKodUrusan().getKod();
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (lhsp.size() > 0) {
                        for (HakmilikSebelumPermohonan sblm : lhsp) {
                            List<HakmilikPermohonan> hmhn = regService.senaraiHakmilikPermohonanByIDHakmilik(sblm.getHakmilik().getIdHakmilik());

                            if (!(hmhn.isEmpty())) {
                                for (HakmilikPermohonan hmhn1 : hmhn) {
                                    Permohonan mohon = permohonanService.findById(hmhn1.getPermohonan().getIdPermohonan());

                                    if (mohon != null) {
                                        if (mohon.getKodUrusan().getKod().equalsIgnoreCase(kod.getKod())) {
                                            if ("TA".equals(mohon.getStatus()) || "AK".equals(mohon.getStatus()) || "SS".equals(mohon.getStatus())) {
                                                results = "26 Hakmilik ini telah ada urusan " + kod.getKod() + "-"
                                                        + mohon.getIdPermohonan() + " Yang Masih Aktif";
                                                break;
                                            } else if ("SL".equals(mohon.getStatus())) {
                                                if (mohon.getKeputusan().getKod().equalsIgnoreCase("T")) {
                                                } else {
                                                    results = "26 Hakmilik ini telah ada urusan " + kod.getKod() + "-"
                                                            + mohon.getIdPermohonan() + " Yang Telah Didaftarkan";
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date today = new Date();
                String currentDate = sdf.format(new Date());
                /*if (h.getTarikhLuput() != null) {
                    if (today.compareTo(h.getTarikhLuput()) > 0) {
                        results = "17 Hakmilik ini telah Luput Pajakan pada " + sdf.format(h.getTarikhLuput());
                    }
                }*/
                Hakmilik hakmilikPajakan = regService.findHakmilikPajakanByIdHAkmilik(idHakmilik);
                if (hakmilikPajakan != null) {
                    HakmilikUrusan hakmilikPSPL = pembetulanService.findHakmilikUrusanPSPL(idHakmilik);
                    if (hakmilikPSPL != null) {
                        PermohonanPembetulanHakmilik mohonHakmilikBetul = pembetulanService.findUrusanPSPL(hakmilikPSPL.getIdPerserahan(), hakmilikPSPL.getHakmilik().getIdHakmilik());
                        if (mohonHakmilikBetul != null){
                            int bakiPajakan;
                            LOG.info("TARIKH LUPUT LAMA = " + mohonHakmilikBetul.getTarikhLuput());
                            LOG.info("TARIKH LUPUT BARU = " + mohonHakmilikBetul.getTarikhLuputSemasa());
                            LOG.info("URUSAN = " + mohonHakmilikBetul.getPermohonan().getKodUrusan().getKod());
                            if (mohonHakmilikBetul.getPermohonan().getKodUrusan().getKod().equals("PSPL") && h.getTarikhLuput() != null) {
                                Date tarikhPajakan = mohonHakmilikBetul.getTarikhLuputSemasa();
                                String pajakan = String.valueOf(tarikhPajakan);
                                bakiPajakan = pajakan.compareTo(currentDate);
                                LOG.info("PAJAKAN = " + String.valueOf(tarikhPajakan));
                                LOG.info("CURRENT DATE = " + currentDate);
                                LOG.info("BAKI PAJAKAN = " + bakiPajakan);
                                if (bakiPajakan < 0) {
                                    results = "17 Hakmilik ini telah Luput Pajakan pada " + sdf.format(h.getTarikhLuput());
                                } else if (bakiPajakan > 0) {
                                    if (kod.getKod().equals("BETUR") && mohonHakmilikBetul.getTarikhLuputSemasa() == null){
                                       if (StringUtils.isBlank(results)) {
                                            results = "1";
                                       } 
                                    } else {                                   
                                            results = "18 Pajakan Hakmilik akan tamat pada " + sdf.format(tarikhPajakan);
                                            if (StringUtils.isBlank(results)) {
                                                results = "1";
                                            }
                                    }  
                                }
                                LOG.info("RESULT = " + results);
                            } else {
                                results = "17 Hakmilik ini telah Luput Pajakan pada " + sdf.format(h.getTarikhLuput());
                            }
                        } else {
                            List<PermohonanPembetulanHakmilik> mhbBaru = pembetulanService.findIdPermohonanIdHakmilik(hakmilikPSPL.getIdPerserahan(), hakmilikPSPL.getHakmilik().getIdHakmilik());
                            HakmilikPermohonan idHp = hakmilikPermohonanService.checkByIdHakmilikIdMohon(hakmilikPSPL.getIdPerserahan(), hakmilikPSPL.getHakmilik().getIdHakmilik());
                            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                            InfoAudit info = peng.getInfoAudit();
                            info.setDimasukOleh(peng);
                            info.setTarikhMasuk(new java.util.Date());
                            if (mhbBaru.size() <= 0) {
                                //filter mohon_hakmilik_betul untuk hakmilik baru is null
                                PermohonanPembetulanHakmilik mhbNew = new PermohonanPembetulanHakmilik();
                                LOG.info("NEW mohon hakmilik betul");
                                mhbNew.setPermohonan(idHp.getPermohonan());
                                mhbNew.setHakmilik(idHp);
                                mhbNew.setIdHakmilik(hakmilikPSPL.getHakmilik().getIdHakmilik());
                                mhbNew.setCawangan(hakmilikPSPL.getCawangan());
                                mhbNew.setInfoAudit(info);
                                pembetulanService.simpanBetul(mhbNew);
                            }
                        }                        
                    } else {
                        //hakmilik ada pajakan tetapi tiada urusan PSPL
                        //results = "Pajakan Hakmilik akan tamat pada " + sdf.format(h.getTarikhLuput());
                        LOG.info("TARIKH LUPUT  = " + hakmilikPajakan.getTarikhLuput());
                        LOG.info("CURRENT DATE = " + currentDate);
                        if (hakmilikPajakan.getTarikhLuput() != null) {
                            int bakiPajakan;
                            Date tarikhPajakan = hakmilikPajakan.getTarikhLuput();
                            String pajakan = String.valueOf(tarikhPajakan);
                            bakiPajakan = pajakan.compareTo(currentDate);
                            LOG.info("PAJAKAN = " + String.valueOf(tarikhPajakan));
                            LOG.info("CURRENT DATE = " + currentDate);
                            LOG.info("BAKI PAJAKAN = " + bakiPajakan);
                            if (bakiPajakan < 0) {
                                results = "17 Hakmilik ini telah Luput Pajakan pada " + sdf.format(hakmilikPajakan.getTarikhLuput());
                            } else if (bakiPajakan > 0) {
                                results = "18 Pajakan Hakmilik ini akan tamat pada " + sdf.format(hakmilikPajakan.getTarikhLuput());
                                if (StringUtils.isBlank(results)) {
                                    results = "1";
                                }
                            }
                            LOG.info("RESULT = " + results);
                        }
                    }
                }

//            results = "12";
//            List<HakmilikPermohonan> listHp = hakmilikPermohonanService.findByIdHakmilik(idHakmilik);
//            if (!listHp.isEmpty()) {
//              for (HakmilikPermohonan lHp : listHp) {
//                Permohonan mohon = permohonanService.findById(lHp.getPermohonan().getIdPermohonan());
//                if (mohon != null) {
//                  if (mohon.getKodUrusan().getKod().equalsIgnoreCase("HKTHK")
//                          || mohon.getKodUrusan().getKod().equalsIgnoreCase("HSTHK")
//                          || mohon.getKodUrusan().getKod().equalsIgnoreCase("HMSC")) {
//                    if ("TA".equals(mohon.getStatus()) || "AK".equals(mohon.getStatus())
//                            ) {
//                      LOG.info("> Terdapat urusan " + mohon.getKodUrusan().getKod() + " yang masih aktif");
//                      results = "15 Hakmilik ini telah ada urusan " + mohon.getKodUrusan().getKod() + "-"
//                              + mohon.getIdPermohonan() + ", sila selesaikan tukarganti sebelum buat kemasukan.";
//                    }
//                  }
//                }
//              }
//            }
//                if ((sk.equals("070000000")) || (sk.equals("020310003")) || (sk.equals("020110007")) || (sk.equals("010000002")) || (sk.equals("020000002")) || (sk.equals("000110061"))
//                        || (sk.equals("030000003")) || (sk.equals("000110031")) || (sk.equals("010000007")) || (sk.equals("000000002")) || (sk.equals("050000003")) || (sk.equals("000000001"))
//                        || (sk.equals("020460001")) || (sk.equals("000410040")) || (sk.equals("030000000")) || (sk.equals("010000000")) || (sk.equals("020000000")) || (sk.equals("050400001"))
//                        || (sk.equals("050000000")) || (sk.equals("000000000")) || (sk.equals("050000001")) || (sk.equals("020000001")) || (sk.equals("030000001")) || (sk.equals("070000001"))
//                        || (sk.equals("010000001")) || (sk.equals("070110008")) || (sk.equals("030000004")) || (sk.equals("070100001")) || (sk.equals("079999999")) || (sk.equals("020000003"))
//                        || (sk.equals("009999999")) || (sk.equals("000099999")) || (sk.equals("029999999")) || (sk.equals("059999999")) || (sk.equals("010000004")) || (sk.equals("010040002"))
//                        || (sk.equals("019999999")) || (sk.equals("039999999")) || (sk.equals("010000010")) || (sk.equals("010000003")) || (sk.equals("000000010")) || (sk.equals("010410012"))
//                        || (sk.equals("050000006")) || (sk.equals("030000002")) || (sk.equals("010000012")) || (sk.equals("01")) || (sk.equals("02")) || (sk.equals("060000000")) || (sk.equals("0000000"))) {
//                    if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
//                        results = "2";
//                    } else {
//                        results = "1";
//                    }
//                } else {
//                    if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
//                        results = "6";
//                    } else {
//                        results = "5";
//                    }
//                }
            } else if (("P".equals(st) || "DE".equals(st)) //status utk hakmilik tukar ganti
                    && !(ArrayUtils.contains(LEPAS_HAKMILIK_BATAL, kod.getKod()))) { // provisional
                if (!(kod.getKod().equals("HKTKP") || kod.getKod().equals("HSTKP") || kod.getKod().equals("HMSC"))) {

                    Akaun ac = h.getAkaunCukai();
                    if (ac != null && ac.getBaki().compareTo(BigDecimal.ZERO) == 0) {
                        results = "1";
                    } else {
                        results = "6";
                    }

                } else {
                    results = "1";
                }

            } else if ("B".equals(st) && (ArrayUtils.contains(LEPAS_HAKMILIK_BATAL, kod.getKod()))) {
                results = "1";
            } else if ("B".equals(st)) { // batal
                // get Hakmilik sambungan
                StringBuilder hmSamb = new StringBuilder();
                List<Hakmilik> listH = hakmilikService.getHakmilikSambungan(idHakmilik);
                for (Hakmilik hm : listH) {
                    if (hakmilikService.checkHakmilikBatal(hm)) {
                        continue;
                    }

                    if (hmSamb.length() > 0) {
                        hmSamb.append(",");
                    }
                    hmSamb.append(hm.getIdHakmilik());
                }

                results = "4 " + hmSamb;
                LOG.debug(results);
            }

            if (kod.getJabatan().getKod().equals("16")) {
                if (!pengguna.getKodCawangan().getKod().equals(h.getCawangan().getKod())) {
                    if (!kod.getKod().equals("HSC")) {
                        if (!kod.getKod().equals("HKC")) {
                            results = "0";
                        }
                    }

                }
            }

            List<HakmilikUrusan> senaraiUrusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilikDistinctPermohonan(h.getIdHakmilik());
            if (senaraiUrusan.size() > 0) {
                for (HakmilikUrusan hu : senaraiUrusan) {
                    if (hu.getKodUrusan() == null) {
                        continue;
                    }
                    String ku = hu.getKodUrusan().getKod();
                    if ((ku.equals("PMHUN") || ku.equals("PMHUK") || ku.equals("PLT") || ku.equals("PLK") || ku.equals("PLS") || ku.equals("PMKMH"))
                            && ((ArrayUtils.contains(LEPAS_HAKMILIK_BATAL, kod.getKod())))) {
                        results = "7";
                    }
                }
            }

            /*
             * Added by Aizuddin for new nota module alert
             */
            List<String> listKod = new ArrayList();
            StringBuilder alert = new StringBuilder();

            listKod.clear();
            listKod.add("ADBSB");
            listKod.add("ADBSS");
            listKod.add("TT");
            listKod.add("TTW");

            List<HakmilikUrusan> urusAlert = hakmilikUrusanService.findByIdHakmilikKodUrusan(idHakmilik, listKod);
            if (!urusAlert.isEmpty()) {
                for (HakmilikUrusan hu : urusAlert) {
                    LOG.info(":::::::::::1st Loop:::::::::::");
                    HakmilikPermohonan hp2 = hakmilikPermohonanService.findHakmilikPermohonan(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
                    permohonan2 = hp2.getPermohonan();
                    LOG.info("ID Hakmilik:::::::::::" + idHakmilik);
                    LOG.info("ID Mohon:::::::::::" + permohonan2.getIdPermohonan());
                    LOG.info("Kod Status:::::::::::" + permohonan2.getStatus());
                    if (permohonan2.getStatus().equalsIgnoreCase("SL")) {
                        if (!permohonan2.getKeputusan().getKod().isEmpty()) {
                            LOG.info("Kod Keputusan:::::::::::" + permohonan2.getKeputusan().getKod());
                            if (permohonan2.getKeputusan().getKod().equalsIgnoreCase("D") && !(kod.getKod().equals("ADBSL"))) {
                                if (alert.length() > 0) {
                                    alert.append(",");
                                }
                                alert.append("Hakmilik " + idHakmilik + " mempunyai urusan " + permohonan2.getKodUrusan().getKod() + " " + permohonan2.getIdPermohonan() + " yang didaftarkan");
                                results = "" + alert;
                            }
                        }
                    }
                }
            }

            if ((kod.getKod().equals("TTWLB")) || (kod.getKod().equals("TTWKP")) || (kod.getKod().equals("TTWLM"))) {
                StringBuilder alert2 = new StringBuilder();
                LOG.info("ID Hakmilik:::::::::::" + idHakmilik);
                List<HakmilikPihakBerkepentingan> listPihak = hakmilikPihakKepentinganService.findPihakBerkepentinganByIdHakmilik(idHakmilik);
                for (HakmilikPihakBerkepentingan hpb : listPihak) {
                    pihak = hpb.getPihak();
                    LOG.info("Nama Pihak:::::::::::" + pihak.getNama());
                    LOG.info("Kod Bangsa:::::::::::" + pihak.getBangsa().getKod());
                    if ((kod.getKod().equals("TTWLB")) && !(pihak.getBangsa().getKod().equals("MEL"))) {
                        if (alert2.length() > 0) {
                            alert2.append(",");
                        }
                        alert2.append("Urusan ini hanya untuk pemilik yang beragama Islam sahaja. Sila pastikan pemilik beragama Islam");
//            results = "8 " + alert2;
                        results = "" + alert2;
                    }
                    if ((kod.getKod().equals("TTWKP")) || (kod.getKod().equals("TTWLM")) && (pihak.getBangsa().getKod().equals("MEL"))) {
                        if (alert2.length() > 0) {
                            alert2.append(",");
                        }
                        alert2.append("Urusan ini hanya untuk pemilik yang bukan beragama Islam sahaja");
                        results = "" + alert2;
                    }
                }
            }

            if (kod.getKod().equals("PPTL") || kod.getKod().equals("PPBL")) {

//                List<HakmilikPermohonan> listHP = permohonan.getSenaraiHakmilik();
//                for (HakmilikPermohonan hp : listHP) {
//                    listHakmilik.add(hp.getHakmilik().getIdHakmilik());
//                }
                List<HakmilikPermohonan> listIDMohon1 = lelong.findPermohonanByIdHakmilik(idHakmilik);
                if (listIDMohon1.isEmpty()) {
                    results = "20";
                } else if (listIDMohon1.isEmpty()) {

                    h = hakmilikDAO.findById(idHakmilik);
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    Lelongan lelong1 = lelong.findHakmilikPermohonanByidHakmilik(idHakmilik);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(lelong1.getTarikhLelong());

                    Calendar cal2 = Calendar.getInstance();
                    cal.add(Calendar.DATE, -7);
                    Date curr7 = cal2.getTime();
                    Date tarikhLellong = cal.getTime();
                    LOG.info(cal.compareTo(cal2));
                    LOG.info("tarikhLellong" + tarikhLellong + "curr7 " + curr7);
                    if (cal.compareTo(cal2) == 0 || cal.compareTo(cal2) == -1) {

                        results = "Permohonan kurang daripada 7 hari sebelum tarikh lelongan pada " + df.format(lelong1.getTarikhLelong() + ". Sila rujuk seksyen 264(2) KTN");
                    }

                }
            }
            List<Notis> senaraiNotis = notisService.getSenaraiNotis(kodUrusan);
            List<Notis> senaraiNotis2 = notisService.getSenaraiNotis(kodUrusan);

            if (kod.getKod().equals("KVSP")) {

                senaraiUrusan = hakmilikUrusanService.findUrusanNottingMH(idHakmilik, kodUrusan);

                if (!senaraiUrusan.isEmpty()) {
                    for (HakmilikUrusan hu : senaraiUrusan) {
                        senaraiNotis = notisService.getSenaraiNotis(hu.getIdPerserahan());
                        for (Notis nt : senaraiNotis) {
                            if (nt.getTarikhTamat() != null) {
                                Calendar cal = Calendar.getInstance();
                                Calendar cal2 = Calendar.getInstance();
                                cal2.setTime(nt.getTarikhHantar()); //tarikh hantar table notis
                                cal2.add(Calendar.MONTH, hu.getTempohBulan()); //tambah dari tempoh bulan table hakmilik_urusan
                                LOG.info("Tarikh tamat : " + cal2.getTime());
                                if (cal.compareTo(cal2) <= 0) {
//                                    results = "10";
                                    senaraiNotis2.add(nt);
//                                    break;
                                }
                            }
                        }
                        if (senaraiUrusan.size() == senaraiNotis2.size()) {
                            results = "10";
                            break;
                        }
                    }
                }

            }

            if (kodUrusan.equals("PPPP")) {
                if (h.getBadanPengurusan() != null) {
                    results = "16";
                }
            }
        }

        LOG.debug(results);

        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckHakmilikCarian() {
        if (debug) {
            LOG.debug("*****RequestValidateIdHakmilik.doCheckHakmilikCarian:hakmilik :" + idHakmilik);
        }

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        KodDaerah daerah = caw.getDaerah();
        String kodDaerah = null;
        String milikDaerah = "";
        if (daerah != null) {
            milikDaerah = "Y";
            kodDaerah = daerah.getKod();
        }
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            milikDaerah = "T";
            kodDaerah = null;
        }
//        LOG.debug("kod caw pengguna :" +pengguna.getKodCawangan().getKod());
//        LOG.debug("kodDaerah : "+kodDaerah);
        String results = "0";
        Hakmilik h = hakmilikService.findHakmilikInDaerah(idHakmilik, kodDaerah, milikDaerah);
        // utk user GEMAS ; simulasi 11-Nov-2014 START
        if ((kodDaerah != null) && (kodDaerah.equals("08"))) {
            h = hakmilikService.findHakmilikInDaerahGemas(idHakmilik, kodDaerah);
        }
        // END

        if (h != null) {
            if (h.getNoVersiDhke() != 0 && h.getNoVersiDhde() != 0) {
                // check status Daftar
                String st = h.getKodStatusHakmilik().getKod();
                if ("D".equals(st)) { // daftar
                    results = "1";

                    // check if tax paid
                    Akaun ac = h.getAkaunCukai();
                    if ((ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) && (h.getIdHakmilikInduk() == null)) {
                        results = "2";
                    }

                } else if ("P".equals(st) || "DE".equals(st)) { // provisional

                    results = "3";
                } else if ("T".equals(st)) { // provisional

                    results = "0";

                } else if ("B".equals(st)) { // batal
                    // get Hakmilik sambungan
                    StringBuilder hmSamb = new StringBuilder();
                    List<Hakmilik> listH = hakmilikService.getHakmilikSambungan(idHakmilik);
                    for (Hakmilik hm : listH) {
                        if (hakmilikService.checkHakmilikBatal(hm)) {
                            continue;
                        }

                        if (hmSamb.length() > 0) {
                            hmSamb.append(",");
                        }
                        hmSamb.append(hm.getIdHakmilik());
                    }

                    results = "4 " + hmSamb;
                    LOG.debug(results);
                }
            } else {
                results = "6";
            }
        }
        LOG.debug(results);

        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckHakmilikBatal() {
        if (debug) {
            LOG.debug("*****RequestValidateIdHakmilik.doCheckHakmilikBatal:hakmilik :" + idHakmilik);
        }
        String kodUrusan = (String) getContext().getRequest().getParameter("kodUrusan");
        LOG.info("kodUrusan : " + kodUrusan);
        //KodUrusan kod = kodUrusanDAO.findById(kodUrusan);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        KodDaerah daerah = caw.getDaerah();
        String kodDaerah = null;
        String milikDaerah = "";
        if (daerah != null) {
            milikDaerah = "Y";
            kodDaerah = daerah.getKod();
        }
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            milikDaerah = "T";
            kodDaerah = null;
        }

//        LOG.debug("kod caw pengguna :" +pengguna.getKodCawangan().getKod());
        LOG.debug("kodDaerah : " + kodDaerah);
        String results = "0";
        Hakmilik h = hakmilikService.findHakmilikInDaerah(idHakmilik, kodDaerah, milikDaerah);
        if (h != null) {

            // check status Daftar
            String st = h.getKodStatusHakmilik().getKod();
            if ("D".equals(st) && !(ArrayUtils.contains(LEPAS_HAKMILIK_BATAL, kodUrusan))) { // daftar
                results = "5";

            } else if (("P".equals(st) || "DE".equals(st)) && !(ArrayUtils.contains(LEPAS_HAKMILIK_BATAL, kodUrusan))) { // provisional

                results = "3";
            } else if ("B".equals(st) && (ArrayUtils.contains(LEPAS_HAKMILIK_BATAL, kodUrusan))) {
                LOG.info("---------check laaaaa---------");
                results = "1";
            } else if ("D".equals(st)) {
                results = "5";
            } else if ("B".equals(st)) {
                results = "2";
            } else {
                results = "2";
            }
        }
        LOG.debug(results);

        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckHakmilikCarianStrata() {
        if (debug) {
            LOG.debug("*****RequestValidateIdHakmilik.doCheckHakmilikCarianStrata:hakmilik :" + idHakmilik);
        }

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        KodDaerah daerah = caw.getDaerah();
        String kodDaerah = null;
        if (daerah != null) {
            kodDaerah = daerah.getKod();
        }

        String results = "0";
        Hakmilik h = hakmilikService.findHakmilikInDaerah(idHakmilik, kodDaerah);
        if (h != null) {

            String kodHakmilik = h.getKodHakmilik().getKod();

            if (!"PN".equals(kodHakmilik) && !"GRN".equals(kodHakmilik)) {
                return new StreamingResolution("text/plain", "0");
            }

            // check status Daftar
            String st = h.getKodStatusHakmilik().getKod();
            if ("D".equals(st)) { // daftar
                results = "1";

                // check if tax paid
//                Akaun ac = h.getAkaunCukai();
//                if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
//                    results = "2";
//                }
            } else if ("P".equals(st)) { // provisional

                results = "3";

            } else if ("B".equals(st)) { // batal
                // get Hakmilik sambungan
                StringBuilder hmSamb = new StringBuilder();
                List<Hakmilik> listH = hakmilikService.getHakmilikSambungan(idHakmilik);
                for (Hakmilik hm : listH) {
                    if (hakmilikService.checkHakmilikBatal(hm)) {
                        continue;
                    }

                    if (hmSamb.length() > 0) {
                        hmSamb.append(",");
                    }
                    hmSamb.append(hm.getIdHakmilik());
                }

                results = "4 " + hmSamb;
                LOG.debug(results);
            }

        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckHakmilikUtilitiStrata() {
        if (debug) {
            LOG.debug("*****RequestValidateIdHakmilik.doCheckHakmilikCarianStrata:hakmilik :" + idHakmilik);
        }

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        KodDaerah daerah = caw.getDaerah();
        String kodDaerah = null;
        if (daerah != null) {
            kodDaerah = daerah.getKod();
        }

        String results = "0";
        Hakmilik h = hakmilikService.findHakmilikInDaerahStrata(idHakmilik);
        if (h != null) {
            if (h.getNoVersiDhde() == null || h.getNoVersiDhde() == 0) {

                String kodHakmilik = h.getKodHakmilik().getKod();

//                if (!"PN".equals(kodHakmilik) && !"GRN".equals(kodHakmilik)) {
//                    return new StreamingResolution("text/plain", "0");
//                }
                // check status Daftar
                String st = h.getKodStatusHakmilik().getKod();
                if ("D".equals(st)) { // daftar
                    results = "1";

                    // check if tax paid
//                Akaun ac = h.getAkaunCukai();
//                if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
//                    results = "2";
//                }
                } else if ("P".equals(st)) { // provisional

                    results = "3";

                } else if ("B".equals(st)) { // batal
                    // get Hakmilik sambungan
                    StringBuilder hmSamb = new StringBuilder();
                    List<Hakmilik> listH = hakmilikService.getHakmilikSambungan(idHakmilik);
                    for (Hakmilik hm : listH) {
                        if (hakmilikService.checkHakmilikBatal(hm)) {
                            continue;
                        }

                        if (hmSamb.length() > 0) {
                            hmSamb.append(",");
                        }
                        hmSamb.append(hm.getIdHakmilik());
                    }

                    results = "4 " + hmSamb;
                    LOG.debug(results);
                }
            } else {
                results = "11";
            }
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution checkHakmilikSelainMH() {
        if (debug) {
            LOG.debug("*****RequestValidateIdHakmilik.checkHakmilikSelainMH:hakmilik :" + idHakmilik);
        }
        String kodUrusan = (String) getContext().getRequest().getParameter("kodUrusan");
        LOG.debug("checkNottingMH:kodUrusan: " + kodUrusan + " idHakmilik : " + idHakmilik);
        StringBuilder message = new StringBuilder();
        LOG.debug("doCheckHakmilikProsesSelainMH");
        List<HakmilikPermohonan> lhp = regService.searchMohonHakmilik(idHakmilik);
        List<HakmilikSebelumPermohonan> lhsp = regService.searchMohonHakmilikSblmByIDHakmilik(idHakmilik);
        List<HakmilikUrusan> lhu = hakmilikUrusanService.findUrusanNottingMH(idHakmilik, kodUrusan);
        if (lhp.size() > 0) {
            for (HakmilikPermohonan hakmilikPermohonan : lhp) {
                if (!hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PCT")) {
                    if (!hakmilikPermohonan.getPermohonan().getStatus().equals("SL") && !hakmilikPermohonan.getPermohonan().getStatus().equals("TK")) {
                        message.append("Hakmilik ini masih mempunyai urusan " + hakmilikPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikPermohonan.getPermohonan().getIdPermohonan() + " yang belum selesai\n");
                    }
                } else {
                    if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PCT")) {
                      if (!hakmilikPermohonan.getPermohonan().getStatus().equals("SL") && !hakmilikPermohonan.getPermohonan().getStatus().equals("TK")) {
                          message.append("Hakmilik ini masih mempunyai urusan " + hakmilikPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikPermohonan.getPermohonan().getIdPermohonan() + " yang belum selesai\n");
                      }
                  }  
                }
            }
        }
        if (lhsp.size() > 0) {
            for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : lhsp) {
                if (!hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod().equals("PCT")) {
                    if (!hakmilikSebelumPermohonan.getPermohonan().getStatus().equals("SL") && !hakmilikSebelumPermohonan.getPermohonan().getStatus().equals("TK")) {
                        //message.append("Hakmilik mempunyai urusan " + hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod() + ": idPerserahan : " + hakmilikSebelumPermohonan.getPermohonan().getIdPermohonan() + "\n");
                        message.append("Hakmilik ini masih mempunyai urusan " + hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikSebelumPermohonan.getPermohonan().getIdPermohonan() + " yang belum selesai\n");
                    }
                }
            }

        }
        /*
         * Added by Aizuddin for new nota module alert
         */
        if (lhp.size() > 0) {
            for (HakmilikPermohonan hakmilikPermohonan : lhp) {
                if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("ADBSB")
                        || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("ADBSS")
                        || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("TT")
                        || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("TTW")) {
                    LOG.info("==============ADA URUSAN AKU ENTER DRAGON!!!!!===================");
                    if (hakmilikPermohonan.getPermohonan().getStatus().equalsIgnoreCase("SL")) {
                        if (!hakmilikPermohonan.getPermohonan().getKeputusan().getKod().isEmpty()) {
                            if (hakmilikPermohonan.getPermohonan().getKeputusan().getKod().equalsIgnoreCase("D")) {
                                message.append("Hakmilik ini mempunyai urusan " + hakmilikPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikPermohonan.getPermohonan().getIdPermohonan() + " yang didaftarkan");
                            }
                        }
                    }
                }
            }
        }

        /*
         * HSPS,HKPS,HKPB,HSPB,HSC,HKC,HKABS,HKABT,HSSBB,HSSTB,HSSB,HKSB
         */
        if (kodUrusan.equals("HSPS") || kodUrusan.equals("HKPS") || kodUrusan.equals("HTSPS") || kodUrusan.equals("HKPB") || kodUrusan.equals("HSPB")
                || kodUrusan.equals("HSC") || kodUrusan.equals("HKC") || kodUrusan.equals("HKABS") || kodUrusan.equals("HKABT")
                || kodUrusan.equals("HSSBB") || kodUrusan.equals("HSSTB") || kodUrusan.equals("HSSB") || kodUrusan.equals("HKSB")) {
            if (lhu.size() < 1) {
                message.append("Hakmilik ini " + idHakmilik + " tidak mempunyai notting berkaitan untuk meneruskan urusan");
            }
        }

        return new StreamingResolution("text/html", new StringReader(message.toString()));
    }
}
