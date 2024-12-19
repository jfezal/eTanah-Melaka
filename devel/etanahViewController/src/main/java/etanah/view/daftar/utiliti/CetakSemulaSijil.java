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
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.model.*;
import etanah.service.common.PermohonanCarianService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.security.action.Secure;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.HakmilikService;
import etanah.service.common.PermohonanCarianService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author ei
 */
@UrlBinding("/daftar/cetakSemulaResit_SijilCarian")
public class CetakSemulaSijil extends AbleActionBean {

//    private PermohonanCarian pCarian = new PermohonanCarian();
  private List<PermohonanCarian> listP = new ArrayList<PermohonanCarian>();
  private String idPermohonan;
  private String idHakmilik;
  private String idResit;
  private DokumenKewangan dokumenKewangan = new DokumenKewangan();
  private String idDokumenKewangan = null;
  private List<Dokumen> senaraiDokumen;
  private List<Permohonan> senaraiPermohonan;
  private List<PermohonanCarian> senaraiPermohonanCarian;
  private List<CarianHakmilik> senaraiCarianHakmilik;
  private Transaksi transaksi = new Transaksi();
  private List<Transaksi> listT = new ArrayList<Transaksi>();
  @Inject
  PermohonanService permohonanDAO;
  @Inject
  PermohonanCarianService carianDAO;
  @Inject
  DokumenDAO dokumenDAO;
  @Inject
  DokumenCapaianDAO dokumenCapaianDAO;
  @Inject
  KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
  @Inject
  HakmilikDAO hakmilikDAO;
  @Inject
  HakmilikService hakmilikService;
  @Inject
  PermohonanCarianService pCarianService;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  private static Logger LOG = Logger.getLogger(CetakSemulaDokumenActionBean.class);
  private static boolean IS_DEBUG = LOG.isDebugEnabled();

  @DefaultHandler
  public Resolution showForm() {
    return new JSP("daftar/utiliti/cetak_semula_resit.jsp");
  }

  @Secure(roles = "pendaftarptg")
  public Resolution searchGeran() {

    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    if (IS_DEBUG) {
      LOG.debug("idHakmilik = " + idHakmilik);
    }

    if (StringUtils.isNotBlank(idHakmilik)) {
      Hakmilik hm = hakmilikDAO.findById(idHakmilik);
      if (hm != null) {
        senaraiDokumen = new ArrayList<Dokumen>();
        Dokumen dhke = hm.getDhke();
        if (dhke == null) {
          addSimpleError("Dokumen tidak dijumpai.");
        } else {
          if (dhke.getKlasifikasi() != null
                  && pengguna.getTahapSekuriti().getKod() < dhke.getKlasifikasi().getKod()) {
            addSimpleError("Pengguna tidak boleh mencapai dokumen ini.");
            return new JSP("daftar/utiliti/cetak_semula_geran.jsp");
          }
          senaraiDokumen.add(dhke);
        }
      } else {
        addSimpleError("Hakmilik tidak dijumpai.");
      }
    }
    return new JSP("daftar/utiliti/cetak_semula_geran.jsp");
  }

  public Resolution searchPartial() {

    ParamEncoder encoder = new ParamEncoder("line");
    String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
    if (IS_DEBUG) {
      LOG.debug("======= page: " + page);
    }

    if (StringUtils.isNotBlank(page)) {
      set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
      set__pg_max_records(get__pg_start() + get__pg_max_records());
    }
    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    if (StringUtils.isNotBlank(idResit)) {
      LOG.info("-- Search By Resit: " + idResit);
      PermohonanCarian pc = carianDAO.findByIdKewDok(idResit);
      if (pc != null)
        idPermohonan = pc.getIdCarian();
      else {
          senaraiPermohonan = permohonanDAO.getPermohonanByNoResit(idResit);
      }
    }

    if (IS_DEBUG) {
      LOG.info("-- mohon : " + idPermohonan);
      LOG.info("-- hakmilik : " + idHakmilik);
      LOG.info("-- resit : " + idResit);
    }

    if (StringUtils.isNotBlank(idPermohonan) || StringUtils.isNotBlank(idHakmilik)) {
      
      senaraiPermohonan = permohonanDAO.getSenaraiPermohonanPartial(idPermohonan, idHakmilik,
              pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records());

      set__pg_total_records(permohonanDAO.getTotalRecord(idPermohonan, idHakmilik, pengguna.getKodCawangan().getKod()).intValue());
      
      if (senaraiPermohonan.isEmpty()) {
        // search record in table carian. only work if search by id mohon
        senaraiPermohonanCarian = carianDAO.getSenaraiPermohonanPartial(idPermohonan,
                pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records());
      }

      if (StringUtils.isNotBlank(idHakmilik)) { 
        //search record from table carian_hakmilik
        senaraiCarianHakmilik = carianDAO.findCarianByIdHakmilik(idHakmilik,
                pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records());
        
        senaraiPermohonanCarian = new ArrayList<PermohonanCarian>();
        for (CarianHakmilik carianHm : senaraiCarianHakmilik) { 
          // find all carian for id hakmilik
          PermohonanCarian mohoncarian = carianDAO.findById(carianHm.getPermohonanCarian().getIdCarian());
          senaraiPermohonanCarian.add(mohoncarian);
        }
      }
    }
    
    if (senaraiPermohonan.isEmpty() && senaraiPermohonanCarian.isEmpty()) {
        addSimpleError("Tiada Maklumat dijumpai.");
      }
    return new JSP("daftar/utiliti/cetak_semula_resit.jsp");
  }

  public Resolution search() {

    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    if (StringUtils.isNotBlank(idPermohonan)) {
      Permohonan p = permohonanDAO.findById(idPermohonan);
      if (p != null) {
        FolderDokumen fd = p.getFolderDokumen();
        List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
        senaraiDokumen = new ArrayList<Dokumen>();
        for (KandunganFolder kandunganFolder : senaraiKand) {
          Dokumen d = kandunganFolder.getDokumen();
          if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
            if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
              senaraiDokumen.add(d);
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

      } else {
        PermohonanCarian pc = carianDAO.findById(idPermohonan);
        if (pc != null) {
          FolderDokumen fd = pc.getFolderDokumen();
          List<KandunganFolder> senaraiKand = fd.getSenaraiKandungan();
          senaraiDokumen = new ArrayList<Dokumen>();
          for (KandunganFolder kandunganFolder : senaraiKand) {
            Dokumen d = kandunganFolder.getDokumen();
            if (StringUtils.isNotBlank(d.getNamaFizikal()) && d.getKlasifikasi() != null) {
              if (pengguna.getTahapSekuriti().getKod() >= d.getKlasifikasi().getKod()) {
                senaraiDokumen.add(d);
              }
            }
          }
        }
      }
    }
    return new JSP("daftar/utiliti/cetak_semula_resit.jsp");
  }

  public Resolution cetakSemula() {
    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    String sbbCetakanSemula = getContext().getRequest().getParameter("sbb_cetakan_semula");
    String idDokumen = getContext().getRequest().getParameter("id_dokumen");
    Dokumen d = dokumenDAO.findById(Long.parseLong(idDokumen));

    if (d == null) {
      return new StreamingResolution("text/plain", "2");
    }


    DokumenCapaian dc = new DokumenCapaian();

    if (StringUtils.isNotBlank(sbbCetakanSemula)) {
      dc.setAlasan("CETAKAN SEMULA [ " + sbbCetakanSemula + " ]");
    }

    dc.setDokumen(d);
    dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("CD"));
    InfoAudit ia = new InfoAudit();
    ia.setDimasukOleh(pengguna);
    ia.setTarikhMasuk(new java.util.Date());
    dc.setInfoAudit(ia);

    Session s = sessionProvider.get();
    Transaction tx = s.beginTransaction();
    dokumenCapaianDAO.save(dc);
    tx.commit();

    return new StreamingResolution("text/plain", "1");
  }

  public String getIdPermohonan() {
    return idPermohonan;
  }

  public void setIdPermohonan(String idPermohonan) {
    this.idPermohonan = idPermohonan;
  }

  public String getIdHakmilik() {
    return idHakmilik;
  }

  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;
  }

  public String getIdResit() {
    return idResit;
  }

  public void setIdResit(String idResit) {
    this.idResit = idResit;
  }

  public String getIdDokumenKewangan() {
    return idDokumenKewangan;
  }

  public void setIdDokumenKewangan(String idDokumenKewangan) {
    this.idDokumenKewangan = idDokumenKewangan;
  }

  public List<Dokumen> getSenaraiDokumen() {
    return senaraiDokumen;
  }

  public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
    this.senaraiDokumen = senaraiDokumen;
  }

  public List<Permohonan> getSenaraiPermohonan() {
    return senaraiPermohonan;
  }

  public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
    this.senaraiPermohonan = senaraiPermohonan;
  }

  public List<PermohonanCarian> getSenaraiPermohonanCarian() {
    return senaraiPermohonanCarian;
  }

  public void setSenaraiPermohonanCarian(List<PermohonanCarian> senaraiPermohonanCarian) {
    this.senaraiPermohonanCarian = senaraiPermohonanCarian;
  }

  public List<Transaksi> getListT() {
    return listT;
  }

  public void setListT(List<Transaksi> listT) {
    this.listT = listT;
  }

  public List<PermohonanCarian> getListP() {
    return listP;
  }

  public void setListP(List<PermohonanCarian> listP) {
    this.listP = listP;
  }

  public Transaksi getTransaksi() {
    return transaksi;
  }

  public void setTransaksi(Transaksi transaksi) {
    this.transaksi = transaksi;
  }

  public DokumenKewangan getDokumenKewangan() {
    return dokumenKewangan;
  }

  public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
    this.dokumenKewangan = dokumenKewangan;
  }

  public List<CarianHakmilik> getSenaraiCarianHakmilik() {
    return senaraiCarianHakmilik;
  }

  public void setSenaraiCarianHakmilik(List<CarianHakmilik> senaraiCarianHakmilik) {
    this.senaraiCarianHakmilik = senaraiCarianHakmilik;
  }
}
