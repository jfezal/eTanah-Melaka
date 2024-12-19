/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.JuruLelongDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodStatusPenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.SytJuruLelongDAO;
import etanah.ldap.LDAPManager;
import etanah.model.InfoAudit;
import etanah.model.JuruLelong;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNegeri;
import etanah.model.KodStatusAkaun;
import etanah.model.KodStatusPengguna;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.model.PermohonanPengguna;
import etanah.model.SytJuruLelong;
import etanah.service.common.JuruLelongService;
import etanah.service.common.LelongService;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import etanah.view.uam.MailService;
import etanah.view.uam.ViewMohonPgunaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/JurulelongTab")
public class TabJurulelongBerlesenActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(TabJurulelongBerlesenActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    JuruLelongDAO jurulelongDAO;
    @Inject
    SytJuruLelongDAO sytJuruLelongDAO;
    @Inject
    private UamService service;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    JuruLelongService jurulelongService;
    @Inject
    DaftarJurulelongManager manager;
    @Inject
    LelongService lelongService;
    @Inject
    KodStatusPenggunaDAO kodStatusPenggunaDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKlasifikasiDAO klasifikasiDAO;
    @Inject
    private etanah.Configuration conf;
    private JuruLelong jurulelong;
    private SytJuruLelong sytJuruLelong;
    private List<SytJuruLelong> listSytJuruLelong;
    private Permohonan permohonan;
    public static final String DELIM = "__^$__";
    private String noic;
    private boolean isPopup = false;
    private boolean melaka;
    public String jenisPengenalan;
    public String noPengenalan;
    private List<JuruLelong> senaraiJurulelong;
    private List<Lelongan> lelonganList = new ArrayList<Lelongan>();
    private boolean form = false;
    private String idPenyerah;
    private String namaPenyerah;
    private String idJBL;
    private String idJLB;
    private String idSykt;
    private List<String> listYear;
    protected static char[] goodChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
        'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_', '-', '@'};
    public static final int password_Length = 8;
    protected static java.util.Random random = new java.util.Random();
    private LDAPManager ldapManager;
    private String idPermohonan;
    private boolean stageJurulelongN9 = true;
    private boolean mlk = true;
    private boolean jual = true;
    private boolean display = false;
    private boolean flag = false;
    private boolean button = false;
    private boolean show = true;

    @DefaultHandler
    public Resolution showForm() {

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            melaka = true;
        } else if ("05".equals(conf.getProperty("kodNegeri"))) {
            melaka = false;
        }


        listSytJuruLelong = sytJuruLelongDAO.findAll();
        Date date = new Date();
        int year = date.getYear() + 1900;
        year -= 5;
        listYear = new ArrayList<String>();
        for (int i = 0; i < 25; i++) {
            listYear.add(String.valueOf(year));
            year++;
        }
        LOG.info("listSytJuruLelong : " + listSytJuruLelong.size());
        return new JSP("lelong/TabJurulelongBerlesen.jsp").addParameter("popup", "true");
    }

    public Resolution showForm2() {
        return new JSP("lelong/daftar_syarikat_jurulelong.jsp").addParameter("popup", "true");
    }

    public Resolution showFormPopup() {
        return new JSP("lelong/carian_jurulelong.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() throws Exception {
        PermohonanPengguna mohonPengguna = new PermohonanPengguna();
        Pengguna pguna = new Pengguna();
        ldapManager = new LDAPManager();
        boolean exist = ldapManager.findUserByUsername("jurulelong.noPengenalan");
        //create jurulelong baru
        LOG.info("idJLB : " + idJLB);
        LOG.info("simpan");
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        JuruLelong jl = null;
        if (idJLB != null) {
            jl = jurulelongDAO.findById(Long.parseLong(idJLB));

        } else {
            jl = new JuruLelong();
        }
        jl.setNama(getContext().getRequest().getParameter("jurulelong.nama"));
        KodJenisPengenalan kod = kodJenisPengenalanDAO.findById(getContext().getRequest().getParameter("jurulelong.jenisPengenalan.kod"));
        jl.setJenisPengenalan(kod);
        jl.setNoPengenalan(getContext().getRequest().getParameter("jurulelong.noPengenalan"));
        SytJuruLelong syrt = sytJuruLelongDAO.findById(Long.parseLong(getContext().getRequest().getParameter("jurulelong.sytJuruLelong.idSytJlb")));
        jl.setSytJuruLelong(syrt);
        jl.setAlamat1(getContext().getRequest().getParameter("jurulelong.alamat1"));
        jl.setAlamat2(getContext().getRequest().getParameter("jurulelong.alamat2"));
        jl.setAlamat3(getContext().getRequest().getParameter("jurulelong.alamat3"));
        jl.setAlamat4(getContext().getRequest().getParameter("jurulelong.alamat4"));
        KodNegeri kodN = kodNegeriDAO.findById(getContext().getRequest().getParameter("jurulelong.negeri.kod"));
        jl.setPoskod(getContext().getRequest().getParameter("jurulelong.poskod"));
        jl.setNegeri(kodN);
        jl.setNoTelefon1(getContext().getRequest().getParameter("jurulelong.noTelefon1"));
        jl.setNoTelefon2(getContext().getRequest().getParameter("jurulelong.noTelefon2"));
//        KodCawangan caw = kodCawanganDAO.findById(getContext().getRequest().getParameter("jurulelong.cawangan.kod"));
        jl.setCawangan(pengguna.getKodCawangan());
        jl.setThn_aktif(getContext().getRequest().getParameter("jurulelong.thn_aktif"));
        jl.setEmel(getContext().getRequest().getParameter("jurulelong.emel"));

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (!exist) {
                mohonPengguna.setIdPengguna(jl.getNoPengenalan());
                mohonPengguna.setNama(jl.getNama());
                mohonPengguna.setNegeri(jl.getNegeri());
                mohonPengguna.setAlamat1(jl.getAlamat1());
                mohonPengguna.setAlamat2(jl.getAlamat2());
                mohonPengguna.setAlamat3(jl.getAlamat3());
                mohonPengguna.setAlamat4(jl.getAlamat4());
                mohonPengguna.setPoskod(jl.getPoskod());
                mohonPengguna.setEmail(jl.getEmel());
                mohonPengguna.setInfoAufit(ia);
                mohonPengguna.setJawatan("JuruLelong");
//                mohonPengguna.setKodCawangan(kodCawanganDAO.findById("00"));
                mohonPengguna.setKodCawangan(pengguna.getKodCawangan());
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < password_Length; i++) {
                    sb.append(goodChar[random.nextInt(goodChar.length)]);
                }
                String passwordGenerate = sb.toString();
                mohonPengguna.setPassword(passwordGenerate);
                service.mohonPengguna(mohonPengguna);
                pguna.setIdPengguna(mohonPengguna.getIdPengguna());
                pguna.setNama(jl.getNama());
                pguna.setNegeri(jl.getNegeri());
                pguna.setAlamat1(jl.getAlamat1());
                pguna.setAlamat2(jl.getAlamat2());
                pguna.setAlamat3(jl.getAlamat3());
                pguna.setAlamat4(jl.getAlamat4());
                pguna.setPoskod(jl.getPoskod());
                pguna.setEmail(jl.getEmel());
                pguna.setInfoAudit(ia);
//                pguna.setKodCawangan(kodCawanganDAO.findById("01"));
                pguna.setKodCawangan(pengguna.getKodCawangan());
                pguna.setJawatan("JuruLelong");
                pguna.setStatus(kodStatusPenggunaDAO.findById("A"));
                pguna.setPerananUtama(kodPerananDAO.findById("JLB"));
                mohonPengguna.setPerananUtama(kodPerananDAO.findById("JLB"));
                pguna.setTahapSekuriti(klasifikasiDAO.findById(1));
                service.savePengguna(pguna);
                PenggunaPeranan penggunaPeranan = new PenggunaPeranan();
                penggunaPeranan.setPengguna(pguna);
                penggunaPeranan.setPeranan(pguna.getPerananUtama());
                penggunaPeranan.setInfoAudit(pguna.getInfoAudit());
                penggunaPeranan = service.savePenggunaPeranan(penggunaPeranan);
                ViewMohonPgunaData uam = new ViewMohonPgunaData();
                uam.newUser(mohonPengguna, passwordGenerate);
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
                mailService.sendEmail(to, subject, msg, conf.getProperty("kodNegeri"));

                jl.setIdPengguna(pguna);
                jl.setInfoAudit(ia);
                jl.setAktif('Y');
                manager.saveOrUpdate(jl);
                LOG.info("JuruLelong laaaa : " + jl.getIdJlb());

                //simpan jurulelong dlm table Lelong
                LOG.info("------simpan table lelong-------");

                idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                if (idPermohonan != null) {
                    permohonan = permohonanDAO.findById(idPermohonan);

                    LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());

                    lelonganList = lelongService.listLelonganAK(idPermohonan);
                    LOG.info("JuruLelong1 : " + jl.getIdJlb());
                    for (Lelongan ll : lelonganList) {
                        ll.setJurulelong(jl);
                        ll.setSytJuruLelong(syrt);
                        lelongService.saveOrUpdate(ll);
                    }

                }

                getContext().getRequest().setAttribute("jurulelong", Boolean.TRUE);
                addSimpleMessage("Maklumat telah berjaya disimpan.");
            } else {
                addSimpleError("Id Pengguna telah wujud");

            }
        } else {
            manager.saveOrUpdate(jl);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

//        showForm();
        jurulelong = jl;
        listSytJuruLelong = sytJuruLelongDAO.findAll();

        getContext().getRequest().setAttribute("jurulelong", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        return new JSP("lelong/TabJurulelongBerlesen.jsp").addParameter("popup", "true");
    }

    public Resolution simpanSyarikat() {
        //create jurulelong baru
        LOG.info("--simpanSyarikat--");
        String results = "";
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        SytJuruLelong sj = new SytJuruLelong();
        sj.setNama(sytJuruLelong.getNama());
        KodJenisPengenalan kod = kodJenisPengenalanDAO.findById(sytJuruLelong.getJenisPengenalan().getKod());
        sj.setJenisPengenalan(kod);
        sj.setNoPengenalan(sytJuruLelong.getNoPengenalan());
        sj.setAlamat1(sytJuruLelong.getAlamat1());
        sj.setAlamat2(sytJuruLelong.getAlamat2());
        sj.setAlamat3(sytJuruLelong.getAlamat3());
        sj.setAlamat4(sytJuruLelong.getAlamat4());
        sj.setPoskod(sytJuruLelong.getPoskod());
        KodNegeri kodN = kodNegeriDAO.findById(sytJuruLelong.getNegeri().getKod());
        sj.setNegeri(kodN);
        sj.setNoTelefon1(sytJuruLelong.getNoTelefon1());
        sj.setNoTelefon2(sytJuruLelong.getNoTelefon2());
        sj.setCawangan(caw);
        sj.setInfoAudit(ia);
        manager.saveOrUpdate(sj);
        showForm();
        listSytJuruLelong = sytJuruLelongDAO.findAll();

        if (sj != null) {
            LOG.debug("SytJuruLelong found! idSyrt=" + sj.getIdSytJlb());
            StringBuilder s = new StringBuilder();
            s.append(sj.getNama() != null ? sj.getNama() : "").append(DELIM).append(sj.getNoPengenalan() != null ? sj.getNoPengenalan() : "").append(DELIM).append(sj.getIdSytJlb() != 0 ? sj.getIdSytJlb() : "");
            results = s.toString();
            LOG.debug(s);
        } else {
            LOG.debug("Jurulelong not found");
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new StreamingResolution("text/plain", results);
    }

    public Resolution searchJurulelong() {
        LOG.info("::start cari penyerah::");
        findJurulelong();
        return showFormPopup();
    }

    public void findJurulelong() {
        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
            senaraiJurulelong = lelongService.findAll();
        } else {
            senaraiJurulelong = lelongService.findPenyerahByParam(namaPenyerah, idPenyerah);
        }
        if (senaraiJurulelong.isEmpty()) {
            form = true;
        }
    }

    public Resolution findNoSyarikat() {
        LOG.debug("idSykt = " + idSykt);
        SytJuruLelong sj = null;
        String results = "";
        try {
            sj = sytJuruLelongDAO.findById(Long.parseLong(idSykt));
        } catch (Exception ex) {
            LOG.debug(ex);
            return new StreamingResolution("text/plain", results);
        }
        if (sj != null) {
            LOG.debug("SytJuruLelong found! noPengenalan=" + sj.getNoPengenalan());

            results = sj.getNoPengenalan();
            LOG.debug("results : " + results);
        } else {
            LOG.debug("Jurulelong not found");
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution findByID() {
        LOG.debug("idJBL=" + idJBL);
        JuruLelong j = null;
        String results = "";
        try {
            j = jurulelongDAO.findById(Long.parseLong(idJBL));
        } catch (Exception ex) {
            LOG.debug(ex);
            return new StreamingResolution("text/plain", results);
        }
        if (j != null) {
            LOG.debug("JuruLelong found! idJlb=" + j.getIdJlb());
            StringBuilder s = new StringBuilder();
            s.append(j.getJenisPengenalan() != null ? j.getJenisPengenalan().getKod() : "").append(DELIM).append(j.getNoPengenalan() != null ? j.getNoPengenalan() : "").append(DELIM).append(j.getNama() != null ? j.getNama() : "").append(DELIM).append(j.getAlamat1() != null ? j.getAlamat1() : "").append(DELIM).append(j.getAlamat2() != null ? j.getAlamat2() : "").append(DELIM).append(j.getAlamat3() != null ? j.getAlamat3() : "").append(DELIM).append(j.getAlamat4() != null ? j.getAlamat4() : "").append(DELIM).append(j.getPoskod() != null ? j.getPoskod() : "").append(DELIM).append(j.getNegeri() != null ? j.getNegeri().getKod() : "").append(DELIM).append(j.getCawangan() != null ? j.getCawangan().getKod() : "").append(DELIM).append(j.getNoTelefon1() != null ? j.getNoTelefon1() : "").append(DELIM).append(j.getNoTelefon2() != null ? j.getNoTelefon2() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getIdSytJlb() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getNoPengenalan() : "").append(DELIM).append(j.getIdJlb() != 0 ? j.getIdJlb() : "").append(DELIM).append(j.getThn_aktif() != null ? j.getThn_aktif() : "");
            results = s.toString();
            LOG.debug(s);
        } else {
            LOG.debug("Jurulelong not found");
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckByNoKp() {
        LOG.debug("jenisPengenalan=" + jenisPengenalan
                + ",noPengenalan=" + noPengenalan);

        List l = sessionProvider.get().createQuery(
                "select j from JuruLelong j "
                + "where j.jenisPengenalan.kod = :jenisPengenalan and "
                + "j.noPengenalan = :noPengenalan").setString("jenisPengenalan", jenisPengenalan).setString("noPengenalan", noPengenalan).list();
        String results = "";

        if (l.size() > 0) {
            if (l.size() > 1) {
                LOG.warn("There are more than 1 result");
            }
            JuruLelong j = (JuruLelong) l.get(0);
            LOG.debug("JuruLelong found! idJlb=" + j.getIdJlb());
            StringBuilder s = new StringBuilder();
            s.append(j.getJenisPengenalan() != null ? j.getJenisPengenalan().getKod() : "").append(DELIM).append(j.getNoPengenalan() != null ? j.getNoPengenalan() : "").append(DELIM).append(j.getNama() != null ? j.getNama() : "").append(DELIM).append(j.getAlamat1() != null ? j.getAlamat1() : "").append(DELIM).append(j.getAlamat2() != null ? j.getAlamat2() : "").append(DELIM).append(j.getAlamat3() != null ? j.getAlamat3() : "").append(DELIM).append(j.getAlamat4() != null ? j.getAlamat4() : "").append(DELIM).append(j.getPoskod() != null ? j.getPoskod() : "").append(DELIM).append(j.getNegeri() != null ? j.getNegeri().getKod() : "").append(DELIM).append(j.getCawangan() != null ? j.getCawangan().getKod() : "").append(DELIM).append(j.getNoTelefon1() != null ? j.getNoTelefon1() : "").append(DELIM).append(j.getNoTelefon2() != null ? j.getNoTelefon2() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getIdSytJlb() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getNoPengenalan() : "").append(DELIM).append(j.getIdJlb() != 0 ? j.getIdJlb() : "").append(DELIM).append(j.getThn_aktif() != null ? j.getThn_aktif() : "");
            results = s.toString();
            LOG.debug(s);
        } else {
            LOG.debug("Jurulelong not found");
        }
        return new StreamingResolution("text/plain", results);
    }

    // for melaka checking duplicate ic
    public Resolution doCheckJuruLelong() {

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            melaka = true;

            String noPengenalan = getContext().getRequest().getParameter("icno");
            LOG.info("*****AjaxValidateIC.doCheckJuruLelong :" + getContext().getRequest().getParameter("icno"));
            String results = "0";

            JuruLelong juru = lelongService.getIC(noPengenalan);

            if (juru != null) {
                LOG.info("id Jurulelong :" + juru.getIdJlb());
                LOG.info("ic jurulelong :" + juru);
                noPengenalan = juru.getNoPengenalan();
                LOG.info("ic : " + noPengenalan);
                results = "1";
            }
            LOG.debug("results : " + results);
            return new StreamingResolution("text/plain", results);
        } else if ("05".equals(conf.getProperty("kodNegeri"))) {
            melaka = false;
        }
        return new StreamingResolution("text/plain");
    }

    public SytJuruLelong getSytJuruLelong() {
        return sytJuruLelong;
    }

    public void setSytJuruLelong(SytJuruLelong sytJuruLelong) {
        this.sytJuruLelong = sytJuruLelong;
    }

    public JuruLelong getJurulelong() {
        return jurulelong;
    }

    public void setJurulelong(JuruLelong jurulelong) {
        this.jurulelong = jurulelong;
    }

    public List<SytJuruLelong> getListSytJuruLelong() {
        return listSytJuruLelong;
    }

    public void setListSytJuruLelong(List<SytJuruLelong> listSytJuruLelong) {
        this.listSytJuruLelong = listSytJuruLelong;
    }

    public String getNoic() {
        return noic;
    }

    public void setNoic(String noic) {
        this.noic = noic;
    }

    public boolean isIsPopup() {
        return isPopup;
    }

    public void setIsPopup(boolean isPopup) {
        this.isPopup = isPopup;
    }

    public List<JuruLelong> getSenaraiJurulelong() {
        return senaraiJurulelong;
    }

    public void setSenaraiJurulelong(List<JuruLelong> senaraiJurulelong) {
        this.senaraiJurulelong = senaraiJurulelong;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getIdJBL() {
        return idJBL;
    }

    public void setIdJBL(String idJBL) {
        this.idJBL = idJBL;
    }

    public String getIdPenyerah() {
        return idPenyerah;
    }

    public void setIdPenyerah(String idPenyerah) {
        this.idPenyerah = idPenyerah;
    }

    public String getNamaPenyerah() {
        return namaPenyerah;
    }

    public void setNamaPenyerah(String namaPenyerah) {
        this.namaPenyerah = namaPenyerah;
    }

    public String getIdJLB() {
        return idJLB;
    }

    public void setIdJLB(String idJLB) {
        this.idJLB = idJLB;
    }

    public String getIdSykt() {
        return idSykt;
    }

    public void setIdSykt(String idSykt) {
        this.idSykt = idSykt;
    }

    public List<String> getListYear() {
        return listYear;
    }

    public void setListYear(List<String> listYear) {
        this.listYear = listYear;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }

    public List<Lelongan> getLelonganList() {
        return lelonganList;
    }

    public void setLelonganList(List<Lelongan> lelonganList) {
        this.lelonganList = lelonganList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public boolean isMlk() {
        return mlk;
    }

    public void setMlk(boolean mlk) {
        this.mlk = mlk;
    }

    public boolean isJual() {
        return jual;
    }

    public void setJual(boolean jual) {
        this.jual = jual;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isStageJurulelongN9() {
        return stageJurulelongN9;
    }

    public void setStageJurulelongN9(boolean stageJurulelongN9) {
        this.stageJurulelongN9 = stageJurulelongN9;
    }
}
