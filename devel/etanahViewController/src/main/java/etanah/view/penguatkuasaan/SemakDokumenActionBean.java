/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanDokumenIringanDAO;
import etanah.dao.PermohonanSemakDokumenDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanDokumenIringan;
import etanah.model.PermohonanSemakDokumen;
import etanah.service.EnforceService;
import etanah.service.common.DokumenService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/semak_dokumen")
public class SemakDokumenActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanSemakDokumenDAO permohonanSemakDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private PermohonanDokumenIringan permohonanDokumenIringan;
    private String idPermohonan;
    private KodDokumen kodDokumen;
    private static final Logger LOG = Logger.getLogger(SemakDokumenActionBean.class);
    private long idMohonDokIringan;
    private List<KodDokumen> kodPilih;
    private List<KandunganFolder> listKandunganFolder;
    private List<PermohonanSemakDokumen> listPermohonanSemakDokumen;
    private String recordCount;
    private PermohonanSemakDokumen permohonanSemakDokumen;
    private String jenisDokumen;
    private String kesalahan;
    private Dokumen d;
    private String idPermohonanSemakDokumen;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("/penguatkuasaan/semak_dokumen.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("/penguatkuasaan/semak_dokumen.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("----rehydrate----");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        listPermohonanSemakDokumen = enforceService.findSenaraiPermohonanSemakDokumen(idPermohonan);
        LOG.info("size listPermohonanSemakDokumen : " + listPermohonanSemakDokumen.size());

        listKandunganFolder = enforceService.getListDokumenDisemak(permohonan.getFolderDokumen().getFolderId());
        LOG.info("listKandunganFolder : " + listKandunganFolder.size());

        recordCount = String.valueOf(listPermohonanSemakDokumen.size());
        LOG.info("recordCount : " + recordCount);


    }

    public Resolution simpan() {
        LOG.info("----simpan----");

        if (StringUtils.isNotBlank(recordCount)) {
            int row = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));

            System.out.println("row : " + row);
            for (int i = 0; i < row; i++) {
                jenisDokumen = getContext().getRequest().getParameter("kodDokumen" + i);
                kesalahan = getContext().getRequest().getParameter("catatan" + i);

                if (listPermohonanSemakDokumen.size() != 0 && i < listPermohonanSemakDokumen.size()) {
                    Long id = listPermohonanSemakDokumen.get(i).getIdPermohonanSemakDokumen();
                    if (id != null) {
                        permohonanSemakDokumen = permohonanSemakDokumenDAO.findById(id);
                        if (!permohonanSemakDokumen.getKodDokumen().getKod().equalsIgnoreCase(jenisDokumen)) {
                            if (listKandunganFolder.size() != 0) {
                                for (int j = 0; j < listKandunganFolder.size(); j++) {
                                    if (listKandunganFolder.get(j).getDokumen().getKodDokumen().getKod().equalsIgnoreCase(permohonanSemakDokumen.getKodDokumen().getKod())) {
                                        System.out.println("update table dokumen");
                                        d = dokumenDAO.findById(listKandunganFolder.get(j).getDokumen().getIdDokumen());
                                        if (d != null) {
                                            System.out.println("id dokumen : " + d.getIdDokumen());
                                            InfoAudit ia = d.getInfoAudit();
                                            ia.setDiKemaskiniOleh(pengguna);
                                            ia.setTarikhKemaskini(new java.util.Date());
                                            
                                            d.setInfoAudit(ia);
                                            d.setKodDokumen(kodDokumenDAO.findById(jenisDokumen));
                                            dokumenService.saveOrUpdate(d);

                                        }

                                    }

                                }
                            }
                        }
                    }
                } else {
                    permohonanSemakDokumen = new PermohonanSemakDokumen();
                }


                jenisDokumen = getContext().getRequest().getParameter("kodDokumen" + i);
                kesalahan = getContext().getRequest().getParameter("catatan" + i);

                permohonanSemakDokumen.setCatatan(kesalahan);
                permohonanSemakDokumen.setKodDokumen(kodDokumenDAO.findById(jenisDokumen));
                permohonanSemakDokumen.setCawangan(pengguna.getKodCawangan());
                permohonanSemakDokumen.setPermohonan(permohonan);
                enforceService.simpanPermohonanSemakDokumen(permohonanSemakDokumen);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/semak_dokumen.jsp").addParameter("tab", "true");
    }

    public Resolution delete() {
        LOG.info("--------------deleteOperasiPenguatkuasaan--------------");
        idPermohonanSemakDokumen = getContext().getRequest().getParameter("idPermohonanSemakDokumen");
        try {
            if (idPermohonanSemakDokumen != null) {
                permohonanSemakDokumen = permohonanSemakDokumenDAO.findById(Long.parseLong(idPermohonanSemakDokumen));

                String kodSemakDokumen = permohonanSemakDokumen.getKodDokumen().getKod();
                enforceService.deletePermohonanSemakDokumen(permohonanSemakDokumen);


                //Delete image from dokumen for permohonanSemakDokumen
                Session s = sessionProvider.get();
                Transaction tx = s.beginTransaction();
                tx.begin();
                try {
                    if (permohonan.getFolderDokumen() != null) {
                        listKandunganFolder = enforceService.getListDokumenDisemak(permohonan.getFolderDokumen().getFolderId());
                        if (listKandunganFolder.size() != 0) {
                            for (KandunganFolder kf : listKandunganFolder) {
                                Dokumen dokumen = kf.getDokumen();
                                if (dokumen.getKodDokumen() != null) {
                                    if (dokumen.getKodDokumen().getKod().equalsIgnoreCase(kodSemakDokumen)) {
                                        LOG.info("------------id dokumen----------------- : " + dokumen.getIdDokumen());
                                        dokumenDAO.delete(dokumen);
                                    }
                                }
                            }
                        }
                    }

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
            }

        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(SemakDokumenActionBean.class, "locate");
    }

    public Resolution deleteSelected() {
        LOG.info("deleteSelected");
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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(SemakDokumenActionBean.class, "locate");
    }

    public Resolution refreshpage() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(SemakDokumenActionBean.class, "locate");
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

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public PermohonanSemakDokumen getPermohonanSemakDokumen() {
        return permohonanSemakDokumen;
    }

    public void setPermohonanSemakDokumen(PermohonanSemakDokumen permohonanSemakDokumen) {
        this.permohonanSemakDokumen = permohonanSemakDokumen;
    }

    public String getJenisDokumen() {
        return jenisDokumen;
    }

    public void setJenisDokumen(String jenisDokumen) {
        this.jenisDokumen = jenisDokumen;
    }

    public String getKesalahan() {
        return kesalahan;
    }

    public void setKesalahan(String kesalahan) {
        this.kesalahan = kesalahan;
    }

    public List<PermohonanSemakDokumen> getListPermohonanSemakDokumen() {
        return listPermohonanSemakDokumen;
    }

    public void setListPermohonanSemakDokumen(List<PermohonanSemakDokumen> listPermohonanSemakDokumen) {
        this.listPermohonanSemakDokumen = listPermohonanSemakDokumen;
    }

    public Dokumen getD() {
        return d;
    }

    public void setD(Dokumen d) {
        this.d = d;
    }
}
