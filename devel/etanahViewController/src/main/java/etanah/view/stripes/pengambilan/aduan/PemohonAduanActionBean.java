/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJnsPemohonDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Alamat;
import etanah.model.InfoAudit;
import etanah.model.KodJnsPemohon;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/pengambilan/aduan_kerosakan/pemaju")
public class PemohonAduanActionBean extends AbleActionBean {
    
    String namaPemaju;
    String kategori;
    String alamat1;
    String alamat2;
    String alamat3;
    String alamat4;
    String poskod;
    String negeri;
    String notel;
    String emel;
    String idPermohonan;
    
    @Inject
    AduanService aduanService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodJnsPemohonDAO kodJnsPemohonDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    
    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pemohon pemohon = aduanService.findPemohonByIdmohon(idPermohonan);
       if(pemohon!=null){
        namaPemaju = pemohon.getNama();
        kategori = pemohon.getKodJnsPemohon().getKod();
        if (pemohon.getAlamat() != null) {
            alamat1 = pemohon.getAlamat().getAlamat1();
            alamat2 = pemohon.getAlamat().getAlamat2();
            alamat3 = pemohon.getAlamat().getAlamat3();
            alamat4 = pemohon.getAlamat().getAlamat4();
            poskod = pemohon.getAlamat().getPoskod();
            negeri = pemohon.getAlamat().getNegeri().getKod();
        }}
        System.out.println("Start showForm");
        return new JSP("pengambilan/aduan_kerosakan/maklumat_pemaju.jsp").addParameter("tab", "true");
   }
    
    public Resolution showFormPemaju() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pemohon pemohon = aduanService.findPemohonByIdmohon(idPermohonan);
        namaPemaju = pemohon.getNama();
        kategori = pemohon.getKodJnsPemohon().getKod();
        if (pemohon.getAlamat() != null) {
            alamat1 = pemohon.getAlamat().getAlamat1();
            alamat2 = pemohon.getAlamat().getAlamat2();
            alamat3 = pemohon.getAlamat().getAlamat3();
            alamat4 = pemohon.getAlamat().getAlamat4();
            poskod = pemohon.getAlamat().getPoskod();
            negeri = pemohon.getAlamat().getNegeri().getKod();
        }
        
        System.out.println("Start showForm");
        return new JSP("pengambilan/aduan_kerosakan/maklumat_pemaju.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpan() {
                Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pemohon pemohon = aduanService.findPemohonByIdmohon(idPermohonan);
        if (pemohon != null) {
        } else {
            pemohon = new Pemohon();
        }
        pemohon.setCawangan(permohonan.getCawangan());
        pemohon.setNama(namaPemaju);
        pemohon.setPermohonan(permohonan);
        KodJnsPemohon kodJnsPemohon = kodJnsPemohonDAO.findById("PE");
        pemohon.setKodJnsPemohon(kodJnsPemohon);
//        pemohon.setPemohonJenis(kategori);
        Alamat alamat = new Alamat();
        alamat.setAlamat1(alamat1);
        alamat.setAlamat2(alamat2);
        alamat.setAlamat3(alamat3);
        alamat.setAlamat4(alamat4);
        alamat.setPoskod(poskod);
        alamat.setNegeri(kodNegeriDAO.findById(negeri));
        pemohon.setAlamat(alamat);
        pemohon.setInfoAudit(ia);
        aduanService.savePemohonPemaju(pemohon);
        System.out.println("Start showForm");
        return new JSP("pengambilan/aduan_kerosakan/maklumat_pemaju.jsp").addParameter("tab", "true");
    }

    public String getNamaPemaju() {
        return namaPemaju;
    }

    public void setNamaPemaju(String namaPemaju) {
        this.namaPemaju = namaPemaju;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNotel() {
        return notel;
    }

    public void setNotel(String notel) {
        this.notel = notel;
    }

    public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    
}
