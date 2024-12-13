/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.KodCawangan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodRujukan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import etanah.service.common.HakmilikService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.PengambilanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.InfoAudit;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/maklumat_pendudukanSementara")
public class MaklumatPendudukanSementara extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    private Permohonan permohonan;
    private PermohonanPengambilan permohonanPengambilan;
    @Inject
    PermohonanRujukanLuarService service;
    private String tarikhRujukan;
    private String cawangan;
    private KodCawangan kodCawangan;
    private KodRujukan kodRujukan;
    private String LuasTerlibat;

    public String getLuasTerlibat() {
        return LuasTerlibat;
    }

    public void setLuasTerlibat(String LuasTerlibat) {
        this.LuasTerlibat = LuasTerlibat;
    }
    

   

    private KodBandarPekanMukim bandarPekanMukim;
    private String sebab;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private int kod=45;

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/maklumat_PendudukanSementara.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/maklumat_PendudukanSementara.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonanPengambilan = service.findByidP(idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanRujukanLuar = service.findByidPermohonan(idPermohonan);
                    if(permohonanRujukanLuar != null)
                    {
                    tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(0, 10);
                    
                    }
                    else{
                    permohonanRujukanLuar = new PermohonanRujukanLuar();
                    }

        }

    }

    public Resolution savePengambilanInfo() throws ParseException {
          String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
          String noRujukan = getContext().getRequest().getParameter("permohonanRujukanLuar.noRujukan");
          Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
          InfoAudit info = peng.getInfoAudit();
          permohonanRujukanLuar = service.findByidPermohonan(idPermohonan);
          if(permohonanPengambilan == null){
              permohonanPengambilan = new PermohonanPengambilan();
          }

            if (permohonanRujukanLuar == null) {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                if (StringUtils.isNotBlank(tarikhRujukan)) {
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
                    tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(0, 10);
                }

                //kt etml not null kuom
                KodUOM kuom = new KodUOM();
                kuom.setKod("M");

                kodRujukan=new KodRujukan();
                kodRujukan.setKod("NF");
                bandarPekanMukim=new KodBandarPekanMukim();
                bandarPekanMukim.setKod(45);
                //kt etml not null kuom
                permohonanPengambilan.setKodUnitLuas(kuom);
                permohonanPengambilan.setBandarPekanMukim(bandarPekanMukim);
                permohonanPengambilan.setCawangan(peng.getKodCawangan());
                permohonanPengambilan.setPermohonan(permohonan);
                permohonanPengambilan.setLuasTerlibat(new BigDecimal("123"));
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setKodRujukan(kodRujukan);
                permohonanRujukanLuar.setNoRujukan(noRujukan);
                permohonanPengambilan.setInfoAudit(info);
                permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                permohonanRujukanLuar.setInfoAudit(info);
                service.simpanrujluar(permohonan);
                service.simpanAmbil(permohonanPengambilan);
                service.save(permohonanRujukanLuar);
                addSimpleMessage("Kemasukan Data Berjaya.");
            } else {
                if (StringUtils.isNotBlank(tarikhRujukan)) {
                    permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
                }
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                KodUOM kuom = new KodUOM();
                kuom.setKod("M");
                kodRujukan=new KodRujukan();
                kodRujukan.setKod("NF");
                bandarPekanMukim=new KodBandarPekanMukim();
                bandarPekanMukim.setKod(45);
                //kt etml not null kuom
                permohonanPengambilan.setKodUnitLuas(kuom);
                permohonanPengambilan.setBandarPekanMukim(bandarPekanMukim);
                permohonanPengambilan.setPermohonan(permohonan);
                permohonanPengambilan.setCawangan(peng.getKodCawangan());
                permohonanPengambilan.setLuasTerlibat(new BigDecimal("123"));
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setKodRujukan(kodRujukan);
                permohonanRujukanLuar.setNoRujukan(noRujukan);
                permohonanPengambilan.setInfoAudit(info);
                permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                permohonanRujukanLuar.setInfoAudit(info);
                service.simpanrujluar(permohonan);
                service.simpanAmbil(permohonanPengambilan);
                service.update(permohonanRujukanLuar);
                addSimpleMessage("Kemaskini Data Berjaya.");

            }
          getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/maklumat_PendudukanSementara.jsp").addParameter("tab", "true");
    }

    public String gettarikhRujukan() {
        return tarikhRujukan;
    }

    public void settarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public PermohonanPengambilan getpermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }
     public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }
     public KodRujukan getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }
}
