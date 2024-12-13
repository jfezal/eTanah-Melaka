/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanPerbincanganDAO;
import etanah.dao.KodPeringkatDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AduanPerbincangan;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.InfoAudit;
import etanah.model.KodPeringkat;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.NotaSiasatan;
import etanah.model.ambil.NotaSiasatanLengkap;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author user
 */
@UrlBinding("/pengambilan/aduan_kerosakan/rekod_keputusan_perbincgn")
public class RekodKeputusanPerbincanganActionBean extends AbleActionBean {

    @Inject
    AduanService aduanService;
    String report_p_id_mohon;
    
    
    private Permohonan permohonan;
    
    @Inject
    PermohonanDAO permohonanDAO;
    
    @Inject
    AduanPerbincanganDAO aduanPerbincgnDAO;
    
    @Inject
    KodPeringkatDAO kodPeringkatDAO;
    
    
    AduanPerbincangan aduanPerbincangan;
    
    
    //
    String masaPerbincgn;
        String tempatPerbincgn ;
        String tarikhPerbincgn ;
        String ketPerbincgn ;
        String statusPerbincgn;

    @DefaultHandler
    public Resolution showForm() {
        
        System.out.println("inside show form RekodKeputusanPerbincanganActionBean ");
        return new JSP("pengambilan/aduan_kerosakan/rekodKeputusanPerbincangan.jsp").addParameter("tab", "true");

    }

    public Resolution simpan() {
        
        System.out.println("start simpan ++> "  );
        
        String idPermohonan = "0401ACQ2020000411";
        ///String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        
        
         masaPerbincgn  = (String) getContext().getRequest().getParameter("masa_perbincgn");
         tempatPerbincgn  = (String) getContext().getRequest().getParameter("tempat_perbincgn");
         tarikhPerbincgn  = (String) getContext().getRequest().getParameter("tarikh_perbincgn");
         ketPerbincgn  = (String) getContext().getRequest().getParameter("ket_perbincgn");
         statusPerbincgn = (String) getContext().getRequest().getParameter("status_perbincgn");
         
         
         aduanPerbincangan = new AduanPerbincangan();
        
        
        System.out.println("masaPerbincgn ++> " + masaPerbincgn);
        System.out.println("tempatPerbincgn ++> " + tempatPerbincgn);
        System.out.println("tarikhPerbincgn ++> " + tarikhPerbincgn);
        System.out.println("ketPerbincgn ++> " + ketPerbincgn);
        System.out.println("statusPerbincgn ++> " + statusPerbincgn);
        permohonan = permohonanDAO.findById(idPermohonan);
        
        
        
        aduanPerbincangan.setPermohonan(permohonan);
        
        
        //Date dateTarikhPerbincgn = new Date (tarikhPerbincgn
        Date dateTarikhPerbincgn = new Date();
        try {
             dateTarikhPerbincgn=new SimpleDateFormat("dd/MM/yyyy").parse(tarikhPerbincgn);
        } catch (ParseException ex) {  
            Logger.getLogger(RekodKeputusanPerbincanganActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
               
        aduanPerbincangan.setTarikhPerbincgn(dateTarikhPerbincgn);
        aduanPerbincangan.setMasaPerbincgn(masaPerbincgn);
        aduanPerbincangan.setTempatPerbincgn(tempatPerbincgn);        
        aduanPerbincangan.setKetPerbincgn(ketPerbincgn);
        aduanPerbincangan.setStatusPerbincgn(statusPerbincgn);
        
        
        
        //aduanPerbincangan.setInfoAudit(ia);
        
        
        
        
        
        Permohonan mohon = aduanService.findfromdb(idPermohonan);
        
        aduanPerbincangan.setPermohonan(mohon);
        
        
        //aduanService.saveAduanPerbincangan(aduanPerbincangan);
        
        
        
        
        
        
        
        // iaaudit
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        
        // nota siasatan
        NotaSiasatan nSiasatan = new NotaSiasatan();
        nSiasatan.setInfoAudit(ia);
        nSiasatan.setPermohonan(mohon);
        KodPeringkat kodP = new KodPeringkat();
        // utuk testing dahulu.. tnaya team theta uk lebih pasti
        String REKOD_KPSN_MMK = "RKMMK";
        kodP = kodPeringkatDAO.findById(REKOD_KPSN_MMK);
        nSiasatan.setKodPeringkat(kodP);
        //nSiasatan.setIdNota(idNota);
        
        
        
        //borang per pb
        BorangPerPB bPb = new BorangPerPB();
        
        bPb.setInfoAudit(ia);
        Alamat alaSrt =  new Alamat();
//        alaSrt.setAlamatSurat1(mohon.getPenyerahAlamat1());
//        alaSrt.setAlamatSurat2(mohon.getPenyerahAlamat2());
//        alaSrt.setAlamatSurat3(mohon.getPenyerahAlamat3());
//        alaSrt.setAlamatSurat4(mohon.getPenyerahAlamat4());
//        alaSrt.setPoskodSurat(mohon.getPenyerahPoskod());
        
        bPb.setAlamat(alaSrt);
        bPb.setNama(mohon.getPenyerahNama());
        bPb.setNoPengenalan(mohon.getPenyerahNoPengenalan());
        
        
        // nota Siatan lengkap
        NotaSiasatanLengkap nSiasatanLkp = new NotaSiasatanLengkap();
        nSiasatanLkp.setBorangPerPB(bPb);
        nSiasatanLkp.setNotaSiasatan(nSiasatan);
        
        nSiasatanLkp.setInfoAudit(ia);
        nSiasatanLkp.setDiBicaraOleh(pengguna);
        nSiasatanLkp.setKeteranganicara(ketPerbincgn);
//        nSiasatanLkp.setTarikhBicara(tarikhPerbincgn);
        nSiasatanLkp.setMasaBicara(masaPerbincgn);
        nSiasatanLkp.setFlagBicara(statusPerbincgn);
        
        
        System.out.println("b4  saveNotaSiasatanLengkap ++> "  );
        //aduanService.saveNotaSiasatanLengkap(nSiasatanLkp);
        
        
        //report_p_id_mohon = mohon.getIdPermohonan();
        return new JSP("pengambilan/aduan_kerosakan/rekodKeputusanPerbincangan.jsp").addParameter("tab", "true");

    }
    
    public NotaSiasatan simpanNotaSiasatan(){
        
        NotaSiasatan nSiasatan = new NotaSiasatan();
        
        //nSiasatan.set
                
        
                
        return nSiasatan;
        
    }
    
    
    public void simpanNotaSiasatanLengkap(){
        NotaSiasatanLengkap nSiasatanLkp = new NotaSiasatanLengkap();
        
        //nSiasatanLkp.set
    }
    
    public void simpanBorang_Per_PB(){
        
    }
    
    
    public Resolution maklumatPengadu() {

        //return new JSP("pengambilan/aduan_kerosakan/kemasukan.jsp").addParameter("tab", "true");
        return new JSP("pengambilan/aduan_kerosakan/agihanTugas.jsp").addParameter("tab", "true");

    }
    public Resolution agihTugas() {

        //return new JSP("pengambilan/aduan_kerosakan/kemasukan.jsp").addParameter("tab", "true");
        return new JSP("pengambilan/aduan_kerosakan/agihanTugas.jsp").addParameter("tab", "true");

    }

    public Resolution view() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        Permohonan mohon = aduanService.findfromdb(idPermohonan);
        report_p_id_mohon = mohon.getIdPermohonan();
        return new JSP("pengambilan/aduan_kerosakan/kemasukan.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public String getReport_p_id_mohon() {
        return report_p_id_mohon;
    }

    public void setReport_p_id_mohon(String report_p_id_mohon) {
        this.report_p_id_mohon = report_p_id_mohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public AduanPerbincangan getAduanPerbincangan() {
        return aduanPerbincangan;
    }

    public void setAduanPerbincangan(AduanPerbincangan aduanPerbincangan) {
        this.aduanPerbincangan = aduanPerbincangan;
    }

    public String getMasaPerbincgn() {
        return masaPerbincgn;
    }

    public void setMasaPerbincgn(String masaPerbincgn) {
        this.masaPerbincgn = masaPerbincgn;
    }

    public String getTempatPerbincgn() {
        return tempatPerbincgn;
    }

    public void setTempatPerbincgn(String tempatPerbincgn) {
        this.tempatPerbincgn = tempatPerbincgn;
    }

    public String getTarikhPerbincgn() {
        return tarikhPerbincgn;
    }

    public void setTarikhPerbincgn(String tarikhPerbincgn) {
        this.tarikhPerbincgn = tarikhPerbincgn;
    }

    public String getKetPerbincgn() {
        return ketPerbincgn;
    }

    public void setKetPerbincgn(String ketPerbincgn) {
        this.ketPerbincgn = ketPerbincgn;
    }

    public String getStatusPerbincgn() {
        return statusPerbincgn;
    }

    public void setStatusPerbincgn(String statusPerbincgn) {
        this.statusPerbincgn = statusPerbincgn;
    }
    
    
    
    
    
}// end class
