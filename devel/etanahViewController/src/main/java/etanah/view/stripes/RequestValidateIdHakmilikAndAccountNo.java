/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.AkaunStrataDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Akaun;
import etanah.model.AkaunStrata;
import etanah.model.BilCukai;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Transaksi;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.Pengguna;
import etanah.model.PermohonanPembetulanHakmilik;
import etanah.service.RegService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
@UrlBinding("/hasil/check_idhakmilik_accountNo")
public class RequestValidateIdHakmilikAndAccountNo extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RequestValidateIdHakmilikAndAccountNo.class);
    private static final boolean debug = LOG.isDebugEnabled();
    SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    public String idHakmilik;
    public String account;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private AkaunDAO akaunDAO;
    @Inject
    private AkaunStrataDAO akaunStrataDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private RegService regService;
    @Inject
    private PembetulanService pembetulanService;

    public Resolution doCheckHakmilik() {
        LOG.info("*****AjaxValidateIdHakmilik.doCheckHakmilik:hakmilik :" + getContext().getRequest().getParameter("idHakmilik"));
        String results = "0";
        String type = getContext().getRequest().getParameter("type");
        System.out.println("type : " + type);
        if (type.equals("hakmilik")) {
            LOG.info("hakmilik");
            String hm = getContext().getRequest().getParameter("idHakmilik");
            Hakmilik h = hakmilikDAO.findById(hm.trim());
            if (h != null) {
//                Akaun ac = h.getAkaunCukai();
                results = "1";
//                System.out.println("ac.getHakmilik().getIdHakmilik() : "+ac.getHakmilik().getIdHakmilik());
//                results = ac.getNoAkaun();

                if (h.getKodStatusHakmilik().getKod().equals("T")) {
                    results = "3";
                }
                if (h.getKodStatusHakmilik().getKod().equals("R")) {
                    results = "5";
                }
                if (h.getKodStatusHakmilik().getKod().equals("B")) {
                    results = "6";
                }
                if (h.getKodStatusHakmilik().getKod().equals("P")) {
                    results = "7";
                }

                Session s = sessionProvider.get();
                Query q = s.createQuery("select u from etanah.model.DasarTuntutanNotis u where u.hakmilik.hakmilik.idHakmilik = :idHakmilik");
                q.setString("idHakmilik", hm.trim());
                List<DasarTuntutanNotis> trList = q.list();

                for (DasarTuntutanNotis dtn : trList) {
                    if (dtn.getNotis().getKod().equals("N8A")) {
                        results = "2";
                    }
                }
                LOG.info("999999");
                Query b = s.createQuery("select c from etanah.model.Transaksi c where c.akaunDebit.noAkaun = :akaunDebit AND c.kodTransaksi.kod ='72457'");
                b.setString("akaunDebit", h.getAkaunCukai().getNoAkaun());
                List<Transaksi> tbList = q.list();

                if (tbList.size() > 0) {
                    results = "8";
                }

                Query qry = s.createQuery("select a from etanah.model.Akaun a where a.hakmilik.idHakmilik = :idHakmilik AND a.kodAkaun.kod ='ACT'");
                qry.setString("idHakmilik", hm.trim());
                List<Akaun> akaunList = qry.list();
                if (akaunList.size() > 0) {
                    results = "4";
                }

                Query qry1 = s.createQuery("SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :idHakmilik AND a.ansuran ='Y'");
                qry1.setString("idHakmilik", hm.trim());
                List<Akaun> aList = qry1.list();
                if (aList.size() > 0) {
                    results = "9";
                }

                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String CurrentDate = simpleDateFormat.format(new Date());

                Hakmilik hakmilikPajakan = regService.findHakmilikPajakanByIdHAkmilik(h.getIdHakmilik());
                if (hakmilikPajakan != null) {
                    HakmilikUrusan hakmilikPSPL = pembetulanService.findHakmilikUrusanPSPL(hakmilikPajakan.getIdHakmilik());
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
                            LOG.info("BAKI PAJAKAN = " + bakiPajakan);
                            if (bakiPajakan < 0) {
                                results = "10";
                            } else if (bakiPajakan > 0) {
                                if (StringUtils.isBlank(results)) {
                                    results = "1";
                                }
                            }
                        } 
                    } else {
                            int bakiPajakan;
                            Date tarikhPajakan = hakmilikPajakan.getTarikhLuput();
                            String pajakan = String.valueOf(tarikhPajakan);
                            bakiPajakan = pajakan.compareTo(CurrentDate);
                            LOG.info("PAJAKAN = " + String.valueOf(tarikhPajakan));
                            LOG.info("CURRENT DATE = " + CurrentDate);
                            if (bakiPajakan < 0) {
                                results = "10";
                            } else if (bakiPajakan > 0) {
                               if (StringUtils.isBlank(results)) {
                                    results = "1";
                                }
                            }
                            LOG.info("RESULT = " + results);
                        }
                }
                // check if tax paid
//                if(ac != null){
//                List<Transaksi> senaraiTransaksi = ac.getSemuaTransaksi();
//                    for(Transaksi tr : senaraiTransaksi){
//                        if(tr.getKodTransaksi().getKod().equals("61018")){
//                            results = "2";
//                        }
//                    }
//                }

                if (results.equals("1")) {
                    Query qAkaun = s.createQuery("select a from etanah.model.Akaun a where a.hakmilik.idHakmilik = :idHakmilik and a.status.kod='A'");
                    qAkaun.setString("idHakmilik", h.getIdHakmilik());
                    Akaun a = (Akaun) qAkaun.uniqueResult();
                    results = a.getNoAkaun();
                }
            }
        }
        if (type.equals("akaun")) {
            LOG.info("akaun");
            AkaunStrata as = akaunStrataDAO.findById((getContext().getRequest().getParameter("idHakmilik")).trim());
            Akaun a = null;
            if (as != null) {
                a = as.getHakmilik().getAkaunCukai();
            } else {
                a = akaunDAO.findById((getContext().getRequest().getParameter("idHakmilik")).trim());
            }
            if (a != null) {
                String hm = a.getHakmilik().getIdHakmilik();
                results = "1";

                if (!a.getHakmilik().getKodStatusHakmilik().getKod().equals("D")) {
                    results = "3";
                }

                Session s = sessionProvider.get();
                Query q = s.createQuery("select u from etanah.model.DasarTuntutanNotis u where u.hakmilik.hakmilik.idHakmilik = :idHakmilik");
                q.setString("idHakmilik", hm);
                List<DasarTuntutanNotis> trList = q.list();

                for (DasarTuntutanNotis dtn : trList) {
                    if (dtn.getNotis().getKod().equals("N8A")) {
                        results = "2";
                    }
                }
                LOG.info("999999" + hm);
                Query b = s.createQuery("select c from etanah.model.Transaksi c where c.akaunDebit.noAkaun = :akaunDebit AND c.kodTransaksi.kod ='72457'");
                b.setString("akaunDebit", a.getNoAkaun());
                List<Transaksi> tbList = b.list();

                if (tbList.size() > 0) {
                    results = "8";
                }
                
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String CurrentDate = simpleDateFormat.format(new Date());

                Hakmilik hakmilikPajakan = regService.findHakmilikPajakanByIdHAkmilik(a.getHakmilik().getIdHakmilik());
                if (hakmilikPajakan != null) {
                    HakmilikUrusan hakmilikPSPL = pembetulanService.findHakmilikUrusanPSPL(hakmilikPajakan.getIdHakmilik());
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
                            LOG.info("BAKI PAJAKAN = " + bakiPajakan);
                            if (bakiPajakan < 0) {
                                results = "10";
                            } else if (bakiPajakan > 0) {
                                results = "1";
                            }
                        }
                    }
                }

                /*String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String CurrentDate = simpleDateFormat.format(new Date());

                Hakmilik hakmilikPajakan = regService.findHakmilikPajakanByIdHAkmilik(a.getHakmilik().getIdHakmilik());
                int bakiPajakan;
                if (hakmilikPajakan != null) {

                    Date tarikhPajakan = hakmilikPajakan.getTarikhLuput();
//            Date CurrentDate = new Date(pattern);
                    String pajakan = String.valueOf(tarikhPajakan);
                    bakiPajakan = pajakan.compareTo(CurrentDate);
//            bakiPajakan = CurrentDate.compareTo(String.valueOf(tarikhPajakan));
                    if (bakiPajakan < 0) {
                        results = "10";
                    } else if (bakiPajakan > 0) {
                        results = pajakan;
                    }
                }*/
            }
        }
        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckHakmilik1() {
        LOG.info("*****AjaxValidateIdHakmilik.doCheckHakmilik:hakmilik :" + getContext().getRequest().getParameter("idHakmilik"));
        String results = "0";
        Hakmilik h = hakmilikDAO.findById((getContext().getRequest().getParameter("idHakmilik")).trim());
        if (h != null) {
            Akaun ac = h.getAkaunCukai();
            results = "1";
            results = ac.getNoAkaun();

            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.DasarTuntutanNotis u where u.hakmilik.hakmilik.idHakmilik = :idHakmilik");
            q.setString("idHakmilik", h.getIdHakmilik());
            List<DasarTuntutanNotis> trList = q.list();

            if (!h.getKodStatusHakmilik().getKod().equals("D")) {
                results = "3";
            }

            // check if tax paid
            if (ac != null) {
//            List<Transaksi> senaraiTransaksi = ac.getSemuaTransaksi();
//                for(Transaksi tr : senaraiTransaksi){
                results = ac.getNoAkaun();
//                }
            }

            for (DasarTuntutanNotis dtn : trList) {
                if (dtn.getNotis().getKod().equals("N8A")) {
                    results = "4";
                }
            }
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckAccount() {
        LOG.info("*****AjaxValidateIdHakmilik.doCheckAccount:Akaun :" + getContext().getRequest().getParameter("idHakmilik"));
        String results = "0";
        String type = getContext().getRequest().getParameter("type");
        if (type.equals("akaun")) {
            LOG.info("akaun");
            Akaun a = akaunDAO.findById((getContext().getRequest().getParameter("idHakmilik")).trim());
            if (a != null) {
                if (a.getStatus().getKod().equals("A")) {
                    results = "1";
                } else {
                    results = "2";
                }
            }
        }
        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckAkaun() {
        LOG.info("*****AjaxValidateIdHakmilik.doCheckAkaun:Akaun :" + getContext().getRequest().getParameter("account"));
        String results = "0";
        Akaun h = new Akaun();
        h = akaunDAO.findById((getContext().getRequest().getParameter("account")).trim());

        if (h != null) {
            LOG.debug("h.getNoAkaun() : " + h.getNoAkaun());
            Hakmilik hm = h.getHakmilik();
            results = "1";

            if (!hm.getKodStatusHakmilik().getKod().equals("D")) {
                results = "3";
            }

            if (results.equals("1")) {
                results = h.getHakmilik().getIdHakmilik();
            }
        }
        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    public Resolution checkingData() {
        String results = "0";
        String kodCaw = getContext().getRequest().getParameter("kodCaw");
        String tahun = getContext().getRequest().getParameter("tahun");
        String jenisCukai = getContext().getRequest().getParameter("jenisCukai");
        LOG.info("kodCaw : " + kodCaw);
        LOG.info("tahun : " + tahun);
        LOG.info("jenisCukai : " + jenisCukai);
        Date now = new Date();
//        int year = Integer.parseInt(sdfYear.format(now)) + 1;
//        String tahun = Integer.toString(year);

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT u FROM etanah.model.BilCukai u WHERE u.kodCawangan.kod = :kodCaw AND u.untukTahun = :tahun AND u.jenisCukai = :jenisCukai");
        q.setString("kodCaw", kodCaw);
        q.setString("tahun", tahun);
        q.setString("jenisCukai", jenisCukai);
        List<BilCukai> senaraiBil = q.list();
        if (senaraiBil.size() > 0) {
            results = "1";
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution checkingDaerah() {
        String results = "0";
        Hakmilik hm = hakmilikDAO.findById((getContext().getRequest().getParameter("idHakmilik")).trim());
        results = hm.getDaerah().getKod();

        return new StreamingResolution("text/plain", results);
    }

    public Resolution getBaki() {
        String results = "0";
        Akaun h = akaunDAO.findById((getContext().getRequest().getParameter("account")).trim());
        if (h != null) {
            results = "1";
            results = h.getBaki().toString();
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution getBaki1() {
        String results = "0";
        Hakmilik hm = hakmilikDAO.findById((getContext().getRequest().getParameter("account")).trim());
        if (hm != null) {
            Session session = sessionProvider.get();
            String query = "SELECT ak FROM etanah.model.Akaun ak WHERE ak.hakmilik.idHakmilik = :idHakmilik AND ak.kodAkaun.kod = 'AC' and ak.status.kod='A'";
            Query q = session.createQuery(query);
            q.setString("idHakmilik", hm.getIdHakmilik());

            Akaun h = (Akaun) q.uniqueResult();
            if (h != null) {
                results = "1";
                results = h.getBaki().toString();
            }
        }

        return new StreamingResolution("text/plain", results);
    }

    /**
     * add by mansur 15May2011 *
     */
    public Resolution doCheckHakmilikKumpulan() {
        LOG.info("*****AjaxValidateIdHakmilik.doCheckHakmilikAll.idHakmilik :" + getContext().getRequest().getParameter("idHakmilik"));
        String results = "0";
        Hakmilik h = hakmilikDAO.findById((getContext().getRequest().getParameter("idHakmilik")).trim());
        if (h != null) {
            Akaun ac = h.getAkaunCukai();
            results = "1";
            results = ac.getNoAkaun();

            if (!h.getKodStatusHakmilik().getKod().equals("D")) {
                results = "3";
            }
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckAkaunKumpulan() {
        LOG.info("*****AjaxValidateIdHakmilik.doCheckAkaunKumpulan:Akaun :" + getContext().getRequest().getParameter("account"));
        String results = "0";
        Akaun h = new Akaun();
        h = akaunDAO.findById((getContext().getRequest().getParameter("account")).trim());

        if (h != null) {
            LOG.debug("h.getNoAkaun() : " + h.getNoAkaun());
            if (h.getHakmilik() != null) {
                Hakmilik hm = h.getHakmilik();
                results = "1";
                results = hm.getIdHakmilik();

                if (!hm.getKodStatusHakmilik().getKod().equals("D")) {
                    results = "3";
                }
            }
        }
        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckIDPermohonan() {
        LOG.info("*****AjaxValidateIdHakmilik.doCheckIDPermohonan:permohonan :" + getContext().getRequest().getParameter("idPermohonan"));
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        String results = "0";
        List<Permohonan> senaraiRayuan = new ArrayList<Permohonan>();
        List<Permohonan> checkingPPJP = new ArrayList<Permohonan>();
        List<Permohonan> senaraiRayuan2 = new ArrayList<Permohonan>();
        // checking idPermohonan except DEV / STRATA
        if (idMohon != null && !(idMohon.contains("DEV") || idMohon.contains("STR"))) {
            if (idMohon.contains("DIS")) {
                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
                Pengguna pengguna = ctx.getUser();
                if (pengguna != null) {
                    KodCawangan caw = pengguna.getKodCawangan();
                    KodDaerah daerah = caw.getDaerah();

                    etanah.model.Permohonan p = permohonanDAO.findById(idMohon);
                    if (p != null) {

                        List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
                        for (HakmilikPermohonan hp : senaraiHakmilik) {
                            if (hp != null && hp.getHakmilik() != null) {
                                KodHakmilik kh = hp.getKodHakmilik();
                                if ((kh.getMilikDaerah() == 'Y'
                                        && caw.getKod().equals("00"))
                                        || (kh.getMilikDaerah() == 'T'
                                        && !caw.getKod().equals("00"))) {
                                    return new StreamingResolution("text/plain", "2");
                                }
                            }
                        }
                    }
                }
            }

            //Added by nur.aqilah
            //Checking for urusan
            Session s = sessionProvider.get();
            Query q = s.createQuery("SELECT p FROM etanah.model.Permohonan p WHERE p.kodUrusan.kod='PPJP' and p.idPermohonan =:idMohon");
            q.setString("idMohon", idMohon);
            checkingPPJP = q.list();

            LOG.info("checkingPPJP " + checkingPPJP.size());

            //PPJP
            if (checkingPPJP.size() > 0) {
                LOG.info("Urusan PPJP");
                Session m = sessionProvider.get();
                Query n = m.createQuery("SELECT p FROM etanah.model.Permohonan p WHERE p.kodUrusan.kod in ('PPJP', 'PPTL', 'PPBL') and p.permohonanSebelum.idPermohonan =:idMohon");
                n.setString("idMohon", idMohon);
                senaraiRayuan2 = n.list();

                //Other Urusan
            } else {
                LOG.info("Lain-Lain Urusan");
                Session p = sessionProvider.get();
                Query r = p.createQuery("SELECT u FROM etanah.model.Permohonan u WHERE u.permohonanSebelum.idPermohonan = :idMohon");
                r.setString("idMohon", idMohon);
                senaraiRayuan = r.list();

            }
        }

        //PPJP
        if (checkingPPJP.size() > 0 && senaraiRayuan2.size() > 0) {
            LOG.info("PPJP");
            LOG.info("Permohonan is not null");
            results = "3";
            //Other Urusan
        } else if (checkingPPJP.size() < 0 && senaraiRayuan.size() > 0) {
            LOG.info("Lain-Lain Urusan");
            LOG.info("Permohonan is not null");
            results = "1";
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckHakmilikSiri() {
        String idHakmilikDari = getContext().getRequest().getParameter("idHakmilikDari");
        String idHakmilikKe = getContext().getRequest().getParameter("idHakmilikKe");
        String kodSerah = getContext().getRequest().getParameter("kodSerah");
        String kodUrusan = (String) getContext().getRequest().getParameter("kodUrusan");

        System.out.println("*****RequestValidateIdHakmilik.doCheckHakmilik:hakmilik :"
                + idHakmilikDari + "-" + idHakmilikKe);
        String results = "0";

        if (idHakmilikDari == null || idHakmilikDari.trim().length() == 0
                || idHakmilikKe == null || idHakmilikKe.trim().length() == 0) {
            LOG.warn("invalid idHakmilik bersiri given");
            return new StreamingResolution("text/plain", results);
        }

        // from
        StringBuilder from = new StringBuilder();
        for (int i = idHakmilikDari.length() - 1; i >= 0; i--) {
            char c = idHakmilikDari.charAt(i);
            if (c >= '0' && c <= '9') {
                from.insert(0, c);
            } else {
                break;
            }
        }
        long lFrom = Long.parseLong(from.toString());
        if (debug) {
            LOG.debug("lFrom=" + lFrom);
        }
        String pre = idHakmilikDari.substring(0, idHakmilikDari.length() - from.length());

        // to
        long lTo = 0l;
        try {
            lTo = Long.parseLong(idHakmilikKe.substring(pre.length(), idHakmilikDari.length()));
        } catch (NumberFormatException e) {
            results = "X";
        }
        if (debug) {
            LOG.debug("lTo=" + lTo);
        }

        // validate the series
        if ("X".equals(results)
                || idHakmilikDari.length() != idHakmilikKe.length()
                || !idHakmilikDari.substring(0, pre.length()).equals(idHakmilikKe.substring(0, pre.length()))
                || lTo < lFrom) {
            results = "X";
        } else {

            ArrayList<String> listIdHakmilik = new ArrayList<String>();
            listIdHakmilik.add(idHakmilikDari);
            listIdHakmilik.add(idHakmilikKe);
            DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
            df.setGroupingUsed(false);
            df.setMinimumIntegerDigits(from.length());
            for (long l = lFrom + 1; l < lTo; l++) {
                String id = pre + df.format(l);
                listIdHakmilik.add(id);
                System.out.println(id + ",");
            }

            List<Hakmilik> list = sessionProvider.get().createQuery(
                    "SELECT h from etanah.model.Hakmilik h  "
                    + "WHERE h.idHakmilik IN (:listHakmilik) "
                    + "AND h.noVersiDhde = 0 "
                    + "AND h.kodStatusHakmilik.kod ='D'").setParameterList("listHakmilik", listIdHakmilik).list();

            System.out.println("list.size() : " + list.size());
            if (list.size() > 0) {
                results = "1";
                StringBuilder hmBatal = new StringBuilder();
                StringBuilder hmTukarGanti = new StringBuilder();
                // check if tax paid
                List<Hakmilik> listTukarganti = new ArrayList<Hakmilik>();
                List<Hakmilik> senaraiTolak = new ArrayList<Hakmilik>();
                for (Hakmilik h : list) {
                    LOG.debug("masuk sini");
                    idHakmilik = h.getIdHakmilik();
                    LOG.debug("check IDHAKMILIK : " + idHakmilik);

                    if (StringUtils.isNotBlank(kodSerah) && kodSerah.equals("MH")) {
                        StringBuilder message = new StringBuilder();
                        List<HakmilikPermohonan> lhp = regService.searchMohonHakmilik(idHakmilik);
                        /*check urusan dalam proses*/
                        if (lhp.size() > 0) {
                            for (HakmilikPermohonan hakmilikPermohonan : lhp) {
                                if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("HKTHK")
                                        || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("HSTHK")) {
                                    if (!hakmilikPermohonan.getPermohonan().getStatus().equals("TK")) {
                                        senaraiTolak.add(h);
                                    }
                                }
                            }
                        }
                    }
                }
                LOG.info("--------------------- senaraiTolak.size()" + senaraiTolak.size());
                List<Hakmilik> resultArrayList = new ArrayList<Hakmilik>(list);
                resultArrayList.removeAll(senaraiTolak);
                LOG.info("resultArrayList.size() : " + resultArrayList.size());

                for (Hakmilik hakmilik : resultArrayList) {
                    if (hmTukarGanti.length() > 0) {
                        hmTukarGanti.append(",");
                    }
                    hmTukarGanti.append(hakmilik.getIdHakmilik());
                }

                //check if there is id Hakmilik Batal
                if (resultArrayList.size() > 0) {
                    results = resultArrayList.size() + " " + hmTukarGanti;
                }
                if (resultArrayList.size() <= 0) {
                    results = "O";
                }
            }
        }
        LOG.info("result : " + results);
        return new StreamingResolution("text/plain", results);
    }

    /**
     * end add by mansur 15May2011 *
     */
    //for checking id permohonan requested by Wan Consent
//    public Resolution doCheckIDPermohonan() {
//        LOG.info("*****AjaxValidateIdHakmilik.doCheckIDPermohonan:permohonan :" + getContext().getRequest().getParameter("idPermohonan"));
//        String idMohon = getContext().getRequest().getParameter("idPermohonan");
//        String results = "0";
//        List<Permohonan> senaraiRayuan = new ArrayList<Permohonan>();
//        // checking idPermohonan except DEV / STRATA
//        if (idMohon != null && !(idMohon.contains("DEV") || idMohon.contains("STR"))) {
//            if (idMohon.contains("DIS")) {
//                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//                Pengguna pengguna = ctx.getUser();
//                if (pengguna != null) {
//                    KodCawangan caw = pengguna.getKodCawangan();
//                    KodDaerah daerah = caw.getDaerah();
//
//                    etanah.model.Permohonan p = permohonanDAO.findById(idMohon);
//                    if (p != null) {
//
//                        List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
//                        for (HakmilikPermohonan hp : senaraiHakmilik) {
//                            if (hp != null && hp.getHakmilik() != null) {
//                                KodHakmilik kh = hp.getKodHakmilik();
//                                if ((kh.getMilikDaerah() == 'Y'
//                                        && caw.getKod().equals("00"))
//                                        || (kh.getMilikDaerah() == 'T'
//                                        && !caw.getKod().equals("00"))) {
//                                    return new StreamingResolution("text/plain", "2");
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("SELECT u FROM etanah.model.Permohonan u WHERE u.permohonanSebelum.idPermohonan = :idMohon");
//            q.setString("idMohon", idMohon);
//            senaraiRayuan = q.list();
//        }
//        if (senaraiRayuan.size() > 0) {
//            LOG.info("Permohonan is not null");
//            results = "1";
//        }
//        return new StreamingResolution("text/plain", results);
//    }
}
