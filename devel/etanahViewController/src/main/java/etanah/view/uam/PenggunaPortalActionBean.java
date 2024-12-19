/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PortalPenggunaDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodAgensiKutipan;
import etanah.model.KodKutipan;
import etanah.model.Pengguna;
import etanah.model.PortalPengguna;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author faidzal
 */
@UrlBinding("/uam/pengguna_portal")
public class PenggunaPortalActionBean extends AbleActionBean {

    List<PortalPengguna> listPenggunaPortal = new ArrayList<PortalPengguna>();
    @Inject
    PortalPenggunaDAO portalPenggunaDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    UamService service;
    List<KodAgensi> lisJTEK = new ArrayList<KodAgensi>();
    List<KodAgensiKutipan>listKutipanAgensi = new ArrayList<KodAgensiKutipan>();
    PortalPengguna portalPengguna;
    String idPengguna;
    boolean kemaskini = false;
    String kodJtek;
    String kutipan;
    public static final int password_Length = 6;
    private String passwordGenerate = "";
    protected static java.util.Random random = new java.util.Random();
    protected static char[] goodChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
        'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_', '-', '@'};

    @DefaultHandler
    public Resolution showForm() {
        listPenggunaPortal = portalPenggunaDAO.findAll();
        return new JSP("uam/senarai_pengguna_portal.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        //TODO
        listPenggunaPortal = portalPenggunaDAO.findAll();
    }

    public Resolution update() {
//        String idPengguna = getContext().getRequest().getParameter("idPportal");
        kemaskini = true;

        portalPengguna = portalPenggunaDAO.findById(idPengguna);
        return new JSP("uam/pengguna_portal.jsp");
    }

    public Resolution newUser() {
        kemaskini = false;
        portalPengguna = new PortalPengguna();
        return new JSP("uam/pengguna_portal.jsp");
    }

    public Resolution newPengguna() {

        PortalPengguna p = portalPenggunaDAO.findById(portalPengguna.getIdPguna());
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        if (p == null) {
            portalPengguna.setInfoAudit(ia);
            portalPengguna.setPasswd(generatePassword());

            portalPengguna.setKodSts("A");
            savePenguna(portalPengguna);
            String kodNegeri = conf.getKodNegeri();
            String[] email = {portalPengguna.getEmail()};
            MailService mailService = new MailService();
            mailService.sendEmail(email, "Pendaftaran Pengguna Portal Berjaya", "Tahniah. Pendaftaran pengguna baru telah berjaya. Sila gunakan maklumat dibawah untuk log masuk. idPengguna anda ialah " + portalPengguna.getIdPguna() + " dan katalaluan " + portalPengguna.getPasswd(), kodNegeri);
            listPenggunaPortal = portalPenggunaDAO.findAll();
            addSimpleMessage("Pengguna baru telah disimpan");
            return new RedirectResolution(PenggunaPortalActionBean.class);
        } else {
            addSimpleError("Id Pengguna telah wujud");
            return new JSP("uam/pengguna_portal.jsp");
        }


    }

    public Resolution updatePengguna() {
        PortalPengguna p = portalPenggunaDAO.findById(portalPengguna.getIdPguna());
        p.setNama(portalPengguna.getNama());
        p.setEmail(portalPengguna.getEmail());
        p.setNoKp(portalPengguna.getNoKp());
        p.setNoTel(portalPengguna.getNoTel());
        p.setKodSts(portalPengguna.getKodSts());
        savePenguna(p);
        listPenggunaPortal = portalPenggunaDAO.findAll();
        addSimpleMessage("Pengguna telah disimpan");
        return new JSP("uam/senarai_pengguna_portal.jsp");

    }

    public Resolution resetPsswrd() {
        portalPengguna = portalPenggunaDAO.findById(idPengguna);
        portalPengguna.setPasswd(generatePassword());
        portalPengguna.setKodSts("A");
        savePenguna(portalPengguna);
        String kodNegeri = conf.getKodNegeri();
        String[] email = {portalPengguna.getEmail()};
        MailService mailService = new MailService();
        mailService.sendEmail(email, "Reset Katalaluan", "Katalaluan anda telah direset. Sila gunakan maklumat dibawah untuk log masuk. idPengguna anda ialah " + portalPengguna.getIdPguna() + " dan katalaluan " + portalPengguna.getPasswd(), kodNegeri);

        addSimpleMessage("Pengguna  telah disimpan");
        return new JSP("uam/senarai_pengguna_portal.jsp");
    }

    @Transactional
    public void savePenguna(PortalPengguna portalPengguna) {
        portalPenggunaDAO.save(portalPengguna);
    }

    public String generatePassword() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password_Length; i++) {
            sb.append(goodChar[random.nextInt(goodChar.length)]);
        }
        passwordGenerate = sb.toString();
        return passwordGenerate;
    }

    public List<PortalPengguna> getListPenggunaPortal() {
        return listPenggunaPortal;
    }

    public void setListPenggunaPortal(List<PortalPengguna> listPenggunaPortal) {
        this.listPenggunaPortal = listPenggunaPortal;
    }

    public PortalPengguna getPortalPengguna() {
        return portalPengguna;
    }

    public void setPortalPengguna(PortalPengguna portalPengguna) {
        this.portalPengguna = portalPengguna;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public boolean isKemaskini() {
        return kemaskini;
    }

    public void setKemaskini(boolean kemaskini) {
        this.kemaskini = kemaskini;
    }

    public String getPasswordGenerate() {
        return passwordGenerate;
    }

    public void setPasswordGenerate(String passwordGenerate) {
        this.passwordGenerate = passwordGenerate;
    }

    public List<KodAgensi> getLisJTEK() {
        lisJTEK = service.findJTEKAgensi();
        return lisJTEK;
    }

    public void setLisJTEK(List<KodAgensi> lisJTEK) {
        this.lisJTEK = lisJTEK;
    }

    public List<KodAgensiKutipan> getListKutipanAgensi() {
        listKutipanAgensi= service.findKutipanAgensi();
        return listKutipanAgensi;
    }

    public void setListKutipanAgensi(List<KodAgensiKutipan> listKutipanAgensi) {
        this.listKutipanAgensi = listKutipanAgensi;
    }

    public String getKodJtek() {
        return kodJtek;
    }

    public void setKodJtek(String kodJtek) {
        this.kodJtek = kodJtek;
    }

    public String getKutipan() {
        return kutipan;
    }

    public void setKutipan(String kutipan) {
        this.kutipan = kutipan;
    }
    
    
}
