/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import org.apache.commons.lang.StringUtils;
import com.google.inject.Inject;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodKadarBayaran;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanKos;
import etanah.sequence.GeneratorNoResit;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.KutipanHasilManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/jenis_bayaran")
public class BayaranUpahUkurActionBean extends BasicTabActionBean {
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KutipanHasilManager manager;
    @Inject
    GeneratorNoResit noResitGenerator;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    List<SenaraiTuntutKos> listTuntutanKos = new ArrayList();
    private Permohonan permohonan;
    PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
    private String idKos;
    private String item;
    private String amaunTuntutan;
    private String kodNegeri;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private static final Logger LOG = Logger.getLogger(BayaranUpahUkurActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    BigDecimal jumlahAmaun = new BigDecimal(0);
    
    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("strata/pbbm/maklumat_bayaran.jsp").addParameter("tab", "true");
    }
    
    public Resolution maklumatBayaranNs() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("strata/pbbm/maklumat_bayaran_NS.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm2() {
        item = getContext().getRequest().getParameter("item");
        amaunTuntutan = getContext().getRequest().getParameter("amaunTuntutan");
        idKos = getContext().getRequest().getParameter("idKos");
        return new JSP("strata/pbbm/kemaskini_maklumat_bayaran.jsp").addParameter("popup", "true");
    }
    
    public Resolution showForm3() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("kaunter/strata/bayaran_pelbagai.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm4() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("kaunter/strata/bayaran_pelbagai_2.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        BigDecimal b = new BigDecimal(0);
        KodKadarBayaran kkb = new KodKadarBayaran();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        int jumlahPetak = 0;
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        listTuntutanKos.clear();
        jumlahAmaun = jumlahAmaun.ZERO;
        kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("----kodNegeri--------::" + kodNegeri);
        /* if (kodNegeri.equals("04")) {
         LOG.info("----melaka--------::");
         String[] kodt = new String[]{"S01", "S02", "S03"};
         for (String kodT : kodt) {
         PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
         SenaraiTuntutKos ltk = new SenaraiTuntutKos();
         KodTuntut kodTuntut = new KodTuntut();
         kodTuntut = kodTuntutDAO.findById(kodT);
         if (kodTuntut == null) {
         kodTuntut = new KodTuntut();
         }
         ltk.setKodTuntut(kodTuntut);
         ptk = strService.findByIDMohon(idPermohonan, kodT);
         if (ptk != null) {
         if (kodT.equals("S03")) {
         List<PermohonanBangunan> ab = strService.findByIDPermohonan1(idPermohonan);
         for (PermohonanBangunan mb : ab) {
         jumlahPetak = jumlahPetak + mb.getBilanganPetak();
         LOG.info("----if Ptk not null-----Jumlah Petak--------::" + jumlahPetak);
         }
         b = new BigDecimal(jumlahPetak);
         if ((b.intValue() != 0) && (b.intValue() < 30)) {
         b = strService.getKodByKat(permohonan.getKodUrusan().getKod(), "1").getKadar();
         } else if ((b.intValue() != 0) && ((b.intValue() > 30))) {
         b = b.multiply(new BigDecimal(10));
         }
         }
         ltk.setJumlahPetak(jumlahPetak);
         jumlahAmaun = jumlahAmaun.add(ptk.getAmaunTuntutan());
         ltk.setMohonTuntutKos(ptk);
         } else {
         ptk = new PermohonanTuntutanKos();
         if (kodT.equals("S03")) {
         List<PermohonanBangunan> ab = strService.findByIDPermohonan1(idPermohonan);
         for (PermohonanBangunan mb : ab) {
         jumlahPetak = jumlahPetak + mb.getBilanganPetak();
         LOG.info("-----if Ptk null----Jumlah Petak--------::" + jumlahPetak);
         }
         b = new BigDecimal(jumlahPetak);
         if ((b.intValue() != 0) && (b.intValue() < 30)) {
         b = strService.getKodByKat(permohonan.getKodUrusan().getKod(), "1").getKadar();
         } else if ((b.intValue() != 0) && ((b.intValue() > 30))) {
         b = b.multiply(new BigDecimal(10));
         }
         }
         ptk.setAmaunTuntutan(b);
         jumlahAmaun = jumlahAmaun.add(ptk.getAmaunTuntutan());
         ltk.setJumlahPetak(jumlahPetak);
         ltk.setMohonTuntutKos(ptk);
         }
         listTuntutanKos.add(ltk);
         }
         } */
        
        List<PermohonanTuntutanKos> mtk = strService.findMohonTuntutKos(idPermohonan);
        if (kodNegeri.equals("05") || kodNegeri.equals("04")) {
            String[] kodtNS = new String[]{"STR01", "STR02", "STR03", "STR07"};
            for (String kodT : kodtNS) {
                int jumlahPetak1 = 0;
                PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
                SenaraiTuntutKos ltk = new SenaraiTuntutKos();
                KodTuntut kodTuntut = new KodTuntut();
                kodTuntut = kodTuntutDAO.findById(kodT);
                if (kodTuntut == null) {
                    kodTuntut = new KodTuntut();
                }
                
                ltk.setKodTuntut(kodTuntut);
                ptk = strService.findByIDMohon(idPermohonan, kodT);
                LOG.info("----ptk--------::" + ptk);
                if (ptk != null) {
                    LOG.info("--ptk--not null--------::");
                    LOG.info("----kodT--------::" + kodT);
//yus comment 25052019                    
//                    if (kodT.equals("STR03")) {
//                        LOG.info("----STR03--------::");
//                        List<PermohonanBangunan> ab = strService.findByIDPermohonan1(idPermohonan);
//                        for (PermohonanBangunan mb : ab) {
//                            LOG.info("--Permohonan--::" + permohonan);
//                            if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
//                                LOG.info("--PHPP--::");
//                                jumlahPetak1 = 1;
//                            } else {
//                                jumlahPetak1 += mb.getBilanganPetak();
//                            }
//                            LOG.info("----if Ptk not null-----Jumlah Petak--------::" + jumlahPetak1);
//                            
//                        }
//                        LOG.info("----ptk.getAmaunSeunit()---STR03-----::" + ptk.getAmaunSeunit());
//                        if (ptk.getAmaunSeunit() == new BigDecimal(0) || ptk.getAmaunSeunit() == null) {
//                            ptk.setAmaunSeunit(new BigDecimal(30));
//                            ptk.setAmaunTuntutan(new BigDecimal(30).multiply(new BigDecimal(jumlahPetak1)));
//                        } else {
//                            ptk.setAmaunTuntutan(ptk.getAmaunSeunit().multiply(new BigDecimal(jumlahPetak1)));
//                        }
//                    }
//                    ltk.setJumlahPetak(jumlahPetak1);
//yus comment 25052019 end                    
                    if (kodT.equals("STR02") || kodT.equals("STR03") ) { //yus add 25052019 || kodT.equals("STR03")
                        LOG.info("----STR02--------::");
                        List<PermohonanBangunan> ab = strService.findByIDPermohonan1(idPermohonan);
                        for (PermohonanBangunan mb : ab) {
                            LOG.info("--Permohonan--::" + permohonan);
                            if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
                                LOG.info("--PHPP--::");
                                jumlahPetak1 = 1;
                            } else {
                                jumlahPetak1 += mb.getBilanganPetak();
                            }
                            LOG.info("----if Ptk not null-----Jumlah Petak--------::" + jumlahPetak1);
                        }
                        LOG.info("----ptk.getAmaunSeunit()----STR02----::" + ptk.getAmaunSeunit());
                        if (ptk.getAmaunSeunit() == new BigDecimal(0) || ptk.getAmaunSeunit() == null) {
                            ptk.setAmaunSeunit(new BigDecimal(20));
                            ptk.setAmaunTuntutan(new BigDecimal(20).multiply(new BigDecimal(ptk.getKuantiti())));
                        } else {
                            ptk.setAmaunTuntutan(ptk.getAmaunSeunit().multiply(new BigDecimal(ptk.getKuantiti())));
                        }
                        ltk.setJumlahPetak(ptk.getKuantiti());
                        
                    }
                    if (kodT.equals("STR07")) {
                        LOG.info("----STR07--------::");
                        List<PermohonanBangunan> ab = strService.findByIDPermohonan1(idPermohonan);
                        for (PermohonanBangunan mb : ab) {
                            LOG.info("--Permohonan--::" + permohonan);
                            jumlahPetak1 += mb.getBilanganPetak();
                            LOG.info("----if Ptk not null-----Jumlah Petak--------::" + jumlahPetak1);
                        }
                        LOG.info("----ptk.getAmaunSeunit()----STR07----::" + ptk.getAmaunSeunit());
                        
                        for (PermohonanTuntutanKos mtk1 : mtk) {
                            if (mtk1.getKodTuntut().getKod().equals("STR07")) {
                                ptk.setAmaunSeunit(mtk1.getAmaunSeunit());
                                ptk.setAmaunTuntutan(mtk1.getAmaunTuntutan());
                            }
                        }
                    }
                    if (kodT.equals("STR01")) {
                        LOG.info("--STR01--::");
                        if (ptk.getAmaunTuntutan() == null) {
                            ptk.setAmaunTuntutan(new BigDecimal(0));
                        }
                    }
                    LOG.info("--------ltk.getJumlahPetak----STR02----::" + ltk.getJumlahPetak());
                    if (ptk.getAmaunTuntutan() != null) {
                        jumlahAmaun = jumlahAmaun.add(ptk.getAmaunTuntutan());
                    }
                    ltk.setMohonTuntutKos(ptk);
                } else {
                    LOG.info("--ptk--null--------::");
                    ptk = new PermohonanTuntutanKos();
                    if (kodT.equals("STR03")) {
                        LOG.info("--STR03--::");
                        List<PermohonanBangunan> ab = strService.findByIDPermohonan1(idPermohonan);
                        for (PermohonanBangunan mb : ab) {
                            LOG.info("--Permohonan--::" + permohonan);
                            if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
                                LOG.info("--PHPP--::");
                                jumlahPetak1 = 1;
                            } else {
                                jumlahPetak1 += mb.getBilanganPetak();
                            }
                            LOG.info("----if Ptk not null-----Jumlah Petak--------::" + jumlahPetak1);
                        }
                        ptk.setAmaunSeunit(new BigDecimal(30));
                        ptk.setAmaunTuntutan(new BigDecimal(30).multiply(new BigDecimal(jumlahPetak1)));
                    }
                    if (kodT.equals("STR02")) {
                        LOG.info("--STR02--::");
                        List<PermohonanBangunan> ab = strService.findByIDPermohonan1(idPermohonan);
                        for (PermohonanBangunan mb : ab) {
                            LOG.info("--Permohonan--::" + permohonan);
                            if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
                                LOG.info("--PHPP--::");
                                jumlahPetak1 = 1;
                            } else {
                                jumlahPetak1 += mb.getBilanganPetak();
                            }
                            LOG.info("----if Ptk not null-----Jumlah Petak--------::" + jumlahPetak1);
                        }
                        ptk.setAmaunTuntutan(new BigDecimal(10).multiply(new BigDecimal(jumlahPetak1)));
                        ptk.setAmaunSeunit(new BigDecimal(10));
                    }
                    
                    if (kodT.equals("STR01")) {
                        LOG.info("--STR01--::");
                        ptk.setAmaunTuntutan(new BigDecimal(0));
                    }
                    
                    ltk.setJumlahPetak(jumlahPetak1);
                    if (kodT.equals("STR07")) {
                        LOG.info("--STR07--::");
                        ptk.setAmaunSeunit(new BigDecimal(100));
                        ptk.setAmaunTuntutan(new BigDecimal(100));
                    }
                    
                    jumlahAmaun = jumlahAmaun.add(ptk.getAmaunTuntutan());
                    ltk.setMohonTuntutKos(ptk);
                }
                listTuntutanKos.add(ltk);
            }
        }
    }
    
    public Resolution setSemula() {
        rehydrate();
        
        return new RedirectResolution(BayaranUpahUkurActionBean.class, "maklumatBayaranNs");
    }
    
    public Resolution SimpanMaklumatBayaran() {
        
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan mohon = null;
        if (idPermohonan != null) {
            mohon = permohonanDAO.findById(idPermohonan);
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanTuntutan pt = new PermohonanTuntutan();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String error = "";
        String msg = "";
        boolean flag = true;
        LOG.info("IdPermohonan : " + mohon.getIdPermohonan());
        for (SenaraiTuntutKos ltk : listTuntutanKos) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            mohonTuntutKos = ltk.getMohonTuntutKos();
            if (ltk.getMohonTuntutKos() != null) {
                if (StringUtils.isNotEmpty(ltk.getMohonTuntutKos().getAmaunTuntutan().toString())) {
                    KodCawangan kodCawangan = new KodCawangan();
                    kodCawangan.setKod(mohon.getCawangan().getKod());
                    KodTransaksiTuntut ktt = new KodTransaksiTuntut();
                    ktt = kodTransaksiTuntutDAO.findById("ST1");
                    pt = comm.findMohonTuntutByKod(mohon.getIdPermohonan(), ktt.getKod());
                    if (pt == null) {
                        pt = new PermohonanTuntutan();
                        pt.setInfoAudit(infoAudit);
                        pt.setKodTransaksiTuntut(ktt);
                        pt.setPermohonan(mohon);
                        pt.setCawangan(kodCawangan);
                        pt.setTarikhTuntutan(new Date());
                    } else {
                        infoAudit = pt.getInfoAudit();
                        infoAudit.setTarikhKemaskini(new Date());
                    }
                    LOG.info("AmaunTuntutan : " + ltk.getMohonTuntutKos().getAmaunTuntutan());
                    mohonTuntutKos.setPermohonan(mohon);
                    mohonTuntutKos.setCawangan(kodCawangan);
                    mohonTuntutKos.setInfoAudit(infoAudit);
                    mohonTuntutKos.setKodTransaksi(ltk.getKodTuntut().getKodKewTrans());
                    mohonTuntutKos.setKodTuntut(ltk.getKodTuntut());
                    mohonTuntutKos.setAmaunTuntutan(ltk.getMohonTuntutKos().getAmaunTuntutan());
                    mohonTuntutKos.setItem(ltk.getKodTuntut().getNama());
                    comm.setBayaran(mohonTuntutKos, pt, peng);
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            msg = "Maklumat telah berjaya disimpan."; //check kodtrans.. dah setle br repair msg ni

            return new RedirectResolution(BayaranUpahUkurActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
        } else {
            error = "Sila isikan maklumat bayaran yang mandatori";
            
            return new RedirectResolution(BayaranUpahUkurActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
        }
    }

    //added for Ns
    public Resolution SimpanMaklumatBayaranNS() {
        
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan mohon = null;
        if (idPermohonan != null) {
            mohon = permohonanDAO.findById(idPermohonan);
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanTuntutan pt = new PermohonanTuntutan();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String error = "";
        String msg = "";
        boolean flag = true;
        LOG.info("IdPermohonan : " + mohon.getIdPermohonan());
        for (SenaraiTuntutKos ltk : listTuntutanKos) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            mohonTuntutKos = ltk.getMohonTuntutKos();
            if (ltk.getMohonTuntutKos() != null) {
                if (StringUtils.isNotEmpty(ltk.getMohonTuntutKos().getAmaunTuntutan().toString())) {
                    KodCawangan kodCawangan = new KodCawangan();
                    kodCawangan.setKod(mohon.getCawangan().getKod());
                    KodTransaksiTuntut ktt = new KodTransaksiTuntut();
                    ktt = kodTransaksiTuntutDAO.findById("ST1");
                    pt = comm.findMohonTuntutByKod(mohon.getIdPermohonan(), ktt.getKod());
                    if (pt == null) {
                        pt = new PermohonanTuntutan();
                        pt.setInfoAudit(infoAudit);
                        pt.setKodTransaksiTuntut(ktt);
                        pt.setPermohonan(mohon);
                        pt.setCawangan(kodCawangan);
                        pt.setTarikhTuntutan(new Date());
                    } else {
                        infoAudit = pt.getInfoAudit();
                        infoAudit.setTarikhKemaskini(new Date());
                    }
                    LOG.info("AmaunTuntutan : " + ltk.getMohonTuntutKos().getAmaunTuntutan());
                    mohonTuntutKos.setPermohonan(mohon);
                    mohonTuntutKos.setCawangan(kodCawangan);
                    mohonTuntutKos.setInfoAudit(infoAudit);
                    mohonTuntutKos.setKodTransaksi(ltk.getKodTuntut().getKodKewTrans());
                    mohonTuntutKos.setKodTuntut(ltk.getKodTuntut());
                    mohonTuntutKos.setAmaunTuntutan(ltk.getMohonTuntutKos().getAmaunTuntutan());
                    //added
                    mohonTuntutKos.setKuantiti(ltk.getJumlahPetak());
                    mohonTuntutKos.setAmaunSeunit(ltk.getMohonTuntutKos().getAmaunSeunit());
                    mohonTuntutKos.setItem(ltk.getKodTuntut().getNama());
                    comm.setBayaran(mohonTuntutKos, pt, peng);
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            msg = "Maklumat telah berjaya disimpan."; //check kodtrans.. dah setle br repair msg ni

            return new RedirectResolution(BayaranUpahUkurActionBean.class, "maklumatBayaranNs").addParameter("error", error).addParameter("message", msg);
        } else {
            error = "Sila isikan maklumat bayaran yang mandatori";
            
            return new RedirectResolution(BayaranUpahUkurActionBean.class, "maklumatBayaranNs").addParameter("error", error).addParameter("message", msg);
        }
    }
    
    public Resolution deleteBayaran() {
        
        idKos = getContext().getRequest().getParameter("idKos");
        
        if (idKos != null) {
            
            PermohonanTuntutanKos byr = permohonanTuntutanKosDAO.findById(Long.parseLong(idKos));
            if (byr != null) {
                strService.deleteBayaran(byr);
            }
        }
        rehydrate();
        
        return new RedirectResolution(BayaranUpahUkurActionBean.class, "showForm");
    }
    
    public Resolution UpdateMaklumatBayaran() {
        
        idKos = getContext().getRequest().getParameter("idKos");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String error = "";
        String msg = "";
        
        PermohonanTuntutanKos ptk = permohonanTuntutanKosDAO.findById(Long.parseLong(idKos));
        
        ptk.setPermohonan(permohonan);
        KodCawangan kodCawangan = new KodCawangan();
        kodCawangan.setKod(permohonan.getCawangan().getKod());
        ptk.setCawangan(kodCawangan);
        ptk.setInfoAudit(infoAudit);
        ptk.setItem(item);
        BigDecimal bd = new BigDecimal(amaunTuntutan);
        ptk.setAmaunTuntutan(bd);
        strService.updateBayaran(ptk);
        msg = "Maklumat telah berjaya disimpan.";
        
        return new RedirectResolution(BayaranUpahUkurActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
        
    }
    
    public BigDecimal getJumlahAmaun() {
        return jumlahAmaun;
    }
    
    public void setJumlahAmaun(BigDecimal jumlahAmaun) {
        this.jumlahAmaun = jumlahAmaun;
    }
    
    public List<SenaraiTuntutKos> getListTuntutanKos() {
        return listTuntutanKos;
    }
    
    public void setListTuntutanKos(List<SenaraiTuntutKos> listTuntutanKos) {
        this.listTuntutanKos = listTuntutanKos;
    }
    
    public String getAmaunTuntutan() {
        return amaunTuntutan;
    }
    
    public void setAmaunTuntutan(String amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }
    
    public String getItem() {
        return item;
    }
    
    public void setItem(String item) {
        this.item = item;
    }
    
    public String getIdKos() {
        return idKos;
    }
    
    public void setIdKos(String idKos) {
        this.idKos = idKos;
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }
    
    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    public String getKodNegeri() {
        return kodNegeri;
    }
    
    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
