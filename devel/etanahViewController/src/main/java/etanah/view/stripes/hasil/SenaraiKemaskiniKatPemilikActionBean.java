/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodKatPemilikDAO;
import etanah.model.Hakmilik;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodKatPemilik;
import etanah.model.Pengguna;
import etanah.service.HasilService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/hasil/senarai_hakmilik_hasil")
public class SenaraiKemaskiniKatPemilikActionBean extends AbleActionBean {

    List<SenaraiHakmilikHasilForm> senaraiView;
    String kodBPM;
    String kodKatTanah;
    String kodCaw;
    String kodDaerah;
    String[] katPemilik = new String[20];
    String[] idHakmilik = new String[20];
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<KodDaerah> senaraiKodDaerah;
    private List<KodKatPemilik> senaraiKodKatPemilik;
    Long count;
    @Inject
    HasilService hasilService;
    @Inject
    KodKatPemilikDAO kodKatPemilikDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    HakmilikDAO hakmilikDAO;

    private boolean validateParam(Pengguna pengguna) {
        kodCaw = pengguna.getKodCawangan().getKod();
        senaraiKodKatPemilik = kodKatPemilikDAO.findAll();
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            senaraiKodDaerah = kodDaerahDAO.findAll();
            if (StringUtils.isNotEmpty(kodDaerah)) {
                listBandarPekanMukim = hasilService.findBPMByDaerah(kodDaerah);

            }
        } else {
            listBandarPekanMukim = hasilService.findBPMByDaerah(pengguna.getKodCawangan().getDaerah().getKod());
            kodDaerah = pengguna.getKodCawangan().getDaerah().getKod();
        }
        if (StringUtils.isEmpty(kodBPM)) {
            addSimpleError("Sila Pilih Mukim ");
            return false;
        }

        if (StringUtils.isEmpty(kodKatTanah)) {
            addSimpleError("Sila Pilih Kategori Tanah");
            return false;
        }

        return true;
    }

    @DefaultHandler
    public Resolution showForm() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (validateParam(pengguna)) {
                        count = hasilService.count(kodCaw, kodDaerah, kodBPM, kodKatTanah);

            senaraiView = hasilService.paparSenaraiView(kodCaw, kodDaerah, kodBPM, kodKatTanah);

        }
        return new JSP("hasil/hakmilik_hasil.jsp");
    }

    public Resolution penyukatanBPM() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        validateParam(pengguna);
        return new JSP("hasil/hakmilik_hasil.jsp");
    }

    public Resolution cari() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiKodKatPemilik = kodKatPemilikDAO.findAll();
        senaraiKodDaerah = kodDaerahDAO.findAll();
        if (validateParam(pengguna)) {
            count = hasilService.count(kodCaw, kodDaerah, kodBPM, kodKatTanah);
            senaraiView = hasilService.paparSenaraiView(kodCaw, kodDaerah, kodBPM, kodKatTanah);

        }
        return new JSP("hasil/hakmilik_hasil.jsp");
    }

    public Resolution simpan() {
        idHakmilik = getContext().getRequest().getParameterValues("idHakmilik");
        katPemilik = getContext().getRequest().getParameterValues("katPemilik");
        for (int i = 0; i < idHakmilik.length; i++) {
            Hakmilik ha = hakmilikDAO.findById(idHakmilik[i]);
            if (katPemilik[i].isEmpty()) {
            } else {
                KodKatPemilik kodKatPemilik = kodKatPemilikDAO.findById(katPemilik[i]);
                ha.setKodKatPemilik(kodKatPemilik);
                hasilService.simpanHakmilik(ha);
            }

        }
        return new RedirectResolution(SenaraiKemaskiniKatPemilikActionBean.class).addParameter("kodBPM", kodBPM).addParameter("kodKatTanah", kodKatTanah).addParameter("kodDaerah", kodDaerah);
    }

    public List<SenaraiHakmilikHasilForm> getSenaraiView() {
        return senaraiView;
    }

    public void setSenaraiView(List<SenaraiHakmilikHasilForm> senaraiView) {
        this.senaraiView = senaraiView;
    }

    public String getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(String kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getKodKatTanah() {
        return kodKatTanah;
    }

    public void setKodKatTanah(String kodKatTanah) {
        this.kodKatTanah = kodKatTanah;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public List<KodDaerah> getSenaraiKodDaerah() {
        return senaraiKodDaerah;
    }

    public void setSenaraiKodDaerah(List<KodDaerah> senaraiKodDaerah) {
        this.senaraiKodDaerah = senaraiKodDaerah;
    }

    public String[] getKatPemilik() {
        return katPemilik;
    }

    public void setKatPemilik(String[] katPemilik) {
        this.katPemilik = katPemilik;
    }

    public List<KodKatPemilik> getSenaraiKodKatPemilik() {
        return senaraiKodKatPemilik;
    }

    public void setSenaraiKodKatPemilik(List<KodKatPemilik> senaraiKodKatPemilik) {
        this.senaraiKodKatPemilik = senaraiKodKatPemilik;
    }

    public String[] getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String[] idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }


}
