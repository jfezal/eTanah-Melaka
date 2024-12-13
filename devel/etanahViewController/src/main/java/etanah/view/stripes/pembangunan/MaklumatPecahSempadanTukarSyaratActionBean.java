/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.model.PermohonanPlotPelan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.KodCawangan;
import etanah.model.KodPemilikan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodRizab;
import etanah.service.RegService;
import etanah.service.LaporanTanahService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.model.KodKategoriTanah;
import etanah.model.KodUOM;
import etanah.model.NoPt;


/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/melaka/maklumat_pecahSempadan_tukarSyarat")
public class MaklumatPecahSempadanTukarSyaratActionBean extends AbleActionBean{

    private static final Logger logger = Logger.getLogger(MaklumatPecahSempadanTukarSyaratActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodKegunaanTanahDAO kodKegunaanTanahDAO;
    @Inject
    RegService regService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;

    private List<PermohonanPlotPelan> senaraiPermohonanPlotPelan;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private BigDecimal luas;
    private List<HakmilikPermohonan> hpList;


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("rhydrate start.");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        senaraiPermohonanPlotPelan = new ArrayList<PermohonanPlotPelan>();
        senaraiPermohonanPlotPelan = pembangunanServ.senaraiPermohonanPlotPelanByIdPermohonan(idPermohonan);
        hpList = new ArrayList<HakmilikPermohonan>();
        hpList = permohonan.getSenaraiHakmilik();
        luas = new BigDecimal(0.00);

        logger.info("hakmilik list.");

        for(int j=0; j < hpList.size(); j++){
            HakmilikPermohonan hp = new HakmilikPermohonan();
            hp = hpList.get(j);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            BigDecimal luastemp = hakmilik.getLuas();
            luas = luas.add(luastemp);
        }

//        logger.info("----jumlah---luas-------:"+luas);
        logger.info("rhydrate end.");
    }

    @DefaultHandler
    public Resolution showForm() {
        logger.info("showForm");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/maklumat_pecahSempadan_tukarSyarat.jsp").addParameter("tab", "true");
    }

    public Resolution simpan(){
        logger.info("simpan start.");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        for (int i = 0; i < senaraiPermohonanPlotPelan.size(); i++) {
          String kodKategoriTanah = getContext().getRequest().getParameter("kodKategoriTanah" + i);
          String kodKegunaanTanah = getContext().getRequest().getParameter("kodKegunaanTanah" + i);
          String idHakmilik = getContext().getRequest().getParameter("idHakmilik" + i);

//          logger.info("------kodKategoriTanah--------:"+kodKategoriTanah);
//          logger.info("------kodKegunaanTanah--------:"+kodKegunaanTanah);
//          logger.info("------idHakmilik--------:"+idHakmilik);

           PermohonanPlotPelan mpp=new PermohonanPlotPelan();
           mpp = senaraiPermohonanPlotPelan.get(i);

           if (mpp != null && mpp.getIdPlot()==0) {               
               mpp.setPermohonan(permohonan);
               mpp.setCawangan(permohonan.getCawangan());
               mpp.setPemilikan(kodPemilikanDAO.findById('H'));
               mpp.setNoDari(0);
               mpp.setNoKe(0);
               mpp.setNoPlot(Integer.toString(i+1));
               mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
//               System.out.println("kodUOM: " + hakmilik.getKodUnitLuas().getKod());
               InfoAudit info=new InfoAudit();
               info.setTarikhMasuk(new java.util.Date());
               info.setDimasukOleh(pengguna);
               mpp.setInfoAudit(info);
           }
           if (kodKategoriTanah != null && !kodKategoriTanah.equals("")) {
               KodKategoriTanah kodKategoriTanah1 = kodKategoriTanahDAO.findById(kodKategoriTanah);
               mpp.setKategoriTanah(kodKategoriTanah1);
           } else {
               mpp.setKategoriTanah(null);
           }
           if (kodKegunaanTanah != null && !kodKegunaanTanah.equals("")) {
               KodKegunaanTanah kodKegunaanTanah1 = kodKegunaanTanahDAO.findById(kodKegunaanTanah);
               mpp.setKegunaanTanah(kodKegunaanTanah1);
           } else {
               mpp.setKegunaanTanah(null);
           }
           if (idHakmilik != null && !idHakmilik.equals("")) {
              HakmilikPermohonan hp = pembangunanServ.findHakmilikPermohonanByIdHakmilik(idPermohonan, idHakmilik);
              mpp.setHakmilikPermohonan(hp); 
           } else {
               mpp.setHakmilikPermohonan(null); 
           }

        pembangunanServ.simpanPermohonanPlotPelan(mpp);

        }
        
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/maklumat_pecahSempadan_tukarSyarat.jsp").addParameter("tab", "true");
    }


    public Resolution deleteSingle() {
        logger.info("------deleteSingle------");
        try {
            senaraiPermohonanPlotPelan = new ArrayList<PermohonanPlotPelan>();
            senaraiPermohonanPlotPelan = pembangunanServ.senaraiPermohonanPlotPelanByIdPermohonan(idPermohonan);
            if(senaraiPermohonanPlotPelan.size() > 0){
                int index = senaraiPermohonanPlotPelan.size()-1;                
                PermohonanPlotPelan mpp1=senaraiPermohonanPlotPelan.get(index);
                NoPt noPT = new NoPt();
                noPT =pembangunanServ.findNoPTByIdPlotPelan(mpp1.getIdPlot());
                if(noPT!=null){
                    pembangunanServ.deleteNoPt(noPT);
                }
                pembangunanServ.deletePermohonanPlotPelan(mpp1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(MaklumatPecahSempadanTukarSyaratActionBean.class, "locate");
    }


    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PermohonanPlotPelan> getSenaraiPermohonanPlotPelan() {
        return senaraiPermohonanPlotPelan;
    }

    public void setSenaraiPermohonanPlotPelan(List<PermohonanPlotPelan> senaraiPermohonanPlotPelan) {
        this.senaraiPermohonanPlotPelan = senaraiPermohonanPlotPelan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public List<HakmilikPermohonan> getHpList() {
        return hpList;
    }

    public void setHpList(List<HakmilikPermohonan> hpList) {
        this.hpList = hpList;
    }

}
