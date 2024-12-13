/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiDAO;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.DasarTuntutanCukai;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTerima;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author nurfaizati
 */
@UrlBinding("/hasil/rekod_penghantaran")
public class RekodPenghantaranActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RekodPenghantaranActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    private PenggunaDAO penggunaDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    private DasarTuntutanCukaiDAO dasarTuntutanCukaiDAO;
    @Inject
    private DasarTuntutanNotisDAO dasarTuntutanNotisDAO;
    @Inject
    NotisPeringatan6AManager notisPeringatan6AManager;
    @Inject
    NotisPeringatan6AManager manager;
    @Inject
    GenerateIdPermohonanWorkflow gipw;

    private Hakmilik hakmilik;
    private DasarTuntutanCukai dasarTuntutanCukai;
    private DasarTuntutanCukaiHakmilik dasarTuntutanCukaiHakmilik;
    private DasarTuntutanNotis dasarTuntutanNotis;
    private List<DasarTuntutanNotis> senaraiNotis = new ArrayList<DasarTuntutanNotis>();
    private List<DasarTuntutanNotis> senarai = new ArrayList<DasarTuntutanNotis>();
    private KodStatusTerima statusTerima;
    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<DasarTuntutanNotis> senaraiDasarTuntutanNotis;
    private ArrayList temp = new ArrayList();
    private boolean visible = false;
    private String noDasar;
//    private Date tarikhNotis;
    private Integer tempohTahun;
    private String kodStatus;
    private boolean btn = false;
    private String idHakmilik;
    private List<String> kodStatus2 = new ArrayList<String>();
    private List<String> kodCaraHantar = new ArrayList<String>();
    private List<String> tarikhTerima = new ArrayList<String>();
    private List<String> tarikhDihantar = new ArrayList<String>();
    private String[] tarikhNotis;
    private List<String> catatanTerima = new ArrayList<String>();
    private String suratPeringatan = "none";
    private String notis6A = "none";
    private String notis8A = "none";
    private String notisGantian6A = "none";
    private boolean caraPenghantaran = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<Map<String, Object>> senaraiSementara = new ArrayList<Map<String, Object>>();

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_penghantaran.jsp");

    }

    public Resolution search() {
        setVisible(true);
        dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(noDasar);
        LOG.debug("noDasar :" + noDasar);
        LOG.debug("kodStatus :" + kodStatus);
        if (dasarTuntutanCukai == null) {
            setVisible(false);
            addSimpleError("No. Rujukan Dasar tidak tepat.");
        }
        if ((kodStatus.equals("N6A")) || (kodStatus.equals("N8A")) || (kodStatus.equals("SP"))) {
            caraPenghantaran = true;

        }
        senaraiDasarTuntutanCukaiHakmilik = new ArrayList<DasarTuntutanCukaiHakmilik>();
        senaraiDasarTuntutanNotis = new ArrayList<DasarTuntutanNotis>();
        List<DasarTuntutanNotis> l = new ArrayList<DasarTuntutanNotis>();

        String[] n1 = {"dasarTuntutanCukai"};
        Object[] v1 = {dasarTuntutanCukai};
        senaraiDasarTuntutanCukaiHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(n1, v1, null);


        for (DasarTuntutanCukaiHakmilik d : senaraiDasarTuntutanCukaiHakmilik) {
            String[] n2 = {"hakmilik"};
            Object[] v2 = {d};
            l = dasarTuntutanNotisDAO.findByEqualCriterias(n2, v2, null);
            senaraiNotis.addAll(l);

            // testing
            Map m = new HashMap();
            m.put("notis", d);
            m.put("catatan", "test 1234567");
            getSenaraiSementara().add(m);

        }
        for (DasarTuntutanNotis notis : senaraiNotis) {
            if (notis.getNotis().getKod().equals(kodStatus)) {
                senaraiDasarTuntutanNotis.add(notis);
            }
        }
        LOG.debug("noDasar22 :" + noDasar);

        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_penghantaran.jsp");
    }

//    public List searchDasar() {
//        String hql = "from DasarTuntutanCukaiHakmilik dt ";
//
//        hql += "where dt.dasarTuntutanCukai.idDasar = :noDasar ";
//
//        if (kodStatus != null) {
//            hql += "and dt.status.kod = :kodStatus ";
//        }
//        Session s = sessionProvider.get();
//        Query q = s.createQuery(hql);
//        q.setParameter("noDasar", noDasar);
//
//        if (kodStatus != null) {
//            q.setParameter("kodStatus", kodStatus);
//        }
//        return q.list();
//    }
    public Resolution popup() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
//        return new ForwardResolution("/WEB-INF/jsp/hasil/hakmilik_details.jsp").addParameter("popup", "true");
        return new ForwardResolution("/WEB-INF/jsp/hasil/pertanyaan_cukai_2.jsp").addParameter("popup", "true");

    }

    public Resolution save() throws ParseException {
        LOG.debug(senaraiSementara);


        if (kodStatus2.isEmpty()) {

            addSimpleError("Sila Masukkan Status Penyampaian");
            search();

        } else if (kodCaraHantar.isEmpty()) {

            addSimpleError("Sila Masukkan Cara Penghantaran");
            search();
        } else {

            search();

            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Pengguna p = ctx.getUser(); //penggunaDAO.findById("admin");

            Date now = new Date();
            InfoAudit info = p.getInfoAudit();
            info.setDimasukOleh(p);
            info.setTarikhMasuk(new java.util.Date());
            KodCawangan caw = p.getKodCawangan();

            List<DasarTuntutanNotis> list = new ArrayList<DasarTuntutanNotis>();

            String trh = null;
            for (int i = 0; i < senaraiDasarTuntutanNotis.size(); i++) {
                LOG.debug(kodStatus2 + "-" + kodCaraHantar + "+" + catatanTerima + "x" + tarikhTerima + "y" + tarikhDihantar);
                KodStatusTerima kodStatusTerima = new KodStatusTerima();

                if (kodStatus2.size() > i) {
                    kodStatusTerima.setKod(kodStatus2.get(i));
                }

                KodCaraPenghantaran kodCaraPenghantaran = new KodCaraPenghantaran();
                if (kodCaraHantar.size() > i) {
                    kodCaraPenghantaran.setKod(kodCaraHantar.get(i));
                }


                DasarTuntutanNotis dtc = senaraiDasarTuntutanNotis.get(i);

                dtc.setInfoAudit(info);
                dtc.setStatusTerima(kodStatusTerima);
                dtc.setCaraPenghantaran(kodCaraPenghantaran);

                if (catatanTerima.size() > i) {
                    dtc.setCatatanTerima(catatanTerima.get(i));
                }

                if (tarikhTerima.size() > i) {
                    trh = tarikhTerima.get(i);
                    if (trh != null) {
                        dtc.setTarikhTerima(sdf.parse(trh));
                    }
                }
                if (tarikhDihantar.size() > i) {
                    trh = tarikhDihantar.get(i);
                    if (trh != null) {
                        dtc.setTarikhDihantar(sdf.parse(trh));
                    }
                }

                manager.updateNotis(dtc);

            }

            setVisible(false);


            addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");
            if ((kodStatus.equals("N6A")) || (kodStatus.equals("N8A"))) {
                SediaEndorsan(p, kodStatus);
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/hasil/rekod_penghantaran.jsp");

    }

    public void SediaEndorsan(Pengguna p, String ks) {
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        Pengguna p = ctx.getUser();
//        KodUrusan ku = null;

        KodUrusan ku = new KodUrusan();
        
        try {
            List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
            dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(noDasar);
            String[] name = {"dasarTuntutanCukai"};
            Object[] value = {dasarTuntutanCukai};
            senaraiDasarTuntutanCukaiHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
//            if("N6A".equals(ks)){
//                LOG.info("Endorsan untuk notis 6A.");
//                ku = kodUrusanDAO.findById("N6A");
//                for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik) {
//                    if(!dtch.getHakmilik().getAkaunCukai().getBaki().equals(BigDecimal.ZERO)){ // check if hakmilik have 'baki' or not
//                        for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
//                            if(("N6A".equals(dtn.getNotis().getKod())) && ("TM".equals(dtn.getStatusTerima().getKod()))) //check utk Notis 6A yg dapat disampaikan sahaja
//                                senaraiHakmilik.add(dtch.getHakmilik());
//                        }
//                        LOG.debug("hakmilik :"+dtch.getHakmilik().getIdHakmilik());
//                    }
//                }
//                if(gipw.generateIdPermohonanWorkflow(ku, p, senaraiHakmilik, null))
//                    addSimpleMessage("Permohonan telah berjaya diwujudkan.");
//                else{
//                    addSimpleError("Permohonan telah gagal diwujudkan");
//                    search();
//                }
//            }
             if ("N6A".equals(ks)) {
                LOG.info("Endorsan untuk notis 6A.");
//                ku.setKod("ED6A");
                ku = kodUrusanDAO.findById("ED6A");
                LOG.debug("senaraiDasarTuntutanCukaiHakmilik.size :"+senaraiDasarTuntutanCukaiHakmilik.size());
                for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik) {
                    if (!dtch.getHakmilik().getAkaunCukai().getBaki().equals(BigDecimal.ZERO)) { // check if hakmilik have 'baki' or not
                        for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                            LOG.debug("dtch.getSenaraiNotis().size :"+dtch.getSenaraiNotis().size());
                            if ("N6A".equals(dtn.getNotis().getKod()) && "TM".equals(dtn.getStatusTerima().getKod())) //check utk Notis 6A yg dapat disampaikan sahaja
                            {
                                senaraiHakmilik.add(dtch.getHakmilik());
                                LOG.debug("idhakmilik :"+dtch.getHakmilik());
                            }
                        }
//                        LOG.debug("hakmilik :"+hakmilik.getIdHakmilik());
                    }
                }
                LOG.debug("senaraiHakmilik.size :"+senaraiHakmilik.size());
                if (gipw.generateIdPermohonanWorkflowInternal(ku, p, senaraiHakmilik, "")) {
                    addSimpleMessage("Permohonan telah berjaya diwujudkan.");
                } else {
                    addSimpleError("Permohonan telah gagal diwujudkan");
                    search();
                }
            }
            else if("N8A".equals(ks)){
                LOG.info("Endorsan untuk notis 8A.");
//                ku.setKod("ED8A");
                ku = kodUrusanDAO.findById("ED8A");
                for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik) {
                    if(!dtch.getHakmilik().getAkaunCukai().getBaki().equals(BigDecimal.ZERO)){ // check if hakmilik have 'baki' or not
                        for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                            if("N8A".equals(dtn.getNotis().getKod()) && "TM".equals(dtn.getStatusTerima().getKod())){ //check utk Notis 8A yg dapat disampaikan sahaja
                                senaraiHakmilik.add(dtch.getHakmilik());
                                LOG.debug("idhakmilik :"+dtch.getHakmilik());
                            }
                        }
//                        LOG.debug("hakmilik :"+hakmilik.getIdHakmilik());
                    }
                }
                if(gipw.generateIdPermohonanWorkflowInternal(ku, p, senaraiHakmilik, ""))
                    addSimpleMessage("Permohonan telah berjaya diwujudkan.");
                else{
                    addSimpleError("Permohonan telah gagal diwujudkan");
                    search();
                }
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            search();
        }
    }    

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(Integer tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public String getNoDasar() {
        return noDasar;
    }

    public void setNoDasar(String noDasar) {
        this.noDasar = noDasar;
    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDasarTuntutanCukaiHakmilik() {
        return senaraiDasarTuntutanCukaiHakmilik;
    }

    public void setSenaraiDasarTuntutanCukaiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik) {
        this.senaraiDasarTuntutanCukaiHakmilik = senaraiDasarTuntutanCukaiHakmilik;
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

    public List<DasarTuntutanNotis> getSenaraiNotis() {
        return senaraiNotis;
    }

    public void setSenaraiNotis(List<DasarTuntutanNotis> senaraiNotis) {
        this.senaraiNotis = senaraiNotis;
    }

    public DasarTuntutanCukaiHakmilik getDasarTuntutanCukaiHakmilik() {
        return dasarTuntutanCukaiHakmilik;
    }

    public void setDasarTuntutanCukaiHakmilik(DasarTuntutanCukaiHakmilik dasarTuntutanCukaiHakmilik) {
        this.dasarTuntutanCukaiHakmilik = dasarTuntutanCukaiHakmilik;
    }

    public KodStatusTerima getStatusTerima() {
        return statusTerima;
    }

    public void setStatusTerima(KodStatusTerima statusTerima) {
        this.statusTerima = statusTerima;
    }

    public DasarTuntutanNotis getDasarTuntutanNotis() {
        return dasarTuntutanNotis;
    }

    public void setDasarTuntutanNotis(DasarTuntutanNotis dasarTuntutanNotis) {
        this.dasarTuntutanNotis = dasarTuntutanNotis;
    }

//    public Date getTarikhNotis() {
//        return tarikhNotis;
//    }
//
//    public void setTarikhNotis(Date tarikhNotis) {
//        this.tarikhNotis = tarikhNotis;
//    }
    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public List<DasarTuntutanNotis> getSenaraiDasarTuntutanNotis() {
        return senaraiDasarTuntutanNotis;
    }

    public void setSenaraiDasarTuntutanNotis(List<DasarTuntutanNotis> senaraiDasarTuntutanNotis) {
        this.senaraiDasarTuntutanNotis = senaraiDasarTuntutanNotis;
    }

    public ArrayList getTemp() {
        return temp;
    }

    public void setTemp(ArrayList temp) {
        this.temp = temp;
    }

    public List<String> getKodStatus2() {
        return kodStatus2;
    }

    public void setKodStatus2(List<String> kodStatus2) {
        this.kodStatus2 = kodStatus2;
    }

    public List<String> getTarikhDihantar() {
        return tarikhDihantar;
    }

    public void setTarikhDihantar(List<String> tarikhDihantar) {
        this.tarikhDihantar = tarikhDihantar;
    }

    public List<String> getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(List<String> tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public List<DasarTuntutanNotis> getSenarai() {
        return senarai;
    }

    public void setSenarai(List<DasarTuntutanNotis> senarai) {
        this.senarai = senarai;
    }

    public String getSuratPeringatan() {
        return suratPeringatan;
    }

    public void setSuratPeringatan(String suratPeringatan) {
        this.suratPeringatan = suratPeringatan;
    }

    public List<String> getCatatanTerima() {
        return catatanTerima;
    }

    public void setCatatanTerima(List<String> catatanTerima) {
        this.catatanTerima = catatanTerima;
    }

    /**
     * @return the tarikhNotis
     */
    public String[] getTarikhNotis() {
        return tarikhNotis;
    }

    /**
     * @param tarikhNotis the tarikhNotis to set
     */
    public void setTarikhNotis(String[] tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    /**
     * @return the kodCaraHantar
     */
    public List<String> getKodCaraHantar() {
        return kodCaraHantar;
    }

    /**
     * @param kodCaraHantar the kodCaraHantar to set
     */
    public void setKodCaraHantar(List<String> kodCaraHantar) {
        this.kodCaraHantar = kodCaraHantar;
    }

    /**
     * @return the notis6A
     */
    public String getNotis6A() {
        return notis6A;
    }

    /**
     * @param notis6A the notis6A to set
     */
    public void setNotis6A(String notis6A) {
        this.notis6A = notis6A;
    }

    /**
     * @return the notis8A
     */
    public String getNotis8A() {
        return notis8A;
    }

    /**
     * @param notis8A the notis8A to set
     */
    public void setNotis8A(String notis8A) {
        this.notis8A = notis8A;
    }

    /**
     * @return the notisGantian6A
     */
    public String getNotisGantian6A() {
        return notisGantian6A;
    }

    /**
     * @param notisGantian6A the notisGantian6A to set
     */
    public void setNotisGantian6A(String notisGantian6A) {
        this.notisGantian6A = notisGantian6A;
    }

    /**
     * @return the senaraiSementara
     */
    public List<Map<String, Object>> getSenaraiSementara() {
        return senaraiSementara;
    }

    /**
     * @param senaraiSementara the senaraiSementara to set
     */
    public void setSenaraiSementara(List<Map<String, Object>> senaraiSementara) {
        this.senaraiSementara = senaraiSementara;
    }

    public boolean isCaraPenghantaran() {
        return caraPenghantaran;
    }

    public void setCaraPenghantaran(boolean caraPenghantaran) {
        this.caraPenghantaran = caraPenghantaran;
    }
}

