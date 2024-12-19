/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import etanah.view.strata.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.*;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author siti.mudmainnah
 */
@UrlBinding("/strata/KemasukkanSifus")
public class KemasukanSifus extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KemasukanSifus.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Pengguna pengguna;
    private Projek sifus;
    private String idHakmilik;
    private String gunaBngn;
    private String noRujukan;
    private List<Projek> listSifus = new ArrayList();
    @Inject
    StrataPtService strataPtService;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    }

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemasukanSifus.jsp");
    }

    public Resolution cari() {
        LOG.info(idHakmilik);
        LOG.info(noRujukan);
        Hakmilik hm = null;
        if (idHakmilik != null) {
            hm = hakmilikDAO.findById(idHakmilik);
        }
        if (idHakmilik == null && noRujukan == null) {
            addSimpleError("Sila masukkan id hakmilik atau no rujukan fail ");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemasukanSifus.jsp");
        }
        listSifus = strataPtService.findSifus(idHakmilik, noRujukan, "");

        if (listSifus.size() <= 0) {
            if (idHakmilik != null) {
                if (hm != null) {
                    addSimpleError("Hakmilik ini belum mendapat sijil SIFUS. Sila klik butang 'Tambah' untuk tambah sijil SIFUS");
                    getContext().getRequest().setAttribute("add", Boolean.TRUE);
                } else {
                    addSimpleError("ID Hakmilik tidak dijumpai");
                }
            }
        } else {
            if (idHakmilik != null) {
                getContext().getRequest().setAttribute("add", Boolean.TRUE);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemasukanSifus.jsp");
    }

    public Resolution addNew() {
        LOG.info(idHakmilik);
        getContext().getRequest().setAttribute("add", Boolean.TRUE);
        sifus = new Projek();
        return new JSP("strata/utiliti/kemasukanSifusPopup.jsp").addParameter("popup", "true");
    }

    public Resolution hapusSifus() {
        String idProjek = getContext().getRequest().getParameter("idProjek");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        sifus = strataPtService.findSifus(idProjek, null);

        if (sifus != null) {
            strataPtService.delProjek(sifus);
        }
        addSimpleMessage("Maklumat telah berjaya dihapuskan");
        getContext().getRequest().setAttribute("add", Boolean.TRUE);
        cari();
        return new JSP("strata/utiliti/kemasukanSifusPopup.jsp");
    }

    public Resolution edit() {
        String idProjek = getContext().getRequest().getParameter("idProjek");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        sifus = strataPtService.findSifus(idProjek, null);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/utiliti/kemasukanSifusPopup.jsp").addParameter("popup", "true");
    }

    public Resolution save() {
        String idProjek = getContext().getRequest().getParameter("idProjek");
        String status = getContext().getRequest().getParameter("status");

        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(pengguna);
        ia2.setTarikhKemaskini(new Date());

        Long idProjek1 = sifus.getIdProjek();
        Projek proj = strataPtService.findSifus(String.valueOf(idProjek1), null);
        if (proj != null) {
            proj.setAktif(status);
            proj.setFormula(sifus.getFormula());//fromula
            if (gunaBngn != null) {
                proj.setGunaBngn(kodKegunaanBangunanDAO.findById(gunaBngn));//guna_bngn
            } else {
                proj.setKegunaanLain(sifus.getKegunaanLain());
            }
            proj.setIdHakmilik(idHakmilik);//id_jhakmilik
            proj.setInfoAudit(ia2);
            proj.setJenisProjek(sifus.getJenisProjek());//nama skim
            proj.setJumlahSemuaUnit(sifus.getJumlahSemuaUnit());//bil_petak
            proj.setNamaPemaju(sifus.getNamaPemaju());//pemohon
            proj.setNoRujFail(sifus.getNoRujFail());//ruj_fail
            proj.setPemilik(sifus.getPemilik());//pemilik
            proj.setTarikhLulus(sifus.getTarikhLulus());
            proj.setMaksimumUnit(sifus.getMaksimumUnit());//siri
            proj.setNoRujukanProjek(sifus.getNoRujukanProjek());//ruj projek
            proj.setBilAksr(sifus.getBilAksr());
            proj.setTarikhLulusBngn(sifus.getTarikhLulusBngn());
            proj.setUnitDiJual(sifus.getUnitDiJual());
//            proj.setInfoAudit(ia2);
            strataPtService.simpanProjek(proj);
            addSimpleMessage("Maklumat telah berjaya disimpan");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            sifus = strataPtService.findSifus(String.valueOf(idProjek1), null);
        } else {
            addSimpleError("Maklumat tidak berjaya disimpan");
        }
        return new JSP("strata/utiliti/kemasukanSifusPopup.jsp").addParameter("popup", "true");
    }

    public Resolution saveBru() {
        LOG.info(gunaBngn);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());

        if (sifus != null) {
            Projek pj = new Projek();
            pj.setAktif(sifus.getAktif());
            pj.setFormula(sifus.getFormula());//fromula
            if (gunaBngn != null) {
                pj.setGunaBngn(kodKegunaanBangunanDAO.findById(gunaBngn));//guna_bngn
            } else {
                pj.setKegunaanLain(sifus.getKegunaanLain());
            }
            pj.setIdHakmilik(idHakmilik);//id_jhakmilik
            pj.setInfoAudit(ia);
            pj.setJenisProjek(sifus.getJenisProjek());//nama skim
            pj.setJumlahSemuaUnit(sifus.getJumlahSemuaUnit());//bil_petak
            pj.setNamaPemaju(sifus.getNamaPemaju());//pemohon
            pj.setNoRujFail(sifus.getNoRujFail());//ruj_fail
            pj.setPemilik(sifus.getPemilik());//pemilik
            pj.setTarikhLulus(sifus.getTarikhLulus());
            pj.setMaksimumUnit(sifus.getMaksimumUnit());//siri
            pj.setNoRujukanProjek(sifus.getNoRujukanProjek());//ruj projek
            pj.setBilAksr(sifus.getBilAksr());
            pj.setTarikhLulusBngn(sifus.getTarikhLulusBngn());
            pj.setUnitDiJual(sifus.getUnitDiJual());

            strataPtService.simpanProjek(pj);
            addSimpleMessage("Maklumat telah berjaya disimpan");
            getContext().getRequest().setAttribute("add", Boolean.TRUE);
        } else {
            addSimpleError("Maklumat tidak berjaya disimpan");
            getContext().getRequest().setAttribute("add", Boolean.TRUE);
        }
        return new JSP("strata/utiliti/kemasukanSifusPopup.jsp").addParameter("popup", "true");
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Projek getSifus() {
        return sifus;
    }

    public void setSifus(Projek sifus) {
        this.sifus = sifus;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public List<Projek> getListSifus() {
        return listSifus;
    }

    public void setListSifus(List<Projek> listSifus) {
        this.listSifus = listSifus;
    }

    public String getGunaBngn() {
        return gunaBngn;
    }

    public void setGunaBngn(String gunaBngn) {
        this.gunaBngn = gunaBngn;
    }

}
