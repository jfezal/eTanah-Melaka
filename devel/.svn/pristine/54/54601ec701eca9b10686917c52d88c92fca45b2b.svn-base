/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.dashboard.tasklist;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.InfoMmknDAO;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoMmkn;
import etanah.model.Pengguna;
import etanah.service.dashboard.MmknListService;
import etanah.view.dashboard.tasklist.form.ListForm;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author nurulwahida
 */
@UrlBinding("/mmkn/tasklist")
public class EmmknTaskListActionBean extends AbleActionBean {

    List<ListForm> dashboard;
    Pengguna pengguna;
    @Inject
    MmknListService mmknListService;
    @Inject
    InfoMmknDAO infoMmknDAO;
    InfoMmknDAO infoMmknDAO1 = new InfoMmknDAO();

    String idMohon;
    String results = "0";

    public Resolution showForm() {

        //  dashboard=emmkntask ;
        return new ForwardResolution("/WEB-INF/jsp/mmkn/tasklist.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    }

    @DefaultHandler
    public Resolution showForm1() {
        dashboard = mmknListService.findListTugasan();

        return new ForwardResolution("/WEB-INF/jsp/mmkn/tasklist.jsp");
    }

    public Resolution paparDetailTugasan() {
        String idInfoMmk = context.getRequest().getParameter("idInfoMmk");

        InfoMmkn mmn = infoMmknDAO.findById(Long.parseLong(idInfoMmk));
        idMohon = mmn.getPermohonan().getIdPermohonan();

        return new ForwardResolution("/WEB-INF/jsp/mmkn/tasklist.jsp").addParameter("popup", true);
    }

    public List<ListForm> getDashboard() {
        return dashboard;
    }

    public void setDashboard(List<ListForm> dashboard) {
        this.dashboard = dashboard;
    }

    public Resolution cari() {
        //String idMohon = context.getRequest().getParameter("idMohon");

        List<InfoMmkn> mmn = mmknListService.findbyIdMohon(idMohon);
        dashboard = new ArrayList<ListForm>();

        if (mmn.size() > 0) {
            for (InfoMmkn infoMmkn : mmn) {
                ListForm form = new ListForm();

                form.setIdMohon(infoMmkn.getPermohonan().getIdPermohonan());
                form.setKeteranganMmkn(infoMmkn.getIdInfoMmkn()+"");
                form.setKodPeringkat(infoMmkn.getKodPeringkat());
                form.setNoFailMmkn(infoMmkn.getNoFailMmkn());
                form.setKpsn(infoMmkn.getKodKpsn());

                dashboard.add(form);
            }
        }

        return new JSP("mmkn/tasklist.jsp");
        // return new ForwardResolution("/WEB-INF/jsp/mmkn/tasklist.jsp").addParameter("popup", true);
    }

    public Resolution countLulus() {

        //String results = "0";        
        results = mmknListService.countKeputusanLulus().toString();
        return new StreamingResolution("text/plain", results);
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
    
    

}
