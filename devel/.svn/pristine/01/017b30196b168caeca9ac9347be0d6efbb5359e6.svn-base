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
import etanah.service.*;
import etanah.view.kaunter.*;
import etanah.service.common.*;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import etanah.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.validation.SimpleError;
import org.apache.commons.lang.ArrayUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author TSTR
 */
@HttpCache(allow = false)
@UrlBinding("/utiliti/kutipan_dokumen")
public class UtilitiKutipanDokumen extends AbleActionBean {

  @Inject
  PermohonanDAO permohonanDAO;
  @Inject
  HakmilikDAO hakmilikDAO;
  @Inject
  HakmilikPermohonanDAO hmMohonDAO;
  @Inject
  KodPenyerahDAO kodPenyerahDAO;
  @Inject
  PermohonanKutipanDokumenDAO permohonanKutipanDokumenDAO;
  @Inject
  CommonService commonService;
  @Inject
  HakmilikPermohonanService hmService;
  @Inject
  KertasHakmilikService kertasHakmilikService;
  @Inject
  PengambilanService pengambilanService;
  @Inject
  private etanah.Configuration conf;
  @Inject
  private RequestPeguamInfo pi;
  @Inject
  private RequestJUBLInfo ji;
  @Inject
  private RequestJLBInfo jlb;
  @Inject
  private RequestUnitDalamInfo ud;
  @Inject
  private RequestAgensiInfo ai;
  private Hakmilik hakmilik;
  private Permohonan permohonan;
  private HakmilikPermohonan hakmilikMohon;
  private PermohonanKutipanDokumen permohonanKutipanDokumen;
  private KodPenyerah kodPenyerah;
  private KandunganFolder kDoc;
  private FolderDokumen fDoc;
  private Pengguna pengguna;
  private List<Penyerah> senaraiPenyerah;
  private List<HakmilikPermohonan> senaraiHakmilik;
  private List<PermohonanKutipanDokumen> senaraiKutipanDokumen;
  private List<KandunganFolder> senaraiFolder;
  private List<FolderDokumen> senaraiFolder1;
  private List<Permohonan> senaraiPermohonan;
  private String kodNegeri;
  private String namaNegeri;
  private String idPenyerah;
  private String idHakmilik;
  private String idPermohonan;
  private String namaPenyerah;
  private String kodSerah;
  private String jenisPenyerah;
  private String idFolder;
  private String tarikhKutip;
  private Long idKutip;
  private String back;
  public String selectedTab = "0";
  private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
  private static final String DEFAULT_VIEW = "/WEB-INF/jsp/daftar/utiliti/kutipan_dokumen.jsp";
  private static final String MAIN_VIEW = "daftar/utiliti/kutipan_dokumen.jsp";
  private static final String TAB_VIEW = "daftar/utiliti/kutipan_dokumen_tab.jsp";
  private static Logger LOG = Logger.getLogger(UtilitiKutipanDokumen.class);

  @DefaultHandler
  public Resolution showForm() {
    if ("04".equals(conf.getProperty("kodNegeri"))) {
      kodNegeri = "Mlk";
    }
    if ("05".equals(conf.getProperty("kodNegeri"))) {
      kodNegeri = "N9";
    }
    return new ForwardResolution(DEFAULT_VIEW);
  }

//    @HandlesEvent("kembali")
  public Resolution searchDokumen() {
    LOG.info("--- Search Start ---");
    String selectedTab = getContext().getRequest().getParameter("selectedTab");
    pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    LOG.info("--- id mohon -->" + idPermohonan);
    LOG.info("--- id Hakmilik -->" + idHakmilik);
    LOG.info("--- jenis Penyerah -->" + jenisPenyerah);
    LOG.info("--- id Penyerah -->" + idPenyerah);
    LOG.info("---- USER kod Caw --> " + pengguna.getKodCawangan().getKod());

    if (StringUtils.isNotBlank(idHakmilik)) {
      if (idHakmilik != null) {
        LOG.info("*** Search id Hakmilik *** " + idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        LOG.info("---- Hakmilik Caw --> " + hakmilik.getCawangan().getKod());

        if (pengguna.getKodCawangan().getKod().equals(hakmilik.getCawangan().getKod())) {
          senaraiHakmilik = pengambilanService.findbyIdHakmilik1(idHakmilik);
          return new JSP(MAIN_VIEW);
        }
        addSimpleError("Id Hakmilik " + idHakmilik + " tidak boleh dikutip disini.");
        addSimpleError("Sila kutip di " + hakmilik.getCawangan().getName() + ".");
        return new JSP(MAIN_VIEW);
      } else {
        addSimpleError("Hakmilik Tidak Dijumpai.Sila masukkan ID Hakmilik yang sah");
        return new JSP(MAIN_VIEW);
      }
    }

    if (StringUtils.isNotBlank(idPermohonan)) {
      LOG.info("*** Search id Permohonan **** " + idPermohonan);
      permohonan = permohonanDAO.findById(idPermohonan);
      if (permohonan == null) {
        LOG.info("Permohonan " + idPermohonan + " tidak dijumpai.");
        addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
        return new JSP(MAIN_VIEW);
      } else {
        kodSerah = permohonan.getKodUrusan().getKodPerserahan().getKod();
        LOG.info("--- kod Serah --> " + kodSerah);
        LOG.info("-- status permohonan--->" + permohonan.getStatus());

//        if (permohonan.getKeputusan().getKod().equalsIgnoreCase("D") && permohonan.getStatus().equalsIgnoreCase("SL")) {
        if (permohonan.getStatus().equalsIgnoreCase("SL")) {    
          LOG.info("*** status ada lah SL atau D");
          LOG.info("---- Permohonan Caw --> " + permohonan.getCawangan().getKod());
          if (pengguna.getKodCawangan().getKod().equals(permohonan.getCawangan().getKod())) {
            if (kodSerah.equalsIgnoreCase("SC") || kodSerah.equalsIgnoreCase("B")
                    || kodSerah.equalsIgnoreCase("N") || kodSerah.equalsIgnoreCase("NB")
                    || kodSerah.equalsIgnoreCase("CR") || kodSerah.equalsIgnoreCase("MH")|| kodSerah.equalsIgnoreCase("SW") || kodSerah.equalsIgnoreCase("SA")) {

              senaraiHakmilik = kertasHakmilikService.findByIdPermohonan(idPermohonan);
              LOG.info("--- senaraiHakmilik.size() --- " + senaraiHakmilik.size());

              if (senaraiHakmilik.size() > 1) {
                senaraiHakmilik = kertasHakmilikService.findByIdPermohonan(idPermohonan);
                return new JSP(MAIN_VIEW);
              } else {
                hakmilikMohon = pengambilanService.findPermohonanByIdPermohonan(idPermohonan);
                if(permohonan.getFolderDokumen()!=null)
                senaraiFolder = kertasHakmilikService.findByIdFolder(String.valueOf(permohonan.getFolderDokumen().getFolderId()));
                
                senaraiKutipanDokumen = kertasHakmilikService.listByIdPermohonan(idPermohonan);
                LOG.info("--- senarai Kutipan Dokumen Size() --->" + senaraiKutipanDokumen.size());

                if (senaraiKutipanDokumen.size() > 0) {
                  permohonanKutipanDokumen = senaraiKutipanDokumen.get(0);
                  LOG.info("--- nama --> " + permohonanKutipanDokumen.getNama());
                  LOG.info("--- tarikh ---> " + permohonanKutipanDokumen.getTarikhKutipan());
//                  LOG.info("--- kod Negeri ---> " + permohonanKutipanDokumen.getAlamat().getNegeri().getKod());
                  addSimpleMessage("URUSAN TELAH DIKUTIP");
                }
                return new JSP(TAB_VIEW);
              }
            } else {
              addSimpleError("Perserahan " + idPermohonan + " Tiada Dokumen Untuk Dikutip");

              return new JSP(MAIN_VIEW);
            }
          }
          addSimpleError("Sila kutip dokumen di " + permohonan.getCawangan().getName() + ".");
          return new JSP(MAIN_VIEW);
        } else {
          idPermohonan = null;
          idHakmilik = null;
          addSimpleError("Urusan Masih Belum Selesai");
          return new JSP(MAIN_VIEW);
        }
      }
    }

    if (StringUtils.isNotBlank(jenisPenyerah)) {
      LOG.info("*** Search by Jenis Penyerah ***");
      LOG.info("--- Jenis Penyerah ---> " + jenisPenyerah);
      LOG.info("--- Id Penyerah ---> " + idPenyerah);
      LOG.info("--- Id hakmlik ---> " + idHakmilik);
      LOG.info("--- hakmlik ---> " + hakmilik);

      if (jenisPenyerah != null) {
        kodPenyerah = kodPenyerahDAO.findById(jenisPenyerah);
        if (StringUtils.isBlank(idPenyerah)) {
          LOG.info("--- id perserahan is BLANK ---");
          senaraiHakmilik = pengambilanService.findByKodPenyerah(jenisPenyerah);
          LOG.info("--- senaraiHakmilik.size() ---" + senaraiHakmilik.size());
        } else {
          LOG.info("--- id perserahan is NOT blank ---");
          senaraiHakmilik = kertasHakmilikService.findByKodPenyerahAndIdPenyerah(jenisPenyerah, idPenyerah);
          LOG.info("--- senaraiHakmilik.size() ---" + senaraiHakmilik.size());
        }
        return new JSP(MAIN_VIEW);
      }
    }
    return new JSP(MAIN_VIEW);
//        return new JSP("daftar/utiliti/kutipan_dokumen.jsp");
  }

  public Resolution paparDokumen() {
    LOG.info("--- papar Dokumen ---");
    pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    idPermohonan = getContext().getRequest().getParameter("idPermohonan2");
    idHakmilik = getContext().getRequest().getParameter("idHakmilik2");
    String backMohon = getContext().getRequest().getParameter("backMohon");
    String backHakmilik = getContext().getRequest().getParameter("backHakmilik");
    String backPenyerah = getContext().getRequest().getParameter("backPenyerah");
    if (backMohon.length() > 1) {
      LOG.info("--- back hakmilik --> " + backMohon.length());
      back = "1";
    }
    if (backHakmilik.length() > 1) {
      LOG.info("--- back hakmilik --> " + backHakmilik.length());
      back = "2";
    }
    if (backPenyerah.length() > 1) {
      LOG.info("--- back Penyerah) --> " + backPenyerah.length());
      back = "3";
      jenisPenyerah = backPenyerah;
    }
    LOG.info("--- id permhonan--> " + idPermohonan);
    LOG.info("--- id Hakmilik--> " + idHakmilik);
    permohonan = permohonanDAO.findById(idPermohonan);

    LOG.info("--- CAW pengguna ---> " + pengguna.getKodCawangan().getKod());
    LOG.info("--- CAW permohonan ---> " + permohonan.getCawangan().getKod());

    if (pengguna.getKodCawangan().getKod().equals(permohonan.getCawangan().getKod())) {

     // if (permohonan.getKeputusan().getKod().equalsIgnoreCase("D") && permohonan.getStatus().equalsIgnoreCase("SL")) {
        if (permohonan.getStatus().equalsIgnoreCase("SL")) {
        LOG.info("*** status ada lah SL atau D");
        LOG.info("---- Permohonan Caw --> " + permohonan.getCawangan().getKod());
        kodSerah = permohonan.getKodUrusan().getKodPerserahan().getKod();

        if (kodSerah.equalsIgnoreCase("SC") || kodSerah.equalsIgnoreCase("B")
                || kodSerah.equalsIgnoreCase("N") || kodSerah.equalsIgnoreCase("NB")
                || kodSerah.equalsIgnoreCase("CR") || kodSerah.equalsIgnoreCase("MH")|| kodSerah.equalsIgnoreCase("SW") || kodSerah.equalsIgnoreCase("SA")) {

          senaraiFolder = kertasHakmilikService.findByIdFolder(String.valueOf(permohonan.getFolderDokumen().getFolderId()));
          senaraiKutipanDokumen = kertasHakmilikService.listByIdPermohonan(idPermohonan);
          LOG.info("--- senarai Kutipan Dokumen Size() --->" + senaraiKutipanDokumen.size());
          if (senaraiKutipanDokumen.size() > 0) {
            permohonanKutipanDokumen = senaraiKutipanDokumen.get(0);
            LOG.info("--- nama --> " + permohonanKutipanDokumen.getNama());
            LOG.info("--- tarikh ---> " + permohonanKutipanDokumen.getTarikhKutipan());
            addSimpleMessage("URUSAN TELAH DIKUTIP");
          }
          return new JSP(TAB_VIEW);
        } else {
          addSimpleError("Perserahan " + idPermohonan + " Tiada Dokumen Untuk Dikutip");
          return new JSP(MAIN_VIEW);
        }
      } else {
        addSimpleError("Urusan Masih Belum Selesai");
        return new JSP(MAIN_VIEW);

      }
    }
    addSimpleError("Sila kutip dokumen di " + permohonan.getCawangan().getName() + ".");
    return new JSP(MAIN_VIEW);
  }

  public Resolution back() {
    return new JSP(MAIN_VIEW);
  }

  public Resolution kutip() throws ParseException {
    LOG.info("--- Kutip Dokumen ---");

    senaraiKutipanDokumen = kertasHakmilikService.listByIdPermohonan(idPermohonan);
    LOG.info("--- senarai Kutipan Dokumen Size() --->" + senaraiKutipanDokumen.size());
    if (senaraiKutipanDokumen.isEmpty()) {
      String kJenisPengenalan = getContext().getRequest().getParameter("kJenisPengenalan");
      String kPengenalan = getContext().getRequest().getParameter("kPengenalan");
      String kNama = getContext().getRequest().getParameter("kNama");
      String kAlamat1 = getContext().getRequest().getParameter("kAlamat1");
      String kAlamat2 = getContext().getRequest().getParameter("kAlamat2");
      String kAlamat3 = getContext().getRequest().getParameter("kAlamat3");
      String kBandar = getContext().getRequest().getParameter("kBandar");
      String kPoskod = getContext().getRequest().getParameter("kPoskod");
      String kNegeri = getContext().getRequest().getParameter("kNegeri");
      String kNoTel = getContext().getRequest().getParameter("kNoTel");
      idPermohonan = getContext().getRequest().getParameter("idPermohonan");

      LOG.info("--- id Permohonan (must NOT null) ---> " + idPermohonan);
      LOG.info("--- kNama ---> " + kNama.length());
      LOG.info("--- kAlamat1 ---> " + kAlamat1.length());
      LOG.info("--- kAlamat2 ---> " + kAlamat2);
      LOG.info("--- kAlamat3 ---> " + kAlamat3);
      LOG.info("--- kBandar ---> " + kBandar.length());
      LOG.info("--- kPoskod ---> " + kPoskod.length());
      LOG.info("--- kNegeri ---> " + kNegeri.length());
      LOG.info("--- kNoTel ---> " + kNoTel);
      LOG.info("--- kJenisPengenalan ---> " + kJenisPengenalan);
      LOG.info("--- kPengenalan ---> " + kPengenalan);

      tarikhKutip = sd.format(new java.util.Date());
      LOG.info("--- tarikhKutip ---> " + tarikhKutip);
      Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);      

      if (kJenisPengenalan.length() > 0 && kPengenalan.length() > 0 && kNama.length() > 0) {
        PermohonanKutipanDokumen pkd = new PermohonanKutipanDokumen();

          InfoAudit info = peng.getInfoAudit();
          info.setDimasukOleh(peng);
          info.setDiKemaskiniOleh(peng);
          info.setTarikhMasuk(new java.util.Date());
          info.setTarikhKemaskini(new java.util.Date());

          Permohonan permohon = new Permohonan();
          permohon.setIdPermohonan(idPermohonan);
          pkd.setPermohonan(permohon);

        KodJenisPengenalan jp = new KodJenisPengenalan();
        jp.setKod(kJenisPengenalan);
        pkd.setJenisPengenalan(jp);

        pkd.setNoPengenalan(kPengenalan);
        pkd.setNama(kNama);
//------ simpan alamat
        Alamat alamat = new Alamat();
        alamat.setAlamat1(kAlamat1);
        alamat.setAlamat2(kAlamat2);
        alamat.setAlamat3(kAlamat3);
        alamat.setAlamat4(kBandar);
        alamat.setPoskod(kPoskod);
        KodNegeri k_Negeri = new KodNegeri();
        k_Negeri.setKod(kNegeri);
        alamat.setNegeri(k_Negeri);
        pkd.setAlamat(alamat);

        pkd.setTelefon(kNoTel);
        pkd.setTarikhKutipan(sd.parse(tarikhKutip));
        pkd.setInfoAudit(info);

        kertasHakmilikService.simpanKutipanDokumen(pkd);
        addSimpleMessage("Penambahan Data Berjaya");
        LOG.info("---MAKLUMAT TELAH DISIMPAN---");

      } else {
        addSimpleError("AMARAN: Pastikan Semua Maklumat Mandatori Telah Diisi.");
      }
    }
//        -------- refresh tab
    permohonan = permohonanDAO.findById(idPermohonan);
    kodSerah = permohonan.getKodUrusan().getKodPerserahan().getKod();
    if (kodSerah.equalsIgnoreCase("SC") || kodSerah.equalsIgnoreCase("B")
            || kodSerah.equalsIgnoreCase("N") || kodSerah.equalsIgnoreCase("NB")
            || kodSerah.equalsIgnoreCase("CR") || kodSerah.equalsIgnoreCase("MH")) {
      senaraiKutipanDokumen = kertasHakmilikService.listByIdPermohonan(idPermohonan);
      permohonanKutipanDokumen = senaraiKutipanDokumen.get(0);

      senaraiFolder = kertasHakmilikService.findByIdFolder(String.valueOf(permohonan.getFolderDokumen().getFolderId()));
      LOG.info("--- nama --> " + permohonanKutipanDokumen.getNama());
      LOG.info("--- tarikh ---> " + permohonanKutipanDokumen.getTarikhKutipan());
      
      if(permohonanKutipanDokumen.getAlamat()!= null){
      namaNegeri = permohonanKutipanDokumen.getAlamat().getNegeri().getNama();
      LOG.info("-- Nama Negeri -->" + namaNegeri);
      }
    }
    addSimpleMessage("URUSAN TELAH DIKUTIP");
//        --------- refresh tab end
    return new JSP(TAB_VIEW);
  }

// *********** Getter and Setter ************
  public String getIdHakmilik() {
    return idHakmilik;
  }

  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;
  }

  public String getJenisPenyerah() {
    return jenisPenyerah;
  }

  public void setJenisPenyerah(String jenisPenyerah) {
    this.jenisPenyerah = jenisPenyerah;
  }

  public String getNamaPenyerah() {
    return namaPenyerah;
  }

  public void setNamaPenyerah(String namaPenyerah) {
    this.namaPenyerah = namaPenyerah;
  }

  public List<Penyerah> getSenaraiPenyerah() {
    return senaraiPenyerah;
  }

  public void setSenaraiPenyerah(List<Penyerah> senaraiPenyerah) {
    this.senaraiPenyerah = senaraiPenyerah;
  }

  public String getIdPermohonan() {
    return idPermohonan;
  }

  public void setIdPermohonan(String idPermohonan) {
    this.idPermohonan = idPermohonan;
  }

  public String getKodNegeri() {
    return kodNegeri;
  }

  public void setKodNegeri(String kodNegeri) {
    this.kodNegeri = kodNegeri;
  }

  public String getKodSerah() {
    return kodSerah;
  }

  public void setKodSerah(String kodSerah) {
    this.kodSerah = kodSerah;
  }

  public Hakmilik getHakmilik() {
    return hakmilik;
  }

  public void setHakmilik(Hakmilik hakmilik) {
    this.hakmilik = hakmilik;
  }

  public Permohonan getPermohonan() {
    return permohonan;
  }

  public void setPermohonan(Permohonan permohonan) {
    this.permohonan = permohonan;
  }

  public String getSelectedTab() {
    return selectedTab;
  }

  public void setSelectedTab(String selectedTab) {
    this.selectedTab = selectedTab;
  }

  public FolderDokumen getfDoc() {
    return fDoc;
  }

  public void setfDoc(FolderDokumen fDoc) {
    this.fDoc = fDoc;
  }

  public List<HakmilikPermohonan> getSenaraiHakmilik() {
    return senaraiHakmilik;
  }

  public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
    this.senaraiHakmilik = senaraiHakmilik;
  }

  public HakmilikPermohonan getHakmilikMohon() {
    return hakmilikMohon;
  }

  public void setHakmilikMohon(HakmilikPermohonan hakmilikMohon) {
    this.hakmilikMohon = hakmilikMohon;
  }

  public KodPenyerah getKodPenyerah() {
    return kodPenyerah;
  }

  public void setKodPenyerah(KodPenyerah kodPenyerah) {
    this.kodPenyerah = kodPenyerah;
  }

  public PermohonanKutipanDokumen getPermohonanKutipanDokumen() {
    return permohonanKutipanDokumen;
  }

  public void setPermohonanKutipanDokumen(PermohonanKutipanDokumen permohonanKutipanDokumen) {
    this.permohonanKutipanDokumen = permohonanKutipanDokumen;
  }

  public List<PermohonanKutipanDokumen> getSenaraiKutipanDokumen() {
    return senaraiKutipanDokumen;
  }

  public void setSenaraiKutipanDokumen(List<PermohonanKutipanDokumen> senaraiKutipanDokumen) {
    this.senaraiKutipanDokumen = senaraiKutipanDokumen;
  }

  public List<KandunganFolder> getSenaraiFolder() {
    return senaraiFolder;
  }

  public void setSenaraiFolder(List<KandunganFolder> senaraiFolder) {
    this.senaraiFolder = senaraiFolder;
  }

  public String getIdFolder() {
    return idFolder;
  }

  public void setIdFolder(String idFolder) {
    this.idFolder = idFolder;
  }

  public List<FolderDokumen> getSenaraiFolder1() {
    return senaraiFolder1;
  }

  public void setSenaraiFolder1(List<FolderDokumen> senaraiFolder1) {
    this.senaraiFolder1 = senaraiFolder1;
  }

  public List<Permohonan> getSenaraiPermohonan() {
    return senaraiPermohonan;
  }

  public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
    this.senaraiPermohonan = senaraiPermohonan;
  }

  public Long getIdKutip() {
    return idKutip;
  }

  public void setIdKutip(Long idKutip) {
    this.idKutip = idKutip;
  }

  public String getTarikhKutip() {
    return tarikhKutip;
  }

  public void setTarikhKutip(String tarikhKutip) {
    this.tarikhKutip = tarikhKutip;
  }

  public Pengguna getPengguna() {
    return pengguna;
  }

  public void setPengguna(Pengguna pengguna) {
    this.pengguna = pengguna;
  }

  public KandunganFolder getkDoc() {
    return kDoc;
  }

  public void setkDoc(KandunganFolder kDoc) {
    this.kDoc = kDoc;
  }

  public String getIdPenyerah() {
    return idPenyerah;
  }

  public void setIdPenyerah(String idPenyerah) {
    this.idPenyerah = idPenyerah;
  }

  public String getNamaNegeri() {
    return namaNegeri;
  }

  public void setNamaNegeri(String namaNegeri) {
    this.namaNegeri = namaNegeri;
  }

  public String getBack() {
    return back;
  }

  public void setBack(String back) {
    this.back = back;
  }
}
