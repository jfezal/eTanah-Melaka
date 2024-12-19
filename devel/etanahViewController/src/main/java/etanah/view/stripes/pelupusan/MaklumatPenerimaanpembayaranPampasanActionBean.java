/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.model.AmbilPampasan;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
//import etanah.model.Pengguna;
import etanah.model.KodCaraBayaran;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.BPelService;

import etanah.service.ConsentPtdService;
import etanah.service.EnforceService;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sitinorajar
 */
@UrlBinding("/pelupusan/maklumat_penerimaan_bayaran_pampasan")
public class MaklumatPenerimaanpembayaranPampasanActionBean extends AbleActionBean {

    private Hakmilik hakmilik;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    private AmbilPampasan ambilPampasan;
    private static final Logger LOG = Logger.getLogger(MaklumatPenerimaanpembayaranPampasanActionBean.class);
    private String idHakmilik;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat minitFormat = new SimpleDateFormat("mm");
    SimpleDateFormat jamFormat = new SimpleDateFormat("hh");
    SimpleDateFormat ampmFormat = new SimpleDateFormat("aaa");
    private String jam;
    private String minit;
    private String ampm;
    private Permohonan permohonan;
    private List<PermohonanPihak> permohonanPihakList;
    private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private Pengguna pguna;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String selectedpihak;
    private String idPermohonan;
    private Long pihakl;
    private List<HakmilikPerbicaraan> hakMilikPerbicaraanList;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private List<PerbicaraanKehadiran> perbicaraanKehadiranList;
    private List<AmbilPampasan> ambilPampasanList;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("::showForm");
        return new JSP("pelupusan/maklumat_penerimaan_bayaran_pampasan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            if (permohonan.getIdPermohonan() != null) {
                permohonanPihakList = new ArrayList<PermohonanPihak>();
                permohonanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
                List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
                hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                if (hmList.size() > 0) {
                    hakmilikPermohonan = hmList.get(0);
                }

                hakMilikPerbicaraanList = pelupusanService.findHakmilikPerbicaraanByIdMHList(hakmilikPermohonan.getId());
                if (hakMilikPerbicaraanList.size() > 0) {
                    hakmilikPerbicaraan = hakMilikPerbicaraanList.get(0);

                    perbicaraanKehadiranList = pelupusanService.findPerbicaraanKehadiranByIdBicaraList(hakmilikPerbicaraan.getIdPerbicaraan());

                }
                System.out.println("hakmilikbicara size : " + hakMilikPerbicaraanList.size());

                if (hakmilikPerbicaraan != null) {
                    System.out.println("id bicara : " + hakmilikPerbicaraan.getIdPerbicaraan());
                    for (int i = 0; i < permohonanPihakList.size(); i++) {
                        perbicaraanKehadiran = pelupusanService.getPerbicaraanKehadiranByidMPidBicara(permohonanPihakList.get(i).getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());

                    }

                    //for ambil_pampasan

                    for (int i = 0; i < permohonanPihakList.size(); i++) {
                        perbicaraanKehadiran = pelupusanService.getPerbicaraanKehadiranByidMPidBicara(permohonanPihakList.get(i).getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                        if (perbicaraanKehadiran != null) {
                            System.out.println("id hadir : " + perbicaraanKehadiran.getIdKehadiran());
                            ambilPampasan = pelupusanService.getAmbilPampasan(perbicaraanKehadiran.getIdKehadiran());

                            if (ambilPampasan != null) {
                                ambilPampasanList = new ArrayList<AmbilPampasan>();
                                System.out.println("id ambil pampasan : " + ambilPampasan.getIdAmbilPampasan());
                                ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                                ambilPampasanList.add(ambilPampasan);
                                LOG.debug("::ambilPampasanList::" + ambilPampasanList.size());
                            }



                        }
                    }

                }



            }
        }

    }

    public Resolution simpan() {

        LOG.info("::Simpan");

        hakMilikPerbicaraanList = pelupusanService.findHakmilikPerbicaraanByIdMHList(hakmilikPermohonan.getId());
        if (hakMilikPerbicaraanList.size() > 0) {
            hakmilikPerbicaraan = hakMilikPerbicaraanList.get(0);
        }

        InfoAudit ia = new InfoAudit();


        if (hakmilikPerbicaraan == null) {
            //add/save data
            hakmilikPerbicaraan = new HakmilikPerbicaraan();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());

        } else {
            //if list not null
            ia = hakmilikPerbicaraan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        hakmilikPerbicaraan.setCawangan(pguna.getKodCawangan());
        hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
        hakmilikPerbicaraan.setInfoAudit(ia);
        pelupusanService.saveHakmilikPerbicaraan(hakmilikPerbicaraan);


        //for ambil_bicara_hadir
        if (hakmilikPerbicaraan != null) {
            System.out.println("id bicara : " + hakmilikPerbicaraan.getIdPerbicaraan());
            for (int i = 0; i < permohonanPihakList.size(); i++) {
                perbicaraanKehadiran = pelupusanService.getPerbicaraanKehadiranByidMPidBicara(permohonanPihakList.get(i).getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());

                if (perbicaraanKehadiran == null) {
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    ia = new InfoAudit();
                    ia.setDimasukOleh(pguna);
                    ia.setTarikhMasuk(new java.util.Date());
                } else {
                    ia = perbicaraanKehadiran.getInfoAudit();
                    ia.setDiKemaskiniOleh(pguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                }
                perbicaraanKehadiran.setInfoAudit(ia);
                perbicaraanKehadiran.setPihak(permohonanPihakList.get(i));
                perbicaraanKehadiran.setCawangan(pguna.getKodCawangan());
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                pelupusanService.simpanAmbilBicaraHadir(perbicaraanKehadiran);
            }

        }

        //for ambil_pampasan
        if (hakmilikPerbicaraan != null) {
            for (int i = 0; i < permohonanPihakList.size(); i++) {
                perbicaraanKehadiran = pelupusanService.getPerbicaraanKehadiranByidMPidBicara(permohonanPihakList.get(i).getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());

                if (perbicaraanKehadiran != null) {
                    ambilPampasan = pelupusanService.getAmbilPampasan(perbicaraanKehadiran.getIdKehadiran());
                    LOG.debug("::ambilPampasan::" + ambilPampasan);
                    if (ambilPampasan == null) {
                        ambilPampasan = new AmbilPampasan();
                        ia = new InfoAudit();
                        ia.setDimasukOleh(pguna);
                        ia.setTarikhMasuk(new java.util.Date());
                    } else {
                        ia = ambilPampasan.getInfoAudit();
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                    String statusDokDiambilYa = getContext().getRequest().getParameter("dokDiambilY" + i);
                    String statusDokDiambilTidak = getContext().getRequest().getParameter("dokDiambilT" + i);
                    String tarikhDokDiambil = getContext().getRequest().getParameter("tarikhTerima" + i);

                    ambilPampasan.setInfoAudit(ia);
                    ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);

                    ambilPampasan.setJumTerimaPampasan(BigDecimal.ZERO);
                    KodCaraBayaran kodCaraBayaran = kodCaraBayaranDAO.findById("T");
                    ambilPampasan.setKodCaraBayaran(kodCaraBayaran);

                    try {
                        if (tarikhDokDiambil != null && !StringUtils.isBlank(tarikhDokDiambil)) {
                            System.out.println("tarikh dok diambil : " + tarikhDokDiambil);
                            ambilPampasan.setTarikhDokDiambil(sdf1.parse(tarikhDokDiambil));
                        }
                        System.out.println("statusDokDiambilYa :" + statusDokDiambilYa);
                        System.out.println("statusDokDiambilTidak :" + statusDokDiambilTidak);
                        if (statusDokDiambilYa != null && !StringUtils.isBlank(statusDokDiambilYa)) {
                            ambilPampasan.setDokDiambil(statusDokDiambilYa.charAt(0));
                        }

                        if (statusDokDiambilTidak != null && !StringUtils.isBlank(statusDokDiambilTidak)) {
                            ambilPampasan.setDokDiambil(statusDokDiambilTidak.charAt(0));
                        }

                    } catch (Exception ex) {
                        ex.getCause();
                        LOG.error(ex);
                    }


                    pelupusanService.simpanAmbilPampasan(ambilPampasan);
                }
            }
        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/maklumat_penerimaan_bayaran_pampasan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPenerimaanPembayaranPampasan() {

        String selectedpihak_ref = getContext().getRequest().getParameter("selectedpihak");
        String[] selectedpihak_refArray = selectedpihak_ref.split(",");
        for (int i = 0; i < selectedpihak_refArray.length; i++) {
            String[] selectedpihak_refArray_ref = selectedpihak_refArray[i].split("_");
            selectedpihak_ref = selectedpihak_refArray_ref[1].toString();
            permohonanPihak = permohonanPihakDAO.findById(Long.parseLong(selectedpihak_ref));
            String idValue = "0";
            if (selectedpihak_refArray_ref[0].toString().equals("") || selectedpihak_refArray_ref[0].toString().equals(" ") || selectedpihak_refArray_ref[0].toString() == null) {
                idValue = "0";
            } else {
                idValue = selectedpihak_refArray_ref[0].toString();
            }

            // permohonanPihak.setNilai(new BigDecimal(idValue));
            pelupusanService.simpanPihak(permohonanPihak.getPihak(), permohonanPihak);
            //  permohonanPihakDAO.saveOrUpdate(permohonanPihak);
        }

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            if (permohonan.getIdPermohonan() != null) {
                permohonanPihakList = new ArrayList<PermohonanPihak>();
                //pemohon = pelupusanService.findPemohon(idPermohonan);
                permohonanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
                List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
                hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                /*
                 * TEMPORARY SINCE LALULANG NOT CATER FOR MANY HAKMILIK
                 */
                if (hmList.size() > 0) {
                    hakmilikPermohonan = hmList.get(0);
                }
//                hakmilikPermohonan = pelupusanService.findHakmilikPermohonan_ref(idPermohonan);
                //permohonanRujukanLuar = pelupusanService.findPermohonanRujukanLuar_ref(idPermohonan);
                hakMilikPerbicaraanList = pelupusanService.findHakmilikPerbicaraanByIdMHList(hakmilikPermohonan.getId());
            }

            LOG.info("simpanPenerimaanPembayaranPampasan");
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setTarikhMasuk(new Date());
            infoAudit.setTarikhKemaskini(new java.util.Date());

            if (ambilPampasan == null) {
                ambilPampasan = new AmbilPampasan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = ambilPampasan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            ambilPampasan.setInfoAudit(infoAudit);
        }



//        LOG.info("simpanPenerimaanPembayaranPampasan");
//        InfoAudit infoAudit = new InfoAudit();
//        infoAudit.setTarikhMasuk(new Date());
//        infoAudit.setTarikhKemaskini(new java.util.Date());
//
//        if (hakMilikPerbicaraanList.isEmpty()) {
//            //add/save data
//
//        } else {
//            //if list not null
//            for (int i = 0; i < hakMilikPerbicaraanList.size(); i++) {
//            }
//        }
//
//
//        if (ambilPampasan == null) {
//            ambilPampasan = new AmbilPampasan();
//            infoAudit = new InfoAudit();
//            infoAudit.setDimasukOleh(pguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//        } else {
//            infoAudit = ambilPampasan.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(pguna);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//        }
//        ambilPampasan.setInfoAudit(infoAudit);
//
//        String tarikh = getContext().getRequest().getParameter("tarikh");
//        jam = getContext().getRequest().getParameter("jam");
//        minit = getContext().getRequest().getParameter("minit");
//        ampm = getContext().getRequest().getParameter("ampm");
//        tarikh = tarikh + " " + jam + ":" + minit + " " + ampm;
//        //LOG.info("tarikh : " + tarikh);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/maklumat_penerimaan_bayaran_pampasan.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
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

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Long getPihakl() {
        return pihakl;
    }

    public void setPihakl(Long pihakl) {
        this.pihakl = pihakl;
    }

    public AmbilPampasan getAmbilPampasan() {
        return ambilPampasan;
    }

    public void setAmbilPampasan(AmbilPampasan ambilPampasan) {
        this.ambilPampasan = ambilPampasan;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public List<HakmilikPerbicaraan> getHakMilikPerbicaraanList() {
        return hakMilikPerbicaraanList;
    }

    public void setHakMilikPerbicaraanList(List<HakmilikPerbicaraan> hakMilikPerbicaraanList) {
        this.hakMilikPerbicaraanList = hakMilikPerbicaraanList;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
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

    public String getSelectedpihak() {
        return selectedpihak;
    }

    public void setSelectedpihak(String selectedpihak) {
        this.selectedpihak = selectedpihak;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList() {
        return perbicaraanKehadiranList;
    }

    public void setPerbicaraanKehadiranList(List<PerbicaraanKehadiran> perbicaraanKehadiranList) {
        this.perbicaraanKehadiranList = perbicaraanKehadiranList;
    }

    public List<AmbilPampasan> getAmbilPampasanList() {
        return ambilPampasanList;
    }

    public void setAmbilPampasanList(List<AmbilPampasan> ambilPampasanList) {
        this.ambilPampasanList = ambilPampasanList;
    }
}
