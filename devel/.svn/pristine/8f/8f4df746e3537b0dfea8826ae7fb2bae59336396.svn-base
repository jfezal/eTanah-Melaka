/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.service.ConsentPtdService;
import etanah.service.common.PemohonService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/common/maklumat_pemohon")
public class MaklumatPemohonActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PemohonService pemohonService;
    Permohonan permohonan = new Permohonan();
    Pihak pihak = new Pihak();
    private String[] arrayCheckBox;
    private String idPermohonan;
    private List<Pihak> listTuanTanah;
    private List<Pemohon> listPemohon;
    private String display;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        display = "display:none;";
        return new JSP("common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");

        pihak = pihakDAO.findById(Long.valueOf(idPihak));

        return new JSP("common/edit_pemohon2.jsp").addParameter("popup", "true");
    }

    public Resolution popup() {

        return new JSP("common/pilih_pemohon.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        try {
            if (idPermohonan != null) {
                listTuanTanah = new LinkedList<Pihak>();
                permohonan = permohonanDAO.findById(idPermohonan);
                List<HakmilikPermohonan> l = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : l) {
                    Hakmilik hk = hp.getHakmilik();
                    List<HakmilikPihakBerkepentingan> lhpk = hk.getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hpk : lhpk) {
                        Pihak phk = hpk.getPihak();
                        listTuanTanah.add(phk);
                    }
                    senaraiHakmilikPihak = new ArrayList<HakmilikPihakBerkepentingan>();
                    senaraiHakmilikPihak = lhpk;
                }
            }

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

            ArrayList listDelete = new ArrayList();

            for (int i = 0; i < listPemohon.size(); i++) {

                for (int j = 0; j < listTuanTanah.size(); j++) {

                    if (listPemohon.get(i).getPihak().equals(listTuanTanah.get(j))) {

                        listDelete.add(listPemohon.get(i).getPihak());

                    }
                }
            }

            int count = 0;

            for (int i = 0; i < listDelete.size(); i++) {

                listTuanTanah.remove(listPemohon.get(i).getPihak());
                count++;
            }
        } catch (Exception ex) {
           
        }
    }

    public Resolution simpanPemohon() {

        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit info = p.getInfoAudit();

        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());

        String idPihak = getContext().getRequest().getParameter("idPihak");

        Pihak phk = pihakDAO.findById(Long.valueOf(idPihak));
        Pemohon pemohon = new Pemohon();
        pemohon.setPermohonan(permohonan);
        pemohon.setPihak(phk);
        pemohon.setCawangan(p.getKodCawangan());
        pemohon.setInfoAudit(info);

        pemohon = conService.simpanPemohon(pemohon);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        return new StreamingResolution("text/plain", String.valueOf(pemohon.getIdPemohon()));

    }

    public Resolution deletePemohon() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                pemohonService.delete(pemohon);
                listPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }

        return new JSP("common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new JSP("common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPihak() {
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pg);
        ia.setTarikhKemaskini(new java.util.Date());
        Pihak p = pihakDAO.findById(pihak.getIdPihak());
        p.setSuratAlamat1(pihak.getSuratAlamat1());
        p.setSuratAlamat2(pihak.getSuratAlamat2());
        p.setSuratAlamat3(pihak.getSuratAlamat3());
        p.setSuratAlamat4(pihak.getSuratAlamat4());
        p.setSuratPoskod(pihak.getSuratPoskod());
        p.setSuratNegeri(pihak.getSuratNegeri());
        p.setNoTelefon1(pihak.getNoTelefon1());
        p.setEmail(pihak.getEmail());
        ia.setDimasukOleh(p.getInfoAudit().getDimasukOleh());
        ia.setTarikhMasuk(p.getInfoAudit().getTarikhMasuk());
        p.setInfoAudit(ia);
        conService.simpanPihak(p);
        return refreshpage();
    }

    public List<Pihak> getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(List<Pihak> listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String[] getArrayCheckBox() {
        return arrayCheckBox;
    }

    public void setArrayCheckBox(String[] arrayCheckBox) {
        this.arrayCheckBox = arrayCheckBox;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }
}
