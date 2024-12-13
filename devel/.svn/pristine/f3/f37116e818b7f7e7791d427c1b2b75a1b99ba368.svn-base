/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
@HttpCache(allow = false)
@UrlBinding("/common/req_penyerah_info")
public class RequestPenyerahInfoForm extends AbleActionBean {

  private List<Penyerah> senaraiPenyerah;
  private String jenisPenyerah;
  private String namaPenyerah;
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
  @Inject
  etanah.Configuration conf;
  private String idPenyerah;
  private String penyerahNama;
  private String penyerahAlamat1;
  private String penyerahAlamat2;
  private String penyerahAlamat3;
  private String penyerahAlamat4;
  private String penyerahPoskod;
  private String penyerahNegeri;
  private String penyerahNoPengenalan;
  private String penyerahJenisPengenalan;
  private String penyerahNoTelefon;
  private String penyerahEmail;
  private String kodPenyerah;
  private String kodKementerian;
  private String temp;
  Logger LOG = Logger.getLogger(RequestPenyerahInfoForm.class);
  private boolean isPopup = false;
  private boolean isSSHMA = false;
  private boolean form = false;
  private Pengguna pengguna;

  @DefaultHandler
  public Resolution showForm() {
    String popup = getContext().getRequest().getParameter("popup");
    String SSHMA = getContext().getRequest().getParameter("SSHMA");
    if (StringUtils.isNotBlank(popup)) {
      isPopup = true;
    }
    if (StringUtils.isNotBlank(SSHMA)) {
      isSSHMA = true;
    }

    if (isPopup) {
      if (isSSHMA) {
        getContext().getRequest().setAttribute("popup", "true");
        getContext().getRequest().setAttribute("SSHMA", "true");
        return new JSP("kaunter/carian_penyerah.jsp").addParameter("popup", "true");
      } else {
        getContext().getRequest().setAttribute("popup", "true");
        return new JSP("kaunter/carian_penyerah.jsp").addParameter("popup", "true");
      }
    } else {
      return new JSP("kaunter/carian_penyerah.jsp");
    }
  }

  public Resolution showFormPopup() {
    getContext().getRequest().setAttribute("popup", "true");
    return new JSP("kaunter/carian_penyerah.jsp").addParameter("popup", "true");

  }

  public Resolution showFormEditPenyerah() {
    return new JSP("kaunter/edit_penyerah.jsp");
  }

  public Resolution searchPenyerahForEdit() {
    findPenyerah();
    return showFormEditPenyerah();
  }

  public Resolution searchPenyerah() {
    LOG.info("::start cari penyerah::");
    pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    findPenyerah();
    return showForm();
  }

  public Resolution searchPenyerahPopup() {
    LOG.info("::start cari penyerah popup::");
    findPenyerah();
    return showFormPopup();
  }

  public void findPenyerah() {
    Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    if (StringUtils.isNotBlank(jenisPenyerah)) {
      LOG.info("--- kod penyerah " + jenisPenyerah);
      if (jenisPenyerah.equals("01")) {
        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
          senaraiPenyerah = pi.findAll();
        } else {
          senaraiPenyerah = pi.findPenyerahByParam(getContext().getRequest().getParameterMap());
        }
//            {
//                temp = idPenyerah;
//                idPenyerah = "";
//                if (StringUtils.isNotBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)){
//                    senaraiPenyerah = pi.findPenyerahByParam2(getContext().getRequest().getParameterMap());
//                    idPenyerah = temp;
//                    temp = "";
//                }
//            
//            else if (StringUtils.isNotBlank(namaPenyerah) && StringUtils.isNotBlank(idPenyerah) && StringUtils.isBlank(temp)){
//                senaraiPenyerah = pi.findPenyerahByParam(getContext().getRequest().getParameterMap());
//            }
//                }
      } else if (jenisPenyerah.equals("02")) {
        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
          senaraiPenyerah = ji.findAll();
        } else {
          senaraiPenyerah = ji.findPenyerahByParam(getContext().getRequest().getParameterMap());
        }
      } else if (jenisPenyerah.equals("04")) {
        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
          senaraiPenyerah = jlb.findAll();
        } else {
          senaraiPenyerah = jlb.findPenyerahByParam(getContext().getRequest().getParameterMap());
        }
      } else if (jenisPenyerah.equals("00")) {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        ud.setCawangan(ctx.getKodCawangan());

        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
          senaraiPenyerah = ud.findAll();
        } else {
          //add by azri 9/7/2013 : get all PTG and PTD 'Unit Dalaman' if PTG    
          if ("05".equals(conf.getProperty("kodNegeri")) && "00".equals(pguna.getKodCawangan().getKod())) {
            senaraiPenyerah = ud.findPenyerahByParamAllKodCaw(getContext().getRequest().getParameterMap());
          } else {
            senaraiPenyerah = ud.findPenyerahByParam(getContext().getRequest().getParameterMap());
          }
        }
      } else if ((jenisPenyerah.equals("05")) || (jenisPenyerah.equals("06")) || (jenisPenyerah.equals("07"))) {
        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
          senaraiPenyerah = ai.findAll();
        } else {
          senaraiPenyerah = ai.findPenyerahByParam(getContext().getRequest().getParameterMap());
        }
      }
    }
    if (senaraiPenyerah != null) {
      if (senaraiPenyerah.isEmpty()) {
        form = true;
      }
    }
  }

  public Resolution aktif() {
    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String idPenyerah = (String) getContext().getRequest().getParameter("idPenyerah");
//        String kodPenyerah = (String) getContext().getRequest().getParameter("kodPenyerah");
    LOG.info(":START ACTIVATE PENYERAH:");
    LOG.debug("idPenyerah : " + idPenyerah);
    LOG.debug("kodPenyerah : " + kodPenyerah);
    if (kodPenyerah.equals("01")) {
      Peguam p = pi.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      p.setAktif('Y');
      p.setInfoAudit(ia);
      pi.updateOrSave(p);
    } else if (jenisPenyerah.equals("02")) {
      JUBL p = ji.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAktif('Y');
      p.setInfoAudit(ia);
      ji.updateOrSave(p);
    } else if (jenisPenyerah.equals("04")) {
      JuruLelong p = jlb.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAktif('Y');
      p.setInfoAudit(ia);
      jlb.updateOrSave(p);
    } else if (jenisPenyerah.equals("00")) {
      KodCawanganJabatan p = ud.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAktif('Y');
      p.setInfoAudit(ia);
      ud.updateOrSave(p);
    } else if ((jenisPenyerah.equals("05")) || (jenisPenyerah.equals("06")) || (jenisPenyerah.equals("07"))) {
      KodAgensi p = ai.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAktif('Y');
      p.setInfoAudit(ia);
      ai.updateOrSave(p);
    }
//        else if (jenisPenyerah.equals("06")) {
//            KodAgensi p = ai.findById(Long.parseLong(idPenyerah));
//            InfoAudit ia = p.getInfoAudit();
//            ia.setDiKemaskiniOleh(pengguna);
//            ia.setTarikhKemaskini(new Date());
//            p.setAktif('Y');
//            p.setInfoAudit(ia);
//            ai.updateOrSave(p);
//        }

    addSimpleMessage("Pengaktifan data berjaya");
    findPenyerah();
    return showForm();
//        return new JSP("kaunter/carian_penyerah.jsp");
    //return new ForwardResolution("/WEB-INF/jsp/kaunter/carian_penyerah.jsp");
  }

  public Resolution hapus() {
    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String idPenyerah = (String) getContext().getRequest().getParameter("idPenyerah");
//        String kodPenyerah = (String) getContext().getRequest().getParameter("kodPenyerah");
    LOG.info(":START DEACTIVATE PENYERAH:");
    LOG.debug("idPenyerah : " + idPenyerah);
    LOG.debug("kodPenyerah : " + kodPenyerah);
    if (kodPenyerah.equals("01")) {
      Peguam p = pi.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      p.setAktif('T');
      p.setInfoAudit(ia);
      pi.updateOrSave(p);
    } else if (jenisPenyerah.equals("02")) {
      JUBL p = ji.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAktif('T');
      p.setInfoAudit(ia);
      ji.updateOrSave(p);
    } else if (jenisPenyerah.equals("04")) {
      JuruLelong p = jlb.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAktif('T');
      p.setInfoAudit(ia);
      jlb.updateOrSave(p);
    } else if (jenisPenyerah.equals("00")) {
      KodCawanganJabatan p = ud.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAktif('T');
      p.setInfoAudit(ia);
      ud.updateOrSave(p);
    } else if ((jenisPenyerah.equals("05")) || (jenisPenyerah.equals("06")) || (jenisPenyerah.equals("07"))) {
      KodAgensi p = ai.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAktif('T');
      p.setInfoAudit(ia);
      ai.updateOrSave(p);
    }
    addSimpleMessage("Tidak Aktif data berjaya");
    findPenyerah();
    return showForm();
//        return new JSP("kaunter/carian_penyerah.jsp");
    //return new ForwardResolution("/WEB-INF/jsp/kaunter/carian_penyerah.jsp");
  }

  public Resolution tambah() {
    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String idPenyerah = (String) getContext().getRequest().getParameter("idPenyerah");
//        String kodPenyerah = (String) getContext().getRequest().getParameter("kodPenyerah");
    LOG.debug("kodPenyerah = " + kodPenyerah);
    LOG.debug("idPenyerah = " + idPenyerah);
    if (kodPenyerah.equals("01")) {
      Peguam p = pi.findById(Long.parseLong(idPenyerah));
      if (p == null) {
        Peguam penyerah = new Peguam();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
        penyerah.setAlamat1(penyerahAlamat1);
        penyerah.setAlamat2(penyerahAlamat2);
        penyerah.setAlamat3(penyerahAlamat3);
        penyerah.setAlamat4(penyerahAlamat4);
        penyerah.setPoskod(penyerahPoskod);
        penyerah.setNoTelefon1(penyerahNoTelefon);
        LOG.debug("=======================EMELLLL==============" + penyerahEmail);
        penyerah.setEmel(penyerahEmail);
        penyerah.setNama(penyerahNama);
        penyerah.setCawangan(pengguna.getKodCawangan());
        penyerah.setIdPeguam(Long.parseLong(idPenyerah));
        penyerah.setAktif('Y');
        penyerah.setInfoAudit(ia);
        pi.updateOrSave(penyerah);
        addSimpleMessage("Penambahan Data Berjaya");
      } else {
        addSimpleMessage("Data telah Wujud");
      }
    } else if (kodPenyerah.equals("02")) {
      JUBL p = ji.findById(Long.parseLong(idPenyerah));
      if (p == null) {
        JUBL penyerah = new JUBL();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
        penyerah.setAlamat1(penyerahAlamat1);
        penyerah.setAlamat2(penyerahAlamat2);
        penyerah.setAlamat3(penyerahAlamat3);
        penyerah.setAlamat4(penyerahAlamat4);
        penyerah.setPoskod(penyerahPoskod);
        penyerah.setNoTelefon1(penyerahNoTelefon);
        penyerah.setEmel(penyerahEmail);
        penyerah.setNama(penyerahNama);
        penyerah.setCawangan(pengguna.getKodCawangan());
        penyerah.setInfoAudit(ia);
        penyerah.setIdJubl(Long.parseLong(idPenyerah));
        penyerah.setAktif('Y');
        ji.updateOrSave(penyerah);
        addSimpleMessage("Penambahan Data Berjaya");
      } else {
        addSimpleMessage("Data telah Wujud");
      }
    } else if (kodPenyerah.equals("04")) {
      JuruLelong p = jlb.findById(Long.parseLong(idPenyerah));
      if (p == null) {
        JuruLelong penyerah = new JuruLelong();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
        penyerah.setAlamat1(penyerahAlamat1);
        penyerah.setAlamat2(penyerahAlamat2);
        penyerah.setAlamat3(penyerahAlamat3);
        penyerah.setAlamat4(penyerahAlamat4);
        penyerah.setPoskod(penyerahPoskod);
        penyerah.setNoTelefon1(penyerahNoTelefon);
        penyerah.setEmel(penyerahEmail);
        penyerah.setNama(penyerahNama);
        penyerah.setCawangan(pengguna.getKodCawangan());
        penyerah.setInfoAudit(ia);
        penyerah.setIdJlb(Long.parseLong(idPenyerah));
        penyerah.setAktif('Y');
        jlb.updateOrSave(penyerah);
        addSimpleMessage("Penambahan Data Berjaya");
      } else {
        addSimpleMessage("Data telah Wujud");
      }
      /*} else if (kodPenyerah.equals("00")) { // JABATAN -- FORBIDDEN TO ADD
       KodJabatan p = ud.findById(Long.parseLong(idPenyerah));
       if (p == null) {
       KodJabatan penyerah = new KodJabatan();
       InfoAudit ia = new InfoAudit();
       ia.setDimasukOleh(pengguna);
       ia.setTarikhMasuk(new Date());
       LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
       penyerah.setAlamat1(penyerahAlamat1);
       penyerah.setAlamat2(penyerahAlamat2);
       penyerah.setAlamat3(penyerahAlamat3);
       penyerah.setAlamat4(penyerahAlamat4);
       penyerah.setPoskod(penyerahPoskod);
       penyerah.setNoTelefon1(penyerahNoTelefon);
       penyerah.setNama(penyerahNama);
       penyerah.setInfoAudit(ia);
       penyerah.setKod(idPenyerah);
       penyerah.setAktif('Y');
       ud.updateOrSave(penyerah);
       addSimpleMessage("Penambahan Data Berjaya");
       } else {
       addSimpleMessage("Data telah Wujud");
       }
       */
    } else if ((kodPenyerah.equals("05")) || (kodPenyerah.equals("06")) || (kodPenyerah.equals("07"))) {
      KodAgensi p = ai.findById(Long.parseLong(idPenyerah));
      if (p == null) {
        KodAgensi penyerah = new KodAgensi();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
        penyerah.setAlamat1(penyerahAlamat1);
        penyerah.setAlamat2(penyerahAlamat2);
        penyerah.setAlamat3(penyerahAlamat3);
        penyerah.setAlamat4(penyerahAlamat4);
        penyerah.setPoskod(penyerahPoskod);
        penyerah.setNoTelefon1(penyerahNoTelefon);
        penyerah.setEmel(penyerahEmail);
        penyerah.setNama(penyerahNama);
        //add by azri 18/7/2013
        penyerah.setKodKementerian(Integer.parseInt(kodKementerian));
        penyerah.setInfoAudit(ia);
        penyerah.setKod(idPenyerah);
        penyerah.setAktif('Y');
        ai.updateOrSave(penyerah);
        addSimpleMessage("Penambahan Data Berjaya");
      } else {
        addSimpleMessage("Data telah Wujud");
      }
    }

//        getContext().getRequest().setAttribute("edit", "true");
//        return searchPenyerahForEdit();
    findPenyerah();
    return showForm();
  }

  public Resolution update() {
    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    LOG.debug("jenisPenyerah = " + jenisPenyerah);
    if (jenisPenyerah.equals("01")) {
      Peguam p = pi.findById(Long.parseLong(idPenyerah));
      InfoAudit ia = p.getInfoAudit();
      KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
      ia.setDiKemaskiniOleh(null);
      ia.setTarikhKemaskini(new Date());
      p.setAlamat1(penyerahAlamat1);
      p.setAlamat2(penyerahAlamat2);
      p.setAlamat3(penyerahAlamat3);
      p.setAlamat4(penyerahAlamat4);
      p.setPoskod(penyerahPoskod);
      p.setNoTelefon1(penyerahNoTelefon);
      p.setEmel(penyerahEmail);
      p.setNama(penyerahNama);
      p.setNoPengenalan(penyerahNoPengenalan);

      KodNegeri kodNeg = new KodNegeri();
      kodNeg.setKod(penyerahNegeri);
      p.setNegeri(kodNeg);

      p.setJenisPengenalan(kjp);
      p.setInfoAudit(ia);

      pi.updateOrSave(p);
      LOG.debug("--------ID PENYERAH---- : " + idPenyerah);
      LOG.debug("===========Save==============");
    } else if (jenisPenyerah.equals("02")) {
      JUBL p = ji.findById(Long.parseLong(idPenyerah));
      KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAlamat1(penyerahAlamat1);
      p.setAlamat2(penyerahAlamat2);
      p.setAlamat3(penyerahAlamat3);
      p.setAlamat4(penyerahAlamat4);
      p.setPoskod(penyerahPoskod);
      p.setNoTelefon1(penyerahNoTelefon);
      p.setEmel(penyerahEmail);
      p.setNama(penyerahNama);
      p.setNoPengenalan(penyerahNoPengenalan);
      p.setJenisPengenalan(kjp);
      KodNegeri kodNeg = new KodNegeri();
      kodNeg.setKod(penyerahNegeri);
      p.setNegeri(kodNeg);;
      p.setInfoAudit(ia);
      ji.updateOrSave(p);
    } else if (jenisPenyerah.equals("04")) {
      JuruLelong p = jlb.findById(Long.parseLong(idPenyerah));
      KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAlamat1(penyerahAlamat1);
      p.setAlamat2(penyerahAlamat2);
      p.setAlamat3(penyerahAlamat3);
      p.setAlamat4(penyerahAlamat4);
      p.setPoskod(penyerahPoskod);
      p.setNoTelefon1(penyerahNoTelefon);
      p.setEmel(penyerahEmail);
      p.setNama(penyerahNama);
      p.setNoPengenalan(penyerahNoPengenalan);
      KodNegeri kodNeg = new KodNegeri();
      kodNeg.setKod(penyerahNegeri);
      p.setNegeri(kodNeg);
      p.setJenisPengenalan(kjp);
      p.setInfoAudit(ia);
      jlb.updateOrSave(p);
    } else if (jenisPenyerah.equals("00")) {
      KodCawanganJabatan p = ud.findById(Long.parseLong(idPenyerah));
      KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAlamat1(penyerahAlamat1);
      p.setAlamat2(penyerahAlamat2);
      p.setAlamat3(penyerahAlamat3);
      p.setAlamat4(penyerahAlamat4);
      p.setPoskod(penyerahPoskod);
      p.setNoTelefon1(penyerahNoTelefon);
      p.setEmel(penyerahEmail);
      p.setNama(penyerahNama);
//            p.setJenisPengenalan(kjp);
      KodNegeri kodNeg = new KodNegeri();
      kodNeg.setKod(penyerahNegeri);
      p.setNegeri(kodNeg);
      p.setInfoAudit(ia);
      ud.updateOrSave(p);
    } else if ((jenisPenyerah.equals("05")) || (jenisPenyerah.equals("06")) || (jenisPenyerah.equals("07"))) {
      KodAgensi p = ai.findById(Long.parseLong(idPenyerah));
      KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
      InfoAudit ia = p.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new Date());
      p.setAlamat1(penyerahAlamat1);
      p.setAlamat2(penyerahAlamat2);
      p.setAlamat3(penyerahAlamat3);
      p.setAlamat4(penyerahAlamat4);
      p.setPoskod(penyerahPoskod);
      p.setNoTelefon1(penyerahNoTelefon);
      p.setEmel(penyerahEmail);
      p.setNama(penyerahNama);
//            p.setJenisPengenalan(kjp);
      KodNegeri kodNeg = new KodNegeri();
      kodNeg.setKod(penyerahNegeri);
      p.setNegeri(kodNeg);
      p.setInfoAudit(ia);
      ai.updateOrSave(p);
    }

    addSimpleMessage("Kemaskini Data Berjaya");
    getContext().getRequest().setAttribute("edit", "true");
    return searchPenyerahForEdit();
  }

  public boolean isIsPopup() {
    return isPopup;
  }

  public void setIsPopup(boolean isPopup) {
    this.isPopup = isPopup;
  }

  public boolean isForm() {
    return form;
  }

  public void setForm(boolean form) {
    this.form = form;
  }

  public String getKodPenyerah() {
    return kodPenyerah;
  }

  public void setKodPenyerah(String kodPenyerah) {
    this.kodPenyerah = kodPenyerah;
  }

  public List<Penyerah> getSenaraiPenyerah() {
    return senaraiPenyerah;
  }

  public void setSenaraiPenyerah(List<Penyerah> senaraiPenyerah) {
    this.senaraiPenyerah = senaraiPenyerah;
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

  public String getIdPenyerah() {
    return idPenyerah;
  }

  public void setIdPenyerah(String idPenyerah) {
    this.idPenyerah = idPenyerah;
  }

  public String getPenyerahAlamat1() {
    return penyerahAlamat1;
  }

  public void setPenyerahAlamat1(String penyerahAlamat1) {
    this.penyerahAlamat1 = penyerahAlamat1;
  }

  public String getPenyerahAlamat2() {
    return penyerahAlamat2;
  }

  public void setPenyerahAlamat2(String penyerahAlamat2) {
    this.penyerahAlamat2 = penyerahAlamat2;
  }

  public String getPenyerahAlamat3() {
    return penyerahAlamat3;
  }

  public void setPenyerahAlamat3(String penyerahAlamat3) {
    this.penyerahAlamat3 = penyerahAlamat3;
  }

  public String getPenyerahAlamat4() {
    return penyerahAlamat4;
  }

  public void setPenyerahAlamat4(String penyerahAlamat4) {
    this.penyerahAlamat4 = penyerahAlamat4;
  }

  public String getPenyerahJenisPengenalan() {
    return penyerahJenisPengenalan;
  }

  public void setPenyerahJenisPengenalan(String penyerahJenisPengenalan) {
    this.penyerahJenisPengenalan = penyerahJenisPengenalan;
  }

  public String getPenyerahNama() {
    return penyerahNama;
  }

  public void setPenyerahNama(String penyerahNama) {
    this.penyerahNama = penyerahNama;
  }

  public String getPenyerahNegeri() {
    return penyerahNegeri;
  }

  public void setPenyerahNegeri(String penyerahNegeri) {
    this.penyerahNegeri = penyerahNegeri;
  }

  public String getPenyerahNoPengenalan() {
    return penyerahNoPengenalan;
  }

  public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
    this.penyerahNoPengenalan = penyerahNoPengenalan;
  }

  public String getPenyerahPoskod() {
    return penyerahPoskod;
  }

  public void setPenyerahPoskod(String penyerahPoskod) {
    this.penyerahPoskod = penyerahPoskod;
  }

  public String getPenyerahEmail() {
    return penyerahEmail;
  }

  public void setPenyerahEmail(String penyerahEmail) {
    this.penyerahEmail = penyerahEmail;
  }

  public String getPenyerahNoTelefon() {
    return penyerahNoTelefon;
  }

  public void setPenyerahNoTelefon(String penyerahNoTelefon) {
    this.penyerahNoTelefon = penyerahNoTelefon;
  }

  public boolean isIsSSHMA() {
    return isSSHMA;
  }

  public void setIsSSHMA(boolean isSSHMA) {
    this.isSSHMA = isSSHMA;
  }

  public String getKodKementerian() {
    return kodKementerian;
  }

  public void setKodKementerian(String kodKementerian) {
    this.kodKementerian = kodKementerian;
  }

    public Pengguna getPengguna() {
        
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
        
    }
  
  
}
