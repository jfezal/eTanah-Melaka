/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodStatusPenggunaDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodStatusPengguna;
import etanah.model.Pengguna;
import etanah.service.uam.UamService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.MessagingException;

/**
 *
 * @author amir.muhaimin
 */
@UrlBinding("/katalaluan")
public class LupaKataLaluanBean extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LupaKataLaluanBean.class);
    private Pengguna pguna;
    private String idPengguna;
    private String noKp;
    private String email;
    @Inject
    PenggunaDAO penggunaDao;
    private String passwordGenerate = "";
    private Properties props = new Properties();
    @Inject
    private etanah.Configuration conf;
    @Inject
    private UamService service;
    public static final int password_Length = 8;
    protected static java.util.Random random = new java.util.Random();
    protected static char[] goodChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
        'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_', '-', '@'};
    private boolean flag = false;
    @Inject
    KodStatusPenggunaDAO kodStatusPenggunaDAO;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        getContext().getRequest().setAttribute("send", Boolean.FALSE);
        return new JSP("uam/lupaKataLaluan.jsp");
    }

    public Resolution kembali1() {
        pguna = new Pengguna();
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        logger.info("Back to Login Page....");
        return new ForwardResolution("/WEB-INF/jsp/login/login.jsp");
    }

    public Resolution forgotPassword() throws AddressException, MessagingException, Exception {
        logger.info("Sending email..");
        if (pguna != null && StringUtils.isNotBlank(pguna.getIdPengguna()) && StringUtils.isNotBlank(pguna.getNoPengenalan())) {
            String kodNegeri = conf.getProperty("kodNegeri");
            String idUser = new String(pguna.getIdPengguna());
            String icUser = new String(pguna.getNoPengenalan());


            pguna = service.searchingUser(idUser, icUser);
            if (pguna != null) {

                logger.info("Id Pengguna ::" + pguna.getIdPengguna());
                logger.info("Ic ::" + pguna.getNoPengenalan());
                logger.info("Email ::" + pguna.getEmail());

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < password_Length; i++) {
                    sb.append(goodChar[random.nextInt(goodChar.length)]);
                }
                passwordGenerate = sb.toString();


                String subject = "Notifikasi kata laluan pengguna.";
                String text = "Tahniah. Permohonan menetapkan semula katalaluan anda untuk Sistem e-Tanah telah Berjaya.\n"
                        + "Sila gunakan log masuk kali pertama untuk login.\n"
                        + "Id Pengguna dan katalaluan anda adalah seperti berikut:\n"
                        + "\t ID Pengguna = " + pguna.getIdPengguna() + "\n"
                        + "\t Katalaluan = " + passwordGenerate;
//                        + "Ini adalah kata laluan anda :" + passwordGenerate + "\n Sila gunakan kata laluan ini untuk login masuk ke dalam sistem e-Tanah.<br>"
//                        + "Sila tukar katalaluan anda.<br> Terima kasih";

                String[] to = {pguna.getEmail()};
                MailService2 mail = new MailService2();
                if (mail.sendEmail(to, subject, text, kodNegeri)) {
                    Pengguna pengguna = penggunaDao.findById("admin");
                    service.changePassword(pguna, pengguna, passwordGenerate);
                    KodStatusPengguna ksp = kodStatusPenggunaDAO.findById("B");
                    pguna.setStatus(ksp);
                    pguna = service.savePengguna(pguna);
                }

                logger.info("Testing : " + pguna.getPassword());

            }
        } else {
            addSimpleError("Sila masukkan maklumat Pengguna.");
            return new ForwardResolution("/WEB-INF/jsp/login/login.jsp");
        }

        logger.info("Sending Email Success....");
        addSimpleMessage("Katalaluan baru telah dihantar. Sila semak email anda.");
//        addSimpleError("Katalaluan baru telah dihantar. Sila semak email anda.");
        getContext().getRequest().setAttribute("send", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/login/login.jsp");
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
