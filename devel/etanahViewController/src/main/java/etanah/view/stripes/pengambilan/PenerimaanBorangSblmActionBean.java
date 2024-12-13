/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/penerimaan_borang_sblm")
public class PenerimaanBorangSblmActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    @Inject
    private PerbicaraanService perbicaraanService;
    private String idPermohonan;
    private String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");

    private List<String> tarikhBicara=new ArrayList<String>();
    private List<String> waktuPerbicaraan=new ArrayList<String>();
    private List<String> lokasiBicara = new ArrayList<String>();

    private List<String> jam=new ArrayList<String>();
    private List<String> minit=new ArrayList<String>();
    private List<String> ampm = new ArrayList<String>();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/melaka/pemulihantanah/BorangE_Sblm.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);

            if(p != null && p.getPermohonanSebelum() != null){
                hakmilikPermohonanList = p.getPermohonanSebelum().getSenaraiHakmilik();
                for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

                    HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
                    hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
                    try{
                        String lb = hakmilikPerbicaraan.getLokasiBicara();
                        lokasiBicara.add(lb);
                        String tb = sdf.format(hakmilikPerbicaraan.getTarikhBicara());

                        String tb1 = tb.substring(0, 10);
                        String tb2 = tb.substring(11,13);
                        String tb3 = tb.substring(14,16);
                        String tb4 = tb.substring(17,19);
                        tarikhBicara.add(tb1);
                        jam.add(tb2);
                        minit.add(tb3);
                        ampm.add(tb4);
                    }catch(Exception e) { }
                }
            }
        }
    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getPermohonanSebelum().getCawangan();

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);

                if(perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId()) == null) {
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
                    hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
                    InfoAudit ia = hakmilikPerbicaraan.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(ia);
                }

                try {

                    if (i < lokasiBicara.size()) {
                        if(lokasiBicara.get(i) != null){
                            hakmilikPerbicaraan.setLokasiBicara(lokasiBicara.get(i));
                        }
                    }

                     if (i < tarikhBicara.size()) {
                         if(tarikhBicara.get(i) != null){
                             String StrTarikhBicara = tarikhBicara.get(i);
                             StrTarikhBicara = StrTarikhBicara + " " + jam.get(i) + ":" + minit.get(i) + " " + ampm.get(i);
                             hakmilikPerbicaraan.setTarikhBicara(sdf.parse(StrTarikhBicara));
                         }
                     }
                }catch(Exception e){
                    e.printStackTrace();
                    addSimpleError("Invalid Data");
                }
                perbicaraanService.saveSingleHakmilikPerbicaraan(hakmilikPerbicaraan);
            }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/pemulihantanah/BorangE_Sblm.jsp").addParameter("tab", "true");
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

    public List<String> getLokasiBicara() {
        return lokasiBicara;
    }

    public void setLokasiBicara(List<String> lokasiBicara) {
        this.lokasiBicara = lokasiBicara;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<String> getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(List<String> tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public List<String> getWaktuPerbicaraan() {
        return waktuPerbicaraan;
    }

    public void setWaktuPerbicaraan(List<String> waktuPerbicaraan) {
        this.waktuPerbicaraan = waktuPerbicaraan;
    }

    public List<String> getAmpm() {
        return ampm;
    }

    public void setAmpm(List<String> ampm) {
        this.ampm = ampm;
    }

    public List<String> getJam() {
        return jam;
    }

    public void setJam(List<String> jam) {
        this.jam = jam;
    }

    public List<String> getMinit() {
        return minit;
    }

    public void setMinit(List<String> minit) {
        this.minit = minit;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }



}