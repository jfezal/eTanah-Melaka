/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.HakmilikUrusanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.common.HakmilikPermohonanService;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.log4j.Logger;
import etanah.model.Hakmilik;
import etanah.dao.HakmilikDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodPerhubunganHakmilik;
import etanah.service.RegService;
import etanah.model.InfoAudit;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodPerhubunganHakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.model.AlamatSurat;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.HakmilikPetakAksesori;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodLot;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanPihak;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.Pihak;
import etanah.sequence.GeneratorIdHakmilik;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
//import etanah.util.StringUtils;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/common/maklumat_hakmilik_permohonan")
public class MaklumatHakmilikPermohonanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatHakmilikPermohonanActionBean.class);
    @Inject
    HakmilikPermohonanService hPService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    RegService regService;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodPerhubunganHakmilikDAO kodPerhubunganHakmilikDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    private GeneratorIdHakmilik idHakmilikGenerator;
    @Inject
    etanah.Configuration conf;
    @Inject
    private HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    //added
    @Inject
    StrataPtService strPtService;
//    @Inject
//    StringUtils sUtils;
//    @Inject
//    HakmilikDAO hakmilikDAO;
//    @Inject
//    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> list1;
    private List<HakmilikPermohonan> list2;
    //added
    private List<HakmilikPermohonan> hakmilikPermohonanListtemp;
    private List<HakmilikPihakBerkepentingan> listHP;
    private List<HakmilikPihakBerkepentingan> listOtherHP;
    private List<HakmilikPermohonan> hakmilikPermohonanKemaskini;
    private List<HakmilikPermohonan> hakmilikPermohonanSebelumList;
    private List<HakmilikPermohonan> hakmilikPermohonanListHakmilik;
    private List<HakmilikUrusan> urusanList;
    private List<HakmilikPihakBerkepentingan> listHakmilikPihak;// list all pihak other then pemilik
    private List<HakmilikPihakBerkepentingan> listPemilik; // list all pemilik
    private int size = 0;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikSebelumPermohonan hpSblm;
    private Pengguna pengguna;
    private Permohonan p;
    private Hakmilik hb;
    private List hakmilikPermohonanMenanggung;
    private List hakmilikPermohonanMenguasai;
    private List<KodUOM> senaraiKodUOM;
    String hubunganHakmilik;
//    private HakmilikPermohonan hp;
    String idHakmilikBaru;
    private String[] strKodUOM;
    private String[] strKodSyaratNyata;
    private String[] strKodSekatan;
    private String[] kodLot;
    private String[] jenisLot;
    private ArrayList<HakmilikPermohonan> listMohonHakmilikBaru = new ArrayList<HakmilikPermohonan>();
    private ArrayList<Hakmilik> listHakmilikBaru = new ArrayList<Hakmilik>();
    private ArrayList<HakmilikPetakAksesori> listHakmilikPetakAksesori = new ArrayList<HakmilikPetakAksesori>();
    private ArrayList<HakmilikPihakBerkepentingan> lhp = new ArrayList<HakmilikPihakBerkepentingan>();
    private StringBuilder msg;
    private StringBuilder err;
    private Permohonan pSebelum;
    private HakmilikUrusan hu;
    private Hakmilik hakmilik;
    static final Comparator<HakmilikPermohonan> HAKMILIK_ORDER
            = new Comparator<HakmilikPermohonan>() {
        @Override
        public int compare(HakmilikPermohonan hp1, HakmilikPermohonan hp2) {

            String id1 = hp1.getHakmilik().getIdHakmilik();
            String id2 = hp2.getHakmilik().getIdHakmilik();

            return id1.compareTo(id2);

        }
    };
    static final Comparator<HakmilikPihakBerkepentingan> HAKMILIK_ORDER1
            = new Comparator<HakmilikPihakBerkepentingan>() {
        @Override
        public int compare(HakmilikPihakBerkepentingan hp1, HakmilikPihakBerkepentingan hp2) {

            String id1 = hp1.getHakmilik().getIdHakmilik();
            String id2 = hp2.getHakmilik().getIdHakmilik();

            return id1.compareTo(id2);

        }
    };

    @DefaultHandler
    public Resolution showForm() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan.jsp").addParameter("tab", "true");
    }

    @HttpCache(allow = false)
    public Resolution showForm2() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("idHakmilik maklumat hakmilik permohonan :" + idHakmilik);
        LOG.debug("idMohon maklumat hakmilik permohonan :" + idPermohonan);
        LOG.debug("kod urusan :" + p.getKodUrusan().getKod());
        //rehydrate();
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
        p = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> lhp = p.getSenaraiHakmilik();
        if (!lhp.isEmpty()) {
            Hakmilik hk = lhp.get(0).getHakmilik();
            LOG.debug(" hakmilik :" + hk.getIdHakmilik());
            LOG.debug("kod jenis hakmilik :" + hk.getKodHakmilik().getKod());

            if (("HTIR".equals(p.getKodUrusan().getKod())) && ("IR".equals(hk.getKodHakmilik().getKod()))) {
                LOG.debug("masuk sini if kod jenis hakmilik IR");
                return new JSP("common/paparan_conversion_pt.jsp").addParameter("tab", "true");
            } else {
                return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
            }
        }

        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");

        //return new ForwardResolution("/WEB-INF/jsp/common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    //add by ida 061013
    public Resolution showFormHakmilikAsal() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.debug("idHakmilik maklumat hakmilik permohonan :" + idHakmilik);
        //rehydrate();
        return new JSP("daftar/senarai_hakmilik_permohonan.jsp").addParameter("tab", "true");
        //return new ForwardResolution("/WEB-INF/jsp/common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilik() {
        return new JSP("daftar/senarai_hakmilik_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanBerkelompok() {
        int counter = 0;
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
            Hakmilik hm = hakmilikPermohonan.getHakmilik();
            LOG.debug("hakmilikPermohonanList lot updated:" + hakmilikPermohonan.getHakmilik().getNoLot() + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            LOG.debug("hakmilikPermohonanList luas updated:" + hakmilikPermohonan.getHakmilik().getLuas() + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            //LOG.debug("hakmilikPermohonanList koduom updated:" + hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod() + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            KodUOM kuom = kodUOMDAO.findById(strKodUOM[counter]);
            KodLot kl = kodLotDAO.findById(kodLot[counter]);

            counter = counter + 1;
            if (kuom != null) {
                hm.setKodUnitLuas(kuom);
            }

            if (kl != null) {
                hm.setLot(kl);
            }
            regService.simpanHakmilik(hm);
        }

//        for (int k = 0; k < hakmilikPermohonanList.size(); k++) {
//            HakmilikPermohonan hp = hakmilikPermohonanList.get(k);
//            KodUOM kuom = kodUOMDAO.findById(strKodUOM[k]);
//            if (kuom != null) {
//                hp.setKodUnitLuas(kuom);
//            }
//            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
//        }
        if (hakmilikPermohonanList.size() > 0) {
            hakmilikPermohonanService.saveHakmilikPermohonan(hakmilikPermohonanList);
        }
        addSimpleMessage("Kemaskini Data Berjaya");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPihak() {

        int counter = 0;

        idHakmilik = (String) getContext().getRequest().getAttribute("idHakmilik");

        String penubuhanSyarikat = (String) getContext().getRequest().getParameter("pihak.penubuhanSyarikat.kod");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDimasukOleh(info.getDimasukOleh());
        info.setTarikhMasuk(info.getTarikhMasuk());
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
//        info.setTarikhMasuk(new java.util.Date());
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
            Hakmilik hm = hakmilikPermohonan.getHakmilik();
            LOG.debug("hakmilikPermohonanList lot updated:" + hakmilikPermohonan.getHakmilik().getNoLot() + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            LOG.debug("hakmilikPermohonanList luas updated:" + hakmilikPermohonan.getHakmilik().getLuas() + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            //LOG.debug("hakmilikPermohonanList koduom updated:" + hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod() + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            KodUOM kuom = kodUOMDAO.findById(strKodUOM[counter]);
            KodSyaratNyata ksn = regService.searchKodSyaratByCaw(strKodSyaratNyata[counter], hm.getCawangan().getKod());
            KodSekatanKepentingan ksk = regService.searchKodSekatanByCaw(strKodSekatan[counter], hm.getCawangan().getKod());
            KodLot kl = kodLotDAO.findById(kodLot[counter]);

            counter = counter + 1;
            if (kuom != null) {
                hm.setKodUnitLuas(kuom);
            }
            if (ksn != null) {
                hm.setSyaratNyata(ksn);
            }
            if (ksk != null) {
                hm.setSekatanKepentingan(ksk);
            }
            if (kl != null) {
                hm.setLot(kl);
            }
            regService.simpanHakmilik(hm);
        }

//        for (int k = 0; k < hakmilikPermohonanList.size(); k++) {
//            HakmilikPermohonan hp = hakmilikPermohonanList.get(k);
//            KodUOM kuom = kodUOMDAO.findById(strKodUOM[k]);
//            if (kuom != null) {
//                hp.setKodUnitLuas(kuom);
//            }
//            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
//        }
        if (hakmilikPermohonanList.size() > 0) {
            hakmilikPermohonanService.saveHakmilikPermohonan(hakmilikPermohonanList);
        }
        addSimpleMessage("Kemaskini Data Berjaya");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");

    }

    public Resolution editHakmilikPermohonan() {
        LOG.debug("edit hakmilik permohonan");
        hakmilikPermohonanKemaskini = new ArrayList<HakmilikPermohonan>();
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            LOG.debug("idHakmilik = " + idHakmilik);
            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                if (hp == null) {
                    continue;

                }
                if (hp.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                    LOG.debug("id hakmilik sama");
                    hakmilikPermohonanKemaskini.add(hp);
                }
            }
        } else {
            hakmilikPermohonanKemaskini = hakmilikPermohonanList;
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/kemaskini_hakmilik_permohonan.jsp").addParameter("popup", "true");
    }

    public Resolution saveHakmilik() {
        String[] senaraiHakmilikBaru = getContext().getRequest().getParameterValues("idHakmilikBaru");
        List<HakmilikPermohonan> senaraiHakmilikPermohonan = p.getSenaraiHakmilik();
        List<HakmilikPermohonan> senaraiHakmilikTmp = new ArrayList<HakmilikPermohonan>();
        InfoAudit ia = new InfoAudit();
        LOG.debug("size = " + senaraiHakmilikBaru.length);
        if (senaraiHakmilikBaru.length > 0) {
            int i = 0;
            for (String id : senaraiHakmilikBaru) {
                LOG.debug("id hakmilik baru = " + id);
                if (StringUtils.isBlank(id)) {
                    continue;

                }
                Hakmilik hakmilik = hakmilikDAO.findById(id);
                if (hakmilik == null) {
                    continue;

                }
                HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(i);
                ia = hp.getInfoAudit();
                ia.setTarikhKemaskini(new Date());
                ia.setDiKemaskiniOleh(pengguna);
                hp.setHakmilik(hakmilik);
                hp.setInfoAudit(ia);
                senaraiHakmilikTmp.add(hp);

                i++;
            }

            hakmilikPermohonanService.saveHakmilikPermohonan(senaraiHakmilikTmp);
        }
        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class, "senaraiHakmilik");
    }

    public Resolution senaraiHakmilikUrusan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> lhp = p.getSenaraiHakmilik();
        Hakmilik h = lhp.get(0).getHakmilik();
        urusanList = hakmilikUrusanService.findHakmilikUrusanUrusniagaKaveatByIdHakmilik(h.getIdHakmilik());
        return new JSP("daftar/senarai_hakmilik_urusan.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilikIsmen() {
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        logger.debug("idPermohonan :"+idPermohonan);
        msg = new StringBuilder();
        err = new StringBuilder();

        if (msg.length() > 0) {
            addSimpleMessage(msg.toString());
        }
        if (err.length() > 0) {
            addSimpleError(err.toString());

        }
        return new JSP("daftar/senarai_hakmilik_ismen.jsp").addParameter("tab", "true");
    }

    public Resolution showMaklumatHakmilikSmbgConvertForm() {
        return new JSP("daftar/maklumat_smbg_convert.jsp").addParameter("tab", "true");
    }

    public Resolution showCarianHakmilik() {
        return new JSP("daftar/common/carian_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution deleteHakmilik() {

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("Deleting idHakmilik :" + idHakmilik);

        if (idHakmilik != null) {
            //List <HakmilikPermohonan> lhp = new ArrayList();
            //lhp = regService.searchMohonHakmilik(idHakmilik, idPermohonan);
            HakmilikPermohonan hp = regService.searchMohonHakmilikObject(idHakmilik, idPermohonan);

            if (hp != null) {
                regService.deleteMohonHakmilik(hp);
                //regService.deleteMohonHakmilik(lhp);
            }
        }
        //rehydrate();
        addSimpleMessage("Data Telah Berjaya diHapuskan");
        //return new JSP("daftar/senarai_hakmilik_ismen.jsp").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class, "senaraiHakmilikIsmen").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution simpanHakmilik() {
        LOG.debug("saving hakmilik");
        LOG.debug("idHakmilikBaru : " + idHakmilikBaru);
        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilikBaru);

        if (hakmilik != null) {
            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            HakmilikPermohonan hp = new HakmilikPermohonan();
            hp.setHakmilik(hakmilik);
            hp.setPermohonan(p);
            //set kod tangung ismen K = Lot menguasai
            KodPerhubunganHakmilik kph = new KodPerhubunganHakmilik();

            kph.setKod("K");
            hp.setHubunganHakmilik(kph);
            hp.setInfoAudit(info);
            regService.simpanMohonHakmilik(hp);

        }
        addSimpleMessage("Kemasukan Data Berjaya");
        //return new JSP("daftar/senarai_hakmilik_ismen.jsp").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class, "senaraiHakmilikIsmen").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
        //return new ForwardResolution(MaklumatHakmilikPermohonanActionBean.class, "senaraiHakmilikIsmen").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution searchHakmilik() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        LOG.debug("IDHakmilik :" + idHakmilik);
        LOG.debug("IDHakmilik yg dicari : " + idHakmilikBaru);
        if (idHakmilikBaru != null) {
//            Hakmilik hb = new Hakmilik();
            hb = hakmilikDAO.findById(idHakmilikBaru);

            if (hb == null) {
                addSimpleError("Tiada Hakmilik Dijumpai");
            }

        } else {
            addSimpleError("Sila Masukkan ID Hakmilik");
        }
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/daftar/common/carian_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() {
        KodPerhubunganHakmilik kph = new KodPerhubunganHakmilik();
        LOG.debug("::SIMPAN::");
        if (hubunganHakmilik != null) {
            LOG.debug(hubunganHakmilik);
            kph.setKod(hubunganHakmilik);
            hakmilikPermohonan.setHubunganHakmilik(kph);
            if (hPService.saveSingleHakmilikPermohonan(hakmilikPermohonan)) {
                addSimpleMessage("Kemasukan Data Berjaya");
            } else {
                addSimpleError("Kemasukan Data Gagal");
            }
        } else {
            addSimpleError("Sila Pilih Kod TanggungKuasa Ismen");

        }
        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class, "senaraiHakmilikIsmen").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
        //return new JSP("daftar/senarai_hakmilik_ismen.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKuasaTanggung() {
        msg = new StringBuilder();
        err = new StringBuilder();

        List<PermohonanPihak> senaraiPermohonanPihak = permohonanPihakService.getSenaraiPmohonPihak(p.getIdPermohonan());
        permohonanPihakService.delete(senaraiPermohonanPihak);
        List<Pemohon> senaraiPemohon = pemohonService.findPemohonByIdPermohonan(p.getIdPermohonan());
        pemohonService.delete(senaraiPemohon);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());

        List<PermohonanPihak> senarai1 = new ArrayList<PermohonanPihak>();
        List<Pemohon> senarai2 = new ArrayList<Pemohon>();

        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

            HakmilikPermohonan hp = hakmilikPermohonanList.get(i);
            if (hp == null || jenisLot[i] == null) {
                continue;
            }

            Hakmilik hm = hp.getHakmilik();

            List<HakmilikPihakBerkepentingan> senaraiPihak = hm.getSenaraiPihakBerkepentingan();

            KodPerhubunganHakmilik kod = kodPerhubunganHakmilikDAO.findById(jenisLot[i]);
            hp.setHubunganHakmilik(kod);
            hPService.saveSingleHakmilikPermohonan(hp);

            for (HakmilikPihakBerkepentingan hpk : senaraiPihak) {
                if (hpk == null || hpk.getAktif() == 'T') {
                    continue;
                }
                if (kod.getKod().equals("LK")) {
                    PermohonanPihak pp = new PermohonanPihak();
                    pp.setCawangan(hm.getCawangan());
                    pp.setHakmilik(hm);
                    pp.setInfoAudit(ia);
                    pp.setJenis(kodJenisPihakBerkepentinganDAO.findById("PI")); //pemasuk Ismen
                    pp.setPermohonan(p);
                    pp.setPihak(hpk.getPihak());
                    if (hpk.getSyerPembilang() != null) {
                        pp.setSyerPembilang(hpk.getSyerPembilang());
                    }
                    if (hpk.getSyerPenyebut() != null) {
                        pp.setSyerPenyebut(hpk.getSyerPenyebut());
                    }
                    senarai1.add(pp);
                } else if (kod.getKod().equals("LT")) {

                    if (hpk.getJenis().getKod().equals("PG")
                            || hpk.getJenis().getKod().equals("PJ")
                            || hpk.getJenis().getKod().equals("PJK")
                            || hpk.getJenis().getKod().equals("PY")) {
                        continue;
                    }

                    Pemohon pemohon = new Pemohon();
                    pemohon.setCawangan(hm.getCawangan());
                    pemohon.setInfoAudit(ia);
                    pemohon.setPermohonan(p);
                    pemohon.setPihak(hpk.getPihak());
                    senarai2.add(pemohon);
                }
            }
        }
        permohonanPihakService.saveOrUpdate(senarai1);
        pemohonService.saveOrUpdateMultiple(senarai2);

//        hPService.saveHakmilikPermohonan(hakmilikPermohonanList);
        msg.append("Kemasukan Data Berjaya.");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class, "senaraiHakmilikIsmen").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!deleteHakmilik,!generateIDHakmilikStrata"})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//       Permohonan p = ((etanahActionBeanContext)getContext()).getPermohonan();

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (p != null) {
            if (p.getKodUrusan().getKod().equals("IS") || p.getKodUrusan().getKod().equals("ISBLS")) {
                hakmilikPermohonanMenanggung = regService.senaraiMohonHakmilikMenanggung(p.getIdPermohonan());
                hakmilikPermohonanMenguasai = regService.senaraiMohonHakmilikMenguasai(p.getIdPermohonan());
            }

            if (p.getIdPermohonan().contains("STR")) {
                LOG.debug("--Strata--IdMohon--");
                hakmilikPermohonanListtemp = strPtService.findIdHakmilikSort(p.getIdPermohonan());
                LOG.debug("--Strata--hakmilikPermohonanListtemp--" + hakmilikPermohonanListtemp);
                hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
                for (int i = 0; i < hakmilikPermohonanListtemp.size(); i++) {
                    HakmilikPermohonan hp = new HakmilikPermohonan();
                    hp = hakmilikPermohonanListtemp.get(i);
                    if (hp.getHakmilik().getIdHakmilik().length() > 20) {
                        hakmilikPermohonanList.add(hp);
                        LOG.debug("--Strata--hakmilikPermohonanList--" + hakmilikPermohonanList);
                    }
                }
            } else if (p.getKodUrusan().getKod().equals("HTB") || p.getKodUrusan().getKod().equals("PBCTM")
                    || p.getKodUrusan().getKod().equals("PBCTL") || p.getKodUrusan().getKod().equals("PBCTT")) {
                LOG.debug("--Kod Urusan HTB/PHPP--");
                Permohonan pp = p.getPermohonanSebelum();
                //Add this condition for handle null (id Sebelum)
                if (pp == null) {
                    LOG.debug("ID Permohonan Sebelum Tiada");
//            hakmilikPermohonanList = Collections.emptyList();
                    hakmilikPermohonanList = p.getSenaraiHakmilik();
                } //end condition
                //LOG.debug("--ID Sebelum for HTB/HTB--"+p.getPermohonanSebelum().getIdPermohonan());
                else {
                    hakmilikPermohonanList = strPtService.findIdHakmilikSort(p.getPermohonanSebelum().getIdPermohonan());
                }
                LOG.debug("--hakmilikPermohonanList--" + hakmilikPermohonanList.size());
            } else if (p.getKodUrusan().getKod().equals("HSSTA") || p.getKodUrusan().getKod().equals("HKSTA")) {
                LOG.debug("--Kod Urusan -- : " + p.getKodUrusan().getKod());

                hakmilikPermohonanList = p.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                    LOG.debug("--id Hakmilik Current-- : " + hp.getHakmilik().getIdHakmilik());
                    LOG.debug("--id MH-- : " + hp.getId());
                    hpSblm = strPtService.findIdMohonSblm(Long.toString(hp.getId()));
                    String idHakmilikTerdahulu = hp.getHakmilik().getIdHakmilik();
                    hakmilik = hakmilikDAO.findById(idHakmilikTerdahulu);
                    hu = strPtService.findIdMohonHakmilikUrusan(hpSblm.getHakmilikSejarah());
                    if (hakmilik.getLuas() == null || hakmilik.getLuas() == BigDecimal.ZERO) {
                        BigDecimal luasAsal = hpSblm.getHakmilik().getLuas();
                        BigDecimal luasTerlibat = hu.getLuasTerlibat();
                        BigDecimal baki = luasAsal.subtract(luasTerlibat);
                        hakmilik.setLuas(baki);
                        strPtService.simpanHakmilik(hakmilik);
                    }
                    if (hakmilik.getCukai() == null) {
                        BigDecimal cukai = hu.getCukaiBaru();
                        hakmilik.setCukai(cukai);
                        strPtService.simpanHakmilik(hakmilik);
                    }
                }
//                    LOG.debug("--hakmilikPermohonanList--"+hakmilikPermohonanSebelumList.size());
            } else {
                hakmilikPermohonanList = p.getSenaraiHakmilik();
            }
            size = hakmilikPermohonanList.size();
            if (hakmilikPermohonanList.size() > 0) {
                strKodUOM = new String[hakmilikPermohonanList.size()];
                strKodSyaratNyata = new String[hakmilikPermohonanList.size()];
                strKodSekatan = new String[hakmilikPermohonanList.size()];
                jenisLot = new String[hakmilikPermohonanList.size()];
                kodLot = new String[hakmilikPermohonanList.size()];
                int counter = 0;
                for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                    HakmilikPermohonan hp = hakmilikPermohonanList.get(i);

                    if (hp.getHakmilik().getKodUnitLuas() != null) {
                        strKodUOM[counter] = hp.getHakmilik().getKodUnitLuas().getKod();
                    } else if (hp.getHakmilik().getKodUnitLuas() == null) {
                        strKodUOM[counter] = "";
                    }

                    if (hp.getHakmilik().getSyaratNyata() != null) {
                        KodSyaratNyata ksn = regService.searchKodSyaratByCaw(hp.getHakmilik().getSyaratNyata().getKodSyarat(), hp.getHakmilik().getCawangan().getKod());
//                        strKodSyaratNyata[counter] = etanah.util.StringUtils.removeLeadingZeros(ksn.getKodSyarat());
                        if (ksn != null) {
                            strKodSyaratNyata[counter] = ksn.getKodSyarat();
                        } else {
                            strKodSyaratNyata[counter] = "";
                        }
                    }
                    if (hp.getHakmilik().getSekatanKepentingan() != null) {
//                        KodSekatanKepentingan ksk = regService.searchKodSekatanByCaw(hp.getHakmilik().getSekatanKepentingan().getKodSekatan(), hp.getHakmilik().getCawangan().getKod());
                        KodSekatanKepentingan ksk = hp.getSekatanKepentingan();

                        if (!hp.getHakmilik().getSekatanKepentingan().getKodSekatan().equals("0000000")) {
//                            strKodSekatan[counter] = etanah.util.StringUtils.removeLeadingZeros(ksk.getKodSekatan());
//                        } else {
                            if (ksk != null) {
                                strKodSekatan[counter] = hp.getSekatanKepentingan().getKod();
                            } else {
                                strKodSekatan[counter] = "";
                            }
//                        }
                        }
                        if (hp.getHubunganHakmilik() != null) {
                            jenisLot[counter] = hp.getHubunganHakmilik().getKod();
                        }
                        if (hp.getHakmilik().getLot() != null) {
                            kodLot[counter] = hp.getHakmilik().getLot().getKod();
                        }
                        counter = counter + 1;
                    }

//                    List<HakmilikUrusan> hakmilikUrusan = new ArrayList<HakmilikUrusan>();
//                    urusanList = new ArrayList<HakmilikUrusan>();
////                    for (HakmilikPermohonan hp1 : hakmilikPermohonanList) {
//                    if ((hakmilikPermohonanList.size() > 100) && (hakmilikPermohonanList.size() < 200)) {
//                        list1 = hakmilikPermohonanList.subList(0, 99);
//                        for (HakmilikPermohonan hakmilikPermohonan1 : list1) {
//                            idHakmilik = hakmilikPermohonan1.getHakmilik().getIdHakmilik();
//                            hakmilikUrusan = hakmilikUrusanService.findHakmilikUrusanByIdHakmilik(idHakmilik);
//                            for (HakmilikUrusan hu : hakmilikUrusan) {
//                                if (hu == null) {
//                                    continue;
//                                }
//                                urusanList.add(hu);
//                            }
//                        }
////                            hakmilikPermohonanList.subList(counter, counter)
//                        int nilai = hakmilikPermohonanList.size();
//                        list2 = hakmilikPermohonanList.subList(100, nilai);
//                        for (HakmilikPermohonan hakmilikPermohonan2 : hakmilikPermohonanList) {
//                            idHakmilik = hakmilikPermohonan2.getHakmilik().getIdHakmilik();
//                            hakmilikUrusan = hakmilikUrusanService.findHakmilikUrusanByIdHakmilik(idHakmilik);
//                            for (HakmilikUrusan hu : hakmilikUrusan) {
//                                if (hu == null) {
//                                    continue;
//                                }
//                                urusanList.add(hu);
//                            }
//                        }
//                    }
                }
//      -------- add by azri: show list of hakmilik berkepentingan in "Maklumat Hakmilik" tab
                try {
                    for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                        LOG.info("id mohon = " + idPermohonan);
                        listHP = regService.getPemilikOnlyPMPP(hp.getHakmilik().getIdHakmilik());
                        listOtherHP = regService.getPihakNotPemilikPPPM(hp.getHakmilik().getIdHakmilik());
                        Map<Long, HakmilikPihakBerkepentingan> pemilikMap = new HashMap<Long, HakmilikPihakBerkepentingan>();
                        Map<Long, HakmilikPihakBerkepentingan> pihakMap = new HashMap<Long, HakmilikPihakBerkepentingan>();

                        for (HakmilikPihakBerkepentingan pemilik : listHP) {
                            Long id = pemilik.getIdHakmilikPihakBerkepentingan();
                            if (pemilikMap.containsKey(id)) {
                                continue;
                            } else {
                                pemilikMap.put(id, pemilik);
                            }
                        }

                        for (HakmilikPihakBerkepentingan hp2 : listOtherHP) {
                            Long id = hp2.getIdHakmilikPihakBerkepentingan();
                            if (pihakMap.containsKey(id)) {
                                continue;
                            } else {
                                pihakMap.put(id, hp2);
                            }
                        }
                        listPemilik = new ArrayList(pemilikMap.values());
                        listHakmilikPihak = new ArrayList(pihakMap.values());
                        LOG.info("------>  list Hakmilik Pihak : " + listPemilik.size());
                        LOG.info("------>  list Hakmilik Pihak : " + listHakmilikPihak.size());
                        if (!listPemilik.isEmpty()) {
                            Collections.sort(listPemilik, HAKMILIK_ORDER1);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    LOG.error(ex);
                }//      ----- add by azri: END
            }
            if (!hakmilikPermohonanList.isEmpty()) {
                Collections.sort(hakmilikPermohonanList, HAKMILIK_ORDER);
            }
        }
    }

    public Resolution generateIDHakmilikStrata() {
        LOG.debug(":generateIDHakmilikStrata:");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        generateHakmilikStrata(pengguna.getInfoAudit(), p, pengguna);
        rehydrate();
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public void generateHakmilikStrata(InfoAudit ia, Permohonan p, Pengguna pengguna) {
        Permohonan permohonanSebelum = p.getPermohonanSebelum();
//        List<HakmilikPihakBerkepentingan> lhp = new  ArrayList<HakmilikPihakBerkepentingan>();
        if (permohonanSebelum != null) {
            Hakmilik hMasterTitle = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik();
            LOG.debug(":generate Hakmilik Strata:");
            LOG.debug("idPermohonanSebelum :" + permohonanSebelum.getIdPermohonan());
            List<PermohonanBangunan> senaraiBangunan = permohonanSebelum.getSenaraiBangunan();

            LOG.debug(":Bangunan : " + senaraiBangunan.size());
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {

                List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                LOG.debug(":Tingkat : " + senaraiTingkat.size());
                int countTingkat = 1;
                for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                    List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();

                    LOG.debug(":Petak : " + senaraiPetak.size());
                    int countPetak = 1;
                    for (BangunanPetak bangunanPetak : senaraiPetak) {
                        String noBangunan = permohonanBangunan.getKodKategoriBangunan().getKod() + permohonanBangunan.getNama();

                        String noTingkat = String.valueOf(countTingkat);
                        String noPetak = String.valueOf(countPetak);

                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat(noTingkat);
                        hakmilik.setNoPetak(noPetak);

                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                        LOG.debug("noBangunan : " + hakmilik.getNoBangunan());
                        LOG.debug("noBangunan substring 1 : " + hakmilik.getNoBangunan().substring(1));

                        String kodNegeri = conf.getProperty("kodNegeri");
                        String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);

                        LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                        Hakmilik hakmilikBaru = new Hakmilik();
                        /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                        hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                        hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                        hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                        hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                        hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                        hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                        hakmilikBaru.setLot(hMasterTitle.getLot());
                        hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                        hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                        hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                        hakmilikBaru.setTarikhDaftar(new java.util.Date());
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hakmilikBaru.setKodStatusHakmilik(ksh);
                        hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                        hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                        hakmilikBaru.setKodUnitLuas(hMasterTitle.getKodUnitLuas());
                        hakmilikBaru.setLuas(hMasterTitle.getLuas());
                        String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                        /*copy NO HAKMILIK*/
                        LOG.debug("noHakmilik :" + noHakmilik);
                        hakmilikBaru.setNoHakmilik(noHakmilik);
                        hakmilikBaru.setNoPelan(hMasterTitle.getNoPelan());
                        hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                        hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                        hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                        hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                        hakmilikBaru.setPbt(hMasterTitle.getPbt());
                        hakmilikBaru.setCukai(hMasterTitle.getCukai());
                        hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                        hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                        hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                        hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                        hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                        hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                        hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                        hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                        hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                        hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                        hakmilikBaru.setNoBangunan(noBangunan);
                        hakmilikBaru.setNoTingkat(noTingkat);
                        hakmilikBaru.setNoPetak(noPetak);
                        hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                        hakmilikBaru.setInfoAudit(ia);

                        /*INSERT INTO MOHON HAKMILIK*/
                        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                        mohonHakmilikBaru.setPermohonan(p);
                        mohonHakmilikBaru.setInfoAudit(ia);
                        listMohonHakmilikBaru.add(mohonHakmilikBaru);
                        listHakmilikBaru.add(hakmilikBaru);

                        List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                        for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                            HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                            hpa.setHakmilik(hakmilikBaru);
                            hpa.setCawangan(hakmilikBaru.getCawangan());
                            hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                            hpa.setNama(bangunanPetakAksesori.getNama());
                            hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                            hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                            hpa.setInfoAudit(ia);
                            listHakmilikPetakAksesori.add(hpa);
                        }

//                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hMasterTitle.getSenaraiPihakBerkepentingan();
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                        LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
                        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                            if (hpk == null || hpk.getAktif() == 'T') {
                                continue;
                            }

                            HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                            hpb.setHakmilik(hakmilikBaru);
                            hpb.setCawangan(hakmilikBaru.getCawangan());
                            hpb.setPihakCawangan(hpk.getPihakCawangan());
                            hpb.setJenis(hpk.getJenis());
                            hpb.setSyerPembilang(hpk.getSyerPembilang());
                            hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                            hpb.setPerserahan(hpk.getPerserahan());
                            hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                            hpb.setKaveatAktif(hpk.getKaveatAktif());
                            hpb.setAktif(hpk.getAktif());
                            hpb.setPihak(hpk.getPihak());
                            hpb.setInfoAudit(ia);
                            //hpkService.save(hpb);
                            lhp.add(hpb);
                        }
                        countPetak += 1;
                    }
                    countTingkat += 1;
                }

            }

            if (!listHakmilikBaru.isEmpty()) {
                regService.simpanHakmilikList(listHakmilikBaru);
            }
            if (!listMohonHakmilikBaru.isEmpty()) {
                regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
            }
            if (!listHakmilikPetakAksesori.isEmpty()) {
                //TODO:HAKMILIKPETAKAKSESORI SERVICE TO INSERT
                regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
            }
            if (!lhp.isEmpty()) {
                regService.simpanHakmilikPihak(lhp);
            }
        }

    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public String[] getStrKodUOM() {
        return strKodUOM;
    }

    public void setStrKodUOM(String[] strKodUOM) {
        this.strKodUOM = strKodUOM;
    }

    public List getHakmilikPermohonanMenguasai() {
        return hakmilikPermohonanMenguasai;
    }

    public void setHakmilikPermohonanMenguasai(List hakmilikPermohonanMenguasai) {
        this.hakmilikPermohonanMenguasai = hakmilikPermohonanMenguasai;
    }

    public List getHakmilikPermohonanMenanggung() {
        return hakmilikPermohonanMenanggung;
    }

    public void setHakmilikPermohonanMenanggung(List hakmilikPermohonanMenanggung) {
        this.hakmilikPermohonanMenanggung = hakmilikPermohonanMenanggung;
    }

    public String getIdHakmilikBaru() {
        return idHakmilikBaru;
    }

    public void setIdHakmilikBaru(String idHakmilikBaru) {
        this.idHakmilikBaru = idHakmilikBaru;
    }

    public Hakmilik getHb() {
        return hb;
    }

    public void setHb(Hakmilik hb) {
        this.hb = hb;
    }

//    public HakmilikPermohonan getHp() {
//        return hp;
//    }
//
//    public void setHp(HakmilikPermohonan hp) {
//        this.hp = hp;
//    }
    public String getHubunganHakmilik() {
        return hubunganHakmilik;
    }

    public void setHubunganHakmilik(String hubunganHakmilik) {
        this.hubunganHakmilik = hubunganHakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikUrusan> getUrusanList() {
        return urusanList;
    }

    public void setUrusanList(List<HakmilikUrusan> urusanList) {
        this.urusanList = urusanList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanKemaskini() {
        return hakmilikPermohonanKemaskini;
    }

    public void setHakmilikPermohonanKemaskini(List<HakmilikPermohonan> hakmilikPermohonanKemaskini) {
        this.hakmilikPermohonanKemaskini = hakmilikPermohonanKemaskini;
    }

    public List<KodUOM> getSenaraiKodUOM() {
        return senaraiKodUOM;
    }

    public void setSenaraiKodUOM(List<KodUOM> senaraiKodUOM) {
        this.senaraiKodUOM = senaraiKodUOM;
    }

    public String[] getStrKodSekatan() {
        return strKodSekatan;
    }

    public void setStrKodSekatan(String[] strKodSekatan) {
        this.strKodSekatan = strKodSekatan;
    }

    public String[] getStrKodSyaratNyata() {
        return strKodSyaratNyata;
    }

    public void setStrKodSyaratNyata(String[] strKodSyaratNyata) {
        this.strKodSyaratNyata = strKodSyaratNyata;
    }

    public String[] getJenisLot() {
        return jenisLot;
    }

    public void setJenisLot(String[] jenisLot) {
        this.jenisLot = jenisLot;
    }

    public String[] getKodLot() {
        return kodLot;
    }

    public void setKodLot(String[] kodLot) {
        this.kodLot = kodLot;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListtemp() {
        return hakmilikPermohonanListtemp;
    }

    public void setHakmilikPermohonanListtemp(List<HakmilikPermohonan> hakmilikPermohonanListtemp) {
        this.hakmilikPermohonanListtemp = hakmilikPermohonanListtemp;
    }

    public HakmilikSebelumPermohonan getHpSblm() {
        return hpSblm;
    }

    public void setHpSblm(HakmilikSebelumPermohonan hpSblm) {
        this.hpSblm = hpSblm;
    }

    public Permohonan getpSebelum() {
        return pSebelum;
    }

    public void setpSebelum(Permohonan pSebelum) {
        this.pSebelum = pSebelum;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanSebelumList() {
        return hakmilikPermohonanSebelumList;
    }

    public void setHakmilikPermohonanSebelumList(List<HakmilikPermohonan> hakmilikPermohonanSebelumList) {
        this.hakmilikPermohonanSebelumList = hakmilikPermohonanSebelumList;
    }

    public HakmilikUrusan getHu() {
        return hu;
    }

    public void setHu(HakmilikUrusan hu) {
        this.hu = hu;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
        this.listHakmilikPihak = listHakmilikPihak;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
        return listHakmilikPihak;
    }

    public void setListPemilik(List<HakmilikPihakBerkepentingan> listPemilik) {
        this.listPemilik = listPemilik;
    }

    public List<HakmilikPihakBerkepentingan> getListPemilik() {
        return listPemilik;
    }

    public List<HakmilikPihakBerkepentingan> getListHP() {
        return listHP;
    }

    public void setListHP(List<HakmilikPihakBerkepentingan> listHP) {
        this.listHP = listHP;
    }

    public List<HakmilikPihakBerkepentingan> getListOtherHP() {
        return listOtherHP;
    }

    public void setListOtherHP(List<HakmilikPihakBerkepentingan> listOtherHP) {
        this.listOtherHP = listOtherHP;
    }

}
