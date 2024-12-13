/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanMahkamahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodKeputusanMahkamah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/rekodKeputusanMahkamah")
public class RekodKeputusanMahkamahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanMahkamahDAO kodKeputusanMahkamahDAO;
    @Inject
    private PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    private NotisPenerimaanService notisPenerimaanService;

    private String idPermohonan;
    private long idPihak;
    private String idHakmilik;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPihak permohonanPihak;
    private PermohonanMahkamah permohonanMahkamah;
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran = new ArrayList<PerbicaraanKehadiran>();
    private List<Penilaian> penilaianList = new ArrayList<Penilaian>();
    private KodKeputusanMahkamah kodKeputusanMahkamah;
    private BigDecimal tambahanPampasan;
    private BigDecimal jumlahPampasan;
    

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }

        return new JSP("pengambilan/RekodKeputusanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String)getContext().getRequest().getParameter("idHakmilik");
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan,idHakmilik,idPihak);
        if(permohonanPihak != null) {
            PermohonanMahkamah permohonanMahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if(permohonanMahkamah != null) {
                kodKeputusanMahkamah = permohonanMahkamah.getKodKeputusanMahkamah();
                tambahanPampasan = permohonanMahkamah.getTambahanPampasan();
            }

        }

        calcJumlahPampasan();

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/RekodKeputusanMahkamah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();

        }
    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String)getContext().getRequest().getParameter("idHakmilik");
        System.out.println("----------"+idPermohonan+"----"+idHakmilik+"---"+idPihak);
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan,idHakmilik,idPihak);

        if(permohonanPihak != null) {
            System.out.println("--------permohonanPihak----------"+permohonanPihak);
            PermohonanMahkamah permohonanMahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if(permohonanMahkamah == null) {
                System.out.println("----permohonanMahkamah is null----------");
                permohonanMahkamah = new PermohonanMahkamah();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanMahkamah.setInfoAudit(infoAudit);
            }else {
                System.out.println("----permohonanMahkamah ----------"+permohonanMahkamah);
                InfoAudit infoAudit = permohonanMahkamah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                permohonanMahkamah.setInfoAudit(infoAudit);
            }
            permohonanMahkamah.setPermohonanPihak(permohonanPihak);
            permohonanMahkamah.setKodKeputusanMahkamah(kodKeputusanMahkamah);
            permohonanMahkamah.setTambahanPampasan(tambahanPampasan);
            permohonanSupayaBantahanService.saveOrupdatePermohonanMahkamah(permohonanMahkamah);

        }

        calcJumlahPampasan();

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/RekodKeputusanMahkamah.jsp").addParameter("tab", "true");
    }

    private void calcJumlahPampasan() {
        senaraiPerbicaraanKehadiran = new ArrayList<PerbicaraanKehadiran>();
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        jumlahPampasan = BigDecimal.ZERO;

        if(hakmilikPermohonan != null){
        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
        if(hakmilikPerbicaraan != null) {
            senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
            for(PerbicaraanKehadiran perbicaraanKehadiran : senaraiPerbicaraanKehadiran){
                if(perbicaraanKehadiran.getPihak() == permohonanPihak) {
                    penilaianList = perbicaraanKehadiran.getSenaraiPenilaian();
                    for(Penilaian penilaian: penilaianList) {
                        jumlahPampasan = jumlahPampasan.add(penilaian.getAmaun());
                    }

                }

            }
        }
        }
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanMahkamah getPermohonanMahkamah() {
        return permohonanMahkamah;
    }

    public void setPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        this.permohonanMahkamah = permohonanMahkamah;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public KodKeputusanMahkamah getKodKeputusanMahkamah() {
        return kodKeputusanMahkamah;
    }

    public void setKodKeputusanMahkamah(KodKeputusanMahkamah kodKeputusanMahkamah) {
        this.kodKeputusanMahkamah = kodKeputusanMahkamah;
    }

    public BigDecimal getTambahanPampasan() {
        return tambahanPampasan;
    }

    public void setTambahanPampasan(BigDecimal tambahanPampasan) {
        this.tambahanPampasan = tambahanPampasan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public BigDecimal getJumlahPampasan() {
        return jumlahPampasan;
    }

    public void setJumlahPampasan(BigDecimal jumlahPampasan) {
        this.jumlahPampasan = jumlahPampasan;
    }

    public List<Penilaian> getPenilaianList() {
        return penilaianList;
    }

    public void setPenilaianList(List<Penilaian> penilaianList) {
        this.penilaianList = penilaianList;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    

    


}

