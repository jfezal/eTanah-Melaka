/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPembetulanUrusan; // temp
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.PembetulanService; // 
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
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
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/susun_perserahan")
public class SusunPerserahanActionBean extends AbleActionBean {

  private String idPermohonan;
  private String trkh;
  private String idHakmilik;
  private List<Permohonan> senaraiPerserahan;
  private String[] dt;
  private String[] ids;
  private Pengguna pengguna;
  @Inject
  PermohonanService permohonanService;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  HakmilikUrusanService hakmilikUrusanService;
  @Inject
  private PembetulanService pService;
  private static Logger LOG = Logger.getLogger(SusunPerserahanActionBean.class);
  private boolean isDebug = LOG.isDebugEnabled();

  @DefaultHandler
  public Resolution showForm() {
    return new JSP("daftar/utiliti/susun_perserahan.jsp");
  }

  @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!showForm"})
  public void rehydrate() {
    idPermohonan = getContext().getRequest().getParameter("idPermohonan");

    pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    trkh = (String) getContext().getRequest().getParameter("strDate");

    if (isDebug) {
      LOG.debug("idPermohonan :" + idPermohonan);
    }

    if (StringUtils.isNotBlank(idPermohonan)) {
      HakmilikUrusan hu = hakmilikUrusanService.findByIdPerserahan(idPermohonan);
      if (hu == null) {
        Permohonan p = permohonanService.findById(idPermohonan);

        if (p != null) {

          String idKumpulan = p.getIdKumpulan();

          if (StringUtils.isBlank(idKumpulan)) {

            addSimpleError("Perserahan bukan perserahan berangkai.");

          } else {
            senaraiPerserahan = permohonanService.getPermohonanByIdKumpTrhMasuk(idKumpulan, trkh);
            ids = new String[senaraiPerserahan.size()];
            dt = new String[senaraiPerserahan.size()];
          }

        } else {
          addSimpleError("Maklumat Perserahan tidak dijumpai.");
        }
      } else {
        addSimpleError("Maklumat Perserahan telah didaftarkan.");
      }
    }

  }

  public Resolution search() {
    if (senaraiPerserahan != null && senaraiPerserahan.size() > 0) {
      int size = senaraiPerserahan.size();

      for (int i = 0; i < size; i++) {
        Permohonan _p = senaraiPerserahan.get(i);
        if (_p != null) {
//                    SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
//                    Date _d = _p.getInfoAudit().getTarikhMasuk();
          ids[i] = _p.getIdPermohonan();
          dt[i] = String.valueOf(_p.getKumpulanNo());
        }
      }
    } else {
      addSimpleError("Tiada Rekod Dijumpai.");
    }
    return new JSP("daftar/utiliti/susun_perserahan.jsp");
  }

  /*
   * Susun semula perserahan by permohonanSebelum and kumpNo
   */
  public Resolution susunSemula() {
    if (isDebug) {
      LOG.debug("***susun semula BERMULA*****");
    }

    Transaction tx = sessionProvider.get().beginTransaction();
    tx.begin();

    try {
      for (int i = 0; i < ids.length; i++) {
        String str = ids[i];
        if (isDebug) {
          LOG.debug("ids[" + i + "]=" + str);
        }

        if (StringUtils.isBlank(str)) {
          continue;
        }

        Permohonan p = permohonanService.findById(str);
        if (p == null) {
          continue;
        }

        LOG.debug("dt[" + i + "]=" + dt[i]);
        int kumpNo = Integer.parseInt(dt[i]);
        int kumpNoSblm = kumpNo - 1;
//                p.setKumpulanNo( kumpNo );         

        for (int a = 0; a < ids.length; a++) {
          int k = Integer.parseInt(dt[a]);
          if (kumpNoSblm == 0) {
            p.setPermohonanSebelum(null);
            break;
          } else if (k != kumpNoSblm) {
            continue;
          }
          String id = ids[a];
          Permohonan permohonanSblm = permohonanService.findById(id);
          p.setPermohonanSebelum(permohonanSblm);
          break;
        }
        permohonanService.saveOrUpdateConn(p);
      }
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
      ex.printStackTrace();
    }

//    add by azri 23/9/2013 : solution to 'susun semula' id mohon by no kump and kod serah
    if (idPermohonan != null) {      
      InfoAudit info = pengguna.getInfoAudit();
      for (int x = 0; x < ids.length; x++) {
        /* UPDATE KUMP NO in table mohon */
        int kumpNo = Integer.parseInt(dt[x]);
        String id = ids[x];
        Permohonan p = permohonanService.findById(id); // find id mohon      
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        p.setInfoAudit(info);
        p.setKumpulanNo(kumpNo);
        permohonanService.update(p); // update kumpNo

        /* USE MOHON_URUSAN_BETUL as temp table */
        PermohonanPembetulanUrusan temp = new PermohonanPembetulanUrusan();
        LOG.info("--- temp use table PermohonanPembetulanUrusan ---");
        InfoAudit info1 = pengguna.getInfoAudit();
        info1.setDimasukOleh(pengguna);
        info1.setTarikhMasuk(new java.util.Date());
        temp.setInfoAudit(info1);
        temp.setCawangan(pengguna.getKodCawangan());
        temp.setPermohonan(p);
        temp.setKodUrusan(p.getKodUrusan());
        temp.setNoJilid(p.getIdKumpulan());      // temp use to store id berangkai
        temp.setTempohTahun(p.getKumpulanNo());  // temp use to store no kump
        temp.setNoFolio("temp");
        pService.simpanUrusanBetul(temp);
      }

      Permohonan p = permohonanService.findById(idPermohonan);
      /* get list of id mohon sort by id mohon */
      List<Permohonan> permohonan = permohonanService.findByIdKump(p.getIdKumpulan());
      for (Permohonan s : permohonan) {        
        String kodSerah = s.getKodUrusan().getKodPerserahan().getKod();
        /* get list of temp id sort by kump no */
        List<PermohonanPembetulanUrusan> tempUrusan = pService.findByNoJilid(p.getIdKumpulan());
        for (PermohonanPembetulanUrusan temp : tempUrusan) {
          String tempKodSerah = temp.getKodUrusan().getKodPerserahan().getKod();
          /* group kod serah for id mohon and temp */
          if (kodSerah.equals(tempKodSerah)) {
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            s.setInfoAudit(info);
            s.setKodUrusan(temp.getKodUrusan());
            s.setKumpulanNo(temp.getTempohTahun());
            permohonanService.update(s);
            LOG.info("-- Delete temp --");
            pService.deleteTambahUrusan(temp); // delete temp data 
            break;
          }
        }
      }
    }

    addSimpleMessage("Urusan Berjaya Disusun");
    return new RedirectResolution(SusunPerserahanActionBean.class, "search")
            .addParameter("idPermohonan", idPermohonan)
            .addParameter("strDate", trkh);
  }

//    public Resolution susunSemula() {
//        if (isDebug) {
//            LOG.debug("***susun semula BERMULA*****");
//        }
//
//        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        Transaction tx = sessionProvider.get().beginTransaction();
//        tx.begin();
//
//        try {
//            for (int i = 0; i < ids.length; i++) {
//                String str = ids[i];
//                if (isDebug) {
//                    LOG.debug("ids[" + i + "]=" + str);
//                }
//                if (StringUtils.isBlank(str)) {
//                    continue;
//                }
//
//                Permohonan p = permohonanService.findById(str);
//                if (p == null) {
//                    continue;
//                }
//
//                InfoAudit ia = new InfoAudit();
//                ia.setDimasukOleh(p.getInfoAudit().getDimasukOleh());
//                ia.setTarikhKemaskini(new Date());
//                ia.setDiKemaskiniOleh(pengguna);
//
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
//
//                Date _d = new Date();
//                try {
//                    _d = sdf.parse(dt[i]);
//                } catch (Exception ex) {
//                    LOG.error(ex);
//                    tx.rollback();
//                    addSimpleError(ex.getMessage());
//                    return search();
//                }
//
//                LOG.debug("date :" + _d.toString());
//                ia.setTarikhMasuk(_d);
//                LOG.debug("date :" + ia.getTarikhMasuk().toString());
//                p.setInfoAudit(ia);
//
//                permohonanService.saveOrUpdateConn(p);//TODO :fixme
//            }
//        } catch (Exception ex) {
//            tx.rollback();
//            addSimpleError(ex.getMessage());
//            return new JSP("daftar/utiliti/susun_perserahan.jsp");
//        }
//        tx.commit();
//        addSimpleMessage("Kemaskini Data Berjaya.");
//        if (isDebug) {
//            LOG.debug("***susun semula HABIS*****");
//        }
//        return search();
//    }
  public String getIdPermohonan() {
    return idPermohonan;
  }

  public void setIdPermohonan(String idPermohonan) {
    this.idPermohonan = idPermohonan;
  }

  public List<Permohonan> getSenaraiPerserahan() {
    return senaraiPerserahan;
  }

  public void setSenaraiPerserahan(List<Permohonan> senaraiPerserahan) {
    this.senaraiPerserahan = senaraiPerserahan;
  }

  public String[] getDt() {
    return dt;
  }

  public void setDt(String[] dt) {
    this.dt = dt;
  }

  public String[] getIds() {
    return ids;
  }

  public void setIds(String[] ids) {
    this.ids = ids;
  }

  public String getIdHakmilik() {
    return idHakmilik;
  }

  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;
  }
}
