/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.nota;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikUrusanSurat;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanSuratPembetulan;
import etanah.model.WakilKuasa;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.daftar.PembetulanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.ArrayList;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/daftar/pembetulan/SuratKuasaWakil")
public class BetulSuratKuasaWakilActionBean extends AbleActionBean {

    @Inject
    PembetulanService pService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    private Permohonan permohonan;
    private PermohonanSuratPembetulan mohonSuratBetul;
    private Pengguna peng;
    private InfoAudit info;
    private HakmilikUrusan hakmilikUrusan;
    private String[] noRujukan;
    private String noSurat;
    private PermohonanAtasPerserahan permohonanAtasPerserahan;
    private List<HakmilikUrusan> hakmilikUrusanListByKodserah;
    private List<PermohonanAtasPerserahan> mohonAtasUrusan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private WakilKuasa wakilkuasa;
    private List<HakmilikUrusanSurat> senaraiUrusanSurat;
    
    private HakmilikPermohonan hp;

    public String getNoSurat() {
        return noSurat;
    }

    public void setNoSurat(String noSurat) {
        this.noSurat = noSurat;
    }

    public WakilKuasa getWakilkuasa() {
        return wakilkuasa;
    }

    public void setWakilkuasa(WakilKuasa wakilkuasa) {
        this.wakilkuasa = wakilkuasa;
    }

    public String[] getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String[] noRujukan) {
        this.noRujukan = noRujukan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikUrusan> getHakmilikUrusanListByKodserah() {
        return hakmilikUrusanListByKodserah;
    }

    public void setHakmilikUrusanListByKodserah(List<HakmilikUrusan> hakmilikUrusanListByKodserah) {
        this.hakmilikUrusanListByKodserah = hakmilikUrusanListByKodserah;
    }

    public List<PermohonanAtasPerserahan> getMohonAtasUrusan() {
        return mohonAtasUrusan;
    }

    public void setMohonAtasUrusan(List<PermohonanAtasPerserahan> mohonAtasUrusan) {
        this.mohonAtasUrusan = mohonAtasUrusan;
    }

    public PermohonanSuratPembetulan getMohonSuratBetul() {
        return mohonSuratBetul;
    }

    public void setMohonSuratBetul(PermohonanSuratPembetulan mohonSuratBetul) {
        this.mohonSuratBetul = mohonSuratBetul;
    }

    public InfoAudit getInfo() {
        return info;
    }

    public void setInfo(InfoAudit info) {
        this.info = info;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }
    private static final Logger LOG = Logger.getLogger(BetulSuratKuasaWakilActionBean.class);

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution maklumatSuratKuasaWakil() {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (hp != null && hp.getHakmilik() != null) {
                mohonAtasUrusan = pService.findAtasMohonHakmilik(idPermohonan, hp.getHakmilik().getIdHakmilik());
            } else {
                mohonAtasUrusan = pService.findAtasUrusan(idPermohonan);
            }
            
            int i = 0;
//            noRujukan = new String[mohonAtasUrusan.size()];
            List<HakmilikUrusanSurat> senaraiHakmilikUrusanSurat = new ArrayList<HakmilikUrusanSurat>();
            for (PermohonanAtasPerserahan pp : mohonAtasUrusan) {
                HakmilikUrusan hu = pService.findUrusan(String.valueOf(pp.getUrusan().getIdUrusan()));                
                for(HakmilikUrusanSurat hus : hu.getSenaraiSurat())
                {
//                    noSurat = hus.getNoSurat();
                    senaraiHakmilikUrusanSurat.add(hus);
                }
//              
//                mohonSuratBetul = pService.findSuratIDMohon(permohonan.getIdPermohonan(), String.valueOf(pp.getUrusan().getIdUrusan()));
//                if (mohonSuratBetul != null) {
//                    noRujukan[i] = String.valueOf(mohonSuratBetul.getNoRujukan());
//                }
//                i++;
            }
            noRujukan = new String[senaraiHakmilikUrusanSurat.size()];
            for (HakmilikUrusanSurat hus : senaraiHakmilikUrusanSurat) {
                    mohonSuratBetul = pService
                            .findSurat(hus.getNoSurat(), permohonan.getIdPermohonan(), String.valueOf(hus.getUrusan().getIdUrusan()));
                    if (mohonSuratBetul != null) {
                        noRujukan[i] = String.valueOf(mohonSuratBetul.getNoRujukan());
                    } else {
                        noRujukan[i] = "";
                    }
                    i++;
            }
            
        }
        return new JSP("daftar/pembetulan/betulSuratKuasaWakil.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikUrusanListByKodserah = new ArrayList<HakmilikUrusan>();
            mohonAtasUrusan = pService.findAtasUrusan(idPermohonan);            
            if (mohonAtasUrusan != null) {
                noRujukan = new String[mohonAtasUrusan.size()];
                List<HakmilikUrusanSurat> senaraiHakmilikUrusanSurat = new ArrayList<HakmilikUrusanSurat>();
                for (PermohonanAtasPerserahan pp : mohonAtasUrusan) {
                   HakmilikUrusan hu = pService.findUrusan(String.valueOf(pp.getUrusan().getIdUrusan()));                
                   for(HakmilikUrusanSurat hus : hu.getSenaraiSurat())
                   {
                       senaraiHakmilikUrusanSurat.add(hus);
                   }
               }
                noRujukan = new String[senaraiHakmilikUrusanSurat.size()];
            }
            if (hakmilikPermohonanList != null) {

                hp = null;

                if (StringUtils.isNotBlank(idHakmilik)) {
                    hp = hakmilikPermohonanService.findHakmilikPermohonan(idHakmilik, idPermohonan);
                } else {
                    hp = hakmilikPermohonanList.get(0);
                }
                
                if (hp != null) {
                    List<HakmilikUrusan> temp = pService.findUrusanByidKodSerah(hp.getHakmilik().getIdHakmilik());
                    if (!temp.isEmpty()) {
                        for (HakmilikUrusan hu : temp) {
                            List<HakmilikUrusanSurat> listSurat = hu.getSenaraiSurat();
                            for (HakmilikUrusanSurat su : listSurat) {
                                if (su.getKodDokumen() == null) continue;
                                if (su.getKodDokumen().getKod().startsWith("SW")) {
                                    hakmilikUrusanListByKodserah.add(hu);
                                }
                            }
                        }
//                        for (HakmilikUrusan hu : temp) {
//                            if((hu.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("SW"))||(hu.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("SWB"))){
//                            hakmilikUrusanListByKodserah.add(hu);}
//                        }
                    }
                }               
            }
        }
    }

    public Resolution savePerserahan() throws ParseException {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        String[] param = getContext().getRequest().getParameterValues("idUrusan");
        for (String idUrusan : param) {
            hakmilikUrusan = pService.findUrusan(idUrusan);
            permohonanAtasPerserahan = pService.findAtasMohon(idUrusan, String.valueOf(permohonan.getIdPermohonan()));
            HakmilikPermohonan hp = 
                    hakmilikPermohonanService.findHakmilikPermohonan(hakmilikUrusan.getHakmilik().getIdHakmilik(), 
                    permohonan.getIdPermohonan());
            if (permohonanAtasPerserahan != null) {
                error = "Urusan Telah diPohon";
                return new RedirectResolution(BetulSuratKuasaWakilActionBean.class, "maklumatSuratKuasaWakil").addParameter("error", error).addParameter("message", msg);
            } else {
                permohonanAtasPerserahan = new PermohonanAtasPerserahan();
                LOG.info("New Record");
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanAtasPerserahan.setInfoAudit(info);
                permohonanAtasPerserahan.setPermohonan(permohonan);
                permohonanAtasPerserahan.setUrusan(hakmilikUrusan);
                permohonanAtasPerserahan.setHakmilikPermohonan(hp);
                pService.simpanAtasUrusan(permohonanAtasPerserahan);

            }
        }
        msg = "Kemasukan Data Pembetulan Telah Berjaya";
        return new RedirectResolution(BetulSuratKuasaWakilActionBean.class, "maklumatSuratKuasaWakil").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteChanges() {
        String error = "";
        String msg = "";
        String idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        LOG.debug("idUrusan : " + idUrusan);
        if (idUrusan != null) {
            permohonanAtasPerserahan = pService.findAtasMohon(idUrusan, String.valueOf(permohonan.getIdPermohonan()));

            if (permohonanAtasPerserahan != null) {
                pService.deleteUrusan(permohonanAtasPerserahan);

                mohonSuratBetul = pService.findSuratIDMohon(permohonan.getIdPermohonan(), String.valueOf(permohonanAtasPerserahan.getUrusan().getIdUrusan()));
                if(mohonSuratBetul !=null)
                {
                    pService.deleteSurat(mohonSuratBetul);
                }
            }
            msg = "Data Telah Berjaya diHapuskan";
        }
        return new RedirectResolution(BetulSuratKuasaWakilActionBean.class, "maklumatSuratKuasaWakil").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution saveSurat() throws ParseException {
        int counter = 0;
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        mohonAtasUrusan = pService.findAtasUrusan(permohonan.getIdPermohonan());
        LOG.debug("Mohon Atas Urusan Size:" + mohonAtasUrusan.size());
        for (PermohonanAtasPerserahan pp : mohonAtasUrusan) {
//            mohonSuratBetul = pService.findSuratIDMohon(permohonan.getIdPermohonan(), String.valueOf(pp.getUrusan().getIdUrusan()));
            if (!pp.getHakmilikPermohonan().equals(hp)) continue;

            hakmilikUrusan = pService.findUrusan(String.valueOf(pp.getUrusan().getIdUrusan()));
            for (HakmilikUrusanSurat hus : hakmilikUrusan.getSenaraiSurat()) {
                wakilkuasa = pService.findWakilKuasa(hus.getNoSurat());
                mohonSuratBetul = pService.findSurat(wakilkuasa.getIdWakil(), permohonan.getIdPermohonan(), String.valueOf(pp.getUrusan().getIdUrusan()));

                if (mohonSuratBetul != null) {
                    LOG.info("Update Existing Record");
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonSuratBetul.setInfoAudit(info);
                    mohonSuratBetul.setWakilKuasa(wakilkuasa);
                    mohonSuratBetul.setCawangan(peng.getKodCawangan());
                    mohonSuratBetul.setPermohonan(permohonan);
                    mohonSuratBetul.setUrusan(hakmilikUrusan);
                    if (StringUtils.isNotBlank(noRujukan[counter])) {
                        mohonSuratBetul.setNoRujukan(noRujukan[counter]);
                    }
                    pService.updateSurat(mohonSuratBetul);
                    msg = "Kemaskini Data Pembetulan Telah Berjaya";
                } else {
                    LOG.info("New Entry");
                    PermohonanSuratPembetulan psp = new PermohonanSuratPembetulan();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    psp.setInfoAudit(info);
                    psp.setWakilKuasa(wakilkuasa);
                    psp.setCawangan(peng.getKodCawangan());
                    psp.setPermohonan(permohonan);
                    psp.setUrusan(hakmilikUrusan);
                    psp.setHakmilik(hus.getUrusan().getHakmilik());
                    if (StringUtils.isNotBlank(noRujukan[counter])) {
                        psp.setNoRujukan(noRujukan[counter]);
                    }
                    pService.simpanSurat(psp);
                    msg = "Kemasukan Data Pembetulan Telah Berjaya";
                }
            counter = counter + 1;
                    
                }            
        }
        return new RedirectResolution(BetulSuratKuasaWakilActionBean.class, "maklumatSuratKuasaWakil").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution doCheckSurat() {

        String result = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String noSurat = (String) getContext().getRequest().getParameter("noRujukan");
        if (noSurat != null) {
            wakilkuasa = pService.findWakilKuasa(noSurat);
            if (wakilkuasa == null) {
                result = "0";
            } else {

                if (!(peng.getKodCawangan().getKod().equals(wakilkuasa.getCawangan().getKod()))) {
                    result = "1";
                }
                if (wakilkuasa.getAktif() == 'T') {
                    result = "2";
                }
                if(peng.getKodCawangan().getKod().equals(wakilkuasa.getCawangan().getKod()) && (wakilkuasa.getAktif() == 'Y')){
                    result = "3";
                }
            }
        }
        LOG.debug(result);

        return new StreamingResolution("text/plain", result);
    }

    public List<HakmilikUrusanSurat> getSenaraiUrusanSurat() {
        return senaraiUrusanSurat;
    }

    public void setSenaraiUrusanSurat(List<HakmilikUrusanSurat> senaraiUrusanSurat) {
        this.senaraiUrusanSurat = senaraiUrusanSurat;
    }
}
