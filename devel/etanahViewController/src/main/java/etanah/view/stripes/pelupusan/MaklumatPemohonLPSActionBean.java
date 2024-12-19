/**
 *
 * @author muhammad.amin
 * Modified By Rohan
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodBangsaDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSukuDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PemohonHubunganDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PihakPengarahDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.KodNegeri;
import etanah.model.KodWarganegara;
import etanah.model.PemohonHubungan;
import etanah.model.PihakPengarah;
import etanah.service.PelupusanService;
import etanah.service.common.CawanganService;
import etanah.view.ListUtil;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import org.hibernate.Session;

@UrlBinding("/pelupusan/maklumat_pemohon_lps")
public class MaklumatPemohonLPSActionBean extends AbleActionBean {

    @Inject
    KodBangsaDAO kodBangsaDAO;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    CawanganService cawanganService;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PemohonHubunganDAO pemohonHubunganDAO;
    @Inject
    PihakPengarahDAO pihakPengarahDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    ListUtil listUtil;
    @Inject
    KodSukuDAO kodSukuDAO;
    private PemohonHubungan pemohonHbgn;
    // private PihakPengarah senaraiPengarah;
    private KodCawangan kodCawangan;
    private Permohonan permohonan;
    private Pihak pihak;
    private Pihak pihak1;
    private PihakPengarah pihakPengarah;
    private PihakCawangan pihakCawangan;
    private PermohonanPihak permohonanPihak;
    private List<Pemohon> pemohonList;
    private List<PihakPengarah> pihakPengarahList;
    private List<PihakCawangan> cawanganList;
    private List<PemohonHubungan> pemohonHubunganList;
    private List<PemohonHubungan> pemohonHubunganList1;
    private List<PemohonHubungan> pemohonHubunganList2;
    private List<KodBangsa> senaraiKodBangsa;
    private List<KodJenisPengenalan> senaraiJenisPengenalan;
    private ArrayList hakmilikList = new ArrayList();
    private String idPermohonan;
    private String idPihak;
    private String idPemohon;
    private Long idHubungan;
    private Permohonan p;
    private String idPengarah;
    private Pengguna pguna;
    private Pemohon pemohon;
    String tarikhLahir;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    boolean tambahCawFlag;
    private String idCawangan;
    private String kod;
    private String kod1;
    private String kodW;
    private Date tarik;
    private Long hbgn;
    private static final Logger LOG = Logger.getLogger(MaklumatPemohonActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String kodBangsa;
    private String kodNegeri;
    private String suratNegeri;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_pemohon_lps.jsp").addParameter("tab", "true");
    }

    public Resolution showTambahPemohon() {
        pemohonHbgn = new PemohonHubungan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahLatarbelakangPemohon() {

        pemohonHbgn = new PemohonHubungan();
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_latarbelakang_pemohon.jsp").addParameter("popup", "true");
    }

    /**
     * Latar belakang pemohon - maklumat suami/isteri pemohon
     * 
     * @return Resolution JSP
     * @author hairudin
     * @version 1.0 7 May 2010
     */
    public Resolution showLatarBelakangPemohon() {
        return new JSP("pelupusan/latarbelakang_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showMaklumatAnak() {
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_maklumat_anak.jsp").addParameter("tab", "true");
    }

    public Resolution showMaklumatAhliLembaga() {
        pihakPengarah = new PihakPengarah();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = permohonanService.findById(idPermohonan);
        if (pemohon != null) {
            pihak1 = pihakService.findById(String.valueOf(pemohon.getPihak().getIdPihak()));
        }
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPihak = (String) getContext().getRequest().getSession().getAttribute("idPihak");
        idPengarah = (String) getContext().getRequest().getSession().getAttribute("idPengarah");
        p = permohonanService.findById(idPermohonan);

        if (p != null) {
            idPermohonan = p.getIdPermohonan();
            pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
            if (pemohon != null) {
                pihak = pemohon.getPihak();
            }

            if (pemohon != null) {
                pemohonHubunganList = pelupusanService.findHubunganByIDPemohon(pemohon.getIdPemohon());
                pemohonHubunganList1 = pelupusanService.findHubunganByIDANAK(pemohon.getIdPemohon());

                if (pemohon.getPihak() != null) {
                    pihakPengarahList = pihakService.findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
                }

            }

            if (!pemohonList.isEmpty()) {
                hakmilikList.clear();
                for (int i = 0; i < pemohonList.size(); i++) {
                    Pihak phk = new Pihak();
                    phk = pemohonList.get(i).getPihak();
                    String[] tname = {"pihak"};
                    Object[] model = {phk};
                    List<HakmilikPihakBerkepentingan> pbList;
                    pbList = hakmilikPihakBerkepentinganDAO.findByEqualCriterias(tname, model, null);
                    if (!pbList.isEmpty()) {
                        for (HakmilikPihakBerkepentingan hpb : pbList) {
                            Hakmilik hak = new Hakmilik();
                            hak = hakmilikDAO.findById(hpb.getHakmilik().getIdHakmilik());
                            hakmilikList.add(hak);
                        }
                    }
                }
            }

        }
        if ((pemohonList == null) || (pemohonList != null && pemohonList.size() == 0)) {
            getContext().getRequest().setAttribute("showTambah", Boolean.TRUE);
        }
    }

    public Resolution refreshMaklumatPemohon() {
        rehydrate();
        return new RedirectResolution(MaklumatPemohonActionBean.class, "locate");
    }

    public Resolution cariSyarikat() {
        if (pihak != null) {
            if (pihak.getJenisPengenalan().equals("L") && pihak.getNoPengenalan() != null) {
                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
                boolean duplicateFlag = false;
                if (pihakSearch != null) {
                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = permohonanService.findById(idPermohonan);
                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
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
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
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

        return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("popup", "true");
    }

    public Resolution cariPihakPemohon() {

        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {

                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null) {

                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = permohonanService.findById(idPermohonan);

                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
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
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
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

        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    /**
     * Cari maklumat suami/isteri pemohon
     * 
     * @return Resolution JSP
     * @author hairudin
     * @version 1.0 16 May 2010
     */
    public Resolution cariLatarbelakangPemohon() {

        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
                boolean duplicateFlag = false;
                if (pihakSearch != null) {
                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = permohonanService.findById(idPermohonan);
                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
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
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
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
        return new JSP("pelupusan/tambah_latarbelakang_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPemohonSuamiIsteri1() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = permohonanService.findById(idPermohonan);
        if (validationIsteri()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_latarbelakang_pemohon.jsp").addParameter("tab", "true");
        }

        if (pemohonHbgn != null) {
            if (tarikhLahir != null) {
                try {
                    pemohonHbgn.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                }
            }

            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            pemohonHbgn.setPemohon(pemohon);
            pemohonHbgn.setInfoAudit(info);
            pemohonHbgn.setCawangan(permohonan.getCawangan());
//            pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tambah_latarbelakang_pemohon.jsp").addParameter("tab", "true");
    }

    public boolean validation() {
        boolean flag = false;
        if ((pemohonHbgn.getNama() == null) || (pemohonHbgn.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pemohonHbgn.getKodJantina().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Jantina ");
        } else if ((pemohonHbgn.getUmur() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Umur");
        }

        return flag;
    }

    public boolean Pemohonvalidation() {
        boolean flag = false;
        if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pihak.getBangsa() == null) {
            flag = true;
            addSimpleError("Sila Pilih Bangsa");
        } else if ((pihak.getJenisPengenalan().getKod().endsWith("B")) || (pihak.getJenisPengenalan().getKod().endsWith("L")) || (pihak.getJenisPengenalan().getKod().endsWith("P")) || (pihak.getJenisPengenalan().getKod().endsWith("T")) || (pihak.getJenisPengenalan().getKod().endsWith("I")) || (pihak.getJenisPengenalan().getKod().endsWith("F")) || (pihak.getJenisPengenalan().getKod().endsWith("O")) || (pihak.getJenisPengenalan().getKod().endsWith("N"))) {
            if (pihak.getKodJantina().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Jantina");
            } else if ((pihak.getTempatLahir() == null) || (pihak.getTempatLahir().trim().equals(""))) {
                flag = true;
                addSimpleError(" Sila Masukkan TempatLahir");
            } else if (pemohon.getUmur() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Umur");
            } else if (pihak.getWargaNegara().getKod().endsWith("0")) {
                flag = true;
                addSimpleError("Sila Masukkan Warganegara");
            } else if (pihak.getNegeriKelahiran() == null) {
                flag = true;
                addSimpleError("Sila Pilih Anak");
            } else if (pemohon.getTempohMastautin() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Tempoh tinggal di Melaka");
            } else if ((pemohon.getPekerjaan() == null) || (pemohon.getPekerjaan().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Pekerjaan");
            } else if (pemohon.getPendapatan() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Pendapatan");
            } else if (pemohon.getTanggungan() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Tanggungan");
            }
        } else if ((pihak.getAlamat1() == null) || (pihak.getAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Alamat");
        } else if (pihak.getPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Poskod");
        } else if (pihak.getNegeri().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Negeri");

        } else if ((pihak.getSuratAlamat1() == null) || (pihak.getSuratAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan SuratAlamat");
        } else if (pihak.getSuratPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan SuratPoskod");
        } else if (pihak.getSuratNegeri().getKod().endsWith("0")) {
            flag = true;
            addSimpleError("Sila Pilih SuratNegeri");

        } else if (pihak.getNoTelefon1() == null) {
            flag = true;
            addSimpleError(" Sila Masukkan TelefonNumber");
        }
        return flag;
    }

    public boolean EditPemohonValidation() {
        boolean flag = false;
        if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pihak.getBangsa() == null) {
            flag = true;
            addSimpleError("Sila Pilih Bangsa");
        }
        if ((pihak.getJenisPengenalan().getKod().endsWith("B")) || (pihak.getJenisPengenalan().getKod().endsWith("L")) || (pihak.getJenisPengenalan().getKod().endsWith("P")) || (pihak.getJenisPengenalan().getKod().endsWith("T")) || (pihak.getJenisPengenalan().getKod().endsWith("I")) || (pihak.getJenisPengenalan().getKod().endsWith("F")) || (pihak.getJenisPengenalan().getKod().endsWith("O")) || (pihak.getJenisPengenalan().getKod().endsWith("N"))) {

            if (pihak.getKodJantina().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Jantina");
            } else if ((pihak.getTempatLahir() == null) || (pihak.getTempatLahir().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan TempatLahir");
            } else if (pemohon.getUmur() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Umur");
            } else if (pihak.getWargaNegara().getKod().endsWith("0")) {
                flag = true;
                addSimpleError("Sila Masukkan Warganegara");
            } else if (pihak.getNegeriKelahiran() == null) {
                flag = true;
                addSimpleError("Sila Pilih Anak");
            } else if (pemohon.getTempohMastautin() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Tempoh tinggal di Melaka");
            } else if ((pemohon.getPekerjaan() == null) || (pemohon.getPekerjaan().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Pekerjaan");
            } else if (pemohon.getPendapatan() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Pendapatan");
            } else if (pemohon.getTanggungan() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Tanggungan");
            }
        } else if ((pihak.getAlamat1() == null) || (pihak.getAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Alamat");
        } else if (pihak.getPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Poskod");
        } else if (pihak.getNegeri().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Negeri");

        } else if ((pihak.getSuratAlamat1() == null) || (pihak.getSuratAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan SuratAlamat");
        } else if (pihak.getSuratPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan SuratPoskod");
        } else if (pihak.getSuratNegeri().getKod().endsWith("0")) {
            flag = true;
            addSimpleError("Sila Pilih SuratNegeri");

        } else if (pihak.getNoTelefon1() == null) {
            flag = true;
            addSimpleError("Sila Masukkan TelefonNumber");
        }

        return flag;
    }

    public boolean validateAhilLembangaPengarah() {
        boolean flag = false;
        System.out.println("nama" + pihakPengarah.getNama());

        if ((pihakPengarah.getNama() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pihakPengarah.getNoPengenalan() == null) {
            flag = true;
            addSimpleError("Sila Masukkan NoPengenalan");
        } else if ((pihak1.getAlamat1() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Alamat");
        } else if (pihak1.getPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Poskod");
        } else if (pihak1.getNegeri().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Negeri");
        } else if (pihakPengarah.getJumlahSaham() == null) {
            flag = true;
            addSimpleError("Sila Masukkan JumlahSaham");
        }
        return flag;
    }

    public boolean validateEditAhilLembagaPengarah() {
        boolean flag = false;
        if ((pihakPengarah.getNama() == null) || (pihakPengarah.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if ((pihakPengarah.getPihak().getAlamat1() == null) || (pihakPengarah.getPihak().getAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Alamat");
        } else if (pihakPengarah.getPihak().getPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Poskod");
        } else if (kod.equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Negeri");
        } else if (pihakPengarah.getJumlahSaham() == null) {
            flag = true;
            addSimpleError("Sila Masukkan JumlahSaham");
        }
        return flag;

    }

    public boolean validationIsteri() {

        boolean flag = false;
        if ((pemohonHbgn.getNama() == null) || (pemohonHbgn.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pemohonHbgn.getJenisPengenalan().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih JenisPengenalan");

        } else if ((pemohonHbgn.getNoPengenalan() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan NoPengenalan");
        } else if (pemohonHbgn.getKaitan().endsWith("0")) {
            flag = true;
            addSimpleError("Sila Pilih Kaitan");

        } else if (pemohonHbgn.getUmur() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Umur");
        } else if ((pemohonHbgn.getTempatLahir() == null) || (pemohonHbgn.getTempatLahir().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan TempatLahir");
        } else if (pemohonHbgn.getWarganegara().getKod().endsWith("0")) {
            flag = true;
            addSimpleError("Sila Masukkan Warganegara");

        } else if ((pemohonHbgn.getPekerjaan() == null) || (pemohonHbgn.getPekerjaan().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Pekerjaan");
        } else if ((pemohonHbgn.getAlamat1() == null) || (pemohonHbgn.getAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Alamat");

        } else if (pemohonHbgn.getPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Poskod");
        } else if (pemohonHbgn.getNegeri().getKod().endsWith("0")) {
            flag = true;
            addSimpleError("Sila Pilih Negeri");
        } else if ((pemohon.getPekerjaan() == null) || (pemohon.getPekerjaan().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Pekerjaan");
        } else if (pemohon.getPendapatan() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Pendapatan");
        } else if (pemohon.getTanggungan() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Tanggungan");
        }
        return flag;

    }

    public boolean validationEditIsteri() {

        boolean flag = false;
        if ((pemohonHbgn.getNama() == null) || (pemohonHbgn.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pemohonHbgn.getKaitan().endsWith("0")) {
            flag = true;
            addSimpleError("Sila Pilih Kaitan");

        } else if (pemohonHbgn.getTarikhLahir() == null) {
            flag = true;
            addSimpleError("Sila Masukkan TarikhLahir");
        } else if (pemohonHbgn.getUmur() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Umur");
        } else if ((pemohonHbgn.getTempatLahir() == null) || (pemohonHbgn.getTempatLahir().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan TempatLahir");
        } else if (pemohonHbgn.getWarganegara().getKod().endsWith("0")) {
            flag = true;
            addSimpleError("Sila Masukkan Warganegara");

        } else if ((pemohonHbgn.getPekerjaan() == null) || (pemohonHbgn.getPekerjaan().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Pekerjaan");
        } else if ((pemohonHbgn.getAlamat1() == null) || (pemohonHbgn.getAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Alamat");
        } else if (pemohonHbgn.getPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Poskod");
        } else if (pemohonHbgn.getNegeri().getKod().endsWith("0")) {
            flag = true;
            addSimpleError("Sila Pilih Negeri");

        }
        return flag;
    }

    public Resolution simpanPemohonAnak() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = permohonanService.findById(idPermohonan);

        if (validation()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_maklumat_anak.jsp").addParameter("tab", "true");
        }
        if (pemohonHbgn != null) {

            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            pemohonHbgn.setKaitan("ANAK");
            pemohonHbgn.setPemohon(pemohon);
            pemohonHbgn.setInfoAudit(info);
            pemohonHbgn.setCawangan(permohonan.getCawangan());
//            pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tambah_maklumat_anak.jsp").addParameter("tab", "true");

    }

    public Resolution simpanMaklumatAhliLembaga() {
        if (validateAhilLembangaPengarah()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
        }

        if (pihakPengarah != null) {
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());

            pihakPengarah.setPihak(pemohon.getPihak());
            pihakPengarah.setInfoAudit(info);
            pihakPengarah.setAktif('Y');

            Pihak pihakTemp = pihakDAO.findById(pihak1.getIdPihak());

            pihakTemp.setAlamat1(pihak1.getAlamat1());
            pihakTemp.setAlamat2(pihak1.getAlamat2());
            pihakTemp.setAlamat3(pihak1.getAlamat3());
            pihakTemp.setAlamat4(pihak1.getAlamat4());
            pihakTemp.setPoskod(pihak1.getPoskod());
            pihakTemp.setNegeri(pihak1.getNegeri());
            pihakPengarah.setPihak(pihakTemp);

            pihakService.simpanPemohonHbgn(pihakPengarah, pihakTemp);
        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPemohonPopup() throws ParseException {
        cariFlag = true;
        tiadaDataFlag = true;

        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());

        if (Pemohonvalidation()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
        }

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        Pihak pihakTemp = new Pihak();
        String idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
        if (idPihak != null && !idPihak.equals("0")) {
            pihakTemp = pihakDAO.findById(Long.parseLong(idPihak));
            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }


        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }


        pihakTemp.setInfoAudit(infoAudit);
        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pihakTemp);
        pmohon.setInfoAudit(infoAudit);
        pmohon.setCawangan(permohonan.getCawangan());

        if (idCawangan != null) {
            pihakCawangan = new PihakCawangan();
            pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
            pmohon.setPihakCawangan(pihakCawangan);

        }

        if (pmohon != null) {

            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
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
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setTarikhLahir(pihak.getTarikhLahir());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());


            if ((pihak.getJenisPengenalan().getKod().endsWith("B")) || (pihak.getJenisPengenalan().getKod().endsWith("L")) || (pihak.getJenisPengenalan().getKod().endsWith("P")) || (pihak.getJenisPengenalan().getKod().endsWith("T")) || (pihak.getJenisPengenalan().getKod().endsWith("I")) || (pihak.getJenisPengenalan().getKod().endsWith("F")) || (pihak.getJenisPengenalan().getKod().endsWith("O")) || (pihak.getJenisPengenalan().getKod().endsWith("N"))) {
                pmohon.setPekerjaan(pemohon.getPekerjaan());
                pmohon.setUmur(pemohon.getUmur());
                pmohon.setPendapatan(pemohon.getPendapatan());
                pmohon.setTempohMastautin(pemohon.getTempohMastautin());
                pmohon.setTanggungan(pemohon.getTanggungan());
                pmohon.setInstitusi(pemohon.getInstitusi());
                pmohon.setInstitusiAlamat1(pemohon.getInstitusiAlamat1());
                pmohon.setInstitusiAlamat2(pemohon.getInstitusiAlamat2());
                pmohon.setInstitusiAlamat3(pemohon.getInstitusiAlamat3());
                pmohon.setInstitusiAlamat4(pemohon.getInstitusiAlamat4());
                pmohon.setInstitusiPoskod(pemohon.getInstitusiPoskod());

            }

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
            pemohonService.simpanPihakPemohon(pihakTemp, pmohon);
            rehydrate();
            getContext().getRequest().setAttribute("flag", Boolean.FALSE);

            return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpaneditLembaga() {
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        if (validateEditAhilLembagaPengarah()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/edit_ahil_lembaga_pengarah.jsp").addParameter("tab", "true");
        }

        PihakPengarah pihakPengarahTemp = pihakPengarahDAO.findById(pihakPengarah.getIdPengarah());

        Pihak pihakTemp = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());

        pihakPengarahTemp.setNama(pihakPengarah.getNama());
        pihakPengarahTemp.setNoPengenalan(pihakPengarah.getNoPengenalan());
        pihakPengarahTemp.setJumlahSaham(pihakPengarah.getJumlahSaham());

        pihakTemp.setAlamat1(pihakPengarah.getPihak().getAlamat1());
        pihakTemp.setAlamat2(pihakPengarah.getPihak().getAlamat2());
        pihakTemp.setAlamat3(pihakPengarah.getPihak().getAlamat3());
        pihakTemp.setAlamat4(pihakPengarah.getPihak().getAlamat4());
        pihakTemp.setPoskod(pihakPengarah.getPihak().getPoskod());

        KodNegeri kn = kodNegeriDAO.findById(kod);
        pihakTemp.setNegeri(kn);

        pihakService.saveOrUpdate(pihakPengarahTemp, pihakTemp);

        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/edit_ahil_lembaga_pengarah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEditSuamiIsteri() {

        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (validationEditIsteri()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/edit_suami.jsp").addParameter("tab", "true");
        }
        if (pemohonHbgn != null) {
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            PemohonHubungan pemohonHubunganTemp = pemohonHubunganDAO.findById(pemohonHbgn.getIdHubungan());
            pemohonHubunganTemp.setNama(pemohonHbgn.getNama());
            pemohonHubunganTemp.setTarikhLahir(pemohonHbgn.getTarikhLahir());
            pemohonHubunganTemp.setTempatLahir(pemohonHbgn.getTempatLahir());
            pemohonHubunganTemp.setPekerjaan(pemohonHbgn.getPekerjaan());
            pemohonHubunganTemp.setKaitan(pemohonHbgn.getKaitan());
            pemohonHubunganTemp.setAlamat1(pemohonHbgn.getAlamat1());
            pemohonHubunganTemp.setAlamat2(pemohonHbgn.getAlamat2());
            pemohonHubunganTemp.setAlamat3(pemohonHbgn.getAlamat3());
            pemohonHubunganTemp.setAlamat4(pemohonHbgn.getAlamat4());
            pemohonHubunganTemp.setPoskod(pemohonHbgn.getPoskod());
            pemohonHubunganTemp.setWarganegara(pemohonHbgn.getWarganegara());
            KodNegeri kn = kodNegeriDAO.findById(pemohonHbgn.getNegeri().getKod());
            pemohonHubunganTemp.setNegeri(kn);
//            pemohonService.saveOrUpdateHbgn(pemohonHubunganTemp);
        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/edit_suami.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEditAnak() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = permohonanService.findById(idPermohonan);

        if (validation()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/edit_Anak.jsp").addParameter("tab", "true");
        }

        if (pemohonHbgn != null) {
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pguna);
            pemohonHbgn.setKaitan("ANAK");
            pemohonHbgn.setPemohon(pemohon);
            pemohonHbgn.setInfoAudit(infoAudit);
            pemohonHbgn.setCawangan(permohonan.getCawangan());
            infoAudit.setTarikhKemaskini(new java.util.Date());
            PemohonHubungan pemohonHubunganTemp = pemohonHubunganDAO.findById(pemohonHbgn.getIdHubungan());
            pemohonHubunganTemp.setKodJantina(pemohonHbgn.getKodJantina());
            pemohonHubunganTemp.setNama(pemohonHbgn.getNama());
            pemohonHubunganTemp.setUmur(pemohonHbgn.getUmur());

            pemohonHubunganTemp.setInstitusi(pemohonHbgn.getInstitusi());
//            pemohonService.saveOrUpdateHbgn(pemohonHubunganTemp);
        }

        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/edit_Anak.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEditPemohon() {

        if (EditPemohonValidation()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/edit_pemohon.jsp").addParameter("tab", "true");
        }
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());


        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
        KodBangsa kodBangsa1 = kodBangsaDAO.findById(kodBangsa);
        pihakTemp.setBangsa(kodBangsa1);
        KodNegeri kn = kodNegeriDAO.findById(kodNegeri);
        pihakTemp.setNegeri(kn);
        KodNegeri kodn = kodNegeriDAO.findById(suratNegeri);
        pihakTemp.setSuratNegeri(kodn);
        pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
        pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
        pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
        pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
        pihakTemp.setSuratPoskod(pihak.getSuratPoskod());

        pihakService.saveOrUpdate(pihakTemp);

        Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pihakTemp);
        infoAudit = new InfoAudit();
        infoAudit = pmohon.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        pmohon.setInfoAudit(infoAudit);
        pmohon.setKaitan(pemohon.getKaitan());

        if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {
            pmohon.setStatusKahwin(pemohon.getStatusKahwin());
            pmohon.setPekerjaan(pemohon.getPekerjaan());
            pmohon.setUmur(pemohon.getUmur());
            pmohon.setPendapatan(pemohon.getPendapatan());
            pmohon.setTanggungan(pemohon.getTanggungan());
        }
        pemohonService.saveOrUpdate(pmohon);

        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/edit_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanCawanganPemohon() {

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());

        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);
        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/pelupusan/?getTambahCawanganPemohon&idPihak=" + id).addParameter("popup", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahCawanganPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        cawanganList = pihak.getSenaraiCawangan();
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution editCawanganPemohon() {
        String idCaw = getContext().getRequest().getParameter("idCawangan");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
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
        rehydrate();
        return showForm();
    }

    public Resolution deletePemohonHbgn() {

        String idPemohonHbgn = getContext().getRequest().getParameter("idPemohonHbgn");
        if (idPemohonHbgn != null) {
            PemohonHubungan pemohonHubungan = pemohonHubunganDAO.findById(Long.parseLong(idPemohonHbgn));
            if (pemohonHubungan != null) {
//                pelupusanService.deletePemohonHbgn(pemohonHubungan);

            }
        }
        rehydrate();
        return showForm();
    }

    public Resolution deletePihakPengarah() {
        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        if (idPengarah != null) {
            PihakPengarah pihakPengarah1 = pihakPengarahDAO.findById(Long.parseLong(idPengarah));
            if (pihakPengarah1 != null) {
                pihakService.deletePihakPengarah(pihakPengarah1);
            }
        }
        rehydrate();
        return showForm();
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
                }
            }
        }
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution backCawanganPemohon() {

        pihak = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        cawanganList = pihak.getSenaraiCawangan();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        cariFlag = true;
        tiadaDataFlag = false;
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohon() {
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pemohonService.findPemohonByPermohonanPihak(p, pihak);


        if (pihak.getWargaNegara() != null) {
            kodW = pihak.getWargaNegara().getKod();
        }

        if (pihak.getSuratNegeri() != null) {
            suratNegeri = pihak.getSuratNegeri().getKod();
        }

        if (pihak.getNegeri() != null) {
            kodNegeri = pihak.getNegeri().getKod();
        }

        if (pihak.getBangsa() != null) {
            kodBangsa = pihak.getBangsa().getKod();
        }
        kod = "";
        if (pihak.getNegeri() != null) {
            kod = pihak.getNegeri().getKod();
        }
     
        return new JSP("pelupusan/edit_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditAnak() {

        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        pemohonHbgn = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/edit_Anak.jsp").addParameter("popup", "true");
    }

    public Resolution showEditSuamiIsteri() {
 
        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        pemohonHbgn = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));

        kodW = "";
        if (pemohonHbgn.getWarganegara().getKod() != null) {
            kodW = pemohonHbgn.getWarganegara().getKod();
        }

        return new JSP("pelupusan/edit_suami.jsp").addParameter("popup", "true");
    }

    public Resolution showEditlembaga() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");

        pihakPengarah = pihakPengarahDAO.findById(Long.parseLong(idPengarah));

        kod = "";
        if (pihakPengarah.getPihak().getNegeri().getKod() != null) {
            kod = pihakPengarah.getPihak().getNegeri().getKod();
        }
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/edit_ahil_lembaga_pengarah.jsp").addParameter("popup", "true");
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
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

    public ArrayList getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(ArrayList hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public String getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(String tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List<PemohonHubungan> getPemohonHubunganList() {
        return pemohonHubunganList;
    }

    public void setPemohonHubunganList(List<PemohonHubungan> pemohonHubunganList) {
        this.pemohonHubunganList = pemohonHubunganList;
    }

    public List<PemohonHubungan> getPemohonHubunganList1() {
        return pemohonHubunganList1;
    }

    public void setPemohonHubunganList1(List<PemohonHubungan> pemohonHubunganList1) {
        this.pemohonHubunganList1 = pemohonHubunganList1;
    }

    public List<PemohonHubungan> getPemohonHubunganList2() {
        return pemohonHubunganList2;
    }

    public void setPemohonHubunganList2(List<PemohonHubungan> pemohonHubunganList2) {
        this.pemohonHubunganList2 = pemohonHubunganList2;
    }

    public PemohonHubungan getPemohonHbgn() {
        return pemohonHbgn;
    }

    public void setPemohonHbgn(PemohonHubungan pemohonHbgn) {
        this.pemohonHbgn = pemohonHbgn;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PihakPengarah> getPihakPengarahList() {
        return pihakPengarahList;
    }

    public void setPihakPengarahList(List<PihakPengarah> pihakPengarahList) {
        this.pihakPengarahList = pihakPengarahList;
    }

    public PihakPengarah getPihakPengarah() {
        return pihakPengarah;
    }

    public void setPihakPengarah(PihakPengarah pihakPengarah) {
        this.pihakPengarah = pihakPengarah;
    }

    public Pihak getPihak1() {
        return pihak1;
    }

    public void setPihak1(Pihak pihak1) {
        this.pihak1 = pihak1;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getIdPengarah() {
        return idPengarah;
    }

    public void setIdPengarah(String idPengarah) {
        this.idPengarah = idPengarah;
    }

    public String getKodW() {
        return kodW;
    }

    public void setKodW(String kodW) {
        this.kodW = kodW;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public Long getIdHubungan() {
        return idHubungan;
    }

    public void setIdHubungan(Long idHubungan) {
        this.idHubungan = idHubungan;
    }

    public Date getTarik() {
        return tarik;
    }

    public void setTarik(Date tarik) {
        this.tarik = tarik;
    }

    public String getKod1() {
        return kod1;
    }

    public void setKod1(String kod1) {
        this.kod1 = kod1;
    }

    public String getKodBangsa() {
        return kodBangsa;
    }

    public void setKodBangsa(String kodBangsa) {
        this.kodBangsa = kodBangsa;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public List<KodJenisPengenalan> getSenaraiJenisPengenalan() {
        return senaraiJenisPengenalan;
    }

    public void setSenaraiJenisPengenalan(List<KodJenisPengenalan> senaraiJenisPengenalan) {
        this.senaraiJenisPengenalan = senaraiJenisPengenalan;
    }
}
