/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.utiliti;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.dao.HelpdeskContractorDAO;
import com.theta.portal.dao.HelpdeskReportDAO;
import com.theta.portal.dao.HelpdeskStageDAO;
import com.theta.portal.dao.HelpdeskTechnicalDAO;
import com.theta.portal.dao.RefHelpdeskStageDAO;
import com.theta.portal.manager.UserManager;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskTechnical;
import com.theta.portal.model.RefHelpdeskStage;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.PengawasanService;
import com.theta.portal.service.StageService;
import com.theta.portal.stripes.BaseActionBean;
import com.theta.portal.stripes.form.aduan.AduanForm;
import com.theta.portal.stripes.form.aduan.AgihanUserForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Query;

import org.json.JSONObject;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/helpdesk/utiliti/pengawasan_tugas")
public class PengawasanTugasActionBean extends BaseActionBean {

    @Inject
    PengawasanService pengawasanService;
    @Inject
    StageService stageService;
@Inject
    RefHelpdeskStageDAO refHelpdeskStageDAO;

    List<AduanForm> listAduan;
    List<AduanForm> listAgihan;
    List<AgihanUserForm> listAgihanKontraktor;

    List<AduanForm> listTeknikal;
    List<AduanForm> listKontraktor;

    List<PengawasanForm> listPengawasan;
    String noAduan;
    String status;
    String pejabat;
    String jenismasalah;
    String tarikhMula;
    String tarikhAkhir;
String peringkat;
String techId;
    String idReport;
@Inject
UserManager userManager;
List<AgihanUserForm>  listTechId;
    @DefaultHandler
    public Resolution welcome() {
      
        return new JSP("utiliti/pengawasan_tugas.jsp");
    }
     @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {
      UserTable pengguna = getContext().getCurrentUser();
        listTechId = userManager.listAgihanTugasanUser("" + pengguna.getUserPtdOfficeId());
    }

    public Resolution view() throws ParseException {
        UserTable pengguna = getContext().getCurrentUser();
        String idReport = getContext().getRequest().getParameter("idReport");
        ManageAduanForm form = pengawasanService.value(idReport, pengguna);
        JSONObject obj = new JSONObject(form);
        return new StreamingResolution("application/json", obj.toString());
    }

    public Resolution cari() throws ParseException {
        UserTable pengguna = getContext().getCurrentUser();
        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }
        Long reportby = null;
        if (pengawasanService.checkAutority(pengguna)) {
            reportby = pengguna.getUserId();
        }
        Long a = pengawasanService.countFindRecord(noAduan, status ,peringkat,techId,pejabat, jenismasalah, tarikhMula, tarikhAkhir, reportby);

        listPengawasan = pengawasanService.findRecord(noAduan, status,peringkat,techId, pejabat, jenismasalah, tarikhMula, tarikhAkhir, reportby, get__pg_start(), get__pg_max_records());
        set__pg_total_records(a.intValue());

        return new JSP("utiliti/pengawasan_tugas.jsp");
    }

    public Resolution tutupAduan() {
        UserTable pengguna = getContext().getCurrentUser();
        String idReport = getContext().getRequest().getParameter("reportId");
                RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("US");

        stageService.close(kodStage,Long.parseLong(idReport), pengguna);
        return new JSP("utiliti/pengawasan_tugas.jsp");

    }

    public Resolution agihSemula() {
        UserTable pengguna = getContext().getCurrentUser();
        String idReport = getContext().getRequest().getParameter("reportId");
        stageService.agihSemula(Long.parseLong(idReport), pengguna);
        return new JSP("utiliti/pengawasan_tugas.jsp");
    }

    public String formatStringSDF(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(date);
    }

    public List<PengawasanForm> getListPengawasan() {
        return listPengawasan;
    }

    public void setListPengawasan(List<PengawasanForm> listPengawasan) {
        this.listPengawasan = listPengawasan;
    }

    public String getNoAduan() {
        return noAduan;
    }

    public void setNoAduan(String noAduan) {
        this.noAduan = noAduan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPejabat() {
        return pejabat;
    }

    public void setPejabat(String pejabat) {
        this.pejabat = pejabat;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhAkhir() {
        return tarikhAkhir;
    }

    public void setTarikhAkhir(String tarikhAkhir) {
        this.tarikhAkhir = tarikhAkhir;
    }

    public String getJenismasalah() {
        return jenismasalah;
    }

    public void setJenismasalah(String jenismasalah) {
        this.jenismasalah = jenismasalah;
    }

    public String getIdReport() {
        return idReport;
    }

    public void setIdReport(String idReport) {
        this.idReport = idReport;
    }

    public PengawasanService getPengawasanService() {
        return pengawasanService;
    }

    public void setPengawasanService(PengawasanService pengawasanService) {
        this.pengawasanService = pengawasanService;
    }

    public List<AduanForm> getListAduan() {
        return listAduan;
    }

    public void setListAduan(List<AduanForm> listAduan) {
        this.listAduan = listAduan;
    }

    public List<AduanForm> getListAgihan() {
        return listAgihan;
    }

    public void setListAgihan(List<AduanForm> listAgihan) {
        this.listAgihan = listAgihan;
    }

    public List<AgihanUserForm> getListAgihanKontraktor() {
        return listAgihanKontraktor;
    }

    public void setListAgihanKontraktor(List<AgihanUserForm> listAgihanKontraktor) {
        this.listAgihanKontraktor = listAgihanKontraktor;
    }

    public List<AduanForm> getListTeknikal() {
        return listTeknikal;
    }

    public void setListTeknikal(List<AduanForm> listTeknikal) {
        this.listTeknikal = listTeknikal;
    }

    public List<AduanForm> getListKontraktor() {
        return listKontraktor;
    }

    public void setListKontraktor(List<AduanForm> listKontraktor) {
        this.listKontraktor = listKontraktor;
    }

    public String getPeringkat() {
        return peringkat;
    }

    public void setPeringkat(String peringkat) {
        this.peringkat = peringkat;
    }

    public String getTechId() {
        return techId;
    }

    public void setTechId(String techId) {
        this.techId = techId;
    }

    public List<AgihanUserForm> getListTechId() {
        return listTechId;
    }

    public void setListTechId(List<AgihanUserForm> listTechId) {
        this.listTechId = listTechId;
    }
    

}
