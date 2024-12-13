/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;


import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.service.common.PemohonService;
import etanah.model.*;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PengambilanService;
import etanah.service.common.PerbicaraanService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Rajesh Reddy
 */
@UrlBinding("/pengambilan/RekodPembayaranCekPengambilan")
public class RekodPembayaranCekPengambilanActionBean extends AbleActionBean {
     private static Logger logger = Logger.getLogger(RekodPembayaranCekPengambilanActionBean.class);
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    private PerbicaraanService perbicaraanService;

    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPihak permohonanPihak;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private Dokumen dokumen;
    private String idPermohonan;
    private String idHakmilik;
    private Hakmilik hakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<AmbilPampasan> ambilPampasanList = new ArrayList<AmbilPampasan>();
    private List<AmbilPampasan> senaraiAmbilPampasan = new ArrayList<AmbilPampasan>();
    private List<Date> tarikhDokDiambil = new ArrayList<Date>();
    private List<Character> dokDiambil = new ArrayList<Character>();


    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/rekodPembayaranCek_general.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        ambilPampasanList = new ArrayList<AmbilPampasan>();
        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            String idMohon = permohonan.getIdPermohonan();
            Permohonan p= permohonanDAO.findById(idMohon);
            hakmilikPermohonanList = p.getSenaraiHakmilik();

            for(HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
//                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
//              -->if lokasiBicara != 'Mahkamah Tinggi'<--
                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
                Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                List<HakmilikPihakBerkepentingan> hakmilikPihakList= hakmilik.getSenaraiPihakBerkepentingan();
                for(HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
                    long idPihak = hp.getPihak().getIdPihak();
                    permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idMohon,hakmilik.getIdHakmilik(),idPihak);
                    if(hakmilikPerbicaraan != null){
                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                        if(perbicaraanKehadiran != null){
                            List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                            for(AmbilPampasan ambilPampasan :ambilPampasanList1) {
                                ambilPampasanList.add(ambilPampasan);
                            }
                        }
                    }
                }
            }
        }
    }


    public Resolution showAmbilPampasanList() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        dokDiambil = new ArrayList<Character>();
        tarikhDokDiambil = new ArrayList<Date>();

        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            String idMohon = permohonan.getIdPermohonan();
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idMohon, idHakmilik);
            if(hakmilikPermohonan != null){
//                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                 hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
                if(hakmilikPerbicaraan != null) {
                    PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idMohon, idHakmilik, idPihak);
                    perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                    if(perbicaraanKehadiran != null) {
                        senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                        for(AmbilPampasan ambilPampasan : senaraiAmbilPampasan) {
                            Character dokDiambil1 = ambilPampasan.getDokDiambil();
                            Date tarikhDokDiambil1 = ambilPampasan.getTarikhDokDiambil();
                            dokDiambil.add(dokDiambil1);
                            tarikhDokDiambil.add(tarikhDokDiambil1);
                        }
                    }
                }
            }
        }
        return new JSP("pengambilan/rekodPembayaranCek_pop_general.jsp").addParameter("popup", "true");
    }



    public Resolution hakmilikDetails() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String)getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        getAmbilPampasan();
        
        return new JSP("pengambilan/rekodPembayaranCek_general.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            String idMohon = permohonan.getIdPermohonan();
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idMohon, idHakmilik);
            if(hakmilikPermohonan != null){
//                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
                if(hakmilikPerbicaraan != null) {
                    PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idMohon, idHakmilik, idPihak);
                    perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                    if(perbicaraanKehadiran != null) {
                        senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                        for(int i=0 ; i<senaraiAmbilPampasan.size(); ++i) {
                            AmbilPampasan ambilPampasan = senaraiAmbilPampasan.get(i);
                            InfoAudit info = ambilPampasan.getInfoAudit();
                            info.setDiKemaskiniOleh(pengguna);
                            info.setTarikhKemaskini(new java.util.Date());
                            ambilPampasan.setInfoAudit(info);
                            try{
                                ambilPampasan.setDokDiambil(dokDiambil.get(i));
                            }catch(Exception e){ }
                            try{
                                ambilPampasan.setTarikhDokDiambil(tarikhDokDiambil.get(i));
                            }catch(Exception e){ }
                            pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                        }
                    }
                }
            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/rekodPembayaranCek_pop_general.jsp").addParameter("popup", "true");
    }


    private void getAmbilPampasan() {
        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            String idMohon = permohonan.getIdPermohonan();
            Permohonan p= permohonanDAO.findById(idMohon);
            hakmilikPermohonanList = p.getSenaraiHakmilik();

            for(HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
//                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
                Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                List<HakmilikPihakBerkepentingan> hakmilikPihakList= hakmilik.getSenaraiPihakBerkepentingan();
                for(HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
                    long idPihak = hp.getPihak().getIdPihak();
                    permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idMohon,hakmilik.getIdHakmilik(),idPihak);
                    if(hakmilikPerbicaraan != null){
                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                        if(perbicaraanKehadiran != null){
                            List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                            for(AmbilPampasan ambilPampasan :ambilPampasanList1) {
                                ambilPampasanList.add(ambilPampasan);
                            }
                        }
                    }
                }
            }
        }
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<AmbilPampasan> getAmbilPampasanList() {
        return ambilPampasanList;
    }

    public void setAmbilPampasanList(List<AmbilPampasan> ambilPampasanList) {
        this.ambilPampasanList = ambilPampasanList;
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

    public List<Character> getDokDiambil() {
        return dokDiambil;
    }

    public void setDokDiambil(List<Character> dokDiambil) {
        this.dokDiambil = dokDiambil;
    }

    public List<Date> getTarikhDokDiambil() {
        return tarikhDokDiambil;
    }

    public void setTarikhDokDiambil(List<Date> tarikhDokDiambil) {
        this.tarikhDokDiambil = tarikhDokDiambil;
    }

    public List<AmbilPampasan> getSenaraiAmbilPampasan() {
        return senaraiAmbilPampasan;
    }

    public void setSenaraiAmbilPampasan(List<AmbilPampasan> senaraiAmbilPampasan) {
        this.senaraiAmbilPampasan = senaraiAmbilPampasan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    
}
