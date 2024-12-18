/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.PermohonanPengguna;
import etanah.service.uam.UamService;
import etanah.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;
import org.hibernate.Query;

/**
 *
 * @author amir.muhaimin
 */
@HttpCache(allow = false)
@UrlBinding("/semakan_permohonan")
public class SemakanPermohonan extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SemakanPermohonan.class);
   
    private PermohonanPengguna Mpengguna;
    private String idPengguna;
    private boolean flag = false;
    private List<PermohonanPengguna> listPengguna = new ArrayList<PermohonanPengguna>();
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/uam/semakan_permohonan.jsp";
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private UamService service;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        return new JSP("uam/semakan_permohonan.jsp");
    }

    public Resolution search(){
       
        if(idPengguna != null){
            listPengguna = searchByIDPengguna(idPengguna);
        }
        flag = true;
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    public List<PermohonanPengguna> searchByIDPengguna(String idPengguna){
        List<PermohonanPengguna> list = new ArrayList<PermohonanPengguna>();

        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PermohonanPengguna u where u.idPengguna = :nama");
        q.setString("nama", idPengguna);

        System.out.println("sql :"+q.getQueryString());
        list = q.list();
        logger.info("list.size() : "+list.size());

        return list;
    }
    
    public Resolution kembali() throws Exception {

        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        if (!StringUtils.isBlank(idPengguna)) {
            Mpengguna = service.searchingUser2(idPengguna);
            logger.info("Id Pengguna :" + idPengguna);
            if (Mpengguna != null) {
                Mpengguna = new PermohonanPengguna();
                Mpengguna.setIdPengguna("");
            } else {
                Mpengguna = new PermohonanPengguna();
                Mpengguna.setIdPengguna("");
            }
        } else {
            Mpengguna = new PermohonanPengguna();
        }
        logger.info("Back to Login Page....");
        return new ForwardResolution("/WEB-INF/jsp/login/login.jsp");
    }


    

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<PermohonanPengguna> getListPengguna() {
        return listPengguna;
    }

    public void setListPengguna(List<PermohonanPengguna> listPengguna) {
        this.listPengguna = listPengguna;
    }

    

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }
    
    
}
