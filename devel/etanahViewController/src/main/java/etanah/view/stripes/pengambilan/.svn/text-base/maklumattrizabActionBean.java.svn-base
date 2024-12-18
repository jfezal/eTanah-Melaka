/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodRizabDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.TanahRizabPermohonan;
import etanah.model.Permohonan;
import etanah.service.common.TanahRizabService;
import java.text.ParseException;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.lang.StringUtils;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodRizab;
import etanah.view.etanahActionBeanContext;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/maklumat_trizab")
public class maklumattrizabActionBean extends AbleActionBean {
     @Inject
     PermohonanDAO permohonanDAO;
     @Inject
     TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
     private TanahRizabPermohonan tanahrizabpermohonan;
     @Inject
     TanahRizabService tanahRizabService;
     @Inject
     KodRizabDAO kodRizabDAO;
     private KodBandarPekanMukim bandarPekanMukim;
     private KodDaerah daerah;
     private KodCawangan cawangan;
     private String noLot;
     private String noLitho;
     private String noWarta;
     private String lokasi;
     private KodRizab rizab;
     private Permohonan permohonan;
     private String tarikhWarta;
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    Long idTanahRizabPermohonan;
    private int size = 0;
    private List<TanahRizabPermohonan> tanahRizabList;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/maklumat_trizab.jsp").addParameter("tab", "true");
    }

     public Resolution showForm2() {
        return new JSP("pengambilan/maklumat_trizab.jsp").addParameter("tab", "true");
    }

     public Resolution hakMilikPopup() {
        return new JSP("pengambilan/kemasukan_tanahrizab.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
          String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        //idTanahRizabPermohonan = (String) getContext().getRequest().getParameter("idTanahRizabPermohonan");
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            tanahRizabList = p.getSenaraiTanahRizab();
//            tanahrizabpermohonan=tanahRizabService.findByidPermohonan(idPermohonan);
             if(tanahrizabpermohonan != null)
                    {
                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);

                    }
                    else{
                    tanahrizabpermohonan = new TanahRizabPermohonan();
                    }

        }

    }

    public Resolution editTanahRizab()
    {
        String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan=tanahrizabPermohonanDAO.findById(Long.valueOf(idtanahRizabPermohonan));
        return new JSP("pengambilan/kemaskini_trizab.jsp").addParameter("popup", "true");


    }

    public Resolution edit() 
    {
            InfoAudit ia = new InfoAudit();
            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
            tanahrizabpermohonan=tanahRizabService.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));
            tanahrizabpermohonan = new TanahRizabPermohonan();
        if (tanahrizabpermohonan != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(ia);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
//            tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
//            tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
            tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);

            //suratkuasaService.deleteAll(wakilHakmilik);
}

    return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_trizab.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle()  {
             InfoAudit ia = new InfoAudit();
            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            tanahrizabpermohonan = new TanahRizabPermohonan();
            String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
             tanahrizabpermohonan=tanahRizabService.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));


            if (tanahrizabpermohonan != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(ia);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabService.deleteAll(tanahrizabpermohonan);
            //suratkuasaService.deleteAll(wakilHakmilik);
}
            return new RedirectResolution(maklumattrizabActionBean.class, "locate");

}

    public Resolution refreshpage() {
    rehydrate();
    return new RedirectResolution(maklumattrizabActionBean.class, "locate");
}

 public Resolution searchtrizab() throws ParseException  {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        tanahrizabpermohonan=tanahRizabService.findByidPermohonan(idPermohonan);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (idPermohonan != null) {
            tanahrizabpermohonan = new TanahRizabPermohonan();
                if (StringUtils.isNotBlank(tarikhWarta)) {
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
                }
            tanahrizabpermohonan.setInfoAudit(info);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabList.add(tanahrizabpermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
               addSimpleMessage("Maklumat telah berjaya disimpan");
        }else{
             tanahrizabpermohonan = new TanahRizabPermohonan();
                if (StringUtils.isNotBlank(tarikhWarta)) {
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
                }
            tanahrizabpermohonan.setInfoAudit(info);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabList.add(tanahrizabpermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
               addSimpleMessage("Maklumat telah berjaya disimpan");}


        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_trizab.jsp").addParameter("tab", "true");



 }

  public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }


    public Long getIdTanahRizab() {
        return idTanahRizabPermohonan;
    }

    public void setIdTanahRizab(Long idTanahRizabPermohonan) {
        this.idTanahRizabPermohonan = idTanahRizabPermohonan;
    }
     public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }
    public List<TanahRizabPermohonan> gettanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabPermohonanList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

     public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }
     public TanahRizabPermohonan getTanahrizab() {
        return tanahrizabpermohonan;
    }

    public void setTanahrizab(TanahRizabPermohonan tanahrizabpermohonan) {
        this.tanahrizabpermohonan = tanahrizabpermohonan;
    }
     public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }
    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

   public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }
}
