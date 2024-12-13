/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanCarian;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import org.displaytag.util.ParamEncoder;
import org.displaytag.tags.TableTagParameters;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ei
 */
@UrlBinding("/daftar/tukar_ganti_hakmilik/cetakan")
public class CetakanTukarGantiHakmilikActionBean extends AbleActionBean {

  @Inject
  DokumenDAO dokumenDAO;
  @Inject
  KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
  @Inject
  DokumenCapaianDAO dokumenCapaianDAO;
  @Inject
  PermohonanService permohonanDAO;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  private int bilHakmilik = 1;
  private String idMohon;
  private String idHakmilik;
  private Pengguna pguna;
  private List<HakmilikPermohonan> senaraiPermohonan;
  private HakmilikUrusan hakmilikUrusan;
  private List<HakmilikUrusan> senaraiHakmilikUrusan = new ArrayList<HakmilikUrusan>();
  private List<Dokumen> senaraiDokumen;
  private List<String> idHakmilikSiriDari = new ArrayList<String>();
  private List<String> idHakmilikSiriHingga = new ArrayList<String>();
  private List<Map<String, String>> senaraiCetakan;
  private boolean kelompok = false;
  private static final Logger LOG = Logger.getLogger(CetakanTukarGantiHakmilikActionBean.class);
  private static String CETAKAN_BERKELOMPOK = "daftar/utiliti/tukarganti_cetakan_berkelompok.jsp";

  @DefaultHandler
  public Resolution cetakanBerkelompok() {
    setKelompok(Boolean.TRUE);
    return new JSP(CETAKAN_BERKELOMPOK);
  }

  @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!cetakanBerkelompok"})
  public void rehydrate() {
    pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
  }

  public Resolution searchPartial() {
    ParamEncoder encoder = new ParamEncoder("line");
    String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
    LOG.info(" --> page : " + page);
    LOG.info(" --> get__pg_start : " + get__pg_start());
    LOG.info(" --> get__pg_max_records() : " + get__pg_max_records());
    LOG.info(" --> id mohon : " + idMohon);
    LOG.info(" --> id hakmilik : " + idHakmilik);

    if (StringUtils.isNotBlank(page)) {
      set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
      set__pg_max_records(get__pg_start() + get__pg_max_records());
    }

    if (StringUtils.isNotBlank(idMohon) || StringUtils.isNotBlank(idHakmilik)) {

      senaraiPermohonan = getSenaraiPermohonanPartial(idMohon, idHakmilik, pguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records());
      LOG.info(" --> senaraiPermohonan size : " + senaraiPermohonan.size());

      set__pg_total_records(getTotalRecord(idMohon, idHakmilik, pguna.getKodCawangan().getKod()).intValue());

      if (senaraiPermohonan.isEmpty()) {
        LOG.info("-- NO FILE FOUND");
        addSimpleError("Tiada Maklumat dijumpai.");
      }
    }
    return new JSP(CETAKAN_BERKELOMPOK);
  }

  public Resolution search() {
    String idPermohonan = getContext().getRequest().getParameter("id");
    String idHm = getContext().getRequest().getParameter("id2");

    if (StringUtils.isNotBlank(idPermohonan) && StringUtils.isNotBlank(idHm)) {
      Permohonan p = permohonanDAO.findById(idPermohonan);
      if (p != null) {
        FolderDokumen fd = p.getFolderDokumen();
        List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
        senaraiDokumen = new ArrayList<Dokumen>();

        for (KandunganFolder kandunganFolder : senaraiKand) {
          Dokumen d = kandunganFolder.getDokumen();
          if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
            if (pguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
              if (d.getKodDokumen().getKod().equalsIgnoreCase("DHDE")
                      || d.getKodDokumen().getKod().equalsIgnoreCase("DHKE")
                      || d.getKodDokumen().getKod().equalsIgnoreCase("DHDKC")
                      || d.getKodDokumen().getKod().equalsIgnoreCase("DHDKC")
                      || d.getKodDokumen().getKod().equalsIgnoreCase("PB1KE")
                      || d.getKodDokumen().getKod().equalsIgnoreCase("PB1DE")) {
                // FILTER BY KOD DOKUMEN 
                // *NOTES: DHDKC -> JADUAL 14, PB1DE/PB1KE -> PELAN, DHDE/DHKE -> GERAN
                senaraiDokumen.add(d);
              }
            }
          }
        }

        if (p.getKeputusan() != null) {
          //Hakmilik Batal
          List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
          List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
          List<HakmilikSebelumPermohonan> senaraiHakmilikBatal = new ArrayList<HakmilikSebelumPermohonan>();

          for (HakmilikPermohonan hp : senaraiHakmilik) {
            if (hp == null || hp.getHakmilik() == null) {
              continue;
            }

            l_temp = hp.getSenaraiHakmilikSebelum();
            for (HakmilikSebelumPermohonan sebelumPermohonan : l_temp) {
              if (sebelumPermohonan == null) {
                continue;
              }
              senaraiHakmilikBatal.add(sebelumPermohonan);
            }
          }

          String tempIdHakmilikSejarah = "";
          for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : senaraiHakmilikBatal) {
            if (hakmilikSebelumPermohonan == null) {
              continue;
            }

            if (tempIdHakmilikSejarah.equals(hakmilikSebelumPermohonan.getHakmilikSejarah())) {
              continue;
            }

            Dokumen doc = hakmilikSebelumPermohonan.getHakmilik().getDhde();
            senaraiDokumen.add(doc);
            tempIdHakmilikSejarah = hakmilikSebelumPermohonan.getHakmilikSejarah();
          }
        }
      }
    }
    return new JSP(CETAKAN_BERKELOMPOK);
  }

  private ArrayList<String> getNoHakmilikBerkelompok(String idHmDari, String idHmHingga) {
    // GET ALL ID HAKMILIK BERSIRI 
    StringBuilder from = new StringBuilder();

    for (int i = idHmDari.length() - 1; i >= 0; i--) {
      char c = idHmDari.charAt(i);
      if (c >= '0' && c <= '9') {
        from.insert(0, c);
      } else {
        break;
      }
    }

    long lFrom = Long.parseLong(from.toString()); // from
    String pre = idHmDari.substring(0, idHmDari.length() - from.length());
    long lTo = 0l; // to
    try {
      lTo = Long.parseLong(idHmHingga.substring(pre.length(), idHmDari.length()));
    } catch (NumberFormatException e) {
      throw new RuntimeException("Id Hakmilik bersiri tidak sah");
    }

    ArrayList<String> listIdHakmilik = new ArrayList<String>();
    // validate the series along the way
    if (idHmDari.length() != idHmHingga.length()
            || !idHmDari.substring(0, pre.length()).equals(idHmHingga.substring(0, pre.length()))
            || lTo < lFrom) {
      throw new RuntimeException("Id Hakmilik bersiri tidak sah");
    } else {
      listIdHakmilik.add(idHmDari); // add first array
      DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
      df.setGroupingUsed(false);
      df.setMinimumIntegerDigits(from.length());
      for (long l = lFrom + 1; l < lTo; l++) {
        String id = pre + df.format(l);
        listIdHakmilik.add(id);
      }
      listIdHakmilik.add(idHmHingga); // add last array
    }
    return listIdHakmilik;
  }

  public Resolution cetakSemula() {
    String sbbCetak = getContext().getRequest().getParameter("sbb");
    String idDok = getContext().getRequest().getParameter("id");
    Dokumen d = dokumenDAO.findById(Long.parseLong(idDok));

    if (d == null) {
      return new StreamingResolution("text/plain", "2");
    }

    DokumenCapaian dc = new DokumenCapaian();
    if (StringUtils.isNotBlank(sbbCetak)) {
      dc.setAlasan("CETAKAN SEMULA [ " + sbbCetak + " ]");
    }

    dc.setDokumen(d);
    dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("CD"));
    InfoAudit ia = new InfoAudit();
    ia.setDimasukOleh(pguna);
    ia.setTarikhMasuk(new java.util.Date());
    dc.setInfoAudit(ia);

    Session s = sessionProvider.get();
    Transaction tx = s.beginTransaction();
    dokumenCapaianDAO.save(dc);
    tx.commit();

    return new StreamingResolution("text/plain", "1");
  }

  public Resolution cetakBerkelompok() {
    String sbbCetak = getContext().getRequest().getParameter("sbb");
    String[] id = getContext().getRequest().getParameterValues("idCetak");
    for (String string : id) {
      String idDok = string;
      Dokumen d = dokumenDAO.findById(Long.parseLong(idDok));

      if (d == null) {
        return new StreamingResolution("text/plain", "2");
      }

      DokumenCapaian dc = new DokumenCapaian();
      if (StringUtils.isNotBlank(sbbCetak)) {
        dc.setAlasan("CETAKAN SEMULA [ " + sbbCetak + " ]");
      }

      dc.setDokumen(d);
      dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("CD"));
      InfoAudit ia = new InfoAudit();
      ia.setDimasukOleh(pguna);
      ia.setTarikhMasuk(new java.util.Date());
      dc.setInfoAudit(ia);

      Session s = sessionProvider.get();
      Transaction tx = s.beginTransaction();
      dokumenCapaianDAO.save(dc);
      tx.commit();
    }
    return new StreamingResolution("text/plain", "1");
  }

  public Hakmilik findHakmilikVersion(String idHakmilik) {
    String query = "SELECT h FROM etanah.model.Hakmilik h "
            + "WHERE h.idHakmilik = :idHakmilik and h.noVersiDhde in ('1','0') and h.kodStatusHakmilik = 'D' ";
    Query q = sessionProvider.get().createQuery(query);
    q.setString("idHakmilik", idHakmilik);
    return (Hakmilik) q.uniqueResult();
  }

  public HakmilikUrusan findUrusanTukarganti(String idMohon, String idHakmilik) {
    String query = "SELECT hu FROM etanah.model.HakmilikUrusan hu "
            + "WHERE hu.idPerserahan = :idPerserahan "
            + "and hu.kodUrusan.kod in ('HKTHK','HSTHK','HMSC') "
            + "and hu.aktif = 'Y' "
            + "ORDER BY hu.hakmilik.idHakmilik";
    Query q = sessionProvider.get().createQuery(query);
    q.setString("idPerserahan", idMohon).setString("idHakmilik", idHakmilik);
    return (HakmilikUrusan) q.uniqueResult();
  }

  public List<HakmilikUrusan> findListUrusanTukarganti(String idMohon) {
    // FIND LIST URUSAN 'HKTHK','HSTHK','HMSC'
    String query = "SELECT hu FROM etanah.model.HakmilikUrusan hu "
            + "WHERE hu.idPerserahan = :idPerserahan "
            + "and hu.kodUrusan.kod in ('HKTHK','HSTHK','HMSC') "
            + "and hu.aktif = 'Y' "
            + "ORDER BY hu.hakmilik.idHakmilik";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("idPerserahan", idMohon);
    return q.list();
  }

  public List<HakmilikPermohonan> getSenaraiPermohonanPartial(String idPermohonan, String idHakmilik, String kodCaw, int start, int max) {
    String q = "";

    if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
      q = "Select hp FROM etanah.model.HakmilikPermohonan hp, etanah.model.Permohonan m ";
      q += "WHERE hp.permohonan.idPermohonan like :idPermohonan ";
      q += "and m.idPermohonan = hp.permohonan.idPermohonan ";
      q += "and m.kodUrusan.kod in ('HKTHK','HSTHK','HMSC') ";
    } else {
      q = "SELECT hp FROM etanah.model.HakmilikPermohonan hp, etanah.model.Permohonan m ";
      q += "WHERE m.idPermohonan = hp.permohonan.idPermohonan ";
      q += "and m.kodUrusan.kod in ('HKTHK','HSTHK','HMSC') ";
      q += "and hp.hakmilik.idHakmilik = :idHakmilik ";
    }

    if (StringUtils.isNotBlank(kodCaw)) {
      q += "and m.cawangan.kod = :caw ";
    }
    q += "ORDER BY hp.permohonan.idPermohonan asc ";
    Query query = sessionProvider.get().createQuery(q);

    if (StringUtils.isNotBlank(idPermohonan)) {
      query.setString("idPermohonan", "%" + idPermohonan + "%");
    } else {
      query.setString("idHakmilik", idHakmilik);
    }

    query.setFirstResult(start);
    query.setMaxResults(max);
    if (StringUtils.isNotBlank(kodCaw)) {
      query.setParameter("caw", kodCaw);
    }
    return query.list();
  }

  public Long getTotalRecord(String idPermohonan, String idHakmilik, String kodCaw) {
    String q = "";

    if (StringUtils.isNotBlank(idPermohonan)) {
      q = "Select count(m) from etanah.model.Permohonan m where m.idPermohonan like :idPermohonan and m.kodUrusan.kod in ('HKTHK','HSTHK','HMSC')";
    } else {
      q = "SELECT count(m) FROM etanah.model.Permohonan m, etanah.model.HakmilikPermohonan hp ";
      q += "WHERE m.idPermohonan = hp.permohonan.idPermohonan and m.kodUrusan.kod in ('HKTHK','HSTHK','HMSC') ";
      q += "AND hp.hakmilik.idHakmilik = :idHakmilik";
    }

    if (StringUtils.isNotBlank(kodCaw)) {
      q += " and m.cawangan.kod = :caw";
    }
    Query query = sessionProvider.get().createQuery(q);
    if (StringUtils.isNotBlank(idPermohonan)) {
      query.setString("idPermohonan", "%" + idPermohonan + "%");
    } else {
      query.setString("idHakmilik", idHakmilik);
    }

    if (StringUtils.isNotBlank(kodCaw)) {
      query.setParameter("caw", kodCaw);
    }
    return (Long) query.iterate().next();
  }

  public boolean isKelompok() {
    return kelompok;
  }

  public void setKelompok(boolean kelompok) {
    this.kelompok = kelompok;
  }

  public int getBilHakmilik() {
    return bilHakmilik;
  }

  public void setBilHakmilik(int bilHakmilik) {
    this.bilHakmilik = bilHakmilik;
  }

  public List<String> getIdHakmilikSiriDari() {
    return idHakmilikSiriDari;
  }

  public void setIdHakmilikSiriDari(List<String> idHakmilikSiriDari) {
    this.idHakmilikSiriDari = idHakmilikSiriDari;
  }

  public List<String> getIdHakmilikSiriHingga() {
    return idHakmilikSiriHingga;
  }

  public void setIdHakmilikSiriHingga(List<String> idHakmilikSiriHingga) {
    this.idHakmilikSiriHingga = idHakmilikSiriHingga;
  }

  public List<Map<String, String>> getSenaraiCetakan() {
    return senaraiCetakan;
  }

  public void setSenaraiCetakan(List<Map<String, String>> senaraiCetakan) {
    this.senaraiCetakan = senaraiCetakan;
  }

  public String getIdMohon() {
    return idMohon;
  }

  public void setIdMohon(String idMohon) {
    this.idMohon = idMohon;
  }

  public List<HakmilikUrusan> getSenaraiHakmilikUrusan() {
    return senaraiHakmilikUrusan;
  }

  public void setSenaraiHakmilikUrusan(List<HakmilikUrusan> senaraiHakmilikUrusan) {
    this.senaraiHakmilikUrusan = senaraiHakmilikUrusan;
  }

  public String getIdHakmilik() {
    return idHakmilik;
  }

  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;
  }

  public List<HakmilikPermohonan> getSenaraiPermohonan() {
    return senaraiPermohonan;
  }

  public void setSenaraiPermohonan(List<HakmilikPermohonan> senaraiPermohonan) {
    this.senaraiPermohonan = senaraiPermohonan;
  }

  public HakmilikUrusan getHakmilikUrusan() {
    return hakmilikUrusan;
  }

  public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
    this.hakmilikUrusan = hakmilikUrusan;
  }

  public List<Dokumen> getSenaraiDokumen() {
    return senaraiDokumen;
  }

  public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
    this.senaraiDokumen = senaraiDokumen;
  }
}
