/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/perakuanWaran")
public class PerakuanKeputusanWaranActionBean extends AbleActionBean {

    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    CommonService comm;
    @Inject
    StrataPtService strService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private String legend = "";
    private String taklimatWaran;
    private String perakuan;
    private PermohonanKertas mohonKertas = null;
    private boolean readOnly = false;
    private String keputusanRadio;
    private String negeri;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = strService.getInfo(peng);
        mohonKertas = strService.findKertas(idPermohonan);
        if (mohonKertas == null) {
            mohonKertas = new PermohonanKertas();
            mohonKertas.setCawangan(peng.getKodCawangan());
            mohonKertas.setPermohonan(permohonan);
            mohonKertas.setTajuk("Kertas Pertimbangan Waran Penahanan");
            mohonKertas.setInfoAudit(ia);
            strService.simpanPermohonanKertas(mohonKertas);
        }
    }

    public Resolution taklimat() {
        List<PermohonanKertasKandungan> listKertasKand = strService.findByIdLapor(mohonKertas.getIdKertas(), 5);
        if (!listKertasKand.isEmpty()) {
            taklimatWaran = listKertasKand.get(0).getKandungan();
        }

        legend = "Taklimat Pelaksana Waran";
        return new JSP("strata/waran/taklimatPelaksanaWaran.jsp").addParameter("tab", "true");
    }

    public Resolution taklimatReadOnly() {
        List<PermohonanKertasKandungan> listKertasKand = strService.findByIdLapor(mohonKertas.getIdKertas(), 5);
        if (!listKertasKand.isEmpty()) {
            taklimatWaran = listKertasKand.get(0).getKandungan();
        }

        legend = "Taklimat Pelaksana Waran";
        readOnly = true;
        return new JSP("strata/waran/taklimatPelaksanaWaran.jsp").addParameter("tab", "true");
    }

    public Resolution perakuanWaranForm() {
        negeri = conf.getProperty("kodNegeri");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        List<PermohonanKertasKandungan> listKertasKand = strService.findByIdLapor(mohonKertas.getIdKertas(), 10);
        if (!listKertasKand.isEmpty()) {
            perakuan = listKertasKand.get(0).getKandungan();
        }
        if (negeri.equals("04")) {
            legend = "Perakuan dan Syor Pentadbir Tanah";
        } else {
            legend = "Perakuan dan Syor Pentadbir Tanah";
        }


        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "keputusan");
        if (mohonFasa != null) {
            if (mohonFasa.getKeputusan() != null) {
                keputusanRadio = mohonFasa.getKeputusan().getKod();
            }
        }
        return new JSP("strata/waran/perakuanWaran.jsp").addParameter("tab", "true");
    }

    public Resolution perakuanWaranFormReadOnly() {
        List<PermohonanKertasKandungan> listKertasKand = strService.findByIdLapor(mohonKertas.getIdKertas(), 10);
        if (!listKertasKand.isEmpty()) {
            perakuan = listKertasKand.get(0).getKandungan();
        }
        legend = "Perakuan dan Syor Pentadbir Tanah";
        readOnly = true;
        return new JSP("strata/waran/perakuanWaran.jsp").addParameter("tab", "true");
    }

    public Resolution savePerakuan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = strService.getInfo(peng);
        negeri = conf.getProperty("kodNegeri");
        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "keputusan");
        if (negeri.equals("05")) {
            if (mohonFasa != null) {
                mohonFasa.setKeputusan(kodKeputusanDAO.findById(keputusanRadio));
                if (perakuan != null) {
                    mohonFasa.setUlasan(perakuan);
                } else {
                    addSimpleError("Sila masukkan maklumat Perakuan.");
                    return new JSP("strata/waran/perakuanWaran.jsp").addParameter("tab", "true");
                }
                mohonFasa.setTarikhKeputusan(new java.util.Date());
            } else {
                mohonFasa = new FasaPermohonan();
                mohonFasa.setKeputusan(kodKeputusanDAO.findById(keputusanRadio));
                if (perakuan != null) {
                    mohonFasa.setUlasan(perakuan);
                    mohonFasa.setTarikhKeputusan(new java.util.Date());
                } else {
                    addSimpleError("Sila masukkan maklumat Perakuan.");
                    return new JSP("strata/waran/perakuanWaran.jsp").addParameter("tab", "true");
                }
                mohonFasa.setInfoAudit(strService.getInfo(peng));
                mohonFasa.setPermohonan(permohonanDAO.findById(idPermohonan));
                mohonFasa.setIdAliran("keputusan");
                mohonFasa.setIdPengguna(peng.getIdPengguna());
                mohonFasa.setCawangan(peng.getKodCawangan());
            }
            strService.saveFasaMohon(mohonFasa);
        }

        mohonKertas = strService.findKertas(idPermohonan);
        List<PermohonanKertasKandungan> listKertasKand = strService.findByIdLapor(mohonKertas.getIdKertas(), 10);
        for (PermohonanKertasKandungan pkk : listKertasKand) {
            pkk.setKertas(mohonKertas);
            if (perakuan != null) {
                pkk.setKandungan(perakuan);
            } else {
                addSimpleError("Sila masukkan maklumat Perakuan.");
                return new JSP("strata/waran/perakuanWaran.jsp").addParameter("tab", "true");
            }
            strService.simpanPermohonanKertasKandungan(pkk);
        }
        if (listKertasKand.isEmpty()) {
            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
            mohonKertasKand.setBil(10);
            mohonKertasKand.setCawangan(peng.getKodCawangan());
            if (perakuan != null) {
                mohonKertasKand.setKandungan(perakuan);
            } else {
                addSimpleError("Sila masukkan maklumat Perakuan.");
                return new JSP("strata/waran/perakuanWaran.jsp").addParameter("tab", "true");
            }
            mohonKertasKand.setInfoAudit(ia);
            mohonKertasKand.setKertas(mohonKertas);
//        mohonKertasKand.setTarikhButiran(null);
            strService.simpanPermohonanKertasKandungan(mohonKertasKand);
        }
        addSimpleMessage("Maklumat berjaya disimpan");
        legend = "Perakuan dan Syor Pentadbir Tanah";
        return new JSP("strata/waran/perakuanWaran.jsp").addParameter("tab", "true");
    }

    public Resolution saveTaklimat() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = strService.getInfo(peng);
        mohonKertas = strService.findKertas(idPermohonan);
        List<PermohonanKertasKandungan> listKertasKand = strService.findByIdLapor(mohonKertas.getIdKertas(), 5);
        for (PermohonanKertasKandungan pkk : listKertasKand) {
            pkk.setKertas(mohonKertas);
            pkk.setKandungan(taklimatWaran);
            strService.simpanPermohonanKertasKandungan(pkk);
        }
        if (listKertasKand.isEmpty()) {
            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
            mohonKertasKand.setBil(5);
            mohonKertasKand.setCawangan(peng.getKodCawangan());
            mohonKertasKand.setKandungan(taklimatWaran);
            mohonKertasKand.setInfoAudit(ia);
            mohonKertasKand.setKertas(mohonKertas);
//        mohonKertasKand.setTarikhButiran(null);
            strService.simpanPermohonanKertasKandungan(mohonKertasKand);
        }
        addSimpleMessage("Maklumat berjaya disimpan");
        legend = "Taklimat Pelaksana Waran";
        return new JSP("strata/waran/taklimatPelaksanaWaran.jsp").addParameter("tab", "true");
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public String getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(String perakuan) {
        this.perakuan = perakuan;
    }

    public String getTaklimatWaran() {
        return taklimatWaran;
    }

    public void setTaklimatWaran(String taklimatWaran) {
        this.taklimatWaran = taklimatWaran;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getKeputusanRadio() {
        return keputusanRadio;
    }

    public void setKeputusanRadio(String keputusanRadio) {
        this.keputusanRadio = keputusanRadio;
    }
}
