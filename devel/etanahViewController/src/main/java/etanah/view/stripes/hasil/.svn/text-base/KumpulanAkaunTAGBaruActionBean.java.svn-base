/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.hasil.TagAkaunAhliDAO;
import etanah.dao.hasil.TagAkaunDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.hasil.TagAkaun;
import etanah.model.hasil.TagAkaunAhli;
import etanah.sequence.GeneratorTagAkaun;
import etanah.service.common.HakmilikPermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/kumpulan_akaunTAG_baru")
public class KumpulanAkaunTAGBaruActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KumpulanAkaunTAGBaruActionBean.class);
    private static final String KUMP_BARU_VIEW = "/WEB-INF/jsp/hasil/tag_akaun_baru.jsp";
    private static final String KUMP_VIEW = "/WEB-INF/jsp/hasil/tag_akaun_carian.jsp";
    private static final String KUMP_AHLI_VIEW = "/WEB-INF/jsp/hasil/tag_akaun_ahli.jsp";

    private String negeri;
    private String hakmilikSiriDari;
    private String hakmilikSiriKe;
    private String akaunSiriDari;
    private String akaunSiriKe;
    private Pengguna peng;
    private TagAkaun tagAkaun;
    private int bilHakmilik = 6;

    private boolean showPanelAhli = false;

    private List<TagAkaunAhli> senaraiKumpulanAhli;
    private List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();

    @Inject
    AkaunDAO akaunDAO;
    @Inject
    TagAkaunAhliDAO tagAkaunAhliDAO;
    @Inject
    TagAkaunDAO tagAkaunDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KutipanHasilManager manager;
    @Inject
    GeneratorTagAkaun generatorTagAkaun;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "sembilan";
        }
        if (tagAkaun != null) {
            getSenaraiKumpAhli(tagAkaun);
            showPanelAhli = true;
        }
        LOG.info("negeri :" + negeri);
        return new ForwardResolution(KUMP_BARU_VIEW);
    }

    private void getSenaraiKumpAhli(TagAkaun tagakaun) {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "sembilan";
        }
        LOG.info("tagAkaun.idTag :" + tagakaun.getIdTag());
        tagAkaun = tagAkaunDAO.findById(tagakaun.getIdTag());
        senaraiKumpulanAhli = new ArrayList<TagAkaunAhli>();
        String[] name = {"tagAkaun"};
        Object[] value = {tagAkaun};
        senaraiKumpulanAhli = tagAkaunAhliDAO.findByEqualCriterias(name, value, null);
    }

    public Resolution simpanKumpulan() {
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String url = null;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "sembilan";
        }
        if (tagAkaun.getIdTag() == null) {
            tagAkaun.setIdTag(generatorTagAkaun.generate(negeri, peng.getKodCawangan(), peng));
            tagAkaun.setCawangan(peng.getKodCawangan());
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(now);
        } else {
            ia = tagAkaun.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(now);
        }
        tagAkaun.setInfoAudit(ia);
        try {
            manager.saveOrUpdateTagAkaun(tagAkaun);
            url = KUMP_AHLI_VIEW;
            LOG.info("daftar kumpulan berjaya");
            addSimpleMessage("Kumpulan telah BERJAYA didaftarkan. ID Kumpulan : " + tagAkaun.getIdTag());
        } catch (Exception ex) {
            LOG.error(ex);
            addSimpleError("Kumpulan TIDAK berjaya didaftarkan. Sila cuba sekali lagi.");
            url = KUMP_BARU_VIEW;
            ex.printStackTrace(); // for development only
        }
        return new ForwardResolution(url);
    }

    public Resolution simpanAhli() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "sembilan";
        }
        String url = null;
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("senaraiHakmilik.size :" + senaraiHakmilik.size());
        LOG.info("tagAkaun.idTag :" + tagAkaun.getIdTag());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        tagAkaun = tagAkaunDAO.findById(tagAkaun.getIdTag());
        senaraiKumpulanAhli = new ArrayList<TagAkaunAhli>();
        if (senaraiHakmilik.size() == 0) {
            LOG.error("Tidak Berjaya. Tiada data untuk disimpan.");
            addSimpleError("Tiada maklumat untuk disimpan.");
            url = KUMP_AHLI_VIEW;
        } else {
            List<Hakmilik> senHakmilik = checkingHakmilik(senaraiHakmilik, tagAkaun.getIdTag());
            for (Hakmilik hm : senHakmilik) {
                TagAkaunAhli taa = new TagAkaunAhli();
                String[] name = {"hakmilik"};
                Object[] value = {hm};
                List<Akaun> senaraiAkaun = akaunDAO.findByEqualCriterias(name, value, null);
                if (senaraiAkaun.size() > 1) {
                    for (Akaun akaun : senaraiAkaun) {
                        if (akaun.getStatus() == null) {
                            continue;
                        }
                        if (akaun.getStatus().getKod().equals("A")) {
                            taa.setAkaun(akaun);
                        }
                    }
                } else {
                    taa.setAkaun(senaraiAkaun.get(0));
                }
                taa.setTagAkaun(tagAkaun);
                taa.setCawangan(peng.getKodCawangan());
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(now);
                taa.setInfoAudit(ia);
                senaraiKumpulanAhli.add(taa);
            }
            try {
                LOG.info("senaraiKumpulanAhli.size :" + senaraiKumpulanAhli.size());
                manager.saveorUpdateTagAkaunAhli(senaraiKumpulanAhli);
                url = KUMP_BARU_VIEW;
                addSimpleMessage("Maklumat BERJAYA disimpan. ID Kumpulan : " + tagAkaun.getIdTag());
                getSenaraiKumpAhli(tagAkaun);
                showPanelAhli = true;
            } catch (Exception ex) {
                LOG.error("Maklumat Tidak Berjaya Disimpan :" + ex);
                ex.printStackTrace(); // for development only
                addSimpleError("Maklumat TIDAK berjaya disimpan.");
                url = KUMP_AHLI_VIEW;
            }
        }
        return new ForwardResolution(url);
    }

    public List<Hakmilik> checkingHakmilik(List<Hakmilik> senHakmilik, String idKump) {
        List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
        LOG.info("senHakmilik.size() : " + senHakmilik.size());
        for (Hakmilik hakmilik : senHakmilik) {
            Akaun ak = getAkaun(hakmilik);
            String query = "SELECT p FROM etanah.model.hasil.TagAkaunAhli p WHERE p.tagAkaun.idTag =:idTag AND p.akaun.noAkaun =:noAkaun";
            LOG.debug("query: " + query);
            Query q = sessionProvider.get().createQuery(query);
            q.setString("idTag", idKump);
            q.setString("noAkaun", ak.getNoAkaun());
            List<TagAkaunAhli> senaraiAhliTAG = q.list();
            if (senaraiAhliTAG.isEmpty()) {
                LOG.info("--------------- AKAUN XDE ---------------");
                listHakmilik.add(hakmilik);
            }
        }
        LOG.info("listHakmilik.size() : " + listHakmilik.size());
        return listHakmilik;
    }

    public Akaun getAkaun(Hakmilik h) {
        String query = "SELECT p FROM etanah.model.Akaun p WHERE p.hakmilik.idHakmilik =:id AND p.status.kod='A'";
        LOG.debug("query: " + query);
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", h.getIdHakmilik());
        Akaun a = (Akaun) q.uniqueResult();

        return a;
    }

    public Resolution updates() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "sembilan";
        }
        LOG.info("tagAkaun.idTag :" + tagAkaun.getIdTag());
        tagAkaun = tagAkaunDAO.findById(tagAkaun.getIdTag());
        for (int i = 0; i < bilHakmilik; i++) {
            Hakmilik hm = new Hakmilik();
            Akaun acc = new Akaun();
            senaraiHakmilik.add(hm);
            senaraiAkaun.add(acc);
        }
        return new ForwardResolution(KUMP_AHLI_VIEW);
    }

    public Resolution kembaliAkaunBaru() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "sembilan";
        }
        LOG.info("tagAkaun.idTag :" + tagAkaun.getIdTag());
        tagAkaun = tagAkaunDAO.findById(tagAkaun.getIdTag());
        showForm();
        return new ForwardResolution(KUMP_BARU_VIEW);
    }

    public Resolution hapusAhli() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "sembilan";
        }
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        long idAhli = Long.parseLong(getContext().getRequest().getParameter("idAhli"));
        String namaKumpulan = null;
        TagAkaunAhli tagAhli = new TagAkaunAhli();
        try {
            tagAhli = tagAkaunAhliDAO.findById(idAhli);
            tagAkaun = tagAhli.getTagAkaun();
            namaKumpulan = tagAhli.getTagAkaun().getNama();
            LOG.info("idAhli :" + tagAhli.getIdAhli());
            manager.deleteAkaunAhli(tagAhli);
            LOG.debug("Hakmilik BERJAYA dihapuskan dari kumpulan :" + namaKumpulan + " .");
            addSimpleMessage("Hakmilik BERJAYA dihapuskan dari kumpulan :" + namaKumpulan + " .");
        } catch (Exception ex) {
            LOG.error("Hakmilik TIDAK berjaya dihapuskan dari kumpulan :" + namaKumpulan + " . " + ex);
            addSimpleError("Hakmilik TIDAK berjaya dihapuskan.");
            ex.printStackTrace(); // for development only
        }
        showForm();
        return new ForwardResolution(KUMP_BARU_VIEW);
    }

    public List<Akaun> checkingAkaun(List<Akaun> senAkaun, String idKump) {
        List<Akaun> listAk = new ArrayList<Akaun>();
        LOG.info("senAkaun.size() : " + senAkaun.size());
        for (Akaun akaun : senAkaun) {
            String query = "SELECT p FROM etanah.model.hasil.TagAkaunAhli p WHERE p.tagAkaun.idTag =:idTag AND p.akaun.noAkaun =:noAkaun";
            LOG.debug("query: " + query);
            Query q = sessionProvider.get().createQuery(query);
            q.setString("idTag", idKump);
            q.setString("noAkaun", akaun.getNoAkaun());
            List<TagAkaunAhli> senaraiAhliTAG = q.list();
            if (senaraiAhliTAG.isEmpty()) {
                LOG.info("--------------- AKAUN XDE ---------------");
                listAk.add(akaun);
            }
        }
        LOG.info("listAk.size() : " + listAk.size());
        return listAk;
    }

    public Resolution simpanSiri() {
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Date now = new Date();
        String url = null;
        List<Akaun> senaraiAkaunStore = new ArrayList<Akaun>();

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "sembilan";
        }
        String stdAkaunA = null;
        String runAkaunB = null;
        String runAkaunC = null;

        if (hakmilikSiriDari != null && hakmilikSiriKe != null) {
            List<Hakmilik> senaraiHakmilik = hakmilikPermohonanService.carianHakmilikStrataBetween(hakmilikSiriDari, hakmilikSiriKe);
//            List<Hakmilik> senaraiHakmilik2 = hakmilikPermohonanService.carianHakmilikBetweenHakmilik(hakmilikSiriDari, hakmilikSiriKe);

            for (Hakmilik hm : senaraiHakmilik) {
                Akaun akaun = hm.getAkaunCukai();
                if (akaun.getStatus().getKod().equals("A")) {
                    senaraiAkaunStore.add(akaun);
                }
            }

//            int hakmilikLength = hakmilikSiriDari.length();
//            String stdHakmilikDari = null;
//            String runHakmilikDari = null;
//            String runHakmilikKe = null;
//
//            if(hakmilikLength == 17){
//                stdHakmilikDari = hakmilikSiriDari.substring(0, 9);
//                runHakmilikDari = hakmilikSiriDari.substring(9, hakmilikLength);
//                runHakmilikKe = hakmilikSiriKe.substring(9, hakmilikLength);
//            }else{
//                stdHakmilikDari = hakmilikSiriDari.substring(0, 8);
//                runHakmilikDari = hakmilikSiriDari.substring(8, hakmilikLength);
//                runHakmilikKe = hakmilikSiriKe.substring(8, hakmilikLength);
//            }
//
////            int length = hakmilikSiriDari.length();
////            String a = idHakmilikSiriDari.substring(9, length);
////            String c = idHakmilikSiriDari.substring(0, 9);
////            String b = idHakmilikSiriKe.substring(9, length);
////
////            int a1 = Integer.parseInt(a);
////            int b1 = Integer.parseInt(b);
////            int series = b1 - a1 +1;
////            String id = null;
////            int l1 = c.length();
////            int l = length - l1;
////            for(int x=0;x<series;x++){
////                int a2 = a1 + x;
////                id = String.format("%s%0"+l+"d", c, a2);
////
////                Hakmilik h = hakmilikDAO.findById(id);
////                if(h != null){
////                    hakmilikList.add(h);
////                }
////            }
//
//            int startHakmilik = Integer.parseInt(runHakmilikDari);
//            int finishHakmilik = Integer.parseInt(runHakmilikKe);
//            int count = (finishHakmilik - startHakmilik) + 1;
//            String hakmilikBaru = null;
//            int countLimit = 500;
//            int countStart = 1;
//            for(int i=0;i<count;i++){
//                int runningNumber = startHakmilik + i;
//                hakmilikBaru = String.format("%s%0"+runHakmilikDari.length()+"d", stdHakmilikDari, runningNumber);
////                LOG.info("hakmilikBaru :"+hakmilikBaru);
////                List<Akaun> tempAkaun = new ArrayList<Akaun>();
////                String[] name = {"hakmilik.idHakmilik"};
////                Object[] value= {hakmilikBaru};
////                tempAkaun = akaunDAO.findByEqualCriterias(name, value, null);
//                Session s = sessionProvider.get();
//                Query qAkaun = s.createQuery("SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik = :hm AND a.kodAkaun ='AC' AND a.status.kod='A'");
//                qAkaun.setString("hm", hakmilikBaru);
//                Akaun a = (Akaun)qAkaun.uniqueResult();
////                LOG.debug("tempAkaun.size :"+tempAkaun.size());
////                LOG.debug("countStart :"+countStart);
//                if(countStart <= countLimit){
////                    if(tempAkaun.size() > 0){
////                        for (Akaun akaun : tempAkaun) {
////                            if(akaun.getKodAkaun().getKod().equals("AC") && akaun.getStatus().getKod().equals("A")){
////                                senaraiAkaunStore.add(akaun);
////                                countStart++;
////                            }
////                        }
////                    }
//                    if(a!=null){
//                        senaraiAkaunStore.add(a);
//                        countStart++;
//                    }
//                    else{
////                        addSimpleError("Akaun untuk hakmilik "+hakmilikBaru+" tidak wujud.");
////                        break;
//                        LOG.error("Akaun untuk hakmilik "+hakmilikBaru+" tidak wujud.");
//                    }
//                }else{
//                    addSimpleError("Bilangan hakmilik yang dimasukkan melebihi had.");
//                    break;
//                }
//            }
//            if(senaraiAkaunStore.size() == count){
            LOG.info("tagAkaun.idTag :" + tagAkaun.getIdTag());
            List<TagAkaunAhli> senaraiAhliBaruSiri = new ArrayList<TagAkaunAhli>();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(now);
            tagAkaun = tagAkaunDAO.findById(tagAkaun.getIdTag());
            List<Akaun> senAkaun = checkingAkaun(senaraiAkaunStore, tagAkaun.getIdTag());
            for (Akaun akaunStore : senAkaun) {
                TagAkaunAhli ahli = new TagAkaunAhli();
                ahli.setTagAkaun(tagAkaun);
                ahli.setCawangan(peng.getKodCawangan());
                ahli.setAkaun(akaunStore);
                ahli.setInfoAudit(ia);
                senaraiAhliBaruSiri.add(ahli);
            }

            try {
                manager.saveorUpdateTagAkaunAhli(senaraiAhliBaruSiri);
                addSimpleMessage("Maklumat Berjaya Disimpan. ID Kumpulan : " + tagAkaun.getIdTag());
                url = KUMP_BARU_VIEW;
                getSenaraiKumpAhli(tagAkaun);
                showPanelAhli = true;
            } catch (Exception ex) {
                addSimpleError("Maklumat TIDAK berjaya disimpan.");
                ex.printStackTrace(); // for development only
                url = KUMP_AHLI_VIEW;
            }
//            }else{
//                url = KUMP_AHLI_VIEW;
//            }
        }
        return new ForwardResolution(url);
    }

    public TagAkaun getTagAkaun() {
        return tagAkaun;
    }

    public void setTagAkaun(TagAkaun tagAkaun) {
        this.tagAkaun = tagAkaun;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public List<TagAkaunAhli> getSenaraiKumpulanAhli() {
        return senaraiKumpulanAhli;
    }

    public void setSenaraiKumpulanAhli(List<TagAkaunAhli> senaraiKumpulanAhli) {
        this.senaraiKumpulanAhli = senaraiKumpulanAhli;
    }

    public boolean isShowPanelAhli() {
        return showPanelAhli;
    }

    public void setShowPanelAhli(boolean showPanelAhli) {
        this.showPanelAhli = showPanelAhli;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getAkaunSiriDari() {
        return akaunSiriDari;
    }

    public void setAkaunSiriDari(String akaunSiriDari) {
        this.akaunSiriDari = akaunSiriDari;
    }

    public String getAkaunSiriKe() {
        return akaunSiriKe;
    }

    public void setAkaunSiriKe(String akaunSiriKe) {
        this.akaunSiriKe = akaunSiriKe;
    }

    public String getHakmilikSiriDari() {
        return hakmilikSiriDari;
    }

    public void setHakmilikSiriDari(String hakmilikSiriDari) {
        this.hakmilikSiriDari = hakmilikSiriDari;
    }

    public String getHakmilikSiriKe() {
        return hakmilikSiriKe;
    }

    public void setHakmilikSiriKe(String hakmilikSiriKe) {
        this.hakmilikSiriKe = hakmilikSiriKe;
    }
}
