/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJawatanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.PermohonanPenggunaDAO;
import etanah.model.KodCawangan;
import etanah.model.KodJawatan;
import etanah.model.KodNegeri;
import etanah.model.KodPeranan;
import etanah.model.Pengguna;
import etanah.model.KodJabatan;
import etanah.model.KodUnitJabatan;
import etanah.model.PermohonanPengguna;
import etanah.service.uam.UamService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author amir.muhaimin
 */
@HttpCache(allow = false)
@UrlBinding("/uam/kemaskini_profil")
public class KemasKiniProfilBean extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(KemasKiniProfilBean.class);
    private PermohonanPengguna pguna;
    private Pengguna pengguna;
    private String pKataLaluan;
    @Inject
    private UamService service;
    @Inject
    private ListUtil listUtil;
    @Inject
    private PermohonanPenggunaDAO mohonPenggunaDAO;
    @Inject
    private KodJawatanDAO kodJawatanDAO;
    @Inject
    private KodNegeriDAO negeriDAO;
    @Inject
    private KodCawanganDAO kodCawanganDAO;
    @Inject
    private PenggunaDAO penggunaDAO;
    @Inject
    private KodJabatanDAO kodJabDAO;
    @Inject
    private KodPerananDAO kodPerananDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private String nama;
    private String nokp;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String noTelefon;
    private String noTelefonBimbit;
    private String email;
    private String kodJawatan;
    private String peranan;
    private String cawangan;
    private String jabatan;
    private String penyelia;
    private boolean melaka = false;
    @Inject
    private etanah.Configuration conf;
    private boolean kaunter = false;
    private List<Pengguna> listPengguna = new ArrayList<Pengguna>();
    private List<Pengguna> senaraiPenyeliaAktif = new ArrayList<Pengguna>();

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        logger.info("THIS IS IDPENGGUNA->" + pengguna.getIdPengguna());
        pguna = mohonPenggunaDAO.findById(pengguna.getIdPengguna());
        nama = pguna.getNama();
        nokp = pguna.getNoPengenalan();
        alamat1 = pguna.getAlamat1();
        alamat2 = pguna.getAlamat2();
        alamat3 = pguna.getAlamat3();
        alamat4 = pguna.getAlamat4();
        noTelefon = pguna.getNoTelefon();
        noTelefonBimbit = pguna.getNoTelefonBimbit();
        email = pguna.getEmail();
        String ptg = "";
        if (pguna.getKodCawangan().getKod().equals("00")) {
            ptg = "Y";
        } else {
            ptg = "N";
        }
        List<KodUnitJabatan> listKj = service.getKodJab(pguna.getKodJabatan().getKod(), ptg);
        if (!listKj.isEmpty()) {
            List<KodUnitJabatan> listK = service.getKodJabUnit(String.valueOf(listKj.get(0).getKodUnit().getId()), ptg);
            List<String> kj = new ArrayList();
            for (KodUnitJabatan kodUnit : listK) {
                kj.add(kodUnit.getJabatan().getKod());
            }
//        listPengguna = service.findByCawangan(cawangan, jabatan);
            listPengguna = service.getPenggunaUnit(kj, pguna.getKodCawangan().getKod());
        }
        if (pguna.getNegeri() != null) {
            if (!StringUtils.isEmpty(pguna.getNegeri().getKod())) {
                negeri = pguna.getNegeri().getKod();
            }
        }
        poskod = pguna.getPoskod();
        if (pguna.getKodJawatan() != null) {
            if (!StringUtils.isEmpty(pguna.getKodJawatan().getKod())) {
                KodJawatan kodJ = new KodJawatan();
                kodJ = (KodJawatan) service.getnamaJawatan(pguna.getKodJawatan().getKod());
                kodJawatan = kodJ.getKod();
            }
        }
        if (pguna.getKodJabatan() != null) {
            if (!StringUtils.isEmpty(pguna.getKodJabatan().getKod())) {
                KodJabatan kj = new KodJabatan();
                kj = (KodJabatan) service.getnamaJabatan(pguna.getKodJabatan().getKod());
                jabatan = kj.getKod();
            }
        }
        if (pguna.getPerananUtama() != null) {
            if (!StringUtils.isEmpty(pguna.getPerananUtama().getKod())) {
                peranan = pguna.getPerananUtama().getKod();
            }
        }
        if (pguna.getKodCawangan() != null) {
            if (!StringUtils.isEmpty(pguna.getKodCawangan().getKod())) {
                KodCawangan kodC = new KodCawangan();
                kodC = (KodCawangan) service.getnamaCawangan(pguna.getKodCawangan().getKod());
                cawangan = kodC.getKod();
            }
        }
        if (pguna.getNegeri() != null) {
            if (!StringUtils.isEmpty(pguna.getNegeri().getKod())) {
                negeri = pguna.getNegeri().getKod();
            }
        }

        if (pguna.getPenyelia() != null && !StringUtils.isEmpty(pguna.getPenyelia().getIdPengguna())) {
            List<Pengguna> senaraiPenggunaAktif = new ArrayList<Pengguna>();
            senaraiPenggunaAktif = listUtil.getSenaraiPenggunaAktif();
            logger.info("THIS is SenaraiPenggunaAktif ->" + senaraiPenggunaAktif.size());
            senaraiPenyeliaAktif = new ArrayList<Pengguna>();
            Pengguna penggunaLogin = new Pengguna();
            penggunaLogin = (Pengguna) disLaporanTanahService.findObject(penggunaLogin, new String[]{pguna.getIdPengguna()}, 0);

            for (int i = 0; i < senaraiPenggunaAktif.size(); i++) {
                Pengguna penyelia2 = new Pengguna();
                penyelia2 = senaraiPenggunaAktif.get(i);
                if (penyelia2.getKodCawangan() != null && penyelia2.getKodJabatan() != null && !StringUtils.isEmpty(penyelia2.getPenyeliaFlag())) {
                    if (penyelia2.getKodCawangan().getKod().equals(penggunaLogin.getKodCawangan().getKod()) && penyelia2.getKodJabatan().getKod().equals(penggunaLogin.getKodJabatan().getKod()) && penyelia2.getPenyeliaFlag().equals("Y")) {
                        logger.info("THIS IS ITERATOR ->" + penyelia2.getIdPengguna());
                        senaraiPenyeliaAktif.add(penyelia2);
                    }
                }
            }
            if (!StringUtils.isEmpty(penyelia)) {
                penyelia = pguna.getPenyelia().getIdPengguna();
            } else {
                penyelia = pguna.getPenyelia().getIdPengguna();
            }
        }

    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
//        if (jabatan.equals("16") || jabatan.equals("17")) {
//            kaunter = true;
//        } else {
//            kaunter = false;
//        }
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        searchUser();

        return new JSP("uam/kemasKini_profile.jsp");
    }

    public Resolution searchByCawangan() {
        String cawangan = getContext().getRequest().getParameter("cawangan");
        String jabatan = getContext().getRequest().getParameter("jabatan");
        logger.debug("cawangan : " + cawangan);
        logger.debug("jabatan : " + jabatan);
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
        
//        listPengguna = service.findByCawangan(cawangan, jabatan);
        logger.debug("listPengguna : " + listPengguna.size());

        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
//        if (jabatan.equals("16") || jabatan.equals("17")) {
//            kaunter = true;
//        } else {
//            kaunter = false;
//        }
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/kemasKini_profile.jsp");
    }

    public void searchUser() {
        logger.info("Searching User....");

//       *********to get session********
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().
                getAttribute(etanahActionBeanContext.KEY_USER);

        if (pengguna != null) {
            pguna = mohonPenggunaDAO.findById(pengguna.getIdPengguna());
        }
//        if (jabatan.equals("16") || jabatan.equals("17")) {
//            kaunter = true;
//        } else {
//            kaunter = false;
//        }
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
    }

    public Resolution editPengguna() throws NamingException, UserNotFoundException, Exception {

        logger.info("Creating User....");

        if (pguna != null) {
            if (StringUtils.isBlank(pguna.getIdPengguna())) {
                addSimpleError("Sila masukkan Id Pengguna.");
            } else {
                try {
                    KodNegeri kodNegeri = negeriDAO.findById(negeri);
                    KodCawangan kodCaw = kodCawanganDAO.findById(cawangan);
                    KodPeranan kodPeranan = kodPerananDAO.findById(peranan);
                    String penyeliaString = getContext().getRequest().getParameter("penyelia");
                    logger.info("Penyelia ->" + penyeliaString);
//                     private String nama;
//    private String nokp;
//    private String alamat1;
//    private String alamat2;
//    private String alamat3;
//    private String alamat4;
//    private String poskod;
//    private String negeri;
//    private String noTelefon;
//    private String noTelefonBimbit;
//    private String email;
//    private String kodJawatan;
//    private String peranan;
//    private String cawangan;
//    private String jabatan;
//    private String penyelia;
                    Pengguna penyeliaP = new Pengguna();
                    if (!StringUtils.isEmpty(penyelia)) {
                        penyeliaP = penggunaDAO.findById(penyelia);
                        pguna.setPenyelia(penyeliaP);
                    }
                    KodJabatan kodJab = kodJabDAO.findById(jabatan);
                    pguna.setNama(nama);
                    logger.info("NAMA: " + nama);
                    pguna.setNoPengenalan(nokp);
                    pguna.setAlamat1(alamat1);
                    pguna.setAlamat2(alamat2);
                    pguna.setAlamat3(alamat3);
                    pguna.setAlamat4(alamat4);
                    pguna.setPoskod(poskod);
                    pguna.setNegeri(kodNegeri);
                    pguna.setNoTelefon(noTelefon);
                    pguna.setNoTelefonBimbit(noTelefonBimbit);
                    pguna.setEmail(email);
                    pguna.setKodCawangan(kodCaw);
                    pguna.setPerananUtama(kodPeranan);
                    pguna.setJawatan(kodJawatan);
                    pguna.setKodJabatan(kodJab);
                    //pguna.setPenyelia(penyeliaP);
                    jabatan = kodJab.getKod();
                    cawangan = kodCaw.getKod();
                    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().
                            getAttribute(etanahActionBeanContext.KEY_USER);
                    if (!service.newUser3(pguna, pguna.getPassword(), pengguna, penyelia)) {
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
        if (jabatan.equals("16") || jabatan.equals("17")) {
            kaunter = true;
        } else {
            kaunter = false;
        }
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }

        logger.info("Creating User Success....");
        return new ForwardResolution("/WEB-INF/jsp/uam/kemasKini_profile_1.jsp");
    }

    public Resolution searchInactiveUser() throws Exception {
        logger.info("Searching Inactive User....");

        String idParam = getContext().getRequest().getParameter("idPengguna");

        if (idParam != null) {
            pguna = service.searchingUser2(idParam);
        } else {
            pguna = service.searchingUser2(pguna.getIdPengguna());
        }

        if (pguna != null) {
            if (pguna.getIdPengguna() != null) {
                getContext().getRequest().setAttribute("kemaskini", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.FALSE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.FALSE);
                addSimpleMessage("Carian pengguna berjaya.");
                logger.info("Searching Inactive User Success...");
                getContext().getRequest().setAttribute("idP", pguna.getIdPengguna());
                return new ForwardResolution("/WEB-INF/jsp/uam/kemasKini_profile.jsp");

            } else {
                addSimpleError("Id Pengguna tidak wujud.");
            }
        } else {
            String idPengguna = pguna.getIdPengguna();
            pguna = new PermohonanPengguna();
            pguna.setIdPengguna(idPengguna);

            addSimpleError("Id Pengguna tidak wujud.");
        }
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/uam/kemasKini_profile.jsp");
    }

    public Resolution kembali() {
        logger.info("Back to Login Page....");
        return new ForwardResolution("/WEB-INF/jsp/main/main.jsp");
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

    public String getNamaNegeri() {
        KodNegeri kn = negeriDAO.findById(pguna.getNegeri().getKod());
        return kn.getNama();
    }

    public String getNamaCaw() {
        KodCawangan kc = kodCawanganDAO.findById(pguna.getKodCawangan().getKod());
        return kc.getName();
    }

    public String getNamaPeranan() {
        KodPeranan kp = kodPerananDAO.findById(pguna.getPerananUtama().getKod());
        return kp.getNama();
    }

    public String getNamaJabatan() {
        KodJabatan kj = kodJabDAO.findById(pguna.getKodJabatan().getKod());
        return kj.getNama();
    }

    public String getNamaJawatan() {
        KodJawatan kjaw = kodJawatanDAO.findById(pguna.getKodJawatan().getKod());
        return kjaw.getNama();
    }

    public String getKodJawatan() {
        return kodJawatan;
    }

    public void setKodJawatan(String kodJawatan) {
        this.kodJawatan = kodJawatan;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getPeranan() {
        return peranan;
    }

    public void setPeranan(String peranan) {
        this.peranan = peranan;
    }

    public String getPenyelia() {
        return penyelia;
    }

    public void setPenyelia(String penyelia) {
        this.penyelia = penyelia;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public boolean isKaunter() {
        return kaunter;
    }

    public void setKaunter(boolean kaunter) {
        this.kaunter = kaunter;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public List<Pengguna> getListPengguna() {
        return listPengguna;
    }

    public void setListPengguna(List<Pengguna> listPengguna) {
        this.listPengguna = listPengguna;
    }

    public List<Pengguna> getSenaraiPenyeliaAktif() {
        return senaraiPenyeliaAktif;
    }

    public void setSenaraiPenyeliaAktif(List<Pengguna> senaraiPenyeliaAktif) {
        this.senaraiPenyeliaAktif = senaraiPenyeliaAktif;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNokp() {
        return nokp;
    }

    public void setNokp(String nokp) {
        this.nokp = nokp;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getNoTelefonBimbit() {
        return noTelefonBimbit;
    }

    public void setNoTelefonBimbit(String noTelefonBimbit) {
        this.noTelefonBimbit = noTelefonBimbit;
    }
    
    
}