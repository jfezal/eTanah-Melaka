/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.PengambilanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.DokumenService;
//import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;


@UrlBinding("/pengambilan/borang_L")
public class BorangLActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    PengambilanService service;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private DokumenService dokumenService;
    private PermohonanPihakService permohonanPihakService;

    @Inject
    private PerbicaraanService perbicaraanService;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private Date tarikhWarta;
    private String noWarta;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private List<Integer> tempohPenyerahan = new ArrayList<Integer>();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/borang_L.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new JSP("pengambilan/borang_L.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikPerbicaraanList = new ArrayList<HakmilikPerbicaraan>();

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            permohonanPihakList = p.getSenaraiPihak();
            hakmilikPermohonanList = p.getSenaraiHakmilik();

            permohonanRujukanLuar = service.getLatestRujukanLuar(p.getIdPermohonan());
            if(permohonanRujukanLuar == null){
                permohonanRujukanLuar = service.getRujLuar(p.getIdPermohonan());
            }

            if(permohonanRujukanLuar != null){
                tarikhWarta = permohonanRujukanLuar.getTarikhLulus();
                noWarta = permohonanRujukanLuar.getItem();
            }

            for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                hakmilikPerbicaraan = service.getHakmilikPerbicaraanPampasan(hakmilikPermohonan.getId());
                if(hakmilikPerbicaraan != null){
//                    add to list for each id_MH
                    hakmilikPerbicaraanList.add(hakmilikPerbicaraan);
                    try{
                    Integer lb = hakmilikPerbicaraan.getTempohPenyerahan();
                    tempohPenyerahan.add(lb);
                }catch(Exception e) { }
                    
                }
            }
        }
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(BorangLActionBean.class, "locate");
    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);

                if(service.getHakmilikPerbicaraanPampasan(hmp.getId()) == null) {
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(info);
                    hakmilikPerbicaraan.setCawangan(cawangan);
                    hakmilikPerbicaraan.setHakmilikPermohonan(hmp);
                    hakmilikPerbicaraan.setBatalRizab('T');
                    hakmilikPerbicaraan.setKawasanPBT('T');
                    hakmilikPerbicaraan.setPelanPembangunan('T');
                }else {
                    hakmilikPerbicaraan = service.getHakmilikPerbicaraanPampasan(hmp.getId());
                    InfoAudit ia = hakmilikPerbicaraan.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(ia);
                }

                try {

                    if (i < tempohPenyerahan.size()) {
                        if(tempohPenyerahan.get(i) != null){
                            hakmilikPerbicaraan.setTempohPenyerahan(tempohPenyerahan.get(i));
                        }
                    }

                }catch(Exception e){
                    e.printStackTrace();
                    addSimpleError("Invalid Data");
                }
                perbicaraanService.saveSingleHakmilikPerbicaraan(hakmilikPerbicaraan);
            }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/borang_L.jsp").addParameter("tab", "true");
    }



    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public List<Integer> getTempohPenyerahan() {
        return tempohPenyerahan;
    }

    public void setTempohPenyerahan(List<Integer> tempohPenyerahan) {
        this.tempohPenyerahan = tempohPenyerahan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public Date getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(Date tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanList() {
        return hakmilikPerbicaraanList;
    }

    public void setHakmilikPerbicaraanList(List<HakmilikPerbicaraan> hakmilikPerbicaraanList) {
        this.hakmilikPerbicaraanList = hakmilikPerbicaraanList;
    }

    

}