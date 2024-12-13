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
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author solahuddin
 */
@HttpCache(allow = false)
@UrlBinding("/uam/change_password")
public class ChangePasswordActionBean extends AbleActionBean {

    private final static Logger logger = Logger.getLogger(ChangePasswordActionBean.class);
    private Pengguna pguna;
    private String pKataLaluan;
    private String lKataLaluan;
    private String idPengguna;
    private String password;
    @Inject
    private UamService service;
    private String[] besar = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] kecik = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private String[] nombor = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    public boolean checkPass(String str) {
        boolean s = false;
        for (int i = 0; i < besar.length; i++) {
            s = str.contains(besar[i]);
            if (s) {
                break;
            }
        }
        if (s) {
            for (int t = 0; t < kecik.length; t++) {
                s = str.contains(kecik[t]);
                if (s) {
                    break;
                }
            }
        }
        if (s) {
            for (int n = 0; n < nombor.length; n++) {
                s = str.contains(nombor[n]);
                if (s) {
                    break;
                }
            }
        }

        return s;
    }

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = ((etanahActionBeanContext) getContext()).getUser();
        idPengguna = pengguna.getIdPengguna();

//        return new JSP("uam/change_password.jsp");
        return new ForwardResolution("/WEB-INF/jsp/uam/change_password.jsp");

    }

    public Resolution change() throws Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().
                getAttribute(etanahActionBeanContext.KEY_USER);
        idPengguna = pengguna.getIdPengguna();

        Pengguna user = service.searchingUser(idPengguna);

        if (user != null) {
            boolean check = service.checkPassword(idPengguna, lKataLaluan);
            if (checkPass(password)) {
                if (check) {
                    if (password.equals(lKataLaluan)) {
                        addSimpleError("Kata Laluan baru anda sama dengan katalaluan terdahulu. Sila masukkan katalaluan lain.");
                    } else {
                        if (password.length() >= 8) {
                            if (password.equals(pKataLaluan)) {
                                pguna = new Pengguna();
                                pguna.setIdPengguna(idPengguna);
                                service.changePassword(pguna, pengguna, password);
                            } else {
                                addSimpleError("Kata laluan anda tidak sama. Sila Betulkan Kata Laluan anda.");
                                return new ForwardResolution("/WEB-INF/jsp/uam/change_password.jsp");
                            }

                            addSimpleMessage("Kata Laluan baru telah berjaya disimpan.");
                        } else {
                            addSimpleError("Had minimum Kata Laluan adalah 8. Sila betulkan Kata Laluan anda.");
                        }

                    }


                } else {
                    addSimpleError("Kata laluan lama anda tidak sah. Sila Betulkan Kata Laluan lama anda.");
                }
            } else {
                addSimpleError("Katalaluan yang dimasukkan tidak mempunyai kombinasi huruf besar, huruf kecil, nombor dan minimum 8 aksara. Sila masukan katalaluan lain");
            }

        } else {
            addSimpleError("Id Pengguna tidak wujud. Sila masukkan Id Pengguna yang lain.");
        }

        return new ForwardResolution("/WEB-INF/jsp/uam/change_password.jsp");
    }

    public String getpKataLaluan() {
        return pKataLaluan;
    }

    public void setpKataLaluan(String pKataLaluan) {
        this.pKataLaluan = pKataLaluan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getlKataLaluan() {
        return lKataLaluan;
    }

    public void setlKataLaluan(String lKataLaluan) {
        this.lKataLaluan = lKataLaluan;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
