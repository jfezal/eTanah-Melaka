/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Transaksi;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.NotaDaftarKodMap;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.AquireActionBean;
import etanah.view.stripes.hasil.KutipanHasilManager;
import etanah.view.stripes.hasil.UtilityQTFTActionBean;
import etanah.view.workflow.KernelActionBean;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;

import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/senarai_keutamaan")
public class SenaraiKeutamaanActionBean extends AbleActionBean {
    
    private int groupFlag;
    private List<Map<String, String>> senaraiKaveat;
    private List<Map<String, String>> senaraiTarikBalik;
    private List<Map<String, String>> senaraiUrusan;
    private Pengguna pengguna;
    private boolean isBerangkai = Boolean.FALSE;
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat;
    private List<Permohonan> senaraiPermohonanBelumSelesai;
    private Permohonan permohonan;
    @Inject
    PermohonanService permohonanService;
    private static final Logger LOG = Logger.getLogger(SenaraiKeutamaanActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikDAO hakmilikDAO;
    private List<HakmilikSebelumPermohonan> senaraiHakmilikBatalUnique;
    private List<HakmilikSebelumPermohonan> senaraiHakmilikBatal;
    private List<KandunganFolder> listKandunganFolder = new ArrayList<KandunganFolder>();
    private static final String[] PAPAR_PRINT_DOKUMEN = {"VDOC", "DHKE", "DHDE", "DHKK", "DHDK", "19A", "19F", "19C", "SD", "ST", "SGT", "PB1DE", "PB1KE", "PB2DE", "PB2KE"};
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/utility_qtft.jsp";
    private Akaun akaun = new Akaun();
    private HakmilikSebelum hakmilikSebelum = new HakmilikSebelum();
    private HakmilikAsal hakmilikAsal = new HakmilikAsal();
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private List<HakmilikAsal> senaraihakmilik = new ArrayList<HakmilikAsal>();
    private List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    protected AkaunDAO akaunDAO;
    @Inject
    protected KodTransaksiDAO kodTransaksiDAO;
    @Inject
    KutipanHasilManager manager;
    @Inject
    UtilityQTFTActionBean qtft;
    private Transaksi transaksi;
    private static final String kod_transaksi = "61401";
    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdfmonth = new SimpleDateFormat("yyyy");
    private String idTransaksi;
    private BigDecimal baki = BigDecimal.ZERO;
    Calendar caldate;
    int currentMonth;
    Boolean flag ;
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/senarai_keutamaan.jsp");
    }
    
    public Resolution toByPassKeutamaan() {
        
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        Date d = permohonan.getInfoAudit().getTarikhMasuk();
        senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
        senaraiPermohonanBelumSelesai = new ArrayList<Permohonan>();
        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
            List<Permohonan> list = permohonanService.getUrusanNotComplete(hp.getHakmilik().getIdHakmilik(), d);
            if (!list.isEmpty()) {
                for (Permohonan p : list) {
                    senaraiPermohonanBelumSelesai.add(p);
                }
            }
        }
        
        return new JSP("daftar/bypass_keutamaan.jsp");
    }
    
    public Resolution acquired() throws Exception {
        String acquiredBy = getContext().getRequest().getParameter("acquiredBy");
        String trkh = getContext().getRequest().getParameter("tarikh");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("acquiredBy ::" + acquiredBy);
        LOG.info("trkh :" + trkh);
        if (StringUtils.isBlank(acquiredBy)) {
            return new RedirectResolution(AquireActionBean.class).addParameter("idPermohonan", idPermohonan)
                    .addParameter("tarikhMasuk", trkh);
        }
        return new RedirectResolution(KernelActionBean.class).addParameter("idPermohonan", idPermohonan);
    }
    
    public Resolution seterusnya() throws Exception {
        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
        
        return new RedirectResolution(UtilityQTFTActionBean.class).addParameter("senaraiHakmilik", senaraiHakmilik);
        
    }
    
    public Resolution cetakGeran() {
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        String msg = (String) getContext().getRequest().getParameter("message");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        permohonan = permohonanService.findById(idPermohonan);
        if (permohonan != null) {
            senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
            
            listKandunganFolder = new ArrayList<KandunganFolder>();
            List<KandunganFolder> listTempKandunganFolder = new ArrayList<KandunganFolder>();
            List<Permohonan> listPermohonan = new ArrayList<Permohonan>();
            if (permohonan.getIdKumpulan() != null) {
                listPermohonan = permohonanService.findByIdKump(permohonan.getIdKumpulan());
            } else {
                listPermohonan.add(permohonan);
            }
            
            for (Permohonan permohonan : listPermohonan) {
                listTempKandunganFolder.addAll(permohonan.getFolderDokumen().getSenaraiKandungan());
            }
            for (KandunganFolder kf : listTempKandunganFolder) {
                if (ArrayUtils.contains(PAPAR_PRINT_DOKUMEN, kf.getDokumen().getKodDokumen().getKod())) {
                    listKandunganFolder.add(kf);
                }
            }
            
        }
//            Start:04/09/2013
        List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
        senaraiHakmilikBatal = new ArrayList<HakmilikSebelumPermohonan>();
        senaraiHakmilikBatalUnique = new ArrayList<HakmilikSebelumPermohonan>();
        
        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
        
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            l_temp = hp.getSenaraiHakmilikSebelum();
            
            for (HakmilikSebelumPermohonan sebelumPermohonan : l_temp) {
                if (sebelumPermohonan == null) {
                    continue;
                }
                senaraiHakmilikBatal.add(sebelumPermohonan);
            }
        }
        List<HakmilikSebelumPermohonan> l_temphsp = senaraiHakmilikBatal;
        LOG.debug("size hakmilik batal :" + l_temphsp.size());
        
        String tempIdHakmilikSejarah = "";
        for (HakmilikSebelumPermohonan hsp : l_temphsp) {
            if (hsp == null) {
                continue;
            }
            if (tempIdHakmilikSejarah.equals(hsp.getHakmilikSejarah())) {
                LOG.debug("NOT UNIQUE IDHAKMILIKSEJARAH FOUND!");
                continue;
            }
            senaraiHakmilikBatalUnique.add(hsp);
            tempIdHakmilikSejarah = hsp.getHakmilikSejarah();
            LOG.debug("size hakmilik batal :" + senaraiHakmilikBatal.size());
        }
        
        caldate = Calendar.getInstance();
        int A = caldate.get(Calendar.MONTH);
        currentMonth = A + 1;
//            End:04/09/2013
/*    
         if (permohonan.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("N"))// edit for nota
         {
         String tmpCode = permohonan.getKodUrusan().getKod().replace("-", "");
         NotaDaftarKodMap nm = NotaDaftarKodMap.valueOf(tmpCode);
         int kodGroup = nm.getCode();
  
         switch (kodGroup) {
         case 1: {
         groupFlag = 1;
         break;
         }
         case 2: {
         groupFlag = 2;
         break;
         }

         default:
         groupFlag = 0;
         break;
         }
         LOG.debug("GroupFlag = " + groupFlag);
         return new JSP("daftar/cetak_geran_nota.jsp");
         } else */
//  return new JSP("daftar/cetak_geran.jsp");
        return new JSP("daftar/cetak_geran_new.jsp");//if this problem, comment this and use above code.
    }
    
    public Resolution process() {
        LOG.info("----------------------------------------PROCESS START----------------------------------------");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        LOG.info("idPermohonan : " + permohonan.getIdPermohonan());
        List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
        List<HakmilikAsalPermohonan> a_temp = new ArrayList<HakmilikAsalPermohonan>();
        String hakmilik_temp = "";
        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
        String table = "sblm";
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            l_temp = hp.getSenaraiHakmilikSebelum();
        }
        
        for (HakmilikSebelumPermohonan h : l_temp) {
            listHakmilik.add(hakmilikDAO.findById(h.getHakmilikSejarah()));
        }
        
        if (listHakmilik.isEmpty()) {
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                a_temp = hp.getSenaraiHakmilikAsal();
            }
            
            for (HakmilikAsalPermohonan h : a_temp) {
                LOG.info("h.getHakmilikSejarah() : " + h.getHakmilikSejarah());
                table = "asal";
                listHakmilik.add(hakmilikDAO.findById(h.getHakmilikSejarah()));
            }
        }
        if (!listHakmilik.isEmpty()) {
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                hakmilik_temp = hp.getHakmilik().getIdHakmilik();
            }
        }
        
        LOG.info("=============================================== listHakmilik.size() : " + listHakmilik.size());
        LOG.info("=============================================== listHakmilik.size() : " + listHakmilik.size());
//        addSimpleError("PERHATIAN : Process telah berjaya. Sila ke menu Pertanyaan Hakmilik untuk semakan transaksi.");
        if (qtft.processTunggakan(listHakmilik, null, table)) {
//            addSimpleMessage("Process telah berjaya. Sila ke menu Pertanyaan Hakmilik untuk semakan transaksi.");
//            addSimpleError("PERHATIAN : Process telah berjaya. Sila ke menu Pertanyaan Hakmilik untuk semakan transaksi.")
            flag = true;
        } else if (!hakmilik_temp.equals("")) {
//             processTunggakan2(listHakmilik, null, table, hakmilik_temp);
//            addSimpleError("Process tidak berjaya. Sila hubungi pentadbir sistem untuk maklumat lanjut.");
        } else {
            
        }
        return cetakGeran();
//        return new JSP("daftar/cetak_geran_new.jsp");
    }
    
    public Resolution cukaiThnPertama() {
        LOG.info("----------------------------------------PROCESS START----------------------------------------");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanService.findById(permohonan.getIdPermohonan());
        LOG.info("idPermohonan : " + permohonan.getIdPermohonan());
        List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
        List<HakmilikAsalPermohonan> a_temp = new ArrayList<HakmilikAsalPermohonan>();
        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
        int year = Integer.parseInt(sdf.format(new java.util.Date()));
        year = year + 1;
        int tahunSemasa = year = Integer.parseInt(sdf.format(new java.util.Date()));
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            BigDecimal baki1;
            baki1 = hp.getHakmilik().getCukaiSebenar();
            baki = baki1.subtract(baki1);
            baki = baki.subtract(baki1);
            Akaun akaun = hp.getHakmilik().getAkaunCukai();
            akaun.setBaki(baki);
//            akaun.getSemuaTransaksi();

            transaksi = new Transaksi();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            transaksi.setCawangan(pengguna.getKodCawangan());
            transaksi.setKodTransaksi(kodTransaksiDAO.findById(kod_transaksi));
            transaksi.setAmaun(baki1);
            transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
            transaksi.setInfoAudit(ia);
            transaksi.setAkaunKredit(akaun);
            transaksi.setUntukTahun(year);
            transaksi.setKuantiti(0);
            transaksi.setTahunKewangan(tahunSemasa);
            
            manager.saveTrans(transaksi);
            manager.saveAkaun(akaun);
        }
        
        addSimpleMessage("Process telah berjaya. Sila ke menu Pertanyaan Hakmilik untuk semakan transaksi.");
        return new JSP("daftar/cetak_geran_new.jsp");
    }
    
    @HandlesEvent("Step2")
    public Resolution carianHakmilik() {
        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
        Hakmilik idHakmilik = hakmilikDAO.findById(permohonan.getIdPermohonan());
        Session s = sessionProvider.get();
        String sql = "SELECT u FROM etanah.model.HakmilikSebelum u WHERE u.hakmilikSebelum = :id";
        Query q = s.createQuery(sql);
        q.setString("id", hakmilikSebelum.getHakmilikSebelum());
        List<HakmilikSebelum> hs = q.list();
        for (HakmilikSebelum hm : hs) {
            senaraiAkaun.addAll(hm.getHakmilik().getSenaraiAkaun());
        }
        akaun = hakmilikDAO.findById(hakmilikSebelum.getHakmilikSebelum()).getAkaunCukai();
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!acquired", "!toByPassKeutamaan", "!cetakGeran", "!process"})
    public void rehydrate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        
        permohonan = permohonanService.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
        if (permohonan != null) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
            if (permohonan.getSenaraiHakmilik() != null) {
                senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
                Date d = permohonan.getInfoAudit().getTarikhMasuk();
                senaraiKaveat = new ArrayList<Map<String, String>>();
                senaraiTarikBalik = new ArrayList<Map<String, String>>();
                senaraiUrusan = new ArrayList<Map<String, String>>();
                
                if (StringUtils.isBlank(idHakmilik)) {
                    idHakmilik = senaraiHakmilikTerlibat.get(0).getHakmilik().getIdHakmilik();
                }
                LOG.debug("size hakmilik = " + senaraiHakmilikTerlibat.size());

//                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
//                    Hakmilik h = hp.getHakmilik();
//                    if (h == null) {
//                        continue;
//                    }
                if (isDebug) {
                    LOG.debug("hakmilik = " + idHakmilik);
                }
                List<Permohonan> list = permohonanService.getSenaraiPermohonan(new Date(), idHakmilik);
                String currId = "";
                for (Permohonan p : list) {
                    if (isDebug) {
                        LOG.debug("currId = " + currId);
                        LOG.debug("ID permohonan = " + p.getIdPermohonan());
                    }
                    if (currId.equals(p.getIdPermohonan())) {
                        continue;
                    }
                    
                    String ku = p.getKodUrusan().getJabatanNama();
                    if (!ku.equalsIgnoreCase("pendaftaran")) {
                        continue;
                    }
                    //filter by kod urusan
                    if (p.getKodUrusan() == null) {
                        continue;
                    }
                    String kodUrusan = p.getKodUrusan().getKod();
                    String namaUrusan = p.getKodUrusan().getNama();
                    
                    boolean flag = false;
                    
                    for (Map<String, String> map : senaraiUrusan) {
                        String id = map.get("idPermohonan");
                        if (StringUtils.isBlank(id)) {
                            continue;
                        }
                        if (id.equals(p.getIdPermohonan())) {
                            flag = true;
                            break;
                        }
                    }
                    
                    if (flag) {
                        continue;
                    }
                    
                    for (Map<String, String> map : senaraiKaveat) {
                        String id = map.get("idPermohonan");
                        if (StringUtils.isBlank(id)) {
                            continue;
                        }
                        if (id.equals(p.getIdPermohonan())) {
                            flag = true;
                            break;
                        }
                    }
                    
                    if (flag) {
                        continue;
                    }
                    
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("idPermohonan", p.getIdPermohonan());
                    map.put("kodUrusan", p.getKodUrusan().getKod());
                    map.put("urusan", p.getKodUrusan().getNama());
                    map.put("idHakmilik", idHakmilik);
                    List<Task> taskList = WorkFlowService.queryTasksByIdMohon(ctx, p.getIdPermohonan());
                    if (taskList.size() > 0) {
                        Task t = taskList.get(0);
                        if (t == null) {
                            continue;
                        }
                        String id = t.getSystemAttributes().getAcquiredBy();
                        Date date = t.getSystemAttributes().getAssignedDate().getTime();
                        String tarikhMasuk = "";
                        if (date != null) {
                            tarikhMasuk = sdf.format(d);
                        }
                        String stage = t.getSystemAttributes().getStage();
                        if (stage.equals("keputusan")) {
                            stage = "pendaftar";
                        } else if (stage.startsWith("agih")) {
                            stage = "awalan";
                        }
                        
                        map.put("stage", stage);
                        if (id == null || id.equalsIgnoreCase(pengguna.getIdPengguna())) {
                            map.put("acquired", "true");
                            map.put("acquiredBy", id);
                            map.put("tarikh", tarikhMasuk);
                            map.put("taskId", t.getSystemAttributes().getTaskId());
                        }
//                            String stage = t.getSystemAttributes().getStage();
//                            map.put("stage",stage);
                    } else {
                        LOG.debug("query task by admin");
                        
                        taskList = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
                        
                        if (taskList.size() > 0) {
                            Task t = taskList.get(0);
                            String stage = t.getSystemAttributes().getStage();
                            if (stage.equals("keputusan")) {
                                stage = "pendaftar";
                            } else if (stage.startsWith("agih")) {
                                stage = "awalan";
                            }
                            map.put("stage", stage);
                        }
                    }
                    
                    if (kodUrusan.startsWith("KV")) {
                        senaraiKaveat.add(map);
                    } else if (namaUrusan.contains("Tarik Balik")) {
                        senaraiTarikBalik.add(map);
                    } else {
                        // add by azri: skip "Perserahan SB" and only for "N9"
                        if ("05".equals(conf.getProperty("kodNegeri"))) {
                            if (!"SB".equalsIgnoreCase(p.getKodUrusan().getKodPerserahan().getKod())) {
                                senaraiUrusan.add(map);
                            }
                        } else {
                            senaraiUrusan.add(map);
                        }
                    }
                    currId = p.getIdPermohonan();
                }
//                }
            }
        }
    }
    
    public List<Map<String, String>> getSenaraiKaveat() {
        return senaraiKaveat;
    }
    
    public void setSenaraiKaveat(List<Map<String, String>> senaraiKaveat) {
        this.senaraiKaveat = senaraiKaveat;
    }
    
    public List<Map<String, String>> getSenaraiTarikBalik() {
        return senaraiTarikBalik;
    }
    
    public void setSenaraiTarikBalik(List<Map<String, String>> senaraiTarikBalik) {
        this.senaraiTarikBalik = senaraiTarikBalik;
    }
    
    public List<Map<String, String>> getSenaraiUrusan() {
        return senaraiUrusan;
    }
    
    public void setSenaraiUrusan(List<Map<String, String>> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }
    
    public List<HakmilikPermohonan> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }
    
    public void setSenaraiHakmilikTerlibat(List<HakmilikPermohonan> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }
    
    public List<Permohonan> getSenaraiPermohonanBelumSelesai() {
        return senaraiPermohonanBelumSelesai;
    }
    
    public void setSenaraiPermohonanBelumSelesai(List<Permohonan> senaraiPermohonanBelumSelesai) {
        this.senaraiPermohonanBelumSelesai = senaraiPermohonanBelumSelesai;
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }
    
    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    public int getGroupFlag() {
        return groupFlag;
    }
    
    public void setGroupFlag(int groupFlag) {
        this.groupFlag = groupFlag;
    }
    
    public List<HakmilikSebelumPermohonan> getSenaraiHakmilikBatalUnique() {
        return senaraiHakmilikBatalUnique;
    }
    
    public void setSenaraiHakmilikBatalUnique(List<HakmilikSebelumPermohonan> senaraiHakmilikBatalUnique) {
        this.senaraiHakmilikBatalUnique = senaraiHakmilikBatalUnique;
    }
    
    public List<HakmilikSebelumPermohonan> getSenaraiHakmilikBatal() {
        return senaraiHakmilikBatal;
    }
    
    public void setSenaraiHakmilikBatal(List<HakmilikSebelumPermohonan> senaraiHakmilikBatal) {
        this.senaraiHakmilikBatal = senaraiHakmilikBatal;
    }
    
    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }
    
    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }
    
    public Akaun getAkaun() {
        return akaun;
    }
    
    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }
    
    public AkaunDAO getAkaunDAO() {
        return akaunDAO;
    }
    
    public void setAkaunDAO(AkaunDAO akaunDAO) {
        this.akaunDAO = akaunDAO;
    }
    
    public KodTransaksiDAO getKodTransaksiDAO() {
        return kodTransaksiDAO;
    }
    
    public void setKodTransaksiDAO(KodTransaksiDAO kodTransaksiDAO) {
        this.kodTransaksiDAO = kodTransaksiDAO;
    }
    
    public KutipanHasilManager getManager() {
        return manager;
    }
    
    public void setManager(KutipanHasilManager manager) {
        this.manager = manager;
    }
    
    public List<HakmilikAsal> getSenaraihakmilik() {
        return senaraihakmilik;
    }
    
    public void setSenaraihakmilik(List<HakmilikAsal> senaraihakmilik) {
        this.senaraihakmilik = senaraihakmilik;
    }
    
    public List<Hakmilik> getListHakmilik() {
        return listHakmilik;
    }
    
    public void setListHakmilik(List<Hakmilik> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }
    
    public SimpleDateFormat getSdf() {
        return sdf;
    }
    
    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
    
    public SimpleDateFormat getSdfmonth() {
        return sdfmonth;
    }
    
    public void setSdfmonth(SimpleDateFormat sdfmonth) {
        this.sdfmonth = sdfmonth;
    }
    
    public Calendar getCaldate() {
        return caldate;
    }
    
    public void setCaldate(Calendar caldate) {
        this.caldate = caldate;
    }
    
    public int getCurrentMonth() {
        return currentMonth;
    }
    
    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
    
}
