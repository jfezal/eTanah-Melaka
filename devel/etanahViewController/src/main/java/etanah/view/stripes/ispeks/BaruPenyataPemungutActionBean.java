/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodPeringkatIspeksDAO;
import etanah.model.Pengguna;
import etanah.model.ispek.KodPeringkatIspeks;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.PenyataResit;
import etanah.model.ispek.TugasanIspeks;
import etanah.model.view.ResitPenyataPemungut;
import etanah.service.ispeks.PenyataPemungutService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/ispeks/cipta_penyata_pemungut")
public class BaruPenyataPemungutActionBean extends AbleActionBean {

    @Inject
    PenyataPemungutService penyataPemungutService;
    @Inject
    KodPeringkatIspeksDAO kodPeringkatIspeksDAO;
    String tarikhMula;
    String tarikhHingga;
    String tarikh;
    String idPenggunaKaunter;
    Integer limit;
    String noKaunter;
    String noAccBank;
    String caraBayar;
    String modBayaran;
    String kodCaw;
    String noResit;
    String[] idKewDok;
    String[] exclude;
    BigDecimal totalAmaun;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    List<ResitPenyataPemungut> senaraiResitPenyataPemungut;

    @DefaultHandler
    public Resolution showForm() {
//        List<ResitPenyataPemungut> l = penyataPemungutService.testView();
        return new JSP("ispeks/jana_penyata_pemungut.jsp");
    }

    public Resolution populateResit() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodCaw = pengguna.getKodCawangan().getKod();
        senaraiResitPenyataPemungut = penyataPemungutService.paparRekod(caraBayar, kodCaw, StringUtils.isNotBlank(tarikh) ? sdf.parse(tarikh) : null, idPenggunaKaunter, noKaunter, modBayaran, noResit, limit);
        totalAmaun = penyataPemungutService.sumAmaun(caraBayar, kodCaw, StringUtils.isNotBlank(tarikh) ? sdf.parse(tarikh) : null, idPenggunaKaunter, noKaunter, modBayaran, noResit, limit);
        return new JSP("ispeks/jana_penyata_pemungut.jsp");
    }

    public Resolution simpanPenyata() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodCaw = pengguna.getKodCawangan().getKod();
        String s[] = getContext().getRequest().getParameterValues("idKewDok");
        String ex[] = getContext().getRequest().getParameterValues("exclude");
        if (s != null) {
            PenyataPemungut pp = new PenyataPemungut();
            pp.setDiJanaOleh(pengguna);
            pp.setNoPenyata(penyataPemungutService.generatNoPenyata(kodCaw));
            pp.setKodCaw(pengguna.getKodCawangan());
            pp.setNoSlipBank(pp.getNoPenyata());
            pp.setCaraBayar(caraBayar);
            pp.setTarikhJana(new Date());
            pp = penyataPemungutService.simpanPenyata(pp);
            for (int i = 0; i < s.length; i++) {
                if (ex != null) {
                    if (ex.length > 0) {
                        List<String> list = Arrays.asList(ex);
                        if (list.contains(s[i])) {
                        } else {
                            PenyataResit p = new PenyataResit();
                            p.setIdKewDok(s[i]);
                            p.setIdPenyata(pp.getId());
                            penyataPemungutService.simpanPenyataResit(p);
                        }
                    } else {
                        PenyataResit p = new PenyataResit();
                        p.setIdKewDok(s[i]);
                        p.setIdPenyata(pp.getId());
                        penyataPemungutService.simpanPenyataResit(p);
                    }
                } else {
                    PenyataResit p = new PenyataResit();
                    p.setIdKewDok(s[i]);
                    p.setIdPenyata(pp.getId());
                    penyataPemungutService.simpanPenyataResit(p);
                }
            }
            TugasanIspeks t = new TugasanIspeks();
            KodPeringkatIspeks kodPeringkat = new KodPeringkatIspeks();
            kodPeringkat = kodPeringkatIspeksDAO.findById("PPSED");
            t.setKodPeringkat(kodPeringkat);
            t.setNoRef(pp.getId() + "");
            t.setTarikhTerima(new Date());
            t.setKodCaw(pengguna.getKodCawangan());
            penyataPemungutService.simpanTugasanIspeks(t);
            addSimpleMessage("Penyata telah berjaya disimpan");
        } else {
            addSimpleError("Tiada maklumat resit");
        }
        return new JSP("ispeks/jana_penyata_pemungut.jsp");
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhHingga() {
        return tarikhHingga;
    }

    public void setTarikhHingga(String tarikhHingga) {
        this.tarikhHingga = tarikhHingga;
    }

    public String getNoAccBank() {
        return noAccBank;
    }

    public void setNoAccBank(String noAccBank) {
        this.noAccBank = noAccBank;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(String caraBayar) {
        this.caraBayar = caraBayar;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public List<ResitPenyataPemungut> getSenaraiResitPenyataPemungut() {
        return senaraiResitPenyataPemungut;
    }

    public void setSenaraiResitPenyataPemungut(List<ResitPenyataPemungut> senaraiResitPenyataPemungut) {
        this.senaraiResitPenyataPemungut = senaraiResitPenyataPemungut;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getIdPenggunaKaunter() {
        return idPenggunaKaunter;
    }

    public void setIdPenggunaKaunter(String idPenggunaKaunter) {
        this.idPenggunaKaunter = idPenggunaKaunter;
    }

    public BigDecimal getTotalAmaun() {
        return totalAmaun;
    }

    public void setTotalAmaun(BigDecimal totalAmaun) {
        this.totalAmaun = totalAmaun;
    }

    public String getNoKaunter() {
        return noKaunter;
    }

    public void setNoKaunter(String noKaunter) {
        this.noKaunter = noKaunter;
    }

    public String getModBayaran() {
        return modBayaran;
    }

    public void setModBayaran(String modBayaran) {
        this.modBayaran = modBayaran;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

}
