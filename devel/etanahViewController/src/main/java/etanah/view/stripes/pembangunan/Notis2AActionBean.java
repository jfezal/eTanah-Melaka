/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */

@UrlBinding("/pembangunan/notis2A_bantahan")
public class Notis2AActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(Notis2AActionBean.class);
    @Inject
    PermohonanDAO  permohonanDAO;
    @Inject
    PembangunanService devService;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat tdf = new SimpleDateFormat("hh:mm a");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private String tmptSiasatan;
    private String tarikh;
    private String masa;
    private String jam;
    private String minit;
    private String pagiPetang;
    private String perkara;
    private String idPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;


    @DefaultHandler
    public Resolution showNotisBorang2A(){
        return new JSP("pembangunan/pecahBahagian/sedia_cetak_notis_borang2A.jsp").addParameter("tab", "true");
    }
    public Resolution showKemasukanSiasatan(){
        return new JSP("pembangunan/pecahBahagian/kemasukan_siasatan.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan pemohonan = permohonanDAO.findById(idPermohonan);
        HakmilikPermohonan mh = (HakmilikPermohonan) pemohonan.getSenaraiHakmilik().get(0);
        hakmilikPerbicaraan = devService.findHakmilikBicaraByIdMH(mh.getId());
        if (hakmilikPerbicaraan != null) {
            tmptSiasatan = hakmilikPerbicaraan.getLokasiBicara();
            perkara = hakmilikPerbicaraan.getCatatan();
            if (hakmilikPerbicaraan.getTarikhBicara() != null) {
                try {
                    tarikh = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
                    masa = tdf.format(hakmilikPerbicaraan.getTarikhBicara());
                    jam = masa.substring(0, 2);
                    minit = masa.substring(3, 5);
                    pagiPetang = masa.substring(6, 8);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }//if
        } //if
        LOG.info("----rehydrate----------------:"+tarikh+"--"+masa+"--"+jam+"--"+minit+"--"+pagiPetang);
    }


        public Resolution simpan() throws ParseException {
            LOG.info("----simpan----------------:"+tarikh+"--"+masa+"--"+jam+"--"+minit+"--"+pagiPetang);
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit ia=new InfoAudit();
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            HakmilikPermohonan mh = (HakmilikPermohonan) permohonan.getSenaraiHakmilik().get(0);
            hakmilikPerbicaraan = devService.findHakmilikBicaraByIdMH(mh.getId());
            if(hakmilikPerbicaraan!=null){
                ia = hakmilikPerbicaraan.getInfoAudit();
                ia.setTarikhKemaskini(new Date());
                ia.setDiKemaskiniOleh(peng);
            }else{
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                ia.setTarikhMasuk(new Date());
                ia.setDimasukOleh(peng);                
                hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
                hakmilikPerbicaraan.setHakmilikPermohonan(mh);
                hakmilikPerbicaraan.setBatalRizab('T');
                hakmilikPerbicaraan.setKawasanPBT('T');
                hakmilikPerbicaraan.setPelanPembangunan('T');
            }
            hakmilikPerbicaraan.setInfoAudit(ia);
            hakmilikPerbicaraan.setLokasiBicara(tmptSiasatan);
            hakmilikPerbicaraan.setCatatan(perkara);
            try{
                if(tarikh!=null && !jam.equals("00")&& !minit.equals("00")&& !pagiPetang.equals("00")){
                    masa = jam+":"+minit+" "+pagiPetang;
                    Date tarikBicara = (Date)formatter.parse(tarikh + " " + masa);
                    hakmilikPerbicaraan.setTarikhBicara(tarikBicara);
                }
               }catch(Exception e){
                    e.printStackTrace();
                }
            devService.simpanHakmilikPerbicaraan(hakmilikPerbicaraan);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            return new JSP("pembangunan/pecahBahagian/sedia_cetak_notis_borang2A.jsp").addParameter("tab", "true");
        }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getPagiPetang() {
        return pagiPetang;
    }

    public void setPagiPetang(String pagiPetang) {
        this.pagiPetang = pagiPetang;
    }

    public String getPerkara() {
        return perkara;
    }

    public void setPerkara(String perkara) {
        this.perkara = perkara;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getTmptSiasatan() {
        return tmptSiasatan;
    }

    public void setTmptSiasatan(String tmptSiasatan) {
        this.tmptSiasatan = tmptSiasatan;
    }

}
