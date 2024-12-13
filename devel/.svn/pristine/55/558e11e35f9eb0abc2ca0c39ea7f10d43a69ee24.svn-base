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
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.service.common.PemohonService;
import etanah.model.*;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.BPelService;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/**
 *
 * @author Rajesh Reddy
 */
@UrlBinding("/pengambilan/RekodAmbilCekPemohon")
public class RekodAmbilCekPemohonActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(RekodAmbilCekPemohonActionBean.class);
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
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
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    Pengguna pguna = null;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private PermohonanPihak permohonanPihak;
    private Dokumen dokumen;
    private String idPermohonan;
    private String stageId;
    private String idHakmilik;
    private Hakmilik hakmilik;
    private Pihak pihak;
    private long idPihak;
    private String namaPemohon;
    private Pemohon pemohon;
    private BigDecimal sum;
    private List<BigDecimal> amaunTuntutan = new ArrayList<BigDecimal>();
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private List<PermohonanTuntutanKos> permohonanTuntutanKosList = new ArrayList<PermohonanTuntutanKos>();
    private List<AmbilPampasan> senaraiAmbilPampasan = new ArrayList<AmbilPampasan>();
    private List<AmbilPampasan> ambilPampasanList = new ArrayList<AmbilPampasan>();
    private List<Date> tarikhDokDiambil = new ArrayList<Date>();
    private List<String> catatan = new ArrayList<String>();
    private boolean display = false;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodAmbilCekPemohon.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonanTuntutanKosList = new ArrayList<PermohonanTuntutanKos>();
        ambilPampasanList = new ArrayList<AmbilPampasan>();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (pihak != null) {
                pihak = pengambilanService.findByIdPihak(pemohon.getPihak().getIdPihak());
                namaPemohon = pihak.getNama();
                logger.debug("namaPemohon" + namaPemohon);
            }

//            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
//            for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
//                hakmilik = hakmilikPermohonan.getHakmilik();
//                List<HakmilikPihakBerkepentingan> hakmilikPihakList = hakmilik.getSenaraiPihakBerkepentingan();
//                for (HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
//                    long idPihak = hp.getPihak().getIdPihak();
//                    permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
//                    hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
//                    if (hakmilikPerbicaraan != null && permohonanPihak != null) {
//                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
//                        if (perbicaraanKehadiran != null) {
//                            List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
//                            for (AmbilPampasan ambilPampasan : ambilPampasanList1) {
//                                ambilPampasanList.add(ambilPampasan);
//                            }
//                        }
//                    }
//                }
//            }

            List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikPermohonan.size() > 0) {
                hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
                if (hakmilik != null) {
                    List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                    senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
                    if (senaraiPermohonanPihak.size() > 0) {
                        permohonanPihak = senaraiPermohonanPihak.get(0);
                        logger.debug(permohonanPihak + "permohonanPihak");
                        if (permohonanPihak != null) {
//                            permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
                            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                            for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                                hakmilik = hakmilikPermohonan.getHakmilik();
//                                List<HakmilikPihakBerkepentingan> hakmilikPihakList = hakmilik.getSenaraiPihakBerkepentingan();
//                                for (HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
//                                    long idPihak = hp.getPihak().getIdPihak();
//                                    permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                                    hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                                    if (hakmilikPerbicaraan != null && permohonanPihak != null) {
                                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                                        if (perbicaraanKehadiran != null) {
                                            List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                                            for (AmbilPampasan ambilPampasan : ambilPampasanList1) {
                                                ambilPampasanList.add(ambilPampasan);
                                            }
                                        }
                                    }
//                                }
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
//        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        long idPermohonanPihak = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPihak"));

        catatan = new ArrayList<String>();
        amaunTuntutan = new ArrayList<BigDecimal>();
        tarikhDokDiambil = new ArrayList<Date>();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            if (hakmilikPermohonan != null) {
                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                    pihak = pengambilanService.findByIdPihak(pemohon.getPihak().getIdPihak());
                    permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, pihak.getIdPihak());
                    if (hakmilikPerbicaraan != null && permohonanPihak != null) {
                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                        if (perbicaraanKehadiran != null) {
                            senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
//                            int c = 1;
//                            int counter = senaraiAmbilPampasan.size() - c;
                            for (AmbilPampasan ambilPampasan : senaraiAmbilPampasan) {
//                                ambilPampasan = senaraiAmbilPampasan.get(counter);
//                            Character dokDiambil1 = ambilPampasan.getDokDiambil();
                                sum = ambilPampasan.getJumTerimaPampasan();
                                Date tarikhDokDiambil1 = ambilPampasan.getTarikhDokDiambil();
//                            dokDiambil.add(dokDiambil1);
                                tarikhDokDiambil.add(tarikhDokDiambil1);
                            }
                        }
                    }
                }
            }

//            permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);
//            if (permohonanPihak != null) {
//                permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
//                for (PermohonanTuntutanKos permohonanTuntutanKos1 : permohonanTuntutanKosList) {
//                    String catatan1 = permohonanTuntutanKos1.getCatatan();
//                    catatan.add(catatan1);
//                    BigDecimal amaunTuntutan1 = permohonanTuntutanKos1.getAmaunTuntutan();
//                    amaunTuntutan.add(amaunTuntutan1);
//                }
//            }
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/RekodPembayaranCek_pop.jsp").addParameter("popup", "true");
    }

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
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        pihak = pengambilanService.findByIdPihak(pemohon.getPihak().getIdPihak());
                        PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, pihak.getIdPihak());
                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                        if (perbicaraanKehadiran != null) {
                            senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                            for (int i = 0; i < senaraiAmbilPampasan.size(); ++i) {
                                AmbilPampasan ambilPampasan = senaraiAmbilPampasan.get(i);
                                InfoAudit info = ambilPampasan.getInfoAudit();
                                info.setDiKemaskiniOleh(pengguna);
                                info.setTarikhKemaskini(new java.util.Date());
                                ambilPampasan.setInfoAudit(info);
//                            try {
//                                ambilPampasan.setDokDiambil(dokDiambil.get(i));
//                            } catch (Exception e) {
//                            }
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

//            if (permohonanPihak != null) {
//                permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
//            }
//            if (permohonanTuntutanKosList.size() > 0) {
//                for (int i = 0; i < permohonanTuntutanKosList.size(); i++) {
//                    PermohonanTuntutanKos ptk = permohonanTuntutanKosList.get(i);
//                    InfoAudit info = ptk.getInfoAudit();
//                    info.setDiKemaskiniOleh(pengguna);
//                    info.setTarikhKemaskini(new java.util.Date());
//                    ptk.setInfoAudit(info);
//                    try {
//                        ptk.setCatatan(catatan.get(i));
//                    } catch (Exception e) {
//                    }
//                    pengambilanService.simpanTuntutanKos(ptk);
//                }
//            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/melaka/bantahanmahkamah/RekodPembayaranCek_pop.jsp").addParameter("popup", "true");
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

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<String> getCatatan() {
        return catatan;
    }

    public void setCatatan(List<String> catatan) {
        this.catatan = catatan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List<PermohonanTuntutanKos> getPermohonanTuntutanKosList() {
        return permohonanTuntutanKosList;
    }

    public void setPermohonanTuntutanKosList(List<PermohonanTuntutanKos> permohonanTuntutanKosList) {
        this.permohonanTuntutanKosList = permohonanTuntutanKosList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<BigDecimal> getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(List<BigDecimal> amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public List<AmbilPampasan> getSenaraiAmbilPampasan() {
        return senaraiAmbilPampasan;
    }

    public void setSenaraiAmbilPampasan(List<AmbilPampasan> senaraiAmbilPampasan) {
        this.senaraiAmbilPampasan = senaraiAmbilPampasan;
    }

    public List<Date> getTarikhDokDiambil() {
        return tarikhDokDiambil;
    }

    public void setTarikhDokDiambil(List<Date> tarikhDokDiambil) {
        this.tarikhDokDiambil = tarikhDokDiambil;
    }

    public List<AmbilPampasan> getAmbilPampasanList() {
        return ambilPampasanList;
    }

    public void setAmbilPampasanList(List<AmbilPampasan> ambilPampasanList) {
        this.ambilPampasanList = ambilPampasanList;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
