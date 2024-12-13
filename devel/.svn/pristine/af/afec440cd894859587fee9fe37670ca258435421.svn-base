/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Permohonan;
import etanah.model.DokumenCapaian;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodStatusDokumenCapai;
import etanah.model.Pengguna;
import etanah.service.uam.UamService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author zahidaziz
 */
@HttpCache(allow = false)
@UrlBinding("/uam/auditCapaianDokumen")
public class AuditCapaiDokumenActionBean extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AuditCapaiDokumenActionBean.class);
    private String idCapai;
    private String idPermohonan;
    private Permohonan pmohon;
    private Dokumen dok;
    private InfoAudit ia;
    private Pengguna pguna;
    private KodStatusDokumenCapai aktiviti;
    private String pengguna;
    private String kodDokumen;
    private boolean flag = false;
    private Date tarikhMsDari;
    private Date tarikhMsHingga;
    private Calendar cd;
    private Date currDate;
    private int paparan = 20;
    private List<DokumenCapaian> auditDok;
    private List<DokumenCapaian> listCapai;
    @Inject
    private UamService service;
    @Inject
    PermohonanDAO permohonanDAO;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("uam/auditCapaianDokumen.jsp");
    }

    public Resolution viewAuditCapaiDok() throws Exception {
        paparan = (Integer) this.getPaparan();

        if (idPermohonan != null) {
            pmohon = permohonanDAO.findById(idPermohonan);
            if (pmohon != null) {
                List<KandunganFolder> kf = new ArrayList<KandunganFolder>();
//            getIdFolder
                kf = service.findFolderDokByIdFolder(pmohon.getFolderDokumen().getFolderId());
                logger.info("kf : " + kf.size());
                if (kf.size() > 0) {
                    List<Long> listDokumen = new ArrayList<Long>();
                    auditDok = new ArrayList<DokumenCapaian>();
                    for (KandunganFolder kff : kf) {
                        listDokumen.add(kff.getDokumen().getIdDokumen());
                    }
                    auditDok = service.findAuditDok3(getContext().getRequest().getParameterMap(), listDokumen);
                }
            }
        } else {
            addSimpleError("Sila Masukkan Id Permohonan.");
            return new ForwardResolution("/WEB-INF/jsp/uam/auditCapaianDokumen.jsp");
        }
        logger.info("Existed Pengguna: " + this.getPengguna());
        if (auditDok != null && auditDok.size() > 0) {
            logger.info("Bilangan row rekod audit dokumen :" + auditDok.size());
            setFlag(true);
        } else {
            addSimpleError("Maaf, data tidak wujud.");
        }

        return new ForwardResolution("/WEB-INF/jsp/uam/auditCapaianDokumen.jsp");
    }

    public List<DokumenCapaian> getAuditDok() {
        return auditDok;
    }

    public void setAuditDok(List<DokumenCapaian> auditDok) {
        this.auditDok = auditDok;
    }

    public List<DokumenCapaian> getListCapai() {
        return listCapai;
    }

    public void setListCapai(List<DokumenCapaian> listCapai) {
        this.listCapai = listCapai;
    }

    public String getIdCapai() {
        return idCapai;
    }

    public void setIdCapai(String idCapai) {
        this.idCapai = idCapai;
    }

    public int getPaparan() {
        return paparan;
    }

    public void setPaparan(int paparan) {
        this.paparan = paparan;
    }

    public Dokumen getDok() {
        return dok;
    }

    public void setDok(Dokumen dok) {
        this.dok = dok;
    }

    public InfoAudit getIa() {
        return ia;
    }

    public void setIa(InfoAudit ia) {
        this.ia = ia;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Date getTarikhMsDari() {
        return tarikhMsDari;
    }

    public void setTarikhMsDari(Date tarikhMsDari) {
        this.tarikhMsDari = tarikhMsDari;
    }

    public Date getTarikhMsHingga() {
        return tarikhMsHingga;
    }

    public void setTarikhMsHingga(Date tarikhMsHingga) {
        this.tarikhMsHingga = tarikhMsHingga;
    }

    public KodStatusDokumenCapai getAktiviti() {
        return aktiviti;
    }

    public void setAktiviti(KodStatusDokumenCapai aktiviti) {
        this.aktiviti = aktiviti;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public UamService getService() {
        return service;
    }

    public void setService(UamService service) {
        this.service = service;
    }

    public String getPengguna() {
        return pengguna;
    }

    public void setPengguna(String pengguna) {
        this.pengguna = pengguna;
    }

    public Calendar getCd() {
        return cd;
    }

    public void setCd(Calendar cd) {
        this.cd = cd;
    }

    public Date getCurrDate() {
        return currDate;
    }

    public void setCurrDate(Date currDate) {
        this.currDate = currDate;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPmohon() {
        return pmohon;
    }

    public void setPmohon(Permohonan pmohon) {
        this.pmohon = pmohon;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }
}
