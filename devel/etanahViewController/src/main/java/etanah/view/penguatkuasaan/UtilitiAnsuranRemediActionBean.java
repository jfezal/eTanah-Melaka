/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.KompaunDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import java.math.BigDecimal;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.KodTransaksiDAO;

import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodTuntut;
import etanah.model.Kompaun;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.report.ReportUtil;

import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/utiliti_ansuran_remedi")
public class UtilitiAnsuranRemediActionBean extends AbleActionBean {
    
    private static Logger logger = Logger.getLogger(UtilitiAnsuranRemediActionBean.class);
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pengguna;
    private Long idDokumen;
    private boolean idPermohonanNotExist = Boolean.FALSE;
    private List<Kompaun> senaraiKompaun;
    private List<Kompaun> senaraiKompaunUpdate = new ArrayList<Kompaun>();
    private String amaunBaru;
    private Kompaun kompaun;
    private String idOks;
    private AduanOrangKenaSyak oks;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private PermohonanTuntutanKos ptk;
    private PermohonanTuntutan pt;
    private PermohonanTuntutanButiran ptb;
    private String tempohHari;
    private boolean statusAnsuran = Boolean.FALSE;
    private String idKompaun;
    private static final long MILISECONDS_PER_DAY = 24 * 60 * 60 * 1000;
    private Date currentDate = new Date();
    private static final int DEFAULT_MONTH = 6;
    private String tarikhAkhirBayaran1;
    private String tarikhAkhirBayaran2;
    private String tarikhAkhirBayaran3;
    private String tarikhAkhirBayaran4;
    private String tarikhAkhirBayaran5;
    private String tarikhAkhirBayaran6;
    private BigDecimal amaunBulan1;
    private BigDecimal amaunBulan2;
    private BigDecimal amaunBulan3;
    private BigDecimal amaunBulan4;
    private BigDecimal amaunBulan5;
    private BigDecimal amaunBulan6;
    private BigDecimal jumlahKeseluruhan;
    private BigDecimal totalPayment;
    private int jumlahHari;
    
    class KompaunCache implements Serializable {
        
        Permohonan permohonan;
        Kompaun kompaun;
    }
    
    @DefaultHandler
    public Resolution findKompaun() {
        logger.info("------------findKompaun-------------");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_ansuran_remedi.jsp");
    }
    
    public Resolution cariSemula() {
        logger.info("------------cariSemula-------------");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();
        
        idPermohonan = "";
        senaraiKompaun.clear();
        senaraiKompaunUpdate.clear();
        
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_ansuran_remedi.jsp");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiKompaun = new ArrayList<Kompaun>();
        getUrusanfromSession();
        
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            
            if (permohonan != null) {
                senaraiKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "BR");
                logger.info("size senaraiKompaun masa rehydrate: " + senaraiKompaun.size());
                
            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
        System.out.println("idPermohonan1 rehydrate : " + idPermohonan);
    }
    
    public Resolution searchKompaun() {
        logger.info("------------searchKompaun-------------");
        System.out.println("idPermohonan : " + idPermohonan);
        //clear data in session before search new data
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();
        
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            
            if (permohonan != null) {
                senaraiKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "BR");
                logger.info("size senaraiKompaun : " + senaraiKompaun.size());
                
                saveToSession(ctx);
                
            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
                senaraiKompaun.clear();
                senaraiKompaunUpdate.clear();
            }
        } else {
            addSimpleError("Sila masukkan id permohonan.");
        }
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_ansuran_remedi.jsp");
    }
    
    public final void getUrusanfromSession() {
        logger.debug("get id permohonan from session");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        try {
            KompaunCache u = (KompaunCache) ctx.getWorkData();
            if (u != null) {
                permohonan = u.permohonan;
                kompaun = u.kompaun;
                if (permohonan != null) {
                    idPermohonan = permohonan.getIdPermohonan();
                    System.out.println("idPermohonan getUrusanfromSession : " + idPermohonan);
                }
            } else {
                logger.debug("no data in session");
            }
        } catch (Exception ex) {
            logger.error(ex);
            ctx.removeWorkdata();
        }
        
    }
    
    public final void saveToSession(etanahActionBeanContext ctx) {
        logger.debug("saveToSession");
        KompaunCache u = (KompaunCache) ctx.getWorkData();
        if (u == null) {
            u = new KompaunCache();
        }
        u.permohonan = permohonan;
        System.out.println("saveToSession u.permohonan : " + u.permohonan.getIdPermohonan());
        
        ctx.setWorkData(u);
    }
    
    public Resolution simpan() {
        logger.info("------------simpanAnsuran--------------");
        
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        try {
            logger.info("size senaraiKompaun masa simpan: " + senaraiKompaun.size());
            for (int i = 0; i < senaraiKompaun.size(); i++) {
                Kompaun k = senaraiKompaun.get(i);
                if (k != null) {
                    /* save table Kompaun*/
                    ia = k.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    k.setInfoAudit(ia);
                    k.setTarikhDihantar(new java.util.Date());
                    k.setRayuan('Y');
                    enforcementService.simpanKompaun(k);
                    
                    if (k.getPermohonanTuntutanKos() != null) {
                        ptk = permohonanTuntutanKosDAO.findById(k.getPermohonanTuntutanKos().getIdKos());
                        ptk.setItem("BAYARAN REMEDI (ANSURAN)");
                        
                        ia = ptk.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ptk.setInfoAudit(ia);
                        ptk.setKodTransaksi(kodTransaksiDAO.findById("79501"));
                        
                        enforceService.simpanBayaran(ptk);
                    }
                }
                
                Date c1 = k.getTarikhDihantar();
                Calendar cal = Calendar.getInstance();
                cal.setTime(c1);
                cal.add(Calendar.MONTH, DEFAULT_MONTH);

                //to calculate month by month
                Date c2 = k.getTarikhDihantar();
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(c2);
                
                String tarikhAkhirBayar = sdf.format(cal.getTime());
                System.out.println("tarikh akhir bayar : " + tarikhAkhirBayar);
                
                jumlahHari = getDaysBetweenDates(cal.getTime(), currentDate);
                System.out.println("jumlah hari after calculate :" + jumlahHari);

//                k.setTempohHari(jumlahHari);
                enforcementService.simpanKompaun(k);
                //TO DO: get total days between two dates

                jumlahKeseluruhan = BigDecimal.ZERO;
                totalPayment = BigDecimal.ZERO;
                BigDecimal bilBulan = new BigDecimal(DEFAULT_MONTH);
                
                for (int j = 1; j <= DEFAULT_MONTH; j++) {
                    if (j == 1) {
                        cal2.add(Calendar.MONTH, j);
                        tarikhAkhirBayaran1 = sdf.format(cal2.getTime());
                        amaunBulan1 = k.getAmaun().divide(bilBulan, BigDecimal.ROUND_FLOOR);

//                        double convertDouble = 0.00;
//                        convertDouble = Double.parseDouble(k.getAmaun().toString()) / 6;
//                        System.out.println(convertDouble);
//                        DecimalFormat decim = new DecimalFormat("#.#");
//                        convertDouble = Double.parseDouble(decim.format(convertDouble));
//                        decim = new DecimalFormat("0.00");
//                        convertDouble = Double.parseDouble(decim.format(convertDouble));
//                        amaunBulan1 = BigDecimal.valueOf(convertDouble);
                        jumlahKeseluruhan = jumlahKeseluruhan.add(amaunBulan1);
                        System.out.println("1 : Jumlah keseluruhan : " + jumlahKeseluruhan);
                    } else if (j == 2) {
                        cal2.add(Calendar.MONTH, 1);
                        tarikhAkhirBayaran2 = sdf.format(cal2.getTime());
                        amaunBulan2 = k.getAmaun().divide(bilBulan, BigDecimal.ROUND_FLOOR);
                        jumlahKeseluruhan = jumlahKeseluruhan.add(amaunBulan2);
                        System.out.println("2 : Jumlah keseluruhan : " + jumlahKeseluruhan);
                    } else if (j == 3) {
                        cal2.add(Calendar.MONTH, 1);
                        tarikhAkhirBayaran3 = sdf.format(cal2.getTime());
                        amaunBulan3 = k.getAmaun().divide(bilBulan, BigDecimal.ROUND_FLOOR);
                        jumlahKeseluruhan = jumlahKeseluruhan.add(amaunBulan3);
                        System.out.println("3 : Jumlah keseluruhan : " + jumlahKeseluruhan);
                    } else if (j == 4) {
                        cal2.add(Calendar.MONTH, 1);
                        tarikhAkhirBayaran4 = sdf.format(cal2.getTime());
                        amaunBulan4 = k.getAmaun().divide(bilBulan, BigDecimal.ROUND_FLOOR);
                        jumlahKeseluruhan = jumlahKeseluruhan.add(amaunBulan4);
                        System.out.println("4 : Jumlah keseluruhan : " + jumlahKeseluruhan);
                    } else if (j == 5) {
                        cal2.add(Calendar.MONTH, 1);
                        tarikhAkhirBayaran5 = sdf.format(cal2.getTime());
                        amaunBulan5 = k.getAmaun().divide(bilBulan, BigDecimal.ROUND_FLOOR);
                        jumlahKeseluruhan = jumlahKeseluruhan.add(amaunBulan5);
                        System.out.println("5 : Jumlah keseluruhan : " + jumlahKeseluruhan);
                        totalPayment = totalPayment.add(jumlahKeseluruhan);
                    } else if (j == 6) {
                        cal2.add(Calendar.MONTH, 1);
                        tarikhAkhirBayaran6 = sdf.format(cal2.getTime());
                        jumlahKeseluruhan = k.getAmaun().subtract(jumlahKeseluruhan);
                        amaunBulan6 = jumlahKeseluruhan;
                        System.out.println("6 : Jumlah keseluruhan : " + jumlahKeseluruhan);
                    }
                }
                
                totalPayment = totalPayment.add(amaunBulan6);
                System.out.println("############## JADUAL PEMBAYARAN ##############");
                System.out.print("Tarikh Bayaran        ");
                System.out.println("Jumlah");
                System.out.print("______________        ");
                System.out.println("______________");
                System.out.println("Bulan 1 :" + tarikhAkhirBayaran1 + "        " + amaunBulan1);
                System.out.println("Bulan 2 :" + tarikhAkhirBayaran2 + "        " + amaunBulan2);
                System.out.println("Bulan 3 :" + tarikhAkhirBayaran3 + "        " + amaunBulan3);
                System.out.println("Bulan 4 :" + tarikhAkhirBayaran4 + "        " + amaunBulan4);
                System.out.println("Bulan 5 :" + tarikhAkhirBayaran5 + "        " + amaunBulan5);
                System.out.println("Bulan 6 :" + tarikhAkhirBayaran6 + "        " + amaunBulan6);
                System.out.println(":::::::::Jumlah keseluruhan : " + totalPayment);
                
                
                KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
                kodTransTuntut = enforceService.findKodTransTuntutByKod("ENFKOMPN");
                
                ptk = permohonanTuntutanKosDAO.findById(k.getPermohonanTuntutanKos().getIdKos());
                ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());
                
                if (ptb != null) {
                    pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
                }

                /* save table PermohonanTuntutan*/
                
                pt.setInfoAudit(ia);
                pt.setKodTransaksiTuntut(kodTransTuntut);
                pt.setTarikhTuntutan(new java.util.Date());
                pt.setTempoh(jumlahHari);
                pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                enforceService.simpanPermohonanTuntutan(pt);
                
                statusAnsuran = true;
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_ansuran_remedi.jsp");
    }
    
    public static int getDaysBetweenDates(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        int days = (int) Math.floor(diff / MILISECONDS_PER_DAY);
        return Math.abs(days);
    }
    
    public Resolution deleteSelected() {
        logger.info("------------deleteSelected--------------");
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);
            
            tx.commit();
            
        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(UtilitiAnsuranRemediActionBean.class, "locate");
    }
    
    public Resolution refreshpage() {
        logger.info("------------refreshpage--------------");
        rehydrate();
        return new RedirectResolution(UtilitiAnsuranRemediActionBean.class, "locate");
    }
    
    public Resolution reload() {
        logger.info("------------reload-------------");
//        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_notis_penyampaian.jsp");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("reload id mohon : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                senaraiKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "BR");
                logger.info("size senaraiKompaun masa reload: " + senaraiKompaun.size());
            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
//        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_ansuran_remedi.jsp");
    }
    
    public String getIdPermohonan() {
        return idPermohonan;
    }
    
    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    public Pengguna getPengguna() {
        return pengguna;
    }
    
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }
    
    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    public Long getIdDokumen() {
        return idDokumen;
    }
    
    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }
    
    public boolean isIdPermohonanNotExist() {
        return idPermohonanNotExist;
    }
    
    public void setIdPermohonanNotExist(boolean idPermohonanNotExist) {
        this.idPermohonanNotExist = idPermohonanNotExist;
    }
    
    public List<Kompaun> getSenaraiKompaun() {
        return senaraiKompaun;
    }
    
    public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
        this.senaraiKompaun = senaraiKompaun;
    }
    
    public String getAmaunBaru() {
        return amaunBaru;
    }
    
    public void setAmaunBaru(String amaunBaru) {
        this.amaunBaru = amaunBaru;
    }
    
    public String getIdOks() {
        return idOks;
    }
    
    public void setIdOks(String idOks) {
        this.idOks = idOks;
    }
    
    public Kompaun getKompaun() {
        return kompaun;
    }
    
    public void setKompaun(Kompaun kompaun) {
        this.kompaun = kompaun;
    }
    
    public AduanOrangKenaSyak getOks() {
        return oks;
    }
    
    public void setOks(AduanOrangKenaSyak oks) {
        this.oks = oks;
    }
    
    public PermohonanTuntutan getPt() {
        return pt;
    }
    
    public void setPt(PermohonanTuntutan pt) {
        this.pt = pt;
    }
    
    public PermohonanTuntutanButiran getPtb() {
        return ptb;
    }
    
    public void setPtb(PermohonanTuntutanButiran ptb) {
        this.ptb = ptb;
    }
    
    public PermohonanTuntutanKos getPtk() {
        return ptk;
    }
    
    public void setPtk(PermohonanTuntutanKos ptk) {
        this.ptk = ptk;
    }
    
    public String getTempohHari() {
        return tempohHari;
    }
    
    public void setTempohHari(String tempohHari) {
        this.tempohHari = tempohHari;
    }
    
    public boolean isStatusAnsuran() {
        return statusAnsuran;
    }
    
    public void setStatusAnsuran(boolean statusAnsuran) {
        this.statusAnsuran = statusAnsuran;
    }
    
    public String getIdKompaun() {
        return idKompaun;
    }
    
    public void setIdKompaun(String idKompaun) {
        this.idKompaun = idKompaun;
    }
    
    public List<Kompaun> getSenaraiKompaunUpdate() {
        return senaraiKompaunUpdate;
    }
    
    public void setSenaraiKompaunUpdate(List<Kompaun> senaraiKompaunUpdate) {
        this.senaraiKompaunUpdate = senaraiKompaunUpdate;
    }
    
    public BigDecimal getAmaunBulan1() {
        return amaunBulan1;
    }
    
    public void setAmaunBulan1(BigDecimal amaunBulan1) {
        this.amaunBulan1 = amaunBulan1;
    }
    
    public BigDecimal getAmaunBulan2() {
        return amaunBulan2;
    }
    
    public void setAmaunBulan2(BigDecimal amaunBulan2) {
        this.amaunBulan2 = amaunBulan2;
    }
    
    public BigDecimal getAmaunBulan3() {
        return amaunBulan3;
    }
    
    public void setAmaunBulan3(BigDecimal amaunBulan3) {
        this.amaunBulan3 = amaunBulan3;
    }
    
    public BigDecimal getAmaunBulan4() {
        return amaunBulan4;
    }
    
    public void setAmaunBulan4(BigDecimal amaunBulan4) {
        this.amaunBulan4 = amaunBulan4;
    }
    
    public BigDecimal getAmaunBulan5() {
        return amaunBulan5;
    }
    
    public void setAmaunBulan5(BigDecimal amaunBulan5) {
        this.amaunBulan5 = amaunBulan5;
    }
    
    public BigDecimal getAmaunBulan6() {
        return amaunBulan6;
    }
    
    public void setAmaunBulan6(BigDecimal amaunBulan6) {
        this.amaunBulan6 = amaunBulan6;
    }
    
    public Date getCurrentDate() {
        return currentDate;
    }
    
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    
    public String getTarikhAkhirBayaran1() {
        return tarikhAkhirBayaran1;
    }
    
    public void setTarikhAkhirBayaran1(String tarikhAkhirBayaran1) {
        this.tarikhAkhirBayaran1 = tarikhAkhirBayaran1;
    }
    
    public String getTarikhAkhirBayaran2() {
        return tarikhAkhirBayaran2;
    }
    
    public void setTarikhAkhirBayaran2(String tarikhAkhirBayaran2) {
        this.tarikhAkhirBayaran2 = tarikhAkhirBayaran2;
    }
    
    public String getTarikhAkhirBayaran3() {
        return tarikhAkhirBayaran3;
    }
    
    public void setTarikhAkhirBayaran3(String tarikhAkhirBayaran3) {
        this.tarikhAkhirBayaran3 = tarikhAkhirBayaran3;
    }
    
    public String getTarikhAkhirBayaran4() {
        return tarikhAkhirBayaran4;
    }
    
    public void setTarikhAkhirBayaran4(String tarikhAkhirBayaran4) {
        this.tarikhAkhirBayaran4 = tarikhAkhirBayaran4;
    }
    
    public String getTarikhAkhirBayaran5() {
        return tarikhAkhirBayaran5;
    }
    
    public void setTarikhAkhirBayaran5(String tarikhAkhirBayaran5) {
        this.tarikhAkhirBayaran5 = tarikhAkhirBayaran5;
    }
    
    public String getTarikhAkhirBayaran6() {
        return tarikhAkhirBayaran6;
    }
    
    public void setTarikhAkhirBayaran6(String tarikhAkhirBayaran6) {
        this.tarikhAkhirBayaran6 = tarikhAkhirBayaran6;
    }
    
    public BigDecimal getJumlahKeseluruhan() {
        return jumlahKeseluruhan;
    }
    
    public void setJumlahKeseluruhan(BigDecimal jumlahKeseluruhan) {
        this.jumlahKeseluruhan = jumlahKeseluruhan;
    }
    
    public BigDecimal getTotalPayment() {
        return totalPayment;
    }
    
    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public int getJumlahHari() {
        return jumlahHari;
    }

    public void setJumlahHari(int jumlahHari) {
        this.jumlahHari = jumlahHari;
    }
    
    
}
