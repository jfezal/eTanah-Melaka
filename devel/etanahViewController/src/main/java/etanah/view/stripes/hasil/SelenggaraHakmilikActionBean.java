/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.*;
import java.util.List;
import etanah.service.HakmilikService;
import etanah.service.common.PelarasanCukaiService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abu.mansur
 */
@Wizard(startEvents = {"selectTransaction", "penyukatanBPM"})
@UrlBinding("/hasil/penyelenggaraan_hakmilik")
public class SelenggaraHakmilikActionBean extends AbleActionBean {

    private Hakmilik hakmilik;
    private KodUrusan kodUrusan;
    private Pengguna pengguna;
    private String idHakmilik;
    private List<Akaun> list;
    private String daerah;
    private boolean flag = false;
    private boolean btn = true;
    private String btn1;
    private List<KodBandarPekanMukim> senaraiBPM;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    private KodUrusanDAO kodUrusanDAO;

    @DefaultHandler
    public Resolution selectTransaction() {
        penyukatanBPM();
        return new ForwardResolution("/WEB-INF/jsp/hasil/selenggara_hakmilik.jsp");
    }

    public Resolution search() {

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna p = ctx.getUser();
        
        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
//            __pg_start = (Integer.parseInt(page) - 1) * __pg_max_records;
//            __pg_max_records = __pg_start + __pg_max_records;
        }

//        list = hakmilikService.findAll(getContext().getRequest().getParameterMap());
        list = hakmilikService.findAll(getContext().getRequest().getParameterMap(), get__pg_start() * get__pg_max_records(), get__pg_max_records(), p.getKodCawangan().getKod());
        set__pg_total_records(hakmilikService.getTotalRecord(getContext().getRequest().getParameterMap(), p.getKodCawangan().getKod()).intValue());
        penyukatanBPM();
        if (list.size() > 0) {
            Akaun ak = new Akaun();
            ak = getList().get(0);
            setBtn(false);
        }

        setFlag(true);
        return new ForwardResolution("/WEB-INF/jsp/hasil/selenggara_hakmilik.jsp");
    }

    @ValidationMethod(on = "selectList")
    public void validateRadio(ValidationErrors errors) {
        if (idHakmilik == null) {
            errors.add("radioButton", new SimpleError("Sila Pilih Salah Satu Daripada Senarai Hakmilik Sebelum Tekan Butang Terus"));
        }
    }

//    @ValidationMethod(on = "search")
//    public void validateField(ValidationErrors errors) {
//        if ((hakmilik == null)) {
//            errors.add(" ", new SimpleError("Sila Masukkan Maklumat Dibawah Sebelum Tekan Butang Cari"));
//        }
//    }

    @Inject
    PelarasanCukaiService service;

    public Resolution selectList() {
        setBtn1("none");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();
        kodUrusan = kodUrusanDAO.findById("PLC");

        if (service.generateIdPermohonanPelarasanCukaiWorkflow(kodUrusan, pengguna, hakmilik)) {
            addSimpleMessage("Maklumat Telah Berjaya Disimpan. Sila ke Senarai Tugasan untuk meneruskan tugasan.");
        } else {
            addSimpleError("Maklumat tidak berjaya disimpan.");
        }

        return new ForwardResolution("/WEB-INF/jsp/hasil/selenggara_hakmilik.jsp");
    }

    public Resolution penyukatanBPM() {
        String kodDaerah = daerah;
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        senaraiBPM = q.list();
        return new ForwardResolution("/WEB-INF/jsp/hasil/selenggara_hakmilik.jsp").addParameter("popup", "true");
    }

    public List getSenaraiKodHakmilik(){
        Session s = sessionProvider.get();
        Query q = s.createQuery("from KodHakmilik kh order by kh.kod");
        return q.list();
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<Akaun> getList() {
        return list;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public String getBtn1() {
        return btn1;
    }

    public void setBtn1(String btn1) {
        this.btn1 = btn1;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }
}