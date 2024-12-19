package etanah.view.utility;

import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import able.stripes.*;
import org.hibernate.*;
import com.google.inject.*;
import etanah.Configuration;
import etanah.service.RegService;
import java.text.NumberFormat;
import net.sourceforge.stripes.action.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 * @modified by haqqiem
 *
 */

@UrlBinding("/kiosk/tukarganti")
public class PertanyaanStatusTukarGantiActionBean extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(PertanyaanStatusTukarGantiActionBean.class);

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    HakmilikDAO hakmilikDAO;
    String idHakmilikR;
    String msg;
    private List<UtilKod> listKodDaerah;
    private List<UtilKod> listBPM;
    private List<UtilKod> listKodSeksyen;
    private List<UtilKod> listJenisHakmilik;
    private String daerah;
    private String mukim;
    private String seksyen;
    private String jenishakmilik;
    private String idHakmilik;
    private String idPerserahan = "-";

    private List<Hakmilik> senaraiHakmilikBerikut;
    private List<HakmilikAsal> listHakmilikAsal = new ArrayList();
    private List<HakmilikSebelum> listHakmilikSebelum = new ArrayList();
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    RegService regService;

    @DefaultHandler
    public Resolution showForm() {
        listKodDaerah = setList(kodDaerahDAO.findAll(), "daerah");
        listBPM = setList(kodBandarPekanMukimDAO.findAll(), "bpm");
        listKodSeksyen = setList(kodSeksyenDAO.findAll(), "seksyen");
        listJenisHakmilik = setList(kodHakmilikDAO.findAll(), "kodhakmilik");

        return new JSP("utiliti/pertanyaan_status_tkr_ganti.jsp");
    }

    public Resolution kembali() {
        return new RedirectResolution(PertanyaanStatusTukarGantiActionBean.class);
    }

    public Resolution carianHakmilik() {
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
        if (hakmilik != null) {
            if (hakmilik.getKodStatusHakmilik().getKod().equals("D")) {
                if (hakmilik.getNoVersiDhde() != 0) {
                    idHakmilikR = hakmilik.getIdHakmilik();
                    msg = "Hakmilik telah dilakukan proses tukar ganti";
                } else {
                    idHakmilikR = hakmilik.getIdHakmilik();
                    String sql = null;
                    Session s = sessionProvider.get();
                    Query q = null;

                    sql = "SELECT h FROM etanah.model.HakmilikPermohonan h where h.hakmilik.idHakmilik = :idHakmilik and h.permohonan.kodUrusan.kod in('HKTHK','HSTHK') "
                            + "and h.permohonan.status != 'SL'";
                    q = s.createQuery(sql);
                    q.setString("idHakmilik", idHakmilikR);
                    System.out.println("q.list().size()  " + q.list().size());
                    if (q != null && !q.list().isEmpty()) {
                        msg = "Hakmilik ini sedang dalam proses tukar ganti";
                        HakmilikPermohonan hp = (HakmilikPermohonan) q.list().get(0);
                        idPerserahan = hp.getPermohonan().getIdPermohonan();
                    } else {
                        msg = "Hakmilik masih belum dilakukan proses tukar ganti";
                    }
                }
            } else {
                idHakmilikR = hakmilik.getIdHakmilik();
                msg = "Hakmilik ini telah batal";
                senaraiHakmilikBerikut = getHakmilikSambungan(idHakmilikR);
            }
        } else {
            msg = "Maklumat tidak dijumpai. Sila isi semula.";
        }
        return new JSP("utiliti/paparan_status_tukar_ganti.jsp");
    }

    public Resolution carianHakmilikdetail() {
        String daerah = getContext().getRequest().getParameter("daerah");
        String mukim = getContext().getRequest().getParameter("mukim");
        KodBandarPekanMukim bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(mukim));
        String jenishakmilik = getContext().getRequest().getParameter("jenishakmilik");
        String seksyen = getContext().getRequest().getParameter("seksyen");

        String noHakmilik = getContext().getRequest().getParameter("noHakmilik").trim();
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(8);
        df.setMinimumIntegerDigits(8);
        df.setGroupingUsed(false);
        if (StringUtils.isNotBlank(noHakmilik)) {
//            String idHakmilik1 = conf.getKodNegeri() + daerah + bpm.getbandarPekanMukim() + jenishakmilik + df.format(Integer.parseInt(noHakmilik));
            Hakmilik hakmilik = hakmilikDetail(daerah, String.valueOf(bpm.getKod()), jenishakmilik, df.format(Integer.parseInt(noHakmilik)), seksyen);
            if (hakmilik != null) {
                if (hakmilik.getKodStatusHakmilik().getKod().equals("D")) {
                    if (hakmilik.getNoVersiDhde() != 0) {
                        idHakmilikR = hakmilik.getIdHakmilik();
                        msg = "Hakmilik ini telah dilakukan proses tukar ganti";
                    } else {
                        String sql = null;
                        Session s = sessionProvider.get();
                        Query q = null;

                        sql = "SELECT h FROM etanah.model.HakmilikPermohonan h where h.hakmilik.idHakmilik = :idHakmilik and h.permohonan.kodUrusan.kod in('HKTHK','HSTHK') "
                                + "and h.permohonan.status not in ('SL','TK')";
                        q = s.createQuery(sql);
                        q.setString("idHakmilik", hakmilik.getIdHakmilik());
                        System.out.println("q.list() : " + q.list().size());
                        if (q != null && !q.list().isEmpty()) {
                            idHakmilikR = hakmilik.getIdHakmilik();
                            msg = "Hakmilik ini sedang dalam proses tukar ganti";
                            HakmilikPermohonan hp = (HakmilikPermohonan) q.list().get(0);
                            idPerserahan = hp.getPermohonan().getIdPermohonan();
                        } else {

                            idHakmilikR = hakmilik.getIdHakmilik();
                            msg = "Hakmilik ini masih belum dilakukan proses tukar ganti";
                        }
                    }
                } else {
                    idHakmilikR = hakmilik.getIdHakmilik();
                    msg = "Hakmilik ini telah batal";
                    senaraiHakmilikBerikut = getHakmilikSambungan(idHakmilikR);
                }
            } else {
                msg = "Hakmilik tidak dijumpai.";
            }
        } else {
            msg = "Sila masukkan nombor hakmilik.";
        }

        return new JSP("utiliti/paparan_status_tukar_ganti.jsp");
    }

    public Hakmilik hakmilikDetail(String daerah, String bpm, String jenishakmilik, String nohakmilik, String sekysen) {
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;

        sql = "SELECT h FROM etanah.model.Hakmilik h where h.daerah.kod = :daerah "
                + "and h.bandarPekanMukim.kod = :bpm "
                + "and h.kodHakmilik.kod = :jenishakmilik "
                + "and h.noHakmilik = :nohakmilik ";

        if (!sekysen.equals("")) {
            sql += "AND h.seksyen.kod = :seksyen ";
        }
        q = s.createQuery(sql);
        q.setString("daerah", daerah);
        q.setString("nohakmilik", nohakmilik);
        q.setString("jenishakmilik", jenishakmilik);
        q.setString("bpm", bpm);
        if (!sekysen.equals("")) {
            q.setString("seksyen", sekysen);
        }

        return (Hakmilik) q.uniqueResult();

    }

    public Resolution filterSeksyen() {
        mukim = (String) getContext().getRequest().getParameter("mukim");
        daerah = (String) getContext().getRequest().getParameter("daerah");
        listKodDaerah = setList(kodDaerahDAO.findAll(), "daerah");

        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (mukim == null || mukim.equals("")) {
            sql = "select seks from KodSeksyen seks where seks.aktif = 'Y'";
            q = s.createQuery(sql);
        } else {
            sql = "select seks from KodSeksyen seks where seks.kodBandarPekanMukim.kod = :kod and seks.aktif='Y' order by seks.seksyen";
            q = s.createQuery(sql);
            q.setString("kod", mukim);
        }
        listKodSeksyen = setList(q.list(), "seksyen");
        daerah = (String) getContext().getRequest().getParameter("daerah");
        if (daerah == null || daerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.aktif ='Y'";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod and bpm.aktif ='Y'";
            q = s.createQuery(sql);
            q.setString("kod", daerah);
        }
        listBPM = setList(q.list(), "bpm");
        listJenisHakmilik = setList(kodHakmilikDAO.findAll(), "kodhakmilik");
        return new JSP("utiliti/pertanyaan_status_tkr_ganti.jsp");
    }

    public Resolution filterMukim() {
        daerah = (String) getContext().getRequest().getParameter("daerah");
        listKodDaerah = setList(kodDaerahDAO.findAll(), "daerah");
//        listKodSeksyen = setList(kodSeksyenDAO.findAll(), "seksyen");
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (daerah == null || daerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.aktif ='Y'";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod and bpm.aktif ='Y'";
            q = s.createQuery(sql);
            q.setString("kod", daerah);
        }
        listBPM = setList(q.list(), "bpm");
        listKodSeksyen = setList(kodSeksyenDAO.findAll(), "seksyen");
        listJenisHakmilik = setList(kodHakmilikDAO.findAll(), "kodhakmilik");
        return new JSP("utiliti/pertanyaan_status_tkr_ganti.jsp");
    }

    public List<UtilKod> setList(List o, String type) {
        List<UtilKod> listUtil = new ArrayList<UtilKod>();
        if (type.equals("daerah")) {
            listUtil = new ArrayList<UtilKod>();
            for (int i = 0; i < o.size(); i++) {
                UtilKod util = new UtilKod();
                KodDaerah daerah = (KodDaerah) o.get(i);
                if (daerah.getNama() != null) {
                    util.setKod(daerah.getKod());
                    util.setNama(daerah.getKod() + "-" + daerah.getNama());
                    listUtil.add(util);
                }
            }
        }
        if (type.equals("bpm")) {
            listUtil = new ArrayList<UtilKod>();
            for (int i = 0; i < o.size(); i++) {
                UtilKod util = new UtilKod();
                KodBandarPekanMukim bpm = (KodBandarPekanMukim) o.get(i);
                util.setKod(String.valueOf(bpm.getKod()));
                util.setNama(bpm.getbandarPekanMukim() + "-" + bpm.getNama());
                listUtil.add(util);
            }
        }
        if (type.equals("seksyen")) {
            listUtil = new ArrayList<UtilKod>();
            for (int i = 0; i < o.size(); i++) {
                UtilKod util = new UtilKod();
                KodSeksyen seks = (KodSeksyen) o.get(i);
                util.setKod(String.valueOf(seks.getKod()));
                util.setNama(seks.getSeksyen() + "-" + seks.getNama());
                listUtil.add(util);
            }
        }
        if (type.equals("kodhakmilik")) {
            listUtil = new ArrayList<UtilKod>();
            for (int i = 0; i < o.size(); i++) {
                UtilKod util = new UtilKod();
                KodHakmilik kodhak = (KodHakmilik) o.get(i);
                if (kodhak.getAktif() == 'Y') {
                    util.setKod(String.valueOf(kodhak.getKod()));
                    util.setNama(kodhak.getKod() + "-" + kodhak.getNama());
                    listUtil.add(util);
                }
            }
        }

        return listUtil;
    }

    public List<Hakmilik> getHakmilikSambungan(String id) {
        System.out.println("id : "+id);
        List<Hakmilik> list = new ArrayList<Hakmilik>();

        listHakmilikAsal = regService.cariHakmilikAsal(id);
        listHakmilikSebelum = regService.cariHakmilikSebelum(id);
        logger.debug("listHakmilikAsal : " + listHakmilikAsal.size());
        if (listHakmilikAsal.size() > 0) {
            for (int k = 0; k < listHakmilikAsal.size(); k++) {
                list.add(listHakmilikAsal.get(k).getHakmilik());
            }
        }
        logger.debug("listHakmilikSebelum : " + listHakmilikSebelum.size());
        if (listHakmilikSebelum.size() > 0) {
            for (int l = 0; l < listHakmilikSebelum.size(); l++) {
                list.add(listHakmilikSebelum.get(l).getHakmilik());
            }
        }

        logger.debug("size  senaraiHakmilikBerikut " + list.size());
        HashSet<Hakmilik> listToSet = new HashSet<Hakmilik>(list);
        list.clear();
        List<Hakmilik> listWithoutDuplicates = new ArrayList<Hakmilik>(listToSet);

        for (int i = 0; i < listWithoutDuplicates.size(); i++) {
            logger.debug("nilai list " + listWithoutDuplicates.get(i).getIdHakmilik());
            list.add(listWithoutDuplicates.get(i));
        }
        logger.info("list.size() : "+list.size());
        return list;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public String getIdHakmilikR() {
        return idHakmilikR;
    }

    public void setIdHakmilikR(String idHakmilikR) {
        this.idHakmilikR = idHakmilikR;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UtilKod> getListKodDaerah() {
        return listKodDaerah;
    }

    public void setListKodDaerah(List<UtilKod> listKodDaerah) {
        this.listKodDaerah = listKodDaerah;
    }

    public List<UtilKod> getListBPM() {
        return listBPM;
    }

    public void setListBPM(List<UtilKod> listBPM) {
        this.listBPM = listBPM;
    }

    public List<UtilKod> getListKodSeksyen() {
        return listKodSeksyen;
    }

    public void setListKodSeksyen(List<UtilKod> listKodSeksyen) {
        this.listKodSeksyen = listKodSeksyen;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getMukim() {
        return mukim;
    }

    public void setMukim(String mukim) {
        this.mukim = mukim;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public List<UtilKod> getListJenisHakmilik() {
        return listJenisHakmilik;
    }

    public void setListJenisHakmilik(List<UtilKod> listJenisHakmilik) {
        this.listJenisHakmilik = listJenisHakmilik;
    }

    public String getJenishakmilik() {
        return jenishakmilik;
    }

    public void setJenishakmilik(String jenishakmilik) {
        this.jenishakmilik = jenishakmilik;
    }

    public KodDaerahDAO getKodDaerahDAO() {
        return kodDaerahDAO;
    }

    public void setKodDaerahDAO(KodDaerahDAO kodDaerahDAO) {
        this.kodDaerahDAO = kodDaerahDAO;
    }

    public KodNegeriDAO getKodNegeriDAO() {
        return kodNegeriDAO;
    }

    public void setKodNegeriDAO(KodNegeriDAO kodNegeriDAO) {
        this.kodNegeriDAO = kodNegeriDAO;
    }

    public KodSeksyenDAO getKodSeksyenDAO() {
        return kodSeksyenDAO;
    }

    public void setKodSeksyenDAO(KodSeksyenDAO kodSeksyenDAO) {
        this.kodSeksyenDAO = kodSeksyenDAO;
    }

    public KodBandarPekanMukimDAO getKodBandarPekanMukimDAO() {
        return kodBandarPekanMukimDAO;
    }

    public void setKodBandarPekanMukimDAO(KodBandarPekanMukimDAO kodBandarPekanMukimDAO) {
        this.kodBandarPekanMukimDAO = kodBandarPekanMukimDAO;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public List<Hakmilik> getSenaraiHakmilikBerikut() {
        return senaraiHakmilikBerikut;
    }

    public void setSenaraiHakmilikBerikut(List<Hakmilik> senaraiHakmilikBerikut) {
        this.senaraiHakmilikBerikut = senaraiHakmilikBerikut;
    }

    public List<HakmilikAsal> getListHakmilikAsal() {
        return listHakmilikAsal;
    }

    public void setListHakmilikAsal(List<HakmilikAsal> listHakmilikAsal) {
        this.listHakmilikAsal = listHakmilikAsal;
    }

    public List<HakmilikSebelum> getListHakmilikSebelum() {
        return listHakmilikSebelum;
    }

    public void setListHakmilikSebelum(List<HakmilikSebelum> listHakmilikSebelum) {
        this.listHakmilikSebelum = listHakmilikSebelum;
    }
}
