/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJawatanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodCawangan;
import etanah.model.KodJawatan;
import etanah.model.KodNegeri;
import etanah.model.KodPeranan;
import etanah.model.Pengguna;
import etanah.model.PermohonanPengguna;
import etanah.service.uam.UamService;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.adf.share.security.model.dc.idm.exception.UserNotFoundException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author amir.muhaimin
 */
@HttpCache(allow = false)
@UrlBinding("/daftar_pengguna_test")
public class UserRegistrationBean_Test extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserRegistrationBean_Test.class);
    private PermohonanPengguna pguna;
    private Pengguna pengguna;
    private String pKataLaluan;
    private String jawatan;
    private List<PermohonanPengguna> listPenyelia = new ArrayList<PermohonanPengguna>();
    @Inject
    private UamService service;
    @Inject
    private KodJawatanDAO jawatanDAO;
    @Inject
    private KodNegeriDAO negeriDAO;
    @Inject
    private PenggunaDAO penggunaDAO;
    @Inject
    private KodCawanganDAO kodCawanganDAO;
    @Inject
    private KodPerananDAO kodPerananDAO;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        return new JSP("uam/user_registration_test.jsp");
    }

//    @Before(stages = {LifecycleStage.BindingAndValidation})
//    public void rehydrate() {
//        pguna = (String) getContext().getRequest().getSession().getAttribute("idPengguna");
//        pguna = penggunaDAO.findById();
//        sebab = permohonan.getSebab();
//    }
    public Resolution newPengguna() throws NamingException, UserNotFoundException, Exception {

        logger.info("Creating User....");

        if (pguna != null) {
            if (StringUtils.isBlank(pguna.getIdPengguna())) {
                addSimpleError("Sila masukkan Id Pengguna.");

            } else if (StringUtils.isBlank(pguna.getNama())) {
                addSimpleError("Sila masukkan Nama.");

            } else if (StringUtils.isBlank(pguna.getNoPengenalan())) {
                addSimpleError("Sila masukkan Kad Pengenalan.");

            } else if (StringUtils.isBlank(pguna.getEmail())) {
                addSimpleError("Sila masukkan Email.");

            } else if (StringUtils.isBlank(pguna.getAlamat1())) {
                addSimpleError("Sila masukkan Alamat.");

            } else if (StringUtils.isBlank(pguna.getAlamat2())) {
                addSimpleError("Sila masukkan Alamat.");

            } else if (StringUtils.isBlank(pguna.getAlamat3())) {
                addSimpleError("Sila masukkan Alamat.");

            } else if (StringUtils.isBlank(pguna.getPoskod())) {
                addSimpleError("Sila masukkan Poskod.");

            } else if (StringUtils.isBlank(pguna.getNegeri().getKod())) {
                addSimpleError("Sila masukkan Negeri.");

            } else if (StringUtils.isBlank(pguna.getNoTelefon())) {
                addSimpleError("Sila masukkan Telefon.");

            } else if (StringUtils.isBlank(pguna.getNoTelefonBimbit())) {
                addSimpleError("Sila masukkan Telefon bimbit.");

            } else if (StringUtils.isBlank(pguna.getPenyelia().getIdPengguna())) {
                addSimpleError("Sila masukkan Penyelia.");


            } else {
                try {
                    if (!service.newUser2(pguna, pguna.getPassword())) {
                        addSimpleError("Id Pengguna telah wujud. Gunakan Id Pengguna lain.");
                    } else {
                        addSimpleMessage("Pengguna baru telah disimpan.");
                    }
                } catch (NameAlreadyBoundException e) {
                    addSimpleError("Id Pengguna telah wujud. Gunakan Id Pengguna lain.");
                } catch (UserNotFoundException e) {
                    throw e;
                }
            }
        } else {
            addSimpleError("Sila isi maklumat yang berkaitan dengan pengguna.");
        }

        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);

        logger.info("Creating User Success....");
        return new ForwardResolution("/WEB-INF/jsp/uam/user_registration_test_1.jsp");
    }

    public Resolution searchUser() throws Exception {
        logger.info("Searching User....");
        if (pguna != null) {
            if (StringUtils.isBlank(pguna.getIdPengguna())) {
                addSimpleError("Sila masukkan Id Pengguna.");

            } else if (StringUtils.isBlank(pguna.getNoPengenalan())) {
                addSimpleError("Sila masukkan Kad Pengenalan.");
            }
            if(!StringUtils.isBlank(pguna.getIdPengguna())&&!StringUtils.isBlank(pguna.getNoPengenalan())){
                String idPengguna = new String();
                String noPengenalan = new String();
                if (pguna != null && StringUtils.isNotBlank(pguna.getIdPengguna()) && StringUtils.isNotBlank(pguna.getNoPengenalan())) {
                    idPengguna = pguna.getIdPengguna();
                    noPengenalan = pguna.getNoPengenalan();
                    boolean checkExist = false;
                    String idUser = new String(pguna.getIdPengguna());
                    String icUser = new String(pguna.getNoPengenalan());
                    pguna = service.searchingUserReg2(idUser, icUser);
                    checkExist = pguna != null ? true : false;
                    if (!checkExist) {
                        pengguna = service.searchingUserReg(idUser, icUser);
                        checkExist = pengguna != null ? true : false;
                    }

                    if (checkExist) {
                        addSimpleError("Id Pengguna wujud.");
                        return new ForwardResolution("/WEB-INF/jsp/uam/user_registration_test.jsp");
                    } else {
                        pguna = new PermohonanPengguna();
                        pguna.setIdPengguna(idPengguna);
                        pguna.setNoPengenalan(noPengenalan);
                        addSimpleMessage("Id Pengguna tidak wujud. Sila daftar menggunakan Id Pengguna ini..");

                    }
                }
//                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");
//        else {
//             addSimpleMessage("Id Pengguna tidak wujud. Sila daftar menggunakan Id Pengguna ini..");
//
//            getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
//            getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
//            getContext().getRequest().setAttribute("id", Boolean.TRUE);
//            getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
//
//            return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");
//        }
//
//        if ((pengguna != null && (service.searchingUserReg(pengguna.getIdPengguna(), pengguna.getNoPengenalan())!=null))
//                && (pguna!=null && (service.searchingUserReg2(pguna.getIdPengguna(), pguna.getNoPengenalan())!=null))) {
//            if (pengguna.getIdPengguna() != null) {
//                getContext().getRequest().setAttribute("kemaskini", Boolean.TRUE);
//                getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
//                getContext().getRequest().setAttribute("id", Boolean.FALSE);
//                getContext().getRequest().setAttribute("kataLaluan", Boolean.FALSE);
//               addSimpleError("Id Pengguna wujud.");
//                logger.info("Searching User Success....");
//                getContext().getRequest().setAttribute("idP", pengguna.getIdPengguna());
//                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");
//
//            } else {
//                addSimpleMessage("Id Pengguna tidak wujud. Sila daftar menggunakan Id Pengguna ini.");
//
//            }
//        } else {
//            if ((pengguna != null)&& pguna!=null) {
//                String idPengguna = pengguna.getIdPengguna();
//                pengguna = new Pengguna();
//                pengguna.setIdPengguna(idPengguna);
//            }
//            addSimpleMessage("Id Pengguna tidak wujud. Sila daftar menggunakan Id Pengguna ini.");
//        }
//        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
//        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
//        getContext().getRequest().setAttribute("id", Boolean.TRUE);
//        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
//        return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");
            }
        }
    return new ForwardResolution("/WEB-INF/jsp/uam/user_registration_test.jsp");
    }
    public Resolution kembali() {

        logger.info("Back to Login Page....");
        return new ForwardResolution("/WEB-INF/jsp/login/login.jsp");
    }

    public Resolution kembali1() {

        pguna = new PermohonanPengguna();
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        logger.info("Back to Login Page....");
        return new ForwardResolution("/WEB-INF/jsp/uam/user_registration_test.jsp");
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PermohonanPengguna getPguna() {
        return pguna;
    }

    public void setPguna(PermohonanPengguna pguna) {
        this.pguna = pguna;
    }

    public String getpKataLaluan() {
        return pKataLaluan;
    }

    public void setpKataLaluan(String pKataLaluan) {
        this.pKataLaluan = pKataLaluan;
    }

    public String getNamaJawatan() {
        KodJawatan kj = jawatanDAO.findById(pguna.getJawatan());
        return kj.getNama();
    }

    public String getNamaPenyelia() {
        Pengguna p = penggunaDAO.findById(pguna.getPenyelia().getIdPengguna());
        return p.getNama();
    }

    public String getNamaNegeri() {
        String negeriNama = "";
        KodNegeri kn = new KodNegeri();
        if (pguna != null && pguna.getNegeri() != null) {
            kn = negeriDAO.findById(pguna.getNegeri().getKod());
            negeriNama = kn.getNama();
        }
        return negeriNama;
    }

    public String getNamaCaw() {
        String kcNama = "";
        KodCawangan kc = new KodCawangan();
        if (pguna != null && pguna.getKodCawangan() != null) {
            kc = kodCawanganDAO.findById(pguna.getKodCawangan().getKod());
            kcNama = kc.getName();
        }

        return kcNama;
    }

    public String getNamaPeranan() {
        String kpNama = "";
        KodPeranan kp = new KodPeranan();
        if (pguna != null && pguna.getPerananUtama() != null) {
            kp = kodPerananDAO.findById(pguna.getPerananUtama().getKod());
            kpNama = kp.getNama();
        }
        return kpNama;
    }

//    public String getNamaNegeri() {
//        KodNegeri kn = negeriDAO.findById(pguna.getNegeri().getKod());
//        return kn.getNama();
//    }
//    public String getNamaCaw() {
//        KodCawangan kc = kodCawanganDAO.findById(pguna.getKodCawangan().getKod());
//        return kc.getName();
//    }
//    public String getNamaPeranan() {
//        KodPeranan kp = kodPerananDAO.findById(pguna.getPerananUtama().getKod());
//        return kp.getNama();
//    }
    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public List<PermohonanPengguna> getListPenyelia() {
        return listPenyelia;
    }

    public void setListPenyelia(List<PermohonanPengguna> listPenyelia) {
        this.listPenyelia = listPenyelia;
    }
}
