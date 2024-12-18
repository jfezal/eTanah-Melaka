/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Murali
 */

@UrlBinding("/utility/strata/cetak_semula")
public class UtilityCetakSemulaActionBean extends AbleActionBean {

    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    private DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    StrataPtService strataPtService;
    private Permohonan permohonan;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandungan4KDHDK = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandungan4KDHKK = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandunganBSDHDK = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandunganBSDHKK = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandunganPSK = new ArrayList<KandunganFolder>();
    private static final Logger LOG = Logger.getLogger(UtilityCetakSemulaActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/strata/utility/utilityCetakSemula.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public Resolution search() {

        if (permohonan == null) {
            addSimpleError("Sila Masukkan ID Permohonan.");
        } else {
            if (permohonan != null) {
                permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                if (permohonan != null) {
                    if (!permohonan.getFolderDokumen().getSenaraiKandungan().isEmpty()) {
                        for (KandunganFolder kFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {
                            if (kFolder.getDokumen().getKodDokumen() != null) {
                                /*if (kFolder.getDokumen().getKodDokumen().getKod().equals("16H")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("16I")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("16O")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("16P")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("16Q")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("KM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("KMLM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("LEL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("LELLM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("MEMO")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("MEMOL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("NL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("NLLM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("PJ")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("PJLM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("RM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SBI")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SBIL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SLHL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SSL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SSLM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("STP")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SWT")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SRW")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("TBP")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("TG")) { */
                                if (!(kFolder.getDokumen().getKodDokumen().getKod().equals("4KDHK") || 
                                        kFolder.getDokumen().getKodDokumen().getKod().equals("4KDHD") ||
                                        kFolder.getDokumen().getKodDokumen().getKod().equals("BSKDK") ||
                                        kFolder.getDokumen().getKodDokumen().getKod().equals("BSKKK") ||
                                        kFolder.getDokumen().getKodDokumen().getKod().equals("PSK"))){
                                    senaraiKandungan.add(kFolder);
                                }
                                if (kFolder.getDokumen().getKodDokumen().getKod().equals("4KDHK")){
                                    senaraiKandungan4KDHKK.add(kFolder);
                                }
                                if (kFolder.getDokumen().getKodDokumen().getKod().equals("4KDHD")){
                                    senaraiKandungan4KDHDK.add(kFolder);
                                }
                                if (kFolder.getDokumen().getKodDokumen().getKod().equals("BSKDK")){
                                    senaraiKandunganBSDHDK.add(kFolder);
                                }
                                if (kFolder.getDokumen().getKodDokumen().getKod().equals("BSKKK")){
                                    senaraiKandunganBSDHKK.add(kFolder);
                                }
                                if (kFolder.getDokumen().getKodDokumen().getKod().equals("PSK")){
                                    senaraiKandunganPSK.add(kFolder);
                                }
                                //}
                            }
                        }
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/utility/utilityCetakSemula.jsp");
    }
    
    public Resolution cetakSemula() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String sbbCetakanSemula = getContext().getRequest().getParameter("sbb_cetakan_semula");
        String idDokumen = getContext().getRequest().getParameter("id_dokumen");
        Dokumen d = dokumenDAO.findById(Long.parseLong(idDokumen));

        if (d == null) {
            return new StreamingResolution("text/plain", "2");
        }
        
        List<DokumenCapaian> dc2 = strataPtService.findDokumenCapaian(d.getIdDokumen());
        int count = 0;
        for (DokumenCapaian dc1 : dc2) {
            LOG.info(dc1.getAlasan());
            String alasan = "CETAKAN SEMULA [ " + sbbCetakanSemula + " ]";
            if (dc1.getAlasan().equals(alasan)) {
                count++;
                return new StreamingResolution("text/plain", "0");
            }
        }
        if (count == 0) {
            DokumenCapaian dc = new DokumenCapaian();

            if (StringUtils.isNotBlank(sbbCetakanSemula)) {
                dc.setAlasan("CETAKAN SEMULA [ " + sbbCetakanSemula + " ]");
            } else {
                dc.setAlasan("Cetakan Dokumen");
            }

            dc.setDokumen(d);
            dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("CD"));
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            dc.setInfoAudit(ia);

            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            dokumenCapaianDAO.save(dc);

            tx.commit();
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        senaraiKandungan = null;
        return new ForwardResolution("/WEB-INF/jsp/strata/utility/utilityCetakSemula.jsp");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<KandunganFolder> getSenaraiKandungan4KDHDK() {
        return senaraiKandungan4KDHDK;
    }

    public void setSenaraiKandungan4KDHDK(List<KandunganFolder> senaraiKandungan4KDHDK) {
        this.senaraiKandungan4KDHDK = senaraiKandungan4KDHDK;
    }

    public List<KandunganFolder> getSenaraiKandungan4KDHKK() {
        return senaraiKandungan4KDHKK;
    }

    public void setSenaraiKandungan4KDHKK(List<KandunganFolder> senaraiKandungan4KDHKK) {
        this.senaraiKandungan4KDHKK = senaraiKandungan4KDHKK;
    }

    public List<KandunganFolder> getSenaraiKandunganBSDHDK() {
        return senaraiKandunganBSDHDK;
    }

    public void setSenaraiKandunganBSDHDK(List<KandunganFolder> senaraiKandunganBSDHDK) {
        this.senaraiKandunganBSDHDK = senaraiKandunganBSDHDK;
    }

    public List<KandunganFolder> getSenaraiKandunganBSDHKK() {
        return senaraiKandunganBSDHKK;
    }

    public void setSenaraiKandunganBSDHKK(List<KandunganFolder> senaraiKandunganBSDHKK) {
        this.senaraiKandunganBSDHKK = senaraiKandunganBSDHKK;
    }

    public List<KandunganFolder> getSenaraiKandunganPSK() {
        return senaraiKandunganPSK;
    }

    public void setSenaraiKandunganPSK(List<KandunganFolder> senaraiKandunganPSK) {
        this.senaraiKandunganPSK = senaraiKandunganPSK;
    }
    
}
