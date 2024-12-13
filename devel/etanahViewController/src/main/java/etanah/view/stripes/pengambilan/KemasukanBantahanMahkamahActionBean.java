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
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakPendeposit;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.Pihak;
import etanah.service.PengambilanAduanService;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/carianIdHakmilikBantahanMahkamah")
public class KemasukanBantahanMahkamahActionBean extends AbleActionBean {

    @Inject
    PengambilanAduanService aduanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    private PerbicaraanService perbicaraanService;
    private static Logger logger = Logger.getLogger(KemasukanBantahanMahkamahActionBean.class);
    private Permohonan permohonan;
    private Permohonan permohonanSblm;
    private PermohonanPihak permohonanPihak;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPihakPendeposit permohonanPihakPendeposit;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String selectedIdSblm;
    private String selectedIdHakmilik;
    private String selectedIdPihak;
    private BigDecimal amaunTotal;
    private BigDecimal amaunTawarPampasan;
    private BigDecimal amaunTawarRosak;
    private BigDecimal amaunTambahPampasan;
    private List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
    private List<HakmilikPihakBerkepentingan> senaraiHPihak = new ArrayList<HakmilikPihakBerkepentingan>();
    private Pihak pihak;
    private List<PermohonanPihakPendeposit> permohonanPihakPendepositList = new ArrayList<PermohonanPihakPendeposit>();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/melaka/bantahanmahkamah/kemasukanBantahanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        calculate();
        return new JSP("pengambilan/melaka/bantahanmahkamah/nilaiPampasanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        logger.info("id pihak >> " + idPihak);
        if (idPermohonan != null) {
//            laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
//            permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan,hakmilik.getIdHakmilik(),idPihak);
            pihak = pihakDAO.findById(idPihak);
//            permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
            permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
            permohonanPihak = pengambilanService.findByIdSblmIdPihak(idPermohonan, idPihak);
            logger.info("maklumat pihak >> " + pihak.getNama());
            logger.info("nama pihak >> " + permohonanPihak.getPihak().getNama());
            logger.info("no mohon >> " + idPermohonan);
            if (permohonanPihakPendeposit == null) {
                logger.info("permohonanPihakPendeposit is null");

            } else {
                logger.info("permohonnan pihak >> " + permohonanPihakPendeposit.getNoAkaun());
            }
            if (pihak == null) {
            } else {
            }
        }
        //rehydrate();
        calculate();
        return new JSP("pengambilan/melaka/bantahanmahkamah/nilaiPampasanMahkamah.jsp").addParameter("tab", "true");
    }

    public void calculate() {
        logger.info("1");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();
        amaunTotal = BigDecimal.ZERO;
        permohonanPihakPendepositList = pengambilanService.findByPendepositList(idPermohonan);
        //long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        // permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
        List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        logger.info("senaraHakmilikPermohonan " + senaraiHakmilikPermohonan.size());
        if (senaraiHakmilikPermohonan.size() > 0) {
            logger.info("Loop senaraHakmilikPermohonan");
            hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
            logger.info("hakmilik " + hakmilik.getIdHakmilik());
            if (hakmilik != null) {
                logger.info("Loop hakmilik ");
                List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());

                logger.info("senaraiPermohonanPihak " + senaraiPermohonanPihak.size());
                if (senaraiPermohonanPihak.size() > 0) {
                    logger.info("Loop senaraiPermohonanPihak ");
//                    permohonanPihak = senaraiPermohonanPihak.get(0);
                    //  permohonanPihak = pengambilanService.findByIdSblmIdPihak(idHakmilik, idPihak);
                    //  logger.info("permohonanPihak " + permohonanPihak.getPihak().getNama());
                    if (permohonanPihak != null) {
                        logger.info("Loop permohonanPihak");
                        logger.info("permohonanPihak " + permohonanPihak.getPihak().getNama());
                        PermohonanMahkamah mahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
                        logger.info("mahkamah " + mahkamah.getPermohonanPihak().getPihak().getNama());
                        if (mahkamah != null) {
                            logger.info("Loop mahkamah");
                            amaunTawarPampasan = mahkamah.getAmnTawarPampasan();
                            amaunTawarRosak = mahkamah.getAmnTawarRosak();
                            amaunTambahPampasan = mahkamah.getTambahanPampasan();
                        }
                    }


                    if (permohonan.getPermohonanSebelum() != null) {
                        HakmilikPermohonan hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan(), hakmilik.getIdHakmilik());
                        PermohonanPihak pp = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(permohonan.getPermohonanSebelum().getIdPermohonan(), hakmilik.getIdHakmilik(), permohonanPihak.getPihak().getIdPihak());
                        if (hakmilikPermohonan != null) {
                            HakmilikPerbicaraan hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                            if (hakmilikPerbicaraan != null && pp != null) {
                                PerbicaraanKehadiran kehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                                if (kehadiran != null) {
                                    List<Penilaian> penilaianList = new ArrayList<Penilaian>();
                                    penilaianList = kehadiran.getSenaraiPenilaian();
                                    for (Penilaian penilaian : penilaianList) {
                                        amaunTotal = penilaian.getAmaun().add(amaunTotal);
                                    }
                                }
                            }
                        }

                    }

                }

            }
        }
    }

    public Resolution searchHakmilik() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        senaraiPermohonan = new ArrayList<Permohonan>();
        if (!idHakmilik.isEmpty()) {
            senaraiPermohonan = pengambilanService.getSeneraiPermohonanByidHakmilik(idHakmilik);
            if (senaraiPermohonan.isEmpty()) {
                senaraiHPihak = pengambilanService.getSenaraiHakmilikPihakBerkepentinganByidHakmilik(idHakmilik);
            } else {
                if (senaraiPermohonan.size() < 1) {
                    addSimpleError("Id Hakmilik Tidak Dijumpai");
                }
            }
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/kemasukanBantahanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution selectHakmilik() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        searchHakmilik();
        selectedIdSblm = getContext().getRequest().getParameter("selectedIdSblm");
        selectedIdHakmilik = getContext().getRequest().getParameter("selectedIdHakmilik");
        if (selectedIdHakmilik != null) {
            hakmilik = hakmilikDAO.findById(selectedIdHakmilik);
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/kemasukanBantahanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        logger.debug("idHakmilik : " + idHakmilik);
        searchHakmilik();

        selectedIdSblm = getContext().getRequest().getParameter("selectedIdSblm");
        logger.debug("selectedIdSblm " + selectedIdSblm);
        selectedIdHakmilik = getContext().getRequest().getParameter("selectedIdHakmilik");
        logger.debug("selectedIdHakmilik " + selectedIdHakmilik);
        if (selectedIdHakmilik != null) {
            logger.debug("selectedIdHakmilik x null");
            hakmilik = hakmilikDAO.findById(selectedIdHakmilik);
        }
        if (selectedIdPihak != null) {
            pihakDAO.findById(Long.valueOf(selectedIdPihak));
            Permohonan permohonanSblm1 = permohonanDAO.findById(selectedIdSblm);
            Pihak pihak = pihakDAO.findById(Long.valueOf(selectedIdPihak));

//            if (permohonanSblm1.getSenaraiPemohon().size() > 0) {
//                for (Pemohon pem : permohonanSblm1.getSenaraiPemohon()) {
            Pemohon pemohon = aduanService.findPemohonByPihak(permohonan.getIdPermohonan(), Long.valueOf(selectedIdPihak));
            if (pemohon == null) {
                pemohon = new Pemohon();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                pemohon.setInfoAudit(ia);
                pemohon.setPermohonan(permohonan);
                pemohon.setCawangan(permohonan.getCawangan());
                pemohon.setPihak(pihak);
                aduanService.simpanPemohon(pemohon);
            }
//                }
//            }

            if (selectedIdSblm != null) {
                permohonan.setPermohonanSebelum(permohonanSblm1);
                InfoAudit info = permohonan.getInfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                permohonan.setInfoAudit(info);
                pengambilanService.simpanIdMohonOSebab(permohonan);
            }
            if (hakmilik != null) {
                List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
//                for (HakmilikPermohonan hakmilikPermohonan : senaraiHakmilikPermohonan) {
//                    if (hakmilikPermohonan.getHakmilik().getIdHakmilik() != hakmilik.getIdHakmilik()) {
//                        pengambilanService.deleteHakmilikPermohonan(hakmilikPermohonan);
//                    }
//                }
                for (HakmilikPermohonan hakmilikPermohonan : senaraiHakmilikPermohonan) {
                    if (hakmilikPermohonan != null) {
                        InfoAudit ia = permohonan.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                        hakmilikPermohonan.setInfoAudit(ia);
                        hakmilikPermohonan.setPermohonan(permohonan);
                        hakmilikPermohonan.setHakmilik(hakmilik);
                        aduanService.simpanHP(hakmilikPermohonan);
                    }
                }

                HakmilikPermohonan hakmilikPermohonan;
                hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
//                if (hakmilikPermohonan == null) {
//                    hakmilikPermohonan = new HakmilikPermohonan();
//                    InfoAudit ia = new InfoAudit();
//                    ia.setDimasukOleh(peng);
//                    ia.setTarikhMasuk(new java.util.Date());
//                    hakmilikPermohonan.setInfoAudit(ia);
//                    hakmilikPermohonan.setPermohonan(permohonan);
//                    hakmilikPermohonan.setHakmilik(hakmilik);
//                    aduanService.simpanHP(hakmilikPermohonan);
//                }


                permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(permohonan.getIdPermohonan(), hakmilik.getIdHakmilik(), Long.valueOf(selectedIdPihak));
                InfoAudit info;
                if (permohonanPihak == null) {
                    permohonanPihak = new PermohonanPihak();
                    info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    permohonanPihak.setPermohonan(permohonan);
                    permohonanPihak.setHakmilik(hakmilik);
                    permohonanPihak.setPihak(pihakDAO.findById(Long.valueOf(selectedIdPihak)));
                    permohonanPihak.setCawangan(permohonan.getCawangan());
                    HakmilikPihakBerkepentingan hpb = pengambilanService.getHakmilikPihakByidPihak(hakmilik.getIdHakmilik(), Long.valueOf(selectedIdPihak));
                    permohonanPihak.setJenis(hpb.getJenis());
                    permohonanPihak.setInfoAudit(info);
                    aduanService.simpanPP(permohonanPihak);
                    addSimpleMessage("Maklumat telah berjaya disimpan");
                }
                List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
                for (PermohonanPihak pp : senaraiPermohonanPihak) {
                    if (pp.getPihak().getIdPihak() != permohonanPihak.getPihak().getIdPihak()) {
                        pengambilanService.deletePermohonanPihak(pp);
                    }
                }
            }
        }
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");

        return new JSP("pengambilan/melaka/bantahanmahkamah/kemasukanBantahanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMahkamah() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        if (idPermohonanPihak != null) {
            PermohonanMahkamah mahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(Long.valueOf(idPermohonanPihak));
            if (mahkamah == null) {
                mahkamah = new PermohonanMahkamah();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                mahkamah.setInfoAudit(infoAudit);
                mahkamah.setPermohonanPihak(permohonanPihakDAO.findById(Long.valueOf(idPermohonanPihak)));
            } else {
                InfoAudit infoAudit = mahkamah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                mahkamah.setInfoAudit(infoAudit);
            }

            mahkamah.setTambahanPampasan(amaunTambahPampasan);
            permohonanSupayaBantahanService.saveOrupdatePermohonanMahkamah(mahkamah);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        calculate();

        return new JSP("pengambilan/melaka/bantahanmahkamah/nilaiPampasanMahkamah.jsp").addParameter("tab", "true");
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

    public Permohonan getPermohonanSblm() {
        return permohonanSblm;
    }

    public void setPermohonanSblm(Permohonan permohonanSblm) {
        this.permohonanSblm = permohonanSblm;
    }

    public String getSelectedIdSblm() {
        return selectedIdSblm;
    }

    public void setSelectedIdSblm(String selectedIdSblm) {
        this.selectedIdSblm = selectedIdSblm;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getSelectedIdHakmilik() {
        return selectedIdHakmilik;
    }

    public void setSelectedIdHakmilik(String selectedIdHakmilik) {
        this.selectedIdHakmilik = selectedIdHakmilik;
    }

    public BigDecimal getAmaunTawarPampasan() {
        return amaunTawarPampasan;
    }

    public void setAmaunTawarPampasan(BigDecimal amaunTawarPampasan) {
        this.amaunTawarPampasan = amaunTawarPampasan;
    }

    public BigDecimal getAmaunTotal() {
        return amaunTotal;
    }

    public void setAmaunTotal(BigDecimal amaunTotal) {
        this.amaunTotal = amaunTotal;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getSelectedIdPihak() {
        return selectedIdPihak;
    }

    public void setSelectedIdPihak(String selectedIdPihak) {
        this.selectedIdPihak = selectedIdPihak;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public BigDecimal getAmaunTawarRosak() {
        return amaunTawarRosak;
    }

    public void setAmaunTawarRosak(BigDecimal amaunTawarRosak) {
        this.amaunTawarRosak = amaunTawarRosak;
    }

    public BigDecimal getAmaunTambahPampasan() {
        return amaunTambahPampasan;
    }

    public void setAmaunTambahPampasan(BigDecimal amaunTambahPampasan) {
        this.amaunTambahPampasan = amaunTambahPampasan;
    }

    public List<PermohonanPihakPendeposit> getPermohonanPihakPendepositList() {
        return permohonanPihakPendepositList;
    }

    public void setPermohonanPihakPendepositList(List<PermohonanPihakPendeposit> permohonanPihakPendepositList) {
        this.permohonanPihakPendepositList = permohonanPihakPendepositList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHPihak() {
        return senaraiHPihak;
    }

    public void setSenaraiHPihak(List<HakmilikPihakBerkepentingan> senaraiHPihak) {
        this.senaraiHPihak = senaraiHPihak;
    }

}
