/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikWaris;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.daftar.PihakKepentinganAction;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
@UrlBinding("/common/pemohon")
public class PemohonActionBean extends AbleActionBean {

  private List<HakmilikPihakBerkepentingan> senaraiTuanTanah;
  private List<PermohonanPihak> senaraiPemohonBerangkai;
  private List<Pihak> senaraiPihakTerlibat;
  @Inject
  PermohonanDAO permohonanDAO;
  @Inject
  HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
  @Inject
  PermohonanPihakService permohonanPihakService;
  @Inject
  KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
  @Inject
  PemohonService pemohonService;
  @Inject
  PihakService pihakService;
  @Inject
  HakmilikUrusanService hakmilikUrusanService;
  @Inject
  PermohonanService permohonanService;
  @Inject
  HakmilikUrusanService hakmilikService;
  private Permohonan permohonan;
  public String idHakmilik;
  private Pengguna pengguna;
  private static final Logger LOG = Logger.getLogger(PemohonActionBean.class);
  private String[] jenisPihak;
  private List<HakmilikPermohonan> senaraiHakmilik;

  @DefaultHandler
  public Resolution showForm() {
    return new JSP("daftar/senarai_tuan_tanah.jsp").addParameter("tab", "true");
  }

  public Resolution showFormPopup() {
    return new JSP("daftar/senarai_tuan_tanah.jsp").addParameter("popup", "true");
  }

  public Resolution saveTuanTanahAsPemohon() {
    if (permohonan != null) {
      LOG.debug("start..");
      List<Pemohon> senaraiPemohon = permohonan.getSenaraiPemohon();
      List<Pemohon> senaraiPemohonTmp = new ArrayList<Pemohon>();
      String[] ids = getContext().getRequest().getParameterValues("rb");
      InfoAudit ia = new InfoAudit();
      for (String id : ids) {
        LOG.debug("id = " + id);
        if (StringUtils.isBlank(id)) {
          continue;
        }
        boolean flag = false;
        HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findById(id);
        if (hpk == null) {
          continue;
        }
        LOG.debug("id pihak = " + hpk.getPihak().getIdPihak());
        for (Pemohon pemohon : senaraiPemohon) {
          if (pemohon == null) {
            continue;
          }
          LOG.debug("id pihak = " + pemohon.getPihak().getIdPihak());
          if (pemohon.getPihak().getIdPihak() == hpk.getPihak().getIdPihak()) {
            LOG.debug("already EXIST in table");
            flag = true;
            break;
          }
        }
        if (flag) {
          continue;
        }
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        LOG.debug("creating new pemohon");
        Pemohon pemohon = new Pemohon();
        pemohon.setPermohonan(permohonan);
        pemohon.setCawangan(hpk.getHakmilik().getCawangan());
        pemohon.setPihak(hpk.getPihak());
        pemohon.setInfoAudit(ia);
        senaraiPemohonTmp.add(pemohon);
      }
      if (!senaraiPemohonTmp.isEmpty()) {
        pemohonService.saveOrUpdateMultiple(senaraiPemohonTmp);
      }
      LOG.debug("finish");
    }
    return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
  }

  public Resolution saveTuanTanahAsMohonPihak() {
    if (permohonan != null) {

      LOG.debug("start..");
      List<PermohonanPihak> senaraiPemohon = permohonan.getSenaraiPihak();
      List<PermohonanPihak> senaraiPemohonTmp = new ArrayList<PermohonanPihak>();
      String[] ids = getContext().getRequest().getParameterValues("rb");
      String copyToAll = getContext().getRequest().getParameter("copyToAll");

      InfoAudit ia = new InfoAudit();
      Pihak pihak = null;
      for (String id : ids) {
        LOG.debug("id = " + id);
        if (StringUtils.isBlank(id)) {
          continue;
        }
        boolean flag = false;
        HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findById(id);
        LOG.debug("id pihak = " + hpk.getPihak().getIdPihak());
        if (hpk == null) {
          pihak = pihakService.findById(id);
          if (pihak == null) {
            continue;
          }

          for (PermohonanPihak pemohon : senaraiPemohon) {
            if (pemohon == null) {
              continue;
            }
            LOG.debug("id pihak =" + pihak.getIdPihak());
            if (pemohon.getPihak().getIdPihak() == pihak.getIdPihak()) {
              LOG.debug("already EXIST in table");
              flag = true;
              break;
            }
          }
        } else {

          for (PermohonanPihak pemohon : senaraiPemohon) {
            if (pemohon == null) {
              continue;
            }
            LOG.debug("id pihak = " + pemohon.getPihak().getIdPihak());
            if ((pemohon.getPihak().getIdPihak() == hpk.getPihak().getIdPihak())
                    && (pemohon.getHakmilik().getIdHakmilik().equals(hpk.getHakmilik().getIdHakmilik()))) {
              LOG.debug("already EXIST in table");
              flag = true;
              break;
            }
          }
        }


        if (flag) {
          continue;
        }
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        LOG.debug("creating new pemohon");
        PermohonanPihak pp = new PermohonanPihak();
        pp.setInfoAudit(ia);
        pp.setPermohonan(permohonan);

        if (hpk != null) {

          pp.setCawangan(hpk.getHakmilik().getCawangan());
          pp.setHakmilik(hpk.getHakmilik());
          pp.setPihak(hpk.getPihak());
          pp.setSyerPembilang(hpk.getSyerPembilang());
          pp.setSyerPenyebut(hpk.getSyerPenyebut());
          pp.setJenis(hpk.getJenis());
//          add by azri: 17/06/2013 - update table 'Mohon_pihak' for single hakmilik  
          pp.setNama(hpk.getNama());
          
          //NULL checking
          if (hpk.getJenisPengenalan() != null) {
              KodJenisPengenalan jp = new KodJenisPengenalan();
                jp.setKod(hpk.getJenisPengenalan().getKod());
                pp.setJenisPengenalan(jp);                    // update JenisPengenalan 'mohon_pihak' table from 'hakmilik_pihak' table
          }          
          pp.setNoPengenalan(hpk.getNoPengenalan());    // update noPengenalan 'mohon_pihak' table from 'hakmilik_pihak' table
          Alamat alamat = new Alamat();                // update alamat 'mohon_pihak' table from 'hakmilik_pihak' table
          alamat.setAlamat1(hpk.getAlamat1());
          alamat.setAlamat2(hpk.getAlamat2());
          alamat.setAlamat3(hpk.getAlamat3());
          alamat.setAlamat4(hpk.getAlamat4());
          alamat.setPoskod(hpk.getPoskod());
          //checking for NULL !!
          if (hpk.getNegeri() != null) {
              KodNegeri kodNegeri = new KodNegeri();
              kodNegeri.setKod(hpk.getNegeri().getKod());
              alamat.setNegeri(kodNegeri);
          }          
          pp.setAlamat(alamat);
//          add by azri: 17/06/2013 - END 

        } else if (pihak != null) {
          for (PermohonanPihak mohonPihak : senaraiPemohonBerangkai) {
            if (pihak.getIdPihak() == mohonPihak.getPihak().getIdPihak()) {
              pp.setCawangan(mohonPihak.getCawangan());
              pp.setHakmilik(mohonPihak.getHakmilik());
              pp.setPihak(pihak);
              pp.setSyerPembilang(mohonPihak.getSyerPembilang());
              pp.setSyerPenyebut(mohonPihak.getSyerPenyebut());
            }
          }
        }

        if (permohonan.getKodUrusan().getKod().equals("KVST")) {
          KodJenisPihakBerkepentingan jenis = kodJenisPihakBerkepentinganDAO.findById("PKS");
          pp.setJenis(jenis);
        }
        senaraiPemohonTmp.add(pp);

        if (StringUtils.isNotBlank(copyToAll)) {
          for (HakmilikPermohonan hp : senaraiHakmilik) {
            if (hp == null || hp.getHakmilik() == null
                    || hp.getHakmilik().equals(pp.getHakmilik())) {
              continue;
            }

            Hakmilik hm = hp.getHakmilik();
            Pihak p = pp.getPihak();
            hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(p, hm);
 
            if (hpk != null) {
              PermohonanPihak pp2 = new PermohonanPihak();
              pp2.setInfoAudit(ia);
              pp2.setPermohonan(permohonan);
              pp2.setCawangan(pp.getHakmilik().getCawangan());
              pp2.setHakmilik(hm);
              pp2.setPihak(pp.getPihak());
              pp2.setSyerPembilang(hpk.getSyerPembilang());
              pp2.setSyerPenyebut(hpk.getSyerPenyebut());
              pp2.setJenis(pp.getJenis());
//          add by azri: 17/06/2013 - update table 'Mohon_pihak' for many hakmilik           
              pp2.setNama(hpk.getNama());
              KodJenisPengenalan jp = new KodJenisPengenalan();
              jp.setKod(hpk.getJenisPengenalan().getKod());
              pp2.setJenisPengenalan(jp);                    // update JenisPengenalan 'mohon_pihak' table from 'hakmilik_pihak' table
              pp2.setNoPengenalan(hpk.getNoPengenalan());    // update noPengenalan 'mohon_pihak' table from 'hakmilik_pihak' table
              Alamat alamat = new Alamat();                // update alamat 'mohon_pihak' table from 'hakmilik_pihak' table
              alamat.setAlamat1(hpk.getAlamat1());
              alamat.setAlamat2(hpk.getAlamat2());
              alamat.setAlamat3(hpk.getAlamat3());
              alamat.setAlamat4(hpk.getAlamat4());
              alamat.setPoskod(hpk.getPoskod());
              KodNegeri kodNegeri = new KodNegeri();
              kodNegeri.setKod(hpk.getNegeri().getKod());
              alamat.setNegeri(kodNegeri);
              pp2.setAlamat(alamat);
//          add by azri: 17/06/2013 - END 
              senaraiPemohonTmp.add(pp2);
            } else {

              for (PermohonanPihak mohonPihak : senaraiPemohonBerangkai) {
                if (pihak.getIdPihak()
                        == mohonPihak.getPihak().getIdPihak()
                        && mohonPihak.getHakmilik().equals(hm)) {
                  PermohonanPihak pp2 = new PermohonanPihak();
                  pp2.setInfoAudit(ia);
                  pp2.setPermohonan(permohonan);
                  pp2.setCawangan(pp.getCawangan());
                  pp2.setHakmilik(hm);
                  pp2.setPihak(pihak);
                  pp2.setSyerPembilang(mohonPihak.getSyerPembilang());
                  pp2.setSyerPenyebut(mohonPihak.getSyerPenyebut());
                  pp2.setJenis(pp.getJenis());
                  senaraiPemohonTmp.add(pp2);
                  break;
                }
              }
            }

          }
        }

      }
      if (!senaraiPemohonTmp.isEmpty()) {
        permohonanPihakService.saveOrUpdate(senaraiPemohonTmp);
      }
      LOG.debug("finish");
    }
    if (permohonan.getKodUrusan().getKod().equals("KVST") || permohonan.getKodUrusan().getKod().equals("KVSPT") ) {
      return new RedirectResolution(PihakKepentinganAction.class, "showFormKVST")
              .addParameter("idHakmilik", idHakmilik);
    } else {
      return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan")
              .addParameter("idHakmilik", idHakmilik);
    }
  }

  public Resolution saveMohonPihakAsMohonPihak() {

    if (permohonan != null) {
      LOG.debug("start..");
      List<PermohonanPihak> senaraiPemohon = permohonan.getSenaraiPihak();
      List<PermohonanPihak> senaraiPemohonTmp = new ArrayList<PermohonanPihak>();
      String[] ids = getContext().getRequest().getParameterValues("rb");
      String copyToAll = getContext().getRequest().getParameter("copyToAll");

      InfoAudit ia = new InfoAudit();
      Pihak pihak = null;
      for (String id : ids) {
        LOG.debug("id = " + id);
        if (StringUtils.isBlank(id)) {
          continue;
        }
        boolean flag = false;
        pihak = pihakService.findById(id);

        if (pihak == null) {
          continue;
        }

        for (PermohonanPihak pemohon : senaraiPemohon) {
          if (pemohon == null) {
            continue;
          }
          LOG.debug("id pihak =" + pihak.getIdPihak());
          if (pemohon.getPihak().getIdPihak() == pihak.getIdPihak()) {
            LOG.debug("already EXIST in table");
            flag = true;
            break;
          }
        }

        if (flag) {
          continue;
        }
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        LOG.debug("creating new pemohon");
        PermohonanPihak pp = new PermohonanPihak();
        pp.setInfoAudit(ia);
        pp.setPermohonan(permohonan);

        for (PermohonanPihak mohonPihak : senaraiPemohonBerangkai) {
          if (pihak.getIdPihak() == mohonPihak.getPihak().getIdPihak()) {
            pp.setCawangan(mohonPihak.getCawangan());
            pp.setHakmilik(mohonPihak.getHakmilik());
            pp.setPihak(pihak);
            pp.setSyerPembilang(mohonPihak.getSyerPembilang());
            pp.setSyerPenyebut(mohonPihak.getSyerPenyebut());
            pp.setNama(mohonPihak.getNama());
            pp.setNoPengenalan(mohonPihak.getNoPengenalan());
            pp.setJenisPengenalan(mohonPihak.getJenisPengenalan());
            pp.setAlamat(mohonPihak.getAlamat());
            pp.setAlamatSurat(mohonPihak.getAlamatSurat());
            pp.setJenis(mohonPihak.getJenis());
          }
        }

        if (permohonan.getKodUrusan().getKod().equals("KVST")
                || permohonan.getKodUrusan().getKod().equals("KVSPT")) {
          KodJenisPihakBerkepentingan jenis = kodJenisPihakBerkepentinganDAO.findById("PKS");
          pp.setJenis(jenis);
        }
        senaraiPemohonTmp.add(pp);

        if (StringUtils.isNotBlank(copyToAll)) {
          for (HakmilikPermohonan hp : senaraiHakmilik) {
            if (hp == null || hp.getHakmilik() == null
                    || hp.getHakmilik().equals(pp.getHakmilik())) {
              continue;
            }

            Hakmilik hm = hp.getHakmilik();
            Pihak p = pp.getPihak();

            for (PermohonanPihak mohonPihak : senaraiPemohonBerangkai) {
              if (pihak.getIdPihak()
                      == mohonPihak.getPihak().getIdPihak()
                      && mohonPihak.getHakmilik().equals(hm)) {
                PermohonanPihak pp2 = new PermohonanPihak();
                pp2.setInfoAudit(ia);
                pp2.setPermohonan(permohonan);
                pp2.setCawangan(pp.getCawangan());
                pp2.setHakmilik(hm);
                pp2.setPihak(pihak);
                pp2.setSyerPembilang(mohonPihak.getSyerPembilang());
                pp2.setSyerPenyebut(mohonPihak.getSyerPenyebut());
                pp2.setJenis(pp.getJenis());
                pp2.setNama(mohonPihak.getNama());
                pp2.setNoPengenalan(mohonPihak.getNoPengenalan());
                pp2.setJenisPengenalan(mohonPihak.getJenisPengenalan());
                pp2.setAlamat(mohonPihak.getAlamat());
                pp2.setAlamatSurat(mohonPihak.getAlamatSurat());
                senaraiPemohonTmp.add(pp2);
                break;
              }
            }
          }
        }

      }
      if (!senaraiPemohonTmp.isEmpty()) {
        permohonanPihakService.saveOrUpdate(senaraiPemohonTmp);
      }
      LOG.debug("finish");
    }
    if (permohonan.getKodUrusan().getKod().equals("KVST") || permohonan.getKodUrusan().getKod().equals("KVSPT")) {
      return new RedirectResolution(PihakKepentinganAction.class, "showFormKVST").addParameter("idHakmilik", idHakmilik);
    } else {
      return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan").addParameter("idHakmilik", idHakmilik);
    }
  }

  public Resolution savePihakAsMohonPihak() {
    if (permohonan != null) {
      Long t = System.currentTimeMillis();
      LOG.debug("start");

      List<PermohonanPihak> senaraiPemohon = permohonan.getSenaraiPihak();
      List<PermohonanPihak> senaraiPemohonTmp = new ArrayList<PermohonanPihak>();
      List<PermohonanAtasPerserahan> senaraiUrusanTerlibat = permohonan.getSenaraiPermohonanAtasPerserahan();

      List<Pemohon> senaraiPmhn = permohonan.getSenaraiPemohon();
//            List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();

      String[] ids = getContext().getRequest().getParameterValues("rb");

      List<PermohonanHubungan> senaraiHubungan =
              permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), idHakmilik);

      InfoAudit ia = new InfoAudit();
      Pihak pihak = null;

      for (String id : ids) {
        LOG.debug("id = " + id);
        if (StringUtils.isBlank(id)) {
          continue;
        }
        boolean flag = false;
        pihak = pihakService.findById(id);

        if (pihak == null) {
          continue;
        }

        for (PermohonanPihak pemohon : senaraiPemohon) {
          if (pemohon == null) {
            continue;
          }
          LOG.debug("id pihak =" + pihak.getIdPihak());
          if (pemohon.getPihak().getIdPihak() == pihak.getIdPihak()
                  && idHakmilik.equals(pemohon.getHakmilik().getIdHakmilik())) {
            LOG.debug("already EXIST in table");
            flag = true;
            break;
          }
        }

        if (flag) {
          continue;
        }
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        LOG.debug("creating new pemohon");
        PermohonanPihak pp = new PermohonanPihak();
        pp.setInfoAudit(ia);
        pp.setPermohonan(permohonan);
        KodJenisPihakBerkepentingan jenis = kodJenisPihakBerkepentinganDAO.findById("PM");
        pp.setJenis(jenis);
        pp.setPihak(pihak);

        //jika pemohon adalah tuan tanah asal
        for (PermohonanAtasPerserahan map : senaraiUrusanTerlibat) {
          if (map == null) {
            continue;
          }
          HakmilikUrusan hu = map.getUrusan();

          if (hu.getKodUrusan().getKod().equals("TRPA")) {

            List<HakmilikUrusan> listUrusanBatal = hakmilikUrusanService.findHakmilikUrusanBatalByIdUrusan(hu);
            for (HakmilikUrusan hakmilikUrusan : listUrusanBatal) {
              List<HakmilikPihakBerkepentingan> listPihakLama = hakmilikPihakKepentinganService.
                      findHakmilikPihakBatalByIdUrusan(hakmilikUrusan);

              for (HakmilikPihakBerkepentingan hpk : listPihakLama) {
                if (hpk == null) {
                  continue;
                }
                Pihak pihak2 = hpk.getPihak();
                if (pihak2.getIdPihak() == pihak.getIdPihak()) {
                  pp.setSyerPembilang(hpk.getSyerPembilang());
                  pp.setSyerPenyebut(hpk.getSyerPenyebut());
                  pp.setHakmilik(hpk.getHakmilik());
                  pp.setCawangan(hpk.getHakmilik().getCawangan());
                }
              }
            }
          } else {

            List<HakmilikPihakBerkepentingan> senaraiTmp = hakmilikPihakKepentinganService.findHakmilikPihakBatalByIdUrusan(hu, idHakmilik);
            for (HakmilikPihakBerkepentingan hpk : senaraiTmp) {
              if (hpk == null) {
                continue;
              }
              Pihak pihak2 = hpk.getPihak();
              if (pihak2.getIdPihak() == pihak.getIdPihak()) {
                pp.setSyerPembilang(hpk.getSyerPembilang());
                pp.setSyerPenyebut(hpk.getSyerPenyebut());
                pp.setHakmilik(hpk.getHakmilik());
                pp.setCawangan(hpk.getHakmilik().getCawangan());
              }
            }
          }
          senaraiPemohonTmp.add(pp);
        }

        for (PermohonanHubungan ph : senaraiHubungan) {
          if (ph == null) {
            continue;
          }
          HakmilikUrusan hu = hakmilikUrusanService
                  .findByIdPerserahanIdHakmilik(ph.getPermohonanSasaran().getIdPermohonan(), ph.getCatatan());


          if (hu.getKodUrusan().getKod().equals("TRPA")) {

            List<HakmilikUrusan> listUrusanBatal = hakmilikUrusanService.findHakmilikUrusanBatalByIdUrusan(hu);
            for (HakmilikUrusan hakmilikUrusan : listUrusanBatal) {
              List<HakmilikPihakBerkepentingan> listPihakLama = hakmilikPihakKepentinganService.
                      findHakmilikPihakBatalByIdUrusan(hakmilikUrusan, idHakmilik);

              for (HakmilikPihakBerkepentingan hpk : listPihakLama) {
                if (hpk == null) {
                  continue;
                }
                Pihak pihak2 = hpk.getPihak();
                if (pihak2.getIdPihak() == pihak.getIdPihak()) {
                  pp.setSyerPembilang(hpk.getSyerPembilang());
                  pp.setSyerPenyebut(hpk.getSyerPenyebut());
                  pp.setHakmilik(hpk.getHakmilik());
                  pp.setCawangan(hpk.getHakmilik().getCawangan());
                }
              }
            }
          } else {

            List<HakmilikPihakBerkepentingan> senaraiTmp = hakmilikPihakKepentinganService.findHakmilikPihakBatalByIdUrusan(hu);
            for (HakmilikPihakBerkepentingan hpk : senaraiTmp) {
              if (hpk == null) {
                continue;
              }
              Pihak pihak2 = hpk.getPihak();
              if (pihak2.getIdPihak() == pihak.getIdPihak()) {
                pp.setSyerPembilang(hpk.getSyerPembilang());
                pp.setSyerPenyebut(hpk.getSyerPenyebut());
                pp.setHakmilik(hpk.getHakmilik());
                pp.setCawangan(hpk.getHakmilik().getCawangan());
              }
            }
          }
          senaraiPemohonTmp.add(pp);
        }

        //jika pemohon adalah waris
        for (Pemohon pemohon : senaraiPmhn) {
          if (pemohon == null || pemohon.getPihak() == null) {
            continue;
          }

          Pihak pihak2 = pemohon.getPihak();
          for (HakmilikPermohonan hp : senaraiHakmilik) {
            if (hp == null || hp.getHakmilik() == null) {
              continue;
            }
            Hakmilik hm = hp.getHakmilik();
            HakmilikPihakBerkepentingan h = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihakPMPPMG(pihak2, hm, "PA");
            if (h == null) {
              continue;
            }
            List<HakmilikWaris> senaraiWaris = h.getSenaraiWaris();
            for (HakmilikWaris hw : senaraiWaris) {
              if (hw == null || hw.getJenisPengenalan() == null) {
                continue;
              }
              Pihak p2 = pihakService.findPihak(hw.getJenisPengenalan().getKod(), hw.getNoPengenalan());
              if (p2 == null) {
                continue;
              }
              if (p2.getIdPihak() == pihak.getIdPihak()) {
                pp.setSyerPembilang(h.getSyerPembilang());
                pp.setSyerPenyebut(h.getSyerPenyebut());
                pp.setCawangan(h.getHakmilik().getCawangan());
                pp.setHakmilik(h.getHakmilik());
              }
            }
          }
        }
        senaraiPemohonTmp.add(pp);
      }

      if (!senaraiPemohonTmp.isEmpty()) {
        permohonanPihakService.saveOrUpdate(senaraiPemohonTmp);
      }

      LOG.debug("finish. Took = " + ((System.currentTimeMillis() - t) / 1000));
    }
//        return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
    return new RedirectResolution(PihakKepentinganAction.class).addParameter("idHakmilik", idHakmilik);
  }

  @Before(stages = {LifecycleStage.BindingAndValidation})
  public void rehydrate() {
    String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
    pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");

    if (StringUtils.isNotBlank(idPermohonan)) {

      permohonan = permohonanDAO.findById(idPermohonan);
      senaraiHakmilik = permohonan.getSenaraiHakmilik();
      List<HakmilikPihakBerkepentingan> senaraiPihak = new ArrayList<HakmilikPihakBerkepentingan>();

      if (StringUtils.isBlank(idHakmilik) && !senaraiHakmilik.isEmpty()) {
        idHakmilik = senaraiHakmilik.get(0).getHakmilik().getIdHakmilik();
      }

      if (senaraiHakmilik.size() > 1) {
        getContext().getRequest().setAttribute("moreThanOneHakmilik", "true");
      }

      Permohonan permohonanSeblum = permohonan.getPermohonanSebelum();

      String idKump = permohonan.getIdKumpulan();
      if (StringUtils.isNotBlank(idKump)) {
        senaraiPemohonBerangkai = new ArrayList<PermohonanPihak>();

        List<PermohonanPihak> senaraiMohonPihak = permohonanPihakService.
                getSenaraiTuanTanahForBerangkai(idKump, idPermohonan, idHakmilik, false, false, permohonan.getKumpulanNo());

        for (PermohonanPihak pp : senaraiMohonPihak) {
          if (pp.getJenis().getKod().equals("PM") || pp.getJenis().getKod().equals("PA")
                  || pp.getJenis().getKod().equals("PPM")) {
            senaraiPemohonBerangkai.add(pp);
          }
        }

        for (HakmilikPermohonan hp : senaraiHakmilik) {
          if (hp == null || hp.getHakmilik() == null) {
            continue;
          }
          Hakmilik hk = hp.getHakmilik();
          //list tuan tanah yg tidak terlibat dalam permohnn berangkai
          List<HakmilikPihakBerkepentingan> tuanTanah =
                  hakmilikPihakKepentinganService.senaraiPBtidakBerkaitan(hk.getIdHakmilik(), idKump, idPermohonan, false, false);
          for (HakmilikPihakBerkepentingan hpk : tuanTanah) {
            if (hpk == null) {
              continue;
            }
            PermohonanPihak pp = new PermohonanPihak();
            pp.setPihak(hpk.getPihak());
            pp.setSyerPembilang(hpk.getSyerPembilang());
            pp.setSyerPenyebut(hpk.getSyerPenyebut());
            pp.setJenis(hpk.getJenis());
            pp.setHakmilik(hpk.getHakmilik());
            pp.setCawangan(hpk.getHakmilik().getCawangan());
            senaraiPemohonBerangkai.add(pp);
          }
        }
      } else {

        if (permohonan.getKodUrusan().getKod().equals("PNPAB")) {

          List<Pemohon> senaraiPemohon = permohonan.getSenaraiPemohon();
          senaraiPihakTerlibat = new ArrayList<Pihak>();

//                    List<PermohonanAtasPerserahan> senaraiUrusanTerlibat = permohonan.getSenaraiPermohonanAtasPerserahan();
          List<PermohonanHubungan> senaraiUrusanTerlibat = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), idHakmilik);

          if (senaraiUrusanTerlibat.isEmpty()) {
//                        addSimpleError("Sila pilih urusan.");
          } else {

            for (PermohonanHubungan ph : senaraiUrusanTerlibat) {
              if (ph == null) {
                continue;
              }
//                            HakmilikUrusan hu = map.getUrusan();
              HakmilikUrusan hu = hakmilikService
                      .findByIdPerserahanIdHakmilik(ph.getPermohonanSasaran().getIdPermohonan(), idHakmilik);
              if (hu == null) {
                continue;
              }
              //utk urusan trpa, terdapat pertukaran pemegang amanah.                            
              if (hu.getKodUrusan().getKod().equals("TRPA")) {
                List<HakmilikUrusan> listUrusanBatal = hakmilikUrusanService.findHakmilikUrusanBatalByIdUrusan(hu);
                for (HakmilikUrusan hakmilikUrusan : listUrusanBatal) {

                  if (StringUtils.isNotBlank(idHakmilik) && !hakmilikUrusan.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                    continue;
                  }
                  List<HakmilikPihakBerkepentingan> listPihakLama = hakmilikPihakKepentinganService.
                          findHakmilikPihakBatalByIdUrusan(hakmilikUrusan);

                  for (HakmilikPihakBerkepentingan hpk : listPihakLama) {
                    if (hpk == null) {
                      continue;
                    }
                    Pihak pihak = hpk.getPihak();
                    if (pihak == null) {
                      continue;
                    }
                    senaraiPihakTerlibat.add(pihak);
                  }
                }
              } else {
                List<HakmilikPihakBerkepentingan> senaraiTmp = hakmilikPihakKepentinganService.findHakmilikPihakBatalByIdUrusan(hu, idHakmilik);
                for (HakmilikPihakBerkepentingan hpk : senaraiTmp) {
                  if (hpk == null) {
                    continue;
                  }
                  Pihak pihak = hpk.getPihak();
                  if (pihak == null) {
                    continue;
                  }
                  senaraiPihakTerlibat.add(pihak);
                }
              }
              for (Pemohon pemohon : senaraiPemohon) {
                if (pemohon == null || pemohon.getPihak() == null) {
                  continue;
                }
                Pihak pihak = pemohon.getPihak();
                for (HakmilikPermohonan hp : senaraiHakmilik) {
                  if (hp == null || hp.getHakmilik() == null) {
                    continue;
                  }
                  Hakmilik hm = hp.getHakmilik();
                  if (StringUtils.isNotBlank(idHakmilik) && !hm.getIdHakmilik().equals(idHakmilik)) {
                    continue;
                  }
                  HakmilikPihakBerkepentingan h = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihakPMPPMG(pihak, hm, "PA");
                  if (h == null) {
                    continue;
                  }
                  List<HakmilikWaris> senaraiWaris = h.getSenaraiWaris();
                  for (HakmilikWaris hw : senaraiWaris) {
                    if (hw == null || hw.getJenisPengenalan() == null) {
                      continue;
                    }
                    Pihak p2 = pihakService.findPihak(hw.getJenisPengenalan().getKod(), hw.getNoPengenalan());
                    if (p2 == null) {
                      continue;
                    }
//                                        senaraiPihakTerlibat.add(p2);//fixme : bugs
                  }
                }
              }
            }
          }
        } else {
          senaraiTuanTanah = new ArrayList<HakmilikPihakBerkepentingan>();
          for (HakmilikPermohonan hp : senaraiHakmilik) {
            if (hp == null || hp.getHakmilik() == null) {
              continue;
            }
            Hakmilik hk = hp.getHakmilik();
            if (StringUtils.isNotBlank(idHakmilik)) {
              if (!idHakmilik.equals(hk.getIdHakmilik())) {
                continue;
              }
            }
            LOG.debug("hakmilik = " + hk.getIdHakmilik());
            senaraiPihak = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hk);
            for (HakmilikPihakBerkepentingan hpk : senaraiPihak) {
              if (hpk == null) {
                continue;
              }
              senaraiTuanTanah.add(hpk);
            }
          }
        }
      }
    }
  }

  public List<HakmilikPihakBerkepentingan> getSenaraiTuanTanah() {
    return senaraiTuanTanah;
  }

  public void setSenaraiTuanTanah(List<HakmilikPihakBerkepentingan> senaraiTuanTanah) {
    this.senaraiTuanTanah = senaraiTuanTanah;
  }

  public List<PermohonanPihak> getSenaraiPemohonBerangkai() {
    return senaraiPemohonBerangkai;
  }

  public void setSenaraiPemohonBerangkai(List<PermohonanPihak> senaraiPemohonBerangkai) {
    this.senaraiPemohonBerangkai = senaraiPemohonBerangkai;
  }

  public List<Pihak> getSenaraiPihakTerlibat() {
    return senaraiPihakTerlibat;
  }

  public void setSenaraiPihakTerlibat(List<Pihak> senaraiPihakTerlibat) {
    this.senaraiPihakTerlibat = senaraiPihakTerlibat;
  }

  public String[] getJenisPihak() {
    return jenisPihak;
  }

  public void setJenisPihak(String[] jenisPihak) {
    this.jenisPihak = jenisPihak;
  }
}
