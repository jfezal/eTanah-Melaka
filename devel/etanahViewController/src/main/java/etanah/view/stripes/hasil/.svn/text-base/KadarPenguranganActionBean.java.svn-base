/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Transaksi;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/kadar_pengurangan")
public class KadarPenguranganActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(KadarPenguranganActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;

    private Hakmilik hakmilik;
    private Akaun akaun;
    private Transaksi transaksi;
    private BigDecimal denda;

    @Inject
    RemisyenManager manager;
    @Inject
    etanah.kodHasilConfig khconf;

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("hasil/kadar_pengurangan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null){
            denda = new BigDecimal(0.00);
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
            akaun = hakmilik.getAkaunCukai();
            for(int i = 0; i < akaun.getSemuaTransaksi().size(); i++){
                transaksi = akaun.getSemuaTransaksi().get(i);
                if (transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("dendaLewat"))){ // kod denda lewat cukai tanah
                    denda = denda.add(transaksi.getAmaun());
                }
            }

            if(!"REMTD".equals(permohonan.getKodUrusan().getKod())){
                //auto save for default value deduction percentage
                try{
                    if(permohonan.getNilaiKeputusan() == null){
                        permohonan.setNilaiKeputusan(new BigDecimal(0.5));
                        manager.save(permohonan, peng);
                        LOG.info("Maklumat Telah Berjaya Dikemaskini..");
                    }
                }catch(Exception ex) {
                    LOG.info("Exception ex :"+ex);
                }
            }
        }
        LOG.info("rehydrate:finish");
    }

    public Resolution saveOrUpdate(){
        LOG.info("saveOrUpdate:start");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        manager.save(permohonan, peng);
        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");
        LOG.info("Maklumat Telah Berjaya Dikemaskini..");
        LOG.info("saveOrUpdate:finish");
//        return new RedirectResolution(KadarPenguranganActionBean.class, "showForm");
        return new JSP("hasil/kadar_pengurangan.jsp").addParameter("tab", "true");
    }

    public Resolution reset(){
        rehydrate();
        return new RedirectResolution(KadarPenguranganActionBean.class, "showForm");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }
}
