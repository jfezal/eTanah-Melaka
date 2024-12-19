/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodLotDAO;
import etanah.model.Akaun;
import etanah.model.DokumenKewangan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikUrusan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodSeksyen;
import etanah.model.Pengguna;
import etanah.service.RegService;
import etanah.service.common.HakmilikUrusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/hakmilik")
public class HakmilikActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(HakmilikActionBean.class);
    private List<Hakmilik> list;
    private Hakmilik hakmilik;
    private DokumenKewangan dokumenKewangan;
    private Akaun akaun;
    private String idHakmilik;
    private KodDaerah kodDaerah;
    private String daerahPilihan;
    private String daerahPilihan2;
    private String bpm;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodLotDAO kotLotDAO;
    @Inject
    RegService regService;
    @Inject
    HakmilikUrusanService hUService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Map<String, String> mapDaerah;
    private Map<String, String> mapDaerah2;
    private List<KodBandarPekanMukim> senaraiBandarPekanMukim;
    private List<KodBandarPekanMukim> senaraiBandarPekanMukim2;
    private List<KodDaerah> senaraiKodDaerah;
    private List<HakmilikUrusan> senaraiHakmilikUrusan;
    
    private List<HakmilikUrusan> senaraiUrusanRizab;
    
    private boolean showUrusan = false;
    private boolean flagBayar = false;

    @DefaultHandler
    public Resolution showForm() {
        list = Collections.EMPTY_LIST;
        return new ForwardResolution("/WEB-INF/jsp/common/carian_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution searchHakmilik() {
        if (hakmilik != null) {
            Hakmilik hkmilik = hakmilikDAO.findById(hakmilik.getIdHakmilik());
            if (hkmilik != null) {
                list = new LinkedList<Hakmilik>();
                list.add(hkmilik);
            } else {
                list = Collections.EMPTY_LIST;
            }
        } else {
            list = Collections.EMPTY_LIST;
        }
        return new ForwardResolution("/WEB-INF/jsp/common/carian_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution hakmilikDetail() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        senaraiUrusanRizab = new ArrayList<HakmilikUrusan>();
        senaraiUrusanRizab = hUService.findHakmilikUrusanTanahRizab(idHakmilik);        
        
        return new ForwardResolution("/WEB-INF/jsp/common/maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution hakmilikDetail2() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        senaraiHakmilikUrusan = new ArrayList<HakmilikUrusan>();
        senaraiHakmilikUrusan = hUService.findHakmilikUrusanActiveByIdHakmilikAndUrusan(idHakmilik, "ADAT");
        senaraiUrusanRizab = new ArrayList<HakmilikUrusan>();
        senaraiUrusanRizab = hUService.findHakmilikUrusanTanahRizab(idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/common/maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution popupHakmilikDetail() {
        logger.debug("popup popupHakmilikDetail");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idHakmilik = idHakmilik.trim();
        logger.debug("popup idHakmilik :" + idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        senaraiHakmilikUrusan = new ArrayList<HakmilikUrusan>();
        senaraiHakmilikUrusan = hUService.findHakmilikUrusanActiveByIdHakmilikAndUrusan(idHakmilik, "ADAT");
        
       senaraiUrusanRizab = new ArrayList<HakmilikUrusan>();
       senaraiUrusanRizab = hUService.findHakmilikUrusanTanahRizab(idHakmilik);
        
        
//         for (HakmilikUrusan hu : senaraiHakmilikUrusan) {
//            if (!hu.getKodUrusan().getKodPerserahan().getKod().equals("HTIR")) {
                akaun = hakmilik.getAkaunCukai();
                
                if (akaun != null) {
                    if ((akaun.getBaki().compareTo(new BigDecimal(0))) < 1) {
                        String query = "SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.akaun.noAkaun =:noAkaun ORDER BY dk.infoAudit.tarikhMasuk DESC";
                        Query q = sessionProvider.get().createQuery(query);
                        q.setString("noAkaun", akaun.getNoAkaun());
                        dokumenKewangan = (DokumenKewangan) q.list().get(0);
                        if (dokumenKewangan != null) {
                            flagBayar = true;
                        }
                    }
                }
//            }
//        }
         
        return new ForwardResolution("/WEB-INF/jsp/common/maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution carianHakmilikIndependent() {
        Pengguna pengguna = (Pengguna) getContext().getRequest()
                .getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        daerahPilihan = getContext().getRequest().getParameter("daerahPilihan");
        bpm = getContext().getRequest().getParameter("bpm");

        String type = "1";

        if (getContext().getRequest().getParameter("type") != null) {
            type = getContext().getRequest().getParameter("type");
        }

        if (type.equals("2")) {
            daerahPilihan2 = daerahPilihan;
            daerahPilihan = null;
        }

        kodDaerah = pengguna.getKodCawangan().getDaerah();

        senaraiKodDaerah = new ArrayList<KodDaerah>();

        if (kodDaerah == null) {
            senaraiKodDaerah = kodDaerahDAO.findAll();
        } else {
            senaraiKodDaerah.add(kodDaerah);
            // utk user GEMAS ; simulasi 11-Nov-2014 START
            if(kodDaerah.getKod().equals("08")){                    
                KodDaerah k = kodDaerahDAO.findById("06");
                senaraiKodDaerah.add(k);
            }
            // END
        }
        mapDaerah = new LinkedHashMap<String, String>();

        mapDaerah2 = new LinkedHashMap<String, String>();

        for (KodDaerah kd : senaraiKodDaerah) {
            if (kd == null || kd.getKod() == null || kd.getNama() == null) {
                continue;
            }
            mapDaerah.put(kd.getKod(), kd.getNama());
            mapDaerah2.put(kd.getKod(), kd.getNama());
        }

        if (hakmilik == null) {
//            KodLot kl = kotLotDAO.findById("1");
            hakmilik = new Hakmilik();
//            hakmilik.setLot(kl);//by default should be lot
//            if(senaraiKodDaerah.size() == 1) {
//                KodDaerah kd = kodDaerahDAO.findById(senaraiKodDaerah.get(0).getKod());
//                hakmilik.setDaerah(kd);
//            }
            if (StringUtils.isNotBlank(daerahPilihan)) {
                KodDaerah kd = kodDaerahDAO.findById(daerahPilihan);
                hakmilik.setDaerah(kd);
            } else if (StringUtils.isNotBlank(daerahPilihan2)) {
                KodDaerah kd = kodDaerahDAO.findById(daerahPilihan2);
                hakmilik.setDaerah(kd);
            }
        }

        if (StringUtils.isNotBlank(bpm)) {
//            KodBandarPekanMukim kbpm = regService.cariBPM(bpm, daerahPilihan);
            KodBandarPekanMukim kbpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(bpm));
            hakmilik.setBandarPekanMukim(kbpm);
        }

        return new JSP("common/carian_hakmilik_independent.jsp").addParameter("popup", "true");
    }

    public Resolution carianHakmilikPopup() {
        String result = "0";

        try {

            KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
            kbpm = regService.cariBPM(String.valueOf(hakmilik.getBandarPekanMukim().getKod()), hakmilik.getDaerah().getKod());

            NumberFormat formatter = new DecimalFormat("0000000");
            String noLot = null;
            String kodLot = null;
            String kodHakmilik = null;
            String noHakmilik = null;
            String kodSeksyen = null;

            if (hakmilik.getLot() != null) {
                kodLot = hakmilik.getLot().getKod();
            }
            if (hakmilik.getKodHakmilik() != null) {
                kodHakmilik = hakmilik.getKodHakmilik().getKod();
            }
            if (StringUtils.isNotBlank(hakmilik.getNoHakmilik())) {
                noHakmilik = hakmilik.getNoHakmilik();
                logger.info("Before Add 0 LeftPad no_hakmilik = " + noHakmilik);
                noHakmilik = StringUtils.leftPad(noHakmilik, 8, '0');//Automatik Masukkan 0 didepan noHakmilik. 2013-Mei-11.
                logger.info("After Add 0 LeftPad no_hakmilik = " + noHakmilik);
            }
            if (hakmilik.getSeksyen() != null) {
                kodSeksyen = String.valueOf(hakmilik.getSeksyen().getKod());
            }

            if (StringUtils.isNotBlank(hakmilik.getNoLot())) {
                noLot = formatter.format(Integer.parseInt(hakmilik.getNoLot()));
            }
            if (hakmilik.getBandarPekanMukim() != null) {
                kbpm = hakmilik.getBandarPekanMukim();
            }

            logger.info("daerah = " + hakmilik.getDaerah().getKod());
            logger.info("bandar pekan mukim = " + kbpm.getKod());
            logger.info("no lot = " + noLot);
            logger.info("kod lot = " + kodLot);
            logger.info("kod hakmilik = " + kodHakmilik);
            logger.info("no hakmilik = " + noHakmilik);
            logger.info("kod seksyen = " + kodSeksyen);

            hakmilik = regService.searchHakmilik(kbpm.getKod(), hakmilik.getDaerah().getKod(), kodLot,
                    noLot, kodHakmilik, noHakmilik, kodSeksyen);

            if (hakmilik != null) {
                result = hakmilik.getIdHakmilik();
            }

        } catch (Exception ex) {
            logger.error(ex);
        }

        return new StreamingResolution("text/plain", result);
    }

    public Resolution carianHakmilikStrata() {
        Pengguna pengguna = (Pengguna) getContext().getRequest()
                .getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        daerahPilihan = getContext().getRequest().getParameter("daerahPilihan");
        bpm = getContext().getRequest().getParameter("bpm");

        String type = "1";

        if (getContext().getRequest().getParameter("type") != null) {
            type = getContext().getRequest().getParameter("type");
        }

        if (type.equals("2")) {
            daerahPilihan2 = daerahPilihan;
            daerahPilihan = null;
        }

        kodDaerah = pengguna.getKodCawangan().getDaerah();

        senaraiKodDaerah = new ArrayList<KodDaerah>();

        if (kodDaerah == null) {
            senaraiKodDaerah = kodDaerahDAO.findAll();
        } else {
            senaraiKodDaerah.add(kodDaerah);
        }
        mapDaerah = new LinkedHashMap<String, String>();

        mapDaerah2 = new LinkedHashMap<String, String>();

        for (KodDaerah kd : senaraiKodDaerah) {
            if (kd == null || kd.getKod() == null || kd.getNama() == null) {
                continue;
            }
            mapDaerah.put(kd.getKod(), kd.getNama());
            mapDaerah2.put(kd.getKod(), kd.getNama());
        }

        if (hakmilik == null) {
//            KodLot kl = kotLotDAO.findById("1");
            hakmilik = new Hakmilik();
//            hakmilik.setLot(kl);//by default should be lot
//            if(senaraiKodDaerah.size() == 1) {
//                KodDaerah kd = kodDaerahDAO.findById(senaraiKodDaerah.get(0).getKod());
//                hakmilik.setDaerah(kd);
//            }
            if (StringUtils.isNotBlank(daerahPilihan)) {
                KodDaerah kd = kodDaerahDAO.findById(daerahPilihan);
                hakmilik.setDaerah(kd);
            } else if (StringUtils.isNotBlank(daerahPilihan2)) {
                KodDaerah kd = kodDaerahDAO.findById(daerahPilihan2);
                hakmilik.setDaerah(kd);
            }
        }

        if (StringUtils.isNotBlank(bpm)) {
//            KodBandarPekanMukim kbpm = regService.cariBPM(bpm, daerahPilihan);
            KodBandarPekanMukim kbpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(bpm));
            hakmilik.setBandarPekanMukim(kbpm);
        }

        return new JSP("common/carian_hakmilik_strata.jsp").addParameter("popup", "true");
    }
    
    public Resolution carianHakmilikStrataPopup() {
        String result = "0";

        try {

            KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
            kbpm = regService.cariBPM(String.valueOf(hakmilik.getBandarPekanMukim().getKod()), hakmilik.getDaerah().getKod());

            NumberFormat formatter = new DecimalFormat("0000000");
            String noLot = null;
            String kodLot = null;
            String kodHakmilik = null;
            String noHakmilik = null;
            String kodSeksyen = null;
            String noBangunan = null;
            String noTingkat = null;
            String noPetak = null;

            if (hakmilik.getLot() != null) {
                kodLot = hakmilik.getLot().getKod();
            }
            if (hakmilik.getKodHakmilik() != null) {
                kodHakmilik = hakmilik.getKodHakmilik().getKod();
            }
            if (StringUtils.isNotBlank(hakmilik.getNoHakmilik())) {
                noHakmilik = hakmilik.getNoHakmilik();
                logger.info("Before Add 0 LeftPad no_hakmilik = " + noHakmilik);
                noHakmilik = StringUtils.leftPad(noHakmilik, 8, '0');//Automatik Masukkan 0 didepan noHakmilik. 2013-Mei-11.
                logger.info("After Add 0 LeftPad no_hakmilik = " + noHakmilik);
            }
            if (hakmilik.getSeksyen() != null) {
                kodSeksyen = String.valueOf(hakmilik.getSeksyen().getKod());
            }

            if (StringUtils.isNotBlank(hakmilik.getNoLot())) {
                noLot = formatter.format(Integer.parseInt(hakmilik.getNoLot()));
            }
            if (hakmilik.getBandarPekanMukim() != null) {
                kbpm = hakmilik.getBandarPekanMukim();
            }
            if(hakmilik.getNoBangunan() != null){
                noBangunan = hakmilik.getNoBangunan();
            }
            if(hakmilik.getNoTingkat() != null){
                noTingkat = hakmilik.getNoTingkat();
            }
            if(hakmilik.getNoPetak() != null){
                noPetak = hakmilik.getNoPetak();
            }

            logger.info("daerah = " + hakmilik.getDaerah().getKod());
            logger.info("bandar pekan mukim = " + kbpm.getKod());
            logger.info("no lot = " + noLot);
            logger.info("kod lot = " + kodLot);
            logger.info("kod hakmilik = " + kodHakmilik);
            logger.info("no hakmilik = " + noHakmilik);
            logger.info("kod seksyen = " + kodSeksyen);
            logger.info("noBangunan = " + noBangunan);
            logger.info("noTingkat = " + noTingkat);
            logger.info("noPetak = " + noPetak);

            hakmilik = regService.searchHakmilikStrata(kbpm.getKod(), hakmilik.getDaerah().getKod(), kodLot,
                    noLot, kodHakmilik, noHakmilik, kodSeksyen, noBangunan, noTingkat, noPetak);

            if (hakmilik != null) {
                result = hakmilik.getIdHakmilik();
            }

        } catch (Exception ex) {
            logger.error(ex);
        }

        return new StreamingResolution("text/plain", result);
    }
    
    public Resolution cariHakmilik() {

        String result = "0";
        try {
            if (hakmilik.getKodHakmilik() != null
                    && StringUtils.isNotBlank(hakmilik.getNoHakmilik())) {

                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
//                kbpm = kodBandarPekanMukimDAO.findById(hakmilik.getBandarPekanMukim().getKod());
                kbpm = regService.cariBPM(String.valueOf(hakmilik.getBandarPekanMukim().getKod()), hakmilik.getDaerah().getKod());

                Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                String daerah = "";
                if (pengguna.getKodCawangan().getDaerah() != null) {
                    daerah = pengguna.getKodCawangan().getDaerah().getKod();
                }

                String kodHakmilik = "";
                if (hakmilik.getKodHakmilik() != null) {
                    kodHakmilik = hakmilik.getKodHakmilik().getKod();
                }

                NumberFormat formatter = new DecimalFormat("00000000");
                String formattednolot = formatter.format(Integer.parseInt(hakmilik.getNoHakmilik()));

                logger.debug("kod KBPM = " + kbpm.getKod());
                logger.debug("kod daerah = " + hakmilik.getDaerah().getKod());
                logger.debug("kod hakmilik = " + kodHakmilik);
                logger.debug("no hakmilik = " + formattednolot);

                hakmilik = regService.searchHakmilik(kbpm.getKod(), hakmilik.getDaerah().getKod(),
                        null, null, kodHakmilik, formattednolot, null);
                if (hakmilik != null) {
                    result = hakmilik.getIdHakmilik();
                }
            } else {
                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
//                kbpm = kodBandarPekanMukimDAO.findById(hakmilik.getBandarPekanMukim().getKod());
                kbpm = regService.cariBPM(String.valueOf(hakmilik.getBandarPekanMukim().getKod()), hakmilik.getDaerah().getKod());
                String kodHakmilik = "";
                if (hakmilik.getKodHakmilik() != null) {
                    kodHakmilik = hakmilik.getKodHakmilik().getKod();
                }
                logger.debug("kod KBPM:" + kbpm.getKod());
                logger.debug("kod daerah:" + hakmilik.getDaerah().getKod());
                logger.debug("kod LOT:" + hakmilik.getLot().getKod());
                logger.debug("NO LOT:" + hakmilik.getNoLot());
                logger.debug("Kod Hakmilik: " + kodHakmilik);

                NumberFormat formatter = new DecimalFormat("0000000");
                String formattednolot = formatter.format(Integer.parseInt(hakmilik.getNoLot()));

                hakmilik = regService.searchHakmilik(kbpm.getKod(), hakmilik.getDaerah().getKod(),
                        hakmilik.getLot().getKod(), formattednolot, null, null, null);
                if (hakmilik != null) {
                    result = hakmilik.getIdHakmilik();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResolution(404);
        }

        return new StreamingResolution("text/plain", result);
    }

    public Resolution reload() {
        return new JSP("common/carian_hakmilik_independent.jsp").addParameter("popup", "true");
    }

    public List<HakmilikUrusan> getSenaraiHakmilikUrusan() {
        return senaraiHakmilikUrusan;
    }

    public void setSenaraiHakmilikUrusan(List<HakmilikUrusan> senaraiHakmilikUrusan) {
        this.senaraiHakmilikUrusan = senaraiHakmilikUrusan;
    }

    public List<HakmilikUrusan> getSenaraiUrusanRizab() {
        return senaraiUrusanRizab;
    }

    public void setSenaraiUrusanRizab(List<HakmilikUrusan> senaraiUrusanRizab) {
        this.senaraiUrusanRizab = senaraiUrusanRizab;
    }
    
    public boolean isShowUrusan() {
        return showUrusan;
    }

    public void setShowUrusan(boolean showUrusan) {
        this.showUrusan = showUrusan;
    }

    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Map<String, String> getMapDaerah() {
        return mapDaerah;
    }

    public void setMapDaerah(Map<String, String> mapDaerah) {
        this.mapDaerah = mapDaerah;
    }

    public Map<String, String> getMapDaerah2() {
        return mapDaerah2;
    }

    public void setMapDaerah2(Map<String, String> mapDaerah2) {
        this.mapDaerah2 = mapDaerah2;
    }

    public boolean isFlagBayar() {
        return flagBayar;
    }

    public void setFlagBayar(boolean flagBayar) {
        this.flagBayar = flagBayar;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public List<KodBandarPekanMukim> getSenaraiBandarPekanMukim() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (StringUtils.isNotBlank(daerahPilihan)) {
            if ((pengguna.getKodCawangan().getKod().equals("08")) && (daerahPilihan.equals("06"))) {
                String query = "SELECT u FROM etanah.model.KodBandarPekanMukim u WHERE u.kod =:kod1 OR u.kod =:kod2 OR u.kod =:kod3 OR "
                        + "u.kod =:kod4 OR u.kod =:kod5 OR u.kod =:kod6";
                Query q = sessionProvider.get().createQuery(query);
                    q.setInteger("kod1", 131);
                    q.setInteger("kod2", 132);
                    q.setInteger("kod3", 138);
                    q.setInteger("kod4", 141);
                    q.setInteger("kod5", 145);
                    q.setInteger("kod6", 146);
                return q.list();
            }else
                return regService.getSenaraiKodBPMByDaerah(daerahPilihan);
        }
        return new ArrayList<KodBandarPekanMukim>();
    }

    public void setSenaraiBandarPekanMukim(List<KodBandarPekanMukim> senaraiBandarPekanMukim) {
        this.senaraiBandarPekanMukim = senaraiBandarPekanMukim;
    }

    public List<KodBandarPekanMukim> getSenaraiBandarPekanMukim2() {
//        return senaraiBandarPekanMukim2;
        if (StringUtils.isNotBlank(daerahPilihan2)) {
            return regService.getSenaraiKodBPMByDaerah(daerahPilihan2);
        }
        return new ArrayList<KodBandarPekanMukim>();
    }

    public List<KodSeksyen> getSenaraiSeksyen() {
        logger.debug("senarai seksyen");

        if (hakmilik != null && hakmilik.getBandarPekanMukim() != null
                && hakmilik.getDaerah() != null) {
            return regService.getSenaraiKodSeksyenByBPM(hakmilik.getBandarPekanMukim().getbandarPekanMukim(), hakmilik.getDaerah().getKod());
        }
        return new ArrayList<KodSeksyen>();
    }

    public void setSenaraiBandarPekanMukim2(List<KodBandarPekanMukim> senaraiBandarPekanMukim2) {
        this.senaraiBandarPekanMukim2 = senaraiBandarPekanMukim2;
    }
}
