package etanah.view.stripes.hasil;

import etanah.dao.*;
import etanah.model.*;
import able.stripes.*;
import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 * 18 Dec 2012
 *
 */
@UrlBinding("/hasil/maklumat_deposit")
public class MaklumatBayaranBalikDepositActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatBayaranBalikDepositActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String idPermohonan;
    private boolean flag = true;
    private Permohonan permohonan;
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private List<String> status = new ArrayList<String>();
    private List<String> warta = new ArrayList<String>();
    private List<String> trhWarta = new ArrayList<String>();

    @Inject PermohonanDAO permohonanDAO;
    @Inject protected com.google.inject.Provider<Session> sessionProvider;
    @Inject KodStatusAkaunDAO kodStatusAkaunDAO;
    @Inject KutipanHasilManager manager;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("hasil/maklumat_deposit.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("showForm2");
        flag = false;
        LOG.info("trhWarta.size() : "+trhWarta.size());
        LOG.info("warta.size() : "+warta.size());
        return new JSP("hasil/maklumat_deposit.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate:start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String sql = "SELECT a FROM etanah.model.Akaun a where a.permohonan.idPermohonan =:idMohon";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        q.setString("idMohon", idPermohonan);
        
        senaraiAkaun = q.list();
        for (Akaun ak : senaraiAkaun) {
            ak.setBaki(ak.getBaki().multiply(new BigDecimal(-1)));
        }
        LOG.info("senaraiAkaun.size() : "+senaraiAkaun.size());
    }

    public Resolution save(){
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();

        LOG.info("status.size() : "+status.size());
        for(int i=0; i<senaraiAkaun.size(); i++){
            Akaun a = senaraiAkaun.get(i);
            String sts = status.get(i);
            ia = a.getInfoAudit();

            LOG.info("a.noAkaun() : "+a.getNoAkaun());
            LOG.info("sts : "+sts);

            a.setBaki(a.getBaki().multiply(new BigDecimal(-1)));
            a.setStatus(kodStatusAkaunDAO.findById(sts));
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(now);
            a.setInfoAudit(ia);

            manager.saveOrUpdate(a);
        }

        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new JSP("hasil/maklumat_deposit.jsp").addParameter("tab", "true");
    }
    
    public Resolution saveWarta() throws ParseException{
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();

        LOG.info("trhWarta.size() : "+trhWarta.size());
        LOG.info("warta.size() : "+warta.size());
        for(int i=0; i<senaraiAkaun.size(); i++){
            Akaun a = senaraiAkaun.get(i);
            String tarikh = trhWarta.get(i);
            String noWarta = warta.get(i);
            ia = a.getInfoAudit();

            LOG.info("a.noAkaun() : "+a.getNoAkaun());
            LOG.info("tarikh Warta : "+tarikh);
            LOG.info("Nombor Warta : "+noWarta);
            
            if(noWarta!= null){
                Date d = sdf.parse(tarikh);
                a.setCatatan(noWarta);
                a.setTarikhMatang(d);
//                a.setBaki(a.getBaki().multiply(new BigDecimal(-1)));
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(now);
                a.setInfoAudit(ia);

                manager.saveOrUpdate(a);
            }
        }

        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return showForm2();
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<String> getWarta() {
        return warta;
    }

    public void setWarta(List<String> warta) {
        this.warta = warta;
    }

    public List<String> getTrhWarta() {
        return trhWarta;
    }

    public void setTrhWarta(List<String> trhWarta) {
        this.trhWarta = trhWarta;
    }
}
