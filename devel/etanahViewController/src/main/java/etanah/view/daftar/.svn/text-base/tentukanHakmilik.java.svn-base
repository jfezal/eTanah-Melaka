/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.dao.WakilKuasaPemberiDAO;
import etanah.dao.WakilKuasaPenerimaDAO;
import etanah.dao.WakilKuasaDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.WakilHakmilik;
//import etanah.model.WakilPihak;
//import etanah.dao.WakilPihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.PermohonanDokumen;
import etanah.model.Pihak;
import etanah.model.WakilKuasa;
import etanah.model.WakilKuasaHakmilik;
import etanah.model.WakilKuasaPemberi;
import etanah.model.WakilKuasaPenerima;
import etanah.service.common.PihakService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.sequence.GeneratorIdWakil;
import etanah.service.common.DokumenService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author mohd.fairul
 */
@UrlBinding("/daftar/tentukanHakmilik")
public class tentukanHakmilik extends AbleActionBean {

  @Inject
  PermohonanDAO permohonanDAO;
//    @Inject
//    WakilPihakDAO wakilPihakDAO;
  @Inject
  PendaftaranSuratKuasaService suratkuasaService;
  @Inject
  DokumenService dokumenService;
  @Inject
  DokumenDAO dokumenDAO;
  @Inject
  PihakService pihakService;
  @Inject
  KodJenisPengenalanDAO kodjenisPengenalanDAO;
  @Inject
  PihakDAO pihakDAO;
  @Inject
  HakmilikDAO hakmilikDAO;
  @Inject
  KodNegeriDAO negeriDAO;
  @Inject
  private GeneratorIdWakil idWakilGenerator;
  @Inject
  HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
  @Inject
  KodCawanganDAO cawanganDAO;
  @Inject
  HakmilikPermohonanDAO hakmilikPermohonanDAO;
  @Inject
  WakilKuasaPenerimaDAO wakilKuasaPenerimaDAO;
  @Inject
  WakilKuasaPemberiDAO wakilKuasaPemberiDAO;
  @Inject
  private Configuration conf;
  private String idPermohonan;
  private WakilKuasaHakmilik wakilKuasaHakmilik;
//    private WakilPihak wakilPihak;//will delete
  //New Table.....
  private WakilKuasa wakilKuasa;
  private WakilKuasa wakilKuasaSB;                  // for SB only  
  private String flagSB = null;                     // for SB only
  private List<Map<String, Object>> listSB = new ArrayList<Map<String, Object>>();
  private List<HakmilikPermohonan> pdlSBlist;       // for SB only
  private WakilKuasaPemberi wakilKuasaPemberi;
  private WakilKuasaPenerima wakilKuasaPenerima;
  private String amaunMaksima;
  private String syaratTambahan;
  private Permohonan permohonan;
  private Pihak pihak;
  private String noKP;
  private String kodKP;
  private String noKadPengenalan;
  private boolean flagCarian;
  private KodJenisPengenalan kodjenisPengenalan;
  private String namaPemberi;
  private String jenisPengenalanPemberi;
  private String noPengenalanPemberi;
  private InfoAudit info;
  private String tmpIDpihak;
  private String namaPenerima;
  private String alamat1;
  private String alamat2;
  private String alamat3;
  private String alamat4;
  private String poskod;
  private String negeriPenerima;
  private KodNegeri negeri;
  private String kodNegeri;
  private KodCawangan cawangan;
  private String jenisPengenalanPenerima;
  private String noPengenalanPenerima;
  private List<HakmilikPihakBerkepentingan> hpB;
  private List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList;
  private List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList2;
  private List<HakmilikPermohonan> hakmilikPermohonanList;
  private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
  private Pengguna peng;
  String pattern1 = "dd/MM/yyyy";
  SimpleDateFormat dateFormat = new SimpleDateFormat(pattern1);
  Calendar calendar = new GregorianCalendar();
  private Date tarikhDaftar;
  private String tkhDaftar;
  private int size;
//    private int sizeWH;
  private String kuasaL1;
  private String kuasaL2;
  private Hakmilik hakmilik;
  private String idH;
  private String idPihakPemberi;
  private String idPenerima;
  private String hakmilikWakil;
  private String idPemberi;
  List<WakilKuasaHakmilik> wKuasaHakmilik;
  List<WakilKuasa> wKuasa;
  List<WakilKuasa> wKuasaSB;        // for SB only
  List<Pihak> pihakList;
  private String jPihak;
  private List<KandunganFolder> senaraiKF;
  private List<Dokumen> dokumen;
//    private List<KandunganFolder> senaraiKF;
  private List<String> senaraiSW = new ArrayList<String>();
  private static final Logger LOG = Logger.getLogger(tentukanHakmilik.class);
  private String[] noRujukan;
  private String[] idHakmilikSW;
  private String[] kuasaPindahMilik;  // saveWakilKuasaSB()
  private String[] kuasaPajak;        // saveWakilKuasaSB()
  private String[] kuasaGadai;        // saveWakilKuasaSB()
  private PermohonanDokumen pDokumen;
  private PermohonanDokumen pD;
  private List<PermohonanDokumen> pdl;
//    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
  private String idRujDelete;
  private String idHakDelete;

  @DefaultHandler
  public Resolution saveTentukanHakmilik() {
    int counter = 0;
    LOG.info("Test Entering SaveTentukan");
    String msg = null;
//       LOG.info("Test Dokumen::"+ dokumen.size());
    for (Dokumen dk : dokumen) {
//            LOG.info("Test" + dk.getNoRujukan());
//            LOG.info("Id Hakmilik"+ idHakmilikSW[counter]);
//            LOG.info("no Rujukan" + noRujukan[counter]);
        LOG.info("id dokumen~~~"+dk.getIdDokumen());
        LOG.info("no rujukan~~~"+dk.getNoRujukan());
        
        
      //check permohonandokumen
      if (StringUtils.isNotBlank(idHakmilikSW[counter]) && StringUtils.isNotBlank(noRujukan[counter])) {
        HakmilikPermohonan hP = suratkuasaService.findPH(idHakmilikSW[counter], permohonan.getIdPermohonan());
        pDokumen = suratkuasaService.findPermohonanDokumen(idHakmilikSW[counter], noRujukan[counter]);
//            LOG.info("Check Permohonan Dokumen:: "+ pDokumen);
//            pDokumen.getHakmilikPermohonan().getHakmilik().getIdHakmilik().
        info = peng.getInfoAudit();
        
        dk.setNoRujukan(noRujukan[counter]);
        dokumenDAO.update(dk);
        
        if (pDokumen != null) {
            
          info.setDiKemaskiniOleh(peng);
          info.setTarikhKemaskini(new java.util.Date());
          pDokumen.setInfoAudit(info);
          pDokumen.setDokumen(dk);
          pDokumen.setNoRujukan(noRujukan[counter]);
          pDokumen.setPermohonan(permohonan);
          pDokumen.setHakmilikPermohonan(hP);
          pDokumen.setCawangan(peng.getKodCawangan());
          suratkuasaService.updatePD(pDokumen);
//                    addSimpleMessage("Maklumat berjaya dikemaskini.");
          msg = "Maklumat berjaya dikemaskini.";
        } else {
          pDokumen = new PermohonanDokumen();
          info.setDimasukOleh(peng);
          info.setTarikhMasuk(new java.util.Date());
          pDokumen.setInfoAudit(info);
          pDokumen.setDokumen(dk);
          pDokumen.setNoRujukan(noRujukan[counter]);
          pDokumen.setPermohonan(permohonan);
          pDokumen.setHakmilikPermohonan(hP);
          pDokumen.setCawangan(peng.getKodCawangan());
          suratkuasaService.savePD(pDokumen);
//                    addSimpleMessage("Maklumat berjaya disimpan.");
          msg = "Maklumat berjaya disimpan.";
        }
      }
      counter = counter + 1;
    }
    addSimpleMessage(msg);
//    suratkuasaService.saveHakmilikUrusanSurat(permohonan, peng);
    return new RedirectResolution(tentukanHakmilik.class, "tentukanHakmilik");
  }

  public Resolution tentukanHakmilik() {
    FolderDokumen fd = permohonan.getFolderDokumen();
    senaraiKF = fd.getSenaraiKandungan();
//            senaraiKF = suratkuasaService.findSWSahaja(idPermohonan);
    dokumen = new ArrayList<Dokumen>();
    if (senaraiKF != null) {
      for (KandunganFolder kf : senaraiKF) {
        if (kf != null) {
          Dokumen d = kf.getDokumen();
          if (d.getKodDokumen().getKod().equals("SWD")
                  || d.getKodDokumen().getKod().equals("SW")
                  || d.getKodDokumen().getKod().equals("SWB")
                  || d.getKodDokumen().getKod().equals("SB")
                  || d.getKodDokumen().getKod().equals("SBB")
                  || d.getKodDokumen().getKod().equals("SBD")
                  || d.getKodDokumen().getKod().equals("SAB")
                  || d.getKodDokumen().getKod().equals("SAD")) {
//                            wakilkuasa = pService.findWakilKuasa(d.getNoRujukan());
            LOG.info("Test ID Rujukan:: " + d.getNoRujukan());
            if (StringUtils.isNotBlank(d.getNoRujukan())) {
              dokumen.add(d);
            }
          }
        }
      }
    }
    return new JSP("daftar/tentukanSurat.jsp").addParameter("tab", "true");
  }

  @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!saveOrUpdate"})
  public void rehydrate() {
    flagCarian = false;
    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
    peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    kodNegeri = conf.getProperty("kodNegeri");

    if (idPermohonan != null) {
      permohonan = permohonanDAO.findById(idPermohonan);
      pdl = suratkuasaService.findLisPermohonanDokumen(idPermohonan);

      FolderDokumen fd = permohonan.getFolderDokumen();
      senaraiKF = fd.getSenaraiKandungan();
      dokumen = new ArrayList<Dokumen>();

      if (senaraiKF != null) {
        for (KandunganFolder kf : senaraiKF) {
          if (kf != null) {
            Dokumen d = kf.getDokumen();
            if (d.getKodDokumen().getKod().equals("SWD")
                    || d.getKodDokumen().getKod().equals("SW")
                    || d.getKodDokumen().getKod().equals("SWB")
                    || d.getKodDokumen().getKod().equals("SB")
                    || d.getKodDokumen().getKod().equals("SBB")
                    || d.getKodDokumen().getKod().equals("SBD")
                    || d.getKodDokumen().getKod().equals("SAB")
                    || d.getKodDokumen().getKod().equals("SAD")) {
              if (StringUtils.isNotBlank(d.getNoRujukan())) {
                dokumen.add(d);
              }
            }
          }
          noRujukan = new String[dokumen.size()];
          idHakmilikSW = new String[dokumen.size()];
        }
      }

      if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SW")) {
        wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        hakmilikPihakBerkepentinganList2 = new ArrayList<HakmilikPihakBerkepentingan>();
        if (hakmilikPermohonanList.size() != 0) {
          for (HakmilikPermohonan hP : hakmilikPermohonanList) {
            hakmilikPihakBerkepentinganList = suratkuasaService.findHakmilikOnly(hP.getHakmilik().getIdHakmilik());
            if (hakmilikPihakBerkepentinganList.size() != 0) {
              for (HakmilikPihakBerkepentingan hPBP : hakmilikPihakBerkepentinganList) {
                hakmilikPihakBerkepentinganList2.add(hPBP);
              }
            }
          }
        }
      } else {
        LOG.info("Not SW so do nothing");
        //fixme. temp solution, fairul faisal to fix it after honeymoon..
        if (!dokumen.isEmpty()) {
          wKuasa = new ArrayList<WakilKuasa>();
          wKuasaSB = new ArrayList<WakilKuasa>();
//          Map m = new HashMap();
          for (Dokumen dok : dokumen) {
            wakilKuasa = new WakilKuasa();
            Permohonan mohon = permohonanDAO.findById(dok.getNoRujukan());
            if (mohon == null) {
              continue;
            }
            //add by azri 3/7/2013 : for urusan SB to show info from table 'wakil_kuasa'
            if (mohon.getKodUrusan().getKod().equalsIgnoreCase("SB")) {
              flagSB = "1";
              wakilKuasaSB = suratkuasaService.findWakilKuasaSB(dok.getNoRujukan());
              
//                if (wakilKuasaSB == null) {
//                      addSimpleMessage("Sila Pastikan anda telah membuat kemasukan Pendaftaran Surat Kebenaran terlebih dahulu untuk surat "+dok.getNoRujukan());   
//                }
              
                if (wakilKuasaSB != null) {
                    LOG.info("--- wakilKuasaSB.getIdWakil() ---" + wakilKuasaSB.getIdWakil());
                    pdlSBlist = suratkuasaService.findListHakmilikPermohonan(mohon.getIdPermohonan());
                    wKuasaSB.add(wakilKuasaSB);
                    Map m = new HashMap();
                    m.put("id_wakil", wakilKuasaSB.getIdWakil());
                    m.put("id_hakmilik", pdlSBlist);
                    m.put("pindahmilik", wakilKuasaSB.getKuasaPindahMilik());
                    m.put("Gadaian", wakilKuasaSB.getKuasaGadai());
                    m.put("Pajakan", wakilKuasaSB.getKuasaPajak());
                    getListSB().add(m);
                }
              
              //add by azri 3/7/2013 : END
            } else {
              wakilKuasa = suratkuasaService.findWakilKuasa(dok.getNoRujukan());
              wKuasa.add(wakilKuasa);
            }
          }
        } else {
          wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
        }
      }
    }
  }
//add by azri 3/7/2013 : for urusan SB to update table 'wakil_kuasa'
  public Resolution saveWakilKuasaSB() {
    LOG.info("--- saveWakilKuasaSB() :: Start ---");
    int counter = 0;
    Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    permohonan = permohonanDAO.findById((String) getContext().getRequest().getSession().getAttribute("idPermohonan"));
    for (WakilKuasa wakilKuasa : wKuasaSB) {
      info = peng.getInfoAudit();
      if (StringUtils.isNotBlank(kuasaPindahMilik[counter]) || StringUtils.isNotBlank(kuasaGadai[counter]) || StringUtils.isNotBlank(kuasaPajak[counter])) {
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        wakilKuasa.setInfoAudit(info);
        char wkpm = kuasaPindahMilik[counter].charAt(0);
        char wkgd = kuasaGadai[counter].charAt(0);
        char wkpjk = kuasaPajak[counter].charAt(0);
        wakilKuasa.setKuasaPindahMilik(wkpm);
        wakilKuasa.setKuasaGadai(wkgd);
        wakilKuasa.setKuasaPajak(wkpjk);
        suratkuasaService.updateWakilKuasa(wakilKuasa);
      }
      counter = counter + 1;
    }
    addSimpleMessage("Maklumat berjaya dikemaskini");
    LOG.info("--- saveWakilKuasaSB() :: End ---");
    return new RedirectResolution(tentukanHakmilik.class, "tentukanHakmilik");
  }
  //add by azri 3/7/2013 : END

  public Resolution deleteTentukan() {

    LOG.info("::Enter Delete Senarai::");
    idRujDelete = (String) getContext().getRequest().getParameter("idRujDelete");
    LOG.info("LOG idRujDelete :: " + idRujDelete);
    idHakDelete = (String) getContext().getRequest().getParameter("idHakDelete");
    LOG.info("LOG idHakDelete :: " + idHakDelete);

    pD = suratkuasaService.findPermohonanDokumen(idHakDelete, idRujDelete);
    LOG.info("LOG pD" + pD);
    if (pD != null) {
      suratkuasaService.deleteTentukan(pD);
      LOG.info("Delete Success");
      addSimpleMessage("Maklumat berjaya dihapuskan");
    }
    return new RedirectResolution(tentukanHakmilik.class, "tentukanHakmilik");
  }

  public void alert(String text, Object msg) {
    JOptionPane.showMessageDialog(null, text + " " + msg);
  }

  public List<HakmilikPihakBerkepentingan> getHpB() {
    return hpB;
  }

  public void setHpB(List<HakmilikPihakBerkepentingan> hpB) {
    this.hpB = hpB;
  }

  public String getTkhDaftar() {
    return tkhDaftar;
  }

  public void setTkhDaftar(String tkhDaftar) {
    this.tkhDaftar = tkhDaftar;
  }

  public String getJenisPengenalanPenerima() {
    return jenisPengenalanPenerima;
  }

  public void setJenisPengenalanPenerima(String jenisPengenalanPenerima) {
    this.jenisPengenalanPenerima = jenisPengenalanPenerima;
  }

  public String getNoPengenalanPenerima() {
    return noPengenalanPenerima;
  }

  public void setNoPengenalanPenerima(String noPengenalanPenerima) {
    this.noPengenalanPenerima = noPengenalanPenerima;
  }

  public String getNamaPenerima() {
    return namaPenerima;
  }

  public void setNamaPenerima(String namaPenerima) {
    this.namaPenerima = namaPenerima;
  }

  public String getNoKadPengenalan() {
    return noKadPengenalan;
  }

  public void setNoKadPengenalan(String noKadPengenalan) {
    this.noKadPengenalan = noKadPengenalan;
  }

  public boolean isFlagCarian() {
    return flagCarian;
  }

  public void setFlagCarian(boolean flagCarian) {
    this.flagCarian = flagCarian;
  }

  public String getIdPermohonan() {
    return idPermohonan;
  }

  public void setIdPermohonan(String idPermohonan) {
    this.idPermohonan = idPermohonan;
  }

  public WakilKuasaHakmilik getWakilKuasaHakmilik() {
    return wakilKuasaHakmilik;
  }

  public void setWakilHakmilik(WakilKuasaHakmilik wakilKuasaHakmilik) {
    this.wakilKuasaHakmilik = wakilKuasaHakmilik;
  }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
  
//    public WakilPihak getWakilPihak() {
//        return wakilPihak;
//    }
//
//    public void setWakilPihak(WakilPihak wakilPihak) {
//        this.wakilPihak = wakilPihak;
//    }
  public String getKodKP() {
    return kodKP;
  }

  public void setKodKP(String kodKP) {
    this.kodKP = kodKP;
  }

  public String getNoKP() {
    return noKP;
  }

  public void setNoKP(String noKP) {
    this.noKP = noKP;
  }

  public Permohonan getPermohonan() {
    return permohonan;
  }

  public void setPermohonan(Permohonan permohonan) {
    this.permohonan = permohonan;
  }

  public Pihak getPihak() {
    return pihak;
  }

  public void setPihak(Pihak pihak) {
    this.pihak = pihak;
  }

  public KodJenisPengenalan getKodjenisPengenalan() {
    return kodjenisPengenalan;
  }

  public void setKodjenisPengenalan(KodJenisPengenalan kodjenisPengenalan) {
    this.kodjenisPengenalan = kodjenisPengenalan;
  }

  public String getJenisPengenalanPemberi() {
    return jenisPengenalanPemberi;
  }

  public void setJenisPengenalanPemberi(String jenisPengenalanPemberi) {
    this.jenisPengenalanPemberi = jenisPengenalanPemberi;
  }

  public String getNamaPemberi() {
    return namaPemberi;
  }

  public void setNamaPemberi(String namaPemberi) {
    this.namaPemberi = namaPemberi;
  }

  public String getNoPengenalanPemberi() {
    return noPengenalanPemberi;
  }

  public void setNoPengenalanPemberi(String noPengenalanPemberi) {
    this.noPengenalanPemberi = noPengenalanPemberi;
  }

  public String getTmpIDpihak() {
    return tmpIDpihak;
  }

  public void setTmpIDpihak(String tmpIDpihak) {
    this.tmpIDpihak = tmpIDpihak;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public String getKuasaL1() {
    return kuasaL1;
  }

  public void setKuasaL1(String kuasaL1) {
    this.kuasaL1 = kuasaL1;
  }

  public String getKuasaL2() {
    return kuasaL2;
  }

  public void setKuasaL2(String kuasaL2) {
    this.kuasaL2 = kuasaL2;
  }

  public Hakmilik getHakmilik() {
    return hakmilik;
  }

  public void setHakmilik(Hakmilik hakmilik) {
    this.hakmilik = hakmilik;
  }

  public String getIdH() {
    return idH;
  }

  public void setIdHakmilik(String idH) {
    this.idH = idH;
  }

  public List<WakilKuasaHakmilik> getwKuasaHakmilik() {
    return wKuasaHakmilik;
  }

  public void setwKuasaHakmilik(List<WakilKuasaHakmilik> wKuasaHakmilik) {
    this.wKuasaHakmilik = wKuasaHakmilik;
  }

  public String getAlamat1() {
    return alamat1;
  }

  public void setAlamat1(String alamat1) {
    this.alamat1 = alamat1;
  }

  public String getAlamat2() {
    return alamat2;
  }

  public void setAlamat2(String alamat2) {
    this.alamat2 = alamat2;
  }

  public String getAlamat3() {
    return alamat3;
  }

  public void setAlamat3(String alamat3) {
    this.alamat3 = alamat3;
  }

  public String getAlamat4() {
    return alamat4;
  }

  public void setAlamat4(String alamat4) {
    this.alamat4 = alamat4;
  }

  public KodNegeri getNegeri() {
    return negeri;
  }

  public void setNegeri(KodNegeri negeri) {
    this.negeri = negeri;
  }

  public String getNegeriPenerima() {
    return negeriPenerima;
  }

  public void setNegeriPenerima(String negeriPenerima) {
    this.negeriPenerima = negeriPenerima;
  }

  public String getPoskod() {
    return poskod;
  }

  public void setPoskod(String poskod) {
    this.poskod = poskod;
  }

  public List<HakmilikPihakBerkepentingan> getHakmilikPihakBerkepentinganList() {
    return hakmilikPihakBerkepentinganList;
  }

  public void setHakmilikPihakBerkepentinganList(List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList) {
    this.hakmilikPihakBerkepentinganList = hakmilikPihakBerkepentinganList;
  }

  public List<HakmilikPihakBerkepentingan> getHakmilikPihakBerkepentinganList2() {
    return hakmilikPihakBerkepentinganList2;
  }

  public void setHakmilikPihakBerkepentinganList2(List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList2) {
    this.hakmilikPihakBerkepentinganList2 = hakmilikPihakBerkepentinganList2;
  }

  public String getIdPihakPemberi() {
    return idPihakPemberi;
  }

  public void setIdPihakPemberi(String idPihakPemberi) {
    this.idPihakPemberi = idPihakPemberi;
  }

  public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
    return hakmilikPihakBerkepentingan;
  }

  public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
    this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
  }

  public WakilKuasa getWakilKuasa() {
    return wakilKuasa;
  }

  public void setWakilKuasa(WakilKuasa wakilKuasa) {
    this.wakilKuasa = wakilKuasa;
  }

  public WakilKuasaPemberi getWakilKuasaPemberi() {
    return wakilKuasaPemberi;
  }

  public void setWakilKuasaPemberi(WakilKuasaPemberi wakilKuasaPemberi) {
    this.wakilKuasaPemberi = wakilKuasaPemberi;
  }

  public WakilKuasaPenerima getWakilKuasaPenerima() {
    return wakilKuasaPenerima;
  }

  public void setWakilKuasaPenerima(WakilKuasaPenerima wakilKuasaPenerima) {
    this.wakilKuasaPenerima = wakilKuasaPenerima;
  }

  public String getHakmilikWakil() {
    return hakmilikWakil;
  }

  public void setHakmilikWakil(String hakmilikWakil) {
    this.hakmilikWakil = hakmilikWakil;
  }

  public List<WakilKuasa> getwKuasa() {
    return wKuasa;
  }

  public void setwKuasa(List<WakilKuasa> wKuasa) {
    this.wKuasa = wKuasa;
  }

  public List<Pihak> getPihakList() {
    return pihakList;
  }

  public void setPihakList(List<Pihak> pihakList) {
    this.pihakList = pihakList;
  }

  public String getIdPenerima() {
    return idPenerima;
  }

  public void setIdPenerima(String idPenerima) {
    this.idPenerima = idPenerima;
  }

  public String getIdPemberi() {
    return idPemberi;
  }

  public void setIdPemberi(String idPemberi) {
    this.idPemberi = idPemberi;
  }

  public String getAmaunMaksima() {
    return amaunMaksima;
  }

  public void setAmaunMaksima(String amaunMaksima) {
    this.amaunMaksima = amaunMaksima;
  }

  public String getSyaratTambahan() {
    return syaratTambahan;
  }

  public void setSyaratTambahan(String syaratTambahan) {
    this.syaratTambahan = syaratTambahan;
  }

  public String getjPihak() {
    return jPihak;
  }

  public void setjPihak(String jPihak) {
    this.jPihak = jPihak;
  }

  public List<KandunganFolder> getSenaraiKF() {
    return senaraiKF;
  }

  public void setSenaraiKF(List<KandunganFolder> senaraiKF) {
    this.senaraiKF = senaraiKF;
  }

  public List<String> getSenaraiSW() {
    return senaraiSW;
  }

  public void setSenaraiSW(List<String> senaraiSW) {
    this.senaraiSW = senaraiSW;
  }

  public List<Dokumen> getDokumen() {
    return dokumen;
  }

  public void setDokumen(List<Dokumen> dokumen) {
    this.dokumen = dokumen;
  }

  public String[] getIdHakmilikSW() {
    return idHakmilikSW;
  }

  public void setIdHakmilikSW(String[] idHakmilikSW) {
    this.idHakmilikSW = idHakmilikSW;
  }

  public String[] getNoRujukan() {
    return noRujukan;
  }

  public void setNoRujukan(String[] noRujukan) {
    this.noRujukan = noRujukan;
  }

  public PermohonanDokumen getpDokumen() {
    return pDokumen;
  }

  public void setpDokumen(PermohonanDokumen pDokumen) {
    this.pDokumen = pDokumen;
  }

  public List<PermohonanDokumen> getPdl() {
    return pdl;
  }

  public void setPdl(List<PermohonanDokumen> pdl) {
    this.pdl = pdl;
  }

  public String getIdHakDelete() {
    return idHakDelete;
  }

  public void setIdHakDelete(String idHakDelete) {
    this.idHakDelete = idHakDelete;
  }

  public String getIdRujDelete() {
    return idRujDelete;
  }

  public void setIdRujDelete(String idRujDelete) {
    this.idRujDelete = idRujDelete;
  }

  public PermohonanDokumen getpD() {
    return pD;
  }

  public void setpD(PermohonanDokumen pD) {
    this.pD = pD;
  }

  public WakilKuasa getWakilKuasaSB() {
    return wakilKuasaSB;
  }

  public void setWakilKuasaSB(WakilKuasa wakilKuasaSB) {
    this.wakilKuasaSB = wakilKuasaSB;
  }

  public List<WakilKuasa> getwKuasaSB() {
    return wKuasaSB;
  }

  public void setwKuasaSB(List<WakilKuasa> wKuasaSB) {
    this.wKuasaSB = wKuasaSB;
  }

  public List<Map<String, Object>> getListSB() {
    return listSB;
  }

  public void setListSB(List<Map<String, Object>> listSB) {
    this.listSB = listSB;
  }

  public String getFlagSB() {
    return flagSB;
  }

  public void setFlagSB(String flagSB) {
    this.flagSB = flagSB;
  }

  public String[] getKuasaPindahMilik() {
    return kuasaPindahMilik;
  }

  public void setKuasaPindahMilik(String[] kuasaPindahMilik) {
    this.kuasaPindahMilik = kuasaPindahMilik;
  }

  public String[] getKuasaPajak() {
    return kuasaPajak;
  }

  public void setKuasaPajak(String[] kuasaPajak) {
    this.kuasaPajak = kuasaPajak;
  }

  public String[] getKuasaGadai() {
    return kuasaGadai;
  }

  public void setKuasaGadai(String[] kuasaGadai) {
    this.kuasaGadai = kuasaGadai;
  }

  public List<HakmilikPermohonan> getPdlSBlist() {
    return pdlSBlist;
  }

  public void setPdlSBlist(List<HakmilikPermohonan> pdlSBlist) {
    this.pdlSBlist = pdlSBlist;
  }
}
//test

