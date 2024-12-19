/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/penyediaan8A")
public class Penyediaan8AActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(Penyediaan8AActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;

    @Inject
    PermohonanDAO permohonanDAO;
    Permohonan permohonan;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    etanah.Configuration conf;

    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List senaraiNoRujukan;

    private boolean visible = false;
    private String noDasar;
    private Integer tempohTahun;
    private String kodStatus;
    private String idHakmilik;
    private String negeri;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        if("04".equals(conf.getProperty("kodNegeri")))
            negeri = "melaka";
        return new JSP("hasil/penyediaan_8A.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("idPermohonan :"+idPermohonan);

        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiNoRujukan = new ArrayList();
            try{
                List<HakmilikPermohonan> senaraiHakmilikPermohonanAll = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanAll) {
                    if(hp.getHakmilik().getAkaunCukai().getBaki().intValue() > 0)
                        senaraiHakmilikPermohonan.add(hp);
                        LOG.info("idHakmilik :"+hp.getHakmilik().getIdHakmilik());
                        String [] tname ={"hakmilik"};
                        Object [] tvalue={hp.getHakmilik()};
                        List<DasarTuntutanCukaiHakmilik> senaraiDasarHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(tname, tvalue, null);
                        senaraiNoRujukan.add(senaraiDasarHakmilik.get(0).getNoRujukan());
                }
            }catch(Exception ex){
                LOG.error("rehydrate ex: "+ex);
            }
        }
        LOG.info("rehydrate:finish");
    }

//    public Resolution search() {
//        setVisible(true);
//        Integer dariTahun = 0;
//        Integer keTahun = 0;
//        LOG.debug("noDasar :"+noDasar);
//        LOG.debug("kodStatus :"+kodStatus);
//        if(!("".equals(noDasar))){
//            senaraiDasarTuntutanCukaiHakmilik = searchDasar();
//            if(senaraiDasarTuntutanCukaiHakmilik.size() > 0){
//                dariTahun = senaraiDasarTuntutanCukaiHakmilik.get(0).getDasarTuntutanCukai().getTunggakanDariTahun();
//                keTahun = senaraiDasarTuntutanCukaiHakmilik.get(0).getDasarTuntutanCukai().getTunggakanKeTahun();
//                tempohTahun = (keTahun - dariTahun)+1;
//            }
//            LOG.debug("noDasar22 :"+noDasar);
////            LOG.debug("senaraiDasarTuntutanCukaiHakmilik :"+senaraiDasarTuntutanCukaiHakmilik.get(0).getHakmilik().getAkaunCukai().getBaki());
//
//        }
//        return new JSP("hasil/penyediaan_8A.jsp").addParameter("tab", "true");
//    }
//
//    public List searchDasar() {
//        String hql = "from DasarTuntutanCukaiHakmilik dt ";
////        hql += ", DasarTuntutanNotis dtn ";
////        hql += "where dt.dasarTuntutanCukai.idDasar = :noDasar ";
//        hql += "where ";
////        hql += "dt.hakmilik.getAkaunCukai.baki != 0 and ";
////        hql += "(to_date(current_date,'dd/MM/yyyy') - to_date(dtn.tarikhTerima,'dd/MM/yyyy')) > 90 and ";
//        hql += "dt.dasarTuntutanCukai.idDasar = :noDasar ";
//
//        if(kodStatus != null){
//            hql += "and dt.status.kod = :kodStatus ";
//        }
//        Session s = sessionProvider.get();
//        Query q = s.createQuery(hql);
//        q.setParameter("noDasar", noDasar);
//
//        if(kodStatus != null){
//            q.setParameter("kodStatus", kodStatus);
//        }
//        return q.list();
//    }

    public Resolution popup(){
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new JSP("hasil/hakmilik_details.jsp").addParameter("popup", "true");
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(Integer tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public String getNoDasar() {
        return noDasar;
    }

    public void setNoDasar(String noDasar) {
        this.noDasar = noDasar;
    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDasarTuntutanCukaiHakmilik() {
        return senaraiDasarTuntutanCukaiHakmilik;
    }

    public void setSenaraiDasarTuntutanCukaiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik) {
        this.senaraiDasarTuntutanCukaiHakmilik = senaraiDasarTuntutanCukaiHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public List getSenaraiNoRujukan() {
        return senaraiNoRujukan;
    }

    public void setSenaraiNoRujukan(List senaraiNoRujukan) {
        this.senaraiNoRujukan = senaraiNoRujukan;
    }
}
