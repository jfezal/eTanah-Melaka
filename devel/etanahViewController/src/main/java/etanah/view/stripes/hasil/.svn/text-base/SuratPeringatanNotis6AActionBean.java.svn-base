/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import java.text.ParseException;
import java.util.Date;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.KodStatusTuntutanCukai;
import etanah.model.DasarTuntutanCukai;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Hakmilik;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodDaerah;
import etanah.model.KodNotis;
import etanah.model.KodSeksyen;
import etanah.sequence.GeneratorIdDasarCukaiHakmilik;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 *
 * @author nurfaizati
 */
//@Wizard(startEvents = {"selectTransaction", "delete", "penyukatanBPM"})
@UrlBinding("/hasil/surat_peringatan_notis6A")
public class SuratPeringatanNotis6AActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(SuratPeringatanNotis6AActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    private String idDasar;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private Date tarikh;
    private String tarikhDasar;
    private boolean flag = false;
    private boolean btn = false;
    int kodBPM;

//    private KodStatusTuntutanCukai status;
    private DasarTuntutanCukai dasarTuntutanCukai = new DasarTuntutanCukai();
    private List<DasarTuntutanCukai> senaraiDasarTuntutanCukai = new ArrayList<DasarTuntutanCukai>();
    private List<DasarTuntutanCukaiHakmilik> list = new ArrayList<DasarTuntutanCukaiHakmilik>();
    private List<DasarTuntutanCukaiHakmilik> listPenghantaran = new ArrayList<DasarTuntutanCukaiHakmilik>();
    private List<DasarTuntutanNotis> senaraiNotis = new ArrayList<DasarTuntutanNotis>();
    private List<KodBandarPekanMukim> senaraiBPM = new ArrayList<KodBandarPekanMukim>();
    private List<DasarTuntutanCukaiHakmilik> listDtch = new ArrayList<DasarTuntutanCukaiHakmilik>();
    private List<KodDaerah> senaraiKodDaerah = new ArrayList<KodDaerah>();
    private KodCaraPenghantaran caraPenghantaran = new KodCaraPenghantaran();
    private KodNotis notis = new KodNotis();
    public String kodStatus;
    private String daerah;
    private String noLot;
    private String noHakmilik;
    private String bandarPekanMukim;
    private String kategoriTanah;
    private String bangsa;
    private String amaunDari;
    private String amaunHingga;
    private String dariTahun;
    private String hinggaTahun;
    private static String kodNegeri;
    private boolean tahun = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    private etanah.Configuration conf;
    @Inject
    private PenggunaDAO penggunaDAO;
    @Inject
    private KodDaerahDAO kodDaerahDAO;
    @Inject
    private DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    private DasarTuntutanCukaiDAO dasarTuntutanCukaiDAO;
    @Inject
    private KodStatusTuntutanCukaiDAO kodStatusTuntutanCukaiDAO;
    @Inject
    NotisPeringatan6AManager manager;
    @Inject
    NotisPeringatan6AManager notisPeringatan6AManager;
    private List<Hakmilik> list1;
    private static List<Hakmilik> list2;// = new ArrayList();
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    GeneratorIdDasarCukaiHakmilik generatorIdDasarCukaiHakmilik;
    SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private String currentYear = sdfYear.format(new java.util.Date());
    private List<KodSeksyen> senaraiKodSeksyen;

    @DefaultHandler
    public Resolution selectTransaction() {
        
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "negeriSembilan";
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/surat_peringatan_notis6A.jsp");

    }

    public Resolution saveDasar() throws ParseException {
        String rtnLocation = "/WEB-INF/jsp/hasil/surat_peringatan_notis6A.jsp";
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = p.getKodCawangan();
        dasarTuntutanCukai.setInfoAudit(info);
        dasarTuntutanCukai.setCawangan(caw);
        dasarTuntutanCukai.setTarikhDasar(sdf.parse(tarikhDasar));
        LOG.info("rtnLocation :" + rtnLocation);

        senaraiDasarTuntutanCukai = dasarTuntutanCukaiDAO.findAll();
        if (dasarTuntutanCukai.getIdDasar() != null) {
            LOG.debug("senaraiDasarTuntutanCukai.size() :" + senaraiDasarTuntutanCukai.size());
            if (senaraiDasarTuntutanCukai.size() != 0 && ValidateIdDasar(dasarTuntutanCukai.getIdDasar(), senaraiDasarTuntutanCukai)) {
                addSimpleError("Dasar Telah Wujud. Sila Masuk Sekali Lagi.");
                rtnLocation = "/WEB-INF/jsp/hasil/surat_peringatan_notis6A.jsp";
                LOG.debug("equals(idDasar) :" + idDasar);
            } else {
                idDasar = manager.save(dasarTuntutanCukai);
                penyukatanBPM();
                addSimpleMessage("Dasar Telah Berjaya Diwujudkan..");
                rtnLocation = "/WEB-INF/jsp/hasil/surat_peringatan_notis6A_2.jsp";
                LOG.debug("Notequals(idDasar) :" + idDasar);
            }
        }
        LOG.info("rtnLocation 2:" + rtnLocation);
        return new ForwardResolution(rtnLocation);
    }

    public Resolution find() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        daerah = p.getKodCawangan().getKod();
        penyukatanBPM();
        dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(idDasar);
        return new ForwardResolution("/WEB-INF/jsp/hasil/surat_peringatan_notis6A_2.jsp").addParameter("popup", "true");
    }

    public Resolution search() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        list1 = notisPeringatan6AManager.findAll(getContext().getRequest().getParameterMap(), p);
        list2 = new ArrayList();
//        int count = 1;
        for (Hakmilik hm : list1) {
//            LOG.info(count +") hm :"+hm.getIdHakmilik());
            listDtch = notisPeringatan6AManager.senaraiDtch(hm);
//            LOG.info("listDtch.size :"+listDtch.size());
            String statusHM = "sb"; // bb = belum bayar, sb = sudah bayar
            if(listDtch.size() > 0){
                for (DasarTuntutanCukaiHakmilik dtchStatus : listDtch) {
                    if(!dtchStatus.getStatus().getKod().equals("BC"))// kod BC = telah bayar cukai
                        statusHM = "bb";
                }
            }
            if(statusHM.equals("bb"))
                list2.add(hm);
            listDtch.clear();
//            count++;
        }
        LOG.debug("list2.size :"+list2.size());
        if(list2.size() > 0){
            for (Hakmilik hmRemove : list2) {
                LOG.info("hmRemove :"+hmRemove.getIdHakmilik());
                list1.remove(hmRemove);
            }
            LOG.info("list1.size :"+list1.size());
        }
        list2 = list1;
        if (idDasar != null) {
            dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(idDasar);
        }
        setFlag(true);
        penyukatanBPM();
        if(StringUtils.isNotBlank(dariTahun)){
            tahun = true;
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/surat_peringatan_notis6A_2.jsp");
    }

    public Resolution refreshPaging(){
        dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(idDasar);
        idDasar = dasarTuntutanCukai.getIdDasar();
        list1 = list2;
        return new ForwardResolution("/WEB-INF/jsp/hasil/surat_peringatan_notis6A_2.jsp").addParameter("tab", "true");
    }

    public Resolution delete() {
        dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(idDasar);
        idDasar = dasarTuntutanCukai.getIdDasar();
        String idHakmilikRemove = getContext().getRequest().getParameter("idHakmilikRemove");
        LOG.info("delete: idHakmilik :" + idHakmilikRemove);
        LOG.info("1) list2.size :" + list2.size());
        LOG.info("delete:daerah :"+daerah);
        LOG.info("delete:bandarPekanMukim :"+bandarPekanMukim);
        List<Hakmilik> list3 = new ArrayList();
        if(StringUtils.isNotBlank(idHakmilikRemove)){
            for (Hakmilik h : list2) {
//                if (StringUtils.isNotBlank(idHakmilikRemove) && !StringUtils.equals(idHakmilikRemove, h.getIdHakmilik())) {
                if(!StringUtils.equals(idHakmilikRemove, h.getIdHakmilik())){
                    LOG.debug("idHakmilik :" + h.getIdHakmilik());
                    list3.add(h);
                }
            }
        }else{
            list3 = list2;
        }
        list2 = list3;
        LOG.info("2) list2.size :" + list2.size());
        list1 = list2;
        LOG.info("3) list1.size :" + list1.size());
        setFlag(true);
        penyukatanBPM2();
        return new ForwardResolution("/WEB-INF/jsp/hasil/surat_peringatan_notis6A_2.jsp").addParameter("popup", "true");
    }

    public Resolution save() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = p.getKodCawangan();
        list = new ArrayList<DasarTuntutanCukaiHakmilik>();
        KodStatusTuntutanCukai status = kodStatusTuntutanCukaiDAO.findById("ST");

        if (idDasar != null) {
            LOG.info("idDasar :"+idDasar);
            dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(idDasar);
        }
        if (StringUtils.isNotBlank(dasarTuntutanCukai.getIdDasar())) {
//            DasarTuntutanCukai dtc = new DasarTuntutanCukai();
//            dtc.setIdDasar(dasarTuntutanCukai.getIdDasar());
            if (StringUtils.isNotBlank(amaunDari)) {
                dasarTuntutanCukai.setTunggakanMinima(new BigDecimal(amaunDari));
            }
            if (StringUtils.isNotBlank(dariTahun)) {
                dasarTuntutanCukai.setTunggakanDariTahun(Integer.parseInt(dariTahun));
            }
            if (StringUtils.isNotBlank(hinggaTahun)) {
                dasarTuntutanCukai.setTunggakanKeTahun(Integer.parseInt(hinggaTahun));
            }
            if (StringUtils.isNotBlank(bandarPekanMukim)) {
                KodBandarPekanMukim bpm = new KodBandarPekanMukim();
                bpm.setKod(Integer.parseInt(bandarPekanMukim));
                dasarTuntutanCukai.setBandarPekanMukim(bpm);
            }
            manager.update(dasarTuntutanCukai);
        }
        for (Hakmilik hm : list2) {
//        for (Hakmilik hm : list1) {
            DasarTuntutanCukaiHakmilik dasar = new DasarTuntutanCukaiHakmilik();
            LOG.info("creat iddch");
            Long seqIdDCH = Long.parseLong(generatorIdDasarCukaiHakmilik.generate(ctx.getKodNegeri(), caw, dasar));
            dasar.setIdDasarHakmilik(seqIdDCH);
            LOG.info("dh create iddch");
            dasar.setCawangan(caw);
            dasar.setDasarTuntutanCukai(dasarTuntutanCukaiDAO.findById(dasarTuntutanCukai.getIdDasar()));
            dasar.setHakmilik(hakmilikDAO.findById(hm.getIdHakmilik()));
            dasar.setInfoAudit(info);
            dasar.setStatus(status);
//            dasar.setNoRujukan(currentYear+"/"+hm.getIdHakmilik());           //lama
            String rujukanNotis = "B3/116/"+currentYear+"/"+hm.getBandarPekanMukim().getKodNotis()+"/";
            String seqNotis = checkingNoFile(rujukanNotis);
            dasar.setNoRujukan(seqNotis);

            list.add(dasar);
        }
        manager.save(list);
        penyukatanBPM();
        delete();
         setBtn(true);
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new ForwardResolution("/WEB-INF/jsp/hasil/surat_peringatan_notis6A_2.jsp");
    }
    
    public String checkingNoFile(String id){
        String noSiri = null;
        DasarTuntutanCukaiHakmilik dtc = new DasarTuntutanCukaiHakmilik();
        String siri = "0000";
        
        String query = "SELECT dtc FROM etanah.model.DasarTuntutanCukaiHakmilik dtc WHERE dtc.noRujukan LIKE :kod ORDER BY dtc.noRujukan DESC";
        Query q = sessionProvider.get().createQuery(query);
            q.setString("kod", id+"%");
        if (q.list().size()!=0){
            dtc = (DasarTuntutanCukaiHakmilik) q.list().get(0);
            siri = dtc.getNoRujukan().substring(16, 20);
        }
        LOG.info("siri : "+siri);
        int seq = Integer.parseInt(siri)+1;
        LOG.info("seq : "+seq);
        noSiri = String.format("%s%0"+4+"d", id, seq);
        LOG.info("noSiri : "+noSiri);
        
        return noSiri;
    }

    private Boolean ValidateIdDasar(String idDasar, List<DasarTuntutanCukai> senaraiDasarTuntutanCukai) {
        Boolean rtnValue = false;
        for (DasarTuntutanCukai dtn : senaraiDasarTuntutanCukai) {
            if (idDasar.equals(dtn.getIdDasar())) {
                rtnValue = true;
                LOG.info("ValidateIdDasar: true");
            }
        }
        return rtnValue;
    }
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public Resolution penyukatanBPM() {
        dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(idDasar);
        idDasar = dasarTuntutanCukai.getIdDasar();
        String kodDaerah = daerah;
        LOG.info("kod Daerah1 :" + kodDaerah);
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        LOG.info("sql :" + q.getQueryString());
        senaraiBPM = q.list();
        LOG.info("q.list() :" + q.list().size());
        
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        
        if (!p.getKodCawangan().getKod().equals("00")) {
            senaraiKodDaerah.add(p.getKodCawangan().getDaerah());
        }else{
            senaraiKodDaerah = kodDaerahDAO.findAll();
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/surat_peringatan_notis6A_2.jsp");
    }
  
    public List<KodBandarPekanMukim> penyukatanBPM2() {
        dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(idDasar);
        idDasar = dasarTuntutanCukai.getIdDasar();
        String kodDaerah = daerah;
        LOG.info("kod Daerah :" + kodDaerah);
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        LOG.info("sql :" + q.getQueryString());
        senaraiBPM = q.list();
        LOG.info("q.list() :" + q.list().size());
        return senaraiBPM;
    }
    
    public Resolution penyukatanSeksyen() {
    dasarTuntutanCukai = dasarTuntutanCukaiDAO.findById(idDasar);
    idDasar = dasarTuntutanCukai.getIdDasar();   
    String kodDaerah = getContext().getRequest().getParameter("daerah");
    String sql = null;
    Session s = sessionProvider.get();
    Query q = null;
    if (kodDaerah == null || kodDaerah.equals("")) {
      sql = "select bpm from KodBandarPekanMukim bpm ";
      q = s.createQuery(sql);
    } else {
      sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
      q = s.createQuery(sql);
      q.setString("kod", kodDaerah);
    }
    senaraiBPM = q.list();
    String kodBPM = getBandarPekanMukim();
    LOG.debug("BPM-->" + kodBPM);
//        String sql = null;
//        Session s = sessionProvider.get();
//        Query q = null;
    if (kodBPM == null || kodBPM.equals("")) {
      sql = "select sek from KodSeksyen sek ";
      q = s.createQuery(sql);
    } else {
      sql = "select sek from KodSeksyen sek where sek.kodBandarPekanMukim.kod = :kod ";
      q = s.createQuery(sql);
      q.setString("kod", kodBPM);
    }
    senaraiKodSeksyen = q.list();
    LOG.debug("senaraiKodSeksyen-->" + senaraiKodSeksyen.get(0).getNama());
    return new ForwardResolution("/WEB-INF/jsp/hasil/surat_peringatan_notis6A_2.jsp");
  }
    public Resolution senaraiKodBPMByDaerah() {
    LOG.debug(":::start search for kodbpm by daerah:::");
    String kodDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
    if (StringUtils.isEmpty(kodDaerah)) {
      kodDaerah = daerah;
    }
    LOG.debug("kodDaerah :" + kodDaerah);
    return new ForwardResolution("/WEB-INF/jsp/hasil/surat_peringatan_notis6A_2.jsp").addParameter("popup", "true");
  }
    
    public String getTarikhDasar() {
        return tarikhDasar;
    }

    public void setTarikhDasar(String tarikhDasar) {
        this.tarikhDasar = tarikhDasar;
    }

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public DasarTuntutanCukai getDasarTuntutanCukai() {
        return dasarTuntutanCukai;
    }

    public void setDasarTuntutanCukai(DasarTuntutanCukai dasarTuntutanCukai) {
        this.dasarTuntutanCukai = dasarTuntutanCukai;
    }

    public List<DasarTuntutanCukaiHakmilik> getList() {
        return list;
    }

    public void setList(List<DasarTuntutanCukaiHakmilik> list1) {
        this.list = list;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public List<DasarTuntutanCukaiHakmilik> getListPenghantaran() {
        return listPenghantaran;
    }

    public void setListPenghantaran(List<DasarTuntutanCukaiHakmilik> listPenghantaran) {
        this.listPenghantaran = listPenghantaran;
    }

    public List<DasarTuntutanNotis> getSenaraiNotis() {
        return senaraiNotis;
    }

    public void setSenaraiNotis(List<DasarTuntutanNotis> senaraiNotis) {
        this.setSenaraiNotis(senaraiNotis);
    }

    public List<DasarTuntutanCukai> getSenaraiDasarTuntutanCukai() {
        return senaraiDasarTuntutanCukai;
    }

    public void setSenaraiDasarTuntutanCukai(List<DasarTuntutanCukai> senaraiDasarTuntutanCukai) {
        this.senaraiDasarTuntutanCukai = senaraiDasarTuntutanCukai;
    }

    public String getIdDasar() {
        return idDasar;
    }

    public void setIdDasar(String idDasar) {
        this.idDasar = idDasar;
    }

    public KodCaraPenghantaran getCaraPenghantaran() {
        return caraPenghantaran;
    }

    public void setCaraPenghantaran(KodCaraPenghantaran caraPenghantaran) {
        this.caraPenghantaran = caraPenghantaran;
    }

    public KodNotis getNotis() {
        return notis;
    }

    public void setNotis(KodNotis notis) {
        this.notis = notis;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<Hakmilik> getList1() {
        return list1;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public List<Hakmilik> getList2() {
        return list2;
    }

    public void setList2(List<Hakmilik> list2) {
        this.list2 = list2;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(String bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(String kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public String getAmaunDari() {
        return amaunDari;
    }

    public void setAmaunDari(String amaunDari) {
        this.amaunDari = amaunDari;
    }

    public String getAmaunHingga() {
        return amaunHingga;
    }

    public void setAmaunHingga(String amaunHingga) {
        this.amaunHingga = amaunHingga;
    }

    public String getDariTahun() {
        return dariTahun;
    }

    public void setDariTahun(String dariTahun) {
        this.dariTahun = dariTahun;
    }

    public String getHinggaTahun() {
        return hinggaTahun;
    }

    public void setHinggaTahun(String hinggaTahun) {
        this.hinggaTahun = hinggaTahun;
    }

    public KodStatusTuntutanCukaiDAO getKodStatusTuntutanCukaiDAO() {
        return kodStatusTuntutanCukaiDAO;
    }

    public void setKodStatusTuntutanCukaiDAO(KodStatusTuntutanCukaiDAO kodStatusTuntutanCukaiDAO) {
        this.kodStatusTuntutanCukaiDAO = kodStatusTuntutanCukaiDAO;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getBangsa() {
        return bangsa;
    }

     public void setBangsa(String bangsa) {
        this.bangsa = bangsa;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }

    public boolean isTahun() {
        return tahun;
    }

    public void setTahun(boolean tahun) {
        this.tahun = tahun;
    }

    public List<DasarTuntutanCukaiHakmilik> getListDtch() {
        return listDtch;
    }

    public void setListDtch(List<DasarTuntutanCukaiHakmilik> listDtch) {
        this.listDtch = listDtch;
    }

    public List<KodDaerah> getSenaraiKodDaerah() {
        return senaraiKodDaerah;
    }

    public void setSenaraiKodDaerah(List<KodDaerah> senaraiKodDaerah) {
        this.senaraiKodDaerah = senaraiKodDaerah;
    }
    public List<KodSeksyen> getSenaraiKodSeksyen() {
    return senaraiKodSeksyen;
  }

  public void setSenaraiKodSeksyen(List<KodSeksyen> senaraiKodSeksyen) {
    this.senaraiKodSeksyen = senaraiKodSeksyen;
  }
  public int getKodBPM() {
    return kodBPM;
  }

  public void setKodBPM(int kodBPM) {
    this.kodBPM = kodBPM;
  }
  public String getKodNegeri() {
        return kodNegeri;
    }
  public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
    
    
    
