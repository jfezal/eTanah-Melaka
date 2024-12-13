package etanah.view.kaunter;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.dao.HakmilikDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.HakmilikUrusan;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.Pengguna;
import etanah.model.PermohonanPembetulanHakmilik;
import etanah.service.common.HakmilikService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.RegService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;

import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;

import java.text.SimpleDateFormat;
import java.util.Date;

@HttpCache(allow = false)
@UrlBinding("/daftar/check_kaveat")
public class RequestCheckKaveatHakmilik extends AbleActionBean {

    public String idHakmilik;
    @Inject
    private HakmilikUrusanService hakmilikUrusanService;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    private HakmilikService hakmilikService;
    @Inject
    private RegService regService;
    @Inject
    private HakmilikDAO hakmilikDAO;
    private static Logger LOG = Logger.getLogger(RequestCheckKaveatHakmilik.class);
    private static boolean debug = LOG.isDebugEnabled();

    public Resolution doCheckKaveat() {
        if (debug) {
            LOG.debug("*****RequestCheckKaveatHakmilik.doCheckKaveat:hakmilik :" + idHakmilik);
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

        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);

        if (hakmilik.getIdHakmilikInduk() == null) {
            List<HakmilikUrusan> listKaveat = hakmilikUrusanService.findUrusanKaveat(idHakmilik);
            if (listKaveat.size() > 0) {
                results = "1";
            }
        } else if (hakmilik.getIdHakmilikInduk() != null) {
            List<HakmilikUrusan> listKaveat = hakmilikUrusanService.findUrusanKaveat(hakmilik.getIdHakmilikInduk());
            if (listKaveat.size() > 0) {
                List<HakmilikUrusan> listKaveat3 = hakmilikUrusanService.findUrusanKaveat(hakmilik.getIdHakmilik());
                if (listKaveat3.size() > 0) {
                    results = "4";
                } else if (listKaveat3.size() == 0) {
                    results = "2";
                }
            } else if (listKaveat.size() == 0) {
                List<HakmilikUrusan> listKaveat2 = hakmilikUrusanService.findUrusanKaveat(idHakmilik);
                if (listKaveat2.size() > 0) {
                    results = "3";
                }
            }
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckPajakan() {
        if (debug) {
            LOG.debug("*****RequestCheckKaveatHakmilik.doCheckPajakan:hakmilik :" + idHakmilik);
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
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String CurrentDate = simpleDateFormat.format(new Date());

        Hakmilik hakmilikPajakan = regService.findHakmilikPajakanByIdHAkmilik(idHakmilik);
        //PermohonanPembetulanHakmilik mohonHakmilikBetul = pembetulanService.findIdHakmilik(idHakmilik);
        if (hakmilikPajakan != null) {
            HakmilikUrusan hakmilikPSPL = pembetulanService.findHakmilikUrusanPSPL(idHakmilik);
            if (hakmilikPSPL != null) {
                PermohonanPembetulanHakmilik mohonHakmilikBetul = pembetulanService.findUrusanPSPL(hakmilikPSPL.getIdPerserahan(), hakmilikPSPL.getHakmilik().getIdHakmilik());
                int bakiPajakan;
                LOG.info("TARIKH LUPUT LAMA = " + mohonHakmilikBetul.getTarikhLuput());
                LOG.info("TARIKH LUPUT BARU = " + mohonHakmilikBetul.getTarikhLuputSemasa());
                LOG.info("URUSAN = " + mohonHakmilikBetul.getPermohonan().getKodUrusan().getKod());
                if (mohonHakmilikBetul.getPermohonan().getKodUrusan().getKod().equals("PSPL")) {
                    Date tarikhPajakan = mohonHakmilikBetul.getTarikhLuputSemasa();
                    String pajakan = String.valueOf(tarikhPajakan);
                    bakiPajakan = pajakan.compareTo(CurrentDate);
                    LOG.info("PAJAKAN = " + String.valueOf(tarikhPajakan));
                    LOG.info("CURRENT DATE = " + CurrentDate);
                    LOG.info("BAKI PAJAKAN = " + bakiPajakan);
                    if (bakiPajakan < 0) {
                        results = "1";
                    } else if (bakiPajakan > 0) {
                        results = simpleDateFormat.format(tarikhPajakan);
                    }
                    LOG.info("RESULT = " + results);
                    LOG.debug("*****RequestCheckKaveatHakmilik.doCheckPajakan:hakmilikPSPLnotNULL*****");

                } else {
                    //if (hakmilikPajakan != null) {
                        Date tarikhPajakan = hakmilikPajakan.getTarikhLuput();
                        String pajakan = String.valueOf(tarikhPajakan);
                        bakiPajakan = pajakan.compareTo(CurrentDate);
                        LOG.info("PAJAKAN = " + String.valueOf(tarikhPajakan));
                        LOG.info("CURRENT DATE = " + CurrentDate);
                        if (bakiPajakan < 0) {
                            results = "1";
                        } else if (bakiPajakan > 0) {
                           results = simpleDateFormat.format(tarikhPajakan);
                        }
                    //}
                }
            }else {
                    //if (hakmilikPajakan != null) {
                        int bakiPajakan;
                        Date tarikhPajakan = hakmilikPajakan.getTarikhLuput();
                        String pajakan = String.valueOf(tarikhPajakan);
                        bakiPajakan = pajakan.compareTo(CurrentDate);
                        LOG.info("PAJAKAN = " + String.valueOf(tarikhPajakan));
                        LOG.info("CURRENT DATE = " + CurrentDate);
                        if (bakiPajakan < 0) {
                            results = "1";
                        } else if (bakiPajakan > 0) {
                           results = simpleDateFormat.format(tarikhPajakan);
                        }
                    //}
                }
        }

        // check if there is kaveat
        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckGadaian() {
        if (debug) {
            LOG.debug("*****RequestCheckKaveatHakmilik.doCheckGadaian:hakmilik :" + idHakmilik);
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

        // check if there is kaveat
        List<HakmilikUrusan> listKaveat = hakmilikUrusanService.findUrusanGadaian(idHakmilik);
        if (listKaveat.size() > 0) {
            results = "1";
        }

        return new StreamingResolution("text/plain", results);
    }
}
