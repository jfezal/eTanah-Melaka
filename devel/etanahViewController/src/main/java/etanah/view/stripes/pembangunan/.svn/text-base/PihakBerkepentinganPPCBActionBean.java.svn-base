package etanah.view.stripes.pembangunan;

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
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakPengarahDAO;
import etanah.model.PihakAlamatTamb;
import etanah.model.PihakPengarah;
import etanah.service.PembangunanService;
import java.math.BigDecimal;

/**
 *
 * @author md.nurfikri
 */
@HttpCache(allow = false)
@UrlBinding("/pembangunan/pihak_berkepentinganPPCB")
public class PihakBerkepentinganPPCBActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PihakBerkepentinganPPCBActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
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
    PermohonanDAO permohonanDAO;
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
    private List<HakmilikPihakBerkepentingan> listphk;
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
    private List<Pemohon> pemohonListIndividu;
    private List<Pemohon> pemohonListCompany;
    private List<Pemohon> pemohonListIndividu2;
    private List<Pemohon> pemohonListCompany2;
    private List<PermohonanPihak> mohonPihakPemegangAmanah;
    private List<PermohonanPihak> mohonPihakList;
    private List<HakmilikPermohonan> listPihak;
    private List<PihakCawangan> cawanganList;
    private String idPermohonan;
    private Permohonan p;
    private Pengguna pguna;
    private String[] syer1;
    private String[] syer2;
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    private PermohonanPihakKemaskini mohonPihakKemaskini;
    private String jenisPengenalan;
    private String jenisPengenalan2;
    private String nomborPengenalan;
    private String suratNegeri1;
    private String negeri1;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String alamat5;
    private String alamat6;
    private String nama;
    private String nokp;
    private int sizelistphk;
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
    private String radio1;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/dev_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution getSenaraiHakmilikKepentinganIsmen() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution getSenaraiHakmilikKepentingan() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (idPermohonan != null) {
//            p = permohonanService.findById(idPermohonan);
            if (p != null && p.getPermohonanSebelum() != null) {
                Permohonan t = p.getPermohonanSebelum();
                if (t != null) {
                    getContext().getRequest().setAttribute("copy", Boolean.TRUE);
                }
            }
            //fikri :: urusan berangkai (25012010)
            if (p != null) {
                if (StringUtils.isNotBlank(p.getIdKumpulan())) {
                    //seandainya urusan berangkai, dan pemohonan belum lg didaftarkan
                    //kena ambil pihak dari pemohon.
                    //bugs : jika hanya ada satu pemohon, ada 2 tuan tanah, tuan tanah yg tidak memohon should keluarkan juga
                    if (p.getPermohonanSebelum() != null) {
                        Permohonan permohonanSebelum = p.getPermohonanSebelum();
//                        String idPermohonanSblm = permohonanSebelum.getIdPermohonan();
                        String idHakmilikSblm = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
//                        senaraiPemohonBerangkai = permohonanPihakService.getSenaraiPmohonPihak(idPermohonanSblm);
//                        senaraiPemohonBerangkai = permohonanSebelum.getSenaraiPihak();
                        senaraiPemohonBerangkai = new ArrayList<PermohonanPihak>();
                        List<PermohonanPihak> senarai_tmp = permohonanPihakService.getSenaraiTuanTanahForBerangkai(p.getIdKumpulan(), idHakmilikSblm, null, false, false, p.getKumpulanNo());

                        for (PermohonanPihak pp : senarai_tmp) {
                            if (pp.getJenis().getKod().equals("PM") || pp.getJenis().getKod().equals("PA")
                                    || pp.getJenis().getKod().equals("PPM")) {
                                senaraiPemohonBerangkai.add(pp);
                            }
                        }

                        List<HakmilikPihakBerkepentingan> senaraiPihak =
                                hakmilikPihakKepentinganService.senaraiPBtidakBerkaitan(idHakmilikSblm, p.getIdKumpulan(), p.getIdPermohonan(), false, false);

//                        if(senaraiPemohonBerangkai == null){
//                            senaraiPemohonBerangkai = new ArrayList<PermohonanPihak>();
//                        }

                        for (HakmilikPihakBerkepentingan hpb : senaraiPihak) {
                            PermohonanPihak pp = new PermohonanPihak();
                            pp.setPihak(hpb.getPihak());
                            pp.setSyerPembilang(hpb.getSyerPembilang());
                            pp.setSyerPenyebut(hpb.getSyerPenyebut());
                            senaraiPemohonBerangkai.add(pp);
                        }
                        if (p.getSenaraiHakmilik().size() > 1 && (!p.getKodUrusan().getKod().startsWith("IS"))) {
                            pihakValidateBerangkai(); //validate for multiple hakmilik
                        }
                        if (senaraiPemohonBerangkai.size() > 0) {
                            getContext().getRequest().setAttribute("berangkai", Boolean.TRUE);
                        }
                    }
                }

                //fikri 14/04/2010
                if (p.getKodUrusan().getKod().equals("PH30B")) {
                    pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
                    List<HakmilikPihakBerkepentingan> senaraiPihakTemp = new ArrayList<HakmilikPihakBerkepentingan>();
                    if (p.getSenaraiHakmilik() != null && p.getSenaraiHakmilik().size() > 0) {
                        for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                            Hakmilik h = hp.getHakmilik();
                            if (h == null) {
                                continue;
                            }
                            senaraiPihakTemp = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), null);

                            for (HakmilikPihakBerkepentingan hpk : senaraiPihakTemp) {
                                if (hpk == null) {
                                    continue;
                                }
                                pihakKepentinganList.add(hpk);
                            }
                        }
                    }
                }
                if (p.getSenaraiHakmilik().size() > 1 && (!p.getKodUrusan().getKod().startsWith("IS"))) {
                    pihakValidate(); //validate for multiple hakmilik
                }
            }
        }

//        if(p.getSenaraiHakmilik().size() > 1) {
//            return new ForwardResolution("/WEB-INF/jsp/daftar/kemaskini_pb.jsp").addParameter("tab", "true");
//        } else {
//            return new ForwardResolution("/WEB-INF/jsp/daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
//        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/dev_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution getSenaraiPemohon() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_senarai_pemohonPPCB.jsp").addParameter("tab", "true");
    }

    public Resolution getSenaraiPemohon2() {
        return new JSP("pembangunan/common/dev_senarai_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilikPindahMilikBerkepentingan() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        List<HakmilikPermohonan> hakmilikPermohonanList = p.getSenaraiHakmilik();
        List<String> senaraiKod = new ArrayList();
        if (hakmilikPermohonanList.size() > 0) {
            Hakmilik h = hakmilikPermohonanList.get(0).getHakmilik();
            String kodPihak = "";
            if (p.getKodUrusan().getKod().equals("PMP")) {
                kodPihak = "PJ"; //pemegang pajakan
//                senaraiKod.add("PJ");
            } else if (p.getKodUrusan().getKod().equals("PMG")) {
                kodPihak = "PG"; //pemegang gadaian
//                senaraiKod.add("PG");
            }
            pihakKepentinganList2 = hakmilikPihakKepentinganService.findHakmilikPihakByKod(h, kodPihak);
        }
        return new JSP("daftar/kemasukan_pihak_berkepentingan_pindahmilik.jsp").addParameter("tab", "true");
    }

    public Resolution tukar() {
//        PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        //kodNegeri = conf.getProperty("kodNegeri");

        p = permohonanService.findById(idPermohonan);
        Hakmilik hakmilik = p.getSenaraiHakmilik().get(0).getHakmilik();
        LOG.debug("start tukar nama / tukar alamat");

        String kodUrusan = p.getKodUrusan().getKod();
        pihak = pihakDAO.findById(pihak.getIdPihak());
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());


//        List <PermohonanPihakKemaskini> listTemp = new ArrayList();
        if (kodUrusan.equals("TN") || kodUrusan.equals("PNKP")) {
            if (nama != null) {

                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                LOG.debug("save nama");
                mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                mohonPihakKemaskini.setNamaMedan("nama");
                mohonPihakKemaskini.setNilaiLama(pihak.getNama());
                mohonPihakKemaskini.setNilaiBaru(nama);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
            }
            if (nokp != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                LOG.debug("save nokp");
                mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                mohonPihakKemaskini.setNamaMedan("nokp");
                mohonPihakKemaskini.setNilaiLama(pihak.getNoPengenalan());
                mohonPihakKemaskini.setNilaiBaru(nokp);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
            }
        }
        if (kodUrusan.equals("TA")) {
            if (alamat1 != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                LOG.debug("save alamat 1");
                mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                mohonPihakKemaskini.setNamaMedan("rumahAlamat1");
                mohonPihakKemaskini.setNilaiLama(pihak.getAlamat1());
                mohonPihakKemaskini.setNilaiBaru(alamat1);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
//                listTemp.add(mohonPihakKemaskini);
            }
            if (alamat2 != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                LOG.debug("save alamat 2");
                mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                mohonPihakKemaskini.setNamaMedan("rumahAlamat2");
                mohonPihakKemaskini.setNilaiLama(pihak.getAlamat2());
                mohonPihakKemaskini.setNilaiBaru(alamat2);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
//                listTemp.add(mohonPihakKemaskini);
            }
            if (alamat3 != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                LOG.debug("save alamat 3");
                mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                mohonPihakKemaskini.setNamaMedan("rumahAlamat3");
                mohonPihakKemaskini.setNilaiLama(pihak.getAlamat3());
                mohonPihakKemaskini.setNilaiBaru(alamat3);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
//                listTemp.add(mohonPihakKemaskini);
            }
            if (alamat4 != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                LOG.debug("save alamat 4");
                mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                mohonPihakKemaskini.setNamaMedan("rumahAlamat4");
                mohonPihakKemaskini.setNilaiLama(pihak.getAlamat4());
                mohonPihakKemaskini.setNilaiBaru(alamat4);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
//                listTemp.add(mohonPihakKemaskini);
            }
            if (alamat5 != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                LOG.debug("save alamat 5");
                mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                mohonPihakKemaskini.setNamaMedan("rumahPoskod");
                mohonPihakKemaskini.setNilaiLama(pihak.getPoskod());
                mohonPihakKemaskini.setNilaiBaru(alamat5);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
//                listTemp.add(mohonPihakKemaskini);
            }
            if (!alamat6.equals("Pilih ...")) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                LOG.debug("save alamat 6");
                mohonPihakKemaskini.setNamaMedan("rumahNegeri.kod");

                if (pihak.getNegeri() != null) {
                    mohonPihakKemaskini.setNilaiLama(pihak.getNegeri().getKod());
                } else {
                    mohonPihakKemaskini.setNilaiLama(null);
                }
                mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                mohonPihakKemaskini.setNilaiBaru(alamat6);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
                mohonPihakKemaskiniList = p.getSenaraiPihakKemaskini();
//                listTemp.add(mohonPihakKemaskini);
            }
        }
        addSimpleMessage("Kemaskini Data Berjaya");
//        rehydrate();
        return new RedirectResolution(PihakBerkepentinganPPCBActionBean.class, "getSenaraiHakmilikKepentingan");
    }

    public Resolution SimpanNewPemohon() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("item");
        String[] listPihak = item.split(",");
        LOG.info("Size :" + listPihak.length);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        kodNegeri = conf.getProperty("kodNegeri");


        for (int i = 0; i < listPihak.length; i++) {
            p = permohonanService.findById(idPermohonan);
            if (!listPihak[i].equals("T")) {
                String idphk = listPihak[i].toString();
                Pihak phk = pembangunanServ.getIdPihakDetails(idphk);
                HakmilikPihakBerkepentingan hp = pembangunanServ.getHakmilik(idPermohonan, idphk);

                if (pemohonList.isEmpty()) {
                    LOG.info("+++++++ pemohonn list empty +++++++");
                    // new add pemohon base on existing pihak berkepentingan as a DEFAULT on permohonan and if empty new add pemohon is a must
                    pemohon = new Pemohon();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pguna);
                    info.setTarikhMasuk(new java.util.Date());
                    pemohon.setPermohonan(p);
                    pemohon.setPihak(phk);
                    pemohon.setInfoAudit(info);
                    pemohon.setCawangan(p.getCawangan());
                    pemohon.setSyerPembilang(hp.getSyerPembilang());
                    pemohon.setSyerPenyebut(hp.getSyerPenyebut());
                    pemohonService.saveOrUpdate(pemohon);

                } else {

                    for (int x = 0; x < pemohonList.size(); x++) {

                        if (phk.getIdPihak() != pemohonList.get(x).getPihak().getIdPihak()) {
                            LOG.info("+++++++ phk +++++++" + phk.getIdPihak());
                            LOG.info("+++++++ pemohonList +++++++" + pemohonList.get(x).getPihak().getIdPihak());
                            LOG.info("+++++++ idphak != +++++++");
                            pemohon = new Pemohon();
                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(pguna);
                            info.setTarikhMasuk(new java.util.Date());
                            pemohon.setPermohonan(p);
                            pemohon.setPihak(phk);
                            pemohon.setInfoAudit(info);
                            pemohon.setCawangan(p.getCawangan());
                            pemohon.setSyerPembilang(hp.getSyerPembilang());
                            pemohon.setSyerPenyebut(hp.getSyerPenyebut());
                        } else {
                            LOG.info("+++++++ idpihak ada dalam list pemohon +++++++");
                            x = pemohonList.size();
                            pemohon = null;
                        }

                    }
                    if (pemohon != null) {
                        pemohonService.saveOrUpdate(pemohon);
                    }

                }

            }
        }
        int l = 0;

        for (int z = 0; z < pemohonList.size(); z++) {
            for (int y = 0; y < listPihak.length; y++) {
                if (!listPihak[y].equals("T")) {

                    String idphk2 = listPihak[y].toString();
                    Pihak phk2 = pembangunanServ.getIdPihakDetails(idphk2);
                    if (pemohonList.get(z).getPihak().getIdPihak() != phk2.getIdPihak()) {
                        l = 1;
                    } else {
                        y = listPihak.length;
                        l = 0;
                    }
//                }
//                if (l >= 1) {
//                    Long idphkdel = pemohonList.get(z).getPihak().getIdPihak();
//                    pembangunanServ.deletePemohnonByIdPihakIdMohon(idPermohonan, idphkdel);
                }
                LOG.info("pemohonlist " + pemohonList.get(z).getPihak().getIdPihak());
                LOG.info("+++++++ L +++++++" + l);
            }
            if (l == 1) {
                LOG.info("+++++++ id ni nk buang +++++++" + pemohonList.get(z).getPihak().getIdPihak());
                Long idphkdel = pemohonList.get(z).getPihak().getIdPihak();
                pembangunanServ.deletePemohnonByIdPihakIdMohon(idPermohonan, idphkdel);
            }
        }
//        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
//        getSenaraiPemohon();
        return new JSP("pembangunan/common/dev_senarai_pemohonPPCB.jsp").addParameter("tab", "true");
//        return new ForwardResolution("pembangunan/common/dev_senarai_pemohonPPCB.jsp").addParameter("tab", "true");
//        return new RedirectResolution(PihakBerkepentinganPPCBActionBean.class, "getSenaraiPemohon");
    }

    public Resolution simpanPemohon2() {

        String idPihak = getContext().getRequest().getParameter("idPihak");
        Pihak pi = pihakService.findById(idPihak);
        Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pi);
        if (pmohon == null) {
            Pemohon pe = new Pemohon();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            pe.setInfoAudit(ia);
            pe.setPermohonan(p);
            pe.setPihak(pi);
            pe.setCawangan(p.getCawangan());
            pemohonService.saveOrUpdate(pe);
        } else {
            addSimpleError(pi.getNama() + " telah memohon.");
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
//        rehydrate();
//        return senaraiHakmilikPindahMilikBerkepentingan();
        return new RedirectResolution(PihakBerkepentinganPPCBActionBean.class, "senaraiHakmilikPindahMilikBerkepentingan");
    }

    public Resolution deletePemohon() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pmohon = pemohonService.findById(idPemohon);
            if (pmohon != null) {
                pemohonService.delete(pmohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        rehydrate();
        return getSenaraiPemohon();
    }

    public Resolution deleteSelectedPemohon() {
        String[] id = getContext().getRequest().getParameterValues("idPihak");
        if (isDebug) {
            LOG.debug("id length = " + id.length);
        }
        if (id.length > 0) {
            pemohonService.deleteSelectedPemohon(id);
        }
        return new RedirectResolution(PihakBerkepentinganPPCBActionBean.class, "getSenaraiHakmilikKepentingan");
    }

    public Resolution deletePemohon2() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pmohon = pemohonService.findById(idPemohon);
            if (pmohon != null) {
                pemohonService.delete(pmohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        rehydrate();
        return senaraiHakmilikPindahMilikBerkepentingan();
    }

    public Resolution refreshHapus(Long idPihak) {

        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(idPihak);
        if (pihak.getSenaraiCawangan() != null) {

            cawanganList = pihak.getSenaraiCawangan();
        }

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution deleteChanges() {

        String idKkini = (String) getContext().getRequest().getParameter("idKkini");
        LOG.debug("idKkini : " + idKkini);
        if (idKkini != null) {
            PermohonanPihakKemaskini mpk = mohonPihakKemaskiniDAO.findById(new Long(idKkini));
            if (mpk != null) {
                mohonPihakKemaskiniService.delete(mpk);
                mohonPihakKemaskiniList = p.getSenaraiPihakKemaskini();
            }
//            getContext().getMessages().add(new SimpleMessage("Data {1}.", mpk));
            addSimpleMessage("Data Telah Berjaya diHapuskan");
        }
        addSimpleError("idKkini tiada");


        return new RedirectResolution(PihakBerkepentinganPPCBActionBean.class, "getSenaraiHakmilikKepentingan");
    }

    public Resolution getPaparanSenaraiHakmilikKepentingan() {
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution pihakKepentinganPopup() {
        String urusan = getContext().getRequest().getParameter("urusan");
        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        if (StringUtils.isNotBlank(jenisPB)) {
            getContext().getRequest().setAttribute("jenis", jenisPB);
        }
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }

        kodNegeri = conf.getProperty("kodNegeri");
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution pemohonPopup() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        String urusan = getContext().getRequest().getParameter("urusan");
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }


        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution showPihakKepentinganForm() {
//        getContext().getRequest().setAttribute("popup", "false");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution showPihakKepentinganPajakanForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("daftar/kemasukan_pihak_pajakan.jsp").addParameter("tab", "true");
    }

    public Resolution showPihakKepentinganTenansiForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("daftar/kemasukan_pihak_ten.jsp").addParameter("tab", "true");
    }

    public Resolution showPihakBerkepentinganKaveatForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (p != null) {
            mapList = p.getSenaraiPermohonanAtasPihakBerkepentingan();
        }
        return new JSP("daftar/kemasukan_tuan_tanah_kaveat.jsp").addParameter("tab", "true");
    }

    public Resolution showPihakBerkepentinganPemegangAmanahForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (p != null) {

            mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "WAR");
            pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            mohonPihakPemegangAmanah = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "PA"); //FIXME:waris?

//            if (mohonPihakPemberiAmanah != null) {
//                syer1 = new String[mohonPihakPemberiAmanah.size()];
//                syer2 = new String[mohonPihakPemberiAmanah.size()];
//                for (int i = 0; i < mohonPihakPemberiAmanah.size(); i++) {
//                    PermohonanPihak pp = mohonPihakPemberiAmanah.get(i);
//                    syer1[i] = String.valueOf(pp.getSyerPembilang());
//                    syer2[i] = String.valueOf(pp.getSyerPenyebut());
//                    LOG.debug("syer1" + syer1[i]);
//                    LOG.debug("syer2" + syer2[i]);
//                }
//            }

        }
        return new JSP("daftar/kemasukan_pb_pemegang_amanah.jsp").addParameter("tab", "true");
    }

    public Resolution showPihakBerkepentinganIsmenForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (p != null) {
        }
        return new JSP("daftar/kemasukan_pb_ismen.jsp").addParameter("tab", "true");
    }

    public Resolution showEditNamaPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        return new JSP("pembangunan/common/edit_nama_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditAlamatPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        return new JSP("pembangunan/common/edit_alamat_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
//        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pihak = pembangunanServ.getIdPihakDetails(idPihak);

        if (pihak != null) {
            alamat1 = pihak.getAlamat1();
            alamat2 = pihak.getAlamat2();
            alamat3 = pihak.getAlamat3();
            alamat4 = pihak.getAlamat4();
            poskod = pihak.getPoskod();
            if (pihak.getNegeri() != null) {
                negeri = pihak.getNegeri().getKod();
                LOG.info("--negeri1--" + negeri);
            }
            pihakAlamatTamb = pihakService.getAlamatTambahan(pihak);
            LOG.info("--pihakAlamatTamb--" + pihakAlamatTamb);
            if (pihakAlamatTamb != null) {

                suratAlamat1 = pihakAlamatTamb.getAlamatKetiga1();
                suratAlamat2 = pihakAlamatTamb.getAlamatKetiga2();
                suratAlamat3 = pihakAlamatTamb.getAlamatKetiga3();
                suratAlamat4 = pihakAlamatTamb.getAlamatKetiga4();
                suratPoskod = pihakAlamatTamb.getAlamatKetigaPoskod();
                if (pihakAlamatTamb.getAlamatKetigaNegeri() != null) {
                    suratNegeri = pihakAlamatTamb.getAlamatKetigaNegeri().getKod();
                    LOG.info("--suratNegeri1--" + suratNegeri);
                }
            }
        }

        Pihak phk = pembangunanServ.findPemohonAlamatSuratEqualAlamatByIdPihak(pihak.getIdPihak());
        LOG.info(":: CARI Pihak Alamat = Alamat_Surat by nombor dan jenis pengenalan ::" + phk);
        if (phk != null) {
            LOG.info(":: Alamat = Alamat_Surat ::");
            checkAlamat = "Yes";
        } else {
            LOG.info(":: Alamat tak sama Alamat_Surat ::");
            checkAlamat = "No";
        }

        if (p.getKodUrusan().getKod().equals("PPT") && p.getPermohonanSebelum() != null) {
            pemohon = pemohonService.findPemohonByPermohonanPihak(p.getPermohonanSebelum(), pihak);
            statusFlag = pemohon.getSurat();
        } else {
            if (p.getKodUrusan().getKod().equals("PPCB")) {
                statusFlag = null;
            } else {
                pemohon = pemohonService.findPemohonByPermohonanPihak(p, pihak);
                statusFlag = pemohon.getSurat();
            }
        }

        // added on 19-12-2011
        if (pihak.getJenisPengenalan() != null) {
            LOG.info("------pihak.getJenisPengenalan()---------" + pihak.getJenisPengenalan().getKod());
            senaraiKodBangsa = pembangunanServ.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        }
        return new JSP("pembangunan/common/edit_pemohonPPCB.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPenerima() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(idPermohonan, Long.valueOf(idPihak));
        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        return new JSP("common/edit_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPihakKepentinganPopup() throws ParseException {
        String tmp = getContext().getRequest().getParameter("urusan");

        Permohonan permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihakTemp == null) {
            pihakTemp = new Pihak();
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setInfoAudit(info);
            pihakTemp.setTempatLahir(pihak.getTempatLahir());

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
        }

        if (StringUtils.isNotBlank(tmp) && tmp.equals("PNPA")) {
            List<HakmilikPermohonan> senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikPermohonan.size() > 0) {
                Hakmilik hm = senaraiHakmilikPermohonan.get(0).getHakmilik();
                if (hm != null) {
                    Pemohon pemohon = new Pemohon();
                    pemohon.setCawangan(hm.getCawangan());
                    pemohon.setPihak(pihakTemp);
                    pemohon.setInfoAudit(info);
                    pemohon.setPermohonan(permohonan);
                    pemohonService.saveOrUpdate(pemohon);
                }
            }
            return new RedirectResolution(PihakBerkepentinganPPCBActionBean.class, "showPihakBerkepentinganPemegangAmanahForm");
        }

        if (jenisPihak != null) {

            permohonanPihak = new PermohonanPihak();

            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }

            Fraction f = Fraction.ZERO;
            List<Pemohon> senaraiPemohon = permohonan.getSenaraiPemohon();
            List<HakmilikPermohonan> senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();


            for (HakmilikPermohonan hakmilikPermohonan : senaraiHakmilikPermohonan) {
                Hakmilik hk = hakmilikPermohonan.getHakmilik();
                if (hk == null) {
                    continue;
                }
                for (Pemohon p : senaraiPemohon) {
                    if (p == null) {
                        continue;
                    }
                    LOG.debug("pihak = " + p.getPihak().getIdPihak());
                    LOG.debug("hakmilik = " + hk.getIdHakmilik());
                    HakmilikPihakBerkepentingan hm = syerService.findSyerPihakFromHakmilikPihak(p.getPihak().getIdPihak(), hk.getIdHakmilik());
                    if (hm == null) {
                        continue;
                    }
                    if (hm.getSyerPembilang() == 0 && hm.getSyerPenyebut() == 0) {
                        continue;
                    }
                    f = f.add(new Fraction(hm.getSyerPembilang(), hm.getSyerPenyebut()));
                }
            }

            permohonanPihak.setPermohonan(permohonan);
            permohonanPihak.setPihak(pihakTemp);
            if (permohonan.getKodUrusan().getKod().equals("PJT") || permohonan.getKodUrusan().getKod().equals("PJKT")) {
                permohonanPihak.setSyerPembilang(1);
                permohonanPihak.setSyerPenyebut(1);
            } else {
                permohonanPihak.setSyerPembilang(f.getNumerator());
                permohonanPihak.setSyerPenyebut(f.getDenominator());
            }
//            else if (permohonan.getKodUrusan().getKod().equals("GDP")
//                    || permohonan.getKodUrusan().getKod().equals("PNPA")) {
//                permohonanPihak.setSyerPembilang(f.getNumerator());
//                permohonanPihak.setSyerPenyebut(f.getDenominator());
//            } else {
////                permohonanPihak.setSyerPembilang(0);
////                permohonanPihak.setSyerPenyebut(0);
//            }

            permohonanPihak.setInfoAudit(info);
            if (permohonan.getSenaraiHakmilik().size() > 0) {
                permohonanPihak.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }

            KodJenisPihakBerkepentingan kodJenisPihak = new KodJenisPihakBerkepentingan();
            kodJenisPihak.setKod(jenisPihak);
            permohonanPihak.setJenis(kodJenisPihak);

            if (idCawangan != null) {
                pihakCawangan = new PihakCawangan();
                pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
                permohonanPihak.setPihakCawangan(pihakCawangan);
            }
            permohonanPihak.setCawangan(permohonan.getCawangan());
            pihakService.saveOrUpdatePihakKepentinganPihak(pihakTemp, permohonanPihak);
            if (pihakCawangan != null) {
                pihakCawangan.setIbupejabat(pihakTemp);
                pihakCawangan.setInfoAudit(info);
                pihakService.saveOrUpdatePihakCawangan(pihakCawangan);
            }

            if (permohonan.getKodUrusan().getKod().equals("PJT") || permohonan.getKodUrusan().getKod().equals("PJBT") || permohonan.getKodUrusan().getKod().equals("PJKBT") || permohonan.getKodUrusan().getKod().equals("PJKT")) {
                return new RedirectResolution("/pihak_berkepentingan?showPihakKepentinganPajakanForm").addParameter("tab", "true");
            } else if (permohonan.getKodUrusan().getKod().equals("TEN") || permohonan.getKodUrusan().getKod().equals("TENBT")) {
                return new RedirectResolution("/pihak_berkepentingan?showPihakKepentinganTenansiForm").addParameter("tab", "true");

            } else if (permohonan.getKodUrusan().getKod().equals("JDGPJ") || permohonan.getKodUrusan().getKod().equals("PHMMK")) {

                return new RedirectResolution(PerintahJualPihakBerkepentingan.class).addParameter("tab", "true");
            }

            if (StringUtils.isNotBlank(tmp)) {
                return new RedirectResolution("/pihak_berkepentingan?showPihakKepentinganForm").addParameter("tab", "true");
            } else {
                if (permohonan.getKodUrusan().getKod().equals("PMG") || permohonan.getKodUrusan().getKod().equals("PMP")) {
                    return new RedirectResolution("/pihak_berkepentingan?senaraiHakmilikPindahMilikBerkepentingan").addParameter("tab", "true");
                } else if (permohonan.getKodUrusan().getKod().equals("PNPA")) {
                    return new RedirectResolution(PihakBerkepentinganPPCBActionBean.class, "showPihakBerkepentinganPemegangAmanahForm").addParameter("tab", "true");
                } else {
                    return new RedirectResolution("/pihak_berkepentingan?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
                }
            }
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanPemohonPopup() throws ParseException {

        Permohonan permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihakTemp == null) {
            pihakTemp = new Pihak();
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());
//          Newly Added
            pihakTemp.setTarikhLahir(pihak.getTarikhLahir());
            pihakTemp.setModalBerbayar(pihak.getModalBerbayar());
            pihakTemp.setModalDibenar(pihak.getModalDibenar());

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
        }

        pihakTemp.setInfoAudit(info);
        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pihakTemp);
        pmohon.setInfoAudit(info);
        pmohon.setCawangan(permohonan.getCawangan());

        if (idCawangan != null) {
            pihakCawangan = new PihakCawangan();
            pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
            pmohon.setPihakCawangan(pihakCawangan);
            //pemohon.setPihakCawangan(pihakCawangan);
        }

        if (pmohon != null) {

            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());
//          Newly Added
            pihakTemp.setTarikhLahir(pihak.getTarikhLahir());
            pihakTemp.setModalBerbayar(pihak.getModalBerbayar());
            pihakTemp.setModalDibenar(pihak.getModalDibenar());

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
            pemohonService.simpanPihakPemohon(pihakTemp, pmohon);
            pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            return new RedirectResolution("/pembangunan/pihak_berkepentingan?getSenaraiPemohon").addParameter("tab", "true");
        }
        pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("tab", "true");
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution cariPihak() {

        String tmp = getContext().getRequest().getParameter("tuanTanah");
        if (StringUtils.isNotBlank(tmp)) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        if (StringUtils.isNotBlank(jenisPB)) {
            KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById(jenisPB);
            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }
            permohonanPihak.setJenis(kod);
            getContext().getRequest().setAttribute("jenis", jenisPB);
        } else {
            String kodUrusan = p.getKodUrusan().getKod();
            if (kodUrusan.equals("TMAML") || kodUrusan.equals("TMAMD")) {
                //if TMAML / TMAMD , jenis pihak must be pendaftar(PP)
                KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById("PP");
                if (permohonanPihak == null) {
                    permohonanPihak = new PermohonanPihak();
                }
                permohonanPihak.setJenis(kod);
            }
        }

        if (pihak != null) {

            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null) {
                    if (!(mohonPihakList.isEmpty())) {
                        for (PermohonanPihak pp : mohonPihakList) {
                            if (pp.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {

                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    // Newly Modified
                    senaraiKodBangsa = pembangunanServ.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {

                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
        }

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution cariPihakPemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        String tmp = getContext().getRequest().getParameter("tuanTanah");
        if (StringUtils.isNotBlank(tmp)) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        if (StringUtils.isNotBlank(jenisPB)) {
            KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById(jenisPB);
            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }
            permohonanPihak.setJenis(kod);
            getContext().getRequest().setAttribute("jenis", jenisPB);
        }

        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {

                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null) {
                    if (!(pemohonList.isEmpty())) {
                        for (Pemohon pem : pemohonList) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {

                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    // Newly Modified
                    senaraiKodBangsa = pembangunanServ.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {
                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
        }

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution cariSemula() {
        pihak = new Pihak();
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
//        String urusan = getContext().getRequest().getParameter("urusan");
//        if (StringUtils.isNotBlank(urusan)) {
//            getContext().getRequest().setAttribute("urusan", urusan);
//        }
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution semakSyer() {

        String result = "Pembahagian tanah berjaya.";
        int i = 0;
        List<PermohonanPihak> permPihak = new ArrayList<PermohonanPihak>();
        for (PermohonanPihak pp : mohonPihakList) {
//            Fraction f = new Fraction(Integer.parseInt(syer1[i]), Integer.parseInt(syer2[i]));
//            pp.setSyer(f.doubleValue());
            pp.setSyerPembilang(Integer.parseInt(syer1[i]));
            pp.setSyerPenyebut(Integer.parseInt(syer2[i]));
            permPihak.add(pp);
            i = i + 1;
        }
        permohonanPihakService.saveOrUpdate(permPihak);


        try {
            int r = syerService.doValidateSyerPortion(idPermohonan, "");
            if (r < 0) {
                result = "Jumlah pembahagian tanah tidak mencukupi.";
            } else if (r > 0) {
                result = "Jumlah pembahagian tanah melebihi daripada bahagian pemohon.";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Pembahagian Tanah Gagal.");
        }

        return new StreamingResolution("text/plain", result);
    }

    public Resolution agihSamaRata() {
        String results = "0";
        String DELIM = "__^$__";

        //TODO : multiple hakmilik
//        Hakmilik hm = p.getSenaraiHakmilik().get(0).getHakmilik();
        List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
        Fraction sumAllPemohon = Fraction.ZERO;
        Fraction samaRata = Fraction.ZERO;
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            LOG.debug("************");
            if (hp == null) {
                continue;
            }
            for (Pemohon pemohon : pemohonList) {
                HakmilikPihakBerkepentingan hmk = hakmilikPihakKepentinganService.findHakmilikPihakByPihak(pemohon.getPihak().getIdPihak(), hp.getHakmilik());
                if (hmk != null) {
                    LOG.debug("pihak = " + pemohon.getPihak().getNama());
                    LOG.debug("pembilang =" + hmk.getSyerPembilang());
                    LOG.debug("penyebut =" + hmk.getSyerPenyebut());

                    sumAllPemohon = sumAllPemohon.add(new Fraction(hmk.getSyerPembilang(), hmk.getSyerPenyebut()));
                    continue;
                }
            }
            LOG.debug("************");
        }

        LOG.debug("sum all = " + sumAllPemohon);
        if (sumAllPemohon.getDenominator() == 1) {
            sumAllPemohon = Fraction.ONE;
        }
        LOG.debug("sum all = " + sumAllPemohon);

        if (mohonPihakList != null && mohonPihakList.size() > 0) {
            samaRata = sumAllPemohon.divide(mohonPihakList.size());
            for (PermohonanPihak pp : mohonPihakList) {
                pp.setSyerPembilang(samaRata.getNumerator());
                pp.setSyerPenyebut(samaRata.getDenominator());
            }

            permohonanPihakService.saveOrUpdate(mohonPihakList);

            StringBuilder s = new StringBuilder();
            s.append(samaRata.getNumerator()).append(DELIM).append(samaRata.getDenominator());
            results = s.toString();
            LOG.debug(results);
        }

//        permohonanPihakService.saveOrUpdate(mohonPihakList);

//        return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
        return new StreamingResolution("text/plain", results);
    }

    public Resolution updateSyerMohonPihak() {
        String idPihak = getContext().getRequest().getParameter("idpihak");
        String s1 = getContext().getRequest().getParameter("syer1");//pembilang
        String s2 = getContext().getRequest().getParameter("syer2");//penyebut
        LOG.debug("s1:" + s1);
        LOG.debug("s2:" + s2);
        LOG.debug("idpihak:" + idPihak);
        if (StringUtils.isNotBlank(idPihak) && StringUtils.isNotBlank(s1) && StringUtils.isNotBlank(s2)) {
            LOG.debug(idPihak);
            LOG.debug(s1);
            LOG.debug(s2);
            PermohonanPihak pp = permohonanPihakService.findById(idPihak);
            pp.setSyerPembilang(Integer.parseInt(s1));
            pp.setSyerPenyebut(Integer.parseInt(s2));
            permohonanPihakService.saveOrUpdate(pp);
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanPihak() {
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();

            Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
            pihakTemp.setAlamat1(alamat1);
            pihakTemp.setAlamat2(alamat2);
            pihakTemp.setAlamat3(alamat3);
            pihakTemp.setAlamat4(alamat4);
            pihakTemp.setPoskod(poskod);
            pihakTemp.setBangsa(pihak.getBangsa());
//            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
//            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
//            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
//            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
//            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());

            // Newly Added
            pihakTemp.setTarikhLahir(pihak.getTarikhLahir());
            pihakTemp.setModalBerbayar(pihak.getModalBerbayar());
            pihakTemp.setModalDibenar(pihak.getModalDibenar());
            LOG.info(":: kodNegeri ::" + kodNegeri);
            if (negeri != null) {
                LOG.info(":: if kodNegeri ::" + negeri);
                KodNegeri kn = kodNegeriDAO.findById(negeri);
                pihakTemp.setNegeri(kn);
            } else {
                LOG.info(":: else kodNegeri ::" + negeri);
                pihakTemp.setNegeri(null);
            }
            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pihakTemp.setInfoAudit(infoAudit);
            pihakService.saveOrUpdate(pihakTemp);
            LOG.info(":: Pihak Alamat Tamb Start::");
            // Added code to save PihakAlamatTamb
            PihakAlamatTamb pihakAlamatTambTemp = pihakService.getAlamatTambahan(pihak);
            if (pihakAlamatTambTemp == null) {
                pihakAlamatTambTemp = new PihakAlamatTamb();
                infoAudit = new InfoAudit();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pguna);
            } else {
                infoAudit = pihakAlamatTambTemp.getInfoAudit();
                infoAudit.setTarikhKemaskini(new java.util.Date());
                infoAudit.setDiKemaskiniOleh(pguna);
            }
            pihakAlamatTambTemp.setAlamatKetiga1(suratAlamat1);
            pihakAlamatTambTemp.setAlamatKetiga2(suratAlamat2);
            pihakAlamatTambTemp.setAlamatKetiga3(suratAlamat3);
            pihakAlamatTambTemp.setAlamatKetiga4(suratAlamat4);
            pihakAlamatTambTemp.setAlamatKetigaPoskod(suratPoskod);
            LOG.info(":: suratNegeri ::" + suratNegeri);
            if (suratNegeri != null) {
                LOG.info(":: If suratNegeri ::" + suratNegeri);
                KodNegeri kn1 = kodNegeriDAO.findById(suratNegeri);
                pihakAlamatTambTemp.setAlamatKetigaNegeri(kn1);
            } else {
                LOG.info(":: else suratNegeri ::" + suratNegeri);
                pihakAlamatTambTemp.setAlamatKetigaNegeri(null);
            }
            pihakAlamatTambTemp.setPihak(pihak);
            pihakAlamatTambTemp.setInfoAudit(infoAudit);
            pembangunanServ.simpanPihakAlamatTamb(pihakAlamatTambTemp);
            LOG.info(":: Simpan Pihak Alamat Tamb ::");

            Pihak phk = pembangunanServ.findPemohonAlamatSuratEqualAlamatByIdPihak(pihak.getIdPihak());
            LOG.info(":: CARI Pihak Alamat = Alamat_Surat by nombor dan jenis pengenalan ::");
            if (phk != null) {
                LOG.info(":: Alamat = Alamat_Surat ::");
                checkAlamat = "Yes";
            } else {
                LOG.info(":: Alamat tak sama Alamat_Surat ::");
                checkAlamat = "No";
            }

            Pemohon pmohon = new Pemohon();
            if (p.getKodUrusan().getKod().equals("PPT") && p.getPermohonanSebelum() != null) {
                pmohon = pemohonService.findPemohonByPermohonanPihak(p.getPermohonanSebelum(), pihakTemp);
            } else {
                pmohon = pemohonService.findPemohonByPermohonanPihak(p, pihakTemp);
            }

            infoAudit = new InfoAudit();
            infoAudit = pmohon.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pmohon.setInfoAudit(infoAudit);

            if (pemohon != null) {
                pmohon.setKaitan(pemohon.getKaitan());
            } else {
                pmohon.setKaitan("");
            }
            if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {

                if (pemohon != null) {
                    pmohon.setStatusKahwin(pemohon.getStatusKahwin());
                    pmohon.setPekerjaan(pemohon.getPekerjaan());
                    pmohon.setUmur(pemohon.getUmur());
                    pmohon.setPendapatan(pemohon.getPendapatan());
//                    pmohon.setTangungan(pemohon.getTangungan());
                }
            }
            LOG.debug(" ======== statusFlag ========:" + statusFlag);
            pmohon.setSurat(statusFlag);
            pemohonService.saveOrUpdate(pmohon);
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("pembangunan/common/dev_senarai_pemohonPPCB.jsp").addParameter("tab", "true");
        }
        tx.commit();
        return new JSP("pembangunan/common/dev_senarai_pemohonPPCB.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEditPenerima() {
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            Pihak pihakTemp = new Pihak();
            pihakTemp = pihakDAO.findById(pihak.getIdPihak());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());

            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pihakTemp.setInfoAudit(infoAudit);

            pihakService.saveOrUpdate(pihakTemp);

            PermohonanPihak perPihak = new PermohonanPihak();
            perPihak = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(idPermohonan, pihak.getIdPihak());

            infoAudit = new InfoAudit();
            infoAudit = perPihak.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            perPihak.setInfoAudit(infoAudit);

            if (permohonanPihak != null) {
                perPihak.setKaitan(permohonanPihak.getKaitan());
            } else {
                perPihak.setKaitan("");
            }

            if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {
                perPihak.setStatusKahwin(permohonanPihak.getStatusKahwin());
                perPihak.setPekerjaan(permohonanPihak.getPekerjaan());
                perPihak.setUmur(permohonanPihak.getUmur());
                perPihak.setPendapatan(permohonanPihak.getPendapatan());
                perPihak.setTangungan(permohonanPihak.getTangungan());
            }
            //comMMENT 
            //permohonanPihakService.saveOrUpdate(perPihak);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("pembangunan/common/dev_senarai_pemohon.jsp").addParameter("tab", "true");
        }
        tx.commit();

        return new JSP("pembangunan/common/dev_senarai_pemohon.jsp").addParameter("tab", "true");

    }

    public Resolution backCawangan() {

        pihak = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        // Newly Modified
        senaraiKodBangsa = pembangunanServ.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
        cariFlag = true;
        tiadaDataFlag = false;

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanpemohon() {

        pihak = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        // Newly Modified
        senaraiKodBangsa = pembangunanServ.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
        cariFlag = true;
        tiadaDataFlag = false;

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution backCawanganPemohon() {

        pihak = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        // Newly Modified
        senaraiKodBangsa = pembangunanServ.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        cariFlag = true;
        tiadaDataFlag = false;
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution deleteCawangan() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        cariFlag = true;

        if (idCaw != null) {

            PihakCawangan pihakCaw = cawanganService.findById(idCaw);
            Long id = pihakCaw.getIbupejabat().getIdPihak();

            if (pihakCaw != null) {
                cawanganService.delete(pihakCaw);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
//                if (pihak.getSenaraiCawangan() != null) {
                cawanganList = pihak.getSenaraiCawangan();
                pengarahList = pihak.getSenaraiPengarah();
//                }
            }
        }
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution deleteCawanganPemohon() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        cariFlag = true;

        if (idCaw != null) {

            PihakCawangan pihakCaw = cawanganService.findById(idCaw);
            Long id = pihakCaw.getIbupejabat().getIdPihak();

            if (pihakCaw != null) {
                cawanganService.delete(pihakCaw);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
//                if (pihak.getSenaraiCawangan() != null) {
                cawanganList = pihak.getSenaraiCawangan();
                pengarahList = pihak.getSenaraiPengarah();
//                }
            }
        }
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution editCawangan() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution editCawanganPemohon() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution tambahCawangan() {

        if (!(pihakDAO.exists(pihak.getIdPihak()))) {

            Transaction tx = sessionProvider.get().getTransaction();
            tx.begin();

            try {

                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                pihak.setInfoAudit(info);
                pihakService.saveOrUpdate(pihak);

            } catch (Exception ex) {
                tx.rollback();
            }
            tx.commit();

        }
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan.setNamaCawangan(pihak.getNama());

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    // Newly added code
    public Resolution tambahPengarah() {

        if (!(pihakDAO.exists(pihak.getIdPihak()))) {
            Transaction tx = sessionProvider.get().getTransaction();
            tx.begin();
            try {
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                pihak.setInfoAudit(info);
                pihakService.saveOrUpdate(pihak);

            } catch (Exception ex) {
                tx.rollback();
            }
            tx.commit();
        }
        pihakPengarah = new PihakPengarah();
        pihakPengarah.setNoPengenalan(pihak.getNoPengenalan());
        pihakPengarah.setJenisPengenalan(pihak.getJenisPengenalan());
        tambahPengarahFlag = true;
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution editPengarah() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        tambahPengarahFlag = true;
        pihakPengarah = new PihakPengarah();
        pihakPengarah = pihakPengarahDAO.findById(Long.parseLong(idPengarah));
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution tambahPengarahPemohon() {

        if (pihak.getNama() == null) {
            addSimpleError("Sila masukkan data yang bertanda *.");
            cariFlag = true;
            tiadaDataFlag = true;
        } else {
            if (!(pihakDAO.exists(pihak.getIdPihak()))) {
                Transaction tx = sessionProvider.get().getTransaction();
                tx.begin();
                try {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pguna);
                    info.setTarikhMasuk(new java.util.Date());
                    pihak.setInfoAudit(info);
                    pihakService.saveOrUpdate(pihak);

                } catch (Exception ex) {
                    tx.rollback();
                }
                tx.commit();
            }
            pihakPengarah = new PihakPengarah();
            pihakPengarah.setNoPengenalan(pihak.getNoPengenalan());
            pihakPengarah.setJenisPengenalan(pihak.getJenisPengenalan());
            tambahPengarahFlag = true;
        }
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution editPengarahPemohon() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        tambahPengarahFlag = true;
        pihakPengarah = new PihakPengarah();
        pihakPengarah = pihakPengarahDAO.findById(Long.parseLong(idPengarah));
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPengarah() {

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        pihakPengarah.setPihak(pihak);
        pihakPengarah.setAktif('Y');
        pihakPengarah.setInfoAudit(infoAudit);
        pihakService.saveOrUpdatePihakPengarah(pihakPengarah);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/pihak_berkepentingan?getTambahPihakPengarah&idPihak=" + id + "&jenisPB=" + jenisPihak).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanPengarahPemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String wargaNegaraKod = getContext().getRequest().getParameter("pihakPengarah.wargaNegara.kod");
        LOG.info("-----wargaNegaraKod-------:" + wargaNegaraKod);
        if (wargaNegaraKod != null) {
            pihakPengarah.setWarganegara(kodWarganegaraDAO.findById(wargaNegaraKod));
        }
        pihakPengarah.setPihak(pihak);
        pihakPengarah.setAktif('Y');
        pihakPengarah.setInfoAudit(infoAudit);
        pihakService.saveOrUpdatePihakPengarah(pihakPengarah);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        LOG.info("-----------simpanPengarahPemohon---iF----------:" + cariFlag);
        if (cariFlag) {
            LOG.info("-----------simpanPengarahPemohon-------------:" + cariFlag);
            return new RedirectResolution("/pembangunan/pihak_berkepentingan?getTambahPihakPengarah&idPihak=" + id).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahPihakPengarah() {
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        LOG.info("-----------getTambahPihakPengarah-------------");
        String idPihak = getContext().getRequest().getParameter("idPihak");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        pengarahList = pihak.getSenaraiPengarah();
        cawanganList = pihak.getSenaraiCawangan();

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution deletePengarah() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        cariFlag = true;

        if (idPengarah != null) {
            PihakPengarah pihakPengarah = pihakPengarahDAO.findById(Long.parseLong(idPengarah));
            if (pihakPengarah != null) {
                Long id = pihakPengarah.getPihak().getIdPihak();
                pihakService.deletePengarah(pihakPengarah);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                cawanganList = pihak.getSenaraiCawangan();
                pengarahList = pihak.getSenaraiPengarah();

            }
        }
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution deletePengarahPemohon() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        cariFlag = true;

        if (idPengarah != null) {
            PihakPengarah pihakPengarah = pihakPengarahDAO.findById(Long.parseLong(idPengarah));
            if (pihakPengarah != null) {
                Long id = pihakPengarah.getPihak().getIdPihak();
                pihakService.deletePengarah(pihakPengarah);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                cawanganList = pihak.getSenaraiCawangan();
                pengarahList = pihak.getSenaraiPengarah();

            }
        }
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanCawangan() {

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());

        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);
        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/pihak_berkepentingan?getTambahCawanganPenerima&idPihak=" + id + "&jenisPB=" + jenisPihak).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahCawanganPenerima() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
        if (pihak.getSenaraiCawangan() != null) {
            cawanganList = pihak.getSenaraiCawangan();
            pengarahList = pihak.getSenaraiPengarah();
        }

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution tambahCawanganPemohon() {

        if (pihak.getNama() == null) {
            addSimpleError("Sila masukkan data yang bertanda *.");
            cariFlag = true;
            tiadaDataFlag = true;
        } else {
            if (!(pihakDAO.exists(pihak.getIdPihak()))) {
                Transaction tx = sessionProvider.get().getTransaction();
                tx.begin();
                try {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pguna);
                    info.setTarikhMasuk(new java.util.Date());
                    pihak.setInfoAudit(info);
                    pihakService.saveOrUpdate(pihak);

                } catch (Exception ex) {
                    tx.rollback();
                }
                tx.commit();
            }
            tambahCawFlag = true;
        }
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanCawanganPemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());

        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);
        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/pembangunan/pihak_berkepentingan?getTambahCawanganPemohon&idPihak=" + id).addParameter("popup", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahCawanganPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        cariFlag = true;

        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));

        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!deletePemohon", "!deletePemohon2", "!deleteSelectedPemohon"})
    public void rehydrate() {
        if (isDebug) {
            LOG.debug("rehydrate start");
        }
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        listPihak = pembangunanServ.getIdPihak(idPermohonan);
        LOG.debug("listPihak++++" + listPihak.size());
        sizelistphk = listPihak.size();
        LOG.debug("listPihak++++" + listPihak.toString());

        kodNegeri = conf.getProperty("kodNegeri");

        p = permohonanService.findById(idPermohonan);
        if (p != null) {

//            p = permohonanService.findById(idPermohonan);
//            idPermohonan = p.getIdPermohonan();
            hakmilikPermohonanList2 = p.getSenaraiHakmilik();
            pemohonList2 = p.getSenaraiPemohon();
//            HakmilikPermohonan hpp = p.getSenaraiHakmilik().get(0);
            if (pemohonList2.isEmpty()) {
                for (int i = 0; i < hakmilikPermohonanList2.size(); i++) {
                    HakmilikPermohonan hpp = new HakmilikPermohonan();
                    hpp = hakmilikPermohonanList2.get(i);
                    Hakmilik h = hpp.getHakmilik();
                    LOG.info("hakmilik" + h.getIdHakmilik());
                    listphk = h.getSenaraiPihakBerkepentingan();
                    LOG.info("listpihak:" + listphk.size());
                    for (HakmilikPihakBerkepentingan hpk : listphk) {
                        if (hpk.getAktif() == 'Y' && hpk.getJenis().getKod().equals("PM")) {
                            Pihak ph = hpk.getPihak();
                            LOG.info("nama tuan tanah:" + ph.getNama());
                                if (ph.getJenisPengenalan() != null) {
                                    jenisPengenalan = ph.getJenisPengenalan().getKod();
                                    jenisPengenalan2 = ph.getJenisPengenalan().getNama();
                                    LOG.info("Jenis Pengenalan tak null : " + jenisPengenalan);
                                }

                                if (ph.getNoPengenalan() != null) {
                                    nomborPengenalan = ph.getNoPengenalan();
                                }
                                nama = ph.getNama();
                                alamat1 = ph.getAlamat1();
                                alamat2 = ph.getAlamat2();
                                alamat3 = ph.getAlamat3();
                                alamat4 = ph.getAlamat4();
                                LOG.info("--alamat1--" + alamat1);
                                poskod = ph.getPoskod();
                                if (ph.getNegeri() != null) {
                                    negeri = ph.getNegeri().getKod();
                                    negeri1 = ph.getNegeri().getNama().toUpperCase();
                                    LOG.info("--negeri1--" + negeri1);
                                }

                                pihakAlamatTamb = pihakService.getAlamatTambahan(ph);
                                LOG.info("--pihakAlamatTamb--" + pihakAlamatTamb);
                                if (pihakAlamatTamb != null) {
                                    suratAlamat1 = pihakAlamatTamb.getAlamatKetiga1();
                                    suratAlamat2 = pihakAlamatTamb.getAlamatKetiga2();
                                    suratAlamat3 = pihakAlamatTamb.getAlamatKetiga3();
                                    suratAlamat4 = pihakAlamatTamb.getAlamatKetiga4();
                                    suratPoskod = pihakAlamatTamb.getAlamatKetigaPoskod();
                                    if (pihakAlamatTamb.getAlamatKetigaNegeri() != null) {
                                        suratNegeri = pihakAlamatTamb.getAlamatKetigaNegeri().getKod();
                                        suratNegeri1 = pihakAlamatTamb.getAlamatKetigaNegeri().getNama().toUpperCase();
                                        LOG.info("--suratNegeri1--" + suratNegeri1);
                                    }
                                }



                            // new add pemohon base on existing pihak berkepentingan as a DEFAULT on permohonan and if empty new add pemohon is a must
//                            pemohon = new Pemohon();
//                            InfoAudit info = new InfoAudit();
//                            info.setDimasukOleh(pguna);
//                            info.setTarikhMasuk(new java.util.Date());
//                            pemohon.setPermohonan(p);
//                            pemohon.setPihak(ph);
//                            pemohon.setInfoAudit(info);
//                            pemohon.setCawangan(p.getCawangan());
//                            pemohon.setSyerPembilang(hpk.getSyerPembilang());
//                            pemohon.setSyerPenyebut(hpk.getSyerPenyebut());
//                            pemohonService.saveOrUpdate(pemohon);
                        }
                    }
                }
            } else {
                LOG.info("###### Tak masuk ");
            }
            List<HakmilikPermohonan> hakmilikPermohonanList = p.getSenaraiHakmilik();
            KodNegeri negeriBaru = new KodNegeri();
            KodNegeri negeriLama = new KodNegeri();
            mohonPihakKemaskiniList = p.getSenaraiPihakKemaskini();
            if (mohonPihakKemaskiniList.size() > 0) {
                mohonPihakKemaskini = p.getSenaraiPihakKemaskini().get(0);
                for (PermohonanPihakKemaskini mohonPihakKemaskiniBaru : mohonPihakKemaskiniList) {
                    if (mohonPihakKemaskiniBaru.getNamaMedan().equals("rumahNegeri.kod")) {
                        negeriBaru = kodNegeriDAO.findById(mohonPihakKemaskiniBaru.getNilaiBaru());
                        namaNegeriBaru = negeriBaru.getNama();
                        negeriLama = kodNegeriDAO.findById(mohonPihakKemaskiniBaru.getNilaiLama());
                        namaNegeriLama = negeriLama.getNama();
                    }
                }
            }

//            if (hakmilikPermohonanList.size() > 0 && hakmilikPermohonanList.size() <= 1) {
            if (hakmilikPermohonanList.size() > 0) { //for multiple hakmilik
                //multiple hakmilik
                List<HakmilikPihakBerkepentingan> senaraiHpk = new ArrayList<HakmilikPihakBerkepentingan>();
                List<HakmilikPihakBerkepentingan> senaraiOtherHpk = new ArrayList<HakmilikPihakBerkepentingan>();
                pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
                othersPihakList = new ArrayList<HakmilikPihakBerkepentingan>();
                mohonPihakList = new ArrayList<PermohonanPihak>();
                pemohonList = new ArrayList<Pemohon>();
                for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                    Hakmilik hk = hp.getHakmilik();
                    if (hk == null) {
                        continue;
                    }
                    senaraiHpk = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hk);
                    senaraiOtherHpk = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(hk.getIdHakmilik(), null);
                    for (HakmilikPihakBerkepentingan hpk : senaraiHpk) {
                        if (hpk == null) {
                            continue;
                        }
                        pihakKepentinganList.add(hpk);
                    }
                    for (HakmilikPihakBerkepentingan hpk : senaraiOtherHpk) {
                        if (hpk == null) {
                            continue;
                        }
                        othersPihakList.add(hpk);
                    }
                }

            }

            //for urusan consent tanah adat
            if (p.getKodUrusan().getKod().equals("PAADT") || p.getKodUrusan().getKod().equals("CGADT")
                    || p.getKodUrusan().getKod().equals("PMADT") || p.getKodUrusan().getKod().equals("TMADT")) {
                mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByNotKod(idPermohonan, "WAR");
            } else {
                mohonPihakList = p.getSenaraiPihak();
            }
            if (p.getKodUrusan().getKod().equals("IS") || p.getKodUrusan().getKod().equals("ISBLS")) {
                //Hakmilik hk = hakmilikPermohonanList.get(1).getHakmilik();
                List<HakmilikPermohonan> hpList = new ArrayList();
                hpList = regService.senaraiMohonHakmilikMenanggung(p.getIdPermohonan());
                Hakmilik hk = hpList.get(0).getHakmilik();
                pihakKepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hk);
                othersPihakList = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(hk.getIdHakmilik(), null);
                mohonPihakList = p.getSenaraiPihak();
                //pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
                syer1 = new String[mohonPihakList.size()];
                syer2 = new String[mohonPihakList.size()];
            }
// new add pemohon base on existing pihak berkepentingan as a DEFAULT on permohonan and if empty new add pemohon is a must
//            pemohonList2 = p.getSenaraiPemohon();
//            System.out.println("###### Pihak Berkepentingan" + pemohonList2.size())
//            if (pemohonList2.isEmpty()) {
//                LOG.info("###### Pihak Berkepentingan" + pihakKepentinganList.size());
//                System.out.println("###### Pihak Berkepentingan" + pihakKepentinganList.size());
//                for (int i = 0; i < pihakKepentinganList.size(); i++) {
//                    HakmilikPihakBerkepentingan hp = pihakKepentinganList.get(i);
//                    pemohon = new Pemohon();
//                    InfoAudit info = new InfoAudit();
//                    info.setDimasukOleh(pguna);
//                    info.setTarikhMasuk(new java.util.Date());
//
//                    pemohon.setPermohonan(p);
//                    pemohon.setPihak(hp.getPihak());
//                    pemohon.setInfoAudit(info);
//                    pemohon.setCawangan(p.getCawangan());
//                    pemohonService.saveOrUpdate(pemohon);
//                }
//            } else {
//                System.out.println("###### Tak masuk ");
//            }
            pemohonList = p.getSenaraiPemohon();
            if (p.getKodUrusan().getKod().equals("PPT") && p.getPermohonanSebelum() != null) {
                LOG.info("######### PPT #########");
                pemohonListIndividu = pembangunanServ.findSenaraiPemohonIndividuByIdPermohonan(p.getPermohonanSebelum().getIdPermohonan());
                pemohonListCompany = pembangunanServ.findSenaraiPemohonSyarikatByIdPermohonan(p.getPermohonanSebelum().getIdPermohonan());
            } else {
                pemohonListIndividu = pembangunanServ.findSenaraiPemohonIndividuByIdPermohonan(idPermohonan);
                pemohonListCompany = pembangunanServ.findSenaraiPemohonSyarikatByIdPermohonan(idPermohonan);
            }

            for (Pemohon p : pemohonListIndividu) {
                LOG.info("######pemohonListIndividu" + p.getPihak().getIdPihak());
            }
            for (Pemohon p : pemohonListCompany) {
                LOG.info("######pemohonListCompany" + p.getPihak().getIdPihak());
            }
            if (mohonPihakList != null) {
                syer1 = new String[mohonPihakList.size()];
                syer2 = new String[mohonPihakList.size()];
                for (int i = 0; i < mohonPihakList.size(); i++) {
                    PermohonanPihak pp = mohonPihakList.get(i);
                    syer1[i] = String.valueOf(pp.getSyerPembilang());
                    syer2[i] = String.valueOf(pp.getSyerPenyebut());
                }
            }
        }
        if (isDebug) {
            LOG.debug("rehydrate finish");
        }
    }

    private void pihakValidate() {
        List<HakmilikPihakBerkepentingan> senarai = new ArrayList<HakmilikPihakBerkepentingan>();
        String t = "";
        for (int i = 0; i < pihakKepentinganList.size(); i++) {
            Pihak pihak = pihakKepentinganList.get(i).getPihak();
            t = String.valueOf(pihak.getIdPihak());
            for (int j = i + 1; j < pihakKepentinganList.size(); j++) {
                pihak = pihakKepentinganList.get(j).getPihak();
                if (t.equals(String.valueOf(pihak.getIdPihak()))) {
                    senarai.add(pihakKepentinganList.get(j));
                    senarai.add(pihakKepentinganList.get(i));
                    continue;
                }
            }
        }

        if (senarai.size() == 0) {
            addSimpleError("Hakmilik yang dipilih tidak mempunyai tuan tanah yang sama.");
            getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        }

        if (senarai.size() > 0) {
            pihakKepentinganList = senarai;
        }
    }

    private void pihakValidateBerangkai() {
        List<PermohonanPihak> senarai = new ArrayList<PermohonanPihak>();
        String t = "";
        for (int i = 0; i < senaraiPemohonBerangkai.size(); i++) {
            Pihak pihak = senaraiPemohonBerangkai.get(i).getPihak();
            t = String.valueOf(pihak.getIdPihak());
            for (int j = i + 1; j < senaraiPemohonBerangkai.size(); j++) {
                pihak = senaraiPemohonBerangkai.get(j).getPihak();
                if (t.equals(String.valueOf(pihak.getIdPihak()))) {
                    senarai.add(senaraiPemohonBerangkai.get(j));
                    senarai.add(senaraiPemohonBerangkai.get(i));
                    continue;
                }
            }
        }

        if (senarai.size() == 0) {
            addSimpleError("Hakmilik yang dipilih tidak mempunyai tuan tanah yang sama.");
            getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        }

        if (senarai.size() > 0) {
            senaraiPemohonBerangkai = senarai;
        }
    }

    public Resolution doGetPartialPage() {
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik hm = hakmilikManager.findById(idHakmilik);
            pihakKepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hm);
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new JSP("daftar/kemaskini_pb_partial.jsp").addParameter("tab", "true");
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

    public List<HakmilikPihakBerkepentingan> getListphk() {
        return listphk;
    }

    public void setListphk(List<HakmilikPihakBerkepentingan> listphk) {
        this.listphk = listphk;
    }

    public int getSizelistphk() {
        return sizelistphk;
    }

    public void setSizelistphk(int sizelistphk) {
        this.sizelistphk = sizelistphk;
    }

    public List<HakmilikPermohonan> getListPihak() {
        return listPihak;
    }

    public void setListPihak(List<HakmilikPermohonan> listPihak) {
        this.listPihak = listPihak;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getJenisPengenalan2() {
        return jenisPengenalan2;
    }

    public void setJenisPengenalan2(String jenisPengenalan2) {
        this.jenisPengenalan2 = jenisPengenalan2;
    }

    public String getNegeri1() {
        return negeri1;
    }

    public void setNegeri1(String negeri1) {
        this.negeri1 = negeri1;
    }

    public String getNomborPengenalan() {
        return nomborPengenalan;
    }

    public void setNomborPengenalan(String nomborPengenalan) {
        this.nomborPengenalan = nomborPengenalan;
    }

    public String getSuratNegeri1() {
        return suratNegeri1;
    }

    public void setSuratNegeri1(String suratNegeri1) {
        this.suratNegeri1 = suratNegeri1;
    }

    public String getRadio1() {
        return radio1;
    }

    public void setRadio1(String radio1) {
        this.radio1 = radio1;
    }

}
