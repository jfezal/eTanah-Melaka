/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BorangPerHakmilikDAO;
import etanah.dao.BorangPerPBDAO;
import etanah.dao.HantarBorangPBDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.BorangPerPermohonan;
import etanah.model.ambil.HantarBorangPB;
import etanah.model.ambil.HantarBorangPermohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.TampalBorangHakmilik;
import etanah.model.ambil.TampalBorangPermohonan;
import etanah.ref.pengambilan.sek4.RefPeringkat;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.service.acq.service.BorangACQService;
import etanah.service.acq.service.BorangEFService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pembangunan.tugasan.TugasanActionBean;
import etanah.view.stripes.pengambilan.share.form.BorangEForm;
import etanah.view.stripes.pengambilan.share.form.BorangFForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author zipzap
 */
@UrlBinding("/pengambilan/penyampaian_g_h")
public class PenyampaianBorangGHActionBean extends AbleActionBean {

    @Inject
    BorangACQService borangACQService;
    @Inject
    BorangEFService borangEFService;
    @Inject
    BorangPerHakmilikDAO borangPerHakmilikDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    BorangPerPBDAO borangPerPBDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;
    @Inject
    HantarBorangPBDAO hantarBorangPBDAO;
    List<BorangEForm> listBorangE;
    List<BorangFForm> listBorangF;
    List<TampalBorangHakmilik> listTampalBorangE;
    Dokumen dokumenBE;
    BorangPerHakmilik borangPerHakmilik;
    Permohonan permohonan;
    String urusan;
    String urlKembali;
    String idPermohonan;
    Long idBorangPerHm;

    String tarikhTampalA;
    String tempatTampalA;
    String penghantarNotisA;
    String catatanA;

    String tarikhHantarB;
    String tempatHantarB;
    String penghantarB;
    String catatanB;
    HantarBorangPB hantarBorangPB;
    String idpbH;

    @DefaultHandler
    public Resolution showForm() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
        urusan = p.getKodUrusan().getNama();
        urlKembali = "showForm";
        listBorangE = borangEFService.findHakmilikAktifNotTDKG(idPermohonan);

        return new JSP("/pengambilan/APT/penyampaian_borang_gh.jsp");
    }

    public Resolution senaraiG() {
        listBorangF = new ArrayList<BorangFForm>();
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String as = (String) getContext().getRequest().getParameter("idBorangPerHm");
        idBorangPerHm = Long.valueOf(as);
        borangPerHakmilik = borangPerHakmilikDAO.findById(idBorangPerHm);
        dokumenBE = borangPerHakmilik.getDokumen();
        listBorangF = borangEFService.listBorangF(idPermohonan, idHakmilik);
        listTampalBorangE = borangEFService.findTampalBorangHakmilik(borangPerHakmilik);
        return new JSP("/pengambilan/APT/penyampaian_borang_h.jsp").addParameter("popup", true);
    }

    public Resolution tambahBorangG() throws ParseException {

        return new JSP("/pengambilan/APT/borangGpopup.jsp").addParameter("popup", true);

    }

    public Resolution tambahBorangH() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        idpbH = (String) getContext().getRequest().getParameter("idBorangPerPBA");

        BorangPerPB borangPerPB = borangPerPBDAO.findById(Long.parseLong(idpbH));

        if (borangPerPB != null) {
            hantarBorangPB = borangEFService.findHantarPerPB1(borangPerPB);
            if (hantarBorangPB != null) {
                tarikhHantarB = String.valueOf(hantarBorangPB.getTrh_hantar());
                penghantarB = hantarBorangPB.getPenghantar_notis();
                catatanB = hantarBorangPB.getCatatan();
            }

        }
        return new JSP("/pengambilan/APT/borangHpopup2.jsp").addParameter("popup", true);
    }

    public Date formatSDF(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(date);
    }

    public Resolution hantar() {
        //idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        RefPeringkat ref = new RefPeringkat();
        PermohonanPengambilan ambil = pengambilanAPTService.findPermohonanPengambilanByIdMH(idPermohonan);
        etanah.ref.pengambilan.sek8a.RefPeringkat ref8a = new etanah.ref.pengambilan.sek8a.RefPeringkat();

        sek8aIntegrationFlowService.completeTask(ref8a.SEDIA_BAUCER_DEPOSIT, permohonan, pengguna);

        return new RedirectResolution(TugasanActionBean.class);
    }

    public Resolution simpanBorangG() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idBorangPerHakmilikA = (String) getContext().getRequest().getParameter("idBorangPerHakmilikA");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        BorangPerHakmilik borang = borangPerHakmilikDAO.findById(Long.parseLong(idBorangPerHakmilikA));
        InfoAudit infoAudit = setInfoAudit(pengguna);
        TampalBorangHakmilik bm = new TampalBorangHakmilik();
        bm.setBorangPerHakmilik(borang);
        bm.setTarikhTampal(formatSDF(tarikhTampalA));
        bm.setTempatTampal(tempatTampalA);
        bm.setCatatan(catatanA);
        bm.setInfoAudit(infoAudit);
        bm.setPenghantarNotis(penghantarNotisA);
        borangACQService.saveTampalBorangHakmilik(bm);
        addSimpleMessage("Maklumat Berjaya disimpan");

        return new JSP("/pengambilan/APT/borangGpopup.jsp").addParameter("popup", true);

    }

    public Resolution simpanBorangH() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idBorangPerPBA = (String) getContext().getRequest().getParameter("idBorangPerPBA");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        BorangPerPB borang = borangPerPBDAO.findById(Long.parseLong(idBorangPerPBA));
        InfoAudit infoAudit = setInfoAudit(pengguna);
        HantarBorangPB bm = null;
        bm = borangACQService.findHantarBorangPB(borang);
        if (bm != null) {

        } else {
            bm = new HantarBorangPB();
        }

        bm.setBorangPerPB(borang);
        bm.setTrh_hantar(formatSDF(tarikhHantarB));//        bm.set
//        bm.set(tempatHantarB);
        bm.setInfoAudit(infoAudit);
        bm.setCatatan(catatanB);
        bm.setPenghantar_notis(penghantarB);
        borangACQService.saveHantarBorangPB(bm);
        addSimpleMessage("Maklumat Berjaya disimpan");

        return new JSP("/pengambilan/APT/borangHpopup.jsp").addParameter("popup", true);

    }

    public Resolution tambahBorangB() {
        return new JSP("/pengambilan/APT/borangBpopup.jsp").addParameter("popup", true);

    }

    private InfoAudit setInfoAudit(Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public List<BorangEForm> getListBorangE() {
        return listBorangE;
    }

    public void setListBorangE(List<BorangEForm> listBorangE) {
        this.listBorangE = listBorangE;
    }

    public List<BorangFForm> getListBorangF() {
        return listBorangF;
    }

    public void setListBorangF(List<BorangFForm> listBorangF) {
        this.listBorangF = listBorangF;
    }

    public Dokumen getDokumenBE() {
        return dokumenBE;
    }

    public void setDokumenBE(Dokumen dokumenBE) {
        this.dokumenBE = dokumenBE;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public String getUrlKembali() {
        return urlKembali;
    }

    public void setUrlKembali(String urlKembali) {
        this.urlKembali = urlKembali;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdBorangPerHm() {
        return idBorangPerHm;
    }

    public void setIdBorangPerHm(Long idBorangPerHm) {
        this.idBorangPerHm = idBorangPerHm;
    }

    public BorangPerHakmilik getBorangPerHakmilik() {
        return borangPerHakmilik;
    }

    public void setBorangPerHakmilik(BorangPerHakmilik borangPerHakmilik) {
        this.borangPerHakmilik = borangPerHakmilik;
    }

    public String getTarikhTampalA() {
        return tarikhTampalA;
    }

    public void setTarikhTampalA(String tarikhTampalA) {
        this.tarikhTampalA = tarikhTampalA;
    }

    public String getTempatTampalA() {
        return tempatTampalA;
    }

    public void setTempatTampalA(String tempatTampalA) {
        this.tempatTampalA = tempatTampalA;
    }

    public String getPenghantarNotisA() {
        return penghantarNotisA;
    }

    public void setPenghantarNotisA(String penghantarNotisA) {
        this.penghantarNotisA = penghantarNotisA;
    }

    public String getCatatanA() {
        return catatanA;
    }

    public void setCatatanA(String catatanA) {
        this.catatanA = catatanA;
    }

    public String getTarikhHantarB() {
        return tarikhHantarB;
    }

    public void setTarikhHantarB(String tarikhHantarB) {
        this.tarikhHantarB = tarikhHantarB;
    }

    public String getTempatHantarB() {
        return tempatHantarB;
    }

    public void setTempatHantarB(String tempatHantarB) {
        this.tempatHantarB = tempatHantarB;
    }

    public String getPenghantarB() {
        return penghantarB;
    }

    public void setPenghantarB(String penghantarB) {
        this.penghantarB = penghantarB;
    }

    public String getCatatanB() {
        return catatanB;
    }

    public void setCatatanB(String catatanB) {
        this.catatanB = catatanB;
    }

    public List<TampalBorangHakmilik> getListTampalBorangE() {
        return listTampalBorangE;
    }

    public void setListTampalBorangE(List<TampalBorangHakmilik> listTampalBorangE) {
        this.listTampalBorangE = listTampalBorangE;
    }

    public HantarBorangPB getHantarBorangPB() {
        return hantarBorangPB;
    }

    public void setHantarBorangPB(HantarBorangPB hantarBorangPB) {
        this.hantarBorangPB = hantarBorangPB;
    }

    public String getIdpbH() {
        return idpbH;
    }

    public void setIdpbH(String idpbH) {
        this.idpbH = idpbH;
    }

}
