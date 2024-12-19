/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import java.text.ParseException;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Dokumen;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;

import etanah.model.Pengguna;
import etanah.service.common.EnforcementService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/mesyuarat")
public class MesyuaratActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(AduanSiasatanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    EnforcementService enforcementService;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private KodCawangan cawangan;
    private KodDokumen koddokumen;
    private String idRujukan;
    private String tarikhSidang;
    private String jam;
    private String minit;
    private String saat;
    private String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mesyuarat.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mesyuarat.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        idRujukan = getContext().getRequest().getParameter("idRujukan");
        String kod_doc = "SMMR";
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            permohonanRujukanLuar.setPermohonan(permohonan);
            String[] key = {"permohonan.idPermohonan", "kodDokumenRujukan.kod"};
            Object[] value = {idPermohonan, kod_doc};
            pengguna.getPerananUtama().getKod();
            List<PermohonanRujukanLuar> list = permohonanRujukanLuarDAO.findByEqualCriterias(key, value, null);
            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                try {

                    tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang());

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            try {
                permohonanRujukanLuar = enforcementService.findByKodDokumen(idPermohonan);
                if (permohonanRujukanLuar.getTarikhSidang() != null) {

                    tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
                    jam = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(11, 13);
                    minit = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(14, 16);
                    saat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(17, 19);
                    ampm = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(20, 22);
                }
            } catch (Exception ex) {
                logger.error(ex);
            }
            if (idRujukan != null && list != null) {
                for (PermohonanRujukanLuar rujuk : list) {
                    if (String.valueOf(rujuk.getIdRujukan()).equals(idRujukan)) {
                        permohonanRujukanLuar = rujuk;
                        break;
                    }
                }
            }
        }

    }

    public Resolution simpan() throws ParseException {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        KodDokumen doc = new KodDokumen();
        doc.setKod("SMMR");

        if (permohonanRujukanLuar == null) {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
        }
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(cawangan);
        permohonanRujukanLuar.setKodDokumenRujukan(doc);

        tarikhSidang = tarikhSidang + " " + jam + ":" + minit + ":" + saat + " " + ampm;

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonanRujukanLuar.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
        enforcementService.simpanRujukan(permohonanRujukanLuar);
        tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
        logger.debug("tess1 :" + permohonanRujukanLuar.getIdRujukan());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        logger.debug("MaklumatLokasiActionBean::simpan::" + permohonanRujukanLuar.getIdRujukan());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mesyuarat.jsp").addParameter("tab", "true");

    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public KodDokumen getKoddokumen() {
        return koddokumen;
    }

    public void setKoddokumen(KodDokumen koddokumen) {
        this.koddokumen = koddokumen;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }
}
