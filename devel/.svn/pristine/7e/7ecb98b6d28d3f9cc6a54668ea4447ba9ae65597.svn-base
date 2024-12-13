/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.model.AduanTindakan;
import etanah.model.Dokumen;
import etanah.model.KodCawangan;


import etanah.model.KodTindakanPenguatkuasaan;
import etanah.service.common.EnforcementService;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanTindakanDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
//import etanah.model.KodStatusBarangRampasan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/remedi_tambahan")
public class RemediTambahanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(RemediTambahanActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AduanTindakanDAO aduanTindakanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    EnforcementService enforcementService;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private KodTindakanPenguatkuasaan tindakan;
    private AduanTindakan aduanTindakan;
    private Dokumen dokumen;
    private Pengguna peng;
    private String idPermohonan;
    private String tarikhTamat;
    private String tarikhMula;


    private int tempohHari;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/remedi_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/remedi_tambahan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            aduanTindakan = enforcementService.findRemediTambahanByIdmohon(idPermohonan);

            
        }
    }

    public Resolution simpan() throws ParseException {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        cawangan = permohonan.getCawangan();

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        InfoAudit iaPermohonan = new InfoAudit();
        if (dokumen == null) {
            dokumen = new Dokumen();
            iaPermohonan.setTarikhMasuk(new Date());
            iaPermohonan.setDimasukOleh(peng);
        } else {
            iaPermohonan.setTarikhKemaskini(new Date());
            iaPermohonan.setDiKemaskiniOleh(peng);
        }

        KodDokumen kodDoc = new KodDokumen();
        kodDoc.setKod("PDT");
        dokumen.setKodDokumen(kodDoc);
        dokumen.setTajuk("Perintah Remedi Tambahan");
        dokumen.setNoVersi("1.0");
        dokumen.setInfoAudit(iaPermohonan);
        dokumenDAO.saveOrUpdate(dokumen);



        if (aduanTindakan == null) {
            aduanTindakan = new AduanTindakan();
            iaPermohonan.setDimasukOleh(peng);
            iaPermohonan.setTarikhMasuk(new Date());
        } else {
            iaPermohonan.setDiKemaskiniOleh(peng);
            iaPermohonan.setTarikhKemaskini(new java.util.Date());
        }
        aduanTindakan.setPermohonan(permohonan);
        aduanTindakan.setCawangan(cawangan);
        aduanTindakan.setDokumen(dokumen);
        tindakan = new KodTindakanPenguatkuasaan();
        tindakan.setKod("RET");
        aduanTindakan.setTindakan(tindakan);
        tarikhMula = getContext().getRequest().getParameter("aduanTindakan.tarikhMula");
        aduanTindakan.setTarikhMula(new Date());
        tempohHari = Integer.parseInt(getContext().getRequest().getParameter("aduanTindakan.tempohHari"));
        aduanTindakan.setTempohHari(tempohHari);
        tarikhTamat = getContext().getRequest().getParameter("aduanTindakan.tarikhTamat");
        aduanTindakan.setTarikhTamat(sdf.parse(tarikhTamat));
        aduanTindakan.setInfoAudit(iaPermohonan);
        aduanTindakanDAO.saveOrUpdate(aduanTindakan);
        tx.commit();
         getContext().getRequest().setAttribute("form", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/remedi_tambahan.jsp").addParameter("tab", "true");
    }

    public AduanTindakan getAduanTindakan() {
        return aduanTindakan;
    }

    public void setAduanTindakan(AduanTindakan aduanTindakan) {
        this.aduanTindakan = aduanTindakan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodTindakanPenguatkuasaan getTindakan() {
        return tindakan;
    }

    public void setTindakan(KodTindakanPenguatkuasaan tindakan) {
        this.tindakan = tindakan;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public int getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(int tempohHari) {
        this.tempohHari = tempohHari;
    }


}
