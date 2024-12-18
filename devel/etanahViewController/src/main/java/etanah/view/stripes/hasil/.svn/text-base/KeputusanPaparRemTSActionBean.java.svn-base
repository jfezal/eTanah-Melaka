/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanAktivitiDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodTransaksi;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanAktiviti;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/papar_keputusan_RemTS")
public class KeputusanPaparRemTSActionBean extends AbleActionBean{

    @Inject
    PermohonanDAO permohonanDAO;

    @Inject
    PermohonanRujukanLuarDAO permohonanUlasanDAO;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    Permohonan permohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<Pihak> listTuanTanah;
    private List<Pemohon> listPemohon;

    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private String idHakmilik;

    @Inject
    PermohonanAktivitiDAO permohonanAktivitiDAO;
    private PermohonanAktiviti permohonanAktiviti;
    private List<PermohonanAktiviti> senaraiPermohonanAktiviti;

    @Inject
    PemohonDAO pemohonDAO;

    @Inject
    TransaksiDAO transaksiDAO;
    private List<Transaksi> listTransaksi;
    private KodTransaksi kodTransaksi;
    private String tahunTunggakan;
    private Akaun akaunBaki;
    
    @DefaultHandler
    public Resolution showForm(){
        return new JSP("hasil/papar_keputusan_ts.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = null;
        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);

            //for maklumat hakmilik
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

            if(!hakmilikPermohonanList.isEmpty())
                hakmilikPermohonan = hakmilikPermohonanList.get(0);

            //for maklumat Tanam Semula
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            permohonanAktiviti = permohonanAktivitiDAO.findById(idPermohonan);


            //for maklumat pemohon
            listTuanTanah = new LinkedList<Pihak>();
            List<HakmilikPermohonan> l = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : l) {
                Hakmilik hk = hp.getHakmilik();
                List<HakmilikPihakBerkepentingan> lhpk = hk.getSenaraiPihakBerkepentingan();
                for (HakmilikPihakBerkepentingan hpk : lhpk) {
                    Pihak phk = hpk.getPihak();
                    listTuanTanah.add(phk);
                }
            }

            listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

            ArrayList listDelete = new ArrayList();

            for(int i=0; i<listPemohon.size(); i++){

                for(int j=0; j<listTuanTanah.size(); j++){

                   if(listPemohon.get(i).getPihak().equals(listTuanTanah.get(j))){

                       listDelete.add(listPemohon.get(i).getPihak());

                   }
                }
            }

             int count = 0;

             for(int i=0; i<listDelete.size(); i++){

                 listTuanTanah.remove(listPemohon.get(i).getPihak());
                 count ++;
             }

            //for maklumat sokongan
            senaraiPermohonanRujukanLuar = permohonanUlasanDAO.findByEqualCriterias(tname, model, null);
            if(!(senaraiPermohonanRujukanLuar.isEmpty()))
                permohonanRujukanLuar = senaraiPermohonanRujukanLuar.get(0);

            //for maklumat perihal cukai
            Hakmilik h  = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
            Akaun akaun = h.getAkaunCukai();
            listTransaksi = akaun.getSemuaTransaksi();
            akaunBaki = h.getAkaunCukai();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            tahunTunggakan = formatter.format(new java.util.Date());
        }
    }

    public Akaun getAkaunBaki() {
        return akaunBaki;
    }

    public void setAkaunBaki(Akaun akaunBaki) {
        this.akaunBaki = akaunBaki;
    }

    public String getTahunTunggakan() {
        return tahunTunggakan;
    }

    public void setTahunTunggakan(String tahunTunggakan) {
        this.tahunTunggakan = tahunTunggakan;
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

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<PermohonanRujukanLuar> getSenaraiPermohonanRujukanLuar() {
        return senaraiPermohonanRujukanLuar;
    }

    public void setSenaraiPermohonanRujukanLuar(List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar) {
        this.senaraiPermohonanRujukanLuar = senaraiPermohonanRujukanLuar;
    }

    public List<Pihak> getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(List<Pihak> listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public PermohonanAktiviti getPermohonanAktiviti() {
        return permohonanAktiviti;
    }

    public void setPermohonanAktiviti(PermohonanAktiviti permohonanAktiviti) {
        this.permohonanAktiviti = permohonanAktiviti;
    }

    public List<PermohonanAktiviti> getSenaraiPermohonanAktiviti() {
        return senaraiPermohonanAktiviti;
    }

    public void setSenaraiPermohonanAktiviti(List<PermohonanAktiviti> senaraiPermohonanAktiviti) {
        this.senaraiPermohonanAktiviti = senaraiPermohonanAktiviti;
    }

}
