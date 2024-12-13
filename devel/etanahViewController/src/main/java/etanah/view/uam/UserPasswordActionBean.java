/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.service.uam.UamService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.util.regex.Pattern;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author Izam
 */
@HttpCache(allow = false)
@UrlBinding("/uam/user_password")
public class UserPasswordActionBean extends AbleActionBean {

    private final static Logger logger = Logger.getLogger(ChangePasswordActionBean.class);
    private Pengguna pguna;
    private String sahKataLaluan;
    private String idPengguna;
    @Inject
    private UamService service;
    private String[] besar = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] kecik = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private String[] nombor = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    public boolean checkPass(String str) {
        boolean s = false;
//        for (int i = 0; i < besar.length; i++) {
//            s = str.contains(besar[i]);
//            if (s) {
//                break;
//            }
//        }
//        if (s) {
//            for (int t = 0; t < kecik.length; t++) {
//                s = str.contains(kecik[t]);
//                if (s) {
//                    break;
//                }
//            }
//        }
//        if (s) {
//            for (int n = 0; n < nombor.length; n++) {
//                s = str.contains(nombor[n]);
//                if (s) {
//                    break;
//                }
//            }
//        }

        boolean upper = false;
        boolean lower = false;
        boolean number = false;
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c) && Character.isUpperCase(c)) {
                upper = true;
            }
            if (Character.isLetter(c) && Character.isLowerCase(c)) {
                lower = true;
            }
            if (Character.isDigit(c)) {
                number = true;
            }

            if (upper && lower && number) {
                s = true;
                break;
            }

        }

        return s;
    }
    private static final String regex = "^[A-Z0-9a-z]"; //alpha-numeric uppercase
    public static boolean isUpperCase(String str) {
        return Pattern.compile(regex).matcher(str).find();
    }

    @DefaultHandler
    public Resolution showForm() {
        setIdPengguna(getContext().getRequest().getParameter("idPengguna"));

        return new JSP("uam/userPassword.jsp");
    }

    public Resolution change() throws Exception {
        Pengguna pengguna = ((etanahActionBeanContext) getContext()).getUser(); //current user

        idPengguna = pguna.getIdPengguna();
        Pengguna user = service.searchingUser(pguna.getIdPengguna());

        if (user != null) {
            if (StringUtils.isBlank(pguna.getPassword())) {
                addSimpleError("Sila masukkan Kata Laluan.");

            } else if (pguna.getPassword().length() < 8) {
                addSimpleError("Had minimum Kata Laluan adalah 8. Sila betulkan Kata Laluan anda.");

            } else if (!pguna.getPassword().equals(sahKataLaluan)) {
                addSimpleError("Kata laluan anda tidak sama. Sila Betulkan Kata Laluan anda.");

            } else if (!checkPass(sahKataLaluan)) {
                addSimpleError("Sila gunakan kombinasi huruf besar, huruf kecil dan nombor.");


            } else if (pguna.getPassword().equals(sahKataLaluan)) {
                service.changePassword(pguna, pengguna, sahKataLaluan);
                addSimpleMessage("Kata Laluan baru telah berjaya disimpan.");
            }
        } else {
            addSimpleError("Id Pengguna tidak wujud.");
        }

        return new ForwardResolution("/WEB-INF/jsp/uam/userPassword.jsp");
    }

    public RedirectResolution kembali() throws Exception {
        logger.info("Back..");
        return new RedirectResolution(UpdateUserActionBean.class, "showForm");
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getSahKataLaluan() {
        return sahKataLaluan;
    }

    public void setSahKataLaluan(String sahKataLaluan) {
        this.sahKataLaluan = sahKataLaluan;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }
}
