/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import java.util.List;
import etanah.dao.*;
import etanah.model.*;
import java.util.Date;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.DokumenService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Zairul
 */
@UrlBinding("/daftar/maklumat_jilid_folio")
public class MaklumatJilidFolio extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private Permohonan permohonan;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    private KandunganFolder folderDokumen;
//    private List<KandunganFolder> listKandunganFolder;
    private Dokumen dokumen;
    private String idPermohonan;
    public String noJilid;
    boolean flag = false;
//    public String noFolder;

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("/daftar/kemasukan_jilid_folio.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            folderDokumen = kandunganFolderService.getDokumenByIdFolderAndCatatan(permohonan.getFolderDokumen().getFolderId(), "Folio/Jilid Gadaian");
            if (folderDokumen != null) {

                dokumen = folderDokumen.getDokumen();
                noJilid = dokumen.getNoRujukan();
                flag = true;
//                     noFolder =  dokumen.getNoRujukan();

            }

        }
    }

    @HandlesEvent("save")
    public Resolution saveOrUpdate() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
        InfoAudit info = new InfoAudit();
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
            if (dokumen == null || dokumen.getInfoAudit() == null) {
                dokumen = new Dokumen();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                dokumen.setBaru('Y');
            } else {
                info = dokumen.getInfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new Date());
            }

            if (noJilid != null) {
                dokumen.setNoRujukan(" " + noJilid);
            }

            dokumen.setInfoAudit(info);
            dokumen.setNoVersi("1.0");
            KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
            dokumen.setKlasifikasi(klasifikasi_AM);
            dokumen.setTajuk("Surat Kuasa Wakil Daftar");
            KodDokumen kodDoc = kodDokumenDAO.findById("SWD");
            dokumen.setKodDokumen(kodDoc);
            dokumen.setSaiz(0);
            dokumen.setPermohonan(permohonan);
            dokumen = dokumenService.saveOrUpdate(dokumen);
            KandunganFolder kf = kandunganFolderService.findByDokumen(dokumen, fd);
            if (kf == null) {
                kf = new KandunganFolder();
            } else {
                info = kf.getInfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new Date());
            }
            kf.setCatatan("Folio/Jilid Gadaian");
            kf.setInfoAudit(info);
            kf.setFolder(fd);
            kf.setDokumen(dokumen);

            dokumenService.saveKandunganWithDokumen(kf);

            tx.commit();
            addSimpleMessage("Kemasukan Data Berjaya");
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
            addSimpleError("Kemasukan Data Gagal.");
        }

        return new JSP("/daftar/kemasukan_jilid_folio.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KandunganFolder getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(KandunganFolder folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

//    public List<KandunganFolder> getListKandunganFolder() {
//        return listKandunganFolder;
//    }
//
//    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
//        this.listKandunganFolder = listKandunganFolder;
//    }
    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
