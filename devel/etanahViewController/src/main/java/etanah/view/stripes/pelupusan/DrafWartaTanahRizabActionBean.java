/*
 * Date : 14 Jul 2012
 * Author : Orogenic
 * edited by shyazli 09082012 khamis
 * to solve tarikh issue
to solve tukar
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.model.gis.PelanGIS;
import etanah.dao.KodUOMDAO;
import etanah.service.BPelService;
import etanah.service.KodService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.math.BigDecimal;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/pelupusan/draf_warta_tanah_rizab")
public class DrafWartaTanahRizabActionBean extends AbleActionBean {

    private String idPermohonan;
    private Long idHakmilik;
    private TanahRizabPermohonan trizabPermohonan;
    private Permohonan permohonanSebelum;
    private Permohonan permohonan;
    private String tujuanRizab;
    private String peg_pengawal;
    private String peg_penyelengara;
    private String daerah;
    private String mukimBandarPekan;
    private String noPelanAkui;
    private String noLot;
    private String keluasanTanah;
    private String tarikhSedia;
    private String tarikhSah;
    private String tempat;
    private String warta;
    private HakmilikPermohonan hakmilikPermohonan;
    private Hakmilik hakmilik;
    private String kegunaanTanah;
    private int noWarta;
    private String tarikhAsal;
    private int noWartaCadangan;
    private String tarikhBaru;
    private String masa;
    private String kod_UOM;
    private String id_sblm;
    private Character aktif = 'Y';
    private boolean flag = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(DrafWartaTanahRizabActionBean.class);
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodUOMDAO kodUOM;
    private boolean drafWarta = false;
    private boolean drafCadanganWarta = false;
    private Pengguna pengguna;
    private String stageId;
    private List<PelanGIS> pelanGIS;

    @DefaultHandler
    public Resolution showForm() {
        setFlag(false);
        return new JSP("pelupusan/draf_warta_tanah_rizab.jsp").addParameter("tab", "true");

    }

    public Resolution showForm1() {
        setFlag(true);
        return new JSP("pelupusan/draf_warta_tanah_rizab.jsp").addParameter("tab", "true");

    }

    public Resolution showCadanganBatal() {
        drafCadanganWarta = true;
        return new JSP("pelupusan/draf_cadangan_pembatalan_warta_rizab.jsp").addParameter("tab", "true");

    }

    public Resolution showBatal() {
        drafWarta = true;
        return new JSP("pelupusan/draf_warta_pembatalan_tanah_rizab.jsp").addParameter("tab", "true");

    }

    public Resolution showWarta() {
        return new JSP("pelupusan/maklumat_warta.jsp").addParameter("tab", "true");

    }

    public Resolution simpanWartaRizabTanah() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("-----------------idPermohonan " + idPermohonan);
        Permohonan permohonan = pelupusanService.findById(idPermohonan);
        permohonan.setSebab(this.getTujuanRizab());
        LOG.info("-----------------Tujuan Rizab " + this.getTujuanRizab());
        LOG.info("-----------------Methode Simpan-----------------");
        pelupusanService.simpanPermohonan(permohonan);

        try {
            pelupusanService.simpanPermohonan(permohonan);
            trizabPermohonan = new TanahRizabPermohonan();
            trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
            if (trizabPermohonan != null) {
                trizabPermohonan.setInfoAudit(disLaporanTanahService.findAudit(trizabPermohonan, "update", pguna));
                trizabPermohonan.setPenjaga(peg_penyelengara);
                trizabPermohonan.setNamaPenjaga(peg_pengawal);
                KodUOM code = kodUOM.findById(kod_UOM);
                trizabPermohonan.setKodUnitLuas(code);
                BigDecimal kt = new BigDecimal(keluasanTanah);
                trizabPermohonan.setLuasTerlibat(kt);
                trizabPermohonan.setNoPW(noPelanAkui);
                trizabPermohonan.setNoWarta(warta);
                if (trizabPermohonan != null && !StringUtils.isEmpty(trizabPermohonan.getNoPW())) {
                    trizabPermohonan.setNoPW(trizabPermohonan.getNoPW());
                    LOG.info("-----------------No PW " + this.getNoPelanAkui());
                    LOG.info("-----------------No PW " + this.getNoPelanAkui());
                }
                pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
                addSimpleMessage("Maklumat Berjaya Di Kemaskini");
            } else if (permohonan.getKodUrusan().getKod().equals("JMRE")) {
                trizabPermohonan = new TanahRizabPermohonan();
                trizabPermohonan.setPermohonan(permohonan);
                trizabPermohonan.setCawangan(permohonan.getCawangan());
                trizabPermohonan.setInfoAudit(disLaporanTanahService.findAudit(trizabPermohonan, "new", pguna));
//                trizabPermohonan.setTarikhPenyediaanWarta(sdf.parse(tarikhSedia));
                pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
            } else {
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
                if (mohonHakmilik != null) {
                    trizabPermohonan = new TanahRizabPermohonan();
                    trizabPermohonan.setPermohonan(permohonan);
                    trizabPermohonan.setCawangan(permohonan.getCawangan());
                    trizabPermohonan.setDaerah(permohonan.getCawangan().getDaerah());
                    trizabPermohonan.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru() != null ? mohonHakmilik.getBandarPekanMukimBaru() : mohonHakmilik.getHakmilik().getBandarPekanMukim());
                    trizabPermohonan.setNoLot(!StringUtils.isEmpty(mohonHakmilik.getNoLot()) ? mohonHakmilik.getNoLot() : mohonHakmilik.getHakmilik().getNoLot());
                    trizabPermohonan.setInfoAudit(disLaporanTanahService.findAudit(trizabPermohonan, "new", pguna));
                    trizabPermohonan.setNamaPenjaga(peg_pengawal);
                    KodUOM code = kodUOM.findById(kod_UOM);
                    trizabPermohonan.setKodUnitLuas(code);
                    LOG.info("********* CODE UOM *********" + trizabPermohonan.getKodUnitLuas().getNama());
                    LOG.info("-----------------Penjaga Pengawal " + this.getPeg_pengawal());
                    trizabPermohonan.setNamaPenjaga(peg_penyelengara);
                    LOG.info("-----------------Penjaga Penyelengara " + this.getPeg_penyelengara());
                    BigDecimal kt = new BigDecimal(keluasanTanah);
                    trizabPermohonan.setLuasTerlibat(kt);
                    trizabPermohonan.setNoPW(noPelanAkui);
                    trizabPermohonan.setNoWarta(warta);
                    LOG.info("-----------------Keluasan Tanah " + this.getKeluasanTanah());
                    trizabPermohonan.setNoPW(trizabPermohonan.getNoPW());
                    trizabPermohonan.setTarikhPenyediaanWarta(null);
                    pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
                    LOG.info("-----------------Simpan berjaya------------------ ");
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleError("Maaf, Tiada Maklumat Tanah Dijumpai, Sila Masukkan Perihal Tanah Terlebih Dahulu.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // rollback
        }
        LOG.info("status drafWarta" + drafWarta);
        if (drafWarta == true) {
            return new JSP("pelupusan/draf_warta_pembatalan_tanah_rizab.jsp").addParameter("tab", "true");
        } else {
            return new JSP("pelupusan/draf_warta_tanah_rizab.jsp").addParameter("tab", "true");

        }
    }

    public Resolution simpanWartaRizabTanah1() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("-----------------idPermohonan " + idPermohonan);
        Permohonan permohonan = pelupusanService.findById(idPermohonan);
        permohonan.setSebab(this.getTujuanRizab());
        LOG.info("-----------------Tujuan Rizab " + this.getTujuanRizab());
        LOG.info("-----------------Methode Simpan-----------------");
        pelupusanService.simpanPermohonan(permohonan);

        try {
            pelupusanService.simpanPermohonan(permohonan);
            trizabPermohonan = new TanahRizabPermohonan();
            trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
            if (trizabPermohonan != null) {
                trizabPermohonan.setInfoAudit(disLaporanTanahService.findAudit(trizabPermohonan, "update", pguna));
                trizabPermohonan.setPenjaga(peg_penyelengara);
                trizabPermohonan.setNamaPenjaga(peg_pengawal);
                KodUOM code = kodUOM.findById(kod_UOM);
                trizabPermohonan.setKodUnitLuas(code);
                BigDecimal kt = new BigDecimal(keluasanTanah);
                trizabPermohonan.setLuasTerlibat(kt);
                trizabPermohonan.setNoPW(noPelanAkui);
                trizabPermohonan.setNoWarta(warta);
                if (trizabPermohonan != null && !StringUtils.isEmpty(trizabPermohonan.getNoPW())) {
                    trizabPermohonan.setNoPW(trizabPermohonan.getNoPW());
                    LOG.info("-----------------No PW " + this.getNoPelanAkui());
                    LOG.info("-----------------No PW " + this.getNoPelanAkui());
                }
                pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
                addSimpleMessage("Maklumat Berjaya Di Kemaskini");
            } else if (permohonan.getKodUrusan().getKod().equals("JMRE")) {
                trizabPermohonan = new TanahRizabPermohonan();
                trizabPermohonan.setPermohonan(permohonan);
                trizabPermohonan.setCawangan(permohonan.getCawangan());
                trizabPermohonan.setInfoAudit(disLaporanTanahService.findAudit(trizabPermohonan, "new", pguna));
//                trizabPermohonan.setTarikhPenyediaanWarta(sdf.parse(tarikhSedia));
                pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
            } else {
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
                if (mohonHakmilik != null) {
                    trizabPermohonan = new TanahRizabPermohonan();
                    trizabPermohonan.setPermohonan(permohonan);
                    trizabPermohonan.setCawangan(permohonan.getCawangan());
                    trizabPermohonan.setDaerah(permohonan.getCawangan().getDaerah());
                    trizabPermohonan.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru() != null ? mohonHakmilik.getBandarPekanMukimBaru() : mohonHakmilik.getHakmilik().getBandarPekanMukim());
                    trizabPermohonan.setNoLot(!StringUtils.isEmpty(mohonHakmilik.getNoLot()) ? mohonHakmilik.getNoLot() : mohonHakmilik.getHakmilik().getNoLot());
                    trizabPermohonan.setInfoAudit(disLaporanTanahService.findAudit(trizabPermohonan, "new", pguna));
                    trizabPermohonan.setNamaPenjaga(peg_pengawal);
                    KodUOM code = kodUOM.findById(kod_UOM);
                    trizabPermohonan.setKodUnitLuas(code);
                    LOG.info("********* CODE UOM *********" + trizabPermohonan.getKodUnitLuas().getNama());
                    LOG.info("-----------------Penjaga Pengawal " + this.getPeg_pengawal());
                    trizabPermohonan.setNamaPenjaga(peg_penyelengara);
                    LOG.info("-----------------Penjaga Penyelengara " + this.getPeg_penyelengara());
                    BigDecimal kt = new BigDecimal(keluasanTanah);
                    trizabPermohonan.setLuasTerlibat(kt);
                    trizabPermohonan.setNoPW(noPelanAkui);
                    trizabPermohonan.setNoWarta(warta);
                    LOG.info("-----------------Keluasan Tanah " + this.getKeluasanTanah());
                    trizabPermohonan.setNoPW(trizabPermohonan.getNoPW());
                    trizabPermohonan.setTarikhPenyediaanWarta(null);
                    pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
                    LOG.info("-----------------Simpan berjaya------------------ ");
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleError("Maaf, Tiada Maklumat Tanah Dijumpai, Sila Masukkan Perihal Tanah Terlebih Dahulu.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // rollback
        }
        LOG.info("status drafWarta" + drafWarta);
        return new JSP("pelupusan/draf_warta_pembatalan_tanah_rizab.jsp").addParameter("tab", "true");
//        else {
//            return new JSP("pelupusan/draf_warta_tanah_rizab.jsp").addParameter("tab", "true");
//
//        }
    }

    public Resolution carianIdSblm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = pelupusanService.findById(idPermohonan);

        if (permohonan != null) {

            id_sblm = (String) getContext().getRequest().getParameter("id_sblm");
            LOG.info("-----ID Permohonan Terdahulu-----: " + id_sblm);

            permohonanSebelum = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(id_sblm);
            if (permohonanSebelum != null) {
                trizabPermohonan = new TanahRizabPermohonan();
                trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(id_sblm);

                if (trizabPermohonan != null) {
                    tarikhAsal = trizabPermohonan.getTarikhWarta().toString();
                    pelanGIS = pelupusanService.findPelanPWByIdMohon(idPermohonan, "PW");
//                    noPelanAkui = pelanGIS.getNoPelanAkui();
//                    noPelanAkui = trizabPermohonan.getNoPW();
                    warta = trizabPermohonan.getNoWarta();
                    tujuanRizab = permohonan.getSebab();
                    daerah = permohonan.getCawangan().getDaerah().getNama();
                    //mukimBandarPekan = permohonan.getCawangan().getCawanganUtama().toString();

                    //LOG.info("-----Tarikh Asal-----: " + tarikhAsal);
                    LOG.info("-----No. PW-----: " + noPelanAkui);
                    LOG.info("-----No. Warta-----: " + warta);
                    LOG.info("-----Tujuan Rizab-----: " + tujuanRizab);
                    LOG.info("-----Daerah-----: " + daerah);
                    //LOG.info("-----Mukim/Bandar/Pekan-----: " + mukimBandarPekan);


                    hakmilikPermohonan = pelupusanService.findByIdPermohonan(id_sblm);
                    if (hakmilikPermohonan != null) {
                        noLot = hakmilikPermohonan.getNoLot();
                        keluasanTanah = hakmilikPermohonan.getLuasTerlibat().toString();
                        kod_UOM = hakmilikPermohonan.getKodUnitLuas().getKod().toString();
                        LOG.info("-----No. Lot-----: " + noLot);
                        LOG.info("-----Keluasan Tanah-----: " + keluasanTanah.concat(kod_UOM));
                    }

                    addSimpleMessage("Maklumat dijumpai");
                }
            } else {
                addSimpleMessage("Pemohonan sebelum tidak dijumpai ");
            }
        } else {
            addSimpleMessage("Pemohonan tidak dijumpai ");
        }

        return new JSP("pelupusan/draf_cadangan_pembatalan_warta_rizab.jsp").addParameter("tab", "true");
    }

    public Resolution simpanWartaCadanganPembatalanRizabTanah() {
        LOG.info(" ++ kodUrusan = " + permohonan.getKodUrusan().getKod());
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("-----------------idPermohonan : " + idPermohonan);
        Permohonan permohonan = pelupusanService.findById(idPermohonan);
        permohonan.setSebab(this.getTujuanRizab());
        LOG.info("-----------------Tujuan Rizab : " + this.getTujuanRizab());
        LOG.info("-----------------Methode Simpan-----------------");
        pelupusanService.simpanPermohonan(permohonan);

        try {
            pelupusanService.simpanPermohonan(permohonan);
            trizabPermohonan = new TanahRizabPermohonan();
            trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
            if (trizabPermohonan != null) {
                trizabPermohonan.setInfoAudit(disLaporanTanahService.findAudit(trizabPermohonan, "update", pguna));
                trizabPermohonan.setPenjaga(peg_penyelengara);
                trizabPermohonan.setNamaPenjaga(peg_pengawal);
                KodUOM code = kodUOM.findById(kod_UOM);
                trizabPermohonan.setKodUnitLuas(code);
                BigDecimal kt = new BigDecimal(keluasanTanah);
                trizabPermohonan.setLuasTerlibat(kt);
                trizabPermohonan.setNoPW(noPelanAkui);
                trizabPermohonan.setNoWarta(warta);
                trizabPermohonan.setTarikhPenyediaanWarta(sdf.parse(tarikhSedia));
                trizabPermohonan.setTarikhWarta(sdf.parse(tarikhAsal));
                if (trizabPermohonan != null && !StringUtils.isEmpty(trizabPermohonan.getNoPW())) {
                    trizabPermohonan.setNoPW(trizabPermohonan.getNoPW());
                    LOG.info("-----------------No PW :" + this.getNoPelanAkui());
                    LOG.info("-----------------No Warta :" + this.getNoWarta());
                }
                pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
                addSimpleMessage("Maklumat Berjaya Di Kemaskini");
            } else {
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
                if (mohonHakmilik != null) {
                    trizabPermohonan = new TanahRizabPermohonan();
                    trizabPermohonan.setPermohonan(permohonan);
                    trizabPermohonan.setCawangan(permohonan.getCawangan());
                    trizabPermohonan.setDaerah(permohonan.getCawangan().getDaerah());
                    trizabPermohonan.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru() != null ? mohonHakmilik.getBandarPekanMukimBaru() : mohonHakmilik.getHakmilik().getBandarPekanMukim());
                    trizabPermohonan.setNoLot(!StringUtils.isEmpty(mohonHakmilik.getNoLot()) ? mohonHakmilik.getNoLot() : mohonHakmilik.getHakmilik().getNoLot());
                    trizabPermohonan.setInfoAudit(disLaporanTanahService.findAudit(trizabPermohonan, "new", pguna));
                    trizabPermohonan.setNamaPenjaga(peg_pengawal);
                    KodUOM code = kodUOM.findById(kod_UOM);
                    trizabPermohonan.setKodUnitLuas(code);

                    LOG.info("********* CODE UOM *********" + trizabPermohonan.getKodUnitLuas().getNama());
                    LOG.info("-----------------Penjaga Pengawal " + this.getPeg_pengawal());
                    trizabPermohonan.setNamaPenjaga(peg_penyelengara);
                    LOG.info("-----------------Penjaga Penyelengara " + this.getPeg_penyelengara());
                    BigDecimal kt = new BigDecimal(keluasanTanah);
                    trizabPermohonan.setLuasTerlibat(kt);
                    trizabPermohonan.setNoPW(noPelanAkui);
                    trizabPermohonan.setNoWarta(warta);
                    LOG.info("-----------------Keluasan Tanah " + this.getKeluasanTanah());
                    trizabPermohonan.setNoPW(trizabPermohonan.getNoPW());
                    trizabPermohonan.setAktif(aktif);
                    trizabPermohonan.setTarikhPenyediaanWarta(sdf.parse(tarikhSedia));
                    trizabPermohonan.setTarikhWarta(sdf.parse(tarikhAsal));
                    pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
                    LOG.info("-----------------Simpan berjaya------------------ ");
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleError("Maaf, Tiada Maklumat Tanah Dijumpai, Sila Masukkan Perihal Tanah Terlebih Dahulu.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // rollback
        }
        return new JSP("pelupusan/draf_cadangan_pembatalan_warta_rizab.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("----------REHYDRATE----------");

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId: BPEL ON ----" + stageId);
        } else {
            stageId = "34SmkSemulaDrfWrt";
            System.out.println("-------------stageId: BPEL OFF ----" + stageId);
        }
        retrieveData();
    }

    public void retrieveData() {
        LOG.info("----retrieveData----");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        idHakmilik = (Long) getContext().getRequest().getSession().getAttribute("idHakmilik");
//        LOG.info("---idhakmilik---" + idHakmilik);
        Permohonan permohonan = pelupusanService.findById(idPermohonan);
        trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (!permohonan.getKodUrusan().getKod().equals("JMRE")) {
            hakmilik = hakmilikPermohonan.getHakmilik();
        }
        LOG.info("== kodUrusan + " + permohonan.getKodUrusan().getKod());
        if (permohonan != null) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBRZ")) {
                ArrayList stageTerlibat = new ArrayList<String>();
                stageTerlibat.add("22TrmPlnWrt");
                stageTerlibat.add("23SmkDrfWrt");
                stageTerlibat.add("24SmkDrfWrt");
                stageTerlibat.add("25PerakuanDrafWarta");

                for (Object s : stageTerlibat) {
                    if (stageId.equalsIgnoreCase(s.toString())) {
                        drafWarta = true;
                    }
                }
            }
            this.setTujuanRizab(permohonan.getSebab());

            if (!permohonan.getKodUrusan().getKod().equals("MMRE") && !permohonan.getKodUrusan().getKod().equals("WMRE") && !permohonan.getKodUrusan().getKod().equals("BMRE") && !permohonan.getKodUrusan().getKod().equals("JMRE")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    this.setId_sblm(permohonan.getPermohonanSebelum().getIdPermohonan());
                }
                LOG.info("---if ! mmre---");
            }
        } else {
            this.setTujuanRizab("");
            this.setId_sblm("");
        }

        if (trizabPermohonan != null) {
            LOG.info("-----Data Not Null-----");


            //edit hakmilik by Syazwan
            if (!permohonan.getKodUrusan().getKod().equals("JMRE")) {
                this.setPeg_pengawal(trizabPermohonan.getNamaPenjaga());
                this.setPeg_penyelengara(trizabPermohonan.getPenjaga());
                if (hakmilikPermohonan.getBandarPekanMukimBaru() == null) {
                    LOG.info("---if BPM = null---");
                    this.setDaerah(hakmilik.getBandarPekanMukim().getDaerah().getNama());
                    this.setMukimBandarPekan(hakmilik.getBandarPekanMukim().getNama());
                    this.setKeluasanTanah(hakmilik.getLuas().toString());
                    this.setKod_UOM(hakmilik.getKodUnitLuas().getKod());
                } else {
                    LOG.info("--- else BPM = null ---");
                    this.setDaerah(hakmilikPermohonan.getBandarPekanMukimBaru().getDaerah().getNama());
                    this.setMukimBandarPekan(hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
                    this.setKeluasanTanah(hakmilikPermohonan.getLuasTerlibat().toString());
                    this.setKod_UOM(hakmilikPermohonan.getKodUnitLuas().getKod());
                }
                this.setNoPelanAkui(trizabPermohonan.getNoPW());
                this.setWarta(trizabPermohonan.getNoWarta());
                this.setKeluasanTanah(hakmilikPermohonan.getLuasTerlibat().toString());
                this.setKod_UOM(hakmilikPermohonan.getKodUnitLuas().getKod());
            }
//            else if (permohonan.getKodUrusan().getKod().equals("BMRE")) {
//                pelanGIS = pelupusanService.findPelanPWByIdMohon(permohonan.getIdPermohonan(), "PW");
//                if (!pelanGIS.isEmpty()) {
//                    for (PelanGIS pelan : pelanGIS) {
//                        LOG.info("size : "+ pelanGIS.size());
//                        LOG.info("NO PW : " + pelanGIS.get(0).getNoPelanAkui());
//                        noPelanAkui = pelan.getNoPelanAkui();
//                        this.setPeg_pengawal(trizabPermohonan.getNamaPenjaga());
//                        this.setPeg_penyelengara(trizabPermohonan.getPenjaga());
//                        if (hakmilikPermohonan.getBandarPekanMukimBaru() == null) {
//                            LOG.info("---if BPM = null---");
//                            this.setDaerah(hakmilik.getBandarPekanMukim().getDaerah().getNama());
//                            this.setMukimBandarPekan(hakmilik.getBandarPekanMukim().getNama());
//                            this.setKeluasanTanah(hakmilik.getLuas().toString());
//                            this.setKod_UOM(hakmilik.getKodUnitLuas().getKod());
//                        } else {
//                            LOG.info("--- else BPM = null ---");
//                            this.setDaerah(hakmilikPermohonan.getBandarPekanMukimBaru().getDaerah().getNama());
//                            this.setMukimBandarPekan(hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
//                            this.setKeluasanTanah(hakmilikPermohonan.getLuasTerlibat().toString());
//                            this.setKod_UOM(hakmilikPermohonan.getKodUnitLuas().getKod());
//                        }
////                this.setNoPelanAkui(trizabPermohonan.getNoPW());
//                        this.setNoPelanAkui(noPelanAkui);
//                        this.setWarta(trizabPermohonan.getNoWarta());
//                        this.setKeluasanTanah(hakmilikPermohonan.getLuasTerlibat().toString());
//                        this.setKod_UOM(hakmilikPermohonan.getKodUnitLuas().getKod());
//                    }
//                }
//            }


            if (!permohonan.getKodUrusan().getKod().equals("PRIZ") && !permohonan.getKodUrusan().getKod().equals("JMRE")) {
                LOG.info(" ## masuk if time ##");
                // this.setTarikhAsal(sdf.format(trizabPermohonan.getTarikhWarta())); Comment out sementara
            }


            if (hakmilik != null) {
                if (StringUtils.isNotBlank(hakmilik.getNoLot())) {
                    this.setNoLot(hakmilik.getNoLot());
                }
            } else if (!permohonan.getKodUrusan().getKod().equals("JMRE")) {
                this.setNoLot(hakmilikPermohonan.getNoLot());
            }

        } else if (permohonan.getKodUrusan().getKod().equals("JMRE")) {
            trizabPermohonan = new TanahRizabPermohonan();
            trizabPermohonan.setPermohonan(permohonan);
            trizabPermohonan.setCawangan(permohonan.getCawangan());
            trizabPermohonan.setInfoAudit(disLaporanTanahService.findAudit(trizabPermohonan, "new", pguna));
//                trizabPermohonan.setTarikhPenyediaanWarta(sdf.parse(tarikhSedia));
            pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
        } else {
            LOG.info("-----Trizab  Is Null-----");
            trizabPermohonan = new TanahRizabPermohonan();
            this.setPeg_pengawal("");
            this.setDaerah(hakmilikPermohonan.getBandarPekanMukimBaru().getDaerah().getNama());
            this.setMukimBandarPekan(hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
//            this.setMukimBandarPekan(" ");
            this.setNoPelanAkui(""); //no_PW
            this.setWarta("");
            this.setNoLot(hakmilikPermohonan.getNoLot());
            this.setKeluasanTanah(hakmilikPermohonan.getLuasTerlibat().toString());
            this.setKod_UOM(hakmilikPermohonan.getKodUnitLuas().getKod());

        }
    }

    public String getId_sblm() {
        return id_sblm;
    }

    public void setId_sblm(String id_sblm) {
        this.id_sblm = id_sblm;
    }

    public String getTujuanRizab() {
        return tujuanRizab;
    }

    public void setTujuanRizab(String tujuanRizab) {
        this.tujuanRizab = tujuanRizab;
    }

    public String getPeg_pengawal() {
        return peg_pengawal;
    }

    public void setPeg_pengawal(String peg_pengawal) {
        this.peg_pengawal = peg_pengawal;
    }

    public String getPeg_penyelengara() {
        return peg_penyelengara;
    }

    public void setPeg_penyelengara(String peg_penyelengara) {
        this.peg_penyelengara = peg_penyelengara;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getMukimBandarPekan() {
        return mukimBandarPekan;
    }

    public void setMukimBandarPekan(String mukimBandarPekan) {
        this.mukimBandarPekan = mukimBandarPekan;
    }

    public String getNoPelanAkui() {
        return noPelanAkui;
    }

    public void setNoPelanAkui(String noPelanAkui) {
        this.noPelanAkui = noPelanAkui;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getKeluasanTanah() {
        return keluasanTanah;
    }

    public void setKeluasanTanah(String keluasanTanah) {
        this.keluasanTanah = keluasanTanah;
    }

    public String getTarikhSah() {
        return tarikhSah;
    }

    public void setTarikhSah(String tarikhSah) {
        this.tarikhSah = tarikhSah;
    }

    public String getTarikhSedia() {
        return tarikhSedia;
    }

    public void setTarikhSedia(String tarikhSedia) {
        this.tarikhSedia = tarikhSedia;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getKegunaanTanah() {
        return kegunaanTanah;
    }

    public void setKegunaanTanah(String kegunaanTanah) {
        this.kegunaanTanah = kegunaanTanah;
    }

    public int getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(int noWarta) {
        this.noWarta = noWarta;
    }

    public String getTarikhAsal() {
        return tarikhAsal;
    }

    public void setTarikhAsal(String tarikhAsal) {
        this.tarikhAsal = tarikhAsal;
    }

    public int getNoWartaCadangan() {
        return noWartaCadangan;
    }

    public void setNoWartaCadangan(int noWartaCadangan) {
        this.noWartaCadangan = noWartaCadangan;
    }

    public String getTarikhBaru() {
        return tarikhBaru;
    }

    public void setTarikhBaru(String tarikhBaru) {
        this.tarikhBaru = tarikhBaru;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getKod_UOM() {
        return kod_UOM;
    }

    public void setKod_UOM(String kod_UOM) {
        this.kod_UOM = kod_UOM;
    }

    public TanahRizabPermohonan getTrizabPermohonan() {
        return trizabPermohonan;
    }

    public void setTrizabPermohonan(TanahRizabPermohonan trizabPermohonan) {
        this.trizabPermohonan = trizabPermohonan;
    }

    public String getWarta() {
        return warta;
    }

    public void setWarta(String warta) {
        this.warta = warta;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Character getAktif() {
        return aktif;
    }

    public void setAktif(Character aktif) {
        this.aktif = aktif;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    /**
     * @return the flag
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(Long idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public boolean isDrafWarta() {
        return drafWarta;
    }

    public void setDrafWarta(boolean drafWarta) {
        this.drafWarta = drafWarta;
    }

    public boolean isDrafCadanganWarta() {
        return drafCadanganWarta;
    }

    public void setDrafCadanganWarta(boolean drafCadanganWarta) {
        this.drafCadanganWarta = drafCadanganWarta;
    }

    public KodUOMDAO getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(KodUOMDAO kodUOM) {
        this.kodUOM = kodUOM;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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

    public List<PelanGIS> getPelanGIS() {
        return pelanGIS;
    }

    public void setPelanGIS(List<PelanGIS> pelanGIS) {
        this.pelanGIS = pelanGIS;
    }
}
