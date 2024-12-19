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
@UrlBinding("/pengambilan/RekodPembayaranCek")
public class RekodPembayaranCekActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(RekodPembayaranCekActionBean.class);
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
    private Permohonan permohonan;
    private AmbilPampasan ambilPampasan;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPihak permohonanPihak;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private Dokumen dokumen;
    private String idPermohonan;
    private String idHakmilik;
    private Hakmilik hakmilik;
    private Date tarikhDokDiambil1;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<AmbilPampasan> ambilPampasanList = new ArrayList<AmbilPampasan>();
    private List<AmbilPampasan> senaraiAmbilPampasan = new ArrayList<AmbilPampasan>();
    private List<Date> tarikhDokDiambil = new ArrayList<Date>();
    private List<Character> dokDiambil = new ArrayList<Character>();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            FasaPermohonan fasaPermohonan = pengambilanService.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "14TentukanKos");
              FasaPermohonan fasaPermohonan2 = pengambilanService.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "16KeputusanBicara");
            if (fasaPermohonan != null) {
                if (fasaPermohonan.getKeputusan() != null) {
                    KodKeputusan kodKeputusan = fasaPermohonan.getKeputusan();
                    if (kodKeputusan != null) {
                        if (kodKeputusan.getKod().equals("1B")) {
                            addSimpleError("Keputusan 31bc tidak perlu rekod pembayaran cek");
                            getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                        } else {
                            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                        }
                    }
                } else {
                    addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                    getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                }
            }
               if (fasaPermohonan2 != null) {
                if (fasaPermohonan2.getKeputusan() != null) {
                    KodKeputusan kodKeputusan = fasaPermohonan2.getKeputusan();
                    if (kodKeputusan != null) {
                        if (kodKeputusan.getKod().equals("1B")) {
                            addSimpleMessage("Keputusan 3(1)(b)(c) tidak perlu rekod pembayaran cek");
                            getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                        } else {
                            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                        }
                    }
                } else {
                    addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                    getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                }
            }
        }
        return new JSP("pengambilan/RekodPembayaranCek.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        ambilPampasanList = new ArrayList<AmbilPampasan>();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

            for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                List<HakmilikPihakBerkepentingan> hakmilikPihakList = hakmilik.getSenaraiPihakBerkepentingan();
                for (HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
                    long idPihak = hp.getPihak().getIdPihak();
//                    permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
//                    permohonanPihakList = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
//                    permohonanPihakList = permohonanPihakService.getAllSenaraiPmohonPihakByHakmilikAndMohon(idPermohonan, hakmilik.getIdHakmilik());
                     permohonanPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
                    hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                    for (PermohonanPihak pp : permohonanPihakList) {
                        if (hakmilikPerbicaraan != null && pp != null) {
                            perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                            if (perbicaraanKehadiran != null) {
                                List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                                for (AmbilPampasan ambilPampasan : ambilPampasanList1) {
                                    ambilPampasanList.add(ambilPampasan);
                                }
                            }
                        }
                    }
                }
            }
//            if(ambilPampasanList.size() == 0 && permohonan.getPermohonanSebelum() != null){
//                String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
//                if(idSblm != null){
//                    for(HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
//                        Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
//                        List<HakmilikPihakBerkepentingan> hakmilikPihakList= hakmilik.getSenaraiPihakBerkepentingan();
//                        for(HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
//                            long idPihak = hp.getPihak().getIdPihak();
//                            permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idSblm,hakmilik.getIdHakmilik(),idPihak);
//                            HakmilikPermohonan hakmilikPermohonanSBLM= pengambilanService.findByIdHakmilikIdPermohonan(idSblm, hakmilik.getIdHakmilik());
//                            if(hakmilikPermohonanSBLM != null){
//                                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonanSBLM.getId());
//                                if(hakmilikPerbicaraan != null&& permohonanPihak != null){
//                                    perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
//                                    if(perbicaraanKehadiran != null){
//                                        List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
//                                        for(AmbilPampasan ambilPampasan :ambilPampasanList1) {
//                                            ambilPampasanList.add(ambilPampasan);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                }
//            }

        }
    }

    public Resolution showAmbilPampasanList() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        dokDiambil = new ArrayList<Character>();
        tarikhDokDiambil = new ArrayList<Date>();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            if (hakmilikPermohonan != null) {
                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
//                PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                permohonanPihakList = pengambilanService.getPmohonPihakByIdHakmilikIdPihakList(idPermohonan, idHakmilik, idPihak);
                for (PermohonanPihak pp : permohonanPihakList) {
                    if (hakmilikPerbicaraan != null && pp != null) {
                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                        if (perbicaraanKehadiran != null) {
                            senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                            for (AmbilPampasan ambilPampasan : senaraiAmbilPampasan) {
                                    logger.info("tarikh dok diambil :: " + ambilPampasan.getTarikhDokDiambil());
                                    Character dokDiambil1 = ambilPampasan.getDokDiambil();
                                    tarikhDokDiambil1 = ambilPampasan.getTarikhDokDiambil();
                                    logger.info("tarikh dok diambil 1 :: " + tarikhDokDiambil1);
                                    dokDiambil.add(dokDiambil1);
                                    tarikhDokDiambil.add(tarikhDokDiambil1);
                            }
                        }
                    }
                }
            }

//            if(senaraiAmbilPampasan.size() == 0 && permohonan.getPermohonanSebelum() != null){
//                String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
//                if(idSblm != null){
//                    hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
//                    if(hakmilikPermohonan != null){
//                        hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
//                        PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idSblm, idHakmilik, idPihak);
//                        if(hakmilikPerbicaraan != null && permohonanPihak != null) {
//                            perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
//                            if(perbicaraanKehadiran != null) {
//                                senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
//                                for(AmbilPampasan ambilPampasan : senaraiAmbilPampasan) {
//                                    Character dokDiambil1 = ambilPampasan.getDokDiambil();
//                                    Date tarikhDokDiambil1 = ambilPampasan.getTarikhDokDiambil();
//                                    dokDiambil.add(dokDiambil1);
//                                    tarikhDokDiambil.add(tarikhDokDiambil1);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
        }
        return new JSP("pengambilan/RekodPembayaranCek_pop.jsp").addParameter("popup", "true");
    }

//    public Resolution hakmilikDetails() {
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        idHakmilik = (String)getContext().getRequest().getParameter("idHakmilik");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
//
//        getAmbilPampasan();
//
//        return new JSP("pengambilan/RekodPembayaranCek.jsp").addParameter("tab", "true");
//    }
    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            if (hakmilikPermohonan != null) {
                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                if (hakmilikPerbicaraan != null) {
//                    PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                    permohonanPihakList = pengambilanService.getPmohonPihakByIdHakmilikIdPihakList(idPermohonan, idHakmilik, idPihak);
                    for (PermohonanPihak pp : permohonanPihakList) {
                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                        if (perbicaraanKehadiran != null) {
                            senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                            for (int i = 0; i < senaraiAmbilPampasan.size(); ++i) {
                                AmbilPampasan ambilPampasan = senaraiAmbilPampasan.get(i);
                                InfoAudit info = ambilPampasan.getInfoAudit();
                                info.setDiKemaskiniOleh(pengguna);
                                info.setTarikhKemaskini(new java.util.Date());
                                ambilPampasan.setInfoAudit(info);
                                try {
                                    ambilPampasan.setDokDiambil(dokDiambil.get(i));
                                } catch (Exception e) {
                                }
                                try {
                                    ambilPampasan.setTarikhDokDiambil(tarikhDokDiambil.get(i));
                                } catch (Exception e) {
                                }
                                pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                            }
                        }
                    }
                }
            }
        }
//        showAmbilPampasanList();
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/RekodPembayaranCek_pop.jsp").addParameter("popup", "true");
    }

//    private void getAmbilPampasan() {
//        if (idPermohonan != null) {
//            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
//            String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
//            Permohonan permohonanSblm= permohonanDAO.findById(idSblm);
//            hakmilikPermohonanList = permohonanSblm.getSenaraiHakmilik();
//
//            for(HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
//                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
//                Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
//                List<HakmilikPihakBerkepentingan> hakmilikPihakList= hakmilik.getSenaraiPihakBerkepentingan();
//                for(HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
//                    long idPihak = hp.getPihak().getIdPihak();
//                    permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idSblm,hakmilik.getIdHakmilik(),idPihak);
//                    if(hakmilikPerbicaraan != null){
//                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
//                        if(perbicaraanKehadiran != null){
//                            List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
//                            for(AmbilPampasan ambilPampasan :ambilPampasanList1) {
//                                ambilPampasanList.add(ambilPampasan);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
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

    public Date getTarikhDokDiambil1() {
        return tarikhDokDiambil1;
    }

    public void setTarikhDokDiambil1(Date tarikhDokDiambil1) {
        this.tarikhDokDiambil1 = tarikhDokDiambil1;
    }

    public AmbilPampasan getAmbilPampasan() {
        return ambilPampasan;
    }

    public void setAmbilPampasan(AmbilPampasan ambilPampasan) {
        this.ambilPampasan = ambilPampasan;
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }
}
