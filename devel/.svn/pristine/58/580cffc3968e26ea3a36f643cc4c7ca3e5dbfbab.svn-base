/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.Permohonan;
import etanah.model.KodRujukan;
//import etanah.service.common.rujukluarpengambilan;
import etanah.service.PengambilanService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/terima_wartaPenarikanBalik")
public class wartaActionBeanPenarikanBalik extends AbleActionBean {
    
    
    private static Logger logger = Logger.getLogger(wartaActionBeanPenarikanBalik.class);
    @Inject
    PermohonanDAO permohonanDAO;
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private KodBandarPekanMukim kodBandarPekanMukim;
    private PermohonanPengambilan permohonanPengambilan;
     @Inject
    PengambilanService service;
    private String tarikhWarta;
    private KodRujukan kodRujukan;
    private String noWarta;
    private String idPermohonan;
    private String idPengambilan;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

     @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/kemasukan_warta.jsp").addParameter("tab", "true");
    }

     @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
//        nowarta = getContext().getRequest().getParameter("noWarta");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
//        permohonan p= permohonanDAO.findById(idPermohonan);

        permohonanPengambilan = service.findByIdMohon(permohonan.getIdPermohonan());

//         if (StringUtils.isNotBlank(nowarta)) {
//
//            permohonan = permohonanDAO.findById(nowarta);
//            permohonanPengambilan = service.findByIdMohon(nowarta);
//        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanPengambilan = service.findByIdMohon(idPermohonan);
            logger.debug(idPermohonan + "idPermohonan");

             if(permohonanPengambilan != null)
                    {
                    if(permohonanPengambilan.getTarikhWarta() != null)
                    tarikhWarta = sdf.format(permohonanPengambilan.getTarikhWarta()).substring(0, 10);
                    noWarta = permohonanPengambilan.getNoWarta();
                    }
                   
        }

    }


     public Resolution simpan() throws ParseException
     {
         noWarta = getContext().getRequest().getParameter("noWarta");
         Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String nowarta2 = getContext().getRequest().getParameter("permohonanPengambilan.noWarta");
//        permohonanPengambilan = permohonanPengambilanDAO.findById(Long.parseLong(idPermohonan));
//        permohonanPengambilan = permohonanPengambilanDAO.findById(Long.parseLong(idPengambilan));
        permohonan = permohonanDAO.findById(idPermohonan);
         permohonanPengambilan = permohonan.getPengambilan();
        
        

//         if (StringUtils.isNotBlank(nowarta2)) {
////           permohonan = permohonanDAO.findById(nowarta2);
//            permohonanPengambilan = service.findByIdMohon(nowarta2);
//        }
                if (permohonanPengambilan == null)
                {
                    permohonanPengambilan = new PermohonanPengambilan();
//                    permohonanPengambilan = service.findNoWartaByIdPengambilan(Long.parseLong(idPengambilan));
                }
                KodBandarPekanMukim pp = new KodBandarPekanMukim();
                pp.setKod(12);
                KodUOM k = new KodUOM();
                k.setKod("D");
//                cawangan = permohonanPengambilan.getCawangan();
//                if (StringUtils.isNotBlank(tarikhWarta)) {
//                tarikhWarta = getContext().getRequest().getParameter("tarikhWarta");
//                permohonanPengambilan.setTarikhWarta(sdf.parse(tarikhWarta));
//                }
//                permohonanPengambilan.setNoWarta(nowarta2);
                InfoAudit info = peng.getInfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPengambilan.setInfoAudit(info);
                permohonanPengambilan.setPermohonan(permohonan);
                permohonanPengambilan.setCawangan(peng.getKodCawangan());
                permohonanPengambilan.setBandarPekanMukim(pp);
                permohonanPengambilan.setKodUnitLuas(k);
                permohonanPengambilan.setLuasTerlibat(new BigDecimal(0.00));
                permohonanPengambilan.setNoWarta(noWarta);
                permohonanPengambilan.setTarikhWarta(sdf.parse(tarikhWarta));
//                permohonanPengambilanDAO.saveOrUpdate(permohonanPengambilan);
                if (info == null) {
                        info = new InfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new Date());
                        permohonanPengambilan.setInfoAudit(info);
                    } else {
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                    }
                service.simpanWarta(permohonanPengambilan);
                addSimpleMessage("Kemasukan Data Berjaya.");

//            } else {
//                if (StringUtils.isNotBlank(tarikhRujukan)) {
//                    permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
//                }
//                info.setDiKemaskiniOleh(peng);
//                info.setTarikhKemaskini(new java.util.Date());
//                permohonanRujukanLuar.setPermohonan(permohonan);
//                permohonanRujukanLuar.setKodRujukan(kodRujukan);
//                permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
//                permohonanRujukanLuar.setInfoAudit(info);
//                service.update(permohonanRujukanLuar);
//                addSimpleMessage("Kemaskini Data Berjaya.");
//
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            addSimpleError("Kemasukan Data Gagal. Sila hubungi Pentadbir Sistem");
//            return showForm();
//        }
//         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        
//         }
//                }
        return new JSP("pengambilan/kemasukan_warta.jsp").addParameter("tab", "true");
        
    }

     public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public KodRujukan getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodBandarPekanMukim getKodBandarPekanMukim() {
        return kodBandarPekanMukim;
    }

    public void setKodBandarPekanMukim(KodBandarPekanMukim kodBandarPekanMukim) {
        this.kodBandarPekanMukim = kodBandarPekanMukim;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }
     public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public String getIdPengambilan() {
        return idPengambilan;
    }

    public void setIdPengambilan(String idPengambilan) {
        this.idPengambilan = idPengambilan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
}
