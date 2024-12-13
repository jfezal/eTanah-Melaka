/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.AduanPortalDAO;
import etanah.dao.KodAduanPortalDAO;
import etanah.dao.KodStatusPortalDAO;
import etanah.model.AduanPortal;
import etanah.model.InfoAudit;
import etanah.model.KodAduanPortal;
import etanah.model.KodStatusPortal;
import etanah.model.Pengguna;
import etanah.service.uam.AduanPortalService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author nurashidah
 */
@UrlBinding("/uam/aduan")
public class TindakanAduanActionBean extends AbleActionBean {
    @Inject
    private etanah.Configuration conf;
    private static Logger logger = Logger.getLogger(TindakanAduanActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private AduanPortalService aduanPortalService;
    @Inject
    AduanPortalDAO aduanPortalDAO;
    @Inject
    KodAduanPortalDAO kodAduanPortalDAO;
    @Inject
    KodStatusPortalDAO kodStatusPortalDAO;
    private AduanPortal aduanPortal;
    private KodAduanPortal kodAduanPortal;
    private KodStatusPortal kodStatusPortal;
    private String nama;
    private String noPengenalan;
    private String noTelefon;
    private String email;
    private String jenis;
    private String tarikhAduan;
    private String penerangan;
    private String idAduan;
    private String status;
    private String statusCur;
    private String maklumBalas;
    private List<AduanPortal> senaraiAduanPortal;
    private List<KodStatusPortal> senaraiStatus;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {

        return new ForwardResolution("/WEB-INF/jsp/uam/tindakanAduan.jsp");

    }

    public Resolution viewAduan() {
        String id = getContext().getRequest().getParameter("idAduan");
        aduanPortal = aduanPortalDAO.findById(Long.parseLong(id));
        if (aduanPortal != null) {
            idAduan = Long.toString(aduanPortal.getIdAduan());
            nama = aduanPortal.getNama();
            noPengenalan = aduanPortal.getNoPengenalan();
            noTelefon = aduanPortal.getNoTelefon();
            email = aduanPortal.getEmail();
            jenis = Integer.toString(aduanPortal.getKodAduanPortal().getKod());
            tarikhAduan = sdf.format(aduanPortal.getTarikhAduan());
            penerangan = aduanPortal.getPenerangan();

            if (aduanPortal.getKodStatusPortal().getKod() != null) {
                statusCur = Integer.toString(aduanPortal.getKodStatusPortal().getKod());
            }

            senaraiStatus = aduanPortalService.getSenaraiStatus();
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/tindakanAduan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws NamingException {
        logger.info("------------rehydrate--------------");
        senaraiStatus = aduanPortalService.getSenaraiStatus();
        System.out.println("size status : " + senaraiStatus.size());
    }

    public Resolution simpan() throws Exception {
        logger.info("------------Simpan Tindakan--------");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit ia = new InfoAudit();
        String id = getContext().getRequest().getParameter("idAduan");
        System.out.println("idAduan :" + id);
        aduanPortal = aduanPortalDAO.findById(Long.parseLong(id));
        System.out.println("aduan  :" + aduanPortal);

        String kodStatus = getContext().getRequest().getParameter("status");
        System.out.println("status :" + status);
        kodStatusPortal = kodStatusPortalDAO.findById(Integer.parseInt(kodStatus));
        System.out.println("aduan  :" + kodStatus);

        ia = aduanPortal.getInfoAudit();
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());

        aduanPortal.setInfoAudit(ia);

        if (status != null && maklumBalas != null) {

            aduanPortal.setKodStatusPortal(kodStatusPortal);
            aduanPortal.setOfficer(peng.getNama());
            aduanPortal.setMaklumBalas(maklumBalas);
            aduanPortal.setTarikhSelesai(new Date());
            MailService mail = new MailService();
            String []to = {aduanPortal.getEmail()};
            String subject = "Maklum Balas Portal e-Tanah";
            String content = "Pihak kami telah mengambil tindakan di atas cadangan atau aduan anda. Sila layari http://etanah.ns.gov.my/etportal/ns.portal?_nfpb=true&_st=&_pageLabel=ns_semak_aduan#wlp_ns_semak_aduan"
                    + " untuk menyemak dan mengetahui maklumbalas dari pihak kami. Terima Kasih.";
            mail.sendEmail(to, subject, content, conf.getKodNegeri());
        } else if (status != null && maklumBalas == null) {
            aduanPortal.setKodStatusPortal(kodStatusPortal);

        }

        aduanPortalService.update(aduanPortal);

        addSimpleMessage("Maklumat berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/uam/tindakanAduan.jsp");
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTarikhAduan() {
        return tarikhAduan;
    }

    public void setTarikhAduan(String tarikhAduan) {
        this.tarikhAduan = tarikhAduan;
    }

    public String getPenerangan() {
        return penerangan;
    }

    public void setPenerangan(String penerangan) {
        this.penerangan = penerangan;
    }

    public List<AduanPortal> getSenaraiAduanPortal() {
        return senaraiAduanPortal;
    }

    public void setSenaraiAduanPortal(List<AduanPortal> senaraiAduanPortal) {
        this.senaraiAduanPortal = senaraiAduanPortal;
    }

    public String getIdAduan() {
        return idAduan;
    }

    public void setIdAduan(String idAduan) {
        this.idAduan = idAduan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCur() {
        return statusCur;
    }

    public void setStatusCur(String statusCur) {
        this.statusCur = statusCur;
    }

    public List<KodStatusPortal> getSenaraiStatus() {
        return senaraiStatus;
    }

    public void setSenaraiStatus(List<KodStatusPortal> senaraiStatus) {
        this.senaraiStatus = senaraiStatus;
    }

    public AduanPortal getAduanPortal() {
        return aduanPortal;
    }

    public void setAduanPortal(AduanPortal aduanPortal) {
        this.aduanPortal = aduanPortal;
    }

    public String getMaklumBalas() {
        return maklumBalas;
    }

    public void setMaklumBalas(String maklumBalas) {
        this.maklumBalas = maklumBalas;
    }
    
    
}
