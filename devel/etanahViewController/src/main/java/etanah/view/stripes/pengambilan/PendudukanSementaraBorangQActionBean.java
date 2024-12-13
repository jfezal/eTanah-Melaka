/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BorangQDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.model.KodCawangan;
import etanah.model.BorangQ;
import etanah.service.common.PendudukanSementaraService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UrlBinding("/pengambilan/PendudukanSementaraBorangQ")
public class PendudukanSementaraBorangQActionBean extends AbleActionBean
{
    private static Logger logger = Logger.getLogger(PendudukanSementaraBorangQActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PendudukanSementaraService pendudukanSementaraService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    BorangQDAO  borangQDAO;

    private KodCawangan cawangan;
    private Permohonan permohonan;
    private BorangQ borangQ;
    private BigDecimal pampasan = new BigDecimal(0.00);
    private Integer tempoh;
    private Date tarikhMula;
    private Date tkhTamat;
    private String tarikhHadir;
    private String maksud;
//    private char waktuHadir;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private String jam;
    private String minit;
    private String pagiPetang;


    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
     public Resolution showForm() {
        getContext().getRequest().setAttribute("tempoh", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMula", Boolean.TRUE);
        getContext().getRequest().setAttribute("tkhTamat", Boolean.TRUE);
        getContext().getRequest().setAttribute("maksud", Boolean.TRUE);
        getContext().getRequest().setAttribute("pampasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhHadir", Boolean.TRUE);
//        getContext().getRequest().setAttribute("waktuHadir", Boolean.TRUE);
        return new JSP("pengambilan/sediaBorangQ.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        return new JSP("pengambilan/tetapkanTarikhRundingan.jsp").addParameter("tab", "true");
    }

//    public Resolution showForm2() {
//        return new JSP("pengambilan/sediaSuratPenolakan.jsp").addParameter("tab", "true");
//    }

    public Resolution showForm6() {
        return new JSP("pengambilan/rekod_maklumat_penerimaan_bayaran.jsp").addParameter("popup", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
     public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (idPermohonan != null) {
            logger.debug("idPermohonan :" + idPermohonan);
            borangQ = pendudukanSementaraService.findByIdPermohonan4BorangQ(idPermohonan);
            if(borangQ != null) {
                tempoh = borangQ.getTempoh();
                tarikhMula= borangQ.getTarikhMulaMendudukiTanah();
                tkhTamat= borangQ.getTarikhTamatPendudukan();
                maksud = borangQ.getMaksud();
                pampasan = borangQ.getTawaranPampasan();

                if(borangQ.getTarikhHadir() != null) {
                    String tarikhHadirObj = dateFormat.format(borangQ.getTarikhHadir());
                    tarikhHadir = tarikhHadirObj.substring(0, 10);
                    jam = tarikhHadirObj.substring(11, 13);
                    minit = tarikhHadirObj.substring(14, 16);
                    String ampm = tarikhHadirObj.substring(20, 22);
                    if(ampm.equalsIgnoreCase("AM")){
                        pagiPetang = "AM";
                    }else if(ampm.equalsIgnoreCase("PM")){
                        pagiPetang = "PM";
                    }
                }


            }
            
        }
    }

    public Resolution simpan() throws ParseException {
        logger.debug("start simpan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();

          System.out.println("----------tempoh------"+tempoh);
          System.out.println("----------tarikhMula------"+tarikhMula);
          System.out.println("----------maksud------"+maksud);
          System.out.println("----------pampasan------"+pampasan);
          System.out.println("----------tarikhHadir------"+tarikhHadir+" "+jam+":"+minit+":"+pagiPetang);

        if (borangQ == null) {
            borangQ = new BorangQ();
        }
          boolean flag=true;

         try{
             System.out.println("--tempoh-try----"+tempoh);
        if (tempoh == null) {
            System.out.println("--tempoh-try--if--"+tempoh);
              flag = false;
            addSimpleError("Sila Masukkan Tempoh.");

        }if(tarikhMula == null) {
            flag=false;
            addSimpleError("Sila Masukkan Tarikh Mula Menduduki Tanah .");
        }if (maksud == null) {
            System.out.println("--tempoh-try--if--"+maksud);
              flag = false;
            addSimpleError("Sila Masukkan Maksud.");

        }if(pampasan == null){
             System.out.println("--pampasan-try--if2--"+pampasan);
              flag = false;
            addSimpleError("Sila Masukkan Tawaran Pampasan.");
        
        } if (tarikhHadir == null) {
              flag = false;
            addSimpleError("Sila Masukkan Tarikh Hadir.");

        }
         }catch(NumberFormatException e){
             System.out.println("--catch-----"+pampasan);
             pampasan = BigDecimal.ZERO;
             flag = false;
             addSimpleError("Sila Masukkan Nilai Tawaran Pampasan.");
         }

         if(flag){
            borangQ.setPermohonan(permohonan);
            borangQ.setTempoh(tempoh);
            borangQ.setMaksud(maksud);
            borangQ.setTarikhMulaMendudukiTanah(tarikhMula);
            borangQ.setTarikhTamatPendudukan(tkhTamat);
            borangQ.setTawaranPampasan(pampasan);
            tarikhHadir = getContext().getRequest().getParameter("tarikhHadir");
            jam = getContext().getRequest().getParameter("jam");
            minit = getContext().getRequest().getParameter("minit");
            pagiPetang = getContext().getRequest().getParameter("pagiPetang");
        if(tarikhHadir.length() > 0 && jam.length() > 0 && minit.length() > 0 && pagiPetang.length()>0){
            String ampm = "";
            if(pagiPetang.equalsIgnoreCase("AM")){
                ampm = "AM";
            }else if(pagiPetang.equalsIgnoreCase("PM")){
                ampm = "PM";
            }
            String strMasa = tarikhHadir + " " + jam + ":" + minit + ":00 " + ampm;
            Date tarikhSidang = dateFormat.parse(strMasa);
            borangQ.setTarikhHadir(tarikhSidang);
            System.out.println("----------tarikhHadir------"+tarikhSidang);
            System.out.println(tarikhHadir+" "+jam+ ":" + minit + ":00 " + ampm);
        }
//            borangQ.setWaktuHadir('0');
            InfoAudit ia = borangQ.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            borangQ.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            borangQ.setInfoAudit(ia);
        }
        pendudukanSementaraService.saveBorangQ(borangQ);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("tempoh", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMula", Boolean.TRUE);
        getContext().getRequest().setAttribute("maksud", Boolean.TRUE);
        getContext().getRequest().setAttribute("pampasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhHadir", Boolean.TRUE);
//        getContext().getRequest().setAttribute("waktuHadir", Boolean.TRUE);
        return new JSP("pengambilan/sediaBorangQ.jsp").addParameter("popup", "true");
    }

    public BorangQ getBorangQ() {
        return borangQ;
    }

    public void setBorangQ(BorangQ borangQ) {
        this.borangQ = borangQ;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getMaksud() {
        return maksud;
    }

    public void setMaksud(String maksud) {
        this.maksud = maksud;
    }

    public BigDecimal getPampasan() {
        return pampasan;
    }

    public void setPampasan(BigDecimal pampasan) {
        this.pampasan = pampasan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhHadir() {
        return tarikhHadir;
    }

    public void setTarikhHadir(String tarikhHadir) {
        this.tarikhHadir = tarikhHadir;
    }

    public Date getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(Date tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public Integer getTempoh() {
        return tempoh;
    }

    public void setTempoh(Integer tempoh) {
        this.tempoh = tempoh;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getPagiPetang() {
        return pagiPetang;
    }

    public void setPagiPetang(String pagiPetang) {
        this.pagiPetang = pagiPetang;
    }

    public PendudukanSementaraService getPendudukanSementaraService() {
        return pendudukanSementaraService;
    }

    public void setPendudukanSementaraService(PendudukanSementaraService pendudukanSementaraService) {
        this.pendudukanSementaraService = pendudukanSementaraService;
    }

    public Date getTkhTamat() {
        return tkhTamat;
    }

    public void setTkhTamat(Date tkhTamat) {
        this.tkhTamat = tkhTamat;
    }


}
