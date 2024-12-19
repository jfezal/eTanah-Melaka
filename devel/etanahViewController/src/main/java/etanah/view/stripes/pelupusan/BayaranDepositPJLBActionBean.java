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
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
@UrlBinding("/pelupusan/bayaran_deposit_pjlb")
public class BayaranDepositPJLBActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    private Permohonan permohonan;
    private Pengguna pguna;
    private PermohonanTuntutanKos mohonTuntutKos;
    private Permit permit;
    private String idPermohonan;
    private String idHakmilik;
    private String noLot;
    private String noPajakan;
    private String kawasan;
    private String bpm;
    private String daerah;
    private HakmilikPermohonan mohonHakmilik;
    private Hakmilik hakmilik;
    private Date bertarikh;
    private Date tarikhPendaftaran;
    private Date tarikhHabisTempoh;
    private String sewaTahunan;
    private String tempohPajakan;
    private KodUOM kodTempoh;
   // private String kodTempohSementara;
    private String namaPemegangLesen;
    private String noKPPN;
    private String amaunSebenar1;
    private String amaunTuntutan1;
    private String alamatPemegangPajakan;
    private PermitSahLaku permitSahLaku;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private Pemohon pemohon;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger log = Logger.getLogger(BorangFActionBean.class);

    @DefaultHandler
    public Resolution editForm() {
        return new JSP("pelupusan/pjlb/bayaran_deposit_pjlb.jsp").addParameter("tab", "true");
    }

    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/pjlb/bayaran_deposit_pjlb.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        permohonan = pelupusanService.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PJLB")) {
            mohonHakmilik = pelupusanService.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISDL"); //for Bayaran Deposit 50% Pajakan Lombong
            BigDecimal amaunSebenar1 = mohonTuntutKos.getAmaunSebenar();
            BigDecimal amaunTuntutan1 = mohonTuntutKos.getAmaunTuntutan();
            
            
        }
        if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISLB"); //DISDL
            if(mohonTuntutKos != null){
                BigDecimal amaunSebenar = mohonTuntutKos.getAmaunSebenar();
                BigDecimal amaunTuntutan = mohonTuntutKos.getAmaunTuntutan();
                System.out.println("Amaun Sebenar : " +amaunSebenar);
                System.out.println("Amaun Tuntutan : " +amaunTuntutan);
            }
           
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
    }

    public Resolution simpan() {

        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");

        String amaunTuntut = getContext().getRequest().getParameter("mohonTuntutKos.amaunTuntutan");
        String amaunSebenar = getContext().getRequest().getParameter("mohonTuntutKos.amaunSebenar");
        System.out.println("amaunSebenar  :"+amaunSebenar);
        System.out.println("amaunTuntut  :"+amaunTuntut);
        BigDecimal amaun = null;
        BigDecimal amaun1 = null;
        
        if (StringUtils.isNotBlank(amaunSebenar)) {
            double bayaran = Double.parseDouble(amaunSebenar);
            amaun1 = BigDecimal.valueOf(bayaran);
            System.out.println("amaun sebenar :" + amaun1);
        }
        if (StringUtils.isNotBlank(amaunTuntut)) {
            double bayaran = Double.parseDouble(amaunTuntut);
            amaun = BigDecimal.valueOf(bayaran);
            System.out.println("amaun :" + amaun);
        }
        log.info("-------Saving Mohon Tuntut Kos---------");
        if(permohonan.getKodUrusan().getKod().equals("PJLB"))
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISDL"); //for Bayaran Deposit 50% Pajakan Lombong
        else
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISLB");

        InfoAudit infoAudit = new InfoAudit();
        if ((mohonTuntutKos != null)) {
            infoAudit = mohonTuntutKos.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonTuntutKos = new PermohonanTuntutanKos();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());     
        }
        mohonTuntutKos.setCawangan(permohonan.getCawangan());
        if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
            mohonTuntutKos.setItem("Bayaran Lesen Pajakan Lombong");
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISLB")); //changes on 21062012
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISLB").getKodKewTrans()); //changes on 21062012  DISDL
        }
        mohonTuntutKos.setPermohonan(permohonan);
        mohonTuntutKos.setInfoAudit(infoAudit);

        if (amaun != null) {
            mohonTuntutKos.setAmaunTuntutan(amaun);
        }
        if (amaun1 != null) {
            mohonTuntutKos.setAmaunSebenar(amaun1);
        }
        pelupusanService.simpanPermohonanTuntutanKos1(mohonTuntutKos);
        rehydrate();
        addSimpleMessage("Maklumat telah disimpan");
        return new JSP("pelupusan/pjlb/bayaran_deposit_pjlb.jsp").addParameter("tab", "true");
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
    public String getAmaunSebenar() {
        return amaunSebenar1;
    }

    public void setAmaunSebenar1(String amaunSebenar1) {
        this.amaunSebenar1 = amaunSebenar1;
    }
    
    public String getAmaunTuntutan1() {
        return amaunTuntutan1;
    }

    public void setAmaunTuntutan1(String amaunTuntutan1) {
        this.amaunTuntutan1 = amaunTuntutan1;
    }
    
}

