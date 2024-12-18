/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodJawatanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodCawangan;
import etanah.model.KodNegeri;
import etanah.model.KodPeranan;
import etanah.model.KodUnitJabatan;
import etanah.model.Pengguna;
import etanah.model.PermohonanPengguna;
import etanah.service.uam.UamService;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.adf.share.security.model.dc.idm.exception.UserNotFoundException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author amir.muhaimin
 */
@HttpCache(allow = false)
@UrlBinding("/daftar_pengguna")
public class UserRegistrationBean extends AbleActionBean {

    @Inject
    private etanah.Configuration conf;
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserRegistrationBean.class);
    private PermohonanPengguna pguna;
    private Pengguna pengguna;
    private String pKataLaluan;
    private String jawatan;
    private String jabatan;
    private List<PermohonanPengguna> listPenyelia = new ArrayList<PermohonanPengguna>();
    @Inject
    private UamService service;
    @Inject
    private KodJawatanDAO jawatanDAO;
    @Inject
    private KodJabatanDAO jabatanDAO;
    @Inject
    private KodNegeriDAO negeriDAO;
    @Inject
    private PenggunaDAO penggunaDAO;
    @Inject
    private KodCawanganDAO kodCawanganDAO;
    @Inject
    private KodPerananDAO kodPerananDAO;
    private boolean flag = false;
    private List<Pengguna> listPengguna = new ArrayList<Pengguna>();
    private String idPenggunaUser = new String();
    private boolean kaunter = false;
    private boolean melaka = false;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new JSP("uam/user_registration.jsp");
    }

    public Resolution searchByCawangan() {
        String cawangan = getContext().getRequest().getParameter("pguna.kodCawangan.kod");
        String jabatan = getContext().getRequest().getParameter("pguna.kodJabatan.kod");
        logger.debug("cawangan : " + cawangan);
//        ---set balik using serchByCaw---

        String ptg = "";
        if (cawangan.equals("00")) {
            ptg = "Y";
        } else {
            ptg = "N";
        }
        List<KodUnitJabatan> listKj = service.getKodJab(jabatan, ptg);
       if(!listKj.isEmpty()){
        List<KodUnitJabatan> listK = service.getKodJabUnit(String.valueOf(listKj.get(0).getKodUnit().getId()), ptg);
        List<String> kj = new ArrayList();
        for (KodUnitJabatan kodUnit : listK) {
            kj.add(kodUnit.getJabatan().getKod());
        }
//        listPengguna = service.findByCawangan(cawangan, jabatan);
        listPengguna = service.getPenggunaUnit(kj, cawangan);
       }
        if (StringUtils.isNotBlank(cawangan)) {
            KodCawangan kodCaw = kodCawanganDAO.findById(cawangan);
            if (kodCaw.getAlamat() != null) {
                pguna.setAlamat1(kodCaw.getAlamat().getAlamat1());
                pguna.setAlamat2(kodCaw.getAlamat().getAlamat2());
                pguna.setAlamat3(kodCaw.getAlamat().getAlamat3());
                pguna.setAlamat4(kodCaw.getAlamat().getAlamat4());
                pguna.setPoskod(kodCaw.getAlamat().getPoskod());
                pguna.setNegeri(kodCaw.getAlamat().getNegeri());
                pguna.setNoTelefon(kodCaw.getNoTelefon());
            } else {
                pguna.setAlamat1(null);
                pguna.setAlamat2(null);
                pguna.setAlamat3(null);
                pguna.setAlamat4(null);
                pguna.setPoskod(null);
                pguna.setNegeri(null);
            }
        }
        logger.debug("listPengguna : " + listPengguna.size());
        if (jabatan.equals("16") || jabatan.equals("17")) {
            kaunter = true;
        } else {
            kaunter = false;
        }
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");
    }

    public Resolution searchByJawatan() {
        String cawangan = getContext().getRequest().getParameter("pguna.kodCawangan.kod");
        String jabatan = getContext().getRequest().getParameter("pguna.kodJabatan.kod");
        String jawatan = getContext().getRequest().getParameter("pguna.jawatan");
        logger.debug("cawangan : " + cawangan);
//        ---set balik using serchByCaw---

        listPengguna = service.findByPenyeliaPguna(jawatan, cawangan);
        if (StringUtils.isNotBlank(cawangan)) {
            KodCawangan kodCaw = kodCawanganDAO.findById(cawangan);
            if (kodCaw.getAlamat() != null) {
                pguna.setAlamat1(kodCaw.getAlamat().getAlamat1());
                pguna.setAlamat2(kodCaw.getAlamat().getAlamat2());
                pguna.setAlamat3(kodCaw.getAlamat().getAlamat3());
                pguna.setAlamat4(kodCaw.getAlamat().getAlamat4());
                pguna.setPoskod(kodCaw.getAlamat().getPoskod());
                pguna.setNegeri(kodCaw.getAlamat().getNegeri());
                pguna.setNoTelefon(kodCaw.getNoTelefon());
            } else {
                pguna.setAlamat1(null);
                pguna.setAlamat2(null);
                pguna.setAlamat3(null);
                pguna.setAlamat4(null);
                pguna.setPoskod(null);
                pguna.setNegeri(null);
            }
        }
        logger.debug("listPengguna : " + listPengguna.size());
        if (jabatan.equals("16") || jabatan.equals("17")) {
            kaunter = true;
        } else {
            kaunter = false;
        }
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");
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
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.TRUE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");

            } else if (StringUtils.isBlank(pguna.getNama())) {
                addSimpleError("Sila masukkan Nama.");
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.TRUE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");

            } else if (StringUtils.isBlank(pguna.getNoPengenalan())) {
                addSimpleError("Sila masukkan Kad Pengenalan.");
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.TRUE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");

            } else if (StringUtils.isBlank(pguna.getEmail())) {
                addSimpleError("Sila masukkan Email.");
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.TRUE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");

            } else if (StringUtils.isBlank(pguna.getAlamat1())) {
                addSimpleError("Sila masukkan Alamat.");
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.TRUE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");

//            } else if (StringUtils.isBlank(pguna.getAlamat2())) {
//                addSimpleError("Sila masukkan Alamat.");
//
//            } else if (StringUtils.isBlank(pguna.getAlamat3())) {
//                addSimpleError("Sila masukkan Alamat.");

            } else if (StringUtils.isBlank(pguna.getPoskod())) {
                addSimpleError("Sila masukkan Poskod.");
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.TRUE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");

//            } else if (StringUtils.isBlank(pguna.get())) {
//                addSimpleError("Sila masukkan Poskod.");

            } else if (StringUtils.isBlank(pguna.getNegeri().getKod())) {
                addSimpleError("Sila masukkan Negeri.");
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.TRUE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");

            } else if (StringUtils.isBlank(pguna.getNoTelefon())) {
                addSimpleError("Sila masukkan Telefon.");
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.TRUE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");
//
//            } else if (StringUtils.isBlank(pguna.getNoTelefonBimbit())) {
//                addSimpleError("Sila masukkan Telefon bimbit.");

            } else if (StringUtils.isBlank(pguna.getPenyelia().getIdPengguna())) {
                addSimpleError("Sila masukkan Penyelia.");
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.TRUE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
                return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");


            } else {
                try {

                    if (!service.newUser2(pguna, pguna.getPassword())) {
                        addSimpleError("Id Pengguna telah wujud. Gunakan Id Pengguna lain.");
                    } else {
                        addSimpleMessage("Pendaftaran berjaya. Sila semak email anda dari semasa ke semasa untuk mendapatkan user id dan katalaluan.");
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
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        logger.info("Creating User Success....");
        return new ForwardResolution("/WEB-INF/jsp/uam/user_registration_1.jsp");
    }

    public Resolution searchUser() throws Exception {
        logger.info("Searching User....");
        if (pguna != null) {
            if (StringUtils.isBlank(pguna.getIdPengguna())) {
                addSimpleError("Sila masukkan Id Pengguna.");

            } else {
                if (!StringUtils.isBlank(pguna.getIdPengguna())) {
                    String idPengguna = new String();
                    if (pguna != null && StringUtils.isNotBlank(pguna.getIdPengguna())) {
                        idPengguna = pguna.getIdPengguna();
                        idPenggunaUser = new String();
                        idPenggunaUser = idPengguna;
                        boolean checkExist = false;
                        String idUser = new String(pguna.getIdPengguna());
                        pguna = service.searchingUserReg2(idUser);
                        checkExist = pguna != null ? true : false;
                        if (!checkExist) {
                            pengguna = service.searchingUserReg(idUser);
                            checkExist = pengguna != null ? true : false;
                        }
                        if (checkExist) {
                            addSimpleError("Id Pengguna wujud.");
                            return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");
                        } else {
                            if (pguna == null) {
                                pguna = new PermohonanPengguna();
                            }
                            addSimpleMessage("Id Pengguna tidak wujud. Sila daftar menggunakan Id Pengguna ini.");
                        }
                    }
                }
            }
        }
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");

    }

    public Resolution kembali() {

        logger.info("Back to Login Page....");
        return new ForwardResolution("/WEB-INF/jsp/login/login.jsp");
    }

    public Resolution kembali1() throws NamingException {


//        pguna = new PermohonanPengguna();
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        if (!StringUtils.isBlank(idPenggunaUser)) {
            pguna = service.searchingUserReg2(idPenggunaUser);
            if (pguna != null) {
                pguna = new PermohonanPengguna();
            } else {
                pguna = new PermohonanPengguna();
                pguna.setIdPengguna(idPenggunaUser);
            }
        } else {
            pguna = new PermohonanPengguna();
        }
        logger.info("Back to Login Page....");
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/user_registration.jsp");
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

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public List<PermohonanPengguna> getListPenyelia() {
        return listPenyelia;
    }

    public void setListPenyelia(List<PermohonanPengguna> listPenyelia) {
        this.listPenyelia = listPenyelia;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Pengguna> getListPengguna() {
        return listPengguna;
    }

    public void setListPengguna(List<Pengguna> listPengguna) {
        this.listPengguna = listPengguna;
    }

    public String getIdPenggunaUser() {
        return idPenggunaUser;
    }

    public void setIdPenggunaUser(String idPenggunaUser) {
        this.idPenggunaUser = idPenggunaUser;
    }

    public boolean isKaunter() {
        return kaunter;
    }

    public void setKaunter(boolean kaunter) {
        this.kaunter = kaunter;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }
}
