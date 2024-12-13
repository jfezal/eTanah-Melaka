/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.JSP;
import etanah.view.kaunter.*;
import net.sourceforge.stripes.action.ForwardResolution;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.WakilKuasaPemberiDAO;
import etanah.model.*;
import etanah.service.common.*;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.service.daftar.PembetulanService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Popup;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author ei
 */
@UrlBinding("/daftar/pembetulanPertanyaanSBSWSA")
public class UtilitiBetulPerserahanSWSBSA extends PermohonanKaunter {

  @Inject
  PermohonanDAO permohonanDAO;
  @Inject
  FasaPermohonanService fService;
  @Inject
  PendaftaranSuratKuasaService suratkuasaService;
  @Inject
  PermohonanService permohonanService;
  List<WakilKuasa> wKuasa;
  @Inject
  PembetulanService pService;
  @Inject
  HakmilikDAO hakmilikDAO;
  @Inject
  WakilKuasaPemberiDAO wakilkuasapemberiDAO;
  @Inject
  HakmilikService hakmilikService;
  @Inject
  PihakService pihakService;
  @Inject
  private HakmilikPermohonanService hakmilikPermohonanService;
  private Permohonan permohonan;
  private WakilKuasaPenerima wakilKuasaPenerima;
  private Pihak pihak;
  private WakilKuasaPemberi wakilKuasaPemberi;
  private Pengguna pengguna;
  private Hakmilik hakmilik;
  private String kodUrusan;
  private String idHakmilik;
  private String hakmilikLama;
  private String idPemberi;
  private String namaPemberi;
  private String idPenerima;
  private String idPermohonan;
  private String status;
  private String idMohon;
  private String pemberiBaru;
  private String penerimaBaru;
  private String noICBaru;
  private String jenisPengenalanBaru;
  private String kod;
  private List<WakilKuasaPenerima> listWakilKuasaPenerima;
  private List<WakilKuasaPemberi> listWakilKuasaPemberi;
  private List<WakilHakmilik> listWakilHakmilik;
  private List<Permohonan> listPermohonan;
  private static final Logger LOG = Logger.getLogger(UtilitiPerserahanSWSBSA.class);
  private static boolean isDebug = LOG.isDebugEnabled();

  @DefaultHandler
  public Resolution showForm() {
    return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_main_swsbsa.jsp");
  }

  @Before(stages = {LifecycleStage.BindingAndValidation})
  @Override
  public void rehydrate() {
    LOG.debug("--- rehydrate() ---");
    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
    String id = getContext().getRequest().getParameter("idPermohonan");
    if (StringUtils.isNotBlank(id)) {
        idPermohonan = id;
    }
    if (idPermohonan != null) {
      permohonan = permohonanDAO.findById(idPermohonan);
    }
  }

  public Resolution checkPermohonan() {
    if (permohonan == null) {
      addSimpleError("Sila masukkan ID Permohonan/Perserahan");
      return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_main_swsbsa.jsp");
    }
    idPermohonan = permohonan.getIdPermohonan();
    if (idPermohonan == null) {
      addSimpleError("Sila masukkan ID Permohonan/Perserahan");
      return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_main_swsbsa.jsp");
    }

    permohonan = permohonanDAO.findById(idPermohonan);
    if (permohonan == null) {
      System.out.print("Permohonan " + idPermohonan + " tidak dijumpai.");
      addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
      return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_main_swsbsa.jsp");
    } else {
      wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
      for (WakilKuasa wk : wKuasa) {
        listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
      }
      listWakilKuasaPenerima = pService.findWakilKuasaPList(idPermohonan);
      kodUrusan = permohonan.getKodUrusan().getKod();
      status = permohonan.getStatus();

      if ("SW".equals(kodUrusan) || "SA".equals(kodUrusan) || "SB".equals(kodUrusan)) {
        if ("TK".equals(status)) {
          addSimpleError("Permohonan telah dibatalkan");
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_perserahan_swsba.jsp");
      }
      addSimpleError("Permohonan tidak dijumpai.");
      return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_main_swsbsa.jsp");
    }
  }

  public Resolution kemaskiniHakmilik() {
    hakmilik = hakmilikDAO.findById(idHakmilik);
    return new JSP("/daftar/pembetulan/kemaskini_hakmilik_popup.jsp").addParameter("popup", "true");
  }

  public Resolution kemaskiniPemberi() {
    long idB = Long.parseLong(getContext().getRequest().getParameter("idPberi")); // get wakil_kuasa_pberi pk
    idPemberi = String.valueOf(idB);
    wakilKuasaPemberi = suratkuasaService.findWKPemberibyIdPberi(idB);  
//    wakilKuasaPemberi = suratkuasaService.findWakilPemberibyIdPermohonan(idPemberi);
    wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
    for (WakilKuasa wk : wKuasa) {
      listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
    }    
    return new JSP("/daftar/pembetulan/kemaskini_hakmilik_popup.jsp").addParameter("popup", "true");
  }

  public Resolution kemaskiniPenerima() {
    idPenerima = getContext().getRequest().getParameter("idPenerima");
    long idP = Long.parseLong(getContext().getRequest().getParameter("idPnerima")); // get wakil_kuasa_pnerima pk
    wakilKuasaPenerima = suratkuasaService.findWKPnerimabyIdPnerima(idP);
    listWakilKuasaPenerima = pService.findWakilKuasaPList(idPenerima);
    idPenerima = String.valueOf(idP);
    return new JSP("/daftar/pembetulan/kemaskini_hakmilik_popup.jsp").addParameter("popup", "true");
  }

  public Resolution batalStatus() {
    permohonan = permohonanDAO.findById(idPermohonan);
    status = permohonan.getStatus();
    if (status != null) {
      if (status.equalsIgnoreCase("SL")
              || status.equalsIgnoreCase("TA")
              || status.equalsIgnoreCase("TS")
              || status.equalsIgnoreCase("AK")) {
//                Permohonan p = new Permohonan();
//                p.setIdPermohonan(idPermohonan); 
        permohonan.setStatus("TK");
        permohonanService.saveOrUpdate(permohonan);

        addSimpleMessage("Status Telah Dibatalkan.");
      } else {
        addSimpleMessage("Status tidak dapat dibatalkan.");
      }
    }
    return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_perserahan_swsba.jsp");
  }

  public Resolution saveHakmilik() {
    hakmilikLama = getContext().getRequest().getParameter("idhakmilik");
    String[] senaraiHakmilikBaru = getContext().getRequest().getParameterValues("idHakmilikBaru");
    idPermohonan = getContext().getRequest().getParameter("idPermohonan");
    permohonan = permohonanDAO.findById(idPermohonan);

    List<HakmilikPermohonan> senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
    List<HakmilikPermohonan> senaraiHakmilikTmp = new ArrayList<HakmilikPermohonan>();
    InfoAudit ia = new InfoAudit();

    LOG.debug("size = " + senaraiHakmilikBaru.length);
    if (senaraiHakmilikBaru.length > 0) {
      int i = 0;
      for (String id : senaraiHakmilikBaru) {
        if (StringUtils.isBlank(id)) {
          continue;
        }
        Hakmilik hakmilik = hakmilikDAO.findById(id);
        if (hakmilik == null) {
          continue;
        }

        HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(i);
        ia = hp.getInfoAudit();
        ia.setTarikhKemaskini(new Date());
        ia.setDiKemaskiniOleh(pengguna);
        hp.setHakmilik(hakmilik);
        hp.setInfoAudit(ia);
        senaraiHakmilikTmp.add(hp);
        i++;
      }
      hakmilikPermohonanService.saveHakmilikPermohonan(senaraiHakmilikTmp);
    }
    return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_main_swsbsa.jsp");
  }

  public Resolution savePemberi() {
//    wakilKuasaPemberi = suratkuasaService.findWakilPemberibyIdPermohonan(idPemberi);
      wakilKuasaPemberi = suratkuasaService.findWKPemberibyIdPberi(Long.valueOf(idPemberi));
    String idPihak = String.valueOf(wakilKuasaPemberi.getPihak().getIdPihak());
    Pihak pk = pihakService.findById(idPihak);
    if (StringUtils.isNotBlank(pemberiBaru)) {
      pk.setNama(pemberiBaru);
      suratkuasaService.savePihak(pk);
    }

    if (StringUtils.isNotBlank(jenisPengenalanBaru)) {
      KodJenisPengenalan kjp = new KodJenisPengenalan(jenisPengenalanBaru);
      pk.setJenisPengenalan(kjp);
      suratkuasaService.savePihak(pk);
    }

    if (StringUtils.isNotBlank(noICBaru)) {
      pk.setNoPengenalan(noICBaru);
      suratkuasaService.savePihak(pk);
    }   

      wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
      
      for (WakilKuasa wk : wKuasa) {
//          listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
          listWakilKuasaPemberi = wk.getSenaraiPemberi();    
          for (WakilKuasaPemberi wkp : listWakilKuasaPemberi) {
              if (wkp.getPihak().getJenisPengenalan() != null) {
                  LOG.debug("pihak , " + wkp.getPihak().getJenisPengenalan().getNama());
              }
          }
      }

      listWakilKuasaPenerima = pService.findWakilKuasaPList(idPermohonan);
      if (StringUtils.isNotBlank(idPermohonan)){
          permohonan = permohonanDAO.findById(idPermohonan);
            kodUrusan = permohonan.getKodUrusan().getKod();
            status = permohonan.getStatus();

            if ("SW".equals(kodUrusan) || "SA".equals(kodUrusan) || "SB".equals(kodUrusan)) {
                if ("TK".equals(status)) {
                    addSimpleError("Permohonan telah dibatalkan");
                }
      //          return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_perserahan_swsba.jsp");
            }   
      }
//    return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_main_swsbsa.jsp");
    return new RedirectResolution(UtilitiBetulPerserahanSWSBSA.class, "checkPermohonan")
              .addParameter("popup", "true").addParameter("idPermohonan", idPermohonan);
    
  }

  public Resolution savePenerima() {
    if (StringUtils.isBlank(penerimaBaru) && StringUtils.isBlank(jenisPengenalanBaru) && StringUtils.isBlank(noICBaru)) {
      addSimpleError("Sila Masukkan Maklumat Baru.");
      return null;
    } else {
      if (StringUtils.isNotBlank(penerimaBaru)) {
//        wakilKuasaPenerima = suratkuasaService.findWakilPenerimabyIdPermohonan(idPenerima);
//        WakilKuasaPenerima wkp = suratkuasaService.findWakilPenerimabyIdPermohonan(idPenerima);
          wakilKuasaPenerima = suratkuasaService.findWKPnerimabyIdPnerima(Long.valueOf(idPenerima));
        WakilKuasaPenerima wkp = suratkuasaService.findWKPnerimabyIdPnerima(Long.valueOf(idPenerima));
        wkp.setNama(penerimaBaru);
        suratkuasaService.saveWakilKuasaPenerima(wkp);
      }

      if (StringUtils.isNotBlank(jenisPengenalanBaru)) {
//        WakilKuasaPenerima wkp = suratkuasaService.findWakilPenerimabyIdPermohonan(idPenerima);
        WakilKuasaPenerima wkp = suratkuasaService.findWKPnerimabyIdPnerima(Long.valueOf(idPenerima));
        KodJenisPengenalan kjp = new KodJenisPengenalan(jenisPengenalanBaru);
        wkp.setJenisPengenalan(kjp);
        suratkuasaService.saveWakilKuasaPenerima(wkp);
      }

      if (StringUtils.isNotBlank(noICBaru)) {
//        WakilKuasaPenerima wkp = suratkuasaService.findWakilPenerimabyIdPermohonan(idPenerima);
        WakilKuasaPenerima wkp = suratkuasaService.findWKPnerimabyIdPnerima(Long.valueOf(idPenerima));
        wkp.setNoPengenalan(noICBaru);
        suratkuasaService.saveWakilKuasaPenerima(wkp);
      }
//      return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/kemaskini_hakmilik_popup.jsp");
      
      
      wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
      
      for (WakilKuasa wk : wKuasa) {
//          listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
          listWakilKuasaPemberi = wk.getSenaraiPemberi();    
          for (WakilKuasaPemberi wkp : listWakilKuasaPemberi) {
              if (wkp.getPihak().getJenisPengenalan() != null) {
                  LOG.debug("pihak , " + wkp.getPihak().getJenisPengenalan().getNama());
              }
          }
      }

      listWakilKuasaPenerima = pService.findWakilKuasaPList(idPermohonan);
      if (StringUtils.isNotBlank(idPermohonan)){
          permohonan = permohonanDAO.findById(idPermohonan);
            kodUrusan = permohonan.getKodUrusan().getKod();
            status = permohonan.getStatus();

            if ("SW".equals(kodUrusan) || "SA".equals(kodUrusan) || "SB".equals(kodUrusan)) {
                if ("TK".equals(status)) {
                    addSimpleError("Permohonan telah dibatalkan");
                }
      //          return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_perserahan_swsba.jsp");
            }   
      }
      
      
//      return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/pembetul_perserahan_swsba.jsp")
//            .addParameter("popup", "true");
      
      return new RedirectResolution(UtilitiBetulPerserahanSWSBSA.class, "checkPermohonan")
              .addParameter("popup", "true").addParameter("idPermohonan", idPermohonan);
    }
  }

  public String getIdMohon() {
    return idMohon;
  }

  public void setIdMohon(String idMohon) {
    this.idMohon = idMohon;
  }

  public List<WakilKuasaPemberi> getListWakilKuasaPemberi() {
    return listWakilKuasaPemberi;
  }

  public void setListWakilKuasaPemberi(List<WakilKuasaPemberi> listWakilKuasaPemberi) {
    this.listWakilKuasaPemberi = listWakilKuasaPemberi;
  }

  public List<WakilKuasa> getwKuasa() {
    return wKuasa;
  }

  public void setwKuasa(List<WakilKuasa> wKuasa) {
    this.wKuasa = wKuasa;
  }

  public List<WakilKuasaPenerima> getListWakilKuasaPenerima() {
    return listWakilKuasaPenerima;
  }

  public void setListWakilKuasaPenerima(List<WakilKuasaPenerima> listWakilKuasaPenerima) {
    this.listWakilKuasaPenerima = listWakilKuasaPenerima;
  }

  public String getKodUrusan() {
    return kodUrusan;
  }

  public void setKodUrusan(String kodUrusan) {
    this.kodUrusan = kodUrusan;
  }

  public void setPermohonan(Permohonan p) {
    this.permohonan = p;
  }

  public Permohonan getPermohonan() {
    return permohonan;
  }

  public void setWakilKuasaPenerima(WakilKuasaPenerima wkp) {
    this.wakilKuasaPenerima = wkp;
  }

  public WakilKuasaPenerima getWakilKuasaPenerima() {
    return wakilKuasaPenerima;
  }

  public void setWakilKuasaPemberi(WakilKuasaPemberi wkb) {
    this.wakilKuasaPemberi = wkb;
  }

  public WakilKuasaPemberi getWakilKuasaPemberi() {
    return wakilKuasaPemberi;
  }

  public void seHakmilik(Hakmilik hm) {
    this.hakmilik = hm;
  }

  public Hakmilik getHakmilik() {
    return hakmilik;
  }

  public String getIdHakmilik() {
    return idHakmilik;
  }

  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;
  }

  public String getHakmilikLama() {
    return hakmilikLama;
  }

  public void setHakmilikLama(String hakmilikLama) {
    this.hakmilikLama = hakmilikLama;
  }

  public String getIdPemberi() {
    return idPemberi;
  }

  public void setIdPemberi(String idPemberi) {
    this.idPemberi = idPemberi;
  }

  public String getIdPenerima() {
    return idPenerima;
  }

  public void setIdPenerima(String idPenerima) {
    this.idPenerima = idPenerima;
  }

  public String getNamaPemberi() {
    return namaPemberi;
  }

  public void setNamaPemberi(String namaPemberi) {
    this.namaPemberi = namaPemberi;
  }

  public String getIdPermohonan() {
    return idPermohonan;
  }

  public void setIdPermohonan(String idPermohonan) {
    this.idPermohonan = idPermohonan;
  }

  public Pihak getPihak() {
    return pihak;
  }

  public void setPihak(Pihak pihak) {
    this.pihak = pihak;
  }

  public String getJenisPengenalanBaru() {
    return jenisPengenalanBaru;
  }

  public void setJenisPengenalanBaru(String jenisPengenalanBaru) {
    this.jenisPengenalanBaru = jenisPengenalanBaru;
  }

  public String getKod() {
    return kod;
  }

  public void setKod(String kod) {
    this.kod = kod;
  }

  public String getNoICBaru() {
    return noICBaru;
  }

  public void setNoICBaru(String noICBaru) {
    this.noICBaru = noICBaru;
  }

  public String getPenerimaBaru() {
    return penerimaBaru;
  }

  public void setPenerimaBaru(String penerimaBaru) {
    this.penerimaBaru = penerimaBaru;
  }

  public String getPemberiBaru() {
    return pemberiBaru;
  }

  public void setPemberiBaru(String pemberiBaru) {
    this.pemberiBaru = pemberiBaru;
  }
}
