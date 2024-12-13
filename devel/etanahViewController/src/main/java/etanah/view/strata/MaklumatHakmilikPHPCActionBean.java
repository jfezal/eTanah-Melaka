/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

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
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
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
@UrlBinding("/strata/maklumat_hakmilik_phpc")
public class MaklumatHakmilikPHPCActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatHakmilikPHPCActionBean.class);
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
    //added
    private List<HakmilikPermohonan> hakmilikPermohonanListtemp;
    private List<HakmilikPermohonan> hakmilikPermohonanKemaskini;
    private List<HakmilikPermohonan> hakmilikPermohonanSebelumList;
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
    private String jarakDari;
    private String lokasiTanah;
    private String kBpm;
    private String bpm;
    private List<HakmilikPihakBerkepentingan> listHP;
    private List<HakmilikPihakBerkepentingan> listOtherHP;
    private List<HakmilikPermohonan> hakmilikPermohonanListBaru;
    private String kodNegeri1;
    private List<HakmilikPermohonan> listHakmilikP = new ArrayList();
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

    public Resolution showPemilik() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        listPemilik = hakmilikPihakKepentinganService.findListHakmilikByIDHakmilik(idHakmilik);
        getContext().getRequest().setAttribute("showHakmilik", true);
        getContext().getRequest().setAttribute("showPemilik", true);
        // rehydrate();
        return new JSP("strata/pecahPetak/maklumathakmilikPHPC.jsp").addParameter("tab", "true");
    }

    public Resolution showHakmilik() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().setAttribute("showHakmilik", true);
        //    rehydrate();
        return new JSP("strata/pecahPetak/maklumathakmilikPHPC.jsp").addParameter("tab", "true");
    }

    @DefaultHandler
    public Resolution senaraiHakmilik() {
        getContext().getRequest().setAttribute("showPemilik", false);
        getContext().getRequest().setAttribute("showHakmilik", false);
        return new JSP("strata/pecahPetak/maklumathakmilikPHPC.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatHakmilikAsal() {
        getContext().getRequest().setAttribute("showPemilik", false);
        getContext().getRequest().setAttribute("showHakmilik", false);
        return new JSP("strata/pecahPetak/PHPC_hakmilik_asal.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetail() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/strata/pecahPetak/maklumatHakmilikPHPC_popup.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!deleteHakmilik,!generateIDHakmilikStrata"})
    public void rehydrate() {
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        p = permohonanDAO.findById(idPermohonan);
//        hakmilikPermohonanListtemp = strPtService.findListHakmilikByIdMohon(p.getPermohonanSebelum().getIdPermohonan());
//        for(int i = 0; i<hakmilikPermohonanListtemp.size(); i++){
//            HakmilikPermohonan hmMohon = hakmilikPermohonanListtemp.get(i);
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
                    hakmilikPermohonanList = Collections.emptyList();
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
                    strKodUOM[counter] = hp.getHakmilik().getKodUnitLuas().getKod();
                    if (hp.getHakmilik().getSyaratNyata() != null) {
                        KodSyaratNyata ksn = regService.searchKodSyaratByCaw(hp.getHakmilik().getSyaratNyata().getKod(), hp.getHakmilik().getCawangan().getKod());
//                        strKodSyaratNyata[counter] = etanah.util.StringUtils.removeLeadingZeros(ksn.getKodSyarat());
                        if (ksn != null) {
                            strKodSyaratNyata[counter] = ksn.getKodSyarat();
                        } else {
                            strKodSyaratNyata[counter] = "";
                        }
                    }
                    if (hp.getHakmilik().getSekatanKepentingan() != null) {
                        KodSekatanKepentingan ksk = regService.searchKodSekatanByCaw(hp.getHakmilik().getSekatanKepentingan().getKod(), hp.getHakmilik().getCawangan().getKod());
//                        if (!hp.getHakmilik().getSekatanKepentingan().getKodSekatan().equals("0000000")) {
//                            strKodSekatan[counter] = etanah.util.StringUtils.removeLeadingZeros(ksk.getKodSekatan());
//                        } else {
                        if (ksk != null) {
                            strKodSekatan[counter] = ksk.getKodSekatan();
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

                List<HakmilikUrusan> hakmilikUrusan = new ArrayList<HakmilikUrusan>();
                urusanList = new ArrayList<HakmilikUrusan>();
                for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                    if (hp == null || hp.getHakmilik() == null) {
                        continue;
                    }
                    idHakmilik = hp.getHakmilik().getIdHakmilik();
                    hakmilikUrusan = hakmilikUrusanService.findHakmilikUrusanByIdHakmilik(idHakmilik);
                    for (HakmilikUrusan hu : hakmilikUrusan) {
                        if (hu == null) {
                            continue;
                        }
                        urusanList.add(hu);
                    }
                }
//                idHakmilik = hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilik();
//                urusanList = hakmilikUrusanService.findHakmilikUrusanByIdHakmilik(idHakmilik);  
            }
//      -------- add by azri: show list of hakmilik berkepentingan in "Maklumat Hakmilik" tab
            try {
                List<String> listIDHakmilik = new ArrayList<String>();
                for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                    List<HakmilikPihakBerkepentingan> listHP1 = regService.getPemilikOnlyPMPP(hp.getHakmilik().getIdHakmilik());
                    List<HakmilikPihakBerkepentingan> listOtherHP1 = regService.getPihakNotPemilikPPPM(hp.getHakmilik().getIdHakmilik());
                    for (HakmilikPihakBerkepentingan hp1 : listHP1) {
                        listHP.add(hp1);
                    }
                    for (HakmilikPihakBerkepentingan hp2 : listOtherHP1) {
                        listOtherHP.add(hp2);
                    }
//                    listIDHakmilik.add(hp.getHakmilik().getIdHakmilik());

                }
                LOG.info("listIDHakmilik : " + listIDHakmilik.size());

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

                for (HakmilikPihakBerkepentingan hp : listOtherHP) {
                    Long id = hp.getIdHakmilikPihakBerkepentingan();
                    if (pihakMap.containsKey(id)) {
                        continue;
                    } else {
                        pihakMap.put(id, hp);
                    }
                }
                listPemilik = new ArrayList(pemilikMap.values());
                listHakmilikPihak = new ArrayList(pihakMap.values());
                LOG.info("------>  list Hakmilik Pihak : " + listPemilik.size());
                LOG.info("------>  list Hakmilik Pihak : " + listHakmilikPihak.size());
                if (!listPemilik.isEmpty()) {
                    Collections.sort(listPemilik, HAKMILIK_ORDER1);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                LOG.error(ex);
            }//      ----- add by azri: END
        }
        if (!hakmilikPermohonanList.isEmpty()) {
            Collections.sort(hakmilikPermohonanList, HAKMILIK_ORDER);
        }
        String idPermohonan1 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        System.out.println("----idPermohonan----" + idPermohonan1);

        if (idPermohonan1 != null) {
            p = permohonanDAO.findById(idPermohonan1);
            setListHakmilikP(strPtService.getMaklumatTan(idPermohonan1));

            if (p.getSenaraiHakmilik().size() != 0) {
                System.out.println("----Hakmilik----:" + p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());

                if (p.getKodUrusan().getKod().equals("HT")) {
                    System.out.println("----kod urusan HT----");
                    listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod((p.getPermohonanSebelum().getSenaraiHakmilik().get(0)).getHakmilik(), "PM");
                    System.out.println("----listHakmilikPihak----" + listHakmilikPihak.size());
                } else {
                    System.out.println("----kod urusan Not HT----");
                    listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod((p.getSenaraiHakmilik().get(0)).getHakmilik(), "PM");
                    System.out.println("----listHakmilikPihak----" + listHakmilikPihak.size());
                }

                System.out.println("----listHakmilikPihak------:" + listHakmilikPihak.size());
                idHakmilik = p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                hakmilik = hakmilikDAO.findById(idHakmilik);

                /*to make textformation */
                String kBpm1 = hakmilik.getDaerah().getNama().toLowerCase();
                int kBpm2 = kBpm1.length();
                setkBpm(kBpm1.substring(0, 1).toUpperCase().concat(kBpm1.substring(1, kBpm2)));
                System.out.println("----kBpm------" + getkBpm());

                String kBpm3 = hakmilik.getBandarPekanMukim().getNama().toLowerCase();
                int kBpm4 = kBpm3.length();
                setBpm(kBpm3.substring(0, 1).toUpperCase().concat(kBpm3.substring(1, kBpm4)));
                System.out.println("----kBpm--1----" + getBpm());

            }

//            System.out.println("----hakmilikPermohonan------"+hakmilikPermohonan);
//            System.out.println("----hakmilikPermohonan------"+hakmilikPermohonan.getNoLot());
        }

        // Untuk perihal tanah ruang udara.  Check maklumat jarakDari & lokasiTanah.
        if (!listHakmilikP.isEmpty() && getListHakmilikP() != null) {
            HakmilikPermohonan hp = getListHakmilikP().get(0);

            if (hp != null) {
                setJarakDari(hp.getJarakDari());
                setLokasiTanah(hp.getLokasi());

            }
        }

        setKodNegeri1(conf.getProperty("kodNegeri"));
        System.out.println("----kodNegeri1----" + getKodNegeri1());

//            
//        }
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

    /**
     * @return the jarakDari
     */
    public String getJarakDari() {
        return jarakDari;
    }

    /**
     * @param jarakDari the jarakDari to set
     */
    public void setJarakDari(String jarakDari) {
        this.jarakDari = jarakDari;
    }

    /**
     * @return the lokasiTanah
     */
    public String getLokasiTanah() {
        return lokasiTanah;
    }

    /**
     * @param lokasiTanah the lokasiTanah to set
     */
    public void setLokasiTanah(String lokasiTanah) {
        this.lokasiTanah = lokasiTanah;
    }

    /**
     * @return the kBpm
     */
    public String getkBpm() {
        return kBpm;
    }

    /**
     * @param kBpm the kBpm to set
     */
    public void setkBpm(String kBpm) {
        this.kBpm = kBpm;
    }

    /**
     * @return the bpm
     */
    public String getBpm() {
        return bpm;
    }

    /**
     * @param bpm the bpm to set
     */
    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    /**
     * @return the hakmilikPermohonanListBaru
     */
    public List<HakmilikPermohonan> getHakmilikPermohonanListBaru() {
        return hakmilikPermohonanListBaru;
    }

    /**
     * @param hakmilikPermohonanListBaru the hakmilikPermohonanListBaru to set
     */
    public void setHakmilikPermohonanListBaru(List<HakmilikPermohonan> hakmilikPermohonanListBaru) {
        this.hakmilikPermohonanListBaru = hakmilikPermohonanListBaru;
    }

    /**
     * @return the kodNegeri1
     */
    public String getKodNegeri1() {
        return kodNegeri1;
    }

    /**
     * @param kodNegeri1 the kodNegeri1 to set
     */
    public void setKodNegeri1(String kodNegeri1) {
        this.kodNegeri1 = kodNegeri1;
    }

    /**
     * @return the listHakmilikP
     */
    public List<HakmilikPermohonan> getListHakmilikP() {
        return listHakmilikP;
    }

    /**
     * @param listHakmilikP the listHakmilikP to set
     */
    public void setListHakmilikP(List<HakmilikPermohonan> listHakmilikP) {
        this.listHakmilikP = listHakmilikP;
    }
}
