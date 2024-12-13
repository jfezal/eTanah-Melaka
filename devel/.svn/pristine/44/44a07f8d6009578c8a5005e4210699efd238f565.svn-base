/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
//import etanah.service.PelupusanService;
import etanah.dao.KodUOMDAO;
import etanah.dao.LupusPermitDAO;
import etanah.dao.PermohonanDAO;
import etanah.view.etanahActionBeanContext;
//import net.sourceforge.stripes.action.DefaultHandler;
//import net.sourceforge.stripes.action.Resolution;
//import net.sourceforge.stripes.action.UrlBinding;
//import etanah.dao.PemohonDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;


//import java.util.List;
//import net.sourceforge.stripes.action.Before;
import etanah.model.KodUOM;
import net.sourceforge.stripes.action.DefaultHandler;
//import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
//import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
//import net.sourceforge.stripes.controller.LifecycleStage;
//import org.apache.log4j.Logger;

//import etanah.model.KodNegeri;
import etanah.model.LupusPermit;
import etanah.model.Pemohon;
//import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
//import etanah.service.common.CawanganService;
//import etanah.view.ListUtil;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
import etanah.service.common.PermohonanService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/pelupusan/borang4c")
public class Borang4CActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    LupusPermitDAO lupusPermitDAO;
    @Inject
    KodUOMDAO kodUOMDAO;

    private String bayaran1;
    private int noResit;
    private String tarikh;
    private String tarikhMula;
    private String tarikhTamat;
    private String jenisBahan;
    private int kuantitiMaks;
    private String unitIsipadu;
    private String syaratKuatkuasa;
    private String syaratSahlaku;
    private String peruntukanTambahan;
    private int bayaran;
    private Permohonan permohonan;
    private String idPermohonan;
    private String id;
    private KodCawangan cawangan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pengguna pengguna;


    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/Borang_4C.jsp").addParameter("tab", "true");
    }

     @Before(stages = {LifecycleStage.BindingAndValidation})
        public void rehydrate() {
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanService.findById(idPermohonan);
            lupusPermit =pelupusanService.getLupusPermitByIdPermohonan(idPermohonan);
        }

    public Resolution simpan4c() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        String idPermit  = (String) getContext().getRequest().getParameter("lupusPermit.id");
        InfoAudit infoAudit = new InfoAudit();
        LupusPermit permitTemp = new LupusPermit();

        if((idPermit!=null)&&(!idPermit.equals(""))){
            permitTemp= lupusPermitDAO.findById(Long.parseLong(idPermit));
            infoAudit = permitTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }else{
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }



          permitTemp.setBayaran(lupusPermit.getBayaran());
          permitTemp.setTambahanBayaran1(lupusPermit.getTambahanBayaran1());
          permitTemp.setNoResit(lupusPermit.getNoResit());
          permitTemp.setKuantitiMaksimum(lupusPermit.getKuantitiMaksimum());
          permitTemp.setTarikhKeluar(lupusPermit.getTarikhKeluar());
          permitTemp.setTarikhMula(lupusPermit.getTarikhMula());
          permitTemp.setTarikhTamat(lupusPermit.getTarikhTamat());
          System.out.println("---------- unitIsipadu KOD-----------"+unitIsipadu);
          KodUOM kodUOM1 = kodUOMDAO.findById(unitIsipadu);
          System.out.println("---------- kodUOM1 -----------"+kodUOM1);
          permitTemp.setUnitIsipadu(kodUOM1);
          permitTemp.setTempohPermit(lupusPermit.getTempohPermit());
          permitTemp.setPeruntukanTambahan(lupusPermit.getPeruntukanTambahan());



          permitTemp.setInfoAudit(infoAudit);
          permitTemp.setPermohonan(permohonan);
          permitTemp.setNoSiri("Siri");
          permitTemp.setNoPermit("Sek");
          permitTemp.setJenisPermit('S');
          permitTemp.setAktif('Y');
          pelupusanService.saveOrUpdate(permitTemp);


      return new JSP("pelupusan/Borang_4C.jsp").addParameter("tab", "true");
  }


// public Resolution simpan4c() throws ParseException {
//     idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//     Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//     permohonan = permohonanDAO.findById(idPermohonan);
//     System.out.print("+++++++++permohonan++++++++++"+permohonan);
//
//     lupusPermit = new LupusPermit();
//     lupusPermit.setPermohonan(permohonan);
//     cawangan = permohonan.getCawangan();
//     InfoAudit info = new InfoAudit();
//     info.setDimasukOleh(pguna);
//     info.setTarikhMasuk(new java.util.Date());
//     lupusPermit.setInfoAudit(info);
//     lupusPermit.setId(2);
//     lupusPermit.setNoSiri("23");
//     lupusPermit.setNoPermit("234");
//     lupusPermit.setJenisPermit('s');
//     lupusPermit.setNoResit(noResit);
//     pelupusanService.saveSimpan4c(lupusPermit);
//     return new JSP("pelupusan/Borang_4C.jsp").addParameter("tab", "true");
// }

    public int getBayaran() {
        return bayaran;
    }

    public void setBayaran(int bayaran) {
        this.bayaran = bayaran;
    }



    public String getJenisBahan() {
        return jenisBahan;
    }

    public void setJenisBahan(String jenisBahan) {
        this.jenisBahan = jenisBahan;
    }

    public int getKuantitiMaks() {
        return kuantitiMaks;
    }

    public void setKuantitiMaks(int kuantitiMaks) {
        this.kuantitiMaks = kuantitiMaks;
    }



    public String getPeruntukanTambahan() {
        return peruntukanTambahan;
    }

    public void setPeruntukanTambahan(String peruntukanTambahan) {
        this.peruntukanTambahan = peruntukanTambahan;
    }

    public String getSyaratKuatkuasa() {
        return syaratKuatkuasa;
    }

    public void setSyaratKuatkuasa(String syaratKuatkuasa) {
        this.syaratKuatkuasa = syaratKuatkuasa;
    }

    public String getSyaratSahlaku() {
        return syaratSahlaku;
    }

    public void setSyaratSahlaku(String syaratSahlaku) {
        this.syaratSahlaku = syaratSahlaku;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

   public String getBayaran1() {
        return bayaran1;
    }

    public void setBayaran1(String bayaran1) {
        this.bayaran1 = bayaran1;
    }





    public LupusPermit getLupusPermit() {
        return lupusPermit;
    }

    public void setLupusPermit(LupusPermit lupusPermit) {
        this.lupusPermit = lupusPermit;
    }
    private LupusPermit lupusPermit;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }




    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    /**
     * @return the noResit
     */
    public int getNoResit() {
        return noResit;
    }

    /**
     * @param noResit the noResit to set
     */
    public void setNoResit(int noResit) {
        this.noResit = noResit;
    }

    /**
     * @return the unitIsipadu
     */
    public String getUnitIsipadu() {
        return unitIsipadu;
    }

    /**
     * @param unitIsipadu the unitIsipadu to set
     */
    public void setUnitIsipadu(String unitIsipadu) {
        this.unitIsipadu = unitIsipadu;
    }

//      public Resolution seterusnya() {
//        return new JSP("pelupusan/perihalTanahBrg4C.jsp").addParameter("tab", "true");
//    }



}
