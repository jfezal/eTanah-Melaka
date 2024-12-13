/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.common.EnforcementService;
import etanah.service.common.NotisService;
import etanah.view.daftar.UtilitiPerserahanSWSBSA;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.mapping.Collection;

/**
 *
 * @author ei
 */
@UrlBinding("/utiliti/pertanyaanNotis")
public class NotisPertanyaanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    NotisService notisService;
    @Inject
    NotisDAO notisDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private etanah.Configuration conf;
    List<Notis> notis;
    private List<Notis> listNotis;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private Hakmilik hakmilik;
    private Notis senaraiNotis;
    private Dokumen dokumen;
    private Long idNotis;
    private String idPermohonan;
    private String idMohonNotis;
    private String recordCount;
    private String idNotis2;
    private String jenisNotis;
    private String penghantarNotis;
    private String statusPenyampaian;
    private String caraPenghantaran;
    private String tarikhHantar;
    private String tarikhTerima;
    private String tarikhTampal;
    private String tempatTampal;
    private String namaTampal;
    private String catatan;
    private String kodNegeri;
    private String kodNotis;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(NotisPertanyaanActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution showForm() {
        LOG.debug("--- pertanyaan notis main ---");

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "Mlk";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "N9";
        }
        LOG.info("--- kod negeri= " + kodNegeri);
        return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/notis_pertanyaan_main.jsp");
    }

    public Resolution checkPermohonan() {
//        --- search-by jenis notis ::START:: ---        
        kodNotis = getContext().getRequest().getParameter("jenisNotis");
        idMohonNotis = null;
        LOG.debug("--- kod Notis -->: " + kodNotis);
        if (StringUtils.isNotBlank(kodNotis)) {
            LOG.debug("--- kod Notis is not Blank --: ");
            listNotis = new ArrayList<Notis>();
            listNotis = notisService.findByKodNotis(kodNotis);
            LOG.debug("--- Size list notis found ---> :" + listNotis.size());
            if (listNotis.size() != 0) {
                senaraiNotis = listNotis.get(0);
                recordCount = String.valueOf(listNotis.size());
                LOG.debug("--- nama notis ---> :" + senaraiNotis.getKodNotis().getNama());
            } else {
                addSimpleError("Notis Yang Dipilih Tiada Dalam Rekod");
                return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/notis_pertanyaan_main.jsp");
            }

            return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pertanyaan_notis.jsp");
        } else {

//        --- search-by id permohon ::START:: ---        
            if (permohonan == null) {
                addSimpleError("Sila masukkan ID Permohonan/Perserahan atau Jenis Notis");
                return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/notis_pertanyaan_main.jsp");
            }

            idPermohonan = permohonan.getIdPermohonan();
            LOG.debug("--- id Permohon ---> " + idPermohonan);
            if (idPermohonan == null) {
                addSimpleError("Sila masukkan ID Permohonan/Perserahan atau Jenis Notis");
                return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/notis_pertanyaan_main.jsp");
            }

            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan == null) {
//                listNotis = Collections.EMPTY_LIST;
                System.out.print("Permohonan " + idPermohonan + " tidak dijumpai.");
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
                return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/notis_pertanyaan_main.jsp");
            } else {
                listNotis = new ArrayList<Notis>();
                listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
                LOG.debug("--- listNotis.size() :" + listNotis.size());
                if (listNotis.size() != 0) {
                    senaraiNotis = listNotis.get(0);
                    recordCount = String.valueOf(listNotis.size());
                }
                else {
                    addSimpleError("Permohonan Tiada Dalam Rekod");
                    return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/notis_pertanyaan_main.jsp");    
                }
                idNotis = senaraiNotis.getIdNotis();
                idMohonNotis = idPermohonan;
                return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pertanyaan_notis.jsp");
            }
        }
//            addSimpleError("Permohonan tidak dijumpai.");
//            return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/kemaskini_notis_main.jsp");
    }

    public Resolution editNotisPopup() {
        LOG.debug("--- edit popup ---");
        idNotis2 = getContext().getRequest().getParameter("idNotis");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("---- idPermohonan --> :" + idPermohonan);
        if (idNotis != null) {
            LOG.info("idNotis :" + idNotis);
            senaraiNotis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis2));
            if (senaraiNotis != null) {
                LOG.info("---- Notis is NOT Null :" + senaraiNotis);
                jenisNotis = senaraiNotis.getKodNotis().getKod();
                if (senaraiNotis.getPenghantarNotis() != null) {
                    penghantarNotis = Integer.toString(senaraiNotis.getPenghantarNotis().getIdPenghantarNotis());
                }
                if (senaraiNotis.getKodStatusTerima() != null) {
                    statusPenyampaian = senaraiNotis.getKodStatusTerima().getKod();
                }
                if (senaraiNotis.getKodCaraPenghantaran() != null) {
                    caraPenghantaran = senaraiNotis.getKodCaraPenghantaran().getKod();
                }
                if (senaraiNotis.getTarikhHantar() != null) {
                    tarikhHantar = dateF.format(senaraiNotis.getTarikhHantar());
                }
                if (senaraiNotis.getTarikhTerima() != null) {
                    tarikhTerima = dateF.format(senaraiNotis.getTarikhTerima());
                }
                if (senaraiNotis.getTarikhTampal() != null) {
                    tarikhTampal = dateF.format(senaraiNotis.getTarikhTampal());
                }
                tempatTampal = senaraiNotis.getTempatTampal();
                namaTampal = senaraiNotis.getNamaTampal();
                catatan = senaraiNotis.getCatatanPenerimaan();
            }
        }
        return new JSP("/daftar/pembetulan/kemaskini_notis_popup.jsp").addParameter("popup", "true");
    }

    public Resolution editNotis() throws ParseException {
        LOG.debug("------------edit Notis 2-------------");
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");        
        System.out.println("id permohonan------------" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("id mohon yaw----------" + permohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idNotis2 = getContext().getRequest().getParameter("idNotis");
        jenisNotis = getContext().getRequest().getParameter("jenisNotis");
        penghantarNotis = getContext().getRequest().getParameter("penghantarNotis");
        statusPenyampaian = getContext().getRequest().getParameter("statusPenyampaian");
        caraPenghantaran = getContext().getRequest().getParameter("caraPenghantaran");

        KodStatusTerima kodTerima = new KodStatusTerima();
        kodTerima = kodStatusTerimaDAO.findById(statusPenyampaian);

        KodCaraPenghantaran caraHantar = new KodCaraPenghantaran();
        caraHantar = kodCaraPenghantaranDAO.findById(caraPenghantaran);

        PenghantarNotis hantarNotis = new PenghantarNotis();
        hantarNotis = penghantarNotisDAO.findById(Integer.parseInt(penghantarNotis));

        KodKlasifikasi kodKlasifikasi = new KodKlasifikasi();
        kodKlasifikasi.setKod(1);

        KodNotis kod = new KodNotis();
        kod = kodNotisDAO.findById(jenisNotis);

        senaraiNotis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis2));
//        notis = notisDAO.findById(Long.parseLong(idNotis));

        dokumen = new Dokumen();
        dokumen.setIdDokumen(senaraiNotis.getDokumenNotis().getIdDokumen());
        senaraiNotis.setPermohonan(permohonan);
        senaraiNotis.setDokumenNotis(dokumen);
        senaraiNotis.setKodNotis(kod);
        senaraiNotis.setKodStatusTerima(kodTerima);
        senaraiNotis.setJabatan(permohonan.getKodUrusan().getJabatan());
        senaraiNotis.setKodStatusTerima(kodTerima);
        senaraiNotis.setKodCaraPenghantaran(caraHantar);
        senaraiNotis.setPenghantarNotis(hantarNotis);
        senaraiNotis.setCatatanPenerimaan(catatan);
        senaraiNotis.setNamaTampal(namaTampal);
        senaraiNotis.setTempatTampal(tempatTampal);
        senaraiNotis.setTarikhTampal(new java.util.Date());
        senaraiNotis.setTarikhNotis(new java.util.Date());
        if (tarikhTampal != null) {
            try {
                senaraiNotis.setTarikhTampal(dateF.parse(tarikhTampal));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }
        if (tarikhHantar != null) {
            try {
                senaraiNotis.setTarikhHantar(dateF.parse(tarikhHantar));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }
        if (tarikhTerima != null) {
            try {
                senaraiNotis.setTarikhTerima(dateF.parse(tarikhTerima));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }

        InfoAudit ia = senaraiNotis.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            senaraiNotis.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanNotisPenguatkuasaan(senaraiNotis);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
//        return showForm();
        return checkPermohonan();
    }

//------ getter and satter
    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Notis> getNotis() {
        return notis;
    }

    public void setNotis(List<Notis> notis) {
        this.notis = notis;
    }

    public Notis getSenaraiNotis() {
        return senaraiNotis;
    }

    public void setSenaraiNotis(Notis senaraiNotis) {
        this.senaraiNotis = senaraiNotis;
    }

    public Long getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(Long idNotis) {
        this.idNotis = idNotis;
    }

    public String getIdMohonNotis() {
        return idMohonNotis;
    }

    public void setIdMohonNotis(String idMohonNotis) {
        this.idMohonNotis = idMohonNotis;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNamaTampal() {
        return namaTampal;
    }

    public void setNamaTampal(String namaTampal) {
        this.namaTampal = namaTampal;
    }

    public String getTempatTampal() {
        return tempatTampal;
    }

    public void setTempatTampal(String tempatTampal) {
        this.tempatTampal = tempatTampal;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public String getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(String tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getCaraPenghantaran() {
        return caraPenghantaran;
    }

    public void setCaraPenghantaran(String caraPenghantaran) {
        this.caraPenghantaran = caraPenghantaran;
    }

    public String getStatusPenyampaian() {
        return statusPenyampaian;
    }

    public void setStatusPenyampaian(String statusPenyampaian) {
        this.statusPenyampaian = statusPenyampaian;
    }

    public String getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(String penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public String getJenisNotis() {
        return jenisNotis;
    }

    public void setJenisNotis(String jenisNotis) {
        this.jenisNotis = jenisNotis;
    }

    public String getIdNotis2() {
        return idNotis2;
    }

    public void setIdNotis2(String idNotis2) {
        this.idNotis2 = idNotis2;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKodNotis() {
        return kodNotis;
    }

    public void setKodNotis(String kodNotis) {
        this.kodNotis = kodNotis;
    }
}
