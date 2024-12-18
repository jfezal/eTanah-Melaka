/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.KodNegeri;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
@UrlBinding("/pelupusan/tanahAdat")
public class TanahAdatActionBean extends AbleActionBean{

    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    PelupusanService pservice ;
    @Inject
    PemohonDAO pemohonDAO ;

    private String idPermohonan ;
    private Permohonan permohonan ;
    private Pemohon pemohon ;
    private String namaSuku ;
    private String dipimpinOleh ;
    private String alamat1 ;
    private String alamat2 ;
    private String alamat3 ;
    private String alamat4 ;
    private String nama ;
    private String poskod ;
    private KodNegeri negeri ;
    private String dibayar ;
    private Pihak pihak ;
    private static final Logger LOG = Logger.getLogger(TanahAdatActionBean.class);


    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/tanah_adat/ketuaSuku.jsp").addParameter("tab", "true");
    }
    public Resolution showFormTB() {
        return new JSP("pelupusan/tanah_adat/terimaBayaran.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        pemohon = pservice.findPemohonByIdPemohon(idPermohonan);
        pihak = pemohon.getPihak();
        if(pihak != null){
            namaSuku = pihak.getSuku().getNama();
            dipimpinOleh = pihak.getSuku().getPemimpinNama();
            alamat1 = pihak.getSuku().getPemimpinAlamat1();
            alamat2 = pihak.getSuku().getPemimpinAlamat2();
            alamat3 = pihak.getSuku().getPemimpinAlamat3();
            alamat4 = pihak.getSuku().getPemimpinAlamat4();
            poskod = pihak.getSuku().getPemimpinPoskod();
            negeri = pihak.getSuku().getPemimpinNegeri();
            nama = pihak.getNama() ;
        }
        if(permohonan != null){
            dibayar = permohonan.getCatatan() ;
        }
    }

    public Resolution simpanBayaran() {
        dibayar = getContext().getRequest().getParameter("check");
        LOG.info("Telah dibayar..?" + dibayar);
         idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        if(dibayar != null && !dibayar.equals("")){
            ia = permohonan.getInfoAudit() ;
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
            permohonan.setCatatan("Telah Dibayar");
            permohonan.setNilaiKeputusan(BigDecimal.TEN);
        }
        else {
             ia = permohonan.getInfoAudit() ;
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
             permohonan.setCatatan("Belum Dibayar");
        }
        pservice.simpanPermohonan(permohonan);
        addSimpleMessage("Maklumat telah disimpan") ;
         return new JSP("pelupusan/tanah_adat/terimaBayaran.jsp").addParameter("tab", "true");
    }
        
    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getDipimpinOleh() {
        return dipimpinOleh;
    }

    public void setDipimpinOleh(String dipimpinOleh) {
        this.dipimpinOleh = dipimpinOleh;
    }
    
    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }
    
    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getNamaSuku() {
        return namaSuku;
    }

    public void setNamaSuku(String namaSuku) {
        this.namaSuku = namaSuku;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDibayar() {
        return dibayar;
    }

    public void setDibayar(String dibayar) {
        this.dibayar = dibayar;
    }

    

  
    
}
