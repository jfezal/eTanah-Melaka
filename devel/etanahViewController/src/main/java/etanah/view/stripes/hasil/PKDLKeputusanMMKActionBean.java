/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

/**
 *
 * @author abu.mansur
 */

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

@UrlBinding("/hasil/mesyuaratMMK")
public class PKDLKeputusanMMKActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PKDLKeputusanMMKActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    
    private boolean flag = false;

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    RemisyenManager manager;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String bilMesyuarat;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("hasil/pkdl_keputusan_mmk.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm1() {
        flag = true;
        return new JSP("hasil/pkdl_keputusan_mmk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate:start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<PermohonanRujukanLuar> listRujLuar;

            listRujLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);

            if (!(listRujLuar.isEmpty())) {
                permohonanRujukanLuar = listRujLuar.get(0);

                if(permohonanRujukanLuar.getTarikhSidang() != null)
                    tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang());
//                    keputusan = permohonan.getKeputusan().getKod();
                if(permohonanRujukanLuar.getNoSidang() != null)
                    bilMesyuarat = permohonanRujukanLuar.getNoSidang();
            }
        }
        LOG.info("rehydrate:finish");
    }

    public Resolution simpanMesyuarat() {
        LOG.info("simpanMesyuarat:start");
        String result = null;
        KodRujukan kr = new KodRujukan();
        kr.setKod("FL");//FIXME :Fail
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan.setKeputusanOleh(peng);
        LOG.info("tarikhMesyuarat :"+tarikhMesyuarat);
        if(permohonanRujukanLuar == null){
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            LOG.debug("new PermohonanRujukanLuar");
        }
        try {
            permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhMesyuarat));
        } catch (ParseException ex) {
            LOG.error("simpanMesyuarat ex :"+ex);
        }
        if(bilMesyuarat != null)
            permohonanRujukanLuar.setNoSidang(bilMesyuarat);
        permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setKodRujukan(kr);
        result = manager.saveOrUpdate(permohonan, permohonanRujukanLuar, peng);
        if("success".equals(result))
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        else
            addSimpleError("Maklumat TIDAK berjaya disimpan. Sila rujuk Pentadbir Sistem");

        LOG.info("simpanMesyuarat:finish");
        return new JSP("hasil/pkdl_keputusan_mmk.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getBilMesyuarat() {
        return bilMesyuarat;
    }

    public void setBilMesyuarat(String bilMesyuarat) {
        this.bilMesyuarat = bilMesyuarat;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}