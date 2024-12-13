/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.service.common.HakmilikService;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.SyerValidationService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.model.PermohonanPihakKemaskini;
import etanah.service.daftar.PermohonanPihakKemaskiniService;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanPihakKemaskiniDAO;
import etanah.model.KodBangsa;
import etanah.model.KodJenisPihakBerkepentingan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.RegService;
import etanah.service.common.PermohonanAtasPihakBerkepentinganService;
import etanah.service.common.CawanganService;
import etanah.view.ListUtil;
import etanah.view.daftar.PerintahJualPihakBerkepentingan;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import net.sourceforge.stripes.action.HttpCache;
import org.apache.commons.math.fraction.Fraction;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakPengarahDAO;
import etanah.model.Alamat;
import etanah.model.KodJenisPengenalan;
import etanah.model.PihakAlamatTamb;
import etanah.model.PihakPengarah;
import etanah.service.PembangunanService;
import java.math.BigDecimal;

/**
 *
 * @author Hazwan
 */
@UrlBinding("/utiliti/utilitiPemohonUtama")
public class utilitiPemohonUtamaActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(utilitiPemohonUtamaActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakService;
    @Inject
    HakmilikService hakmilikManager;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    SyerValidationService syerService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanPihakKemaskiniService mohonPihakKemaskiniService;
    @Inject
    PermohonanPihakKemaskiniDAO mohonPihakKemaskiniDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    RegService regService;
    @Inject
    PermohonanAtasPihakBerkepentinganService mapService;
    @Inject
    CawanganService cawanganService;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    ListUtil listUtil;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    PihakPengarahDAO pihakPengarahDAO;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    private List<PermohonanPihakKemaskini> mohonPihakKemaskiniList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> othersPihakList;
    private List<PermohonanAtasPihakBerkepentingan> mapList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList2;
    private List<PermohonanPihak> senaraiPemohonBerangkai;
    private List<KodBangsa> senaraiKodBangsa;
    private List<KodJenisPihakBerkepentingan> senaraiKodPenerima;
    private Pihak pihak;
    private PihakCawangan pihakCawangan;
    private PihakPengarah pihakPengarah;
    private PermohonanPihak permohonanPihak;
    private Pemohon pemohon;
    private List<Pemohon> pemohonList;
    private List<Pemohon> pemohonList2;
    private List<Pemohon> senaraiPemohon4Penyerah;
    private List<Pemohon> pemohonListIndividu;
    private List<Pemohon> pemohonListCompany;
    private List<Pemohon> allpemohonList;
    private List<Pemohon> pemohonListIndividu2;
    private List<Pemohon> pemohonListCompany2;
    private List<PermohonanPihak> mohonPihakList;
    private List<PermohonanPihak> mohonPihakPemegangAmanah;
    private List<PihakCawangan> cawanganList;
    private String idPermohonan;
    private Permohonan p;
    private Pengguna pguna;
    private String[] syer1;
    private String[] syer2;
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    private PermohonanPihakKemaskini mohonPihakKemaskini;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String alamat5;
    private String alamat6;
    private String nama;
    private String nokp;
    String tarikhLahir;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean tambahCawFlag;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanAtasPerserahanService mohonAtasSerahService;
    private String idCawangan;
    private String kodNegeri;
    private String namaNegeriBaru;
    private String namaNegeriLama;
    private String jenisPihak;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private boolean isLayak = true;
    private boolean tambahPengarahFlag;
    private List<PihakPengarah> pengarahList;
    private Character statusFlag;
    private PihakAlamatTamb pihakAlamatTamb;
    private String checkAlamat;
    private String poskod;
    private String suratAlamat1;
    private String suratAlamat2;
    private String suratAlamat3;
    private String suratAlamat4;
    private String suratPoskod;
    private String suratNegeri;
    private String negeri;
    private List<HakmilikPihakBerkepentingan> senaraiKeempunyaan;
    private String kodBangsa;
    private String noTel1;
    private String noTel2;
    private String radio1;
    private String radio2;
    private String pihakUtama;
    private Pemohon findPihakUtama;
    private Permohonan permohonan;
    private String noPengenalan;
    private String kodP;
    private String idPihak;
    private String idPemohon;
    private Boolean idMohonShow = false;

    @DefaultHandler
    public Resolution showForm() {
        idMohonShow = true;
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiPemohonUtama/utilitiPemohonUtama.jsp");
    }

    public Resolution reset() {
        idMohonShow = true;
        permohonan = new Permohonan();
        findPihakUtama = new Pemohon();
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiPemohonUtama/utilitiPemohonUtama.jsp");
    }

    public Resolution checkPermohonan() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String path = "";

        if (idPermohonan != null) {
            List<Pemohon> pem = pembangunanServ.findPemohonByIdPermohonan(idPermohonan);

            if (pem.size() > 0) {
                check();
                addSimpleMessage("Berikut Adalah Senarai Pemohon Bagi Id Permohonan = " + idPermohonan + ". Sila Pastikan Pemohon Utama Telah Dipilih ");
                path = "/WEB-INF/jsp/pembangunan/utiliti/utilitiPemohonUtama/utilitiPemohonUtama.jsp";
            } else {
                idMohonShow = true;
                addSimpleMessage("Id Permohonan = " + idPermohonan + ". Wujud Tetapi Malangnya Data Pemohon Tidak Wujud !!!");
                path = "/WEB-INF/jsp/pembangunan/utiliti/utilitiPemohonUtama/utilitiPemohonUtama.jsp";
            }
        } else {
            idMohonShow = true;
            addSimpleMessage("Id Permohonan = " + idPermohonan + " Tidak Wujud !!!");
            path = "/WEB-INF/jsp/pembangunan/utiliti/utilitiPemohonUtama/utilitiPemohonUtama.jsp";
        }
        return new ForwardResolution(path);
    }

    public void check() {

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");

        if (permohonan.getKodUrusan().getKod().equals("PPT") && permohonan.getPermohonanSebelum() != null) {
            LOG.info("######### PPT #########");
            allpemohonList = pembangunanServ.findSenaraiPemohon(permohonan.getPermohonanSebelum().getIdPermohonan());
        } else if (permohonan.getKodUrusan().getKod().equals("RPP") || (permohonan.getKodUrusan().getKod().equals("RPS")) || (permohonan.getKodUrusan().getKod().equals("RLTB")) && (kodNegeri.equals("05"))) {
            if (p.getPermohonanSebelum() != null) {
                allpemohonList = pembangunanServ.findSenaraiPemohon(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                allpemohonList = pembangunanServ.findSenaraiPemohon(idPermohonan);
            }
        } else {
            allpemohonList = pembangunanServ.findSenaraiPemohon(idPermohonan);
        }

        pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
        if (pemohonList.size() > 0) {
            for (Pemohon p1 : pemohonList) {
                if (p1.getSurat() != null) {
                    radio1 = p1.getSurat().toString();
                }
                if (p1.getNama() != null) {
                    radio2 = p1.getNama();
                }
            }
        }

        if (pihak != null) {
            System.out.println("--HERE 2 EDIT PEMOHON--");
            nama = pihak.getNama();
            if (pihak.getJenisPengenalan() != null) {
                kodP = pihak.getJenisPengenalan().getKod();
            }
            noPengenalan = pihak.getNoPengenalan();
            alamat1 = pihak.getAlamat1();
            alamat2 = pihak.getAlamat2();
            alamat3 = pihak.getAlamat3();
            alamat4 = pihak.getAlamat4();
            poskod = pihak.getPoskod();
            if (pihak.getNegeri() != null) {
                negeri = pihak.getNegeri().getKod();
            }
            noTel1 = pihak.getNoTelefon1();
        }


    }

    public Resolution showEditPemohon() {
        idPihak = getContext().getRequest().getParameter("idPihak");
        idPemohon = getContext().getRequest().getParameter("idPemohon");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pemohonDAO.findById(Long.valueOf(idPemohon));
        check();
        return new JSP("pembangunan/utiliti/utilitiPemohonUtama/utilitiPemohonUtamaEdit.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPihak() throws Exception {

        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        idPihak = getContext().getRequest().getParameter("idPihak");
        idPemohon = getContext().getRequest().getParameter("idPemohon");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("ID PIHAK - " + idPihak);
        System.out.println("ID PEMOHON - " + idPemohon);

//        alamat1 = getContext().getRequest().getParameter("alamat1");
//        alamat2 = getContext().getRequest().getParameter("alamat2");
//        alamat3 = getContext().getRequest().getParameter("alamat3");
//        alamat4 = getContext().getRequest().getParameter("alamat4");
//        poskod = getContext().getRequest().getParameter("poskod");
//        negeri = getContext().getRequest().getParameter("negeri");
//        noPengenalan = getContext().getRequest().getParameter("noPengenalan");
//        kodP = getContext().getRequest().getParameter("kodP");
//        noTel1 = getContext().getRequest().getParameter("noTel1");

        Pihak pihakTemp = pembangunanServ.getIdPihakDetails(idPihak);
        pihakTemp.setAlamat1(alamat1);
        pihakTemp.setAlamat2(alamat2);
        pihakTemp.setAlamat3(alamat3);
        pihakTemp.setAlamat4(alamat4);
        pihakTemp.setPoskod(poskod);
        pihakTemp.setNoTelefon1(noTel1);

        if (negeri != null) {
            LOG.info(":: if kodNegeri ::" + negeri);
            KodNegeri kn = kodNegeriDAO.findById(negeri);
            pihakTemp.setNegeri(kn);
        } else {
            LOG.info(":: else kodNegeri ::" + negeri);
            pihakTemp.setNegeri(null);
        }

        pihakTemp.setNoPengenalan(noPengenalan);

        if (kodP != null) {
            pihakTemp.setJenisPengenalan(kodJenisPengenalanDAO.findById(kodP));
        }

        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        pihakTemp.setInfoAudit(infoAudit);
        pembangunanServ.simpanPihak(pihakTemp);

        Pemohon pemo = pembangunanServ.findPemohonByIdPemohon(idPemohon);
        if (pemo != null) {

            if (pemo.getNama() != null) {
                pemo.setNoPengenalan(pihakTemp.getNoPengenalan());
                if (pihakTemp.getJenisPengenalan() != null) {
                    pemo.setJenisPengenalan(pihakTemp.getJenisPengenalan());
                }

                Alamat address = new Alamat();
                address.setAlamat1(pihakTemp.getAlamat1());
                address.setAlamat2(pihakTemp.getAlamat2());
                address.setAlamat3(pihakTemp.getAlamat3());
                address.setAlamat4(pihakTemp.getAlamat4());
                address.setPoskod(pihakTemp.getPoskod());
                if (pihakTemp.getNegeri() != null) {
                    address.setNegeri(pihakTemp.getNegeri());
                }
                pemo.setAlamat(address);

                infoAudit.setDiKemaskiniOleh(pg);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                pemo.setInfoAudit(infoAudit);
                pembangunanServ.simpanPemohon(pemo);
            }
        }

        check();
        addSimpleMessage("Maklumat Pemohon Telah Disimpan");
        return new JSP("pembangunan/utiliti/utilitiPemohonUtama/utilitiPemohonUtamaEdit.jsp").addParameter("popup", "true");
    }

    public Resolution deletePemohon() throws Exception {
        idPemohon = getContext().getRequest().getParameter("idPemohon");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        if (idPemohon != null) {
            Pemohon pmohon = pemohonService.findById(idPemohon);
            if (pmohon != null) {
                pemohonService.delete(pmohon);
                //pembangunanServ.deletePemohnonByIdPihakIdMohon(pmohon,idPermohonan);
                //pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
        check();
        addSimpleMessage("Maklumat Pemohon Tersebut Telah Dibatalkan");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiPemohonUtama/utilitiPemohonUtama.jsp");
    }

    public Resolution simpanPenyeraheqPemohon() throws Exception {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        radio1 = getContext().getRequest().getParameter("radio1");

        if (idPermohonan != null) {
            LOG.debug("Here !!!!");
            senaraiPemohon4Penyerah = pembangunanServ.findPemohonByIdPermohonan(idPermohonan);
            if (senaraiPemohon4Penyerah.size() > 0) {
                for (Pemohon pp : senaraiPemohon4Penyerah) {
                    pp.setSurat(radio1.charAt(0));
                    pemohonService.saveOrUpdate(pp);
                }
                LOG.debug("Done !!!!");
            }
        } else {
            LOG.debug("x masuk boh");
        }

        check();
        addSimpleMessage("Maklumat Pemohon Dan Penyerah Telah Disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiPemohonUtama/utilitiPemohonUtama.jsp");

    }

    public Resolution simpanPemohonUtama() throws Exception {

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        radio1 = getContext().getRequest().getParameter("radio1");
        pihakUtama = getContext().getRequest().getParameter("idPihak");
        Pihak pi = pihakService.findById(pihakUtama);
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pguna);
        ia.setTarikhKemaskini(new java.util.Date());

        if (idPermohonan != null) {
            LOG.debug("Here !!!!");

            //deleteSemuaNama
            List<Pemohon> pem = pembangunanServ.findPemohonByIdPermohonan(idPermohonan);
            for (Pemohon pp1 : pem) {
                pp1.setInfoAudit(ia);
                pp1.setNama(null);
                pp1.setAlamat(null);
                pp1.setJenisPengenalan(null);
                pp1.setNoPengenalan(null);
                pemohonService.saveOrUpdate(pp1);
            }

            //addNamaUtama
            List<Pemohon> pe = pembangunanServ.findPemohonByIdPihak(idPermohonan, Long.valueOf(pihakUtama));
            for (Pemohon pp : pe) {
                pp.setInfoAudit(ia);
                pp.setNama(pi.getNama());
                if (pi.getNoPengenalan() != null) {
                    pp.setNoPengenalan(pi.getNoPengenalan());
                }
                pp.setJenisPengenalan(pi.getJenisPengenalan());
                Alamat alamat = new Alamat();
                alamat.setAlamat1(pi.getAlamat1());
                alamat.setAlamat2(pi.getAlamat2());
                alamat.setAlamat3(pi.getAlamat3());
                alamat.setAlamat4(pi.getAlamat4());
                alamat.setPoskod(pi.getPoskod());
                if (pi.getNegeri() != null) {
                    alamat.setNegeri(pi.getNegeri());
                }
                pp.setAlamat(alamat);
                pemohonService.saveOrUpdate(pp);
            }
        } else {
            LOG.debug("x masuk boh");
        }
        check();
        addSimpleMessage("Maklumat Pemohon Utama Telah Disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiPemohonUtama/utilitiPemohonUtama.jsp");
    }

    public String getNamaNegeriLama() {
        return namaNegeriLama;
    }

    public void setNamaNegeriLama(String namaNegeriLama) {
        this.namaNegeriLama = namaNegeriLama;
    }

    public String getNamaNegeriBaru() {
        return namaNegeriBaru;
    }

    public void setNamaNegeriBaru(String namaNegeriBaru) {
        this.namaNegeriBaru = namaNegeriBaru;
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

    public String getAlamat5() {
        return alamat5;
    }

    public void setAlamat5(String alamat5) {
        this.alamat5 = alamat5;
    }

    public String getAlamat6() {
        return alamat6;
    }

    public void setAlamat6(String alamat6) {
        this.alamat6 = alamat6;
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

    public PermohonanPihakKemaskini getMohonPihakKemaskini() {
        return mohonPihakKemaskini;
    }

    public void setMohonPihakKemaskini(PermohonanPihakKemaskini mohonPihakKemaskini) {
        this.mohonPihakKemaskini = mohonPihakKemaskini;
    }

    public List<PermohonanPihakKemaskini> getMohonPihakKemaskiniList() {
        return mohonPihakKemaskiniList;
    }

    public void setMohonPihakKemaskiniList(List<PermohonanPihakKemaskini> mohonPihakKemaskiniList) {
        this.mohonPihakKemaskiniList = mohonPihakKemaskiniList;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public String[] getSyer1() {
        return syer1;
    }

    public void setSyer1(String[] syer1) {
        this.syer1 = syer1;
    }

    public String[] getSyer2() {
        return syer2;
    }

    public void setSyer2(String[] syer2) {
        this.syer2 = syer2;
    }

    public boolean isCariFlag() {
        return cariFlag;
    }

    public void setCariFlag(boolean cariFlag) {
        this.cariFlag = cariFlag;
    }

    public boolean isTiadaDataFlag() {
        return tiadaDataFlag;
    }

    public void setTiadaDataFlag(boolean tiadaDataFlag) {
        this.tiadaDataFlag = tiadaDataFlag;
    }

    public List<PermohonanAtasPihakBerkepentingan> getMapList() {
        return mapList;
    }

    public void setMapList(List<PermohonanAtasPihakBerkepentingan> mapList) {
        this.mapList = mapList;
    }

    public boolean isTambahCawFlag() {
        return tambahCawFlag;
    }

    public void setTambahCawFlag(boolean tambahCawFlag) {
        this.tambahCawFlag = tambahCawFlag;
    }

    public PihakCawangan getPihakCawangan() {
        return pihakCawangan;
    }

    public void setPihakCawangan(PihakCawangan pihakCawangan) {
        this.pihakCawangan = pihakCawangan;
    }

    public List<HakmilikPihakBerkepentingan> getOthersPihakList() {
        return othersPihakList;
    }

    public void setOthersPihakList(List<HakmilikPihakBerkepentingan> othersPihakList) {
        this.othersPihakList = othersPihakList;
    }

    public String getIdCawangan() {
        return idCawangan;
    }

    public void setIdCawangan(String idCawangan) {
        this.idCawangan = idCawangan;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList2() {
        return pihakKepentinganList2;
    }

    public void setPihakKepentinganList2(List<HakmilikPihakBerkepentingan> pihakKepentinganList2) {
        this.pihakKepentinganList2 = pihakKepentinganList2;
    }

    public List<PermohonanPihak> getSenaraiPemohonBerangkai() {
        return senaraiPemohonBerangkai;
    }

    public void setSenaraiPemohonBerangkai(List<PermohonanPihak> senaraiPemohonBerangkai) {
        this.senaraiPemohonBerangkai = senaraiPemohonBerangkai;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public List<PermohonanPihak> getMohonPihakPemegangAmanah() {
        return mohonPihakPemegangAmanah;
    }

    public void setMohonPihakPemegangAmanah(List<PermohonanPihak> mohonPihakPemegangAmanah) {
        this.mohonPihakPemegangAmanah = mohonPihakPemegangAmanah;
    }

    public String getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(String tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public List<KodJenisPihakBerkepentingan> getSenaraiKodPenerima() {
        return senaraiKodPenerima;
    }

    public void setSenaraiKodPenerima(List<KodJenisPihakBerkepentingan> senaraiKodPenerima) {
        this.senaraiKodPenerima = senaraiKodPenerima;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getJenisPihak() {
        return jenisPihak;
    }

    public void setJenisPihak(String jenisPihak) {
        this.jenisPihak = jenisPihak;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList2() {
        return hakmilikPermohonanList2;
    }

    public void setHakmilikPermohonanList2(List<HakmilikPermohonan> hakmilikPermohonanList2) {
        this.hakmilikPermohonanList2 = hakmilikPermohonanList2;
    }

    public boolean isTambahPengarahFlag() {
        return tambahPengarahFlag;
    }

    public void setTambahPengarahFlag(boolean tambahPengarahFlag) {
        this.tambahPengarahFlag = tambahPengarahFlag;
    }

    public PihakPengarah getPihakPengarah() {
        return pihakPengarah;
    }

    public void setPihakPengarah(PihakPengarah pihakPengarah) {
        this.pihakPengarah = pihakPengarah;
    }

    public List<PihakPengarah> getPengarahList() {
        return pengarahList;
    }

    public void setPengarahList(List<PihakPengarah> pengarahList) {
        this.pengarahList = pengarahList;
    }

    public List<Pemohon> getPemohonListCompany() {
        return pemohonListCompany;
    }

    public void setPemohonListCompany(List<Pemohon> pemohonListCompany) {
        this.pemohonListCompany = pemohonListCompany;
    }

    public List<Pemohon> getPemohonListIndividu() {
        return pemohonListIndividu;
    }

    public void setPemohonListIndividu(List<Pemohon> pemohonListIndividu) {
        this.pemohonListIndividu = pemohonListIndividu;
    }

    public List<Pemohon> getAllpemohonList() {
        return allpemohonList;
    }

    public void setAllpemohonList(List<Pemohon> allpemohonList) {
        this.allpemohonList = allpemohonList;
    }

    public Character getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Character statusFlag) {
        this.statusFlag = statusFlag;
    }

    public PihakAlamatTamb getPihakAlamatTamb() {
        return pihakAlamatTamb;
    }

    public void setPihakAlamatTamb(PihakAlamatTamb pihakAlamatTamb) {
        this.pihakAlamatTamb = pihakAlamatTamb;
    }

    public String getCheckAlamat() {
        return checkAlamat;
    }

    public void setCheckAlamat(String checkAlamat) {
        this.checkAlamat = checkAlamat;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getSuratAlamat1() {
        return suratAlamat1;
    }

    public void setSuratAlamat1(String suratAlamat1) {
        this.suratAlamat1 = suratAlamat1;
    }

    public String getSuratAlamat2() {
        return suratAlamat2;
    }

    public void setSuratAlamat2(String suratAlamat2) {
        this.suratAlamat2 = suratAlamat2;
    }

    public String getSuratAlamat3() {
        return suratAlamat3;
    }

    public void setSuratAlamat3(String suratAlamat3) {
        this.suratAlamat3 = suratAlamat3;
    }

    public String getSuratAlamat4() {
        return suratAlamat4;
    }

    public void setSuratAlamat4(String suratAlamat4) {
        this.suratAlamat4 = suratAlamat4;
    }

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public String getSuratPoskod() {
        return suratPoskod;
    }

    public void setSuratPoskod(String suratPoskod) {
        this.suratPoskod = suratPoskod;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiKeempunyaan() {
        return senaraiKeempunyaan;
    }

    public void setSenaraiKeempunyaan(List<HakmilikPihakBerkepentingan> senaraiKeempunyaan) {
        this.senaraiKeempunyaan = senaraiKeempunyaan;
    }

    public String getNoTel1() {
        return noTel1;
    }

    public void setNoTel1(String noTel1) {
        this.noTel1 = noTel1;
    }

    public String getNoTel2() {
        return noTel2;
    }

    public void setNoTel2(String noTel2) {
        this.noTel2 = noTel2;
    }

    public String getRadio1() {
        return radio1;
    }

    public void setRadio1(String radio1) {
        this.radio1 = radio1;
    }

    public List<Pemohon> getSenaraiPemohon4Penyerah() {
        return senaraiPemohon4Penyerah;
    }

    public void setSenaraiPemohon4Penyerah(List<Pemohon> senaraiPemohon4Penyerah) {
        this.senaraiPemohon4Penyerah = senaraiPemohon4Penyerah;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getPihakUtama() {
        return pihakUtama;
    }

    public void setPihakUtama(String pihakUtama) {
        this.pihakUtama = pihakUtama;
    }

    public Pemohon getFindPihakUtama() {
        return findPihakUtama;
    }

    public void setFindPihakUtama(Pemohon findPihakUtama) {
        this.findPihakUtama = findPihakUtama;
    }

    public String getRadio2() {
        return radio2;
    }

    public void setRadio2(String radio2) {
        this.radio2 = radio2;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getKodP() {
        return kodP;
    }

    public void setKodP(String kodP) {
        this.kodP = kodP;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public Boolean getIdMohonShow() {
        return idMohonShow;
    }

    public void setIdMohonShow(Boolean idMohonShow) {
        this.idMohonShow = idMohonShow;
    }
}
