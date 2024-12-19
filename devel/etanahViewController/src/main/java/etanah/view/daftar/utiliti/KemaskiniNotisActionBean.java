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
import etanah.service.RegService;
import etanah.service.common.EnforcementService;
import etanah.service.common.NotisService;
import etanah.view.daftar.UtilitiPerserahanSWSBSA;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ei
 */
@UrlBinding("/utiliti/kemaskini_Notis")
public class KemaskiniNotisActionBean extends AbleActionBean {
  
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
  RegService regservice;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  private etanah.Configuration conf;
  List<Notis> notis;
  private List<Notis> listNotis;
  private List<Permohonan> listPartial;
  private Permohonan permohonan;
  private Pengguna pengguna;
//  private Hakmilik hakmilik;
  private Notis senaraiNotis;
  private PermohonanRujukanLuar rujukLuar;
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
  private String tarikhNotis;
  private String tarikhLuput;
  private String tarikhHantar;
  private String tarikhTerima;
  private String tarikhTampal;
//  private String tempatTampal;
  private String namaTampal;
  private String catatan;
  private String kodNegeri;
  private String kodNotis;
  private String jenisNotis2;
  private boolean save = true;

  public boolean isSave() {
    return save;
  }

  public void setSave(boolean save) {
    this.save = save;
  }
  SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
  private static final Logger LOG = Logger.getLogger(KemaskiniNotisActionBean.class);
  private static boolean isDebug = LOG.isDebugEnabled();
  
  @DefaultHandler
  public Resolution showForm() {
    LOG.info("/* Kemaskini notis main */");
    if ("04".equals(conf.getProperty("kodNegeri"))) {
      kodNegeri = "Mlk";
    }
    if ("05".equals(conf.getProperty("kodNegeri"))) {
      kodNegeri = "N9";
    }
    return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/kemaskini_notis_main.jsp");
  }
  
  @Before(stages = {LifecycleStage.BindingAndValidation})
  public void rehydrate() {
    LOG.info("/* REHYDRATE */");
    pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    kodNotis = getContext().getRequest().getParameter("jenisNotis");
    idPermohonan = getContext().getRequest().getParameter("permohonan.idPermohonan");
    LOG.info("kod Notis : " + kodNotis);
    LOG.info("id mohon " + idPermohonan);
  }
  
  public Resolution checkPermohonan() {
    LOG.info("/* CHECK PERMOHONAN */");
    kodNotis = getContext().getRequest().getParameter("jenisNotis");
    idPermohonan = getContext().getRequest().getParameter("idPermohonan");
    idMohonNotis = null;
    
    if (StringUtils.isNotBlank(kodNotis)) { // SEARCH-BY-NOTIS
      LOG.info("--> kod Notis: " + kodNotis);
      listNotis = new ArrayList<Notis>();
      listNotis = notisService.findByKodNotis2(kodNotis);
      LOG.debug("--> listNotis.size(): " + listNotis.size());
      if (!listNotis.isEmpty()) {
        senaraiNotis = listNotis.get(0);
        recordCount = String.valueOf(listNotis.size());
      } else {
        addSimpleError("Notis Yang Dipilih Tiada Dalam Rekod");
        return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/kemaskini_notis_main.jsp");
      }
      return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/kemaskini_notis.jsp");
    } else {
      // SEARCH-BY-PERMOHONAN      
      if (permohonan == null) {
        addSimpleError("Sila masukkan ID Permohonan/Perserahan atau Jenis Notis");
        return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/kemaskini_notis_main.jsp");
      }
      idPermohonan = permohonan.getIdPermohonan();
      if (idPermohonan == null) {
        addSimpleError("Sila masukkan ID Permohonan/Perserahan atau Jenis Notis");
        return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/kemaskini_notis_main.jsp");
      }
      permohonan = permohonanDAO.findById(idPermohonan);
      if (permohonan == null) {
        LOG.info("Permohonan " + idPermohonan + " tidak dijumpai.");
        addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
        return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/kemaskini_notis_main.jsp");
      } else {
        listNotis = new ArrayList<Notis>();
        listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
        LOG.info("--> listNotis.size() :" + listNotis);
        if (!listNotis.isEmpty()) {
          senaraiNotis = listNotis.get(0);
          recordCount = String.valueOf(listNotis.size());
          idNotis = senaraiNotis.getIdNotis();
        }
//                idNotis = senaraiNotis.getIdNotis();
        idMohonNotis = idPermohonan;
        return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/kemaskini_notis.jsp");
      }
    }
  }
  
  public List<Permohonan> getPermohonanPartial(String idMohon) {
    /* get mohon partial */
    Session session = sessionProvider.get();
    Query q = session.createQuery("SELECT m FROM etanah.model.Permohonan m "
            + "WHERE m.idPermohonan LIKE :idPermohonan "
            + "ORDER BY m.idPermohonan");
//    q.setParameter("idPermohonan", "%"+idMohon);
    q.setString("idPermohonan", idMohon);
    return q.list();
  }
  
  public Resolution editNotisPopup() {
    /* use this to show pop-up */
    LOG.debug("/* EDIT NOTIS POPUP */");
    idNotis2 = getContext().getRequest().getParameter("idNotis");
    idPermohonan = getContext().getRequest().getParameter("idPermohonan");
    String idRuj = getContext().getRequest().getParameter("warta");
    String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    if (idNotis != null) {
      senaraiNotis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis2));

//Find In Table Mohon_ruj_Luar
      if (StringUtils.isNotBlank(idRuj)) {
        rujukLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRuj));
        
        if (rujukLuar != null) {
          if (rujukLuar.getTarikhTamat() != null) {
            tarikhLuput = dateF.format(rujukLuar.getTarikhTamat());
            LOG.debug("-- Tarikh Luput --> " + tarikhLuput);
          }
          if (rujukLuar.getTarikhKuatkuasa() != null) {
            String tarikhWarta = dateF.format(rujukLuar.getTarikhKuatkuasa());
            LOG.debug("-- tarik Warta --> " + tarikhWarta);
          }
        }
      }
      
      if (senaraiNotis != null) {
        LOG.info("---- Notis is NOT Null :" + senaraiNotis);
        
        if (senaraiNotis.getKodNotis() != null) {
            jenisNotis = senaraiNotis.getKodNotis().getKod();
            jenisNotis2 = senaraiNotis.getKodNotis().getNama();
        }
                
        if (senaraiNotis.getTarikhNotis() != null) {
          tarikhNotis = dateF.format(senaraiNotis.getTarikhNotis());
        }
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
//                if (senaraiNotis.getTempatTampal() != null) {
//                    tempatTampal = senaraiNotis.getTempatTampal();
//                }
//                if (senaraiNotis.getNamaTampal() != null) {
//                    namaTampal = senaraiNotis.getNamaTampal();
//                }
//                if (senaraiNotis.getCatatanPenerimaan() != null) {
//                    catatan = senaraiNotis.getCatatanPenerimaan();
//                }                
      }
    }
    setSave(Boolean.FALSE);
    return new JSP("/daftar/pembetulan/kemaskini_notis_popup.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
  }
  
  public Resolution editNotis() throws ParseException {
    /* use this to save or update notis from pop-up*/
    LOG.info("/* EDIT NOTIS */");
    LOG.info("id permohonan------------" + idPermohonan);
    permohonan = permohonanDAO.findById(idPermohonan);
    pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    idNotis2 = getContext().getRequest().getParameter("idNotis");
    String noWarta = getContext().getRequest().getParameter("nomborWarta"); // get id_warta from table notis
    String idRuj = getContext().getRequest().getParameter("noWarta"); // will be save in no_ruj in both table notis and mohon_ruj_luar
    jenisNotis = getContext().getRequest().getParameter("jenisNotis");
    tarikhNotis = getContext().getRequest().getParameter("tarikhNotis");
    tarikhLuput = getContext().getRequest().getParameter("tarikhLuput");
    tarikhHantar = getContext().getRequest().getParameter("tarikhHantar");
    penghantarNotis = getContext().getRequest().getParameter("penghantarNotis");
    statusPenyampaian = getContext().getRequest().getParameter("statusPenyampaian");
    caraPenghantaran = getContext().getRequest().getParameter("caraPenghantaran");
    tarikhTampal = getContext().getRequest().getParameter("tarikhTampal");
    String penerimaNotis = getContext().getRequest().getParameter("penerimaNotis");
    String tarikhWarta = getContext().getRequest().getParameter("tarikhWarta");
//        String tarikhWartaKerajaan = getContext().getRequest().getParameter("tarikhWartaKerajaan");        

    PenghantarNotis hantarNotis = new PenghantarNotis();
    if (StringUtils.isNotBlank(penghantarNotis)) {
      hantarNotis = penghantarNotisDAO.findById(Integer.parseInt(penghantarNotis));
    }
    
    KodStatusTerima kodTerima = new KodStatusTerima();
    kodTerima = kodStatusTerimaDAO.findById(statusPenyampaian);
    
    KodCaraPenghantaran caraHantar = new KodCaraPenghantaran();
    caraHantar = kodCaraPenghantaranDAO.findById(caraPenghantaran);
    
    KodKlasifikasi kodKlasifikasi = new KodKlasifikasi();
    kodKlasifikasi.setKod(1);
    
    KodNotis kod = new KodNotis();
    kod = kodNotisDAO.findById(jenisNotis);
    
    senaraiNotis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis2));
    
    dokumen = new Dokumen();
    dokumen.setIdDokumen(senaraiNotis.getDokumenNotis().getIdDokumen());
//        senaraiNotis.setPermohonan(permohonan);
//        senaraiNotis.setDokumenNotis(dokumen);
    if (kod != null) {
      senaraiNotis.setKodNotis(kod);
    }
    senaraiNotis.setJabatan(permohonan.getKodUrusan().getJabatan());
    senaraiNotis.setNoRujukan(idRuj);
    senaraiNotis.setKodStatusTerima(kodTerima);
    senaraiNotis.setKodCaraPenghantaran(caraHantar);
    senaraiNotis.setPenghantarNotis(hantarNotis);
    if (kodTerima != null) {
      senaraiNotis.setKodStatusTerima(kodTerima);
    }
    if (StringUtils.isNotBlank(penerimaNotis)) {
      senaraiNotis.setPenerimaNotis(penerimaNotis);
    } else {
      senaraiNotis.setPenerimaNotis(null);
    }
    
    if (StringUtils.isNotBlank(noWarta)) { // save in mohon_ruj_luar 
      rujukLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(noWarta));      
      if (rujukLuar != null) {
        rujukLuar.setNoRujukan(idRuj);
//        rujukLuar.setTarikhTamat(new java.util.Date());
        if (StringUtils.isNotBlank(tarikhLuput)) {
          rujukLuar.setTarikhTamat(dateF.parse(tarikhLuput));
        } else {
          rujukLuar.setTarikhTamat(null);
        }
        rujukLuar.setTarikhRujukan(new java.util.Date());
        if (StringUtils.isNotBlank(tarikhWarta)) {
          rujukLuar.setTarikhRujukan(dateF.parse(tarikhWarta)); // follow urusan > maklumat notis tab
        } else {
          rujukLuar.setTarikhRujukan(null);
        }
      }
      
      String idHakmilik = rujukLuar.getHakmilik().getIdHakmilik();
      HakmilikPermohonan mh = regservice.searchMohonHakmilikObject(idHakmilik, idPermohonan);
      /* need to save id_MH from mohon_hakmilik. 
       * vdoc will use this to get info from mohon_hakmilik */
      if (mh != null) {
        long idMH = mh.getId();
        HakmilikPermohonan id_mh = new HakmilikPermohonan();
        id_mh.setId(idMH);        
        senaraiNotis.setHakmilikPermohonan(id_mh); 
      }
    }
    
    senaraiNotis.setTarikhNotis(new java.util.Date()); // tarikh notis..
    if (tarikhNotis != null) {
      try {
        senaraiNotis.setTarikhNotis(dateF.parse(tarikhNotis));
      } catch (ParseException ex) {
        System.out.println("Got error" + ex);
      }
    }
    
    if (StringUtils.isNotBlank(tarikhTampal)) {
      senaraiNotis.setTarikhTampal(dateF.parse(tarikhTampal));
    } else {
      senaraiNotis.setTarikhTampal(null);
    }
    
    if (StringUtils.isNotBlank(tarikhHantar)) {
      senaraiNotis.setTarikhHantar(dateF.parse(tarikhHantar));
    } else {
      senaraiNotis.setTarikhHantar(null);
    }
    
    if (StringUtils.isNotBlank(tarikhTerima)) {
      senaraiNotis.setTarikhTerima(dateF.parse(tarikhTerima));
    } else {
      senaraiNotis.setTarikhTerima(null);
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
//    return checkPermohonan();
    setSave(Boolean.TRUE);
    return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/kemaskini_notis_popup.jsp");
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

//  public Hakmilik getHakmilik() {
//    return hakmilik;
//  }
//
//  public void setHakmilik(Hakmilik hakmilik) {
//    this.hakmilik = hakmilik;
//  }
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

//  public String getTempatTampal() {
//    return tempatTampal;
//  }
//
//  public void setTempatTampal(String tempatTampal) {
//    this.tempatTampal = tempatTampal;
//  }
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
  
  public PermohonanRujukanLuar getRujukLuar() {
    return rujukLuar;
  }
  
  public void setRujukLuar(PermohonanRujukanLuar rujukLuar) {
    this.rujukLuar = rujukLuar;
  }
  
  public String getTarikhLuput() {
    return tarikhLuput;
  }
  
  public void setTarikhLuput(String tarikhLuput) {
    this.tarikhLuput = tarikhLuput;
  }
  
  public String getTarikhNotis() {
    return tarikhNotis;
  }
  
  public void setTarikhNotis(String tarikhNotis) {
    this.tarikhNotis = tarikhNotis;
  }
  
  public String getJenisNotis2() {
    return jenisNotis2;
  }
  
  public void setJenisNotis2(String jenisNotis2) {
    this.jenisNotis2 = jenisNotis2;
  }
  
  public List<Permohonan> getListPartial() {
    return listPartial;
  }
  
  public void setListPartial(List<Permohonan> listPartial) {
    this.listPartial = listPartial;
  }
}
