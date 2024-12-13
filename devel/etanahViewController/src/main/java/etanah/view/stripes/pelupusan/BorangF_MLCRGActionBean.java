/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.KodTuntutDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPermit;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/borangF_mlcrg")
public class BorangF_MLCRGActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    private Permohonan permohonan;
    private Pengguna pguna;
    private PermohonanTuntutanKos mohonTuntutKos;
    private PermohonanTuntutanKos mohonTuntutKosNew;
    private Permit permit;
    private Permit permitSebelum;
    private String idPermohonan;
    private String idHakmilik;
    private String noLot;
    private String noPajakan;
    private String kawasan;
    private String bpm;
    private String daerah;
    private HakmilikPermohonan mohonHakmilik;
    private Hakmilik hakmilik;
//    private String syarat1;
//    private String syarat2;
    private Date bertarikh;
    private Date tarikhPendaftaran;
    private Date tarikhHabisTempoh;
    private String sewaTahunan;
    private String tempohPajakan;
    private KodUOM kodTempoh;
    // private String kodTempohSementara;
    private String namaPemegangLesen;
    private String noKPPN;
    private String alamatPemegangPajakan;
    private PermitSahLaku permitSahLaku;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private Pemohon pemohon;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger log = Logger.getLogger(BorangF_MLCRGActionBean.class);
    private boolean simpan = true;
    private boolean editTmsk = true;
    private boolean editTtmt = true;
    private boolean editTujuan = true;
    private boolean permitSblm;
    private String sebabPembaharuan;
    private String stageId;
    private Pengguna peng;
    private String bool;

    @DefaultHandler
    public Resolution editForm() {
//            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/carigali/Borang_F_mlcrg.jsp").addParameter("tab", "true");
    }

    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/carigali/Borang_F_mlcrg.jsp").addParameter("tab", "true");
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        log.debug("\n\n\n");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        permohonan = pelupusanService.findById(idPermohonan);
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        permitSebelum = pelupusanService.findPermitByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//        stageId = stageId(taskId);
        stageId = "001_Kemasukan";
        if (stageId.equals("001_Kemasukan")) {
            editTujuan = true;
            bool = "true";
        } else {
            editTujuan = false;
            bool = "false";
        }

        log.info("editTujuan : " + editTujuan);

        if (permit != null) {
            editTmsk = false;
            editTtmt = false;
            simpan = false;
            permitSblm = false;
        } else {
//             idPermohonan= permohonan.getPermohonanSebelum().getIdPermohonan();
//             permohonan = pelupusanService.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
            editTmsk = true;
            editTtmt = true;
            simpan = true;
        }

        if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("MPJLB")) {
            mohonHakmilik = pelupusanService.findByIdPermohonan(idPermohonan);
            // mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR"); COMMENTED BY SHAZWAN 21062012
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DISLB"); //modifid on 21062012 
            // permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR") ;
        } else {
            mohonHakmilik = pelupusanService.findByIdPermohonan(idPermohonan);
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");
            //permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR") ;
        }
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
//        log.debug("no_permit" + permit.getNoPermit());

        if (permit != null) {
            permitSahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
        } else {
            permit = pelupusanService.findPermitByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
        }

        if (mohonHakmilik != null) {
            if (mohonHakmilik.getHakmilik() != null) {
                hakmilik = mohonHakmilik.getHakmilik();
                noLot = hakmilik.getNoLot();
                bpm = hakmilik.getBandarPekanMukim().getNama();
                daerah = hakmilik.getDaerah().getNama();
            } else {
                noLot = mohonHakmilik.getNoLot();
                bpm = mohonHakmilik.getBandarPekanMukimBaru().getNama();
                daerah = mohonHakmilik.getBandarPekanMukimBaru().getDaerah().getNama();
            }
        }

        if (permit != null) {
            tarikhPendaftaran = permit.getTarikhPermit();
//            tarikhHabisTempoh = permit.getTarikhpermitAkhir();
        }

        permohonan = pelupusanService.findById(idPermohonan);
        if (permohonan.getSebab() != null) {
            sebabPembaharuan = permohonan.getSebab();
        }
    }

    public Resolution simpanTujuan() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        log.info("-------Permit SahLaku Saving--------------------");
        permohonan = pelupusanService.findById(idPermohonan);
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);

        if (sebabPembaharuan != null) {
            permohonan.setSebab(sebabPembaharuan);
            InfoAudit ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
            pelupusanService.simpanPermohonan(permohonan);
        }
        rehydrate();
        addSimpleMessage("Maklumat telah disimpan");
        return new JSP("pelupusan/carigali/Borang_F_mlcrg.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {

        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        log.info("-------Permit SahLaku Saving--------------------");
        permohonan = pelupusanService.findById(idPermohonan);
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
//        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");
        log.info("-------permit--------------------" + permit);
        PermitSahLaku permitSahLakuTemp = null;
        if (sebabPembaharuan != null) {
            permohonan.setSebab(sebabPembaharuan);
            InfoAudit ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
            pelupusanService.simpanPermohonan(permohonan);
        }

        if (permit != null) {
            log.info("-------permitSahLaku--------------------" + permitSahLaku);
            permit.setKodCawangan(permohonan.getCawangan());
            InfoAudit ia = permit.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            log.info("-------permit is Null--------------------");

            log.info("-------Generating No permit---------");
            permit = new Permit();
            permit.setPermohonan(permohonan);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new Date());
            permit.setInfoAudit(ia);
            permit.setKodCawangan(permohonan.getCawangan());
            permit.setTarikhPermit(new java.util.Date());
        }
        log.info("-------permit Not Null--------------------");

        if (permit.getNoPermit() == null) {
            Permit maxpermit = pelupusanService.getMaxOfNoPermit();
//            if (maxpermit == null) {
//                permit.setNoPermit("1");
//            } else {
            int maxVal = Integer.parseInt(maxpermit.getNoPermit()) + 1;
            permit.setNoPermit(maxVal + "");
        }
        KodJenisPermit kodJenis = kodJenisPermitDAO.findById("G");
        if (permitSahLaku != null) {
            if (permitSahLaku.getTarikhPermitMula() != null) {
                permit.setTarikhPermitMula(permitSahLaku.getTarikhPermitMula());
            }
            if (permitSahLaku.getTarikhPermitTamat() != null) {
                permit.setTarikhpermitAkhir(permitSahLaku.getTarikhPermitTamat());
            }
        }

        permit.setKodJenisPermit(kodJenis);
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permit.setPihak(pemohon.getPihak());

//        permit.setPeruntukanTambahan(peruntukanTambahan);
        permit = pelupusanService.saveOrUpdate(permit);



        permitSahLakuTemp = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());

        InfoAudit info;
        if (permitSahLakuTemp != null) {
            log.info("-------permitSahLaku Not Null---------------::");
            info = permitSahLakuTemp.getInfoAudit();
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
            permitSahLakuTemp.setInfoAudit(info);

        } else {
            log.info("-------permitSahLaku is Null--------::");
            permitSahLakuTemp = new PermitSahLaku();
            permitSahLakuTemp.setPermit(permit);
            permitSahLakuTemp.setPermohonan(permohonan);
            info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            permitSahLakuTemp.setInfoAudit(info);
            permitSahLakuTemp.setKodCawangan(permohonan.getCawangan());
        }
        if (permitSahLakuTemp.getNoSiri() == null) {
            PermitSahLaku pTemp = pelupusanService.getMaxOfNoSiri();
            int maxSiri = Integer.parseInt(pTemp.getNoSiri()) + 1;

            permitSahLakuTemp.setNoSiri(maxSiri + "");
        }
        permitSahLakuTemp.setTarikhPermit(new java.util.Date());
        if (permitSahLaku != null) {
            permitSahLakuTemp.setTarikhPermitMula(permitSahLaku.getTarikhPermitMula());
            permitSahLakuTemp.setTarikhPermitTamat(permitSahLaku.getTarikhPermitTamat());
        }

        pelupusanService.simpanPermitSahLaku(permitSahLakuTemp);

        if (permohonan.getKodUrusan().getKod().equals("MPJLB")) {
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DISLB"); //CHANGES ON 21062012
        } else {
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DISCR");
        }

//        String amaunTuntut = getContext().getRequest().getParameter("mohonTuntutKos.amaunTuntutan");
        String amaunTuntut = mohonTuntutKos.getAmaunTuntutan().toString();
        System.out.println("amaunTuntut  :" + amaunTuntut);
        BigDecimal amaun = null;
        if (StringUtils.isNotBlank(amaunTuntut)) {
            double bayaran = Double.parseDouble(amaunTuntut);
            amaun = BigDecimal.valueOf(bayaran);
            System.out.println("amaun :" + amaun);
        }
        log.info("-------Saving Mohon Tuntut Kos---------");
        if (permohonan.getKodUrusan().getKod().equals("MPJLB")) {
            mohonTuntutKosNew = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISLB"); //CHANGES ON 21062012
        } else {
            mohonTuntutKosNew = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");
        }

        InfoAudit infoAudit = new InfoAudit();
        if ((mohonTuntutKosNew != null)) {
            infoAudit = mohonTuntutKosNew.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonTuntutKosNew = new PermohonanTuntutanKos();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        mohonTuntutKosNew.setCawangan(permohonan.getCawangan());
        if (permohonan.getKodUrusan().getKod().equals("MLCRG")) {
            mohonTuntutKosNew.setItem("Bayaran Lesen Cari Gali dan Penjelajahan");
            mohonTuntutKosNew.setKodTuntut(kodTuntutDAO.findById("DISCR"));
            mohonTuntutKosNew.setKodTransaksi(kodTuntutDAO.findById("DISCR").getKodKewTrans());
        } else if (permohonan.getKodUrusan().getKod().equals("MPJLB")) {
            mohonTuntutKosNew.setItem("Bayaran Lesen Pajakan Lombong");
            mohonTuntutKosNew.setKodTuntut(kodTuntutDAO.findById("DISLB")); //changes on 21062012
            mohonTuntutKosNew.setKodTransaksi(kodTuntutDAO.findById("DISLB").getKodKewTrans()); //changes on 21062012
            //  mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISCR")); //Tukar kod tuntut (For PJLB) changes on 21062012
            //  mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISCR").getKodKewTrans()); //Tukar kod transaksi changes on 21062012
        }
        mohonTuntutKosNew.setPermohonan(permohonan);
        mohonTuntutKosNew.setInfoAudit(infoAudit);

        if (amaun != null) {
            mohonTuntutKosNew.setAmaunTuntutan(amaun);
        }
        pelupusanService.simpanPermohonanTuntutanKos1(mohonTuntutKosNew);
        rehydrate();
        addSimpleMessage("Maklumat telah disimpan");
        return new JSP("pelupusan/carigali/Borang_F_mlcrg.jsp").addParameter("tab", "true");
    }

    public Date getBertarikh() {
        return bertarikh;
    }

    public void setBertarikh(Date bertarikh) {
        this.bertarikh = bertarikh;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKawasan() {
        return kawasan;
    }

    public void setKawasan(String kawasan) {
        this.kawasan = kawasan;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getSewaTahunan() {
        return sewaTahunan;
    }

    public void setSewaTahunan(String sewaTahunan) {
        this.sewaTahunan = sewaTahunan;
    }

    public Date getTarikhHabisTempoh() {
        return tarikhHabisTempoh;
    }

    public void setTarikhHabisTempoh(Date tarikhHabisTempoh) {
        this.tarikhHabisTempoh = tarikhHabisTempoh;
    }

    public Date getTarikhPendaftaran() {
        return tarikhPendaftaran;
    }

    public void setTarikhPendaftaran(Date tarikhPendaftaran) {
        this.tarikhPendaftaran = tarikhPendaftaran;
    }

    public String getTempohPajakan() {
        return tempohPajakan;
    }

    public void setTempohPajakan(String tempohPajakan) {
        this.tempohPajakan = tempohPajakan;
    }

    public String getAlamatPemegangPajakan() {
        return alamatPemegangPajakan;
    }

    public void setAlamatPemegangPajakan(String alamatPemegangPajakan) {
        this.alamatPemegangPajakan = alamatPemegangPajakan;
    }

    public String getNamaPemegangLesen() {
        return namaPemegangLesen;
    }

    public void setNamaPemegangLesen(String namaPemegangLesen) {
        this.namaPemegangLesen = namaPemegangLesen;
    }

    public String getNoKPPN() {
        return noKPPN;
    }

    public void setNoKPPN(String noKPPN) {
        this.noKPPN = noKPPN;
    }

    public KodUOM getKodTempoh() {
        return kodTempoh;
    }

    public void setKodTempoh(KodUOM kodTempoh) {
        this.kodTempoh = kodTempoh;
    }

    public String getNoPajakan() {
        return noPajakan;
    }

    public void setNoPajakan(String noPajakan) {
        this.noPajakan = noPajakan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public PermitSahLaku getPermitSahLaku() {
        return permitSahLaku;
    }

    public void setPermitSahLaku(PermitSahLaku permitSahLaku) {
        this.permitSahLaku = permitSahLaku;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public boolean isEditTmsk() {
        return editTmsk;
    }

    public void setEditTmsk(boolean editTmsk) {
        this.editTmsk = editTmsk;
    }

    public boolean isEditTtmt() {
        return editTtmt;
    }

    public void setEditTtmt(boolean editTtmt) {
        this.editTtmt = editTtmt;
    }

    public boolean isSimpan() {
        return simpan;
    }

    public void setSimpan(boolean simpan) {
        this.simpan = simpan;
    }

    public boolean isPermitSblm() {
        return permitSblm;
    }

    public void setPermitSblm(boolean permitSblm) {
        this.permitSblm = permitSblm;
    }

    public String getSebabPembaharuan() {
        return sebabPembaharuan;
    }

    public void setSebabPembaharuan(String sebabPembaharuan) {
        this.sebabPembaharuan = sebabPembaharuan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public boolean isEditTujuan() {
        return editTujuan;
    }

    public void setEditTujuan(boolean editTujuan) {
        this.editTujuan = editTujuan;
    }

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }
}
