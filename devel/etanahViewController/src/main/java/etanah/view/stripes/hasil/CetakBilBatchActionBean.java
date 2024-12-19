/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.hasil.TagAkaun;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ubuntu-mansur
 */
@UrlBinding("/hasil/cetak_bil")
public class CetakBilBatchActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(CetakBilBatchActionBean.class);
    
    private String negeri;
    private String idKumpulan;
    private String kodCaw;
    private String namaKumpulan;
    private String catatan;
    
    private boolean show = false;
    
    private List<TagAkaun> senaraiKumpulanAkaun;
    
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    @DefaultHandler
    public Resolution showForm(){
        if("05".equals(conf.getProperty("kodNegeri")))
            negeri = "sembilan";
        if("04".equals(conf.getProperty("kodNegeri")))
            negeri = "melaka";
        return new ForwardResolution("/WEB-INF/jsp/hasil/cetak_bil_batch.jsp");
    }
    
    public Resolution search(){
        if("05".equals(conf.getProperty("kodNegeri")))
            negeri = "sembilan";
        if("04".equals(conf.getProperty("kodNegeri")))
            negeri = "melaka";
        senaraiKumpulanAkaun = new ArrayList<TagAkaun>();
        Session s = sessionProvider.get();
        String sql = "select ta from etanah.model.hasil.TagAkaun ta where 1=1 ";
            if(idKumpulan != null)
                sql += "and ta.idTag like :idKump ";
            if(kodCaw != null)
                sql += "and ta.cawangan.kod = :kodCaw ";
            if(namaKumpulan != null)
                sql += "and ta.nama like :namaKump ";
//            if(catatan != null)
//                sql += "and ta.catatan like :catat ";
        LOG.info("sql :"+sql);
        Query q = s.createQuery(sql);
            if(idKumpulan != null)
                q.setString("idKump", "%"+idKumpulan+"%");
            if(kodCaw != null)
                q.setString("kodCaw", kodCaw);
            if(namaKumpulan != null)
                q.setString("namaKump", "%"+namaKumpulan+"%");
//            if(catatan != null)
//                q.setString("catat", "%"+catatan+"%");

        senaraiKumpulanAkaun = q.list();
        LOG.debug("senaraiKumpulanAkaun.size() :"+senaraiKumpulanAkaun.size());
        show = true;
        return new ForwardResolution("/WEB-INF/jsp/hasil/cetak_bil_batch.jsp");
    }

    public String getIdKumpulan() {
        return idKumpulan;
    }

    public void setIdKumpulan(String idKumpulan) {
        this.idKumpulan = idKumpulan;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getNamaKumpulan() {
        return namaKumpulan;
    }

    public void setNamaKumpulan(String namaKumpulan) {
        this.namaKumpulan = namaKumpulan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public List<TagAkaun> getSenaraiKumpulanAkaun() {
        return senaraiKumpulanAkaun;
    }

    public void setSenaraiKumpulanAkaun(List<TagAkaun> senaraiKumpulanAkaun) {
        this.senaraiKumpulanAkaun = senaraiKumpulanAkaun;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

}
