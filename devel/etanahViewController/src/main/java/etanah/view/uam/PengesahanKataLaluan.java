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
import etanah.model.Pengguna;
import etanah.service.uam.PasswordExp;
import etanah.service.uam.UamService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.MainActionBean;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

import etanah.model.PermohonanPengguna;

/**
 *
 * @author amir.muhaimin
 */
@HttpCache(allow = false)
@UrlBinding("/pengesahan_katalaluan")
public class PengesahanKataLaluan extends AbleActionBean {

    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodStatusPenggunaDAO kspDAO;
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PengesahanKataLaluan.class);
    private String lKataLaluan;
    private String password;
    private String pKataLaluan;
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
//        Pengguna pengguna = ((etanahActionBeanContext) getContext()).getUser();
//        pengguna = penggunaDAO.findById(pengguna.getIdPengguna());

//
//        PasswordExp pe = service.checkExpiry(pengguna.getIdPengguna());
//        if (pe.isExp()) {
//            return new JSP("uam/pengesahan_katalaluan.jsp");
//        } else {
//            if (pe.isReminder()) {
//                return new JSP("uam/pengesahan_katalaluan.jsp");
//            } else {
//                if (pengguna.getPerananUtama() == null || pengguna.getPerananUtama().getDefaultScreen() == null) {
//                   return new JSP("uam/pengesahan_katalaluan.jsp");
//                } else {
//                     return new JSP("uam/pengesahan_katalaluan.jsp");
//                }
//            }
//        }

        return new JSP("uam/pengesahan_katalaluan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna pengguna = ((etanahActionBeanContext) getContext()).getUser();



    }

    public Resolution kembali() throws Exception {
        logger.info("Back..");
        return new ForwardResolution("/WEB-INF/jsp/login/login.jsp");
    }

    public Resolution pengesahan() throws Exception {
        boolean success = false;
        Pengguna pengguna = ((etanahActionBeanContext) getContext()).getUser();

        pengguna = penggunaDAO.findById(pengguna.getIdPengguna());
        logger.info("Pengesahan Katalaluan...");
        if (StringUtils.isBlank(lKataLaluan)) {
            addSimpleError("Sila masukkan katalaluan lama.");
        } else {
            boolean check = service.checkPassword(pengguna.getIdPengguna(), lKataLaluan);
            if (check) {
                if (checkPass(password)) {
                    if (lKataLaluan.equals(password)) {
                        success = false;
                        addSimpleError("Katalaluan yang dimasukkan sama dengan katalaluan lama!");
                    } else {
                        if (password.equals(pKataLaluan)) {
                            if (!service.pengesahanKatalaluan(pengguna, password)) {
                                success = false;
                                addSimpleError("Sila semak katalaluan terdahulu anda.");
                            } else {

                                pengguna.setTarikhKemaskiniKatalaluan(new Date());
                                pengguna.setStatus(kspDAO.findById("A"));
                                service.savePengguna(pengguna);
//                            penggunaDAO.saveOrUpdate(pengguna);
                                success = true;
                                addSimpleMessage("Katalaluan telah disimpan.");
                            }
                        } else {
                            success = false;
                            addSimpleError("Katalaluan tidak sama!");
                        }
                    }
                } else {
                    addSimpleError("Katalaluan yang dimasukkan tidak mempunyai kombinasi huruf dan nombor");
                }

            } else {
                addSimpleError("Katalaluan terdahulu tidak sah!");
            }

        }
        if (!success) {
            return new ForwardResolution("/WEB-INF/jsp/uam/pengesahan_katalaluan.jsp");
        } else {
            return new RedirectResolution(MainActionBean.class);
        }
    }

    public String getlKataLaluan() {
        return lKataLaluan;
    }

    public void setlKataLaluan(String lKataLaluan) {
        this.lKataLaluan = lKataLaluan;
    }

    public String getpKataLaluan() {
        return pKataLaluan;
    }

    public void setpKataLaluan(String pKataLaluan) {
        this.pKataLaluan = pKataLaluan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
