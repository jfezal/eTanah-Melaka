/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.NoPtDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodRujukan;
import etanah.model.KodUOM;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/pelupusan/laporanNilaian")
public class LaporanNilaianActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(LaporanNilaianActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    NoPtDAO noPtDAO;
    @Inject
    PelupusanService pelupusanService;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private NoPt noPt;
    private BigDecimal nilai;
    private String tarikhRujukan;
    private Date trhRujukan;
    private String sebab;
    private boolean viewOnly;
    private KodBandarPekanMukim kodBPM;
    private KodUOM kodUL;
    String kodgunatanah;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler()
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanRujukanLuar = pelupusanService.findByIdMohon2(idPermohonan, "Nilaian");
        if(permohonanRujukanLuar != null)
        {
            nilai = permohonanRujukanLuar.getNilai();
            tarikhRujukan = dateFormat.format(permohonanRujukanLuar.getTarikhRujukan());
        }
        viewOnly = Boolean.FALSE;
        return new JSP("pelupusan/laporanNilaian.jsp").addParameter("tab", "true");
    }
    public Resolution viewForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanRujukanLuar = pelupusanService.findByIdMohon2(idPermohonan, "Nilaian");
        if(permohonanRujukanLuar != null)
        {
            nilai = permohonanRujukanLuar.getNilai();
            tarikhRujukan = dateFormat.format(permohonanRujukanLuar.getTarikhRujukan());
        }
        viewOnly = Boolean.TRUE;
        return new JSP("pelupusan/laporanNilaian.jsp").addParameter("tab", "true");
    }
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
       
       
        //one idPermohonan to one idHakmilik
        if (idPermohonan != null) {
            System.out.println("idPermohonan:-----------" + idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);

            
            if (permohonan.getKodUrusan().getKod().equals("PHLP") || permohonan.getKodUrusan().getKod().equals("PHLA")){
                /*
                 * TEMPORARY SINCE PHLP ONLY CATER 1 HM DATE for PAT - 30/11/2011 
                 */
                List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
                hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                if(hmList.size()>0){
                    hakmilikPermohonan = hmList.get(0);
                }
            }
            else
                hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            if(!permohonan.getKodUrusan().getKod().equals("PHLP")){
                noPt = pelupusanService.findNoPtByIdHakmilikPermohonan(hakmilikPermohonan.getId());
                System.out.println("noPt:-----------:" + noPt);
            }
            
            if (!pelupusanService.findByIdMohon1(idPermohonan, "Nilaian").isEmpty()) {
                permohonanRujukanLuar = pelupusanService.findByIdMohon2(idPermohonan, "Nilaian");

            }

            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
             kodgunatanah = hakmilikPermohonan.getKodKegunaanTanah().getKod();
            }
                    
            if (hakmilikPermohonan.getKodKegunaanTanah() != null) {
                kodgunatanah = kodgunatanah + " " + "(" + hakmilikPermohonan.getKodKegunaanTanah().getNama() + ")";
            }

        }
    }


    public Resolution simpan3() throws ParseException {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = peng.getKodCawangan();
        InfoAudit ia = new InfoAudit();

        if (pelupusanService.findByIdMohon1(idPermohonan, "Nilaian").isEmpty()) {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            permohonanRujukanLuar.setPermohonan(permohonan);

//            kodUL = new KodUOM();
//            String kod4 = getContext().getRequest().getParameter("kodUnitLuas.kod");
//
//            //kodUL.setKod(kod4);
//            kodUL = kodUOMDAO.findById(kod4);
//            permohonanRujukanLuar.setKodUnitLuas(kodUL);

            System.out.println("baru...");
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);

            KodRujukan kodRuj = new KodRujukan();
            kodRuj.setKod("FL");
            permohonanRujukanLuar.setKodRujukan(kodRuj);
            permohonanRujukanLuar.setNamaSidang("Nilaian");
            permohonanRujukanLuar.setNilai(nilai);
            permohonanRujukanLuar.setCawangan(caw);
            trhRujukan = dateFormat.parse(tarikhRujukan);
            permohonanRujukanLuar.setTarikhRujukan(trhRujukan);
            pelupusanService.simpanRujukLuar(permohonanRujukanLuar);

        } else {
            permohonanRujukanLuar = pelupusanService.findByIdMohon2(idPermohonan, "Nilaian");
            ia = permohonanRujukanLuar.getInfoAudit();

            System.out.println("lame...");
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            if (permohonanRujukanLuar.getNamaSidang().equalsIgnoreCase("Nilaian")) {
//                kodUL = new KodUOM();
//                String kod4 = getContext().getRequest().getParameter("kodUnitLuas.kod");
//
//                kodUL.setKod(kod4);
//                permohonanRujukanLuar.setKodUnitLuas(kodUL);
                permohonanRujukanLuar.setInfoAudit(ia);
                permohonanRujukanLuar.setNilai(nilai);
                trhRujukan = dateFormat.parse(tarikhRujukan);
                permohonanRujukanLuar.setTarikhRujukan(trhRujukan);
                pelupusanService.simpanRujukLuar(permohonanRujukanLuar);
            }
        }

//        ia = new InfoAudit();
//        ia.setDimasukOleh(peng);
//        ia.setTarikhMasuk(new Date());
//        noPt.setInfoAudit(ia);
//        noPt.setHakmilikPermohonan(hakmilikPermohonan);
//        pelupusanService.simpanNoPt(noPt);

        //saving in No_Pt

        
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/laporanNilaian.jsp").addParameter("tab", "true");

    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public KodUOM getKodUL() {
        return kodUL;
    }

    public void setKodUL(KodUOM kodUL) {
        this.kodUL = kodUL;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public KodBandarPekanMukim getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(KodBandarPekanMukim kodBPM) {
        this.kodBPM = kodBPM;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public NoPt getNoPt() {
        return noPt;
    }

    public void setNoPt(NoPt noPt) {
        this.noPt = noPt;
    }

    public String getKodgunatanah() {
        return kodgunatanah;
    }

    public void setKodgunatanah(String kodgunatanah) {
        this.kodgunatanah = kodgunatanah;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    
}
