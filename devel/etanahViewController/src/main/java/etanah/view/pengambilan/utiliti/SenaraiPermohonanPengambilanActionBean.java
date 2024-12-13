/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.service.ambil.SenaraiPermohonanACQService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pengambilan.share.form.SenaraiPermohonanACQForm;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/pengambilan/senarai_permohonan")
public class SenaraiPermohonanPengambilanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(SenaraiPermohonanPengambilanActionBean.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    SenaraiPermohonanACQService senaraiPermohonanACQService;
    private List<SenaraiPermohonanACQForm> senaraiPermohonanPengambilan;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiPermohonanPengambilan = populateList();
        setPage(senaraiPermohonanPengambilan.size());
        int a = get__pg_start();
        int b = get__pg_max_records();

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/utiliti/senarai_permohonan.jsp");
    }
    
        private void setPage(int senarai) {

        ParamEncoder encoder = new ParamEncoder("line");

        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        set__pg_total_records(senarai);
        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }

        if (get__pg_max_records() > get__pg_total_records()) {
            set__pg_max_records(get__pg_total_records());
        }

    }

    public Resolution popupPermohonanDetail(){
    return new JSP("").addParameter("popup", "true");
    }
    
    public Resolution popupSenaraiHakmilikDetail(){
    return new JSP("").addParameter("popup", "true");
    }
    public List<SenaraiPermohonanACQForm> getSenaraiPermohonanPengambilan() {
        return senaraiPermohonanPengambilan;
    }

    public void setSenaraiPermohonanPengambilan(List<SenaraiPermohonanACQForm> senaraiPermohonanPengambilan) {
        this.senaraiPermohonanPengambilan = senaraiPermohonanPengambilan;
    }

    private List<SenaraiPermohonanACQForm> populateList() {
        List<SenaraiPermohonanACQForm> list = new ArrayList<SenaraiPermohonanACQForm>();
        list = senaraiPermohonanACQService.populateList();
        return list;
    }

}
