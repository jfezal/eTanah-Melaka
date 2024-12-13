package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import able.stripes.*;
import etanah.model.*;
import java.math.BigDecimal;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/kadar_pengurangan_mlk")
public class KadarPenguranganMlkActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(KadarPenguranganMlkActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();

    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Akaun akaun;
    private Transaksi transaksi;
    private BigDecimal denda = new BigDecimal(0.0);

    @Inject RemisyenManager manager;
    @Inject etanah.kodHasilConfig khconf;
    @Inject PermohonanDAO permohonanDAO;
    @Inject HakmilikDAO hakmilikDAO;

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("hasil/kadar_pengurangan_mlk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
            hakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
            akaun = hakmilik.getAkaunCukai();
            for(int i = 0; i < akaun.getSemuaTransaksi().size(); i++){
                transaksi = akaun.getSemuaTransaksi().get(i);
                if (permohonan.getKodUrusan().getKod().equals("PPDL") && transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("dendaLewat"))){
                    denda = denda.add(transaksi.getAmaun());
                }
                if (permohonan.getKodUrusan().getKod().equals("PPPT") && transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("cukaiTanahTunggakan"))){
                    denda = denda.add(transaksi.getAmaun());
                }
            }

            //auto save for default value deduction percentage for PPDL only
            if("PPDL".equals(permohonan.getKodUrusan().getKod())){
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
    
    public Resolution reloadEdit() {
        LOG.info("..:: INSIDE reloadEdit ::..");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String id = getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        
        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
            hakmilik = hakmilikDAO.findById(id);
            akaun = hakmilik.getAkaunCukai();
            for(int i = 0; i < akaun.getSemuaTransaksi().size(); i++){
                transaksi = akaun.getSemuaTransaksi().get(i);
                if (permohonan.getKodUrusan().getKod().equals("PPDL") && transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("dendaLewat"))){
                    denda = denda.add(transaksi.getAmaun());
                }
                if (permohonan.getKodUrusan().getKod().equals("PPPT") && transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("cukaiTanahTunggakan"))){
                    denda = denda.add(transaksi.getAmaun());
                }
            }

            //auto save for default value deduction percentage for PPDL only
            if("PPDL".equals(permohonan.getKodUrusan().getKod())){
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
        LOG.info("..:: reloadEdit:FINISHED ::..");
        return showForm();
    }

    public Resolution saveOrUpdate(){
        LOG.info("saveOrUpdate:start");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        manager.save(permohonan, peng);
        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");
        LOG.info("Maklumat Telah Berjaya Dikemaskini..");
        LOG.info("saveOrUpdate:finish");
//        return new RedirectResolution(KadarPenguranganActionBean.class, "showForm");
        return new JSP("hasil/kadar_pengurangan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution reset(){
        rehydrate();
        return new RedirectResolution(KadarPenguranganMlkActionBean.class, "showForm");
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

    public List<HakmilikPermohonan> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<HakmilikPermohonan> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }
}
