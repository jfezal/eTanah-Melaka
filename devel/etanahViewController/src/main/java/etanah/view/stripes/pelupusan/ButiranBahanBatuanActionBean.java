/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
//import electric.xml.ParseException;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanJenteraDAO;
import etanah.dao.PermohonanPermitItemDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBahanBatu;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodItemPermit;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanJentera;
import etanah.model.PermohonanPermitItem;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import java.text.DateFormat;
import java.util.Calendar;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.BPelService;
import java.text.DecimalFormat;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/pelupusan/butiran_bahan_batuan")
public class ButiranBahanBatuanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(ButiranBahanBatuanActionBean.class);
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanJenteraDAO permohonanJenteraDAO;
    @Inject
    KodUOMDAO kodUomDAO;
    @Inject
    PermohonanPermitItemDAO permohonanPermitItemDAO;
    @Inject
    etanah.Configuration conf;
    private KodBandarPekanMukim kodBPM;
    private PermohonanBahanBatuan permohonanBahanBatuan;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> listHM;
    private PermohonanJentera permohonanJentera;
    private KodItemPermit jenisBahanBatu;
    private KodItemPermit kodItemPermit1;
    private KodItemPermit kodItemPermit2;
    private KodItemPermit kodItemPermit3;
    private String kawasanAmbilBatuan;
    private String kawasanPindahBatuan;
    private String jalanDilalui;
    private String tarikhMula;
    private String tarikhTamat;
    private Integer tempohKeluar;
    private Character unitTempohKeluar;
    private BigDecimal jumlahIsipadu;
    private BigDecimal anggaranMuatan;
    private BigDecimal jumlahIsipaduDipohon;
    private BigDecimal lebarBangunanSementara;
    private BigDecimal panjangBangunanSementara;
    private String bilanganPekerja;
    private String idPermohonan;
    private String id;
    private String unitTempohKeluarUOM;
    private List<PermohonanJentera> listJentera;
    private Pengguna pengguna;
    private String pemilik;
    private Character jenisJentera;
    private String pndftran;
    private static final Logger LOG = Logger.getLogger(ButiranBahanBatuanActionBean.class);
    private KodUOM lebarBangunanSementaraUom;
    private KodUOM panjangBangunanSementaraUom;
    private KodUOM jumlahIsipaduDipohonUom;
    private PermohonanPermitItem permohonanPermitItem;
    private String test1;
    private String test12;
    private String test13;
    private String kodNegeri;
    private String stageId;
//    private Date tarikhMula ;
//    private Date tarikhTamat ;
//    private String bangunanSementara ;
    private boolean view;
    private DecimalFormat dF = new DecimalFormat("#,##0.00");

    @DefaultHandler
    public Resolution showForm() {
        view = Boolean.FALSE;
        return new JSP("pelupusan/batuan/bahan_batuan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        view = Boolean.TRUE;
        return new JSP("pelupusan/batuan/bahan_batuan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution showFormNS() {
        return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
    }

    public Resolution viewFormNS() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
    }

//    public Resolution showForm1() {
//        return new JSP("pelupusan/butiran_bahan_batuan.jsp").addParameter("tab", "true");
//    }
//
//    public Resolution showForm2() {
//        return new JSP("pelupusan/butiran_mesin_jentera.jsp").addParameter("tab", "true");
//    }
//
//    public Resolution popup() {
//        return new JSP("pelupusan/butiran_mesin_jentera2.jsp").addParameter("popup", "true");
//    }
//
//    public Resolution editJentera() {
//        return new JSP("pelupusan/butiran_mesin_jentera2.jsp").addParameter("popup", "true");
//    }
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");
        if (permohonan != null) {
            permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan);
            if (permohonanBahanBatuan != null) {
                lebarBangunanSementara = permohonanBahanBatuan.getLebarBangunanSementara();
                panjangBangunanSementara = permohonanBahanBatuan.getPanjangBangunanSementara();
            }
//            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            listHM = pelupusanService.findHakmilikPermohonanList(idPermohonan);
            String[] tname = {"permohonan"};
            Object[] tvalue = {permohonan};
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            stageId = stageId(taskId);
            //listJentera = permohonanJenteraDAO.findByEqualCriterias(tname, tvalue, null) ;
        }
        permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "KB");
        if (permohonanPermitItem != null) {
            test1 = permohonanPermitItem.getKodItemPermit().getKod();
        }

        permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "PB");
        if (permohonanPermitItem != null) {
            test12 = permohonanPermitItem.getKodItemPermit().getKod();
        }

        permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "MB");
        if (permohonanPermitItem != null) {
            test13 = permohonanPermitItem.getKodItemPermit().getKod();
        }

    }

    public Resolution simpan() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
//        permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan) ;
        List<HakmilikPermohonan> hakmilikPermohonanList = pelupusanService.findHakmilikPermohonanList(idPermohonan);
        // Hakmilik hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());

        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<PermohonanBahanBatuan> listBhnBatuan = permohonanBahanBatuanDAO.findByEqualCriterias(tname, tvalue, null);
        if (listBhnBatuan.isEmpty()) {
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());

            permohonanBahanBatuan = new PermohonanBahanBatuan();
            permohonanBahanBatuan.setInfoAudit(ia);
            if (hakmilikPermohonanList.get(0).getHakmilik() != null) {
                permohonanBahanBatuan.setBandarPekanMukim(hakmilikPermohonanList.get(0).getHakmilik().getBandarPekanMukim());
            } else {
                permohonanBahanBatuan.setBandarPekanMukim(hakmilikPermohonanList.get(0).getBandarPekanMukimBaru());
            }

//        kodBPM = new KodBandarPekanMukim();
//        String kod1 = getContext().getRequest().getParameter("bandarPekanMukimBaru.kod");
            permohonanBahanBatuan.setPermohonan(permohonan);
//        kodBPM.setKod(Integer.parseInt(kod1));
//        hakmilikPermohonan.setBandarPekanMukimBaru(kodBPM);

            jenisBahanBatu = new KodItemPermit();
            String kod = getContext().getRequest().getParameter("jenisBahanBatu.kod");

            lebarBangunanSementaraUom = new KodUOM();
            String kod1 = getContext().getRequest().getParameter("lebarBangunanSementaraUom.kod");
            panjangBangunanSementaraUom = new KodUOM();
            String kod2 = getContext().getRequest().getParameter("panjangBangunanSementaraUom.kod");
            jumlahIsipaduDipohonUom = new KodUOM();
            String kod3 = getContext().getRequest().getParameter("jumlahIsipaduDipohonUom.kod");

            //jenisBahanBatu.setKod(kod);
            permohonanBahanBatuan.setJenisBahanBatu(kodItemPermitDAO.findById(kod));

            lebarBangunanSementaraUom.setKod(kod1);
            permohonanBahanBatuan.setLebarBangunanSementaraUom(lebarBangunanSementaraUom);
            panjangBangunanSementaraUom.setKod(kod2);
            permohonanBahanBatuan.setPanjangBangunanSementaraUom(panjangBangunanSementaraUom);
            jumlahIsipaduDipohonUom.setKod(kod3);
            permohonanBahanBatuan.setJumlahIsipaduDipohonUom(jumlahIsipaduDipohonUom);


            kawasanAmbilBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanAmbilBatuan");
            permohonanBahanBatuan.setKawasanAmbilBatuan(kawasanAmbilBatuan);
//        String a = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhMula") ;

            kawasanPindahBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanPindahBatuan");
            permohonanBahanBatuan.setKawasanPindahBatuan(kawasanPindahBatuan);

            jalanDilalui = getContext().getRequest().getParameter("permohonanBahanBatuan.jalanDilalui");
            permohonanBahanBatuan.setJalanDilalui(jalanDilalui);

            String str = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhMula");
            if (str != null && !("").equals(str)) {
                DateFormat df;
                Date date;
                df = new SimpleDateFormat("dd/MM/yyyy");
                date = df.parse(str);
                permohonanBahanBatuan.setTarikhMula(date);
            }

            String str2 = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhTamat");
            if (str2 != null && !("").equals(str2)) {
                DateFormat df2;
                Date date2;
                df2 = new SimpleDateFormat("dd/MM/yyyy");
                date2 = df2.parse(str2);
                permohonanBahanBatuan.setTarikhTamat(date2);
            }

            String tKeluar = getContext().getRequest().getParameter("permohonanBahanBatuan.tempohKeluar");
            if (tKeluar != null && !("").equals(tKeluar)) {
                tempohKeluar = Integer.parseInt(tKeluar);
                permohonanBahanBatuan.setTempohKeluar(tempohKeluar);
            }

            String charUnitTempoh = getContext().getRequest().getParameter("unitTempohKeluarUOM");
            KodUOM kodUOMtempohKeluar = new KodUOM();
            kodUOMtempohKeluar = kodUomDAO.findById(charUnitTempoh);
            LOG.info("Kod UOM Tempoh Keluar :" + kodUOMtempohKeluar);
//        if(kodUOMtempohKeluar!=null){
            permohonanBahanBatuan.setUnitTempohKeluar(kodUOMtempohKeluar);
//        }

            String test = getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipaduDipohon");
            if (test != null && !("").equals(test)) {
                jumlahIsipadu = new BigDecimal(test);
                permohonanBahanBatuan.setJumlahIsipaduDipohon(jumlahIsipadu);
            }

            String sebabPermohonan = getContext().getRequest().getParameter("permohonan.sebab");
            permohonan.setSebab(sebabPermohonan);
            pelupusanService.simpanPermohonan(permohonan);

            bilanganPekerja = getContext().getRequest().getParameter("permohonanBahanBatuan.bilanganPekerja");
            permohonanBahanBatuan.setBilanganPekerja(bilanganPekerja);

            String test2 = getContext().getRequest().getParameter("permohonanBahanBatuan.lebarBangunanSementara");
            LOG.info("test2 :" + test2);
            if (test2 != null && !("").equals(test2)) {
//        To convert s String to a BigDecimal
                lebarBangunanSementara = new BigDecimal(test2);
                LOG.info("lebarBangunanSementara = " + lebarBangunanSementara);
                permohonanBahanBatuan.setLebarBangunanSementara(lebarBangunanSementara);
            }
//        if(mohonPermitItem == null){
//            mohonPermitItem = new PermohonanPermitItem() ;
//            mohonPermitItem.setInfoAudit(ia);
//            mohonPermitItem.setKodCawangan(permohonan.getCawangan());
//            mohonPermitItem.setPermohonan(permohonan);
//            mohonPermitItem.setKodItemPermit(jenisBahanBatu);
//            
//        }
//        }
            String test3 = getContext().getRequest().getParameter("permohonanBahanBatuan.panjangBangunanSementara");
            LOG.info("test3 :" + test3);
            if (test3 != null && !("").equals(test3)) {
                // To convert s String to a BigDecimal
                panjangBangunanSementara = new BigDecimal(test3);
                LOG.info("panjangBangunanSementara = " + panjangBangunanSementara);
                permohonanBahanBatuan.setPanjangBangunanSementara(panjangBangunanSementara);
            }


            pelupusanService.simpanPermohonanBahanBatuan(permohonanBahanBatuan);
        } else {
            permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan);
            InfoAudit ia = permohonanBahanBatuan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanBahanBatuan.setInfoAudit(ia);
//             jenisBahanBatu = new KodItemPermit();
            String kod = getContext().getRequest().getParameter("jenisBahanBatu.kod");
            lebarBangunanSementaraUom = new KodUOM();
            String kod1 = getContext().getRequest().getParameter("lebarBangunanSementaraUom.kod");
            panjangBangunanSementaraUom = new KodUOM();
            String kod2 = getContext().getRequest().getParameter("panjangBangunanSementaraUom.kod");
            jumlahIsipaduDipohonUom = new KodUOM();
            String kod3 = getContext().getRequest().getParameter("jumlahIsipaduDipohonUom.kod");

//        jenisBahanBatu.setKod(kod);
            permohonanBahanBatuan.setJenisBahanBatu(kodItemPermitDAO.findById(kod));

            lebarBangunanSementaraUom.setKod(kod1);
            permohonanBahanBatuan.setLebarBangunanSementaraUom(lebarBangunanSementaraUom);
            panjangBangunanSementaraUom.setKod(kod2);
            permohonanBahanBatuan.setPanjangBangunanSementaraUom(panjangBangunanSementaraUom);
            jumlahIsipaduDipohonUom.setKod(kod3);
            permohonanBahanBatuan.setJumlahIsipaduDipohonUom(jumlahIsipaduDipohonUom);
            if(permohonanBahanBatuan.getBandarPekanMukim()!=null)
                permohonanBahanBatuan.setBandarPekanMukim(hakmilikPermohonanList.get(0).getBandarPekanMukimBaru());

            kawasanAmbilBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanAmbilBatuan");
            permohonanBahanBatuan.setKawasanAmbilBatuan(kawasanAmbilBatuan);

            String test3 = getContext().getRequest().getParameter("permohonanBahanBatuan.panjangBangunanSementara");
            LOG.info("test3 :" + test3);
            if (test3 != null && !("").equals(test3)) {
                // To convert s String to a BigDecimal
                panjangBangunanSementara = new BigDecimal(test3);
                LOG.info("panjangBangunanSementara = " + panjangBangunanSementara);
                permohonanBahanBatuan.setPanjangBangunanSementara(panjangBangunanSementara);
            }

            kawasanPindahBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanPindahBatuan");
            permohonanBahanBatuan.setKawasanPindahBatuan(kawasanPindahBatuan);

            jalanDilalui = getContext().getRequest().getParameter("permohonanBahanBatuan.jalanDilalui");
            permohonanBahanBatuan.setJalanDilalui(jalanDilalui);

            String str = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhMula");
            if (str != null && !("").equals(str)) {
                DateFormat df;
                Date date;
                df = new SimpleDateFormat("dd/MM/yyyy");
                date = df.parse(str);
                permohonanBahanBatuan.setTarikhMula(date);
            }

            String str2 = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhTamat");
            if (str2 != null && !("").equals(str2)) {
                DateFormat df2;
                Date date2;
                df2 = new SimpleDateFormat("dd/MM/yyyy");
                date2 = df2.parse(str2);
                permohonanBahanBatuan.setTarikhTamat(date2);
            }

            String tKeluar = getContext().getRequest().getParameter("permohonanBahanBatuan.tempohKeluar");
            if (tKeluar != null && !("").equals(tKeluar)) {
                tempohKeluar = Integer.parseInt(tKeluar);
                permohonanBahanBatuan.setTempohKeluar(tempohKeluar);
            }

            String charUnitTempoh = getContext().getRequest().getParameter("unitTempohKeluarUOM");
            KodUOM kodUOMtempohKeluar = new KodUOM();
            kodUOMtempohKeluar = kodUomDAO.findById(charUnitTempoh);
            LOG.info("kod uom tempoh keluar :" + kodUOMtempohKeluar);
            if (kodUOMtempohKeluar != null) {
                permohonanBahanBatuan.setUnitTempohKeluar(kodUOMtempohKeluar);
            }
            String test = getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipaduDipohon");
            if (test != null && !("").equals(test)) {
                jumlahIsipadu = new BigDecimal(test);
//                DecimalFormat decimalFormat = new DecimalFormat();
//                decimalFormat.setParseBigDecimal(true);
//                BigDecimal bd = (BigDecimal) decimalFormat.parse(test);
//                jumlahIsipadu = dF.format(bd);
                LOG.info("jumlahIsipadu :" + jumlahIsipadu);
                permohonanBahanBatuan.setJumlahIsipaduDipohon(jumlahIsipadu);
            }

            bilanganPekerja = getContext().getRequest().getParameter("permohonanBahanBatuan.bilanganPekerja");
            permohonanBahanBatuan.setBilanganPekerja(bilanganPekerja);

            String test2 = getContext().getRequest().getParameter("permohonanBahanBatuan.lebarBangunanSementara");
            LOG.info("test2 :" + test2);
            if (test2 != null && !("").equals(test2)) {
//        To convert s String to a BigDecimal
                lebarBangunanSementara = new BigDecimal(test2);
                LOG.info("lebarBangunanSementara = " + lebarBangunanSementara);
                permohonanBahanBatuan.setLebarBangunanSementara(lebarBangunanSementara);
            }

            String sebabPermohonan = getContext().getRequest().getParameter("permohonan.sebab");
            permohonan.setSebab(sebabPermohonan);
            pelupusanService.simpanPermohonan(permohonan);

            pelupusanService.simpanPermohonanBahanBatuan(permohonanBahanBatuan);


        }

        test1 = getContext().getRequest().getParameter("kodItem1"); //KB
        LOG.info("Testing :" + test1);
        test12 = getContext().getRequest().getParameter("kodItem2"); //PB
        LOG.info("Testing :" + test12);
        test13 = getContext().getRequest().getParameter("kodItem3"); //MB
        LOG.info("Testing :" + test13);

        if (test1 != null && !("").equals(test1)) {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "KB");
            InfoAudit info = new InfoAudit();
//             kodItemPermit1 = new KodItemPermit();
            if (permohonanPermitItem == null) {
                permohonanPermitItem = new PermohonanPermitItem();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setKodCawangan(permohonan.getCawangan());
//                 kodItemPermit1.setKod("KB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("KB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            } else {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                info.setDimasukOleh(permohonanPermitItem.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanPermitItem.getInfoAudit().getTarikhMasuk());
                permohonanPermitItem.setInfoAudit(info);

//                 kodItemPermit1.setKod("KB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("KB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        } else {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "KB");
            if (permohonanPermitItem != null) {
                pelupusanService.deletePermohonanPermitItem(permohonanPermitItem);
            }
        }

        if (test12 != null && !("").equals(test12)) {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "PB");
            InfoAudit info = new InfoAudit();
//             kodItemPermit2 = new KodItemPermit();
            if (permohonanPermitItem == null) {
                permohonanPermitItem = new PermohonanPermitItem();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setKodCawangan(permohonan.getCawangan());
//                 kodItemPermit2.setKod("PB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("PB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            } else {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                info.setDimasukOleh(permohonanPermitItem.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanPermitItem.getInfoAudit().getTarikhMasuk());
                permohonanPermitItem.setInfoAudit(info);
//                 kodItemPermit2.setKod("PB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("PB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        } else {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "PB");
            if (permohonanPermitItem != null) {
                pelupusanService.deletePermohonanPermitItem(permohonanPermitItem);
            }

        }

        if (test13 != null && !("").equals(test13)) {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "MB");
            InfoAudit info = new InfoAudit();
//             kodItemPermit3 = new KodItemPermit();
            if (permohonanPermitItem == null) {
                permohonanPermitItem = new PermohonanPermitItem();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setKodCawangan(permohonan.getCawangan());
//                 kodItemPermit3.setKod("MB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("MB"));
                pelupusanService.simpanPermohonanPermitItem(permohonanPermitItem);
            } else {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                info.setDimasukOleh(permohonanPermitItem.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanPermitItem.getInfoAudit().getTarikhMasuk());
                permohonanPermitItem.setInfoAudit(info);
//                 kodItemPermit3.setKod("MB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("MB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        } else {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "MB");
            if (permohonanPermitItem != null) {
                pelupusanService.deletePermohonanPermitItem(permohonanPermitItem);
            }

        }



        logger.debug("tess1 :" + permohonanBahanBatuan.getId());
        view = Boolean.FALSE;

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/batuan/bahan_batuan_mlk.jsp").addParameter("tab", "true");

    }

    public Resolution simpanNS() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
//        permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan) ;
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        // Hakmilik hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());

        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<PermohonanBahanBatuan> listBhnBatuan = permohonanBahanBatuanDAO.findByEqualCriterias(tname, tvalue, null);
        if (listBhnBatuan.isEmpty()) {
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());

            permohonanBahanBatuan = new PermohonanBahanBatuan();
            permohonanBahanBatuan.setInfoAudit(ia);
            permohonanBahanBatuan.setBandarPekanMukim(hakmilikPermohonan.getBandarPekanMukimBaru());
//        kodBPM = new KodBandarPekanMukim();
//        String kod1 = getContext().getRequest().getParameter("bandarPekanMukimBaru.kod");
            permohonanBahanBatuan.setPermohonan(permohonan);
//        kodBPM.setKod(Integer.parseInt(kod1));
//        hakmilikPermohonan.setBandarPekanMukimBaru(kodBPM);

            jenisBahanBatu = new KodItemPermit();
            String kod = getContext().getRequest().getParameter("jenisBahanBatu.kod");

            lebarBangunanSementaraUom = new KodUOM();
            //String kod1 = getContext().getRequest().getParameter("lebarBangunanSementaraUom.kod");
            panjangBangunanSementaraUom = new KodUOM();
            //String kod2 = getContext().getRequest().getParameter("panjangBangunanSementaraUom.kod");
            jumlahIsipaduDipohonUom = new KodUOM();
            String kod3 = getContext().getRequest().getParameter("jumlahIsipaduDipohonUom.kod");

            //jenisBahanBatu.setKod(kod);
            permohonanBahanBatuan.setJenisBahanBatu(kodItemPermitDAO.findById(kod));

            //lebarBangunanSementaraUom.setKod(kod1);
            //permohonanBahanBatuan.setLebarBangunanSementaraUom(lebarBangunanSementaraUom);
            //panjangBangunanSementaraUom.setKod(kod2);
            //permohonanBahanBatuan.setPanjangBangunanSementaraUom(panjangBangunanSementaraUom);
            jumlahIsipaduDipohonUom.setKod(kod3);
            permohonanBahanBatuan.setJumlahIsipaduDipohonUom(jumlahIsipaduDipohonUom);


            kawasanAmbilBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanAmbilBatuan");
            permohonanBahanBatuan.setKawasanAmbilBatuan(kawasanAmbilBatuan);
//        String a = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhMula") ;

            kawasanPindahBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanPindahBatuan");
            permohonanBahanBatuan.setKawasanPindahBatuan(kawasanPindahBatuan);

            jalanDilalui = getContext().getRequest().getParameter("permohonanBahanBatuan.jalanDilalui");
            permohonanBahanBatuan.setJalanDilalui(jalanDilalui);

            String str = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhMula");
            if (str != null && !("").equals(str)) {
                DateFormat df;
                Date date;
                df = new SimpleDateFormat("dd/MM/yyyy");
                date = df.parse(str);
                permohonanBahanBatuan.setTarikhMula(date);
            }

            String str2 = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhTamat");
            if (str2 != null && !("").equals(str2)) {
                DateFormat df2;
                Date date2;
                df2 = new SimpleDateFormat("dd/MM/yyyy");
                date2 = df2.parse(str2);
                permohonanBahanBatuan.setTarikhTamat(date2);
            }

            String tKeluar = getContext().getRequest().getParameter("permohonanBahanBatuan.tempohKeluar");
            if (tKeluar != null && !("").equals(tKeluar)) {
                tempohKeluar = Integer.parseInt(tKeluar);
                permohonanBahanBatuan.setTempohKeluar(tempohKeluar);
            }

            String charUnitTempoh = getContext().getRequest().getParameter("unitTempohKeluarUOM");
            KodUOM kodUOMtempohKeluar = new KodUOM();
            kodUOMtempohKeluar = kodUomDAO.findById(charUnitTempoh);
            if (kodUOMtempohKeluar != null) {
                permohonanBahanBatuan.setUnitTempohKeluar(kodUOMtempohKeluar);
            }

            String test = getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipaduDipohon");
            if (test != null && !("").equals(test)) {
                jumlahIsipadu = new BigDecimal(test);
                permohonanBahanBatuan.setJumlahIsipaduDipohon(jumlahIsipadu);
            }

            String sebabPermohonan = getContext().getRequest().getParameter("permohonan.sebab");
            permohonan.setSebab(sebabPermohonan);
            pelupusanService.simpanPermohonan(permohonan);

            //bilanganPekerja = getContext().getRequest().getParameter("permohonanBahanBatuan.bilanganPekerja");
            //permohonanBahanBatuan.setBilanganPekerja(bilanganPekerja);

            //String test2 = getContext().getRequest().getParameter("permohonanBahanBatuan.lebarBangunanSementara") ;
            //LOG.info("test2 :" + test2);
            //if(test2 != null && !("").equals(test2)){
//        To convert s String to a BigDecimal
            //lebarBangunanSementara = new BigDecimal(test2);
            //LOG.info("lebarBangunanSementara = " + lebarBangunanSementara);
            //permohonanBahanBatuan.setLebarBangunanSementara(lebarBangunanSementara);
            //}
//        if(mohonPermitItem == null){
//            mohonPermitItem = new PermohonanPermitItem() ;
//            mohonPermitItem.setInfoAudit(ia);
//            mohonPermitItem.setKodCawangan(permohonan.getCawangan());
//            mohonPermitItem.setPermohonan(permohonan);
//            mohonPermitItem.setKodItemPermit(jenisBahanBatu);
//
//        }
//        }
            //String test3 = getContext().getRequest().getParameter("permohonanBahanBatuan.panjangBangunanSementara") ;
            //LOG.info("test3 :" + test3);
            //if(test3 != null && !("").equals(test3)){
            // To convert s String to a BigDecimal
            //panjangBangunanSementara = new BigDecimal(test3);
            //LOG.info("panjangBangunanSementara = " + panjangBangunanSementara);
            //permohonanBahanBatuan.setPanjangBangunanSementara(panjangBangunanSementara);
            //}


            pelupusanService.simpanPermohonanBahanBatuan(permohonanBahanBatuan);
        } else {
            permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan);
            InfoAudit ia = permohonanBahanBatuan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanBahanBatuan.setInfoAudit(ia);
//             jenisBahanBatu = new KodItemPermit();
            String kod = getContext().getRequest().getParameter("jenisBahanBatu.kod");
            lebarBangunanSementaraUom = new KodUOM();
            //String kod1 = getContext().getRequest().getParameter("lebarBangunanSementaraUom.kod");
            panjangBangunanSementaraUom = new KodUOM();
            //String kod2 = getContext().getRequest().getParameter("panjangBangunanSementaraUom.kod");
            jumlahIsipaduDipohonUom = new KodUOM();
            String kod3 = getContext().getRequest().getParameter("jumlahIsipaduDipohonUom.kod");

//        jenisBahanBatu.setKod(kod);
            permohonanBahanBatuan.setJenisBahanBatu(kodItemPermitDAO.findById(kod));

            //lebarBangunanSementaraUom.setKod(kod1);
            //permohonanBahanBatuan.setLebarBangunanSementaraUom(lebarBangunanSementaraUom);
            //panjangBangunanSementaraUom.setKod(kod2);
            //permohonanBahanBatuan.setPanjangBangunanSementaraUom(panjangBangunanSementaraUom);
            jumlahIsipaduDipohonUom.setKod(kod3);
            permohonanBahanBatuan.setJumlahIsipaduDipohonUom(jumlahIsipaduDipohonUom);

            permohonanBahanBatuan.setBandarPekanMukim(hakmilikPermohonan.getBandarPekanMukimBaru());

            kawasanAmbilBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanAmbilBatuan");
            permohonanBahanBatuan.setKawasanAmbilBatuan(kawasanAmbilBatuan);

            //String test3 = getContext().getRequest().getParameter("permohonanBahanBatuan.panjangBangunanSementara") ;
            //LOG.info("test3 :" + test3);
            //if(test3 != null && !("").equals(test3)){
            // To convert s String to a BigDecimal
            //panjangBangunanSementara = new BigDecimal(test3);
            //LOG.info("panjangBangunanSementara = " + panjangBangunanSementara);
            //permohonanBahanBatuan.setPanjangBangunanSementara(panjangBangunanSementara);
            //}

            kawasanPindahBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanPindahBatuan");
            permohonanBahanBatuan.setKawasanPindahBatuan(kawasanPindahBatuan);

            jalanDilalui = getContext().getRequest().getParameter("permohonanBahanBatuan.jalanDilalui");
            permohonanBahanBatuan.setJalanDilalui(jalanDilalui);

            String str = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhMula");
            if (str != null && !("").equals(str)) {
                DateFormat df;
                Date date;
                df = new SimpleDateFormat("dd/MM/yyyy");
                date = df.parse(str);
                permohonanBahanBatuan.setTarikhMula(date);
            }

            String str2 = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhTamat");
            if (str2 != null && !("").equals(str2)) {
                DateFormat df2;
                Date date2;
                df2 = new SimpleDateFormat("dd/MM/yyyy");
                date2 = df2.parse(str2);
                permohonanBahanBatuan.setTarikhTamat(date2);
            }

            String tKeluar = getContext().getRequest().getParameter("permohonanBahanBatuan.tempohKeluar");
            if (tKeluar != null && !("").equals(tKeluar)) {
                tempohKeluar = Integer.parseInt(tKeluar);
                permohonanBahanBatuan.setTempohKeluar(tempohKeluar);
            }

            String charUnitTempoh = getContext().getRequest().getParameter("unitTempohKeluarUOM");
            KodUOM kodUOMtempohKeluar = new KodUOM();
            kodUOMtempohKeluar = kodUomDAO.findById(charUnitTempoh);
            if (kodUOMtempohKeluar != null) {
//            permohonanBahanBatuan.setUnitTempohKeluar(kodUOMtempohKeluar);
            }
            String test = getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipaduDipohon");
            if (test != null && !("").equals(test)) {
                jumlahIsipadu = new BigDecimal(test);
                permohonanBahanBatuan.setJumlahIsipaduDipohon(jumlahIsipadu);
            }

            //bilanganPekerja = getContext().getRequest().getParameter("permohonanBahanBatuan.bilanganPekerja");
            //permohonanBahanBatuan.setBilanganPekerja(bilanganPekerja);

            //String test2 = getContext().getRequest().getParameter("permohonanBahanBatuan.lebarBangunanSementara") ;
            //LOG.info("test2 :" + test2);
            //if(test2 != null && !("").equals(test2)){
//        To convert s String to a BigDecimal
            //lebarBangunanSementara = new BigDecimal(test2);
            //LOG.info("lebarBangunanSementara = " + lebarBangunanSementara);
            //permohonanBahanBatuan.setLebarBangunanSementara(lebarBangunanSementara);
            //}

            String sebabPermohonan = getContext().getRequest().getParameter("permohonan.sebab");
            permohonan.setSebab(sebabPermohonan);
            pelupusanService.simpanPermohonan(permohonan);

            pelupusanService.simpanPermohonanBahanBatuan(permohonanBahanBatuan);


        }

        test1 = getContext().getRequest().getParameter("kodItem1");
        LOG.info("Testing :" + test1);
        test12 = getContext().getRequest().getParameter("kodItem2");
        LOG.info("Testing :" + test12);
        test13 = getContext().getRequest().getParameter("kodItem3");
        LOG.info("Testing :" + test13);

        if (test1 != null && !("").equals(test1)) {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "KB");
            InfoAudit info = new InfoAudit();
//             kodItemPermit1 = new KodItemPermit();
            if (permohonanPermitItem == null) {
                permohonanPermitItem = new PermohonanPermitItem();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setKodCawangan(permohonan.getCawangan());
//                 kodItemPermit1.setKod("KB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("KB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            } else {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                info.setDimasukOleh(permohonanPermitItem.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanPermitItem.getInfoAudit().getTarikhMasuk());
                permohonanPermitItem.setInfoAudit(info);

//                 kodItemPermit1.setKod("KB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("KB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        } else {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "KB");
            if (permohonanPermitItem != null) {
                pelupusanService.deletePermohonanPermitItem(permohonanPermitItem);
            }
        }

        if (test12 != null && !("").equals(test12)) {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "PB");
            InfoAudit info = new InfoAudit();
//             kodItemPermit2 = new KodItemPermit();
            if (permohonanPermitItem == null) {
                permohonanPermitItem = new PermohonanPermitItem();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setKodCawangan(permohonan.getCawangan());
//                 kodItemPermit2.setKod("PB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("PB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            } else {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                info.setDimasukOleh(permohonanPermitItem.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanPermitItem.getInfoAudit().getTarikhMasuk());
                permohonanPermitItem.setInfoAudit(info);
//                 kodItemPermit2.setKod("PB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("PB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        } else {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "PB");
            if (permohonanPermitItem != null) {
                pelupusanService.deletePermohonanPermitItem(permohonanPermitItem);
            }

        }

        if (test13 != null && !("").equals(test13)) {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "MB");
            InfoAudit info = new InfoAudit();
//             kodItemPermit3 = new KodItemPermit();
            if (permohonanPermitItem == null) {
                permohonanPermitItem = new PermohonanPermitItem();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setKodCawangan(permohonan.getCawangan());
//                 kodItemPermit3.setKod("MB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("MB"));
                pelupusanService.simpanPermohonanPermitItem(permohonanPermitItem);
            } else {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                info.setDimasukOleh(permohonanPermitItem.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanPermitItem.getInfoAudit().getTarikhMasuk());
                permohonanPermitItem.setInfoAudit(info);
//                 kodItemPermit3.setKod("MB");
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("MB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        } else {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "MB");
            if (permohonanPermitItem != null) {
                pelupusanService.deletePermohonanPermitItem(permohonanPermitItem);
            }

        }

//             
//             permohonanJentera = pelupusanService.findJenteraById(idPermohonan);
//             if(permohonanJentera == null){
//             permohonanJentera = new PermohonanJentera();
//             String jenisJentera = getContext().getRequest().getParameter("permohonanJentera.jenisJentera");
//             permohonanJentera.setJenisJentera(jenisJentera);
//             String kapasiti = getContext().getRequest().getParameter("permohonanJentera.kapasiti");
//             permohonanJentera.setKapasiti(kapasiti);
//             permohonanJentera.setPermohonan(permohonan);
//             permohonanJentera.setCawangan(permohonan.getCawangan());
//             InfoAudit infoJenta = new InfoAudit() ;
//             infoJenta.setDimasukOleh(pengguna);
//             infoJenta.setTarikhMasuk(new java.util.Date());
//             permohonanJentera.setInfoAudit(infoJenta);
//             pelupusanService.simpanJentera(permohonanJentera);
//             }else{
//
//             String jenisJentera = getContext().getRequest().getParameter("permohonanJentera.jenisJentera");
//             permohonanJentera.setJenisJentera(jenisJentera);
//             String kapasiti = getContext().getRequest().getParameter("permohonanJentera.kapasiti");
//             permohonanJentera.setKapasiti(kapasiti);
//             InfoAudit infoJenta = new InfoAudit() ;
//             infoJenta = permohonanJentera.getInfoAudit();
//             infoJenta.setDiKemaskiniOleh(peng);
//             infoJenta.setTarikhKemaskini(new java.util.Date());
//             pelupusanService.simpanJentera(permohonanJentera);
//             }



        logger.debug("tess1 :" + permohonanBahanBatuan.getId());

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");

    }
    
    public Resolution simpanNSBahanBatuan() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);

        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<PermohonanBahanBatuan> listBhnBatuan = permohonanBahanBatuanDAO.findByEqualCriterias(tname, tvalue, null);
        
        if(getContext().getRequest().getParameter("kodItem1") == null && getContext().getRequest().getParameter("kodItem2")==null && getContext().getRequest().getParameter("kodItem3") == null)
        {
            addSimpleError("Sila pilih sekurang-kurangnya satu daripada tujuan permohonan.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else if(getContext().getRequest().getParameter("jenisBahanBatu.kod").isEmpty() || getContext().getRequest().getParameter("jenisBahanBatu.kod").equals(""))
        {
            addSimpleError("Sila pilih jenis bahan batuan.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else if(getContext().getRequest().getParameter("permohonan.sebab").isEmpty() || getContext().getRequest().getParameter("permohonan.sebab").equals(""))
        {
            addSimpleError("Sila Masukan Tujuan Pengeluaran.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else if(getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanAmbilBatuan").isEmpty() || getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanAmbilBatuan").equals(""))
        {
            addSimpleError("Sila Masukan Kawasan Pengambilan.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else if(getContext().getRequest().getParameter("permohonanBahanBatuan.jalanDilalui").isEmpty() || getContext().getRequest().getParameter("permohonanBahanBatuan.jalanDilalui").equals(""))
        {
            addSimpleError("Sila Masukan Jalan Yang Dilalui.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else if(getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhMula").isEmpty() || getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhMula").equals(""))
        {
            addSimpleError("Sila Pilih Tempoh Mula Pengeluaran.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else if(getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhTamat").isEmpty() || getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhTamat").equals(""))
        {
            addSimpleError("Sila Pilih Tempoh Tamat Pengeluaran.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else if(getContext().getRequest().getParameter("permohonanBahanBatuan.tempohKeluar").isEmpty() || getContext().getRequest().getParameter("permohonanBahanBatuan.tempohKeluar").equals(""))
        {
            addSimpleError("Sila Masukkan Jangka Masa.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else if(getContext().getRequest().getParameter("unitTempohKeluarUOM").isEmpty() || getContext().getRequest().getParameter("unitTempohKeluarUOM").equals(""))
        {
            addSimpleError("Sila Pilih Kod Jangka Masa.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else if(getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipaduDipohon").isEmpty() || getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipaduDipohon").equals(""))
        {
            addSimpleError("Sila Masukkan Jumlah Isipadu.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else if(getContext().getRequest().getParameter("jumlahIsipaduDipohonUom.kod").isEmpty() || getContext().getRequest().getParameter("jumlahIsipaduDipohonUom.kod").equals(""))
        {
            addSimpleError("Sila Pilih Kod Jumlah Isipadu.");
            return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
        else
        {
            test1 = getContext().getRequest().getParameter("kodItem1");
            LOG.info("Testing :" + test1);
            test12 = getContext().getRequest().getParameter("kodItem2");
            LOG.info("Testing :" + test12);
            test13 = getContext().getRequest().getParameter("kodItem3");
            int count = 0;
            String kod = getContext().getRequest().getParameter("jenisBahanBatu.kod");
            String kod3 = getContext().getRequest().getParameter("jumlahIsipaduDipohonUom.kod");
            
        if (listBhnBatuan.isEmpty()) {
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            
            permohonanBahanBatuan = new PermohonanBahanBatuan();
            permohonanBahanBatuan.setInfoAudit(ia);
            permohonanBahanBatuan.setBandarPekanMukim(hakmilikPermohonan.getBandarPekanMukimBaru());
            permohonanBahanBatuan.setPermohonan(permohonan);

            jenisBahanBatu = new KodItemPermit();
            

            lebarBangunanSementaraUom = new KodUOM();
            //String kod1 = getContext().getRequest().getParameter("lebarBangunanSementaraUom.kod");
            panjangBangunanSementaraUom = new KodUOM();
            //String kod2 = getContext().getRequest().getParameter("panjangBangunanSementaraUom.kod");
            jumlahIsipaduDipohonUom = new KodUOM();
            
            permohonanBahanBatuan.setJenisBahanBatu(kodItemPermitDAO.findById(kod));
            jumlahIsipaduDipohonUom.setKod(kod3);
            permohonanBahanBatuan.setJumlahIsipaduDipohonUom(jumlahIsipaduDipohonUom);
            kawasanAmbilBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanAmbilBatuan");
            permohonanBahanBatuan.setKawasanAmbilBatuan(kawasanAmbilBatuan);
            kawasanPindahBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanPindahBatuan");
            permohonanBahanBatuan.setKawasanPindahBatuan(kawasanPindahBatuan);
            jalanDilalui = getContext().getRequest().getParameter("permohonanBahanBatuan.jalanDilalui");
            permohonanBahanBatuan.setJalanDilalui(jalanDilalui);

            String str = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhMula");
            if (str != null && !("").equals(str)) {
                DateFormat df;
                Date date;
                df = new SimpleDateFormat("dd/MM/yyyy");
                date = df.parse(str);
                permohonanBahanBatuan.setTarikhMula(date);
            }

            String str2 = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhTamat");
            if (str2 != null && !("").equals(str2)) {
                DateFormat df2;
                Date date2;
                df2 = new SimpleDateFormat("dd/MM/yyyy");
                date2 = df2.parse(str2);
                permohonanBahanBatuan.setTarikhTamat(date2);
            }

            String tKeluar = getContext().getRequest().getParameter("permohonanBahanBatuan.tempohKeluar");
            if (tKeluar != null && !("").equals(tKeluar)) {
                tempohKeluar = Integer.parseInt(tKeluar);
                permohonanBahanBatuan.setTempohKeluar(tempohKeluar);
            }

            String charUnitTempoh = getContext().getRequest().getParameter("unitTempohKeluarUOM");
            KodUOM kodUOMtempohKeluar = new KodUOM();
            kodUOMtempohKeluar = kodUomDAO.findById(charUnitTempoh);
            if (kodUOMtempohKeluar != null) {
                permohonanBahanBatuan.setUnitTempohKeluar(kodUOMtempohKeluar);
            }

            String test = getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipaduDipohon");
            if (test != null && !("").equals(test)) {
                jumlahIsipadu = new BigDecimal(test);
                permohonanBahanBatuan.setJumlahIsipaduDipohon(jumlahIsipadu);
            }

            String sebabPermohonan = getContext().getRequest().getParameter("permohonan.sebab");
            permohonan.setSebab(sebabPermohonan);
            pelupusanService.simpanPermohonan(permohonan);

            pelupusanService.simpanPermohonanBahanBatuan(permohonanBahanBatuan);
        } else {
            permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan);
            InfoAudit ia = permohonanBahanBatuan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanBahanBatuan.setInfoAudit(ia);
            lebarBangunanSementaraUom = new KodUOM();
            panjangBangunanSementaraUom = new KodUOM();
            jumlahIsipaduDipohonUom = new KodUOM();
            permohonanBahanBatuan.setJenisBahanBatu(kodItemPermitDAO.findById(kod));
            jumlahIsipaduDipohonUom.setKod(kod3);
            permohonanBahanBatuan.setJumlahIsipaduDipohonUom(jumlahIsipaduDipohonUom);
            permohonanBahanBatuan.setBandarPekanMukim(hakmilikPermohonan.getBandarPekanMukimBaru());
            kawasanAmbilBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanAmbilBatuan");
            permohonanBahanBatuan.setKawasanAmbilBatuan(kawasanAmbilBatuan);
            kawasanPindahBatuan = getContext().getRequest().getParameter("permohonanBahanBatuan.kawasanPindahBatuan");
            permohonanBahanBatuan.setKawasanPindahBatuan(kawasanPindahBatuan);
            jalanDilalui = getContext().getRequest().getParameter("permohonanBahanBatuan.jalanDilalui");
            permohonanBahanBatuan.setJalanDilalui(jalanDilalui);

            String str = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhMula");
            if (str != null && !("").equals(str)) {
                DateFormat df;
                Date date;
                df = new SimpleDateFormat("dd/MM/yyyy");
                date = df.parse(str);
                permohonanBahanBatuan.setTarikhMula(date);
            }

            String str2 = getContext().getRequest().getParameter("permohonanBahanBatuan.tarikhTamat");
            if (str2 != null && !("").equals(str2)) {
                DateFormat df2;
                Date date2;
                df2 = new SimpleDateFormat("dd/MM/yyyy");
                date2 = df2.parse(str2);
                permohonanBahanBatuan.setTarikhTamat(date2);
            }

            String tKeluar = getContext().getRequest().getParameter("permohonanBahanBatuan.tempohKeluar");
            if (tKeluar != null && !("").equals(tKeluar)) {
                tempohKeluar = Integer.parseInt(tKeluar);
                permohonanBahanBatuan.setTempohKeluar(tempohKeluar);
            }

            String charUnitTempoh = getContext().getRequest().getParameter("unitTempohKeluarUOM");
            KodUOM kodUOMtempohKeluar = new KodUOM();
            kodUOMtempohKeluar = kodUomDAO.findById(charUnitTempoh);
            if (kodUOMtempohKeluar != null) {
                permohonanBahanBatuan.setUnitTempohKeluar(kodUOMtempohKeluar);
            }
            String test = getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipaduDipohon");
            if (test != null && !("").equals(test)) {
                jumlahIsipadu = new BigDecimal(test);
                permohonanBahanBatuan.setJumlahIsipaduDipohon(jumlahIsipadu);
            }

            String sebabPermohonan = getContext().getRequest().getParameter("permohonan.sebab");
            permohonan.setSebab(sebabPermohonan);
            pelupusanService.simpanPermohonan(permohonan);

            pelupusanService.simpanPermohonanBahanBatuan(permohonanBahanBatuan);


        }

        test1 = getContext().getRequest().getParameter("kodItem1");
        LOG.info("Testing :" + test1);
        test12 = getContext().getRequest().getParameter("kodItem2");
        LOG.info("Testing :" + test12);
        test13 = getContext().getRequest().getParameter("kodItem3");
        LOG.info("Testing :" + test13);

        if (test1 != null && !("").equals(test1)) {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "KB");
            InfoAudit info = new InfoAudit();
            if (permohonanPermitItem == null) {
                permohonanPermitItem = new PermohonanPermitItem();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setKodCawangan(permohonan.getCawangan());
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("KB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            } else {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                info.setDimasukOleh(permohonanPermitItem.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanPermitItem.getInfoAudit().getTarikhMasuk());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("KB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        } else {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "KB");
            if (permohonanPermitItem != null) {
                pelupusanService.deletePermohonanPermitItem(permohonanPermitItem);
            }
        }

        if (test12 != null && !("").equals(test12)) {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "PB");
            InfoAudit info = new InfoAudit();
            if (permohonanPermitItem == null) {
                permohonanPermitItem = new PermohonanPermitItem();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setKodCawangan(permohonan.getCawangan());
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("PB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            } else {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                info.setDimasukOleh(permohonanPermitItem.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanPermitItem.getInfoAudit().getTarikhMasuk());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("PB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        } else {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "PB");
            if (permohonanPermitItem != null) {
                pelupusanService.deletePermohonanPermitItem(permohonanPermitItem);
            }

        }

        if (test13 != null && !("").equals(test13)) {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "MB");
            InfoAudit info = new InfoAudit();
            if (permohonanPermitItem == null) {
                permohonanPermitItem = new PermohonanPermitItem();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setKodCawangan(permohonan.getCawangan());
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("MB"));
                pelupusanService.simpanPermohonanPermitItem(permohonanPermitItem);
            } else {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                info.setDimasukOleh(permohonanPermitItem.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanPermitItem.getInfoAudit().getTarikhMasuk());
                permohonanPermitItem.setInfoAudit(info);
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById("MB"));
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        } else {
            permohonanPermitItem = pelupusanService.findByIdMohonAndIdKodPermitPermitItem(idPermohonan, "MB");
            if (permohonanPermitItem != null) {
                pelupusanService.deletePermohonanPermitItem(permohonanPermitItem);
            }

        }
        logger.debug("tess1 :" + permohonanBahanBatuan.getId());

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/bahan_batuan_mlkns.jsp").addParameter("tab", "true");
        }
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    public BigDecimal getAnggaranMuatan() {
        return anggaranMuatan;
    }

    public void setAnggaranMuatan(BigDecimal anggaranMuatan) {
        this.anggaranMuatan = anggaranMuatan;
    }

    public String getBilanganPekerja() {
        return bilanganPekerja;
    }

    public void setBilanganPekerja(String bilanganPekerja) {
        this.bilanganPekerja = bilanganPekerja;
    }

    public String getJalanDilalui() {
        return jalanDilalui;
    }

    public void setJalanDilalui(String jalanDilalui) {
        this.jalanDilalui = jalanDilalui;
    }

    public KodItemPermit getJenisBahanBatu() {
        return jenisBahanBatu;
    }

    public void setJenisBahanBatu(KodItemPermit jenisBahanBatu) {
        this.jenisBahanBatu = jenisBahanBatu;
    }

    public KodUOM getLebarBangunanSementaraUom() {
        return lebarBangunanSementaraUom;
    }

    public void setLebarBangunanSementaraUom(KodUOM lebarBangunanSementaraUom) {
        this.lebarBangunanSementaraUom = lebarBangunanSementaraUom;
    }

    public KodUOM getPanjangBangunanSementaraUom() {
        return panjangBangunanSementaraUom;
    }

    public void setPanjangBangunanSementaraUom(KodUOM panjangBangunanSementaraUom) {
        this.panjangBangunanSementaraUom = panjangBangunanSementaraUom;
    }

    public KodUOM getJumlahIsipaduDipohonUom() {
        return jumlahIsipaduDipohonUom;
    }

    public void setJumlahIsipaduDipohonUom(KodUOM jumlahIsipaduDipohonUom) {
        this.jumlahIsipaduDipohonUom = jumlahIsipaduDipohonUom;
    }

    public BigDecimal getJumlahIsipadu() {
        return jumlahIsipadu;
    }

    public void setJumlahIsipadu(BigDecimal jumlahIsipadu) {
        this.jumlahIsipadu = jumlahIsipadu;
    }

    public BigDecimal getJumlahIsipaduDipohon() {
        return jumlahIsipaduDipohon;
    }

    public void setJumlahIsipaduDipohon(BigDecimal jumlahIsipaduDipohon) {
        this.jumlahIsipaduDipohon = jumlahIsipaduDipohon;
    }

    public String getKawasanAmbilBatuan() {
        return kawasanAmbilBatuan;
    }

    public void setKawasanAmbilBatuan(String kawasanAmbilBatuan) {
        this.kawasanAmbilBatuan = kawasanAmbilBatuan;
    }

    public String getKawasanPindahBatuan() {
        return kawasanPindahBatuan;
    }

    public void setKawasanPindahBatuan(String kawasanPindahBatuan) {
        this.kawasanPindahBatuan = kawasanPindahBatuan;
    }

    public BigDecimal getLebarBangunanSementara() {
        return lebarBangunanSementara;
    }

    public void setLebarBangunanSementara(BigDecimal lebarBangunanSementara) {
        this.lebarBangunanSementara = lebarBangunanSementara;
    }

    public BigDecimal getPanjangBangunanSementara() {
        return panjangBangunanSementara;
    }

    public void setPanjangBangunanSementara(BigDecimal panjangBangunanSementara) {
        this.panjangBangunanSementara = panjangBangunanSementara;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public Integer getTempohKeluar() {
        return tempohKeluar;
    }

    public void setTempohKeluar(Integer tempohKeluar) {
        this.tempohKeluar = tempohKeluar;
    }

    public Character getUnitTempohKeluar() {
        return unitTempohKeluar;
    }

    public void setUnitTempohKeluar(Character unitTempohKeluar) {
        this.unitTempohKeluar = unitTempohKeluar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanBahanBatuan getPermohonanBahanBatuan() {
        return permohonanBahanBatuan;
    }

    public void setPermohonanBahanBatuan(PermohonanBahanBatuan permohonanBahanBatuan) {
        this.permohonanBahanBatuan = permohonanBahanBatuan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public KodBandarPekanMukim getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(KodBandarPekanMukim kodBPM) {
        this.kodBPM = kodBPM;
    }

    public List<PermohonanJentera> getListJentera() {
        return listJentera;
    }

    public void setListJentera(List<PermohonanJentera> listJentera) {
        this.listJentera = listJentera;
    }

    public PermohonanJentera getPermohonanJentera() {
        return permohonanJentera;
    }

    public void setPermohonanJentera(PermohonanJentera permohonanJentera) {
        this.permohonanJentera = permohonanJentera;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getPndftran() {
        return pndftran;
    }

    public void setPndftran(String pndftran) {
        this.pndftran = pndftran;
    }

    public Character getJenisJentera() {
        return jenisJentera;
    }

    public void setJenisJentera(Character jenisJentera) {
        this.jenisJentera = jenisJentera;
    }

//    public String getBangunanSementara() {
//        return bangunanSementara;
//    }
//
//    public void setBangunanSementara(String bangunanSementara) {
//        this.bangunanSementara = bangunanSementara;
//    }
    public PermohonanPermitItem getPermohonanPermitItem() {
        return permohonanPermitItem;
    }

    public void setPermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        this.permohonanPermitItem = permohonanPermitItem;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public String getTest12() {
        return test12;
    }

    public void setTest12(String test12) {
        this.test12 = test12;
    }

    public String getTest13() {
        return test13;
    }

    public void setTest13(String test13) {
        this.test13 = test13;
    }

    public KodItemPermit getKodItemPermit1() {
        return kodItemPermit1;
    }

    public void setKodItemPermit1(KodItemPermit kodItemPermit1) {
        this.kodItemPermit1 = kodItemPermit1;
    }

    public KodItemPermit getKodItemPermit2() {
        return kodItemPermit2;
    }

    public void setKodItemPermit2(KodItemPermit kodItemPermit2) {
        this.kodItemPermit2 = kodItemPermit2;
    }

    public KodItemPermit getKodItemPermit3() {
        return kodItemPermit3;
    }

    public void setKodItemPermit3(KodItemPermit kodItemPermit3) {
        this.kodItemPermit3 = kodItemPermit3;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getUnitTempohKeluarUOM() {
        return unitTempohKeluarUOM;
    }

    public void setUnitTempohKeluarUOM(String unitTempohKeluarUOM) {
        this.unitTempohKeluarUOM = unitTempohKeluarUOM;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public List<HakmilikPermohonan> getListHM() {
        return listHM;
    }

    public void setListHM(List<HakmilikPermohonan> listHM) {
        this.listHM = listHM;
    }
}
