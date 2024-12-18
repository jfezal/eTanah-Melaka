/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodSenaraiDAO;
import etanah.dao.SenaraiRujukanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodJabatan;
import etanah.model.KodSenarai;
import etanah.model.Pengguna;
import etanah.model.SenaraiRujukan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/utiliti_datok_lembaga")
public class UtilitiDatokLembagaActionBean extends AbleActionBean {

    @Inject
    SenaraiRujukanDAO senaraiRujukanDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    KodSenaraiDAO kodSenaraiDAO;
    private KodSenarai kodSenarai;
    private SenaraiRujukan senaraiRujukan;
    private List<KodSenarai> kodSenaraiList;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/consent/utiliti_datok_lembaga.jsp");
    }

    public Resolution simpan() {

        if (senaraiRujukan == null) {
            addSimpleError("Sila masukkan jenis suku.");
        } else {
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            kodSenaraiList = conService.getKodSenaraiByJabatanAndKod("22", "S");

            int max = 0;
            int value;

            for (KodSenarai kodSen : kodSenaraiList) {

                value = Integer.parseInt(kodSen.getKod().substring(1, kodSen.getKod().length()));

                if (value > max) {
                    max = value;
                }
            }

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            KodJabatan kodJabatan = new KodJabatan();
            kodJabatan.setKod("22");

            int kod = max + 1;
            kodSenarai.setKod("S" + kod);
            kodSenarai.setKodJabatan(kodJabatan);
            kodSenarai.setInfoAudit(infoAudit);

            senaraiRujukan.setKod("S" + kod);
            senaraiRujukan.setSenarai(kodSenarai);
            senaraiRujukan.setInfoAudit(infoAudit);

            conService.simpanKodSenarai(kodSenarai);
            conService.simpanSenaraiRujukan(senaraiRujukan);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        return new ForwardResolution("/WEB-INF/jsp/consent/utiliti_datok_lembaga.jsp");
    }

    public List<KodSenarai> getKodSenaraiList() {
        return kodSenaraiList;
    }

    public void setKodSenaraiList(List<KodSenarai> kodSenaraiList) {
        this.kodSenaraiList = kodSenaraiList;
    }

    public SenaraiRujukan getSenaraiRujukan() {
        return senaraiRujukan;
    }

    public void setSenaraiRujukan(SenaraiRujukan senaraiRujukan) {
        this.senaraiRujukan = senaraiRujukan;
    }

    public KodSenarai getKodSenarai() {
        return kodSenarai;
    }

    public void setKodSenarai(KodSenarai kodSenarai) {
        this.kodSenarai = kodSenarai;
    }
}
