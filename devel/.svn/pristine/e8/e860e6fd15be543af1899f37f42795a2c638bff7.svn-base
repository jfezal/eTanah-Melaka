/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.AduanPemantauanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.AduanPemantauan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Notis;
import etanah.model.Dokumen;
import etanah.model.Pengguna;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_pemantauan")
public class MaklumatPemantauanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatPemantauanActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    AduanPemantauanDAO aduanPemantauanDAO;
    @Inject
    EnforcementService enforcementService;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private AduanPemantauan aduanPemantauan;
    private Notis notisPenguatkuasaan;
    private KodCawangan cawangan;
    private String idPemantauan;
    private String tarikhPemantauan;
    private String idpengguna;
    private List<AduanPemantauan> senaraiAduanPemantauan;
    private String catatan;
    private String mesej;
    private String jam;
    private String minit;
    private String saat;
    private String ampm;
    private Boolean errorMsg = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
    public Resolution showForm() {
        if (errorMsg) {
            addSimpleError(mesej);
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pemantauan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pemantauan.jsp").addParameter("tab", "true");
    }

    public Resolution popup() {
        return new JSP("penguatkuasaan/maklumat_pemantauan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution popupedit() {
        idPemantauan = getContext().getRequest().getParameter("idPemantauan");
        aduanPemantauan = aduanPemantauanDAO.findById(Long.parseLong(idPemantauan));
        return new JSP("penguatkuasaan/maklumat_pemantauan_edit.jsp").addParameter("popup", "true");
    }

//    public Resolution refreshpage() {
//        return new RedirectResolution(MaklumatPemantauanActionBean.class, "locate");
//    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        notisPenguatkuasaan = enforcementService.findByIdNotis(idPermohonan, "NK");

        idPemantauan = getContext().getRequest().getParameter("idPemantauan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
//            aduanPemantauan = aduanPemantauanDAO.findById(idPemantauan);

            if (notisPenguatkuasaan != null) {
                if (notisPenguatkuasaan.getKodNotis().getKod().equals("NK")) {
                    Date c1 = notisPenguatkuasaan.getTarikhNotis();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(c1);
                    cal.add(Calendar.DATE, notisPenguatkuasaan.getTempohHari());

                    String tarikhNotis = sdf.format(notisPenguatkuasaan.getTarikhNotis()).substring(0, 10);
                    Date current = new java.util.Date();

                    if (cal.getTime().before(current)) {
                        setErrorMsg(false);
//                        senaraiAduanPemantauan = enforcementService.findByID(idPermohonan);
//                        if (idPemantauan != null) {
//                            aduanPemantauan = enforcementService.findAduanPemantauanByIdPemantauan(Long.parseLong(idPemantauan));
//                            if (aduanPemantauan.getTarikhPemantauan() != null) {
//                                jam = sdf.format(aduanPemantauan.getTarikhPemantauan()).substring(11, 13);
//                                minit = sdf.format(aduanPemantauan.getTarikhPemantauan()).substring(14, 16);
//                                saat = "00";
//                                ampm = sdf.format(aduanPemantauan.getTarikhPemantauan()).substring(20, 22);
//                            }
//                        }
                    } else {
                        setErrorMsg(true);
                        setMesej("Sila jalankan pemantauan setelah tamat tempoh " + notisPenguatkuasaan.getTempohHari() + " hari "
                                + "dari tarikh " + tarikhNotis + " dan buat kemasukan");
                    }
                }
            } else {
                setErrorMsg(false);
            }
            senaraiAduanPemantauan = enforcementService.findByID(idPermohonan);
            if (idPemantauan != null) {
                aduanPemantauan = enforcementService.findAduanPemantauanByIdPemantauan(Long.parseLong(idPemantauan));
                if (aduanPemantauan.getTarikhPemantauan() != null) {
                    jam = sdf.format(aduanPemantauan.getTarikhPemantauan()).substring(11, 13);
                    minit = sdf.format(aduanPemantauan.getTarikhPemantauan()).substring(14, 16);
                    saat = "00";
                    ampm = sdf.format(aduanPemantauan.getTarikhPemantauan()).substring(20, 22);
                }
            }
        }
    }

    public Resolution simpanPopup() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();

        aduanPemantauan = new AduanPemantauan();
        aduanPemantauan.setPermohonan(permohonan);
        aduanPemantauan.setCawangan(cawangan);
        tarikhPemantauan = getContext().getRequest().getParameter("tarikhPemantauan");
        tarikhPemantauan = tarikhPemantauan + " " + jam + ":" + minit + ":00 " + ampm;
        aduanPemantauan.setTarikhPemantauan(sdf.parse(tarikhPemantauan));
        catatan = getContext().getRequest().getParameter("catatan");
        aduanPemantauan.setCatatan(catatan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = aduanPemantauan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            aduanPemantauan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanAduanPemantauan(aduanPemantauan);
        tarikhPemantauan = sdf.format(aduanPemantauan.getTarikhPemantauan()).substring(0, 10);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        logger.debug("MaklumatLokasiActionBean::simpan::" + aduanPemantauan.getIdPemantauan());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatPemantauanActionBean.class, "locate");

    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPemantauan = getContext().getRequest().getParameter("idPemantauan");
        aduanPemantauan = enforcementService.findAduanPemantauanByIdPemantauan(Long.parseLong(idPemantauan));

        if (aduanPemantauan != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            aduanPemantauan.setInfoAudit(ia);
            aduanPemantauan.setCawangan(cawangan);
            //  aduanPemantauan.setDipantauOleh(peng);
            enforcementService.deleteAduanPemantauan(aduanPemantauan);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatPemantauanActionBean.class, "locate");
    }

    public Resolution simpanSingle() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        idPemantauan = getContext().getRequest().getParameter("idPemantauan");
        logger.info(idPemantauan);

        aduanPemantauan = enforcementService.findAduanPemantauanByIdPemantauan(Long.parseLong(idPemantauan));

        cawangan = permohonan.getCawangan();

        if (aduanPemantauan == null) {
            aduanPemantauan = new AduanPemantauan();
        }
        aduanPemantauan.setPermohonan(permohonan);
        aduanPemantauan.setCawangan(cawangan);
        catatan = getContext().getRequest().getParameter("aduanPemantauan.catatan");
        aduanPemantauan.setCatatan(catatan);
        tarikhPemantauan = getContext().getRequest().getParameter("aduanPemantauan.tarikhPemantauan");
        tarikhPemantauan = tarikhPemantauan + " " + jam + ":" + minit + ":00 " + ampm;
        aduanPemantauan.setTarikhPemantauan(sdf.parse(tarikhPemantauan));

        //  aduanPemantauan.setDipantauOleh(peng);
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            aduanPemantauan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        enforcementService.simpanAduanPemantauan(aduanPemantauan);
        tarikhPemantauan = sdf.format(aduanPemantauan.getTarikhPemantauan()).substring(0, 10);
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        logger.debug("MaklumatLokasiActionBean::simpan::" + aduanPemantauan.getIdPemantauan());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new JSP("penguatkuasaan/maklumat_pemantauan.jsp").addParameter("tab", "true");
        return new RedirectResolution(MaklumatPemantauanActionBean.class, "locate");
    }

    public AduanPemantauan getAduanPemantauan() {
        return aduanPemantauan;
    }

    public void setAduanPemantauan(AduanPemantauan aduanPemantauan) {
        this.aduanPemantauan = aduanPemantauan;
    }

    public EnforcementService getEnforcementService() {
        return enforcementService;
    }

    public void setEnforcementService(EnforcementService enforcementService) {
        this.enforcementService = enforcementService;
    }

    public String getIdPemantauan() {
        return idPemantauan;
    }

    public void setIdPemantauan(String idPemantauan) {
        this.idPemantauan = idPemantauan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<AduanPemantauan> getSenaraiAduanPemantauan() {
        return senaraiAduanPemantauan;
    }

    public void setSenaraiAduanPemantauan(List<AduanPemantauan> senaraiAduanPemantauan) {
        this.senaraiAduanPemantauan = senaraiAduanPemantauan;
    }

    public String getTarikhPemantauan() {
        return tarikhPemantauan;
    }

    public void setTarikhPemantauan(String tarikhPemantauan) {
        this.tarikhPemantauan = tarikhPemantauan;
    }

    public String getIdpengguna() {
        return idpengguna;
    }

    public void setIdpengguna(String idpengguna) {
        this.idpengguna = idpengguna;
    }

    public Boolean getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Boolean errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMesej() {
        return mesej;
    }

    public void setMesej(String mesej) {
        this.mesej = mesej;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Notis getNotisPenguatkuasaan() {
        return notisPenguatkuasaan;
    }

    public void setNotisPenguatkuasaan(Notis notisPenguatkuasaan) {
        this.notisPenguatkuasaan = notisPenguatkuasaan;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
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

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(MaklumatPemantauanActionBean.class, "locate");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(MaklumatPemantauanActionBean.class, "locate");
    }

}
