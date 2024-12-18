/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.report.ReportUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"showForm", "popup"})
@UrlBinding("/hasil/semak_transaksi")
public class SemakanTransaksiActionBean extends AbleActionBean {

  private static final Logger LOG = Logger.getLogger(SemakanTransaksiActionBean.class);
  private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/semak_transaksi.jsp";
  private static final String POPUP_VIEW = "hasil/semak_transaksi_1.jsp";
  private String negeri;
  private String kew38 = null;
  private Akaun akaun;
  private Transaksi transaksi;
  private DokumenKewangan dokumenKewangan;
  private Hakmilik hakmilik;
  private DokumenKewanganBayaran dokumenKewanganBayaran;
  private TransaksiDAO transaksiDAO;
  private AkaunDAO akaunDAO;
  private HakmilikDAO hakmilikDAO;
  private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
  private DokumenKewanganDAO dokumenKewanganDAO;
  private boolean flag;
  private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
  private List<DokumenKewanganBayaran> dkbList = new ArrayList<DokumenKewanganBayaran>();

  @Inject
  public SemakanTransaksiActionBean(TransaksiDAO transaksiDAO, AkaunDAO akaunDAO, HakmilikDAO hakmilikDAO,
          DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO, DokumenKewanganDAO dokumenKewanganDAO) {
    this.transaksiDAO = transaksiDAO;
    this.akaunDAO = akaunDAO;
    this.hakmilikDAO = hakmilikDAO;
    this.dokumenKewanganBayaranDAO = dokumenKewanganBayaranDAO;
    this.dokumenKewanganDAO = dokumenKewanganDAO;
  }
  @Inject
  private ReportUtil reportUtil;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  etanah.Configuration conf;

  @DefaultHandler
  public Resolution showForm() {
    negeri = conf.getProperty("kodNegeri");
    return new ForwardResolution(DEFAULT_VIEW);
  }

  public Resolution search() {
    negeri = conf.getProperty("kodNegeri");
    if (hakmilik != null) {
      LOG.info("id Hakmilik : " + hakmilik.getIdHakmilik());
      senaraiTransaksi = searchByIDHakmilik(hakmilik.getIdHakmilik());
    } else if (dokumenKewangan != null) {
      LOG.info("id Dokumen : " + dokumenKewangan.getIdDokumenKewangan());
      senaraiTransaksi = searchByNoResit();
    } else if (kew38 != null) {
      LOG.info("kew38 : " + kew38);
      senaraiTransaksi = searchByResitManual();
    }

//    String resit = getContext().getRequest().getParameter("dokumenKewangan.idDokumenKewangan");
//    LOG.info("no resit: " + resit);
//    if (StringUtils.isNotBlank(resit)) {
//      dokumenKewangan = dokumenKewanganDAO.findById(resit);
//    }

    String[] n1 = {"dokumenKewangan"};
    Object[] v1 = {dokumenKewangan};
    dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);

    flag = true;
    return new ForwardResolution(DEFAULT_VIEW);
  }

  public List<Transaksi> searchByIDHakmilik(String idHakmilik) {
    List<Transaksi> list = new ArrayList<Transaksi>();
    hakmilik = hakmilikDAO.findById(idHakmilik);
    String[] n1 = {"hakmilik"};
    Object[] v1 = {hakmilik};
    List<Akaun> acList = akaunDAO.findByEqualCriterias(n1, v1, null);
    Akaun ac = new Akaun();
    for (Akaun ak : acList) {
      if (ak.getKodAkaun().getKod().equals("AC")) {
        ac = ak;
      }
    }

    Session s = sessionProvider.get();
    String sql = "SELECT t FROM etanah.model.Transaksi t WHERE t.akaunDebit.noAkaun = :kod OR t.akaunKredit.noAkaun = :kod AND t.dokumenKewangan != null";
    Query q = s.createQuery(sql);
    q.setString("kod", ac.getNoAkaun());
    String rst = "";
    List<Transaksi> tList = q.list();
    LOG.info("list.size() : " + tList.size());
    for (Transaksi tr : tList) {
      if (tr.getDokumenKewangan() != null) {
        if (!tr.getDokumenKewangan().getIdDokumenKewangan().equals(rst)) {
          list.add(tr);
          rst = tr.getDokumenKewangan().getIdDokumenKewangan();
          LOG.info("---> rst : " + rst);
          dokumenKewangan = dokumenKewanganDAO.findById(rst);
        }
      }
    }
    return list;
  }

  public List<Transaksi> searchByNoResit() {
    List<Transaksi> list = new ArrayList<Transaksi>();
    String resit = "";

    Session s = sessionProvider.get();

    String sqlResit = "SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.idDokumenKewangan = :kod";
    Query qResit = s.createQuery(sqlResit);
    qResit.setString("kod", dokumenKewangan.getIdDokumenKewangan());
    DokumenKewangan dk = (DokumenKewangan) qResit.uniqueResult();

    if (dk != null) {
      resit = dk.getIdDokumenKewangan();
    } else {
      String sql1 = "SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.noRujukan = :kod";
      Query q1 = s.createQuery(sql1);
      q1.setString("kod", dokumenKewangan.getIdDokumenKewangan());
      DokumenKewangan d = (DokumenKewangan) q1.uniqueResult();
      if (d != null) {
        resit = d.getIdDokumenKewangan();
      }
    }

    String sql = "SELECT t FROM etanah.model.Transaksi t WHERE t.dokumenKewangan.idDokumenKewangan = :kod";
    Query q = s.createQuery(sql);
    q.setString("kod", resit);
    String rst = "";
    List<Transaksi> tList = q.list();
    LOG.info("list.size() : " + tList.size());
    for (Transaksi tr : tList) {
      if (tr.getDokumenKewangan() != null) {
        if (!tr.getDokumenKewangan().getIdDokumenKewangan().equals(rst)) {
          list.add(tr);
          rst = tr.getDokumenKewangan().getIdDokumenKewangan();
        }
      }
    }
    return list;
  }

  public List<Transaksi> searchByResitManual() {
    List<Transaksi> list = new ArrayList<Transaksi>();

    Session s = sessionProvider.get();

    String sqlResit = "SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.noRujukanManual = :id";
    Query qResit = s.createQuery(sqlResit);
    qResit.setString("id", kew38);
    DokumenKewangan kewDok = (DokumenKewangan) qResit.uniqueResult();
    dokumenKewangan = kewDok;
    LOG.info("--> kewDok.getIdDokumenKewangan(): " + kewDok.getIdDokumenKewangan());

    String sql = "SELECT t FROM etanah.model.Transaksi t WHERE t.dokumenKewangan.idDokumenKewangan = :kod";
    Query q = s.createQuery(sql);
    q.setString("kod", kewDok.getIdDokumenKewangan());
    String rst = "";
    List<Transaksi> tList = q.list();
    LOG.info("list.size() : " + tList.size());
    for (Transaksi tr : tList) {
      if (tr.getDokumenKewangan() != null) {
        if (!tr.getDokumenKewangan().getIdDokumenKewangan().equals(rst)) {
          list.add(tr);
          rst = tr.getDokumenKewangan().getIdDokumenKewangan();
        }
      }
    }
    return list;
  }

  public Resolution popup() {
    String resit = getContext().getRequest().getParameter("resit");
    LOG.info("resit : " + resit);
    dokumenKewangan = dokumenKewanganDAO.findById(resit);

    String[] n1 = {"dokumenKewangan"};
    Object[] v1 = {dokumenKewangan};
    dkbList = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, v1, null);

    String[] n2 = {"dokumenKewangan"};
    Object[] v2 = {dokumenKewangan};
    List<Transaksi> trList = transaksiDAO.findByEqualCriterias(n2, v2, null);
    String rst = "";
    for (Transaksi tr : trList) {
      if (tr.getDokumenKewangan() != null) {
        if (!tr.getDokumenKewangan().getIdDokumenKewangan().equals(rst)) {
          transaksi = tr;
          rst = tr.getDokumenKewangan().getIdDokumenKewangan();
        }
      }
    }

    return new JSP(POPUP_VIEW).addParameter("popup", "true");
  }

  public Resolution cetak() throws FileNotFoundException {
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    Pengguna p = ctx.getUser();
    Date now = new Date();
    File f = null;
    String tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
    String idKewDok = getContext().getRequest().getParameter("idKewDok");
    LOG.info("idKewDok : " + idKewDok);
    String documentPath = File.separator + "tmp" + File.separator;
    String path = tarikh + File.separator + String.valueOf(idKewDok);
    reportUtil.generateReport("SPOCCetakanSemulaResit_MLK.rdf",
            new String[]{"p_id_kew_dok"},
            new String[]{idKewDok},
            documentPath + path, null);

    f = new File(documentPath + path);
    FileInputStream fis = new FileInputStream(f);
    return new StreamingResolution("application/pdf", fis);
  }

  public Akaun getAkaun() {
    return akaun;
  }

  public void setAkaun(Akaun akaun) {
    this.akaun = akaun;
  }

  public DokumenKewangan getDokumenKewangan() {
    return dokumenKewangan;
  }

  public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
    this.dokumenKewangan = dokumenKewangan;
  }

  public Transaksi getTransaksi() {
    return transaksi;
  }

  public void setTransaksi(Transaksi transaksi) {
    this.transaksi = transaksi;
  }

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  public List<Transaksi> getSenaraiTransaksi() {
    return senaraiTransaksi;
  }

  public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
    this.senaraiTransaksi = senaraiTransaksi;
  }

  public Hakmilik getHakmilik() {
    return hakmilik;
  }

  public void setHakmilik(Hakmilik hakmilik) {
    this.hakmilik = hakmilik;
  }

  public List<DokumenKewanganBayaran> getDkbList() {
    return dkbList;
  }

  public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
    this.dkbList = dkbList;
  }

  public DokumenKewanganBayaran getDokumenKewanganBayaran() {
    return dokumenKewanganBayaran;
  }

  public void setDokumenKewanganBayaran(DokumenKewanganBayaran dokumenKewanganBayaran) {
    this.dokumenKewanganBayaran = dokumenKewanganBayaran;
  }

  public String getNegeri() {
    return negeri;
  }

  public void setNegeri(String negeri) {
    this.negeri = negeri;
  }

  public String getKew38() {
    return kew38;
  }

  public void setKew38(String kew38) {
    this.kew38 = kew38;
  }
}
