/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BorangPerHakmilikDAO;
import etanah.dao.KodPeringkatDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.NotaSiasatan;
import etanah.model.ambil.NotaSiasatanLengkap;
import etanah.service.acq.service.BorangEFService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pengambilan.share.form.BorangEForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/pengambilan/perbicaraan")
public class TetapPerbicaraanActionBean extends BasicTabActionBean {

    @Inject
    BorangEFService borangEFService;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    BorangPerHakmilikDAO borangPerHakmilikDAO;
    @Inject
    KodPeringkatDAO kodPeringkatDAO;
    List<BorangEForm> listBorangE;
    String[] check;
    String tarikhBicara;
    String masaBicara;
    String tempatBicara;
    String keteranganBicara;
    String noBicara;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        listBorangE = borangEFService.findHakmilikAktifNotTDK(idPermohonan);
        return new JSP("/pengambilan/APT/tetap_tarikh_bicara.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        NotaSiasatan notas = pengambilanAPTService.findNotaSiasatanKodPeringkat(idPermohonan, "NOTAS");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (notas != null) {
        } else {
            notas = new NotaSiasatan();
            notas.setKodPeringkat(kodPeringkatDAO.findById("NOTAS"));
            notas.setPermohonan(permohonanDAO.findById(idPermohonan));
            notas.setInfoAudit(setIa(pengguna));
            pengambilanAPTService.saveNotaSiasatan(notas);
        }
        if (check != null) {
            for (String s : check) {
                BorangPerHakmilik bph = borangPerHakmilikDAO.findById(Long.valueOf(s));
                HakmilikPerbicaraan bicara = pengambilanAPTService.findHakmilikPerbicaraanByIdMH(Long.valueOf(bph.getHakmilikPermohonan().getId()));
                if (bicara == null) {
                    bicara = new HakmilikPerbicaraan();
                }

                bicara.setHakmilikPermohonan(bph.getHakmilikPermohonan());
                bicara.setTarikhBicara(sdf.parse(tarikhBicara));
                bicara.setLokasiBicara(tempatBicara);
                bicara.setCawangan(permohonan.getCawangan());
                bicara.setNoRujukan(noBicara);
                pengambilanAPTService.saveHakmilikPerbicaraan(bicara);

                for (BorangPerPB pb : bph.getSenaraiBorangPerPB()) {
                    NotaSiasatanLengkap nsl = pengambilanAPTService.findNotaSiasatanLengkapByIdPB(pb.getId());
                    
                    if(nsl == null){
                         nsl = new NotaSiasatanLengkap();
                    }
                    
                    nsl.setNotaSiasatan(notas);
                    nsl.setKeteranganicara(keteranganBicara);
                    nsl.setMasaBicara(masaBicara);
                    nsl.setNoBicara(noBicara);
                    nsl.setTarikhBicara(sdf.parse(tarikhBicara));
                    nsl.setTempatBicara(tempatBicara);
                    nsl.setInfoAudit(setIa(pengguna));
                    nsl.setBorangPerPB(pb);
                    pengambilanAPTService.saveNotaSiasatanLengkap(nsl);

                }
            }
        } else {
            addSimpleError("Sila Pilih Hakmilik Terdahulu");
        }

//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        listBorangE = borangEFService.findHakmilikAktifNotTDK(idPermohonan);
        return new JSP("/pengambilan/APT/tetap_tarikh_bicara.jsp").addParameter("tab", "true");
    }

    InfoAudit setIa(Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public List<BorangEForm> getListBorangE() {
        return listBorangE;
    }

    public void setListBorangE(List<BorangEForm> listBorangE) {
        this.listBorangE = listBorangE;
    }

    public String[] getCheck() {
        return check;
    }

    public void setCheck(String[] check) {
        this.check = check;
    }

    public BorangEFService getBorangEFService() {
        return borangEFService;
    }

    public void setBorangEFService(BorangEFService borangEFService) {
        this.borangEFService = borangEFService;
    }

    public String getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(String tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public String getMasaBicara() {
        return masaBicara;
    }

    public void setMasaBicara(String masaBicara) {
        this.masaBicara = masaBicara;
    }

    public String getTempatBicara() {
        return tempatBicara;
    }

    public void setTempatBicara(String tempatBicara) {
        this.tempatBicara = tempatBicara;
    }

    public String getKeteranganBicara() {
        return keteranganBicara;
    }

    public void setKeteranganBicara(String keteranganBicara) {
        this.keteranganBicara = keteranganBicara;
    }

    public String getNoBicara() {
        return noBicara;
    }

    public void setNoBicara(String noBicara) {
        this.noBicara = noBicara;
    }

}
