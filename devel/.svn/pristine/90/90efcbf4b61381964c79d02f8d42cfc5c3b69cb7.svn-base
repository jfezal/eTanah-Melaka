               /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
//import etanah.dao.FasaPermohonanUlasanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
//import etanah.model.FasaPermohonanUlasan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/pelupusan/laporan_status_tanah")
public class LaporanStatusTanahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
//    FasaPermohonanUlasanDAO fasaPermohonanUlasanDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    String latarBelakang;
    String ulasanJabatanKebajikan;
    String ulasanJabatanTenagaKerja;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/laporan_status_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pelupusan/laporan_status_tanah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpan"})
    public void rehydrate() {

//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
//
//        HakmilikPermohonan hp = new HakmilikPermohonan();
//
//        hp = permohonan.getSenaraiHakmilik().get(0);
//        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
//
//        String[] tname = {"permohonan"};
//        Object[] model = {permohonan};
//
//        List<Pemohon> listPemohon;
//        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
//
//        if (!(listPemohon.isEmpty())) {
//            pemohon = listPemohon.get(0);
//        }
//        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());
//
//        if (!(permohonan.getSenaraiFasa().isEmpty())) {
//
//            for (int i = 0; i < permohonan.getSenaraiFasa().size(); i++) {
//
//                FasaPermohonan fasaP = new FasaPermohonan();
//                fasaP = permohonan.getSenaraiFasa().get(i);
//
//                for (int j = 0; j < fasaP.getSenaraiUlasan().size(); j++) {
//
//                    FasaPermohonanUlasan fasaU = new FasaPermohonanUlasan();
//                    fasaU = fasaP.getSenaraiUlasan().get(j);
//                    tarikhMesyuarat = sdf.format(fasaU.getInfoAudit().getTarikhMasuk());
//
//                    if (fasaU.getBil() == 1) {
//                        latarBelakang = fasaU.getUlasan();
//                    } else if (fasaU.getBil() == 2) {
//                        ulasanJabatanKebajikan = fasaU.getUlasan();
//                    } else if (fasaU.getBil() == 3) {
//                        ulasanJabatanTenagaKerja = fasaU.getUlasan();
//                    } else if (fasaU.getBil() == 4) {
//                        huraianPtg = fasaU.getUlasan();
//                    } else if (fasaU.getBil() == 5) {
//                        syorPtg = fasaU.getUlasan();
//                    }
//                }
//            }
//        }
    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (tarikhMesyuarat == null) {

            addSimpleError("Sila masukkan tarikh mesyuarat.");
//        } else {
//
//            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//            InfoAudit infoAudit = new InfoAudit();
//            infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//
//            FasaPermohonan fasaPermohonan = new FasaPermohonan();
//            fasaPermohonan.setInfoAudit(infoAudit);
//            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
//            fasaPermohonan.setCawangan(pengguna.getKodCawangan());
//            fasaPermohonan.setPermohonan(permohonan);
//            conService.simpanFasaPermohonan(fasaPermohonan);
//
//            ArrayList listUlasan = new ArrayList();
//            listUlasan.add(latarBelakang);
//            listUlasan.add(ulasanJabatanKebajikan);
//            listUlasan.add(ulasanJabatanTenagaKerja);
//            listUlasan.add(huraianPtg);
//            listUlasan.add(syorPtg);
//
//            for (int i = 0; i < listUlasan.size(); i++) {
//
//                String ulasan = (String) listUlasan.get(i);
//
//                try {
//                    infoAudit.setTarikhMasuk(sdf.parse(tarikhMesyuarat));
//                } catch (java.text.ParseException ex) {
//                    Logger.getLogger(JktdActionBean.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                FasaPermohonanUlasan fasaUlasan = new FasaPermohonanUlasan();
//                fasaUlasan.setFasa(fasaPermohonan);
//                fasaUlasan.setBil(i + 1);
//                fasaUlasan.setInfoAudit(infoAudit);
//                fasaUlasan.setUlasan(ulasan);
//                conService.simpanFasaPermohonanUlasan(fasaUlasan);
//            }
//
//            addSimpleMessage("Maklumat telah berjaya disimpan.");
//        }


            addSimpleError("Sila masukkan tarikh mesyuarat.");
        } else {

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            FasaPermohonan fasaPermohonan = new FasaPermohonan();
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setCawangan(pengguna.getKodCawangan());
            fasaPermohonan.setPermohonan(permohonan);
            conService.simpanFasaPermohonan(fasaPermohonan);

            ArrayList listUlasan = new ArrayList();
            listUlasan.add(latarBelakang);
            listUlasan.add(ulasanJabatanKebajikan);
            listUlasan.add(ulasanJabatanTenagaKerja);
            listUlasan.add(huraianPtg);
            listUlasan.add(syorPtg);

//            for (int i = 0; i < listUlasan.size(); i++) {
//
//                String ulasan = (String) listUlasan.get(i);
//
//                try {
//                    infoAudit.setTarikhMasuk(sdf.parse(tarikhMesyuarat));
//                } catch (java.text.ParseException ex) {
//                    Logger.getLogger(JktdActionBean.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                FasaPermohonanUlasan fasaUlasan = new FasaPermohonanUlasan();
//                fasaUlasan.setFasa(fasaPermohonan);
//                fasaUlasan.setBil(i + 1);
//                fasaUlasan.setInfoAudit(infoAudit);
//                fasaUlasan.setUlasan(ulasan);
//                conService.simpanFasaPermohonanUlasan(fasaUlasan);
//            }

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/laporan_status_tanah.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getHuraianPtg() {
        return huraianPtg;
    }

    public void setHuraianPtg(String huraianPtg) {
        this.huraianPtg = huraianPtg;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getUlasanJabatanKebajikan() {
        return ulasanJabatanKebajikan;
    }

    public void setUlasanJabatanKebajikan(String ulasanJabatanKebajikan) {
        this.ulasanJabatanKebajikan = ulasanJabatanKebajikan;
    }

    public String getUlasanJabatanTenagaKerja() {
        return ulasanJabatanTenagaKerja;
    }

    public void setUlasanJabatanTenagaKerja(String ulasanJabatanTenagaKerja) {
        this.ulasanJabatanTenagaKerja = ulasanJabatanTenagaKerja;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }
}


