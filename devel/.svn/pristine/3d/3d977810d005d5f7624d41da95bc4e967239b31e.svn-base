/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.KaunterService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@HttpCache(allow = false)
@UrlBinding("/daftar/utiliti/dokumenTertinggal")
public class TambahDokumenTertinggalActionBean extends AbleActionBean {
  
  @Inject
  private FolderDokumenDAO folderDAO;
  @Inject
  private PermohonanDAO permohonanDAO;
  @Inject
  private DokumenDAO dokumenDAO;
  @Inject
  private KodKlasifikasiDAO kodKlasifikasiDAO;
  @Inject
  private KodDokumenDAO kodDokumenDAO;
  @Inject
  private KaunterService kaunterService;
  @Inject
  private DokumenService dokumenService;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  private HakmilikPermohonanService hakmilikPermohonanService;
  private Permohonan permohonan;
  private FolderDokumen folderDokumen;
  private FolderDokumen folderDokumenSebelum;
  private Pengguna pengguna;
  private String idPermohonan;
  private String idHakmilik;
  private boolean refresh = false;
  private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
  private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
  private Map<String, String> kodMap = new HashMap<String, String>();
  private Map<String, String> kodMapSebelum = new HashMap<String, String>();
  private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
  private List<KandunganFolder> senaraiKandunganSebelum = new ArrayList<KandunganFolder>();
  private static final Logger LOG = Logger.getLogger(TambahDokumenTertinggalActionBean.class);
  
  @DefaultHandler
  public Resolution view() {
    return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/dokumen_tertinggal_main.jsp");
  }
  
  public Resolution search() throws WorkflowException {
    if (permohonan == null) {
      return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/dokumen_tertinggal_main.jsp");
    }
    
    idPermohonan = permohonan.getIdPermohonan();
    
    if (idPermohonan == null) {
      addSimpleError("Sila masukkan ID Permohonan/Perserahan");
      return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/dokumen_tertinggal_main.jsp");
    }
    
    permohonan = permohonanDAO.findById(idPermohonan);
    if (permohonan == null) {
      addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
      return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/dokumen_tertinggal_main.jsp");
    } else {
      pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
      senaraiKandungan = new ArrayList<KandunganFolder>();
//      senaraiKandunganSebelum = new ArrayList<Dokumen>();
      senaraiKandunganSebelum = new ArrayList<KandunganFolder>();
      
      if (pengguna != null) {
        getContext().getRequest().setAttribute("idPengguna", pengguna.getIdPengguna());
      }
      
      String id = getContext().getRequest().getParameter("folder.idFolder");
      String filter = getContext().getRequest().getParameter("filterBy"); // to filter dokumen current permohonan
      String filter2 = getContext().getRequest().getParameter("filterBy2"); // to filter dokumen permohonan sebelum
      if (id != null && id.length() > 0) {
        folderDokumen = folderDAO.findById(Long.valueOf(id));
      } else {
        id = getContext().getRequest().getParameter("permohonan.idPermohonan");
        
        if (id != null && id.length() > 0) {
          permohonan = permohonanDAO.findById(id);
          
          if (permohonan == null) {
            addSimpleError("ID Permohonan " + id + " tidak wujud");
            return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/dokumen_tertinggal_main.jsp");
          }
          
          folderDokumen = permohonan.getFolderDokumen();
          if (permohonan.getPermohonanSebelum() != null) {
            folderDokumenSebelum = permohonan.getPermohonanSebelum().getFolderDokumen();
            
            for (KandunganFolder kf : folderDokumenSebelum.getSenaraiKandungan()) {
              if (kf == null || kf.getDokumen() == null) {
                continue;
              }
              KodDokumen kd = kf.getDokumen().getKodDokumen();
              if (kd == null) {
                continue;
              }
              kodMapSebelum.put(kd.getKod(), kd.getNama());
              if (StringUtils.isNotBlank(filter2)) {
                if (kd.getKod().equals(filter2)) {
//                senaraiKandunganSebelum.add(kf.getDokumen());
                  senaraiKandunganSebelum.add(kf);
                }
              } else {
//              senaraiKandunganSebelum.add(kf.getDokumen());
                senaraiKandunganSebelum.add(kf);
              }
            }
          }
          
          if (permohonan != null) {
            senaraiKodUrusan.add(permohonan.getKodUrusan().getKod());
          }

          //for filtering purposes
          for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
            if (kf == null || kf.getDokumen() == null) {
              continue;
            }
            KodDokumen kd = kf.getDokumen().getKodDokumen();
            if (kd != null) {
              kodMap.put(kd.getKod(), kd.getNama());
            }
            if (StringUtils.isNotBlank(filter)) {
              if (kd.getKod().equals(filter)) {
                senaraiKandungan.add(kf);
              }
            } else {
              senaraiKandungan.add(kf);
            }
          }
        } else {
          addSimpleError("Folder tidak ditentukan.");
        }
      }
    }
    return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tambah_dokumen_tertinggal.jsp");
  }
  
  public Resolution saveFolderInfo() {
    Session s = sessionProvider.get();
    Transaction tx = s.beginTransaction();
    tx.begin();
//        Pengguna pguna = (Pengguna)getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    InfoAudit ia = new InfoAudit();
    ia.setDiKemaskiniOleh(pengguna);
    ia.setTarikhKemaskini(new java.util.Date());
    
    try {
      folderDAO.saveOrUpdate(folderDokumen);
      tx.commit();
      addSimpleMessage("Kemasukan Data Berjaya.");
    } catch (Exception ex) {
      addSimpleError("Kemasukan Data Gagal." + ex.getMessage());
      tx.rollback();
    }
    
    return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tambah_dokumen_tertinggal.jsp");
  }
  
  public Resolution reload() {
    return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tambah_dokumen_tertinggal.jsp");
  }
  
  public Resolution viewPDF() {
    return new ForwardResolution("/WEB-INF/jsp/dokumen/uploadFileApplet.jsp");
  }
  
  @HandlesEvent("addDocForm")
  public Resolution addDocForm() {
    // reset the additional documents submitted to 10
    if (kandunganFolderTamb.isEmpty()) {
      for (int i = 0; i < 5; i++) {
        KandunganFolder kf = new KandunganFolder();
        kandunganFolderTamb.add(kf);
      }
    }
    return new JSP("dokumen/dokumen_tambahan.jsp").addParameter("popup", "true");
  }
  
  public Resolution deleteSelected() {
    
    String[] ids = getContext().getRequest().getParameterValues("chkbox");
    idPermohonan = getContext().getRequest().getParameter("permohonan.idPermohonan");
    
    if (StringUtils.isNotBlank(idPermohonan)) {
      permohonan = permohonanDAO.findById(idPermohonan);
    }
    
    Session s = sessionProvider.get();
    Transaction tx = s.beginTransaction();
    tx.begin();
    
    try {
      for (String id : ids) {
        Dokumen d = dokumenDAO.findById(Long.parseLong(id));
        if (d != null) {
          List<HakmilikPermohonan> senarai = permohonan.getSenaraiHakmilik();
          for (HakmilikPermohonan hp : senarai) {
            if (hp.getDokumen1() != null && hp.getDokumen1().getIdDokumen() == d.getIdDokumen()) {
              hp.setDokumen1(null);
            }
            if (hp.getDokumen2() != null && hp.getDokumen2().getIdDokumen() == d.getIdDokumen()) {
              hp.setDokumen2(null);
            }
            if (hp.getDokumen3() != null && hp.getDokumen3().getIdDokumen() == d.getIdDokumen()) {
              hp.setDokumen3(null);
            }
            if (hp.getDokumen4() != null && hp.getDokumen4().getIdDokumen() == d.getIdDokumen()) {
              hp.setDokumen4(null);
            }
            if (hp.getDokumen5() != null && hp.getDokumen5().getIdDokumen() == d.getIdDokumen()) {
              hp.setDokumen5(null);
            }
            hakmilikPermohonanService.saveOrUpdateWithoutConnection(hp);
          }
          dokumenDAO.delete(d);
        }
      }
    } catch (Exception ex) {
      tx.rollback();
      Throwable t = ex;
      // getting the root cause
      while (t.getCause() != null) {
        t = t.getCause();
      }
      t.printStackTrace();
      addSimpleError(t.getMessage());
    }
    tx.commit();
    return new RedirectResolution(TambahDokumenTertinggalActionBean.class, "search").addParameter("permohonan.idPermohonan", permohonan.getIdPermohonan());
  }
  
  public Resolution saveEditInfo() {
    String result = "0";
    String tajuk = getContext().getRequest().getParameter("tajuk");
    String idDok = getContext().getRequest().getParameter("idDok");
    
    if (StringUtils.isNotBlank(idDok)) {
      Dokumen d = dokumenDAO.findById(Long.valueOf(idDok));
      if (d != null) {
        d.setTajuk(tajuk);
        dokumenService.saveOrUpdate(d);
        result = "1";
      }
    }
    return new StreamingResolution("text/plain", result);
  }
  
  public Resolution simpanDokumenTambahan() {
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    pengguna = ctx.getUser();
    Date now = new Date();
    InfoAudit ia = new InfoAudit();
    ia.setDimasukOleh(pengguna);
    ia.setTarikhMasuk(now);
    KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
    List<KandunganFolder> akf = new ArrayList<KandunganFolder>();
    Session s = sessionProvider.get();
    Transaction tx = s.beginTransaction();
    
    try {
      for (KandunganFolder f : kandunganFolderTamb) {
        if (f == null) {
          continue;
        }
        Dokumen d = f.getDokumen();
        if (d == null || d.getKodDokumen() == null) {
          continue;
        }
        
        KodDokumen kd = kodDokumenDAO.findById(d.getKodDokumen().getKod());
        if (StringUtils.isBlank(kd.getKod())) {
          continue;
        }
        String c = f.getCatatan();
        if ((kd != null && !kd.getKod().equals("0"))
                || (c != null && c.trim().length() > 0)) {
//                    if (kd != null && !kd.getKod().equals("0")) { // the type not set
//                        d.setKodDokumen(null);
//                    }
//                    d.setCatatanMinit(c);
          d.setInfoAudit(ia);
          d.setTajuk(kd == null ? "KIV" : kd.getNama());
          d.setNoVersi("1.0");
          d.setKlasifikasi(klasifikasiAm);
          dokumenDAO.save(d);
          f.setCatatan(c);
          f.setFolder(folderDokumen);
          f.setInfoAudit(ia);
          akf.add(f);
        }
      }
      LOG.info(" ..akf.size() : " + akf.size());
      if (akf.size() > 0) {
        folderDokumen.setSenaraiKandungan(akf);
      }
      
      folderDAO.save(folderDokumen);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      Throwable t = e;
      // getting the root cause
      while (t.getCause() != null) {
        t = t.getCause();
      }
      t.printStackTrace();
      addSimpleError(t.getMessage());
    }
    setRefresh(Boolean.TRUE);
    return new RedirectResolution(TambahDokumenTertinggalActionBean.class, "search").addParameter("permohonan.idPermohonan", permohonan.getIdPermohonan());
  }
  
  public String getIdPermohonan() {
    return idPermohonan;
  }
  
  public void setIdPermohonan(String idPermohonan) {
    this.idPermohonan = idPermohonan;
  }
  
  public void setFolderDokumen(FolderDokumen folderDokumen) {
    this.folderDokumen = folderDokumen;
  }
  
  public FolderDokumen getFolderDokumen() {
    return folderDokumen;
  }
  
  public FolderDokumen getFolderDokumenSebelum() {
    return folderDokumenSebelum;
  }
  
  public void setFolderDokumenSebelum(FolderDokumen folderDokumenSebelum) {
    this.folderDokumenSebelum = folderDokumenSebelum;
  }
  
  public String getIdHakmilik() {
    return idHakmilik;
  }
  
  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;
  }
  
  public void setPermohonan(Permohonan permohonan) {
    this.permohonan = permohonan;
  }
  
  public Permohonan getPermohonan() {
    return this.permohonan;
  }
  
  public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
    this.kandunganFolderTamb = kandunganFolderTamb;
  }
  
  public List<KandunganFolder> getKandunganFolderTamb() {
    return kandunganFolderTamb;
  }
  
  public List<KodDokumen> getKodDokumenNotRequired() {
    return kaunterService.getKodDokumenNotRequired(senaraiKodUrusan);
  }
  
  public Map<String, String> getKodMap() {
    return kodMap;
  }
  
  public void setKodMap(Map<String, String> kodMap) {
    this.kodMap = kodMap;
  }
  
  public Map<String, String> getKodMapSebelum() {
    return kodMapSebelum;
  }
  
  public void setKodMapSebelum(Map<String, String> kodMapSebelum) {
    this.kodMapSebelum = kodMapSebelum;
  }
  
  public List<KandunganFolder> getSenaraiKandungan() {
    return senaraiKandungan;
  }
  
  public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
    this.senaraiKandungan = senaraiKandungan;
  }
  
  public List<KandunganFolder> getSenaraiKandunganSebelum() {
    return senaraiKandunganSebelum;
  }
  
  public void setSenaraiKandunganSebelum(List<KandunganFolder> senaraiKandunganSebelum) {
    this.senaraiKandunganSebelum = senaraiKandunganSebelum;
  }
  
  public Pengguna getPengguna() {
    return pengguna;
  }
  
  public void setPengguna(Pengguna pengguna) {
    this.pengguna = pengguna;
  }
  
  public boolean isRefresh() {
    return refresh;
  }
  
  public void setRefresh(boolean refresh) {
    this.refresh = refresh;
  }
}
