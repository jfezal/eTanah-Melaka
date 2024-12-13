/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanDokumenIringanDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanDokumenIringan;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/dokumen_iringan")
public class DokumenIringanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanDokumenIringanDAO permohonanDokumenIringanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private PermohonanDokumenIringan permohonanDokumenIringan;
    private String idPermohonan;
    private List<KodDokumen> listDokIringan;
    private List<PermohonanDokumenIringan> listMohonDokIringan;
    private KodDokumen kodDokumen;
    private static final Logger LOG = Logger.getLogger(DokumenIringanActionBean.class);
    private long idMohonDokIringan;
    private List<KodDokumen> kodPilih;
    private List<KandunganFolder> listKandunganFolder;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/penguatkuasaan/dokumen_iringan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate dokumen iringan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        listDokIringan = enforceService.findListDokumenIringan();
        LOG.info("size dropdown list : " + listDokIringan.size());

        listMohonDokIringan = enforceService.findListByIdMohon(idPermohonan);
        LOG.info("size mohon dok iringan : " + listMohonDokIringan.size());

    }

    public Resolution simpan() {
        LOG.info("simpan dokumen iringan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        boolean check = false;
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());

            listKandunganFolder = enforceService.getListDokumen(permohonan.getFolderDokumen().getFolderId());

            if (listKandunganFolder.size() != 0) {
                Dokumen dokumen = new Dokumen();
                for (KandunganFolder kf : listKandunganFolder) {
                    dokumen = kf.getDokumen();
                }
                if (dokumen.getKodDokumen() != null) {
                    for (int i = 0; i < listKandunganFolder.size(); i++) {
                        if (listKandunganFolder.get(i).getDokumen().getKodDokumen().getKod().equalsIgnoreCase(kodDokumen.getKod())) {

                            check = true;
                            KodDokumen kd = kodDokumenDAO.findById(kodDokumen.getKod());

                            permohonanDokumenIringan = new PermohonanDokumenIringan();
                            permohonanDokumenIringan.setIdPermohonan(permohonan);
                            permohonanDokumenIringan.setKodDokumen(kd);
                            permohonanDokumenIringan.setCawangan(pengguna.getKodCawangan());
                            permohonanDokumenIringan.setInfoAudit(ia);
                            permohonanDokumenIringanDAO.saveOrUpdate(permohonanDokumenIringan);
                            tx.commit();
                            addSimpleMessage("Maklumat telah berjaya disimpan.");
                        }
                    }
                    if (check == false) {
                        addSimpleError("Dokumen iringan tidak wujud. Sila lampirkan/muat turun dahulu dokumen tersebut di tab dokumen");
                        return new JSP("penguatkuasaan/dokumen_iringan.jsp").addParameter("tab", "true");
                    }
                }
            }

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(DokumenIringanActionBean.class, "locate");
    }

    public Resolution delete() {
        LOG.info("hapus dokumen iringan");
        idMohonDokIringan = Long.parseLong(getContext().getRequest().getParameter("idMohonDokIringan"));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            PermohonanDokumenIringan p = permohonanDokumenIringanDAO.findById(idMohonDokIringan);
            permohonanDokumenIringanDAO.delete(p);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(DokumenIringanActionBean.class, "locate");
    }

    public Resolution findDokIringan() {
        LOG.info("cari dokumen iringan");
        String result = "";
//        String wujud = "";
        String kodDok = getContext().getRequest().getParameter("id");
        listKandunganFolder = enforceService.getListDokumen(permohonan.getFolderDokumen().getFolderId());
        System.out.println("size list : " + listKandunganFolder.size() + " kodDok : " + kodDok);

        if (listKandunganFolder.size() != 0) {
            Dokumen dokumen = new Dokumen();
            for (KandunganFolder kf : listKandunganFolder) {
                dokumen = kf.getDokumen();
                System.out.println("dokumen ==== " + dokumen.getKodDokumen().getKod());
                if (dokumen.getKodDokumen() != null) {
                    if (dokumen.getKodDokumen().getKod().equalsIgnoreCase(kodDok)) {
                        System.out.println("dokumen itu wujud");
                        result = "Exist";

                    }
//                    else {
//                        System.out.println("dokumen itu tidak wujud");
//                        result = "NotExist";
//                    }
                }
            }
        }

        System.out.println("result value : " + result);
        return new StreamingResolution("text/plain", result);
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public PermohonanDokumenIringan getPermohonanDokumenIringan() {
        return permohonanDokumenIringan;
    }

    public void setPermohonanDokumenIringan(PermohonanDokumenIringan permohonanDokumenIringan) {
        this.permohonanDokumenIringan = permohonanDokumenIringan;
    }

    public List<KodDokumen> getListDokIringan() {
        return listDokIringan;
    }

    public void setListDokIringan(List<KodDokumen> listDokIringan) {
        this.listDokIringan = listDokIringan;
    }

    public List<PermohonanDokumenIringan> getListMohonDokIringan() {
        return listMohonDokIringan;
    }

    public void setListMohonDokIringan(List<PermohonanDokumenIringan> listMohonDokIringan) {
        this.listMohonDokIringan = listMohonDokIringan;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public long getIdMohonDokIringan() {
        return idMohonDokIringan;
    }

    public void setIdMohonDokIringan(long idMohonDokIringan) {
        this.idMohonDokIringan = idMohonDokIringan;
    }

    public List<KodDokumen> getKodPilih() {
        return kodPilih;
    }

    public void setKodPilih(List<KodDokumen> kodPilih) {
        this.kodPilih = kodPilih;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }
}
