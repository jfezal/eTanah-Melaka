/*
 *   Document   : MohonKemukaDocActionBean.java
 *   Created on : Aug 16, 2012, 12:15:38 PM
 *   Author     : Aizuddin Orogenic
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.NotaService;
import etanah.service.common.PemohonService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/common/mohon_kemuka_doc")
public class MohonKemukaDocActionBean extends AbleActionBean {

  @Inject
  PermohonanDAO permohonanDAO;
  @Inject
  PermohonanRujukanLuarDAO permohonanRujLuarDAO;
  @Inject
  HakmilikDAO hakmilikDAO;
  @Inject
  KodRujukanDAO kodRujukanDAO;
  @Inject
  NotaService notaService;
  @Inject
  HakmilikPermohonanDAO hakmilikPermohonanDAO;
  @Inject
  PemohonDAO pemohonDAO;
  private String idPermohonan;
  private String idPemohon;
  private String idHakmilik;
  private String tempohHari;
  private String catatanBorang2B;
  private String ulasan;
  private String pihaknama;
  private String kod;
  private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
  private List<HakmilikPermohonan> hakmilikPermohonanList;
  private Permohonan permohonan;
  private Pemohon pemohon;
  private HakmilikPermohonan hakmilikPermohonan;
  private Hakmilik hakmilik;
  private PermohonanRujukanLuar permohonanRujLuar;
  private KodRujukan kodRujukan;
  private Pihak pihak;
  private InfoAudit info;
  private static final Logger LOGGER = Logger.getLogger(MohonKemukaDocActionBean.class);

  @DefaultHandler
  public Resolution showForm() {
    return new JSP("common/mohon_kemuka_doc.jsp").addParameter("tab", "true");
  }

  @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!saveOrUpdate"})
  public void rehydrate() {
    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
    LOGGER.debug("=========idPermohonan==========" + idPermohonan);
    Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    if (idPermohonan != null) {
      permohonan = permohonanDAO.findById(idPermohonan);
      kod = permohonan.getKodUrusan().getKod();
      senaraiPermohonanRujukanLuar = permohonan.getSenaraiRujukanLuar();
      LOGGER.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
    }

    if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
      permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
      LOGGER.debug("=====Tempoh Hari=========" + permohonanRujLuar.getTempohHari());
      if (permohonanRujLuar.getTempohHari() != null) {
        tempohHari = permohonanRujLuar.getTempohHari().toString();
      } else {
        tempohHari = "";
      }

      LOGGER.debug("========Dokumen Perlu Diserahkan=========" + permohonanRujLuar.getCatatan());
      if (permohonanRujLuar.getCatatan() != null) {
//                catatanBorang2B = permohonanRujLuar.getCatatan();
//                if (kod.equalsIgnoreCase("IRTB") || kod.equalsIgnoreCase("IRTBB") || kod.equalsIgnoreCase("KRM") || kod.equalsIgnoreCase("KRMB")) {
        ulasan = permohonanRujLuar.getCatatan();
//                }
//            } else {
//                LOGGER.debug("========Dokumen Perlu Diserahkan is null=========" + permohonanRujLuar.getCatatan());
//                String[] tname = {"permohonan"};
//                Object[] tvalue = {permohonan};
//                List senaraiHakMilik = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
//                hakmilikPermohonan = (HakmilikPermohonan) senaraiHakMilik.get(0);
//                idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
//                if (idHakmilik != null) {
//                    hakmilik = hakmilikDAO.findById(idHakmilik);
//
//                    catatanBorang2B = "Dokumen Hakmilik : Daerah " + hakmilik.getDaerah().getNama()
//                            + " " + hakmilik.getBandarPekanMukim().getNama()
//                            + " " + hakmilik.getKodHakmilik().getKod()
//                            + " " + hakmilik.getNoHakmilik();
      } else {
//                    catatanBorang2B = "Tiada Rekod Hakmilik";
        ulasan = "";
      }
//                if (kod.equalsIgnoreCase("IRTB") || kod.equalsIgnoreCase("IRTBB") || kod.equalsIgnoreCase("KRM") || kod.equalsIgnoreCase("KRMB")
//                        || kod.equalsIgnoreCase("TTWB")) {
//                    ulasan = "";
//                }
    }
//        }
  }

  public Resolution simpanTempoh() throws ParseException {
    Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    permohonan = permohonanDAO.findById(idPermohonan);
    LOGGER.debug("==============permohonan=================" + permohonan);
    if (permohonan != null) {
//            permohonanRujLuar.setPermohonan(permohonan);
    }
    String kod = permohonan.getKodUrusan().getKod();
    InfoAudit in = permohonan.getInfoAudit();

    if (permohonanRujLuar != null) {
      permohonanRujLuar.setPermohonan(permohonan);
    }
    if (idPermohonan != null) {
      if ((senaraiPermohonanRujukanLuar.isEmpty())) {
//                info = peng.getInfoAudit();
        permohonanRujLuar.setInfoAudit(in);
        if (StringUtils.isNotBlank(permohonanRujLuar.getNoRujukan())) {
          kodRujukan = kodRujukanDAO.findById("FL");
        } else {
          kodRujukan = kodRujukanDAO.findById("WT");
        }
        LOGGER.debug("==============To save Tempoh Hari=================" + getTempohHari());
        permohonanRujLuar.setTempohHari(new Integer(getTempohHari()));
        LOGGER.debug("Save tempoh_hari");
//                LOGGER.debug("==============Catatan Borang 2B================= " + getCatatanBorang2B());
//                permohonanRujLuar.setCatatan(catatanBorang2B);
        LOGGER.info("Save catatan");
//                if (kod.equalsIgnoreCase("IRTB") || kod.equalsIgnoreCase("IRTBB") || kod.equalsIgnoreCase("KRM") || kod.equalsIgnoreCase("KRMB")) {
        LOGGER.debug("==============Catatan ulasan================= " + getUlasan());
        permohonanRujLuar.setCatatan(ulasan);
        LOGGER.info("Save ulasan");
//                }

        permohonanRujLuar.setCawangan(peng.getKodCawangan());
        permohonanRujLuar.setKodRujukan(kodRujukan);
        permohonanRujLuar.setPermohonan(permohonan);
        permohonanRujLuar.setInfoAudit(in);
        notaService.simpanPermohonanRujLuar(permohonanRujLuar);
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");

      } else {
        LOGGER.info("Enter Update record");
//  Add by azri: to update data more then 1        
        List<PermohonanRujukanLuar> senaraiRujukLuar = senaraiPermohonanRujukanLuar;        
        for (int y = 0; y < senaraiRujukLuar.size(); y++) {
          permohonanRujLuar = senaraiRujukLuar.get(y);
          LOGGER.debug("------- permohon ruj luar : " + permohonanRujLuar.getIdRujukan());
          in.setDimasukOleh(peng);
          in.setTarikhMasuk(permohonan.getInfoAudit().getTarikhMasuk());
          in.setDiKemaskiniOleh(peng);
          in.setTarikhKemaskini(new java.util.Date());
          kodRujukan = kodRujukanDAO.findById("FL");
          LOGGER.debug("==============To save Tempoh Hari=================" + getTempohHari());
          permohonanRujLuar.setTempohHari(new Integer(getTempohHari()));
          LOGGER.info("Save tempoh_hari");
//                LOGGER.debug("==============Catatan Borang 2B================= " + getCatatanBorang2B());
//                permohonanRujLuar.setCatatan(catatanBorang2B);
//                LOGGER.info("Save catatan");
//                if (kod.equalsIgnoreCase("IRTB") || kod.equalsIgnoreCase("IRTBB") || kod.equalsIgnoreCase("KRM") || kod.equalsIgnoreCase("KRMB")) {
          LOGGER.debug("==============Catatan ulasan================= " + getUlasan());
          permohonanRujLuar.setCatatan(ulasan);
          LOGGER.info("Save ulasan");
//                }
          permohonanRujLuar.setCawangan(peng.getKodCawangan());
          permohonanRujLuar.setKodRujukan(kodRujukan);
          permohonanRujLuar.setInfoAudit(in);
          notaService.simpanPermohonanRujLuar(permohonanRujLuar);          
        }
        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini");
      }
    }
//        }

//        if (kod.equalsIgnoreCase("IRTBB")) {
//            permohonanRujLuar.setPermohonan(permohonan);
//            permohonanRujLuar.setInfoAudit(info);
//            if (StringUtils.isNotBlank(permohonanRujLuar.getNoRujukan())) {
//                kodRujukan = kodRujukanDAO.findById("FL");
//            } else {
//                kodRujukan = kodRujukanDAO.findById("WT");
//            }
//            LOGGER.debug("==============To save Tempoh Hari=================" + getTempohHari());
//            permohonanRujLuar.setTempohHari(new Integer(getTempohHari()));
//            LOGGER.debug("Save tempoh_hari");
//            LOGGER.debug("==============Catatan ulasan================= " + getUlasan());
//            permohonanRujLuar.setCatatan(ulasan);
//            LOGGER.debug("Save ulasan");
//            InfoAudit in = permohonan.getInfoAudit();
//            permohonanRujLuar.setCawangan(peng.getKodCawangan());
//            permohonanRujLuar.setKodRujukan(kodRujukan);
////                permohonanRujLuar.setPermohonan(permohonan);
//            permohonanRujLuar.setInfoAudit(in);
//            notaService.simpanPermohonanRujLuar(permohonanRujLuar);
//        }
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new ForwardResolution("common/mohon_kemuka_doc.jsp").addParameter("tab", "true");
    return new JSP("common/mohon_kemuka_doc.jsp").addParameter("tab", "true");
  }

  public HakmilikPermohonan getHakmilikPermohonan() {
    return hakmilikPermohonan;
  }

  public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
    this.hakmilikPermohonan = hakmilikPermohonan;
  }

  public InfoAudit getInfo() {
    return info;
  }

  public void setInfo(InfoAudit info) {
    this.info = info;
  }

  public NotaService getNotaService() {
    return notaService;
  }

  public void setNotaService(NotaService notaService) {
    this.notaService = notaService;
  }

  public Pemohon getPemohon() {
    return pemohon;
  }

  public void setPemohon(Pemohon pemohon) {
    this.pemohon = pemohon;
  }

  public String getCatatanBorang2B() {
    return catatanBorang2B;
  }

  public void setCatatanBorang2B(String catatanBorang2B) {
    this.catatanBorang2B = catatanBorang2B;
  }

  public String getPihaknama() {
    return pihaknama;
  }

  public void setPihaknama(String pihaknama) {
    this.pihaknama = pihaknama;
  }

  public String getIdHakmilik() {
    return idHakmilik;
  }

  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;
  }

  public String getIdPermohonan() {
    return idPermohonan;
  }

  public void setIdPermohonan(String idPermohonan) {
    this.idPermohonan = idPermohonan;
  }

  public String getTempohHari() {
    return tempohHari;
  }

  public void setTempohHari(String tempohHari) {
    this.tempohHari = tempohHari;
  }

  public String getKod() {
    return kod;
  }

  public void setKod(String kod) {
    this.kod = kod;
  }

  public Hakmilik getHakmilik() {
    return hakmilik;
  }

  public void setHakmilik(Hakmilik hakmilik) {
    this.hakmilik = hakmilik;
  }

  public Pihak getPihak() {
    return pihak;
  }

  public void setPihak(Pihak pihak) {
    this.pihak = pihak;
  }

  public KodRujukan getKodRujukan() {
    return kodRujukan;
  }

  public void setKodRujukan(KodRujukan kodRujukan) {
    this.kodRujukan = kodRujukan;
  }

  public Permohonan getPermohonan() {
    return permohonan;
  }

  public void setPermohonan(Permohonan permohonan) {
    this.permohonan = permohonan;
  }

  public PermohonanRujukanLuar getPermohonanRujLuar() {
    return permohonanRujLuar;
  }

  public void setPermohonanRujLuar(PermohonanRujukanLuar permohonanRujLuar) {
    this.permohonanRujLuar = permohonanRujLuar;
  }

  public PemohonDAO getPemohonDAO() {
    return pemohonDAO;
  }

  public void setPemohonDAO(PemohonDAO pemohonDAO) {
    this.pemohonDAO = pemohonDAO;
  }

  public String getIdPemohon() {
    return idPemohon;
  }

  public void setIdPemohon(String idPemohon) {
    this.idPemohon = idPemohon;
  }

  public String getUlasan() {
    return ulasan;
  }

  public void setUlasan(String ulasan) {
    this.ulasan = ulasan;
  }

  public HakmilikDAO getHakmilikDAO() {
    return hakmilikDAO;
  }

  public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
    this.hakmilikDAO = hakmilikDAO;
  }

  public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
    return hakmilikPermohonanDAO;
  }

  public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
    this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
  }

  public KodRujukanDAO getKodRujukanDAO() {
    return kodRujukanDAO;
  }

  public void setKodRujukanDAO(KodRujukanDAO kodRujukanDAO) {
    this.kodRujukanDAO = kodRujukanDAO;
  }

  public PermohonanDAO getPermohonanDAO() {
    return permohonanDAO;
  }

  public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
    this.permohonanDAO = permohonanDAO;
  }

  public List<HakmilikPermohonan> getHakmilikPermohonanList() {
    return hakmilikPermohonanList;
  }

  public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
    this.hakmilikPermohonanList = hakmilikPermohonanList;
  }

  public PermohonanRujukanLuarDAO getPermohonanRujLuarDAO() {
    return permohonanRujLuarDAO;
  }

  public void setPermohonanRujLuarDAO(PermohonanRujukanLuarDAO permohonanRujLuarDAO) {
    this.permohonanRujLuarDAO = permohonanRujLuarDAO;
  }

  public List<PermohonanRujukanLuar> getSenaraiPermohonanRujukanLuar() {
    return senaraiPermohonanRujukanLuar;
  }

  public void setSenaraiPermohonanRujukanLuar(List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar) {
    this.senaraiPermohonanRujukanLuar = senaraiPermohonanRujukanLuar;
  }
}
