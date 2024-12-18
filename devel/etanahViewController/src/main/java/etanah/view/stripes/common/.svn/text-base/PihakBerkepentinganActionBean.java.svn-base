/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodWarganegaraDAO;
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
import etanah.model.PermohonanPihakHubungan;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
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
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.HakmilikUrusan;
import etanah.model.KodBangsa;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodWarganegara;
import etanah.model.PermohonanAtasPerserahan;
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
import java.util.Date;
import net.sourceforge.stripes.action.HttpCache;
import org.apache.commons.math.fraction.Fraction;

/**
 *
 * @author md.nurfikri
 */
@HttpCache(allow = false)
@UrlBinding("/pihak_berkepentingan")
public class PihakBerkepentinganActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PihakBerkepentinganActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    @Inject
    HakmilikService hakmilikManager;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
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
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
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
    ListUtil listUtil;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    HakmilikDAO hakmilikDAO;
    private List<PermohonanPihakKemaskini> mohonPihakKemaskiniList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> othersPihakList;
    private List<PermohonanAtasPihakBerkepentingan> mapList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList2;
    private List<PermohonanPihak> senaraiPemohonBerangkai;
    private List<KodBangsa> senaraiKodBangsa;
    private List<KodJenisPihakBerkepentingan> senaraiKodPenerima;
    private List<KodBangsa> senaraiKodBangsaOrang;
    private List<KodBangsa> senaraiKodBangsaSyarikat;
    private Pihak pihak;
    private PihakCawangan pihakCawangan;
    private PihakPengarah pihakPengarah;
    private PermohonanPihak permohonanPihak;
    private PermohonanPihakHubungan permohonanPihakHubungan;
    private Pemohon pemohon;
    private List<Pemohon> pemohonList;
    private List<PermohonanPihak> mohonPihakList;
    private List<PermohonanPihak> mohonPihakPemegangAmanah;
    private List<PihakCawangan> cawanganList;
    private List<PihakPengarah> pengarahList;
    private List<Pihak> pihakByNameList;
    private List<PermohonanPihak> senaraiPihakHubungan;
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
    //used for TA
    private String nama;
    private String nokp;
    private String jeniskp;
    String tarikhLahir;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean tambahCawFlag;
    boolean tambahPengarahFlag;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanAtasPerserahanService mohonAtasSerahService;
    private String idCawangan;
    private String kodNegeri;
    private String namaNegeriBaru;
    private String namaNegeriLama;
    private String jenisPihak;
    private boolean isLayak = true;
    private HakmilikPihakBerkepentingan hakmilikPihak;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/common/senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution getSenaraiHakmilikKepentinganIsmen() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution getSenaraiHakmilikKepentingan() {
        boolean flagMultiple = true;
        if (p.getKodUrusan().getKod().equals("KVST")) {
            flagMultiple = false;
        }
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
                        String idPermohonanSblm = permohonanSebelum.getIdPermohonan();
                        String idHakmilikSblm = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
//                        senaraiPemohonBerangkai = permohonanPihakService.getSenaraiPmohonPihak(idPermohonanSblm);
//                        senaraiPemohonBerangkai = permohonanSebelum.getSenaraiPihak();
                        senaraiPemohonBerangkai = new ArrayList<PermohonanPihak>();
                        List<PermohonanPihak> senarai_tmp = permohonanPihakService.
                                getSenaraiTuanTanahForBerangkai(p.getIdKumpulan(), idPermohonanSblm, null, false, false, p.getKumpulanNo());

                        for (PermohonanPihak pp : senarai_tmp) {
                            if (p.getKodUrusan().getKod().equals("TN")) {
                                if (pp.getJenis().getKod().equals("PG")) {
                                    senaraiPemohonBerangkai.add(pp);
                                }
                            } else {
                                if (pp.getJenis().getKod().equals("PM") || pp.getJenis().getKod().equals("PA")
                                        || pp.getJenis().getKod().equals("PPM")) {
                                    senaraiPemohonBerangkai.add(pp);
                                }
                            }
                        }

                        if (!p.getKodUrusan().getKod().equals("TN")) {
                            List<HakmilikPihakBerkepentingan> senaraiPihak =
                                    hakmilikPihakKepentinganService.senaraiPBtidakBerkaitan(idHakmilikSblm, p.getIdKumpulan(),
                                    p.getIdPermohonan(), false, false);

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
                if (p.getKodUrusan().getKod().equals("PH30B") || p.getKodUrusan().getKod().equals("PH30A")
                        || p.getKodUrusan().getKod().equals("TN")) {
                    pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
                    List<HakmilikPihakBerkepentingan> senaraiPihakTemp = new ArrayList<HakmilikPihakBerkepentingan>();
                    if (p.getSenaraiHakmilik() != null && p.getSenaraiHakmilik().size() > 0) {
                        for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                            Hakmilik h = hp.getHakmilik();
                            if (h == null) {
                                continue;
                            }
//                            if (p.getKodUrusan().getKod().equals("PH30B")) {
//                                senaraiPihakTemp = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), null);
//                            } else {
//                                senaraiPihakTemp = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(h);
//                            }
                            senaraiPihakTemp = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(h);

                            for (HakmilikPihakBerkepentingan hpk : senaraiPihakTemp) {
                                if (hpk == null) {
                                    continue;
                                }
                                pihakKepentinganList.add(hpk);
                            }
                        }
                    }
                }

                if (p.getKodUrusan().getKod().equals("PHMM") || p.getKodUrusan().getKod().equals("JAGAB")
                        || p.getKodUrusan().getKod().equals("KVAK") || p.getKodUrusan().getKod().equals("KVSK")
                        || p.getKodUrusan().getKod().equals("KVPK") || p.getKodUrusan().getKod().equals("KVLK")
                        || p.getKodUrusan().getKod().equals("JPGPJ") || p.getKodUrusan().getKod().equals("GDPJ")
                        || p.getKodUrusan().getKod().equals("GDPJK") || p.getKodUrusan().getKod().equals("PHMMK")) {

                    pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
                    List<HakmilikPihakBerkepentingan> senaraiPihakTemp = new ArrayList<HakmilikPihakBerkepentingan>();
                    List<String> tmp = new ArrayList<String>();

                    if (p.getSenaraiHakmilik() != null && p.getSenaraiHakmilik().size() > 0) {
                        for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                            Hakmilik h = hp.getHakmilik();
                            if (h == null) {
                                continue;
                            }
//                            if (p.getKodUrusan().getKod().equals("PH30B")) {
//                                senaraiPihakTemp = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), null);
//                            } else {
//                                senaraiPihakTemp = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(h);
//                            }
                            if (p.getKodUrusan().getKod().equals("JPGPJ")) {

                                tmp.add("PJ");
                                tmp.add("PJK");
                                tmp.add("PM");
                                tmp.add("PA");
                                senaraiPihakTemp = hakmilikPihakKepentinganService.
                                        findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), tmp);
                            } else if (p.getKodUrusan().getKod().equals("GDPJ")) {

                                tmp.add("PJ");
                                senaraiPihakTemp = hakmilikPihakKepentinganService.
                                        findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), tmp);
                            } else if (p.getKodUrusan().getKod().equals("GDPJK")) {

                                tmp.add("PJK");
                                senaraiPihakTemp = hakmilikPihakKepentinganService.
                                        findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), tmp);
                            } else if (p.getKodUrusan().getKod().equals("PHMM")) {

                                tmp.add("PG");
                                senaraiPihakTemp = hakmilikPihakKepentinganService.
                                        findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), tmp);
                            } else if (p.getKodUrusan().getKod().equals("PHMMK")) {
                                tmp.add("PG");
                                tmp.add("PJ");
                                tmp.add("PJK");
                                tmp.add("PI");
                                tmp.add("PKA");
                                tmp.add("PKD");
                                tmp.add("PKL");
                                tmp.add("PMG");
                                tmp.add("PT");
                                senaraiPihakTemp = hakmilikPihakKepentinganService.
                                        findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), tmp);
                            } //                            else if (p.getKodUrusan().getKod().equals("KVSPC")) {
                            //                                tmp.add("PKS");
                            ////                                senaraiPihakTemp = hakmilikPihakKepentinganService.
                            ////                                        findHakmilikAllPihakActiveByHakmilik(h);
                            //                                senaraiPihakTemp = hakmilikPihakKepentinganService.
                            //                                    findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), tmp);
                            //                            }
                            else if (p.getKodUrusan().getKod().equals("JAGAB")) {
                                tmp.add("JG");
                                senaraiPihakTemp = hakmilikPihakKepentinganService.
                                        findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), tmp);
                            } else {
                                senaraiPihakTemp = hakmilikPihakKepentinganService.
                                        findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), null);
                            }

                            for (HakmilikPihakBerkepentingan hpk : senaraiPihakTemp) {
                                if (hpk == null) {
                                    continue;
                                }
                                pihakKepentinganList.add(hpk);
                            }
                        }
                    }
                }

                if (p.getKodUrusan().getKod().equals("TRPA")
                        || p.getKodUrusan().getKod().equals("GDPJ")
                        || p.getKodUrusan().getKod().equals("GDPJK")
                        || p.getKodUrusan().getKod().equals("PNPAB")
                        || p.getKodUrusan().getKod().equals("KVSPC")) {

                    pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
                    List<PermohonanAtasPerserahan> senaraiUrusanTerlibat = p.getSenaraiPermohonanAtasPerserahan();
                    for (PermohonanAtasPerserahan pap : senaraiUrusanTerlibat) {
                        if (pap == null) {
                            continue;
                        }
                        HakmilikUrusan hu = pap.getUrusan();
                        if (hu == null) {
                            continue;
                        }
                        List<HakmilikPihakBerkepentingan> senaraiTmp = hakmilikPihakKepentinganService.
                                findHakmilikPihakByIdUrusan(hu);
                        for (HakmilikPihakBerkepentingan hpk : senaraiTmp) {

                            if (hpk == null) {
                                continue;
                            }
                            if (p.getKodUrusan().getKod().equals("TRPA")
                                    || p.getKodUrusan().getKod().equals("PNPAB")) {

                                if (hpk.getJenis().getKod().equals("PA")) {
                                    pihakKepentinganList.add(hpk);
                                }
                                mapList = p.getSenaraiPermohonanAtasPihakBerkepentingan();

                            } else {
                                pihakKepentinganList.add(hpk);
                            }
                        }
                    }
                    if (senaraiUrusanTerlibat.isEmpty()) {
                        addSimpleError("Sila pilih Urusan terlebih dahulu.");
                    }
                }

//                if (p.getKodUrusan().getKod().equals("TRPA")) {
//                    pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
//                    List<HakmilikPihakBerkepentingan> senaraiTmp = new ArrayList<HakmilikPihakBerkepentingan>();
//                    List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
//                    for (HakmilikPermohonan hp : senaraiHakmilik) {
//                        if (hp == null || hp.getHakmilik() == null) continue;
//                        Hakmilik hm = hp.getHakmilik();
//                        senaraiTmp = hakmilikPihakKepentinganService.findHakmilikPihakByKod(hm, "PA");
//                        for (HakmilikPihakBerkepentingan hpk : senaraiTmp) {
//                            if (hpk == null) continue;
//                            pihakKepentinganList.add(hpk);
//                        }
//                    }
//                    if (pihakKepentinganList.isEmpty()) {
//                        addSimpleError("Tiada Pemegang Amanah Untuk Hakmilik yang dipilih.");
//                    }
//                }

                if (p.getSenaraiHakmilik().size() > 1
                        && (p.getKodUrusan().getKod().equals("PMT") || p.getKodUrusan().getKod().equals("GD"))) {
//                    pihakValidate(); //validate for multiple hakmilik
                    checkPihakValidate(pihakKepentinganList);
                    if (pihakKepentinganList.isEmpty()) {
                        addSimpleError("Hakmilik yang dipilih tidak mempunyai tuan tanah yang sama.");
                        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
                    }
                }
            }
        }

        if (!pihakKepentinganList.isEmpty()) {
            semakSyer(othersPihakList);
        }

        if (p.getSenaraiHakmilik().size() > 1 && flagMultiple) {
            return new ForwardResolution("/WEB-INF/jsp/daftar/kemaskini_pb.jsp").addParameter("tab", "true");
        } else {
            return new ForwardResolution("/WEB-INF/jsp/daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        }
//            return new ForwardResolution("/WEB-INF/jsp/daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    private int semakSyer(List<HakmilikPihakBerkepentingan> senaraiPihak) {
        Fraction syer = Fraction.ZERO;

        for (HakmilikPihakBerkepentingan hp : senaraiPihak) {
            if (hp == null) {
                continue;
            }
            if (hp.getSyerPembilang() == null || hp.getSyerPenyebut() == null) {
                continue;
            }
            if (hp.getSyerPembilang() == 0 && hp.getSyerPenyebut() == 0) {
                continue;
            }
            syer.add(new Fraction(hp.getSyerPembilang(), hp.getSyerPenyebut()));
        }

        return syer.compareTo(Fraction.ONE);
    }

    public Resolution senaraiHakmilikPindahMilikBerkepentingan() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        List<HakmilikPermohonan> hakmilikPermohonanList = p.getSenaraiHakmilik();
        List<String> senaraiKod = new ArrayList();
        if (hakmilikPermohonanList.size() > 0) {
            Hakmilik h = hakmilikPermohonanList.get(0).getHakmilik();
            String kodPihak = "";
            if (p.getKodUrusan().getKod().equals("PMP") || p.getKodUrusan().getKod().equals("PMPJK")) {
                kodPihak = "PJ"; //pemegang pajakan
//                senaraiKod.add("PJ");
            } else if (p.getKodUrusan().getKod().equals("PMG") || p.getKodUrusan().getKod().equals("PNPBK")) {
                kodPihak = "PG"; //pemegang gadaian
//                senaraiKod.add("PG");
            } else if (p.getKodUrusan().getKod().equals("ROSB")) {
                kodPihak = "ROS"; //pemegang gadaian
//                senaraiKod.add("PG");
            }

            pihakKepentinganList2 = hakmilikPihakKepentinganService.findHakmilikPihakByKod(h, kodPihak);

            if (pihakKepentinganList2.isEmpty()) {
                addSimpleError("Tiada Pihak Berkepentingan.");
            }
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

        Pemohon pemohon = pemohonService.findPemohonByPermohonanPihak(p, pihak);


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
                mohonPihakKemaskini.setPemohon(pemohon);
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
                mohonPihakKemaskini.setPemohon(pemohon);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
            }
            if (jeniskp != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                LOG.debug("save jenisKP");
                mohonPihakKemaskini.setCawangan(hakmilik.getCawangan());
                mohonPihakKemaskini.setNamaMedan("jeniskp");
                mohonPihakKemaskini.setNilaiLama(pihak.getJenisPengenalan().getKod());
                mohonPihakKemaskini.setNilaiBaru(jeniskp);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskini.setPemohon(pemohon);
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
                mohonPihakKemaskini.setPemohon(pemohon);
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
                mohonPihakKemaskini.setPemohon(pemohon);
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
                mohonPihakKemaskini.setPemohon(pemohon);
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
                mohonPihakKemaskini.setPemohon(pemohon);
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
                mohonPihakKemaskini.setPemohon(pemohon);
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
        return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
    }

    public Resolution simpanPemohon() {

        Hakmilik hm = p.getSenaraiHakmilik().get(0).getHakmilik();
        List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();

//        String idPihak = getContext().getRequest().getParameter("idPihak");

        KodJenisPihakBerkepentingan jenis = null;

        String kod = "PM";
        String hPnama = null;
        String hPnoPengenalan = null;
        KodJenisPengenalan hPjenisPengenalan = null;
        KodWarganegara hPkodWarganegara = null;
        String hPalamat1 = null;
        String hPalamat2 = null;
        String hPalamat3 = null;
        String hPalamat4 = null;
        String hPposkod = null;
        KodNegeri hPnegeri = null;

        String[] param = getContext().getRequest().getParameterValues("idPihak");
        for (String idPihak : param) {
            int syerPembilang = 0;
            int syerPenyebut = 0;

            Pihak pi = pihakService.findById(idPihak);

            HakmilikPihakBerkepentingan hpk = hakmilikPihakService.findHakmilikPihakByIdPihakPMPPMG(pi, hm, kod);

            if (hpk == null) {
                PermohonanPihak pp = permohonanPihakService.
                        getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hm.getIdHakmilik(), pi.getIdPihak());
                if (pp != null) {
                    jenis = pp.getJenis();
                    syerPembilang = pp.getSyerPembilang();
                    syerPenyebut = pp.getSyerPenyebut();
                }
            } else {
                jenis = hpk.getJenis();
                syerPembilang = hpk.getSyerPembilang();
                syerPenyebut = hpk.getSyerPenyebut();
                hPnama = hpk.getNama();
                hPjenisPengenalan = hpk.getJenisPengenalan();
                hPnoPengenalan = hpk.getNoPengenalan();
                hPkodWarganegara = hpk.getWargaNegara();
                hPalamat1 = hpk.getAlamat1();
                hPalamat2 = hpk.getAlamat2();
                hPalamat3 = hpk.getAlamat3();
                hPalamat4 = hpk.getAlamat4();
                hPposkod = hpk.getPoskod();
                hPnegeri = hpk.getNegeri();
            }

            Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pi);
            if (pmohon == null) {
                Pemohon pe = new Pemohon();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                if (hpk != null) {
                    pe.setHakmilikPihak(hpk);
                }
                pe.setPermohonan(p);
                pe.setHakmilik(hm);
                pe.setPihak(pi);
                pe.setCawangan(p.getCawangan());
                pe.setSyerPembilang(syerPembilang);
                pe.setSyerPenyebut(syerPenyebut);
                if (jenis != null) {
                    pe.setJenis(jenis);
                }
                Alamat alamatPemohon = new Alamat();
                alamatPemohon.setAlamat1(hPalamat1);
                alamatPemohon.setAlamat2(hPalamat2);
                alamatPemohon.setAlamat3(hPalamat3);
                alamatPemohon.setAlamat4(hPalamat4);
                alamatPemohon.setPoskod(hPposkod);
                alamatPemohon.setNegeri(hPnegeri);
                pe.setAlamat(alamatPemohon);
                pe.setNama(hPnama);
                pe.setJenisPengenalan(hPjenisPengenalan);
                pe.setNoPengenalan(hPnoPengenalan);
                pe.setWargaNegara(hPkodWarganegara);
                senaraiPemohon.add(pe);
            }
        }
        if (senaraiPemohon.size() > 0) {
            pemohonService.saveOrUpdateMultiple(senaraiPemohon);
        }

//        Pihak pi = pihakService.findById(idPihak);
//        Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pi);
//        if (pmohon == null) {
//            Pemohon pe = new Pemohon();
//            InfoAudit ia = new InfoAudit();
//            ia.setDimasukOleh(pguna);
//            ia.setTarikhMasuk(new java.util.Date());
//            pe.setInfoAudit(ia);
//            pe.setPermohonan(p);
//            pe.setPihak(pi);
//            pe.setCawangan(p.getCawangan());
//
//            pemohonService.saveOrUpdate(pe);
//        } else {
//            addSimpleError("Tuan Tanah " + pi.getNama() + " telah memohon. Sila pilih tuan tanah yang lain.");
//        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
//        rehydrate();
        return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
    }

    public Resolution simpanMohonAtasPihak() {
        List<PermohonanAtasPihakBerkepentingan> senaraiPihakAtas = new ArrayList<PermohonanAtasPihakBerkepentingan>();
        String[] param = getContext().getRequest().getParameterValues("idPihak");

        String idHakmilik = getContext().getRequest().getParameter("hakmilik");

        if (p != null) {
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
            for (String idPihak : param) {
                Pihak pi = pihakService.findById(idPihak);
                for (HakmilikPermohonan hp : senaraiHakmilik) {
                    if (hp == null) {
                        continue;
                    }
                    Hakmilik hk = hp.getHakmilik();
                    HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihakPMPPMG(pi, hk, "PA");
                    if (hpk == null) {
                        continue;
                    }

                    PermohonanAtasPihakBerkepentingan pap = new PermohonanAtasPihakBerkepentingan();
                    pap.setInfoAudit(ia);
                    pap.setPermohonan(p);
                    pap.setPihakBerkepentingan(hpk);
                    senaraiPihakAtas.add(pap);
                }

            }
            mapService.save(senaraiPihakAtas);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
        }
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
        return new RedirectResolution(PihakBerkepentinganActionBean.class, "senaraiHakmilikPindahMilikBerkepentingan");
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
        return getSenaraiHakmilikKepentingan();
    }

    public Resolution deleteSelectedPemohon() {
        String[] id = getContext().getRequest().getParameterValues("idPihak");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");

        if (isDebug) {
            LOG.debug("id length = " + id.length);
        }

        if (id.length > 0) {
            pemohonService.deleteSelectedPemohon(id);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
        }
    }

    public Resolution deleteSelectedMohonPihak() {
        String[] id = getContext().getRequest().getParameterValues("idMohonAtas");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");

        if (isDebug) {
            LOG.debug("id length = " + id.length);
        }

        if (id.length > 0) {
            mapService.delete(id);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
        }
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

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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


        return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
    }

    public Resolution getPaparanSenaraiHakmilikKepentingan() {
        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution pihakKepentinganPopup() {
        String urusan = getContext().getRequest().getParameter("urusan");
        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        if (StringUtils.isNotBlank(jenisPB)) {
            getContext().getRequest().setAttribute("jenis", jenisPB);
        }
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        }

        kodNegeri = conf.getProperty("kodNegeri");
        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution pemohonPopup() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        String urusan = getContext().getRequest().getParameter("urusan");
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }
        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution showPihakKepentinganForm() {
//        getContext().getRequest().setAttribute("popup", "false");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("daftar/kemasukan_senarai_tuan_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showPihakKepentinganPajakanForm() {

        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik curr_hakmilik = hakmilikDAO.findById(idHakmilik);
            pihakKepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(curr_hakmilik);
            mohonPihakList = permohonanPihakService.getAllSenaraiPmohonPihakByHakmilikAndMohon(idPermohonan, idHakmilik);
        } else {
            if (p != null) {
                List<HakmilikPermohonan> senaraiHakmilikPermohonan = p.getSenaraiHakmilik();
                if (senaraiHakmilikPermohonan.size() > 0) {
                    Hakmilik hm = senaraiHakmilikPermohonan.get(0).getHakmilik();
                    pihakKepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hm);
                    mohonPihakList = permohonanPihakService.getAllSenaraiPmohonPihakByHakmilikAndMohon(idPermohonan, hm.getIdHakmilik());
                }
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("daftar/kemasukan_pihak_pajakan.jsp").addParameter("tab", "true");
    }

    public Resolution reloadPajakanForm() {

        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik curr_hakmilik = hakmilikDAO.findById(idHakmilik);
            pihakKepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(curr_hakmilik);
            mohonPihakList = permohonanPihakService.getAllSenaraiPmohonPihakByHakmilikAndMohon(idPermohonan, idHakmilik);
        }

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
            pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
            othersPihakList = new ArrayList<HakmilikPihakBerkepentingan>();
            List<HakmilikPihakBerkepentingan> senarai = new ArrayList<HakmilikPihakBerkepentingan>();

            List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hm = hp.getHakmilik();
                senarai = hakmilikPihakKepentinganService.doCheckPihakKaviet(hm.getIdHakmilik());
                for (HakmilikPihakBerkepentingan hpk : senarai) {
                    pihakKepentinganList.add(hpk);
                }
                senarai = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hm);
                for (HakmilikPihakBerkepentingan hpk : senarai) {
                    othersPihakList.add(hpk);
                }
                if (p.getKodUrusan().getKod().equals("PMKMH")
                        || p.getKodUrusan().getKod().equals("KVPK")) {
                    senarai = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(hm.getIdHakmilik(), null);
                    for (HakmilikPihakBerkepentingan hpk : senarai) {
                        pihakKepentinganList.add(hpk);
                    }
                }
            }

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
        return new JSP("common/edit_nama_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditAlamatPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        return new JSP("common/edit_alamat_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pemohonService.findPemohonByPermohonanPihak(p, pihak);

        String idHakmilik = getContext().getRequest().getParameter("hakmilik");

        if (!p.getKodUrusan().getKod().equals("PJT")
                && !p.getKodUrusan().getKod().equals("PJTM")
                && !p.getKodUrusan().getKod().equals("PJBT")
                && !p.getKodUrusan().getKod().equals("PJKBT")
                && !p.getKodUrusan().getKod().equals("PJKT")) {
            if (StringUtils.isNotBlank(idHakmilik)) {
                getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
                getContext().getRequest().setAttribute("hakmilik", idHakmilik);
            }
        }

        return new JSP("common/edit_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPenerima() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
//        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(idPermohonan, Long.valueOf(idPihak));
        getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        if (StringUtils.isNotBlank(idHakmilik)
                && !p.getKodUrusan().getKod().equals("PJT")
                && !p.getKodUrusan().getKod().equals("PJTM")
                && !p.getKodUrusan().getKod().equals("PJBT")
                && !p.getKodUrusan().getKod().equals("PJKBT")
                && !p.getKodUrusan().getKod().equals("PJKT")) {
            permohonanPihak = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik(Long.valueOf(idPihak), idHakmilik, idPermohonan);
            getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);

        } else {
            permohonanPihak = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik(Long.valueOf(idPihak), idHakmilik, idPermohonan);
        }
        return new JSP("common/edit_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPihakKepentinganPopup() throws ParseException {
        String tmp = getContext().getRequest().getParameter("urusan");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        String copyToAll = getContext().getRequest().getParameter("copyToAll");
        Hakmilik hmk = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hmk = hakmilikDAO.findById(idHakmilik);
        } else {
            hmk = p.getSenaraiHakmilik().get(0).getHakmilik();
        }

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
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakBerkepentinganPemegangAmanahForm");
        }

        if (jenisPihak != null) {


            //TODO ubah balik ikut urusan. Cater for urusan
            if (StringUtils.isBlank(idHakmilik)
                    && p.getKodUrusan().getKod().startsWith("KV")) {
                List<PermohonanPihak> senaraiPihakMohon = permohonan.getSenaraiPihak();
                for (PermohonanPihak permohonanPihak : senaraiPihakMohon) {
                    if (permohonanPihak.getPihak().getIdPihak() == pihak.getIdPihak()) {
                        if (permohonanPihak.getJenis().getKod().equals(jenisPihak)) {
                            return new StreamingResolution("text/plain", "2");
                        }
                    }
                }
            }


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
            pihakTemp.setWargaNegara(pihak.getWargaNegara());

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

            for (Pemohon p : senaraiPemohon) {
                if (p == null) {
                    continue;
                }
                LOG.debug("pihak = " + p.getPihak().getIdPihak());
                LOG.debug("hakmilik = " + hmk.getIdHakmilik());
                HakmilikPihakBerkepentingan hm = syerService.findSyerPihakFromHakmilikPihak(p.getPihak().getIdPihak(), hmk.getIdHakmilik());

                if (hm == null) {
                    hm = syerService.findSyerPihakFromHakmilikPihak(p.getPihak().getIdPihak(), hmk.getIdHakmilik(), jenisPihak);
                    if (hm == null) {
                        continue;
                    }
                }
                if (hm.getSyerPembilang() == null || hm.getSyerPenyebut() == null) {
                    continue;
                }
                if (hm.getSyerPembilang() == 0 && hm.getSyerPenyebut() == 0) {
                    continue;
                }
                if (isDebug) {
                    LOG.debug("syer terlibat = " + hm.getSyerPembilang() + "/" + hm.getSyerPenyebut());
                }
                f = f.add(new Fraction(hm.getSyerPembilang(), hm.getSyerPenyebut()));
            }

            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }

            permohonanPihak.setPermohonan(permohonan);
            permohonanPihak.setPihak(pihakTemp);

            Alamat alamatPermohonanPihak = new Alamat();
            alamatPermohonanPihak.setAlamat1(pihak.getAlamat1());
            alamatPermohonanPihak.setAlamat2(pihak.getAlamat2());
            alamatPermohonanPihak.setAlamat3(pihak.getAlamat3());
            alamatPermohonanPihak.setAlamat4(pihak.getAlamat4());
            alamatPermohonanPihak.setPoskod(pihak.getSuratPoskod());
            alamatPermohonanPihak.setNegeri(pihak.getNegeri());

            AlamatSurat suratAlamatPermohonanPihak = new AlamatSurat();
            suratAlamatPermohonanPihak.setAlamatSurat1(pihak.getSuratAlamat1());
            suratAlamatPermohonanPihak.setAlamatSurat2(pihak.getSuratAlamat2());
            suratAlamatPermohonanPihak.setAlamatSurat3(pihak.getSuratAlamat3());
            suratAlamatPermohonanPihak.setAlamatSurat4(pihak.getSuratAlamat4());
            suratAlamatPermohonanPihak.setPoskodSurat(pihak.getSuratPoskod());
            suratAlamatPermohonanPihak.setNegeriSurat(pihak.getSuratNegeri());

            permohonanPihak.setNama(pihak.getNama());
            permohonanPihak.setWargaNegara(pihak.getWargaNegara());
            permohonanPihak.setJenisPengenalan(pihak.getJenisPengenalan());
            permohonanPihak.setNoPengenalan(pihak.getNoPengenalan());
            permohonanPihak.setPekerjaan(pihak.getNoPengenalan());
            permohonanPihak.setAlamat(alamatPermohonanPihak);
            permohonanPihak.setAlamatSurat(suratAlamatPermohonanPihak);

            if (isDebug) {
                LOG.debug("syer terlibat =" + f.toString());
            }
            if (permohonan.getKodUrusan().getKod().equals("PJT")
                    || permohonan.getKodUrusan().getKod().equals("PJKT")) {
                permohonanPihak.setSyerPembilang(1);
                permohonanPihak.setSyerPenyebut(1);
            } else {
                if (f.equals(Fraction.ZERO)) {
                    permohonanPihak.setSyerPembilang(0);
                    permohonanPihak.setSyerPenyebut(0);
                } else {
                    if (permohonan.getKodUrusan().getKod().equals("PNPA")) {
                        permohonanPihak.setSyerPembilang(f.getNumerator());
                        permohonanPihak.setSyerPenyebut(f.getDenominator());
                    } else if (permohonan.getKodUrusan().getKod().equals("TRPA")) {
                        f = Fraction.ZERO;
                        List<PermohonanAtasPihakBerkepentingan> senarai = p.getSenaraiPermohonanAtasPihakBerkepentingan();
                        for (PermohonanAtasPihakBerkepentingan elem : senarai) {
                            if (elem == null || elem.getPihakBerkepentingan() == null) {
                                continue;
                            }
                            HakmilikPihakBerkepentingan hm = elem.getPihakBerkepentingan();
                            f = f.add(new Fraction(hm.getSyerPembilang(), hm.getSyerPenyebut()));
                        }
                        permohonanPihak.setSyerPembilang(f.getNumerator());
                        permohonanPihak.setSyerPenyebut(f.getDenominator());
                    } else {
                        List<PermohonanPihak> senaraiMohonPihak = permohonan.getSenaraiPihak();
                        List<PermohonanPihak> senaraiMohonPihakTmp = null;
                        if (senaraiMohonPihak.size() > 0) {
                            senaraiMohonPihakTmp = new ArrayList<PermohonanPihak>();
                            int jumPemohon = senaraiMohonPihak.size() + 1;
                            f = f.divide(jumPemohon);
                            for (PermohonanPihak pp : senaraiMohonPihak) {
                                pp.setSyerPembilang(f.getNumerator());
                                pp.setSyerPenyebut(f.getDenominator());
                                pp.setInfoAudit(info);
                                senaraiMohonPihakTmp.add(pp);
                            }
                            permohonanPihakService.saveOrUpdate(senaraiMohonPihakTmp);
                        }
                        permohonanPihak.setSyerPembilang(f.getNumerator());
                        permohonanPihak.setSyerPenyebut(f.getDenominator());
                    }
                }
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
//            if (permohonan.getSenaraiHakmilik().size() > 0) {
//                permohonanPihak.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
//            }
            permohonanPihak.setHakmilik(hmk);
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

            if (StringUtils.isNotBlank(copyToAll)) {
                LOG.debug("copy to all");
                for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                    if (hp == null || hp.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik hm = hp.getHakmilik();
                    LOG.debug("id hakmilik = " + hmk.getIdHakmilik());
                    LOG.debug("id hakmilik = " + hm.getIdHakmilik());
                    if (hmk.getIdHakmilik().equals(hm.getIdHakmilik())) {
                        continue;
                    }

                    PermohonanPihak pp = new PermohonanPihak();
                    pp.setCawangan(permohonanPihak.getCawangan());
                    pp.setHakmilik(hm);
                    pp.setInfoAudit(info);
                    pp.setJenis(permohonanPihak.getJenis());
                    pp.setSyerPembilang(permohonanPihak.getSyerPembilang());
                    pp.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
                    pp.setPihak(permohonanPihak.getPihak());
                    pp.setPermohonan(permohonan);
                    pp.setUmur(permohonanPihak.getUmur());
                    pp.setPekerjaan(permohonanPihak.getPekerjaan());
                    pp.setStatusKahwin(permohonanPihak.getStatusKahwin());
                    permohonanPihakService.saveOrUpdate(pp);

                    if (permohonan.getKodUrusan().getKod().equals("TRPA")) {

                        List<PermohonanAtasPihakBerkepentingan> senarai = mapService.findByPermohonanList(permohonan);

                        for (PermohonanAtasPihakBerkepentingan pap : senarai) {
                            if (pap == null) {
                                continue;
                            }
                            HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.
                                    findHakmilikPihakByIdPihakPMPPMG(pap.getPihakBerkepentingan().getPihak(), hm, "PA");
                            if (hpk == null) {
                                continue;
                            }
                            pap.setPermohonanPihak(pp);
                            mapService.save(pap);

                        }

                    }

                }
            }

            if (pihakCawangan != null) {
                pihakCawangan.setIbupejabat(pihakTemp);
                pihakCawangan.setInfoAudit(info);
                pihakService.saveOrUpdatePihakCawangan(pihakCawangan);
            }

            if (permohonan.getKodUrusan().getKod().equals("TRPA")) {
//                PermohonanAtasPihakBerkepentingan pap = mapService.findByPermohonan(permohonan);
                List<PermohonanAtasPihakBerkepentingan> senarai = mapService.findByPermohonanList(permohonan);

                for (PermohonanAtasPihakBerkepentingan pap : senarai) {
                    if (pap == null) {
                        continue;
                    }
                    HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.
                            findHakmilikPihakByIdPihakPMPPMG(pap.getPihakBerkepentingan().getPihak(), hmk, "PA");
                    if (hpk == null) {
                        continue;
                    }
                    pap.setPermohonanPihak(permohonanPihak);
                    mapService.save(pap);

                }
//                if (pap != null) {
//                    pap.setPermohonanPihak(permohonanPihak);
//                    mapService.save(pap);
//                }
            }


            if (StringUtils.isNotBlank(idHakmilik)
                    && (!permohonan.getKodUrusan().getKod().equals("PJT")
                    && !permohonan.getKodUrusan().getKod().equals("PJTM")
                    && !permohonan.getKodUrusan().getKod().equals("PJBT")
                    && !permohonan.getKodUrusan().getKod().equals("PJKBT")
                    && !permohonan.getKodUrusan().getKod().equals("PJKT"))) {
                LOG.debug("returning to doGetPartialPage");
                return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
            }


            String[] kp = getContext().getRequest().getParameterValues("kpPA");
            String[] noKP = getContext().getRequest().getParameterValues("noKpPA");
            String[] namaPA = getContext().getRequest().getParameterValues("namaPA");
            String[] add1PA = getContext().getRequest().getParameterValues("add1PA");
            String[] add2PA = getContext().getRequest().getParameterValues("add2PA");
            String[] add3PA = getContext().getRequest().getParameterValues("add3PA");
            String[] add4PA = getContext().getRequest().getParameterValues("add4PA");
            String[] poskodPA = getContext().getRequest().getParameterValues("poskodPA");
            String[] negeriPA = getContext().getRequest().getParameterValues("negeriPA");

            if (etanah.util.StringUtils.isNotBlank(kp)
                    && etanah.util.StringUtils.isNotBlank(noKP)) {

                Fraction frac = Fraction.ZERO;
                if (permohonanPihak.getSyerPembilang() != 0 && permohonanPihak.getSyerPenyebut() != 0) {
                    frac = new Fraction(permohonanPihak.getSyerPembilang(), permohonanPihak.getSyerPenyebut());
                    frac = frac.divide(kp.length);
                }


                for (int i = 0; i < kp.length; i++) {

                    String kp_ = kp[i];
                    String noKp_ = noKP[i];
                    if (StringUtils.isNotBlank(kp_) && StringUtils.isNotBlank(noKp_)) {
                        Pihak phk = pihakService.findPihak(kp_, noKp_);
                        if (phk == null) {
                            phk = new Pihak();
                            phk.setAlamat1(StringUtils.isNotBlank(add1PA[i]) ? add1PA[i] : "");
                            phk.setAlamat2(StringUtils.isNotBlank(add2PA[i]) ? add2PA[i] : "");
                            phk.setAlamat3(StringUtils.isNotBlank(add3PA[i]) ? add3PA[i] : "");
                            phk.setAlamat4(StringUtils.isNotBlank(add4PA[i]) ? add4PA[i] : "");
                            phk.setPoskod(StringUtils.isNotBlank(poskodPA[i]) ? poskodPA[i] : "");
                            phk.setNegeri(StringUtils.isNotBlank(negeriPA[i]) ? kodNegeriDAO.findById(negeriPA[i]) : null);
                            phk.setNama(StringUtils.isNotBlank(namaPA[i]) ? namaPA[i] : "");
                            phk.setWargaNegara(kodWarganegaraDAO.findById("MAL"));
                            phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp_));
                            phk.setNoPengenalan(noKp_);
                            phk.setInfoAudit(info);
                        }

                        PermohonanPihak pp = new PermohonanPihak();
                        pp.setInfoAudit(info);
                        pp.setCawangan(permohonan.getCawangan());
                        pp.setPermohonan(permohonan);
                        KodJenisPihakBerkepentingan jenis = kodJenisPihakBerkepentinganDAO.findById("WAR");
                        pp.setJenis(jenis);
                        pp.setHakmilik(hmk);
                        if (frac == Fraction.ZERO) {
                            pp.setSyerPembilang(0);
                            pp.setSyerPenyebut(0);
                        } else {
                            pp.setSyerPembilang(frac.getNumerator());
                            pp.setSyerPenyebut(frac.getDenominator());
                        }

//                        pp.setKaitan(permohonanPihakHubungan.getKaitan());
                        pp.setPihak(phk);
                        pihakService.saveOrUpdatePihakKepentinganPihak(phk, pp);
                    }
                }
            }

//            if (permohonanPihakHubungan != null) {
////                permohonanPihakHubungan.setPihak(permohonanPihak);
////                permohonanPihakHubungan.setCawangan(p.getCawangan());
////                permohonanPihakHubungan.setKaitan("DipegangAmanah");
////                permohonanPihakHubungan.setInfoAudit(info);
////                pihakService.saveOrUpdatePihakHubungan(permohonanPihakHubungan);
//                Pihak phk = pihakService.findPihak(permohonanPihakHubungan.getJenisPengenalan().getKod(), permohonanPihakHubungan.getNoPengenalan());
//                if (phk == null) {
//                    phk = new Pihak();
//                    phk.setAlamat1(permohonanPihakHubungan.getAlamat1());
//                    phk.setAlamat2(permohonanPihakHubungan.getAlamat2());
//                    phk.setAlamat3(permohonanPihakHubungan.getAlamat3());
//                    phk.setAlamat4(permohonanPihakHubungan.getAlamat4());
//                    phk.setPoskod(permohonanPihakHubungan.getPoskod());
//                    phk.setNegeri(permohonanPihakHubungan.getNegeri());
//                    phk.setNama(permohonanPihakHubungan.getNama());
//                    phk.setWargaNegara(kodWarganegaraDAO.findById("MAL"));
//                    phk.setJenisPengenalan(permohonanPihakHubungan.getJenisPengenalan());
//                    phk.setNoPengenalan(permohonanPihakHubungan.getNoPengenalan());
//                    phk.setInfoAudit(info);
//                }
//                PermohonanPihak pp = new PermohonanPihak();
//                pp.setInfoAudit(info);
//                pp.setCawangan(permohonan.getCawangan());
//                pp.setPermohonan(permohonan);
//                KodJenisPihakBerkepentingan jenis = kodJenisPihakBerkepentinganDAO.findById("WAR");
//                pp.setJenis(jenis);
//                pp.setHakmilik(hmk);
//                pp.setSyerPembilang(permohonanPihak.getSyerPembilang());
//                pp.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
//                pp.setKaitan(permohonanPihakHubungan.getKaitan());
//                pp.setPihak(phk);
//                pihakService.saveOrUpdatePihakKepentinganPihak(phk, pp);
//            }

            if (permohonan.getKodUrusan().getKod().equals("PJT")
                    || permohonan.getKodUrusan().getKod().equals("PJTM")
                    || permohonan.getKodUrusan().getKod().equals("PJBT")
                    || permohonan.getKodUrusan().getKod().equals("PJKBT")
                    || permohonan.getKodUrusan().getKod().equals("PJKT")) {
                LOG.debug("returning to showPihakKepentinganPajakanForm");
//                return new RedirectResolution("/pihak_berkepentingan?showPihakKepentinganPajakanForm").addParameter("tab", "true");
                return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakKepentinganPajakanForm")
                        .addParameter("idHakmilik", idHakmilik)
                        .addParameter("tab", "true");

            } else if (permohonan.getKodUrusan().getKod().equals("TEN") || permohonan.getKodUrusan().getKod().equals("TENBT")) {
                return new RedirectResolution("/pihak_berkepentingan?showPihakKepentinganTenansiForm").addParameter("tab", "true");
            } else if (permohonan.getKodUrusan().getKod().equals("JDGPJ")) {
                return new RedirectResolution(PerintahJualPihakBerkepentingan.class).addParameter("tab", "true");
            }

            if (StringUtils.isNotBlank(tmp)) {
                return new RedirectResolution("/pihak_berkepentingan?showPihakKepentinganForm").addParameter("tab", "true");
            } else {
                if (permohonan.getKodUrusan().getKod().equals("PMG")
                        || permohonan.getKodUrusan().getKod().equals("PMP") || permohonan.getKodUrusan().getKod().equals("PNPBK")
                        || permohonan.getKodUrusan().getKod().equals("ROSB")) {
                    return new RedirectResolution("/pihak_berkepentingan?senaraiHakmilikPindahMilikBerkepentingan").addParameter("tab", "true");
                } else if (permohonan.getKodUrusan().getKod().equals("PNPA") || permohonan.getKodUrusan().getKod().equals("PNPAB")) {
                    return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakBerkepentinganPemegangAmanahForm").addParameter("tab", "true");
                } else {
                    return new RedirectResolution("/pihak_berkepentingan?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
                }
            }
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanPemohonPopup() throws ParseException {

        Permohonan permohonan = null;
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");

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

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
            pemohonService.simpanPihakPemohon(pihakTemp, pmohon);
            if (StringUtils.isNotBlank(idHakmilik)) {
                return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
            }
            return new RedirectResolution("/pihak_berkepentingan?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution cariPihak() {

        String tmp = getContext().getRequest().getParameter("tuanTanah");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");


        if (!p.getKodUrusan().getKod().equals("PJT")
                && !p.getKodUrusan().getKod().equals("PJTM")
                && !p.getKodUrusan().getKod().equals("PJBT")
                && !p.getKodUrusan().getKod().equals("PJKBT")
                && !p.getKodUrusan().getKod().equals("PJKT")) {
            if (StringUtils.isNotBlank(idHakmilik)) {
                getContext().getRequest().setAttribute("hakmilik", idHakmilik);
                getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
            }
        }

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
            LOG.debug("************");
            String kodUrusan = p.getKodUrusan().getKod();
            LOG.debug("************ kod urusan = " + kodUrusan);
            if (kodUrusan.equals("TMAML") || kodUrusan.equals("TMAMD") || kodUrusan.equals("TMAMF")) {
                //if TMAML / TMAMD , jenis pihak must be pendaftar(PP)
//                KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById("PP");
                jenisPihak = "PP";
//                if (permohonanPihak == null) {
//                    permohonanPihak = new PermohonanPihak();
//                }
//                permohonanPihak.setJenis(kod);
            }
        }

        if (pihak != null) {

            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

//                if (pihakSearch != null && StringUtils.isBlank(idHakmilik)) {
//                    if (!(mohonPihakList.isEmpty())) {
//                        for (PermohonanPihak pp : mohonPihakList) {
//                            if (pp.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
//
//                                duplicateFlag = true;
//                                break;
//                            }
//                        }
//                    }
//                }

                if (!duplicateFlag) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        pengarahList = pihak.getSenaraiPengarah();
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else if (pihak.getNama() != null) {
                String kodPengenalan = "";
                if (pihak.getJenisPengenalan() != null) {
                    kodPengenalan = pihak.getJenisPengenalan().getKod();
                }
                pihakByNameList = new ArrayList<Pihak>();
                pihakByNameList = pihakService.findPihakByName(pihak.getNama(), kodPengenalan);

                if (pihakByNameList.isEmpty()) {
                    addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                    cariFlag = true;
                    tiadaDataFlag = true;
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsa();
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
                }
            } else {

                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan Atau Nama.");
        }

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution selectName() {

        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakService.findById(idPihak);
        cariFlag = true;
        tiadaDataFlag = false;
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");

        if (!p.getKodUrusan().getKod().equals("PJT")
                && !p.getKodUrusan().getKod().equals("PJTM")
                && !p.getKodUrusan().getKod().equals("PJBT")
                && !p.getKodUrusan().getKod().equals("PJKBT")
                && !p.getKodUrusan().getKod().equals("PJKT")) {
            if (StringUtils.isNotBlank(idHakmilik)) {
                getContext().getRequest().setAttribute("hakmilik", idHakmilik);
                getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
            }
        }

        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution cariPihakPemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");


        if (!p.getKodUrusan().getKod().equals("PJT")
                && !p.getKodUrusan().getKod().equals("PJTM")
                && !p.getKodUrusan().getKod().equals("PJBT")
                && !p.getKodUrusan().getKod().equals("PJKBT")
                && !p.getKodUrusan().getKod().equals("PJKT")) {
            if (StringUtils.isNotBlank(idHakmilik)) {
                getContext().getRequest().setAttribute("hakmilik", idHakmilik);
                getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
            }
        }



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

                if (pihakSearch != null && StringUtils.isBlank(idHakmilik)) {
                    if (!(pemohonList.isEmpty())) {
                        for (Pemohon pem : pemohonList) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {

                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (!duplicateFlag) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        pengarahList = pihak.getSenaraiPengarah();
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
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

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution cariSemula() {
        pihak = new Pihak();
        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution semakSyer() {
        String result = "Pembahagian tanah berjaya.";
        int i = 0;
//        List<PermohonanPihak> permPihak = new ArrayList<PermohonanPihak>();
//        for (PermohonanPihak pp : mohonPihakList) {
////            Fraction f = new Fraction(Integer.parseInt(syer1[i]), Integer.parseInt(syer2[i]));
////            pp.setSyer(f.doubleValue());
//            pp.setSyerPembilang(Integer.parseInt(syer1[i]));
//            pp.setSyerPenyebut(Integer.parseInt(syer2[i]));
//            permPihak.add(pp);
//            i = i + 1;
//        }
//        permohonanPihakService.saveOrUpdate(permPihak);

        List<HakmilikPermohonan> senaraiPermohonan = p.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : senaraiPermohonan) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            try {
                int r = syerService.doValidateSyerPortion(idPermohonan, hm.getIdHakmilik());
                if (r < 0) {
                    result = "Jumlah pembahagian tanah tidak mencukupi.";
                    break;
                } else if (r > 0) {
                    result = "Jumlah pembahagian tanah melebihi daripada bahagian pemohon.";
                    break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return new StreamingResolution("text/plain", "Pembahagian Tanah Gagal.");
            }
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution semakSyerByIdHakmilik() {
        String result = "Pembahagian tanah berjaya.";
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Hakmilik hm = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hm = hakmilikDAO.findById(idHakmilik);
        } else {
            hm = p.getSenaraiHakmilik().get(0).getHakmilik();
        }

        int i = 0;
        List<PermohonanPihak> permPihak = new ArrayList<PermohonanPihak>();
        for (PermohonanPihak pp : mohonPihakList) {
            if (pp == null || pp.getHakmilik() == null) {
                continue;
            }
            Hakmilik h = pp.getHakmilik();
            if (!h.getIdHakmilik().equals(idHakmilik)) {
                continue;
            }
//            Fraction f = new Fraction(Integer.parseInt(syer1[i]), Integer.parseInt(syer2[i]));
//            pp.setSyer(f.doubleValue());
            pp.setSyerPembilang(Integer.parseInt(syer1[i]));
            pp.setSyerPenyebut(Integer.parseInt(syer2[i]));
            permPihak.add(pp);
            i = i + 1;
        }
        permohonanPihakService.saveOrUpdate(permPihak);

        try {
            int r = syerService.doValidateSyerPortion(idPermohonan, hm.getIdHakmilik());
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
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Hakmilik hm = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hm = hakmilikDAO.findById(idHakmilik);
        } else {
            hm = p.getSenaraiHakmilik().get(0).getHakmilik();
        }

        //TODO : multiple hakmilik
//        Hakmilik hm = p.getSenaraiHakmilik().get(0).getHakmilik();
//        List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
        Fraction sumAllPemohon = Fraction.ZERO;
        Fraction samaRata = Fraction.ZERO;

        if (hm != null) {
            LOG.debug("************");

            for (Pemohon pemohon : pemohonList) {
                HakmilikPihakBerkepentingan hmk = hakmilikPihakKepentinganService.findHakmilikPihakByPihak(pemohon.getPihak().getIdPihak(), hm);
                if (hmk != null) {
                    LOG.debug("pihak = " + pemohon.getPihak().getNama());
                    LOG.debug("pembilang =" + hmk.getSyerPembilang());
                    LOG.debug("penyebut =" + hmk.getSyerPenyebut());

                    sumAllPemohon = sumAllPemohon.add(new Fraction(hmk.getSyerPembilang(), hmk.getSyerPenyebut()));
                    continue;
                } else {

                    PermohonanPihak pp = permohonanPihakService
                            .getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hm.getIdHakmilik(), pemohon.getPihak().getIdPihak());
                    if (pp != null) {
                        sumAllPemohon = sumAllPemohon.add(new Fraction(pp.getSyerPembilang(), pp.getSyerPenyebut()));
                        continue;
                    }
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
            int pemohonList = 0;

            for (PermohonanPihak pp : mohonPihakList) {
                if (pp == null || pp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik h = pp.getHakmilik();
                if (h.getIdHakmilik().equals(hm.getIdHakmilik())) {
                    pemohonList++;
                }
            }
            if (pemohonList > 0) {
                List<PermohonanPihak> senarai = new ArrayList<PermohonanPihak>();
                samaRata = sumAllPemohon.divide(pemohonList);
                for (PermohonanPihak pp : mohonPihakList) {
                    if (pp == null || pp.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik h = pp.getHakmilik();
                    if (h.getIdHakmilik().equals(hm.getIdHakmilik())) {
                        pp.setSyerPembilang(samaRata.getNumerator());
                        pp.setSyerPenyebut(samaRata.getDenominator());
                        senarai.add(pp);
                    }
                }
                permohonanPihakService.saveOrUpdate(senarai);
            }

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
            pp.setJumlahPembilang(Integer.parseInt(s1));
            pp.setJumlahPenyebut(Integer.parseInt(s2));
            permohonanPihakService.saveOrUpdate(pp);
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanPihak() {
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();

            Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());
            if (pihak.getSuratNegeri() != null) {
                KodNegeri kn = kodNegeriDAO.findById(pihak.getSuratNegeri().getKod());
                pihakTemp.setSuratNegeri(kn);
            }

            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pihakTemp.setInfoAudit(infoAudit);

            pihakService.saveOrUpdate(pihakTemp);

            if (!p.getKodUrusan().getKod().equals("TRPA")) {
                Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pihakTemp);
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
                if (pihakTemp.getJenisPengenalan() != null) {
                    if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {

                        if (pemohon != null) {
                            pmohon.setStatusKahwin(pemohon.getStatusKahwin());
                            pmohon.setPekerjaan(pemohon.getPekerjaan());
                            pmohon.setUmur(pemohon.getUmur());
                            pmohon.setPendapatan(pemohon.getPendapatan());
                            pmohon.setTanggungan(pemohon.getTanggungan());
                        }
                    }
                }

                pemohonService.saveOrUpdate(pmohon);
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            if (StringUtils.isNotBlank(idHakmilik)) {
                return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
            } else {
//                return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
                return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
            }
        }
        tx.commit();
        addSimpleMessage("Kemaskini Data Berjaya.");
        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
//            return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
        }
    }

    public Resolution simpanEditPenerima() {
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            Pihak pihakTemp = new Pihak();
            pihakTemp = pihakDAO.findById(pihak.getIdPihak());
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());

            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pihakTemp.setInfoAudit(infoAudit);

            pihakService.saveOrUpdate(pihakTemp);

            PermohonanPihak perPihak = new PermohonanPihak();
            if (StringUtils.isNotBlank(idHakmilik)) {
                perPihak = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik(pihak.getIdPihak(), idHakmilik, idPermohonan);
            } else {
                perPihak = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(idPermohonan, pihak.getIdPihak());
            }


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

            perPihak.setJenis(permohonanPihak.getJenis());

            if (pihakTemp.getJenisPengenalan() != null) {
                if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {
                    perPihak.setStatusKahwin(permohonanPihak.getStatusKahwin());
                    perPihak.setPekerjaan(permohonanPihak.getPekerjaan());
                    perPihak.setUmur(permohonanPihak.getUmur());
                    perPihak.setPendapatan(permohonanPihak.getPendapatan());
                    perPihak.setTangungan(permohonanPihak.getTangungan());
                }
            }

            permohonanPihakService.saveOrUpdate(perPihak);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            if (StringUtils.isNotBlank(idHakmilik)) {
                return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
            } else {
//                return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
                return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
            }
        }
        tx.commit();
        addSimpleMessage("Kemaskini Data Berjaya.");
        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            if (p.getKodUrusan().getKod().startsWith("PJ")) {
                return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakKepentinganPajakanForm");
            } else {
                return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
            }
            //            return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        }
    }

    public Resolution backCawangan() {

        pihak = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
        cariFlag = true;
        tiadaDataFlag = false;

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution backCawanganPemohon() {

        pihak = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        cariFlag = true;
        tiadaDataFlag = false;
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                }
                if (pihak.getSenaraiPengarah() != null) {
                    pengarahList = pihak.getSenaraiPengarah();
                }
            }
        }
        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                    pengarahList = pihak.getSenaraiPengarah();
                }
            }
        }
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution editCawangan() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution editCawanganPemohon() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution deletePengarah() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        cariFlag = true;

        if (idPengarah != null) {

            PihakPengarah pPengarah = pihakService.findPengarahById(idPengarah);
            Long id = pPengarah.getPihak().getIdPihak();

            if (pPengarah != null) {
                pihakService.deletePengarah(pPengarah);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                }
                if (pihak.getSenaraiPengarah() != null) {
                    pengarahList = pihak.getSenaraiPengarah();
                }
            }
        }
        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution deletePengarahPemohon() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        cariFlag = true;

        if (idPengarah != null) {

            PihakPengarah pPengarah = pihakService.findPengarahById(idPengarah);
            Long id = pPengarah.getPihak().getIdPihak();

            if (pPengarah != null) {
                pihakService.deletePengarah(pPengarah);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                }
                if (pihak.getSenaraiPengarah() != null) {
                    pengarahList = pihak.getSenaraiPengarah();
                }
            }
        }
        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution editPengarah() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        tambahPengarahFlag = true;
        pihakPengarah = new PihakPengarah();
        pihakPengarah = pihakService.findPengarahById(idPengarah);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution editPengarahPemohon() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        tambahPengarahFlag = true;
        pihakPengarah = new PihakPengarah();
        pihakPengarah = pihakService.findPengarahById(idPengarah);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

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
        tambahPengarahFlag = true;
        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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
        }

        if (pihak.getSenaraiPengarah() != null) {
            pengarahList = pihak.getSenaraiPengarah();
        }

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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
        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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
            return new RedirectResolution("/pihak_berkepentingan?getTambahCawanganPemohon&idPihak=" + id).addParameter("popup", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahCawanganPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        cariFlag = true;

        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));

        cawanganList = pihak.getSenaraiCawangan();

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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
            return new RedirectResolution("/pihak_berkepentingan?getTambahPengarahPenerima&idPihak=" + id + "&jenisPB=" + jenisPihak).addParameter("popup", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahPengarahPenerima() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());

        if (pihak.getSenaraiCawangan() != null) {
            cawanganList = pihak.getSenaraiCawangan();
        }

        if (pihak.getSenaraiPengarah() != null) {
            pengarahList = pihak.getSenaraiPengarah();
        }

        return new JSP("daftar/kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution viewPihakDetail() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String jenis = getContext().getRequest().getParameter("jenis");

        pihak = pihakService.findById(idPihak);
        pengarahList = pihak.getSenaraiPengarah();

        if (jenis.equals("penerima")) {
            permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(p.getIdPermohonan(), Long.parseLong(idPihak));
            if (permohonanPihak.getPihakCawangan() != null) {
                pihakCawangan = pihakCawanganDAO.findById(permohonanPihak.getPihakCawangan().getIdPihakCawangan());
                cawanganList = new ArrayList<PihakCawangan>();
                cawanganList.add(pihakCawangan);
            }
            getContext().getRequest().setAttribute("penerima", Boolean.TRUE);
        }

        if (jenis.equals("pemohon")) {
            pemohon = pemohonService.findPemohonByPermohonanPihak(p, pihak);
            getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        }

        if (jenis.equals("tuanTanah")) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        return new ForwardResolution("/WEB-INF/jsp/common/paparan_pihak.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!deletePemohon", "!deletePemohon2", "!deleteSelectedPemohon"})
    public void rehydrate() {
        if (isDebug) {
            LOG.debug("rehydrate start");
        }
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        Hakmilik hakm = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hakm = hakmilikDAO.findById(idHakmilik);
        }

        kodNegeri = conf.getProperty("kodNegeri");

        p = permohonanService.findById(idPermohonan);
        if (p != null) {
//            p = permohonanService.findById(idPermohonan);
//            idPermohonan = p.getIdPermohonan();
            List<HakmilikPermohonan> hakmilikPermohonanList = p.getSenaraiHakmilik();
            KodNegeri negeriBaru = new KodNegeri();
            KodNegeri negeriLama = new KodNegeri();
            mohonPihakKemaskiniList = p.getSenaraiPihakKemaskini();
            if (mohonPihakKemaskiniList.size() > 0) {
                mohonPihakKemaskini = p.getSenaraiPihakKemaskini().get(0);
                for (PermohonanPihakKemaskini mohonPihakKemaskiniBaru : mohonPihakKemaskiniList) {
                    if (mohonPihakKemaskiniBaru.getNamaMedan().equals("rumahNegeri.kod")) {
                        if (StringUtils.isNotBlank(mohonPihakKemaskiniBaru.getNilaiBaru())) {
                            negeriBaru = kodNegeriDAO.findById(mohonPihakKemaskiniBaru.getNilaiBaru());
                            namaNegeriBaru = negeriBaru.getNama();
                        }

                        if (StringUtils.isNotBlank(mohonPihakKemaskiniBaru.getNilaiLama())) {
                            negeriLama = kodNegeriDAO.findById(mohonPihakKemaskiniBaru.getNilaiLama());
                            namaNegeriLama = negeriLama.getNama();
                        }
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

                    if (hakm != null) {
                        if (!hk.getIdHakmilik().equals(hakm.getIdHakmilik())) {
                            continue;
                        }
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
            if (p.getKodUrusan().getKod().equals("PAADT")
                    || p.getKodUrusan().getKod().equals("CGADT")
                    || p.getKodUrusan().getKod().equals("PMADT")
                    || p.getKodUrusan().getKod().equals("TMADT")) {
                mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByNotKod(idPermohonan, "WAR");
            } else {
                if (hakm != null) {
                    mohonPihakList = permohonanPihakService.getSenaraiMohonPihakForMultipleHakmilik(idPermohonan, idHakmilik);
                } else {
                    List<PermohonanPihak> senaraiPemohonPihak = p.getSenaraiPihak();
                    for (PermohonanPihak elem : senaraiPemohonPihak) {
                        if (elem == null || elem.getJenis().getKod().equals("WAR")) {
                            continue;
                        }
                        mohonPihakList.add(elem);
                    }
                }
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

            pemohonList = p.getSenaraiPemohon();

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
        boolean flag = false;
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
        if (isDebug) {
            LOG.debug("id hakmilik =" + idHakmilik);
            LOG.debug("id permohonan =" + idPermohonan);
        }
        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik hm = hakmilikDAO.findById(idHakmilik);

            String idKump = p.getIdKumpulan();
            if (StringUtils.isNotBlank(idKump)) {
                senaraiPemohonBerangkai = new ArrayList<PermohonanPihak>();
                if (p.getPermohonanSebelum() != null) {

                    Permohonan permohonanSebelum = p.getPermohonanSebelum();
                    String idPermohonanSblm = permohonanSebelum.getIdPermohonan();

                    List<PermohonanPihak> senarai_tmp = permohonanPihakService
                            .getSenaraiTuanTanahForBerangkai(p.getIdKumpulan(), idPermohonanSblm, idHakmilik, false, false, p.getKumpulanNo());

                    LOG.debug("senarai_tmp size =" + senarai_tmp.size());

                    for (PermohonanPihak pp : senarai_tmp) {
                        if (p.getKodUrusan().getKod().equals("TN")) {
                            if (pp.getJenis().getKod().equals("PG")) {
                                senaraiPemohonBerangkai.add(pp);
                            }
                        } else {
                            if (pp.getJenis().getKod().equals("PM") || pp.getJenis().getKod().equals("PA")
                                    || pp.getJenis().getKod().equals("PPM")) {
                                senaraiPemohonBerangkai.add(pp);
                            }
                        }
                    }

                    if (!p.getKodUrusan().getKod().equals("TN")) {
                        List<HakmilikPihakBerkepentingan> senaraiPihak =
                                hakmilikPihakKepentinganService.senaraiPBtidakBerkaitan(idHakmilik, p.getIdKumpulan(),
                                p.getIdPermohonan(), false, false);

                        LOG.debug("senaraiPihak size =" + senaraiPihak.size());

//                        if(senaraiPemohonBerangkai == null){
//                            senaraiPemohonBerangkai = new ArrayList<PermohonanPihak>();
//                        }

                        for (HakmilikPihakBerkepentingan hpb : senaraiPihak) {
                            PermohonanPihak pp = new PermohonanPihak();
                            pp.setPihak(hpb.getPihak());
                            pp.setSyerPembilang(hpb.getSyerPembilang());
                            pp.setSyerPenyebut(hpb.getSyerPenyebut());
                            pp.setHakmilik(hm);
                            pp.setJenis(hpb.getJenis());
                            senaraiPemohonBerangkai.add(pp);
                        }
                    }

                    LOG.debug("size berangkai = " + senaraiPemohonBerangkai.size());

                    if (senaraiPemohonBerangkai.size() > 0) {
                        getContext().getRequest().setAttribute("berangkai", Boolean.TRUE);
                    }
                } else {
                    List<PermohonanPihak> senarai_tmp = permohonanPihakService
                            .getSenaraiTuanTanahForBerangkai(p.getIdKumpulan(), null, idHakmilik, false, false, p.getKumpulanNo());

                    LOG.debug("senarai_tmp size =" + senarai_tmp.size());

                    for (PermohonanPihak pp : senarai_tmp) {
                        if (p.getKodUrusan().getKod().equals("TN")) {
                            if (pp.getJenis().getKod().equals("PG")) {
                                senaraiPemohonBerangkai.add(pp);
                            }
                        } else if (p.getKodUrusan().getKod().equals("TRPA")) {
                            if (pp.getJenis().getKod().equals("PA")) {
                                senaraiPemohonBerangkai.add(pp);
                            }
                        } else {
                            if (pp.getJenis().getKod().equals("PM") || pp.getJenis().getKod().equals("PA")
                                    || pp.getJenis().getKod().equals("PPM")) {
                                senaraiPemohonBerangkai.add(pp);
                            }
                        }
                    }

                    if (!p.getKodUrusan().getKod().equals("TN")) {
                        List<HakmilikPihakBerkepentingan> senaraiPihak =
                                hakmilikPihakKepentinganService.senaraiPBtidakBerkaitan(idHakmilik, p.getIdKumpulan(),
                                p.getIdPermohonan(), false, false);

                        LOG.debug("senaraiPihak size =" + senaraiPihak.size());

//                        if(senaraiPemohonBerangkai == null){
//                            senaraiPemohonBerangkai = new ArrayList<PermohonanPihak>();
//                        }

                        for (HakmilikPihakBerkepentingan hpb : senaraiPihak) {
                            PermohonanPihak pp = new PermohonanPihak();
                            pp.setPihak(hpb.getPihak());
                            pp.setSyerPembilang(hpb.getSyerPembilang());
                            pp.setSyerPenyebut(hpb.getSyerPenyebut());
                            pp.setHakmilik(hm);
                            pp.setJenis(hpb.getJenis());
                            senaraiPemohonBerangkai.add(pp);
                        }
                    }

                    LOG.debug("size berangkai = " + senaraiPemohonBerangkai.size());

                    if (senaraiPemohonBerangkai.size() > 0) {
                        getContext().getRequest().setAttribute("berangkai", Boolean.TRUE);
                    }

                }
            } else {
                List<HakmilikPihakBerkepentingan> senarai2 = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hm);
                List<HakmilikPihakBerkepentingan> senarai = new ArrayList<HakmilikPihakBerkepentingan>();

                for (HakmilikPihakBerkepentingan hp : senarai2) {
                    if (p.getKodUrusan().getKod().equals("TRPA") || p.getKodUrusan().getKod().equals("PNPAB")) {
                        if (hp.getJenis().getKod().equals("PA")) {
                            senarai.add(hp);
                        }
                    } else {
                        senarai.add(hp);
                    }
                }

                //TODO by kod urusan
                checkPihakValidate(senarai);
                mohonPihakList = permohonanPihakService.getSenaraiMohonPihakForMultipleHakmilik(idPermohonan, idHakmilik);
                syer1 = new String[mohonPihakList.size()];
                syer2 = new String[mohonPihakList.size()];
                for (int i = 0; i < mohonPihakList.size(); i++) {
                    PermohonanPihak pp = mohonPihakList.get(i);
                    syer1[i] = String.valueOf(pp.getSyerPembilang());
                    syer2[i] = String.valueOf(pp.getSyerPenyebut());
                }
            }
            mapList = p.getSenaraiPermohonanAtasPihakBerkepentingan();

            LOG.debug("size = " + mapList.size());

            List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();

            List<PermohonanAtasPihakBerkepentingan> senaraiTmp = new ArrayList<PermohonanAtasPihakBerkepentingan>();

            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hmk = hp.getHakmilik();
                if (hmk.getIdHakmilik().equals(idHakmilik)) {
                    List<HakmilikPihakBerkepentingan> list = hmk.getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hpk : list) {
                        if (hpk == null) {
                            continue;
                        }
                        if (p.getKodUrusan().getKod().equals("TRPA")) {
                            if (!hpk.getJenis().getKod().equals("PA")) {
                                continue;
                            }
                        }

                        for (PermohonanAtasPihakBerkepentingan map : mapList) {
                            if (map.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan()
                                    == hpk.getIdHakmilikPihakBerkepentingan()) {
                                senaraiTmp.add(map);
                            }
                        }

                    }
                }
            }

            mapList = senaraiTmp;

            pemohonList = pemohonService.senaraiPemohonByIdPermohonanIdHakmilik(idPermohonan, idHakmilik);
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new JSP("daftar/kemaskini_pb_partial.jsp").addParameter("tab", "true");
    }

    public void checkPihakValidate(List<HakmilikPihakBerkepentingan> senarai) {
        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
        long idPihak = 0;
        for (HakmilikPihakBerkepentingan hp : senarai) {
            boolean flag = true;
            if (hp == null || hp.getPihak() == null) {
                continue;
            }
            if (isDebug) {
                LOG.debug("prev ID pihak = " + idPihak);
                LOG.debug("current ID pihak = " + hp.getIdHakmilikPihakBerkepentingan());
            }
            if (idPihak == hp.getIdHakmilikPihakBerkepentingan()) {
                continue;
            }

            if (p.getKodUrusan().getKod().equals("PMT")
                    || p.getKodUrusan().getKod().equals("GD")) {

                for (HakmilikPermohonan hpm : senaraiHakmilik) {
                    if (hpm == null || hpm.getHakmilik() == null) {
                        continue;
                    }
                    HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(hp.getPihak(), hpm.getHakmilik());
                    if (hpk == null) {
                        if (isDebug) {
                            LOG.debug("pihak NOT FOUND in hakmilik " + hpm.getHakmilik().getIdHakmilik());
                        }
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                pihakKepentinganList.add(hp);
            }
            idPihak = hp.getIdHakmilikPihakBerkepentingan();
        }
    }

    public Resolution simpanPemohonMultipleHakmilik() {

        Hakmilik hakmilik = p.getSenaraiHakmilik().get(0).getHakmilik();
        List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();
        String[] param = getContext().getRequest().getParameterValues("idPihak");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        for (String idPihak : param) {
            Pihak pi = pihakService.findById(idPihak);

            Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pi, hakmilik, null);

            if (pmohon != null) {
                continue;
            }
//            if (pmohon == null) {
//
//            }
            Pemohon pe = new Pemohon();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            pe.setInfoAudit(ia);
            pe.setPermohonan(p);
            pe.setHakmilik(hakmilik);
            pe.setPihak(pi);
            pe.setCawangan(p.getCawangan());
            senaraiPemohon.add(pe);
        }
        if (senaraiPemohon.size() > 0) {
            pemohonService.saveOrUpdateMultiple(senaraiPemohon);
        }
//        return new JSP("daftar/kemaskini_pb_partial.jsp").addParameter("tab", "true");
        return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution simpanPemegangAmanah() {

        Permohonan permohonan = null;
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new Date());

        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        String nama = getContext().getRequest().getParameter("nama");
        String kp = getContext().getRequest().getParameter("kp");
        String noKp = getContext().getRequest().getParameter("noKp");
        String add1 = getContext().getRequest().getParameter("add1");
        String add2 = getContext().getRequest().getParameter("add2");
        String add3 = getContext().getRequest().getParameter("add3");
        String add4 = getContext().getRequest().getParameter("add4");
        String poskod = getContext().getRequest().getParameter("poskod");
        String negeri = getContext().getRequest().getParameter("negeri");

        Hakmilik hmk = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hmk = hakmilikDAO.findById(idHakmilik);
        } else {
            hmk = p.getSenaraiHakmilik().get(0).getHakmilik();
        }

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }

        if (StringUtils.isNotBlank(kp) && StringUtils.isNotBlank(noKp)) {
            Pihak phk = pihakService.findPihak(kp, noKp);
            if (phk == null) {
                phk = new Pihak();
                phk.setAlamat1(add1);
                phk.setAlamat2(add2);
                phk.setAlamat3(add3);
                phk.setAlamat4(add4);
                phk.setPoskod(poskod);
                phk.setNegeri(kodNegeriDAO.findById(negeri));
                phk.setNama(nama);
                phk.setWargaNegara(kodWarganegaraDAO.findById("MAL"));
                phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(kp));
                phk.setNoPengenalan(noKp);
                phk.setInfoAudit(info);
            }
            PermohonanPihak pp = new PermohonanPihak();
            pp.setInfoAudit(info);
            pp.setCawangan(permohonan.getCawangan());
            pp.setPermohonan(permohonan);
            KodJenisPihakBerkepentingan jenis = kodJenisPihakBerkepentinganDAO.findById("WAR");
            pp.setJenis(jenis);
            pp.setHakmilik(hmk);
            pp.setSyerPembilang(0);
            pp.setSyerPenyebut(0);
            pp.setKaitan(permohonanPihakHubungan.getKaitan());
            pp.setPihak(phk);
            pihakService.saveOrUpdatePihakKepentinganPihak(phk, pp);

        }
        return new StreamingResolution("text/plain", "1");
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

    public PermohonanPihakHubungan getPermohonanPihakHubungan() {
        return permohonanPihakHubungan;
    }

    public void setPermohonanPihakHubungan(PermohonanPihakHubungan permohonanPihakHubungan) {
        this.permohonanPihakHubungan = permohonanPihakHubungan;
    }

    public List<Pihak> getPihakByNameList() {
        return pihakByNameList;
    }

    public void setPihakByNameList(List<Pihak> pihakByNameList) {
        this.pihakByNameList = pihakByNameList;
    }

    public List<KodBangsa> getSenaraiKodBangsaOrang() {
        return senaraiKodBangsaOrang;
    }

    public void setSenaraiKodBangsaOrang(List<KodBangsa> senaraiKodBangsaOrang) {
        this.senaraiKodBangsaOrang = senaraiKodBangsaOrang;
    }

    public List<KodBangsa> getSenaraiKodBangsaSyarikat() {
        return senaraiKodBangsaSyarikat;
    }

    public void setSenaraiKodBangsaSyarikat(List<KodBangsa> senaraiKodBangsaSyarikat) {
        this.senaraiKodBangsaSyarikat = senaraiKodBangsaSyarikat;
    }

    public List<PermohonanPihak> getSenaraiPihakHubungan() {
        return senaraiPihakHubungan;
    }

    public void setSenaraiPihakHubungan(List<PermohonanPihak> senaraiPihakHubungan) {
        this.senaraiPihakHubungan = senaraiPihakHubungan;
    }

    public String getJeniskp() {
        return jeniskp;
    }

    public void setJeniskp(String jeniskp) {
        this.jeniskp = jeniskp;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihak;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihak = hakmilikPihak;
    }
}
