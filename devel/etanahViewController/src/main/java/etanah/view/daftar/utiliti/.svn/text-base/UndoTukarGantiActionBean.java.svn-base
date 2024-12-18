/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikLama;
import etanah.model.HakmilikUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.RegService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.KutipanDataService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.util.StringUtil;
import net.sourceforge.stripes.validation.Validate;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author ei
 */
@UrlBinding("/daftar/utiliti/undoTukarGanti")
public class UndoTukarGantiActionBean extends AbleActionBean {

  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  private etanah.Configuration conf;
  @Inject
  HakmilikDAO hakmilikDAO;
  @Inject
  PermohonanDAO permohonanDAO;
  @Inject
  private RegService regService;
  @Inject
  PermohonanService permohonanService;
  @Inject
  private KutipanDataService kutipanDataService;
  private String idHakmilik;       // id hakmilik
  private String idPermohonan;
  private String sebab;
  private Pengguna pguna;
  private Permohonan mohon;
  private Hakmilik hakmilik;
  private List<String> idHakmilikSiriDari = new ArrayList<String>();   // ID Hakmilik bersiri
  private List<String> idHakmilikSiriHingga = new ArrayList<String>(); // ID Hakmilik bersiri
  private List<HakmilikUrusan> hakmilikUrusan;
  private List<HakmilikUrusan> listHakmilikUrusan;
  private boolean kelompok = false;
  private int bilHakmilik = 1;
  private static final Logger LOG = Logger.getLogger(UndoTukarGantiActionBean.class);
  private static String UNDO_TUKARGANTI_MAIN = "daftar/utiliti/undo_tukarganti_main.jsp";

  @DefaultHandler
  public Resolution undoSingle() {
    /* MAIN PAGE FOR UNDO TUKARGANTI SINGLE */
    kelompok = false;
    return new JSP(UNDO_TUKARGANTI_MAIN);
  }

  public Resolution undoBerkelompok() {
    /* MAIN PAGE FOR UNDO TUKARGANTI BERKELOMPOK */
    kelompok = true;
    bilHakmilik = 2;
    return new JSP(UNDO_TUKARGANTI_MAIN);
  }

  public Resolution seterusnya() {
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    pguna = ctx.getUser();
    sebab = null;
    if (StringUtils.isNotBlank(idHakmilik)) {
      /* SEARCH-BY-HAKMILIK */
      hakmilik = kutipanDataService.findHakmilikVersiSatu(idHakmilik, pguna.getKodCawangan().getKod());
      if (hakmilik != null) {
        // search HSTHK / HKTHK / HMSC for hakmilik
        List<HakmilikUrusan> listUrusanTukarganti = kutipanDataService.findListUrusanTukarganti(hakmilik.getIdHakmilik());
        for (HakmilikUrusan hu : listUrusanTukarganti) {
          // get all list hakmilik for urusan HSTHK / HKTHK / HMSC
          listHakmilikUrusan = kutipanDataService.findListUrusanByIdMohon(hu.getIdPerserahan());
        }
      } else {
        addSimpleError("Harap maaf. Hakmilik " + idHakmilik + " tidak wujud.");
      }
    } else if (StringUtils.isNotBlank(idPermohonan)) {
      /* SEARCH-BY-MOHON */
      // get list of all hakmilik for urusan HSTHK / HKTHK / HMSC
      mohon = kutipanDataService.findIdMohonTukarganti(idPermohonan, pguna.getKodCawangan().getKod());
      if (mohon != null) {
        listHakmilikUrusan = kutipanDataService.findListUrusanByIdMohon(mohon.getIdPermohonan());
      } else {
        addSimpleError("Harap maaf. Id Permohonan Tukarganti tidak dijumpai.");
      }
    } else {
      addSimpleError("Sila pastikan carian ID hakmilik atau ID Permohonan.");
    }

    setKelompok(Boolean.FALSE);
    return new JSP(UNDO_TUKARGANTI_MAIN);
  }

  public Resolution seterusnyaBerkelompok() {
    LOG.info(" Seterusnya kelompok");
    /*TODO*/
    bilHakmilik = 2;
    sebab = null;
    setKelompok(Boolean.TRUE);
    return new JSP(UNDO_TUKARGANTI_MAIN);
  }

  public Resolution undoTukarganti() {
    LOG.info(" undoTukarganti ");
    String idhm = (String) getContext().getRequest().getParameter("idhm");
    String idm = (String) getContext().getRequest().getParameter("idm");
//    Integer size = Integer.parseInt(getContext().getRequest().getParameter("size"));
    LOG.info(" > idhm : " + idhm);
    LOG.info(" > idm : " + idm);
    LOG.info(" > sebab : " + sebab);
    LOG.info(" > kelompok : " + kelompok);

    ArrayList<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
    if (StringUtils.isNotBlank(idhm)) {
      List<HakmilikUrusan> listUrusanTukarganti = kutipanDataService.findListUrusanTukarganti(idhm);
      // get list 
      for (HakmilikUrusan hu : listUrusanTukarganti) {
        listHakmilikUrusan = kutipanDataService.findListUrusanByIdMohon(hu.getIdPerserahan());

        mohon = kutipanDataService.findMohon(hu.getIdPerserahan());
        // UPDATE TABLE PERMOHONAN
        if (StringUtils.isBlank(sebab)) {
          addSimpleError("Sila Masukkan Sebab.");
          return new JSP(UNDO_TUKARGANTI_MAIN);
        }
        mohon.setSebab(sebab);
        mohon.setStatus("TK"); // need to set as "TAK AKTIF"
        permohonanService.saveOrUpdate(mohon);

        for (HakmilikUrusan list : listHakmilikUrusan) {
          LOG.info(" > id hakmilik berkaitan : " + list.getHakmilik().getIdHakmilik());
          hakmilik = hakmilikDAO.findById(list.getHakmilik().getIdHakmilik());
          // UPDATE VERSION TABLE HAKMILIK
          hakmilik.setNoVersiDhde(0);
          hakmilik.setNoVersiDhke(0);
          listHakmilik.add(hakmilik);

          HakmilikLama hakmilikLama = regService.searchHakmiliklama(list.getHakmilik().getIdHakmilik());
          if (hakmilikLama != null) {
            // UPDATE VERSION TABLE HAKMILIK_LAMA
//            hakmilikLama.setVersiDhdk("0");
//            hakmilikLama.setVersiDhkk("0");
//            regService.simpanHakmilikLama(hakmilikLama);
            regService.deleteHakmilikLama(hakmilikLama); // delete hakmilik_lama
          }
          
          list.setAktif('T'); // set hakmilik_urusan status as not active
          kutipanDataService.saveOrUpdateHakmilikUrusan(list);
        }

        if (!listHakmilik.isEmpty()) {
          regService.simpanHakmilikList(listHakmilik);
          addSimpleMessage("Undo Tukarganti Berjaya.");
        }
      }
    } else if (StringUtils.isNotBlank(idm)) {
      listHakmilikUrusan = kutipanDataService.findListUrusanByIdMohon(idm);
      mohon = kutipanDataService.findMohon(idm);
      // UPDATE TABLE PERMOHONAN
      if (StringUtils.isBlank(sebab)) {
        addSimpleError("Sila Masukkan Sebab.");
        return new JSP(UNDO_TUKARGANTI_MAIN);
      }
      mohon.setSebab(sebab);
      mohon.setStatus("TK"); // need to set as "TAK AKTIF"
      permohonanService.saveOrUpdate(mohon);

      for (HakmilikUrusan list : listHakmilikUrusan) {
        LOG.info(" > id hakmilik berkaitan : " + list.getHakmilik().getIdHakmilik());
        hakmilik = hakmilikDAO.findById(list.getHakmilik().getIdHakmilik());
        // UPDATE VERSION TABLE HAKMILIK
        hakmilik.setNoVersiDhde(0);
        hakmilik.setNoVersiDhke(0);
        listHakmilik.add(hakmilik);

        HakmilikLama hakmilikLama = regService.searchHakmiliklama(list.getHakmilik().getIdHakmilik());
        if (hakmilikLama != null) {
          // UPDATE VERSION TABLE HAKMILIK_LAMA
//          hakmilikLama.setVersiDhdk("0");
//          hakmilikLama.setVersiDhkk("0");
//          regService.simpanHakmilikLama(hakmilikLama);
          regService.deleteHakmilikLama(hakmilikLama);  // delete hakmilik_lama
        }

        list.setAktif('T');  // set hakmilik_urusan status as not active
        kutipanDataService.saveOrUpdateHakmilikUrusan(list);
      }
      if (!listHakmilik.isEmpty()) {
        regService.simpanHakmilikList(listHakmilik);
        addSimpleMessage("Undo Tukarganti Berjaya.");
      }
    }
    return new JSP(UNDO_TUKARGANTI_MAIN);
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

  public String getIdHakmilik() {
    return idHakmilik;
  }

  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;
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

  public Permohonan getMohon() {
    return mohon;
  }

  public void setMohon(Permohonan mohon) {
    this.mohon = mohon;
  }

  public Hakmilik getHakmilik() {
    return hakmilik;
  }

  public void setHakmilik(Hakmilik hakmilik) {
    this.hakmilik = hakmilik;
  }

  public String getIdPermohonan() {
    return idPermohonan;
  }

  public void setIdPermohonan(String idPermohonan) {
    this.idPermohonan = idPermohonan;
  }

  public Pengguna getPguna() {
    return pguna;
  }

  public void setPguna(Pengguna pguna) {
    this.pguna = pguna;
  }

  public List<HakmilikUrusan> getListHakmilikUrusan() {
    return listHakmilikUrusan;
  }

  public void setListHakmilikUrusan(List<HakmilikUrusan> listHakmilikUrusan) {
    this.listHakmilikUrusan = listHakmilikUrusan;
  }

  public String getSebab() {
    return sebab;
  }

  public void setSebab(String sebab) {
    this.sebab = sebab;
  }
}
