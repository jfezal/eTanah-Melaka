/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Hakmilik;
import etanah.model.KodTransaksi;
import etanah.model.Permohonan;
import etanah.model.Transaksi;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/common/perihalCukai")
public class PerihalCukaiActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(PerihalCukaiActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @Inject
    PermohonanDAO permohonanDAO;
    Permohonan permohonan;
    
    @Inject
    TransaksiDAO transaksiDAO;
    private List<Transaksi> listTransaksi;
    private KodTransaksi kodTransaksi;
    private BigDecimal jumlahAmaun;
    private String tahunTunggakan;
    private Akaun akaunBaki;
    
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarCukaiHakmilikDAO;
    private List<DasarTuntutanCukaiHakmilik> listDCH;
    private List<DasarTuntutanNotis> listDCN;
    private String tarikhHantar = "Tiada.";
    private String tarikhTerima = "Tiada.";

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("hasil/perihal_cukai.jsp").addParameter("tab", "true");
    }

    public List<Transaksi> getListTransaksi() {
        return listTransaksi;
    }

    public void setListTransaksi(List<Transaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public BigDecimal getJumlahAmaun() {
        return jumlahAmaun;
    }

    public void setJumlahAmaun(BigDecimal jumlahAmaun) {
        this.jumlahAmaun = jumlahAmaun;
    }

    public String getTahunTunggakan() {
        return tahunTunggakan;
    }

    public void setTahunTunggakan(String tahunTunggakan) {
        this.tahunTunggakan = tahunTunggakan;
    }

    public Akaun getAkaunBaki() {
        return akaunBaki;
    }

    public void setAkaunBaki(Akaun akaunBaki) {
        this.akaunBaki = akaunBaki;
    }

    public List<DasarTuntutanCukaiHakmilik> getListDCH() {
        return listDCH;
    }

    public void setListDCH(List<DasarTuntutanCukaiHakmilik> listDCH) {
        this.listDCH = listDCH;
    }

    public List<DasarTuntutanNotis> getListDCN() {
        return listDCN;
    }

    public void setListDCN(List<DasarTuntutanNotis> listDCN) {
        this.listDCN = listDCN;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(String tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("idPermohonan :"+idPermohonan);
        jumlahAmaun = new BigDecimal(0.00);
        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
            Hakmilik h  = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
            Akaun akaun = h.getAkaunCukai();
            listTransaksi = akaun.getSemuaTransaksi();
            akaunBaki = h.getAkaunCukai();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            tahunTunggakan = formatter.format(new java.util.Date());

            //for Notis6A

            String[] name = {"hakmilik"};
            Object[] value= {h};
            listDCH = dasarCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
//            LOG.debug("listDCH :"+listDCH.size());
            for (DasarTuntutanCukaiHakmilik dch : listDCH) {
                if(dch.getPerserahanBatal6A() == null || dch.getPerserahanBatal8A() == null){
                    listDCN = dch.getSenaraiNotis();
//                    LOG.debug("listDCN :"+listDCN.size());
                    for (DasarTuntutanNotis dcn : listDCN) {
                        if(dcn.getNotis().getKod().equals("N6A")){
                            if(dcn.getTarikhDihantar() != null)
                                tarikhHantar = sdf.format(dcn.getTarikhDihantar());
                            if(dcn.getTarikhTerima() != null)
                                tarikhTerima = sdf.format(dcn.getTarikhTerima());
                            LOG.debug("N6A appear. IdNotis : "+dcn.getIdNotis());
                        }
                    }
                }
            }
        }
        LOG.info("rehydrate:finish");
    }
}
