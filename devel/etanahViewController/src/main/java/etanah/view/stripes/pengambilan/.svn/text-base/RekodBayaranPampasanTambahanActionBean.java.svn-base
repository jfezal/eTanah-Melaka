/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AmbilPampasan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.BayaranPampasanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/bayaranPampasanTambahan")
public class RekodBayaranPampasanTambahanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    private Hakmilik hakmilik;
    @Inject
    private PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    private PerbicaraanKehadiran perbicaraanKehadiran;


    private BayaranPampasanService bayaranPampasanService;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanPihak permohonanPihak;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private AmbilPampasan ambilPampasan;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private BigDecimal jumTerimaPampasan;
    private KodCaraBayaran kodCaraBayaran;
    private String noDok;
    private Date tarikhDok;
    private KodBank kodBank;
    private BigDecimal jumCaraBayar1 = new BigDecimal(0.00);
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private Permohonan permohonanSblm;
    private HakmilikPermohonan hakmilikPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
          
        }
        return new JSP("pengambilan/RekodBayaranPampasanTambahan.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan,hakmilik.getIdHakmilik(),idPihak);

        if (permohonanPihak != null) {
                    
                    ambilPampasan = permohonanSupayaBantahanService.getAmbilPampasanByidMP(permohonanPihak.getIdPermohonanPihak());
                    if(ambilPampasan != null) {
                        jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                        kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
                        noDok = ambilPampasan.getNoDok();
                        tarikhDok = ambilPampasan.getTarikhDok();
                        kodBank = ambilPampasan.getKodBank();
                    }
                }//if
        if(permohonanPihak == null){

            addSimpleError("Tiada Data");
        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/RekodBayaranPampasanTambahan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if(idHakmilik!=null)
            getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);


        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            }
        }
    }

    public Resolution simpan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan,hakmilik.getIdHakmilik(),idPihak);
        if(permohonanPihak != null) {
            ambilPampasan = permohonanSupayaBantahanService.getAmbilPampasanByidMP(permohonanPihak.getIdPermohonanPihak());
            if(ambilPampasan != null) {
                ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                ambilPampasan.setNoDok(noDok);
                ambilPampasan.setTarikhDok(tarikhDok);
                ambilPampasan.setKodBank(kodBank);
                InfoAudit ia = ambilPampasan.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                ambilPampasan.setInfoAudit(ia);
                permohonanSupayaBantahanService.saveOrupdateAmbilPampasan(ambilPampasan);

            }else {
                ambilPampasan = new AmbilPampasan();
                ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                ambilPampasan.setNoDok(noDok);
                ambilPampasan.setTarikhDok(tarikhDok);
                ambilPampasan.setKodBank(kodBank);
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                ambilPampasan.setInfoAudit(info);
                permohonanSupayaBantahanService.saveOrupdateAmbilPampasan(ambilPampasan);
            }
            ambilPampasan = permohonanSupayaBantahanService.getAmbilPampasanByidMP(permohonanPihak.getIdPermohonanPihak());
                    if(ambilPampasan != null) {
                        jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                        kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
                        noDok = ambilPampasan.getNoDok();
                        tarikhDok = ambilPampasan.getTarikhDok();
                        kodBank = ambilPampasan.getKodBank();
                    }

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/RekodBayaranPampasanTambahan.jsp").addParameter("tab", "true");

    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(RekodBayaranPampasanTambahanActionBean.class, "locate");
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public BigDecimal getJumCaraBayar1() {
        return jumCaraBayar1;
    }

    public void setJumCaraBayar1(BigDecimal jumCaraBayar1) {
        this.jumCaraBayar1 = jumCaraBayar1;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonanSblm() {
        return permohonanSblm;
    }

    public void setPermohonanSblm(Permohonan permohonanSblm) {
        this.permohonanSblm = permohonanSblm;
    }

    public AmbilPampasan getAmbilPampasan() {
        return ambilPampasan;
    }

    public void setAmbilPampasan(AmbilPampasan ambilPampasan) {
        this.ambilPampasan = ambilPampasan;
    }

    public BigDecimal getJumTerimaPampasan() {
        return jumTerimaPampasan;
    }

    public void setJumTerimaPampasan(BigDecimal jumTerimaPampasan) {
        this.jumTerimaPampasan = jumTerimaPampasan;
    }

    public KodBank getKodBank() {
        return kodBank;
    }

    public void setKodBank(KodBank kodBank) {
        this.kodBank = kodBank;
    }

    public KodCaraBayaran getKodCaraBayaran() {
        return kodCaraBayaran;
    }

    public void setKodCaraBayaran(KodCaraBayaran kodCaraBayaran) {
        this.kodCaraBayaran = kodCaraBayaran;
    }

    public String getNoDok() {
        return noDok;
    }

    public void setNoDok(String noDok) {
        this.noDok = noDok;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public Date getTarikhDok() {
        return tarikhDok;
    }

    public void setTarikhDok(Date tarikhDok) {
        this.tarikhDok = tarikhDok;
    }
    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }


 }


