/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.common.DokumenService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zipzap
 */
@UrlBinding("/pengambilan/permohonan_ukur")
public class SediaPermohonanUkurActionBean extends BasicTabActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenService dokumenService;
    List<PermohonanUkurForm> listPermohonanUkurForm;
    FileBean fileToBeUpload;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        PermohonanPengambilan m = pengambilanAPTService.findPermohonanPengambilanByIdMH(mohon.getIdPermohonan());
        String[] kodDokumen = null;

        if (m.getKatPengambilan().equals("1")) {
            kodDokumen = new String[]{"8SPU", "PU", "K", "PPRE", "MMKT", "D"};
            listPermohonanUkurForm = pengambilanAPTService.setCheckList(kodDokumen, mohon);
        } else {
            kodDokumen = new String[]{"SMPU", "K", "PPRE", "MMKT", "D"};
            listPermohonanUkurForm = pengambilanAPTService.setCheckList(kodDokumen, mohon);
        }
        return new JSP("/pengambilan/APT/permohonan_ukur.jsp").addParameter("tab", "true");
    }

    public Resolution muatNaikForm() {

        String folderId = getContext().getRequest().getParameter("folderId");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String kodDokumen = getContext().getRequest().getParameter("kodDokumen");

        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        } else {
            return new ErrorResolution(404, "Tiada Id Permohonan.");
        }
        return new JSP("/pengambilan/APT/muat_naik_APT_PU.jsp").addParameter("popup", "true");
    }

    public Resolution processUpload() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new Date());
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String folderDokumenId = getContext().getRequest().getParameter("folderDokumenId");
        String kodDokumen = getContext().getRequest().getParameter("kodDokumen");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");

//        if (!kodDokumen.equals("")) {
        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();
                String contentType = fileToBeUpload.getContentType();
                Permohonan permohonan = permohonanDAO.findById(idPermohonan);
                if (permohonan != null) {
                    FolderDokumen folder = permohonan.getFolderDokumen();
                    Dokumen dokumen = null;
                    if (StringUtils.isNotBlank(dokumenId)) {
                        dokumen = dokumenDAO.findById(Long.valueOf(dokumenId));
                        if (dokumen != null) {
                        } else {
                            dokumen = new Dokumen();
                            dokumen.setKodDokumen(kodDokumenDAO.findById(kodDokumen));
                            dokumen.setInfoAudit(infoAudit);
                            dokumenService.save(dokumen);
                        }
                    } else {
                        dokumen = new Dokumen();
                        dokumen.setKodDokumen(kodDokumenDAO.findById(kodDokumen));
                        dokumen.setTajuk(kodDokumen);
                        dokumen.setInfoAudit(infoAudit);
                        dokumenService.save(dokumen);
                    }
                    KandunganFolder kf = null;
                    if (StringUtils.isNotBlank(folderDokumenId)) {
                        kf = kandunganFolderDAO.findById(Long.valueOf(folderDokumenId));
                        if (kf != null) {
                            kf.setDokumen(dokumen);
                            dokumenService.saveKandunganWithDokumen(kf);

                        } else {
                            kf = new KandunganFolder();
                            kf.setDokumen(dokumen);
                            kf.setInfoAudit(infoAudit);
                            dokumenService.saveKandunganWithDokumen(kf);
                        }
                    } else {
                        kf = new KandunganFolder();
                        kf.setDokumen(dokumen);
                        kf.setFolder(folder);
                        kf.setInfoAudit(infoAudit);
                        dokumenService.saveKandunganWithDokumen(kf);
                    }

                    DMSUtil dmsUtil = new DMSUtil();
                    FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                    fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                    String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                    updatePathDokumen(fizikalPath, dokumen.getIdDokumen(), contentType);
                    addSimpleMessage("Muat naik fail berjaya.");

                } else {
                    addSimpleError("Muat naik tidak berjaya.");
                    return showForm();
                }
            } catch (Exception ex) {
                addSimpleError("Muat naik tidak berjaya.");
                return muatNaikForm();
            }
        }
        return muatNaikForm();
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public List<PermohonanUkurForm> getListPermohonanUkurForm() {
        return listPermohonanUkurForm;
    }

    public void setListPermohonanUkurForm(List<PermohonanUkurForm> listPermohonanUkurForm) {
        this.listPermohonanUkurForm = listPermohonanUkurForm;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

}
