/*
 * Date : 16 June 2013
 * Author : User
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.dao.KodUOMDAO;
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
import java.util.Date;
import java.util.logging.Level;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.BPelService;

@UrlBinding("/pelupusan/draf_warta_prmmk")
public class DrafWartaPRMMKActionBean extends AbleActionBean {

    private String idPermohonan;
    private TanahRizabPermohonan trizabPermohonan;
    private Pengguna peng;
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
    private HakmilikPermohonan hakmilikPermohonan;
    private Permohonan permohonan;
    private String kegunaanTanah;
    private int noWarta;
    private String tarikhAsal;
    private int noWartaCadangan;
    private String tarikhBaru;
    private String masa;
    private String kod_UOM;
    private String stageId;
    private String taskId;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(DrafWartaPRIZActionBean.class);
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodUOMDAO kodUOM;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/prmmk/draf_warta_prmmk.jsp").addParameter("tab", "true");

    }

    public Resolution showForm2() {
        return new JSP("pelupusan/prmmk/draf_warta_prmmk_view.jsp").addParameter("tab", "true");

    }

    public Resolution showCadanganBatal() {
        return new JSP("pelupusan/draf_cadangan_pembatalan_warta_rizab.jsp").addParameter("tab", "true");

    }

    public Resolution showBatal() {
        return new JSP("pelupusan/draf_warta_pembatalan_tanah_rizab.jsp").addParameter("tab", "true");

    }

    public Resolution showWarta() {
        return new JSP("pelupusan/maklumat_warta.jsp").addParameter("tab", "true");

    }

    public Resolution showFormCharting() {
        getContext().getRequest().setAttribute("charting", Boolean.TRUE);
        return new JSP("pelupusan/prmmk/draf_warta_prmmk_view.jsp").addParameter("tab", "true");
    }

    public Resolution semakCharting() {
        getContext().getRequest().setAttribute("SemakCharting", Boolean.TRUE);
        return new JSP("pelupusan/prmmk/draf_warta_prmmk_view.jsp").addParameter("tab", "true");
    }

    public Resolution simpanWartaRizabTanah() {


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
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
                if (StringUtils.isNotBlank(noLot)) {
                    trizabPermohonan.setNoLot(noLot);
                }

//                trizabPermohonan.setTarikhPengesahanWarta(sdf.parse(tarikhBaru));
                if (trizabPermohonan != null && !StringUtils.isEmpty(trizabPermohonan.getNoPW())) {
                    trizabPermohonan.setNoPW(trizabPermohonan.getNoPW());
                    LOG.info("-----------------No PW " + this.getNoPelanAkui());
                    LOG.info("-----------------No PW " + this.getNoPelanAkui());
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
                    if (!StringUtils.isEmpty(mohonHakmilik.getNoLot())) {
                        trizabPermohonan.setNoLot(mohonHakmilik.getNoLot());
                    }

                    if (mohonHakmilik.getHakmilik() != null) {
                        trizabPermohonan.setNoLot(mohonHakmilik.getHakmilik().getNoLot());
                    }

                    if (StringUtils.isNotBlank(noLot)) {
                        trizabPermohonan.setNoLot(noLot);
                    }
//                    trizabPermohonan.setNoLot(!StringUtils.isEmpty(mohonHakmilik.getNoLot()) ? mohonHakmilik.getNoLot() : mohonHakmilik.getHakmilik().getNoLot());
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
//                    trizabPermohonan.setTarikhPengesahanWarta(sdf.parse(tarikhBaru));
                    LOG.info("-----------------Keluasan Tanah " + this.getKeluasanTanah());
                    trizabPermohonan.setNoPW(trizabPermohonan.getNoPW());
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
        return new JSP("pelupusan/prmmk/draf_warta_prmmk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("----------REHYDRATE----------");
        retrieveData();
    }

    public void retrieveData() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = pelupusanService.findById(idPermohonan);
        trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = currentStageId(taskId);


        if (permohonan != null) {
            this.setTujuanRizab(permohonan.getSebab());
        } else {
            this.setTujuanRizab("");
        }

        if (trizabPermohonan != null) {
            LOG.info("-----Data Not Null-----");
            if (!"MMRE".equals(permohonan.getKodUrusan().getKod()) || !"WMRE".equals(permohonan.getKodUrusan().getKod()) || !"BMRE".equals(permohonan.getKodUrusan().getKod())) {
                this.setPeg_pengawal(trizabPermohonan.getNamaPenjaga());
                this.setPeg_penyelengara(trizabPermohonan.getPenjaga());
            }
            this.setDaerah(hakmilikPermohonan.getBandarPekanMukimBaru().getDaerah().getNama());
            this.setMukimBandarPekan(hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
            this.setNoPelanAkui(trizabPermohonan.getNoPW());
            this.setNoLot(hakmilikPermohonan.getNoLot());
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRMMK")) {
                this.setKeluasanTanah(hakmilikPermohonan.getLuasDiluluskan().toString());
                this.setKod_UOM(hakmilikPermohonan.getLuasLulusUom().getKod());
            } else {
                this.setKeluasanTanah(hakmilikPermohonan.getLuasTerlibat().toString());
                this.setKod_UOM(hakmilikPermohonan.getKodUnitLuas().getKod());
            }

//            if(trizabPermohonan.getTarikhPengesahanWarta() != null)
//            this.setTarikhBaru(trizabPermohonan.getTarikhPengesahanWarta().to);


        } else {


            LOG.info("-----Trizab  Is Null-----");
            trizabPermohonan = new TanahRizabPermohonan();
            this.setPeg_pengawal("");
            this.setDaerah(hakmilikPermohonan.getBandarPekanMukimBaru().getDaerah().getNama());
            //  this.setDaerah("");
            this.setMukimBandarPekan(hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
            this.setMukimBandarPekan(" ");
            this.setNoPelanAkui(""); //no_PW
            this.setNoLot(hakmilikPermohonan.getNoLot());
//            this.setKeluasanTanah(hakmilikPermohonan.getLuasTerlibat().toString());
            //    this.setKeluasanTanah("");
//            this.setKod_UOM(hakmilikPermohonan.getKodUnitLuas().getKod());
            //   this.setKod_UOM("");
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRMMK")) {
                this.setKeluasanTanah(hakmilikPermohonan.getLuasDiluluskan().toString());
                this.setKod_UOM(hakmilikPermohonan.getLuasLulusUom().getKod());
            } else {
                this.setKeluasanTanah(hakmilikPermohonan.getLuasTerlibat().toString());
                this.setKod_UOM(hakmilikPermohonan.getKodUnitLuas().getKod());
            }
        }
    }

    public String currentStageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
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

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
}
