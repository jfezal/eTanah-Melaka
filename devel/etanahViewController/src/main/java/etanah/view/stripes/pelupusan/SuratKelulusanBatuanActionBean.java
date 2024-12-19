/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.ParseException;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermitItemDAO;
import etanah.dao.PermitItemKuantitiDAO;
import etanah.dao.PermohonanJenteraDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBahanBatu;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodKeputusan;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.PermitItemKuantiti;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanJentera;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Akmal modified by Shazwan
 */
@UrlBinding("/pelupusan/surat_kelulusan_batuan")
public class SuratKelulusanBatuanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(SuratKelulusanBatuanActionBean.class);
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermitItemDAO permitItemDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermitItemKuantitiDAO permitItemKuantitiDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanJenteraDAO permohonanJenteraDAO;
    @Inject
    etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(SuratKelulusanBatuanActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Permohonan permohonan;
    private PermohonanKertas mohonKertas;
    private HakmilikPermohonan hakmilikPermohonan;
    private Permit permit;
    private String kodLuaslulusUOM;
    private String kodUnitLuasUOM;
    private String namaLuaslulusUOM;
    private double kuponAmaun;
    private String keputusan;
    private Pengguna pguna;
    private String stageId;
    private String bilMesyuarat;
    private Date tarikh;
    private BigDecimal kuantitiMax_kntt;
    private String kuantitiMax_UOM;
    private String namaKuantitiMax_UOM;
    private BigDecimal kuantiti; // FOR JUMLAH ISIPADU DISYORKAN
    private String kuantitiUOM;  // FOR JUMLAH ISIPADU DISYORKAN
    private String kodkuantitiUOM;
    private int tempohDisyorkan;
    private String tempohDisyorkanUOM;
    private int tempoh;  // FOR TEMPOH MAX_KNTT_TEMPOH     
    private String tempohUOM; // FOR TEMPOH MAX_KNTT_UOM
    private String namaTempohUOM;
    private int kuponQty;
    private BigDecimal kadar;
    private String kadarUOM;
    private BigDecimal royalti;
    private BigDecimal cagaran;
    private BigDecimal kupon;
    private BigDecimal jumlah;
    private BigDecimal cagarJalan;
    private String syarat;
    private Boolean edit;
    private String kodNeg;

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//       String ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        LOG.info("---------------------------stageID---------------------" + stageId);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (kodNeg.equals("04")) {
            if (permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                String idAliranMhonFasa = permohonan.getKodUrusan().getKod().equals("PBPTD") ? "perakuan_draf_rencana_ptd" : "semak_peraku"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                FasaPermohonan mohonFasa = new FasaPermohonan();
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, idAliranMhonFasa);
                if (mohonFasa != null) {
                    keputusan = mohonFasa.getKeputusan().getKod();
                }
            } else {
                LOG.info(keputusan);
                keputusan = permohonan.getKeputusan() != null ? permohonan.getKeputusan().getKod() : keputusan;
            }
        } else if (kodNeg.equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                String idAliranMhonFasa = null;
                if (permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                    idAliranMhonFasa = "keputusan";
                } else if (permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                    idAliranMhonFasa = "keputusan_ptg";
                } else {
                    idAliranMhonFasa = "terima_keputusan_mmk";
                }
                FasaPermohonan mohonFasa = new FasaPermohonan();
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, idAliranMhonFasa);
                if (mohonFasa != null) {
                    keputusan = mohonFasa.getKeputusan().getKod();
                }
                if (keputusan != null) {
                    if (keputusan.equals("T")) {
                        SimpanSuratTolakNS();
                    }
                }
            } else if (permohonan.getKodUrusan().getKod().equals("PBMMK")) {
                String idAliranMhonFasa = null;
                FasaPermohonan mohonFasa = new FasaPermohonan();
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, idAliranMhonFasa);
                if (mohonFasa != null) {
                    keputusan = permohonan.getKeputusan().getKod();
                }
            } else if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                String idAliranMhonFasa = null;
                if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                    idAliranMhonFasa = "25TrmMklmnKptsn";
                }
                FasaPermohonan mohonFasa = new FasaPermohonan();
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, idAliranMhonFasa);
                if (mohonFasa != null) {
                    if (mohonFasa.getKeputusan() != null) {

                        keputusan = mohonFasa.getKeputusan().getKod();
                    }
                }
                if (keputusan != null) {
                    if (keputusan.equals("T")) {
                        SimpanSuratTolakNS();
                    }
                }
            } else {
                LOG.info(keputusan);
                keputusan = permohonan.getKeputusan() != null ? permohonan.getKeputusan().getKod() : keputusan;
            }
        }
        edit = Boolean.TRUE;
        return new JSP("pelupusan/batuan/surat_kelulusan_batuan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//       String ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        LOG.info("---------------------------stageID---------------------" + stageId);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (kodNeg.equals("04")) {
            if (permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                String idAliranMhonFasa = permohonan.getKodUrusan().getKod().equals("PBPTD") ? "perakuan_draf_rencana_ptd" : "semak_peraku"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                FasaPermohonan mohonFasa = new FasaPermohonan();
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, idAliranMhonFasa);
                keputusan = mohonFasa.getKeputusan().getKod();
            } else {
                LOG.info(keputusan);
                keputusan = permohonan.getKeputusan() != null ? permohonan.getKeputusan().getKod() : keputusan;
            }
        } else if (kodNeg.equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                String idAliranMhonFasa = null;
                if (permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                    idAliranMhonFasa = "keputusan";
                } else if (permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                    idAliranMhonFasa = "keputusan_ptg";
                } else {
                    idAliranMhonFasa = "terima_keputusan_mmk";
                }
                FasaPermohonan mohonFasa = new FasaPermohonan();
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, idAliranMhonFasa);
                if (mohonFasa != null) {
                    keputusan = mohonFasa.getKeputusan().getKod();
                }
            } else {
                LOG.info(keputusan);
                keputusan = permohonan.getKeputusan() != null ? permohonan.getKeputusan().getKod() : keputusan;
            }
        }
        edit = Boolean.FALSE;
        return new JSP("pelupusan/batuan/surat_kelulusan_batuan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanLulusTolak() {

        keputusan = getContext().getRequest().getParameter("kpsn");
        getContext().getRequest().setAttribute("keputusan", keputusan);
        getContext().getRequest().setAttribute("edit", edit);
        return new JSP("pelupusan/batuan/surat_kelulusan_batuan_mlk.jsp").addParameter("tab", "true");
    }
//    public Resolution simpanSetuju(){
//        
//    }

    public Resolution SimpanSuratTolak() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        if (idPermohonan != null) {

            permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
            permohonan.setKeputusanOleh(peng);
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonan.setInfoAudit(info);

            pelupusanService.simpanPermohonan(permohonan);
            if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBMMK") || permohonan.getKodUrusan().getKod().equals("LPSP")) {
                mohonKertas.setInfoAudit(info);
                pelupusanService.simpanPermohonanKertas(mohonKertas);
            }
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            addSimpleMessage("IDPERMOHONAN TIDAK DIJUMPAI -- SILA HUBUNGI PENTADBIR SISTEM");
        }

        rehydrate();
        edit = Boolean.TRUE;
        return new JSP("pelupusan/batuan/surat_kelulusan_batuan_mlk.jsp").addParameter("tab", "true");

    }

    public Resolution SimpanSuratTolakNS() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        if (idPermohonan != null) {

            permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
            permohonan.setKeputusanOleh(peng);
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonan.setInfoAudit(info);

            pelupusanService.simpanPermohonan(permohonan);
            if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBMMK") || permohonan.getKodUrusan().getKod().equals("LPSP")) {
                mohonKertas.setInfoAudit(info);
                pelupusanService.simpanPermohonanKertas(mohonKertas);
            }
        } else {
        }

        rehydrate();
        edit = Boolean.TRUE;
        return new JSP("pelupusan/batuan/surat_kelulusan_batuan_mlk.jsp").addParameter("tab", "true");

    }

    public Resolution SimpanSuratKelulusan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        InfoAudit info = new InfoAudit();
//        info.setDimasukOleh(pguna);
//        info.setTarikhMasuk(new java.util.Date());
        if (idPermohonan != null) {

            permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
            permohonan.setKeputusanOleh(peng);
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonan.setInfoAudit(info);
            permohonan.setTarikhKeputusan(new java.util.Date());
            pelupusanService.simpanPermohonan(permohonan);
            if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTG") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBMMK") || permohonan.getKodUrusan().getKod().equals("LPSP")) {
                if (mohonKertas != null) {
                    info = mohonKertas.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());

                } else {

                    mohonKertas = new PermohonanKertas();

                }
                mohonKertas.setInfoAudit(info);
                mohonKertas.setPermohonan(permohonan);
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBPTD")) {
                    mohonKertas.setTajuk("Surat Keputusan");
                }
                mohonKertas.setCawangan(permohonan.getCawangan());
                pelupusanService.simpanPermohonanKertas(mohonKertas);
            }

            /*
             * MOHON BAHAN BATUAN 
             */
            PermohonanBahanBatuan bahanBatuan = new PermohonanBahanBatuan();
            bahanBatuan = pelupusanService.findPermohonanBahanBatuanByIdMohon(idPermohonan);
            if (bahanBatuan.getJumlahIsipaduUom() != null) {
                bahanBatuan.setJumlahIsipadu(kuantiti);
                bahanBatuan.setJumlahIsipaduUom(kodUOMDAO.findById(kodkuantitiUOM));
            }
            pelupusanService.simpanPermohonanBahanBatuan(bahanBatuan);
            /*
             * END OF BAHAN BATUAN
             */
            /*
             * PERMIT
             */
//            if (!permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBPTD")) {

                permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);


                if (permit == null) {
                    Permit p = new Permit();
                    info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    p.setInfoAudit(info);
                    p.setPermohonan(permohonan);
                    p.setKeterangan(syarat != null ? syarat : new String());
                    pengambilanService.saveOrUpdatePermit(p);
                } else {
                    permit.setInfoAudit(info);
                    permit.setPermohonan(permohonan);
                    permit.setKeterangan(syarat != null ? syarat : new String());
                    pelupusanService.simpanSaveUpdatePermit(permit);
                }

                permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
                /*
                 * END OF PERMIT
                 */
                if (permit != null) {
                    PermohonanPermitItem mohonPermitItem = new PermohonanPermitItem();
                    //        mohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohon(idPermohonan);
                    List<PermohonanPermitItem> mohonPermitItemList = pelupusanService.findPermohonanPermitItemByIdMohonList(idPermohonan);
                    /*
                     * PERMIT_ITEM
                     */
                    PermitItem permitItem = new PermitItem();
                    PermohonanBahanBatuan pbb = new PermohonanBahanBatuan();
                    pbb = pelupusanService.findByIdMBB(idPermohonan);

                    permitItem = pelupusanService.findPermitItemByIdPermit2(permit.getIdPermit());

                    if (permitItem == null) {

                        permitItem = new PermitItem();
                        permitItem.setPermit(permit);
                        permitItem.setInfoAudit(info);
                        permitItem.setKodCawangan(permohonan.getCawangan());
                        permitItem.setKodItemPermit(pbb.getJenisBahanBatu());
                        pelupusanService.simpanPermitItem(permitItem);

                    }
                    /*
                     * END OF PERMIT_ITEM
                     */
                    /*
                     * PERMIT_ITEM_KUANTITI
                     */
                    PermitItemKuantiti permitItemKuantiti = new PermitItemKuantiti();
                    permitItem = pelupusanService.findPermitItemByIdPermit(permit.getIdPermit());
                    permitItemKuantiti = pelupusanService.findPermitItemKuantitiByIdPermitItem(permitItem.getIdPermitItem());
                    if (permitItemKuantiti == null) {
                        permitItemKuantiti = new PermitItemKuantiti();
                        permitItemKuantiti.setInfoAudit(info);
                        permitItemKuantiti.setKuantitiMax(kuantitiMax_kntt);
                        if (StringUtils.isNotBlank(kuantitiMax_UOM)) {
                            permitItemKuantiti.setKuantitiMaxUom(kodUOMDAO.findById(kuantitiMax_UOM));
                        }
                        permitItemKuantiti.setKodCawangan(permohonan.getCawangan());
                        permitItemKuantiti.setPermitItem(permitItem);
                        permitItemKuantiti.setKodItemPermit(bahanBatuan.getJenisBahanBatu());
                        permitItemKuantiti.setTempoh(tempoh);
                        if (StringUtils.isNotBlank(tempohUOM)) {
                            permitItemKuantiti.setTempohUom(kodUOMDAO.findById(tempohUOM));
                        }
                        permitItemKuantitiDAO.save(permitItemKuantiti);
                    } else {
                        permitItemKuantiti.setInfoAudit(info);
                        permitItemKuantiti.setKuantitiMax(kuantitiMax_kntt);
                        if (StringUtils.isNotBlank(kuantitiMax_UOM)) {
                            permitItemKuantiti.setKuantitiMaxUom(kodUOMDAO.findById(kuantitiMax_UOM));
                        }
                        permitItemKuantiti.setTempoh(tempoh);
                        if (StringUtils.isNotBlank(tempohUOM)) {
                            permitItemKuantiti.setTempohUom(kodUOMDAO.findById(tempohUOM));
                        }
                        permitItemKuantitiDAO.saveOrUpdate(permitItemKuantiti);
                    }
            }

                /*
                 * END OF PERMIT_ITEM_KUANTITI
                 */
//            }
            /*
             * MOHON_TUNTUT_KOS
             */
            settingPermohonanTuntutanKos(idPermohonan, info);
            /*
             * END OF MOHON_TUNTUT_KOS
             */
            /*
             * MOHON_HAKMILIK
             */
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            if (hakmilikPermohonan != null) {
                hakmilikPermohonan.setLuasLulusUom(kodUOMDAO.findById(hakmilikPermohonan.getKodUnitLuas().getKod()));
                pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
            }
            /*
             * END
             */
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            addSimpleMessage("IDPERMOHONAN TIDAK DIJUMPAI -- SILA HUBUNGI PENTADBIR SISTEM");
        }



        rehydrate();
        edit = Boolean.TRUE;
        // getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/batuan/surat_kelulusan_batuan_mlk.jsp").addParameter("tab", "true");
        return new JSP("pelupusan/batuan/surat_kelulusan_batuan_mlk.jsp").addParameter("tab", "true");
    }

    public void settingPermohonanTuntutanKos(String idPermohonan, InfoAudit info) {
        /*
         * CASE ROYALTY
         */
        PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4C"); //DIS4C //12103

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Royalti");
            mohonTuntutKos.setAmaunTuntutan(royalti);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DIS4C"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DIS4C").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Royalti");
            mohonTuntutKos.setAmaunTuntutan(royalti);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DIS4C"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DIS4C").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }


        /*
         * CASE Borang 4B
         */
        mohonTuntutKos = new PermohonanTuntutanKos();
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");//12102

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Borang 4B");
            mohonTuntutKos.setAmaunTuntutan(royalti);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DIS4B"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DIS4B").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Borang 4B");
            mohonTuntutKos.setAmaunTuntutan(royalti);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DIS4B"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DIS4B").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
        //mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("")); MUST INSERT BUT XTAU LG
                /*
         * CASE BAYARAN KUPON
         */
        mohonTuntutKos = new PermohonanTuntutanKos();
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Kupon");
            mohonTuntutKos.setAmaunTuntutan(kupon);
            mohonTuntutKos.setKuantiti(kuponQty);
            BigDecimal seUnit = new BigDecimal(5);
            mohonTuntutKos.setAmaunSeunit(seUnit);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISKD"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISKD").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Kupon");
            mohonTuntutKos.setAmaunTuntutan(kupon);
            BigDecimal seUnit = new BigDecimal(5);
            mohonTuntutKos.setAmaunSeunit(seUnit);
            mohonTuntutKos.setKuantiti(kuponQty);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISKD"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISKD").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
        /*
         * END OF BAYARAN KUPON
         */
        /*
         * CASE CAGARAN
         */
        mohonTuntutKos = new PermohonanTuntutanKos();
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISWC");

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Cagaran");
            mohonTuntutKos.setAmaunTuntutan(cagaran);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISWC"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISWC").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Cagaran");
            mohonTuntutKos.setAmaunTuntutan(cagaran);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISWC"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISWC").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
        /*
         * END OF CAGARAN
         */
        /*
         * CASE CAGARAN JALAN
         */
        mohonTuntutKos = new PermohonanTuntutanKos();
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Cagaran Jalan");
            mohonTuntutKos.setAmaunTuntutan(cagarJalan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISCJ"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISCJ").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Cagaran Jalan");
            mohonTuntutKos.setAmaunTuntutan(cagarJalan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISCJ"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISCJ").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
        /*
         * END OF CAGARAN JALAN
         */

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//       String ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        keputusan = getContext().getRequest().getParameter("keputusan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        kodNeg = conf.getProperty("kodNegeri");
//        stageId = "sedia_surat_lulustolak";
        LOG.info("---------------------------stageID---------------------" + stageId);
        permohonan = permohonanDAO.findById(idPermohonan);
        // (PBPTG using jkbb)if (permohonan.getKodUrusan().getKod().equals("PBMMK") || permohonan.getKodUrusan().getKod().equals("PBPTG") || permohonan.getKodUrusan().getKod().equals("LPSP")) {
        if (permohonan.getKodUrusan().getKod().equals("PBMMK") || permohonan.getKodUrusan().getKod().equals("LPSP")) {
            mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "RMN");
        } else {
            mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        }
        PermohonanBahanBatuan mohonBatu = new PermohonanBahanBatuan();
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getLuasLulusUom() != null) {
//                kodLuaslulusUOM = hakmilikPermohonan.getLuasLulusUom().getKod();
                namaLuaslulusUOM = hakmilikPermohonan.getLuasLulusUom().getNama();
            }
            if (hakmilikPermohonan.getKodUnitLuas() != null) {
                kodUnitLuasUOM = hakmilikPermohonan.getKodUnitLuas().getKod();
            }
        }

        /*
         * PERMIT AREA
         */
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        if (permit != null) {
            if (permit.getKeterangan() != null) {
                syarat = permit.getKeterangan();
            }

            List<PermitItem> permitItemList = pelupusanService.findPermitItemByIdPermitList(permit.getIdPermit());
            PermitItemKuantiti pik = new PermitItemKuantiti();
            if (!permitItemList.isEmpty()) {
                for (int i = 0; i < permitItemList.size(); i++) {
                    pik = pelupusanService.findPermitItemKuantitiByIdPermitItem(permitItemList.get(i).getIdPermitItem());
                    if (pik != null) {
                        if (pik.getTempoh() != null) {
                            kuantitiMax_kntt = pik.getKuantitiMax();
                            kuantitiMax_UOM = pik.getKuantitiMaxUom().getKod();
                            namaKuantitiMax_UOM = pik.getKuantitiMaxUom().getNama();
                            tempoh = pik.getTempoh();
                            tempohUOM = pik.getTempohUom().getKod();
                            if (pik.getTempohUom() != null) {
                                namaTempohUOM = pik.getTempohUom().getNama();
                            }
                        }
                    }
                }
            }
        }
        //Move keputusan to here since to filter for keputusan for PBPTD,PBPTG and PPBB
        if (kodNeg.equals("04")) {
            if (permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                String idAliranMhonFasa = permohonan.getKodUrusan().getKod().equals("PBPTD") ? "perakuan_draf_rencana_ptd" : "semak_peraku"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                FasaPermohonan mohonFasa = new FasaPermohonan();
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, idAliranMhonFasa);
                keputusan = mohonFasa.getKeputusan().getKod();
            } else {
                LOG.info(keputusan);
                keputusan = permohonan.getKeputusan() != null ? permohonan.getKeputusan().getKod() : keputusan;
            }
        } else if (kodNeg.equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG") || permohonan.getKodUrusan().equals("LPSP")) {
                String idAliranMhonFasa = null;
                if (permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                    idAliranMhonFasa = "15Keputusan";
                } else if (permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                    idAliranMhonFasa = "23Keputusan";
                } else if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                    idAliranMhonFasa = "25TrmMklmnKptsn";
                } else {
                    idAliranMhonFasa = "terima_keputusan_mmk";
                }
                FasaPermohonan mohonFasa = new FasaPermohonan();
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, idAliranMhonFasa);
                if (mohonFasa != null) {
                    keputusan = mohonFasa.getKeputusan().getKod();
                }

                System.out.println("id aliran mohon fasa : " + idAliranMhonFasa);
            } else {
                LOG.info(keputusan);
                keputusan = permohonan.getKeputusan() != null ? permohonan.getKeputusan().getKod() : keputusan;
            }
        }
        /*
         * MOHON BATU
         */
        jumlah = new BigDecimal(0);
        mohonBatu = pelupusanService.findPermohonanBahanBatuanByIdMohon(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
            if (keputusan.equals("L")) {
                if (mohonBatu != null) {
                    kuantiti = mohonBatu.getJumlahIsipadu();
                    if (mohonBatu.getJumlahIsipaduUom() != null) {
                        kuantitiUOM = mohonBatu.getJumlahIsipaduUom().getNama();
                        kodkuantitiUOM = mohonBatu.getJumlahIsipaduUom().getKod();
                    }
                    if (mohonBatu.getTempohDisyor() != null) {
                        tempohDisyorkan = mohonBatu.getTempohDisyor();

                    }
                    if (mohonBatu.getUnitTempohKeluar() != null) {
                        tempohDisyorkanUOM = mohonBatu.getUnitTempohKeluar().getNama();
                    }

                    HakmilikPermohonan hm = new HakmilikPermohonan();
                    hm = pelupusanService.findByIdPermohonan(idPermohonan);
                    if (hm.getKodMilik().getKod().equals('K') || hm.getKodMilik().getKod().equals('R')) {
                        kadar = mohonBatu.getJenisBahanBatu().getRoyaltiTanahKerajaan();
                        kadarUOM = mohonBatu.getJenisBahanBatu().getRoyaltiTanahMilikUom().getNama();
                    } else {
                        kadar = mohonBatu.getJenisBahanBatu().getRoyaltiTanahMilik();
                        kadarUOM = mohonBatu.getJenisBahanBatu().getRoyaltiTanahKerajaanUom().getNama();
                    }
                    if (kuantitiMax_kntt != null) {
                        royalti = kadar.multiply(kuantitiMax_kntt);
                        double royaltiDouble = Double.parseDouble(royalti.toString());
                        cagaran = BigDecimal.valueOf(royaltiDouble * 0.2);
                    }
                    PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");
                    if (mohonTuntutKos != null) {
                        kuponQty = mohonTuntutKos.getKuantiti();
                        if (kodNeg.equals("04")) {
                            kuponAmaun = 50.00;
                        } else {
                            kuponAmaun = 10.00;
                        }
                        kupon = BigDecimal.valueOf(Double.parseDouble(String.valueOf(kuponQty)) * kuponAmaun);
                        if (cagaran != null) {
                            jumlah = royalti.add(cagaran).add(kupon);
                        }
                    } else {
                        if (kodNeg.equals("04")) {
                            kuponAmaun = 50.00;
                        } else {
                            kuponAmaun = 10.00;
                        }
                    }
                    //Penambahan Cagaran Jalan 
                    PermohonanTuntutanKos mohonTuntutKosCagaranJalan = new PermohonanTuntutanKos();
                    mohonTuntutKosCagaranJalan = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");
                    if (mohonTuntutKosCagaranJalan != null) {
                        cagarJalan = mohonTuntutKosCagaranJalan.getAmaunTuntutan();
                        if (cagarJalan != null) {
                            jumlah = jumlah.add(cagarJalan);
                        }
                    }
                    if (royalti != null && cagaran != null && kupon != null && cagarJalan != null) {
                        jumlah = royalti.add(cagaran).add(kupon).add(cagarJalan);
                    }
                }
            }
        } else if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PBMMK")) {
            if (mohonBatu != null) {
                kuantiti = mohonBatu.getJumlahIsipadu();
                if (mohonBatu.getJumlahIsipaduUom() != null) {
                    kuantitiUOM = mohonBatu.getJumlahIsipaduUom().getNama();
                    kodkuantitiUOM = mohonBatu.getJumlahIsipaduUom().getKod();
                }
                if (mohonBatu.getTempohDisyor() != null) {
                    tempohDisyorkan = mohonBatu.getTempohDisyor();
                }
                if (mohonBatu.getUnitTempohKeluar() != null) {
                    tempohDisyorkanUOM = mohonBatu.getUnitTempohKeluar().getNama();
                }

                HakmilikPermohonan hm = new HakmilikPermohonan();
                hm = pelupusanService.findByIdPermohonan(idPermohonan);
                if (hm.getKodMilik().getKod().equals('H')) {
                    kadar = mohonBatu.getJenisBahanBatu().getRoyaltiTanahMilik();
                    kadarUOM = mohonBatu.getJenisBahanBatu().getRoyaltiTanahMilikUom().getNama();
                } else if (hm.getKodMilik().getKod().equals('K') || hm.getKodMilik().getKod().equals('R')) {
                    kadar = mohonBatu.getJenisBahanBatu().getRoyaltiTanahKerajaan();
                    kadarUOM = mohonBatu.getJenisBahanBatu().getRoyaltiTanahKerajaanUom().getNama();
                }
                if (kuantitiMax_kntt != null) {
                    royalti = kadar.multiply(kuantitiMax_kntt);
                    double royaltiDouble = Double.parseDouble(royalti.toString());
                    cagaran = BigDecimal.valueOf(royaltiDouble * 3);
                }
                PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");
                if (mohonTuntutKos != null) {
                    kuponQty = mohonTuntutKos.getKuantiti();
                    if (kodNeg.equals("04")) {
                        kuponAmaun = 50.00;
                    } else {
                        kuponAmaun = 10.00;
                    }
                    kupon = BigDecimal.valueOf(Double.parseDouble(String.valueOf(kuponQty)) * kuponAmaun);
                    if (cagaran != null) {
                        jumlah = royalti.add(cagaran).add(kupon);
                    }
                } else {
                    if (kodNeg.equals("04")) {
                        kuponAmaun = 50.00;
                    } else {
                        kuponAmaun = 10.00;
                    }
                }
                //Penambahan Cagaran Jalan 
                PermohonanTuntutanKos mohonTuntutKosCagaranJalan = new PermohonanTuntutanKos();
                mohonTuntutKosCagaranJalan = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");
                if (mohonTuntutKosCagaranJalan != null) {
                    cagarJalan = mohonTuntutKosCagaranJalan.getAmaunTuntutan();
                    if (cagarJalan != null) {
                        jumlah = jumlah.add(cagarJalan);
                    }
                }
                if (royalti != null && cagaran != null && kupon != null && cagarJalan != null) {
                    jumlah = royalti.add(cagaran).add(kupon).add(cagarJalan);
                }
            }
        }


    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    public String getBilMesyuarat() {
        return bilMesyuarat;
    }

    public void setBilMesyuarat(String bilMesyuarat) {
        this.bilMesyuarat = bilMesyuarat;
    }

    public BigDecimal getCagaran() {
        return cagaran;
    }

    public void setCagaran(BigDecimal cagaran) {
        this.cagaran = cagaran;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getKadar() {
        return kadar;
    }

    public void setKadar(BigDecimal kadar) {
        this.kadar = kadar;
    }

    public BigDecimal getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(BigDecimal kuantiti) {
        this.kuantiti = kuantiti;
    }

    public BigDecimal getKupon() {
        return kupon;
    }

    public void setKupon(BigDecimal kupon) {
        this.kupon = kupon;
    }

    public BigDecimal getRoyalti() {
        return royalti;
    }

    public void setRoyalti(BigDecimal royalti) {
        this.royalti = royalti;
    }

    public String getSyarat() {
        return syarat;
    }

    public void setSyarat(String syarat) {
        this.syarat = syarat;
    }

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public int getTempoh() {
        return tempoh;
    }

    public void setTempoh(int tempoh) {
        this.tempoh = tempoh;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getKuantitiUOM() {
        return kuantitiUOM;
    }

    public void setKuantitiUOM(String kuantitiUOM) {
        this.kuantitiUOM = kuantitiUOM;
    }

    public String getTempohUOM() {
        return tempohUOM;
    }

    public void setTempohUOM(String tempohUOM) {
        this.tempohUOM = tempohUOM;
    }

    public String getKadarUOM() {
        return kadarUOM;
    }

    public void setKadarUOM(String kadarUOM) {
        this.kadarUOM = kadarUOM;
    }

    public int getKuponQty() {
        return kuponQty;
    }

    public void setKuponQty(int kuponQty) {
        this.kuponQty = kuponQty;
    }

    public double getKuponAmaun() {
        return kuponAmaun;
    }

    public void setKuponAmaun(double kuponAmaun) {
        this.kuponAmaun = kuponAmaun;
    }

    public String getKuantitiMax_UOM() {
        return kuantitiMax_UOM;
    }

    public void setKuantitiMax_UOM(String kuantitiMax_UOM) {
        this.kuantitiMax_UOM = kuantitiMax_UOM;
    }

    public BigDecimal getKuantitiMax_kntt() {
        return kuantitiMax_kntt;
    }

    public void setKuantitiMax_kntt(BigDecimal kuantitiMax_kntt) {
        this.kuantitiMax_kntt = kuantitiMax_kntt;
    }

    public int getTempohDisyorkan() {
        return tempohDisyorkan;
    }

    public void setTempohDisyorkan(int tempohDisyorkan) {
        this.tempohDisyorkan = tempohDisyorkan;
    }

    public String getTempohDisyorkanUOM() {
        return tempohDisyorkanUOM;
    }

    public void setTempohDisyorkanUOM(String tempohDisyorkanUOM) {
        this.tempohDisyorkanUOM = tempohDisyorkanUOM;
    }

    public String getKodkuantitiUOM() {
        return kodkuantitiUOM;
    }

    public void setKodkuantitiUOM(String kodkuantitiUOM) {
        this.kodkuantitiUOM = kodkuantitiUOM;
    }

    public String getNamaKuantitiMax_UOM() {
        return namaKuantitiMax_UOM;
    }

    public void setNamaKuantitiMax_UOM(String namaKuantitiMax_UOM) {
        this.namaKuantitiMax_UOM = namaKuantitiMax_UOM;
    }

    public String getNamaTempohUOM() {
        return namaTempohUOM;
    }

    public void setNamaTempohUOM(String namaTempohUOM) {
        this.namaTempohUOM = namaTempohUOM;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getKodLuaslulusUOM() {
        return kodLuaslulusUOM;
    }

    public void setKodLuaslulusUOM(String kodLuaslulusUOM) {
        this.kodLuaslulusUOM = kodLuaslulusUOM;
    }

    public String getNamaLuaslulusUOM() {
        return namaLuaslulusUOM;
    }

    public void setNamaLuaslulusUOM(String namaLuaslulusUOM) {
        this.namaLuaslulusUOM = namaLuaslulusUOM;
    }

    public BigDecimal getCagarJalan() {
        return cagarJalan;
    }

    public void setCagarJalan(BigDecimal cagarJalan) {
        this.cagarJalan = cagarJalan;
    }

    public String getKodUnitLuasUOM() {
        return kodUnitLuasUOM;
    }

    public void setKodUnitLuasUOM(String kodUnitLuasUOM) {
        this.kodUnitLuasUOM = kodUnitLuasUOM;
    }

    public String getKodNeg() {
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
        this.kodNeg = kodNeg;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }
}
