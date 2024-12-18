package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import able.stripes.JSP;
import java.math.BigDecimal;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 * @author haqqiem 14 Mar 2013
 */
@UrlBinding("/hasil/perihalCukai")
public class MaklumatPerihalCukaiActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(MaklumatPerihalCukaiActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    Permohonan permohonan;
    private Akaun akaunBaki = new Akaun();
    private KodTransaksi kodTransaksi = new KodTransaksi();
    
    private String tahunTunggakan = "";
    private String tarikhHantar = "Tiada.";
    private String tarikhTerima = "Tiada.";
    private BigDecimal jumlahAmaun = new BigDecimal(0.00);
    
    private List<DasarTuntutanNotis> listDCN;
    private List<DasarTuntutanCukaiHakmilik> listDCH;
    private List<Transaksi> listTransaksi = new ArrayList<Transaksi>();
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
    
    @Inject HakmilikDAO hakmilikDAO;
    @Inject TransaksiDAO transaksiDAO;
    @Inject PermohonanDAO permohonanDAO;
    @Inject DasarTuntutanCukaiHakmilikDAO dasarCukaiHakmilikDAO;
    
    @DefaultHandler
    public Resolution showForm(){
        return new JSP("hasil/maklumat_perihal_cukai.jsp").addParameter("tab", "true");
    }
    
//    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!searchPihak", "!selectPihak", "!searchPihakPemohon", "!selectPihakPemohon", "!deletePemohon", "!savePengarahEdit"})
    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
        
        jumlahAmaun = new BigDecimal(0.00);
        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
            Hakmilik h  = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
            Akaun akaun = h.getAkaunCukai();
            listTransaksi = akaun.getSemuaTransaksi();
            akaunBaki = h.getAkaunCukai();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            tahunTunggakan = formatter.format(new java.util.Date());

            String[] name = {"hakmilik"};
            Object[] value= {h};
            listDCH = dasarCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
            for (DasarTuntutanCukaiHakmilik dch : listDCH) {
                if(dch.getPerserahanBatal6A() == null || dch.getPerserahanBatal8A() == null){
                    listDCN = dch.getSenaraiNotis();
                    for (DasarTuntutanNotis dcn : listDCN) {
                        if(dcn.getNotis().getKod().equals("N6A")){
                            if(dcn.getTarikhDihantar() != null){tarikhHantar = sdf.format(dcn.getTarikhDihantar());}
                            if(dcn.getTarikhTerima() != null){ tarikhTerima = sdf.format(dcn.getTarikhTerima());}
                        }
                    }
                }
            }
        }
        LOG.info("rehydrate:finish");
    }
    
    public Resolution reloadEdit() {
        LOG.info("..:: INSIDE reloadEdit ::..");
        String id = getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        
        jumlahAmaun = new BigDecimal(0.00);
        if(idPermohonan != null){
            Hakmilik h  = hakmilikDAO.findById(id);
            Akaun akaun = h.getAkaunCukai();
            listTransaksi = akaun.getSemuaTransaksi();
            akaunBaki = h.getAkaunCukai();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            tahunTunggakan = formatter.format(new java.util.Date());

            String[] name = {"hakmilik"};
            Object[] value= {h};
            listDCH = dasarCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
            for (DasarTuntutanCukaiHakmilik dch : listDCH) {
                if(dch.getPerserahanBatal6A() == null || dch.getPerserahanBatal8A() == null){
                    listDCN = dch.getSenaraiNotis();
                    for (DasarTuntutanNotis dcn : listDCN) {
                        if(dcn.getNotis().getKod().equals("N6A")){
                            if(dcn.getTarikhDihantar() != null){tarikhHantar = sdf.format(dcn.getTarikhDihantar());}
                            if(dcn.getTarikhTerima() != null){ tarikhTerima = sdf.format(dcn.getTarikhTerima());}
                        }
                    }
                }
            }
        }        
        return showForm();
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Akaun getAkaunBaki() {
        return akaunBaki;
    }

    public void setAkaunBaki(Akaun akaunBaki) {
        this.akaunBaki = akaunBaki;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public String getTahunTunggakan() {
        return tahunTunggakan;
    }

    public void setTahunTunggakan(String tahunTunggakan) {
        this.tahunTunggakan = tahunTunggakan;
    }

    public BigDecimal getJumlahAmaun() {
        return jumlahAmaun;
    }

    public void setJumlahAmaun(BigDecimal jumlahAmaun) {
        this.jumlahAmaun = jumlahAmaun;
    }

    public List<Transaksi> getListTransaksi() {
        return listTransaksi;
    }

    public void setListTransaksi(List<Transaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
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

    public List<DasarTuntutanNotis> getListDCN() {
        return listDCN;
    }

    public void setListDCN(List<DasarTuntutanNotis> listDCN) {
        this.listDCN = listDCN;
    }

    public List<DasarTuntutanCukaiHakmilik> getListDCH() {
        return listDCH;
    }

    public void setListDCH(List<DasarTuntutanCukaiHakmilik> listDCH) {
        this.listDCH = listDCH;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<HakmilikPermohonan> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }
}
