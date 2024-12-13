package com.theta.portal.stripes;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.PengawasanService;
import com.theta.portal.service.TableService;
import com.theta.portal.stripes.form.SenaraiTugasan;
import com.theta.portal.stripes.utiliti.ManageAduanForm;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Session;
import org.json.JSONObject;

@UrlBinding("/helpdesk/main")
public class HelpdeskMainActionBean extends BaseActionBean {

    @Inject
    TableService tableService;
    @Inject
    PengawasanService pengawasanService;
    private String action;
    private UserTable pengguna;
    String idReport;
    String tarikhMula;
    String tarikhAkhir;
    private int sl = 0;
    private int ln = 0;
    private int py = 0;
    private int pr = 0;
    private int ac = 0;
    private int ai = 0;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    List<SenaraiTugasan> list = new ArrayList<SenaraiTugasan>();
    List<SenaraiTugasan> listPulangSemula2 = new ArrayList<SenaraiTugasan>();

    @DefaultHandler
    public Resolution welcome() throws ParseException {
        pengguna = getContext().getCurrentUser();
        if (pengguna == null) {
            return new RedirectResolution(LoginActionBean.class);
        }
        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }

//        calculatePermohonan(pengguna);
        Long a = tableService.countSenaraiTugasan(pengguna, idReport, tarikhMula, tarikhAkhir);
        Long b = tableService.countSenaraiSemakSemula(pengguna);
        ac = a != null ? a.intValue() : 0;
        pr = b != null ? b.intValue() : 0;
        list = tableService.senaraiTugasan(pengguna, idReport, tarikhMula, tarikhAkhir,get__pg_start(), get__pg_max_records());
        listPulangSemula2 = tableService.senaraiTugasanPulangSemula(pengguna);
        
        set__pg_total_records(a.intValue());
//        ac = list.size();
        return new JSP("/helpdesk/home.jsp");
    }

    public Resolution cari() throws ParseException {
        pengguna = getContext().getCurrentUser();
        if (pengguna == null) {
            return new RedirectResolution(LoginActionBean.class);
        }
ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }
//        calculatePermohonan(pengguna);
        Long a = tableService.countSenaraiTugasan(pengguna, idReport, tarikhMula, tarikhAkhir);
        ac = a != null ? a.intValue() : 0;
        list = tableService.senaraiTugasan(pengguna, idReport, tarikhMula, tarikhAkhir,get__pg_start(), get__pg_max_records());
//        ac = list.size();
set__pg_total_records(a.intValue());
        return new JSP("/helpdesk/home.jsp");
    }

    public Resolution claimTask() {
        return new RedirectResolution(action);
//        return new JSP("/helpdesk/user?selesaiForm&reportId=1762");
    }
    
    public Resolution view() throws ParseException{
            UserTable pengguna = getContext().getCurrentUser();
        String idReport = getContext().getRequest().getParameter("idReport");
        ManageAduanForm form = pengawasanService.value(idReport, pengguna);
        JSONObject obj = new JSONObject(form);
        return new StreamingResolution("application/json", obj.toString());
    }

    public UserTable getPengguna() {
        return pengguna;
    }

    public void setPengguna(UserTable pengguna) {
        this.pengguna = pengguna;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getLn() {
        return ln;
    }

    public void setLn(int ln) {
        this.ln = ln;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public int getPr() {
        return pr;
    }

    public void setPr(int pr) {
        this.pr = pr;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getAi() {
        return ai;
    }

    public void setAi(int ai) {
        this.ai = ai;
    }

    public List<SenaraiTugasan> getList() {
        return list;
    }

    public void setList(List<SenaraiTugasan> list) {
        this.list = list;
    }

    public String getIdReport() {
        return idReport;
    }

    public void setIdReport(String idReport) {
        this.idReport = idReport;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<SenaraiTugasan> getListPulangSemula2() {
        return listPulangSemula2;
    }

    public void setListPulangSemula2(List<SenaraiTugasan> listPulangSemula2) {
        this.listPulangSemula2 = listPulangSemula2;
    }

}
