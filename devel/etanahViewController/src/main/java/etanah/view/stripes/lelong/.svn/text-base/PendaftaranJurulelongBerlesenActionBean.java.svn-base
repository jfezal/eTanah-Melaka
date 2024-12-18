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
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodJawatanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodStatusPenggunaDAO;
import etanah.dao.SytJuruLelongDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.MenuCapaianDAO;
import etanah.dao.PenggunaDAO;
import etanah.ldap.LDAPManager;
import etanah.model.InfoAudit;
import etanah.model.JuruLelong;
import etanah.model.KodCawangan;
import etanah.model.KodJabatan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodStatusAkaun;
import etanah.model.KodStatusPengguna;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPeranan;
import etanah.model.KodJawatan;
import etanah.model.MenuCapaian;
import etanah.model.MenuItem;
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
 * @author mdizzat.mashrom, nur.aqilah
 */
@UrlBinding("/lelong/pendaftaran_jurulelong")
public class PendaftaranJurulelongBerlesenActionBean extends AbleActionBean {
    
    private static final Logger LOG = Logger.getLogger(PendaftaranJurulelongBerlesenActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    MenuCapaian menuCapaian;
    @Inject
    MenuCapaianDAO menuCapaianDAO;
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
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodJawatanDAO kodJawatanDAO;
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
    KodKlasifikasiDAO klasifikasiDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    private etanah.Configuration conf;
    private JuruLelong jurulelong;
    private SytJuruLelong sytJuruLelong;
    private List<SytJuruLelong> listSytJuruLelong;
    private List<MenuCapaian> capaiList;
    public static final String DELIM = "__^$__";
    private String noic;
    private boolean isPopup = false;
    private boolean showMess = false;
    private boolean melaka;
    private boolean cariSykt;
    private String idjlb;
    public String idJlb;
    public String jenisPengenalan;
    public String noPengenalan;
    private List<JuruLelong> senaraiJurulelong;
    private boolean button;
    private boolean btn = false;
    private boolean btn2 = false;
    private boolean form = false;
    private boolean alertM = false;
    private int idCapaian;
    private String idPenyerah;
    private String namaPenyerah;
    private String idJBL;
    private String idJLB;
    private String idSykt;
    private String idSyarikat;
    private String aktif;
    private String tahunAktif;
    private String noSykt;
    private List<String> listYear;
    protected static char[] goodChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
        'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_', '-', '@'};
    public static final int password_Length = 8;
    protected static java.util.Random random = new java.util.Random();
    private LDAPManager ldapManager;
    
    @DefaultHandler
    public Resolution showForm() {
        
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            melaka = true;
        } else if ("05".equals(conf.getProperty("kodNegeri"))) {
            melaka = false;
        }
        cariSykt = false;
        
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
        return new ForwardResolution("/WEB-INF/jsp/lelong/perlantikkan_penolong_berlesen.jsp");
    }
    
    public Resolution showForm2() {
        return new JSP("lelong/daftar_syarikat_jurulelong.jsp").addParameter("popup", "true");
    }
    
    public Resolution showFormPopup() {
        LOG.info("popup1");
        cariSykt = false;
        return new JSP("lelong/carian_jurulelong.jsp").addParameter("popup", "true");
    }
    
    public Resolution showFormPopup2() {
        LOG.info("popup2");
        cariSykt = true;
        return new JSP("lelong/carian_jurulelong.jsp").addParameter("popup", "true");
    }

    //added by nur.aqilah
    //popup kemaskini maklumat syarikat jurulelong
    public Resolution showEditSyarikat() {
        
        LOG.info("--------ni untuk Popup Edit Maklumat Syarikat Jurulelong-------");
        LOG.debug("idSyarikat = " + idSyarikat);
        LOG.debug("idjlb = " + idjlb);
        LOG.debug("noSykt = " + noSykt);

//        long idJLB = Long.parseLong(idjlb);
        SytJuruLelong sj = null;
        JuruLelong jl = null;
        
        try {
            sj = sytJuruLelongDAO.findById(Long.parseLong(idSyarikat));
            if (idjlb != null) {
                jl = jurulelongDAO.findById(Long.parseLong(idJLB));
                LOG.debug("jurulelong " + jl.getNama());
            }
        } catch (Exception ex) {
            LOG.debug(ex);
            
        }
        if (jl != null) {
            jurulelong = jl;
            LOG.debug("JuruLelong found! noPengenalan=" + jl.getNoPengenalan());
        } else {
            LOG.debug("Jurulelong not found");
        }
        
        if (sj != null) {
            sytJuruLelong = sj;
            LOG.debug("SytJuruLelong found! noPengenalan=" + sj.getNoPengenalan());
            
        } else {
            LOG.debug("Syarikat Jurulelong not found");
        }
        
        return new ForwardResolution("/WEB-INF/jsp/lelong/edit_syarikat_jurulelong.jsp").addParameter("popup", "true");
        
    }

    //added by nur.aqilah
    //kemaskini maklumat syarikat jurulelong
    public Resolution kemaskiniSyarikat() {
        LOG.info("--------ni untuk Kemaskini Maklumat Syarikat Jurulelong-------");
        
        idSyarikat = getContext().getRequest().getParameter("sytJuruLelong.idSytJlb");
        LOG.info("id syarikat " + idSyarikat);
        
        SytJuruLelong sj = null;
        String results = "";
        
        try {
            sj = sytJuruLelongDAO.findById(Long.parseLong(idSyarikat));
        } catch (Exception ex) {
            LOG.debug(ex);
            
        }
        
        if (sj != null) {
            sytJuruLelong = sj;
            LOG.debug("SytJuruLelong found! noPengenalan=" + sj.getNoPengenalan());
            
            sj.setNama(getContext().getRequest().getParameter("sytJuruLelong.nama"));
            sj.setAlamat1(getContext().getRequest().getParameter("sytJuruLelong.alamat1"));
            sj.setAlamat2(getContext().getRequest().getParameter("sytJuruLelong.alamat2"));
            sj.setAlamat3(getContext().getRequest().getParameter("sytJuruLelong.alamat3"));
            sj.setAlamat4(getContext().getRequest().getParameter("sytJuruLelong.alamat4"));
            sj.setNoPengenalan(getContext().getRequest().getParameter("sytJuruLelong.noPengenalan"));
            sj.setNoTelefon1(getContext().getRequest().getParameter("sytJuruLelong.noTelefon1"));
            sj.setNoTelefon2(getContext().getRequest().getParameter("sytJuruLelong.noTelefon2"));
            sj.setPoskod(getContext().getRequest().getParameter("sytJuruLelong.poskod"));
            KodNegeri kodN = kodNegeriDAO.findById(getContext().getRequest().getParameter("sytJuruLelong.negeri.kod"));
            sj.setNegeri(kodN);
            
            aktif = getContext().getRequest().getParameter("actionBean.sytJuruLelong.aktif");
            if (aktif.equals("Y")) {
                sj.setAktif('Y');
            } else {
                sj.setAktif('T');
            }
            
            manager.saveOrUpdate(sj);
            addSimpleMessage("Maklumat Syarikat Jurulelong Telah Berjaya Dikemaskini");
        } else {
            LOG.debug("Jurulelong not found");
        }
        
        return new ForwardResolution("/WEB-INF/jsp/lelong/edit_syarikat_jurulelong.jsp").addParameter("popup", "true");
    }

    //added by nur.aqilah
    //refresh
    public Resolution refresh() {
        
        LOG.info("--------ni untuk REFRESHHHH-------");
        
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            melaka = true;
        } else if ("05".equals(conf.getProperty("kodNegeri"))) {
            melaka = false;
        }
        
        idSyarikat = getContext().getRequest().getParameter("idSyarikat");
        idJlb = getContext().getRequest().getParameter("idJurulelong");
        LOG.info("idSyarikat : " + idSyarikat);
        LOG.info("idjlb : " + idjlb);
        
        SytJuruLelong sj = null;
        JuruLelong jl = null;
        
        try {
            sj = sytJuruLelongDAO.findById(Long.parseLong(idSyarikat));
            if (idjlb != null) {
                jl = jurulelongDAO.findById(Long.parseLong(idJLB));
            }
        } catch (Exception ex) {
            LOG.debug(ex);
            
        }
        
        if (jl != null) {
            
            aktif = jl.getAktif() + "";
            
            if (aktif.equals("Y")) {
                jl.setAktif('Y');
                aktif = 'Y' + "";
            } else {
                jl.setAktif('T');
                aktif = 'T' + "";
            }
            
            LOG.debug("JuruLelong found! noPengenalan=" + jl.getNoPengenalan());
            
        } else {
            LOG.debug("Jurulelong not found");
        }
        
        if (sj != null) {
            sytJuruLelong = sj;
            LOG.debug("SytJuruLelong found! noPengenalan=" + sj.getNoPengenalan());
            
        } else {
            LOG.debug("Syarikat Jurulelong not found");
        }
        
        jurulelong = jl;
        listSytJuruLelong = sytJuruLelongDAO.findAll();
        addSimpleMessage("Maklumat Syarikat Jurulelong Telah Berjaya Dikemaskini");
        
        return new ForwardResolution("/WEB-INF/jsp/lelong/perlantikkan_penolong_berlesen.jsp");
    }

    //added by nur.aqilah
    //kemaskini maklumat jurulelong
    public Resolution kemaskini() {
        
        LOG.info("--------ni untuk Kemaskini Maklumat Jurulelong-------");
        
        idPenyerah = getContext().getRequest().getParameter("jurulelong.idjlb");
        LOG.info("id jurulelong " + idPenyerah);
        
        aktif = getContext().getRequest().getParameter("aktif");
        
        JuruLelong jl = null;
        
        jl = jurulelongDAO.findById(Long.parseLong(idPenyerah));
        
        if (jl != null) {
            
            if (aktif.equals("Y")) {
                jl.setAktif('Y');
            } else {
                jl.setAktif('T');
            }
            
            jl.setNama(getContext().getRequest().getParameter("jurulelong.nama"));
            jl.setAlamat1(getContext().getRequest().getParameter("jurulelong.alamat1"));
            jl.setAlamat2(getContext().getRequest().getParameter("jurulelong.alamat2"));
            jl.setAlamat3(getContext().getRequest().getParameter("jurulelong.alamat3"));
            jl.setAlamat4(getContext().getRequest().getParameter("jurulelong.alamat4"));
            
            sytJuruLelong = sytJuruLelongDAO.findById(Long.parseLong(getContext().getRequest().getParameter("jurulelong.sytJuruLelong.idSytJlb")));
            jl.setSytJuruLelong(sytJuruLelong);
            
            KodJenisPengenalan kodP = kodJenisPengenalanDAO.findById(getContext().getRequest().getParameter("jurulelong.jenisPengenalan.kod"));
            jl.setJenisPengenalan(kodP);
            
            KodNegeri kodN = kodNegeriDAO.findById(getContext().getRequest().getParameter("jurulelong.negeri.kod"));
            
            jl.setNoPengenalan(getContext().getRequest().getParameter("jurulelong.noPengenalan"));
            jl.setPoskod(getContext().getRequest().getParameter("jurulelong.poskod"));
            jl.setNegeri(kodN);
            
            jl.setNoTelefon2(getContext().getRequest().getParameter("jurulelong.noTelefon2"));
            jl.setEmel(getContext().getRequest().getParameter("jurulelong.emel"));
            
            manager.saveOrUpdate(jl);
            addSimpleMessage("Maklumat Jurulelong Telah Berjaya Dikemaskini");
        }
        
        jurulelong = jl;
        showForm();
        
        listSytJuruLelong = sytJuruLelongDAO.findAll();
        return new JSP("lelong/perlantikkan_penolong_berlesen.jsp");
    }

    //editted by nur.aqilah
    //simpan jurulelong baru
    public Resolution simpan() throws Exception {
        LOG.info("--------ni untuk Simpan Maklumat Jurulelong Baru-------");
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
        jl.setIdPengguna(penggunaDAO.findById(getContext().getRequest().getParameter("jurulelong.noPengenalan")));
        jl.setInfoAudit(ia);
        jl.setNoPengenalan(getContext().getRequest().getParameter("jurulelong.noPengenalan"));
        SytJuruLelong syrt = sytJuruLelongDAO.findById(Long.parseLong(getContext().getRequest().getParameter("jurulelong.sytJuruLelong.idSytJlb")));

        //kalo xisi
        if (syrt == null) {
            jl.setSytJuruLelong(null);
        } else {
            jl.setSytJuruLelong(syrt);
        }
        
        jl.setAlamat1(getContext().getRequest().getParameter("jurulelong.alamat1"));
        jl.setAlamat2(getContext().getRequest().getParameter("jurulelong.alamat2"));
        jl.setAlamat3(getContext().getRequest().getParameter("jurulelong.alamat3"));
        jl.setAlamat4(getContext().getRequest().getParameter("jurulelong.alamat4"));
        KodNegeri kodN = kodNegeriDAO.findById(getContext().getRequest().getParameter("jurulelong.negeri.kod"));
        jl.setPoskod(getContext().getRequest().getParameter("jurulelong.poskod"));
        jl.setNegeri(kodN);
        jl.setNoTelefon1(getContext().getRequest().getParameter("jurulelong.noTelefon1"));
        jl.setNoTelefon2(getContext().getRequest().getParameter("jurulelong.noTelefon2"));
        jl.setCawangan(pengguna.getKodCawangan());
        jl.setThn_aktif(getContext().getRequest().getParameter("jurulelong.thn_aktif"));
        jl.setEmel(getContext().getRequest().getParameter("jurulelong.emel"));
        aktif = getContext().getRequest().getParameter("aktif");
        LOG.info(aktif);
        if (aktif.equals("Y")) {
            jl.setAktif('Y');
        } else {
            jl.setAktif('T');
        }
        
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
                mohonPengguna.setNoPengenalan(jl.getNoPengenalan());
                mohonPengguna.setEmail(jl.getEmel());
                mohonPengguna.setStatus(kodStatusPenggunaDAO.findById("SH"));
                mohonPengguna.setKodJabatan(kodJabatanDAO.findById("8"));
                mohonPengguna.setInfoAufit(ia);
                mohonPengguna.setJawatan("JuruLelong");
                mohonPengguna.setKodCawangan(kodCawanganDAO.findById(pengguna.getKodCawangan().getKod()));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < password_Length; i++) {
                    sb.append(goodChar[random.nextInt(goodChar.length)]);
                }
                String passwordGenerate = sb.toString();
                mohonPengguna.setPassword(passwordGenerate);
                service.mohonPengguna(mohonPengguna);
                Pengguna pgunaNew = lelongService.findPengguna(mohonPengguna.getIdPengguna());
//                pguna = lelongService.findPengguna(mohonPengguna.getIdPengguna());
                if (pgunaNew != null) {
                    pguna = pgunaNew;
//                    pguna.setIdPengguna(mohonPengguna.getIdPengguna());
                }else {
                   pguna.setIdPengguna(mohonPengguna.getIdPengguna());
                }
                
                pguna.setNama(jl.getNama());
                pguna.setPassword(passwordGenerate);
                pguna.setNegeri(jl.getNegeri());
                pguna.setAlamat1(jl.getAlamat1());
                pguna.setAlamat2(jl.getAlamat2());
                pguna.setAlamat3(jl.getAlamat3());
                pguna.setAlamat4(jl.getAlamat4());
                pguna.setPoskod(jl.getPoskod());
                pguna.setEmail(jl.getEmel());
                pguna.setInfoAudit(ia);
                pguna.setKodCawangan(kodCawanganDAO.findById(pengguna.getKodCawangan().getKod()));
                pguna.setJawatan("JuruLelong");
                pguna.setKodJabatan(kodJabatanDAO.findById("8"));
                pguna.setKodJawatan(kodJawatanDAO.findById("50"));
                pguna.setStatus(kodStatusPenggunaDAO.findById("A"));
                pguna.setNoPengenalan(jl.getNoPengenalan());
                pguna.setPerananUtama(kodPerananDAO.findById("JLB"));
                mohonPengguna.setPerananUtama(kodPerananDAO.findById("JLB"));
                pguna.setTahapSekuriti(klasifikasiDAO.findById(1));
                service.savePengguna(pguna);
                
                PenggunaPeranan penggunaPeranan = new PenggunaPeranan();
//                penggunaPeranan = service.findPPeqPguna(penggunaDAO.findById(getContext().getRequest().getParameter("jurulelong.noPengenalan")).getIdPengguna());
                penggunaPeranan.setPengguna(penggunaDAO.findById(getContext().getRequest().getParameter("jurulelong.noPengenalan")));
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
                String[] to = {mohonPengguna.getEmail()};
                mohonPengguna.setPassword(passwordGenerate);
                MailService mailService = new MailService();
                mailService.sendEmail(to, subject, msg, conf.getProperty("kodNegeri"));

//                 if (!service.newUser2(mohonPengguna, passwordGenerate)) {
//                        addSimpleError("Id Pengguna telah wujud. Gunakan Id Pengguna lain.");
//                 }
                jl.setIdPengguna(pguna);
                jl.setInfoAudit(ia);
                jl.setAktif('Y');
                manager.saveOrUpdate(jl);
                addSimpleMessage("Maklumat telah berjaya disimpan. Id Pengguna Dan Kata Laluan Akan Dihantar Ke Email Anda");
            } else {
                addSimpleError("Id Pengguna telah wujud");
                
            }
        } else {
            manager.saveOrUpdate(jl);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        //added by nur.aqilah
        //Check list of menuCapai either has JLB as kodPeranan or not
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            capaiList = lelongService.capaiList();
            LOG.info("size menuCapaian list:" + capaiList.size());
            
            if (capaiList.isEmpty()) { //if empty insert into Menu_Capaian table

                //MenuItem - (id item)
                MenuItem menuItem = new MenuItem();
                menuItem.setIdMenu(467);

                //KodPeranan - (kod peranan)
                KodPeranan kodPeranan = new KodPeranan();
                kodPeranan.setKod("JLB");
                
                LOG.info(kodPeranan.getKod());

                //MenuCapaian
                menuCapaian = new MenuCapaian();
                menuCapaian.setMenu(menuItem);
                menuCapaian.setPeranan(kodPeranan);
                menuCapaian.setInfoAudit(ia);
                
                manager.saveOrUpdate(menuCapaian);
            }
        }
        showForm();
        jurulelong = jl;
        listSytJuruLelong = sytJuruLelongDAO.findAll();
        return new JSP("lelong/perlantikkan_penolong_berlesen.jsp");
        
    }
    //added by nur.aqilah
    //reset

    public Resolution reset() {
        LOG.info("---nk reset---");
        jurulelong = new JuruLelong();
        jurulelong = null;
        sytJuruLelong = new SytJuruLelong();
        return new ForwardResolution("/WEB-INF/jsp/lelong/perlantikkan_penolong_berlesen.jsp");
    }
    
    public Resolution simpanSyarikat() {
        //create jurulelong baru
        LOG.info("--------ni untuk Simpan Maklumat Syarikat Jurulelong Baru-------");
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
        
        SytJuruLelong syt = lelongService.findByNoSykt(sytJuruLelong.getNoPengenalan());
        LOG.info("No Pengenalan: " + sytJuruLelong.getNoPengenalan());
        
        if (syt != null) {
            addSimpleError("Nombor Pengenalan Telah Wujud");
            return new JSP("lelong/perlantikkan_penolong_berlesen.jsp");
        }
        sj.setNoPengenalan(sytJuruLelong.getNoPengenalan());
        sj.setTahunAktif(getContext().getRequest().getParameter("sytJuruLelong.thn_aktif"));
        sj.setAlamat1(sytJuruLelong.getAlamat1());
        sj.setAlamat2(sytJuruLelong.getAlamat2());
        sj.setAlamat3(sytJuruLelong.getAlamat3());
        sj.setAlamat4(sytJuruLelong.getAlamat4());
        sj.setPoskod(sytJuruLelong.getPoskod());
        KodNegeri kodN = kodNegeriDAO.findById(sytJuruLelong.getNegeri().getKod());
        sj.setNegeri(kodN);
        sj.setNoTelefon1(sytJuruLelong.getNoTelefon1());
        sj.setNoTelefon2(sytJuruLelong.getNoTelefon2());
        sj.setAktif('Y');
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
        LOG.info("------------------ " + results);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new StreamingResolution("text/plain", results);
//        return new JSP("lelong/perlantikkan_penolong_berlesen.jsp");
    }
    
    public Resolution searchJurulelong() {
        LOG.info("::start cari penyerah::");
        findJurulelong();
        return showFormPopup();
    }
    
    public Resolution searchJurulelong2() {
        LOG.info("::start cari sykt penyerah::");
        findSytJuruLelong();
        return showFormPopup2();
    }
    
    public void findJurulelong() {
        LOG.info("findJurulelong");
        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
            senaraiJurulelong = lelongService.findAll();
        } else {
            senaraiJurulelong = lelongService.findPenyerahByParam(namaPenyerah, idPenyerah);
            LOG.info("ada");
        }
        if (senaraiJurulelong.size() <= 0) {
            senaraiJurulelong = lelongService.findJuruLelongByNoPengenalan(idPenyerah);
        }
        if (senaraiJurulelong.size() <= 0) {
            Pengguna pengguna = lelongService.findPengguna(idPenyerah);
            if (pengguna != null) {
                JuruLelong jl = new JuruLelong();
                
                jl.setNama(pengguna.getNama());
//                KodJenisPengenalan kod = kodJenisPengenalanDAO.findById(getContext().getRequest().getParameter("jurulelong.jenisPengenalan.kod"));
//            jl.setJenisPengenalan(pengguna.getpe);
                jl.setIdPengguna(pengguna);
                jl.setInfoAudit(pengguna.getInfoAudit());
                jl.setNoPengenalan(pengguna.getNoPengenalan());
//            SytJuruLelong syrt = sytJuruLelongDAO.findById(Long.parseLong(getContext().getRequest().getParameter("jurulelong.sytJuruLelong.idSytJlb")));

                //kalo xisi
//            if (syrt == null) {
//                jl.setSytJuruLelong(null);
//            } else {
//                jl.setSytJuruLelong(syrt);
//            }
                jl.setAlamat1(pengguna.getAlamat1());
                jl.setAlamat2(pengguna.getAlamat2());
                jl.setAlamat3(pengguna.getAlamat3());
                jl.setAlamat4(pengguna.getAlamat4());
//                KodNegeri kodN = kodNegeriDAO.findById(getContext().getRequest().getParameter("jurulelong.negeri.kod"));
                jl.setPoskod(pengguna.getPoskod());
                jl.setNegeri(pengguna.getNegeri());
                jl.setNoTelefon1(pengguna.getNoTelefon());
                jl.setNoTelefon2(pengguna.getNoTelefonBimbit());
                jl.setCawangan(pengguna.getKodCawangan());
//            jl.setThn_aktif(getContext().getRequest().getParameter("jurulelong.thn_aktif"));
                jl.setEmel(pengguna.getEmail());
                manager.saveOrUpdate(jl);
                senaraiJurulelong.add(jl);
//            aktif = getContext().getRequest().getParameter("aktif");
            }
        }
        LOG.info("list = " + senaraiJurulelong.size());
        if (senaraiJurulelong.isEmpty()) {
            LOG.info("empty");
            addSimpleError("Tiada data ditemui.");
            form = true;
        }
//        form = false;
    }
    
    public void findSytJuruLelong() {
        LOG.info("findSytJurulelong");
        LOG.info(form);
        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
            listSytJuruLelong = lelongService.findAllSyt();
        } else {
            listSytJuruLelong = lelongService.findSytPenyerahByParam(namaPenyerah, idPenyerah);
            LOG.info("ada");
        }
        LOG.info("list = " + listSytJuruLelong.size());
        if (listSytJuruLelong.isEmpty()) {
            LOG.info("empty");
            addSimpleError("Tiada data ditemui.");
            form = true;
        }
//        form = false;
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
            if (sj.getNoPengenalan() == null) {
                results = "";
            }
        } else {
            LOG.debug("Jurulelong not found");
        }
        //
        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
            listSytJuruLelong = lelongService.findAllSyt();
        } else {
            listSytJuruLelong = lelongService.findSytPenyerahByParam(namaPenyerah, idPenyerah);
            LOG.info("ada");
        }
        LOG.info("list = " + listSytJuruLelong.size());
        if (listSytJuruLelong.isEmpty()) {
            LOG.info("empty");
            addSimpleError("Tiada data ditemui.");
            form = true;
        }
        
        return new StreamingResolution("text/plain", results);
    }

    //editted by nur.aqilah
    public Resolution findByID() {
        LOG.debug("idJBL=" + idJBL);
        JuruLelong j = null;
        String results = "";
        try {
            LOG.info("masuk try dulu!!");
            j = jurulelongDAO.findById(Long.parseLong(idJBL));
        } catch (Exception ex) {
            LOG.debug(ex);
            return new StreamingResolution("text/plain", results);
        }
        if (j != null) {
            LOG.debug("JuruLelong found! idJlb=" + j.getIdJlb());
            LOG.info(j.getAktif());
            
            j.getIdJlb();
            
            if (j.getAktif() == 'T') {
                addSimpleMessage("Jurulelong ini tidak aktif");
                LOG.info("appear message");
            }
            StringBuilder s = new StringBuilder();
            String msg = "Jurulelong ini tidak aktif";
            
            if (j.getAktif() == 'T') {
                //LOG.info(s.append(msg));
                s.append(j.getJenisPengenalan() != null ? j.getJenisPengenalan().getKod() : "").append(DELIM).append(j.getNoPengenalan() != null ? j.getNoPengenalan() : "").append(DELIM).append(j.getNama() != null ? j.getNama() : "").append(DELIM).append(j.getAlamat1() != null ? j.getAlamat1() : "").append(DELIM).append(j.getAlamat2() != null ? j.getAlamat2() : "").append(DELIM).append(j.getAlamat3() != null ? j.getAlamat3() : "").append(DELIM).append(j.getAlamat4() != null ? j.getAlamat4() : "").append(DELIM).append(j.getPoskod() != null ? j.getPoskod() : "").append(DELIM).append(j.getNegeri() != null ? j.getNegeri().getKod() : "").append(DELIM).append(j.getCawangan() != null ? j.getCawangan().getKod() : "").append(DELIM).append(j.getNoTelefon1() != null ? j.getNoTelefon1() : "").append(DELIM).append(j.getNoTelefon2() != null ? j.getNoTelefon2() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getIdSytJlb() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getNoPengenalan() : "").append(DELIM).append(j.getIdJlb() != 0 ? j.getIdJlb() : "").append(DELIM).append(j.getThn_aktif() != null ? j.getThn_aktif() : "").append(DELIM).append(j.getAktif() != 0 ? j.getAktif() : "").append(DELIM).append(j.getEmel() != null ? j.getEmel() : "").append(DELIM).append(j.getIdJlb() != 0 ? j.getIdJlb() : "").append(DELIM).append(msg).append(DELIM);
            } else {
                s.append(j.getJenisPengenalan() != null ? j.getJenisPengenalan().getKod() : "").append(DELIM).append(j.getNoPengenalan() != null ? j.getNoPengenalan() : "").append(DELIM).append(j.getNama() != null ? j.getNama() : "").append(DELIM).append(j.getAlamat1() != null ? j.getAlamat1() : "").append(DELIM).append(j.getAlamat2() != null ? j.getAlamat2() : "").append(DELIM).append(j.getAlamat3() != null ? j.getAlamat3() : "").append(DELIM).append(j.getAlamat4() != null ? j.getAlamat4() : "").append(DELIM).append(j.getPoskod() != null ? j.getPoskod() : "").append(DELIM).append(j.getNegeri() != null ? j.getNegeri().getKod() : "").append(DELIM).append(j.getCawangan() != null ? j.getCawangan().getKod() : "").append(DELIM).append(j.getNoTelefon1() != null ? j.getNoTelefon1() : "").append(DELIM).append(j.getNoTelefon2() != null ? j.getNoTelefon2() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getIdSytJlb() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getNoPengenalan() : "").append(DELIM).append(j.getIdJlb() != 0 ? j.getIdJlb() : "").append(DELIM).append(j.getThn_aktif() != null ? j.getThn_aktif() : "").append(DELIM).append(j.getAktif() != 0 ? j.getAktif() : "").append(DELIM).append(j.getEmel() != null ? j.getEmel() : "").append(DELIM).append(j.getIdJlb() != 0 ? j.getIdJlb() : "");
            }
            
            results = s.toString();
            LOG.debug(s);
            
        } else {
            LOG.debug("Jurulelong not found");
        }
        cariSykt = true;
        return new StreamingResolution("text/plain", results);
    }

    //created by syazwan
    //for n9
    public Resolution findByIDN9() {
        LOG.debug("idJBL=" + idJBL);
        SytJuruLelong sj = null;
        String results = "";
        try {
            sj = sytJuruLelongDAO.findById(Long.parseLong(idJBL));
        } catch (Exception ex) {
            LOG.debug(ex);
            return new StreamingResolution("text/plain", results);
        }
        if (sj != null) {
            LOG.debug("SyktJuruLelong found! idJlb=" + sj.getIdSytJlb());
            LOG.info(sj.getAktif());
            
            sj.getIdSytJlb();
            
            if (sj.getAktif() == 'T') {
                addSimpleMessage("Syarikat Jurulelong ini tidak aktif");
                LOG.info("appear message");
            }
            StringBuilder s = new StringBuilder();
            String msg = "Jurulelong ini tidak aktif";
//            String msgY = "Jurulelong ini aktif";
            LOG.info("nama = " + sj.getNama());
            if (sj.getAktif() == 'T') {
                //                msg = "Jurulelong ini tidak aktif";
                //                LOG.info(s.append(msg));
//                s.append(sj.getJenisPengenalan() != null ? sj.getJenisPengenalan().getKod() : "").append(DELIM).append(sj.getNoPengenalan() != null ? sj.getNoPengenalan() : "").append(DELIM).append(sj.getNama() != null ? sj.getNama() : "").append(DELIM).append(sj.getAlamat1() != null ? sj.getAlamat1() : "").append(DELIM).append(sj.getAlamat2() != null ? sj.getAlamat2() : "").append(DELIM).append(sj.getAlamat3() != null ? sj.getAlamat3() : "").append(DELIM).append(sj.getAlamat4() != null ? sj.getAlamat4() : "").append(DELIM).append(sj.getPoskod() != null ? sj.getPoskod() : "").append(DELIM).append(sj.getNegeri() != null ? sj.getNegeri().getKod() : "").append(DELIM).append(sj.getCawangan() != null ? sj.getCawangan().getKod() : "").append(DELIM).append(sj.getNoTelefon1() != null ? sj.getNoTelefon1() : "").append(DELIM).append(sj.getNoTelefon2() != null ? sj.getNoTelefon2() : "").append(DELIM).append(DELIM).append(sj.getIdSytJlb() != 0 ? sj.getIdSytJlb() : "").append(DELIM).append(sj.getTahunAktif()!= null ? sj.getTahunAktif(): "").append(DELIM).append(sj.getAktif() != 0 ? sj.getAktif() : "").append(DELIM).append(DELIM).append(sj.getIdSytJlb() != 0 ? sj.getIdSytJlb() : "").append(DELIM).append(msg).append(DELIM);
                s.append(sj.getJenisPengenalan() != null ? sj.getJenisPengenalan().getKod() : "").append(DELIM).append(sj.getNoPengenalan() != null ? sj.getNoPengenalan() : "").append(DELIM).append(sj.getIdSytJlb() != 0 ? sj.getIdSytJlb() : "").append(DELIM).append(sj.getAktif() != 0 ? sj.getAktif() : "").append(DELIM).append(sj.getNama() != null ? sj.getNama() : "").append(DELIM).append(msg).append(DELIM);
            } else {
//                s.append(sj.getJenisPengenalan() != null ? sj.getJenisPengenalan().getKod() : "").append(DELIM).append(sj.getNoPengenalan() != null ? sj.getNoPengenalan() : "").append(DELIM).append(sj.getNama() != null ? sj.getNama() : "").append(DELIM).append(sj.getAlamat1() != null ? sj.getAlamat1() : "").append(DELIM).append(sj.getAlamat2() != null ? sj.getAlamat2() : "").append(DELIM).append(sj.getAlamat3() != null ? sj.getAlamat3() : "").append(DELIM).append(sj.getAlamat4() != null ? sj.getAlamat4() : "").append(DELIM).append(sj.getPoskod() != null ? sj.getPoskod() : "").append(DELIM).append(sj.getNegeri() != null ? sj.getNegeri().getKod() : "").append(DELIM).append(sj.getCawangan() != null ? sj.getCawangan().getKod() : "").append(DELIM).append(sj.getNoTelefon1() != null ? sj.getNoTelefon1() : "").append(DELIM).append(sj.getNoTelefon2() != null ? sj.getNoTelefon2() : "").append(DELIM).append(DELIM).append(sj.getIdSytJlb() != 0 ? sj.getIdSytJlb() : "").append(DELIM).append(sj.getTahunAktif()!= null ? sj.getTahunAktif(): "").append(DELIM).append(sj.getAktif() != 0 ? sj.getAktif() : "").append(DELIM).append(DELIM).append(sj.getIdSytJlb() != 0 ? sj.getIdSytJlb() : "");
                s.append(sj.getJenisPengenalan() != null ? sj.getJenisPengenalan().getKod() : "").append(DELIM).append(sj.getNoPengenalan() != null ? sj.getNoPengenalan() : "").append(DELIM).append(sj.getIdSytJlb() != 0 ? sj.getIdSytJlb() : "").append(DELIM).append(sj.getAktif() != 0 ? sj.getAktif() : "").append(DELIM).append(sj.getNama() != null ? sj.getNama() : "");
            }
            
            results = s.toString();
            LOG.debug(s);
            
        } else {
            LOG.debug("Jurulelong not found");
        }
        cariSykt = false;
        return new StreamingResolution("text/plain", results);
    }

    //editted by nur.aqilah    
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

            //added by nur.aqilah
            StringBuilder s = new StringBuilder();
            String msg = "Jurulelong ini tidak aktif";
            
            if (j.getAktif() == 'T') {
                LOG.info(s.append(msg));
                s.append(j.getJenisPengenalan() != null ? j.getJenisPengenalan().getKod() : "").append(DELIM).append(j.getNoPengenalan() != null ? j.getNoPengenalan() : "").append(DELIM).append(j.getNama() != null ? j.getNama() : "").append(DELIM).append(j.getAlamat1() != null ? j.getAlamat1() : "").append(DELIM).append(j.getAlamat2() != null ? j.getAlamat2() : "").append(DELIM).append(j.getAlamat3() != null ? j.getAlamat3() : "").append(DELIM).append(j.getAlamat4() != null ? j.getAlamat4() : "").append(DELIM).append(j.getPoskod() != null ? j.getPoskod() : "").append(DELIM).append(j.getNegeri() != null ? j.getNegeri().getKod() : "").append(DELIM).append(j.getCawangan() != null ? j.getCawangan().getKod() : "").append(DELIM).append(j.getNoTelefon1() != null ? j.getNoTelefon1() : "").append(DELIM).append(j.getNoTelefon2() != null ? j.getNoTelefon2() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getIdSytJlb() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getNoPengenalan() : "").append(DELIM).append(j.getIdJlb() != 0 ? j.getIdJlb() : "").append(DELIM).append(j.getThn_aktif() != null ? j.getThn_aktif() : "").append(DELIM).append(j.getAktif() != 0 ? j.getAktif() : "").append(DELIM).append(j.getEmel() != null ? j.getEmel() : "").append(DELIM).append(j.getIdJlb() != 0 ? j.getIdJlb() : "").append(DELIM).append(msg).append(DELIM);
                
            } else {
                s.append(j.getJenisPengenalan() != null ? j.getJenisPengenalan().getKod() : "").append(DELIM).append(j.getNoPengenalan() != null ? j.getNoPengenalan() : "").append(DELIM).append(j.getNama() != null ? j.getNama() : "").append(DELIM).append(j.getAlamat1() != null ? j.getAlamat1() : "").append(DELIM).append(j.getAlamat2() != null ? j.getAlamat2() : "").append(DELIM).append(j.getAlamat3() != null ? j.getAlamat3() : "").append(DELIM).append(j.getAlamat4() != null ? j.getAlamat4() : "").append(DELIM).append(j.getPoskod() != null ? j.getPoskod() : "").append(DELIM).append(j.getNegeri() != null ? j.getNegeri().getKod() : "").append(DELIM).append(j.getCawangan() != null ? j.getCawangan().getKod() : "").append(DELIM).append(j.getNoTelefon1() != null ? j.getNoTelefon1() : "").append(DELIM).append(j.getNoTelefon2() != null ? j.getNoTelefon2() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getIdSytJlb() : "").append(DELIM).append(j.getSytJuruLelong() != null ? j.getSytJuruLelong().getNoPengenalan() : "").append(DELIM).append(j.getIdJlb() != 0 ? j.getIdJlb() : "").append(DELIM).append(j.getThn_aktif() != null ? j.getThn_aktif() : "").append(DELIM).append(j.getAktif() != 0 ? j.getAktif() : "").append(DELIM).append(j.getEmel() != null ? j.getEmel() : "").append(DELIM).append(j.getIdJlb() != 0 ? j.getIdJlb() : "");
            }
            
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

    //check n9 duplicate ic
    public Resolution doCheckIC() {
        LOG.info("------check duplicate ic---------");
        
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            melaka = false;
            
            String noPengenalan = getContext().getRequest().getParameter("icno");
            LOG.info("*****AjaxValidateIC.doCheckIC :" + getContext().getRequest().getParameter("icno"));
            String results = "0";
            
            SytJuruLelong syt = lelongService.findByNoSykt(noPengenalan);
            
            if (syt != null) {
                LOG.info("id Syt Jurulelong :" + syt.getIdSytJlb());
                LOG.info("ic Syt jurulelong :" + syt);
                //            noPengenalan = syt.getNoPengenalan();
                LOG.info("ic : " + noPengenalan);
                results = "1";
            }
            LOG.debug("results : " + results);
            return new StreamingResolution("text/plain", results);
        } else if ("04".equals(conf.getProperty("kodNegeri"))) {
            melaka = true;
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
    
    public boolean isCariSykt() {
        return cariSykt;
    }
    
    public void setCariSykt(boolean cariSykt) {
        this.cariSykt = cariSykt;
    }

    /**
     * @return the btn
     */
    public boolean isBtn() {
        return btn;
    }

    /**
     * @param btn the btn to set
     */
    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    /**
     * @return the btn2
     */
    public boolean isBtn2() {
        return btn2;
    }

    /**
     * @param btn2 the btn2 to set
     */
    public void setBtn2(boolean btn2) {
        this.btn2 = btn2;
    }
    
    public boolean isButton() {
        return button;
    }
    
    public void setButton(boolean button) {
        this.button = button;
    }

    /**
     * @return the aktif
     */
    public String getAktif() {
        return aktif;
    }

    /**
     * @param aktif the aktif to set
     */
    public void setAktif(String aktif) {
        this.aktif = aktif;
    }
    
    public String getTahunAktif() {
        return tahunAktif;
    }

    /**
     * @param aktif the tahunaktif to set
     */
    public void setTahunAktif(String tahunAktif) {
        this.tahunAktif = tahunAktif;
    }

    /**
     *
     * /
     *
     **
     * @return the idCapaian
     */
    public int getIdCapaian() {
        return idCapaian;
    }

    /**
     * @param idCapaian the idCapaian to set
     */
    public void setIdCapaian(int idCapaian) {
        this.idCapaian = idCapaian;
    }

    /**
     * @return the capaiList
     */
    public List<MenuCapaian> getCapaiList() {
        return capaiList;
    }

    /**
     * @param capaiList the capaiList to set
     */
    public void setCapaiList(List<MenuCapaian> capaiList) {
        this.capaiList = capaiList;
    }

    /**
     * @return the showMess
     */
    public boolean isShowMess() {
        return showMess;
    }

    /**
     * @param showMess the showMess to set
     */
    public void setShowMess(boolean showMess) {
        this.showMess = showMess;
    }
    
    public String getNoSykt() {
        return noSykt;
    }
    
    public void setNoSykt(String noSykt) {
        this.noSykt = noSykt;
    }
    
    public String getIdSyarikat() {
        return idSyarikat;
    }
    
    public void setIdSyarikat(String idSyarikat) {
        this.idSyarikat = idSyarikat;
    }
    
    public boolean isAlertM() {
        return alertM;
    }
    
    public void setAlertM(boolean alertM) {
        this.alertM = alertM;
    }
    
    public String getIdjlb() {
        return idjlb;
    }
    
    public void setIdjlb(String idjlb) {
        this.idjlb = idjlb;
    }
}
