/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanUrusan;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.UrusniagaService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.service.RegService;
import org.apache.log4j.Logger;
import etanah.model.Hakmilik;
import etanah.model.HakmilikUrusan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.CalcTax;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.util.DateUtil;
import etanah.view.daftar.constant.RegConstant;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/pajakan")
public class Pajakan extends AbleActionBean {

    private Permohonan permohonan;
    private Pengguna pengguna;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String[] kodOum;
    private String[] luas;
    private PermohonanUrusan permohonanUrusan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanRujukanLuar permohonanRujukanLuarSblm;
    private String idHakmilik;
    @Inject
    RegService regService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    KodUOMDAO kodUomDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    UrusniagaService uManager;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    //TODO : setting date format in locale?
    private String tkhMula;
    private String tkhLama;
    private String idPermohonanSblm;
    private String tkhTamat;
    private String noFail;
    Hakmilik hakmilik;
    private String kodUrusan;
    private Date trhMulaPlus;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(Pajakan.class);
//    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @DefaultHandler
    @HandlesEvent("showMaklumatPajakan")
    public Resolution showPajakanForm() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String kodUrusan = permohonan.getKodUrusan().getKod();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        } else {
            hakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
            idHakmilik = hakmilik.getIdHakmilik();
        }

        //Added by Aizuddin to find ID_Mohon di tick utk PJLT
        List<PermohonanHubungan> listUrusanSblm = permohonanService.getSenaraiHubungan(idPermohonan, idHakmilik);

        if (listUrusanSblm.size() > 0) {
            PermohonanHubungan ph = listUrusanSblm.get(0);
            idPermohonanSblm = ph.getPermohonanSasaran().getIdPermohonan();
            permohonanRujukanLuarSblm = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, idPermohonanSblm);

            if (permohonanRujukanLuarSblm != null) {
                if (permohonanRujukanLuarSblm.getTarikhTamat() != null) {
                    tkhLama = sdf.format(permohonanRujukanLuarSblm.getTarikhTamat());
                    Calendar c = Calendar.getInstance();
                    c.setTime(permohonanRujukanLuarSblm.getTarikhTamat());
                    c.add(Calendar.DATE, 1);
                    Date trhMulaPlus = c.getTime();
                    tkhMula = sdf.format(trhMulaPlus);
                } else {
                    addSimpleError("Tiada maklumat pajakan terdahulu!");
                }
            }
        }

        permohonanRujukanLuar = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, idPermohonan);

        if (permohonanRujukanLuar != null) {
            if (permohonanRujukanLuar.getTarikhKuatkuasa() != null) {
                if (permohonan.getIdPermohonan().equalsIgnoreCase("PJLT")) {
                    tkhMula = sdf.format(trhMulaPlus);
                } else {
                    tkhMula = sdf.format(permohonanRujukanLuar.getTarikhKuatkuasa());
                }
            }

            if (permohonanRujukanLuar.getTarikhTamat() != null) {
                tkhTamat = sdf.format(permohonanRujukanLuar.getTarikhTamat());
            }

            //Added by Aizuddin
            if (permohonanRujukanLuar.getNoFail() != null) {
                noFail = permohonanRujukanLuar.getNoFail();
            }

            if (kodUrusan.equals("IS") || kodUrusan.equals("ISBLS")) {

                int hari = permohonanRujukanLuar.getTempohHari() == null ? 1 : permohonanRujukanLuar.getTempohHari();
                int bln = permohonanRujukanLuar.getTempohBulan() == null ? 1 : permohonanRujukanLuar.getTempohBulan();
                int thn = permohonanRujukanLuar.getTempohTahun() == null ? 1 : permohonanRujukanLuar.getTempohTahun();

                if (hari == 0 && bln == 0 && thn == 0) {
                    getContext().getRequest().setAttribute("selamanya", "true");
                }
            }

            getContext().getRequest().setAttribute("hari", permohonanRujukanLuar.getTempohHari() == null ? 0 : permohonanRujukanLuar.getTempohHari());
            getContext().getRequest().setAttribute("bln", permohonanRujukanLuar.getTempohBulan() == null ? 0 : permohonanRujukanLuar.getTempohBulan());
            getContext().getRequest().setAttribute("thn", permohonanRujukanLuar.getTempohTahun() == null ? 0 : permohonanRujukanLuar.getTempohTahun());

        }

        return new JSP("daftar/maklumat_pajakan.jsp").addParameter("tab", "true");
    }

//    @DefaultHandler
//    public Resolution showMaklumatPajakan() {
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
//        hakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
//        if (permohonan != null) {
//            List<PermohonanUrusan> urs = permohonan.getSenaraiUrusan();
//            if (urs != null && urs.size() > 0) {
//                permohonanUrusan = urs.get(0);
//            }
//        }
//
//        if (permohonanUrusan != null) {
//            if (permohonanUrusan.getPerjanjianTarikhMula() != null) {
//                tkhMula = sdf.format(permohonanUrusan.getPerjanjianTarikhMula());
//            }
//            if (permohonanUrusan.getPerjanjianTarikhTamat() != null) {
//                tkhTamat = sdf.format(permohonanUrusan.getPerjanjianTarikhTamat());
//            }
//            int hari = permohonanUrusan.getPerjanjianTempohHari();
//            int bulan = permohonanUrusan.getPerjanjianTempohBulan();
//            int tahun = permohonanUrusan.getPerjanjianTempohTahun();
//            if (hari == 0 && bulan ==0 && tahun == 0 && StringUtils.isBlank(tkhTamat)) {
//                getContext().getRequest().setAttribute("selamanya", "true");
//            }
//        }
//
//        return new JSP("daftar/maklumat_pajakan.jsp").addParameter("tab", "true");
//    }
    public Resolution showMaklumatLuasPajakan() {

        if (permohonan.getKodUrusan().getKod().equals("PJT") || permohonan.getKodUrusan().getKod().equals("PJKT")) {
            List<HakmilikPermohonan> senarai = new ArrayList<HakmilikPermohonan>();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hp = hakmilikPermohonanList.get(i);
                if (hp == null) {
                    continue;
                }
                Hakmilik hm = hp.getHakmilik();
                if (hp.getKodUnitLuas() == null) {
                    hp.setKodUnitLuas(hm.getKodUnitLuas());
                }
                if (hp.getLuasTerlibat() == null) {
                    hp.setLuasTerlibat(hm.getLuas());
                }
                senarai.add(hp);
            }
            hakmilikPermohonanService.saveHakmilikPermohonan(senarai);

        }
        rehydrate();

        return new JSP("daftar/kemasukan_luas_pajakan.jsp").addParameter("tab", "true");
    }

    public Resolution checkTempoh() {
        String result = "0";
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String y = getContext().getRequest().getParameter("year") == null ? "0" : getContext().getRequest().getParameter("year");
        String m = getContext().getRequest().getParameter("month") == null ? "0" : getContext().getRequest().getParameter("month");
        String d = getContext().getRequest().getParameter("day") == null ? "0" : getContext().getRequest().getParameter("day");
        Calendar currentcal = Calendar.getInstance();
        currentcal.set(Integer.parseInt(y), Integer.parseInt(m), Integer.parseInt(d));

        if ((kodUrusan.equals(RegConstant.PAJAKAN_KECIL_SEBAHAGIAN_TANAH)) || (kodUrusan.equals(RegConstant.PAJAKAN_KECIL_SELURUH_TANAH))) {
            PermohonanHubungan mohonHbgn = null;
            List<PermohonanHubungan> senaraiHubungan
                    = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), idHakmilik);
            for (PermohonanHubungan hb : senaraiHubungan) {
                if (hb == null) {
                    continue;
                }
                mohonHbgn = hb;
                break;
            }
            if (mohonHbgn != null) {
                Permohonan p = mohonHbgn.getPermohonanSasaran();
                if (p != null) {
                    HakmilikUrusan hu = hakmilikUrusanService
                            .findByIdPerserahanIdHakmilik(p.getIdPermohonan(), idHakmilik);
                    if (hu != null) {
                        if (hu.getTempohTahun() == null) {
                            hu.setTempohTahun(0);
                        }
                        if (hu.getTempohBulan() == null) {
                            hu.setTempohBulan(0);
                        }
                        if (hu.getTempohHari() == null) {
                            hu.setTempohHari(0);
                        }
                        int year = hu.getTempohTahun();
                        int month = hu.getTempohBulan();
                        int day = hu.getTempohHari();
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month, day);
                        if (currentcal.after(cal)) {
                            result = "1";
                        }
                    }
                }
            }
        }

        return new StreamingResolution("text/plain", result);
    }

    public Resolution checkLuas() {
        String result = "0";
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String luas = getContext().getRequest().getParameter("luas") == null ? "0" : getContext().getRequest().getParameter("luas");
        String kodOumTukar = getContext().getRequest().getParameter("kodOum");

        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
            if (hm != null && StringUtils.isNotBlank(luas)) {

                luas = luas.replaceAll(",", "");
                BigDecimal b1 = hm.getLuas();
                BigDecimal b2 = BigDecimal.valueOf(Double.parseDouble(luas));
                String kodOumAsal = hm.getKodUnitLuas().getKod();

                if (kodUrusan.equals(RegConstant.PAJAKAN_KECIL_SEBAHAGIAN_TANAH)) {
                    PermohonanHubungan mohonHbgn = null;
                    List<PermohonanHubungan> senaraiHubungan
                            = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), idHakmilik);
                    for (PermohonanHubungan hb : senaraiHubungan) {
                        if (hb == null) {
                            continue;
                        }
                        mohonHbgn = hb;
                        break;
                    }
                    if (mohonHbgn != null) {
                        Permohonan p = mohonHbgn.getPermohonanSasaran();
                        if (p != null) {
                            HakmilikUrusan hu = hakmilikUrusanService
                                    .findByIdPerserahanIdHakmilik(p.getIdPermohonan(), idHakmilik);
                            if (hu != null) {
                                b1 = hu.getLuasTerlibat();
                                kodOumAsal = hu.getKodUnitLuas().getKod();
                            }
                        }
                    }
                }
                if (!kodOumAsal.equalsIgnoreCase(kodOumTukar)) {
                    b2 = calcTax.kiraUnitLuas(kodOumAsal, kodOumTukar, b2);
                }

                LOG.debug("b1 = " + b1.toPlainString());
                LOG.debug("b2 = " + b2.toPlainString());
                int re = b2.compareTo(b1);
                result = String.valueOf(re);
            }
        }

        return new StreamingResolution("text/plain", result);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
//        permohonan = ((etanahActionBeanContext) getContext()).getPermohonan();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        kodUrusan = permohonan.getKodUrusan().getKod();
        hakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (permohonan != null) {
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            if (hakmilikPermohonanList != null) {
                kodOum = new String[hakmilikPermohonanList.size()];
                luas = new String[hakmilikPermohonanList.size()];
                List<HakmilikPermohonan> listTemp = new ArrayList<HakmilikPermohonan>();
                for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                    HakmilikPermohonan hp = hakmilikPermohonanList.get(i);
                    if (hp == null || hp.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik hm = hp.getHakmilik();
                    if (hp.getKodUnitLuas() != null) {
                        kodOum[i] = hp.getKodUnitLuas().getKod();
                        LOG.debug("kod UOM = " + kodOum[i]);
                    } else {
                        kodOum[i] = hm.getKodUnitLuas().getKod();
                        hp.setKodUnitLuas(hm.getKodUnitLuas());
                    }
                    if (hp.getLuasTerlibat() != null) {
//                        luas[i] = String.valueOf(hp.getLuasTerlibat());
                        luas[i] = etanah.util.StringUtils.formatLuas(String.valueOf(hp.getLuasTerlibat()));
                    } else {
                        if (permohonan.getKodUrusan().getKod().equals("PJT")
                                || permohonan.getKodUrusan().getKod().equals("PJKT")) {
                            luas[i] = String.valueOf(hm.getLuas());
                            hp.setLuasTerlibat(hm.getLuas());
                        }
                    }
                    listTemp.add(hp);
                }
                if (!listTemp.isEmpty()) {
                    hakmilikPermohonanService.saveHakmilikPermohonan(listTemp);
                }
            }
        }
    }

    public Resolution savePajakan() {

        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();

        LOG.debug("savePajakan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        } else {
            idHakmilik = hakmilik.getIdHakmilik();
        }

        InfoAudit ia = null;

        try {

            String tahun = getContext().getRequest().getParameter("tahun");
            String bulan = getContext().getRequest().getParameter("bulan");
            String hari = getContext().getRequest().getParameter("hari");

//            ia = permohonanRujukanLuar.getInfoAudit();
            if (permohonanRujukanLuar != null) {
                ia = permohonanRujukanLuar.getInfoAudit();
                if (ia == null) {
                    ia = new InfoAudit();
                }
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
            }

            if (StringUtils.isNotBlank(hari)) {
                permohonanRujukanLuar.setTempohHari(Integer.parseInt(hari));
            } else {
                permohonanRujukanLuar.setTempohHari(0);
            }
            if (StringUtils.isNotBlank(bulan)) {
                permohonanRujukanLuar.setTempohBulan(Integer.parseInt(bulan));
            } else {
                permohonanRujukanLuar.setTempohBulan(0);
            }
            if (StringUtils.isNotBlank(tahun)) {
                permohonanRujukanLuar.setTempohTahun(Integer.parseInt(tahun));
            } else {
                permohonanRujukanLuar.setTempohTahun(0);
            }

            //Added to save No Rujukan Fail
            if (StringUtils.isNotBlank(noFail)) {
                permohonanRujukanLuar.setNoFail(noFail);
            } else {
                permohonanRujukanLuar.setNoFail("");
            }

            permohonanRujukanLuar.setCawangan(hakmilik.getCawangan());
            permohonanRujukanLuar.setHakmilik(hakmilik);
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setInfoAudit(ia);
            permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("FL"));

            if (StringUtils.isNotBlank(tkhMula)) {
                permohonanRujukanLuar.setTarikhKuatkuasa(sdf.parse(tkhMula));
//                permohonanUrusan.setPerjanjianTarikhMula(sdf.parse(tkhMula));
            }
            if (StringUtils.isNotBlank(tkhTamat)) {
                permohonanRujukanLuar.setTarikhTamat(sdf.parse(tkhTamat));
//                permohonanUrusan.setPerjanjianTarikhTamat(sdf.parse(tkhTamat));
            }

            permohonanRujukanLuarService.saveOrUpdate(permohonanRujukanLuar);

            if (StringUtils.isNotBlank(hakmilik.getNoPetak()) || StringUtils.isNotBlank(hakmilik.getNoTingkat())
                    || StringUtils.isNotBlank(hakmilik.getNoLot())) {
                InfoAudit ia2 = new InfoAudit();
                ia2 = hakmilik.getInfoAudit();
                ia2.setDiKemaskiniOleh(pengguna);
                ia2.setTarikhKemaskini(new Date());
                hakmilik.setInfoAudit(ia2);
                senaraiHakmilik.add(hakmilik);
            }

            String copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
            if (StringUtils.isNotBlank(copyToAllHakmilik)) {
                for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                    if (hp == null || hp.getHakmilik() == null
                            || hp.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                        continue;
                    }
                    PermohonanRujukanLuar prl
                            = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(hp.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
                    if (prl == null) {
                        prl = new PermohonanRujukanLuar();
                    }
                    prl.setHakmilik(hp.getHakmilik());
                    prl.setCawangan(hp.getHakmilik().getCawangan());
                    prl.setPermohonan(permohonan);
                    prl.setKodRujukan(permohonanRujukanLuar.getKodRujukan());
                    prl.setInfoAudit(ia);
                    prl.setTarikhKuatkuasa(permohonanRujukanLuar.getTarikhKuatkuasa());
                    prl.setTarikhTamat(permohonanRujukanLuar.getTarikhTamat());
                    prl.setTempohBulan(permohonanRujukanLuar.getTempohBulan());
                    prl.setTempohHari(permohonanRujukanLuar.getTempohHari());
                    prl.setTempohTahun(permohonanRujukanLuar.getTempohTahun());
                    prl.setNoFail(permohonanRujukanLuar.getNoFail());
                    permohonanRujukanLuarService.saveOrUpdate(prl);

                    if (!kodUrusan.equals("IS")) {
                        if (StringUtils.isNotBlank(hakmilik.getNoPetak()) || StringUtils.isNotBlank(hakmilik.getNoTingkat())
                                || StringUtils.isNotBlank(hakmilik.getNoLot())) {
                            Hakmilik hm = hp.getHakmilik();
//                        hm.setNoLot(hakmilik.getNoLot());
//                        hm.setNoPetak(hakmilik.getNoPetak());
//                        hm.setNoTingkat(hakmilik.getNoTingkat());
                            ia = hm.getInfoAudit();
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(new Date());
                            senaraiHakmilik.add(hm);
                        }
                    }
                }
            }

            if (!senaraiHakmilik.isEmpty()) {
                regService.simpanHakmilikList(senaraiHakmilik);
                addSimpleMessage("Kemasukan Data Berjaya");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            addSimpleError("Kemasukan Data Gagal. Sila hubungi Pentadbir Sistem.");
            return new JSP("daftar/maklumat_pajakan.jsp").addParameter("tab", "true");
        }
        return new RedirectResolution(Pajakan.class, "showPajakanForm").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution savePajakanInfo() {
        LOG.info("Start :: savePajakanInfo");
//        permohonan = ((etanahActionBeanContext) getContext()).getPermohonan();

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        // Hakmilik hakmilik = new Hakmilik();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
            LOG.debug("savePajakanInfo idHakmilik :" + hakmilik.getIdHakmilik());
        }

        int hari = 0;
        int bln = 0;
        int thn = 0;
        if (permohonanUrusan != null) {
            hari = permohonanUrusan.getPerjanjianTempohHari();
            bln = permohonanUrusan.getPerjanjianTempohBulan();
            thn = permohonanUrusan.getPerjanjianTempohTahun();
        }

        LOG.info("tmph::" + hari + "," + bln + "," + thn);

        InfoAudit ia = new InfoAudit();

        if (permohonanUrusan != null) {
            permohonanUrusan = uManager.findById(permohonanUrusan.getIdUrusan());
            if (permohonanUrusan == null) {
                permohonanUrusan = new PermohonanUrusan();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(pengguna);
            } else {
                ia = permohonanUrusan.getInfoAudit();
                ia.setTarikhMasuk(ia.getTarikhMasuk());
                ia.setDimasukOleh(ia.getDimasukOleh());
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
            }
        } else {
            permohonanUrusan = new PermohonanUrusan();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(pengguna);
        }
        try {
            if (StringUtils.isNotBlank(tkhMula)) {
                permohonanUrusan.setPerjanjianTarikhMula(sdf.parse(tkhMula));
            } else {
                permohonanUrusan.setPerjanjianTarikhMula(null);
            }
            if (StringUtils.isNotBlank(tkhTamat)) {
                permohonanUrusan.setPerjanjianTarikhTamat(sdf.parse(tkhTamat));
            } else {
                permohonanUrusan.setPerjanjianTarikhTamat(null);
            }
        } catch (Exception ex) {
            LOG.error(ex);
            addSimpleError("Kemasukan Data Gagal. Sila hubungi Pentadbir Sistem.");
            return new JSP("daftar/maklumat_pajakan.jsp").addParameter("tab", "true");
        }

        permohonanUrusan.setPerjanjianTempohHari(hari);
        permohonanUrusan.setPerjanjianTempohBulan(bln);
        permohonanUrusan.setPerjanjianTempohTahun(thn);
        permohonanUrusan.setPermohonan(permohonan);
        permohonanUrusan.setCawangan(pengguna.getKodCawangan());
        permohonanUrusan.setInfoAudit(ia);
        permohonanUrusan = uManager.saveOrUpdate(permohonanUrusan);
        regService.simpanPermohonan(permohonan);
        if (hakmilik != null) {
            LOG.debug("noPetak" + hakmilik.getNoPetak());
            LOG.debug("noBangunan" + hakmilik.getNoBangunan());
            LOG.debug("noTingkat" + hakmilik.getNoTingkat());
            Transaction tx = sessionProvider.get().beginTransaction();
            tx.begin();
            try {
                hakmilik.setInfoAudit(ia);
                regService.simpanHakmilik(hakmilik);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                Throwable t = e;
                // getting the root cause
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                t.printStackTrace();
                addSimpleError("Hapus Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                return null;

            }
        }

        addSimpleMessage("Kemasukan Data Berjaya");
        LOG.info("savePajakanInfo :: finish");
        if (hari == 0 && bln == 0 && thn == 0) {
            getContext().getRequest().setAttribute("selamanya", "true");
        }
        return new JSP("daftar/maklumat_pajakan.jsp").addParameter("tab", "true");
//        return new RedirectResolution(Pajakan.class, "showMaklumatPajakan");
    }

    public Resolution saveLuasPajakan() {
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        List<HakmilikPermohonan> listTemp = new ArrayList<HakmilikPermohonan>();
        int counter = 0;
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
            InfoAudit ia2 = hakmilikPermohonan.getInfoAudit();
            if (ia2 != null) {
                ia.setDimasukOleh(ia2.getDimasukOleh());
                ia.setTarikhMasuk(ia2.getTarikhMasuk());
            }
            //TODO : validate against current luas
            if (validateLuas(hakmilikPermohonan.getHakmilik().getIdHakmilik(),
                    luas[counter].replaceAll(",", ""), kodOum[counter]).equals("1")) {
                addSimpleError("Luas untuk " + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " melebihi luas yang ada.");
                return new JSP("daftar/kemasukan_luas_pajakan.jsp").addParameter("tab", "true");
            }

            hakmilikPermohonan.setLuasTerlibat(new java.math.BigDecimal(luas[counter].replaceAll(",", "")));
            KodUOM kod = kodUomDAO.findById(kodOum[counter]);
            hakmilikPermohonan.setInfoAudit(ia);
            hakmilikPermohonan.setKodUnitLuas(kod);
            listTemp.add(hakmilikPermohonan);
            counter = counter + 1;
        }
        if (hakmilikPermohonanService.saveHakmilikPermohonan(listTemp)) {
            addSimpleMessage("Kemasukan Data Berjaya");
        } else {
            addSimpleError("Kemasukan Data GAGAL. Sila hubungi Pentadbir Sistem");
        }
        return new JSP("daftar/kemasukan_luas_pajakan.jsp").addParameter("tab", "true");
    }

    public String checkTempoh(String idHakmilik, String tahun, String bulan, String hari) {

        String result = "0";

        Calendar currentcal = Calendar.getInstance();
        currentcal.set(Integer.parseInt(tahun), Integer.parseInt(bulan), Integer.parseInt(hari));

        if (kodUrusan.equals(RegConstant.PAJAKAN_KECIL_SEBAHAGIAN_TANAH)) {
            PermohonanHubungan mohonHbgn = null;
            List<PermohonanHubungan> senaraiHubungan
                    = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), idHakmilik);
            for (PermohonanHubungan hb : senaraiHubungan) {
                if (hb == null) {
                    continue;
                }
                mohonHbgn = hb;
                break;
            }
            if (mohonHbgn != null) {
                Permohonan p = mohonHbgn.getPermohonanSasaran();
                if (p != null) {
                    HakmilikUrusan hu = hakmilikUrusanService
                            .findByIdPerserahanIdHakmilik(p.getIdPermohonan(), idHakmilik);
                    if (hu != null) {
                        int year = hu.getTempohTahun();
                        int month = hu.getTempohBulan();
                        int day = hu.getTempohHari();
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month, day);
                        if (currentcal.after(cal)) {
                            javax.swing.JOptionPane.showMessageDialog(null, "hello");
                            result = "1";
                        }
                    }
                }
            }
        }

        return result;
    }

    public String validateLuas(String idHakmilik, String luas, String kodOum) {
        String result = "0";

        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
            if (hm != null && StringUtils.isNotBlank(luas)) {

                luas = luas.replaceAll(",", "");
                BigDecimal b1 = hm.getLuas();
                BigDecimal b2 = BigDecimal.valueOf(Double.parseDouble(luas));
                String kodOumAsal = hm.getKodUnitLuas().getKod();

                if (kodUrusan.equals(RegConstant.PAJAKAN_KECIL_SEBAHAGIAN_TANAH)) {
                    PermohonanHubungan mohonHbgn = null;
                    List<PermohonanHubungan> senaraiHubungan
                            = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), idHakmilik);
                    for (PermohonanHubungan hb : senaraiHubungan) {
                        if (hb == null) {
                            continue;
                        }
                        mohonHbgn = hb;
                        break;
                    }
                    if (mohonHbgn != null) {
                        Permohonan p = mohonHbgn.getPermohonanSasaran();
                        if (p != null) {
                            HakmilikUrusan hu = hakmilikUrusanService
                                    .findByIdPerserahanIdHakmilik(p.getIdPermohonan(), idHakmilik);
                            if (hu != null) {
                                b1 = hu.getLuasTerlibat();
                                if (hu.getKodUnitLuas() == null) {
                                    kodOumAsal = "";
                                } else {
                                    kodOumAsal = hu.getKodUnitLuas().getKod();
                                }
                            }
                        }
                    }
                }
                if (!kodOumAsal.equalsIgnoreCase(kodOum)) {
                    b2 = calcTax.kiraUnitLuas(kodOumAsal, kodOum, b2);
                }
                int re = 0;

                if (b1 != null) {
                    re = b2.compareTo(b1);
                }
                result = String.valueOf(re);
            }
        }

        return result;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String[] getKodOum() {
        return kodOum;
    }

    public void setKodOum(String[] kodOum) {
        this.kodOum = kodOum;
    }

    public String[] getLuas() {
        return luas;
    }

    public void setLuas(String[] luas) {
        this.luas = luas;
    }

    public PermohonanUrusan getPermohonanUrusan() {
        return permohonanUrusan;
    }

    public void setPermohonanUrusan(PermohonanUrusan permohonanUrusan) {
        this.permohonanUrusan = permohonanUrusan;
    }

    public String getTkhMula() {
        return tkhMula;
    }

    public void setTkhMula(String tkhMula) {
        this.tkhMula = tkhMula;
    }

    public String getTkhTamat() {
        return tkhTamat;
    }

    public void setTkhTamat(String tkhTamat) {
        this.tkhTamat = tkhTamat;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getTkhLama() {
        return tkhLama;
    }

    public void setTkhLama(String tkhLama) {
        this.tkhLama = tkhLama;
    }

    public Date getTrhMulaPlus() {
        return trhMulaPlus;
    }

    public void setTrhMulaPlus(Date trhMulaPlus) {
        this.trhMulaPlus = trhMulaPlus;
    }
}
