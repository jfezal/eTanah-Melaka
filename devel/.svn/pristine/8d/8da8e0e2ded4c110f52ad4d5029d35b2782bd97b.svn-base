/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodStatusPenggunaDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanPenggunaDAO;
import etanah.ldap.LDAPManager;
import etanah.model.InfoAudit;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPeranan;
import etanah.model.KodStatusPengguna;
import etanah.model.KodUnitJabatan;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.model.PermohonanPengguna;
import etanah.model.PermohonanPenggunaDitolak;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import etanah.view.etanahConfiguration;
import etanah.view.stripes.MainActionBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Aziz
 */
//@HttpCache(allow = false)
@UrlBinding("/uam/viewMohonPguna")
public class ViewMohonPgunaData extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NewAndUpdateActionBean.class);
    @Inject
    private UamService service;
    @Inject
    KodStatusPenggunaDAO kodStatusPenggunaDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    private PermohonanPenggunaDAO mohonpgunaDAO;
    @Inject
    private PenggunaDAO penggunaDAO;
    @Inject
    KodKlasifikasiDAO klasifikasiDAO;
    private LDAPManager ldapManager;
    private String noKp;
    private String nama;
    private String kodStatus;
    private String kodCawangan;
    private String alamat;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String noPhone;
    private String noHp;
    private String peranan;
    private String kodKlas;
    private PermohonanPengguna mohonPguna;
    private List<KodPeranan> listkodPeranan = new ArrayList<KodPeranan>();
    private String idPengguna;
    private String catatan;
    private String peranankod;
    private boolean stageAdmin = false;
    private Pengguna penyelia;
    private String perananUtama;
    public static final int password_Length = 8;
    private String passwordGenerate = "";
    protected static java.util.Random random = new java.util.Random();
    private String pelulusBayar;
    private String pelulusbayarValue;
    @Inject
    private etanah.Configuration conf;
    private String klasifikasiKod;
    protected static char[] goodChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
        'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_', '-', '@'};
    private boolean kaunter = false;
    private boolean melaka = false;
    private String sokongan = new String();
    private boolean spoc = false;
    private String noKaunter;
    private String penyeliaFlag;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("WEB-INF/jsp/uam/viewMohonPguna.jsp");
    }

    public Resolution getMohonPgunaData() {
        idPengguna = getContext().getRequest().getParameter("idPengguna");
        logger.debug("Search mohon pguna by Id :: " + idPengguna);
        mohonPguna = service.viewMohonPgunaData(idPengguna);
//        alamat = mohonPguna.getAlamat1() + returnValue(mohonPguna.getAlamat2()) + returnValue(mohonPguna.getAlamat3()) + returnValue(mohonPguna.getPoskod()) + " " + returnValue(mohonPguna.getAlamat4()) + ", " + mohonPguna.getNegeri().getNama();
        alamat = mohonPguna.getAlamat1();
        alamat2 = mohonPguna.getAlamat2();
        alamat3 = mohonPguna.getAlamat3();
        alamat4 = mohonPguna.getAlamat4();
        poskod = mohonPguna.getPoskod();
        if (mohonPguna.getNegeri() != null) {
            negeri = mohonPguna.getNegeri().getNama();
        }
        String ptg = "";
        if (mohonPguna.getKodCawangan().getKod().equals("00")) {
            ptg = "Y";
        } else {
            ptg = "N";
        }
        List<KodUnitJabatan> listKj = service.getKodJab(mohonPguna.getKodJabatan().getKod(), ptg);
        if (!listKj.isEmpty()) {
            List<KodUnitJabatan> listK = service.getKodJabUnit(String.valueOf(listKj.get(0).getKodUnit().getId()), ptg);
            List<String> kj = new ArrayList();
            for (KodUnitJabatan kodUnit : listK) {
                kj.add(kodUnit.getJabatan().getKod());
            }
            listkodPeranan = service.getKodPerananByUnit(kj);
        }

//        listkodPeranan = service.findPerananByJab(mohonPguna.getKodJabatan().getKod());
        logger.debug("Search mohon pguna " + idPengguna + " success!!");
        if (mohonPguna.getStatus().getKod().equals("SH")) {
            stageAdmin = true;
            penyelia = mohonPguna.getPenyelia();
            perananUtama = mohonPguna.getPerananUtama().getNama();

            pelulusBayar = mohonPguna.getPelulusBayaranKurang();
            if (StringUtils.isNotBlank(pelulusBayar)) {
                if (pelulusBayar.equals("Y")) {
                    pelulusbayarValue = "Ya";
                } else {
                    pelulusbayarValue = "Tidak";
                }
            }
            if (mohonPguna.getPerananUtama() != null && mohonPguna.getPerananUtama().getKumpBPEL().equals("ptspoc")) {
                spoc = true;
            }
        }
        if (mohonPguna.getStatus().getKod().equals("KM")) {

            penyelia = mohonPguna.getPenyelia();
            peranankod = mohonPguna.getPerananUtama().getKod();
            if (mohonPguna.getTahapSekuriti() != null) {
                klasifikasiKod = String.valueOf(mohonPguna.getTahapSekuriti().getKod());
            }
            pelulusBayar = mohonPguna.getPelulusBayaranKurang();

            if (mohonPguna.getPerananUtama() != null && mohonPguna.getPerananUtama().getKumpBPEL().equals("ptspoc")) {
                spoc = true;
            }

        }
        if (mohonPguna.getKodJabatan().getKod().equals("16") || mohonPguna.getKodJabatan().getKod().equals("17")) {
            kaunter = true;
        } else {
            kaunter = false;
        }
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }

        return new ForwardResolution("/WEB-INF/jsp/uam/viewMohonPguna.jsp");
    }

    private String returnValue(String sa) {
        String s = "";
        if (StringUtils.isNotBlank(sa)) {
            s = ", " + sa;
        } else {
            s = "";
        }
        return s;
    }

    public Resolution sah() throws NamingException {
        idPengguna = getContext().getRequest().getParameter("idPengguna");
        if (sokongan.equals("Y")) {
            if (StringUtils.isNotBlank(peranankod)) {
                KodStatusPengguna kodStatusPengguna = kodStatusPenggunaDAO.findById("SH");
//            KodKlasifikasi kodKlasi = klasifikasiDAO.findById(Integer.parseInt(klasifikasiKod));
                KodPeranan kodPeranan = kodPerananDAO.findById(peranankod);
                mohonPguna = service.viewMohonPgunaData(idPengguna);
                mohonPguna.setPerananUtama(kodPeranan);
                mohonPguna.setStatus(kodStatusPengguna);
                mohonPguna.setTahapSekuriti(kodPeranan.getKodKlasifikasi());
                mohonPguna.setCatatan(catatan);
                mohonPguna.setIdKaunter(noKaunter);
                mohonPguna.setPelulusBayaranKurang(pelulusBayar);

                service.mohonPengguna(mohonPguna);
            } else {
                addSimpleError("Sila Pilih Peranan Utama");
            }
        } else {
            tolak(idPengguna);
        }

        return new RedirectResolution(VerifyUserActionBean.class);
    }

    public void tolak(String idPengguna) throws NamingException {
        String kodNegeri = conf.getProperty("kodNegeri");
//        idPengguna = getContext().getRequest().getParameter("idPengguna");
        KodStatusPengguna kodStatusPengguna = kodStatusPenggunaDAO.findById("TK");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().
                getAttribute(etanahActionBeanContext.KEY_USER);
        String subject;
        mohonPguna = service.viewMohonPgunaData(idPengguna);
        mohonPguna.setStatus(kodStatusPengguna);
        mohonPguna.setCatatan(catatan);

        subject = "Kemaskini Akaun";
        if (mohonPguna.getStatus().getKod().equals("KM")) {
            Pengguna peng = penggunaDAO.findById(idPengguna);
            mohonPguna = service.copyFromPengguna(peng, pengguna);
            service.mohonPengguna(mohonPguna);

        } else {
            PermohonanPenggunaDitolak ppd = setPenggunaDitolak(mohonPguna);
            service.deleteMohonPguna(mohonPguna);
            subject = "Permohonan Akaun";
        }

        String content = "Permohonan anda untuk menggunakan aplikasi eTanah ditolak pada peringkat penyelia atas sebab - sebab berikut : /n" + catatan;

        MailService mail = new MailService();
        String[] emel = {mohonPguna.getEmail()};
        mail.sendEmail(emel, subject, content, kodNegeri);
//        return new RedirectResolution(VerifyUserActionBean.class);
    }

    public PermohonanPenggunaDitolak setPenggunaDitolak(PermohonanPengguna pp) {

        PermohonanPenggunaDitolak ppd = new PermohonanPenggunaDitolak();
        InfoAudit ia = pp.getInfoAufit();
        ia.setTarikhMasuk(new Date());
        ppd.setIdPguna(pp.getIdPengguna());
        ppd.setAlamat1(pp.getAlamat1());
        ppd.setAlamat2(pp.getAlamat2());
        ppd.setAlamat3(pp.getAlamat3());
        ppd.setAlamat4(pp.getAlamat4());
        ppd.setCatatan(pp.getCatatan());
        ppd.setEmail(pp.getEmail());
        ppd.setInfoAufit(ia);
        ppd.setJawatan(pp.getJawatan());
        ppd.setKodCawangan(pp.getKodCawangan());
        ppd.setKodJabatan(pp.getKodJabatan());
        ppd.setNama(pp.getNama());
        ppd.setNegeri(pp.getNegeri());
        ppd.setKodJawatan(pp.getKodJawatan());
        service.savePenggunaTolak(ppd);
        return ppd;
    }

    public Resolution sendPassword() throws NamingException, Exception {
        logger.info("Sending email..");
        String kodNegeri = conf.getProperty("kodNegeri");
//        get id_pengguna from jsp...
        idPengguna = getContext().getRequest().getParameter("idPengguna");
        PermohonanPengguna mohonPengguna = mohonpgunaDAO.findById(idPengguna);
        Pengguna pengguna = new Pengguna();
        System.out.println("Peranan Utama ::::" + mohonPengguna.getPerananUtama().getKod());
        ldapManager = new LDAPManager();
        logger.info("noKaunter" + noKaunter);

        boolean kemaskini = ldapManager.findUserByUsername(mohonPengguna.getIdPengguna());
        if (kemaskini) {
            pengguna = transferMohonPguna(mohonPengguna, mohonPengguna.getPassword(), kemaskini, penyeliaFlag);

            PenggunaPeranan penggunaPeranan = service.checkPerananbyIdPguna(pengguna.getIdPengguna(), pengguna.getPerananUtama().getKod());
            if (penggunaPeranan != null) {
            } else {
                penggunaPeranan = new PenggunaPeranan();
            }
            penggunaPeranan.setPengguna(pengguna);
            penggunaPeranan.setPeranan(pengguna.getPerananUtama());
            penggunaPeranan.setInfoAudit(pengguna.getInfoAudit());
            sahAdmin(pengguna);
            penggunaPeranan = service.savePenggunaPeranan(penggunaPeranan);
//            penggunaPerananDAO.save(penggunaPeranan);
        } else {
            if (mohonPengguna != null && StringUtils.isNotBlank(mohonPengguna.getIdPengguna()) && StringUtils.isNotBlank(mohonPengguna.getEmail())) {

                if (mohonPengguna.getEmail() != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < password_Length; i++) {
                        sb.append(goodChar[random.nextInt(goodChar.length)]);
                    }
                    passwordGenerate = sb.toString();
                    String subject = "Notifikasi kata laluan pengguna.";
                    String msg = "Tahniah. Pendaftaran anda untuk Sistem e-Tanah telah berjaya.\n"
                            + "ID Pengguna dan katalaluan anda adalah seperti berikut:\n\n"
                            + "\t ID Pengguna = " + mohonPengguna.getIdPengguna() + "\n"
                            + "\t Katalaluan = " + passwordGenerate + "\n\n"
                            + "Sila gunakan ID Pengguna dan Katalaluan ini untuk login masuk ke dalam sistem e-Tanah.\n"
                            + "Terima Kasih.";
//                            Ini adalah kata laluan anda untuk sistem e-Tanah :" + passwordGenerate + "\n Sila gunakan kata laluan ini untuk login masuk ke dalam sistem e-Tanah. Terima kasih";
                    String[] to = {mohonPengguna.getEmail()};
                    mohonPengguna.setPassword(passwordGenerate);
                    MailService mailService = new MailService();
                    if (mailService.sendEmail(to, subject, msg, kodNegeri)) {
                        pengguna = transferMohonPguna(mohonPengguna, passwordGenerate, kemaskini, penyeliaFlag);
                        PenggunaPeranan penggunaPeranan = new PenggunaPeranan();
                        penggunaPeranan.setPengguna(pengguna);
                        penggunaPeranan.setPeranan(pengguna.getPerananUtama());
                        penggunaPeranan.setInfoAudit(pengguna.getInfoAudit());
                        sahAdmin(pengguna);
                        penggunaPeranan = service.savePenggunaPeranan(penggunaPeranan);
                    }
                    logger.info("Testing : " + pengguna.getPassword());
                }
            }
        }

        logger.info("Sending Email Success....");
        addSimpleMessage("Akaun pengguna telah diaktifkan.");
        return new RedirectResolution(VerifyUserActionBean.class);

    }

    public Pengguna transferMohonPguna(PermohonanPengguna pp, String password, boolean kemaskini, String penyeliaFlag) throws Exception {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();

        Pengguna p = new Pengguna();
        boolean proceed = false;

        KodStatusPengguna ksp = new KodStatusPengguna();
        if (kemaskini) {
            proceed = modifyUser(pp);
            ksp.setKod("A");
        } else {
            proceed = newUser(pp, password);
            ksp.setKod("B");
        }
        if (proceed) {
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            p = penggunaDAO.findById(pp.getIdPengguna());
            if (p == null) {
                p = new Pengguna();
                p.setIdPengguna(pp.getIdPengguna());

            } else {
            }

            p.setNama(pp.getNama());
            p.setNoPengenalan(pp.getNoPengenalan());
//            p.setPassword(pp.getPassword());
            p.setAlamat1(pp.getAlamat1());
            p.setAlamat2(pp.getAlamat2());
            p.setAlamat3(pp.getAlamat3());
            p.setBilCubaan(pp.getBilCubaan());
            p.setEmail(pp.getEmail());
            p.setIdKaunter(pp.getIdKaunter());
            p.setJawatan(pp.getJawatan());
            p.setKodCawangan(pp.getKodCawangan());
            p.setKodJabatan(pp.getKodJabatan());
            p.setNamaPencetak1(pp.getNamaPencetak1());
            p.setNamaPencetak2(pp.getNamaPencetak2());
            p.setNegeri(pp.getNegeri());
            p.setNoTelefon(pp.getNoTelefon());
            p.setNoTelefonBimbit(pp.getNoTelefonBimbit());
            p.setPenyelia(pp.getPenyelia());
            p.setPerananUtama(pp.getPerananUtama());
            p.setPoskod(pp.getPoskod());
            //p.setSenaraiPeranan(pp.getSenaraiPeranan());
            p.setPelulusBayaranKurang(pp.getPelulusBayaranKurang());
            p.setKodJawatan(pp.getKodJawatan());
            p.setPenyeliaFlag(penyeliaFlag);

            p.setStatus(ksp);
            p.setTahapSekuriti(pp.getTahapSekuriti());
            p.setInfoAudit(info);
            p = service.savePengguna(p);

        }

        //create new user at LDAP
        return p;

    }

    private boolean modifyUser(PermohonanPengguna pp) throws NamingException {
        boolean check = true;
        Pengguna pengguna = new Pengguna();
        ldapManager = new LDAPManager();
        ldapManager.modifyUser(pp.getIdPengguna(), pp.getNama(), pp.getNama(), pp.getJawatan());
//        ldapManager.changePassword(pp.getIdPengguna(), pp.getPassword());
        pengguna = penggunaDAO.findById(pp.getIdPengguna());
        if (pp.getPerananUtama() != null) {
            if (!pp.getPerananUtama().getKumpBPEL().equals(pengguna.getPerananUtama().getKumpBPEL())) {
                try {
                    ldapManager.removeGroupFromUser(pp.getIdPengguna(), pengguna.getPerananUtama().getKumpBPEL());
                    ldapManager.assignGroupToUser(pp.getIdPengguna(), pp.getPerananUtama().getKumpBPEL());
                    service.deletePenggunaPeranan(pp.getIdPengguna(), pengguna.getPerananUtama().getKod());
                    //FIXME
                    pengguna.setPerananUtama(pp.getPerananUtama());

                    penggunaDAO.saveOrUpdate(pengguna);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(VerifyUserActionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return check;
    }

    public boolean newUser(PermohonanPengguna mguna, String pass) throws Exception {
        logger.info("Create new user " + mguna.getIdPengguna());
        boolean check = true;
        ldapManager = new LDAPManager();
        try {
            ldapManager.addUser(mguna.getIdPengguna(), mguna.getNama(), mguna.getNama(), pass,
                    mguna.getJawatan());
            if (mguna.getPerananUtama() != null) {
                ldapManager.assignGroupToUser(mguna.getIdPengguna(), mguna.getPerananUtama().getKumpBPEL());
            }
            check = true;
        } catch (Exception ex) {
        }
        logger.info("Create new user " + mguna.getIdPengguna() + " complete");
        return check;
    }

    public void sahAdmin(Pengguna pengguna) throws Exception {
        PermohonanPengguna mohonPengguna = service.searchingUser2(pengguna.getIdPengguna());
        if (mohonPengguna != null) {
            mohonPengguna.setStatus(kodStatusPenggunaDAO.findById("SD"));
            service.updatePP(mohonPengguna);
        }
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getKodKlas() {
        return kodKlas;
    }

    public void setKodKlas(String kodKlas) {
        this.kodKlas = kodKlas;
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public PermohonanPengguna getMohonPguna() {
        return mohonPguna;
    }

    public void setMohonPguna(PermohonanPengguna mohonPguna) {
        this.mohonPguna = mohonPguna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getNoKp() {
        return noKp;
    }

    public void setNoKp(String noKp) {
        this.noKp = noKp;
    }

    public String getNoPhone() {
        return noPhone;
    }

    public void setNoPhone(String noPhone) {
        this.noPhone = noPhone;
    }

    public String getPeranan() {
        return peranan;
    }

    public void setPeranan(String peranan) {
        this.peranan = peranan;
    }

    public UamService getService() {
        return service;
    }

    public void setService(UamService service) {
        this.service = service;
    }

    public List<KodPeranan> getListkodPeranan() {
        return listkodPeranan;
    }

    public void setListkodPeranan(List<KodPeranan> listkodPeranan) {
        this.listkodPeranan = listkodPeranan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getPeranankod() {
        return peranankod;
    }

    public void setPeranankod(String peranankod) {
        this.peranankod = peranankod;
    }

    public boolean isStageAdmin() {
        return stageAdmin;
    }

    public void setStageAdmin(boolean stageAdmin) {
        this.stageAdmin = stageAdmin;
    }

    public String getPerananUtama() {
        return perananUtama;
    }

    public void setPerananUtama(String perananUtama) {
        this.perananUtama = perananUtama;
    }

    public Pengguna getPenyelia() {
        return penyelia;
    }

    public void setPenyelia(Pengguna penyelia) {
        this.penyelia = penyelia;
    }

    public String getKlasifikasiKod() {
        return klasifikasiKod;
    }

    public void setKlasifikasiKod(String klasifikasiKod) {
        this.klasifikasiKod = klasifikasiKod;
    }

    public String getPelulusBayar() {
        return pelulusBayar;
    }

    public void setPelulusBayar(String pelulusBayar) {
        this.pelulusBayar = pelulusBayar;
    }

    public String getPelulusbayarValue() {
        return pelulusbayarValue;
    }

    public void setPelulusbayarValue(String pelulusbayarValue) {
        this.pelulusbayarValue = pelulusbayarValue;
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

    public String getSokongan() {
        return sokongan;
    }

    public void setSokongan(String sokongan) {
        this.sokongan = sokongan;
    }

    public boolean isSpoc() {
        return spoc;
    }

    public void setSpoc(boolean spoc) {
        this.spoc = spoc;
    }

    public String getNoKaunter() {
        return noKaunter;
    }

    public void setNoKaunter(String noKaunter) {
        this.noKaunter = noKaunter;
    }

    public String getPenyeliaFlag() {
        return penyeliaFlag;
    }

    public void setPenyeliaFlag(String penyeliaFlag) {
        this.penyeliaFlag = penyeliaFlag;
    }
}
