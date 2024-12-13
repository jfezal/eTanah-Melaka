/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility.kod;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.SenaraiKodRujukan;
import etanah.service.KodService;
import etanah.util.StringUtils;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author Aziz
 */
@HttpCache(allow = false)
@UrlBinding("/utility_kod/kodRujukan")
public class CarianKodRujActionBean extends AbleActionBean {

    private String table_name;
    private String table_act_name;
    private List<SenaraiKodRujukan> kodRuj;
    private boolean flag = false;
    private static final org.apache.log4j.Logger SYSLOG = etanah.SYSLOG.getLogger();
    @Inject
    KodService service;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utiliti_kod_ruj/all_kod_ruj.jsp");
    }

    public Resolution findKodRuj() throws Exception {


        if (StringUtils.isBlank(table_act_name) || table_act_name == null || table_act_name.equalsIgnoreCase("")) {
            table_act_name = null;
        }

        kodRuj = service.findKodRujuk1(table_act_name);

        //SYSLOG.debug("table_name ==>"+table_name);
        //SYSLOG.debug("table_act_name ==>"+table_act_name);

        SYSLOG.debug("Result of KODRUJ ==>" + kodRuj.size());

        if (kodRuj.size() > 0) {
            setFlag(true);
            addSimpleMessage(kodRuj.size() + " jadual kod ditemui.");

        } else {
            addSimpleError("Tiada jadual kod ditemui.");
        }


        return new ForwardResolution("/WEB-INF/jsp/utiliti/utiliti_kod_ruj/all_kod_ruj.jsp");

    }

    public String getTable_act_name() {
        return table_act_name;
    }

    public void setTable_act_name(String table_act_name) {
        this.table_act_name = table_act_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public List<SenaraiKodRujukan> getKodRuj() {
        return kodRuj;
    }

    public void setKodRuj(List<SenaraiKodRujukan> kodRuj) {
        this.kodRuj = kodRuj;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public KodService getService() {
        return service;
    }

    public void setService(KodService service) {
        this.service = service;
    }
}
